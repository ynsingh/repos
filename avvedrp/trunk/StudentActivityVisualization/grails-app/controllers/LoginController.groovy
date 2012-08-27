import org.codehaus.groovy.grails.plugins.springsecurity.RedirectUtils

import org.springframework.security.AuthenticationTrustResolverImpl
import org.springframework.security.DisabledException
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.AbstractProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.util.Environment

import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt
import org.apache.commons.lang.StringUtils
import org.iitk.brihaspati.modules.utils.security.RemoteAuth

import groovy.sql.Sql

/**
 * Login Controller (Example).
 */
class LoginController {

	/**
	 * Dependency injection for the authentication service.
	 */
	def authenticateService
	def dataSource    
   
	/**
	 * Dependency injections for OpenIDConsumer and OpenIDAuthenticationProcessingFilter.
	 * Uncomment if using OpenID, ok to delete if not.
	 */
//	def openIDConsumer
//	def openIDAuthenticationProcessingFilter

	private final authenticationTrustResolver = new AuthenticationTrustResolverImpl()

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
		 GrailsHttpSession gh=getSession()
		if (authenticateService.isLoggedIn()) {
				    			
		             def ROLE		
					 def sql=new Sql(dataSource);		  
					 
					 gh.putValue("env",grails.util.Environment.getCurrent())  
					  
					  //getting the last transformation update time
					 def update = sql.firstRow("select id,date as LAST_UPDATE from last_update  order by id desc limit 0,1");
			         def last_updated=update.LAST_UPDATE;  
				     gh.putValue("last_update",last_updated)  
					 
					 					                          
                    if(authenticateService.ifAllGranted('ROLE_SUPERADMIN'))
					{						
						 ROLE="ROLE_SUPERADMIN"
						 gh.putValue("ROLE", ROLE);
						 redirect action: 'admindashboard', controller:'dashboard'
					}
					
					 if(authenticateService.ifAllGranted('ROLE_UNIVERSITY'))
					{	println("entered5");					
						
						 ROLE="ROLE_UNIVERSITY"
						 gh.putValue("ROLE", ROLE);						
						 redirect action: 'admindashboard', controller:'dashboard'
					}
					
					 if(authenticateService.ifAllGranted('ROLE_INSTITUTE'))
					{						 
						 ROLE="ROLE_INSTITUTE"
						 gh.putValue("ROLE", ROLE);
						 redirect action: 'institutedashboard', controller:'dashboard'
					}
					
					/*
					 if (authenticateService.ifAllGranted('ROLE_ADMIN')) 
					 {
					       ROLE="ROLE_ADMIN"
			        	   gh.putValue("ROLE", ROLE);   
                           redirect action: 'admindashboard', controller:'dashboard'
			          }
					  */
                           

					 if (authenticateService.ifAllGranted('ROLE_STAFF'))
					 {						
						  ROLE="ROLE_STAFF"
						  gh.putValue("ROLE", ROLE);
						  redirect action: 'staffdashboard', controller:'dashboard'
					 }
					         
					 
					 if (authenticateService.ifAllGranted('ROLE_STUDENT')) {
						 // println("entered5");
						 ROLE="ROLE_STUDENT"			        	 
			        	 gh.putValue("ROLE", ROLE);
			        	 redirect action: 'studentdashboard', controller:'dashboard'
			         }
                   
					
					 def userDetails = authenticateService.userDomain()
					 gh.putValue("currUserId", userDetails.getId());               
					 gh.putValue("UserId", userDetails.getUsername());                                      
                     def user_name=userDetails.getUsername().split("@");
                     gh.putValue("currUsername", user_name[0]);		    	   
					
				 }
			else {
			   // println("enterde6");
				redirect action: auth, params: params
	}
	}

	/**
	 * Show the login page.
	 * Remote Authentication using brihaspati
	 */
	def auth = {
			 GrailsHttpSession gh=getSession()					
		if(params.rand!=null)
		  {
                 
          // getting the logged in user using brihaspati authentication credentials     
                  String hdir=System.getProperty("user.home");
                  String path=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                  String line=ReadNWriteInTxt.readLin(path,"avv_dive");
                  String skey=StringUtils.substringBetween(line,";",";");
                  String serverurl=StringUtils.substringAfterLast(line,";");
                  String enUrl=EncrptDecrpt.decrypt(params.encd, "avv_dive")
		            def arr=enUrl.split('&');
		            String PersonID=arr[0];
		            PersonID=PersonID.substring(6,PersonID.length())
		              //  gh.putValue("UserLogin",PersonID) 
		                 def user=Person.find("from Person U where U.username='"+PersonID+"'");
		        if(user)
		          {
				             def ROLE		
							 def sql=new Sql(dataSource);		  
							 gh.putValue("env",grails.util.Environment.getCurrent())  
							  
							  //getting the last transformation update time
							 def update = sql.firstRow("select id,date as LAST_UPDATE from last_update  order by id desc limit 0,1");
					         def last_updated=update.LAST_UPDATE;  
						     gh.putValue("last_update",last_updated)  
		            	
		            	// getting the role	
						def userRoleInstance=sql.rows("SELECT AP.authority_id AS authority FROM  authority_people AP WHERE AP.person_id="+user.id)
						def role=userRoleInstance.authority
						def authorityInstance = Authority.find("from Authority A where A.id="+role[0])
						
					    gh.putValue("currUserId", user.getId());               
						gh.putValue("UserId", user.getUsername()); 
		                def user_name=user.getUsername().split("@");                                      
		                gh.putValue("currUsername", user_name[0]);	 
					
					if(authorityInstance.authority=='ROLE_SUPERADMIN')
					{						
						 ROLE="ROLE_SUPERADMIN"
						 gh.putValue("ROLE", ROLE);
						 redirect action: 'admindashboard', controller:'dashboard'
					}
					
					if(authorityInstance.authority=='ROLE_UNIVERSITY')
					 {		
						 ROLE="ROLE_UNIVERSITY"
						 gh.putValue("ROLE", ROLE);						
						 redirect action: 'admindashboard', controller:'dashboard'
					 }
					
					 if(authorityInstance.authority=='ROLE_INSTITUTE')
					{						 
						 ROLE="ROLE_INSTITUTE"
						 gh.putValue("ROLE", ROLE);
						 redirect action: 'institutedashboard', controller:'dashboard'
					}
	
					 if (authorityInstance.authority=='ROLE_STAFF')
					 {						
						  ROLE="ROLE_STAFF"
						  gh.putValue("ROLE", ROLE);
						  redirect action: 'staffdashboard', controller:'dashboard'
					 }
					         
					 
					 if (authorityInstance.authority=='ROLE_STUDENT') {
						 // println("entered5");
						 ROLE="ROLE_STUDENT"			        	 
			        	 gh.putValue("ROLE", ROLE);
			        	 redirect action: 'studentdashboard', controller:'dashboard'
			         }
							  
		         }
		  }	
			
		
			nocache response
	        //Setting language
			
			if(params.lang!=null)
			{
			 println  "params:"+params.lang
			 gh.putValue("lang",params.lang) 
			}
			else
			{
			 if(gh.getValue("lang")==null)
			 {
				 gh.putValue("lang","en")
			 }
			}
			
			def config = authenticateService.securityConfig.security
	
			if (authenticateService.isLoggedIn()) {
				redirect uri: config.defaultTargetUrl
				return
			}
	
			String view
			String postUrl
			if (config.useOpenId) {
				view = 'openIdAuth'
				postUrl = "${request.contextPath}/login/openIdAuthenticate"
			}
			else if (config.useFacebook) {
				view = 'facebookAuth'
				postUrl = "${request.contextPath}${config.facebook.filterProcessesUrl}"
			}
			else {
				view = 'auth'
				postUrl = "${request.contextPath}${config.filterProcessesUrl}"
			}
	
			render view: view, model: [postUrl: postUrl]
	}

	/**
	 * Form submit action to start an OpenID authentication.
	 * Uncomment if using OpenID.
	 */
	/*
	def openIdAuthenticate = {
		String openID = params['j_username']
		try {
			String returnToURL = RedirectUtils.buildRedirectUrl(
					request, response, openIDAuthenticationProcessingFilter.filterProcessesUrl)
			String redirectUrl = openIDConsumer.beginConsumption(request, openID, returnToURL)
			redirect url: redirectUrl
		}
		catch (org.springframework.security.ui.openid.OpenIDConsumerException e) {
			log.error "Consumer error: $e.message", e
			redirect url: openIDAuthenticationProcessingFilter.authenticationFailureUrl
		}
	}
	*/

	// Login page (function|json) for Ajax access.
	def authAjax = {
		nocache(response)
		//this is example:
		render """
		<script type='text/javascript'>
		(function() {
			loginForm();
		})();
		</script>
		"""
	}

	/**
	 * The Ajax success redirect url.
	 */
	def ajaxSuccess = {
		nocache(response)
		render '{success: true}'
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (authenticateService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: full, params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		def config = authenticateService.securityConfig.security
		render view: 'auth', params: params,
			model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			        postUrl: "${request.contextPath}${config.filterProcessesUrl}"]
	}

	// Denial page (data|view|json) for Ajax access.
	def deniedAjax = {
		//this is example:
		render "{error: 'access denied'}"
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {

		def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		def msg = ''
		def exception = session[AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
		if (exception) {
			if (exception instanceof DisabledException) {
				//msg = "[$username] is disabled."
                                msg = "Username is disabled."
								flash.message = msg
			}
			else {
				// msg = "[$username] wrong username/password."
                                msg = "Invalid username/password."
								flash.message = msg
			}
		}

		if (authenticateService.isAjax(request)) {
			render "{error: '${msg}'}"
		}
		else {
			//flash.message = msg
			redirect action: auth, params: params
                        //  render '<login><loginsuccess>no</loginsuccess></login>'
		}
	}
	
	    	
	 

	/** cache controls */
	private void nocache(response) {
		response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
		response.addDateHeader('Expires', 0)
		response.setDateHeader('max-age', 0)
		response.setIntHeader ('Expires', -1) //prevents caching at the proxy server
		response.addHeader('cache-Control', 'private') //IE5.x only
	}
	
	def brihaspatiLogin = {
	
	
	
	
	}

	
	def register =
	{
       println"params---register---"+params
           String Email=request.getParameter("email");
            String server=request.getLocalAddr()
            String port=request.getServerPort();

          // System.out.print(Email);

          String returnurl=" http://"+server+":"+port+"/StudentActivityVisualization/login/auth";


           String resp=RemoteAuth.AuthR(Email,returnurl,"avv_dive");
           //  System.out.print("resp------------"+resp);
             response.sendRedirect(resp);
	
	}
	
	
	
	
	
	
	
	
}
