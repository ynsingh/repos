package org.bss.brihaspatisync.util;

/**
 * ClientObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010-2011-2012,2013,2015 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import org.bss.brihaspatisync.http.HttpCommManager;
import org.bss.brihaspatisync.gui.UserListPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar Pal </a>
 * This class is used to store objects which are needed in runtime by this client.
 */

public class ClientObject {

	private static float desktopquality = 0.5f;
	private static float insquality = 0.5f;
	private static float stdquality = 0.5f;
	
	private static String indexServerName="";
	
	private static String lect_id="";
	private static String localIP="";
	private static String usr_name="";
	private static String usr_role="";
	private static String reflector_ip=null;
	private static String courseForAnnounce="";
	private static String parent_reflector_ip="";
	private static String selectedUserlistname="";
	
	private static Vector usrNameVector=null;
	private static String user_full_name="";
	private static Vector usrStatusVector=null;
	private static Vector indexServerList=null;
	private static String parent_ref="";
	
        public static void setParentReflectorIP(String value){
                parent_reflector_ip=value;
        }
		
	public static String getParentReflectorIP(){
		if(!parent_reflector_ip.equals("")){
	                return parent_reflector_ip;
		}
		return null;
        }

	protected static void setReflectorIP(String value){
                reflector_ip=value;
        }

        public static String getReflectorIP(){
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
	public static Vector getIndexServerList(){
		if(indexServerList == null)
			indexServerList=HttpCommManager.connectToMasterServer();
		return indexServerList;
	}
	
	/**
         * This method is used to get index server name which is choosen by this client for login authentication.
         */
	public static String getIndexServerName(){
		return indexServerName;
	}
	
	/**
         * This method is used to retrive the list of sessions for all course in which this client is treated as a student
         * as well as instructor from HttpCommManager.
         */
	public static Vector getSessionList(Vector course, String indexServer){
		return HttpsUtil.getSessionForCourse(course, indexServer);
	}

	/**
         * This method is used to retrive the list of sessions for course in which this client is treated as a student,
         * from HttpCommManager.
         */
        public static Vector getStudCourseList(){
                return HttpCommManager.getStudCourseList();
       }

        /**
         * This method is used to retrive the list of sessions for course in which this client is treated as a instructor,
         * from HttpCommManager.
         */
        public static Vector getInstCourseList(){
		return HttpCommManager.getInstCourseList();	
        }

	/**
       	 * This method is used to retrive the list of sessions for course in which this client is treated as a student,
         * from HttpCommManager.
         */
	public static Vector getStudSessionList(){
		return HttpCommManager.getStudSessionList();
	}

	/**
         * This method is used to retrive the list of sessions for course in which this client is treated as a instructor,
	 * from HttpCommManager.
         */
	public static Vector getInstSessionList(){
		return HttpCommManager.getInstSessionList();        }

     	/**
         * This method is used to get user name of this client which is used to login authentication from login window.
         */
	public static String getUserName(){
		return usr_name;
	}
	
	public static String getwelcomeUserName(){
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
	public static String getUserRole(){
		return usr_role;
	}
	
	/**
         * This method is used to send username and password to indexSerName which match these username
	 * and password to database for client authentication.if authentication is successfull this method 
	 * return true otherwise it return false.
         */
	public static boolean getAuthentication(String indexSerName, String usr, String pass){
		boolean value =HttpCommManager.connectToIndexServer(indexSerName,usr,pass);
		return value;
	}

	/**
         * This method is used to get lecture id of lecture, which is joined by this client from InstSessionPanel or StudSessionPanel.
         */
	public static String getLectureID(){
		return lect_id;
	}

	/**
         * This method is used to get Parent peer ip address to start router entry, from JoinSession class.
         */

	/**
         * This method is used to get course name from InstructorCSPanel which is used to announce new session in this course.
         */
	public static String getCourseForAnnounce(){
		return courseForAnnounce;
	}
	
	/**
         * This method is used store all user status to change icons in userlist.
         */
	public static Vector getUsrStatusVector(){
		return usrStatusVector;
	}

	/**
         * This method is used store user name to display all user in user list those are joined this Lecture.
         */
	public static Vector getUsrNameVector(){
		return usrNameVector;
	}
	
	 public static String getUser_full_name(){
                return user_full_name;
        }


	public static void setIndexServerName(String value){
		indexServerName=value;
        }

        public static void setUserName(String value){
		usr_name=value;
        }

        public static void setUserRole(String value){
		usr_role=value;
        }

        public static void setLectureID(String value){
		value=value.replace("[","");	
		value=value.replace("]","");	
		lect_id=value;
        }

	public static void setCourseForAnnounce(String value){
                courseForAnnounce=value;
        }

	public static void setUsrStatusVector(Vector value){
		if(usrStatusVector!=null)
			usrStatusVector.clear();
		usrStatusVector=value;
	}

	public static void setUsrNameVector(Vector value){
                if(usrNameVector!=null)
                        usrNameVector.clear();
                usrNameVector=value;
        }
	//Set the user full name
	public static void setUser_full_name(String value){
               	user_full_name=value;
        }

	public static void setLocalIP(String str){
		localIP=str;
	}
	
	public static String getLocalIP(){
                return localIP;
        }

	private static Vector LectureInfo=null;
	private static int LectureInfoindex=-1;
	
	public static void setLectureInfo(Vector info){
                LectureInfo=info;
        }

	public static Vector getLectureInfo(){
                return LectureInfo;
        }
	
	public static void setLectureInfoIndex(int i){
                LectureInfoindex=i;
        }
	
        public static int getLectureInfoIndex(){

                return LectureInfoindex;
        }
	
	public static void setDesktopImageQuality(float quality) {
		desktopquality = quality;
	}
	
	public static float getDesktopImageQuality(){
		return desktopquality;
        }
	
	public static void setInsImageQuality(float quality) {
                insquality = quality;
        }

        public static float getInsImageQuality(){
                return insquality;
        }

	public static void setStdImageQuality(float quality) {
                stdquality = quality;
        }

        public static float getStdImageQuality(){
                return stdquality;
        }
	
	public static void setSelectedUserListname(String str){
                selectedUserlistname=str;
        }

        public static String getSelectedListUsername(){
                return selectedUserlistname;
        }
	
	/** 
	 * set grand parent reflector ip, if parent reflector is disconect
	 **/   	
        public static void setParentReflector(String str){
                parent_ref=str;
        }
	
        public static String getParentReflector(){
                return parent_ref;
        }
}

