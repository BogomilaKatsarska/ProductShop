package bg.softuni.productshop.service.impl;

import bg.softuni.productshop.data.entities.User;
import bg.softuni.productshop.data.repositories.UserRepository;
import bg.softuni.productshop.service.UserService;
import bg.softuni.productshop.service.dtos.UserSeedJsonDto;
import bg.softuni.productshop.utils.ValidatorUtil;
import com.google.gson.Gson;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String  JSON_PATH = "src/main/resources/json/users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
    }

    @Override
    public void seedUser() throws IOException {
        UserSeedJsonDto[] userSeedJsonDtos = this.gson
                .fromJson(String.join("", Files.readAllLines(Path.of(JSON_PATH))), UserSeedJsonDto[].class);

        for (UserSeedJsonDto userSeedJsonDto : userSeedJsonDtos) {
            if (!this.validatorUtil.isValid(userSeedJsonDto)) {
                System.out.println(this.validatorUtil.validate(userSeedJsonDto)
                        .stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining()));
                continue;
            }
            this.userRepository.save(this.modelMapper.map(userSeedJsonDto, User.class));
        }
    }

    @Override
    public boolean isImported() {
        return this.userRepository.count() > 0;
    }
}
