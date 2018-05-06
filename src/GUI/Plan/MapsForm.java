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
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author sana
 */
public class MapsForm {

    Form hi;

    private Form current;
    private Resources theme;
    private static final String HTML_API_KEY = "AIzaSyBfp1K5cF9bUzYNBQaNqo94Erww5jNHMc8";
    public static int idplan = 0;
    public static double latitude = 0;
    public static double longitude = 0;
    public static String libmarker;
    Coord dest = new Coord(latitude, longitude);
    Coord destint = new Coord(36.813280, 10.168338);
    boolean tapDisabled = false;

    public MapsForm() {
 

        try {
            theme = UIManager.initFirstTheme("/theme_1");
//            if (hi != null) {
//                hi.show();
//                return;
//            }
            hi = new Form("Localisation", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
          /*  hi.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                AccueilForm pf = new AccueilForm();
                pf.show();
            });*/
       Form last = Display.getInstance().getCurrent();
        hi.getToolbar().setBackCommand("", e -> last.show());
            MapContainer cnt = new MapContainer(HTML_API_KEY);
            cnt.setCameraPosition(dest);
            // int maxZoom = cnt.getMaxZoom(); 
            Style s = new Style();
            s.setFgColor(0xff0000);
            s.setBgTransparency(0);
            FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
            cnt.addMapListener(new MapListener() {

                @Override
                public void mapPositionUpdated(Component source, int zoom, Coord center) {
                    //System.out.println("Map position updated: zoom="+zoom+", Center="+center); 
                    // cnt.setCameraPosition(dest);
                    cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(latitude, longitude), libmarker, "",
                            e3 -> {
                                destint = new Coord(latitude, longitude);
                                //   ToastBar.showMessage("You clicked "+txt+" and pos :"+cnt.getCoordAtPosition(e.getX(), e.getY()), FontImage.MATERIAL_PLACE);
                            });
                    //   ToastBar.showMessage("Map position updated: zoom="+zoom+", Center="+center, FontImage.MATERIAL_ACCESS_TIME);
                }

            });

            //add marker
//            cnt.addLongPressListener(e -> {
//                System.out.println("Long press");
//                // ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
//                if (tapDisabled) {
//                    return;
//                }
//                tapDisabled = true;
//                TextField enterName = new TextField();
//                Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
//                InteractionDialog dlg = new InteractionDialog("Add Marker");
//                dlg.getContentPane().add(wrapper);
//                enterName.setDoneListener(e2 -> {
//                    String txt = enterName.getText();
//                    cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "",
//                            e3 -> {
//                                destint = new Coord(cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(), cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
//                                //   ToastBar.showMessage("You clicked "+txt+" and pos :"+cnt.getCoordAtPosition(e.getX(), e.getY()), FontImage.MATERIAL_PLACE);
//                            });
//                    dlg.dispose();
//                    tapDisabled = false;
//
//                });
//                dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
//                enterName.startEditingAsync();
//            });

            cnt.addTapListener(e -> {
                tapDisabled = false;
            });
            //iteneraire
            FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_LOCATION);
            nextForm.addActionListener(e -> {
                //path 
                double lat = 36.899193;
                double lng = 10.190011;
 
                //Check if location is turned on and your app is allowed to use it.
                if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
                    if (Display.getInstance().getLocationManager().isGPSEnabled()) {
                        InfiniteProgress ip = new InfiniteProgress();
                        final Dialog ipDlg = ip.showInifiniteBlocking();
                        //Cancel after 20 seconds
                        Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                        ipDlg.dispose();
                        if (loc != null) {
                            lat = loc.getLatitude();
                            lng = loc.getLongitude();
                            try {
                                Display.getInstance().sendSMS("0021622568886", "http://maps.google.com/?q=" + lat + "," + lng, false);
                            } catch (IOException ex) {
                                Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
                                ex.printStackTrace();
                            }
                        } else {
                            Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                        }
                    } else {
                        Dialog.show("GPS disabled", "AppName needs access to GPS. Please enable GPS", "Ok", null);
                    }
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog ipDlg = ip.showInifiniteBlocking();
                    //Cancel after 20 seconds
                    Location loc = LocationManager.getLocationManager().getCurrentLocationSync(20000);
                    ipDlg.dispose();
                    if (loc != null) {
                        lat = loc.getLatitude();
                        lng = loc.getLongitude();
                        try {
                            Display.getInstance().sendSMS("0021622568886", "http://maps.google.com/?q=" + lat + "," + lng, false);
                        } catch (IOException ex) {
                            Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
                            ex.printStackTrace();
                        }
                    } else {
                        Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
                    }
                }

                Coord src2 = new Coord(lat, lng);
                cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), dest, "", "", null);
                // String encoded = getRoutesEncoded(dest, destint);
                //if you wanna get it async ...
                 double latesp = 36.899193;
                double lngesp = 10.190011;     
                Coord sourceesprit = new Coord(latesp, lngesp);
                getRoutesEncodedAsync(sourceesprit, dest, new Callback() {
                    @Override
                    public void onError(Object sender, Throwable err, int errorCode, String errorMessage) {

                    }

                    @Override
                    public void onSucess(Object encoded) {
                        Style s = new Style();
                        s.setFgColor(0xff0000);
                        s.setBgTransparency(0);
                        Coord[] coords = decode((String) encoded);
                        cnt.addPath(coords);
                    }
                });
            });
            //nextForm.bindFabToContainer(cnt) //panTo, testCoordPositions,toggleTopMargin, btnMoveCamera, btnAddMarker,, btnClearAll 
            Container root = LayeredLayout.encloseIn(
                    BorderLayout.center(cnt),
                    BorderLayout.south(
                            FlowLayout.encloseBottom(nextForm)
                    )
            );

            hi.add(BorderLayout.CENTER, root);
            hi.show();

            /*   cnt.addTapListener(e -> {
                /*
            ArrayList<Coord> data = new ArrayList<>();
         Coord a= new Coord(latitude, longitude);  
         data.add(a);    
           int pdd=idplan;
               ///
                //  for(Coord t : data) {
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        new Coord(latitude, longitude),
                        "Hi marker",
                        "Optional long description",
                        evt -> {
                            ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                        }
                );
                cnt.revalidate();
                //} 
            });
             */
        } catch (Exception e) {
            ToastBar.showMessage(e.getMessage(), FontImage.MATERIAL_PLACE);

        }
    }

    public Form getF() {
        if (hi != null) {
            return hi;
        }
        return null;
    }

    public void setF(Form f) {
        this.hi = hi;
    }

    public Coord[] decode(final String encodedPath) {
        int len = encodedPath.length();
        final ArrayList<Coord> path = new ArrayList<Coord>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coord(lat * 1e-5, lng * 1e-5));
        }
        Coord[] p = new Coord[path.size()];
        for (int i = 0; i < path.size(); i++) {
            p[i] = path.get(i);
        }

        return p;
    }

    public String getRoutesEncoded(Coord src, Coord dest) {
        String ret = "";
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
            request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("routes") != null) {
                ArrayList routes = (ArrayList) response.get("routes");
                if (routes.size() > 0) {
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {
        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                String ret = "";
                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));
                if (response.get("routes") != null) {
                    ArrayList routes = (ArrayList) response.get("routes");
                    if (routes.size() > 0) {
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                    }
                }
                callback.onSucess(ret);
            }

        };
        request.addArgument("key", HTML_API_KEY);
        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);
    }
}
