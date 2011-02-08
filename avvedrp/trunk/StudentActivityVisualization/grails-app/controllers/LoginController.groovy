import org.codehaus.groovy.grails.plugins.springsecurity.RedirectUtils

import org.springframework.security.AuthenticationTrustResolverImpl
import org.springframework.security.DisabledException
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.AbstractProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.util.Environment
/**
 * Login Controller (Example).
 */
class LoginController {

	/**
	 * Dependency injection for the authentication service.
	 */
	def authenticateService

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
		if (authenticateService.isLoggedIn()) {
		 def ROLE
		  
					 GrailsHttpSession gh=getSession()
					 gh.putValue("env",grails.util.Environment.getCurrent())

                                           if(authenticateService.ifAllGranted('ROLE_SUPERADMIN'))
					{
						ROLE="ROLE_SUPERADMIN"
						gh.putValue("ROLE", ROLE);
						redirect action: 'admindashboard', controller:'dashboard'
					}

                                         if (authenticateService.ifAllGranted('ROLE_STAFF'))
                                         {
                                                   ROLE="ROLE_STAFF"
                                                   gh.putValue("ROLE", ROLE);
                                                   redirect action: 'staffdashboard', controller:'dashboard'
                                         }

					 if (authenticateService.ifAllGranted('ROLE_ADMIN')) {
					        ROLE="ROLE_ADMIN"
			        	 	gh.putValue("ROLE", ROLE);
                                                println("entered")
			        	       // redirect action: 'listSiteForLoginUser', controller:'courseActivity'
                                               redirect action: 'admindashboard', controller:'dashboard'
                                               //render '<login><loginsuccess>yes</loginsuccess><controller>dashboard</controller><action>admindashboard</action></login>'
			                 }

                                         /*
					 if (authenticateService.ifAllGranted('ROLE_STAFF')) {
						 ROLE="ROLE_STAFF"
			        	 	
			        	 	gh.putValue("ROLE", ROLE);
			        	 redirect action: 'listSiteForLoginUser', controller:'courseActivity'
			                    } */
					 
					 if (authenticateService.ifAllGranted('ROLE_STUDENT')) {
						 ROLE="ROLE_STUDENT"
			        	 
			        	 gh.putValue("ROLE", ROLE);
			        	 redirect action: 'studentdashboard', controller:'dashboard'
			         }
                                      /*
					if(authenticateService.ifAllGranted('ROLE_SUPERADMIN'))
					{
						ROLE="ROLE_ADMIN"
						gh.putValue("ROLE", ROLE);
						redirect action: 'listLMS', controller:'courseActivity'
                                                println "enetered"
					}
                                        */

					
					
					 def userDetails = authenticateService.userDomain()
					 gh.putValue("UserId", userDetails.getUsername());                                      
                                         def user_name=userDetails.getUsername().split("@");                                      
                                         gh.putValue("currUsername", user_name[0]);
					    
		    	
	        			
		        	 		        	 	
					
				 }
			else {
				redirect action: auth, params: params
	}
	}

	/**
	 * Show the login page.
	 */
	def auth = {

		nocache response

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
			}
			else {
				// msg = "[$username] wrong username/password."
                                msg = "Invalid username/password."
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
}
