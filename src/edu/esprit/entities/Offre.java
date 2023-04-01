/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

/**
 *
 * @author BAZINFO
 */
public class Offre {

    private String nom, description, image;
<<<<<<< HEAD
    private int id_offre, points;
=======
    private int id_offre, points ;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    private Categories id_cat;

    public Offre() {
    }

<<<<<<< HEAD
=======
    
    
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    public Offre(String nom, String description, String image, int id_offre, int points, Categories id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_offre = id_offre;
        this.points = points;
        this.id_cat = id_cat;
    }
<<<<<<< HEAD

    public Offre(String nom, String description, String image, int id_offre, int points, int id_categorie) {
=======
       public Offre(String nom, String description, String image, int id_offre, int points, int id_categorie) {
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_offre = id_offre;
        this.points = points;
<<<<<<< HEAD
        this.id_cat = new Categories(id_categorie);
=======
        this.id_cat = new Categories(id_categorie) ;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    }

    public Offre(String nom, String description, String image, int points, Categories id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.points = points;
        this.id_cat = id_cat;
    }
<<<<<<< HEAD

=======
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    public Offre(String nom, String description, String image, int points, int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.points = points;
<<<<<<< HEAD
        this.id_cat = new Categories(id_categorie);
=======
      this.id_cat = new Categories(id_categorie) ;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    }

    public Offre(String nom) {
        this.nom = nom;
    }
<<<<<<< HEAD

    public Offre(int id_offre) {

        this.id_offre = id_offre;
    }

    public Offre(int id_offre, String nom, String description, int points, String image, int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.points = points;
        this.image = image;
        this.id_cat = new Categories(id_categorie);
    }

    public Offre(int id_offre, String nom, String description, int points, String image) {
        this.nom = nom;
        this.description = description;
        this.points = points;
        this.image = image;

    }

=======
       
    
    public Offre(int id_offre) {
        
       this.id_offre = id_offre; 
    }

    public Offre(int id_offre, String nom, String description, int points, String image,int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.points = points;
        this.image=image;
        this.id_cat = new Categories(id_categorie) ;
    }

   
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Categories getId_cat() {
        return id_cat;
    }

    public void setId_cat(Categories id_cat) {
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        String CatSting = this.id_cat.getNomC();
        return "Offre{" + "nom=" + nom + ", description=" + description + ", image=" + image + ", id_offre=" + id_offre + ", points=" + points + ", id_cat=" + CatSting + '}';
    }
<<<<<<< HEAD

=======
    
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
}
