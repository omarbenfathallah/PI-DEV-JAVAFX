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
public class Type {

    private int id;
    private String name;

    public Type(String name) {

        this.name = name;
    }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type(int aInt, String string, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type() {

    }
}
