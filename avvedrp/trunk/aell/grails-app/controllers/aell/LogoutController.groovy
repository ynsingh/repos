
package aell
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class LogoutController {
	def LoginService
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		def referal_url = grailsApplication.config.referal_url;
		def ell_integrate_flag = grailsApplication.config.ell_integrate_flag
		def userid,sessionid
		userid=session.user.id
		sessionid=session.sessionid
		LoginService.logoutAction(userid,sessionid);
    	// TODO  put any pre-logout code here
		if(ell_integrate_flag==0)
		   redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
		else if(ell_integrate_flag==1){
			request.session.invalidate()
			redirect url: referal_url
		}
	}
	
}
