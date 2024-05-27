package upc.edu.artcollab.api.users.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByEmailAndPasswordQuery;
import upc.edu.artcollab.api.users.domain.model.queries.GetReaderByIdQuery;
import upc.edu.artcollab.api.users.domain.model.services.ReaderCommandService;
import upc.edu.artcollab.api.users.domain.model.services.ReaderQueryService;
import upc.edu.artcollab.api.users.interfaces.rest.resources.CreateReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.resources.ReaderResource;
import upc.edu.artcollab.api.users.interfaces.rest.transform.CreateReaderCommandFromResourceAssembler;
import upc.edu.artcollab.api.users.interfaces.rest.transform.ReaderResourceFromEntityAssembler;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/readers")
public class ReaderController {
    private final ReaderCommandService readerCommandService;
    private final ReaderQueryService readerQueryService;

    public ReaderController(ReaderCommandService readerCommandService, ReaderQueryService readerQueryService) {
        this.readerCommandService = readerCommandService;
        this.readerQueryService = readerQueryService;
    }

    @PostMapping
    public ResponseEntity<ReaderResource> createReader(@RequestBody CreateReaderResource resource) {
        Optional<Reader> reader = readerCommandService.handle(CreateReaderCommandFromResourceAssembler.toCommandFromResource(resource));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReaderResource> getReaderById(@PathVariable Long id) {
        Optional<Reader> reader = readerQueryService.handle(new GetReaderByIdQuery(id));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{email}/{password}")
    public ResponseEntity<ReaderResource> getReaderByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<Reader> reader = readerQueryService.handle(new GetReaderByEmailAndPasswordQuery(email, password));
        return reader.map(r -> new ResponseEntity<>(ReaderResourceFromEntityAssembler.toResourceFromEntity(r), OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }



}
