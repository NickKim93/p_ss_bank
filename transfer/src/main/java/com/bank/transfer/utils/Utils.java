package com.bank.transfer.utils;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

@UtilityClass
public class Utils {

    public String getMassageOfError(BindingResult bindingResult) {
        StringBuilder massages = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            massages.append(" ")
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        return massages.toString();
    }
}
