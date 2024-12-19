package bg.softuni.productshop.service;

import java.io.IOException;

public interface ProductService {
    void seedProducts() throws IOException;

    boolean isImported();

    void exportProductsInRange() throws IOException;
}
