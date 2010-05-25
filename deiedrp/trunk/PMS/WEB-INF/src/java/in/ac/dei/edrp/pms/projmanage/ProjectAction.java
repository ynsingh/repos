/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package in.ac.dei.edrp.pms.projmanage;

import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
import in.ac.dei.edrp.pms.viewer.CodeGenerator;
import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.sql.*;

/** 
 * MyEclipse Struts
 * Creation date: 04-21-2010
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
		
			ProjectForm projectform = (ProjectForm) form;// TODO Auto-generated method stub
			HttpSession session=request.getSession();
			Connection con=null;
			String valid_code="";
			String forwardmsg="projectfail";
			request.setAttribute("message","Project creation fail.because this Project already exist.!!");
		try{
			/*
			 * This method Established the connection from the database MySql
			 */
			con=MyDataSource.getConnection();
		
			if(checkRecord.twoFieldDuplicacyChecker("Project_Code","project","Project_Name",projectform.getPname().trim(),"Valid_Org_Inportal",(String)session.getAttribute("validOrgInPortal"))!=null)
			{
				System.out.println("project already exist.");
				return mapping.findForward("success");
			}
		/*
		 * Inserting the record into project table.
		 */
			PreparedStatement ps=con.prepareStatement("insert into project values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement pst=con.prepareStatement("select max(substr(Project_Code,9)) from project where substr(Project_Code,5,4)=Date_Format(Now(),'%Y')");
			ResultSet rst=pst.executeQuery();
			rst.next();
			String maxvalue=rst.getString(1);
			if(maxvalue!=null)
			{
				valid_code=CodeGenerator.gettingCombineCode("PROJ",Long.parseLong(maxvalue)+1,4);
			}
			else
				valid_code=CodeGenerator.gettingCombineCode("PROJ",1,4);
			System.out.println("valid code="+valid_code);
			ps.setString(1,valid_code);
			ps.setString(2,projectform.getPname().trim());
			ps.setString(3,projectform.getScheduleStartDate());
			ps.setString(4,projectform.getScheduleEndDate());
			ps.setString(5,null);//actual start date
			ps.setString(6,null);//actual end date
//			if(projectform.getActualEndDate().equals(""))
//			{
//				ps.setString(6,null);
//			}
//			else
//			{
//				ps.setString(6,projectform.getActualEndDate());
//			}
			ps.setInt(7,Integer.parseInt(projectform.getTbudget()));//converting String into Integer
			ps.setString(8,projectform.getPriority());
			ps.setString(9,projectform.getStatus());
			ps.setString(10,projectform.getGcolor());
			ps.setString(11,projectform.getDarea().trim());
			ps.setInt(12, 0);
			ps.setInt(13, 0);
			ps.setString(14,(String)session.getAttribute("validOrgInPortal"));
			int x=ps.executeUpdate();
			if(x>0) /*if x is greater than zero it means insertion operation is successful.*/
			{
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("msg.project.added");
				errors.add("projmsg",error);
				saveErrors(request,errors);
				forwardmsg="success"; /*call the success.jsp page for showing the success message.*/
			}
		}
		catch(Exception e)
		{
			System.out.println("error in proj action file="+e);
			//return mapping.findForward("projectfail"); /*call the projectfail.jsp page for showing the error message.*/
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardmsg); 
	
	}
	
}