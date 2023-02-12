/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUIinterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ivana
 */
public interface GraphingInterface {
    /**
     * Čišćenje baze podataka na početku korištenja
     * @param url link na bazu podataka kalkulatora
     * @author Ivana
     */
    public default void setUpDatabase(String url){
        String sql1 ="DELETE FROM Polinomi;";
        String sql2 ="VACUUM;";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {}
        Connection conn=null;
        ResultSet result = null; 
        try{
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    };
}
