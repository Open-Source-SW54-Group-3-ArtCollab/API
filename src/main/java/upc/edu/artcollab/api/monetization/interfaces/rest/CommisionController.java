package upc.edu.artcollab.api.monetization.interfaces.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionsByAmountGreatherThanQuery;
import upc.edu.artcollab.api.monetization.domain.services.CommisionCommandService;
import upc.edu.artcollab.api.monetization.domain.services.CommisionQueryService;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateCommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CommisionResourceFromEntityAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CreateCommisionCommandFromResourceAssembler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @summary
 * Declares rest controller for Commision entity and its related services and classes in the monetization domain.
 * Request mapping is set to /api/v1/monetization/commissions to map the controller to the specified path.
 * The CommisionController class is used to handle the rest api requests related to the Commision entity.
 */

@RestController
@RequestMapping("/api/v1/monetization/commisions")
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

    @GetMapping("/all")
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

    @PostMapping("/create")
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
     * This method is used to get all the Commisions with parameters.
     * The method takes a Map<String, String> params as input and returns a ResponseEntity object.
     * @param params
     * The parameters to be used to filter the Commisions.
     * @return
     * Returns a ResponseEntity object with the list of Commisions.
     */

    @GetMapping
    public ResponseEntity<?> getCommisionsWithParameters(@RequestParam Map<String, String> params) {
        if(params.containsKey("amount")){
            return getCommisionsByAmountGreatherThan(Double.parseDouble(params.get("amount")));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @summary
     * This method is used to update a Commision.
     * The method takes a Long id and CreateCommisionResource object as input and returns a ResponseEntity object.
     * The method first checks if the Commision exists and then updates the Commision.
     * @param id
     * The id of the Commision to be updated.
     * @param resource
     * The CreateCommisionResource object to be transformed into a Commision object.
     * @return
     * Returns a ResponseEntity object with the updated Commision object.
     */

    @PutMapping("{id}")
    public ResponseEntity<CommisionResource> updateCommision(@PathVariable Long id, @RequestBody CreateCommisionResource resource){
        Optional<Commision> commisionOptional = commissionQueryService.handle(new GetCommisionByIdQuery(id));
        if(commisionOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Commision existingCommision = commisionOptional.get();
        existingCommision.setAmount(resource.amount());
        existingCommision.setContent(resource.content());
        commissionCommandService.update(existingCommision);
        return ResponseEntity.ok(CommisionResourceFromEntityAssembler.toResourceFromEntity(existingCommision));
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
    public ResponseEntity<?> deleteCommision(@PathVariable Long id){
        Optional<Commision> commisionOptional = commissionQueryService.handle(new GetCommisionByIdQuery(id));
        if(commisionOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        commissionCommandService.delete(commisionOptional.get());
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }

}
