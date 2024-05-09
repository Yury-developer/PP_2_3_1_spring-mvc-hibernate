package academy.kata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // просто возвращает ___ доработать!
    @RequestMapping(method = RequestMethod.HEAD)
    public String ping() {
        System.out.println("\n\nUserController: ping");

//        return "greeting-page";
        return "redirect:/";
    }


    // отдает страницу для создания нового пользователя
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        System.out.println("\n\nUserController: showAddUserForm");

        model.addAttribute("addedUser", new User());
        return "userPages/add-user";
    }

    // ловит и добавляет в базу только что созданного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/add")
    public String addUser(@ModelAttribute("addedUser") User user) {
        System.out.println("\n\nUserController: addUser, user = " + user);

        userService.add(user);
        return "redirect:/users";
    }


    // отдает страницу с пользователем, пример: /view?user_id=1
    @GetMapping("/view")
    public String showUserDetails(@RequestParam(defaultValue = "0", required = false, name = "user_id") Integer userId, Model model) {
        System.out.println("\n\nUserController: showUserDetails, user_id = " + userId);

        User user = userService.get(userId);
        model.addAttribute("viewUser", user);
        return "userPages/view-user";
    }


    // отдает страницу со всеми существующими пользователями
    @GetMapping()
    public String showAllUsers(Model model) {
        System.out.println("\n\nUserController: showAllUsers");

        List<User> userList = userService.get();
        userList.stream().forEach(System.out::println);
        model.addAttribute("viewAllUsers", userList);
        return "userPages/all-users";
    }



    // отдает страницу для редактирования выбранного пользователя
    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam(name = "user_id", required = true) Integer userId, Model model) {
        System.out.println("\n\nUserController: showEditUserForm, user_id = " + userId);

        User user = userService.get(userId);
        model.addAttribute("editUser", user);
        return "userPages/edit-user";
    }

    // ловит и обновляет в базе отредактированного user, затем выкидывает на страницу по умолчанию
    @PostMapping("/edit")
    public String editUser(@RequestParam(name = "id", required = true) Integer userId, @ModelAttribute("editUser") User user) {
        System.out.println("\n\nUserController: editUser, user_id = " + userId + "\n user = " + user);

        userService.update(user);
        return "redirect:/users";
    }



    // ловит и удаляет из базы user по его id, затем выкидывает на страницу по умолчанию
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "user_id") Integer userId) {
        System.out.println("\n\nUserController: deleteUser, user_id = " + userId);

        userService.delete(userId);
        return "redirect:/users";
    }
}
