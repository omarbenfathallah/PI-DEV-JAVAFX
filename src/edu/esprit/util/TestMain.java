/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.util;

import edu.esprit.dao.classes.AchatDAO;
import edu.esprit.dao.classes.CategorieDAO;
import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Achat;
import edu.esprit.entities.Offre;
import edu.esprit.entities.Usertest;
import java.util.Date;


/**
 *
 * @author BAZINFO
 */
public class TestMain {
    public static void main(String[] args) {
        
    //// test crud categorie
    
        CategorieDAO ca = new CategorieDAO();
       // Categories c = new Categories( "MOHSSEN", "sabrina");
      //  ca.insertCategorie(c);
        
       
       // System.out.println( ca.DisplayAllCategories());
        
       // ca.deleteCategories(new Categories(14));
       // ca.updateCategories(new Categories(13, "Samar", "Okhty"));
       //Categories cc = new Categories( "MOHSSEN", "sabrina");
       //ca.updateCategories(13, cc);
       
       
       //////-------test crud offre
       
        OffreDAO of = new OffreDAO();
      //  Offre o1 = new Offre("aaaaaaaaaaaaa", "aaaaa", "aaaa", 50, 3);
       // of.insertOffre(o1);*/
      //  of.updateOffre(75,o1);
        
       /*  Categories p3=new Categories(3,"sport","Réduction sur les produits de beauté");
         Offre o2 = new Offre("aaaaaaaaaaaaa", "aaaaa", "aaaa",75, 50, p3);
         of.updateOffre(o2);*/
       
       
      // of.deleteOffre(new Offre(75));
      //System.out.println(of.DisplayAllOffres());
      
      
      
   //  System.out.println(of.findOffreByNom(new Offre("gant")));
       
   
    
//        User u1 = new User(23,"omar@gmail.com","123","omar","bf","citoyen","123","2733355","citoyen");
//       
//        AchatDAO ac = new AchatDAO();
//        
//        Achat a = new Achat(new java.sql.Date(10, 10, 2022), 22, 35);
//        ac.insertAchat(a);

///////----------- test  crud  achat

        AchatDAO dao = new AchatDAO();
        
//        Achat achat = new Achat(new java.util.Date(25, 03, 202), 44,22);
//        dao.insertAchat(achat);
        // Creating a new User
//        User user = new User();
//        user.setId(22); // Assuming the ID 22 already exists in the database
//      
//        
//        // Creating a new Offre
//        Offre offre = new Offre();
//        offre.setId_offre(44); // Assuming the ID 74 already exists in the database
//        
//        // Creating a new Achat
//        Achat achat = new Achat();
//              
//        achat.setId_us(user);
//        achat.setId_off(offre);
//        achat.setDate_achat(new java.util.Date(25, 03, 202));
//        
//        // Inserting the Achat into the database
//        dao.insertAchat(achat);
       
        

      //  System.out.println( dao.DisplayAllAchat());
      
      
              // TODO: Call other methods of AchatDAO to test them 
              // update achat !!!!!!
                           
    //        Achat aa = new Achat(new java.util.Date(25, 03, 202), 44,8);
//                    dao.updateAchat(77, aa);
//            delete jawha behy
//             dao.deleteAchat(new Achat(77));
        
    
    
    }
     
}