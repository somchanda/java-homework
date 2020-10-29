/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import CustomClass.Action;
import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import login.ConnectionDB;

/**
 *
 * @author TD06
 */
public class Product implements Action {

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

    public void setPhoto(byte[] photo) {
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

    public static void addProductToTable(JTable table, String viewName) {
        try {
            ConnectionDB.rs = ConnectionDB.ps.executeQuery("SELECT * FROM " + viewName);
            while (ConnectionDB.rs.next()) {
                Object row[] = {ConnectionDB.rs.getString("productName"), ConnectionDB.rs.getString("barcode"), ConnectionDB.rs.getString("unitPrice"), ConnectionDB.rs.getString("sellPrice"), ConnectionDB.rs.getString("productName")};
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int update(boolean withImage) {
        if (withImage == true) {
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
        } else {
            String sql = "UPDATE tblproduct SET productName=?, barcode=?, unitPrice=?, sellPrice=?, categoryID=?, QtyInStock=? WHERE productID=?";
            try {
                ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
                ConnectionDB.ps.setLong(7, id);
                ConnectionDB.ps.setString(1, productName);
                ConnectionDB.ps.setDouble(2, barcode);
                ConnectionDB.ps.setDouble(3, unitPrice);
                ConnectionDB.ps.setDouble(4, sellPrice);
                ConnectionDB.ps.setInt(5, categoryID);
                ConnectionDB.ps.setInt(6, unitInStock);
                return ConnectionDB.ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }

    @Override
    public int delete() {
        String sql = "DELETE FROM tblproduct WHERE productID=?";
        try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
            ConnectionDB.ps.setLong(1, this.id);
            return ConnectionDB.ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public ArrayList<Product> productList() {
        String sql = "SELECT * FROM viewProduct ORDER BY productID";
        ArrayList<Product> pList = new ArrayList<>();
        try {
            ConnectionDB.rs = ConnectionDB.ps.executeQuery(sql);
            while (ConnectionDB.rs.next()) {
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
    
    public ArrayList<Product> productListByNameAndBarcode() {
        String sql = "SELECT * FROM viewProduct WHERE productName LIKE CONCAT( '%',?,'%') OR barcode = ? OR productID = ?";
        ArrayList<Product> pList = new ArrayList<>();
        try {
            ConnectionDB.ps = ConnectionDB.con.prepareStatement(sql);
            ConnectionDB.ps.setString(1, this.productName);
            ConnectionDB.ps.setLong(2, this.barcode);
            ConnectionDB.ps.setLong(3, this.id);
            ConnectionDB.rs = ConnectionDB.ps.executeQuery();
            while (ConnectionDB.rs.next()) {
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
    
    @Override
    public void search(JTable table){
        ArrayList<Product> pList = this.productListByNameAndBarcode();
        String[] colNames = {"ID", "Product Name", "Barcode", "Unit Price", "Sell Price", "Qty", "Category Name", "Photo"};
        Object[][] rows = new Object[pList.size()][8];
        for (int i = 0; i < pList.size(); i++) {
            rows[i][0] = pList.get(i).getId();
            rows[i][1] = pList.get(i).getProductName();
            rows[i][2] = pList.get(i).getBarcode();
            rows[i][3] = pList.get(i).getUnitPrice();
            rows[i][4] = pList.get(i).getSellPrice();
            rows[i][5] = pList.get(i).getUnitInStock();
            rows[i][6] = pList.get(i).getCateogoryName();
            ImageIcon pic = new ImageIcon(new ImageIcon(pList.get(i).getPhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            rows[i][7] = pic;
        }
        CustomTableModel model = new CustomTableModel(colNames, rows);
        table.setModel(model);
        table.setRowHeight(100);
        table.setShowGrid(true);
        JTableHeader th = table.getTableHeader();
        th.setFont(new Font("Times New Roman", Font.BOLD, 16));
    }
}
