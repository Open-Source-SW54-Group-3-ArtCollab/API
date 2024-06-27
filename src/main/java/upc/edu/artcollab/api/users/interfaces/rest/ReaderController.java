package upc.edu.artcollab.api.users.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.commands.DeleteReaderCommand;
import upc.edu.artcollab.api.users.domain.model.queries.GetAllReadersQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;
import upc.edu.artcollab.api.users.domain.services.ReaderCommandService;
import upc.edu.artcollab.api.users.domain.services.ReaderQueryService;
import upc.edu.artcollab.api.users.interfaces.rest.resources.CreateReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.resources.ReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.resources.UpdateReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.transform.CreateReaderCommandFromResourceAssembler;
import upc.edu.artcollab.api.users.interfaces.rest.transform.ReaderResourceFromEntityAssembler;
import upc.edu.artcollab.api.users.interfaces.rest.transform.UpdateReaderCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * ReaderController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Reader entity.
 *     It exposes the following endpoints:
 *     - POST /api/v1/readers
 *     - GET /api/v1/readers/{id}
 *     - GET /api/v1/readers/{email}/{password}
 * </p>
 *
 * @version 1.0
 * @author Gustavo Huilca Chipana - u202213983
 */

@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server side"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The request was not authorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "The request was forbidden"),
})
@Tag(name = "Readers", description = "The Reader Controller")
@RestController
@RequestMapping(value = "/api/v1/readers" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ReaderController {
    private final ReaderCommandService readerCommandService;
    private final ReaderQueryService readerQueryService;

    public ReaderController(ReaderCommandService readerCommandService, ReaderQueryService readerQueryService) {
        this.readerCommandService = readerCommandService;
        this.readerQueryService = readerQueryService;
    }

    /**
     * Create a new Reader
     * @param resource the resource containing the data to create the Reader
     * @return the created Reader
     */
    @Operation(summary = "Create a new Reader", description = "Create a new Reader with the data provided in the request body")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "The request was not successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "The Reader was created successfully")
    })
    @PostMapping
    public ResponseEntity<ReaderResource> createReader(@RequestBody CreateReaderResource resource) {
        Optional<Reader> reader = readerCommandService.handle(CreateReaderCommandFromResourceAssembler.toCommandFromResource(resource));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get a Reader by id
     * @param id the id of the Reader
     * @return the Reader
     */
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The Reader was not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Reader was found")
    })
    @Operation(summary = "Get a Reader by id", description = "Get a Reader by id")
    @GetMapping("/{id}")
    public ResponseEntity<ReaderResource> getReaderById(@PathVariable Long id) {
        Optional<Reader> reader = readerQueryService.handle(new GetReaderByIdQuery(id));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get a Reader by email and password
     * @param email the email of the Reader
     * @param password the password of the Reader
     * @return the Reader
     */
    @Operation(summary = "Get a Reader by email and password", description = "Get a Reader by email and password")
    @GetMapping("/{email}/{password}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The Reader was not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Reader was found")
    })
    public ResponseEntity<ReaderResource> getReaderByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<Reader> reader = readerQueryService.handle(new GetReaderByEmailAndPasswordQuery(email, password));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a Reader by id", description = "Delete a Reader by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The Reader was not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Reader was deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ReaderResource> deleteReader(@PathVariable long id){
        var command = new DeleteReaderCommand(id);
        Optional<Reader> reader = readerCommandService.handle(command);
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get all Readers", description = "Get all Readers")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The Readers were not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Readers were found")
    })
    @GetMapping
    public ResponseEntity<List<ReaderResource>> getAllReaders(){
        var query = new GetAllReadersQuery();
        List<Reader> reader = readerQueryService.handle(query);
        var templateResource = reader.stream().map(ReaderResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(templateResource);
    }

    @Operation(summary = "Update a Reader by id", description = "Update a Reader by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The Reader was not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Reader was updated")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReaderResource> updateReader(@PathVariable long id, UpdateReaderResource updateReaderResource){
        var command = UpdateReaderCommandFromResourceAssembler.toCommandFromResource(id,updateReaderResource);
        Optional<Reader> reader = readerCommandService.handle(command);
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
