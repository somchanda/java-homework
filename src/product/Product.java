/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import CustomClass.Action;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import login.ConnectionDB;

/**
 *
 * @author TD06
 */
public class Product implements Action{
    private long id;
    private String productName;
    private int barcode;
    private double unitPrice;
    private double sellPrice;
    private int categoryID;
    private byte[] photo;
    private int unitInStock;
    private String CateogoryName;
    
    public Product() {
    }

    public Product(long id, String productName, int barcode, double unitPrice, double sellPrice, int categoryID, byte[] photo, int unitInStock) {
        this.id = id;
        this.productName = productName;
        this.barcode = barcode;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
        this.categoryID = categoryID;
        this.photo = photo;
        this.unitInStock = unitInStock;
    }

    public Product(long id, String productName, int barcode, double unitPrice, double sellPrice, int categoryID, byte[] photo, int unitInStock, String CateogoryName) {
        this.id = id;
        this.productName = productName;
        this.barcode = barcode;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
        this.categoryID = categoryID;
        this.photo = photo;
        this.unitInStock = unitInStock;
        this.CateogoryName = CateogoryName;
    }

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte [] photo) {
        this.photo = photo;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public String getCateogoryName() {
        return CateogoryName;
    }

    public void setCateogoryName(String CateogoryName) {
        this.CateogoryName = CateogoryName;
    }

    @Override
    public int save() {
        String sql = "INSERT INTO tblproduct VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
            ConnectionDB.ps.setLong(1, id);
            ConnectionDB.ps.setString(2, productName);
            ConnectionDB.ps.setDouble(3, barcode);
            ConnectionDB.ps.setDouble(4, unitPrice);
            ConnectionDB.ps.setDouble(5, sellPrice);
            ConnectionDB.ps.setInt(6, categoryID);
            ConnectionDB.ps.setBytes(7, photo);
            ConnectionDB.ps.setInt(8, unitInStock);
            return ConnectionDB.ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public static void addProductToTable(JTable table, String viewName){
        try {
            ConnectionDB.rs = ConnectionDB.ps.executeQuery("SELECT * FROM " + viewName);
            while(ConnectionDB.rs.next()){
                Object row[] = {ConnectionDB.rs.getString("productName"), ConnectionDB.rs.getString("barcode"), ConnectionDB.rs.getString("unitPrice"), ConnectionDB.rs.getString("sellPrice"), ConnectionDB.rs.getString("productName")};
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int update() {
         String sql = "UPDATE tblproduct SET productName=?, barcode=?, unitPrice=?, sellPrice=?, categoryID=?, photo=?, QtyInStock=? WHERE productID=?";
        try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
            ConnectionDB.ps.setLong(8, id);
            ConnectionDB.ps.setString(1, productName);
            ConnectionDB.ps.setDouble(2, barcode);
            ConnectionDB.ps.setDouble(3, unitPrice);
            ConnectionDB.ps.setDouble(4, sellPrice);
            ConnectionDB.ps.setInt(5, categoryID);
            ConnectionDB.ps.setBytes(6, photo);
            ConnectionDB.ps.setInt(7, unitInStock);
            return ConnectionDB.ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Product> productList (){
        String sql = "SELECT * FROM viewProduct";
        ArrayList<Product> pList = new ArrayList<>();
        try {
            ConnectionDB.rs = ConnectionDB.ps.executeQuery(sql);
            while(ConnectionDB.rs.next()){
                Product p = new Product(
                        ConnectionDB.rs.getInt("productID"), 
                        ConnectionDB.rs.getString("productName"), 
                        ConnectionDB.rs.getInt("barcode"), 
                        ConnectionDB.rs.getDouble("unitPrice"), 
                        ConnectionDB.rs.getDouble("sellPrice"), 
                        ConnectionDB.rs.getInt("categoryID"), 
                        ConnectionDB.rs.getBytes("photo"), 
                        ConnectionDB.rs.getInt("qtyInStock"),
                        ConnectionDB.rs.getString("categoryName")
                        );
                pList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }
}
