/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BeveragepageController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label beverage;
    @FXML
    private Label coffeebev;
    @FXML
    private Label watjuicebev;
    @FXML
    private Label orangejuicebev;
    @FXML
    private Label applejuicebev;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private TextField price1;
    @FXML
    private TextField beveragename;
    @FXML
    private Label amount;
    private Label amount1;
    private Label amount2;
    private Label amount3;
    
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
     
    String copy;
    float ft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void homeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Frontpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void orderAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Selectcategorypage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void cartAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void offersAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Offerspage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Selectcategorypage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void coffeebevaction(ActionEvent event) {
        
        Float Quantity; 

        String name = beverage.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = coffeebev.getText();
        beveragename.setText(pname);

        price.setText(copy);

        Quantity = Float.parseFloat(quan);

    }

    @FXML
    private void watjuicebevaction(ActionEvent event) {
         
         Float Quantity; 
        
        String name = beverage.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = watjuicebev.getText();
        beveragename.setText(pname);

         price.setText(copy);

        Quantity = Float.parseFloat(quan);

    }

    @FXML
    private void orangejuicebevaction(ActionEvent event) {
 
        Float Quantity; 
        
        String name = beverage.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = orangejuicebev.getText();
        beveragename.setText(pname);

        price.setText(copy);

        Quantity = Float.parseFloat(quan);

    }

    @FXML
    private void applejuicebevaction(ActionEvent event) {
 
        Float Quantity; 
        
        String name = beverage.getText();
        price1.setText(name);

        String quan = quantity.getText();

    String pname = applejuicebev.getText();
        beveragename.setText(pname);

        price.setText(copy);

        Quantity = Float.parseFloat(quan);

    }

    @FXML
    private void pizzconfirm(ActionEvent event) {
        
        
        try {
            connect = Database.connectDb();
            String sql = "insert into addtocart(Itemname,Quantity,Unitprice,Totalprice)values(?,?,?,?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, beveragename.getText());
            prepare.setString(2, quantity.getText());
            prepare.setString(3, copy);
            
            
           // int tp = (int)(quantity.getText() * price.getText());
            
            
            prepare.setString(4, price.getText());

            prepare.execute();
            JOptionPane.showMessageDialog(null, "Item Added Successfully!");
        } catch (SQLException ex) {
            
            System.out.println(ex);
            
        }
        
        
    }

    @FXML
    private void calculatebil(ActionEvent event) {
        
        Float totalprice;
        Float Quantity;

        String pric = amount.getText();
        copy = pric;
        String quan = quantity.getText();
        totalprice = Float.parseFloat(pric);
        Quantity = Float.parseFloat(quan);

        ft = (totalprice * Quantity);
 
        price.setText(String.valueOf(ft));
        
    }

}
