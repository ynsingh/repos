package org.iitk.livetv.schedular;

import java.awt.Toolkit;

import java.util.TimerTask;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.torque.util.Criteria;
import org.apache.torque.om.NumberKey;

import org.iitk.livetv.util.ServerLog;
import org.iitk.livetv.util.ServerUtil;

public class TimerOfDatabase extends TimerTask {

	static TimerOfDatabase timerOfDatabase=null;

      	public static TimerOfDatabase getController() {
        	if(timerOfDatabase==null);
         	timerOfDatabase=new TimerOfDatabase();
         	return timerOfDatabase;
      	}
      
       	private TimerOfDatabase() { }
        
	/**Overrides the run method */

       	public void run()  {
       	}

}   // end of class
