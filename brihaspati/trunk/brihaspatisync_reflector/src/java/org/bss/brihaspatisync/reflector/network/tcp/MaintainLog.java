package org.bss.brihaspatisync.reflector.network.tcp;

/**
 * MaintainLog.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */
import java.util.Vector;
import org.bss.brihaspatisync.reflector.util.ReflectorLog;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class MaintainLog implements Runnable{

	private Thread runner=null;
	
	private boolean ml_Flag=false;
	
	private static MaintainLog ml=null;
	
	private Vector vector=new Vector();	
	
	/**
        * Controller for the class.
        */

	public static MaintainLog getController(){
                if(ml==null){
                        ml=new MaintainLog();
                }
                return ml;
        }

	private MaintainLog(){}

	/**
        * Start MaintainLog Thread.
        */
        public void start(){
                if (runner == null) {
			ml_Flag=true;
                        runner = new Thread(this);
                        runner.start();	
			setString("MaintainLog Sender Start");
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
			setString("MaintainLog Sender Stop");
		}
        }

	/**
	 *Check Queue for all new entry availble for broadcast.
         */
	public void run(){
		try {
			while(ml_Flag) {	
				if(vector.size()>0){
					ReflectorLog.getController().Log(vector.get(0).toString());	
					vector.remove(0);
				}
			}
			this.runner.yield();
			this.runner.sleep(100000);
		}catch(Exception e){ 
			System.out.println("Error in Catch MaintainLog.java line no 76 ");	
		}
	}
		
	public void setString(String str){
		vector.add(str);
        }
	
		
}


