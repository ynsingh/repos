package com.erp.nfes;

//import java.io.FileInputStream;
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

public class ProfileCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    private static final String ENCRYPTION_KEY = "PortalUserEncryption.KEY";
   
	private Properties properties = null;
	
	public ProfileCreationServlet() {		
		super();
    }

	public void init() throws ServletException {
		super.init();    	
    	try{    		
    		//properties = new Properties();
    	//	properties.load(new FileInputStream("../conf/mail.properties"));    		
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
    	String Instid;
    	int status=0;
		try {
			
			Context initCtx = new InitialContext();			
			//DataSource ds = (DataSource) initCtx.lookup("java:/PortalDS");			
			user = request.getParameter("userName");
			pwd  = request.getParameter("userPassword");
			mail = request.getParameter("emailId");
			userFullName=request.getParameter("userFullName");
			Instid=request.getParameter("institution");
			//patient_status=request.getParameter("hcheck");
			//status=Integer.parseInt(patient_status);			
			RequestDispatcher rd;			
	        /*==================== Temporarily Commented on 21-12-2010
	        String captchaId = request.getSession().getId();			
	        String responseStr = request.getParameter("j_captcha_response");
	        System.out.println("\n\n========================validateCaptcha Commented===============");
			boolean valid = validateCaptcha(captchaId, responseStr);				
			 if( !valid ){
				rd = request.getRequestDispatcher("Account.jsp?invalidCaptch=1");
				rd.forward( request, response );	
				return;
			} 
			===========================END=============================*/			
			//conn = ds.getConnection(); 21-12-2010
			ConnectDB conObj=new ConnectDB(); //New 21-12-2010
			conn = conObj.getMysqlConnection();//New 21-12-2010			
			String criteria = "username = \"" + user + "\"";
	        if( existsUserRecord( criteria, conn ) ){	        	
				rd = request.getRequestDispatcher("jsp/Account.jsp?userExists=1");
				rd.forward( request, response );				
				return;
	        }	        
	        criteria = "email = \"" + mail + "\"";
	        if( existsUserRecord( criteria, conn ) ){	    
				rd = request.getRequestDispatcher("jsp/Account.jsp?emailExists=1");
				rd.forward( request, response );	
				return;
	        }	        
			//System.out.println(user+" "+pass+" "+mail+" "+fname+" "+lname+" "+mob+" "+hname+" "+city+" "+state+" "+zip+" "+country);
/*			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/appdb", "root","amma");
			Statement st=con.createStatement();*/			
			
	        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	        ShaPasswordEncoder passwordEncoder =  (ShaPasswordEncoder) context.getBean("passwordEncoderBean");			
	        String encodedPwd = passwordEncoder.encodePassword( pwd, user );	        
	        /*========= User Id Generation 22-12-2010 ================*/	        
	    	String nextSeqQuery = "select coalesce((max(id) + 1),1) as newid from users";
			//System.out.println("nextSeqQuery : "+nextSeqQuery);
			Statement sst = conn.createStatement();
			ResultSet Rset = sst.executeQuery(nextSeqQuery);
			Rset.next();
			int userid = Rset.getInt("newid");
			Rset.close();	        
	        /*==========================================================*/
			
			String qry = "INSERT INTO users( username, password, email,user_full_name,id,enabled,institution_id) VALUES ( ?, ?, ?, ?,?,1,?)";
			PreparedStatement insertStmt = conn.prepareStatement( qry );
			insertStmt.setString( 1, user );
			insertStmt.setString( 2, encodedPwd );
			insertStmt.setString( 3, mail );
			insertStmt.setString(4, userFullName);
			insertStmt.setInt(5,userid);
			insertStmt.setString(6,Instid);
			insertStmt.executeUpdate();
			
			/*==================Temporarily Commented the account activation using Mail Sending Procedure
			sendemail( request, mail, user, pwd );			
			response.sendRedirect("Accountconfirmation.jsp");
			==========================================================================*/
			
			/*============================User Actiavtion 22-12-2010 =======================*/
			//PreparedStatement st = conn.prepareStatement("UPDATE users SET enabled = 1 WHERE username = ?");
			//st.setString( 1, user ); code exists in the insertion of users table
			
			/* Commented on 10-02-2009 , activate only after role_assign
			assignDefaultPrivileges(conn, user, "ROLE_TELLER" );//For user authentication*/
			assignDefaultPrivileges(conn, user, "2" );//For Profile Creation 10-02-2011
			
			/*================ Creating Folder for storing uploaded files 19-02-2011============*/
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("../conf/fileuploadpath.properties"));
		    String url = properties.getProperty("DESTINATION_DIR_PATH");
		   	CreateDir createdirobj= new CreateDir();
			createdirobj.CreateFolder(url,Integer.toString(userid));
			createdirobj.CreateFolder(url+"/"+Integer.toString(userid), "photo");
			
			/*============================== End of 19-02-2011 =================================*/
			
			String val = "Your Registration Activated Successfully !";
			response.sendRedirect("jsp/ActivateProfile.jsp?successVal=" + val);
			/*==========================================================================*/
			
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
	private boolean validateCaptcha(String id, Object response) {
		try {			
			if (id == null || response == null || "".equals(id))
				{				
				return false;				
				}
			else
			{			
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());			
			GenericManageableCaptchaService captchaServiceBean =  (GenericManageableCaptchaService) context.getBean("captchaService");						
			Boolean b = captchaServiceBean.validateResponseForID( id, response );			
			return b.booleanValue();
			}
		} catch (CaptchaServiceException cse) {
			// fixes known bug in JCaptcha			
			return false;
		}
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
    			System.out.println("------" + ite.next() );
    		}
    		String hostIp = properties.getProperty("email.smtp.host");    		
    		int port = Integer.parseInt( properties.getProperty("email.smtp.port") );
    		String userId = properties.getProperty("email.smtp.userid");
    		String emailpwd = properties.getProperty("email.smtp.password");
    		//String activationUrl = props.getProperty( "email.activation.url" );    			
    		email.setFrom("noreply@amritatech.com");
    		email.setHostName( hostIp );    
    		email.setSmtpPort( port );
    		email.setAuthentication(userId, emailpwd);
    		email.addTo( emailAddr, emailAddr );
    		/*email.addCc("unnikrishnans@amritatech.com","unnikrishnans@amritatech.com");
    	email.addBcc("anoopnt@amritatech.com","anoopnt@amritatech.com");*/
    		email.setSubject("NFES Staff Registation Activation");    		
    		String scheme = request.getScheme();    		
    		String serverName = request.getServerName();    		
    		int portNumber = request.getServerPort();
    		String contextPath = request.getContextPath();    		
    		String actKey = generateActivationKey( login );    		
    		String activationUrl = scheme + "://" + serverName + ":" + portNumber + contextPath;    		    		
    		StringBuffer msg = new StringBuffer();
    		msg.append("<html><body><p>Hello!\n</p>")
    		.append("<p>This is an automatic message from NFES. Thanks for registering a user account with us!</p>")
    		.append("<p>To complete the process, please click the link below:\n </p>")
    		.append( activationUrl + "/ProfileActivationServlet?userKey=" + actKey )
    		.append("<br>\n\n(Please note: If this doesn't appear as a link, no stress, just copy and paste it into your browser and press enter. Please make sure you get the entire link when you do this!)</br>")
    		.append("<br>\n\n** ACCOUNT INFORMATION ** </br>")
    		.append("\n\nYour account information is:")
    		.append("<br>\n\nUser name : " + login + "</br>")
    		.append("\n\nPassword  :" + passWd + "")
    		.append("\n\n<p>Thank you for using our Online Service. Wishing you all the success.</p>")
    		.append("<br>Administrator</br>")
    		.append("\nNFES </body></html>\n");    		
    		email.setHtmlMsg( msg.toString() );
    		//email.setHtmlMsg("<html><body> <p style='color:red;'> Click on the following link to activate your profile \n" + activationUrl + "/ActivateProfile.jsp?userKey=" + actKey + "</p> <body> </html>");
    		//email.setTextMsg("Click on the ");  
    	} else {
    		System.out.println("------Email Props not set------");    		
    	}
    	return email;
    }

/*    private Properties getPropertyUtils() {
    	Properties properties = null;
    	try{    		
    		properties = new Properties();
    		properties.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return properties; 
    }   */
    
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



/*====================22-12-2010 User Activation==================*/
private void assignDefaultPrivileges( Connection conn, String userLogin, String privilege ) throws SQLException{
	String insertStr = "INSERT INTO authorities ( username, authority ) VALUES ( ?, ? );";
	PreparedStatement insertRole = conn.prepareStatement( insertStr );
	insertRole.setString( 1, userLogin );
	insertRole.setString( 2, privilege );		
	insertRole.executeUpdate();
}

}