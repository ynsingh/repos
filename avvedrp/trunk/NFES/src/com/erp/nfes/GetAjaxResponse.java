package com.erp.nfes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAjaxResponse extends HttpServlet {
	protected void doGet(HttpServletRequest requestObj, HttpServletResponse responseObj) throws IOException{
		
		//System.out.println("requestObj.action"+requestObj.getParameter("action"));
		String action=requestObj.getParameter("action");
		
		responseObj.setContentType("text/xml");        
        responseObj.setHeader("Cache-Control", "no-cache");
        PrintWriter writer = responseObj.getWriter(); 
        
		if(action.equals("SHOW_INSTITUTION")){
			String university_id=requestObj.getParameter("universityId");
			String sql="";
			String responseXMLInstitution="";			
			Connection connect = null;
			try{
				sql="SELECT  id,NAME  FROM institution_master WHERE active_yes_no=1 AND university_id="+ university_id +" ORDER BY NAME";	
				ConnectDB conObj=new ConnectDB();
				connect = conObj.getMysqlConnection();
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				responseXMLInstitution=responseXMLInstitution+"<reponseXML>";				
				while (rs.next()){
					responseXMLInstitution=responseXMLInstitution+rs.getString("id")+"~"+rs.getString("name") +"|";					
				}
			   responseXMLInstitution=responseXMLInstitution+"</reponseXML>";			   
			   //System.out.println(responseXMLInstitution);
		       writer.println(responseXMLInstitution);	        
			   writer.close();  
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
							
		}
		else if(action.equals("SHOW_DEPARTMENTS")){

			String institution_id=requestObj.getParameter("institutionId");
			String sql="";
			String responseXMLDepartments="";			
			Connection connect = null;
			try{
				sql="SELECT department_master.id,fld_value,institution_id FROM `general_master` INNER JOIN `department_master` ON  `general_master`.id=department_master.department_id  AND department_master.institution_id="+institution_id + "  AND  department_master.active_yes_no=1 ORDER BY fld_value";	
				ConnectDB conObj=new ConnectDB();
				connect = conObj.getMysqlConnection();
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				responseXMLDepartments=responseXMLDepartments+"<reponseXML>";
				
				while (rs.next()){
					responseXMLDepartments=responseXMLDepartments+rs.getString("id")+"~"+rs.getString("fld_value") +"|";					
				}
			   responseXMLDepartments=responseXMLDepartments+"</reponseXML>";			   
			   //System.out.println(responseXMLDepartments);
		       writer.println(responseXMLDepartments);	        
			   writer.close();  
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
							
		
		}else if(action.equals("SHOW_FACULTIES")){
			String institutionDept_id=requestObj.getParameter("institutionDeId");
			String sql="";
			String responseXMLDepartments="";			
			Connection connect = null;
			try{
				sql="SELECT userid,full_name FROM staff_profile_masterdetails_v0_values WHERE  department_id="+institutionDept_id + "  ORDER BY full_name";
				//System.out.println(sql);
				ConnectDB conObj=new ConnectDB();
				connect = conObj.getMysqlConnection();
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				responseXMLDepartments=responseXMLDepartments+"<reponseXML>";				
				while (rs.next()){
					responseXMLDepartments=responseXMLDepartments+rs.getString("userid")+"~"+rs.getString("full_name") +"|";					
				}
			   responseXMLDepartments=responseXMLDepartments+"</reponseXML>";			   
			   //System.out.println(responseXMLDepartments);
		       writer.println(responseXMLDepartments);	        
			   writer.close();  
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("SHOW_FACULTY_DEAILS")){
			String userId=requestObj.getParameter("userId");
			String sql="";
			String responseXMLDepartments="";			
			Connection connect = null;
			try{
				sql="SELECT Designation,join_date FROM staff_profile_masterdetails_v0_values WHERE  userid="+userId + "  ORDER BY full_name";
				//System.out.println(sql);
				ConnectDB conObj=new ConnectDB();
				connect = conObj.getMysqlConnection();
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(sql);
				responseXMLDepartments=responseXMLDepartments+"<reponseXML>";
				
				while (rs.next()){
					responseXMLDepartments=responseXMLDepartments+rs.getString("Designation")+"~"+rs.getString("join_date");					
				}
			   responseXMLDepartments=responseXMLDepartments+"</reponseXML>";			   
			   //System.out.println(responseXMLDepartments);
		       writer.println(responseXMLDepartments);	        
			   writer.close();  
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}			
	}else if(action.equals("GET_URL_FIELDS")){
		String tablename=requestObj.getParameter("tablename");
		//System.out.println("tablename:"+tablename);	
		String sql="";
		String responseXMLDepartments="";			
		Connection connect = null;
		try{
			sql="SELECT NAME FROM "+tablename+"_items WHERE itemtype='URL'";
			//System.out.println(sql);
			ConnectDB conObj=new ConnectDB();
			connect = conObj.getMysqlConnection();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			responseXMLDepartments=responseXMLDepartments+"<reponseXML>";
			
			while (rs.next()){
				responseXMLDepartments=responseXMLDepartments+rs.getString("NAME")+"~";					
			}
		   responseXMLDepartments=responseXMLDepartments+"</reponseXML>";			   
		   //System.out.println(responseXMLDepartments);
	       writer.println(responseXMLDepartments);	        
		   writer.close();  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}		
	}
}
}
