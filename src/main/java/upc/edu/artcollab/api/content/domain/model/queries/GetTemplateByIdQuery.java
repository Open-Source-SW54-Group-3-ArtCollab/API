/**
 * GetTemplateByIdQuery
 * @summary
 * GetTemplateByIdQuery is a record class that represents the query to get a template by ID.
 */
package upc.edu.artcollab.api.content.domain.model.queries;

public record GetTemplateByIdQuery (Integer id){
    public GetTemplateByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
