package org.bss.brihaspatisync.network;


/**
 * MaintainLog.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */
import java.util.Vector;
import org.bss.brihaspatisync.util.ClientLog;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class Log implements Runnable{

	private Thread runner=null;
	
	private boolean ml_Flag=false;
	
	private static Log ml=null;
	
	private Vector vector=new Vector();	
	
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
			setLog("MaintainLog Sender Start");
		}
        }

	/**
         * Stop MaintainLog Thread.
         */
        public void stop() {
                if (runner != null) {
			ml_Flag=false;
                        runner.stop();
			runner = null;
			setLog("MaintainLog Sender Stop");
		}
        }

	/**
	 *Check Queue for all new entry availble for broadcast.
         */
	public void run(){
		try {
			while(ml_Flag) {	
				if(vector.size()>0){
					ClientLog.getController().Log(vector.get(0).toString());	
					vector.remove(0);
				}
			}
			this.runner.yield();
			this.runner.sleep(100000);
		}catch(Exception e){ 
			System.out.println("Error in Catch MaintainLog.java line no 76 ");	
		}
	}
		
	public void setLog(String str){
		vector.add(str);
        }
}


