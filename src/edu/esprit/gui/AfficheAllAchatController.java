/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.dao.classes.AchatDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import javafx.collections.ObservableList;

import edu.esprit.entities.Achat;
import edu.esprit.entities.Offre;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficheAllAchatController implements Initializable {

    @FXML
    private TableColumn<Achat, Integer> iduser;
    @FXML
    private TableColumn<Achat, Date> date;
    @FXML
    private TableColumn<Achat, Integer> offre;
    @FXML
    private Button btnR;
    ObservableList<Achat> obList;

     ObservableList<Achat> obListAch = FXCollections.observableArrayList();
    
    Achat a = new Achat();
    @FXML
    private TableView<Achat> affichageAchat;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private Button butnstat;
    @FXML
    private NumberAxis nombre;
    @FXML
    private CategoryAxis Axissss;
    @FXML
    private Button imprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        AchatDAO aa = new AchatDAO();
        obListAch = aa.DisplayAllAchat();
        
        //   idachat.setCellValueFactory(new PropertyValueFactory<>("id_achat"));

        iduser.setCellValueFactory(new PropertyValueFactory<>("id_us"));
        offre.setCellValueFactory(new PropertyValueFactory<>("id_off"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_achat"));

        List<Achat> list2 = aa.DisplayAllAchat();
        affichageAchat.getItems().addAll(list2);

    }

    @FXML
    private void RetourAccueil(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleStatsButtonAction(ActionEvent event) {

        ObservableList<Achat> achatlis = affichageAchat.getItems();
        Map<String, Integer> stats = new HashMap<>();
        for (Achat achat : achatlis) {
            Offre offre = achat.getId_off();
            String nom = offre.getNom();
            stats.put(nom, stats.getOrDefault(nom, 0) + 1);
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Offre");
        for (String nom : stats.keySet()) {
            int count = stats.get(nom);
            XYChart.Data<String, Integer> data = new XYChart.Data<>(nom, count);
            String label = nom + " (" + count + ")";
            Tooltip tooltip = new Tooltip(label);
            Tooltip.install(data.getNode(), tooltip);
            series.getData().add(data);
        }

// Afficher le graphique
        barChart.getData().clear();
        barChart.getData().add(series);
        nombre.setLabel("nombre");
        Axissss.setLabel("Nom d'offre");
        barChart.setTitle("Statistiques des Offre acheter");

    }

    @FXML
    private void boutonimrimer(ActionEvent event) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("RapportAchat.pdf"));
            document.open();
            Chunk c = new Chunk("Rapport Achat");
            Paragraph p1 = new Paragraph(c);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setSpacingAfter(10f);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.now();
            Paragraph date = new Paragraph(dtf.format(localDate));
            date.setAlignment(Paragraph.ALIGN_RIGHT);

            document.add(date);

            document.add(p1);

            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Phrase("User"));

            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(new Phrase("Date Achat"));

            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(new Phrase("Offre"));
            
             cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            for (int i = 0; i < obListAch.size(); i++) {
                
                table.addCell(affichageAchat.getItems().get(i).getId_us().toString());
                table.addCell("" + affichageAchat.getItems().get(i).getDate_achat());
                table.addCell(affichageAchat.getItems().get(i).getId_off().toString());
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
