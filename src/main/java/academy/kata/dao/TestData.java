package academy.kata.dao;

import academy.kata.model.*;
import academy.kata.model.entry.*;

import java.sql.Date;
import java.util.List;


public interface TestData {

    User[] USERS = {
            new User("Abybakirov Andry Alexandrovich",
                    new Date(90, 0, 1),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address("Minsk", "Sovetskaya, 101, 15, A"),
                    List.of(
                            new PhoneEntry("work phone", "+375 29 111-00-01"),
                            new PhoneEntry("home phone", "+375 29 111-00-02")),
                    List.of(
                            new EmailEntry("work email1", "workEmail011@mail.ru"),
                            new EmailEntry("home email2", "workEmail012@mail.ru"),
                            new EmailEntry("home email3", "workEmail013@mail.ru"))), // (int postalCode, String state, String city, String street, int home, int flat, String corpus)

            new User("Barinova Beatrisa Borisovna",
                    new Date(102, 6, 28),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address("Piter", "Konnaya, 21, 247, b"),
                    List.of(
                            new PhoneEntry("work phone", "+375 29 222-00-01"),
                            new PhoneEntry("home phone", "+375 29 222-00-02")),
                    List.of(
                            new EmailEntry("work email", "workEmail021@mail.ru"),
                            new EmailEntry("home email", "workEmail022@mail.ru"))),

            new User("Иванов Иван Иванович",
                    new Date(47, 11, 31),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address("ПольшГрад", "Шпионова, 36, 666, М"),
                    List.of(
                            new PhoneEntry("work phone", "+375 29 333-00-01"),
                            new PhoneEntry("home phone", "+375 29 333-00-02")),
                    List.of(
                            new EmailEntry("раб. email", "workEmail031@mail.ru"),
                            new EmailEntry("дом. email", "workEmail032@mail.ru"))),
            new User("Ельцин Борис Николаевич",
                    new Date(31, 1, 1),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address("Москва", "Царская, 1 А"),
                    List.of(
                            new PhoneEntry("work phone", "+375 29 444-00-01"),
                            new PhoneEntry("home phone", "+375 29 444-00-02")),
                    List.of(
                            new EmailEntry("work email1", "workEmail041@mail.ru"),
                            new EmailEntry("home email2", "workEmail042@mail.ru"),
                            new EmailEntry("home email3", "workEmail043@mail.ru"))), // (int postalCode, String state, String city, String street, int home, int flat, String corpus)

            new User("Ленин Владимир Ильич",
                    new Date(-30, 03, 22),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address("Москва", "Кремль"),
                    List.of(
                            new PhoneEntry("work phone", "+375 29 555-00-01"),
                            new PhoneEntry("home phone", "+375 29 555-00-02")),
                    List.of(
                            new EmailEntry("work email", "workEmail051@mail.ru"),
                            new EmailEntry("home email", "workEmail052@mail.ru")))
    };

    default void printUsers(User... users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
}



