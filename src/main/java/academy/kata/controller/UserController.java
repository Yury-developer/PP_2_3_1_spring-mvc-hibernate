package academy.kata.controller;

import academy.kata.config.RootPathProvider;
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

import academy.kata.model.*;
import academy.kata.model.entry.*;
import academy.kata.service.UserService;


/**
 * @Author: Yury Lapitski
 * 2024-05-05
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private static Logger LOGGER;
    static {

//        System.setProperty("java.util.logging.config.file", "src/main/resources/userController_loggerConfig.properties");

//        try (FileInputStream ins = new FileInputStream("c:/Users/Yury/IdeaProjects/Kata/PP_2_3_1_spring-mvc-hibernate/src/main/resources/userController_loggerConfig.properties")) {
//            LogManager.getLogManager().readConfiguration(ins);
//        } catch (Exception ignore) {
//            ignore.printStackTrace();
//        }
//        LOGGER = Logger.getLogger(UserController.class.getName());
//        LOGGER.setLevel(Level.ALL);

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



    // просто возвращает страницу приветствия (с нее генерирую тестовые данные)
    @RequestMapping(method = RequestMethod.HEAD)
    public String ping() {
        LOGGER.fine("UserController: ping");
        return "redirect:/";
    }


    // отдает страницу для создания нового пользователя
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        LOGGER.fine("UserController: showAddUserForm");
        model.addAttribute("addedUser", new User());
        return "userPages/add-user";
    }

    // ловит и добавляет в базу только что созданного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/add")
    public String addUser(@ModelAttribute("addedUser") User user) {
        LOGGER.fine("UserController: addUser, user = " + user);
        userService.add(user);
        return "redirect:/users";
    }



    // отдает страницу с пользователем, пример: /view?user_id=1
    @GetMapping("/view")
    public String showUserDetails(@RequestParam(defaultValue = "0", required = false, name = "user_id") Integer userId, Model model) {
        LOGGER.fine("UserController: showUserDetails, user_id = " + userId);
        User user = userService.get(userId);
        model.addAttribute("viewUser", user);
        return "userPages/view-user";
    }


    @Autowired
    RootPathProvider rootPathProvider;
    // отдает страницу со всеми существующими в базе пользователями
    @GetMapping()
    public String showAllUsers(Model model) {
        LOGGER.fine("UserController: showAllUsers");
        List<User> userList = userService.get();
        userList.stream().forEach(System.out::println);
        model.addAttribute("viewAllUsers", userList);

        System.out.println("\n\n\t!!!" + rootPathProvider.getRootPath() + "\t!!!\n\n");

        return "userPages/all-users";
    }


    // отдает страницу для редактирования выбранного пользователя
    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam(name = "user_id", required = true) Integer userId, Model model) {
        LOGGER.fine("UserController: showEditUserForm, user_id = " + userId);
        User user = userService.get(userId);
        model.addAttribute("editUser", user);


//        System.out.println("\n\n\t!!!" + rootPathProvider.getRootPath() + "\t!!!\n\n");

        return "userPages/edit-user";
    }

    // ловит и обновляет в базе отредактированного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/edit")
    public String editUser(@RequestParam(name = "id", required = true) Integer userId, @ModelAttribute("editUser") User user) {
        LOGGER.fine("UserController: editUser, user_id = " + userId + "\n user = " + user);
        userService.update(user);
        return "redirect:/users";
    }


    @GetMapping("/deletePhone")
    public String deletePhone(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "phone_id") Integer phoneId,
                              Model model) {
        LOGGER.fine("UserController: deletePhone, \tuser_id = " + userId + "\tphone_id = " + phoneId);
        User user = userService.get(userId);
        user.getPhones().remove(user.getPhones().get(phoneId));
        if (user.getPhones().isEmpty()) {
            user.getPhones().add(new PhoneEntry("", ""));
        }
        userService.update(user);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }

    @GetMapping("/addPhone")
    public String addPhone(@RequestParam(name = "user_id") Integer userId, Model model) {
        LOGGER.fine("UserController: addPhone, \n\tuser_id = " + userId);
        model.addAttribute("user_id", userId);
        model.addAttribute("phone_entry", new PhoneEntry());
        return "userPages/hepler-pages/add-phone-page";
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




    @GetMapping("/deleteEmail")
    public String deleteEmail(@RequestParam(name = "user_id") Integer userId,
                              @RequestParam(name = "email_id") Integer emailId,
                              Model model) {
        LOGGER.fine("UserController: deleteEmail, \tuser_id = " + userId + "\tphone_id = " + emailId);
        User user = userService.get(userId);
        user.getEmails().remove(user.getEmails().get(emailId));
        if (user.getEmails().isEmpty()) {
            user.getEmails().add(new EmailEntry("", ""));
        }
        userService.update(user);
        model.addAttribute("editUser", user);
        return "redirect:/users/edit?user_id=" + userId;
    }

    @GetMapping("/addEmail")
    public String addEmail(@RequestParam(name = "user_id") Integer userId, Model model) {
        LOGGER.fine("UserController: addPhone, \tuser_id = " + userId);
        model.addAttribute("user_id", userId);
        model.addAttribute("email_entry", new EmailEntry());
        return "userPages/hepler-pages/add-email-page";
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


    // ловит и удаляет из базы user по его id, затем выкидывает на страницу по умолчанию
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "user_id") Integer userId) {
        LOGGER.fine("UserController: deleteUser, user_id = " + userId);
        userService.delete(userId);
        return "redirect:/users";
    }
}
