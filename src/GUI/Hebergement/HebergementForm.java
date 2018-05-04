/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package GUI.Hebergement;
//import javax.mail.Session;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
//import java.util.Properties;
import java.util.ArrayList;
import com.codename1.io.NetworkManager;
import Services.ServiceHebergement;
import Entity.Hebergement;
import Entity.Messagerie;
import GUI.AccueilForm;
//import GUI.Article.ArticleForm;
import GUI.Plan.PlanForm;
import Services.ServiceMessagerie;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
//import com.twilio.Twilio;
//import com.twilio.type.PhoneNumber;
import java.util.Map;
import java.util.Random;
//import com.twilio.rest.api.v2010.account.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.util.Date;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class HebergementForm extends BaseForm {
     Image img;
       String imagecode="";
    public HebergementForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public HebergementForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        
        installSidemenu(resourceObjectInstance);
        
        
        //popup bouton
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage(""), e -> {});
        
        gui_Label_5.setShowEvenIfBlank(true);
        gui_Label_6.setShowEvenIfBlank(true);
        gui_Label_7.setShowEvenIfBlank(true);
        gui_Label_8.setShowEvenIfBlank(true);
        gui_Label_9.setShowEvenIfBlank(true);
        
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1_1.setRows(2);
        gui_Text_Area_1_1.setColumns(100);
        gui_Text_Area_1_1.setEditable(false);
        gui_Text_Area_1_2.setRows(2);
        gui_Text_Area_1_2.setColumns(100);
        gui_Text_Area_1_2.setEditable(false);
        gui_Text_Area_1_3.setRows(2);
        gui_Text_Area_1_3.setColumns(100);
        gui_Text_Area_1_3.setEditable(false);
        gui_Text_Area_1_4.setRows(2);
        gui_Text_Area_1_4.setColumns(100);
        gui_Text_Area_1_4.setEditable(false);
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getSelectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener((ActionListener) (ActionEvent evt) -> {
            
            //Formulaire ajout
        
            HebergementDetails hd = new HebergementDetails();
            Container ajoutForm = new Container(BoxLayout.y());        
            TextField titre = new TextField("", "Titre", 20, TextArea.ANY);
            CheckBox t6 = new CheckBox("Hôtel");
            CheckBox t7 = new CheckBox("Maison_d'hôte");
            CheckBox t8 = new CheckBox("Pension");
            TextField prix = new TextField("", "Prix/nuit", 20, TextArea.NUMERIC);
            TextArea description = new TextArea();
            TextField telephone = new TextField("", "Phone", 20, TextArea.PHONENUMBER);
            TextField lieu = new TextField("","Adresse");
            TextField site = new TextField("", "Site", 20, TextArea.URL);
            Label titreL = new Label("Titre :");
            Label categorieL = new Label("Catégorie :");
            Label prixL = new Label("Prix par nuit :");
            Label descriptionL = new Label("Description :");
            Label telephoneL = new Label("Téléphone :");
            Label lieuL = new Label("Adresse :");
            Label siteL = new Label("Site :");
            Button ajouter =new Button("Ajouter");
            Button btnimage = new Button("Image");
     
            
            ajoutForm.add(titreL);
            ajoutForm.add(titre);
            ajoutForm.add(categorieL);
            ajoutForm.add(t6);
            ajoutForm.add(t7);
            ajoutForm.add(t8);
            ajoutForm.add(prixL);
            ajoutForm.add(prix);
            ajoutForm.add(descriptionL);
            ajoutForm.add(description);
            ajoutForm.add(telephoneL);
            ajoutForm.add(telephone);
            ajoutForm.add(lieuL);
            ajoutForm.add(lieu);
            ajoutForm.add(siteL);
            ajoutForm.add(site);
            ajoutForm.add(ajouter);
             ajoutForm.add(btnimage);
       
         btnimage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        if (ev != null && ev.getSource() != null) {
                            String filePath = (String) ev.getSource();
                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
                            String fileName = filePath.substring(fileNameIndex);
                            img = null;
                              ImageIO imgIO = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                            try {
                                img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                               // System.out.println("aaaaaaaaaaaaa"+ filePath);
                                imgIO.save(img, out, ImageIO.FORMAT_JPEG, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                             byte[] ba = out.toByteArray();
                 imagecode = Base64.encode(ba);
             

                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });
            
            
            hd.add(ajoutForm);
            
            
            
            ajouter.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent evt){
                 if(t6.isSelected()){
                t7.setSelected(false);
                t8.setSelected(false);
                }
                if(t7.isSelected()){
                t6.setSelected(false);
                t8.setSelected(false);
                }
                if(t8.isSelected()){
                t6.setSelected(false);
                t7.setSelected(false);
                }
                String ch ="";
                if(t6.isSelected())ch+= "Hôtel";
                if(t7.isSelected())ch+= "Maison_d'hôte";
                if(t8.isSelected())ch+= "Pension";
               
                ServiceHebergement ser = new ServiceHebergement();
                 UploadImage.imageupload(imagecode, titre.getText());
                Hebergement t = new Hebergement(titre.getText(),ch,titre.getText()+".jpg",
                                                 Double.valueOf(prix.getText()),description.getText(),Integer.valueOf(telephone.getText()),lieu.getText(),site.getText());
                ser.ajoutHeb(t);
                
                System.out.println("Titre : "+titre.getText()+" Prix : "+prix.getText()+" Description : "+description.getText()+" Lieu : "+lieu.getText()+"Categorie : "+ch);
                System.out.println("ahmeddddddddddddddddddddd "+t.getPhoto());
                HebergementForm hh= new HebergementForm();
               
                hh.show();
            };
            
            });
            
            hd.show();
        });
        
        //////////////////////////////////////////////////////////////////////////////////
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
        gui_Text_Area_2.setRows(2);
        gui_Text_Area_2.setColumns(100);
        gui_Text_Area_2.setGrowByContent(false);
        gui_Text_Area_2.setEditable(false);
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_1 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_7 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_2 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_8 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_3 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_3 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_9 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_4 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_5 = new com.codename1.ui.Label();
    


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Hebergements");
        setName("TrendingForm");
        ServiceHebergement serviceHebergement=new ServiceHebergement();
       ArrayList<Hebergement> list = serviceHebergement.getList2();
       int i =0; 
       for (Hebergement h :list){
       i++;
            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
            com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
            com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
            com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
            com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
            com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
            com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
            com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
            
            
            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Multi_Button_1);
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
            gui_Multi_Button_1.setPropertyValue("line1", h.getTitre());
            gui_Multi_Button_1.setPropertyValue("line2", "l'adresse :");
            gui_Multi_Button_1.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
          

            EncodedImage image1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 150), true);
            URLImage images1 = URLImage.createToStorage(image1, "images1"+i, "http://localhost/planners/web/uploads/"+h.getPhoto());
            images1.fetch();
            ImageViewer imgv1 = new ImageViewer(images1);
            Container C1 = new Container(BoxLayout.y());
            C1.add(imgv1);
            addComponent(C1);
            
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
            Button Button_1 = new Button();
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, Button_1);
            gui_Text_Area_1.setText(h.getDescription());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");
            Button_1.setText("");
            Button_1.setUIID("Label");
            Button_1.setName("Button_1");
            com.codename1.ui.FontImage.setMaterialIcon(Button_1,"".charAt(0));
            gui_Container_2.setName("Container_2");
            addComponent(gui_separator1);
            addComponent(gui_null_1_1);
            
            
            Button consulter = new Button();
            C1.setLeadComponent(consulter);
            
            consulter.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent evt){
                
                DetailsForm hd = new DetailsForm();
                Picker dateDeb;
                Picker dateFin;
                TextField nb_person;
                Container c3 = new Container(new com.codename1.ui.layouts.BorderLayout());
                hd.add(c3);
                
            c3.setName("Container_1");
            MultiButton gui_Multi_Button_1 = new MultiButton();
            c3.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Multi_Button_1);
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
            gui_Multi_Button_1.setPropertyValue("line1", h.getTitre());
            gui_Multi_Button_1.setPropertyValue("line2", "l'adresse :");
            gui_Multi_Button_1.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
          

            EncodedImage img22 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 150), true);
            URLImage imgg2 = URLImage.createToStorage(img22,h.getPhoto(), "http://localhost/planners/web/uploads/"+h.getPhoto());
            imgg2.fetch();
            ImageViewer imgv2 = new ImageViewer(imgg2);
            Container C1 = new Container(BoxLayout.y());
            C1.add(imgv2);
            hd.add(C1);
            
            
            Container imageContainer1 = new Container(new com.codename1.ui.layouts.BorderLayout());
            hd.add(imageContainer1);
            imageContainer1.setName("imageContainer1");
            Container Container_2 = new Container(new com.codename1.ui.layouts.BorderLayout());
            imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, Container_2);
            Container_2.setName("Container_2");
            TextArea Text_Area_1 = new TextArea();
            Container_2.addComponent(BorderLayout.CENTER, Text_Area_1);
            Button Button_1 = new Button();
            Container_2.addComponent(BorderLayout.EAST, Button_1);
            Text_Area_1.setText(h.getDescription());
            Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            Text_Area_1.setName("Text_Area_1");
            Button_1.setText("");
            Button_1.setUIID("Label");
            Button_1.setName("Button_1");
            com.codename1.ui.FontImage.setMaterialIcon(Button_1,"".charAt(0));
            Container_2.setName("Container_2");
            Label separator1 = new Label();
            Label Datedebut = new Label("Datedebut");
            Label Datefin = new Label("Datefin");
            Label nbp = new Label("nombre de personne");
            addComponent(separator1);
            MultiButton null_1_1_1 = new MultiButton();         
            addComponent(null_1_1_1);
            dateDeb = new Picker();
            dateFin = new Picker();
            nb_person = new TextField("", "--", 2, TextField.NUMERIC);
            Container Cdd = new Container(BoxLayout.y());
            Cdd.add(Datedebut);
            Cdd.add(dateDeb);
            hd.add(Cdd);
            Container Cdf = new Container(BoxLayout.y());
            Cdf.add(Datefin);
            Cdf.add(dateFin);
            hd.add(Cdf);
            Container Cnbp = new Container(BoxLayout.y());
            Cnbp.add(nbp);
            Cnbp.add(nb_person);
            hd.add(Cnbp);
            
            
            Container re = new Container(BoxLayout.y());
            
            
            BrowserComponent browser = new BrowserComponent();
            browser.setScrollVisible(false);
            browser.setURL("http://localhost/planners/web/app_dev.php/hebergement/map?departid=ChIJaZ17jZnL4hIR_fY4C482aV8&destinationid=ChIJUe3GVHTL4hIRV9NcVrU6O2g");
            browser.setPreferredH(500);
            re.add(browser);
        

        
            Button reserver = new Button("Réserver");
            Button suprimer = new Button("Supprimer");
            re.add(reserver);
            re.add(suprimer);
            hd.add(re);
            hd.show();
            reserver.addActionListener(new ActionListener(){
            
                                @Override
                                public void actionPerformed(ActionEvent evt){
                                ServiceMessagerie ser = new ServiceMessagerie();
                                
                                dateDeb.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                                dateDeb.setWidth(500);
                                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                                String datdebu = formater.format(dateDeb.getDate());
                                
                                dateFin.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                                dateFin.setWidth(500);
                                SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd");
                                String datefin = formater2.format(dateFin.getDate()); 
                                Messagerie m = new Messagerie(datdebu,datefin ,Integer.valueOf(nb_person.getText()), "Client",1,h.getId(),1,1);
                                ser.ajoutMessagerie(m);
                                
                             //   MessagerieForm dd = new MessagerieForm();
                                  DetailsForm dd = new DetailsForm();
                             
                                  
                                ArrayList<Messagerie> list = ser.getList2();
                             /*  String accountSID = "AC1511259609341a6be56ca4eaff03075f";
                                String authToken = "01f5e137830c03368491bd634900e46b";
                                String fromPhone = "(415) 650-5203";
                                Response <Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                                queryParam("To", destinationPhone).
                                queryParam("From", fromPhone).
                                queryParam("Body", val).
                                basicAuth(accountSID, authToken)).
                                getAsJsonMap();*/
                                 for (Messagerie msg :list)
                                 {
                                     
                                     //mahmah
                                     Container messagerie = new Container(BoxLayout.y()); 
                                     
                                     Label L1 = new Label();
                                     Label L2 = new Label();
                                     Label L3 = new Label();
                                     SimpleDateFormat formater7 = new SimpleDateFormat("dd-MM-yyyy");
               
                                        String datdebu7 = formater7.format( msg.getDateDebut());
                                        String datfin7 = formater7.format(msg.getDateFin());
                                        
                                     L1.setText(datdebu7);
                                     L2.setText(datfin7);
                                     L3.setText(msg.getNbPerson()+"");
                                     Button bt = new Button("confirmer");
                                     messagerie.add(L1);
                                     messagerie.add(L2);
                                      messagerie.add(L3);
                                     messagerie.add(bt);
                                     dd.add(messagerie);
                                
                                     
                                     
                                 }
                                dd.show();
                                };            
                                
                                ////// mail api
                           //     Message msg = new Message("Body of message");
                           //     m.getAttachments().put(textAttachmentUri, "text/plain");
                           //     m.getAttachments().put(imageAttachmentUri, "image/png");
                           //     Display.getInstance().sendMessage(new String[] {"mahdiznaidi93@gmail.com"}, "Subject of message", msg);            
                                
           /*mail java*/                     
             /*             ConnectionRequest req = new ConnectionRequest();
                          NetworkManager.getInstance().addToQueue(req);
                          try{
                
                String host ="smtp.gmail.com";
                String user ="znaidimahdi93@gmail.com";
                String pass ="zapabenarous";
                String from ="znaidimahdi93@gmail.com";
                String to ="mahdi.znaidi@esprit.tn";
                String subject ="Consultation du mot de passe ";
                String messageText ="R";
                boolean sessionDebug =false ;
                
                Properties props = System.getProperties();

                   
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession =Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);
                
                Transport transport =mailSession.getTransport("smtp");
                transport.connect(host, user , pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message envoyé");
                } catch (MessagingException ex) {
                                    }

                          */
            });
            suprimer.addActionListener((e1) -> {
                ServiceHebergement ser = new ServiceHebergement();
                ser.supprimerHebergement(h.getId());
                Form rés = new Form("Hébergement supprimer", BoxLayout.y());
                                Container c5 = new Container(BoxLayout.y());
                                Label nombre_per = new Label("retour à la page suivante ");
                                Label nombre_per2 = new Label();
                                
                                c5.add(nombre_per);
                                c5.add(nombre_per2);
                                
                                rés.add(c5);
                                rés.show();
            });
            }
            });
        
            
        }
        
       }
    


//-- DON'T EDIT ABOVE THIS LINE!!!
    

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
