package com.erp.nfes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

//import javax.naming.Context;
//import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * Servlet implementation class InsertPatientServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String user = request.getParameter("user");
		String user = request.getUserPrincipal().getName();
		String newPwd = request.getParameter("newpass");
		String currentPwd = request.getParameter("oldpass");
		String contextPath = request.getContextPath();		
		Connection conn=null;
		try	{
			ConnectDB conObj=new ConnectDB(); 
			conn = conObj.getMysqlConnection();	
			
			String encOldPwd = getUserEncodedPassword( currentPwd, user );
			String encNewPwd = getUserEncodedPassword( newPwd, user );
			
			String actualPwd = getCurrentPassword( user, conn );
			
			if( !encOldPwd.equals( actualPwd ) ){//Current password entered is not correct
				response.sendRedirect( contextPath + "/jsp/Myaccount.jsp?value=1" );
				return;
			}
			if( encOldPwd.equals( encNewPwd ) ){//Current and new passwords are same
				response.sendRedirect( contextPath + "/jsp/Myaccount.jsp?value=2" );
				return;
			}

			PreparedStatement pst = conn.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
			pst.setString( 1, encNewPwd );
			pst.setString( 2, user );
			pst.executeUpdate();
			
			response.sendRedirect(contextPath + "/jsp/Myaccount.jsp?value=0");
		}catch(Exception e)
		{
			System.out.println(e.toString());
			
		}
		finally{
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private String getCurrentPassword( String user, Connection con ){
		String qry = "SELECT * FROM users WHERE username = ?";
		
		String res = null;
		try {
			PreparedStatement selectUserStmt = con.prepareStatement( qry );		
			selectUserStmt.setString( 1, user );
			ResultSet rsPortalUsers = selectUserStmt.executeQuery();
			while( rsPortalUsers.next() ){
					res = rsPortalUsers.getString("password");
			} 
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		return res;		
	}
	private String getUserEncodedPassword( String pwd, String user ){
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ShaPasswordEncoder passwordEncoder =  (ShaPasswordEncoder) context.getBean("passwordEncoderBean");
		
        String encodedPwd = passwordEncoder.encodePassword( pwd, user );
		
        return encodedPwd;        
	}

}
