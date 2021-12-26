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

    public OtherData(int totalAccounts, int totalAccountsCA, int totalAccountsCC, int totalTransfers) {
        this.totalAccounts = totalAccounts;
        this.totalAccountsCA = totalAccountsCA;
        this.totalAccountsCC = totalAccountsCC;
        this.totalTransfers = totalTransfers;
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

    public HashMap<String, Integer> mapOtherData() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Cantidad total de cuentas", this.totalAccounts);
        map.put("Cantidad total de cuentas CA", this.totalAccountsCA);
        map.put("Cantidad total de cuentas CC", this.totalAccountsCC);
        map.put("Cantidad total de transferencias CC", this.totalTransfers);
        
        return map;
        
    }

}
