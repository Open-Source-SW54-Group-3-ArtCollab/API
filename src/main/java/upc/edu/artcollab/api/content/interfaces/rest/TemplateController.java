package upc.edu.artcollab.api.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.commands.DeleteTemplateCommand;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;
import upc.edu.artcollab.api.content.domain.services.TemplateCommandService;
import upc.edu.artcollab.api.content.domain.services.TemplateQueryService;
import upc.edu.artcollab.api.content.interfaces.rest.resources.CreateTemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.resources.TemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.resources.UpdateTemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.transform.CreateTemplateCommandFromResourceAssembler;
import upc.edu.artcollab.api.content.interfaces.rest.transform.TemplateResourceFromEntityAssembler;
import upc.edu.artcollab.api.content.interfaces.rest.transform.UpdateTemplateCommandFromResourceAssembler;

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
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/templates", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Template", description = "Template Controller")
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden"),
})
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created a new template"
            ),})
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get a template by ID"
            ),})
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get all templates by genre"
            ),})
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Get all templates"
            ),})
    @Operation(summary = "Get all templates", description = "Get all templates.")
    @GetMapping
    public ResponseEntity<List<TemplateResource>> getAllTemplates() {
        var getAllTemplatesQuery = new GetAllTemplatesQuery();
        var templates = templateQueryService.handle(getAllTemplatesQuery);
        var templateResources = templates.stream().map(TemplateResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(templateResources);
    }

    /**
     * Deletes a template by its ID.
     *
     * @param id the ID of the template to delete
     * @return the deleted template resource, or a bad request response if the deletion fails
     */
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Delete a template"
            ),})
    @Operation(summary = "Delete a template", description = "Delete a template.")
    @DeleteMapping("/{id}")
    public ResponseEntity<TemplateResource> deleteTemplate(@PathVariable Integer id) {
        Optional<Template> template = templateCommandService.handle(new DeleteTemplateCommand(id));
        return template.map(source -> new ResponseEntity<>(TemplateResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    /**
     * Update a template
     * @param id
     * @param resource
     * @return the updated template resource, or a not found response if the template does not exist
     */
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Update a template"
            ),})
    @Operation(summary = "Update a template", description = "Update a template.")

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResource> updateTemplate(@PathVariable Integer id, @RequestBody UpdateTemplateResource resource) {
        var command = UpdateTemplateCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var template = templateCommandService.handle(command);
        return template.map(source -> ResponseEntity.ok(TemplateResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
