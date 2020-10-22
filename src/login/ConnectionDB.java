/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import CustomClass.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author TD06
 */
public class ConnectionDB {
    static Connection con = null;
    static PreparedStatement ps;
    static ResultSet rs;
    static String username;
    static long userid;
    public static void connection() {
        String JDBCURL="jdbc:mysql://localhost:3308/jre-homework?autoReconnect=true";
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(JDBCURL,"root","");
            ConnectionDB.con = connection;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static long automaticID(String table, String field){
        long id = 0;
        try {
            String sql = "SELECT " + field + " FROM " + table + " ORDER BY " + field + " DESC;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                id = Integer.valueOf(rs.getString(field)) + 1;
            }else{
                id =1;
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error automatic id!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
    public static void addDataToCombo(JComboBox cmb, String table, String idCol, String valueCol){
        try {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            String sql = "SELECT " + idCol + ", " + valueCol + " FROM " + table;
            rs = ps.executeQuery(sql);
            while(rs.next()){
                model.addElement(new Item(Integer.valueOf(rs.getString(idCol)), rs.getString(valueCol)));
            }
            cmb.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
