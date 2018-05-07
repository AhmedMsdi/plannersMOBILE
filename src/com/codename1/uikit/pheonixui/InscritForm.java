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
package com.codename1.uikit.pheonixui;

import Entity.User;
import GUI.Article.PubliciteForm;
import Services.ServicePublicite;
import static Services.ServicePublicite.serverAhmed;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import static com.codename1.uikit.pheonixui.SignInForm.id_u;
import static com.codename1.uikit.pheonixui.SignInForm.points_fid;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class InscritForm extends com.codename1.ui.Form {

    public InscritForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }


    public InscritForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SignInForm().show());
        getContentPane().setUIID("SignInForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField("","Nom");
     private com.codename1.ui.TextField gui_Text_Field_22 = new com.codename1.ui.TextField("","Prenom");
      private com.codename1.ui.TextField gui_Text_Field_222 = new com.codename1.ui.TextField("","Email");
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField("","Mot de passe");
    private com.codename1.ui.TextField gui_Text_Field_11 = new com.codename1.ui.TextField("","Pseudo");
    private com.codename1.ui.TextField gui_Text_Field_111 = new com.codename1.ui.TextField("","");
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
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
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
            if (sourceComponent == gui_Button_1) {
               
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Inscription");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_22);
        gui_Component_Group_1.addComponent(gui_Text_Field_222);
        gui_Component_Group_1.addComponent(gui_Text_Field_11);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        
        //gui_Component_Group_1.addComponent(gui_Text_Field_111);
        
        gui_Text_Field_2.setText("");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setText("");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
       // gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Inscrire");
        gui_Button_2.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
       // addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Créer un compte");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        //new InboxForm().show();
        ConnectionRequest con = new ConnectionRequest();
        String name = gui_Text_Field_2.getText();
        String prename = gui_Text_Field_22.getText();
        String email = gui_Text_Field_222.getText();
        String pswd = gui_Text_Field_1.getText();
        String pseudo = gui_Text_Field_11.getText();
        
        if (name.trim() != "" && pswd.trim() != "" && prename.trim() != "" && email.trim() != ""
               && pseudo.trim() != "" ) {
            
            
        String Url = "http://"+serverAhmed+"/planners/web/app_dev.php/newuser?nom="+
                name+"&prenom="+prename+"&email="+email+"&username="+pseudo+"&password="+pswd;
        con.setUrl(Url);
         NetworkManager.getInstance().addToQueueAndWait(con);
            //String sejours = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
     //   id_u = Integer.parseInt(new String(con.getResponseData()));
     
      ConnectionRequest con1 = new ConnectionRequest();
       String Url1 = "http://"+serverAhmed+"/planners/web/app_dev.php/alluser";
      con1.setUrl(Url1);
         con1.addResponseListener((NetworkEvent evt) -> {
                ArrayList<User> listuser = (ArrayList<User>) getListUser(new String(con1.getResponseData()));
                if (!listuser.isEmpty()) {
                    for (User e : listuser) {

                       
                        /*  Curentuser c =new Curentuser();
                    c.setCurrent(users.get("username").toString());
                    c.setCurrentId(Integer.parseInt(users.get("id").toString()));
                         */
               
             
             //System.out.println("wilyeeeeeeeeeeeeeeeeeeeeeeeeeey pseudo"+pseudo);
                 if (e.getLogin().equals(pseudo) ) {
                       id_u = e.getId_u();
                      // System.out.println("wselna ya ahmed    "+id_u);
                        points_fid=e.getPoint_fidelite();
                        break;
                 }
                    }
                } else {
                    Dialog.show("Erreur d'authentification", "Verifier votre Nom d'utilisateur ou mot de passe!!", "OK", "Annuler");

                }
            });
               NetworkManager.getInstance().addToQueueAndWait(con1);          
       
       
       
            
          PubliciteForm AccueilF = new PubliciteForm();
                        AccueilF.show();
        } else {
            Dialog.show("Erreur d'authentification", "Veuillez saisir le Nom d'utilisateur ou mot de passe!!", "OK", "Annuler");
        }
    }

    public ArrayList<User> getListUser(String json) {
        ArrayList<User> listUser = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> sejours = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println();
            ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) sejours.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();
                // System.out.println("com.mycompany.myapp.MyApplication.getList()"+e);
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId_u((int) id);
                //  e.setDateDeSejour(obj.get("dateDeSejour").toString());
                if (obj.get("username") != null) {
                    e.setLogin(obj.get("username").toString());
                   // System.out.println("wilyeeeeeeeeeeeeeeeeeeeeeeeeeey entite"+e.getLogin());
                }
                if (obj.get("password") != null) {
                    e.setPassword(obj.get("password").toString());
                }

                if (obj.get("nom") != null) {
                    e.setNom(obj.get("nom").toString());
                }
                if (obj.get("prenom") != null) {
                    e.setPrenom(obj.get("prenom").toString());
                }

                float fidelite = Float.parseFloat(obj.get("pointFidelite").toString());
                e.setPoint_fidelite((int) fidelite);

                listUser.add(e);
            }
        } catch (IOException ex) {
        }
        return listUser;

    }
}

