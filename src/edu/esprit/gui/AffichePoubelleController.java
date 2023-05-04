/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Poubelle;
import edu.esprit.entities.DemandeP;
import edu.esprit.entities.Type;
import edu.esprit.util.MyConnection;
import edu.esprit.gui.getData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
//import net.sf.jasperreports.engine.JRException;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author marwa
 */
public class AffichePoubelleController implements Initializable {

    @FXML
    private TableColumn<DemandeP, String> DDe_col_EtatDde;

    @FXML
    private TableColumn<Poubelle, String> ajouterPoubelle_col_Image;

    @FXML
    private TableColumn<DemandeP, String> DDe_col_dateDde;

    @FXML
    private TableColumn<DemandeP, String> DDe_col_poubelle;

    @FXML
    private TableColumn<DemandeP, String> DDe_col_quantite;

    @FXML
    private TableColumn<DemandeP, String> DDe_col_ref;

    @FXML
    private TableView<DemandeP> Tableau_dde_View;
    @FXML
    private ImageView ImageView_Poubelle;


    private TextField POUBELLE;

    private DatePicker date_dde;
    private TextField etat_Dde;

    private TextField ref_dde;
    @FXML
    private Button Addpoubl_btn;
    private TextField Quantit_Pubelle;

    @FXML
    private TextField Ajoutpoubelle_search;

    @FXML
    private TableColumn<Type, Integer> type_clon_id;

    @FXML
    private TableView<Type> type_table;
    @FXML
    private TextField type_poubelle;

    @FXML
    private ComboBox<?> Couleur_poubelle;

    @FXML
    private TextField Desc_poubelle;

    @FXML
    private TextField Name_poubelle;

    @FXML
    private TableColumn<Poubelle, String> ajouterPoubelle_col_Name;

    @FXML
    private TableColumn<Poubelle, String> ajouterPoubelle_col_PoidsVide;

    @FXML
    private TableColumn<Poubelle, String> ajouterPoubelle_col_couleur;

    @FXML
    private TableColumn<Poubelle, String> ajouterPoubelle_col_description;

    @FXML
    private TableView<Poubelle> ajouterPoubelle_tableauView;

    @FXML
    private Button type_btn;

    @FXML
    private TableColumn<Poubelle, Integer> ajouterPoubelle_col_TPID;
    @FXML
    private AnchorPane type_form;

    @FXML
    private TextField type_type;
    @FXML
    private TextField type_id;

    @FXML
    private AnchorPane demande_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> poidsVide_poubelle;

    @FXML
    private Button poub_btn;
    


    @FXML
    private Label file_path;
    

    @FXML
    private AnchorPane poubelle_form;


    @FXML
    private TableColumn<Type, Integer> type_colone;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    private Image image;
    @FXML

    private Button addTypeBTN;
    @FXML
    private Button DeleteTypeBtn;
    @FXML
    private Label nametype;
    @FXML
    private Label nametype1;
    @FXML
    private ScrollPane poubelles_avaible;
    @FXML
    private GridPane poubelles_liste;
    @FXML
    private Label poidsVide;
    @FXML
    private Label coulurePoubelle;
    @FXML
    private Button Ajoutpoubelle_add_btn;
    @FXML
    private Button Ajoutpoubelle_update_btn;
    @FXML
    private Button Ajoutpoubelle_clear_btn;
    @FXML
    private Button Ajoutpoubelle_delete_btn;
    @FXML
    private Button Ajoutpoubelle_dde_btn;
    @FXML
    private Label typepoubelle;
    @FXML
    private Button ImportIMG_BTn;
    @FXML
    private Button Print;
    @FXML
    private AnchorPane DDE_poubellr;
    @FXML
    private TextField dde_search;
    @FXML
    private Button BtnR;


      @Override
    public void initialize(URL location, ResourceBundle resources) {
        poidsVidePoubelle();
        CouleurPoubelles();
     
        typeShowData();
        poubellesSelectData();
        try {
            demandeShowData();
        } catch (SQLException ex) {
            Logger.getLogger(AffichePoubelleController.class.getName()).log(Level.SEVERE, null, ex);
        }
//
        try {
            poubellesShowData();
        } catch (SQLException ex1) {
            Logger.getLogger(AffichePoubelleController.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    public ObservableList<Poubelle> poubelleListData() throws SQLException { 
        ObservableList<Poubelle> listData = FXCollections.observableArrayList();

        String sql = "SELECT Poubelle.id AS poubelle_id, Poubelle.name, Poubelle.poids_vide, Poubelle.couleur, Poubelle.description, Poubelle.image, Type.id AS type_id, Type.name AS type_name "
                + "FROM Poubelle "
                + "INNER JOIN Type ON Poubelle.type_id = Type.id";


        try {
              connect = MyConnection.getInstance().getConnection();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Poubelle poubelle;
            while (result.next()) {
                poubelle = new Poubelle(
//                        result.getInt("poubelle_id"),
                        result.getInt("type_id"),
                        result.getString("name"),
                        result.getString("poids_vide"),
                        result.getString("couleur"),
                        result.getString("description"),
                        result.getString("image")
                );

                Type type = new Type(result.getString("type_id"));
                poubelle.setType(type);

                listData.add(poubelle);
            }
        } catch (SQLException ex) {

   }
        return listData;
    }


 private ObservableList<Poubelle> poubellDatalist;

    @FXML
    public void poubellesShowData() throws SQLException {
        poubellDatalist = poubelleListData();
        ajouterPoubelle_col_TPID.setCellValueFactory(new PropertyValueFactory<>("type_id"));
        ajouterPoubelle_col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ajouterPoubelle_col_PoidsVide.setCellValueFactory(new PropertyValueFactory<>("poids_vide"));
        ajouterPoubelle_col_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        ajouterPoubelle_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
       ajouterPoubelle_col_Image.setCellValueFactory(new PropertyValueFactory<>("image"));
        ajouterPoubelle_tableauView.setItems(poubellDatalist);
        for (Poubelle poubelle : poubellDatalist) {
            String typeId = poubelle.getName();
            Type type = typePListData().stream().filter(t -> t.getName() == typeId).findFirst().orElse(null);
            poubelle.setType(type);
        }
    }

    @FXML
    public void poubelleSearch() {
        if (poubellDatalist == null) {
            return;
        }
        FilteredList<Poubelle> filter = new FilteredList<>(poubellDatalist, e -> true);
        Ajoutpoubelle_search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
            filter.setPredicate((Poubelle predicatePoubelle) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicatePoubelle.getName() != null && predicatePoubelle.getName().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePoubelle.getPoids_vide() != null && predicatePoubelle.getPoids_vide().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePoubelle.getCouleur() != null && predicatePoubelle.getCouleur().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePoubelle.getDescription() != null && predicatePoubelle.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Poubelle> sortList = new SortedList<>(filter);
        if (ajouterPoubelle_tableauView != null) {
            sortList.comparatorProperty().bind(ajouterPoubelle_tableauView.comparatorProperty());
            ajouterPoubelle_tableauView.setItems(sortList);
        }

    }

    @FXML
    public void AjouterPoubelleADD() {
    Alert alert;

    if (type_poubelle.getText().isEmpty()
            || Name_poubelle.getText().isEmpty()
            || poidsVide_poubelle.getSelectionModel().getSelectedItem() == null
            || Couleur_poubelle.getSelectionModel().getSelectedItem() == null
            || Desc_poubelle.getText().isEmpty()
            || ImageView_Poubelle.getImage() == null){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        String insertData = "INSERT INTO poubelle (type_id ,name, poids_vide, couleur, description, image) VALUES (?,?,?,?,?,?)";
         connect = MyConnection.getInstance().getConnection();

        try {
            String checkname = "SELECT type_id FROM poubelle WHERE type_id = ?";
            prepare = connect.prepareStatement(checkname);
            prepare.setInt(1, Integer.parseInt(type_poubelle.getText()));
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("This type already exists");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(insertData);
                prepare.setInt(1, Integer.parseInt(type_poubelle.getText()));
                prepare.setString(2, Name_poubelle.getText());
                prepare.setString(3, (String) poidsVide_poubelle.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String) Couleur_poubelle.getSelectionModel().getSelectedItem());
                prepare.setString(5, Desc_poubelle.getText());
                prepare.setString(6, file_path.getText());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added!");
                alert.showAndWait();

                poubellesClear();
                poubellesShowData();
            }
        } catch (SQLException e) {
            // handle SQL exception here
        } catch (NumberFormatException e) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid integer value for Type");
            alert.showAndWait();
        }
    }
}

    @FXML
    public void ImportBtn() {
        FileChooser open = new FileChooser();
        
      Stage stage = (Stage)main_form.getScene().getWindow();
              File file = open.showOpenDialog(stage);

if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
             file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 136, 139, false, true);
            
            ImageView_Poubelle.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }
        

 
    @FXML
    public void poubellesDemandPoubelle() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DemandeController.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {

                x = event.getSceneX();
                y = event.getSceneY();

            });
            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                stage.setOpacity(.8);
            });
            root.setOnMouseReleased((MouseEvent event) -> {
                stage.setOpacity(1);
            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
        }
    }

    @FXML
    public void poubellesSelectData() {
    Poubelle poubelleD = ajouterPoubelle_tableauView.getSelectionModel().getSelectedItem();
    int num = ajouterPoubelle_tableauView.getSelectionModel().getFocusedIndex();
    if ((num - 1) < -1 ) 
        return;
    type_poubelle.setText(String.valueOf(poubelleD.getType_id()));
    Name_poubelle.setText(String.valueOf(poubelleD.getName()));
    poidsVide_poubelle.getSelectionModel().clearSelection();
    Couleur_poubelle.getSelectionModel().clearSelection();
    Desc_poubelle.setText(String.valueOf(poubelleD.getDescription()));
    String picture = "file:" + poubelleD.getImage();

            Image image = new Image(picture, 136, 139, false, true);
    ImageView_Poubelle.setImage(image);
    String path =  poubelleD.getImage();
    file_path.setText(path);


}
    
    

        
    private String Poids_Vide[] = {"5kg", "10kg", "15kg"};

    @FXML
    public void poidsVidePoubelle() {
        List<String> listData = new ArrayList<>();
        listData.addAll(Arrays.asList(Poids_Vide));
        ObservableList list = FXCollections.observableArrayList(listData);
        poidsVide_poubelle.setItems(list);
    }

    private String Couleur[] = {"jaune", "verte", "rouge", "bleu"};

    @FXML
    public void CouleurPoubelles() {
        List<String> listData = new ArrayList<>();
        listData.addAll(Arrays.asList(Couleur));
        ObservableList list = FXCollections.observableArrayList(listData);
        Couleur_poubelle.setItems(list);
    }
    @FXML
    public void poubellesUpdate() {
        if (type_poubelle.getText().isEmpty() || Name_poubelle.getText().isEmpty() || poidsVide_poubelle.getSelectionModel().getSelectedItem() == null || Couleur_poubelle.getSelectionModel().getSelectedItem() == null || Desc_poubelle.getText().isEmpty() || ImageView_Poubelle.getImage() == null){
 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String path = file_path.getText();
//            path = path.replace("\\", "\\\\");

            String sql = "UPDATE poubelle SET type_id ='" + type_poubelle.getText() + "', name ='" + Name_poubelle.getText() + "', poids_vide ='" + poidsVide_poubelle.getSelectionModel().getSelectedItem() + "', couleur='" + Couleur_poubelle.getSelectionModel().getSelectedItem() + "', description='" + Desc_poubelle.getText() + "',image='" + path + "'"+getData.id;

             connect = MyConnection.getInstance().getConnection();
            try {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update type ID:"+type_poubelle.getText().isEmpty() +"?");
                                Optional<ButtonType> option = alert.showAndWait();

if (option.get().equals(ButtonType.OK)){
//                Optional<ButtonType> option = alert.showAndWait();
//                if (result.isPresent() && result.get() == ButtonType.OK) {

                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    poubellesShowData();
                    poubellesClear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a poubelle first.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
            }
        }
    }

    @FXML
    public void poubellesClear() {
        type_poubelle.setText("");
        Name_poubelle.setText("");
        poidsVide_poubelle.getSelectionModel().clearSelection();
        Couleur_poubelle.getSelectionModel().clearSelection();
        Desc_poubelle.setText("");
        getData.path = "";
//        getData.id=0;
        ImageView_Poubelle.setImage(null);
    }

    
    @FXML
    public void poubelleDelete() {
        if (type_poubelle.getText().isEmpty()  || Name_poubelle.getText().isEmpty() || poidsVide_poubelle.getSelectionModel().getSelectedItem() == null || Couleur_poubelle.getSelectionModel().getSelectedItem() == null || Desc_poubelle.getText().isEmpty() || getData.path == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the record?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                try {
                    String deleteQuery = "DELETE FROM Poubelle WHERE id = ?";
                    PreparedStatement statement = connect.prepareStatement(deleteQuery);

                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted == 1) {
                        // Show success message and update table view
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Record successfully deleted from the database.");
                        alert.showAndWait();

                        poubellesShowData();
                        poubellesClear();
                    } else {
                        // Show error message
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to delete the record. Please try again.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
             
                }
            }
        }
    }

    @FXML
    public void TypeADD() {
        String sql = "INSERT INTO type (name) VALUES (?,?)";
        connect = MyConnection.getInstance().getConnection();
        try {
            if (type_id.getText().isEmpty() || type_type.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
//                Integer id = Integer.parseInt(type_id.getText());
                String name = type_type.getText();

                // Check if the type already exists
//                String checkname = "SELECT id FROM type WHERE 
                statement = connect.createStatement();
                result = statement.executeQuery(sql);
                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This type already exists");
                    alert.showAndWait();
                } else {
                    // Add the type
                    prepare = connect.prepareStatement(sql);
//                    prepare.setInt(1, id);
                    prepare.setString(2, name);
                    prepare.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    typeShowData();
                }
            }
        } catch (SQLException e) {

        }
    }
    public void AjouterDDEPoubelleADD() throws SQLException {
    String sql = "INSERT INTO demande_poubelle (ref, date_demande, etat_demande, poubel, quantite) VALUES (?,?,?,?,?)";
    Connection  connect = MyConnection.getInstance().getConnection();
    PreparedStatement prepare = null;
    Alert alert;

    try {
        String ref = ref_dde.getText();
        String dateDem = String.valueOf(date_dde.getValue());
        String etatDem = etat_Dde.getText();
        String poubel = POUBELLE.getText();
        String quantite = Quantit_Pubelle.getText();

        if (ref == null || dateDem == null || etatDem == null || poubel == null || quantite == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } else {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, ref);
            prepare.setString(2, dateDem);
            prepare.setString(3, etatDem);
            prepare.setString(4, poubel);
            prepare.setString(5, quantite);

            int rowsInserted = prepare.executeUpdate();
            if (rowsInserted > 0) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added!!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Code to execute when OK is pressed
                } else {
                    return;
                }
            }
        }
    } catch (SQLException e) {
        // Code to handle the exception
    } finally {
        if (prepare != null) {
            prepare.close();
        }
    }

}


    @FXML
    public void typeDelete() {
        if (type_id.getText().isEmpty() || type_type.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the record?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                try {
                    // Prepare delete query
                    String deleteQuery = "DELETE FROM type WHERE id = ?";
                    PreparedStatement statement = connect.prepareStatement(deleteQuery);
                    statement.setInt(1, Integer.parseInt(type_id.getText()));

                    // Execute delete query
                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted == 1) {
                        // Show success message and update table view
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Record successfully deleted from the database.");
                        alert.showAndWait();
                        typeShowData();
                    } else {
                        // Show error message
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to delete the record. Please try again.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    // Show error message
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Unable to delete the record. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }

//    public ObservableList<DemandeP> poubelmleListData() throws SQLException {
///            }
    public ObservableList<DemandeP> DemandeListData() throws SQLException {
    ObservableList<DemandeP> listData = FXCollections.observableArrayList();
    String sql = "SELECT * FROM demande_poubelle";
     connect = MyConnection.getInstance().getConnection();
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        while (result.next()) {
            DemandeP poubelleDde = new DemandeP(result.getString("ref"),
                                              result.getString("date_demande"),
                                              result.getString("etat_demande"),
                                              result.getString("poubel"),
                                              result.getString("quantite"));
            listData.add(poubelleDde);
        }
    } catch (SQLException ex) {
    }
    return listData;
}

 
 private ObservableList<DemandeP> DemandeDatalist;
public void demandeShowData() throws SQLException {  
    DemandeDatalist = DemandeListData();
    DDe_col_ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
 DDe_col_dateDde.setCellValueFactory(new PropertyValueFactory<>("dateDem"));
    DDe_col_EtatDde.setCellValueFactory(new PropertyValueFactory<>("EtatDem"));
    DDe_col_poubelle.setCellValueFactory(new PropertyValueFactory<>("poubel"));
    DDe_col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    Tableau_dde_View.setItems(DemandeDatalist);
}

    public ObservableList<Type> typePListData() throws SQLException {
        ObservableList<Type> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM type";
         connect = MyConnection.getInstance().getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                Type typeP = new Type(result.getInt("id"),
                        result.getString("name"));

                listData.add(typeP);
            }
        } catch (SQLException ex) {
        }
        return listData;
    }

    public void typesSelectData() {
        int num = type_table.getSelectionModel().getFocusedIndex();
        if (num < 0) {
            return;
        }
        Type selectedType = type_table.getItems().get(num);
type_id.setText(Integer.toString(selectedType.getId()));
        
        type_type.setText(selectedType.getName());
    }

    private ObservableList<Type> typeDatalist;

    public void typeShowData() {
        try {
            typeDatalist = typePListData();
            type_clon_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            type_colone.setCellValueFactory(new PropertyValueFactory<>("name"));
            type_table.setItems(typeDatalist);
        } catch (SQLException ex) {
            Logger.getLogger(AffichePoubelleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }


    @FXML
    public void switchForm(ActionEvent event) throws SQLException {
//        if (event.getSource() == type_btn) {

         if (event.getSource() == type_btn) {
            poubelle_form.setVisible(false);
            demande_form.setVisible(false);
            type_form.setVisible(true);
            typeShowData();

        } else if (event.getSource() == Addpoubl_btn) {
            poubelle_form.setVisible(true);
            demande_form.setVisible(false);
            type_form.setVisible(false);

            poubellesShowData();

        } else if (event.getSource() == poub_btn) {

            poubelle_form.setVisible(false);
            demande_form.setVisible(true);
            type_form.setVisible(false);
            demandeShowData();
        }
        
    }  
    
    @FXML
        public void print(){
         connect = MyConnection.getInstance().getConnection();
        
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\BAZINFO\\Documents\\NetBeansProjects\\Utri\\src\\GUI\\report1.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Utri Report");
            viewer.show();
            
        }catch (JRException e) {
    e.printStackTrace();
}
        }        

    @FXML
    private void ReturnUserList(ActionEvent event) {
           try {
            Parent page1 = FXMLLoader.load(getClass().getResource("FXMLuserList.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

  
}
