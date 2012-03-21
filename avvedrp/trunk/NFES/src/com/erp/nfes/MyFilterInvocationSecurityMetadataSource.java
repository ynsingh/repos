package com.erp.nfes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static List<DBTableRecord> interceptUrl;
	
	public MyFilterInvocationSecurityMetadataSource(){
		cacheDBTable();
	}
	
	public List<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        if (logger.isDebugEnabled()) {
			logger.debug("XXXX-URL is: '" + url + "' Method is: '" + httpMethod + "'");
		}
        int firstQuestionMarkIndex = url.indexOf("&");
        if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
        if (logger.isDebugEnabled()) {
        	logger.debug("XXXX-Candidate is: '" + url + "' Method is: '" + httpMethod + "'");
		}
        return SecurityConfig.createList(matchUrl(url,httpMethod));
    }

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

    public boolean supports(Class<?> clazz) {
      return FilterInvocation.class.isAssignableFrom(clazz);
    }
    
    public String[] matchUrl(String url,String httpMethod){
        String[] roles = null;
        try {
          String pattern = "";String matched = "False";
          for(int i=0;i<interceptUrl.size();i++){
          	pattern = interceptUrl.get(i).getPattern();
          	if(pattern.equals(url)){
              roles = interceptUrl.get(i).getAccess().split(",");matched = "True";	
          	}else if (pattern.lastIndexOf("*") == pattern.length()-1 && pattern.indexOf("*") ==  pattern.length()-2){
          	  if(url.length() >= pattern.length()-2){
          	    if(pattern.substring(0,pattern.indexOf("*")-1).equals(url.substring(0,pattern.indexOf("*")-1))){
          	      roles = interceptUrl.get(i).getAccess().split(",");matched = "True";
          	    }
          	  }
          	}
          	if (logger.isDebugEnabled()) {
                logger.debug("XXXX-Candidate is: '" + url + "' Pattern is: '" + pattern + "'" + " Matched: " + matched );
          	}
          	if(matched.equals("True")){
          	  break;	
          	}
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
        if(roles == null){	
          throw new AccessDeniedException("Access is denied");
        }else{
          return roles;
        }
      }
      
      public String arrayToString(String[] stringarray){
        String str = "[";
        if(stringarray.length == 1){
          str = str + stringarray[0];	  
        }else{
          for (int i = 0; i < stringarray.length; i++) {
            str = str + stringarray[i] + ",";
          }
          str = str.substring(0,str.length()-1);
        }
        
        return str+"]";
      }
      public static void cacheDBTable(){
    	interceptUrl = new ArrayList<DBTableRecord>();
        Connection conn;
        try {
          ConnectDB conObj = new ConnectDB();
          conn = conObj.getMysqlConnection();
          Statement theStatement = conn.createStatement();
          ResultSet theResult = theStatement.executeQuery("select pattern,access from intercept_url where enabled=1 order by sequence");
          while (theResult.next()) {
            DBTableRecord object = new DBTableRecord();
            object.setPattern(theResult.getString("pattern"));
            object.setAccess(theResult.getString("access"));
            interceptUrl.add(object);
          }
          theResult.close();
          theStatement.close();
          conn.close();
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
  }

 class DBTableRecord{
   private String pattern;
   private String access;
   
   public void setPattern(String p){
     pattern = p;	   
   }
   public void setAccess(String a){
     access = a;	   
   }
   public String getPattern(){
     return pattern;	   
   }
   public String getAccess(){
     return access;	   
   }
 }