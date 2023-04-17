/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import static java.awt.SystemColor.info;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */

public class Database {
    public static Connection connectDb(){
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/marco","root","");
            return connect;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
           
    }
}
