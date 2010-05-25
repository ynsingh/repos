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
 * Creation date: 03-04-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ForwardAddMemberAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forwardString="member";
		HttpSession session=request.getSession();
		if((String)session.getAttribute("mysession")!=null)
		{	
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
		{
			String add_member_permission=checkRecord.AuthorityChecker("add_member", 
				(String)session.getAttribute("uid"),(String)session.getAttribute("validOrgInPortal"),
				(String)session.getAttribute("role_in_org"));
			if(add_member_permission==null || add_member_permission.equalsIgnoreCase("Not Allow"))
			{
				request.setAttribute("message", "Sorry!!! You are not an authorized person for this operation.");
				forwardString="notAssigning";
			}
		}
		}
		return mapping.findForward(forwardString);
		
	}
}