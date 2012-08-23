package org.bss.brihaspatisync.monitor.network;

/**
 * ReguestMasterServerList.java
 *  
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import javax.swing.JPanel;
import java.util.Collections;
import java.util.Vector;
import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.monitor.ReflectorManager;
import org.bss.brihaspatisync.monitor.gui.MainWindow;
import javax.swing.JFrame;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 * Modified for showing dynamic courses
 * @date 19/06/2012
 */
	
public class ReguestMasterServerList extends Thread {
	
	private Vector ReflectorsRunning=null;
        private Vector CoursesRunning=null;
	private Vector addCourse=new Vector();
	private Vector removeCourse=new Vector();
	Vector courses=new Vector(5);



	public ReguestMasterServerList() {}
	static String refIP;
 
	public void getSelectedRef(String str){
                refIP=str;
        }
 	
	public void run(){
		try {	
			while(true){
				try {	this.sleep(1000);this.yield();}catch(Exception e){}				                                     		
		                          MainWindow.getController().getLeftPanel().remove(1);

		                          MainWindow.getController().getLeftPanel().add(org.bss.brihaspatisync.monitor.gui.TreeMenu.getController().createGUI(),BorderLayout.CENTER);

					  MainWindow.getController().getLeftPanel().revalidate(); 
					  System.gc();
					  org.bss.brihaspatisync.monitor.RegisterToIndexServer.getController().connectGetReflectorList();
                                	  org.bss.brihaspatisync.monitor.RegisterToIndexServer.getController().connectGetIndexServerList();
                      			  CoursesRunning=ReflectorManager.getController(). getReflectorCourses(refIP);
					  addCourse=ReflectorManager.getController().removeDuplicates(CoursesRunning,courses);
					  removeCourse=ReflectorManager.getController().removeDuplicates(courses,CoursesRunning);
  
					  for(int i=0;i<CoursesRunning.size();i++){
						if(!courses.contains(CoursesRunning.elementAt(i)))
                                        	courses.add(CoursesRunning.elementAt(i));
                        		 }
					for(int i=0;i<addCourse.size();i++){
						if(addCourse.size()>0)
		                        	MainWindow.getController().getdisplayPanel().add(MainWindow.getController().createCoursePanel(addCourse),BorderLayout.CENTER);
 
					}

                      	}
		}catch(Exception e){System.out.println("Request time out error in ReguestMasterServerList"+e.getMessage());}
	}
}


