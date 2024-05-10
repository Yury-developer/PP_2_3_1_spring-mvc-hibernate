package academy.kata.controller;

import academy.kata.model.entry.PhoneEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import academy.kata.service.UserService;

import academy.kata.model.*;


/**
 * @Author: Yury Lapitski
 * 2024-05-05
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private static final Logger LOGGER;
    static {
        try (FileInputStream ins = new FileInputStream("/src/main/resources/logger_config.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        LOGGER = Logger.getLogger(UserController.class.getName());
        LOGGER.setLevel(Level.FINE);
    }



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    // просто возвращает страницу приветствия (с нее генерирую тестовые данные)
    @RequestMapping(method = RequestMethod.HEAD)
    public String ping() {
        LOGGER.warning("\nUserController: ping");
        return "redirect:/";
    }


    // отдает страницу для создания нового пользователя
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        LOGGER.warning("\nUserController: showAddUserForm");
        model.addAttribute("addedUser", new User());
        return "userPages/add-user";
    }

    // ловит и добавляет в базу только что созданного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/add")
    public String addUser(@ModelAttribute("addedUser") User user) {
        LOGGER.warning("\nUserController: addUser, user = " + user);
        userService.add(user);
        return "redirect:/users";
    }


    // отдает страницу с пользователем, пример: /view?user_id=1
    @GetMapping("/view")
    public String showUserDetails(@RequestParam(defaultValue = "0", required = false, name = "user_id") Integer userId, Model model) {
        LOGGER.warning("\nUserController: showUserDetails, user_id = " + userId);
        User user = userService.get(userId);
        model.addAttribute("viewUser", user);
        return "userPages/view-user";
    }


    // отдает страницу со всеми существующими в базе пользователями
    @GetMapping()
    public String showAllUsers(Model model) {
        LOGGER.warning("\nUserController: showAllUsers");
        List<User> userList = userService.get();
        userList.stream().forEach(System.out::println);
        model.addAttribute("viewAllUsers", userList);
        return "userPages/all-users";
    }


    // отдает страницу для редактирования выбранного пользователя
    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam(name = "user_id", required = true) Integer userId, Model model) {
        LOGGER.warning("\nUserController: showEditUserForm, user_id = " + userId);
        User user = userService.get(userId);
        model.addAttribute("editUser", user);
        return "userPages/edit-user";
    }

    // ловит и обновляет в базе отредактированного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/edit")
    public String editUser(@RequestParam(name = "id", required = true) Integer userId, @ModelAttribute("editUser") User user) {
        LOGGER.warning("\nUserController: editUser, user_id = " + userId + "\n user = " + user);
        userService.update(user);
        return "redirect:/users";
    }


    @GetMapping("/deletePhone")
    public String deletePhone(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "phone") PhoneEntry phone,
                              Model model) {
        LOGGER.warning("\nUserController: deletePhone, \n\tuser_id = " + userId + "\n\tphone = " + phone);

        // это от GPT
        User user = userService.get(userId);
        user.getPhones().remove(phone);

//        userService.deletePhone(phoneId);
//        User user = userService.get(userId);

        userService.update(user);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }

    @GetMapping("/deleteEmail")
    public String deleteEmail(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "email_id") Integer emailId,
                              Model model) {
        LOGGER.warning("\nUserController: deleteEmail, \n\tuser_id = " + userId + "\n\tphone_id = " + emailId);
        userService.deleteEmail(emailId);
        User user = userService.get(userId);
        userService.update(user);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }


    // ловит и удаляет из базы user по его id, затем выкидывает на страницу по умолчанию
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "user_id") Integer userId) {
        LOGGER.warning("\nUserController: deleteUser, user_id = " + userId);

        userService.delete(userId);
        return "redirect:/users";
    }
}
