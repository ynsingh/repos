package org.dei.edrp.pms.member;

import javax.servlet.http.HttpServletRequest;

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
			//System.out.println("id="+request.getParameter("id"));
			//HttpSession session=request.getSession();
			//session.setAttribute("id",request.getParameter("id"));
		request.setAttribute("memberList", new MemberList());
			return mapping.findForward("viewmember");
		 }
	
}