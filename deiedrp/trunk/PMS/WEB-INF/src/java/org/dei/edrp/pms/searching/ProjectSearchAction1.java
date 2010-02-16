package org.dei.edrp.pms.searching;

import javax.servlet.http.HttpServletRequest;

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
 * @struts:action-forward name="searchproject" path="page.searchproject"
 */
/**
 * This action class is related with Search link of project.
 */
public class ProjectSearchAction1 extends Action {
	
/** 
	 * In this method we sets the attribute 'update' with the value of 'no'
	 * in whole application, that is used in other jsp pages. 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward with the value of searchproject for calling the projectSearch.jsp page.
	 * @see searching.ProjectSearchAction
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		this.getServlet().getServletConfig().getServletContext().setAttribute("update","no");
		return mapping.findForward("searchproject");
	}
}
