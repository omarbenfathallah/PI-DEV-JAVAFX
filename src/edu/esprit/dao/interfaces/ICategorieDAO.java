/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.esprit.dao.interfaces;
import edu.esprit.entities.Categories;
import java.util.List;

/**
 *
 * @author BAZINFO
 */
public interface ICategorieDAO {
    
    void insertCategorie(Categories ca);

  //  void updateCategories(Categories ca);
    
    void updateCategories( Categories ca);

   // void deleteCategories(Categories ca);
    
     void deleteCategories(Categories ca);
     
     
        
   public boolean findCategorieByNom(Categories ca);
    
    List<Categories> DisplayAllCategories();
    
}
