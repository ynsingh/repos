<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  <link rel="stylesheet" href="style/main.css" type="text/css"></link>
  <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  
  <body >
  <%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	 %>
	 <%
	 if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
	  %>
	 <logic:notEmpty name="roleDetail" property="list">
  <div id="main_title"><font color="#0044ff">Role Details: </font></div>
  <br>
  <logic:iterate name="roleDetail" property="list" id="var">
		
		<table cellspacing="2" cellpadding="4" style="margin-left: 150px;width: 60%">
		
		<tr class="form-element">
			<td  width="26%"><font size="2">Role Name:</font></td>
			<td class="hilite"><font size="3"><bean:write name="var" property="rolename"/></font></td>
		</tr>
		<tr class="form-element">
			<td><font size="2">Role Description:</font></td>
			<td class="hilite"><bean:write name="var" property="roledescription" /></td>
		</tr>
		<tr><td> <font size="3"> Authorities :</font></td></tr>
		<logic:equal name="var" property="addrole" value="Allow">
		<tr class="form-element"><td>
		<html:link href="newrole.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Add New Role</html:link></td>
		<td class="hilite">: For adding new role in his organization.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="editrole" value="Allow">
		<tr class="form-element"><td>
		<html:link href="viewrole.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit / Remove Role</html:link></td>
		  <td class="hilite">: For edit and remove roles only for his organization.</td>
		  </tr></logic:equal>
		  <!-- 
		<logic:equal name="var" property="addorg" value="Allow">
		<tr class="form-element"><td>
		<html:link href="neworganization.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Add New Organization</html:link></td>
		<td class="hilite">: For add Organization in which the project will be run under project manager.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="editorg" value="Allow">
		<tr class="form-element"><td>
		<html:link href="vieworganization.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit / Remove Organization</html:link></td>
		  <td class="hilite">: For edit and remove Organization in which the project will be run under project manager.</td>
		  </tr></logic:equal>
		  -->
		<logic:equal name="var" property="addproject" value="Allow">
		<tr class="form-element"><td>
		<html:link href="newproject.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Add Project</html:link></td><td>: For adding new project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="editproject" value="Allow">
		<tr class="form-element"><td>
		<html:link href="updateoperation.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit / Disable Project</html:link></td><td>: For editing and disabling project</td>
		 </tr></logic:equal>
		<logic:equal name="var" property="addmember" value="Allow">
		<tr class="form-element"><td>
		<html:link href="addmember.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Add Member</html:link></td><td>:  For adding member in a project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="editmember" value="Allow">
		<tr class="form-element"><td>
		<html:link href="viewmember.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit / Remove Member</html:link></td><td>: For editing and removing members from the project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="assignproject" value="Allow">
		<tr class="form-element"><td>
		<html:link href="assignproject.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Assign Project</html:link></td><td>: For assigning project to the member.</td>
		 </tr></logic:equal>
		<logic:equal name="var" property="editauthority" value="Allow">
		<tr class="form-element"><td>
		<html:link href="editTask.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit Authority </html:link></td><td>: For edit the authority of the member.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="assigntask" value="Allow">
		 <tr class="form-element"><td>
		<html:link href="newtask.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Assigning Task </html:link></td><td>: For assigning task to the member.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="edittask" value="Allow">
		<tr class="form-element"><td>
		<html:link href="viewtask.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Edit / Remove Task </html:link></td><td>: For editing and removing the task.</td>
		 </tr></logic:equal>
		<!--  <logic:equal name="var" property="uploaddoc" value="Allow">
		 <tr class="form-element"><td>
		<html:link href="upload.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Upload Documents </html:link></td><td>: Uploads the documents for sharing anything b/w the project members.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Allow">
		<tr class="form-element"><td>
		<html:link href="download.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Download / Remove Documents </html:link></td><td>: For downloading the documents.</td>
		 </tr></logic:equal> -->
		</table>
		</logic:iterate>		
		</logic:notEmpty>
	<br><div style="padding-left: 100px;">
	 <html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
    </div>
    <%
    }//in case of super admin
    else
    {
     %>
     <logic:notEmpty name="roleDetail" property="list">
  <div id="main_title"><font color="#0044ff">Role Details: </font></div>
  <br>
  <logic:iterate name="roleDetail" property="list" id="var">
		
		<table cellspacing="2" cellpadding="4" style="margin-left: 150px;width: 60%">
		
		<tr class="form-element">
			<td  width="26%"><font size="2">Role Name:</font></td>
			<td class="hilite"><font size="3"><bean:write name="var" property="rolename"/></font></td>
		</tr>
		<tr class="form-element">
			<td><font size="2">Role Description:</font></td>
			<td class="hilite"><bean:write name="var" property="roledescription" /></td>
		</tr>
		<tr><td> <font size="3"> Authorities :</font></td></tr>
		<logic:equal name="var" property="addrole" value="Allow">
		<tr class="form-element"><td>
		Add New Role</td>
		<td class="hilite">: For adding new role in his organization.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="editrole" value="Allow">
		<tr class="form-element"><td>
		Edit / Remove Role</td>
		  <td class="hilite">: For edit and remove roles only for his organization.</td>
		  </tr></logic:equal>
		  <!--  
		<logic:equal name="var" property="addorg" value="Allow">
		<tr class="form-element"><td>
		Add New Organization</td>
		<td class="hilite">: For add Organization in which the project will be run under project manager.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="editorg" value="Allow">
		<tr class="form-element"><td>
		Edit / Remove Organization</td>
		  <td class="hilite">: For edit and remove Organization in which the project will be run under project manager.</td>
		  </tr></logic:equal>
		  -->
		<logic:equal name="var" property="addproject" value="Allow">
		<tr class="form-element"><td>
		Add Project</td><td>: For adding new project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="editproject" value="Allow">
		<tr class="form-element"><td>
		Edit / Disable Project</td><td>: For editing and disabling project</td>
		 </tr></logic:equal>
		<logic:equal name="var" property="addmember" value="Allow">
		<tr class="form-element"><td>
		Add Member</td><td>:  For adding member in a project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="editmember" value="Allow">
		<tr class="form-element"><td>
		Edit / Remove Member</td><td>: For editing and removing members from the project.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="assignproject" value="Allow">
		<tr class="form-element"><td>
		Assign Project</td><td>: For assigning project to the member.</td>
		 </tr></logic:equal>
		<logic:equal name="var" property="editauthority" value="Allow">
		<tr class="form-element"><td>
		Edit Authority </td><td>: For edit the authority of the member.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="assigntask" value="Allow">
		 <tr class="form-element"><td>
		Assigning Task</td><td>: For assigning task to the member.</td>
		</tr></logic:equal>
		<logic:equal name="var" property="edittask" value="Allow">
		<tr class="form-element"><td>
		Edit / Remove Task </td><td>: For editing and removing the task.</td>
		 </tr></logic:equal>
		<!--  <logic:equal name="var" property="uploaddoc" value="Allow">
		 <tr class="form-element"><td>
		<html:link href="upload.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Upload Documents </html:link></td><td>: Uploads the documents for sharing anything b/w the project members.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Allow">
		<tr class="form-element"><td>
		<html:link href="download.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Download / Remove Documents </html:link></td><td>: For downloading the documents.</td>
		 </tr></logic:equal> -->
		</table>
		</logic:iterate>		
		</logic:notEmpty>
	<br><div style="padding-left: 100px;">
	 <html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
    </div>
     
     
     <%} %>
  </body>
</html>
