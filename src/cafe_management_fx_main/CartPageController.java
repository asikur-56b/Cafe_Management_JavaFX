/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe_management_fx_main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CartPageController implements Initializable {

    @FXML
    private TableView<BillDetails> billTable;
    @FXML
    private TableColumn<BillDetails, String> colitemName;
    @FXML
    private TableColumn<BillDetails, String> colQuantity;
    @FXML
    private TableColumn<BillDetails, String> colUPrice;
    @FXML
    private TableColumn<BillDetails, String> colTotalPrice;

    ObservableList< BillDetails> oblist = FXCollections.observableArrayList();
    @FXML
    private Button PrintButton;
    @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        select s1 = new select();
        ResultSet rs;
        try {
            rs = s1.st.executeQuery("SELECT * FROM addtocart;");
            while (rs.next()) {
                oblist.add(new BillDetails(rs.getString("Itemname"), rs.getInt("Quantity"), rs.getInt("Unitprice"), rs.getInt("Totalprice")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colitemName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        billTable.setItems(oblist);
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
    private void printButton(ActionEvent event) {

        try {

            String path = "D:\\";
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            try {

               
                PdfWriter.getInstance(doc, new FileOutputStream(path + "Bill.pdf"));
                doc.open();
                Paragraph paragraph1 = new Paragraph("                                     Cafe Bill                                     ");
                doc.add(paragraph1);
                Paragraph paragraph2 = new Paragraph("*******************************************************************************************");
                doc.add(paragraph2);

                PdfPTable tb1 = new PdfPTable(4);

                select s1 = new select();
                ResultSet rs;
                try {
                    rs = s1.st.executeQuery("SELECT * FROM addtocart;");
                    while (rs.next()) {
                        String name = rs.getString("Itemname");
                        int quanty = rs.getInt("Quantity");
                        int uprice = rs.getInt("Unitprice");
                        int tprice = rs.getInt("Totalprice");
                        tb1.addCell("Item Name: " + name);
                        tb1.addCell("Quantity: " + quanty);
                        tb1.addCell("Unit Price: " + uprice);
                        tb1.addCell("Total Amount Paid: " + tprice);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
                }

                doc.add(tb1);
                doc.add(paragraph2);
                Paragraph paragraph5 = new Paragraph("Thank You . Please Visit Again");
                doc.add(paragraph5);

            } catch (DocumentException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            doc.close();
            JOptionPane.showMessageDialog(null, "Bill Completed");
            int a = JOptionPane.showConfirmDialog(null, "Do you want to print Bill", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                try {
                    if ((new File("D:\\Bill.pdf")).exists()) {
                        Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\Bill.pdf");
                    } else {
                        System.out.println("File not found");
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            //new add
            select s1 = new select();
            String str = "DELETE  FROM addtocart;";
            s1.st.executeUpdate(str);

        } catch (SQLException ex) {
            Logger.getLogger(CartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Frontpage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
