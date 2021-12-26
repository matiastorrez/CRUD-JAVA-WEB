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
    
    public List<Account> getUserAccounts(User user)  {

        String query = "select a.* from users u inner join accounts a on u.id = a.id_user where u.id = ?";
        List<Account> accountsDB = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accountsDB.add(new Account(rs.getInt("id_account"), rs.getString("account_type"), rs.getDouble("total")));
                }
                return accountsDB;
            }

        } catch (Exception ex) {
            throw new RuntimeException("No se pudieron obtener las cuentas de la BD", ex);
        }
    }

    public Account getUserAccount(User user, int idAccount) {
        Account getAccount = null;
        String query = "select * from users u inner join accounts a on u.id = a.id_user where u.id = ? and a.id_account = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, idAccount);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_account");
                    String account_type = rs.getString("account_type");
                    double total = rs.getDouble("total");
                    getAccount = new Account(id, account_type, total);
                }
                return getAccount;
            }

        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener la cuenta con el id: " + idAccount + " de la BD", ex);
        }
    }

    public boolean createAccout(User user, Account account) {

        String query = "INSERT INTO accounts (account_type,total,id_user) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, account.getAccount_type());
            ps.setDouble(2, 2000.00);
            ps.setInt(3, user.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear la cuenta en la BD", ex);
        }

    }

    public boolean updateTotalAccount(Account account) {
        String query = "UPDATE accounts SET total = ? where id_account = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, account.getTotal());
            ps.setInt(2, account.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar la cuenta en la BD", ex);
        }
    }

    public Account getAccountWithUser(int id) {
        User user = null;
        Account account = null;
        String query = "select * from accounts a inner join users u on u.id = a.id_user where a.id_account = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
                return account;
            }
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener la cuenta con el usuario de la BD", ex);
        }
    }

}
