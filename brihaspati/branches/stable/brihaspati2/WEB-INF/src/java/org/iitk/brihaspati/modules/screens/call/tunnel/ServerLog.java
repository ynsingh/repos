package org.iitk.brihaspati.modules.screens.call.tunnel;
/*@(#)ServerLog.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;

public class ServerLog {

        private static ServerLog log=null;
        private File existingFile =null;
        private DataOutputStream dos = null;

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
                	existingFile=new File("/home/guest/tdk-2.3_01Com/logs/ServerLog.txt");
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
                        if(!((new File("/home/guest/tdk-2.3_01Com/logs/ServerLog.txt")).isFile()) || (existingFile == null)){
                                createFile();
                        }
                        Date Errordate=new Date();
                        dos.writeBytes(Errordate+"---"+msg+"\n");
                        dos.flush();
                }catch(Exception e) { }
        }
}

