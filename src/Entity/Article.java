/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;



/**
 *
 * @author a7med
 */

public class Article {
      String id_article;
    String titre_article;
    String img_article;
    Date date_article;
    String contenu;
    String id_u;
    String tags;
    int etat_article;

    public Article() {
    }

   
      public Article( String titre_article, String img_article, String contenu,
              String tags) {
 
          this.titre_article=titre_article;
          this.img_article=img_article;
         
          this.contenu=contenu;
          this.tags=tags;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate_article() {
        return date_article;
    }

    public int getEtat_article() {
        return etat_article;
    }

    public String getId_article() {
        return id_article;
    }

    public String getId_u() {
        return id_u;
    }

    public String getImg_article() {
        return img_article;
    }

    public String getTags() {
        return tags;
    }

    public String getTitre_article() {
        return titre_article;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate_article(Date date_article) {
        this.date_article = date_article;
    }

    public void setEtat_article(int etat_article) {
        this.etat_article = etat_article;
    }

    public void setId_article(String id_article) {
        this.id_article = id_article;
    }

    public void setId_u(String id_u) {
        this.id_u = id_u;
    }

    public void setImg_article(String img_article) {
        this.img_article = img_article;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTitre_article(String titre_article) {
        this.titre_article = titre_article;
    }
      
      
      



}
