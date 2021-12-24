/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Matias
 */
public class Account {

    private int id;
    private String account_type;
    private double total;
    private User user;

    public Account(int id, String account_type, double total, User user) {
        this.id = id;
        this.account_type = account_type;
        this.total = total;
        this.user = user;
    }

    public Account(int id, String account_type, double total) {
        this.id = id;
        this.account_type = account_type;
        this.total = total;
    }

    public Account(String account_type, double total, User user) {
        this.account_type = account_type;
        this.total = total;
        this.user = user;
    }

    public Account(String account_type, double total) {
        this.account_type = account_type;
        this.total = total;
    }

    public Account(String account_type) {
        this.account_type = account_type;
    }

    public int getId() {
        return id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public double getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }
    
    

    public void deposit(double amount) {
        this.total += amount;
    }
    
    public void extract(double amount){
        if(this.getTotal() > amount){
          this.total -= amount;
        }
    }

    public void transfer(Account accountDestination, double amount) {
            extract(amount);
            accountDestination.deposit(amount);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", account_type=" + account_type + ", total=" + total + ", user=" + user + '}';
    }

}
