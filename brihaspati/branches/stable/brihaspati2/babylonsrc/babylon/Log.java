package babylon;

/**
 * Log.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */
import java.lang.Long;

import java.util.Date;
import java.util.Vector;

import java.io.File;
import java.io.FileOutputStream;


public class Log implements Runnable{

	private Thread runner=null;
	
	private boolean ml_Flag=false;
	
	private static Log ml=null;
	
	private Vector vector=new Vector();	

	private String LogfilePath="";	
	/**
        * Controller for the class.
        */

	public static Log getController(){
                if(ml==null){
                        ml=new Log();
                }
                return ml;
        }

	private Log(){}

	/**
        * Start MaintainLog Thread.
        */
        public void start(){
                if (runner == null) {
			ml_Flag=true;
                        runner = new Thread(this);
                        runner.start();	
			//setLog("MaintainLog Sender Start");
		}
        }

	/**
         * Stop MaintainLog Thread.
         */
        public void stop() {
                if (runner != null) {
			LogfilePath="";
			ml_Flag=false;
                        runner.stop();
			runner = null;
		}
        }

	/**
	 *Check Queue for all new entry availble for broadcast.
         */
	public void run(){
		try {
			while(ml_Flag) {	
				if(vector.size()>0){
					//System.out.println("run thread start and absolute path --> "+LogfilePath);
					FileOutputStream log=new FileOutputStream(LogfilePath,true);
					//System.out.println("data run thread start "+vector.get(0).toString());
                                        log.write((vector.get(0).toString()+"\n").getBytes());
					vector.remove(0);
                                        log.close();
				}
			}
			this.runner.yield();
			this.runner.sleep(100000);
		}catch(Exception e){ 
			System.out.println("Error in Catch Log.java line no 84 ");	
		}
	}
		
	public void setLog(String str){
		vector.add(str);
        }
	
	public void createFile(String groupname) {
		try {
			String fileName = (Integer.toString(new java.util.Date().getDate())+"-"+Integer.toString(new java.util.Date().getMonth()+1) +"-"+Integer.toString(new java.util.Date().getYear()+1900));
                	String groupname1 =groupname+"-"+fileName;
                        File existingFile=new File(groupname);
                        LogfilePath = existingFile.getAbsolutePath();
                        existingFile=new File(LogfilePath);
                        if(!existingFile.exists()){
                                existingFile.mkdirs();
                        }
                        LogfilePath=LogfilePath+"/"+groupname1+".txt";
                }catch(Exception e){
                        LogfilePath="";
                }

        }

	
	
}


