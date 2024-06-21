package upc.edu.artcollab.api.monetization.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionsByAmountGreatherThanQuery;
import upc.edu.artcollab.api.monetization.domain.services.CommisionCommandService;
import upc.edu.artcollab.api.monetization.domain.services.CommisionQueryService;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateCommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.UpdateCommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.UpdateSubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CommisionResourceFromEntityAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CreateCommisionCommandFromResourceAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.UpdateCommisionCommandFromResourceAssembler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @summary
 * <p>
 *      Declares rest controller for Commision entity and its related services and classes in the monetization domain.
 *  Request mapping is set to /api/v1/monetization/commissions to map the controller to the specified path.
 *   The CommisionController class is used to handle the rest api requests related to the Commision entity.
 * </p>
 * @author  U202212721 Mathias Jave Diaz
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/monetization/commisions")
@Tag(name = "Commision", description = "Commision Controller")
public class CommisionController {
    /**
     * @summary
     * import CommisionQueryService and CommisionCommandService class from domain services
     */
    private final CommisionQueryService commissionQueryService;
    private final CommisionCommandService commissionCommandService;

    /**
     * @summary
     * Constructor for CommisionController class.
     * @param commissionQueryService
     *  The CommisionQueryService object is injected into the CommisionController class.
     * @param commissionCommandService
     * The CommisionCommandService object is injected into the CommisionController class.
     * The constructor is used to initialize the CommisionQueryService and CommisionCommandService objects.
     */

    public CommisionController(CommisionQueryService commissionQueryService, CommisionCommandService commissionCommandService) {
        this.commissionQueryService = commissionQueryService;
        this.commissionCommandService = commissionCommandService;
    }

    /**
     * @summary
     * This method is used to get all the Commisions.
     * @return
     * Returns a list of Commisions.
     */

    @GetMapping
    public ResponseEntity<List<Commision>> getAll() {
        return ResponseEntity.ok(commissionQueryService.getAll());
    }

    /**
     * @summary
     * This method is used to create a new Commision.
     * The method takes a CreateCommisionResource object as input and returns a ResponseEntity object.
     * @param resource
     * The CreateCommisionResource object to be transformed into a Commision object.
     * @return
     * Returns a ResponseEntity object with the created Commision object.
     */

    @PostMapping
    public ResponseEntity<CommisionResource> createCommision(@RequestBody CreateCommisionResource resource) {
        Optional<Commision> commisionOptional = commissionCommandService.handle(CreateCommisionCommandFromResourceAssembler.toCommandFromResource(resource));
        return commisionOptional.map(commision -> new ResponseEntity<>(CommisionResourceFromEntityAssembler.toResourceFromEntity(commision), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @summary
     * This method is used to get a Commision by its id.
     * The method takes a Long id as input and returns a ResponseEntity object.
     * @param id
     * The id of the Commision to be retrieved.
     * @return
     * Returns a ResponseEntity object with the Commision object.
     */

    @GetMapping("{id}")
    public ResponseEntity<CommisionResource> getCommisionbyId(@PathVariable Long id) {
        Optional<Commision> commisionOptional = commissionQueryService.handle(new GetCommisionByIdQuery(id));
        return commisionOptional.map(commision -> ResponseEntity.ok(CommisionResourceFromEntityAssembler.toResourceFromEntity(commision))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * @summary
     * This method is used to get all the Commisions with amount greater than the specified amount.
     * The method takes a double amount as input and returns a ResponseEntity object.
     * @param amount
     * The amount to be used to filter the Commisions.
     * @return
     * Returns a ResponseEntity object with the list of Commisions.
     */

    @GetMapping({"/amount/{amount}"})

    private ResponseEntity<List<CommisionResource>> getCommisionsByAmountGreatherThan(double amount) {
        var query = new GetCommisionsByAmountGreatherThanQuery(amount);
        var commisions = commissionQueryService.handle(query);
        if(commisions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<CommisionResource> commisionResources = commisions.stream()
                .map(CommisionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commisionResources);
    }




    /**
     * @summary
     * This method is used to update a Commision.
     * @param id The id of the Commision to be updated.
     * @param updateCommisionResource The resource object to be transformed into a Commision object.
     * @return Returns a ResponseEntity object with the updated Commision object.
     */

    @PutMapping("{id}")
    public ResponseEntity<CommisionResource> updateCommision(@PathVariable long id, @RequestBody UpdateCommisionResource updateCommisionResource){
        var command = UpdateCommisionCommandFromResourceAssembler.toCommandFromResource(id,updateCommisionResource);
        Optional<Commision> commisionOptional = commissionCommandService.handle(id,command);
        return commisionOptional.map(commision -> ResponseEntity.ok(CommisionResourceFromEntityAssembler.toResourceFromEntity(commision))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * @summary
     * This method is used to delete a Commision.
     * The method takes a Long id as input and returns a ResponseEntity object.
     * The method first checks if the Commision exists and then deletes the Commision.
     * @param id
     * The id of the Commision to be deleted.
     * @return
     * Returns a ResponseEntity object with the status of the deletion.
     * If the Commision is not found, it returns a 404 not found status.
     */

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCommision(@PathVariable long id){
        var command = new DeleteCommisionCommand(id);
        Optional<Commision> commisionOptional = commissionCommandService.handle(command);
        return commisionOptional.map(commision -> ResponseEntity.ok().build()).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
