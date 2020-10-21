/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Somchanda
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String gender;
    private String dob;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public User() {
        ConnectionDB.connection();
    }

    
    
    public List<User> getAll(){
         try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement("SELECT * FROM tbluser");
            ConnectionDB.rs = ConnectionDB.ps.executeQuery();
            List<User> users = new ArrayList<>();
            while(ConnectionDB.rs.next()){
                users.add(new User(Integer.parseInt(ConnectionDB.rs.getString("userID")), ConnectionDB.rs.getString("username"), ConnectionDB.rs.getString("password"), ConnectionDB.rs.getString("gender"), ConnectionDB.rs.getString("dob")));
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public User(int id, String username, String password, String gender, String dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
    }
}
