/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IAchatDAO;
import edu.esprit.entities.Achat;
import edu.esprit.entities.Categories;
import edu.esprit.entities.Offre;
import edu.esprit.entities.User;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BAZINFO
 */
public class AchatDAO implements IAchatDAO {
    
    Connection cnx;
    public AchatDAO(){
             cnx = MyConnection.getInstance().getConnection() ;
    }
    
    @Override
    public void insertAchat(Achat ac) {
       
       String sql ="INSERT INTO `achat` ( `id`, `id_offre` ,`date_achat`) VALUES " + "(?,?,?)";   
       
      try {     
        PreparedStatement psUser = cnx.prepareStatement
        ("SELECT `id`, `email`, `roles`, `password`, `nom`, `prenom`, `tel`, `Type`, `confirm_password` FROM `user` WHERE id=?");
        psUser.setInt(1, (int) ac.getId_us().getId()); 
        ResultSet rsUser = psUser.executeQuery();
        if (rsUser.next()) {
            User id_us = new User(
                    rsUser.getInt("id"),
                    rsUser.getString("email"),
                    rsUser.getString("password"),
                    rsUser.getString("nom"),
                    rsUser.getString("prenom"),
                    rsUser.getString("tel"),
                    rsUser.getString("Type"),
                    rsUser.getString("confirm_password"),
                    rsUser.getString("roles")
            );

            // Set the produit property of the produit object to the retrieved Freelancer object
            ac.setId_us(id_us);
        }
          
      try {     
        PreparedStatement psOffre = cnx.prepareStatement("SELECT `id_offre`, `nom`, `description`, `image`, `points`, `id_categorie`  FROM `offre` WHERE id_offre=?");
        psOffre.setInt(1, ac.getId_off().getId_offre()); 
        ResultSet rsOffre = psOffre.executeQuery();
        if (rsOffre.next()) {
            Offre id_off = new Offre(                  
                     rsOffre.getString("nom"),
                     rsOffre.getString("description"),
                     rsOffre.getString("image"),   
                     rsOffre.getInt("id_offre"),
                     rsOffre.getInt("points"),
                     rsOffre.getInt("id_categorie")
                    
            );

            // Set the produit property of the produit object to the retrieved Freelancer object
            ac.setId_off(id_off);
        }
        
        
     try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, (int) ac.getId_us().getId());
            ps.setInt(2, ac.getId_off().getId_offre());
            ps.setDate(3, new java.sql.Date(ac.getDate_achat().getTime()));     
            ps.executeUpdate();
            System.out.println("Achat ajouté avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
    }   catch (SQLException ex) { 
            Logger.getLogger(OffreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   catch (SQLException ex) {
            Logger.getLogger(AchatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void updateAchat(int id_achat, Achat newAchat) {
        String sql =  " UPDATE `achat` SET `id`=?,`id_offre`=?,`date_achat`=?   WHERE  id_achat=? "; 
    try {
        // Retrieve the Produit object corresponding to the given FreeCIN
        PreparedStatement psOffre = cnx.prepareStatement
        ("SELECT `id_offre`, `nom`, `description`, `image`, `points`, `id_categorie` FROM `offre` WHERE id_offre=?");
        psOffre.setInt(1, newAchat.getId_off().getId_offre()); 
        ResultSet rsOffre = psOffre.executeQuery();
        if (!rsOffre.next()) {
            System.out.println("Offre inexistant !");
            return;
        }

         // Retrieve the Produit object corresponding to the given FreeCIN
       
        PreparedStatement psUser = cnx.prepareStatement
        ("SELECT `id`, `email`, `roles`, `password`, `nom`, `prenom`, `tel`, `Type`, `confirm_password` FROM `user` WHERE id=?");
        psUser.setInt(1, newAchat.getId_us().getId()); 
        ResultSet rsUser = psUser.executeQuery();
        if (!rsUser.next()) {
            System.out.println("User inexistant !");
            return;
        }
        
        // Update the offre object in the database
          PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, (int) newAchat.getId_us().getId());
            ps.setInt(2, newAchat.getId_off().getId_offre());
            ps.setDate(3, new java.sql.Date(newAchat.getDate_achat().getTime())); 
            ps.setInt(4,id_achat);
            ps.executeUpdate();
            System.out.println("Achat modifier avec succés !");


    } 
    catch (SQLException ex) {
        System.out.println(ex);
    }
    }

    @Override
    public void deleteAchat( Achat ac) {
          String sql = "DELETE FROM `achat` WHERE id_achat=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, ac.getId_achat());
            ps.executeUpdate();
            System.out.println("Achat supprimé avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean findAchatByNom(Achat ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Achat> DisplayAllAchat() {
      
        String sql = "SELECT * FROM achat";
        List<Achat> listeAchat = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_achat = result.getInt(1);               
                int id_offre = result.getInt(2);
                int id = result.getInt(3);
                Date date_achat = result.getDate(4);
                          
                Achat e = new Achat(id_achat,  id_offre,id, date_achat);
                listeAchat.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listeAchat;
    }
    }

    

