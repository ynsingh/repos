/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import javax.activation.DataHandler;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  


import java.io.*;
import java.util.UUID;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;
import java.util.Properties;



public class ImportData {
    
   public static final String fileName = "logtransform.xml";  
   
   
   public void doBinaryWithDataHandler(DataHandler data) {          
            try {   
                
                 Properties properties = new Properties();
                 properties.load(new FileInputStream("../conf/diveServer.properties"));
                 String sname = properties.getProperty("fileName");
                 System.out.println("OUTPTH IS"+sname);
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                System.out.println(randomUUIDString);
                File outFile = new File(sname+randomUUIDString);  
                System.out.println(outFile.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);                 
                data.writeTo(fileOutputStream);              
                fileOutputStream.flush();              
                fileOutputStream.close();       
                
                
            } catch (Exception e) {              
                e.printStackTrace();          
            }     
            try
            {
                File fileName=fileUpdate.updateFile();
                ReadXml.xmlRead(fileName);
            }
            catch(Exception e){System.out.println(e);}
   }     
  
     
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import javax.activation.DataHandler;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  


import java.io.*;
import java.util.UUID;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;
import java.util.Properties;



public class ImportData {
    
   public static final String fileName = "logtransform.xml";  
   
   
   public void doBinaryWithDataHandler(DataHandler data) {          
            try {   
                
                 Properties properties = new Properties();
                 properties.load(new FileInputStream("../conf/diveServer.properties"));
                 String sname = properties.getProperty("fileName");
                 System.out.println("OUTPTH IS"+sname);
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                System.out.println(randomUUIDString);
                File outFile = new File(sname+randomUUIDString);  
                System.out.println(outFile.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);                 
                data.writeTo(fileOutputStream);              
                fileOutputStream.flush();              
                fileOutputStream.close();       
                
                
            } catch (Exception e) {              
                e.printStackTrace();          
            }     
            try
            {
                File fileName=fileUpdate.updateFile();
                ReadXml.xmlRead(fileName);
            }
            catch(Exception e){System.out.println(e);}
   }     
  
     
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import javax.activation.DataHandler;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  


import java.io.*;
import java.util.UUID;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;
import java.util.Properties;



public class ImportData {
    
   public static final String fileName = "logtransform.xml";  
   
   
   public void doBinaryWithDataHandler(DataHandler data) {          
            try {   
                
                 Properties properties = new Properties();
                 properties.load(new FileInputStream("../conf/diveServer.properties"));
                 String sname = properties.getProperty("fileName");
                 System.out.println("OUTPTH IS"+sname);
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                System.out.println(randomUUIDString);
                File outFile = new File(sname+randomUUIDString);  
                System.out.println(outFile.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);                 
                data.writeTo(fileOutputStream);              
                fileOutputStream.flush();              
                fileOutputStream.close();       
                
                
            } catch (Exception e) {              
                e.printStackTrace();          
            }     
            try
            {
                File fileName=fileUpdate.updateFile();
                ReadXml.xmlRead(fileName);
            }
            catch(Exception e){System.out.println(e);}
   }     
  
     
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import javax.activation.DataHandler;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  


import java.io.*;
import java.util.UUID;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;
import java.util.Properties;



public class ImportData {
    
   public static final String fileName = "logtransform.xml";  
   
   
   public void doBinaryWithDataHandler(DataHandler data) {          
            try {   
                
                 Properties properties = new Properties();
                 properties.load(new FileInputStream("../conf/diveServer.properties"));
                 String sname = properties.getProperty("fileName");
                 System.out.println("OUTPTH IS"+sname);
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                System.out.println(randomUUIDString);
                File outFile = new File(sname+randomUUIDString);  
                System.out.println(outFile.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);                 
                data.writeTo(fileOutputStream);              
                fileOutputStream.flush();              
                fileOutputStream.close();       
                
                
            } catch (Exception e) {              
                e.printStackTrace();          
            }     
            try
            {
                File fileName=fileUpdate.updateFile();
                ReadXml.xmlRead(fileName);
            }
            catch(Exception e){System.out.println(e);}
   }     
  
     
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import javax.activation.DataHandler;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  


import java.io.*;
import java.util.UUID;

import java.rmi.RemoteException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import divewebservice.Master;
import divewebservice.Table;
import java.util.Properties;



public class ImportData {
    
   public static final String fileName = "logtransform.xml";  
   
   
   public void doBinaryWithDataHandler(DataHandler data) {          
            try {   
                
                 Properties properties = new Properties();
                 properties.load(new FileInputStream("../conf/diveServer.properties"));
                 String sname = properties.getProperty("fileName");
                 System.out.println("OUTPTH IS"+sname);
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                System.out.println(randomUUIDString);
                File outFile = new File(sname+randomUUIDString);  
                System.out.println(outFile.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);                 
                data.writeTo(fileOutputStream);              
                fileOutputStream.flush();              
                fileOutputStream.close();       
                
                
            } catch (Exception e) {              
                e.printStackTrace();          
            }     
            try
            {
                File fileName=fileUpdate.updateFile();
                ReadXml.xmlRead(fileName);
            }
            catch(Exception e){System.out.println(e);}
   }     
  
     
}