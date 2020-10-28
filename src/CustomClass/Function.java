/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomClass;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author TD06
 */
public class Function {

    public static int checkTextBoxEmpty(JTextField... txt) {
        for (JTextField t : txt) {
            String st = t.getText().trim();
            if (st.length() == 0) {
                t.grabFocus();
                Toolkit.getDefaultToolkit().beep();
                return 0;
            }
        }
        return 1;
    }

    public static void setImage(JLabel lbl, JTextField txt) {
        String pathImage = null;
        JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getFileSystemView().getHomeDirectory());
        choose.setDialogTitle("Select an image");
        choose.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, gif and jpg", "png", "gif", "jpg");
        choose.setFileFilter(filter);
        int click = choose.showOpenDialog(null);
        if(click == JFileChooser.APPROVE_OPTION){
            File files = choose.getSelectedFile();
            pathImage = files.getPath();
            ImageIcon icon = new ImageIcon(new ImageIcon(pathImage).getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
            lbl.setIcon(icon);
            txt.setText(pathImage);
        }
    }
    public static void setImage(JLabel lbl, String pathImage) {
            ImageIcon icon = new ImageIcon(new ImageIcon(pathImage).getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
            lbl.setIcon(icon);
    }
    public static void setImage(JLabel lbl, BufferedImage bmg) {
            ImageIcon icon = new ImageIcon(new ImageIcon(bmg).getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
            lbl.setIcon(icon);
    }
    public static void clearText(JTextField... txts){
        for(JTextField txt : txts){
            txt.setText("");
        }
    }
    public static void clearImage(JLabel... lbls){
        for(JLabel lbl : lbls){
            lbl.setIcon(null);
        }
    }
    public static ImageIcon setImage(String pathImage, byte[] blobPic, int w, int h) {
        ImageIcon myImg;
        if(pathImage != null){
            myImg = new ImageIcon(pathImage);
        }else{
            myImg = new ImageIcon(blobPic);
        }
        Image img = myImg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }
}
