package in.ac.dei.edrp.pms.portal;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class AddNewPortalAction extends Action {
	
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
		PortalForm newportalform = (PortalForm) form;
		HttpSession session=request.getSession();
		Connection con=null;
		String forwardmsg="portalfail";
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		request.setAttribute("message","New Portal has not been added.");
		con=MyDataSource.getConnection();
		if(checkRecord.duplicacyChecker("Portal_ID","portal","Portal_Name",newportalform.getPortalname().trim())!=null)
		{
			return mapping.findForward("portalsuccess");
		}
		/*
		 * Inserting the record into portal table.
		 */
		PreparedStatement ps=con.prepareStatement("insert into portal values(?,?,?,?,NOW())");
		ps.setInt(1,0);
		ps.setString(2,newportalform.getPortalname().trim());
		ps.setString(3,newportalform.getPortaldescription().trim());
		ps.setString(4,(String)session.getAttribute("uid"));
		
		int x=ps.executeUpdate();
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("msg.portal.added");
			errors.add("portalmsg",error);
			saveErrors(request,errors);
			forwardmsg="portalsuccess"; 
		}
			
		}
		catch(Exception e)
		{
			System.out.println("error in addnewportalaction="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}

		return mapping.findForward(forwardmsg);
	}
}
