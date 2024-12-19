package bg.softuni.productshop.service;

import java.io.IOException;

public interface UserService {
    void seedUser() throws IOException;

    boolean isImported();
}
