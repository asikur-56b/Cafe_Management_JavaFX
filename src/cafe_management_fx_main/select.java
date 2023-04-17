/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
class select {
     Connection c=null;
        Statement st=null;
        ResultSet rs=null;
    public select() {
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost/marco","root","");
            st=c.createStatement();
        }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
        
    }
}
