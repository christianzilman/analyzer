/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.siuden.analyzer;

import java.io.*;
import java.net.*;

/**
 *
 * @author juliozilman
 */
public class Peticion {
    public static String postRequest(String targetURL,String requestMethod, String postParams) {
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
}
