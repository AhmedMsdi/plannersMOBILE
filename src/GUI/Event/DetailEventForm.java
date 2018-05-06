/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Entity.Evenement;
import Services.ServiceEvent;
import Services.ServiceParticipant;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;



public class DetailEventForm extends Form {
      
    public DetailEventForm(Resources resourceObjectInstance, int id) {
        
        setToolbar(new Toolbar());
        setLayout(BoxLayout.y());
        
        //setName("DetailsForm");

        //initGuiBuilderComponents(resourceObjectInstance,id);
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        
        ArrayList<Evenement> pp = new ArrayList<>();
        ServiceEvent se = new ServiceEvent();
        pp = se.findEventById(id);
        
        for (Iterator<Evenement> iterator = se.findEventById(id).iterator(); iterator.hasNext();) {
            Evenement p = iterator.next();
                setTitle(p.getTitre());
                //initGuiBuilderComponents(resourceObjectInstance,prodnext);
                int id_event = p.getId_event();
                Container gui_Container_1 = new Container(new BorderLayout());
        MultiButton gui_Multi_Button_1 = new MultiButton();
        MultiButton gui_LA = new MultiButton();
        Container block_info = new Container(BoxLayout.y());
        Label label_adr = new Label();
        Label label_prix = new Label();
        Label label_tel = new Label();
        Container gui_participant = new Container(new BorderLayout());
        Label label_nbr_particip = new Label();
        Button btn_participe = new Button();
        Container gui_imageContainer1 = new Container(new BorderLayout());
        Container gui_Container_2 = new Container(new BorderLayout());
        TextArea gui_Text_Area_1 = new TextArea();
        Container gui_imageContainer2 = new Container(new BorderLayout());
        Container gui_group_button = new Container(new BorderLayout());
        Button btn_Modif = new Button();
        Button btn_delete = new Button();
        Button btn_facebook=new Button();
        Label label_etat = new Label();
        
        add(gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setPropertyValue("line1", p.getTitre());
        gui_Multi_Button_1.setPropertyValue("line2", "");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_Container_1.addComponent(BorderLayout.SOUTH, block_info);
        block_info.add(label_adr);
        block_info.add(label_prix);
        block_info.add(label_tel);
        
        label_adr.setText("Adresse : "+p.getAdresse() +" " +p.getVille());
        label_adr.getAllStyles().setFgColor(0xFF8000);
        label_prix.setText("Prix : "+p.getPrix() +" DT");
        label_tel.setText("Numero : "+p.getTel());
        
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dd = formater.format(p.getDate_event());
        
        SimpleDateFormat formater_time = new SimpleDateFormat("HH:mm");
        String tt = formater_time.format(p.getTime_event());
        
        gui_LA.setPropertyValue("line1", "Le "+dd +" "+tt);
        gui_LA.setPropertyValue("line2", "à "+p.getVille());
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        
        add(gui_participant);
        gui_participant.setName("gui_participant");
        gui_participant.addComponent(BorderLayout.WEST, label_nbr_particip);
        gui_participant.addComponent(BorderLayout.CENTER, btn_participe);
        ServiceParticipant sp = new ServiceParticipant();
        String nbr_part = sp.nombreParticipant(id_event);
        label_nbr_particip.setText("Nbr de participants : "+nbr_part);
        label_nbr_particip.getAllStyles().setFgColor(0xFF8000);
        btn_participe.setText("Participer");
        btn_participe.setUIID("CenterLabelSmall");
        btn_participe.setName("btn_participe");
        btn_participe.addActionListener((e) -> {
            sp.ajouterParticipant(id_event, 1);
        });
        
        add(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);
        
        gui_Text_Area_1.setText(p.getDescription());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Container_2.setName("Container_2");
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setName("imageContainer1");  
        gui_imageContainer2.setName("imageContainer2");
        System.out.println(p.getId_user());
    
    if (p.getId_user() == EventForm.id_user) {
        String etat ;
        add(gui_group_button);
        gui_group_button.addComponent(BorderLayout.NORTH, label_etat);
        gui_group_button.addComponent(BorderLayout.WEST, btn_Modif);
        gui_group_button.addComponent(BorderLayout.EAST, btn_delete);
        gui_group_button.addComponent(BorderLayout.SOUTH,btn_facebook);
        if (p.getEtat() == 0)
            etat = "Non validé";
        else
            etat = "validé";
        label_etat.setText("Etat : "+etat);
        
        btn_Modif.setText("Modifier");
        btn_Modif.setName("btn_Modif");
        btn_Modif.addActionListener((e) -> {
            new NewEventForm(resourceObjectInstance, id_event).show();
        });
        
        btn_delete.setText("Supprimer");
        btn_delete.setName("btn_delete");
        btn_facebook.setText("Je Partage sur Facebook");
        btn_facebook.setName("btn_Facebook");
        
        btn_delete.addActionListener((e) -> {
            ServiceEvent serviceTask=new ServiceEvent();
            serviceTask.DeleteEvent(id_event);
                try {
                    new EventForm().show();
                } catch (IOException ex) {
                }
        });
        btn_facebook.addActionListener((e) -> {
          String accessToken = "EAACEdEose0cBAGtKSXLHROnO1WWITsTZB0bCsO0lWcj17vo4tTW5LS8bBByWalvIzYfZAfQZAhSrFcXToqlAXtqhnxsX8iF7CPlZCyRZB97sdm1K4ZBCcOebZBMH5vam6xcEmpZCCjsfwcPIMUW4URJ07koV17VhxZCwhV7zyBRv1D1bgRChqrq2C9PUbVuobZB7lXQ1IZB748zw90tyJZBATfGe";
               
                FacebookClient fbClient= new DefaultFacebookClient(accessToken,Version.VERSION_2_0); 
                String message = ""+p.getTitre()+"   à:  "+p.getAdresse();
                 FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                         Parameter.with("message",message)  );
                System.out.println("fb.com/"+response.getId());
                Dialog.show("Succes", "Votre Article à été partagé sur facebook", "Fermer", null);
        });
    }    
        
        
        if (p.getImage().toString() != "") {
            EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 180), true);
            URLImage imgg1 = URLImage.createToStorage(img1, p.getImage(), "http://localhost/planners/web/imageEvent/" + p.getImage());
            
            ImageViewer imgv1 = new ImageViewer(imgg1);
            gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
        } else {
            ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            gui_imageContainer1.add(BorderLayout.CENTER, sl);
        }
        
        
        // bouton ajouter
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
                try {
                    // lien pour la page d'jout
                    new MyEventForm(Resources.getGlobalResources(),EventForm.id_user).show();
                } catch (IOException ex) {
                }
        });
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);
        gui_LA.addActionListener((e) -> {
            //new MapEventForm().show();
            
        });
        
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
          
        }
    }
}
