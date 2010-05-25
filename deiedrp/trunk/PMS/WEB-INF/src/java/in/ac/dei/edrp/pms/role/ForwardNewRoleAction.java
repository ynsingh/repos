package in.ac.dei.edrp.pms.role;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ForwardNewRoleAction extends Action {
	
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
//		HttpSession session=request.getSession();
//		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
//		{
//		request.setAttribute("roleAutority", new RoleList((String)session.getAttribute("uid"),
//				(String)session.getAttribute("validOrgInPortal")));
//		}
		String forwardString="role";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_role_permission=checkRecord.AuthorityChecker("add_role", 
				(String)session.getAttribute("uid"),(String)session.getAttribute("validOrgInPortal"),
				(String)session.getAttribute("role_in_org"));
			if(add_role_permission==null || add_role_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
}
