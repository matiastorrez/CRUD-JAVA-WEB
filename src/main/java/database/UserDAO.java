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

        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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

        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener el usuario con id: " + id + " de la BD", ex);
        }
    }

    public List<User> getUsers(int limit) throws SQLException {
        List<User> usersDB = new ArrayList<>();
        String query = "SELECT * FROM users LIMIT ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usersDB.add(new User(rs.getString("username"), rs.getString("password")));
                }
                return usersDB;
            }
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener los usuarios de la BD", ex);
        }
    }

    public List<User> getUsers() throws SQLException {

        List<User> usersDB = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usersDB.add(new User(rs.getString("username"), rs.getString("password")));
                }
                return usersDB;
            }
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener todos los usuarios de la la BD", ex);
        }
    }

    public User getUserByID(int id) throws SQLException {

        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String name = rs.getString("name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");

                    user = new User(id, username, name, lastName, email, gender);
                }
                return user;
            }

        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener el usuario  con id: " + id+ " de la BD", ex);
        }
    }

    public User login(String username, String password) throws SQLException {

        User user = null;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo verifiar el usuario en la BD", ex);
        }

    }

    public boolean createUser(String username, String password, String name, String lastname, String email, String gender, String repass) throws SQLException {

        String query = "INSERT INTO users(username, password, name, last_name, email, gender) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, gender);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el usuario en la BD", ex);
        }
    }

}
