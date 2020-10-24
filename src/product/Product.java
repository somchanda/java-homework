/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import CustomClass.Action;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.ConnectionDB;

/**
 *
 * @author TD06
 */
public class Product implements Action{
    private int id;
    private String productName;
    private int barcode;
    private double unitPrice;
    private double sellPrice;
    private int categoryID;
    private FileInputStream photo;
    private int unitInStock;

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    @Override
    public void save() {
        String sql = "INSERT INTO tblproduct VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
//            ConnectionDB.ps.setInt(1, id);
//            ConnectionDB.ps.setString(2, productName);
//            ConnectionDB.ps.setDouble(3, barcode);
//            ConnectionDB.ps.setDouble(4, unitPrice);
//            ConnectionDB.ps.setDouble(5, sellPrice);
//            ConnectionDB.ps.setInt(6, categoryID);
//            ConnectionDB.ps.setBinaryStream(7, photo);
//            ConnectionDB.ps.setInt(8, unitInStock);
            System.out.println(photo);
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
