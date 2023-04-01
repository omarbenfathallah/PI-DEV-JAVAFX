/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author BAZINFO
 */
public class Achat {
    private int id_achat ;
    private Date date_achat ;
    private Offre id_off;
    private User id_us;

    public Achat(int id_achat, Date date_achat, Offre id_off, User id_us) {
        this.id_achat = id_achat;
        this.date_achat = date_achat;
        this.id_off = id_off;
        this.id_us = id_us;
    }
    public Achat(int id_achat, Date date_achat, int id_offre, int id) {
        this.id_achat = id_achat;
        this.date_achat = date_achat;
        this.id_off = new Offre(id_offre);
        this.id_us = new User(id);
    }
    
     public Achat(Date date_achat, int id_offre, int id) {
        this.date_achat = date_achat;
        this.id_off = new Offre(id_offre);
        this.id_us = new User(id);
    }

    public Achat(Date date_achat) {
        this.date_achat = date_achat;
    }
    public Achat(int id_achat){
        this.id_achat=id_achat;
    }

    public Achat() {
     
    }

    public Achat(int id_achat, int id_offre, int id, java.sql.Date date_achat) {
         this.id_achat = id_achat;
           this.id_off = new Offre(id_offre);
           this.id_us = new User(id);
            this.date_achat = date_achat;
           
           
    }


    public Achat(int id_achat,  User id_us,Offre id_off, Date date_achat) {
          this.id_achat = id_achat;
               this.id_us = id_us;
               this.id_off = id_off;
                this.date_achat = date_achat;
    }
    
    public Achat(int id_achat, int id, int id_offre, Date date_achat) {
          this.id_achat = id_achat;
               this.id_us = new User(id);
               this.id_off = new Offre(id_offre);
                this.date_achat = date_achat;
    }
    
    


    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    public Offre getId_off() {
        return id_off;
    }

    public void setId_off(Offre id_off) {
        this.id_off = id_off;
    }

    public User getId_us() {
        return id_us;
    }

    public void setId_us(User id_us) {
        this.id_us = id_us;
    }

    @Override
    public String toString() {
    
        return "Achat{" + "id_achat=" + id_achat + ", id_us=" + id_us + ", id_off=" + id_off + ",  date_achat=" + date_achat + '}';
    }
      
    
    
}

    
    
    
    
    
