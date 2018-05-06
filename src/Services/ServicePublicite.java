/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import Entity.Publicite;
import GUI.Article.PubliciteForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.uikit.pheonixui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServicePublicite {

        
    public static String serverAhmed ="localhost";
    public static double[] values1 = new double[2];
    public static String[] values2 = new String[2];
   public static ArrayList<String> titres=new ArrayList<String>();  
   public static ArrayList<Double> clicks=new ArrayList<Double>(); 

    public void ajoutTask(Publicite ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+serverAhmed+"/planners/web/app_dev.php/pubjsonnew?text=" + ta.getTitre_pub()
                + "&description=" + ta.getDesc_pub() +"&siteweb="+ta.getSite_pub()+"&tags="+ta.getTags()
                +"&image="+ta.getImg_pub()+"&idUser="+SignInForm.id_u;
        con.setUrl(Url);

        System.out.println("tt");
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public void clickPub(String ta) {
         int id = (int)Float.parseFloat(ta);
         
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+serverAhmed+"/planners/web/app_dev.php/click/"+ta;
        con.setUrl(Url);
        
   
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Publicite> getListTask(String json) {

        ArrayList<Publicite> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
                int i=0;
                titres.clear();
                clicks.clear();
            for (Map<String, Object> obj : list) {
                
                Publicite e = new Publicite();

                // System.out.println(obj.get("id"));
               // float id = Float.parseFloat(obj.get("idPub").toString());
               // System.out.println(id);
                e.setId_pub(obj.get("idPub").toString());
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setImg_pub(obj.get("image").toString());
                e.setTitre_pub(obj.get("text").toString());
                e.setSite_pub(obj.get("siteWeb").toString());
                e.setDesc_pub(obj.get("description").toString());
                //e.setNb_click();
              String user_id ="";
                user_id = obj.get("user").toString();
                String id_user=user_id.substring(user_id.indexOf("id=")+3, user_id.indexOf("prenom")-2);
              //  System.out.println("7achty   "+id_user);
             e.setId_u(id_user);
                clicks.add(Double.parseDouble(obj.get("nbClick").toString()));
             //  values1[i]=Double.parseDouble(obj.get("nbClick").toString());
              //  values2[i]=e.getTitre_pub();
              
                titres.add(e.getTitre_pub());
              
               i++;
                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
   ArrayList<Publicite> listTasks = new ArrayList<>();
    
    public ArrayList<Publicite> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+serverAhmed+"/planners/web/app_dev.php/pubjson");  
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  listTasks.clear();
                ServicePublicite ser = new ServicePublicite();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
     
    
    /*****************************************************************************/
    
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(30);
    renderer.setLegendTextSize(30);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
     * @param titres
     * @param clicks
     * @param values2
     * @param values1
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(ArrayList<String> titres, ArrayList<Double> clicks) {
    CategorySeries series = new CategorySeries("Projet");
    int i = 0;
    
    for (i=0;i<titres.size();i++) {
        series.add(titres.get(i), clicks.get(i));
        
    }

    return series;
}

public Form createPieChartForm() {
    // Generate the values
    double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY,ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY,ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY,ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY,ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY,ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN
            , ColorUtil.BLACK, ColorUtil.WHITE,ColorUtil.GRAY,ColorUtil.MAGENTA,ColorUtil.LTGRAY};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);
    

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset(titres, clicks), renderer);

    // Wrap the chart in a Component so we can add it to a form
    Container c1 = new Container();
    ChartComponent c = new ChartComponent(chart);
 c1.getStyle().setBgColor(0x99CCCC);
 c1.getStyle().setBgTransparency(255);
 c1.add(c);
    // Create a form and show it.
    Form f = new Form("Nombre de clicks", new BorderLayout());
   
    f.add(BorderLayout.CENTER, c1);
     f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_KEYBOARD_BACKSPACE, e -> {
             PubliciteForm h=new PubliciteForm();
                                h.show();
        });
    return f;

}

}
