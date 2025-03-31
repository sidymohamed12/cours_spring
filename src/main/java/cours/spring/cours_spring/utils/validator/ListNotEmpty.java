package cours.spring.cours_spring.utils.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ListNotEmptyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface ListNotEmpty {

    String message()

    default "La liste est obligatoire";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}