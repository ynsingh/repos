package com.erp.nfes;

import java.sql.*;

public class GetRecordValue{
	Connection conn=null;
	Statement theStatement=null;
	ResultSet theResult=null;
	
	public GetRecordValue(){
		ConnectDB conObj=new ConnectDB();
		conn = conObj.getMysqlConnection();
	}
    
	public String getRole(String userName){
		String answer="";
    	 try{    		 
 		     theStatement=conn.createStatement();
 		     theResult=theStatement.executeQuery("SELECT authority FROM authorities WHERE username='"+ userName +"'");
 		     while(theResult.next()){
 		    	 answer=  theResult.getString("authority");
 		     } 
    	 }catch(Exception e){
    	     e.printStackTrace();
    	 }
		return answer;
     }
    
	public String getUniversity(String userName){
		String answer="";
   	 try{
		     theStatement=conn.createStatement();
		     theResult=theStatement.executeQuery("SELECT university FROM `staff_profile_masterdetails_v0_values` WHERE username='"+ userName +"'");
		     while(theResult.next()){
		    	 answer= theResult.getString("university");
		     } 
	 }catch(Exception e){
	     e.printStackTrace();
	 }
	return answer;
	}

	public String getUniversityID(String userName){
		String answer="";
	   	 try{
			     theStatement=conn.createStatement();
			     theResult=theStatement.executeQuery("SELECT university_id FROM `staff_profile_masterdetails_v0_values` WHERE username='"+ userName +"'");
			     while(theResult.next()){
			    	 answer= theResult.getString("university_id");
			     } 
		 }catch(Exception e){
		     e.printStackTrace();
		 }
		return answer;
		}
	
	public String getDesignation(String userId){
		String answer="";
	   	 try{
			     theStatement=conn.createStatement();
			     theResult=theStatement.executeQuery("SELECT designation FROM `staff_profile_masterdetails_v0_values` WHERE userid="+ userId);
			     while(theResult.next()){
			    	 answer= theResult.getString("designation");
			     } 
		 }catch(Exception e){
		     e.printStackTrace();
		 }
		return answer;		
	}
	
}