/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import static java.awt.Event.INSERT;
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
import static javafx.scene.input.KeyCode.INSERT;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BurgerpageController implements Initializable {

    Database data = new Database();

    @FXML
    private Button back;
    @FXML
    private Button chikburid;
    @FXML
    private TextField burgername;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;

    private String pname;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Label chikbur;
    @FXML
    private TextField price1;
    @FXML
    private Label amount;
    @FXML
    private Label burger;
    @FXML
    private Label cheeseburg;
    @FXML
    private Label Garlicburg;
    @FXML
    private Label Mushroomburg;
    private Label amount2;
    private Label amount3;
    @FXML
    private Button Cheeseburid;
    @FXML
    private Button Garlicburid;
    @FXML
    private Button Mushroomburid;

    float ft;
    String copy;

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
    private void chikburaction(ActionEvent event) throws SQLException {

        //String unitp;
        Float Quantity;

        String name = burger.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = chikbur.getText();
        burgername.setText(pname);
        
        price.setText(copy);

        Quantity = Float.parseFloat(quan);
        
        //String po = amount.getText();
       // unitprice.setText(po);
        

    }

    @FXML
    private void burgconfirm(ActionEvent event) throws SQLException {

        try {
            connect = Database.connectDb();
            String sql = "insert into addtocart(Itemname,Quantity,Unitprice,Totalprice)values(?,?,?,?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, burgername.getText());
            prepare.setString(2, quantity.getText());           
            prepare.setString(3, copy);
            prepare.setFloat(4, ft);

            // int tp = (int)(quantity.getText() * price.getText());
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Item Added Successfully!");
        } catch (SQLException ex) {

            System.out.println(ex);

        }
    }

    @FXML
    private void CheeseburAction(ActionEvent event) {
     
        
        Float Quantity; 
        
        String name = burger.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = cheeseburg.getText();
        burgername.setText(pname);

        price.setText(copy);

        Quantity = Float.parseFloat(quan);

    }

    @FXML
    private void Garlicburaction(ActionEvent event) {
        
        
        Float Quantity; 

        String name = burger.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = Garlicburg.getText();
        burgername.setText(pname);
    
        price.setText(copy);

        Quantity = Float.parseFloat(quan);


    }

    @FXML
    private void MushroomburAction(ActionEvent event){
        
        Float Quantity; 

        String name = burger.getText();
        price1.setText(name);

        String quan = quantity.getText();

        String pname = Mushroomburg.getText();
        burgername.setText(pname);

        price.setText(copy);

        Quantity = Float.parseFloat(quan);
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
