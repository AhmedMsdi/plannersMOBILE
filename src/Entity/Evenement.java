/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.TimeZone;
import java.util.Date;


public class Evenement {
    private int id_event;
    private String titre;
    private String adresse;
    private String ville;
    private String description;
    private String image;
    private Date date_event;
    private String event_date_string;
    private Date time_event;
    private String event_time_string;
//    private TimeZone time_event;
    private int prix;
    private int tel;
    private int id_user;
    private int id_cat;
    private int etat;

    public Evenement() {
    }
    
    public Evenement(int id_event, String titre, String adresse,String ville, String description, String image, Date date_event, Date time_event, int prix, int tel, int id_user, int id_cat, int etat){//,Time time_event) {
        this.id_event = id_event;
        this.titre = titre;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date_event = date_event;
        this.time_event = time_event;
        this.prix = prix;
        this.tel = tel;
        this.id_user = id_user;
        this.id_cat = id_cat;
        this.ville=ville;
        this.etat=etat;
    }

    public Evenement(String titre, String address,String ville, int prix, String description,int tel, Date date_event, int idcat, int id_user, int etat) {
        this.titre=titre;
        this.adresse=address;
        this.ville = ville;
        this.prix = prix;
        this.description = description;
        this.date_event = date_event;
        //this.time_event = time_event;
        this.tel= tel;
        this.id_cat=idcat;
        this.id_user = id_user;
        this.etat=etat;
    }

    public Evenement(int id,int etat, String titre, String address,String ville, int prix, String description,int tel, String img, String event_date_string, String event_time_string, int idcat, int id_user) {
        this.id_event = id;
        this.titre=titre;
        this.adresse=address;
        this.ville = ville;
        this.prix = prix;
        this.description = description;
        this.event_date_string = event_date_string;
        this.event_time_string = event_time_string;
        this.tel= tel;
        this.image = img;
        this.id_cat=idcat;
        this.id_user = id_user;
        this.etat=etat;
    }

    public String getEvent_date_string() {
        return event_date_string;
    }

    public void setEvent_date_string(String event_date_string) {
        this.event_date_string = event_date_string;
    }

    public String getEvent_time_string() {
        return event_time_string;
    }

    public void setEvent_time_string(String event_time_string) {
        this.event_time_string = event_time_string;
    }
    
    
    
    

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getId_event() {
        return id_event;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    
    public Date getTime_event() {
        return time_event;
    }

    public void setTime_event(Date time_event) {
        this.time_event = time_event;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    
    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", titre=" + titre + ", adresse=" + adresse + ", ville=" + ville + ", description=" + description + ", image=" + image + ", date_event=" + date_event + ", time_event=" + time_event + ", prix=" + prix + ", tel=" + tel + ", id_user=" + id_user + ", id_cat=" + id_cat + '}';
    }

    

    

    
    
}
