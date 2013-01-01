package org.iitk.livetv;

/*@(#)ProcessRequest.java
 *
 * See licence file for usage and redistribution terms
 * Copyright (c) 2012-2013.All Rights Reserved.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Timer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import java.text.ParsePosition;

import java.net.InetAddress;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import org.apache.torque.Torque;
import org.apache.torque.om.NumberKey;
import org.apache.torque.util.Criteria;

import org.iitk.livetv.util.ServerLog;
import org.iitk.livetv.util.ServerUtil;
import org.iitk.livetv.util.EncryptionUtil;
import org.iitk.livetv.schedular.TimerOfDatabase;

import org.iitk.livetv.om.UserPeer;
import org.iitk.livetv.om.User;
import org.iitk.livetv.om.CategoryPeer;
import org.iitk.livetv.om.Category;
import java.sql.Date;
 /**
  * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
  */

public class ProcessRequest extends HttpServlet {
	
   	private Timer dbTimer=null;
	private static Hashtable table;
	private Torque set=null;
	private ServletContext context=null;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		if(set==null){
			try{
				set=new Torque();
				context = getServletContext();
				ServerLog.getController().setContext(context);
				String tempFileName =context.getRealPath("Torque.properties");
                                set.init(tempFileName);
		      		dbTimer=new Timer(true);
				ServerLog.getController().Log("database connection ");
               		}catch(Exception dberror){ServerLog.getController().Log("Error in database connection "+dberror.getMessage());}
		}
		
			
		try{
  			dbTimer.schedule(TimerOfDatabase.getController(), 1000, 60*60*1000);
 		}catch(Exception e){ ServerLog.getController().Log("Error in database Scheduler "+e.getMessage());}

			
		String reqType=request.getParameter("req");
		PrintWriter out = response.getWriter();

		if(reqType.equals("userReg")){

                        String name=request.getParameter("name");
			String email=request.getParameter("email");
                        String pass=request.getParameter("pass");
                        String gender=request.getParameter("gender");

                        String dob_mm=request.getParameter("mm");
                        String dob_dd=request.getParameter("dd");
                        String dob_yy=request.getParameter("yy");
                        String dob_date=dob_yy+"-"+dob_mm+"-"+dob_dd;
                        java.sql.Date dob=java.sql.Date.valueOf(dob_date);

                        String ph_count_code=request.getParameter("ph1");
                        String ph_area_code=request.getParameter("ph2");
                        String ph_no=request.getParameter("ph3");
                        String phone=ph_count_code+ph_area_code+ph_no;

                        String addr=request.getParameter("addr");
                        String city=request.getParameter("city");
                        String state=request.getParameter("state");
                        String country=request.getParameter("country");
                        String zip=request.getParameter("zip");

			String message=userRegistration(name,email,pass,gender,dob,phone,addr,city,state,country,zip);
			response.setContentLength(message.length());
                       	out.println(message);
                       	out.flush();
                       	out.close();
						
		}else if(reqType.equals("login")){
			String userName=request.getParameter("usr");
	                String userPassword=request.getParameter("pass");
        		String ipAddress=request.getParameter("ip");

			String message=checkAuthentication(userName,userPassword);

			if(message.equals("loginfailed")){
				response.setContentLength(message.length());
               			out.println(message);
                       		out.flush();
                       		out.close();
			}else{
				/** Send the userid and  session key of the client for the authentication purpose each time 
				    when the request is coming from the client to the server This session key is creating by
				    using the server util.After sending the session key to the user also store this session
				    key as a key with the ipAddress of the client in the hash table for the handling
				    of logger,task which is related with the Handraise,query handling in future and
				    for the single session 
				*/
              			int key=ServerUtil.getController().generateSessionKey();
       		                message=message+"\n"+Integer.toString(key);
				/* send server date to client user for synchrozise clock with this server. */
			//	message=message+"\n"+ServerUtil.getController().getCurrentDate("");
				//ServerLog.getController().Log(message);
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
               	        	out.close();
                	        table=new Hashtable();
				table.put(ipAddress,new Integer(key));
			}//else

		}else if(reqType.equals("logout")){

		}else if(reqType.equals("getCategory")){
			Vector result=new Vector();
			result=getCategory();
			int resultSize=result.size();
			String message="";
			if(resultSize!=0){
				for(int i=0;i<resultSize;i++){
					if(i==0)
						message=result.elementAt(i).toString();
					else
						message=message+","+result.elementAt(i).toString();
				}
				response.setContentLength(message.length());
        	              	out.println(message);
                	        out.flush();
                        	out.close();
			}else{
				message="noCategory";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			}
		}else if(reqType.equals("getChannel")){

		}else if(reqType.equals("createChannel")){

		}else if(reqType.equals("playChannel")){

		}else if(reqType.equals("updateChannel")){
		
		}else if(reqType.equals("removeChannel")){
		
		}


	}//end of post method

	private String userRegistration(String name, String email, String pass, String gender, Date dob, String phone, String addr, String city, String state, String country, String zip){

		

		String result=null;
		   try{
        		boolean flag=false;
                       	Criteria criteria = new Criteria();
                       	criteria.addGroupByColumn(UserPeer.USER_ID);
                       	List registereduser=UserPeer.doSelect(criteria);
                      	if(registereduser.size() !=0){
                        	for(int i=0;i<registereduser.size();i++){
                                	User regusr=(User)(registereduser.get(i));
                                      	String username=regusr.getUsername();
				        if(username.equals(email)){
                                                	flag=true;
                                       	}
                           	}
                      	}

                        if(flag){
                        	result="UserExist";
                       	}else{
        			Criteria crit = new Criteria();
        			crit.add(UserPeer.FULL_NAME,name);
        			crit.add(UserPeer.USERNAME,email);
        			crit.add(UserPeer.PASSWORD,pass);
        			crit.add(UserPeer.EMAIL_ID,email);
        			crit.add(UserPeer.DATE_OF_BIRTH,dob );
        			crit.add(UserPeer.PHONE_NUMBER,phone);
        			crit.add(UserPeer.ADDRESS,addr);
        			crit.add(UserPeer.POSTCODE,zip);
        			crit.add(UserPeer.CITY,city);
       				crit.add(UserPeer.STATE,state);
        			crit.add(UserPeer.COUNTRY,country);
        			crit.setDistinct();
        			UserPeer.doInsert(crit);
        			result="Registeration_successfull  !!";
			}
        	}catch(Exception e){
			ServerLog.getController().Log("Exception in user registeration------->"+e.getMessage());
        	}
		return result;
	}
		
	/** If authorisation is successful then return client's USER_ID else return loginfailed */
	
	private String checkAuthentication(String username,String password){
		
		String result=null;
		List returnValue=null;
		try{
			 // use the server util for getting the password in string form from the MD5 
                        //String authPassword=EncryptionUtil.createDigest("MD5",password);

                        /** send the Login and Password to the Mysql Database for the authentication purpose */
                        try{
				Criteria crit = new Criteria();
                		crit.add(UserPeer.USERNAME,username);
                		crit.add(UserPeer.PASSWORD,password);
                		returnValue = UserPeer.doSelect(crit);
                   	}catch(Exception e){ServerLog.getController().Log("Error in Criteria = "+e.getMessage());}

                        /* if authorisation is unsuccessfull then return null in result vector */
                        if(returnValue.size()== 0) {
                                result="login_failed";
                     	}
                        /** If authorisation is successfull then put information of the client
                         *  in result vector.These information are such as userid of the client. 
			 */
                        else {
				result="login_successful";
				try {	
					User element=(User)returnValue.get(0);
        	                      	result=result+","+Integer.toString(element.getUserId());
				} catch(Exception ex) { ServerLog.getController().Log(ex.getMessage());}	
        		}

		} catch(Exception e) { ServerLog.getController().Log("Error in authentication in ProcessRequest class= "+e.getMessage());}
		ServerLog.getController().Log(result);
		return result;
		
	}

	public Vector getCategory(){
		Vector result=new Vector();
                try{
                	Criteria crit = new Criteria();
                        List category = CategoryPeer.doSelect(crit);
                        Iterator iterate = category.iterator();
                        while (iterate.hasNext()){
                               	Category cat=(Category)iterate.next();
                              	result.addElement(cat.getCategoryName());
                       	}
                      	
            	} catch(Exception e) { ServerLog.getController().Log("Error in get Category list"+e); }
                return result;
	}
	
}//end of class



