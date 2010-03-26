import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession


/**
 * @author User
 *
 */
public class GmsController{

	
	def  getUserPartyID=
	{
			GrailsHttpSession gh=getSession()
			return gh.getValue("Party")
	}
	
	def  getUserRole=
	{
			GrailsHttpSession gh=getSession()
			return gh.getValue("Role")
	}
	
	
}
