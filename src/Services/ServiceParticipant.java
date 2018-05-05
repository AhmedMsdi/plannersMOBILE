/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Participant;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Linab
 */
public class ServiceParticipant {
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    String nbr_participant;
    public String nombreParticipant(int id_event)
    {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/planners/web/app_dev.php/"+id_event+"/EventParticipant";
        //System.out.println(url);
        con.setUrl(url);
        con.setPost(false);
        
        con.addResponseListener((e) -> {
            nbr_participant = new String(con.getResponseData());
            System.out.println(nbr_participant);
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return nbr_participant;
    }
    
    public void ajouterParticipant(int id_event, int id_user){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/planners/web/app_dev.php/newParicipant/"+id_event+"/"+id_user;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
