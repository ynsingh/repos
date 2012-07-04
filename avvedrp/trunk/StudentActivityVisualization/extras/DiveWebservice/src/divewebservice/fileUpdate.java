/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * @author sathyaraj
 */
public class fileUpdate {

     public static File updateFile() throws Exception  {
         
          Properties properties = new Properties();
          properties.load(new FileInputStream("../conf/diveServer.properties"));
          String sname = properties.getProperty("folderName");
          System.out.println("OUTPTH IS"+sname);
        File fl = new File(sname);
        File[] files = fl.listFiles(new FileFilter() {                  
            @Override
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        System.out.println("UPDATED"+choise);
        return choise;
}
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * @author sathyaraj
 */
public class fileUpdate {

     public static File updateFile() throws Exception  {
         
          Properties properties = new Properties();
          properties.load(new FileInputStream("../conf/diveServer.properties"));
          String sname = properties.getProperty("folderName");
          System.out.println("OUTPTH IS"+sname);
        File fl = new File(sname);
        File[] files = fl.listFiles(new FileFilter() {                  
            @Override
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        System.out.println("UPDATED"+choise);
        return choise;
}
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * @author sathyaraj
 */
public class fileUpdate {

     public static File updateFile() throws Exception  {
         
          Properties properties = new Properties();
          properties.load(new FileInputStream("../conf/diveServer.properties"));
          String sname = properties.getProperty("folderName");
          System.out.println("OUTPTH IS"+sname);
        File fl = new File(sname);
        File[] files = fl.listFiles(new FileFilter() {                  
            @Override
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        System.out.println("UPDATED"+choise);
        return choise;
}
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * @author sathyaraj
 */
public class fileUpdate {

     public static File updateFile() throws Exception  {
         
          Properties properties = new Properties();
          properties.load(new FileInputStream("../conf/diveServer.properties"));
          String sname = properties.getProperty("folderName");
          System.out.println("OUTPTH IS"+sname);
        File fl = new File(sname);
        File[] files = fl.listFiles(new FileFilter() {                  
            @Override
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        System.out.println("UPDATED"+choise);
        return choise;
}
    
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileFilter;
/**
 *
 * @author sathyaraj
 */
public class fileUpdate {

     public static File updateFile() throws Exception  {
         
          Properties properties = new Properties();
          properties.load(new FileInputStream("../conf/diveServer.properties"));
          String sname = properties.getProperty("folderName");
          System.out.println("OUTPTH IS"+sname);
        File fl = new File(sname);
        File[] files = fl.listFiles(new FileFilter() {                  
            @Override
                public boolean accept(File file) {
                        return file.isFile();
                }
        });
        long lastMod = Long.MIN_VALUE;
        File choise = null;
        for (File file : files) {
                if (file.lastModified() > lastMod) {
                        choise = file;
                        lastMod = file.lastModified();
                }
        }
        System.out.println("UPDATED"+choise);
        return choise;
}
    
}
