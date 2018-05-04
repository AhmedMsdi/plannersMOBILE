/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Date;
/**
 *
 * @author Mahdi
 */
public class Hebergement {
    private int id;
    private String categorie;
    private String lieu;
    private String titre;
    private String photo;
    private String description;
    private double prix;
    private int tel;
    private Date datecreation;
    private String site_web;
    private int idUser;
    private int enable;
    private double x;
    private double y;

    public Hebergement(String titre, String description) {
        
        this.titre = titre;
        this.description = description;
        
    }

    public Hebergement() {
    }
    
    
    public Hebergement(int id, String categorie, String lieu, String titre, String photo, String description, double prix, int tel, Date datecreation, String site_web, int enable, double x, double y) {
        this.id = id;
        this.categorie = categorie;
        this.lieu = lieu;
        this.titre = titre;
        this.photo = photo;
        this.description = description;
        this.prix = prix;
        this.tel = tel;
        this.datecreation = datecreation;
        this.site_web = site_web;
        this.enable = enable;
        this.x = x;
        this.y = y;
    }
    
     public Hebergement(String titre, String categorie, String photo, double prix, String description,  int tel, String lieu, String site_web) {
        this.categorie = categorie;
        this.lieu = lieu;
        this.titre = titre;
        this.photo = photo;
        this.description = description;
        this.prix = prix;
        this.tel = tel;
        this.site_web = site_web;
        
    }

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getLieu() {
        return lieu;
    }

    public String getTitre() {
        return titre;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }

    public int getTel() {
        return tel;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public String getSite_web() {
        return site_web;
    }

    public int getEnable() {
        return enable;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "titre=" + titre + ", description=" + description+", photo="+photo;
    }

    

    
    
}
