/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejadorPersistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jean
 */
public class Conexion {
    Connection con;

    public Conexion() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/covid-db";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
