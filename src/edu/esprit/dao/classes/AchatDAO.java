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
                    //ps.setDate(3, new java.sql.Date(ac.getDate_achat().getTime()));
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

    public ObservableList<Achat> DisplayAllAchat() {

        String sql = "SELECT * FROM achat a JOIN user cl ON a.id = cl.id JOIN offre o  ON o.id_offre = a.id_offre";

        List<Achat> listeAchat = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {

                int id_achat = result.getInt(1);
                int id_us = result.getInt(2);
                int id_offre = result.getInt(3);
                Date date_achat = result.getDate(4);

                String nomU = result.getString(5);
                String prenom = result.getString(6);
                String password = result.getString(7);
                String email = result.getString(8);
                String type = result.getString(9);
                String confirm_password = result.getString(10);
                String tel = result.getString(11);
                String roles = result.getString(12);

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
}



//
//
// public List<participation> recupererParticipation() {
//         String sql = "SELECT * FROM participation p JOIN user cl ON p.id_user = cl.id_user JOIN evenement e ON e.EventId = p.EventId";
//        List<participation> particip = new ArrayList<>();
//        try {
//        Statement statement = cnx.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//        while (result.next()) {
//            
//            int id_part =result.getInt("id_part");
//            Date dateValidation = new Date(result.getDate("date_part").getTime());
//             int userid = result.getInt("id_user");
//            String Nom = result.getString("nom_user");
//            String Prenom = result.getString("prenom_user");
//            String Email = result.getString("email_user");
//            String Password = result.getString("mdp_user");
//            String ClAdresse = result.getString("adresse_user");
//      
//            String Role = result.getString("role");
//            String Description = result.getString("description");
//             int EventId = result.getInt("EventId");
//                String EventNom = result.getString("EventNom");
//                String EventTheme = result.getString("EventTheme");
//                Date DateDebutEvent = result.getDate("DateDebutEvent");
//                Date DateFinEvent = result.getDate("DateFinEvent");
//                String EventAdresse = result.getString("EventAdresse");
//                String EventDescription = result.getString("EventDescription");
//                String Eventimage = result.getString("Eventimage");
//                
//          //  participation par =new participation(id_part, dateValidation,c1,ee);
//            //produit p = new produit(ProdId,ProdLib,ProdDesc,ProdPrix,ProdImg);
//            
//            User cl = new User(userid,Nom,Prenom,Email,Password,ClAdresse,Role,Description);
//            Evenment ee = new Evenment(EventId, EventNom, EventTheme, DateDebutEvent, DateFinEvent, EventAdresse, EventDescription, Eventimage);
//            participation par = new participation(dateValidation, cl, ee);
//
//            //commande c = new commande(RefCom,PrixTotal, dateValidation, p, cl);
//            particip.add(par);
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex);
//    }
//    return particip;
//}
//
//
       
    
    

    
