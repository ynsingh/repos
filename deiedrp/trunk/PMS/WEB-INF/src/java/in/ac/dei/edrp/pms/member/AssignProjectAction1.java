package in.ac.dei.edrp.pms.member;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="notassign" path="login.jsp"
 * @struts:action-forward name="assign" path="page.assignproject"
 */

public class AssignProjectAction1 extends Action {
	
/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		//String user=(String)this.getServlet().getServletConfig().getServletContext().getAttribute("uid");
		/*if(!user.equalsIgnoreCase("pms123"))
		{
			return mapping.findForward("notassign");
		}
		else*/
		String forwardString="assigning";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String assign_project_permission=checkRecord.AuthorityChecker("assign_project", 
				(String)session.getAttribute("uid"),(String)session.getAttribute("validOrgInPortal"),
				(String)session.getAttribute("role_in_org"));
			if(assign_project_permission==null || assign_project_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
	}
}
