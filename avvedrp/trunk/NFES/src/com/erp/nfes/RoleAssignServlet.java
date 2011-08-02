/* Created on Feb 10, 2011
 * @author nfes
*/
package com.erp.nfes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleAssignServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username="";
		String role_name="";
		String val=null;
		Connection conn = null;
		try {
			username = request.getParameter("userName");
			int role_pos =(username.lastIndexOf("+"))+1; //Extract User Name from Username+role 
			username=username.substring(0,role_pos-1);
			
			role_name  = request.getParameter("role");
			ConnectDB conObj=new ConnectDB(); 
			conn = conObj.getMysqlConnection();
						
			PreparedStatement st = conn.prepareStatement("UPDATE authorities SET authority=(SELECT role_id FROM roles WHERE role_name=?) WHERE username = ?");
			st.setString( 1, role_name );
			st.setString( 2, username );						
			st.executeUpdate();
									
			val = "1";
			st.close();
			response.setContentType("text/html; charset=utf-8");
			response.sendRedirect("jsp/role_assign.jsp?value=" + val);
			
		}catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}	
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    	}
    
}