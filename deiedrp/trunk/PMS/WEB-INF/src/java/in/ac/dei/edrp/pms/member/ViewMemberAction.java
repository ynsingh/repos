package in.ac.dei.edrp.pms.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;


/** 
 * Action Class ExampleAction
 */

public class ViewMemberAction extends Action {
	/** 
	* Method execute
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return ActionForward
 */

	public ActionForward execute(ActionMapping mapping,ActionForm form,	HttpServletRequest request,HttpServletResponse response)
		 {
			HttpSession session=request.getSession();
			String retString="invalid";
			if((String)session.getAttribute("mysession")!=null)
			{		//list comes as active/inactive on date 7 april 2010
				if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
					retString="viewOrgMember";
				else
					retString="viewmember";
			}
			return mapping.findForward(retString);
		 }
}