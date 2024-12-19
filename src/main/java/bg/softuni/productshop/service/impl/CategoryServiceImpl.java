package bg.softuni.productshop.service.impl;

import bg.softuni.productshop.data.entities.Category;
import bg.softuni.productshop.data.repositories.CategoryRepository;
import bg.softuni.productshop.service.CategoryService;
import bg.softuni.productshop.service.dtos.CategorySeedJsonDto;
import bg.softuni.productshop.utils.ValidatorUtil;
import com.google.gson.Gson;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String  JSON_PATH = "src/main/resources/json/categories.json";

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
    }


    @Override
    public void seedCategories() throws IOException {
        String jsonString = String.join("", Files.readAllLines(Path.of(JSON_PATH)));
        CategorySeedJsonDto[] categorySeedJsonDtos = this.gson.fromJson(jsonString, CategorySeedJsonDto[].class);
        for (CategorySeedJsonDto seedJsonDto : categorySeedJsonDtos) {
            if (!this.validatorUtil.isValid(seedJsonDto)){
                System.out.println(this.validatorUtil.validate(seedJsonDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining()));
                continue;
            }
            this.categoryRepository.saveAndFlush(this.modelMapper.map(seedJsonDto, Category.class));
        }
    }

    @Override
    public boolean isImported() {
        return this.categoryRepository.count() > 0;
    }
}
