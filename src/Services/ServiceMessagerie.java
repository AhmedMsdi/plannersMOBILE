/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import Entity.Messagerie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Mahdi
 */
public class ServiceMessagerie {
   public void ajoutMessagerie(Messagerie mess) {
        ConnectionRequest con = new ConnectionRequest();
          String Url = "http://localhost/planners/web/app_dev.php/messagerie/newMessaMob?dateDebut="+mess.getDateDebutS()+"&dateFin="+ mess.getDateFinS()+"&nbrPerson="+ mess.getNbPerson()+"&typemessage=Client&etat=1&idheb="+mess.getIdHeb()+"&idUser=1&idClient=ahmed";
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   public ArrayList<Messagerie> getListMess(String json) {

        ArrayList<Messagerie> listMessageries = new ArrayList<>();

        try {
           
            JSONParser j = new JSONParser();

            Map<String, Object> messageries = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(messageries);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) messageries.get("root");

            for (Map<String, Object> obj : list) {
                Messagerie mes = new Messagerie(json, json);

                float id = Float.parseFloat(obj.get("id").toString());
                String date = obj.get("datedebut").toString();
                String datefin = obj.get("datefin").toString();
                int enb;
                if( obj.get("etat").toString().equals("true") ){
                    enb = 1;
                }else{
                    enb =0;
                }
                float nbPerson = Float.parseFloat(obj.get("nbrPerson").toString());
                Map<String, Object> date2  = (Map<String, Object>) obj.get("datedebut");
                 float da = Float.parseFloat(date2.get("timestamp").toString());
                 Date d = new Date((long)(da-3600 )*1000);
                  Map<String, Object> date3  = (Map<String, Object>) obj.get("datefin");
                 float da2 = Float.parseFloat(date3.get("timestamp").toString());
                 Date d2 = new Date((long)(da2-3600)*1000);
                 System.out.println(d);
                
                mes.setTypemessage(obj.get("typemessage").toString());
             //   mes.setTypemessage(obj.get("typemessage").toString());
                
                mes.setId((int) id);
                mes.setEtat(enb);
                mes.setDateDebut(d);
               // e.setIdUser(u);
                mes.setDateFin(d2);
                mes.setNbPerson((int) nbPerson);
               
                
                System.out.println(mes);
                listMessageries.add(mes);

            }

        } catch (IOException ex) {
        }
        System.out.println(listMessageries);
        return listMessageries;

    }
    ArrayList<Messagerie> listMessageries = new ArrayList<>();
    
    public ArrayList<Messagerie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/planners/web/app_dev.php/messagerie/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMessagerie ser = new ServiceMessagerie();
                listMessageries = ser.getListMess(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMessageries;
    }
   

   
}
