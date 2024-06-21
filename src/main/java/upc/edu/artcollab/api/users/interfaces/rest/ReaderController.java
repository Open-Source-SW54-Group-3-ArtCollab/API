package upc.edu.artcollab.api.users.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;
import upc.edu.artcollab.api.users.domain.services.ReaderCommandService;
import upc.edu.artcollab.api.users.domain.services.ReaderQueryService;
import upc.edu.artcollab.api.users.interfaces.rest.resources.CreateReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.resources.ReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.transform.CreateReaderCommandFromResourceAssembler;
import upc.edu.artcollab.api.users.interfaces.rest.transform.ReaderResourceFromEntityAssembler;

import java.util.Optional;

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
 * @autor Gustavo Huilca Chipana - u202213983
 */

@RestController
@RequestMapping("/api/v1/readers")
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
    @Operation(summary = "Get a Reader by id", description = "Get a Reader by id")
    @GetMapping("{id}")
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
    @GetMapping("{email}/{password}")
    public ResponseEntity<ReaderResource> getReaderByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<Reader> reader = readerQueryService.handle(new GetReaderByEmailAndPasswordQuery(email, password));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
