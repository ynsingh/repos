package organization;
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataBaseConnection.MyDataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/** 
 * MyEclipse Struts
 * Creation date: 10-15-2009
 * 
 * XDoclet definition:
 * @struts.action path="/saveorganization" name="orgform" input="neworganisation.jsp" scope="request" validate="true"
 */
/**
 * This Action class is responsible for inserting the Organisation details in database.
 */
public class AddNewOrganisationAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * This method is resposible for inserting the Organisation details into database
	 * "orgSearchList": this attribute sets the value of all Organisation from the object of class "organiazation.OrgList"
	 * "message" : This attribute sets only the message,which willbe shown to next view page.
	 * @param mapping It holds the action mapping information  used to invoke a struts action
	 * @param form It holds the object of bean class,named OrgForm
	 * @param request The HTTP servlet request,which is going to be processed
	 * @param response The HTTP servlet response,which is going to be created.
	 * @return ActionForward Onsuccessfull insertion,it returns saveorg othewise error and null for next view
	 * @param x if x is greater than zero it means insertion operation is successful.
	
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
				OrgForm orgform=(OrgForm) form;
				Connection con=null;
		try
		{
		/*
		 * Established database connection.
		 * */
			con=MyDataSource.getConnection();
		/*
		 * inserting values into 'organisation' table.
		 * */
		PreparedStatement ps=con.prepareStatement("insert into organisation values(?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,0);	//org_id is auto incremented.
		ps.setString(2,orgform.getIname());
		ps.setString(3,orgform.getIaddress());
		ps.setString(4,orgform.getIcity());
		ps.setString(5,orgform.getIstate());
		ps.setString(6,orgform.getIpin());
		ps.setString(7,orgform.getIfax());
		ps.setString(8,orgform.getIurl());
		ps.setString(9,orgform.getIhead());
		ps.setString(10,orgform.getIeid());
		ps.setLong(11,Long.parseLong(orgform.getIphoneno()));//convert String into Long.
		ps.setString(12,orgform.getDescription());
		int x=ps.executeUpdate();
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
		{	
		request.setAttribute("message", "'"+orgform.getIname()+"' "+" Organisation has been successfully added.");
		}
		else{
				request.setAttribute("message", "No Organisation Added.");
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message", "No Organisation Added.");
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward("info");
	}	
}