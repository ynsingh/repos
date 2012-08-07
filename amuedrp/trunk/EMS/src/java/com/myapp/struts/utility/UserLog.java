
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */



import java.io.BufferedReader;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Logger;

public class UserLog {
    private static Logger log4j=LoggerUtils.getLogger();

    static String path=AppPath.getPropertiesFilePath();

public static void HibernateErrorLog(String msg,String filename)
	{
    boolean success=false;

		try
		{
			Date Errordate=new Date();
                        File file=new File(path);
			FileOutputStream log;
                        boolean exists = file.exists();
            if (!exists) {
               success = (new File(path)).mkdir();
                if(success==true){
                    log=new FileOutputStream(path+filename,true);
			log.write((Errordate+"===>"+msg+"\n").getBytes());

                       	log.close();
                }

            }
            else{
                    log=new FileOutputStream(path+"/"+filename,true);
			log.write((Errordate+"===>"+msg+"\n").getBytes());

                       	log.close();
                }

		}//try
		catch(Exception e)
		{
                    log4j.error(e.toString());
		}
    	}
public static String HibernatePrintErrorLog(String filename)
	{
    String lastLine =null;
		try
		{
			FileInputStream in = new FileInputStream(path+"/"+filename);
                          BufferedReader br = new BufferedReader(new InputStreamReader(in));

                         String strLine = null, tmp;

                            while ((tmp = br.readLine()) != null)
                             {
                                strLine = tmp;
                                 }

                         lastLine= strLine;




		}//try
		catch(Exception e)
		{
                   log4j.error(e.toString());
		}
    System.out.println(lastLine);
                 return lastLine;
    	}

	public static void ErrorLog(String msg,String Filename)
	{
		try
		{
			Date Errordate=new Date();
			path=AppPath.getPropertiesFilePath()+Filename;
			FileOutputStream log=new FileOutputStream(path,true);
			log.write((Errordate+"===>"+msg+"\n").getBytes());
                       	log.close();
		}//try
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
          public static void ErrorListLog(List log,String filename)
	{
		try
		{
                    path=AppPath.getPropertiesFilePath()+filename;
			Date Errordate=new Date();
			StringBuffer str = new StringBuffer();

                            PrintWriter pw = new PrintWriter(new FileOutputStream(path,true));

			        for(int ii=0;ii<log.size();ii++)
		                str.append(Errordate+">>"+log.get(ii)+"\n");
      			        pw.println(str+"\n");
                       	pw.close();
		}//try
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
            public static String readProperty(String profile,String searchkey) throws GlobalException
          {
                                String mailbody="";
              try{

                     FileInputStream in = new FileInputStream(AppPath.getPropertiesFilePath()+profile);
                     Properties	pro = new Properties();
                                 pro.load(in);


				Enumeration keys = pro.keys();
                                int i=0;

				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();

                                       if(key.equalsIgnoreCase(searchkey)){
                                       mailbody=(String)pro.get(key);
                                       }




                                   i++;
				}
				in.close();
              }
              catch(Exception e)
              {
                  log4j.error(e.toString());

              }
              return mailbody;
          }
         public static void writeProperty(String filename,HashMap map) throws GlobalException
          {
              boolean success=false;
             try{

                    Properties properties = new Properties();
                    Set set = map.keySet();
                    Iterator itr = set.iterator();
                    while(itr.hasNext())
                    {
                        String key = (String)itr.next();
                        String value =(String) map.get(key);
                        properties.setProperty(key, value);
                    }

                //We have all the properties, now write them to the file.
                //The second argument is optional. You can use it to identify the file.
               path=AppPath.getPropertiesFilePath();
               try
		{
			Date Errordate=new Date();
                        File file=new File(path);
			FileOutputStream log;
                        boolean exists = file.exists();
            if (!exists) {
               success = (new File(path)).mkdir();
                if(success==true){
                   properties.store(new FileOutputStream(path+filename),"Java properties test");
                }

            }
            else{
                  properties.store(new FileOutputStream(path+filename),"Java properties test");
                }
               
		}//try
		catch(Exception e)
		{
                 
                    log4j.error(e.toString());
		}




         //To keep this example simple, I did not include any exception handling
         //code, but in your application you might want to handle exceptions here
         //according to your requirements.
             }catch(Exception e)
             {
                 log4j.error(e.toString());
                 
                 
             }

          }
         private boolean CheckDate(Timestamp d1,Timestamp d2)
    {
       if(d1.after(d2))
        return false;
       else
           return true;
    }

         public static void writeImage(String filename,byte[] data)
         {

         try
         {
            FileOutputStream fos = new FileOutputStream(AppPath.getProjectImagePath()+filename);

      /*
#
       * To write byte array to a file, use
#
       * void write(byte[] bArray) method of Java FileOutputStream class.
#
       *
#
       * This method writes given byte array to a file.
#
       */

        fos.write(data);

      /*
#
       * Close FileOutputStream using,
#
       * void close() method of Java FileOutputStream class.
#
       *
#
       */

       fos.close();

     }
     catch(FileNotFoundException ex)
     {
      //System.out.println("FileNotFoundException : " + ex);
      log4j.error(ex.toString());
     }
     catch(IOException ioe)
     {
      //System.out.println("IOException : " + ioe);
      log4j.error(ioe.toString());
     }

         }

      public static void writeImage1(String filename,byte[] data)
         {

         try
         {
String p=AppPath.getProjectPropertiesImagePath();
            FileOutputStream fos=null ;

             File file=new File(p);
			boolean success=false;
                        boolean exists = file.exists();
            if (!exists) {
               success = (new File(p)).mkdir();

                               if(success==true){


                     fos = new FileOutputStream(p+filename);

                }
            }
            else{
               fos = new FileOutputStream(p+filename);
            }
                        System.out.println(file.getAbsolutePath());
      /*
#
       * To write byte array to a file, use
#
       * void write(byte[] bArray) method of Java FileOutputStream class.
#
       *
#
       * This method writes given byte array to a file.
#
       */

        fos.write(data);

      /*
#
       * Close FileOutputStream using,
#
       * void close() method of Java FileOutputStream class.
#
       *
#
       */

       fos.close();

     }
     catch(FileNotFoundException ex)
     {
      System.out.println("FileNotFoundException : " + ex);
      log4j.error(ex.toString());
     }
     catch(IOException ioe)
     {
     ioe.printStackTrace();
      log4j.error(ioe.toString());
     }

         }


         public static String returnextension(String FileName){
         String ext=FileName.substring(FileName.lastIndexOf(".")+1,FileName.length());
         return ext;

         }
         // Returns the contents of the file in a byte array.
public static byte[] getBytesFromFile(String file1)  {
    byte[] bytes=null;
    try
    {
    File file=new File(file1);
    InputStream is = new FileInputStream(file);

    // Get the size of the file
    long length = file.length();

    // You cannot create an array using a long type.
    // It needs to be an int type.
    // Before converting to an int type, check
    // to ensure that file is not larger than Integer.MAX_VALUE.
    if (length > Integer.MAX_VALUE) {
        // File is too large
    }

    // Create the byte array to hold the data
    bytes = new byte[(int)length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length
           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
        offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
        throw new IOException("Could not completely read file "+file.getName());
    }

    // Close the input stream and return bytes
    is.close();
    }
    catch(Exception e)
    {

    log4j.error("Error"+e);
    }
    return bytes;
}

  public static void updateProperty(String filename,HashMap map) throws GlobalException
          {

      HashMap data=new HashMap();
      //read all keys from properties file
                       String mailbody="";
              try{

                     FileInputStream in = new FileInputStream(AppPath.getPropertiesFilePath()+filename);
                     Properties	pro = new Properties();
                                 pro.load(in);


				Enumeration keys = pro.keys();
                                int i=0;

				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();

                                      data.put(key, (String)pro.get(key));




                                   i++;
				}
				in.close();
              }
              catch(Exception e)
              {
                  log4j.error(e.toString());

              }

      //update key with new value

                    Set set = map.keySet();
                    Iterator itr = set.iterator();
                    while(itr.hasNext())
                    {
                        String key = (String)itr.next();
                        String value =(String) map.get(key);
                        data.put(key, value);
                    }



      //write in properties file












              boolean success=false;
             try{

                    Properties properties = new Properties();
                     set = data.keySet();
                     itr = set.iterator();
                    while(itr.hasNext())
                    {
                        String key = (String)itr.next();
                        String value =(String) data.get(key);
                        properties.setProperty(key, value);
                    }


               path=AppPath.getPropertiesFilePath();
               try
		{
			Date Errordate=new Date();
                        File file=new File(path);
			FileOutputStream log;
                        boolean exists = file.exists();
            if (!exists) {
               success = (new File(path)).mkdir();
                if(success==true){
                   properties.store(new FileOutputStream(path+filename),"Java properties test");
                }

            }
            else{
                  properties.store(new FileOutputStream(path+filename),"Java properties test");
                }

		}//try
		catch(Exception e)
		{

                    log4j.error(e.toString());
		}





             }catch(Exception e)
             {
                 log4j.error(e.toString());


             }

          }


}
