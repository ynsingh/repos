<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<script type="text/javascript">
function deleteSubConfirm(formName,roleId,roleName)
{
	
	var answer = confirm ("This will delete the Role '"+roleName+"'. Are you sure, you want to proceed?")
	if(answer)
	{
		document.contentForm.action='deleteRole';
		$('#deleteId').val(roleId);
		document.contentForm.deleteId = roleId;
		document.contentForm.submit();
		return true;			
	}
	return false;
}
function showEditForm(id,name)
{
	  $('#addeditForm').show();
	  $('#addeditedId').val(id);
	  $('#authority').val(name);
}



function showAddForm()
{
	  $('#addeditForm').show();
	  $('#addeditedId').val("");
	  $('#authority').val("");
}


function closeAddEditForm(id,name,description)
{
	  $('#addeditForm').hide();
}

function checkAddEdit(){
	var name = document.roleForm.authority;
	if((name.value=="") || (name.value==null)) {
		alert("Please fill the role field");
		name.focus();
		return false;
	}
}



$(document).ready(function() {
	  $('#addeditForm').hide();
     
});

</script>

  </head>
<body> 
<div id="innerData">
<table border="0" width="100%">
     <tr>
       <!-- <td width="1%" bgcolor="#FFCC00">&nbsp;</td>
       -->
       <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.role_management} </td>
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
			
			
						
			<g:form action="index" name="contentForm" method="post" >
			<table class="myTable" id="tableDrag" border="1" width="100%">	
				<tbody><tr style="cursor: move; background-color: rgb(204, 227, 255);" class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#cce3ff">       
				    <td width="5%">Sl No</td>
				    <td width="25%">Name</td>
				    <td width="5%">Edit</td>
				    <td width="6%">Delete</td>        
				
				</tr>	
				</tbody>
				<tbody>
					 <g:each in="${roleList}" status="i" var="avlRoleMasterInstance">     
						<tr class="text" id="${avlRoleMasterInstance.id}" style="cursor: move; ">
						<td align="center">${i+1 }</td>
						<td align="left">
						<g:link params="[roleId:avlRoleMasterInstance.id]">${fieldValue(bean:avlRoleMasterInstance, field:'authority')}</g:link>
						</td>
				<g:if test= "${avlRoleMasterInstance.id != 1000}">
					    <td align="center">
				
					    <a href="javascript:showEditForm(${avlRoleMasterInstance.id},'${avlRoleMasterInstance.authority}');"><img src="${hostname}/aell/images/Edit.gif" title="Edit Role" height="20" width="20"></img></a>
					    </td>
					    <td align="center">	
					       			<input type="image" id="delete_submit" name="delete_submit"  src="${hostname}/aell/images/Delete.gif" title="Delete Role" alt="Submit" onClick="deleteSubConfirm(this,${avlRoleMasterInstance.id},'${avlRoleMasterInstance.authority}')"/>  
					    </td> 
				</g:if>
						</tr>
					 </g:each>
				 <input name="deleteId" id="deleteId" type="hidden"/>
				</tbody>
		    </table>
		    </g:form>
		 </td>
	     </tr>
	     <tr align="left">

        <td class="textHead1" colspan="2">
		 <a href="javascript:showAddForm();"><img src="${hostname}/aell/images/add.gif" title="Add" height="16" width="16"></img>Add new role</a>
        </td>

   		 </tr>
	 	<tr>
	 	<td align="center" colspan="2">
	 		<div id="addeditForm">		 
	 			<g:form action="saveRole" name="roleForm" method="post" >		
				    <input name="addeditedId" id="addeditedId" type="hidden"/>
				    <table border="0" width="50%" style="border:dotted">
				    	<tr class="textHead1"><td colspan="2" align="center"><u>New/Edit</u></td></tr>
				        <tr class="text"><td width="30%">Role</td>
				        <td>
				        	<input name="authority" id="authority" type="text"  value="" style="width:300px"  maxlength="100"/>
				        </td>
				        </tr>
				       
				        <tr class="text">
				        <td colspan="2" align="center">
				        	<input type="submit" value="Submit" id="addedit_submit" name="addedit_submit"  onclick="return checkAddEdit();" style="width:50px"/>
				        	&nbsp;&nbsp;<input type="button" value="Cancel" id="addedit_cancel" name="addedit_cancel"  onclick="return closeAddEditForm();" style="width:50px"/>
				        </td>
				        </tr>
				</table>				 			
			</g:form>	
			</div>
		</td> 	 
</table>
</div>

</body>
</html>