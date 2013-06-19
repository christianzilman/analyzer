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
            
            System.out.println("Hello World!");
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
