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
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class RuntimeDataObject {

        private Properties prop=null;

	private MyHashTable ht=null;

	private MyHashTable videoht=null;
	
	private MyHashTable audioht=null;

	private MyHashTable desktopht=null;
	
	private MyHashTable pptht=null;

        private String indexServerAddr="";

        private boolean handraiseflag=false;
        private boolean presentationflag=false;

        private Vector vector= new  Vector();

	private MyHashTable userListVector=null;

        private Vector courseid = new  Vector();

        private Vector master_ref= new  Vector();

        private static RuntimeDataObject obj=null;


	/**
 	 * Controller for this class
 	 */  
        public static RuntimeDataObject getController() {
                if(obj==null) {
                        obj=new RuntimeDataObject();
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

	public MyHashTable getInstructorVideoMyHashTable(){
                if(videoht == null)
                        videoht=new MyHashTable();
                return videoht;
        }

	public MyHashTable getStudentVideoMyHashTable(){
                if(videoht == null)
                        videoht=new MyHashTable();
                return videoht;
        }	

	public MyHashTable getDesktopServerMyHashTable(){
                if(desktopht == null)
                        desktopht=new MyHashTable();
                return desktopht;
        }
	
	public MyHashTable getAudioServerMyHashTable(){
                if(audioht == null)
                        audioht=new MyHashTable();
                return audioht;
        }
	
	public MyHashTable getPPTServerMyHashTable(){
                if(pptht == null)
                        pptht=new MyHashTable();
                return pptht;
        }

	public MyHashTable getMyHashTable(){
                if(ht == null)
                        ht=new MyHashTable();
                return ht;
        }

        /** 
 	 * Get UserList Vector object according to course id
         */

        public MyHashTable getUserListMyHashTable(){
                if(userListVector == null)
                        userListVector = new MyHashTable();
                return userListVector;
        }

        /** set course id */
        public void setCourseID(String value) {
                if(!courseid.contains(value))
                        courseid.add(value);
        }

	/**
 	 * Get course id vector
 	 */  	
        public Vector getCourseID(){
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

}

