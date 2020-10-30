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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author TD06
 */
public class ConnectionDB {

    static public Connection con = null;
    static public PreparedStatement ps;
    static public ResultSet rs;
    static public String username;
    static public long userid;

    public static void connection() {
        String JDBCURL = "jdbc:mysql://localhost:3306/jre-homework?autoReconnect=true";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBCURL, "root", "");
            ConnectionDB.con = connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long automaticID(String table, String field) {
        long id = 0;
        try {
            String sql = "SELECT " + field + " FROM " + table + " ORDER BY " + field + " DESC;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = Integer.valueOf(rs.getString(field)) + 1;
            } else {
                id = 1;
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error automatic id!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public static List<Item> addDataToCombo(JComboBox cmb, String table, String idCol, String valueCol) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Item> items = new ArrayList<>();
        try {
            String sql = "SELECT " + idCol + ", " + valueCol + " FROM " + table;
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item(Integer.valueOf(rs.getString(idCol)), rs.getString(valueCol));
                items.add(item);
                model.addElement(item);
            }
            cmb.setModel(model);
            return items;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean isDuplicate(String table, String column, JTextField txt, String msg) {
        try {
            String sql = "SELECT * FROM " + table + " WHERE " + column + " = '" + txt.getText().trim() + "'";
            rs = ps.executeQuery(sql);
            int r = 0;
            if (rs.next()) {
                r++;
            }
            if (r > 0) {
                JOptionPane.showMessageDialog(null, msg, "Required field", JOptionPane.WARNING_MESSAGE);
                txt.grabFocus();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean isDuplicateExcept(String table, String column, JTextField txt, int idExcept, String idField, String msg) {
        try {
            String sql = "SELECT * FROM " + table + " WHERE " + column + " = '" + txt.getText().trim() + "' and " + idField + " != " + idExcept;
            rs = ps.executeQuery(sql);
            int r = 0;
            if (rs.next()) {
                r++;
            }
            if (r > 0) {
                JOptionPane.showMessageDialog(null, msg, "Required field", JOptionPane.WARNING_MESSAGE);
                txt.grabFocus();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static void addDataToTable(JTable table, String viewName){
        try {
            rs = ps.executeQuery("SELECT * FROM "+ viewName);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
