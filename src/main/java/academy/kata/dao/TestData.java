package academy.kata.dao;

import academy.kata.model.*;

import java.sql.Date;


public interface TestData {

    @SuppressWarnings("deprecation")
    User[] USERS = {
            new User("Abybakirov Andry Alexandrovich",
                    new Date(90, 0, 1),   // Params:   year – the year minus 1900; must be 0 to 8099. (Note that 8099 is 9999 minus 1900.)   /   month – 0 to 11   /   day – 1 to 31
                    "Minsk Sovetskaya, 101, 15, A"),

            new User("Barinova Beatrisa Borisovna",
                    new Date(102, 6, 28),
                    "Piter Konnaya, 21, 247, b"),

            new User("Иванов Иван Иванович",
                    new Date(47, 11, 31),
                    "ПольшГрад Шпионова, 36, 666, М"),

            new User("Ельцин Борис Николаевич",
                    new Date(31, 1, 1),
                    "Москва Царская, 1 А"),

            new User("Ленин Владимир Ильич",
                    new Date(-30, 3, 22),
                    "Москва Кремль"),
    };
}



