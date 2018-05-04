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
package GUI.Hebergement;

import GUI.*;
import com.codename1.components.ImageViewer;
import com.codename1.uikit.pheonixui.*;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class HebergementDetails extends BaseForm {

    public HebergementDetails() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public HebergementDetails(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);
        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer2.add(BorderLayout.CENTER, sl);
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
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
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
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
            com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
            com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
            com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
            
           
        
            
            
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
