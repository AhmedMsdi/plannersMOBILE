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

import Entity.Publicite;
import Services.ServicePublicite;
import com.codename1.components.ImageViewer;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import java.util.Random;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class WalkthruPubForm extends com.codename1.ui.Form {

   public static ArrayList<Publicite> listTasks;
    
    public WalkthruPubForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    class BgPainter implements Painter {
        private Container parent;
        private Image pic;
        private int textHeight;
        
        public BgPainter(Container parent, Image pic, int textHeight) {
            this.parent = parent;
            this.pic = pic;
            this.textHeight = textHeight;
        }

        
        @Override
        public void paint(Graphics g, Rectangle rect) {
            int mm1 = Display.getInstance().convertToPixels(2);
                
            GeneralPath gp = new GeneralPath();
            float x = parent.getX() + mm1;
            float radius = mm1;
            float y = parent.getY() + mm1;
            float widthF = parent.getWidth() - (2 * mm1);
            float heightF = parent.getHeight()- (2 * mm1);
            gp.moveTo(x + radius, y);
            gp.lineTo(x + widthF - radius, y);
            gp.quadTo(x + widthF, y, x + widthF, y + radius);
            gp.lineTo(x + widthF, y + heightF - radius);
            gp.quadTo(x + widthF, y + heightF, x + widthF - radius, y + heightF);
            gp.lineTo(x + radius, y + heightF);
            gp.quadTo(x, y + heightF, x, y + heightF - radius);
            gp.lineTo(x, y + radius);
            gp.quadTo(x, y, x + radius, y);
            gp.closePath();            

            g.setColor(0xffffff);
            g.setAntiAliased(true);
            int [] clip = g.getClip();
            if(g.isShapeClipSupported()) {
                g.setClip(gp);
            } else {
                // we won't have a round rect but at least we will respect its bounds
                Rectangle r = gp.getBounds();
                g.setClip(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            }
            int pw = pic.getWidth();
            float ratio = ((float)pw) / ((float)pic.getHeight());
            int width = parent.getWidth();
            float height = ((float)width) * ratio;
            int hh = (mm1 * 2) + textHeight;
            if(height < parent.getHeight() - hh) {
                hh = (int)(parent.getHeight() - height);
            }
            
            g.drawImage(pic, parent.getX(), parent.getY(), width, (int)height);
            g.setColor(0xffffff);
            g.setAlpha(255);
            g.fillRect(parent.getX(), parent.getY() + parent.getHeight() - hh, parent.getWidth(), hh);
            g.fillTriangle(parent.getX(), parent.getY() + parent.getHeight() - hh, 
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh,
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh - (mm1 * 2));
            g.setClip(clip);
        }
        
    }
     static String titre1="";
     static String desc1="";
     static String site1="";
     
          static String titre2="";
     static String desc2="";
     static String site2="";
     
          static String titre3="";
     static String desc3="";
     static String site3="";
     int r,r1,r2;
    public WalkthruPubForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        
              ServicePublicite serviceTask=new ServicePublicite();
        listTasks = serviceTask.getList2();
         r =new Random().nextInt(listTasks.size());
        
         r1 =new Random().nextInt(listTasks.size());
         while(r1==r){
            r1 =new Random().nextInt(listTasks.size());
         }
          r2 =new Random().nextInt(listTasks.size());
          while(r2==r || r2==r1){
            r1 =new Random().nextInt(listTasks.size());
         }
          
         titre1=listTasks.get(r).getTitre_pub();
         desc1=listTasks.get(r).getDesc_pub();
        site1=listTasks.get(r).getSite_pub();
      
         titre2=listTasks.get(r1).getTitre_pub();
         desc2=listTasks.get(r1).getDesc_pub();
        site2=listTasks.get(r1).getSite_pub();
        
         titre3=listTasks.get(r2).getTitre_pub();
         desc3=listTasks.get(r2).getDesc_pub();
        site3=listTasks.get(r2).getSite_pub();
         
       
         EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, listTasks.get(r).getImg_pub(),
                                    "http://localhost/planners/web/"+listTasks.get(r).getImg_pub());
                            imgg1.fetch();
                            
                              EncodedImage img2 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg2 = URLImage.createToStorage(img2, listTasks.get(r1).getImg_pub(),
                                    "http://localhost/planners/web/"+listTasks.get(r1).getImg_pub());
                            imgg2.fetch();
                            
                              EncodedImage img3 = EncodedImage.createFromImage(Image.createImage(1000, 600), true);
                            URLImage imgg3 = URLImage.createToStorage(img3, listTasks.get(r2).getImg_pub(),
                                    "http://localhost/planners/web/"+listTasks.get(r2).getImg_pub());
                            imgg3.fetch();
                 
         
        ButtonGroup bg = new ButtonGroup();
        gui_tab1.setToggle(true);
        gui_tab1.setUIID("Label");
        bg.add(gui_tab1);
        gui_tab2.setToggle(true);
        gui_tab2.setUIID("Label");
        bg.add(gui_tab2);
        gui_tab3.setToggle(true);
        gui_tab3.setUIID("Label");
        bg.add(gui_tab3);
        gui_tab1.setSelected(true);
        
        Image bla = resourceObjectInstance.getImage("bla.jpg");
        Image griffith = resourceObjectInstance.getImage("griffith.jpg");
        Image learn = resourceObjectInstance.getImage("learn-codename-one.jpg");
        
        gui_tab1Root.setLayout(new BorderLayout());
        gui_tab2Root.setLayout(new BorderLayout());
        gui_tab3Root.setLayout(new BorderLayout());
        
        String line1 =  desc1;
        if(!Display.getInstance().isTablet()) {
            line1 = line1.replace('\n', ' ');
        }

        String line2 =  desc2;
        if(!Display.getInstance().isTablet()) {
            line2 = line2.replace('\n', ' ');
        }

        String line3 =  desc3;
        if(!Display.getInstance().isTablet()) {
            line3 = line3.replace('\n', ' ');
        }
        
        Container content1 = BoxLayout.encloseY(
                new Label(titre1, "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line1, "WelcomeBody")
        );

        Container content2 = BoxLayout.encloseY(
                new Label(titre2, "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line2, "WelcomeBody")
        );

        Container content3 = BoxLayout.encloseY(
                new Label(titre3, "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line3, "WelcomeBody")
        );

        content1.setUIID("WelcomeContent");
        content2.setUIID("WelcomeContent");
        content3.setUIID("WelcomeContent");

        gui_tab1Root.add(BorderLayout.SOUTH, content1);
        gui_tab2Root.add(BorderLayout.SOUTH, content2);
        gui_tab3Root.add(BorderLayout.SOUTH, content3);

        gui_tab1Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab1Root, imgg1, content1.getPreferredH() +
                content1.getUnselectedStyle().getPaddingTop() + 
                content1.getUnselectedStyle().getPaddingBottom() + 
                content1.getUnselectedStyle().getMarginTop() + 
                content1.getUnselectedStyle().getMarginBottom()));

        gui_tab2Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab2Root, imgg2, content2.getPreferredH() +
                content2.getUnselectedStyle().getPaddingTop() + 
                content2.getUnselectedStyle().getPaddingBottom() + 
                content2.getUnselectedStyle().getMarginTop() + 
                content2.getUnselectedStyle().getMarginBottom()));

        gui_tab3Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab3Root, imgg3, content3.getPreferredH() +
                content3.getUnselectedStyle().getPaddingTop() + 
                content3.getUnselectedStyle().getPaddingBottom() + 
                content3.getUnselectedStyle().getMarginTop() + 
                content3.getUnselectedStyle().getMarginBottom()));
        Button t1 = new Button();
        Button t2 = new Button();
        Button t3 = new Button();
     //  gui_tab1Root.add(BorderLayout.SOUTH, t1);
        content1.setLeadComponent(t1);
        t1.addActionListener(l->{
            serviceTask.clickPub(listTasks.get(r).getId_pub());
           Display.getInstance().execute(listTasks.get(r).getSite_pub());   
           
        });
         content2.setLeadComponent(t2);
        t2.addActionListener(l->{
             serviceTask.clickPub(listTasks.get(r1).getId_pub());
 Display.getInstance().execute(listTasks.get(r1).getSite_pub());   
        });
        
         content3.setLeadComponent(t3);
        t3.addActionListener(l->{
            serviceTask.clickPub(listTasks.get(r2).getId_pub());
 Display.getInstance().execute(listTasks.get(r2).getSite_pub());   
        });
        
        
        gui_Tabs_1.addSelectionListener((i, ii) -> {
            switch(ii) {
                case 0:
                    gui_tab1.setSelected(true);
                    break;
                case 1:
                    gui_tab2.setSelected(true);
                    break;
                default:
                    gui_tab3.setSelected(true);
                    break;
            }
        });

        gui_Tabs_1.hideTabs();
        gui_Tabs_1.getContentPane().setUIID("Container");
        //gui_slide1Image
    }

    @Override
    protected Component createStatusBar() {
        Component c = super.createStatusBar();
        c.getUnselectedStyle().setPadding(0, 0, 0, 0);
        return c;
    }

    
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Tabs gui_Tabs_1 = new com.codename1.ui.Tabs();
    private com.codename1.ui.Container gui_tab1Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_tab2Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_tab3Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.RadioButton gui_tab1 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.RadioButton gui_tab2 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.RadioButton gui_tab3 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_tab3.addActionListener(callback);
        gui_Button_1.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_tab3) {
                ontab3ActionEvent(ev);
            }
            if(sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setUIID("Welcome");
        setTitle("");
        setName("WalkthruForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Tabs_1);
        gui_Tabs_1.setName("Tabs_1");
        gui_Tabs_1.addTab("Tab" ,gui_tab1Root);
        gui_Tabs_1.addTab("Tab" ,gui_tab2Root);
        gui_Tabs_1.addTab("Tab" ,gui_tab3Root);
        gui_tab1Root.setUIID("Card");
        gui_tab1Root.setName("tab1Root");
        gui_tab2Root.setName("tab2Root");
        gui_tab3Root.setName("tab3Root");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_4);
        gui_Container_4.setName("Container_4");
        gui_Container_4.addComponent(gui_Container_3);
        gui_Container_3.setName("Container_3");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_3.addComponent(gui_tab1);
        gui_Container_3.addComponent(gui_tab2);
        gui_Container_3.addComponent(gui_tab3);
        gui_tab1.setSelected(false);
        gui_tab1.setUIID("Label");
        gui_tab1.setName("tab1");
        gui_tab1.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab1.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_tab2.setUIID("Label");
        gui_tab2.setName("tab2");
        gui_tab2.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab2.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_tab3.setUIID("Label");
        gui_tab3.setName("tab3");
        gui_tab3.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab3.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_Container_4.addComponent(gui_Container_6);
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_6.addComponent(gui_Button_1);
        gui_Container_6.addComponent(gui_Label_1);
        gui_Button_1.setText("Get Started");
        gui_Button_1.setUIID("GetStartedButton");
        gui_Button_1.setName("Button_1");
        gui_Button_1.setTextPosition(com.codename1.ui.Component.LEFT);
        gui_Label_1.setUIID("GetStartedRedArrow");
        gui_Label_1.setName("Label_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Label_1,"".charAt(0));
        gui_Container_3.setName("Container_3");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Tabs_1.setName("Tabs_1");
        gui_Container_4.setName("Container_4");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void ontab3ActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

    public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new SignInForm().show();
    }

}
