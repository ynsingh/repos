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

public class ViewProjTeamAction extends Action {
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
			{		retString="viewprojteam";
			}
			return mapping.findForward(retString);
		 }
}