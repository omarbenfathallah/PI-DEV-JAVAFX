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
public class Poubelle {

    private int id;
    private int type_id;
    private String name;
    private String poids_vide;
    private String couleur;
    private String description;
    private String image;

    public Poubelle(int id, int type_id, String name, String poids_vide, String couleur, String description, String image) {
        this.id = id;
        this.type_id = type_id;
        this.name = name;
        this.poids_vide = poids_vide;
        this.couleur = couleur;
        this.description = description;
        this.image = image;
    }

    public Poubelle(int type_id, String name, String poids_vide, String couleur, String description, String image) {
        this.type_id = type_id;
        this.name = name;
        this.poids_vide = poids_vide;
        this.couleur = couleur;
        this.description = description;
        this.image = image;
    }

    public Poubelle(String name, String poids_vide, String couleur, String description, String image) {
        this.name = name;
        this.poids_vide = poids_vide;
        this.couleur = couleur;
        this.description = description;
        this.image = image;
    }

    public Poubelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Poubelle(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoids_vide() {
        return poids_vide;
    }

    public void setPoids_vide(String poids_vide) {
        this.poids_vide = poids_vide;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getImage() {
        return null;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Poubelle{" + "id=" + id + ", type_id=" + type_id + ", name=" + name + ", poids_vide=" + poids_vide + ", couleur=" + couleur + ", description=" + description + ", image=" + image + '}';
    }

    public void setType_id(Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setType(Type type) {
        Type t1 = new Type(1, "Plastic");
        Type t2 = new Type(2, "Paper");
        Type t3 = new Type(3, "ORGANIC");
        Type t4 = new Type(4, "glace");

    }
}
