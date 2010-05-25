package in.ac.dei.edrp.pms.portal;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 17-03-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="viewPortalList" path="/WEB-INF/JspFiles/portal/ViewPortal.jsp"
 */
/**
 * This Action class is only for forwarding  the request to next view of edit portal.
 */
public class EditPortalAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * This method returns the forward name for /viewPortalList Action
	  * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward returns viewPortalList for next view
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
		EditPortalForm editportalform = (EditPortalForm)form;
		
		String forwardmsg="editportalfail";
		Connection con=null;
			
		try{
			request.setAttribute("message","Portal updation failed,because this portal name is already exist. !!");
			
			con=MyDataSource.getConnection();//This method Established the connection from the database MySql
			if(!editportalform.getPortalname().trim().equalsIgnoreCase(editportalform.getOldportalname().trim()))
			{
			if(checkRecord.duplicacyChecker("Portal_ID","portal","Portal_Name",editportalform.getPortalname().trim())!=null)
			{
				return mapping.findForward("editportalfail");
			}
			}
			
		/*
		 * update the 'portal' table with the desired values.
		 * */
				PreparedStatement ps = con.prepareStatement("update portal set Portal_Name=?,Portal_Description=?"+
					   " where Portal_Id=?");
			    ps.setString(1,editportalform.getPortalname().trim());
				ps.setString(2,editportalform.getPortaldescription());
				ps.setInt(3,Integer.parseInt(editportalform.getPortalid()));
				int n=ps.executeUpdate();
						
		if(n>0) /*if n is greater than zero it means update operation is successful.*/
		{
			forwardmsg="viewPortalList";
		}
		}
		catch(Exception e)
		{
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardmsg);//calling to that jsp page which is assigned in variable forwardmsg.
		
	}
}