/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Linab
 */
public class CategorieEvent {
    private int id_cat;
    private String libelle;

    public CategorieEvent() {
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    
    public CategorieEvent(int id_cat, String libelle) {
        this.id_cat = id_cat;
        this.libelle = libelle;
    }

    public int getId_cat() {
        return id_cat;
    }

    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
    

    
    
    
}
