/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author BAZINFO
 */
public class Offre {

    private String nom, description, image;

    private int id_offre, points;

    private Categories id_cat;

    public Offre() {
    }

    public Offre(String nom, String description, String image, int id_offre, int points, Categories id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_offre = id_offre;
        this.points = points;
        this.id_cat = id_cat;
    }

    public Offre(String nom, String description, String image, int id_offre, int points, int id_categorie) {

        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_offre = id_offre;
        this.points = points;

        this.id_cat = new Categories(id_categorie);

        this.id_cat = new Categories(id_categorie);

    }

    public Offre(String nom, String description, String image, int points, Categories id_cat) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.points = points;
        this.id_cat = id_cat;
    }

    public Offre(String nom, String description, String image, int points, int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.points = points;
        this.id_cat = new Categories(id_categorie);

    }

    public Offre(String nom) {
        this.nom = nom;
    }

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

    public Offre(int idOf, String nom, String desc, int pnt, int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.points = points;
        this.id_cat = new Categories(id_categorie);
    }
//      public Offre(String nom, int points, String image) {
//        this.nom = nom;
//        this.points = points;
//        this.image = image;
//    }

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

//    public HBox getImageView() {
//        ImageView imageView = new ImageView(new Image(image));
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(100);
//
//        Text imageText = new Text(nom + " - " + points);
//        imageText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//
//        HBox hbox = new HBox(imageView, imageText);
//        hbox.setAlignment(Pos.CENTER_LEFT);
//        hbox.setSpacing(10);
//
//        return hbox;
//    }
//    @Override
//    public String toString() {
//        String CatSting = this.id_cat.getNomC();
//        return "Offre{" + "nom=" + nom + ", description=" + description + ", image=" + image + ", id_offre=" + id_offre + ", points=" + points + ", id_cat=" + CatSting + '}';
//    }

    @Override
    public String toString() {
        return nom;
    }

}
