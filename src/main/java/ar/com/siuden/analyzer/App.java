package ar.com.siuden.analyzer;

import ar.com.siuden.analyzer.domain.json.Informacion;
import ar.com.siuden.analyzer.domain.json.Nota;
import ar.com.siuden.analyzer.domain.json.Noticia;
import ar.com.siuden.analyzer.library.Funciones;
import ar.com.siuden.analyzer.view.Menu;
import com.google.gson.Gson;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        //Clarin
        //http://www.clarin.com/ediciones-anteriores/20130601 http://www.clarin.com/ediciones-anteriores/20130602 http://www.clarin.com/ediciones-anteriores/20130604
        try {
            ApplicationContext appContext = new ClassPathXmlApplicationContext("contexto/Spring.xml");
            Menu menu = (Menu) appContext.getBean("menu");
            menu.setVisible(true);
            String datos = Funciones.postGetRequest("http://www.lanacion.com.ar/acumuladosAjax-p" + String.valueOf(1).trim() + "-7775-15",
                    "GET", "");

            Gson gson = new Gson();
            datos = datos.replaceAll("[\n\r]", "");
            datos = datos.trim();
            datos = datos.replace("{\"notas\":[", "");
            datos = datos.replace("]}", "");
            String[] notas = datos.split("        ,        ");



            //System.out.println(datos);
            // String[] notas = datos.split(",");




            int i = 0;
            try {
                i++;
                // "clase": "nota conFoto conBajada conTopico ", 
                // "id": "1591352",
                // "url": "/1591352-allanan-la-casa-de-angeles-rawson", 
                // "ocultarFecha": "0", 
                // "fecha_actualizacion": "201306122113",
                for (String info : notas) {
                    info = info.replace("{\"nota\": {", "{\"nota\": [{");
                    info = info.replace("}}", "}]}");
                    info = info.replace("{\"nota\": [{ ", "");
                    info = info.replace("}]}", "");
                    info = info.trim();

                    String[] test = info.split(",");

                    System.out.println(test[2]);
                    System.out.println(test[4]);
                    

                    //Nota noticias = gson.fromJson(info, Nota.class);
                    System.out.println(info);
                    break;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           // System.in.read();


            //System.out.println(datos);
            System.out.println("Antes del error");
            //Noticia noticias = gson.fromJson(datos, Noticia.class);

            System.out.println("Hello World!");
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
