package bg.softuni.productshop.controller;

import bg.softuni.productshop.service.CategoryService;
import bg.softuni.productshop.service.ProductService;
import bg.softuni.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CommandLineRunner(CategoryService categoryService, UserService userService, ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!this.categoryService.isImported()){
            this.categoryService.seedCategories();
        }

        if (!this.userService.isImported()){
            this.userService.seedUser();
        }

        if (!this.productService.isImported()){
            this.productService.seedProducts();
        }
    }
}
