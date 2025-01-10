package com.shubhamsawarkar.lakshya.annotations;

import com.shubhamsawarkar.lakshya.annotations.impl.FollowsConstraint;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FollowsConstraint.class)
public @interface Follows {

    LocalDateTime predecessor();
    String message() default "This date must be after "
}
