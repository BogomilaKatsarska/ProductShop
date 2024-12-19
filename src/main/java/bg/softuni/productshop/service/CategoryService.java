package bg.softuni.productshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    boolean isImported();
}
