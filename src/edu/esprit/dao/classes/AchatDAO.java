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
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BAZINFO
 */
public class AchatDAO implements IAchatDAO {

    Connection cnx;

    public AchatDAO() {
        cnx = MyConnection.getInstance().getConnection();
    }
    ObservableList<Achat> obListAch = FXCollections.observableArrayList();

    @Override
    public void insertAchat(Achat ac) {

        String sql = "INSERT INTO `achat` ( `id`, `id_offre` ,`date_achat`) VALUES " + "(?,?,?)";

        try {
            PreparedStatement psUser = cnx.prepareStatement("SELECT `id`, `email`, `roles`, `password`, `nom`, `prenom`, `tel`, `Type`, `confirm_password` FROM `user` WHERE id=?");
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

            } catch (SQLException ex) {
                Logger.getLogger(OffreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AchatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAchat(Achat ac) {
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

    public ObservableList<Achat> DisplayAllAchat() {

        String sql = "SELECT * FROM achat a JOIN user cl ON a.id = cl.id JOIN offre o  ON o.id_offre = a.id_offre";

        // List<Achat> listeAchat = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                int id_achat = result.getInt("id_achat");
                int id_us = result.getInt("id");
                int id_offre = result.getInt("id_offre");
                Date date_achat = result.getDate("date_achat");

                String email = result.getString("email");
                String password = result.getString("password");
                String nomU = result.getString("nom");
                String prenom = result.getString("prenom");
                String type = result.getString("type");
                String confirm_password = result.getString("confirm_password");
                String tel = result.getString("tel");
                String roles = result.getString("roles");

                String nom = result.getString("nom");
                String description = result.getString("description");
                int points = result.getInt("points");
                String image = result.getString("image");

                int id_categorie = result.getInt("id_categorie");

                User u = new User(id_us, email, password, nomU, prenom, type, confirm_password, tel, roles);
                Offre o = new Offre(id_offre, nom, description, points, image, id_categorie);
                Achat e = new Achat(id_achat, u, o, date_achat);
                obListAch.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return obListAch;
    }

    @Override
    public void updateAchat(int id_achat, Achat newAchat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String tel = result.getString("tel");
                String type = result.getString("Type");
                String confirm_password = result.getString("confirm_password");
                String roles = result.getString("roles");
                User user = new User(id, email, password, nom, prenom, tel, type, confirm_password, roles);
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
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

}
