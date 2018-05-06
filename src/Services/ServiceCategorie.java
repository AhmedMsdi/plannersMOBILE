/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.CategorieEvent;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Linab
 */
public class ServiceCategorie {
    
    public List<CategorieEvent> getListTask(String json) {
        List<CategorieEvent> listCatEvent = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> categorie = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) categorie.get("root");
            
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("id").toString());
                CategorieEvent cat = new CategorieEvent();
                cat.setId_cat((int) id);
                cat.setLibelle(obj.get("libelle").toString());
                System.out.println(cat);
                listCatEvent.add(cat);
            }
        } catch (IOException ex) {
            }
        return listCatEvent;
    }
    
    public List<CategorieEvent> getList1Task(String json) {
        List<CategorieEvent> listCatEvent = new ArrayList<>();
        try {
            String json2 = "["+json+"]";
            JSONParser j = new JSONParser();
            Map<String, Object> categorie = j.parseJSON(new CharArrayReader(json2.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) categorie.get("root");
            //System.out.println(list.toString());
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("id").toString());
                CategorieEvent cat = new CategorieEvent();
                cat.setId_cat((int) id);
                cat.setLibelle(obj.get("libelle").toString());
                //System.out.println(cat);
                listCatEvent.add(cat);
            }
        } catch (IOException ex) {
            }
        return listCatEvent;
    }
    
    List<CategorieEvent> listTasks = new ArrayList<>();
     public List<CategorieEvent> getList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);

        con.setUrl("http://127.0.0.1/planners/web/app_dev.php/indexCategorie");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCategorie ser = new ServiceCategorie();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     
    List<CategorieEvent> listCategorieById = new ArrayList<>();
    public List<CategorieEvent> getCategorie(int id){

        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1/planners/web/app_dev.php/"+id+"/categorie");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCategorie ser = new ServiceCategorie();
                listCategorieById = ser.getList1Task(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorieById;
     }
}
