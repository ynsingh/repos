package org.dei.edrp.pms.deco;
import org.dei.edrp.pms.upload.UploadFileForm;
import org.dei.edrp.pms.projmanage.ProjectFields;
import org.dei.edrp.pms.organization.OrgFields;
import org.dei.edrp.pms.task.TaskFields;
import org.dei.edrp.pms.member.MemberBean;
import org.displaytag.decorator.*;

public class PmsDecorator extends TableDecorator {
	 
	public String getProjectLink()
	{
		ProjectFields lObject= (ProjectFields)getCurrentRowObject();
		String lId= String.valueOf(lObject.getId());
		String lId1= lObject.getName();
		return "<a href=\"editProject.do?id=" + lId+ "&name="+lId1+" onclick="+"return confirm('Really delete the user ?');"
			+ "&action=edit\">Edit</a> | "
			+ "<a href=\"deleteProject.do?id=" + lId
			+ "&action=delete\">Delete</a>";
		
	}

	
	
	public String getProjectManagerTasklink()
	{
		TaskFields lObject= (TaskFields)getCurrentRowObject();
		String tName= String.valueOf(lObject.getTaskName());
		String pName= lObject.getProjectName();
		String rName= lObject.getResourceName();
		return "<a href=\"editTask.do?tName=" + tName+ "&pName="+pName+"&rName="+rName
			+ "&action=edit\">Edit</a> | "
			+ "<a href=\"deleteTask.do?tName=" + tName+ "&pName="+pName+"&rName="+rName
			+ "&action=delete\">Delete</a>";
		
	}
	
	public String getUserTasklink()
	{
		TaskFields lObject= (TaskFields)getCurrentRowObject();
		String tName= String.valueOf(lObject.getTaskName());
		String pName= lObject.getProjectName();
		String rName= lObject.getResourceName();
		return "<a href=\"editTask.do?tName=" + tName+ "&pName="+pName+"&rName="+rName
			+ "&action=edit\">Edit</a>";
		
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

	
	}