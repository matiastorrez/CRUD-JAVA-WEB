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
public class Transfer {

    private int id;
    private Account origin;
    private Account destination;
    private double amount;

    public Transfer(int id, Account origin, Account destination, double amount) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public Transfer(Account origin, Account destination, double amount) {
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void transfer() {
        origin.transfer(destination, amount);
    }

    @Override
    public String toString() {
        return "Transfer{" + "id=" + id + ", origin=" + origin + ", destination=" + destination + ", amount=" + amount + "}\n-----------------------------------";
    }
    
    

}
