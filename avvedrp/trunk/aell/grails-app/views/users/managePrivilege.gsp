
<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.tablednd_0_5.js')}"></script>
<script type="text/javascript">
function getUsers()
{
		document.roleSelectForm.submit();			
}
function getSubjects()
{
	document.userSelectForm.submit();	
}
function checkOrUncheck()
{
	//var field=document.assignPrivilegeForm.userCheckBox;
	var field=document.getElementsByName("userCheckBox");
	if(document.getElementById("check").checked==true)
	  {
         for (i = 0; i < field.length; i++){
	         field[i].checked = true ;
          }
	  }
	 else
	 {
		for (i = 0; i < field.length; i++){
			field[i].checked = false ;
		}
	  }
}
function submitUserPrivilege()
{
	document.assignSubjectPrivilegeForm.submit();	
}
</script>
</head>
<body> 
<div id="innerData">
<g:form action="managePrivilege" name="assignPrivilegeForm" method="post" >
<table border="0" width="100%">
     <tr>
         <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.manage_privilege} </td>
     </tr>
     
      <tr>
	 	 <td colspan="2">
	 	      <g:if test="${flash.message}">
                  <div class="message"><img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20">${flash.message}</div>
              </g:if>
              <g:if test="${flash.error}">
                  <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
              </g:if>
		</td>
	  </tr>
     <tr>
     	<td >
    	      <table border="0" align="left">
    	         <g:form action="managePrivilege" name="universitySelectForm" method="post" >
  			     <tr >
    			      <td class="textHead2" style="padding-left:0px;">Select University Name </td>      
    			      <td>
		                   <g:select style="width:252px" name="universityId"
		                   from="${universityInstance}" optionValue="universityName"
		                   optionKey="id" size="1" onChange="getSubjects();" value="${session.selectedUniversityId}"/>
		              </td> 
		 	     </tr>
		 	     </g:form>   
		 	     <g:form action="managePrivilege" name="roleSelectForm" method="post">
		 	     <tr>
		 	           <td class="textHead2" style="padding-left:0px;">Select Role </td>      
    			      <td>
		                   <g:select style="width:252px" name="roleListId"
		                   from="${roleList}" optionValue="authority"
		                   optionKey="id" size="1" onChange="getUsers();" value="${session.selectedRoleId}"/>
		              </td> 
		 	     </tr>
		 	     </g:form>
		 	     <g:form action="managePrivilege" name="userSelectForm" method="post">
		 	     <tr>
		 	          <td class="textHead2" style="padding-left:0px;">Select User </td>      
    			      <td>
		                   <g:select style="width:252px" name="userId"
		                   from="${usersList}" optionValue="uname"
		                   optionKey="uid" size="1" onChange="getSubjects();" value="${session.selectedUserId}"/>
		              </td> 
		 	     </tr>
		 	     </g:form>
		  </table>
		  </td>
		   </tr>
		   <tr>
		  <td>
		 	                    <g:form action="managePrivilege" name="assignSubjectPrivilegeForm" method="post">
		 	                    <table class="myTableAdmin" id="tableDrag" border="1" align="center" width="100%">
		 	               		   <tbody>
		 	               				<tr style="cursor: move; background-color: rgb(204, 227, 255);" class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#cce3ff">
		 	               				   <td style="font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:5%;">Sl No</td>    
		                                  <td style="font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;">Subjects</td>
		                                </tr>
		                                <tr>		
		                                   <td>
		                                       ALL
		                                   </td>
		                                   <td><g:checkBox name="check" value="${child?.enabled}" onclick="checkOrUncheck();" /></td>
		                               </tr>
		                           </tbody>
						           <g:each in="${subjectList}" status="i" var="subject">
						              <tr>
						                  <td style="font-family:Verdana, Geneva, sans-serif;font-size:12px;">${i+1}</td>
						                   <td style="font-family:Verdana, Geneva, sans-serif;font-size:12px;">
						                       <g:checkBox  value="${subject.id}" checked="${subject.rel}" name="userCheckBox" />&nbsp;&nbsp;${subject.subjectName}
						                   </td>
						              </tr>
						           </g:each>
						          
						            <tr>
						                  <td colspan="2">
						                       <input type="submit" value="Submit" id="submit" name="submit"  onclick="submitUserPrivilege();" style="width:50px"/>
										  </td>
									</tr>
						          </table>
						          <input type="hidden" name="roleId" value="${session.selectedRoleId }"/>
	 	                          <input type="hidden" name="UserId" value="${session.selectedUserId}"/>
	 	                          </g:form>
		  </td>
	 	</tr>	
	 	
</table>
</g:form>
</div>
</body>
</html>