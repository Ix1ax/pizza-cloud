package ru.ixlax.spring.pizzacloud.Model;


import lombok.Data;

@Data // <- Во время компиляции генерирует GETTER | SETTER и прочее
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
