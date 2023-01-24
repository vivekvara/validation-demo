package com.example.validation.validator;

import com.example.validation.annotation.Country;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.regex.PatternSyntaxException;

public class CountryValidator implements ConstraintValidator<Country, String> {

    List<String> countries = null;

    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {
        return countries.contains(country);
    }

    @Override
    public void initialize(Country country) {
        try {
            countries = List.of("India", "US", "UK", "Russia", "Japan");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Could not initialize Country", e);
        }
    }
}
