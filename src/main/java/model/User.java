/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pol_m
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String gender;
    private String email;

    public User(String username, String password, String name, String lastName, String email, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String name, String lastName, String email, String gender) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }
    
    public User(int id,String username, String name, String lastName, String email, String gender){
        this(username,name,lastName,email,gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + '}';
    }

    

   

}
