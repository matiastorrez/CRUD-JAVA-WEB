/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matias
 */
public class DBConn {

    private String driver = "com.mysql.jdbc.Driver";
    private String host = "jdbc:mysql://localhost:3307/";

    public Connection getConnection(String database, String userMYSQL, String passMYSQL) {

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(host + database, userMYSQL, passMYSQL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

}
