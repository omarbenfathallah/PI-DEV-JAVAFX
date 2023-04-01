/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.dao.classes;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BAZINFO
 */
<<<<<<< HEAD
public class OffreDAO implements IOffreDAO {

    Connection cnx;

    ObservableList<Offre> obListOff = FXCollections.observableArrayList();

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

                // Set the produit property of the produit object to the retrieved Freelancer object
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
            // Retrieve the Produit object corresponding to the given FreeCIN
            PreparedStatement psCategorie = cnx.prepareStatement("SELECT `id_categorie` FROM `categorie_offres` WHERE id_categorie=?");
            psCategorie.setInt(1, of.getId_cat().getId_categorie());
            ResultSet rsCategorie = psCategorie.executeQuery();
            if (!rsCategorie.next()) {
                System.out.println("Categorie inexistant !");
                return;
            }

            // Update the offre object in the database
=======
public class OffreDAO implements IOffreDAO{

    Connection cnx;
    
      ObservableList<Offre>obListOff = FXCollections.observableArrayList();
      
    public OffreDAO() {
         cnx = MyConnection.getInstance().getConnection() ;
        
    }
    
    

    @Override
    public void insertOffre(Offre of) {
     String sql ="INSERT INTO `offre`(`nom`, `description`, `image`, `points`, `id_categorie`) VALUES " + "(?,?,?,?,?)";   
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

            // Set the produit property of the produit object to the retrieved Freelancer object
            of.setId_cat(Prod_C);
        }
        
        
     try {
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, of.getNom());
            ps.setString(2, of.getDescription());
            ps.setString(3, of.getImage());
            ps.setInt(4, of.getPoints());
<<<<<<< HEAD
            ps.setInt(5, of.getId_cat().getId_categorie());
            ps.setInt(6, of.getId_offre());
            ps.executeUpdate();
            System.out.println("Offre modifier avec succés  !");
=======
            ps.setInt(5, of.getId_cat().getId_categorie());      
            ps.executeUpdate();
            System.out.println("Offre ajouté avec succés !");
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285

        } catch (SQLException ex) {
            System.out.println(ex);
        }
<<<<<<< HEAD
=======
     
    }   catch (SQLException ex) { 
            Logger.getLogger(OffreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void updateOffre(Offre of) {   
    String sql =  " UPDATE `offre` SET `nom`=?,`description`=?,`image`=?, `points`=?,`id_categorie`=?  WHERE  id_offre=? "; 
    try {
        // Retrieve the Produit object corresponding to the given FreeCIN
        PreparedStatement psCategorie = cnx.prepareStatement("SELECT `id_categorie` FROM `categorie_offres` WHERE id_categorie=?");
        psCategorie.setInt(1, of.getId_cat().getId_categorie()); 
        ResultSet rsCategorie = psCategorie.executeQuery();
        if (!rsCategorie.next()) {
            System.out.println("Categorie inexistant !");
            return;
        }

        // Update the offre object in the database
         PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, of.getNom());
            ps.setString(2, of.getDescription());
             ps.setString(3,of.getImage());
            ps.setInt(4, of.getPoints());
            ps.setInt(5, of.getId_cat().getId_categorie());
            ps.setInt(6, of.getId_offre());
            ps.executeUpdate();
            System.out.println("Offre modifier avec succés !");


    } catch (SQLException ex) {
        System.out.println(ex);
    }
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    }

    @Override
    public void deleteOffre(Offre of) {
<<<<<<< HEAD
        String sql = "DELETE FROM `offre` WHERE id_offre=?";
=======
              String sql = "DELETE FROM `offre` WHERE id_offre=?";
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
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
<<<<<<< HEAD
    public boolean findOffreByNom(Offre of) {
=======
    public boolean  findOffreByNom(Offre of) {
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
        try {
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT * FROM offre WHERE nom = ?");
            ps.setString(1, of.getNom());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
<<<<<<< HEAD

    @Override
    public ObservableList<Offre> DisplayAllOffres() {
        String sql = "SELECT * FROM offre o JOIN categorie_offres cl ON o.id_categorie = cl.id_categorie ";
    //    List<Offre> listeOffre = new ArrayList<>();
=======
    
    @Override
    public ObservableList<Offre> DisplayAllOffres() {
            String sql = "SELECT * FROM offre o JOIN categorie_offres cl ON o.id_categorie = cl.id_categorie ";
            
        
        List<Offre> listeOffre = new ArrayList<>();
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
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
<<<<<<< HEAD

                String nomC = result.getString(8);
                String descriptionC = result.getString(9);

=======
              
                String nomC = result.getString(8);
                String descriptionC = result.getString(9);
             
                
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
                Categories c = new Categories(id_categorie, nomC, descriptionC);
                Offre e = new Offre(nom, description, image, id_offre, points, c);
                obListOff.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obListOff;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
}
