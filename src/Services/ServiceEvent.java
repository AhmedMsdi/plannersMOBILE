/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Linab
 */
public class ServiceEvent {
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    public void ajouterEvent(Evenement f){
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://localhost/planners/web/app_dev.php/newTask?titre="+f.getTitre()+"&adresse="
                +f.getAdresse()+"&ville="+f.getVille()+"&description="+f.getDescription()+"&image="+f.getImage()
                +"&date_event="+f.getEvent_date_string()+"&time_event="+f.getEvent_time_string()
                +"&prix="+f.getPrix()+"&contact="+f.getTel()+"&CatEvent="+f.getId_cat()
                +"&User="+f.getId_user()+"&etat="+f.getEtat();
        con.setUrl(Url);

        System.out.println(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
 
    public ArrayList<Evenement> getListTask(String json) {
        ArrayList<Evenement> listEvennements = new ArrayList<>();
        try {
            //System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println("ttt   "+etudiants);
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float tel = Float.parseFloat(obj.get("contact").toString());
               
                Map<String,Object> dates=(Map<String, Object>)obj.get("dateEvent");
                float date = Float.parseFloat(dates.get("timestamp").toString());
                float time = 0;
                Map<String,Object> times = (Map<String, Object>)obj.get("timeEvent");
                if (times != null){
                    time = Float.parseFloat(times.get("timestamp").toString());
                   // System.out.println(date+"   -----   "+time);
                }
               
                Date datepub = new Date((long)(date-3600)*1000);
                Date timeevent = new Date((long)(time-3600)*1000);
                //System.out.println("timeevent "+timeevent);
               
                SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
                String tt = formater.format(timeevent);
               
                //System.out.println(tt);
                e.setId_event((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setAdresse(obj.get("adresse").toString());
                e.setDescription(obj.get("description").toString());
                e.setVille(obj.get("ville").toString());
                e.setPrix((int) prix);
                e.setTel((int) tel);
                e.setDate_event(datepub);
                e.setEvent_time_string(tt);
                e.setImage(obj.get("image").toString());
                
                
                listEvennements.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvennements;
    }
    
    public ArrayList<Evenement> getByIdTask(String json) {
        ArrayList<Evenement> list1Event = new ArrayList<>();
        try {
            String json2 = "["+json+"]";
            //System.out.println(json2);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json2.toCharArray()));
            //System.out.println("cccc "+etudiants);
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
               float id = Float.parseFloat(obj.get("id").toString());
               
               Map<String, Object> list_user = (Map<String, Object>) (obj.get("User"));
               float idUser = Float.parseFloat(list_user.get("id").toString());
               
               
               float prix = Float.parseFloat(obj.get("prix").toString());
               float tel = Float.parseFloat(obj.get("contact").toString());
               float etat = Float.parseFloat(obj.get("etat").toString());
               Map<String,Object> dates=(Map<String, Object>)obj.get("dateEvent");
               float date = Float.parseFloat(dates.get("timestamp").toString());
                
               float time = 0;
               Map<String,Object> times = (Map<String, Object>)obj.get("timeEvent");
               if (times != null){
                    time = Float.parseFloat(times.get("timestamp").toString());
               //System.out.println(date+"   -----   "+time);
               
               }
               Map<String,Object> cat = (Map<String, Object>)obj.get("CatEvent");
               float idcat = Float.parseFloat(cat.get("id").toString());
               
               Date datepub = new Date((long)(date-3600)*1000);
               Date timeevent = new Date((long)(time-3600)*1000);
                //System.out.println("timeevent "+timeevent);
               
               SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
                String tt = formater.format(timeevent);
                String tt2 =""+(int) time;
               e.setId_event((int) id);
              e.setTitre(obj.get("titre").toString());
              e.setAdresse(obj.get("adresse").toString());
              e.setDescription(obj.get("description").toString());
              e.setVille(obj.get("ville").toString());
              e.setPrix((int) prix);
              e.setTel((int) tel);
              e.setDate_event(datepub);
              e.setImage(obj.get("image").toString());
              e.setId_user((int) idUser);
                e.setEtat((int) etat);
                e.setEvent_time_string(tt2);
                e.setTime_event(timeevent);
                e.setId_cat((int) idcat);
                list1Event.add(e);
            }          
        } catch (IOException ex) {
        }
        return list1Event;

    }
    ArrayList<Evenement> listTasks = new ArrayList<>();
    
    public ArrayList<Evenement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
                con.setPost(false);

                con.setUrl("http://127.0.0.1/planners/web/app_dev.php/all");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<Evenement> getListMesEvennements(int id){       
        ConnectionRequest con = new ConnectionRequest();
                con.setPost(false);

                con.setUrl("http://localhost/planners/web/app_dev.php/MesEvent/"+id);  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
   public void DeleteEvent(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        String url = "http://localhost/planners/web/app_dev.php/"+id+"/deleteTask";
        System.out.println(url);        
        con.setUrl(url);

        con.setPost(false);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
    
   ArrayList<Evenement> e1 = new ArrayList<>();
   
   public ArrayList<Evenement> findEventById(int id){
       
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
                String url = "http://127.0.0.1/planners/web/app_dev.php/"+id+"/byId";
                con.setUrl(url);
                System.out.println(url);
                
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                e1 = ser.getByIdTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       return e1;
       
   }
   
   public void ModifEvent(Evenement f){
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/planners/web/app_dev.php/"+f.getId_event()+"/updateTask?titre="
                +f.getTitre()+"&adresse="+f.getAdresse()+"&ville="+f.getVille()+"&description="+f.getDescription()
                +"&image="+f.getImage()
                +"&date_event="+f.getEvent_date_string()+"&time_event="+f.getEvent_time_string()
                +"&prix="+f.getPrix()+"&contact="+f.getTel()+"&CatEvent="+f.getId_cat();
        con.setUrl(Url);
        
        System.out.println(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
   
    
    
}
