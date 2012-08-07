/*
 * @(#) OnlyBmpExt.java
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

import in.ac.dei.mhrd.omr.processSheet.ProcessSheetAction;

/**
* This class extract path of BMP files only
* @author Anshul Agarwal
* @Creation date:09-27-2010
* @version 1.0
*
*/
public class OnlyBmpExt implements FilenameFilter {
   String ext;
   String rejectedFolderPath;
   int testid;

   /**
    * This method reads the files from the input folder and transfers the the files
    * into the rejectedfolder that are not in bitmap format
    * @param ext
    * @param rejcteFolderPath
    * @param testid
    */
	public OnlyBmpExt(String ext, String rejcteFolderPath, int testid){
		
		this.ext = ext;
		this.rejectedFolderPath = rejectedFolderPath;
		this.testid = testid;
	}
	private static Logger log = Logger.getLogger(OnlyBmpExt.class);

	public boolean accept(File dir, String name){
		
		Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

		
		 boolean b = false;
		 //check if file is in bitmap format
	        b = (name.endsWith(ext)||name.endsWith(ext.toLowerCase()));
	        System.out.println("accept");
	        if (b == false) {
	        	  File source = new File(dir, name);
	        	  //if file is not a bitmap image, enter this 
	        	  //information into the database
	        	  LogEntry.insert_Log(testid, name,
          	            message.getString("code.E104"), message.getString("msg.E104"));
          	  log.info(message.getString("msg.E104"));

	  	        boolean flag = source.renameTo(new File (ProcessSheetAction.RejectedFolderPath, name));
	  	        log.info("filer move to rejected folder in ext " + flag);
	            
	        }
	 return b;
	}

}
