/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import javafx.collections.ObservableList;

/**
 *
 * @author BAZINFO
 * @param <T>
 */
public interface IService<T> {
        public boolean ajouter(T t);
    public T getById(int id);
    public ObservableList<T> getAll();
   
    public boolean supprimer(int id);
    
}
