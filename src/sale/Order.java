/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import product.Product;

/**
 *
 * @author Somchanda
 */
public class Order {
    private static int qty;
    public static void scanBarcode(JTable table, JTextField barcode){
        ArrayList<Product> products = Product.getProductByBarcode(barcode.getText().trim());
        for(Product pro : products){
            System.out.println(pro.getProductName());
        }
    }
}
