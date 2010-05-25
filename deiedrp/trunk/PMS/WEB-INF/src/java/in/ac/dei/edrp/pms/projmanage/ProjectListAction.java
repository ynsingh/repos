package in.ac.dei.edrp.pms.projmanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="projectlist" path="page.projectlist"
 */
/**
 * This action class is responsible for showing the project list.
 * It contains only one method called execute.Inside this method we writes the business logic
 * related with pagination.
 * 
 */

public class ProjectListAction extends Action {
	
/** 
	 * Method execute is used for Pagination.
	 * It means how many number of records to be shown per page.
	 * It is also used for setting the 'projectList' as an attribute in the request scope,
	 * which is used in ProjectList.jsp page.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form This holds the object of the bean class
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @param maxEntriesPerPage It holds maximum number of records for showing per page.
	 * by default value is 5.
	 * @param page It holds the position of desired page for showing ,which will be requested from user.
	 * by default is 1.
	 * @param offset It holds the position of record, from where the next records will be shown. 
	 * @return ActionForward holds the "projectlist", which is defined in the struts-config.xml file
	 * @see "projectlist" is used for calling the ProjectList.jsp page for showing the project list"
	 * @see projmanage.ProjectList 
	 * 
	 */
	static final Logger logger = Logger.getLogger(ProjectListAction.class);
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
  	HttpSession session = request.getSession();
  	String retString="invalid";
  	String query=request.getQueryString();
  	if((String)session.getAttribute("mysession")!=null)
		{												//list comes as active/inactive on date 7 april 2010
			if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
			{
				retString="userProjectList";
				if(query==null)
					query="10";
			}
			else
				retString="adminProjectList";
			 if(query!=null)
			 {//this attribute is used in delete project action file
				session.setAttribute("myquery",request.getRequestURL()+"?"+query);
			 }
		}
		return mapping.findForward(retString);
	 }
}
