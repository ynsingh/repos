package org.bss.brihaspatisync.monitor.util;

/* @ServerLog.java
 *
 * See licence file for usage and redistribution terms
 * Copyright (c) 2011.All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */


public class ServerLog {

	private static ServerLog log=null;
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
			existingFile=new File("MonitoringTool.txt");
                        dos = new DataOutputStream(new FileOutputStream(existingFile,true));
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
			createFile();
			Date dNow = new Date( );
	        	dos.writeBytes(dNow+"---"+msg+"\n");
			dos.flush();                        
                }catch(Exception e) { }
        }
}
 
