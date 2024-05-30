package upc.edu.artcollab.api.monetization.interfaces.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetSubscriptionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionCommandService;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionQueryService;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateSubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.SubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/monetization/subscriptions")
public class SubscriptionController {

    private final SubscriptionQueryService subscriptionQueryService;
    private final SubscriptionCommandService subscriptionCommandService;

    public SubscriptionController(SubscriptionQueryService subscriptionQueryService, SubscriptionCommandService subscriptionCommandService) {
        this.subscriptionQueryService = subscriptionQueryService;
        this.subscriptionCommandService = subscriptionCommandService;
    }

    /**
     * @summary
     * This method is used to get all the Subscriptions.
     * @return
     * Returns a list of Subscriptions.
     */

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> getAll(){
        return ResponseEntity.ok(subscriptionQueryService.getAll());
    }

    /**
     * @summary
     * This method is used to get a Subscription by its id.
     * @param resource
     * The resource parameter is used to get the id of the Subscription.
     * @return
     * Returns the Subscription with the specified id.
     */

    @PostMapping("/create")
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource){
        Optional<Subscription> subscription = subscriptionCommandService.handle(CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return subscription.map(value -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.fromEntity(value), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @summary
     * This method is used to get a Subscription by its id.
     * @param id
     * The id parameter is used to get the id of the Subscription.
     * @return
     */

    @GetMapping("{id}")
    public ResponseEntity<SubscriptionResource> getById(@PathVariable Long id){
        Optional<Subscription> subscriptionSearched = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscriptionSearched.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.fromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @summary
     * This method is used to delete a Subscription by its id.
     * @param id
     * The id parameter is used to get the id of the Subscription.
     * @return
     * Returns a response entity.
     */

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id){
        Optional<Subscription> subscriptionSearched = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(id));
        if(subscriptionSearched.isEmpty()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        subscriptionCommandService.delete(subscriptionSearched.get());
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }
}
