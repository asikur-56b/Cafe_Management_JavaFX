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
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField loginUsernameTF;
    @FXML
    private PasswordField logPasswordTF;
    @FXML
    private TextField signUsernameTF;
    @FXML
    private PasswordField signPasswordTF;
    @FXML
    private Button Signup;
    @FXML
    private TextField signEmailTF;
    @FXML
    private Hyperlink cnw;
    @FXML
    private Hyperlink lya;
    @FXML
    private Button exit;
    @FXML
    private AnchorPane loginAP;
    @FXML
    private AnchorPane signupAP;
    @FXML
    private Button login;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void loginUsernameTFAction(ActionEvent event) {
    }

    @FXML
    private void logPasswordTFAction(ActionEvent event) {
    }

    @FXML
    private void signUsernameTFAction(ActionEvent event) {
    }

    @FXML
    private void signPasswordTFAction(ActionEvent event) {
    }

    @FXML
    private void SignupAction(ActionEvent event) {
        
        try {

            connect = Database.connectDb();

            String sql = "INSERT INTO account (username,password,email) VALUES(?,?,?)";

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signUsernameTF.getText());
            prepare.setString(2, signPasswordTF.getText());
            prepare.setString(3, signEmailTF.getText());

            prepare.execute();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Yahoo");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Create New Account!");
                alert.showAndWait();
              

              

            

        } catch (SQLException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void signEmailTFAction(ActionEvent event) {
    }

    @FXML
    private void cnwAction(ActionEvent event) {

        if (event.getSource() == cnw) {
            signupAP.setVisible(true);
            loginAP.setVisible(false);

        }
    }

    @FXML
    private void lyaAction(ActionEvent event) {

        if (event.getSource() == lya) {
            loginAP.setVisible(true);
            signupAP.setVisible(false);
        }

    }

    @FXML
    private void exitAction(ActionEvent event) {
        exit();
    }

    @FXML
    private void loginAPAction(MouseEvent event) {
    }

   
    @FXML
    private void signupAPAction(MouseEvent event) {
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        try {

            connect = Database.connectDb();

            String sql = "SELECT * FROM ACCOUNT WHERE username = ? and password = ?";

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, loginUsernameTF.getText());
            prepare.setString(2, logPasswordTF.getText());

            result = prepare.executeQuery();

            if (result.next()) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login!");
                alert.showAndWait();
                login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Frontpage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Usernmae/Password!");
                alert.showAndWait();

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

     
       
}
