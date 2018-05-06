/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Plan;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.list.DefaultListModel;
import Services.ServicePlans;
import Entity.Plan;
import Entity.categorie;
import Entity.sous_categorie;
import GUI.AccueilForm;
import static GUI.Plan.MapsForm.latitude;
import static GUI.Plan.MapsForm.libmarker;
import static GUI.Plan.MapsForm.longitude;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import rest.file.uploader.tn.FileUploader;
import com.codename1.ui.validation.Validator;
import com.codename1.uikit.pheonixui.SignInForm;
import javafx.scene.control.Alert;

/**
 *
 * @author chaima
 */
public class AjouterPlanForm {

    public static int id_u;

    Form f;
    ComboBox Categorie, SousCategorie;
    TextField tlib;
    TextField tadress;
    TextField tville;
    TextField tlon, timg;
    TextField tlat;
    TextField tdes, tprix;
    Button btnajout, btnref;
    int i;
    private Image img;

    private String imgPath;
    ImageViewer iv;
    private EncodedImage enc;
    private ImageViewer iV;
    private FileUploader file;
    String fileNameInServer;
    private static final String HTML_API_KEY = "AIzaSyBfp1K5cF9bUzYNBQaNqo94Erww5jNHMc8";
    Coord dest = new Coord(36.813280, 10.168338);
    Coord destint = new Coord(36.813280, 10.168338);
    boolean tapDisabled = false;

    public static Plan plan = null;

    public AjouterPlanForm() {
        if (plan != null) {
            f = new Form("Modifier un Plan");
        } else {
            f = new Form("Ajouter un Plan");
        }
        Form last = Display.getInstance().getCurrent();
        f.getToolbar().setBackCommand("", e -> last.show());
        // userPicture=resourceObjectInstance.getImage("skate-park.jpg") ;

        Container C1 = new Container(BoxLayout.y());
        Container C2 = new Container(BoxLayout.y());
        Container C3 = new Container(BoxLayout.y());

        Container Cmap = new Container(BoxLayout.y());
        Container Cimg = new Container(BoxLayout.y());
        ServicePlans sp = new ServicePlans();
//        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
//            PlanFormProfil pf = new PlanFormProfil();
//            pf.show();
//        });

        List<categorie> ls = new ArrayList<categorie>();
        ls = sp.getListC();
        Categorie = new ComboBox();
        SousCategorie = new ComboBox();
        DefaultListModel dd = new DefaultListModel(ls);
        Categorie.setModel(dd);

        SousCategorie.addActionListener((evt) -> {
            List<sous_categorie> sc = new ArrayList<sous_categorie>();

            sous_categorie selectedSCategorie = (sous_categorie) SousCategorie.getSelectedItem();
            i = selectedSCategorie.getId_sc();

        });

        Categorie.addActionListener((evt) -> {

            categorie selectedCategorie = (categorie) Categorie.getSelectedItem();

            List<sous_categorie> sc = new ArrayList<sous_categorie>();
            sc = sp.getListSC(selectedCategorie.getId_c());
//           sc = sp.getListSC(selectedSCategorie.getId_sc());
            // selectedSCategorie.getId_sc() ;  
            DefaultListModel scc = new DefaultListModel(sc);
            SousCategorie.setModel(scc);
        });

        // Categorie.setModel( (ls.toArray()));
        // Categorie.addItem(ls);
        // imagelink = new Button("Inserer image");
        tlib = new TextField("", "libelle");
        tadress = new TextField("", "adresse");
        tville = new TextField("", "ville");
        tdes = new TextField("", "description");

        tprix = new TextField("", "prix");
        tprix.setConstraint(TextArea.NUMERIC);
        //      tprix.setConstraint(TextArea.NUMERIC);
        tlon = new TextField("", "longitude");
        tlat = new TextField("", "latitude");
        timg = new TextField("", "image");

        btnajout = new Button("Ajouter");
        btnref = new Button("Refresh");

        if (plan != null) {
            try { 
            //    plan.setId_c(2);plan.setId_sc(12);
            btnajout.setText("Modifier");
            SousCategorie = new ComboBox();
            List<sous_categorie> sc = new ArrayList<sous_categorie>();
            sc = sp.getListSC(plan.getId_c());
            DefaultListModel scc = new DefaultListModel(sc);
            SousCategorie.setModel(scc);
            categorie cc = new categorie();
            sous_categorie ss = new sous_categorie();
            for (categorie c : ls) {
                if (c.getId_c() == plan.getId_c()) {
                    cc = c;
                }
            }
            for (sous_categorie es : sc) {
                if (es.getId_sc() == plan.getId_sc()) {
                    ss = es;
                }
            }
            Categorie.setSelectedItem(cc);
            SousCategorie.setSelectedItem(ss);
            //Categorie.setSelectedIndex(plan.getId_c());
            // SousCategorie.setSelectedIndex(plan.getId_sc());
            tlib.setText(plan.getLibelle());
            tadress.setText(plan.getAdresse());
            tville.setText(plan.getVille());
            tprix.setText(plan.getPrix());
            tdes.setText(plan.getDescription());
            timg.setText(plan.getImg());
            tlat.setText(plan.getLatitude() + "");
            tlon.setText(plan.getLongitude() + "");
            EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 300), true);
                            Image imgg1 = URLImage.createToStorage(img1, plan.getImg(), "http://localhost/planners/web/uploads/ImagesPlans/" + plan.getImg());
                         
                //  Image img = Image.createImage("http://localhost/planners/web/uploads/ImagesPlans/"+plan.getImg());
                imgg1.fill(50, 50); 
                ScaleImageLabel sl = new ScaleImageLabel(imgg1);
                sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                Style boxStyle2 = sl.getUnselectedStyle();
                boxStyle2.setBgTransparency(255);
                boxStyle2.setBgColor(0xeeeeee);
                boxStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
                boxStyle2.setPaddingUnit(Style.UNIT_TYPE_DIPS);
                boxStyle2.setPadding(2, 2, 2, 2);
                Cimg.add(sl);
                
            } catch (Exception e) {
            }
        }

        C1.add(Categorie);
        C1.add(SousCategorie);
        C1.add(tlib);
        C1.add(tadress);
        C1.add(tville);
        C1.add(tdes);
        C1.add(tprix);

        C1.add(tlon);
        C1.add(tlat);

        f.add(C1);
        f.add(Cmap);
        f.add(Cimg);
        f.add(C2);
        f.add(C3);

        //Map
        MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setSize(new Dimension(40, 20));
        cnt.setCameraPosition(dest);
        // int maxZoom = cnt.getMaxZoom(); 
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        if (plan != null) {
            cnt.addMapListener(new MapListener() {

                @Override
                public void mapPositionUpdated(Component source, int zoom, Coord center) {
                    //System.out.println("Map position updated: zoom="+zoom+", Center="+center); 
                    // cnt.setCameraPosition(dest);
                    Coord pt = new Coord(plan.getLatitude(), plan.getLongitude());
                    cnt.addMarker(EncodedImage.createFromImage(markerImg, false), pt, plan.getLibelle(), "",
                            e3 -> {
                                destint = pt;
                            });
                }

            });
        }
        cnt.addLongPressListener(e -> {
            System.out.println("Long press");
            // ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
            if (tapDisabled) {
                return;
            }
            tapDisabled = true;
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2 -> {
                String txt = enterName.getText();
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "",
                        e3 -> {
                            destint = new Coord(cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(), cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                            //   ToastBar.showMessage("You clicked "+txt+" and pos :"+cnt.getCoordAtPosition(e.getX(), e.getY()), FontImage.MATERIAL_PLACE);
                        });
                tlon.setText(cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude() + "");
                tlat.setText(cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude() + "");
                dlg.dispose();
                tapDisabled = false;

            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });
        cnt.addTapListener(e -> {
            cnt.setCameraPosition(dest);
        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom()
                )
        );
        root.setHeight(10);
        root.setWidth(10);
        Style boxStyle = root.getUnselectedStyle();
        boxStyle.setBgTransparency(255);
        boxStyle.setBgColor(0xeeeeee);
        boxStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        boxStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        // boxStyle.setMargin(4, 3, 3, 3);
        boxStyle.setPadding(2, 2, 2, 2);

        Cmap.setHeight(10);
        Cmap.setWidth(10); 
        Cmap.add(root);

        C2.add(timg);

        FloatingActionButton imagelink = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_A_PHOTO);
        imagelink.addActionListener(e -> {
            try {
                imgPath = Capture.capturePhoto();
                timg.setText(imgPath);
                // Image img = Image.createImage(imgPath);
                Image img = Image.createImage(imgPath);
                img.fill(50, 50); 
                ScaleImageLabel sl = new ScaleImageLabel(img);
                sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                Style boxStyle2 = sl.getUnselectedStyle();
                boxStyle2.setBgTransparency(255);
                boxStyle2.setBgColor(0xeeeeee);
                boxStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
                boxStyle2.setPaddingUnit(Style.UNIT_TYPE_DIPS);
                boxStyle2.setPadding(2, 2, 2, 2);
                Cimg.add(sl);
            } catch (Exception es) {
                ToastBar.showMessage(es.getMessage(), FontImage.MATERIAL_PLACE);
            }
        });

        C2.add(imagelink);

        C2.add(btnajout);
        C3.add(btnref);

        btnajout.addActionListener((evt) -> {
            try {

                if (tlib.getText() == "") {
                    ToastBar.showMessage("Libelle obligatoire !!!", FontImage.MATERIAL_ERROR);
                    return;
                }
                sous_categorie selectedSCategorie = (sous_categorie) SousCategorie.getSelectedItem();
                if (selectedSCategorie == null) {
                    ToastBar.showMessage("sous_categorie obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                }
                if (tadress.getText().isEmpty()) {
                    ToastBar.showMessage("Adresse obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                }
                String prixx = "";
                if (tprix.getText().isEmpty()) {
                    ToastBar.showMessage("Prix obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                } else {

                    String fileNameserver = tprix.getText();
                    String fruits = fileNameserver;
                    String delimiter = "D";
                    StringTokenizer fruitsTokenizer = new StringTokenizer(fruits, delimiter);
                    while (fruitsTokenizer.hasMoreTokens()) {
                        prixx = fruitsTokenizer.nextToken();
                        break;
                    }

                }
                if (tlon.getText().isEmpty() || validatedouble(tlon.getText())) {
                    ToastBar.showMessage("Longitude obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                }
                if (tlat.getText().isEmpty() || validatedouble(tlat.getText())) {
                    ToastBar.showMessage("Latitude obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                }
                String fruit = "";
                if (imgPath != null) {
                    if (!imgPath.isEmpty()) {
                        String nomimage = "";
                        String link = imgPath.toString();
                        int pod = link.indexOf("/", 2);
                        String news = link.substring(pod + 2, link.length());
                        FileUploader fu = new FileUploader("http://localhost/planners/web/uploads/");

                        fileNameInServer = fu.upload(news);
                        String fileNameserver = fileNameInServer.toString();
                        String fruits = fileNameserver;
                        String delimiter = "?";
                        StringTokenizer fruitsTokenizer = new StringTokenizer(fruits, delimiter);
                        while (fruitsTokenizer.hasMoreTokens()) {
                            fruit = fruitsTokenizer.nextToken();
                            break;
                        }
                    }
                }
                if (timg.getText().isEmpty()) {
                    ToastBar.showMessage("Image obligatoire !!!", FontImage.MATERIAL_ERROR);

                    return;
                }
                int id_p = 0;
                if (plan != null) {
                    id_p = plan.getId_p();
                }
                Plan p = new Plan(id_p, 0, tlib.getText(), tadress.getText(), tdes.getText(), tville.getText(), prixx, SignInForm.id_u, selectedSCategorie.getId_sc(), Double.parseDouble(tlon.getText()), Double.parseDouble(tlat.getText()), fruit);
                // Plan p = new Plan(tlib.getText(), tadress.getText(), tdes.getText(), tville.getText(), tprix.getText(), selectedSCategorie.getId_sc(), Double.parseDouble(tlon.getText()), Double.parseDouble(tlat.getText()), fruit);
                if (plan != null) {
                    sp.ModifPlan(p);
                    ToastBar.showMessage("Modif Plan avec succee !!!", FontImage.MATERIAL_STAR);
                } else {
                    int retins = sp.ajoutPlan(p);
                    if (retins != -1) {
                        ToastBar.showMessage("Ajout Plan avec succee !!!", FontImage.MATERIAL_STAR);
                        PlanFormProfil pf = new PlanFormProfil();
                        pf.show();
                    } else {
                        ToastBar.showMessage("Probleme Ajout Plan !!!", FontImage.MATERIAL_ERROR);
                    }
                }

            } catch (Exception ex) {
                ToastBar.showMessage(ex.getMessage().toString(), FontImage.MATERIAL_ERROR);
            }

        });
        btnref.addActionListener((evt) -> {
            OnclikReset();
        });
        
   
    }

    
        
                       // gui_Container_2.add(modif);
    private boolean validatedouble(String text) {
        try {
            Double value = Double.parseDouble(text);
            return value.isNaN();
        } catch (Exception e) {
            return true;
        }

    }
    private Image userPicture;

    private Image captureRoundImage() {
        try {
            int width = userPicture.getWidth();
            String result = "";
            //Capture.capturePhoto(width, -1);
            if (result == null) {
                return userPicture;
            }
            Image capturedImage = Image.createImage(result);
            if (capturedImage.getHeight() != width) {
                if (capturedImage.getWidth() < capturedImage.getHeight()) {
                    capturedImage = capturedImage.subImage(0, capturedImage.getHeight() / 2 - width / 2, width, width, false);
                } else {
                    Image n = Image.createImage(width, width);
                    n.getGraphics().drawImage(capturedImage, 0, width / 2 - capturedImage.getHeight() / 2);
                    capturedImage = n;
                }
            }
            return roundImage(capturedImage);
        } catch (IOException err) {
            err.printStackTrace();
            return userPicture;
        }
    }

    private Image roundImage(Image img) {
        int width = img.getWidth();
        Image roundMask = Image.createImage(width, width, 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        img = img.applyMask(mask);
        return img;
    }

    private void OnclikReset() {

        try {
            tlib.setText("");
            tadress.setText("");
            tville.setText("");
            tprix.setText("");
            timg.setText("");
            tlat.setText("");
            tlon.setText("");
            tadress.setText("");

        } catch (Exception e) {
            //   AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
