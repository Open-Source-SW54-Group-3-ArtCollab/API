package upc.edu.artcollab.api.monetization.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.DeleteSubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.SubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.resources.UpdateSubscriptionResource;
import upc.edu.artcollab.api.monetization.interfaces.rest.transform.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 *  SubscriptionController
 *  <p>
 *      This class is used to manage the Subscription Controller.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/monetization/subscriptions")
@Tag(name = "Subscription", description = "The Subscription Controller")
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Not authorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden"),
})
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

    @Operation(summary = "Get all Subscriptions")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Subscriptions found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Subscriptions not found"),
    })

    @GetMapping
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
    @Operation(summary = "Create a new Subscription")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Subscription created"),
    })
    @PostMapping
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

    @Operation(summary = "Get a Subscription by its id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Subscription found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Subscription not found"),
    })
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

    @Operation(summary = "Delete a Subscription by its id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Subscription deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Subscription not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id){
        var deleteSubscriptionResource = new DeleteSubscriptionResource(id);
        var command = DeleteSubscriptionCommandFromResourceAssembler.toCommandFromResource(deleteSubscriptionResource);
        subscriptionCommandService.handle(command);
        return ResponseEntity.ok().build();
    }

    /**
     * @summary
     * This method is used to update a Subscription by its id.
     * @param id
     * The id parameter is used to get the id of the Subscription.
     * @return
     * Returns a response entity.
     */


    @Operation(summary = "Update a Subscription by its id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Subscription updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("{id}")
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long id, @RequestBody UpdateSubscriptionResource updateSubscriptionResource){
        var command = UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(id,updateSubscriptionResource);
        Optional<Subscription> subscription = subscriptionCommandService.handle(id,command);
        return subscription.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.fromEntity(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @summary
     * This method is used to get all the active Subscriptions.
     * @return
     * Returns a list of active Subscriptions.
     */

    @Operation(summary = "Get all active Subscriptions")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Subscriptions found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Subscriptions not found"),
    })
    @GetMapping("/active/{active}")

    private ResponseEntity<List<SubscriptionResource>> getAllSubscriptionsActive(@PathVariable boolean active){
        var query = new GetAllSubscriptionsActiveQuery(active);
        var subscriptions = subscriptionQueryService.handle(query);
        if(subscriptions == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        List<SubscriptionResource> subscriptionResources = subscriptions.stream().map( SubscriptionResourceFromEntityAssembler::fromEntity).toList();
        return ResponseEntity.ok(subscriptionResources);
    }

}
