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
public class Messagerie {
    private int id;
    private String typemessage;
    private int idUser;
    private int idClient;
    private Date dateDebut;
    private Date dateFin;
    private String dateDebutS;
    private String dateFinS;
    private int etat;
    private int idHeb;
    private int nbPerson;

    public Messagerie(String dateDebutS, String dateFinS, int nbPerson,String typemessage, int etat,  int idHeb, int idUser,int idClient) {
        this.typemessage = typemessage;
        this.idUser = idUser;
        this.idClient = idClient;
        this.dateDebutS = dateDebutS;
        this.dateFinS = dateFinS;
        this.etat = etat;
        this.idHeb = idHeb;
        this.nbPerson = nbPerson;
    }
    
    public Messagerie(String dateDebutS, String dateFinS) {
        
        this.dateDebutS = dateDebutS;
        this.dateFinS = dateFinS;
        
    }
    
    
    public Messagerie(int id, String typemessage, String dateDebutS, String dateFinS,int nbPerson, int etat) {
        this.id = id;
        this.typemessage = typemessage;
        this.dateDebutS = dateDebutS;
        this.dateFinS= dateFinS;
        this.nbPerson = nbPerson;
        this.etat = etat;
    }
    
    public Messagerie(String dateDebutS, String dateFinS,int nbPerson) {
        
        this.dateDebutS = dateDebutS;
        this.dateFinS= dateFinS;
        this.nbPerson = nbPerson;
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypemessage() {
        return typemessage;
    }

    public void setTypemessage(String typemessage) {
        this.typemessage = typemessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getDateDebutS() {
        return dateDebutS;
    }

    public void setDateDebutS(String dateDebutS) {
        this.dateDebutS = dateDebutS;
    }
    

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdHeb() {
        return idHeb;
    }

    public void setIdHeb(int idHeb) {
        this.idHeb = idHeb;
    }

    public int getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public String getDateFinS() {
        return dateFinS;
    }

    public void setDateFinS(String dateFinS) {
        this.dateFinS = dateFinS;
    }

    @Override
    public String toString() {
        return "Messagerie{" + "dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nbPerson=" + nbPerson +", Type de message :" +typemessage+'}';
    }
    
    
    
    
}
