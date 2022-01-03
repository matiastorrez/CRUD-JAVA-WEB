/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.HashMap;

/**
 *
 * @author Matias
 */
public class OtherData {

    private int totalAccounts;
    private int totalAccountsCA;
    private int totalAccountsCC;
    private int totalTransfers;
    private double totalMoneyAccounts;

    public OtherData(int totalAccounts, int totalAccountsCA, int totalAccountsCC, int totalTransfers, double totalMoneyAccounts) {
        this.totalAccounts = totalAccounts;
        this.totalAccountsCA = totalAccountsCA;
        this.totalAccountsCC = totalAccountsCC;
        this.totalTransfers = totalTransfers;
        this.totalMoneyAccounts = totalMoneyAccounts;
    }

    public int getTotalAccounts() {
        return totalAccounts;
    }

    public int getTotalAccountsCA() {
        return totalAccountsCA;
    }

    public int getTotalAccountsCC() {
        return totalAccountsCC;
    }

    public int getTotalTransfers() {
        return totalTransfers;
    }

    public double getTotalMoneyAccounts() {
        return totalMoneyAccounts;
    }

    public HashMap<String, Object> mapOtherData() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("Cantidad total de cuentas CA", this.totalAccountsCA);
        map.put("Cantidad total de cuentas CC", this.totalAccountsCC);
        map.put("Cantidad total de transferencias CC", this.totalTransfers);
        map.put("Cantidad total de cuentas", this.totalAccounts);
        map.put("Cantidad total de pesos de todas las cuentas", this.totalMoneyAccounts);

        return map;

    }

}
