/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomClass;

import javax.swing.JTable;

/**
 *
 * @author TD06
 */
public interface Action {
    public int save();
    public int update(boolean withImage);
    public int delete();
    public void search(JTable table);
}
