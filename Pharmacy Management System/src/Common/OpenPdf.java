/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import javax.swing.JOptionPane;
import java.io.File;
import Dao.PharmacyUtils;

/**
 *
 * @author SR Laptop
 */
import java.awt.Desktop;


public class OpenPdf {
    public static void openById(String id) {
        try {
            File file = new File(PharmacyUtils.billPath + id + ".pdf");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}