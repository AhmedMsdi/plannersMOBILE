/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Article;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceArticle {

        
    
    public static double[] values1 = new double[2];
    public static String[] values2 = new String[2];
   public static ArrayList<String> titres=new ArrayList<String>();  
   public static ArrayList<Double> clicks=new ArrayList<Double>(); 

    public void ajoutTask(Article ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/planners/web/app_dev.php/articlejsonnew?titre="+ta.getTitre_article()
                +"&contenu="+ta.getContenu()+"&tags="+ta.getTags()+"&image="+ta.getImg_article()+"&idUser="+SignInForm.id_u;
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

    public ArrayList<Article> getListTask(String json) {

        ArrayList<Article> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
                int i=0;
            for (Map<String, Object> obj : list) {
                
                Article e = new Article();

                // System.out.println(obj.get("id"));
               // float id = Float.parseFloat(obj.get("idPub").toString());
               // System.out.println(id);
              //  e.setId_pub(obj.get("idPub").toString());
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                 e.setId_article((obj.get("id").toString()));
                e.setTitre_article(obj.get("titre").toString());
                e.setContenu(obj.get("contenu").toString());
                e.setTags(obj.get("tags").toString());
                 e.setImg_article(obj.get("image").toString());
               // e.setDate_article(obj.get("datecreation"));
               // System.out.println(obj.get("contenu").toString());
              //  e.setTitre_pub(obj.get("text").toString());
              //  e.setSite_pub(obj.get("siteWeb").toString());
               // e.setDesc_pub(obj.get("description").toString());
                //e.setNb_click();
                 Map<String, Object> date2  = (Map<String, Object>) obj.get("datecreation");
                 float da = Float.parseFloat(date2.get("timestamp").toString());
                 Date d = new Date((long)(da-3600 )*1000);
                 /*SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                                String dab = formater.format(d.getDate());*/
                 e.setDate_article(d);
                 
                   String user_id ="";
                user_id = obj.get("user").toString();
                String id_user=user_id.substring(user_id.indexOf("id=")+3, user_id.indexOf("prenom")-2);
               System.out.println("7achty   "+id_user);
             e.setId_u(id_user);
               // clicks.add(Double.parseDouble(obj.get("nbClick").toString()));
             //  values1[i]=Double.parseDouble(obj.get("nbClick").toString());
              //  values2[i]=e.getTitre_pub();
               // titres.add(e.getTitre_pub());
              
               i++;
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
   ArrayList<Article> listTasks = new ArrayList<>();
    
    public ArrayList<Article> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+ServicePublicite.serverAhmed+"/planners/web/app_dev.php/articlejson");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  listTasks.clear();
                ServiceArticle ser = new ServiceArticle();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
     
    


}
