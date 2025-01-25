package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String sqlCommand = "CREATE TABLE IF NOT EXISTS UserTest (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))";
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String sqlCommand = "DROP TABLE IF EXISTS UserTest";
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO UserTest (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = conn.createStatement()) {
            String sqlCommand = "DELETE FROM UserTest WHERE id = " + id;
            statement.executeUpdate(sqlCommand);
            System.out.println("User удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> AllUsers = new ArrayList<>();

        try (Statement statement = conn.createStatement()) {
            String sqlCommand = "SELECT * FROM UserTest";
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User();
                user.setId (resultSet.getLong("id"));
                user.setName (resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                AllUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return AllUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            String sqlCommand = "DELETE FROM UserTest";
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
