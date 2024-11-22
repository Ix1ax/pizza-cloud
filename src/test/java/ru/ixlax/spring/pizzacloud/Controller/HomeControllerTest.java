package ru.ixlax.spring.pizzacloud.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HomeController.class) // <- Тест для HomeController
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; // <- Внедрить MockMvc

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")) // <- Выполнить запрос GET
                .andExpect(status().isOk()) // <- Проверить какой статус пришел (Ожидается 200)
                .andExpect(view().name("home")) // <- Имя ответа должно быть home
                .andExpect(content().string( // <- Контент страницы в строку
                        containsString("Welcome to..."))); // <- Ожидаем эту строчку
    }
}
