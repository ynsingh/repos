/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package projmanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//For established database connection
import dataBaseConnection.MyDataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import control.CustomRequestProcessor;

import java.sql.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-17-2009
 * XDoclet definition:
 * @struts.action path="/go" name="projectform" input="newproject.jsp" scope="request" validate="true"
 */
/**
 * The role of ProjectAction class is as Action class for adding new project.
 * It is related with Add New Project link
 * Input is comes from newproject.jsp page.
 * This class contains execute method for inserting the record in project table.
 * 
 */
public class ProjectAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute is used for executing business logic.
	 * Inside execute method we use the sql query for inserting data in the project table.			
	 * @param mapping It holds the action mapping information used to invoke a Struts action.
	 * @param form This holds the object of the bean class named ProjectForm
	 * @param request The HTTP servlet request we are processing
	 * @param response The HTTP servlet response we are creating
	 * @return ActionForward called "success or projectfail", which is defined in the struts-config.xml file
	 * For forwarding we use the {@link mapping#findForward(String) findForword} method.
	 * @see "success for call the success.jsp page for showing the success message"
	 * @see "projectfail call the projectfail.jsp page for showing the error message."
	 * @see projmanage.ProjectForm 
	 * @see dataBaseConnection.MyConnection#getConnection()
	 * @exception SQLException
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if(new CustomRequestProcessor().processPreprocess(request, response)==true)
		{
			ProjectForm projectform = (ProjectForm) form;// TODO Auto-generated method stub
			Connection con=null;
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		PreparedStatement check=con.prepareStatement("select Project_ID from project where "+
		"Project_Name=?");
		check.setString(1,projectform.getPname().trim());
		ResultSet rs=check.executeQuery();
		if(rs.next())//it means record already erxist.
		{
			return mapping.findForward("success");
		}
		/*
		 * Inserting the record into project table.
		 */
		PreparedStatement ps=con.prepareStatement("insert into project values(?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,0);
		ps.setString(2,projectform.getPname().trim());
		ps.setString(3,projectform.getSdate());
		ps.setString(4,projectform.getFdate());
		ps.setInt(5,Integer.parseInt(projectform.getTbudget()));//converting String into Integer
		ps.setString(6,projectform.getPriority());
		ps.setString(7,projectform.getStatus());
		ps.setString(8,projectform.getViewPermission());
		ps.setString(9,projectform.getGcolor());
		ps.setString(10,projectform.getDarea().trim());
		ps.setInt(11, 0);
		ps.setInt(12, 0);
		int x=ps.executeUpdate();
		if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
			//response.getWriter().println("One Record has been Successfully Inserted ! ");
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("msg.project.added");
			errors.add("pname",error);
			saveErrors(request,errors);
			return mapping.findForward("success"); /*call the success.jsp page for showing the success message.*/
		}
			else
			//response.getWriter().println("No Record Inserted ! ");
			return mapping.findForward("projectfail"); /*call the projectfail.jsp page for showing the error message.*/
		}
		catch(Exception e)
		{
			return mapping.findForward("projectfail"); /*call the projectfail.jsp page for showing the error message.*/
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		}
		return null;
		}
		
}