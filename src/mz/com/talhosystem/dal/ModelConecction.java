/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mz.com.talhosystem.dal;

import java.sql.*;

/**
 *
 * @author CUSTODIO
 */
public class ModelConecction {

    // Conect to database 
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // Call the driver conector 
        String driver = "com.mysql.cj.jdbc.Driver";
        // Information to conect a database
        String url = "jdbc:mysql://localhost:3306/talho_system";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
