package com.erp.nfes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class InstitutionTransfer extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		Connection conn=null;
		
		try{
			ConnectDB conObj=new ConnectDB();
			conn=conObj.getMysqlConnection();
			String userId=request.getParameter("faculty_name");
			String relieve_date=request.getParameter("relieve_date");
			String joining_date=request.getParameter("joining_date");
			String designationId=request.getParameter("designation");
			String department_id=request.getParameter("new_department");
			
			String sql = "UPDATE staff_master SET active_yesno=0,relieve_date='"+relieve_date+"',record_staus='T' WHERE userid="+userId + " and active_yesno=1";
			//System.out.println(sql);
			Statement smt_update = conn.createStatement();
			smt_update.execute(sql);			
			
			sql="INSERT INTO staff_master(userid,department_id,designation_id,join_date,active_yesno,record_staus)";
			sql=sql+"VALUES("+userId+","+department_id+","+designationId+",'"+joining_date+"',1,'T')";
			//System.out.println(sql);
			Statement smt_insert = conn.createStatement();
			smt_insert.execute(sql);
			
			response.sendRedirect(request.getContextPath() + "/jsp/InstitutionTransfer.jsp?Saved=1");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
	}
	
}
