package edu.avv.ws.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import edu.avv.util.GetConsole;
import edu.avv.util.GetPropertiesFile;

public class PostXmlRequest {

  private static URL url = null;
  
  public static InputStream postXml(ByteArrayOutputStream xml){
    try {
      GetConsole.info("--------- WSRequest.xml ----------");
      GetConsole.info(xml.toString());	
      String propFileName = "gms-ws.properties";
	  Properties properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);      	
	  String ServletUrl = properties.getProperty("SERVICE_URL");	
	  url = new URL(System.getProperty("url", ServletUrl));
	} catch (MalformedURLException e) {
	  GetConsole.fatal("System Property 'url' is not a valid URL: " + url);
	}
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(xml.toByteArray());
    GetConsole.info("Posting XML Request To URL:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVICE_URL"));
    postData(in,out);
    GetConsole.info("--------- WSResponse.xml ----------");
    GetConsole.info(out.toString());
    return new ByteArrayInputStream(out.toByteArray());
  }
	
  public static void postData(InputStream data, OutputStream output) {
    
    String type = System.getProperty("type", "application/xml");
    HttpURLConnection urlc = null;
    try {
      try {
	    urlc = (HttpURLConnection) url.openConnection();
	    urlc.setRequestMethod("POST");
	    urlc.setDoOutput(true);
	    urlc.setDoInput(true);
	    urlc.setUseCaches(false);
	    urlc.setAllowUserInteraction(false);
	    urlc.setRequestProperty("Content-type", type); 
	  } catch (IOException e) {
	    GetConsole.fatal("Connection error (is Server running at " + url + " ?): " + e);
	  }
	  OutputStream out = null;
	  try {
	    out = urlc.getOutputStream();
	    pipe(data, out);
	  } catch (IOException e) {
		GetConsole.fatal("IOException while posting data: " + e); } finally {
	    try {
	      if (out != null) out.close(); 
	    } catch (IOException x) {
	    }
	  }
	  InputStream in = null;
	  try {
	    if (200 != urlc.getResponseCode()) {
	      GetConsole.fatal("Server returned an error #" + urlc.getResponseCode() + " " + urlc.getResponseMessage());
	    }
	    in = urlc.getInputStream();
        pipe(in, output);
      } catch (IOException e) {
    	GetConsole.fatal("IOException while reading response: " + e); } finally {
	    try {
	      if (in != null) in.close(); 
	    } catch (IOException x) {
	    }
	  }
	} finally {
	  if (urlc != null) urlc.disconnect(); 
	}
  }
  private static void pipe(InputStream source, OutputStream dest)throws IOException{
    byte[] buf = new byte[1024];
	int read = 0;
	while ((read = source.read(buf)) >= 0) {
	  if (null == dest) continue; dest.write(buf, 0, read);
	}
	if (null != dest) dest.flush();
  }
  public void doGet(String url) {
    try {
      doGet(new URL(url));
    } catch (MalformedURLException e) {
      GetConsole.fatal("The specified URL " + url + " is not a valid URL. Please check");
    }
  }
  public void doGet(URL url) {
    try {
      HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
      if (200 != urlc.getResponseCode())
        GetConsole.fatal("Server returned an error #" + urlc.getResponseCode() + " " + urlc.getResponseMessage());
    }
    catch (IOException e)
    {
      GetConsole.fatal("An error occured posting data to " + url + ". Please check that Server is running.");
    }
  }
}
