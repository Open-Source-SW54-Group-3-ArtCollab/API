/**
 * GetTemplateByIdQuery
 * @summary
 * GetTemplateByIdQuery is a record class that represents the query to get a template by ID.
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
package upc.edu.artcollab.api.content.domain.model.queries;

public record GetTemplateByIdQuery (Integer id){
    public GetTemplateByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
