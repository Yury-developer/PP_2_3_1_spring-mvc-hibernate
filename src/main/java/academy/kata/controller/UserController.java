package academy.kata.controller;

import academy.kata.model.User;
import academy.kata.model.entry.EmailEntry;
import academy.kata.model.entry.PhoneEntry;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * @Author: Yury Lapitski
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
            e.printStackTrace();
            LOGGER = null; // Или другое действие по умолчанию
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
        model.addAttribute("addedUser", new User());
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
        User user = userService.get(userId);
        model.addAttribute("viewUser", user);
        return "userPages/view-user";
    }


    @GetMapping()
    public String showAllUsers(Model model) {
        LOGGER.fine("UserController: showAllUsers");
        List<User> userList = userService.get();
        model.addAttribute("viewAllUsers", userList);
        return "userPages/all-users";
    }


    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam(name = "user_id", required = true) Integer userId,
                                   Model model) {
        LOGGER.fine("UserController: showEditUserForm, user_id = " + userId);
        User user = userService.get(userId);
        model.addAttribute("editUser", user);
        return "userPages/edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam(name = "id", required = true) Integer userId,
                           @ModelAttribute("editUser") User user) {
        LOGGER.fine("UserController: editUser, user_id = " + userId + "\n user = " + user);
        userService.update(user);
        return "redirect:/users";
    }


    @GetMapping("/addPhone")
    public String addPhone(@RequestParam(name = "user_id") Integer userId,
                           Model model) {
        LOGGER.fine("UserController: addPhone, \n\tuser_id = " + userId);
        model.addAttribute("user_id", userId);
        model.addAttribute("phone_entry", new PhoneEntry());
        return "userPages/helper-pages/add-phone-page";
    }

    @PostMapping("/savePhone")
    public String savePhone(@RequestParam(name = "user_id", required = true) Integer userId,
                            @ModelAttribute("phone_entry") PhoneEntry phoneEntry) {
        LOGGER.fine("UserController: savePhone, user_id = " + userId + "\n phone_entry = " + phoneEntry);
        User user = userService.get(userId);
        user.getPhones().add(phoneEntry);
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/deletePhone")
    public String deletePhone(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "phone_id") Integer phoneId,
                              Model model) {
        LOGGER.fine("UserController: deletePhone, \tuser_id = " + userId + "\tphone_id = " + phoneId);
        User user = userService.deletePhone(userId, phoneId);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }


    @GetMapping("/addEmail")
    public String addEmail(@RequestParam(name = "user_id") Integer userId,
                           Model model) {
        LOGGER.fine("UserController: addPhone, \tuser_id = " + userId);
        model.addAttribute("user_id", userId);
        model.addAttribute("email_entry", new EmailEntry());
        return "userPages/helper-pages/add-email-page";
    }

    @PostMapping("/saveEmail")
    public String saveEmail(@RequestParam(name = "user_id", required = true) Integer userId,
                            @ModelAttribute("email_entry") EmailEntry emailEntry) {
        LOGGER.fine("UserController: saveEmail, user_id = " + userId + " email_entry = " + emailEntry);
        User user = userService.get(userId);
        user.getEmails().add(emailEntry);
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/deleteEmail")
    public String deleteEmail(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "email_id") Integer emailId,
                              Model model) {
        LOGGER.fine("UserController: deleteEmail, \tuser_id = " + userId + "\tphone_id = " + emailId);
        User user = userService.deleteEmail(userId, emailId);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "user_id") Integer userId) {
        LOGGER.fine("UserController: deleteUser, user_id = " + userId);
        userService.delete(userId);
        return "redirect:/users";
    }
}
