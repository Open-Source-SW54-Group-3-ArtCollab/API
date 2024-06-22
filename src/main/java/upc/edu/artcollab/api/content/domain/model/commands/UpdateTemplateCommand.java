package upc.edu.artcollab.api.content.domain.model.commands;

/**
 * The UpdateTemplateCommand class is a command model.
 * <p>
 *     This class represents the UpdateTemplateCommand command model. It contains the attributes of an UpdateTemplateCommand.
 * </p>
 * @author Camila Elena Amaro Villanueva U202114248
 * @version 1.0
 */
public record UpdateTemplateCommand(Integer id,String title, String description, String type, String imgURL, Integer views, Integer likes, String genre, Integer templateState_Id, Integer templateHistory_Id, Integer portfolio_Id) {
}
