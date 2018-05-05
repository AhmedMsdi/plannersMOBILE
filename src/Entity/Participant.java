/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import Entity.Participant;
/**
 *
 * @author Linab
 */
public class Participant {
    
     private int id_Part;
        private String id_event;;
     private int id_user;

    public Participant(int id_Part, String id_event, int id_user) {
        this.id_Part = id_Part;
        this.id_event = id_event;
        this.id_user = id_user;
    }
    
     
     public Participant()
     {}

    public int getId_Part() {
        return id_Part;
    }

    public String getId_event() {
        return id_event;
    }

    public int getId_user() {
        return id_user;
    }
     
}
