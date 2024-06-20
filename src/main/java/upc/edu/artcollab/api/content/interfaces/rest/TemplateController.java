package upc.edu.artcollab.api.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;
import upc.edu.artcollab.api.content.domain.services.TemplateCommandService;
import upc.edu.artcollab.api.content.domain.services.TemplateQueryService;
import upc.edu.artcollab.api.content.interfaces.rest.resources.CreateTemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.resources.TemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.transform.CreateTemplateCommandFromResourceAssembler;
import upc.edu.artcollab.api.content.interfaces.rest.transform.TemplateResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Template Controller
 * <p>
 *     This class is responsible for handling the REST API requests related to templates.
 *     It is responsible for creating templates and retrieving templates by ID or genre.
 * </p>
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/templates", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Template", description = "Template Controller")
public class TemplateController {

    private final TemplateCommandService templateCommandService;
    private final TemplateQueryService templateQueryService;

    /**
     * Constructs a new TemplateController.
     *
     * @param templateCommandService the service for handling commands related to templates
     * @param templateQueryService the service for handling queries related to templates
     */
    public TemplateController(TemplateCommandService templateCommandService, TemplateQueryService templateQueryService) {
        this.templateCommandService = templateCommandService;
        this.templateQueryService = templateQueryService;
    }

    /**
     * Creates a new template.
     *
     * @param resource the resource containing the details of the template to be created
     * @return the created template resource, or a bad request response if the creation fails
     */
    @Operation(summary = "Create a template", description = "Create a new template.")
    @PostMapping
    public ResponseEntity<TemplateResource> createTemplate(@RequestBody CreateTemplateResource resource) {
        Optional<Template> template = templateCommandService.handle(CreateTemplateCommandFromResourceAssembler.toCommandFromResource(resource));
        return template.map(source -> new ResponseEntity<>(TemplateResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves a template by its ID.
     *
     * @param id the ID of the template to retrieve
     * @return the template resource, or a not found response if the template does not exist
     */
    @Operation(summary = "Get a template by ID", description = "Get a template by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<TemplateResource> getTemplateById(@PathVariable Integer id) {
        Optional<Template> template = templateQueryService.handle(new GetTemplateByIdQuery(id));
        return template.map(source -> ResponseEntity.ok(TemplateResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all templates by genre.
     *
     * @param genre the genre of the templates to retrieve
     * @return a list of template resources, or a not found response if no templates exist for the genre
     */
    @Operation(summary = "Get all templates by genre", description = "Get all templates by genre.")
    @GetMapping("/genre/{genre}")
    private ResponseEntity<List<TemplateResource>> getAllTemplatesByGenre(@PathVariable String genre) {
        var getAllTemplatesByGenreQuery = new GetAllTemplatesByGenreQuery(genre);
        var templates = templateQueryService.handle(getAllTemplatesByGenreQuery);
        if (templates.isEmpty()) return ResponseEntity.notFound().build();
        var templateResources = templates.stream().map(TemplateResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(templateResources);
    }

    /**
     * Retrieves all templates.
     *
     * @return a list of all template resources
     */
    @Operation(summary = "Get all templates", description = "Get all templates.")
    @GetMapping
    public ResponseEntity<List<TemplateResource>> getAllTemplates() {
        var getAllTemplatesQuery = new GetAllTemplatesQuery();
        var templates = templateQueryService.handle(getAllTemplatesQuery);
        var templateResources = templates.stream().map(TemplateResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(templateResources);
    }
}
