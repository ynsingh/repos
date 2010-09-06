/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package in.ac.dei.edrp.pms.home;

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
 * Creation date: 05-04-2010
 * 
 * XDoclet definition:
 * @struts.action input="login.jsp" validate="true"
 */
/**
 * This Action class is related with login the user.
 * This class have only one method that is execute. 
 */
public class MainWelcomeAction extends Action {
	/*
	 * Generated Methods
	 */

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PortalBean portalnameform=(PortalBean)form;
		
		String forwardString="invalid";
		HttpSession session=request.getSession();
		try{
		if((String)session.getAttribute("mysession")!=null)
		{
			session.setAttribute("portalname", portalnameform.getPortalname());
			session.setAttribute("orgname", portalnameform.getOrgname());
			session.setAttribute("rolename", portalnameform.getRolename());
			String portal_id=checkRecord.duplicacyChecker("Portal_ID","portal","Portal_Name",portalnameform.getPortalname());
			String org_id=checkRecord.duplicacyChecker("Org_ID","organisation","Org_Name",portalnameform.getOrgname());
			session.setAttribute("validOrgInPortal",checkRecord.twoFieldDuplicacyChecker("valid_org_inportal","org_into_portal","org_id",org_id,"portal_id",portal_id));
			String roleid=checkRecord.twoFieldDuplicacyChecker("Role_Id","role","Role_Name",portalnameform.getRolename(),"ValidOrgPortal",(String)session.getAttribute("validOrgInPortal"));
			if(roleid==null)
			{
				roleid=checkRecord.twoFieldDuplicacyChecker("Role_Id","role","Role_Name",portalnameform.getRolename(),"ValidOrgPortal",null);
			}
			session.setAttribute("roleid", roleid);
			session.setAttribute("myquery",request.getRequestURL()+"?"+request.getQueryString());
			forwardString="showWelcome";
		}
		
	}
	catch(Exception e)
	{
		System.out.println("error in main welcome.java file ="+e);
	}
	
	return mapping.findForward(forwardString);
  }
}

