package org.bss.brihaspatisync.reflector.util;

/*@(#)ReflectorLog
 * See licence file for usage and redistribution terms
 * Copyright (c) 2009 .All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;

/**
 * @author <a href="mailto:ayadav@iitk.ac.in"    > Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 */

public class ReflectorLog {

	private static ReflectorLog log=null;
	private String LogfilePath ="";
 
	/**
	 * ServerLog controller 
	 */
	
	public static ReflectorLog getController(){
                if (log==null){
                        log=new ReflectorLog();
                }
                return log;
        }
	
	private void createFile(){
		try {
			String str="logs/ServerLog.txt";
        	        File existingFile=new File(str);
                	LogfilePath = existingFile.getAbsolutePath();
		}catch(Exception e){
			LogfilePath="";
		}

	}
	
		
        /**
        * In this method, Dump error message in logfile
        * @param msg String
        * @return
        */
        public void Log(String msg)
        {
                try
                {
			if(LogfilePath.equals("")){
				createFile();
			}
			if(!LogfilePath.equals("")){ 
	                        File existingFile=new File(LogfilePath);
        	                String fileSize=Long.toString(existingFile.length());
                	        if(fileSize.equals("1048576")) {
                        	        boolean success=existingFile.delete();
	                        } else{
        	                	Date Errordate=new Date();
                	                FileOutputStream log=new FileOutputStream(LogfilePath,true);
                        	        log.write((Errordate+"---"+msg+"\n").getBytes());
                                	log.close();
                        	}
			}
                }catch(Exception e) { }
        }
}
 
