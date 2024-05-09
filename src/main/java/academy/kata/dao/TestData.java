package academy.kata.dao;

import academy.kata.model.Address;
import academy.kata.model.Contact;
import academy.kata.model.Personality;
import academy.kata.model.User;
import academy.kata.model.entry.EmailEntry;
import academy.kata.model.entry.NoteEntry;
import academy.kata.model.entry.PhoneEntry;
import academy.kata.model.entry.UrlEntry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public interface TestData {

    User[] USERS = {
            new User(
                    new Personality(
                            new Date(90, 0, 1), "Abybakirov", "Andry", "Alexandrovich"),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    new Address(246000, "Belarus", "Minsk", "Sovetskaya", 101, 15, "A"), // (int postalCode, String state, String city, String street, int home, int flat, String corpus)
                    new Contact(
                            List.of(
                                    new PhoneEntry("work phone", "+375 29 111-00-01"),
                                    new PhoneEntry("home phone", "+375 29 111-00-02")),
                            Set.of(
                                    new EmailEntry("work email1", "workEmail011@mail.ru"),
                                    new EmailEntry("home email2", "workEmail012@mail.ru"),
                                    new EmailEntry("home email3", "workEmail013@mail.ru")),
                            Set.of(
                                    new UrlEntry("LinkedIn", "https://javarush.com/groups/posts/2472-podrobnihy-razbor-klassa-arraylist"),
                                    new UrlEntry("work profile", "https://sky.pro/wiki/java/initsializatsiya-array-list-v-java-analogichno-massivu/"))
                    ),
                    new ArrayList<>(Arrays.asList(
                            new NoteEntry("Note №1", "This is note 1 qqqwwweee"),
                            new NoteEntry("Note №2", "This is note 2 aaasssddd"),
                            new NoteEntry("Note №3", "This is note 3 zzzxxxccc")))),

            new User(
                    new Personality(
                            new Date(98, 11, 31), "Barinova", "Beatrisa", "Borisovna"),
                    new Address(247123, "Russian", "Piter", "Konnaya", 21, 247, "b"),
                    new Contact(
                            List.of(
                                    new PhoneEntry("work phone", "+7 929 222-00-03"),
                                    new PhoneEntry("home phone", "+7 928 222-00-04")),
                            Set.of(
                                    new EmailEntry("work email", "workEmail021@mail.ru"),
                                    new EmailEntry("home email", "workEmail022@mail.ru")),
                            Set.of(
                                    new UrlEntry("LinkedIn", "https://javarush.com/groups/posts/2472-podrobnihy-razbor-klassa-arraylist"),
                                    new UrlEntry("work profile", "https://sky.pro/wiki/java/initsializatsiya-array-list-v-java-analogichno-massivu/"))),
                    new ArrayList<>(Arrays.asList(
                            new NoteEntry("Note №1", "This is note 1 qqqwwweee"),
                            new NoteEntry("Note №2", "This is note 2 aaasssddd"),
                            new NoteEntry("Note №3", "This is note 3 zzzxxxccc")))),

            new User(
                    new Personality(
                            new Date(98, 11, 31), "Иванов", "Иван", "Иванович"),
                    new Address(247123, "Польша", "ПольшГрад", "Шпионова", 36, 666, "М"),
                    new Contact(
                            List.of(
                                    new PhoneEntry("рабочий 1", "+7 454 222-00-03"),
                                    new PhoneEntry("дом", "+7 928 554-00-04")),
                            Set.of(
                                    new EmailEntry("раб. email", "workEmail031@mail.ru"),
                                    new EmailEntry("дом. email", "workEmail032@mail.ru")),
                            Set.of(
                                    new UrlEntry("LinkedIn", "https://javarush.com/groups/posts/2472-podrobnihy-razbor-klassa-arraylist"),
                                    new UrlEntry("work profile", "https://sky.pro/wiki/java/initsializatsiya-array-list-v-java-analogichno-massivu/"))),
                    new ArrayList<>(Arrays.asList(
                            new NoteEntry("Запись №1", "This is note 1 апрс"),
                            new NoteEntry("Запись №2", "This is note 2 исм"),
                            new NoteEntry("Запись №3", "This is note 3 мрв"))))
    };

    default void printUsers(User... users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
}



