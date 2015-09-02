package org.iitk.brihaspati.modules.utils;

/*
 * @(#)SortedProperties.java	
 *
 *  Copyright (c) 2005,2009-2013 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CustomProperties;
import org.apache.turbine.util.RunData;

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 */

public class SortedProperties extends Properties{
  
   private static boolean debug= false;
   //Define a Comparator<String> to order strings in case-insensitive manner
   public static class StringComparator implements Comparator<String> 
   {
      @Override
      public int compare(String s1, String s2) 
      {
         return s1.compareToIgnoreCase(s2);
      }
   }
   public static void sortPropertyFile(String path,RunData data) throws Exception 
   {        
      ArrayList<String> ar = new ArrayList<String>();//Take an ArrayList of strings.
      try
      {
         Properties props = new Properties();
         FileInputStream f = new FileInputStream(path);
         props.load(f);//Load properties object for Admin.properties file.
         f.close();
         Enumeration eKey = props.keys();
         // Use a customized Comparator for Strings defined above in StringComparator class.
         Comparator<String> compStr = new StringComparator();
         //Enumerating Admin.properties file.
         while (eKey.hasMoreElements()) 
         {
            String key = (String)eKey.nextElement();
            if (debug) ErrorDumpUtil.ErrorLog("Value of key is: "+key);
            //Add each element into the ArrayList from Enumeration of Properties file.
            ar.add(new String(key));
            if (debug) ErrorDumpUtil.ErrorLog("Print Array list containing keys before sort...."+ar);
         }
         Collections.sort(ar,compStr);
         if (debug) ErrorDumpUtil.ErrorLog("Print Array list containing keys after sort...."+ar);
         //Get the path to reach new Properties File.
         String new_path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"New_Admin.properties";
         //get an Iterator object for ArrayList using iterator() method.
         Iterator itr = ar.iterator();
         //Iterating through ArrayList elements.
         while(itr.hasNext())
         {
            String new_key = (String)(itr.next());
            if (debug) ErrorDumpUtil.ErrorLog("New key fetched from sorted array list.................+++++++"+new_key);
            String val = props.getProperty(new_key);//Get value from old properties file object "props".
            if (debug) ErrorDumpUtil.ErrorLog("New key value from fetched from unsorted array list........+++++++"+val);
            File file = new File(new_path);
            FileOutputStream fileOut = new FileOutputStream(file,true);
            Properties cs = new CustomProperties();
            cs.setProperty(new_key,val);
            cs.store(fileOut, "Admin's property");
            fileOut.close();
         }
         //Now finally rename this New_Admin.properties filename to Admin.properties filename.
         File f_new = new File(data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"New_Admin.properties");
         f_new.renameTo(new File(data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties"));
      }//End Try
      catch (Exception e) {
         e.printStackTrace();
      }
   }//method end.

}//class ends.  

