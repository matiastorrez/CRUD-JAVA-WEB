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
import model.Account;
import model.User;

/**
 *
 * @author Matias
 */
public class AccountDAO {

    Connection connection;

    public AccountDAO() {
        DBConn conn = new DBConn();
        this.connection = conn.getConnection("homebanking", DatosDB.USER, DatosDB.PASSWORD);
    }

    public List<Account> getUserAccounts(User user) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        List<Account> accountsDB = new ArrayList<>();

        ps = connection.prepareStatement("select a.* from users u inner join accounts a on u.id = a.id_user where u.id = ?");
        ps.setInt(1, user.getId());
        rs = ps.executeQuery();

        while (rs.next()) {
            accountsDB.add(new Account(rs.getInt("id_account"), rs.getString("account_type"), rs.getDouble("total")));
        }

        rs.close();
        ps.close();

        return accountsDB;
    }
    
   
    public Account getUserAccount(User user, int idAccount) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        Account getAccount = null;

        ps = connection.prepareStatement("select * from users u inner join accounts a on u.id = a.id_user where u.id = ? and a.id_account = ?");
        ps.setInt(1, user.getId());
        ps.setInt(2, idAccount);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_account");
            String account_type = rs.getString("account_type");
            double total = rs.getDouble("total");
            getAccount = new Account(id, account_type, total);
        }

        rs.close();
        ps.close();
      

        return getAccount;

    }

    public boolean createAccout(User user, Account account) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO accounts (account_type,total,id_user) VALUES (?,?,?)");
        ps.setString(1, account.getAccount_type());
        ps.setDouble(2, 2000.00);
        ps.setInt(3, user.getId());
        int result = ps.executeUpdate();
        ps.close();
        return result > 0;
    }

    public boolean updateTotalAccount(Account account) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("UPDATE accounts SET total = ? where id_account = ? ");
        ps.setDouble(1, account.getTotal());
        ps.setInt(2, account.getId());
        int result = ps.executeUpdate();

        ps.close();
        return result > 0;
    }

    public Account getAccountWithUser(int id) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        User user = null;
        Account account = null;
        ps = connection.prepareStatement("select * from accounts a inner join users u on u.id = a.id_user where a.id_account = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.next()) {
            int getId = rs.getInt("id");
            String getUserName = rs.getString("username");
            String getName = rs.getString("name");
            String getLastName = rs.getString("last_name");
            String getEmail = rs.getString("email");
            String getGender = rs.getString("gender");
            user = new User(getId, getUserName, getName, getLastName, getEmail, getGender);

            int idAccount = rs.getInt("id_account");
            String accountType = rs.getString("account_type");
            double total = rs.getDouble("total");

            account = new Account(idAccount, accountType, total, user);
        }

        rs.close();
        ps.close();
      
        return account;
    }

}
