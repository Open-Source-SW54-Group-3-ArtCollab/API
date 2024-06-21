package upc.edu.artcollab.api.content.domain.model.commands;

public record UpdateTemplateCommand(Integer id,String title, String description, String type, String imgURL, Integer views, Integer likes, String genre, Integer templateState_Id, Integer templateHistory_Id, Integer portfolio_Id) {
}
