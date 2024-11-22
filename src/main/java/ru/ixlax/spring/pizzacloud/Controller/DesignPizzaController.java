package ru.ixlax.spring.pizzacloud.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.ixlax.spring.pizzacloud.Model.Ingredient;
import ru.ixlax.spring.pizzacloud.Model.Ingredient.Type;
import ru.ixlax.spring.pizzacloud.Model.Pizza;
import ru.ixlax.spring.pizzacloud.Model.PizzaOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Аннотация @Slf4j реали
зована в Lombok. Во время компиляции она автоматически генери
рует статическое свойство типа Logger, определяемого библиотекой
SLF4J
 */
@Slf4j
@Controller
/*
При применении на уровне класса аннотация @RequestMapping опре
деляет тип запросов, которые обрабатывает этот контроллер. В дан
ном случае она сообщает, что DesignPizzaController будет обрабаты
вать запросы, пути в которых начинаются с /design.
 */
@RequestMapping("/design")
/*
Наконец, класс DesignPizzaController снабжен аннотацией @Ses
si on Attributes("pizzaOrder"). Она указывает, что объект PizzaOrder,
объявленный в классе чуть ниже, должен поддерживаться на уровне
сеанса. Это важно, потому что создание тако также является первым
шагом в создании заказа, и созданный нами заказ необходимо будет
перенести в сеанс, охватывающий несколько запросов.
 */
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

    @ModelAttribute("Ingredient")
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );


        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    private String processPizza(@Valid Pizza pizza, Errors errors , @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {

        if(errors.hasErrors()) {
            return "design";

        }
        pizzaOrder.addPizza(pizza);
        log.info("Processing Pizza: {}", pizza);
        return "redirect:/orders/current";
    }

}
