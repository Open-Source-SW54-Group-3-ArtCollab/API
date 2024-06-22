package upc.edu.artcollab.api.users.interfaces.rest.transform;

import upc.edu.artcollab.api.users.domain.model.aggregates.Reader;
import upc.edu.artcollab.api.users.interfaces.rest.resources.ReaderResource;

public class ReaderResourceFromEntityAssembler {
    public static ReaderResource toResourceFromEntity(Reader reader) {
        return new ReaderResource(reader.getId(), reader.getName(), reader.getUsername(), reader.getEmail(), reader.getType().name().toLowerCase(), reader.getImgUrl());
    }
}
