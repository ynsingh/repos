/*
 * @(#) FileClass.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr.img;


import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;


/**
 * This class retrieves the path of all the bitmap files in the folder
 * @author Anshul Agarwal
 * @Creation date:09-27-2010
 * 
 */
public class FileClass {
   
	private static Logger log = Logger.getLogger(FileClass.class);
	
	/**
	 * This method finds all the Bmp files in the specified folder and add them to an array list 
	 * and return this list to the calling function
     * to the calling function
	 * @param filepath
	 * @param testid
	 * @param rejectedFolderPath
	 * @return
	 */

    public ArrayList<String> pathfunc(String filepath, int testid, String rejectedFolderPath) {
    	Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

        File f1 = new File(filepath);

        FilenameFilter only = new OnlyBmpExt("BMP", rejectedFolderPath, testid);

        File[] s = f1.listFiles(only); // return all the files with bmp extension
        ArrayList<String> path = new ArrayList<String>();

        for (int i = 0; i < s.length; i++) {
          //  System.out.println("path :" + s[i].getAbsolutePath().toString());
             
              if((s[i].length()/1048576) >3){
            	  LogEntry.insert_Log(testid, s[i].getName(),
            	            message.getString("code.E103"), message.getString("msg.E103"));
            	  log.info(message.getString("msg.E103"));

            	  
            	 /* Move the file to rejected folder */
            	   
 	        	 boolean flag =s[i].renameTo(new File(rejectedFolderPath, s[i].getName()));
 	      	    log.info("move file status in file class :  "+ flag);
            	  continue;
              }
              else{
        	path.add(s[i].getAbsolutePath().toString()); // add absolute path of each file into the arraylist
              }
        }

        return path;
    }
}
