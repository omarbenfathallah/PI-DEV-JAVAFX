/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IService;
import edu.esprit.entities.User;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BAZINFO
 */
public class UserService implements IService<User>  {
    
    
    Connection cnx = MyConnection.getInstance().getConnection();

    /**
     *
     * @param username
     * @return
     */
    public int getId(String username) {
        int id;
        id = 0;
        String req = "select id from user where email='" + username + "'";

        try {
            Statement st = cnx.createStatement();
            ResultSet y = st.executeQuery(req);

            for (; y.next();) {
                id = y.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public boolean isAdmin(int id) {
        String req = "select roles from user where id=" + id;
        boolean bool = false;

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                if (rs.getString("roles").contains("ROLE_ADMIN")) {
                    System.out.println(rs.getString("roles"));
                    bool = true;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;

    }

    public String getEmail(String username) {

        String email = null;
        String req = "SELECT email FROM user Where email ='" + username + "'";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                email = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return email;
    }

    public User DisplayById(int id) {

        String req = "select * from user where id=" + id;
        User user = new User();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("nom"));
                user.setLastname(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setNumero(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean ajouter(User t) {
        boolean etat;
        try {
            String req = "INSERT INTO `user`( `nom`, `prenom`, `roles`, `type`, `email`, `password`, `confirm_password`,tel,adresse) VALUES('" + t.getFirstname() + "', '" + t.getLastname() + "','" + t.verifRole() + "','" + t.verifType() + "','" + t.getEmail() + "','" + User.encrypMD5(t.getPassword()) + "','" + User.encrypMD5(t.getPassword()) + "','" + t.getNumero() + "','" + t.getAdresse() + "')";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(req);
            etat = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            etat = false;
        }
        return etat;
    }

    @Override
    public User getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<User> getAll() {

        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from  user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            list.clear();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("nom"));
                user.setLastname(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setNumero(rs.getString("tel"));
                user.setAdresse(rs.getString("adresse"));

                System.err.println(user.getAdresse());
                list.add(user);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public boolean modifier(User t, int id) {
        try {
            String req = "UPDATE  user  SET email=? ,nom=?,prenom=?,tel=?,adresse=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getFirstname());
            ps.setString(3, t.getLastname());
            ps.setString(4, t.getNumero());
            ps.setString(5, t.getAdresse());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean supprimer(int id) {
        try {
            String req = "DELETE FROM user WHERE `id` =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    public boolean Updatepassword(String password, String email) {
        try {
            String req = "UPDATE user  SET password=?,confirm_password=?  WHERE email=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(3, email);
            ps.setString(2, User.encrypMD5(password));
            ps.setString(1, User.encrypMD5(password));

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<String> checkEmail(String mail) {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select email from user where email='" + mail + "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<String> checksignin(String mailorusername, String password) {
        List<String> list;
        list = new ArrayList<>();
        String req;
        try {

            req = "Select email,password from user where email='" + mailorusername + "' and password='" + password + "' ";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    
}
