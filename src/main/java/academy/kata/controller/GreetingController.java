package academy.kata.controller;

import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;


@Controller
@RequestMapping(value = "/")
public class GreetingController {

    private final UserService userService;

    @Autowired
    public GreetingController(UserService userService) {
        this.userService = userService;
    }

    // отдает страницу для создания нового пользователя
    @GetMapping
    public String greetingPage(Model model) {
        model.addAttribute("greeting", "Hello!");
        model.addAttribute("greetingMessage", "Практическая задача 2.3.1 Java pre-project. Задача 2.3.1. Spring MVC + Hibernate.");
        model.addAttribute("author", "Выполнил: Лапицкий Юрий   //   Performed by: Yury Lapitski");
        return "greeting-page";
    }

    // отдает страницу для создания нового пользователя
    @Transactional
    @GetMapping(value = "/generate")
    public String generateTestData() {
        userService.generateTestData();
        return "redirect:/users";
    }
}
