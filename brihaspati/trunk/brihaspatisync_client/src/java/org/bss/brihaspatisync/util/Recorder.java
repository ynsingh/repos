package org.bss.brihaspatisync.util;

/*@(#) Recorder.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2009 .All Rights Reserved.
 */

import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;

/**
 * @author <a href="mailto:sndpsngh000@gmail.com"> Sandeep Pal </a>
 * @author <a href="mailto:crystal.brawal@gmail.com"> Ankit Porwall </a>
 */

public class Recorder {

	private static Recorder log=null;
	private String LogfilePath ="";
	private DataOutputStream dos = null; 
	
	/**
	 * ServerLog controller 
	 */
	
	public static Recorder getController(){
                if (log==null){
                        log=new Recorder();
                }
                return log;
        }
	
	private void createFile(){
		try {
			File existingFile=new File("logs");
                	LogfilePath = existingFile.getAbsolutePath();
			existingFile=new File(LogfilePath);
			if(!existingFile.exists()){
				existingFile.mkdirs();
			}
			LogfilePath=LogfilePath+"/Recorder.txt";
			dos = new DataOutputStream(new FileOutputStream(LogfilePath,true));
		}catch(Exception e){ LogfilePath=""; }
	}
	
        /**
        * In this method, Dump error message in logfile
        * @param msg String
        * @return
        */

        public void Record(String msg)
        {
                try
                {
			if(LogfilePath.equals(""))
				createFile();
			Date Errordate=new Date();
                        dos.writeBytes(Errordate+"---"+msg+"\n");
		}catch(Exception e) { }
        }
}
 
