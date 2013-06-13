/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer.library;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author juliozilman
 */
public class Funciones {

    /**
     *
     * @param targetURL
     * @param requestMethod
     * @param postParams
     * @return
     */
    public static String postGetRequest(String targetURL, String requestMethod, String postParams) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", ""
                    + Integer.toString(postParams.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection
                    .getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     *
     * @param path
     * @return
     * @throws FileAlreadyExistsException
     */
    public static void crearDirectorioSimple(String path) {
        File file = new File(path);
        try {
            if (!file.isDirectory()) {
                file.mkdir();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "No se puede crear el directorio especificado",
                    "Error Directorio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     * @param cadena
     * @param palabraBuscar
     * @return cantidad de ocurrencia de una palabra en una cadena
     */
    public static int contadorDePalabras(String cadena, String palabraBuscar) {
        int contador = 0;
        while (cadena.indexOf(palabraBuscar) > -1) {
            cadena = cadena.substring(cadena.indexOf(
                    palabraBuscar) + palabraBuscar.length(), cadena.length());
            contador++;
        }
        return contador;
    }

    /**
     *
     * @param cadena
     * @param palabraBuscar
     * @return cantidad de ocurrencia de una palabra en una cadena
     */
    public static int contadorDePalabrasPersonalizado(String cadena, String palabraBuscar) {
        int contador = 0;
        String normalized = Normalizer.normalize(" " + cadena + " ", Normalizer.Form.NFD);
        // Nos quedamos únicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\P{ASCII}");
        normalized = pattern.matcher(normalized).replaceAll("").toUpperCase();

        pattern = Pattern.compile("\\W");
        normalized = pattern.matcher(normalized).replaceAll(" ").toUpperCase();

        palabraBuscar = (palabraBuscar.trim()).toUpperCase();
        //String patron = "\\b" + palabraBuscar + "(S\\b|\\b)";
        String patron = "\\b" + palabraBuscar + "\\b";
        Pattern patterBusqueda = Pattern.compile(patron);

        Matcher matcherBusqueda = patterBusqueda.matcher(normalized);
        // Recuperacion de coincidencias
        while (matcherBusqueda.find()) {
            //System.out.println(matcherBusqueda.group());
            contador++;
        }
        return contador;
    }

    public static boolean esMayorIgual(Date fechaDesde, Date fechaHasta) {
        boolean retorno = false;
        if (fechaHasta.compareTo(fechaDesde) >= 0) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    public static boolean perteneceFecha(Date fechaDesde, Date fechaHasta, Date fechaPertenecer) {
        boolean retorno = false;
        if ((fechaPertenecer.compareTo(fechaDesde) >= 0) && (fechaPertenecer.compareTo(fechaHasta) <= 0)) {
            retorno = true;
        } else {
            if (igualdadFecha(fechaDesde, fechaPertenecer) || igualdadFecha(fechaHasta, fechaPertenecer)) {
                retorno = true;
            } else {
                retorno = false;
            }
        }
        return retorno;
    }

    public static boolean igualdadFecha(Date fechaDesde, Date fechaHasta) {
        if ((fechaDesde.getYear() == fechaHasta.getYear()) && (fechaDesde.getMonth() == fechaHasta.getMonth()) && (fechaDesde.getDay() == fechaHasta.getDay())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esMenorFechaActual(Date fechaHasta) {
        boolean retorno = false;
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        if (fechaHasta.compareTo(date) <= 0) {
            retorno = true;
        } else {
            if (igualdadFecha(fechaHasta, date)) {
                retorno = true;
            } else {

                retorno = false;
            }
        }
        return retorno;
    }

    public static boolean esMenorA(Date fechaDesde, Date time) {
        boolean retorno = false;
        if (fechaDesde.compareTo(time) <= 0) {
            retorno = true;
        } else {
            if (igualdadFecha(fechaDesde, time)) {
                retorno = true;
            } else {
                retorno = false;
            }
        }
        return retorno;
    }
}
