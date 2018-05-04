/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Hebergement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class ServiceHebergement {
    public void ajoutHeb(Hebergement hb) {
        ConnectionRequest con = new ConnectionRequest();
     String Url =  "http://localhost/planners/web/app_dev.php/hebergement/newM?titre="+hb.getTitre()+"&categorie="+hb.getCategorie()+"&photo="+hb.getPhoto()
             + "&prix="+hb.getPrix()+"&description="+hb.getDescription()+"&tel="+hb.getTel()+"&lieu="+hb.getLieu()+"&siteWeb="+hb.getSite_web();
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
    
    public void supprimerHebergement(int id) {
        ConnectionRequest con = new ConnectionRequest();
          String Url = "http://localhost/planners/web/app_dev.php/hebergement/"+id+"/deleteM";
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

    public ArrayList<Hebergement> getListHeb(String json) {

        ArrayList<Hebergement> listHebergements = new ArrayList<>();

        try {
           
            JSONParser j = new JSONParser();

            Map<String, Object> hebergements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(hebergements);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) hebergements.get("root");

            for (Map<String, Object> obj : list) {
                Hebergement hb = new Hebergement(json, json);

                float id = Float.parseFloat(obj.get("id").toString());
                float t = Float.parseFloat(obj.get("tel").toString());
                int enb;
                if( obj.get("enable").toString().equals("true") ){
                    enb = 1;
                }else{
                    enb =0;
                }
                System.out.println(id);
                hb.setId((int) id);
                hb.setDescription(obj.get("description").toString());
                hb.setTitre(obj.get("titre").toString());
                hb.setPhoto(obj.get("photo").toString());
                hb.setCategorie(obj.get("categorie").toString());
                hb.setLieu(obj.get("lieu").toString());
                hb.setTel((int) t);
                hb.setEnable( enb);
                
                System.out.println(hb);
                listHebergements.add(hb);

            }

        } catch (IOException ex) {
        }
        System.out.println(listHebergements);
        return listHebergements;

    }
    ArrayList<Hebergement> listHebergements = new ArrayList<>();
    
    public ArrayList<Hebergement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/planners/web/app_dev.php/hebergement/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceHebergement ser = new ServiceHebergement();
                listHebergements = ser.getListHeb(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listHebergements;
    }

  
    
}


