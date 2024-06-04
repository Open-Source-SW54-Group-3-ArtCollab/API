package upc.edu.artcollab.api.monetization.domain.model.queries;

public record GetCommisionsByAmountGreatherThanQuery(double amount) {

    public GetCommisionsByAmountGreatherThanQuery {
        if (amount < 0) {
            throw new IllegalArgumentException("Ammount must be greater than 0");
        }
    }
}
