import grails.converters.JSON

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

@Secured(['permitAll'])
class LoginController {

	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
			 
		println "index"
		if (springSecurityService.isLoggedIn()) 
		{
			 Integer user=SCH.context.authentication.principal.id
			 def role= SCH.context.authentication.principal.authorities.iterator().next()
			 GrailsHttpSession gh=getSession()
			 println "role_______+-="+SCH.context.authentication.principal.authorities
			 def userMap=UserMap.find("from UserMap UM where UM.user.id="+user);
			 println "role_______+-="+role
	         gh.putValue("UserId", userMap.user.id);
			 def PartyID=userMap.party.id
		     gh.putValue("Party", PartyID);
	         gh.putValue("Help","Project_List.htm")
	         gh.putValue("PartyID","('"+ PartyID.toString()+"')");
	         redirect uri:'/grantAllocation/gmsFrame'
	         gh.putValue("Role", role);
	        //redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		}
		else {
			redirect action: auth, params: params
		}
	}

	/**
	 * Show the login page.
	 */
	def auth = {
		println  "auth"
		GrailsHttpSession gh=getSession()
		def config = SpringSecurityUtils.securityConfig
		 println  "params:"+params
		 println  "gh.getValue(ang):"+gh.getValue("lang")
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
		if (springSecurityService.isLoggedIn()) {
			println  "indexisLoggedIn"+SCH.context.authentication.principal.username
			println  "authisLoggedIn"
			redirect uri:'/grantAllocation/gmsFrame'
			return
		}

		String view = 'auth'
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		render view: view, model: [postUrl: postUrl,
		                           rememberMeParameter: config.rememberMe.parameter]
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: full, params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		def config = SpringSecurityUtils.securityConfig
		render view: 'auth', params: params,
			model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {

		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		String msg = ''
		def exception = session[AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = "${message(code: 'default.errors.login.expired.label')}"
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = "${message(code: 'default.errors.login.passwordExpired.label')}"
			}
			else if (exception instanceof DisabledException) {
				msg ="${message(code: 'default.errors.login.disabled.label')}"
			}
			else if (exception instanceof LockedException) {
				msg = "${message(code: 'default.errors.login.locked.label')}"
			}
			else {
				msg = "${message(code: 'default.errors.login.fail.label')}"
			}
		}

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: auth, params: params
		}
	}

	/**
	 * The Ajax success redirect url.
	 */
	def ajaxSuccess = {
		render([success: true, username: springSecurityService.authentication.name] as JSON)
	}

	/**
	 * The Ajax denied redirect url.
	 */
	def ajaxDenied = {
		render([error: 'access denied'] as JSON)
	}
}
