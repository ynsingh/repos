package org.iitk.brihaspatisync.schedular;



import java.awt.Toolkit;

import java.util.TimerTask;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.torque.util.Criteria;
import org.apache.torque.om.NumberKey;

import org.iitk.brihaspatisync.om.SessionaddressPeer;
import org.iitk.brihaspatisync.om.Sessionaddress;
import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.util.ServerUtil;

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
        	try {
		       	Criteria crit=new Criteria();
                  	List select=LecturePeer.doSelect(crit);
                  	Iterator itr=select.iterator();

                    	/**Get the difference of Day */
			
                     	while(itr.hasNext()) {
                        	Lecture resultofLecture=(Lecture)itr.next();
                           	Date sessiondate=resultofLecture.getSessiondate();
                		//ServerLog.getController().Log("schedular is running under thread"+sessiondate);
				int returnvalue=ServerUtil.getController().getDifferenceOfDay(sessiondate,resultofLecture.getSessiontime());
				if(returnvalue==1) {
                             		deleteRow(resultofLecture.getLectureid());
                            	}
                     	}	 
			
                } catch(Exception ex) {ServerLog.getController().Log("Error in try catch run() in Timer of Database class"+ex.getMessage());
 } 
       	}

 	/** It deletes the rows from the Lecture Table  and  free the multicast Address which is assign to this Lecture */

     	private void deleteRow(NumberKey value) {
        	try {
                	//ServerLog.getController().Log("schedular is running and deleting of row");
                  	Criteria del=new Criteria();
                  	del.add(LecturePeer.LECTUREID,value.toString());                              
                  	LecturePeer.doDelete(del);
                } catch(Exception er) {ServerLog.getController().Log("Error in first try catch deleteRow() in Timer of Database "+er.getMessage());
		}

                try {
                	Criteria up=new Criteria();
                        up.add(SessionaddressPeer.FLAG,value.toString());
                        List select=SessionaddressPeer.doSelect(up);
                        Iterator itr=select.iterator();               

                        while(itr.hasNext()) {
                        	Sessionaddress add=(Sessionaddress)itr.next();
                               	int flag=add.getFlag();
                                if(flag==(Integer.parseInt(value.toString()))) {
                                	add.setFlag(0);          
                                   	add.save(); 
                          //         	ServerLog.getController().Log("Free the Session Address=>"+value.toString());
                                        break;
                             	}
	           	}
        	} catch(Exception er) {  ServerLog.getController().Log("Error in second try catch deleteRow() in Timer of Database "+er.getMessage());
		}
           	Toolkit.getDefaultToolkit().beep();
     	} 
}   // end of class
