package com.inmotion.trafico.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidatorImpl implements ConstraintValidator<PhoneValidator, String> {
    
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        // Validar formato: 10 dígitos, con o sin espacios/guiones
        String cleanPhone = phone.replaceAll("[\\s\\-()]", "");
        return cleanPhone.matches("\\d{10}");
    }
}