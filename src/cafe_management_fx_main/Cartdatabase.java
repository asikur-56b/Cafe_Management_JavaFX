/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class Cartdatabase {
   
    public static Connection connectDb(){
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/addtocart","root","");
            return connect;
        } catch(Exception e){e.printStackTrace();}
        return null;
        
        
        
       
       
    }
}
    

