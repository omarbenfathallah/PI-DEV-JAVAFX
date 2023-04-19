/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AchatDAO aa = new AchatDAO();
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

}
