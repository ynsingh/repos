package com.erp.nfes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;

public class ProfileActivationServlet extends HttpServlet{
	
	private static final String ENCRYPTION_KEY = "PortalUserEncryption.KEY";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		//System.out.println("------ " + request.getParameter( "userKey") );
		Context initCtx;
		Connection conn = null;
		try {
			initCtx = new InitialContext();
			
			//DataSource ds = (DataSource) initCtx.lookup("java:/PortalDS");
			//conn = ds.getConnection();
			ConnectDB conObj=new ConnectDB(); //New 21-12-2010
			conn = conObj.getMysqlConnection();//New 21-12-2010		
			
			String val;
			String usrKey = request.getParameter( "userKey");
			usrKey = usrKey.replace('_' , '+');
			String userLogin = generateLoginFromActivationKey( usrKey );
			
			if( isUserActivated( conn, userLogin ) ){
				val = "Your Registration is already activated !";
				response.sendRedirect("jsp/ActivateProfile.jsp?successVal=" + val);
				return;
			}
			PreparedStatement selectSt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			selectSt.setString( 1, userLogin );
			ResultSet rsUsers = selectSt.executeQuery();
			
			/* 21-12-2010
			  int hasMrd = 0;			  
			 if( rsUsers.next() ){
				hasMrd = rsUsers.getInt("patient_status");
			}
			*/
			
			PreparedStatement st = conn.prepareStatement("UPDATE users SET enabled = 1 WHERE username = ?");

			st.setString( 1, userLogin );
			
			//System.out.println("------ User Login " + userLogin );
			st.executeUpdate();
			
			//assignDefaultPrivileges(conn, userLogin, "ROLE_TELLER" );//For user authentication
			//if( hasMrd == 0 ){
				assignDefaultPrivileges(conn, userLogin, "2" );//For staff registration
			//} else {
			//	assignDefaultPrivileges(conn, userLogin, "PORTAL_MRD_LINK_REQUEST" );//For existing patient MRD linking request
			//}
				String conPath = request.getContextPath();
			val = "Your Registration Activated Successfully !";			
			response.sendRedirect(conPath + "/ActivateProfile.jsp?successVal=" + val);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void assignDefaultPrivileges( Connection conn, String userLogin, String privilege ) throws SQLException{
		String insertStr = "INSERT INTO authorities ( username, authority ) VALUES ( ?, ? );";
		PreparedStatement insertRole = conn.prepareStatement( insertStr );
		insertRole.setString( 1, userLogin );
		insertRole.setString( 2, privilege );		
		insertRole.executeUpdate();
	}
	
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
    	doPost(request,response);    	
    }
    
    private boolean isUserActivated( Connection con, String userName ){

    	String qry = "SELECT * FROM users WHERE username = ? AND enabled = 1";
    	PreparedStatement selectUserStmt;
    	boolean res = false;
    	ResultSet rsPortalUsers = null;
    	try {
    		selectUserStmt = con.prepareStatement( qry );	
    		selectUserStmt.setString( 1, userName );
    		rsPortalUsers = selectUserStmt.executeQuery();
    		while( rsPortalUsers.next() ){
    			res = true;
    		} 
    	} catch (SQLException e) {			
    		e.printStackTrace();
    	} finally{
    		try {
				rsPortalUsers.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
    	}

    	return res;		
    }
	private String generateLoginFromActivationKey( String actKey ) throws Exception	{
		StringEncrypter encrypter = new StringEncrypter( StringEncrypter.DESEDE_ENCRYPTION_SCHEME , ENCRYPTION_KEY );
		String decryptedString = encrypter.decrypt( actKey );
		//assertEquals( "test", decryptedString );		
		return decryptedString;
	}
}
