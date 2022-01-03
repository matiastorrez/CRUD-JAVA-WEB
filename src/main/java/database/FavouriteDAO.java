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
import model.User;

/**
 *
 * @author Matias
 */
public class FavouriteDAO {

    Connection connection;

    public FavouriteDAO() {
        DBConn conn = new DBConn();
        this.connection = conn.getConnection("homebanking", DatosDB.USER, DatosDB.PASSWORD);
    }
    
  

    public List<User> getFavouriteUsers(User user) {

        String query = "select u.id, u.username, u.email from favourites f inner join users u on u.id = f.id_favourite_user where id_user = ? order by f.id_favourite desc;";
        List<User> favourites = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    favourites.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                return favourites;
            }
        } catch (Exception ex) {
            throw new RuntimeException("No se pudieron obtener los favoritos del usuario con id " + user.getId() + " de la BD", ex);
        }
    }

    public boolean deleteFavouriteUser(User user, int idFavouriteUser) {

        String query = "delete from favourites where id_user = ? and id_favourite_user = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, idFavouriteUser);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo eliminar el favorito con id " + idFavouriteUser + " del usuario con id " + user.getId() + " de la BD", ex);
        }
    }

    public boolean isAFavouriteUser(User user, int idFavouriteUser) {
//        String query = "select u.id, u.username, u.email from favourites f inner join users u on u.id = f.id_favourite_user where id_user = ? and id_favourite_user = ?;";
        String query = "select * from favourites where id_user = ? and id_favourite_user = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, idFavouriteUser);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo verificar si el favorito con id " + idFavouriteUser + " es un favorito del usuario con id " + user.getId() + " de la BD", ex);
        }
    }
    
    public boolean addFavouriteUser(User user, int idFavouriteUser){
        String query = "INSERT INTO favourites (id_user, id_favourite_user) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, idFavouriteUser);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo verificar si el favorito con id " + idFavouriteUser + " es un favorito del usuario con id " + user.getId() + " de la BD", ex);
        }
        
        
    }

}
