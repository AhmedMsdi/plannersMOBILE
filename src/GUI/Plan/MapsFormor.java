/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Plan;
 

import GUI.AccueilForm;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer; 
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.io.NetworkEvent;
import com.codename1.maps.Coord;
import com.codename1.ui.ComponentSelector;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeArray;
import static com.codename1.ui.ComponentSelector.$;

/**
 *
 * @author sana
 */
public class MapsFormor {

    Form hi; 
   
    private Form current;
    private Resources theme;

     public static  int   idplan;
      public static  double   latitude;
       public static  double   longitude;
    public MapsFormor() { 
   
        try {
              theme = UIManager.initFirstTheme("/theme_1");
        if(hi != null){
            hi.show();
            return;
        }
        hi = new Form("Localisation", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        hi.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                                    AccueilForm pf = new AccueilForm();
                                    pf.show();
                                      });
        final Label apple = new Label(theme.getImage("apple-icon.png")); 
        final Label android = new Label(theme.getImage("android-icon.png")); 
        final Label windows = new Label(theme.getImage("windows-icon.png")); 
        Button getStarted = new Button("Let's Get Started!");
        FontImage.setMaterialIcon(getStarted, FontImage.MATERIAL_LINK);
        
         
        
        getStarted.setUIID("GetStarted");
      
         MapContainer cnt = new MapContainer("AIzaSyBfp1K5cF9bUzYNBQaNqo94Erww5jNHMc8"); 
                  float zoom = cnt.getMaxZoom();
             cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
           cnt.  revalidate();
        Style s = new Style();
       // s.setFgColor(0xff0000);  
        s.setFgColor(0x007700);
        s.setBgTransparency(0);
        //FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_RESTAURANT, s, Display.getInstance().convertToPixels(1));
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(new Float(0.9)));
  

        Button btnAddMarker = new Button("Add Marker");
  
        btnAddMarker. addActionListener(e->{

             cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
            cnt.addMarker(
                    EncodedImage.createFromImage (markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );

        });
 
        Button btnClearAll = new Button("Clear All");
        btnClearAll.addActionListener(e->{
            cnt.clearMapLayers();
        }); 
        
        
        cnt.addTapListener(e->{
      /*
            ArrayList<Coord> data = new ArrayList<>();
         Coord a= new Coord(latitude, longitude);  
         data.add(a);    
           int pdd=idplan;
           */
      //  for(Coord t : data) {
                  cnt.addMarker(
                    EncodedImage.createFromImage (markerImg, false),
                        new Coord(latitude ,  longitude),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
                  cnt.revalidate();
        //} 
        });
       Button showNextForm = $(new Button("Next Form"))
                .addActionListener(e->{
                    Form form = new Form("Hello World");
                    Button b1 = $(new Button("B1"))
                            .addActionListener(e2->{
                                ToastBar.showMessage("B1 was pressed", FontImage.MATERIAL_3D_ROTATION);
                            })
                            .asComponent(Button.class);
                    
                    Button back = $(new Button("Back"))
                            .addActionListener(e2->{
                                hi.showBack();
                            })
                            .asComponent(Button.class);
                    form.add(b1);
                })
                .asComponent(Button.class);
        
            FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_ACCESS_ALARM);
        nextForm.addActionListener(e->{
            Form form = new Form("Hello World");
               Button b1 = $(new Button("B1"))
                    .addActionListener(e2->{
                        ToastBar.showMessage("B1 was pressed", FontImage.MATERIAL_3D_ROTATION);
                    })
                    .asComponent(Button.class);

            Button back = $(new Button("Back"))
                    .addActionListener(e2->{
                        hi.showBack();
                    })
                    .asComponent(Button.class);
            form.add(b1).add(back);
            form.show();
        });
        /*
             Container root = LayeredLayout.encloseIn(
                BorderLayout.center(nextForm.bindFabToContainer(cnt)),
                BorderLayout.south(
                        FlowLayout.encloseBottom( btnAddMarker,  btnClearAll)
                )
        );
   */
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom( btnAddMarker,  btnClearAll)
                )
        );
        
        hi.add(BorderLayout.CENTER, root);
        hi.show();
        
         }
        
         catch (Exception e) 
        {
             ToastBar.showMessage(e.getMessage(), FontImage.MATERIAL_PLACE);
            
        }
    }
      public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = hi;
    }

    }

   
 