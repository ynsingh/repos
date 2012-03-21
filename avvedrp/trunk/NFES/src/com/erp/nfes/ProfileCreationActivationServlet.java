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
import java.util.Locale;
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

public class ProfileCreationActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ENCRYPTION_KEY = "PortalUserEncryption.KEY";

	private Properties properties = null;

	public ProfileCreationActivationServlet() {
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
    	String userFullName = "";
    	String pwd = "";
    	String mail = "";
    	String title="";
    	//String firstName="";
    	String lastName="";
    	String Instid="";
    	String department_id="";
    	String designation_id="";
    	String action="";
    	String universityName = "";
    	String shortName = "";
    	String address = "";
    	String location = "";
    	String param = "";
    	String role = "";
    	String joining_date="";
    	String openId="";
    	int status=0;
    	String language="";//(String) request.getSession().getAttribute("language");
    	MultiLanguageString ml=new MultiLanguageString();

		try {
			language=(String) request.getSession().getAttribute("language");
			response.setContentType("text/html; charset=UTF-8");
			Locale locale = new Locale(language, "");
			ml.init(language);

			Context initCtx = new InitialContext();
			//  DataSource ds = (DataSource) initCtx.lookup("java:/PortalDS");
			action = request.getParameter("action");
			if(action.equals("REGISTER_UNIVERSITY")){
				user = request.getParameter("username");
				pwd  = request.getParameter("password");
				mail = request.getParameter("email");
				title = request.getParameter("title");
				userFullName = request.getParameter("name");
				lastName = request.getParameter("lastname");
				universityName = request.getParameter("universityname");
				shortName = request.getParameter("shortname");
				address = request.getParameter("address");
				location = request.getParameter("location");
				openId=request.getParameter("openId");
				param = "title="+title+"&name="+userFullName+"&lastname="+lastName+"&username="+user+"&password="+pwd+"&confirmpassword="+pwd+
				"&email="+mail+"&universityname="+universityName+"&shortname="+shortName+"&address="+address+
				"&location="+location+"&openId="+openId;
			}else{
			  user = request.getParameter("userName");
			  pwd  = request.getParameter("userPassword");
			  mail = request.getParameter("emailId");
			  title = request.getParameter("title");
			  userFullName = request.getParameter("firstName");
			  lastName = request.getParameter("lastName");
			  Instid=request.getParameter("institution");
			  department_id=request.getParameter("department");
			  designation_id=request.getParameter("designation");
			  role = request.getParameter("role");
			  joining_date=request.getParameter("joining_date");
			  openId=request.getParameter("openId");
			}
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
			ConnectDB conObj=new ConnectDB();//New 21-12-2010
			conn = conObj.getMysqlConnection();//New 21-12-2010
			String criteria = "username = \"" + user + "\"";
	        if( existsUserRecord( criteria, conn ) ){
	        	if(action.equals("REGISTER_UNIVERSITY")){
	        		response.sendRedirect("./registerUser.jsp?action=ERROR_MESSAGE&code=1&"+param);
	        	}else{
	        	  response.sendRedirect("jsp/Account.jsp?action=CREATE_USER&userExists=1");
	        	}
	        	conn.close();
				return;
	        }
	        criteria = "email = \"" + mail + "\"";
	        if( existsUserRecord( criteria, conn ) ){
	          if(action.equals("REGISTER_UNIVERSITY")){
	            response.sendRedirect("./registerUser.jsp?action=ERROR_MESSAGE&code=2&"+param);
	          }else{
	            response.sendRedirect("jsp/Account.jsp?action=CREATE_USER&emailExists=1");
	          }
	            conn.close();
	            return;
	        }
	        if(isOpenIdExsts(conn, openId)){
	        	if(action.equals("REGISTER_UNIVERSITY")){
	        		response.sendRedirect("./registerUser.jsp?action=ERROR_MESSAGE&code=4&"+param);
	        	}else{
	        		response.sendRedirect("jsp/Account.jsp?action=CREATE_USER&openIdExists=1");
	        	}	
	        	conn.close();
	        	return;
	        }
	        if(action.equals("REGISTER_UNIVERSITY")&&isUniversityExists(conn,universityName)){
	        	response.sendRedirect("./registerUser.jsp?action=ERROR_MESSAGE&code=3&"+param);
	        	conn.close();
	        	return;
	        }

	        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	        ShaPasswordEncoder passwordEncoder =  (ShaPasswordEncoder) context.getBean("passwordEncoderBean");
	        String encodedPwd = passwordEncoder.encodePassword( pwd, user );
	        /*========= User Id Generation 22-12-2010 ================*/
	    	String nextSeqQuery = "select coalesce((max(id) + 1),1) as newid from users";
			Statement sst = conn.createStatement();
			ResultSet Rset = sst.executeQuery(nextSeqQuery);
			Rset.next();
			int userid = Rset.getInt("newid");
			Rset.close();
	        /*==========================================================*/
			String qry = "";
			if(action.equals("REGISTER_UNIVERSITY")){
			  qry = "INSERT INTO users( username, password, email,user_full_name,id,enabled,title,last_name) VALUES ( ?, ?, ?, ?,?,0,?,?)";
			}else{
			  qry = "INSERT INTO users( username, password, email,user_full_name,id,enabled,title,last_name) VALUES ( ?, ?, ?, ?,?,1,?,?)";
			}
			PreparedStatement insertStmt = conn.prepareStatement( qry );
			insertStmt.setString( 1, user );
			insertStmt.setString( 2, encodedPwd );
			insertStmt.setString( 3, mail );
			insertStmt.setString(4, userFullName);
			insertStmt.setInt(5,userid);
			insertStmt.setString(6,title);
			insertStmt.setString(7,lastName);
			insertStmt.executeUpdate();
			insertStmt.close();
			if(action.equals("REGISTER_UNIVERSITY")){
			  String institutionName = universityName + " Campus";
			  String institutionShortName = shortName + "C";
			  String universityId = createUniversityAccount(conn,universityName,shortName,address);
			  String institutionId = createInstitution(conn,institutionName,address,institutionShortName,location,universityId);
			 // String departmentId = "124";//Table:general_master, Category:Department, Value:Administration, Id:124
			  String departmentId = getMasterId(conn,"Department","Administration");
			  String institutionDepartmentId = createInstitutionDepartment(conn,institutionId,departmentId);
			 // String designationId = "125";//Table:general_master, Category:Designation, Value:Administrator, Id:125
			  String designationId = getMasterId(conn,"Designation","Administrator");
			  department_id = institutionDepartmentId;
			  designation_id = designationId;
			}
			qry = "INSERT INTO staff_master(userid,department_id,designation_id,join_date,active_yesno) VALUES ( ?, ?, ?,?,1)";
			insertStmt = conn.prepareStatement( qry );
			insertStmt.setInt(1,userid);
			insertStmt.setString(2,department_id);
			insertStmt.setString(3,designation_id);
			insertStmt.setString(4,joining_date);
			insertStmt.executeUpdate();
			System.out.println("openId :"+openId);
			if(!openId.equals("")){
				PreparedStatement openIdInstStmt=conn.prepareStatement("INSERT INTO user_openID_map(user_id,openId) VALUES(" + userid + ",'" + openId + "')");
				openIdInstStmt.executeUpdate();
				openIdInstStmt.close();				
			}
			/*========= Temporarily Commented the account activation using Mail Sending Procedure =========*/
			sendemail( request, mail, user, pwd, action );
			//response.sendRedirect("jsp/Accountconfirmation.jsp");
			/*==========================================================================*/


			if(action.equals("REGISTER_UNIVERSITY")){
				assignDefaultPrivileges(conn, user, "ROLE_ADMIN_UNIVERSITY" );

			}else{
			  /*============================Roll Creation =======================*/
			  assignDefaultPrivileges(conn, user, role );//For Profile Creation 03-05-2011
			}

			/*==============================Account Creation Information============================================*/
			String conPath = request.getContextPath();
			if(action.equals("REGISTER_UNIVERSITY")){
				 response.sendRedirect("./registerUser.jsp?action=SUCCESS_MESSAGE&code=1");
			}else{
			  String val =ml.getValue("account_creation_info"); // "You have Succesfully created an account. Account Information details sent to the user's email.";
			  response.sendRedirect(conPath + "/jsp/ActivateProfile.jsp?successVal=account_creation_info");
			}		
		}catch(Exception e)	{
			System.out.println("error "+e.toString());
		} 
		finally{
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
			rsPortalUsers.close();
			selectUserStmt.close();
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

    private void sendemail( HttpServletRequest request, String emailAddr, String login, String passWd,String action ) {
        HtmlEmail email;
		try {
			email = prepareEmail( request, emailAddr, login, passWd, action );
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    private HtmlEmail prepareEmail( HttpServletRequest request, String emailAddr, String login, String passWd,String action ) throws Exception {
    	HtmlEmail email = new HtmlEmail();
    	if( properties != null ){
    		for( Iterator ite = properties.keySet().iterator(); ite.hasNext();){
    			System.out.println("------" + ite.next() );
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
    		email.setSubject("NFES Staff Registration Activation");
    		String scheme = request.getScheme();
    		String serverName = request.getServerName();
    		int portNumber = request.getServerPort();
    		String contextPath = request.getContextPath();
    		String activationUrl = scheme + "://" + serverName + ":" + portNumber + contextPath;
    		String actKey = generateActivationKey( login );
    		StringBuffer msg = new StringBuffer();
    		if(action.equals("REGISTER_UNIVERSITY")){
    			msg.append("<html><body><p>Hello!\n</p>")
        		.append("<p>This is an automatic message from NFES. Thanks for registering a user account with us!</p>")
        		.append("<p>To complete the process, please click the link below:\n </p>")
        		.append("<a href=\""+ activationUrl + "/ProfileActivationServlet?userKey=" + actKey+"\">"+ activationUrl + "/ProfileActivationServlet?userKey=" + actKey+"</a>")
        		.append("<br>\n\n(Please note: If this doesn't appear as a link, no stress, just copy and paste it into your browser and press enter. Please make sure you get the entire link when you do this!)</br>");
    		}else{
    		  msg.append("<html><body><p>Hello!\n</p>")
    		  .append("<p>This is an automatic message from NFES. Your NFES account is scuccessfully created.</p>")
    		  .append("<p>Application URL :"+activationUrl+" </p>");
    		}
    		msg.append("<br>\n\n** ACCOUNT INFORMATION ** </br>")
    		.append("<br>\n\nUser name : " + login + "</br>")
    		.append("\n\nPassword  :" + passWd + "\n\n")
    		.append("<p><br>Administrator</br>")
    		.append("\nNFES </body></html>\n");
    		email.setHtmlMsg( msg.toString() );
    	} else {
    		System.out.println("------Email Props not set------");
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



/*====================22-12-2010 User Activation==================*/
private void assignDefaultPrivileges( Connection conn, String userLogin, String privilege ) throws SQLException{
	String insertStr = "INSERT INTO authorities ( username, authority ) VALUES ( ?, ? );";
	PreparedStatement insertRole = conn.prepareStatement( insertStr );
	insertRole.setString( 1, userLogin );
	insertRole.setString( 2, privilege );
	insertRole.executeUpdate();
}

 private String createUniversityAccount(Connection conn,String universityName,String shortName,String address){
   String id = "";
   try{
     Statement theStatement=conn.createStatement();
	 theStatement.executeUpdate("insert into university_master(name,short_name,address,active_yes_no)" +
	 " values('"+universityName+"','"+shortName+"','"+address+"',1)");
	 ResultSet rs = theStatement.executeQuery("SELECT id FROM university_master where name='"+universityName+"'");
	 while( rs.next() ){
			id = rs.getString("id");
	 }
     rs.close();
	 theStatement.close();//Close statement
   }catch(Exception e){
     e.printStackTrace();
   }
   return id;
 }

 private boolean isUniversityExists(Connection conn,String universityName){
	 boolean res = false;
	 try{
		 Statement theStatement = conn.createStatement();
		 ResultSet rs = theStatement.executeQuery("SELECT * FROM university_master WHERE name='"+universityName+"'");
			while( rs.next() ){
					res = true;
			}
		rs.close();
		theStatement.close();
	 }catch(Exception e){
	     e.printStackTrace();
	 }
	 return res;
 }

 private String createInstitution(Connection conn,String institutionName,String address,String shortName,String location,String universityId){
	 String id = "";
	 try{
	     Statement theStatement=conn.createStatement();
		 theStatement.executeUpdate("insert into institution_master(name,address,short_name,location,university_id,active_yes_no)" +
		 " values('"+institutionName+"','"+address+"','"+shortName+"','"+location+"',"+universityId+",1)");
		 ResultSet rs = theStatement.executeQuery("SELECT id FROM institution_master where name='"+institutionName+"'");
		 while( rs.next() ){
				id = rs.getString("id");
		 }
	     rs.close();
		 theStatement.close();//Close statement
	   }catch(Exception e){
	     e.printStackTrace();
	   }
	   return id;
 }
 private String createInstitutionDepartment(Connection conn,String institutionId,String departmentId){
   String id = "";
   try{
	   Statement theStatement=conn.createStatement();
	   theStatement.executeUpdate("insert into department_master(institution_id,department_id,active_yes_no)" +
	   " values("+institutionId+","+departmentId+",1)");
	   ResultSet rs = theStatement.executeQuery("SELECT id FROM department_master where institution_id="+institutionId+" and department_id="+departmentId);
	   while( rs.next() ){
			id = rs.getString("id");
	   }
	   rs.close();
	   theStatement.close();//Close statement
   }catch(Exception e){
	     e.printStackTrace();
   }
   return id;
 }
 private String getMasterId(Connection conn,String category,String value){
	String id = "";
	try{
	  Statement theStatement=conn.createStatement();
	  ResultSet rs = theStatement.executeQuery("SELECT id FROM general_master where category='"+category+"' and fld_value='"+value+"'");
	  while( rs.next() ){
		id = rs.getString("id");
	  }
	  rs.close();
	  theStatement.close();//Close statement
	}catch(Exception e){
		     e.printStackTrace();
	}
	return id;
 }

 private boolean isOpenIdExsts(Connection conn,String openId){	
	 	boolean openidxists;
		openidxists=false;
		try{
		  Statement theStatement=conn.createStatement();
		  ResultSet rs = theStatement.executeQuery("SELECT * from user_openID_map where openid='"+openId+"'");
		  while( rs.next() ){
			  openidxists=true;
		  }
		  rs.close();
		  theStatement.close();//Close statement
		  return openidxists;
		}catch(Exception e){
			     e.printStackTrace();
		}
		return openidxists;		
	 } 
}