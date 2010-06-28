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
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 */

public class ServerLog {

	private static String LogfilePath ="";
	public static ServerLog log=null;
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

	private static void createFile(){
                try {
                        File existingFile=new File("../webapps/brihaspatisync_mserver/logs/");
                        LogfilePath = existingFile.getAbsolutePath();
                        existingFile=new File(LogfilePath);
                        if(!existingFile.exists()){
                                existingFile.mkdirs();
                        }
                        LogfilePath=LogfilePath+"/ServerLog.txt";
                        dos = new DataOutputStream(new FileOutputStream(LogfilePath,true));

                }catch(Exception e){
                        LogfilePath="";
  		}
        }


        /**
        * In this method, Dump error message in logfile
        * @param msg String
        * @return
        */
	
        public static void Log(String msg)
        {
                try
                {
			if(LogfilePath.equals("")){
                                createFile();
			}
			dos.writeBytes(new Date()+"---"+msg+"\n");
                }//try
                catch(Exception e) {
                }
        }
}

