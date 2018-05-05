/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.googlemaps.MapContainer;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Linab
 */
public class MapEventForm extends Form {
      private static final String HTML_API_KEY = "AIzaSyDUEfLiucpDK8tifF7sB0KcZZCqWQeNmKU";
      private String ville;
    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /*  public MapEventForm() {
    this(com.codename1.ui.util.Resources.getGlobalResources());
    }*/
 
    public  MapEventForm(com.codename1.ui.util.Resources resourceObjectInstance,String v) {
        setToolbar(new Toolbar());
        initGuiBuilderComponents(resourceObjectInstance);
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());
         Style s = new Style();
                 s.setFgColor(0xFF7F50);
                 s.setBgTransparency(0);
                 FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
                 MapContainer cn = new MapContainer(); 
                 System.out.println(v+"----");
               cn.zoom(getCoords(v), 18);
            
                cn.setCameraPosition(getCoords(v)); // since the image is iin the jar this is unlikely
                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords(v), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                this.add(cn);
    }
                         
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("Localisation");
        setName("LocalisationForm");
        
    }// </editor-fold>
    

    
    
}
