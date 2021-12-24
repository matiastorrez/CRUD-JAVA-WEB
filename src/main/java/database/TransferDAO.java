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
import model.Transfer;
import model.User;

/**
 *
 * @author Matias
 */
public class TransferDAO {

    Connection connection;

    public TransferDAO() {
        DBConn conn = new DBConn();
        this.connection = conn.getConnection("homebanking", DatosDB.USER, DatosDB.PASSWORD);

    }

    public boolean createTransfer(Transfer transfer) throws SQLException {

        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO transfers (origin,destination,amount) VALUES (?,?,?)");
        ps.setInt(1, transfer.getOrigin().getId());
        ps.setInt(2, transfer.getDestination().getId());
        ps.setDouble(3, transfer.getAmount());
        int result = ps.executeUpdate();
        ps.close();
        return result > 0;

    }

    public List<Transfer> getAllTransfers(User user) throws SQLException {
        AccountDAO accountDB = new AccountDAO();
        PreparedStatement ps;
        ResultSet rs;
        List<Transfer> transfers = new ArrayList<>();

//       HACIENDO ESTO ME EVITRIA LLAMAR A AccountDAO ya que puedo obtener todos los datos de esta consulta
//select * from transfers t
//inner join accounts ao on t.origin = ao.id_account 
//inner join accounts ad on t.destination = ad.id_account 
//inner join users uo on uo.id = ao.id_user
//inner join users ud on ud.id = ad.id_user
//where t.origin IN(select id_account from accounts where id_user = ?) OR t.destination IN(select id_account from accounts where id_user = ?);
        ps = connection.prepareStatement("select * from transfers t where t.origin IN(select id_account from accounts where id_user = ?) OR t.destination IN(select id_account from accounts where id_user = ?)");
        ps.setInt(1, user.getId());
        ps.setInt(2, user.getId());
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int origin = rs.getInt("origin");
            int destination = rs.getInt("destination");
            double amount = rs.getDouble("amount");
            Account accountOrigin = accountDB.getAccountWithUser(origin);
            Account accountDestination = accountDB.getAccountWithUser(destination);
            Transfer transfer = new Transfer(id, accountOrigin, accountDestination, amount);
            transfers.add(transfer);
        }
        return transfers;

    }

}
