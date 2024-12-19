package bg.softuni.productshop.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidatorUtil{
    <E> Set<ConstraintViolation<E>> validate (E e);

    <E> boolean isValid(E e);
}
