/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import product.CustomTableModel;
import product.Product;

/**
 *
 * @author Somchanda
 */
public class Order {
    public static void ScanBarcode(DefaultTableModel model, JTextField txt){
        ArrayList<Product> pList = Product.getProductByBarcode(txt.getText());
        Object[] colNames = {"Product Name","Price", "Qty", "ID", "Barcode"};
        Object[][] rows = new Object[pList.size()][5];
        for (int i = 0; i < pList.size(); i++) {
            rows[i][3] = pList.get(i).getId();
            rows[i][0] = pList.get(i).getProductName();
            rows[i][4] = pList.get(i).getBarcode();
            rows[i][2] = 1;
            rows[i][1] = pList.get(i).getSellPrice();
        }
//        model.(colNames);
//        model.addRow(rows);
    }
}
