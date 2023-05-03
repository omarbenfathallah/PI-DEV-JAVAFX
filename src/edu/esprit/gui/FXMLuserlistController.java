/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import edu.esprit.dao.classes.UserService;
import edu.esprit.util.Session;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.User;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class FXMLuserlistController implements Initializable {

    @FXML
    private TableView<User> tableU;
    @FXML
    private TableColumn<User, String> colFirstname;
    @FXML
    private TableColumn<User, String> colLastname;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colAddress;
    @FXML
    private TableColumn<User, String> colPhone;

    @FXML
    private TextField filterField;
    UserService sp = new UserService();
    User user = new User();
    ObservableList<User> list = FXCollections.observableArrayList();
    @FXML
    private ImageView userImg;
    @FXML
    private Label txtUsername;
    @FXML
    private Button logout;
    @FXML
    private Button imprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user = sp.DisplayById(Session.current_user.getId());

        tableU.setItems(refresh());

        searsh();

    }

    public ObservableList<User> refresh() {

        colAddress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        list = sp.getAll();
        return list;
    }

    @FXML
    private void logout(ActionEvent event) {
        Session.current_user = null;
        try {
            Parent page = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void delete(ActionEvent event) {

        sp.supprimer(tableU.getSelectionModel().getSelectedItem().getId());
        searsh();
        tableU.setItems(refresh());

    }

    @FXML
    private void edit(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("FXMLprofil.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void searsh() {
        FilteredList<User> filteredData = new FilteredList<>(refresh(), b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches Email.
                } else if (user.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches firstname.
                } else if (user.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches lastname.
                } else if (user.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches adresse.
                } else if (String.valueOf(user.getNumero()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableU.comparatorProperty());

        // 5. Add sorted (and filtered) data to the tableU.
        tableU.setItems(sortedData);
    }

    @FXML
    private void boutonimprimer(ActionEvent event) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("RapportUser.pdf"));
            document.open();
            Chunk c = new Chunk("Rapport User");
            Paragraph p1 = new Paragraph(c);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setSpacingAfter(10f);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.now();
            Paragraph date = new Paragraph(dtf.format(localDate));
            date.setAlignment(Paragraph.ALIGN_RIGHT);

            document.add(date);

            document.add(p1);

            PdfPTable table = new PdfPTable(4);

            PdfPCell cell1 = new PdfPCell(new Phrase("FirstName"));

            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Phrase("LastName"));

            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Phrase("Email"));

            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell4 = new PdfPCell(new Phrase("Telephone"));
            
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
         
     
            
            

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
        //    table.addCell(cell5);

            for (int i = 0; i < list.size(); i++) {

                table.addCell(tableU.getItems().get(i).getFirstname().toString());
                table.addCell(tableU.getItems().get(i).getLastname().toString());
                table.addCell(tableU.getItems().get(i).getEmail().toString());
//                table.addCell(tableU.getItems().get(i).getAdresse().toString());
                table.addCell(tableU.getItems().get(i).getNumero().toString());

            }
            document.add(table);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre Rapport a été généré avec succès");
            alert.show();
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
