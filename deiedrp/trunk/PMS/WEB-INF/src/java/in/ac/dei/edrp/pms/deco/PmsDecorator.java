package in.ac.dei.edrp.pms.deco;
import in.ac.dei.edrp.pms.member.MemberBean;
import in.ac.dei.edrp.pms.organization.OrgFields;
import in.ac.dei.edrp.pms.projmanage.ProjectFields;
import in.ac.dei.edrp.pms.task.TaskFields;
import in.ac.dei.edrp.pms.upload.UploadFileForm;

import org.displaytag.decorator.*;

public class PmsDecorator extends TableDecorator {
	 
		
	public String getTasklink()//for task updation
	{
		TaskFields lObject= (TaskFields)getCurrentRowObject();
		String task_id= String.valueOf(lObject.getTaskid());
		String project_code= lObject.getProject_code();
		return "<a href=\"editTask.do?taskkey=" + task_id+ "&projectkey="+project_code
			+ "&action=edit\"><img border=\"0"+"\"title=\"Edit"+"\"src=\"img/write_pen.gif"+"\"width=\"15"+"\"height=\"10\" ></a>";
		
	}
	
	public String getOrgLink()
	{
		OrgFields lObject= (OrgFields)getCurrentRowObject();
		String oId= String.valueOf(lObject.getId());
		
		return "<a href=\"editorgpage.do?id=" + oId
			+ "&action=edit\">Edit</a> | "
			+ "<a href=\"deleteorg.do?id=" + oId
			+ "&action=delete\">Delete</a>";
		
	}
	public String getEmailId()
	    {
		OrgFields lObject= (OrgFields)getCurrentRowObject();
	        String emailId = "<a href=\"mailto:"+lObject.getIeid()+"\">"+lObject.getIeid()+ "</a>";
	        return emailId;
	    }
	
	public String getUserEmailId()
    {
	MemberBean lObject= (MemberBean)getCurrentRowObject();
        String emailId = "<a href=\"mailto:"+lObject.getUserid()+"\">"+lObject.getUserid()+ "</a>";
        return emailId;
    }
	
	
	public String getLink2()
	{
		UploadFileForm temp= (UploadFileForm)getCurrentRowObject();
		String lId= String.valueOf(temp.getFileName());
		String lId1=temp.getOwner();
		String lId2= temp.getProjectName();
		//System.out.println("In Decorate"+lId2);
	
		return "<a href=\"rundownload.do?fileName=" + lId+ "&owner="+lId1 + "&projectName="+lId2 + "&action=downlaod\">Downlaod</a>| "
			+ "<a href=\"deleteupload.do?fileName=" + lId+ "&owner="+lId1+"&projectName="+lId2
			+ "&action=delete\">Delete</a>"; 
			
		
	}
	
	public String getLink3()
	{
		UploadFileForm temp= (UploadFileForm)getCurrentRowObject();
		String lId= String.valueOf(temp.getFileName());
		String lId1=temp.getOwner();
		String lId2= temp.getProjectName();
		//System.out.println("In Decorate"+lId2);
	
		return "<a href=\"rundownload.do?fileName=" + lId+ "&owner="+lId1 + "&projectName="+lId2 + "&action=downlaod\">Downlaod</a> "; 
			
	}
//this method writes on 8th april 2010 and working fine with javascript function and use in showMemberList.jsp
	
	public String getActiveMemberLink()
	{
		MemberBean lObject= (MemberBean)getCurrentRowObject();
		String valid_key= String.valueOf(lObject.getValid_key());
		String roleName= lObject.getRolename();
		String user_id= String.valueOf(lObject.getUserid());
		return "<a href=\"editMember.do?valid_key=" + valid_key+ "&roleName="+roleName
			+ "&action=edit\"><img border=\"0"+"\"title=\"Edit"+"\"src=\"img/write_pen.gif"+"\"width=\"15"+"\"height=\"10\" ></a> | "
			+ "<a href=\"deleteUserRole.do?valid_key=" + valid_key+ "&roleName="+roleName+"&key1="+"Active"+ "&userid="+user_id
			+ "\"onclick=\"return deleteUserRole();\">Delete</a>";
		
	}
	
	public String getInActiveMemberLink()
	{
		MemberBean lObject= (MemberBean)getCurrentRowObject();
		String user_id= String.valueOf(lObject.getUserid());
		
		return "<a href=\"editMember.do?userid=" + user_id
			+ "&action=edit\"><img border=\"0"+"\"title=\"Edit"+"\"src=\"img/write_pen.gif"+"\"width=\"15"+"\"height=\"10\" ></a> | "
			+ "<a href=\"deleteMember.do?userid=" + user_id
			+ "\"onclick=\"return deleteUser();\">Delete</a>";
			
	}
	
	public String getUserIdLink()
    {
		MemberBean lObject= (MemberBean)getCurrentRowObject();
		//String user_id= String.valueOf(lObject.getUserid());
		return "<a href=\"addorg_in_portal.do?userid=" + lObject.getUserid()
		+"\">"+lObject.getUserid()+ "</a>";
    }
	
	public String getAddUserIntoProject()
    {
		MemberBean lObject= (MemberBean)getCurrentRowObject();
		//String user_id= String.valueOf(lObject.getUserid());
		return "<a href=\"assignproject.do?userid=" + lObject.getUserid()
		+"\">"+lObject.getUserid()+ "</a>";
    }
	
	public String getProjectLink()//using
	{
		ProjectFields lObject= (ProjectFields)getCurrentRowObject();
		String project_code= String.valueOf(lObject.getProject_code());
		return "<a href=\"editProject.do?project_code=" + project_code
			+ "&action=edit\"><img border=\"0"+"\"title=\"Edit"+"\"src=\"img/write_pen.gif"+"\"width=\"15"+"\"height=\"10\" ></a> | "
			+ "<a href=\"deleteProject.do?project_code=" + project_code
			+ "\"onclick=\"return deleteProject();\">Delete</a>";
		
	}
	
}