/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Offre;
import java.util.List;

/**
 *
 * @author BAZINFO
 */
public interface IOffreDAO {
     void insertOffre(Offre of);

     
    // void updateOffre(int  id_offre , Offre newOffre);
    void updateOffre(Offre of);

    void deleteOffre(Offre of);

    public boolean findOffreByNom(Offre of);

    List<Offre> DisplayAllOffres();
    
}
