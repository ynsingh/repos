package com.erp.nfes;

import java.sql.Connection;
import java.sql.*;

public class mlObj{
	Connection conn=null;
	Statement theStatement=null;
	ResultSet theResult=null;
	String[][] ls;
    int length;
     
    public void init(String fileName,String languageCode){
    	 try{
    		 ConnectDB conObj=new ConnectDB();
    	 	 conn = conObj.getMysqlConnection();
 		     theStatement=conn.createStatement();
 		     theResult=theStatement.executeQuery("SELECT control_name,language_string FROM language_localisation WHERE file_code=(SELECT id FROM file_master WHERE NAME=\'"+fileName+"\') AND language_code=\'"+languageCode+"\'");
 		     theResult.last();
 		     length=theResult.getRow();
 		     ls=new String[length][2];
 		     theResult.beforeFirst();int i=0;
 		     while(theResult.next()){
 		          ls[i][0]=theResult.getString("control_name");
 		          ls[i][1]=theResult.getString("language_string");
 		          i++;
 		     }
            
    	 }catch(Exception e){
    	     e.printStackTrace();
    	 }
     }
     public String getValue(String arg){
    	 String str="";
    	 for(int i=0;i<length;i++){
    	      if(arg.equals(ls[i][0])){
    	    	  str=ls[i][1];break;
    	      }
    	 }
    	 return str;	 
     }
}