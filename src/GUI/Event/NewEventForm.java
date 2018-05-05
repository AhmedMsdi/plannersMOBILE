/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Entity.CategorieEvent;
import Entity.Evenement;
import Services.ServiceCategorie;
import Services.ServiceEvent;
import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.Animation;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.events.StyleListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.util.Resources.getGlobalResources;
import java.util.Date;
import com.codename1.ui.spinner.Picker;
import com.codename1.uikit.pheonixui.BaseForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewEventForm extends BaseForm implements Animation, StyleListener, Iterable<Component>{
    
    CategorieEvent categorie1;
    
    public NewEventForm() {
        this(getGlobalResources());
    }
     public int id_cat =-1;
     // form ajout
    public NewEventForm(Resources resourceObjectInstance) {
        Container gui_Container_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ComponentGroup gui_Component_Group_1 = new ComponentGroup();
        TextField gui_Text_titre= new com.codename1.ui.TextField();
        TextField gui_Text_adresse =new com.codename1.ui.TextField();
        TextField gui_Text_ville=new com.codename1.ui.TextField();
        TextField gui_Text_prix=new com.codename1.ui.TextField();
        TextField gui_text_contact = new com.codename1.ui.TextField();
        Button gui_Button_ajouter = new com.codename1.ui.Button();
        Button gui_Button_annuler = new com.codename1.ui.Button();
        TextArea gui_Text_desc=new com.codename1.ui.TextArea();
        Calendar gui_Calender_1=new com.codename1.ui.Calendar();
        ComboBox comboCategorie ;
        
        setLayout(BoxLayout.x());
        setToolbar(new Toolbar());
        
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        setTitle("Nouveau Evenement");
        comboCategorie = new ComboBox();
        add(gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(comboCategorie);
        gui_Component_Group_1.addComponent(gui_Text_titre);
        gui_Component_Group_1.addComponent(gui_Text_adresse);
        gui_Component_Group_1.addComponent(gui_Text_ville);
        gui_Component_Group_1.addComponent(gui_text_contact);
        gui_Component_Group_1.addComponent(gui_Text_prix);
        gui_Component_Group_1.addComponent(gui_Text_desc);
        
        ServiceCategorie sc = new ServiceCategorie();
        List<CategorieEvent> listCat = new ArrayList<CategorieEvent>();
        listCat = sc.getList();
        DefaultListModel scc = new DefaultListModel(listCat);
        comboCategorie.setModel(scc);
        

        
       comboCategorie.addActionListener((evt) -> {
            CategorieEvent cat = (CategorieEvent) comboCategorie.getSelectedItem();
            id_cat = cat.getId_cat();
            System.out.println(id_cat);
        });
       
        gui_Text_titre.setText("Titre");
        gui_Text_titre.setName("gui_Text_titre");
        gui_Text_adresse.setText("Adresse");
        gui_Text_adresse.setName("gui_Text_adresse");
        gui_Text_ville.setText("ville");
        gui_Text_ville.setName("gui_Text_ville");
        gui_Text_prix.setText("prix");
        gui_Text_prix.setName("gui_Text_prix");
        gui_text_contact.setText("numero contact");
        gui_text_contact.setName("gui_text_contact");
        gui_Text_desc.setName("gui_Text_desc");
        gui_Text_desc.setText("donner votre description");
        
//gui_Calender_1.addActionListener((e) -> Log.p("You picked: " + new Date(gui_Calender_1.getSelectedDay())));

        Picker date_event_picker = new Picker();
        Picker time_event_picker = new Picker();
        gui_Component_Group_1.addComponent(date_event_picker);
        gui_Component_Group_1.addComponent(time_event_picker);
        gui_Container_1.addComponent(gui_Button_ajouter);
        gui_Container_1.addComponent(gui_Button_annuler);
        
        date_event_picker.setType(Display.PICKER_TYPE_DATE);
        date_event_picker.setDate(new Date());
        
        time_event_picker.setType(Display.PICKER_TYPE_TIME);
        //time_event_picker.setTime(new Time());
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_ajouter.setText("Ajouter");
        gui_Button_ajouter.setName("Button_2");
        gui_Button_ajouter.addActionListener((e) -> {
            int prix =Integer.parseInt(gui_Text_prix.getText());
            int tel =Integer.parseInt(gui_text_contact.getText());
            Date date_event = date_event_picker.getDate();
//            Date time_event = time_event_picker.getDate();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
            String dd = formater.format(date_event);
//            String tt = formater.format(time_event);
//            System.out.println(tt);
            int etat = 0;
            ServiceEvent ser = new ServiceEvent();
            Evenement t = new Evenement(gui_Text_titre.getText(),gui_Text_adresse.getText(),gui_Text_ville.getText()
                    ,prix,gui_Text_desc.getText(),tel,dd,id_cat,EventForm.id_user,etat);
            System.out.println("ajout "+t);
            ser.ajouterEvent(t);

        });
    }

    // form modif
    public NewEventForm(Resources resourceObjectInstance, int id) {
        
        ArrayList<Evenement> pp = new ArrayList<>();
        ServiceEvent se = new ServiceEvent();
        pp = se.findEventById(id);
        
        for (Iterator<Evenement> iterator = se.findEventById(id).iterator(); iterator.hasNext();) {
            Evenement p = iterator.next();
        Container gui_Container_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ComponentGroup gui_Component_Group_1 = new ComponentGroup();
        TextField gui_Text_titre= new com.codename1.ui.TextField();
        TextField gui_Text_adresse =new com.codename1.ui.TextField();
        TextField gui_Text_ville=new com.codename1.ui.TextField();
        TextField gui_Text_prix=new com.codename1.ui.TextField();
        TextField gui_text_contact = new com.codename1.ui.TextField();
        Button gui_Button_ajouter = new com.codename1.ui.Button();
        Button gui_Button_annuler = new com.codename1.ui.Button();
        TextArea gui_Text_desc=new com.codename1.ui.TextArea();
        Calendar gui_Calender_1=new com.codename1.ui.Calendar();
        ComboBox<Map<String, Object>> comboCategorie = new ComboBox<>();
        
        setLayout(BoxLayout.x());
        setToolbar(new Toolbar());
        
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        setTitle("Modifier Evenement");
        
        add(gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(comboCategorie);
        gui_Component_Group_1.addComponent(gui_Text_titre);
        gui_Component_Group_1.addComponent(gui_Text_adresse);
        gui_Component_Group_1.addComponent(gui_Text_ville);
        gui_Component_Group_1.addComponent(gui_text_contact);
        gui_Component_Group_1.addComponent(gui_Text_prix);
        gui_Component_Group_1.addComponent(gui_Text_desc);
        
        ServiceCategorie sc = new ServiceCategorie();
        ArrayList<CategorieEvent> listCat = new ArrayList<>();
        
        for (CategorieEvent categorie : sc.getList()) {
            System.out.println(categorie.getId_cat());
            System.out.println(categorie.getLibelle());
            //String id = Integer.valueOf(categorie);
            //CategorieEvent prodnext = iterator.next();
            comboCategorie.addItem(createListEntry(categorie));
            comboCategorie.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        }
        
        comboCategorie.addActionListener((evt) -> {

            CategorieEvent cat = (CategorieEvent) comboCategorie.getSelectedItem();
                      id_cat = cat.getId_cat();
                    System.out.println(id_cat);
        });
        
        gui_Text_titre.setText(p.getTitre());
        gui_Text_titre.setName("gui_Text_titre");
        gui_Text_adresse.setText(p.getAdresse());
        gui_Text_adresse.setName("gui_Text_adresse");
        gui_Text_ville.setText(p.getVille());
        gui_Text_ville.setName("gui_Text_ville");
        gui_Text_prix.setText(String.valueOf(p.getPrix()));
        gui_Text_prix.setName("gui_Text_prix");
        gui_text_contact.setText(String.valueOf(p.getTel()));
        gui_text_contact.setName("gui_text_contact");
        gui_Text_desc.setName("gui_Text_desc");
        gui_Text_desc.setText(p.getDescription());
        
//gui_Calender_1.addActionListener((e) -> Log.p("You picked: " + new Date(gui_Calender_1.getSelectedDay())));

        Picker date_event_picker = new Picker();
        Picker time_event_picker = new Picker();
        gui_Component_Group_1.addComponent(date_event_picker);
        gui_Component_Group_1.addComponent(time_event_picker);
        gui_Container_1.addComponent(gui_Button_ajouter);
        gui_Container_1.addComponent(gui_Button_annuler);
        date_event_picker.setType(Display.PICKER_TYPE_DATE);
        date_event_picker.setDate(new Date());
        
        time_event_picker.setType(Display.PICKER_TYPE_TIME);
        //time_event_picker.setTime(new Time());
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_ajouter.setText("Enregistrer");
        gui_Button_ajouter.setName("Button_2");
        gui_Button_ajouter.addActionListener((e) -> {
            int prix =Integer.parseInt(gui_Text_prix.getText());
            int tel =Integer.parseInt(gui_text_contact.getText());
            Date date_event = date_event_picker.getDate();
            Date time_event = time_event_picker.getDate();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
            String dd = formater.format(date_event);
            String tt = formater.format(time_event);
            System.out.println(tt);
            ServiceEvent ser = new ServiceEvent();
            
            Evenement t = new Evenement(gui_Text_titre.getText(),gui_Text_adresse.getText(),gui_Text_ville.getText(),
                    prix,gui_Text_desc.getText(),tel,dd,id_cat,EventForm.id_user,0);
            System.out.println("ajout "+t);
            ser.ModifEvent(t);

        });
        }
    }

    private Map<String, Object> createListEntry(CategorieEvent obj) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("id", obj.getId_cat());
        entry.put("lib", obj.getLibelle());
        return entry;
    }
}

