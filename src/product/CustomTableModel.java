/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TD06
 */
public class CustomTableModel extends AbstractTableModel{
    
    private String [] columns;
    private Object [][] rows;

    public CustomTableModel() {
    }

    public CustomTableModel(String[] columns, Object[][] rows) {
        this.columns = columns;
        this.rows = rows;
    }
    
    public Class getColumnClass(int col){
        if(col == 8){
            return Icon.class;
        }else{
            return getValueAt(0, col).getClass();
        }
    }

    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows[rowIndex][columnIndex];
    }
    
    public String getColumnName(int col){
        return this.columns[col];
    }
}
