package bg.softuni.productshop.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidatorUtilImpl implements ValidatorUtil{
    private final Validator validator;

    @Autowired
    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> Set<ConstraintViolation<E>> validate (E e) {
        return this.validator.validate(e);
    }

    @Override
    public <E> boolean isValid(E e){
        return this.validator.validate(e).isEmpty();
    }
}
