import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import grails.plugins.springsecurity.Secured

//@Secured(['permitAll'])
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
	}
}
