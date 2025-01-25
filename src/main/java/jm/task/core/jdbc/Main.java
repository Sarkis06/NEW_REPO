package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        // Создаем таблицу пользователей
        userService.createUsersTable();

        // Добавляем пользователей в таблицу
        userService.saveUser("Иван", "Иванов", (byte) 20);
        System.out.println("User с именем — Иван добавлен в базу данных");
        userService.saveUser("Петр", "Петров", (byte) 30);
        System.out.println("User с именем — Петр добавлен в базу данных");
        userService.saveUser("Сергей", "Сергеев", (byte) 40);
        System.out.println("User с именем — Сергей добавлен в базу данных");
        userService.saveUser("Олег", "Олегов", (byte) 50);
        System.out.println("User с именем — Олег добавлен в базу данных");

        // Получаем всех пользователей и выводим их информацию
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        // Очищаем таблицу
        userService.cleanUsersTable();
        // Удаляем таблицу
        userService.dropUsersTable();
    }
}
