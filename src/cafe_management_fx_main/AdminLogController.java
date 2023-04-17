
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

 

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminLogController implements Initializable {

    @FXML
    private TextField loginUsernameAD;
    @FXML
    private PasswordField logPasswordAD;
    @FXML
    private Button exit;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Button loginbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginActionAdmin(ActionEvent event) throws IOException {
  
    try {

            connect = Database.connectDb();

            String sql = "SELECT * FROM adminlogin WHERE usernameadmin = ? and passwordadmin = ?";

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, loginUsernameAD.getText());
            prepare.setString(2, logPasswordAD.getText());

            result = prepare.executeQuery();

            if (result.next()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login as Admin!");
                alert.showAndWait();
                loginbtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Empty.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Usernmae/Password!");
                alert.showAndWait();

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @FXML
    private void exitAction(ActionEvent event) {
    }
    
}
