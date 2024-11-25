package ru.ixlax.spring.pizzacloud.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
public class Pizza {

    /*
    Аннотация Spring Validation что поле не может быть пустое
     */
    @NotNull

   /*
   Аннотация Spring Validation что поле минимум 5 символов должно быть и + вывод сообщения
    */
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size (min = 1, message = "You must choose as least 1 ingredient")
    private List<Ingredient> ingredients;
}
