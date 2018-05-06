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
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.Animation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.events.StyleListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.codename1.ui.util.Resources.getGlobalResources;
import java.util.Date;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
     CategorieEvent cat = new CategorieEvent();
     String pictureCode = null;
     String picturegallery = null;
     Image picturegalleryImage;
     Label picturecont=new Label();
     StringBuffer str=new StringBuffer();
    StringBuffer str2=new StringBuffer();
    String reponse = null;
    String reponse2 = null;
     Evenement e1 = new Evenement();
     // form ajout
    public NewEventForm(Resources resourceObjectInstance) {
        Container gui_Container_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
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
        Label lbl_categorie = new Label("Choix du categorie :");
        Label lbl_titre = new Label("titre :");
        Label lbl_adr = new Label("adresse :");
        Label lbl_ville = new Label("ville :");
        Label lbl_tel = new Label("numero contact :");
        Label lbl_prix = new Label("prix :");
        Label lbl_descrip = new Label("Description :");
        Label lbl_date = new Label("Date d'evenement :");
        Label lbl_heure = new Label("Heure d'evenement :");
        Button takepicture = new Button();
        Button opengallery = new Button();

        setLayout(BoxLayout.x());
        setToolbar(new Toolbar());
        
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        setTitle("Nouveau Evenement");
        comboCategorie = new ComboBox();
        add(gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        
        gui_Container_1.addComponent(lbl_categorie);
        gui_Container_1.addComponent(comboCategorie);
        gui_Container_1.addComponent(lbl_titre);
        gui_Container_1.addComponent(gui_Text_titre);
        gui_Container_1.addComponent(lbl_adr);
        gui_Container_1.addComponent(gui_Text_adresse);
        gui_Container_1.addComponent(lbl_ville);
        gui_Container_1.addComponent(gui_Text_ville);
        gui_Container_1.addComponent(lbl_tel);
        gui_Container_1.addComponent(gui_text_contact);
        gui_Container_1.addComponent(lbl_prix);
        gui_Container_1.addComponent(gui_Text_prix);
        gui_Container_1.addComponent(lbl_descrip);
        gui_Container_1.addComponent(gui_Text_desc);
        
        ServiceCategorie sc = new ServiceCategorie();
        List<CategorieEvent> listCat = new ArrayList<CategorieEvent>();
        listCat = sc.getList();
        DefaultListModel scc = new DefaultListModel(listCat);
        comboCategorie.setModel(scc);
        
        cat = (CategorieEvent) comboCategorie.getSelectedItem();
        id_cat = cat.getId_cat();
        comboCategorie.addActionListener((evt) -> {
            cat = (CategorieEvent) comboCategorie.getSelectedItem();
            id_cat = cat.getId_cat();
            //System.out.println(id_cat);
        });
       
        Picker date_event_picker = new Picker();
        Picker time_event_picker = new Picker();
        gui_Container_1.addComponent(lbl_date);
        gui_Container_1.addComponent(date_event_picker);
        gui_Container_1.addComponent(lbl_heure);
        gui_Container_1.addComponent(time_event_picker);
        gui_Container_1.addComponent(takepicture);
        gui_Container_1.addComponent(opengallery);
        gui_Container_1.addComponent(picturecont);
        gui_Container_1.addComponent(gui_Button_ajouter);
        gui_Container_1.addComponent(gui_Button_annuler);
        
        date_event_picker.setType(Display.PICKER_TYPE_DATE);
        date_event_picker.setDate(new Date());
        
        time_event_picker.setType(Display.PICKER_TYPE_TIME);
        time_event_picker.setTime(0);
        gui_Button_ajouter.setText("Ajouter");
        gui_Button_ajouter.setName("Button_2");
        gui_Button_ajouter.addActionListener((e) -> {
            int prix =Integer.parseInt(gui_Text_prix.getText());
            int tel =Integer.parseInt(gui_text_contact.getText());
            Date date_event = date_event_picker.getDate();
            int time_event = time_event_picker.getTime();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            //SimpleDateFormat format_time = new SimpleDateFormat("HH:mm");
            String dd = formater.format(date_event);
            System.out.println("date : "+dd);
            int heure = time_event/60;
            int minute = time_event%60;
            String time = heure+":"+minute+":00";
            System.out.println(time);
            int etat = 0;
            String img = null;
            
            if(pictureCode!=null)
           {
           uploadimage(pictureCode);
           img = reponse2;
           }else if(picturegallery!=null)
            {
                uploadimage(picturegallery);
                img = reponse2;
            }
            //img = img.substring(img.lastIndexOf("/") + 1);
            ServiceEvent ser = new ServiceEvent();
            Evenement t = new Evenement(0,etat,gui_Text_titre.getText(),gui_Text_adresse.getText(),gui_Text_ville.getText()
                    ,prix,gui_Text_desc.getText(),tel,img,dd,time,id_cat,EventForm.id_user);
            System.out.println("ajout "+t);
            ser.ajouterEvent(t);

        });
        takepicture.setText("camera");
        opengallery.setText("image");
        
        
        takepicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    pictureCode = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                    if(pictureCode!=null)
                    {
                        Image img;
                        img = Image.createImage(pictureCode);
                        picturecont.setIcon(img);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        opengallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
       Display.getInstance().openGallery(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (ev != null && ev.getSource() != null) {
                    picturegallery = (String) ev.getSource();
                    Image img = null;
                    try {
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(picturegallery));
                          picturecont.setIcon(img);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, Display.GALLERY_IMAGE);
            }
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
        TextField gui_Text_titre= new TextField("","",20, TextArea.ANY);
        TextField gui_Text_adresse =new TextField();
        TextField gui_Text_ville=new TextField();
        TextField gui_Text_prix=new com.codename1.ui.TextField();
        TextField gui_text_contact = new com.codename1.ui.TextField();
        Button gui_Button_ajouter = new com.codename1.ui.Button();
        Button gui_Button_annuler = new com.codename1.ui.Button();
        TextArea gui_Text_desc=new com.codename1.ui.TextArea();
        Calendar gui_Calender_1=new com.codename1.ui.Calendar();
        ComboBox<Map<String, Object>> comboCategorie = new ComboBox<>();
        Label lbl_categorie = new Label("Choix du categorie :");
        Label lbl_titre = new Label("titre :");
        Label lbl_adr = new Label("adresse :");
        Label lbl_ville = new Label("ville :");
        Label lbl_tel = new Label("numero contact :");
        Label lbl_prix = new Label("prix :");
        Label lbl_descrip = new Label("Description :");
        Label lbl_date = new Label("Date d'evenement :");
        Label lbl_heure = new Label("Heure d'evenement :");
        Label lbl_image = new Label("Image :");
        Button takepicture = new Button();
        
        
        setLayout(BoxLayout.x());
        setToolbar(new Toolbar());
        
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
        setTitle("Modifier Evenement");
        
        add(gui_Container_1);
        gui_Container_1.setScrollableY(false);
        gui_Container_1.setName("Container_1");
        
        gui_Container_1.addComponent(lbl_categorie);
        gui_Container_1.addComponent(comboCategorie);
        gui_Container_1.addComponent(lbl_titre);
        gui_Container_1.addComponent(gui_Text_titre);
        gui_Container_1.addComponent(lbl_adr);
        gui_Container_1.addComponent(gui_Text_adresse);
        gui_Container_1.addComponent(lbl_ville);
        gui_Container_1.addComponent(gui_Text_ville);
        gui_Container_1.addComponent(lbl_tel);
        gui_Container_1.addComponent(gui_text_contact);
        gui_Container_1.addComponent(lbl_prix);
        gui_Container_1.addComponent(gui_Text_prix);
        gui_Container_1.addComponent(lbl_descrip);
        gui_Container_1.addComponent(gui_Text_desc);
        gui_Container_1.addComponent(lbl_image);
        gui_Container_1.addComponent(takepicture);
        gui_Container_1.addComponent(picturecont);
        
        if (p.getImage().toString() != "") {
            EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 180), true);
            URLImage imgg1 = URLImage.createToStorage(img1, p.getImage(), "http://localhost/planners/web/imageEvent/" + p.getImage());
            
            ImageViewer imgv1 = new ImageViewer(imgg1);
            gui_Container_1.addComponent(imgv1);
        } else {
            ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            gui_Container_1.addComponent(sl);
        }
        
        takepicture.setText("Changer image");
        
        takepicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    pictureCode = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                    if(pictureCode!=null)
                    {
                        Image img;
                        img = Image.createImage(pictureCode);
                        picturecont.setIcon(img);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        ServiceCategorie sc = new ServiceCategorie();
        List<CategorieEvent> listCat = new ArrayList<CategorieEvent>();
        List<CategorieEvent> CatSelect = new ArrayList<CategorieEvent>();
        listCat = sc.getList();
        System.out.println("111 "+listCat.toString());
        DefaultListModel scc = new DefaultListModel(listCat);
        comboCategorie.setModel(scc);
        
        CategorieEvent ce = new CategorieEvent();
            System.out.println("id cat "+p.getId_cat());
        CatSelect = sc.getCategorie(p.getId_cat());
        //CatSelect.get(0).getLibelle();
        System.out.println("aaa "+CatSelect.get(0).getId_cat()+" zzz "+CatSelect.get(0).getLibelle());
        comboCategorie.setSelectedItem(createListEntry(CatSelect.get(0).getLibelle()));
        
        int index = 0;
        for (int i = 0; i<comboCategorie.size();i++){
            comboCategorie.setSelectedIndex(i);
            cat = (CategorieEvent) comboCategorie.getSelectedItem();
            if (CatSelect.get(0).getLibelle().equals(cat.getLibelle())){
                index = i;
                System.out.println("index "+index);
            }
        }
        comboCategorie.setSelectedIndex(index);
        
        cat = (CategorieEvent) comboCategorie.getSelectedItem();
        id_cat = cat.getId_cat();
        System.out.println("test "+id_cat);
        comboCategorie.addActionListener((evt) -> {
            cat = (CategorieEvent) comboCategorie.getSelectedItem();
            id_cat = cat.getId_cat();
            System.out.println(id_cat);
        });
        
        gui_Text_titre.setText(p.getTitre());
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
        
        Picker date_event_picker = new Picker();
        Picker time_event_picker = new Picker();
        gui_Container_1.addComponent(lbl_date);
        gui_Container_1.addComponent(date_event_picker);
        gui_Container_1.addComponent(lbl_heure);
        gui_Container_1.addComponent(time_event_picker);
        gui_Container_1.addComponent(gui_Button_ajouter);
        gui_Container_1.addComponent(gui_Button_annuler);
        
        date_event_picker.setType(Display.PICKER_TYPE_DATE);
        date_event_picker.setDate(p.getDate_event());
        
        int tim = (Integer.parseInt(p.getEvent_time_string()))/60;
        time_event_picker.setType(Display.PICKER_TYPE_TIME);
        time_event_picker.setTime(tim);
        gui_Button_ajouter.setText("Enregistrer");
        gui_Button_ajouter.setName("Button_2");
        gui_Button_ajouter.addActionListener((e) -> {
            int prix =Integer.parseInt(gui_Text_prix.getText());
            int tel =Integer.parseInt(gui_text_contact.getText());
            Date date_event = date_event_picker.getDate();
            int time_event = time_event_picker.getTime();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            
            String dd = formater.format(date_event);
            int heure = time_event/60;
            int minute = time_event%60;
            String time = heure+":"+minute+":00";
            int etat = 0;
            ServiceEvent ser = new ServiceEvent();
            
            String img = null;
            
            if(pictureCode!=null)
           {
           uploadimage(pictureCode);
           img = reponse2;
           }else if(picturegallery!=null)
            {
                uploadimage(picturegallery);
                img = reponse2;
            }
            Evenement t = new Evenement(p.getId_event(),etat,gui_Text_titre.getText(),gui_Text_adresse.getText(),gui_Text_ville.getText(),
                    prix,gui_Text_desc.getText(),tel,img,dd,time,id_cat,EventForm.id_user);
            System.out.println("Modif "+t);
            ser.ModifEvent(t);

        });
        }
    }
    
    public void uploadimage(String picture)
    {
             if (picture == null) {
                return;
            }
            try {
                Image img = Image.createImage(picture).scaledSmallerRatio(256, 256);

                ImageIO imgIO = ImageIO.getImageIO();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                imgIO.save(img, out, ImageIO.FORMAT_JPEG, 1);
                byte[] ba = out.toByteArray();
                String Imagecode1 = Base64.encode(ba);
              
                  ConnectionRequest request = new ConnectionRequest(){
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        int ch2;
                             while ((ch2=input.read())!=-1) {
                                str2.append((char)ch2);
                        }
                            reponse2=str2.toString();
                    }

                    @Override
                    protected void postResponse() {
                        System.out.println(reponse2);
                           e1.setImage(reponse2);
                        
                    }
                      
                  };
            request.setPost(true);
          request.setHttpMethod("POST");
          request.addArgument("Image", Imagecode1);
          request.setUrl("http://localhost/scriptup.php");
          NetworkManager.getInstance().addToQueueAndWait(request);
           } catch (Exception ex) {
                System.err.println("fffff"+ex.getMessage());
            }
      }

    private Map<String, Object> createListEntry(String libelle) {
        Map<String, Object> entry = new HashMap<>();
        System.out.println("createListEntry libelle "+libelle);
        entry.put("", libelle);
        return entry;
    }
}

