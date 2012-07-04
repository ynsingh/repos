/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;
import java.sql.*;

import divewebservice.Master;
import divewebservice.Table;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ReadXml {   
     public static final String fileName = "/home/Shyam/axisresponse/hello.xml";  
     public static void xmlRead(File outFile) {
               System.out.println("in XMLREAD");
              // File outFile = new File(fileName);          
              // ########## FOR READING FILE CONTENT ########
                int ch;
                StringBuffer strContent = new StringBuffer("");
                FileInputStream fin = null;
                try {
                fin = new FileInputStream(outFile);
                while ((ch = fin.read()) != -1)
                strContent.append((char) ch);
                fin.close();
                //System.out.println(strContent);
                } 
                catch (FileNotFoundException e) {
                }
                catch (IOException ioe) {
                //System.out.println("Exception while reading the file" + ioe);
                }
                StringReader reader = new StringReader(strContent.toString());
                // ########## FOR READING FILE CONTENT ########
                
               // ########## JAXB FOR GETTING XML DATA  ########
                Connection conn = null;
                String url = "jdbc:mysql://192.168.18.95:3306/";
                String dbName = "studviz_test";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root"; 
                String password = "devima";
                try {
                        Class.forName(driver).newInstance();
                        conn = DriverManager.getConnection(url+dbName,userName,password);
                        //System.out.println("Connected to the database");
                       // Statement stmt = conn.createStatement();
                       // stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
                        
                           JAXBContext context = JAXBContext.newInstance(Table.class);
                            Unmarshaller um = context.createUnmarshaller();
                            Table rowdata = (Table) um.unmarshal(reader);
                            for (int i = 0; i < rowdata.getRowData().toArray().length; i++) {
                                
                              int LMS_ID=Integer.parseInt(rowdata.getRowData().get(i).getLmsid());
                              String ACTION=rowdata.getRowData().get(i).getAction();
                              String COURSE_NAME=rowdata.getRowData().get(i).getCoursename();
                              String DATE=rowdata.getRowData().get(i).getDate();                                                              
                              String MODULE_NAME=rowdata.getRowData().get(i).getModulename();
                              String USER_NAME=rowdata.getRowData().get(i).getUsername();
                              int INST_ID=Integer.parseInt(rowdata.getRowData().get(i).getInstid());
                              String USER_TYPE=rowdata.getRowData().get(i).getUsertype();
            
                              Statement stmt = conn.createStatement();
                             stmt.executeUpdate("insert into master (lms_id, action, coursename, date, module_name,user_name, inst_id,user_type) values('"+LMS_ID+"','"+ACTION+"','"+COURSE_NAME+"','"+DATE+"','"+MODULE_NAME+"','"+USER_NAME+"','"+INST_ID+"','"+USER_TYPE+"')");
                            }          
                        
                        conn.close();  
                        outFile.deleteOnExit();
                } catch (Exception e) {
                e.printStackTrace();
                }
                }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;
import java.sql.*;

import divewebservice.Master;
import divewebservice.Table;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ReadXml {   
     public static final String fileName = "/home/Shyam/axisresponse/hello.xml";  
     public static void xmlRead(File outFile) {
               System.out.println("in XMLREAD");
              // File outFile = new File(fileName);          
              // ########## FOR READING FILE CONTENT ########
                int ch;
                StringBuffer strContent = new StringBuffer("");
                FileInputStream fin = null;
                try {
                fin = new FileInputStream(outFile);
                while ((ch = fin.read()) != -1)
                strContent.append((char) ch);
                fin.close();
                //System.out.println(strContent);
                } 
                catch (FileNotFoundException e) {
                }
                catch (IOException ioe) {
                //System.out.println("Exception while reading the file" + ioe);
                }
                StringReader reader = new StringReader(strContent.toString());
                // ########## FOR READING FILE CONTENT ########
                
               // ########## JAXB FOR GETTING XML DATA  ########
                Connection conn = null;
                String url = "jdbc:mysql://192.168.18.95:3306/";
                String dbName = "studviz_test";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root"; 
                String password = "devima";
                try {
                        Class.forName(driver).newInstance();
                        conn = DriverManager.getConnection(url+dbName,userName,password);
                        //System.out.println("Connected to the database");
                       // Statement stmt = conn.createStatement();
                       // stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
                        
                           JAXBContext context = JAXBContext.newInstance(Table.class);
                            Unmarshaller um = context.createUnmarshaller();
                            Table rowdata = (Table) um.unmarshal(reader);
                            for (int i = 0; i < rowdata.getRowData().toArray().length; i++) {
                                
                              int LMS_ID=Integer.parseInt(rowdata.getRowData().get(i).getLmsid());
                              String ACTION=rowdata.getRowData().get(i).getAction();
                              String COURSE_NAME=rowdata.getRowData().get(i).getCoursename();
                              String DATE=rowdata.getRowData().get(i).getDate();                                                              
                              String MODULE_NAME=rowdata.getRowData().get(i).getModulename();
                              String USER_NAME=rowdata.getRowData().get(i).getUsername();
                              int INST_ID=Integer.parseInt(rowdata.getRowData().get(i).getInstid());
                              String USER_TYPE=rowdata.getRowData().get(i).getUsertype();
            
                              Statement stmt = conn.createStatement();
                             stmt.executeUpdate("insert into master (lms_id, action, coursename, date, module_name,user_name, inst_id,user_type) values('"+LMS_ID+"','"+ACTION+"','"+COURSE_NAME+"','"+DATE+"','"+MODULE_NAME+"','"+USER_NAME+"','"+INST_ID+"','"+USER_TYPE+"')");
                            }          
                        
                        conn.close();  
                        outFile.deleteOnExit();
                } catch (Exception e) {
                e.printStackTrace();
                }
                }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;
import java.sql.*;

import divewebservice.Master;
import divewebservice.Table;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ReadXml {   
     public static final String fileName = "/home/Shyam/axisresponse/hello.xml";  
     public static void xmlRead(File outFile) {
               System.out.println("in XMLREAD");
              // File outFile = new File(fileName);          
              // ########## FOR READING FILE CONTENT ########
                int ch;
                StringBuffer strContent = new StringBuffer("");
                FileInputStream fin = null;
                try {
                fin = new FileInputStream(outFile);
                while ((ch = fin.read()) != -1)
                strContent.append((char) ch);
                fin.close();
                //System.out.println(strContent);
                } 
                catch (FileNotFoundException e) {
                }
                catch (IOException ioe) {
                //System.out.println("Exception while reading the file" + ioe);
                }
                StringReader reader = new StringReader(strContent.toString());
                // ########## FOR READING FILE CONTENT ########
                
               // ########## JAXB FOR GETTING XML DATA  ########
                Connection conn = null;
                String url = "jdbc:mysql://192.168.18.95:3306/";
                String dbName = "studviz_test";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root"; 
                String password = "devima";
                try {
                        Class.forName(driver).newInstance();
                        conn = DriverManager.getConnection(url+dbName,userName,password);
                        //System.out.println("Connected to the database");
                       // Statement stmt = conn.createStatement();
                       // stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
                        
                           JAXBContext context = JAXBContext.newInstance(Table.class);
                            Unmarshaller um = context.createUnmarshaller();
                            Table rowdata = (Table) um.unmarshal(reader);
                            for (int i = 0; i < rowdata.getRowData().toArray().length; i++) {
                                
                              int LMS_ID=Integer.parseInt(rowdata.getRowData().get(i).getLmsid());
                              String ACTION=rowdata.getRowData().get(i).getAction();
                              String COURSE_NAME=rowdata.getRowData().get(i).getCoursename();
                              String DATE=rowdata.getRowData().get(i).getDate();                                                              
                              String MODULE_NAME=rowdata.getRowData().get(i).getModulename();
                              String USER_NAME=rowdata.getRowData().get(i).getUsername();
                              int INST_ID=Integer.parseInt(rowdata.getRowData().get(i).getInstid());
                              String USER_TYPE=rowdata.getRowData().get(i).getUsertype();
            
                              Statement stmt = conn.createStatement();
                             stmt.executeUpdate("insert into master (lms_id, action, coursename, date, module_name,user_name, inst_id,user_type) values('"+LMS_ID+"','"+ACTION+"','"+COURSE_NAME+"','"+DATE+"','"+MODULE_NAME+"','"+USER_NAME+"','"+INST_ID+"','"+USER_TYPE+"')");
                            }          
                        
                        conn.close();  
                        outFile.deleteOnExit();
                } catch (Exception e) {
                e.printStackTrace();
                }
                }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;
import java.sql.*;

import divewebservice.Master;
import divewebservice.Table;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ReadXml {   
     public static final String fileName = "/home/Shyam/axisresponse/hello.xml";  
     public static void xmlRead(File outFile) {
               System.out.println("in XMLREAD");
              // File outFile = new File(fileName);          
              // ########## FOR READING FILE CONTENT ########
                int ch;
                StringBuffer strContent = new StringBuffer("");
                FileInputStream fin = null;
                try {
                fin = new FileInputStream(outFile);
                while ((ch = fin.read()) != -1)
                strContent.append((char) ch);
                fin.close();
                //System.out.println(strContent);
                } 
                catch (FileNotFoundException e) {
                }
                catch (IOException ioe) {
                //System.out.println("Exception while reading the file" + ioe);
                }
                StringReader reader = new StringReader(strContent.toString());
                // ########## FOR READING FILE CONTENT ########
                
               // ########## JAXB FOR GETTING XML DATA  ########
                Connection conn = null;
                String url = "jdbc:mysql://192.168.18.95:3306/";
                String dbName = "studviz_test";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root"; 
                String password = "devima";
                try {
                        Class.forName(driver).newInstance();
                        conn = DriverManager.getConnection(url+dbName,userName,password);
                        //System.out.println("Connected to the database");
                       // Statement stmt = conn.createStatement();
                       // stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
                        
                           JAXBContext context = JAXBContext.newInstance(Table.class);
                            Unmarshaller um = context.createUnmarshaller();
                            Table rowdata = (Table) um.unmarshal(reader);
                            for (int i = 0; i < rowdata.getRowData().toArray().length; i++) {
                                
                              int LMS_ID=Integer.parseInt(rowdata.getRowData().get(i).getLmsid());
                              String ACTION=rowdata.getRowData().get(i).getAction();
                              String COURSE_NAME=rowdata.getRowData().get(i).getCoursename();
                              String DATE=rowdata.getRowData().get(i).getDate();                                                              
                              String MODULE_NAME=rowdata.getRowData().get(i).getModulename();
                              String USER_NAME=rowdata.getRowData().get(i).getUsername();
                              int INST_ID=Integer.parseInt(rowdata.getRowData().get(i).getInstid());
                              String USER_TYPE=rowdata.getRowData().get(i).getUsertype();
            
                              Statement stmt = conn.createStatement();
                             stmt.executeUpdate("insert into master (lms_id, action, coursename, date, module_name,user_name, inst_id,user_type) values('"+LMS_ID+"','"+ACTION+"','"+COURSE_NAME+"','"+DATE+"','"+MODULE_NAME+"','"+USER_NAME+"','"+INST_ID+"','"+USER_TYPE+"')");
                            }          
                        
                        conn.close();  
                        outFile.deleteOnExit();
                } catch (Exception e) {
                e.printStackTrace();
                }
                }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;
import java.sql.*;

import divewebservice.Master;
import divewebservice.Table;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ReadXml {   
     public static final String fileName = "/home/Shyam/axisresponse/hello.xml";  
     public static void xmlRead(File outFile) {
               System.out.println("in XMLREAD");
              // File outFile = new File(fileName);          
              // ########## FOR READING FILE CONTENT ########
                int ch;
                StringBuffer strContent = new StringBuffer("");
                FileInputStream fin = null;
                try {
                fin = new FileInputStream(outFile);
                while ((ch = fin.read()) != -1)
                strContent.append((char) ch);
                fin.close();
                //System.out.println(strContent);
                } 
                catch (FileNotFoundException e) {
                }
                catch (IOException ioe) {
                //System.out.println("Exception while reading the file" + ioe);
                }
                StringReader reader = new StringReader(strContent.toString());
                // ########## FOR READING FILE CONTENT ########
                
               // ########## JAXB FOR GETTING XML DATA  ########
                Connection conn = null;
                String url = "jdbc:mysql://192.168.18.95:3306/";
                String dbName = "studviz_test";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root"; 
                String password = "devima";
                try {
                        Class.forName(driver).newInstance();
                        conn = DriverManager.getConnection(url+dbName,userName,password);
                        //System.out.println("Connected to the database");
                       // Statement stmt = conn.createStatement();
                       // stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
                        
                           JAXBContext context = JAXBContext.newInstance(Table.class);
                            Unmarshaller um = context.createUnmarshaller();
                            Table rowdata = (Table) um.unmarshal(reader);
                            for (int i = 0; i < rowdata.getRowData().toArray().length; i++) {
                                
                              int LMS_ID=Integer.parseInt(rowdata.getRowData().get(i).getLmsid());
                              String ACTION=rowdata.getRowData().get(i).getAction();
                              String COURSE_NAME=rowdata.getRowData().get(i).getCoursename();
                              String DATE=rowdata.getRowData().get(i).getDate();                                                              
                              String MODULE_NAME=rowdata.getRowData().get(i).getModulename();
                              String USER_NAME=rowdata.getRowData().get(i).getUsername();
                              int INST_ID=Integer.parseInt(rowdata.getRowData().get(i).getInstid());
                              String USER_TYPE=rowdata.getRowData().get(i).getUsertype();
            
                              Statement stmt = conn.createStatement();
                             stmt.executeUpdate("insert into master (lms_id, action, coursename, date, module_name,user_name, inst_id,user_type) values('"+LMS_ID+"','"+ACTION+"','"+COURSE_NAME+"','"+DATE+"','"+MODULE_NAME+"','"+USER_NAME+"','"+INST_ID+"','"+USER_TYPE+"')");
                            }          
                        
                        conn.close();  
                        outFile.deleteOnExit();
                } catch (Exception e) {
                e.printStackTrace();
                }
                }
}
