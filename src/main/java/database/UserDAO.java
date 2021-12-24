/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import config.DBConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Matias
 */
public class UserDAO {

    Connection connection;

    public UserDAO() {
        DBConn conn = new DBConn();
        this.connection = conn.getConnection("homebanking", DatosDB.USER, DatosDB.PASSWORD);

    }

    public User getUser(int id) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        User user = null;

        ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");

            user = new User(username, password, name, lastName, email, gender);
        }

        return user;
    }

    public List<User> getUsers(int limit) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        List<User> usersDB = new ArrayList<>();

        ps = connection.prepareStatement("SELECT * FROM users LIMIT ?");
        ps.setInt(1, limit);
        rs = ps.executeQuery();

        while (rs.next()) {
            usersDB.add(new User(rs.getString("username"), rs.getString("password")));
        }

        rs.close();
        ps.close();

        return usersDB;
    }

    public List<User> getUsers() throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        List<User> usersDB = new ArrayList<>();

        ps = connection.prepareStatement("SELECT * FROM users");
        rs = ps.executeQuery();

        while (rs.next()) {
            usersDB.add(new User(rs.getString("username"), rs.getString("password")));
        }
        rs.close();
        ps.close();

        return usersDB;
    }

    public User getUserByID(int id) throws SQLException {
        PreparedStatement preparedSt;
        ResultSet resultSt;
        User user = null;

        preparedSt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        preparedSt.setInt(1, id);
        resultSt = preparedSt.executeQuery();

        if (resultSt.next()) {
            String username = resultSt.getString("username");
            String name = resultSt.getString("name");
            String lastName = resultSt.getString("last_name");
            String email = resultSt.getString("email");
            String gender = resultSt.getString("gender");

            user = new User(id, username, name, lastName, email, gender);
        }

        resultSt.close();
        preparedSt.close();
        return user;
    }

    public User getUserByUserName(String username) throws SQLException {
        PreparedStatement preparedSt;
        ResultSet resultSt;
        User user = null;

        preparedSt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        preparedSt.setString(1, username);
        resultSt = preparedSt.executeQuery();

        if (resultSt.next()) {
            String userName = resultSt.getString("username");
            String password = resultSt.getString("password");
            String name = resultSt.getString("name");
            String lastName = resultSt.getString("last_name");
            String email = resultSt.getString("email");

            user = new User(userName, password, name, lastName, email);
        }

        return user;
    }

    public User login(String username, String password) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        User user = null;

        ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) {
            int getId = rs.getInt("id");
            String getUserName = rs.getString("username");
            String getName = rs.getString("name");
            String getLastName = rs.getString("last_name");
            String getEmail = rs.getString("email");
            String getGender = rs.getString("gender");
            user = new User(getId, getUserName, getName, getLastName, getEmail, getGender);
        }

        return user;

    }

    public boolean createUser(String username, String password, String name, String lastname, String email, String gender, String repass) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        if (password.equals(repass)) {
            ps = connection.prepareStatement("INSERT INTO users(username, password, name, last_name, email, gender) "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, gender);
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        }
        return false;
    }

}
