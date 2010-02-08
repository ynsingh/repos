package searching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;

import org.apache.struts.action.ActionForward;

import org.apache.struts.action.ActionMapping;

import projmanage.ProjectList;


/** 
 * MyEclipse Struts
 *Creation date: 06-09-2009
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="searchproject" path="page.searchproject"
 */
/**
 * This action class is responsible for holding the search reasult.
 */
public class ProjectSearchListAction extends Action {
	
	/*
	 * Generated Methods
	 */

	/** 
	 * This method is used for setting the search result in the request scope.
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @return ActionForward returns projectsearchlist for calling ProjectSearchResult.jsp page.
	 * @see projmanage.ProjectList
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		HttpSession session=request.getSession();
		request.setAttribute("projectSearchList", new ProjectList((String)session.getAttribute("soption"),(String)session.getAttribute("svalue")));
		return mapping.findForward("projectsearchlist");
	}
}
