package upc.edu.artcollab.api.content.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.content.domain.model.aggregates.Template;
import upc.edu.artcollab.api.content.domain.model.queries.GetAllTemplatesByGenreQuery;
import upc.edu.artcollab.api.content.domain.model.queries.GetTemplateByIdQuery;
import upc.edu.artcollab.api.content.domain.services.TemplateCommandService;
import upc.edu.artcollab.api.content.domain.services.TemplateQueryService;
import upc.edu.artcollab.api.content.interfaces.rest.resources.CreateTemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.resources.TemplateResource;
import upc.edu.artcollab.api.content.interfaces.rest.transform.CreateTemplateCommandFromResourceAssembler;
import upc.edu.artcollab.api.content.interfaces.rest.transform.TemplateResourceFromEntityAssembler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/templates")
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

    @PostMapping
    public ResponseEntity<TemplateResource> createTemplate(@RequestBody CreateTemplateResource resource) {
        Optional<Template> template = templateCommandService.handle(CreateTemplateCommandFromResourceAssembler.toCommandFromResource(resource));
        return template.map(source -> new ResponseEntity<>(TemplateResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResource> getTemplateById(@PathVariable Integer id) {
        Optional<Template> template = templateQueryService.handle(new GetTemplateByIdQuery(id));
        return template.map(source -> ResponseEntity.ok(TemplateResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<TemplateResource>> getAllTemplatesByGenre(String genre) {
        var getAllTemplatesByGenreQuery = new GetAllTemplatesByGenreQuery(genre);
        var templates = templateQueryService.handle(getAllTemplatesByGenreQuery);
        if (templates.isEmpty()) return ResponseEntity.notFound().build();
        var templateResources = templates.stream().map(TemplateResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(templateResources);
    }

    @GetMapping
    public ResponseEntity<?> getTemplatesWithParameters(@RequestParam Map<String, String> params) {
        if (params.containsKey("genre")) {
            return getAllTemplatesByGenre(params.get("genre"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
