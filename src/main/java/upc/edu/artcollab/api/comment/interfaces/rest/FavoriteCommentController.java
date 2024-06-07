package upc.edu.artcollab.api.comment.interfaces.rest;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.comment.domain.model.aggregates.FavoriteComment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllFavoriteCommentByName;
import upc.edu.artcollab.api.comment.domain.model.queries.GetFavoriteCommentByIdQuery;
import upc.edu.artcollab.api.comment.domain.services.FavoriteCommentCommandService;
import upc.edu.artcollab.api.comment.domain.services.FavoriteCommentQueryService;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.CreateFavoriteCommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.FavoriteCommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.FavoriteCommentResourceFromEntityAssembler;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.CreateFavoriteCommentCommandFromResourceAssembler;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/favorite-comments")
public class FavoriteCommentController {
    private final FavoriteCommentCommandService favoriteCommentCommandService;

    private final FavoriteCommentQueryService favoriteCommentQueryService;

    public FavoriteCommentController(FavoriteCommentCommandService favoriteCommentCommandService, FavoriteCommentQueryService favoriteCommentQueryService) {
        this.favoriteCommentCommandService = favoriteCommentCommandService;
        this.favoriteCommentQueryService = favoriteCommentQueryService;
    }

    @PostMapping
    public ResponseEntity<FavoriteCommentResource> createFavoriteComment(@RequestBody CreateFavoriteCommentResource resource) {
        Optional<FavoriteComment> favoriteComment = favoriteCommentCommandService.handle(CreateFavoriteCommentCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteComment.map(comment -> new ResponseEntity<>(FavoriteCommentResourceFromEntityAssembler.toResourceFromEntity(comment), org.springframework.http.HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteCommentResource> getFavoriteComment(@PathVariable Long id) {
        Optional<FavoriteComment> favoriteComment = favoriteCommentQueryService.handle(new GetFavoriteCommentByIdQuery(id));
        return favoriteComment.map(comment -> ResponseEntity.ok(FavoriteCommentResourceFromEntityAssembler.toResourceFromEntity(comment))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<FavoriteCommentResource>> getAllFavoriteCommentsByName(String name) {
        var getAllFavoriteCommentsByNameQuery = new GetAllFavoriteCommentByName(name);
        var favoriteComments = favoriteCommentQueryService.handle(getAllFavoriteCommentsByNameQuery);
        if (favoriteComments.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteCommentResources = favoriteComments.stream().map(FavoriteCommentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(favoriteCommentResources);
    }

    @GetMapping
    public ResponseEntity<?> getAllFavoriteCommentsWithParameters (@RequestParam Map<String, String> params){
        if (params.containsKey("name")){
            return getAllFavoriteCommentsByName(params.get("name"));
        }
        else{
            var favoriteComments = favoriteCommentQueryService.getAllFavoriteComments();
            if (favoriteComments.isEmpty()) return ResponseEntity.notFound().build();
            var favoriteCommentResources = favoriteComments.stream().map(FavoriteCommentResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(favoriteCommentResources);
        }

    }
}
