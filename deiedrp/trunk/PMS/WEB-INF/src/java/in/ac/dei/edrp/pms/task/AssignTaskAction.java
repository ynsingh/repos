package in.ac.dei.edrp.pms.task;

import java.sql.Connection;
import in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;
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
/** 
 * MyEclipse Struts
 *Creation date: 11-05-2010
 * XDoclet definition:
 * @struts:action scope="null" validate="true"
 * @struts:action-forward name="page.assigntask" path="assigntask"
 */

public class AssignTaskAction extends Action{
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
		String forwardMsg="taskAssignfail";
		AssignTaskForm assignTaskform = (AssignTaskForm) form;
		HttpSession session=request.getSession();
		String orgportal=(String)session.getAttribute("validOrgInPortal");
		Connection con=null;
		request.setAttribute("message","Task assigned failed,because this task already assign to the same user.");

		try{
			/*
			 * This method established the connection from the database MySql
			 */
		con=MyDataSource.getConnection();
		String project_code=checkRecord.twoFieldDuplicacyChecker("Project_Code","project",
				"Project_Name",assignTaskform.getProjectName(),"Valid_Org_Inportal",
				orgportal);
		String taskid=checkRecord.twoFieldDuplicacyChecker("Task_Id","task","Task_Name",
				assignTaskform.getTaskNameList(),"VProject_Code",project_code);
		
		//insert the data into task_with_user table when we select assigned to option.	
		if(AddingTaskWithUser.insertTaskWithUser(assignTaskform.getAssignedTo(),taskid,project_code,orgportal)>0)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("msg.assignTask.added");
			errors.add("assignTaskMsg",error);
			saveErrors(request,errors);
			forwardMsg="taskAssignsuccess";
			//System.out.println("task has been assigned to the selected user.");
		}
		}
		catch(Exception e)
		{
			System.out.println("error in assign task action file="+e);
		}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		return mapping.findForward(forwardMsg);
		
	}
}