package org.iitk.brihaspati.modules.utils;
/*
 * @(#)AdminProperties.java
 *
 *  Copyright (c) 2005-2006, 2013 ETRG,IIT Kanpur. 
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 *
 */
import java.io.File;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *  @author <a href="mailto:chitvesh@yahoo.com">Chitvesh Dutta</a>
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 *  @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 *  @modified date: 18-05-2015 (Seemanti)
 */
/** 
 * This class methods set and return the value of Admin.properties 
 * file for configuration parameter.
 */
public class AdminProperties{
	/**
	 * This method returns the value of configuration parameter
	 * in Admin.Properties file
	 * @param path String
	 * @return retval int
	 * @exception a generic exception
	 */
         
	
//	public static int getValue(String path,String key) throws Exception{
	public static String getValue(String path,String key) throws Exception{
	 	InputStream f = new FileInputStream(path);
 		Properties p = new Properties();
		p.load(f);
		String val = null;
		val=p.getProperty(key);
		f.close();
		return(val);
	 }
	
	/**
	 * This method sets the value of configuration parameters 
	 * in Admin.Properties file.
	 */
   //This method is for setting and updating the configuration parameters(key-value pairs)in the Admin.properties file by removing the old key-value pairs.
      public static void setPropertyValue(String path,String Value,String key) throws Exception{
         //Do the whole file operation inside try-catch block to catch exceptions.
         try {
            String MessageDisplay = null;
            //Create an empty property list with no default values.
            Properties props = new Properties();
            //Create a variable of type FileOutputStream.   
            FileOutputStream stream = null;
            //FileOutputStream stream = null;
            
            //If Property file path does not exists then setProperty(key,Value) as in case of institute profile creation.
            File fpath = new File(path);
            if(!fpath.exists())
            {  props.setProperty(key,Value);
            } 

            //Load the input byte stream inorder to read the key and value pairs from the property file.
            props.load(new FileInputStream(path));
                         
            /**Check if properties file is empty or not.
             *if (not empty)
             *   property.check if the specified object is a key in this hashtable and returns true otherwise it returns false.
             *      property.remove the existing key and its corresponding value pair.
             *      property.set the key to be placed into this property list and the value corresponding to key.
             *else
             *   property.set the key to be placed into this property list and the value corresponding to key.
             */
            if(!props.isEmpty()){
              ErrorDumpUtil.ErrorLog("PROPERTY LOADED INSIDE IF");
              if(props.containsKey(key)){
                 props.remove(key);
                 props.setProperty(key,Value);
              }
              else{
                props.setProperty(key,Value);
              }
            //For overwriting the existing property file use the constructor that takes as an argument the String which contains the path of the file to write to. 
            stream = new FileOutputStream(path,false);
            //Writes this property list (key and element pairs) in this property file to the OutputStream.
            props.store(stream, "Property File Configuration");     
            }
            else{
               props.setProperty(key,Value);
               stream = new FileOutputStream(path,false);
               props.store(stream, "header");
            }
            //FileOutputStream instance needs to be closed after use. 
            stream.close();
         }
         catch (Exception e) {
            e.printStackTrace();
         }
    }

	/**
	 * This method sets the value of configuration parameters 
	 * in TurbineResources.properties
	 * @param path String
	 * @param Value String
	 * @exception a generic exception
	 */ 

	public static void setTRValue(String path,String Value,String key) throws Exception{
		InputStream f = new FileInputStream(path);
                CommentedProperties cP= new CommentedProperties();
                cP.load(f);
                FileOutputStream out = new FileOutputStream(path);
                cP.add(key,Value);
                cP.store(out,null);
	}
}
