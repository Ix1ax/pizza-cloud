package ru.ixlax.spring.pizzacloud.Model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaOrder {
    /*
    Аннотация @NotBlank
     */
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    /*
    Аннотация @CreditCardNumber проверяет валидность карточки, чтобы она проходила по алгоритму Луна
    https://www.creditcardvalidator.org/articles/luhn-algorithm
     */
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    /*
    Аннотация @Pattern проверяет выражение для даты, аннотация для даты нету
    https://www.creditcardvalidator.org/articles/luhn-algorithm
     */
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    /*
    Аннотация @Digit говорит, что поле будет содержать ровно 3 цифры
     */
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Pizza> pizzaList = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        pizzaList.add(pizza);
    }

}
