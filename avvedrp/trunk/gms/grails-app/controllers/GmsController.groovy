import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession


/**
 * @author User
 *
 */
public class GmsController{

	def dataSource
	def  getUserPartyID=
	{
			GrailsHttpSession gh=getSession()
			return (Integer)gh.getValue("Party")
	}
	
	def  getUserRole=
	{
			GrailsHttpSession gh=getSession()
			return gh.getValue("Role")
	}
	
	
}
