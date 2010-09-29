package org.iitk.brihaspatisync.util;

/*@(#)ServerLog.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;

/**
 * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
 * @author <a href="mailto:"> Arvind Pal </a>
 */

public class ServerLog {

	public static ServerLog log=null;
	private static File existingFile =null;
        private static DataOutputStream dos = null;
	
	/**
	 * ServerLog controller 
	 */
	public static ServerLog getController(){
                if (log==null){
                        log=new ServerLog();
                }
                return log;
        }
	
	private void createFile(){
		try {
			existingFile=new File("../webapps/brihaspatisync_iserver/logs/");
                        String LogfilePath = existingFile.getAbsolutePath();
                        existingFile=new File(LogfilePath);
                        if(!existingFile.exists()) {
                                existingFile.mkdirs();
                        }
                        LogfilePath=LogfilePath+"/ServerLog.txt";
                        dos = new DataOutputStream(new FileOutputStream(LogfilePath,true));
		}catch(Exception e){ }	
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
			if(!(existingFile.isFile()) ||(existingFile == null) ){
				createFile();
			}
			Date Errordate=new Date();
	        	dos.writeBytes(Errordate+"---"+msg+"\n");                        
                }catch(Exception e) { }
        }
}
 
