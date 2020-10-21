/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author TD06
 */
public class ConnectionDB {
    static Connection con = null;
    static PreparedStatement ps;
    static ResultSet rs;
    public static void connection() {
        String JDBCURL="jdbc:mysql://localhost:3308/jre-homework?autoReconnect=true";
        Connection con=null;
        try{
            con = DriverManager.getConnection(JDBCURL,"root","");
            ConnectionDB.con = con;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
