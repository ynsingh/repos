package org.bss.brihaspatisync.reflector.util;

/**
 * RuntimeDataObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010-2011
 */

import java.util.Properties;
import java.util.Vector;
import java.io.InputStream;
import org.bss.brihaspatisync.reflector.buffer_mgt.StoreBufferMgnObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class RuntimeObject {

        private Properties prop=null;
	private StoreBufferMgnObject all_tool_buffer=null;
        private String indexServerAddr="";
        private boolean handraiseflag=false;
        private boolean presentationflag=false;
        private Vector vector= new  Vector();
        private Vector courseid = new  Vector();
        private Vector master_ref= new  Vector();
        private static RuntimeObject obj=null;
        private String parent_ref="";
	private String reflectorrunning="";


	/**
 	 * Controller for this class
 	 */  
        public static RuntimeObject getController() {
                if(obj==null) {
                        obj=new RuntimeObject();
                }
                return obj;
         }

	public Vector getCourseid_IP(String course_id){
                Vector v=new Vector();
                for(int i=0;i<vector.size();i++) {
                        String str=vector.get(i).toString();
                        if(str.startsWith(course_id)){
                                str=str.replaceAll(course_id+"#","");
                                v.add(str);
                        }
                }
                return v;
        }

        public void setCourseid_IP(String courseid_IP){
                vector.add(courseid_IP);
        }

        public boolean getStatusCourseidIP(String courseid_IP){
                return (vector.contains(courseid_IP)) ? false : true ;
        }

        public void setMastrerReflecterCourseid(String course_id){
                if(!master_ref.contains(course_id)) {
                        master_ref.add(course_id);
                }
        }
        public Vector getMastrerReflecterCourseid(){
                return master_ref;
        }
	
	public void resetMastrerReflecterCourseid(String course_id){
                if(master_ref.contains(course_id)) {
                        master_ref.remove(course_id);
                }
        }

	/**
 	 * Set indexing server url 
 	 */  
        public void setindexServerAddr(String value){
                if(!value.equals(""))
                        indexServerAddr=value+"/ProcessRequest?";
        }
        public String getindexServerAddr(){
                if(!(indexServerAddr.equals("")))
                        return indexServerAddr;
                return null;
        }
	
	public StoreBufferMgnObject getAll_Tool_BufferMng(){
                if(all_tool_buffer == null)
                        all_tool_buffer=new StoreBufferMgnObject();
                return all_tool_buffer;
        }
		
        /** set course id */
        private void setCourseID(String value) {
                if(!courseid.contains(value))
                        courseid.add(value);
        }

	/**
 	 * Get course id vector
 	 */  	
        private Vector getCourseID(){
                return courseid;
        }

	/**
 	 * Set handraise flag to start or stop thread
 	 */  
        public void setHandraiseFlag(boolean value){
                handraiseflag=value;
        }

	/**
 	 * get handraise flag
 	 */  	
        public boolean getHandraiseFlag(){
                return handraiseflag;
        }

	/**
         * Set presentation flag to start or stop thread
	 */
         
	public void setPresentationFlag(boolean value){
                presentationflag=value;
        }

	/**
         * get presentation flag
	 */
         
	public boolean getPresentationFlag(){
                return presentationflag;
        }
	
	public void setReflectorRunning(String str){

                reflectorrunning=str;
        }

        public String getReflectorRunning(){
                return reflectorrunning;
        }
	
	public void setParentReflector(String str){
		parent_ref=str;
        }

        public String getParentReflector(){
                return parent_ref;
        }

}

