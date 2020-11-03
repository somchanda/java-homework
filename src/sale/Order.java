/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import product.Product;

/**
 *
 * @author Somchanda
 */
public class Order {

    private static int qty;
    private static double amount;
    private static DefaultTableModel model;

    public static void scanBarcode(JTable table, JTextField barcode) {
        model = (DefaultTableModel) table.getModel();
        ArrayList<Product> products = Product.getProductByBarcode(barcode.getText().trim());
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no product with this barcode!", "Product not found", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            int bar = Integer.parseInt(model.getValueAt(i, 5).toString());
            for (Product pro : products) {
                if (pro.getBarcode() == bar) {
                    qty = Integer.parseInt(model.getValueAt(i, 1).toString()) + 1;
                    amount = pro.getSellPrice() * qty;
                    model.setValueAt(qty, i, 1);
                    model.setValueAt(amount, i, 3);
                    return;
                }
            }
        }
        for (Product pro : products) {
            qty = 1;
            amount = qty * pro.getSellPrice();
            Object[] row = {pro.getProductName(), qty, pro.getSellPrice(), amount, pro.getId(), pro.getBarcode()};
            model.addRow(row);
        }

    }

    public static double getTotalAmount(JTable table) {
        DefaultTableModel mode = (DefaultTableModel) table.getModel();
        double total = 0;
        try {
            for (int i = 0; i < mode.getRowCount(); i++) {
                total += Double.parseDouble(mode.getValueAt(i, 3).toString());
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }
}
