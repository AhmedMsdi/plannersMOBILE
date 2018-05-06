/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author sana
 */
public class Plan {

    private int id_p;
    private int note;

    private String libelle;
    private String adresse;

    private String description;

    private String ville;
    private String avis;

    private String email;

    private String prix;
    private int id_u;
    private int id_c;
    private int id_sc;

    private double longitude;
    private double latitude;
    //  private  byte[] img ; 
    private String img;
    private int etat;
    private String UtilisateurPlan;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtatnotif() {
        return etatnotif;
    }

    public String getUtilisateurPlan() {
        return UtilisateurPlan;
    }

    public void setUtilisateurPlan(String UtilisateurPlan) {
        this.UtilisateurPlan = UtilisateurPlan;
    }

    public void setEtatnotif(int etatnotif) {
        this.etatnotif = etatnotif;
    }
 
    private int etatnotif;
 
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    private String LibCat;
    private String LibSousCat;

    
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
 
    public int getId_p() {
        return id_p;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getId_sc() {
        return id_sc;
    }

    public void setId_sc(int id_sc) {
        this.id_sc = id_sc;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getLibCat() {
        return LibCat;
    }

    public void setLibCat(String LibCat) {
        this.LibCat = LibCat;
    }

    public String getLibSousCat() {
        return LibSousCat;
    }

    public void setLibSousCat(String LibSousCat) {
        this.LibSousCat = LibSousCat;
    }

    public Plan() {
    }

    public Plan(int id_p, int note, String libelle, String adresse, String description, String ville  , String prix, int id_u,  int id_sc, double longitude, double latitude, String img ) {
        this.id_p = id_p;
        this.note = note;
        this.libelle = libelle;
        this.adresse = adresse;
        this.description = description;
        this.ville = ville; 
        this.prix = prix;
        this.id_u = id_u; 
        this.id_sc = id_sc;
        this.longitude = longitude;
        this.latitude = latitude;
        this.img = img; 
    }

    @Override
    public String toString() {
        return "Plan{" + "id_p=" + id_p + ", libelle=" + libelle + ", note=" + note + ", adresse=" + adresse + ", description=" + description + ", ville=" + ville + ", avis=" + avis + ", email=" + email + ", prix=" + prix + ", id_u=" + id_u + ", id_c=" + id_c + ", id_sc=" + id_sc + ", longitude=" + longitude + ", latitude=" + latitude + ", img=" + img + ", etat=" + etat + ", UtilisateurPlan=" + UtilisateurPlan + ", etatnotif=" + etatnotif + ", LibCat=" + LibCat + ", LibSousCat=" + LibSousCat + '}';
    }

}
