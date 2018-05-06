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
import Entity.Publicite;
import Services.ServiceArticle;
import Services.ServicePublicite;
//import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.components.ImageViewer;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import static com.codename1.util.StringUtil.replaceAll;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

  

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class AddArticleForm extends BaseForm {
  
      Image img;
       String imagecode="";

    public AddArticleForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
   
    
    
    public AddArticleForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Ajout Artcile");
        setName("TrendingForm");
        String defstring ="";
        
         Button btnajout = new Button("Ajouter");
         Button btnimage = new Button("Image");
       Container C = new Container();
         TextField tnom = new TextField("","Titre");
    TextField tdesc= new TextField("","Contenu");
    TextField ttags= new TextField("","Tags");
 //   TextField tsite= new TextField("","Site");
   
       C.add(tnom);
       C.add(tdesc);
       C.add(ttags);
     //  C.add(tsite);
       C.add(btnajout);
       C.add(btnimage);
       
       /* CKeditor editor = new CKeditor();
    editor.initLater();
    
 C.add(editor);*/
       
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
       
       
        addComponent(C);

       
        
        btnajout.addActionListener(e->{
              ServiceArticle ser = new ServiceArticle();
           UploadImage.imageupload(imagecode, tnom.getText());
          /* String desc= editor.getData();
        
                 //imagecode = Base64.encode(desc);
           desc=replaceAll(desc,"<", "%3C");
            desc=replaceAll(desc,">", "%3E");
            desc=replaceAll(desc,"/", "%2F");*/
          //  desc=replaceAll(desc,"\\", "%5C");
           //String desc1=desc.replaceAll(">", "\\u003E");
            Article p=new Article(tnom.getText(),tnom.getText()+".jpg",tdesc.getText(),ttags.getText());
            ser.ajoutTask(p);
             ServiceArticle serviceTask=new ServiceArticle();
          //  WalkthruPubForm.listTasks = serviceTask.getList2();
            ArticleForm pf = new ArticleForm();
            pf.show();
        });
      
      

        
       // initGuiBuilderComponents(resourceObjectInstance);
     
    
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_KEYBOARD_BACKSPACE, e -> {
             ArticleForm h=new ArticleForm();
                                h.show();
        });
        
       

      
        
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
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
         ServicePublicite serviceTask=new ServicePublicite();
         ArrayList<Publicite> listTasks = serviceTask.getList2();
        for(Publicite pub:listTasks){
            
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
      
           gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 200), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, pub.getImg_pub(),
                                    "http://192.168.1.4/planners/web/"+pub.getImg_pub());
                            imgg1.fetch();
                           // Image bla = imgg1.getImage("bla.jpg");
                            ImageViewer imgv1 = new ImageViewer(imgg1);
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       
        gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
        
         FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);
        /****/
          Button bb = new Button();
                        gui_imageContainer1.setLeadComponent(bb);

                   bb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            InboxForm a = new InboxForm();
                            Container C = new Container();
                      
                            C.add(pub.getTitre_pub());
                        
                            a.add(C);
                            a.show();
                            
                        }
                   });
       
        
        
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
        
          }
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
