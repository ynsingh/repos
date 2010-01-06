package org.iitk.brihaspatisync.util;

/*@(#)ServerLog.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;

/**
 * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
 * @author <a href="mailto:"> Arvind Pal </a>
 */

public class ServerLog {

	public static ServerLog log=null;
	 
	/**
	 * ServerLog controller 
	 */
	public static ServerLog getController(){
                if (log==null){
                        log=new ServerLog();
                }
                return log;
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
                        Date Errordate=new Date();
                        String LogfilePath="../webapps/brihaspatisync_mserver/logs/ServerLog.txt";
                        File existingFile=new File(LogfilePath);
			LogfilePath = existingFile.getAbsolutePath();
                        String fileSize=Long.toString(existingFile.length());
                        if(fileSize.equals("1048576"))
                        {
                                boolean success=existingFile.delete();
                        }
                        else{
                                FileOutputStream log=new FileOutputStream(LogfilePath,true);
                                log.write((Errordate+"---"+msg+"\n").getBytes());
                                log.close();
                        }
                }//try
                catch(Exception e)
                {
                }
        }
}

