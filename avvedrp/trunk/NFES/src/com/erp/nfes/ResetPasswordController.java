package com.erp.nfes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.erp.nfes.StringEncrypter.EncryptionException;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    private static final String ENCRYPTION_KEY = "PortalUserEncryption.KEY";
   
	private Properties properties = null;
	
	public ResetPasswordController() {		
		super();
    }

	public void init() throws ServletException {
		super.init();    	
    	try{    		
    		String propFileName = "mail.properties";
  		    properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);   		
    	}catch(Exception ex){
    		ex.printStackTrace();    		
    	}
	}
	
	/**
	 * @throws SQLException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        Connection conn = null;
    	String user = "";    	
    	String pwd = "";
    	String mail = "";
    	String userFullName="";
		ResultSet rset = null;
		Statement stmt = null;
    	int status=0;
		try {
			ConnectDB conObj=new ConnectDB(); 
			conn = conObj.getMysqlConnection();
			user = request.getParameter("userName");
			/*mail=user;			
			mail = request.getParameter("email");*/
			//System.out.println("======"+user);
			//user="";
			String criteria = "username = \"" + user + "\"";
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT username,email FROM users WHERE " + criteria);
			while (rset.next()) {
				user=rset.getString(1);
				mail=rset.getString(2);
			}
			//System.out.println("======"+user);
			
	        if( existsUserRecord( criteria, conn ) ){	        	
	        	pwd=resetUserPassword(conn,user);
				sendemail( request, mail, user, pwd );			
				response.sendRedirect("ForgotPwdSuccess.jsp");			
				//String val = "Your Registration Activated Successfully !";
				//response.sendRedirect("ActivateProfile.jsp?successVal=" + val);
	        }
			else
			{	//System.out.println("======Invalid User Name");
				//String val = "Invalid User Name!!!";
				response.sendRedirect("ForgotPassword.jsp?invalidUser=1");
	        }
		}catch(Exception e)	{
			System.out.println("error "+e.toString());
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private boolean existsUserRecord( String criteria, Connection con ){
		String qry = "SELECT * FROM users WHERE " + criteria;
		Statement selectUserStmt;
		boolean res = false;
		try {
			selectUserStmt = con.createStatement();			
			ResultSet rsPortalUsers = selectUserStmt.executeQuery( qry );
			while( rsPortalUsers.next() ){					
					res = true;
			} 
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		return res;		
	}
	
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, 
            IOException {        
        doPost(request,response);
    }
    
    private void sendemail( HttpServletRequest request, String emailAddr, String login, String passWd  ) {
        HtmlEmail email;
		try {
			email = prepareEmail( request, emailAddr, login, passWd );
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    private HtmlEmail prepareEmail( HttpServletRequest request, String emailAddr, String login, String passWd ) throws Exception {    	
    	HtmlEmail email = new HtmlEmail();
    	if( properties != null ){
    		for( Iterator ite = properties.keySet().iterator(); ite.hasNext();){
    			//System.out.println("------" + ite.next() );
    			ite.next();
    		}
    		String hostIp = properties.getProperty("email.smtp.host");    		
    		int port = Integer.parseInt( properties.getProperty("email.smtp.port") );
    		String userId = properties.getProperty("email.smtp.userid");
    		String emailpwd = properties.getProperty("email.smtp.password");    		
    		email.setFrom("noreply@NFES");
    		email.setHostName( hostIp );    
    		email.setSmtpPort( port );
    		email.setAuthentication(userId, emailpwd);
    		email.addTo( emailAddr, emailAddr );
    		email.setSubject("NFES Forgot login details");    		
    		String scheme = request.getScheme();    		
    		String serverName = request.getServerName();    		
    		int portNumber = request.getServerPort();
    		String contextPath = request.getContextPath();    		
    		String actKey = generateActivationKey( login );    		
    		String activationUrl = scheme + "://" + serverName + ":" + portNumber + contextPath;    		    		
    		StringBuffer msg = new StringBuffer();
    		msg.append("<html><body><p>Hello!\n</p>")    		
    		.append("<p>As per your 'Forgot Password' request, Your NFES account password has been reset and your account details listed below :\n</p>")
    		.append("<br>\n\n** NEW PASSWORD INFORMATION ** </br>")
    		.append("\n\nNew Password  :" + passWd + "")    		
    		.append("\n\n<p> We recommend you to change this password\n</p>")    		    		
    		.append("\n\n<p>Thank you for using our Online Service. Wishing you all the success.</p>")
    		.append("<br>Administrator</br>")
    		.append("\nNFES </body></html>\n");
    		email.setHtmlMsg( msg.toString() );
    	} else {
    		//System.out.println("------Email Props not set------");    		
    	}
    	return email;
    }


    
    private String generateActivationKey( String userLogin ){    	
		StringEncrypter encrypter;
		String encryptedString = null;
		try {			
			encrypter = new StringEncrypter( StringEncrypter.DESEDE_ENCRYPTION_SCHEME , ENCRYPTION_KEY );
			encryptedString = encrypter.encrypt( userLogin );
		} catch (EncryptionException e) {
			e.printStackTrace();
		}		

		//assertEquals( "Ni2Bih3nCUU=", encryptedString );
		encryptedString = encryptedString.replace( '+', '_' );
		//System.out.println("Activation Key : " + encryptedString );    	
    	return encryptedString;    	
    }  


private String resetUserPassword( Connection conn, String userName ){
	String randomPwd = String.valueOf( new Double( Math.random() * 1000000 ).intValue() );
	randomPwd = "app" + randomPwd;
	
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    ShaPasswordEncoder passwordEncoder =  (ShaPasswordEncoder) context.getBean("passwordEncoderBean");
	
    String encodedPwd = passwordEncoder.encodePassword( randomPwd, userName );
    
    String qry = "UPDATE users SET password = ? WHERE username = ?";
    try {
		PreparedStatement updateStmt = conn.prepareStatement( qry );
		updateStmt.setString( 1, encodedPwd );
		updateStmt.setString( 2, userName );
		updateStmt.executeUpdate();
	} catch (SQLException e) {			
		e.printStackTrace();
	}
	//System.out.println("================end of pwd reset==========");
	return randomPwd;		
}

}