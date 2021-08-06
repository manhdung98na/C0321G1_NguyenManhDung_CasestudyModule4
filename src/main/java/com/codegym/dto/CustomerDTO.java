package com.codegym.dto;

import com.codegym.model.entity.about_customer.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Validator {
    private String customerId;
    private CustomerType customerType;
    private String customerName;
    private String customerBirthday;
    private Byte customerGender;
    private String customerIdCard;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private boolean isDeleted;

    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;
        String regexId = "^KH-[0-9]{4}$";
        String regexName = "^\\p{Lu}\\p{Ll}*(\\s\\p{Lu}\\p{Ll}*)*$";
        String regexPhone = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
        String regexIdCard = "^([0-9]{9}|[0-9]{12})$";
        String regexEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";

        if (!Pattern.matches(regexId, customerDTO.customerId)) {
            errors.rejectValue("customerId", "customer-id-error");
        }
        if (!Pattern.matches(regexName, customerDTO.customerName)) {
            errors.rejectValue("customerName", "customer-name-error");
        }
        if (!Pattern.matches(regexPhone, customerDTO.customerPhone)) {
            errors.rejectValue("customerPhone", "customer-phone-error");
        }
        if (!Pattern.matches(regexIdCard, customerDTO.customerIdCard)) {
            errors.rejectValue("customerIdCard", "customer-id-card-error");
        }
        if (!Pattern.matches(regexEmail, customerDTO.customerEmail)) {
            errors.rejectValue("customerEmail", "customer-email-error");
        }
        String[] dates = customerDTO.customerBirthday.split("-");
        String currentDate = LocalDate.now().toString();
        String[] currentDateArr = currentDate.split("-");
        if (Integer.parseInt(dates[0]) > Integer.parseInt(currentDateArr[0])) {
            errors.rejectValue("customerBirthday", "customer-birthday-error");
        }
        if (Integer.parseInt(dates[0]) == Integer.parseInt(currentDateArr[0])) {
            if (Integer.parseInt(dates[1]) > Integer.parseInt(currentDateArr[1])) {
                errors.rejectValue("customerBirthday", "customer-birthday-error");
            }
            if (Integer.parseInt(dates[1]) == Integer.parseInt(currentDateArr[1])) {
                if (Integer.parseInt(dates[2]) > Integer.parseInt(currentDateArr[2])) {
                    errors.rejectValue("customerBirthday", "customer-birthday-error");
                }
            }
        }
    }
}
