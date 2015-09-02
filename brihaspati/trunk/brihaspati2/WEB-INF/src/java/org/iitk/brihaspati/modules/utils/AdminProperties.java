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
import org.iitk.brihaspati.modules.utils.SortedProperties;

/**
 *  @author <a href="mailto:chitvesh@yahoo.com">Chitvesh Dutta</a>
 *  @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 *  @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 *  @modified date: 18-05-2015 (Seemanti)
 *  @modified date: 31-08-2015 (Seemanti);
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
      public static void setPropertyValue(String path,String Value,String key) throws Exception
      {
         try 
         {
            Properties props = new Properties();
            FileOutputStream outstream = null;
            props.load(new FileInputStream(path));
            props.setProperty(key,Value);
            //For overwriting prop file use the constructor that takes as an argument the String containing the path of the file to write to and boolean parameter "false". 
            outstream = new FileOutputStream(path,false);
            props.store(outstream,"header");     
            //FileOutputStream instance needs to be closed after use. 
            outstream.close();
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
