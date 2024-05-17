package academy.kata.controller;

import academy.kata.model.User;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import java.time.LocalDate;


/**
 * Author: Yury Lapitski
 * 2024-05-17
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private static Logger LOGGER;

    static {
        try {
            Resource resource = new ClassPathResource("userController_loggerConfig.properties");
            InputStream ins = resource.getInputStream();
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(UserController.class.getName());
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            System.out.println("class UserController: error initializing the logger // IOException");
            e.printStackTrace();
            LOGGER = null;
        }
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    // просто возвращает страницу приветствия (с нее можно сгенерировать тестовые данные во все таблицы)
    @RequestMapping(method = RequestMethod.HEAD)
    public String ping() {
        LOGGER.fine("UserController: ping");
        return "redirect:/";
    }



    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        LOGGER.fine("UserController: showAddUserForm");

        String name = "no_name";
        LocalDate localDate = LocalDate.now();   // Получаем текущую дату как LocalDate
        Date sqlDate = Date.valueOf(localDate);   // Преобразуем LocalDate в java.sql.Date
        String address = "no_address";

        model.addAttribute("addedUser", new User(name, sqlDate, address));
        return "userPages/add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("addedUser") User user) {
        LOGGER.fine("UserController: addUser, user = " + user);
        userService.add(user);
        return "redirect:/users";
    }


    @GetMapping("/view")
    public String showUserDetails(@RequestParam(defaultValue = "0", required = false, name = "user_id") Integer userId,
                                  Model model) {
        LOGGER.fine("UserController: showUserDetails, user_id = " + userId);
        User user = userService.getById(userId);
        model.addAttribute("viewUser", user);
        return "userPages/view-user";
    }


    @GetMapping()
    public String showAllUsers(Model model) {
        LOGGER.fine("UserController: showAllUsers");
        List<User> userList = userService.getAll();
        model.addAttribute("viewAllUsers", userList);
        return "userPages/all-users";
    }


    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam(name = "user_id") Integer userId,
                                   Model model) {
        LOGGER.fine("UserController: showEditUserForm, user_id = " + userId);
        User user = userService.getById(userId);
        model.addAttribute("editUser", user);
        return "userPages/edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam(name = "id") Integer userId,
                           @ModelAttribute("editUser") User user) {
        LOGGER.fine("UserController: editUser, user_id = " + userId + "\n user = " + user);
        userService.update(user);
        return "redirect:/users";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "user_id") Integer userId) {
        LOGGER.fine("UserController: deleteUser, user_id = " + userId);
        userService.delete(userId);
        return "redirect:/users";
    }
}
