/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.dao.classes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.esprit.entities.Categories;
import edu.esprit.entities.Offre;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.dao.interfaces.IOffreDAO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author BAZINFO
 */
public class OffreDAO implements IOffreDAO {

    Connection cnx;

    ObservableList<Offre> obListOff = FXCollections.observableArrayList();
    ObservableList<Offre> obList = FXCollections.observableArrayList();

    public OffreDAO() {
        cnx = MyConnection.getInstance().getConnection();

    }

    @Override
    public void insertOffre(Offre of) {
        String sql = "INSERT INTO `offre`(`nom`, `description`, `image`, `points`, `id_categorie`) VALUES " + "(?,?,?,?,?)";
        try {
            PreparedStatement psCategorie = cnx.prepareStatement("SELECT `id_categorie`, `nom`, `description` FROM `categorie_offres` WHERE 'id_categorie'=?");
            psCategorie.setInt(1, of.getId_cat().getId_categorie());
            ResultSet rsCategorie = psCategorie.executeQuery();
            if (rsCategorie.next()) {
                Categories Prod_C = new Categories(
                        rsCategorie.getInt("id_categorie"),
                        rsCategorie.getString("nom"),
                        rsCategorie.getString("description")
                );

                of.setId_cat(Prod_C);
            }

            try {
                PreparedStatement ps = cnx.prepareStatement(sql);
                ps.setString(1, of.getNom());
                ps.setString(2, of.getDescription());
                ps.setString(3, of.getImage());
                ps.setInt(4, of.getPoints());
                ps.setInt(5, of.getId_cat().getId_categorie());
                ps.executeUpdate();
                System.out.println("Offre ajouté avec succés !");

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OffreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateOffre(Offre of) {
        String sql = " UPDATE `offre` SET `nom`=?,`description`=?,`image`=?, `points`=?,`id_categorie`=?  WHERE  id_offre=? ";
        try {
//         
//            PreparedStatement psCategorie = cnx.prepareStatement("SELECT `id_categorie` FROM `categorie_offres` WHERE id_categorie=?");
//            psCategorie.setInt(1, of.getId_cat().getId_categorie());
//            ResultSet rsCategorie = psCategorie.executeQuery();
//            if (!rsCategorie.next()) {
//                System.out.println("Categorie inexistant !");
//                return;
//            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, of.getNom());
            ps.setString(2, of.getDescription());
            ps.setString(3, of.getImage());
            ps.setInt(4, of.getPoints());
            ps.setInt(5, of.getId_cat().getId_categorie());
            ps.setInt(6, of.getId_offre());
            ps.executeUpdate();
            System.out.println("Offre modifiée avec succès!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteOffre(Offre of) {
        String sql = "DELETE FROM `offre` WHERE id_offre=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, of.getId_offre());
            ps.executeUpdate();
            System.out.println("Offre supprimé avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Offre> findOffreByNom() {
        String sql = "SELECT * FROM offre ";
        List<Offre> listeOffre = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_offre = result.getInt(1);
                String nom = result.getString(2);
                String description = result.getString(3);
                String image = result.getString(4);
                int points = result.getInt(5);
//                 int id_categorie = result.getInt(6)
                int id_categorie = result.getInt(6);

                String nomC = result.getString(8);
                String descriptionC = result.getString(9);

                Categories c = new Categories(id_categorie, nomC, descriptionC);

                Offre o = new Offre(nom, description, image, points, c);
                obList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }

    @Override
    public ObservableList<Offre> DisplayAllOffres() {
        String sql = "SELECT DISTINCT  * FROM offre o JOIN categorie_offres cl ON o.id_categorie = cl.id_categorie   ORDER BY o.id_offre DESC";
        //    List<Offre> listeOffre = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_offre = result.getInt(1);
                String nom = result.getString(2);
                String description = result.getString(3);
                String image = result.getString(4);
                int points = result.getInt(5);
                int id_categorie = result.getInt(6);

                String nomC = result.getString(8);
                String descriptionC = result.getString(9);

                Categories c = new Categories(id_categorie, nomC, descriptionC);
                Offre e = new Offre(nom, description, image, id_offre, points, c);
                obListOff.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obListOff;
    }

    public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT nom FROM categorie_offres";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("nom"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public int chercherIdCat(String nom_Cat) throws SQLException {
        int id = 0;
        String requetee = "SELECT id_categorie FROM categorie_offres where nom='" + nom_Cat + "';";
        PreparedStatement pst = cnx.prepareStatement(requetee);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id_categorie");
        }
        return id;
    }

    public List<Offre> getAllOffres() {
        List<Offre> offreList = new ArrayList<>();
        String sql = "SELECT * FROM offre";
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_offre = result.getInt("id_offre");
                String nom = result.getString("nom");
                String description = result.getString("description");
                String image = result.getString("image");
                int points = result.getInt("points");
                int id_categorie = result.getInt("id_categorie");
                Offre offre = new Offre(nom, description, image, points, id_categorie);
                offreList.add(offre);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offreList;
    }

    public ObservableList<Offre> OffresQR() {
        ObservableList<Offre> offres = FXCollections.observableArrayList();
        try {
//            Connection conn = getConnection();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM offre");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offre = new Offre();
                offre.setId_offre(rs.getInt("id_offre"));
                offre.setNom(rs.getString("nom"));
                offre.setPoints(rs.getInt("points"));
                offre.setDescription(rs.getString("description"));
                offres.add(offre);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return offres;
    }

    /**
     *
     * @param chaine
     * @return
     */
    public List<Offre> chercherOffre(String chaine) {

        String sql = "SELECT * FROM offre WHERE (nom LIKE ? or points LIKE ?  )  ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<Offre> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                Offre e = new Offre();

                e.setNom(rs.getString("Nom"));
                e.setDescription(rs.getString("Description"));
                e.setPoints(rs.getInt("Points"));
                e.setImage(rs.getString("Image"));
                //       e.setId_cat(rs.getId_cat().getId_categorie("id_categorie"));

                myList.add(e);
                System.out.println("Offre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public List<Offre> getOffresByPage(int pageNumber, int itemsPerPage) {
        EntityManager em = Persistence.createEntityManagerFactory("UtriPU").createEntityManager();
        Query query = em.createQuery("SELECT o.nom, o.image , o.points   FROM Offre o ORDER BY o.id_offre DESC");
        query.setFirstResult((pageNumber - 1) * itemsPerPage);
        query.setMaxResults(itemsPerPage);
        List<Object[]> results = query.getResultList();
        List<Offre> offres = new ArrayList<>();
        for (Object[] result : results) {
            Offre offre = new Offre();
            offre.setNom((String) result[0]);
            offre.setImage((String) result[1]);
            offre.setPoints((int) result[2]);
            offres.add(offre);
        }
        em.close();
        return offres;
    }

    public ObservableList<Offre> DisplaySport() {
        String sql
                = //"SELECT * FROM offre o JOIN categorie_offres cl ON o.id_categorie = cl.id_categorie WHERE c1.id_categorie = 3 ORDER BY o.id_offre DESC";
                "SELECT DISTINCT  * FROM offre o   JOIN categorie_offres cl ON o.id_categorie = cl.id_categorie WHERE cl.id_categorie = 3 ORDER BY o.id_offre DESC";
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_offre = result.getInt(1);
                String nom = result.getString(2);
                String description = result.getString(3);
                String image = result.getString(4);
                int points = result.getInt(5);
                int id_categorie = result.getInt(6);

                String nomC = result.getString(8);
                String descriptionC = result.getString(9);

                Categories c = new Categories(id_categorie, nomC, descriptionC);
                Offre e = new Offre(nom, description, image, id_offre, points, c);
                obListOff.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obListOff;
    }
    
    
    public void Qr( Stage primaryStage,Offre p) {
         //Stage primaryStage = null;
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    
        String myWeb = "nom: " + p.getNom() +"\n"+ " description: " + p.getDescription() +"\n" + " points: " + p.getPoints() +"\n" + " Categorie: " + p.getId_cat().getNomC();

        
        
        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            //Logger.getLogger(JavaFX_QRCo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
       primaryStage.show();
    }

}
