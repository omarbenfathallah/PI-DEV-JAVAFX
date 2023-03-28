/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ICategorieDAO;
import edu.esprit.entities.Categories;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BAZINFO
 */
public class CategorieDAO implements ICategorieDAO{
       Connection cnx;
       ObservableList<Categories>obListCat = FXCollections.observableArrayList();
        
       public CategorieDAO() {
         cnx = MyConnection.getInstance().getConnection() ;      
    }

    @Override
    public void insertCategorie(Categories ca) {
        String sql = " INSERT INTO `categorie_offres`(`nom`, `description`)" +"VALUES (?,?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, ca.getNom());
            ps.setString(2, ca.getDescription());
            ps.executeUpdate();
            System.out.println("Categorie ajouté avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    //public void updateCategories(Categories ca) {
        public void updateCategories(Categories ca) {
        String sql = "UPDATE `categorie_offres` SET `nom`=?, `description`=? WHERE id_categorie=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setString(1, ca.getNom());
            ps.setString(2, ca.getDescription());        
            ps.setInt(3, ca.getId_categorie());
            ps.executeUpdate();
            System.out.println("Categorie modifié avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        

    @Override
    public void deleteCategories( Categories ca) {
        String sql = "DELETE from categorie_offres WHERE id_categorie=?";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, ca.getId_categorie());
            ps.executeUpdate();
            System.out.println("evenment  supprimé avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean findCategorieByNom(Categories ca) {
        try {
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT * FROM categorie_offres WHERE EventNom = ?");
            ps.setString(1, ca.getNom());
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

    @Override
    public ObservableList<Categories> DisplayAllCategories() {
          String sql = "SELECT * FROM categorie_offres";
        List<Categories> listeCategorie = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_categorie = result.getInt(1);
                String nom = result.getString(2);
                String description = result.getString(3);
              
                Categories e = new Categories(id_categorie,nom,description);
                obListCat.add(e);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obListCat;
    }
       
    
    
}
