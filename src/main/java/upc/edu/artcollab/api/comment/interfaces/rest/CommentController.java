package upc.edu.artcollab.api.comment.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.artcollab.api.comment.domain.model.aggregates.Comment;
import upc.edu.artcollab.api.comment.domain.model.queries.GetAllComments;
import upc.edu.artcollab.api.comment.domain.model.queries.GetCommentByIdQuery;
import upc.edu.artcollab.api.comment.domain.services.CommentCommandService;
import upc.edu.artcollab.api.comment.domain.services.CommentQueryService;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.CreateCommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.CommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.DeleteCommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.resource.UpdateCommentResource;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.CommentResourceFromEntityAssembler;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.CreateCommentCommandFromResourceAssembler;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.DeleteCommentCommandFromResourceAssembler;
import upc.edu.artcollab.api.comment.interfaces.rest.transform.UpdateCommentCommandFromResourceAssembler;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/collaboration")
@Tag(name = "Comment", description = "Comment Controller")
public class CommentController {
    private final CommentCommandService commentCommandService;

    private final CommentQueryService commentQueryService;

    public CommentController(CommentCommandService commentCommandService, CommentQueryService commentQueryService) {
        this.commentCommandService = commentCommandService;
        this.commentQueryService = commentQueryService;
    }

    @PostMapping
    public ResponseEntity<CommentResource> createComment(@RequestBody CreateCommentResource resource) {
        Optional<Comment> favoriteComment = commentCommandService.handle(CreateCommentCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteComment.map(comment -> new ResponseEntity<>(CommentResourceFromEntityAssembler.toResourceFromEntity(comment), org.springframework.http.HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResource> getCommentById(@PathVariable Long id) {
        Optional<Comment> favoriteComment = commentQueryService.handle(new GetCommentByIdQuery(id));
        return favoriteComment.map(comment -> ResponseEntity.ok(CommentResourceFromEntityAssembler.toResourceFromEntity(comment))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CommentResource> getAllComments() {
        var query = new GetAllComments();
        var comments = commentQueryService.handle(query);
        return comments.stream().map(CommentResourceFromEntityAssembler::toResourceFromEntity).toList();
    }
    @DeleteMapping("/{id}")
    public Optional<?> deleteComment(@PathVariable Long id){
        var deleteCommentResource = new DeleteCommentResource(id);
        var command = DeleteCommentCommandFromResourceAssembler.toCommandFromResource(deleteCommentResource);
        return commentCommandService.handle(command);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResource> updateComment(@PathVariable Long id, @RequestBody UpdateCommentResource updateCommentResource) {
        var command = UpdateCommentCommandFromResourceAssembler.toCommandFromResource(id, updateCommentResource);
        var comment = commentCommandService.handle(command);
        return comment.map(value -> ResponseEntity.ok(CommentResourceFromEntityAssembler.toResourceFromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }



}
