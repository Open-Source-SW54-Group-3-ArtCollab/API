package upc.edu.artcollab.api.monetization.domain.model.commands;

import upc.edu.artcollab.api.monetization.domain.model.valueObjects.Amount;
import upc.edu.artcollab.api.monetization.domain.model.valueObjects.Content;

public record UpdateCommisionCommand(Amount amount, Content content) {
}
