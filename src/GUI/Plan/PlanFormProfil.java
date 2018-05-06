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
package GUI.Plan;

import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import Entity.Plan;
import Services.ServicePlans;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class PlanFormProfil extends BaseForm {

    FloatingActionButton modif, Supprimer;
    //Button butModif ; 

    public PlanFormProfil() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public PlanFormProfil(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {

        });

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
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
            ServicePlans ser = new ServicePlans();
            AjouterPlanForm.plan = null;
            AjouterPlanForm a = new AjouterPlanForm();
            a.getF().show();

        });
        //addComponent(fab);
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
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();
    Button butModif = new Button();
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        try {

            setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            setTitle("List Des Plans");
            setName("List Des Plans");
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1/planners/web/app_dev.php/getPlanUser/" + SignInForm.id_u);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServicePlans sc = new ServicePlans();
                    ArrayList<Plan> l = (ArrayList<Plan>) sc.getList(new String(con.getResponseData()));

                    for (Plan e : l) {

                        gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
                        gui_Multi_Button_1 = new com.codename1.components.MultiButton();
                        gui_LA = new com.codename1.components.MultiButton();
                        gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
                        gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
                        gui_Text_Area_1 = new com.codename1.ui.TextArea();
                        gui_Button_1 = new com.codename1.ui.Button();
                        gui_separator1 = new com.codename1.ui.Label();
                        gui_Button_3 = new com.codename1.ui.Button();
                        gui_Button_4 = new com.codename1.ui.Button();

                        gui_separator1.setShowEvenIfBlank(true);
                        installSidemenu(resourceObjectInstance);

                        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
                        gui_LA.setIconPosition(BorderLayout.EAST);

                        gui_Text_Area_1.setRows(2);
                        gui_Text_Area_1.setColumns(100);
                        gui_Text_Area_1.setGrowByContent(false);
                        gui_Text_Area_1.setEditable(false);
                        if (e.getImg() != null) {
                            EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 300), true);
                            URLImage imgg1 = URLImage.createToStorage(img1, e.getImg(), "http://localhost/planners/web/uploads/ImagesPlans/" + e.getImg());
                            // imgg1.fetch();
                            //   ImageViewer imgv1 = new ImageViewer(imgg1);

                            ScaleImageLabel sl = new ScaleImageLabel(imgg1);
                            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                            gui_imageContainer1.add(BorderLayout.CENTER, sl);

                            // gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
                        } else {
                            ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));

                            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                            gui_imageContainer1.add(BorderLayout.CENTER, sl);
                        }
                        addComponent(gui_Container_1);
                        gui_Container_1.setName("Container_1");
                        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
                        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
                        gui_Multi_Button_1.setUIID("Label");
                        gui_Multi_Button_1.setName("Multi_Button_1");
                        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
                        gui_Multi_Button_1.setPropertyValue("line1", e.getLibelle());
                        gui_Multi_Button_1.setPropertyValue("line2", e.getVille());
                        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
                        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
                        gui_LA.setUIID("Label");
                        gui_LA.setName("LA");
                        gui_LA.setPropertyValue("line1", e.getPrix());
                        gui_LA.setPropertyValue("line2", e.getAdresse());
                        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
                        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
                        addComponent(gui_imageContainer1);
                        gui_imageContainer1.setName("imageContainer1");
                        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
                        gui_Container_2.setName("Container_2");
                        //gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
                        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
                         gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Button_3);
                        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Button_4);
 
                        // gui_Text_Area_1.setText(e.getDescription());
                        // gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
                        // gui_Text_Area_1.setName("Text_Area_1");
                        /*  FloatingActionButton modif = FloatingActionButton.createFAB(FontImage.MATERIAL_EDIT);*/
                      //  gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, modif);
/*
                        modif.addPointerPressedListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                AjouterPlanForm.plan = e;
                                AjouterPlanForm a = new AjouterPlanForm();
                                //a.id_plan= e.getId_p(); 
                                a.getF().show();

                            }
                        });*/


 /*
                        FloatingActionButton Supprimer = FloatingActionButton.createFAB(FontImage.MATERIAL_REMOVE);
                        modif.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                ServicePlans ser = new ServicePlans();
                                Plan p = new Plan();
                                p.setId_p(e.getId_p());
                                ser.Delete(p);
                                PlanFormProfil a = new PlanFormProfil();
                                a.show();
                            }
                        });
                        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, Supprimer);
                         */
 
 
                        gui_Button_3.setText("Modifier");
                        gui_Button_3.setUIID("Label");
                        gui_Button_3.setName("Modifier");
                        gui_Button_4.getStyle().setFgColor(0x333399);
                        
                        gui_Button_3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                AjouterPlanForm.plan = e;
                                AjouterPlanForm a = new AjouterPlanForm();
                                //a.id_plan= e.getId_p(); 
                                a.getF().show();

                            }
                        });
                        gui_Button_4.setText("Supprimer");
                        gui_Button_4.setUIID("Labe2");
                        gui_Button_4.setName("Supprimer");
                        gui_Button_4.getStyle().setFgColor(0x333399);

                        gui_Button_4.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                ServicePlans ser = new ServicePlans();
                                Plan p = new Plan();
                                p.setId_p(e.getId_p());
                                ser.Delete(p);
                                PlanFormProfil a = new PlanFormProfil();
                                a.show();
                            }
                        });

                     /*   FloatingActionButton Suit = FloatingActionButton.createFAB(FontImage.MATERIAL_TRENDING_FLAT);
                        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, Suit);
                       */

                      //  gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
                
                  
                        gui_Button_1.setText("");
                        gui_Button_1.setUIID("Label");
                        gui_Button_1.setName("Button_1");

                       com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1, "".charAt(0));
                        gui_Container_2.setName("Container_2");
      gui_Button_1.setHeight(10);


                        gui_Button_1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Form consulter = new Form("BON & PLANS", BoxLayout.y());
                                // consulter.getStyle().setBgColor(0x99CCCC);
                               /* consulter.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                                    PlanFormProfil pf = new PlanFormProfil();
                                    pf.show();

                                });*/
                               
                                       Form last = Display.getInstance().getCurrent();
        consulter.getToolbar().setBackCommand("", e -> last.show());
                               
                                Container c = new Container(BoxLayout.y());
                                Container C = new Container(new GridLayout(2, 1));
                                consulter.add(c);
                                consulter.add(C);

                                Label sp = new Label();
                                Label tit = new Label("  Libelle : ");
                                Label Mes = new Label("  Description :");
                                Label vil = new Label("  Ville : ");
                                Label ad = new Label("  Adresse : ");

                                Label Libelle = new Label();
                                Label Adresse = new Label();
                                Label ville = new Label();
                                SpanLabel message = new SpanLabel();

                                EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 300), true);
                                URLImage imgg1 = URLImage.createToStorage(img1, e.getImg(), "http://localhost/planners/web/uploads/ImagesPlans/" + e.getImg());
                                imgg1.fetch();
                                ImageViewer imgv1 = new ImageViewer(imgg1);
                                c.add(sp);
                                c.add(tit);
                                c.add(Libelle);
                                c.add(vil);
                                c.add(ville);
                                c.add(ad);
                                c.add(Adresse);
                                c.add(Mes);
                                c.add(message);
                                c.add(imgv1);

                                Libelle.setText("  " + e.getLibelle());
                                ville.setText("  " + e.getVille());
                                Adresse.setText("  " + e.getAdresse());
                                message.setText("  " + e.getDescription());

                                consulter.show();
                                tit.getStyle().setFgColor(0xFF3366);
                                Mes.getStyle().setFgColor(0xFF3366);
                                vil.getStyle().setFgColor(0xFF3366);
                                ad.getStyle().setFgColor(0xFF3366);
                            }
                        });
                    }                  addComponent(gui_separator1);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        } catch (Exception e) {
            ToastBar.showMessage(e.getMessage(), FontImage.MATERIAL_PLACE);
        }
    }
// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
