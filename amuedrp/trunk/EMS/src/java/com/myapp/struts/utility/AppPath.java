/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;
import java.util.HashMap;
import javax.naming.*;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

public class AppPath 
{
    static String path=null;
    static String ds=null;
  static   Locale locale = null;
  static  String locale1 = "en";
  static  String rtl = "ltr";
  static   boolean page = true;
      private static final Logger log=LoggerUtils.getLogger();
    public static String getProject()
    {
          try

       {


                 Context ctx=new InitialContext();
                if(ctx==null)
                    throw new RuntimeException("JNDI");

                    ds=(String)ctx.lookup("java:comp/env/path1");
                    path=ds.toString();
                  System.out.println("Path"+ds);
                  }

        catch(Exception e)
        {
            System.out.println(e);
         }

                return path;
        }
public static String getReportPath(){
    String os=System.getProperty("os.name");
    
String projectPath = getProject();

    if(os.equalsIgnoreCase("linux"))
    {
       //projectPath=projectPath+"/web/reports/";
         projectPath=projectPath+"/reports/";
    return projectPath;
    }
    else
    { 
      //projectPath=projectPath+"\\web\\reports\\";   
      projectPath=projectPath+"\\reports\\";
      System.out.println("project path "+projectPath);
    return projectPath;
    }

    

}
public static String getProjectPath(){
    String os=System.getProperty("os.name");
   
String projectPath = getProject();



    if(os.equalsIgnoreCase("linux"))
    {
       
    return projectPath;
    }
    else
    {
    
    return projectPath;
    }



}
public static String getProjectImagePath(){
    String os=System.getProperty("os.name");
  
String projectPath = getProject();


    if(os.equalsIgnoreCase("linux"))
    {
       // projectPath=projectPath+"/web/images/";
         projectPath=projectPath+"/images/";
    return projectPath;
    }
    else
    { 
       //projectPath=projectPath+"\\web\\images\\";   
         projectPath=projectPath+"\\images\\";
    return projectPath;
    }



}

public static String getProjectPropertiesImagePath(){
    String os=System.getProperty("os.name");
String projectPath = getProject();


    if(os.equalsIgnoreCase("linux"))
    {
        projectPath=projectPath+"/../EMSLOG/images/";
    return projectPath;
    }
    else
    {
    projectPath=projectPath+"\\..\\EMSLOG\\images\\";
    return projectPath;
    }



}

public static String getPropertiesFilePath(){
    String os=System.getProperty("os.name");

String projectPath = getProject();


    if(os.equalsIgnoreCase("linux"))
    {
        projectPath=projectPath+"/../EMSLOG/";
    return projectPath;
    }
    else
    {
    projectPath=projectPath+"\\..\\EMSLOG\\";
    return projectPath;
    }



}
public static String getPropertiesFilePath1(){
    String os=System.getProperty("os.name");
  
String projectPath = getProject();


    if(os.equalsIgnoreCase("linux"))
    {
      //  projectPath=projectPath+"/web/EMSLOG/";
        projectPath=projectPath+"/EMSLOG/";

    return projectPath;
    }
    else
    {
    System.out.println("log path is  "+projectPath);
//    projectPath=projectPath+"\\web\\EMSLOG\\";
    projectPath=projectPath+"\\EMSLOG\\";
    return projectPath;
    }



}




public static ResourceBundle MLI(String lang)
{
try {
            locale1 = lang;





            if (lang != null) {
                locale1 = (String) lang;

            } else {
                locale1 = "en";
            }
        } catch (Exception e) {
            locale1 = "en";
        }
        locale = new Locale(locale1);
        if (!(locale1.equals("ur") || locale1.equals("ar"))) {
            rtl = "LTR";
            page = true;
        } else {
            rtl = "RTL";
            page = false;
        }
        ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
return resource;

}
public static void sendmail(String to,String password,String subject,String body)
{
Email mail=new Email(to,password,subject,body);
mail.send();

}
public static void sendAltmail(String to,String password,String subject,String body)
{
Email mail=new Email(to,password,subject,body);
mail.send();

}
void test(){
                  //0123456789012345678901
    String text = "Hello,my name is=Helen";
    Map<Character,Integer> map = new HashMap<Character,Integer>();

    boolean lastIsLetter = false;
    for (int i = 0; i < text.length(); i++) {
        char ch = text.charAt(i);
        boolean currIsLetter = Character.isLetter(ch);
        if (!lastIsLetter && currIsLetter) {
            map.put(ch, i);
        }
        lastIsLetter = currIsLetter;
    }

   // System.out.println(map);

    }
public static byte[] getImage(FormFile image) throws GlobalException
{
    byte [] img;
    try
    {
         img =image.getFileData();
         log.error("Image Uploaded"+img);
    }
    catch(Exception e)
    {
        log.error("error");
    throw  new GlobalException("Error");

    }
    return img;
}
public static String getProjectExportPath(){

    String os=System.getProperty("os.name");
    String projectPath = getProject();


    if(os.equalsIgnoreCase("linux"))
    {
        //projectPath=projectPath+"/web/Export/";
         projectPath=projectPath+"/Export/";
        return projectPath;
    }
    else
    {
       //projectPath=projectPath+"\\web\\Export\\";
       projectPath=projectPath+"\\Export\\";
       return projectPath;
    }



}
}
