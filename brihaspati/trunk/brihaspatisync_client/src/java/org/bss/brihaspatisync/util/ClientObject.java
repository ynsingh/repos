package org.bss.brihaspatisync.util;

/**
 * ClientObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010-2011-2012,2013 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import org.bss.brihaspatisync.http.HttpCommManager;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * This class is used to store objects which are needed in runtime by this client.
 */

public class ClientObject {

	private static ClientObject cb=null;
	private float desktopquality = 0.5f;
	private float insquality = 0.5f;
	private float stdquality = 0.5f;
	
	private String indexServerName="";
	
	private String lect_id="";
	private String localIP="";
	private String usr_name="";
	private String usr_role="";
	private String reflector_ip=null;
	private String courseForAnnounce="";
	private String parent_reflector_ip="";
	private String selectedUserlistname="";
	
	private Vector usrNameVector=null;
	private Vector usrStatusVector=null;
	private Vector indexServerList=null;
		
	public static ClientObject getController(){
		if(cb==null)
			cb=new ClientObject();
		return cb;
	}
	
        public void setParentReflectorIP(String value){
                parent_reflector_ip=value;
        }
		
	public String getParentReflectorIP(){
		if(!parent_reflector_ip.equals("")){
	                return parent_reflector_ip;
		}
		return null;
        }

	protected void setReflectorIP(String value){
                reflector_ip=value;
        }

        public String getReflectorIP(){
                if( reflector_ip!=null ){
                        return reflector_ip;
                }
                return reflector_ip;
        }

	/**
	 * This method is used to retrive the list of index server by calling connectToMasterServer() 
	 * from HttpCommManager which initiate a connection with master server and get indexing server 
	 * list.
	 */
	public Vector getIndexServerList(){
		if(indexServerList == null)
			indexServerList=HttpCommManager.connectToMasterServer();
		return indexServerList;
	}
	
	/**
         * This method is used to get index server name which is choosen by this client for login authentication.
         */
	public String getIndexServerName(){
		return indexServerName;
	}
	
	/**
         * This method is used to retrive the list of sessions for all course in which this client is treated as a student
         * as well as instructor from HttpCommManager.
         */
	public Vector getSessionList(Vector course, String indexServer){
		return HttpsUtil.getSessionForCourse(course, indexServer);
	}

	/**
         * This method is used to retrive the list of sessions for course in which this client is treated as a student,
         * from HttpCommManager.
         */
        public Vector getStudCourseList(){
                return HttpCommManager.getStudCourseList();
       }

        /**
         * This method is used to retrive the list of sessions for course in which this client is treated as a instructor,
         * from HttpCommManager.
         */
        public Vector getInstCourseList(){
		return HttpCommManager.getInstCourseList();	
        }

	/**
       	 * This method is used to retrive the list of sessions for course in which this client is treated as a student,
         * from HttpCommManager.
         */
	public Vector getStudSessionList(){
		return HttpCommManager.getStudSessionList();
	}

	/**
         * This method is used to retrive the list of sessions for course in which this client is treated as a instructor,
	 * from HttpCommManager.
         */
	public Vector getInstSessionList(){
		return HttpCommManager.getInstSessionList();
        }

     	/**
         * This method is used to get user name of this client which is used to login authentication from login window.
         */
	public String getUserName(){
		return usr_name;
	}
	
	public String getwelcomeUserName(){
                String name_new=usr_name;
                try {
                        if(name_new.length()>9){
                                name_new=name_new.substring(0,7);
                                name_new=name_new+"..";
                        }
                }catch(Exception e){}
                return name_new;
        }


	/**
         * This method is used to get user role of this client.
         */
	public String getUserRole(){
		return usr_role;
	}
	
	/**
         * This method is used to send username and password to indexSerName which match these username
	 * and password to database for client authentication.if authentication is successfull this method 
	 * return true otherwise it return false.
         */
	public boolean getAuthentication(String indexSerName, String usr, String pass){
		boolean value =HttpCommManager.connectToIndexServer(indexSerName,usr,pass);
		return value;
	}

	/**
         * This method is used to get lecture id of lecture, which is joined by this client from InstSessionPanel or StudSessionPanel.
         */
	public String getLectureID(){
		return lect_id;
	}

	/**
         * This method is used to get Parent peer ip address to start router entry, from JoinSession class.
         */

	/**
         * This method is used to get course name from InstructorCSPanel which is used to announce new session in this course.
         */
	public String getCourseForAnnounce(){
		return courseForAnnounce;
	}
	
	/**
         * This method is used store all user status to change icons in userlist.
         */
	public Vector getUsrStatusVector(){
		return usrStatusVector;
	}

	/**
         * This method is used store user name to display all user in user list those are joined this Lecture.
         */
	public Vector getUsrNameVector(){
		return usrNameVector;
	}

	public void setIndexServerName(String value){
		this.indexServerName=value;
        }

        public void setUserName(String value){
		this.usr_name=value;
        }

        public void setUserRole(String value){
		this.usr_role=value;
        }

        public void setLectureID(String value){
		value=value.replace("[","");	
		value=value.replace("]","");	
		this.lect_id=value;
        }


	public void setCourseForAnnounce(String value){
                this.courseForAnnounce=value;
        }

	public void setUsrStatusVector(Vector value){
		if(usrStatusVector!=null)
			usrStatusVector.clear();
		this.usrStatusVector=value;
	}

	public void setUsrNameVector(Vector value){
                if(usrNameVector!=null)
                        usrNameVector.clear();
                this.usrNameVector=value;
        }
	public void setLocalIP(String str){
		this.localIP=str;
	}
	
	public String getLocalIP(){
                return this.localIP;
        }

	private Vector LectureInfo=null;
	private int LectureInfoindex=-1;
	
	public void setLectureInfo(Vector info){
                this.LectureInfo=info;
        }

	public Vector getLectureInfo(){
                return LectureInfo;
        }
	
	public void setLectureInfoIndex(int LectureInfoindex){
                this.LectureInfoindex=LectureInfoindex;
        }
	
        public int getLectureInfoIndex(){
                return LectureInfoindex;
        }
	
	public void setDesktopImageQuality(float quality) {
		this.desktopquality = quality;
	}
	
	public float getDesktopImageQuality(){
		return desktopquality;
        }
	
	public void setInsImageQuality(float quality) {
                this.insquality = quality;
        }

        public float getInsImageQuality(){
                return insquality;
        }

	public void setStdImageQuality(float quality) {
                this.stdquality = quality;
        }

        public float getStdImageQuality(){
                return stdquality;
        }
	
	public void setSelectedUserListname(String str){
                selectedUserlistname=str;
        }

        public String getSelectedListUsername(){
                return selectedUserlistname;
        }
}

