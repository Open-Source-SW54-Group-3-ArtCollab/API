package upc.edu.artcollab.api.monetization.interfaces.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetAllSubscriptionsActiveQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetSubscriptionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionCommandService;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionQueryService;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.CreateSubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.SubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;

import java.util.List;
import java.util.Map;
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
     * @param subscriptionId
     * The id parameter is used to get the id of the Subscription.
     * @return
     */

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getById(@PathVariable Long subscriptionId){
        Optional<Subscription> subscriptionSearched = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(subscriptionId));
        return subscriptionSearched.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.fromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @summary
     * This method is used to delete a Subscription by its id.
     * @param subscriptionId
     * The id parameter is used to get the id of the Subscription.
     * @return
     * Returns a response entity.
     */

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId){
        subscriptionCommandService.delete(new DeleteSubscriptionCommand(subscriptionId));
        return ResponseEntity.ok().build();
    }

    /**
     * @summary
     * This method is used to update a Subscription by its id.
     * @param subscriptionId
     * The id parameter is used to get the id of the Subscription.
     * @return
     * Returns a response entity.
     */


    @PutMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionCommand command){
        Optional<Subscription> subscription = subscriptionCommandService.update(subscriptionId, command);
        return subscription.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.fromEntity(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @summary
     * This method is used to get all the active Subscriptions.
     * @return
     * Returns a list of active Subscriptions.
     */


    private ResponseEntity<List<SubscriptionResource>> getAllSubscriptionsActive(){
        var query = new GetAllSubscriptionsActiveQuery(true);
        var subscriptions = subscriptionQueryService.handle(query);
        if(subscriptions == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        List<SubscriptionResource> subscriptionResources = subscriptions.stream().map( SubscriptionResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(subscriptionResources);
    }

    /**
     * @summary
     * This method is used to get all the Subscriptions with parameters.
     * @param params
     * The params parameter is used to get the parameters to filter the Subscriptions.
     * @return
     * Returns a response entity.
     */

    @GetMapping
    public ResponseEntity<?> getSubscriptionsWithParameters(@RequestParam Map<String, String> params){
        if(params.containsKey("active")){
            return getAllSubscriptionsActive();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
