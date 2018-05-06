/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Plan;
 

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
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author sana
 */
public class MapsForm1 {

    Form hi; 
   
    private Form current;
    private Resources theme;

     
    public MapsForm1() { 
   
        try {
      theme = UIManager.initFirstTheme("/theme");
        if(hi != null){
            hi.show();
            return;
        }
          hi = new Form("Welcome", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        final Label apple = new Label(theme.getImage("apple-icon.png")); 
        final Label android = new Label(theme.getImage("android-icon.png")); 
        final Label windows = new Label(theme.getImage("windows-icon.png")); 
        Button getStarted = new Button("Let's Get Started!");
        FontImage.setMaterialIcon(getStarted, FontImage.MATERIAL_LINK);
        
         
        
        getStarted.setUIID("GetStarted");
      
         MapContainer cnt = new MapContainer("AIzaSyBfp1K5cF9bUzYNBQaNqo94Erww5jNHMc8"); 
        Button btnMoveCamera = new Button("Move Camera");
      
        Style s = new Style();
       // s.setFgColor(0xff0000);
          s.setFgColor(0x007700);
        s.setBgTransparency(0); 
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s,5);
   ArrayList<Coord> data = new ArrayList<>();
         Coord a= new Coord(36.880805, 10.201290); 
         Coord b= new Coord(-33.866, 151.195);
          Coord c= new Coord(30.59889,10.659998);
         data.add(a);   data.add(b);   data.add(c); 
                       //  cnt.getCoordAtPosition((int)t.getLatitude() ,(int) t.getLongitude()),
          cnt.clearMapLayers();
        for(Coord t : data) {
             
                  cnt.addMarker(
                    EncodedImage.createFromImage (markerImg, false),
                        new Coord(t.getLatitude() ,  t.getLongitude()),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
        }
          btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(36.880805, 10.201290));
        });
        Button btnAddMarker = new Button("Add Marker");
        btnAddMarker.addActionListener(e->{

             cnt.setCameraPosition(new Coord(36.880805, 10.201290));
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
        cnt.setCameraPosition(new Coord(36.880805, 10.201290));
         
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

   
 