
<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
  <g:javascript library="prototype" />
 
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.tablednd_0_5.js')}"></script>
<script type="text/javascript">
function getUsers()
{
		document.universitySelectForm.submit();			
}
</script>
  </head>
<body> 
<div id="innerData">
<table border="0" width="100%">
     <tr>
         <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.user_manage} </td>
     </tr>
     <tr>
     	<td >
     	      <g:form action="index" name="universitySelectForm" method="post" >
    	      <table border="0" align="left">
  			     <tr >
    			      <td class="textHead2" style="padding-left:0px;">select University Name </td>      
    			      <td>
		                   <g:select style="width:252px" name="universityId"
		                   from="${universityList}" optionValue="universityName"
		                   optionKey="id" size="1" onChange="getUsers();" value="${session.selectedUniversityId}"/>
		              </td> 
		 	     </tr>
		      </table>
		      </g:form>   
	 	 </td>
	 	 </tr>
	 	 <tr>
	 	 <td colspan="2">
	 	      <g:if test="${flash.message}">
                  <div class="message"><img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20">${flash.message}</div>
              </g:if>
              <g:if test="${flash.error}">
                  <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
              </g:if>
			  <table border="0"><tbody><tr><td></td></tr></tbody></table>
			  <g:form action="updateSequence" name="sequenceForm" method="post" >
				  <input type="hidden" name="sequenceOrder" id="sequenceOrder"/>
			  </g:form>
			  <g:form action="index" name="contentForm" method="post" >
			  <table class="myTable" id="tableDrag" border="1" width="100%">	
				   <tbody>
				       <tr style="cursor: move; background-color: rgb(204, 227, 255);" class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#cce3ff">       
				         <td width="5%">Sl No</td>
				         <td width="25%">User Name</td>
				         <td>Email</td>
				         <td>User Role</td>
				         <td>Status</td>
				        <td width="5%">Edit</td>
				       </tr>	
				   </tbody>
				   <tbody>
					    <g:each in="${userInstnaceList}" status="i" var="userInstnace">     
						<tr class="text" id="${userInstnace.id}" style="cursor: move; ">
						    <td align="center">${i+1 }</td>
						    <td>${userInstnace.username}</td>
						    <td>${userInstnace.email}</td>
						    <td>${userInstnace.role}</td>
						    <td>${userInstnace.userStatus}
						        <g:if test="${userInstnace.status== 'A'}" >Active</g:if>
				                <g:elseif test="${userInstnace.status== 'D'}">Deactive</g:elseif> 
				            </td>
				            <td>
                                 <a onclick="<g:remoteFunction action="editUserProfile" controller="users" id="${userInstnace.id}" update="editUserForm"/>">
				                    <img style="cursor:pointer"  src="${hostname}/aell/images/Edit.gif" title="Edit Broad Area" height="20" width="20"></img>
				                 </a>
				            </td>
						 </tr>
					     </g:each>
				  </tbody>
		    </table>
		    </g:form>
		</td>
	 </tr>
</table>
<div id="editUserForm">		 
</div>
</div>
</body>
</html>