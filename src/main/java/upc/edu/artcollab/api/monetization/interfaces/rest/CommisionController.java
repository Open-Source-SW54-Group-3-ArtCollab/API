package upc.edu.artcollab.api.monetization.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.services.CommisionCommandService;
import upc.edu.artcollab.api.monetization.domain.services.CommisionQueryService;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateCommisionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CommisionResourceFromEntityAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CreateCommisionCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @summary
 * Declares rest controller for Commision entity and its related services and classes in the monetization domain.
 * Request mapping is set to /api/v1/monetization/commissions to map the controller to the specified path.
 * The CommisionController class is used to handle the rest api requests related to the Commision entity.
 */

@RestController
@RequestMapping("/api/v1/monetization/commissions")
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

    @PostMapping("/create")
    public ResponseEntity<CommisionResource> createCommision(@RequestBody CreateCommisionResource resource) {
        Optional<Commision> commisionOptional = commissionCommandService.handle(CreateCommisionCommandFromResourceAssembler.toCommandFromResource(resource));
        return commisionOptional.map(commision -> new ResponseEntity<>(CommisionResourceFromEntityAssembler.toResourceFromEntity(commision), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());

    }

}
