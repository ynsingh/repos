package in.ac.dei.edrp.pms.task;

import java.io.IOException;

import in.ac.dei.edrp.pms.viewer.checkRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** 
 * Creation date: 09-October-2010
 * This class is related for assigning task to the project member
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 *
 */
public class AssignedTaskAction extends Action{
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
		AssignedTaskForm assignedTaskform = (AssignedTaskForm) form;
		HttpSession session=request.getSession();
		String orgportal=(String)session.getAttribute("validOrgInPortal");
		//System.out.println("pname="+assignedTaskform.getProjname());
		String assoignTo[]=assignedTaskform.getAssignedTo();
		String taskName[]=assignedTaskform.getTname();
		
		String project_code=checkRecord.twoFieldDuplicacyChecker("Project_Code","project",
				"Project_Name",assignedTaskform.getProjname(),"Valid_Org_Inportal",
				orgportal);
		String taskid=null;
		for(int i=0;i<assoignTo.length;i++){
//			System.out.println("task name="+taskName[i]);
//			System.out.println("assigned to="+assoignTo[i]);
			
			if(!assoignTo[i].equalsIgnoreCase("--Select--")){
			taskid=checkRecord.twoFieldDuplicacyChecker("Task_Id","task","Task_Name",
					taskName[i],"VProject_Code",project_code);
//for inserting task with user in task_with_user table
			AddingTaskWithUser.insertTaskWithUser(assoignTo[i],taskid,project_code,orgportal);
			
			}
		}
		try {
			response.sendRedirect((String)session.getAttribute("mytaskquery"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message","Task assigned failed,because this task already assign to the same user.");
		return mapping.findForward(forwardMsg);
		
	}
}