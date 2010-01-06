package org.bss.brihaspatisync.util;

/*@(#)ClientLog.java
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

public class ClientLog {

	private static ClientLog log=null;
	private String LogfilePath ="";
 
	/**
	 * ServerLog controller 
	 */
	
	public static ClientLog getController(){
                if (log==null){
                        log=new ClientLog();
                }
                return log;
        }
	
	private void createFile(){
		try {
			String str="logs";
        	        File existingFile=new File(str);
                	LogfilePath = existingFile.getAbsolutePath();
			existingFile=new File(LogfilePath);
			if(!existingFile.exists()){
				existingFile.mkdirs();
			}
			LogfilePath=LogfilePath+"/ServerLog.txt";
			
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
 
