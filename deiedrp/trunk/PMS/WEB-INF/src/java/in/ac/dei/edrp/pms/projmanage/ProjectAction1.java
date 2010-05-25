package in.ac.dei.edrp.pms.projmanage;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="page.newproject" path="page.newproject"
 */
/**
 * This action class is used for user varification.
 * Related with Add new project link.
 */

public class ProjectAction1 extends Action {
	
/** 
	 * The execute method of this class is used for authenticates the user.
	 *  If the user is admin this means user can add the new project
	 *  If the user is not a admin this means he cant add the new project.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form 
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @param user It holds the user_id of that person which is currently logged in.
	 * @return ActionForward called "project or notshow", which is defined in the struts-config.xml file
 	 * For forwarding we use the {@link mapping#findForward(String) findForword} method.
	 * @see "project for calling the newproject.jsp page for input"
	 * @see "notshow for calling the login.jsp page."
	 * @see projmanage.ProjectAction 
	 * 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	
		String forwardString="project";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{		
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_project_permission=checkRecord.AuthorityChecker("add_project", 
				(String)session.getAttribute("uid"),(String)session.getAttribute("validOrgInPortal"),
				(String)session.getAttribute("role_in_org"));
			if(add_project_permission==null || add_project_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
}
