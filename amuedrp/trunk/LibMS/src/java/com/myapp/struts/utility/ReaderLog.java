package com.myapp.struts.utility;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author imran
 */
public class ReaderLog {
    public static String read(String path){
        String setting=null;
try{
  // Open the file that is the first
  // command line parameter
System.out.println(path);
  FileInputStream fstream = new FileInputStream(path+"\\userlog.txt");

  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  //Read File Line By Line
  while ((strLine = br.readLine()) != null) {
  setting+=strLine+",";
  }
  System.out.println(setting);
  //Close the input stream
  in.close();
  }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
return setting;
    }


}
