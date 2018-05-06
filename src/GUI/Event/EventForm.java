package GUI.Event;

import Entity.Evenement;
import Services.ServiceEvent;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Iterator;


public class EventForm extends BaseForm {
    public static int id_user = 1;
    public EventForm() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    Container block1 = new Container(new BorderLayout());
    Form f = new Form();
    public EventForm(Resources resourceObjectInstance) throws IOException {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Evenements");
        setName("EvenementForm");
        //initGuiBuilderComponents(resourceObjectInstance);
        
        ServiceEvent event = new ServiceEvent();
        for (Iterator<Evenement> iterator = event.getList2().iterator(); iterator.hasNext();) {
            Evenement prodnext = iterator.next();
            //initGuiBuilderComponents(resourceObjectInstance);
            blockEvent(resourceObjectInstance, prodnext);
        }
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            new NewEventForm().show();
        });         
    }
    
    public void blockEvent(Resources resourceObjectInstance,Evenement p) throws IOException {
        
        Container gui_Container_1 = new Container(new BorderLayout());
        MultiButton gui_Multi_Button_1 = new MultiButton();
        MultiButton gui_LA = new MultiButton();
        Container gui_imageContainer1 = new Container(new BorderLayout());
        Container gui_Container_2 = new Container(new BorderLayout());
        TextArea gui_Text_Area_1 = new TextArea();
        Button gui_detail_event = new Button();
        Container gui_imageContainer2 = new Container(new BorderLayout());
        Label gui_Label_1_1_1 = new Label();
        Label gui_separator1 = new Label();
        MultiButton gui_newYork = new MultiButton();
    
        add(gui_Container_1);
        
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setPropertyValue("line1", p.getTitre());
        gui_Multi_Button_1.setPropertyValue("line2", "");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        /*
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dd = formater.format(p.getDate_event());
*/
        //System.out.println(p.getDate_event());
        gui_LA.setPropertyValue("line1", "Le "+p.getDate_event());
        gui_LA.setPropertyValue("line2", "à "+p.getVille());
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(BorderLayout.EAST, gui_detail_event);
        gui_Text_Area_1.setText(p.getDescription());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_detail_event.setText("");
        gui_detail_event.setUIID("Label");
        gui_detail_event.setName("Button_1");
        FontImage.setMaterialIcon(gui_detail_event,"".charAt(0));
        gui_detail_event.addActionListener((e) -> {
            new DetailEventForm(Resources.getGlobalResources(),p.getId_event()).show();
        });
        gui_Container_2.setName("Container_2");
        addComponent(gui_separator1);
        
        addComponent(gui_Label_1_1_1);
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setName("imageContainer1");
        gui_separator1.setUIID("Separator");
        gui_separator1.setName("separator1");
        
        gui_imageContainer2.setName("imageContainer2");
        gui_Label_1_1_1.setUIID("Separator");
        gui_Label_1_1_1.setName("Label_1_1_1");
        
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        if (p.getImage().toString() != "") {
            EncodedImage img1 = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), 180), true);
            URLImage imgg1 = URLImage.createToStorage(img1, p.getImage(), "http://localhost/planners/web/imageEvent/" + p.getImage());
            
            ImageViewer imgv1 = new ImageViewer(imgg1);
            gui_imageContainer1.add(BorderLayout.CENTER, imgv1);
        } else {
            ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            gui_imageContainer1.add(BorderLayout.CENTER, sl);
        }
        
        installSidemenu(resourceObjectInstance);
        // bouton ajouter
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
            try {
                // lien pour la page d'jout
                new MyEventForm(Resources.getGlobalResources(),id_user).show();
            } catch (IOException ex) {
            }
        });
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);
        gui_LA.addActionListener((e) -> {
            System.out.println(p.getVille());
           
            new MapEventForm(resourceObjectInstance, p.getVille()).show();
            
        });

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }
    
    
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
    //ahmed was here
}