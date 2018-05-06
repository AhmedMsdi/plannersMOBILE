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
package GUI.Article;

import Entity.Article;
import Services.ServiceArticle;
import Services.ServicePublicite;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ArticleForm extends BaseForm {

    public ArticleForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public ArticleForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Articles");
        setName("TrendingForm");
        
    
        initGuiBuilderComponents(resourceObjectInstance);
     
     FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_HOME);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
           
            fab.setUIID("FloatingActionButtonClose");
            Image oldImage = fab.getIcon();
            FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
            fab.setIcon(image);
      
            Dialog popup = new Dialog();
            popup.setDialogUIID("Container");
            popup.setLayout(new LayeredLayout());
            FloatingActionButton c3  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
            FloatingActionButton c2  = FloatingActionButton.createFAB(FontImage.MATERIAL_FACE);
            FloatingActionButton c1  = FloatingActionButton.createFAB(FontImage.MATERIAL_PIE_CHART);
          //  Button c1 = new Button(resourceObjectInstance.getImage("contact-a.png"));
           // Button c2 = new Button(resourceObjectInstance.getImage("contact-b.png"));
           // Button c3 = new Button(resourceObjectInstance.getImage("contact-c.png"));
            Button trans = new Button(" ");
            trans.setUIID("Container");
            c1.setUIID("Container");
            c2.setUIID("Container");
            c3.setUIID("Container");
            Style c1s = c1.getAllStyles();
            Style c2s = c2.getAllStyles();
            Style c3s = c3.getAllStyles();
            
            c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c2s.setMarginUnit(Style.UNIT_TYPE_DIPS);
            c3s.setMarginUnit(Style.UNIT_TYPE_DIPS);

            c1s.setMarginBottom(16);
            c1s.setMarginLeft(12);
            c1s.setMarginRight(3);

            c2s.setMarginLeft(4);
            c2s.setMarginTop(5);
            c2s.setMarginBottom(10);
            c3s.setMarginRight(14);
            
            c3s.setMarginTop(15);
            c3s.setMarginRight(16);

            popup.add(trans).
                    add(FlowLayout.encloseIn(c1)).
                    add(FlowLayout.encloseIn(c2)).
                    add(FlowLayout.encloseIn(c3));
            
            ActionListener a = ee -> popup.dispose();
            
            trans.addActionListener(a);
           c1.addActionListener(a2->{
               ServicePublicite sp = new ServicePublicite();
                 
               sp.createPieChartForm().show();
           });
            c2.addActionListener(a);
            c3.addActionListener(a1->{
              AddArticleForm v = new AddArticleForm();
                v.show();  
            });
            
            popup.setTransitionInAnimator(CommonTransitions.createEmpty());
            popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
            popup.setDisposeWhenPointerOutOfBounds(true);
            int t = ArticleForm.this.getTintColor();
            ArticleForm.this.setTintColor(0);
            popup.showPopupDialog(new Rectangle(ArticleForm.this.getWidth() - 10, ArticleForm.this.getHeight() - 10, 10, 10));
            ArticleForm.this.setTintColor(t);
            fab.setUIID("FloatingActionButton");
            fab.setIcon(oldImage);
            
      
        });
        
        installSidemenu(resourceObjectInstance);
       getToolbar().addSearchCommand(e -> {
             String text = (String)e.getSource();
             if(text != null && text.length() != 0) {
                 
           
            getToolbar().getParent().removeAll();
            try{
            getToolbar().getParent().animateLayout(300);
            }catch(NullPointerException ex){
                System.out.println("null");
            }
            //showDishesContainer();
            
             System.out.println(text);
             initGuiBuilderComponentsR(resourceObjectInstance,text);

             }
            
}, 4);
        
       

      
        
  
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
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
         ServiceArticle serviceTask=new ServiceArticle();
       //  ArrayList<publicite> listTasks = serviceTask.getList2();
        for(Article pub:serviceTask.getList2()){
            
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
     com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
     com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
     
     
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
      
           gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, pub.getImg_article(),
                                    "http://"+ServicePublicite.serverAhmed+"/planners/web/"+pub.getImg_article());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       
        gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
        
         FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);
       
       
        
        
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_1.setPropertyValue("line1", pub.getTitre_article());
        gui_Multi_Button_1.setPropertyValue("line2", "@dropperidiot");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", "3 minutes ago");
        gui_LA.setPropertyValue("line2", "in Los Angeles");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Text_Area_1.setText(pub.getDate_article()+"");
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,"".charAt(0));
   
        
        gui_Container_3.setName("Container_3");
        addComponent(gui_Label_1_1_1);
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setName("imageContainer1");
        gui_separator1.setUIID("Separator");
        gui_separator1.setName("separator1");
        gui_null_1_1.setName("null_1_1");
       
        gui_Label_1_1_1.setUIID("Separator");
        gui_Label_1_1_1.setName("Label_1_1_1");
        
         /****/
          Button bb = new Button();
                        gui_imageContainer1.setLeadComponent(bb);

                   bb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            VoidForm a = new VoidForm();
                             a.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_KEYBOARD_BACKSPACE, e -> {
             ArticleForm h=new ArticleForm();
                                h.show();
        });
                            
                            
                                 com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
                                     com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
             com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
   
                                 com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
                                 com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());

                          EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, pub.getImg_article(),
                                    "http://"+ServicePublicite.serverAhmed+"/planners/web/"+pub.getImg_article());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
                             gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
                             gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
                              gui_Text_Area_1.setText(pub.getContenu());
                              gui_Multi_Button_1.setPropertyValue("line1", pub.getTitre_article());
                               a.add(gui_Container_1);
                            a.add(gui_imageContainer1);
                                           Button b = new Button("Supprimer");
                                         
                                           if ( (int)Float.parseFloat(pub.getId_u())==SignInForm.id_u){
                         a.add(b);
                                           }
                     b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+ServicePublicite.serverAhmed+"/planners/web/app_dev.php/articlejsondelete/"+pub.getId_article();
        con.setUrl(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
         ArticleForm h=new ArticleForm();
                                h.show();
         
                        }
                        
                        });
                            a.show();
                            
                        }
                   });
          }
    }// </editor-fold>

    
        private void initGuiBuilderComponentsR(com.codename1.ui.util.Resources resourceObjectInstance,String text) {
    /*     ServicePublicite serviceTask=new ServicePublicite();
       //  ArrayList<publicite> listTasks = serviceTask.getList2();
        for(Publicite pub:WalkthruPubForm.listTasks){
            if(pub.getTitre_pub().contains(text)){
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
     com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
     com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
     
     
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
      
           gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, pub.getImg_pub(),
                                    "http://"+ServicePublicite.serverAhmed+"/planners/web/"+pub.getImg_pub());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       
        gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
        
         FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);
       
       
        
        
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_1.setPropertyValue("line1", pub.getTitre_pub());
        gui_Multi_Button_1.setPropertyValue("line2", "@dropperidiot");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", "3 minutes ago");
        gui_LA.setPropertyValue("line2", "in Los Angeles");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Text_Area_1.setText(pub.getDesc_pub());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,"".charAt(0));
   
        
        gui_Container_3.setName("Container_3");
        addComponent(gui_Label_1_1_1);
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setName("imageContainer1");
        gui_separator1.setUIID("Separator");
        gui_separator1.setName("separator1");
        gui_null_1_1.setName("null_1_1");
       
        gui_Label_1_1_1.setUIID("Separator");
        gui_Label_1_1_1.setName("Label_1_1_1");
        
       
          Button bb = new Button();
                        gui_imageContainer1.setLeadComponent(bb);

                   bb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            VoidForm a = new VoidForm();
                                 com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
                                     com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
             com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
   
                                 com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
                                 com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());

                          EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(1000, 1000), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, pub.getImg_pub(),
                                    "http://"+ServicePublicite.serverAhmed+"/planners/web/"+pub.getImg_pub());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
                             gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
                             gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
                              gui_Text_Area_1.setText(pub.getDesc_pub());
                              gui_Multi_Button_1.setPropertyValue("line1", pub.getTitre_pub());
                               a.add(gui_Container_1);
                            a.add(gui_imageContainer1);
                          if (Integer.parseInt(pub.getId_u())==SignInForm.id){
                             // System.out.println("ahmeeeeeeeeeeeeeed "+ Integer.parseInt(pub.getId_u()));
                                           Button b = new Button("Supprimer");
                                          
                     a.add(b);
                                           
                     b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+ServicePublicite.serverAhmed+"/planners/web/app_dev.php/pubjsondelete/"+pub.getId_pub();
        con.setUrl(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
         ServicePublicite serviceTask=new ServicePublicite();
            WalkthruPubForm.listTasks = serviceTask.getList2();
            
         PubliciteForm h=new PubliciteForm();
                                h.show();
         
                        }
                        
                        });  }
                            a.show();
                            
                        }
                   });
          }
        }*/
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
