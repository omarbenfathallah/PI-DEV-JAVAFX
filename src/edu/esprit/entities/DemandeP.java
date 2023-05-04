/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author BAZINFO
 */
public class DemandeP {

    private int id;
    private String ref;
    private String dateDem;
    private String EtatDem;
    private String poubel;
    private String quantite;

    public DemandeP(int id, String ref, String dateDem, String EtatDem, String poubel, String quantite) {
        this.id = id;
        this.ref = ref;
        this.dateDem = dateDem;
        this.EtatDem = EtatDem;
        this.poubel = poubel;
        this.quantite = quantite;
    }

    public DemandeP(String ref, String dateDem, String EtatDem, String poubel, String quantite) {
        this.ref = ref;
        this.dateDem = dateDem;
        this.EtatDem = EtatDem;
        this.poubel = poubel;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDateDem() {
        return dateDem;
    }

    public void setDateDem(String dateDem) {
        this.dateDem = dateDem;
    }

    public String getEtatDem() {
        return EtatDem;
    }

    public void setEtatDem(String EtatDem) {
        this.EtatDem = EtatDem;
    }

    public String getPoubel() {
        return poubel;
    }

    public void setPoubel(String poubel) {
        this.poubel = poubel;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public DemandeP() {
    }

    @Override
    public String toString() {
        return "DemandeP{" + "id=" + id + ", ref=" + ref + ", dateDem=" + dateDem + ", EtatDem=" + EtatDem + ", poubel=" + poubel + ", quantite=" + quantite + '}';
    }

}
