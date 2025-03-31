package cours.spring.cours_spring.utils.validator;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ListNotEmptyValidator implements ConstraintValidator<ListNotEmpty, List<?>> {

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }

}
