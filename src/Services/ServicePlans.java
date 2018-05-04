/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import Entity.Plan;
import Entity.categorie;

import Entity.sous_categorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chaima
 */
public class ServicePlans {

      public void ajoutPlan(Plan ta) {
        ConnectionRequest con = new ConnectionRequest(); 
        String Url = "http://127.0.0.1/planners/web/app_dev.php/tasks/Wnew? idSc=" + ta.getId_sc()+"&libelle=" + ta.getLibelle()+ 
                "&adresse=" + ta.getAdresse() +"&ville=" + ta.getVille()
                + "&description=" + ta.getDescription()+ "&prix=" + ta.getPrix()+ "&latitude=" + ta.getLatitude()+ 
                "&longitude=" + ta.getLongitude()+ "&image=" + ta.getImg();
     
      //  http://127.0.0.1/planners/web/app_dev.php/tasks/Wnew?idSc=6&libelle=chchch&adresse=ffffff&ville=bhhhhh&description=lllllllllll&prix=1&latitude=2011.235&longitude=30.2536
        
        con.setUrl(Url);
          System.out.println(Url);

        //System.out.println("tt");

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
    /*---------------------    Modifier un Plan  ---------------------------*/
      
       public void ModifPlan(Plan p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pidevmobile/web/app_dev.php/Evenements/edit/"+p.getId_p()+"?idSc="
                +p.getId_sc()+"&libelle="
                +p.getLibelle()+
                "&adresse="+p.getAdresse()+
                "&prix="+p.getPrix()+
                "&latitude="+p.getLatitude()+"&longitude="+p.getLongitude()+"&image="+p.getImg();
        System.out.println(Url);
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
      
      
          /*   ---------------------                   LIST PLANS      ------------------------------                 */

    public ArrayList<Plan> getListPlans (String json) {
try{

        ArrayList<Plan> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Plan e = new Plan();

                 //System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("idP").toString());
               //System.out.println(id);
               e.setId_p((int) id);
               String descp="";
               if(obj.get("description")!=null)
                   descp=obj.get("description").toString();
             e.setDescription(descp);
                String adress="";
               if(obj.get("adresse")!=null)
                   descp=obj.get("adresse").toString();
              e.setAdresse(adress);
                String lib="";
               if(obj.get("libelle")!=null)
                   descp=obj.get("libelle").toString();
                e.setLibelle(lib);
                //System.out.println(e.toString());
                listEtudiants.add(e);

            }

        } catch ( Exception ex) {  
            System.out.println(ex.getMessage());
        }
        System.out.println(listEtudiants);
        return listEtudiants;

}catch(Exception ex)
{
    return null;
}
    }
     ArrayList<Plan> ListPlans = new ArrayList<>();
    
    public ArrayList<Plan> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/planners/web/app_dev.php/tasks/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePlans ser = new ServicePlans();
                ListPlans = ser.getListPlans(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListPlans;
    }
    
    
    public ArrayList<Plan> getList(String json) {
        ArrayList<Plan> listSejours = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> sejours = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println();
            ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) sejours.get("root");
            System.out.println("List()" + list);
            for (Map<String, Object> obj : list) {
                Plan e = new Plan();
                // System.out.println("com.mycompany.myapp.MyApplication.getList()"+e);
                   float id = Float.parseFloat(obj.get("idP").toString()); 
                e.setId_p((int) id);
                //  e.setDateDeSejour(obj.get("dateDeSejour").toString());
                 if(obj.get("description")!=null)
                e.setDescription(obj.get("description").toString());
                if(obj.get("libelle")!=null)
                e.setLibelle(obj.get("libelle").toString());
                // e.setPicture(obj.get("picture").toString());
                 if(obj.get("ville")!=null)
                e.setVille(obj.get("ville").toString());
                 if(obj.get("adresse")!=null)
                e.setAdresse(obj.get("adresse").toString());
              
                       if(obj.get("prix")!=null)
                e.setPrix(obj.get("prix").toString()+"DT");
              
                 
                  if(obj.get("image")!=null)
                e.setImg(obj.get("image").toString());
                  
                    if(obj.get("longitude")!=null)
                e.setLongitude( Double.parseDouble(obj.get("longitude").toString()));
                      if(obj.get("latitude")!=null)
                e.setLatitude(Double.parseDouble(obj.get("latitude").toString()));
                listSejours.add(e);
            }
        } catch (IOException ex) {
        }
        return listSejours;

    }
    
     public void Delete(Plan p){
       
        ConnectionRequest con = new ConnectionRequest(); 
        String Url = "http://localhost/planners/web/app_dev.php/WsSupp/"+p.getId_p() ;
        con.setUrl(Url);
 con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
                   });
         NetworkManager.getInstance().addToQueueAndWait(con);

    
     }

    /*   ---------------------                   LIST SCATEGORIE      ------------------------------                 */



 public ArrayList<categorie> getListCategories(String json) {
try{

        ArrayList<categorie> list_Cat = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                categorie e = new categorie();

                 //System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("idC").toString());
               //System.out.println(id);
              e.setId_c((int) id);
           
            
            
                String lib="";
               if(obj.get("libelle")!=null)
                   lib=obj.get("libelle").toString();
                e.setLibelle(lib);
                    
                //System.out.println(e.toString());
                list_Cat.add(e);

            }

        } catch ( Exception ex) {  
            System.out.println(ex.getMessage());
        }
        System.out.println(list_Cat);
        return list_Cat;

}catch(Exception ex)
{
    return null;
}
    }
     ArrayList<categorie> ListCts = new ArrayList<>();
   
    public ArrayList<categorie> getListC(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/planners/web/app_dev.php/cat/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePlans ser = new ServicePlans();
                ListCts = ser.getListCategories(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListCts;
    }

    
    
    /*   ---------------------                   LIST SOUS_CATEGORIE      ------------------------------                 */
    
     public ArrayList<sous_categorie> getListsous_Categorie(String json) {
try{

        ArrayList<sous_categorie> list_SCat = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                sous_categorie e = new sous_categorie();

                 //System.out.println(obj.get("id"));
            float id = Float.parseFloat(obj.get("idSc").toString());
             
               e.setId_sc((int) id);
           
            
            
                String lib="";
               if(obj.get("libelle")!=null)
                   lib=obj.get("libelle").toString();
                e.setLibelle(lib);
                //System.out.println(e.toString());
                list_SCat.add(e);

            }

        } catch ( Exception ex) {  
            System.out.println(ex.getMessage());
        }
        System.out.println(list_SCat);
        return list_SCat;

}catch(Exception ex)
{
    return null;
}
    }
     ArrayList<sous_categorie> List_Sous_Cts = new ArrayList<>();
    
    
    
     public ArrayList<sous_categorie> getListSC( int Id_c ){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/planners/web/app_dev.php/getSc/"+Id_c);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePlans ser = new ServicePlans();
                List_Sous_Cts = ser.getListsous_Categorie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return List_Sous_Cts;
    }

    
    
    
    
    
    
    
    
}