/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Achat;
import java.util.List;

/**
 *
 * @author BAZINFO
 */
public interface IAchatDAO {
     void insertAchat(Achat ac);

  //  void updateCategories(Categories ca);
    
    void updateAchat(int  id_achat , Achat newAchat);

 
    
     void deleteAchat( Achat ac);
     
     
        
    public boolean findAchatByNom(Achat ac);
    
    List<Achat> DisplayAllAchat();
    
}
