<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/aell/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.tablednd_0_5.js')}"></script>
<script type="text/javascript">
function deleteSubConfirm(formName,subjectId,subjectName)
{
	function getSubjects()
	{
			document.universitySelectForm.submit();			
	}
	
	var answer = confirm ("This will delete the Module '"+subjectName+"'. Are you sure, you want to proceed?")
	if(answer)
	{
		document.contentForm.action='deleteSubject';
		$('#deleteId').val(subjectId);
		document.contentForm.deleteId = subjectId;
		document.contentForm.submit();
		return true;			
	}
	return false;
}
function showEditForm(id,dec_name,description)
{
	  var name=decode64(dec_name);
      var desc=decode64(description);
	  $('#addeditForm').show();
	  $('#addeditedId').val(id);
	  $('#subjectName').val(name);
	  $('#subjectDescription').val(desc);
}



function showAddForm()
{
	  $('#addeditForm').show();
	  $('#addeditedId').val("");
	  $('#subjectName').val("");
	  $('#subjectDescription').val("");
}


function closeAddEditForm(id,name,description)
{
	  $('#addeditForm').hide();
}

function checkAddEdit(){
	var name = document.subjectForm.subjectName;
	var desc = document.subjectForm.subjectDescription;
	if((name.value=="") || (name.value==null)) {
		alert("Please fill the name field");
		name.focus();
		return false;
	}
	if((desc.value=="") || (desc.value==null)) {
		alert("Please fill the description field");
		desc.focus();
		return false;
	}
}

function updateSequence(order) {
	  $('#sequenceOrder').val(order);
	  $('#sequenceForm').submit();
}

$(document).ready(function() {
	  $('#addeditForm').hide();
      $("#tableDrag").tableDnD({ onDrop: function(table, row) {	
            var rows = table.tBodies[1].rows;
            var debugStr = rows[0].id;
            for (var i=1; i<rows.length; i++) {
                debugStr += "," + rows[i].id;
            }
	        updateSequence(debugStr);
          }
      });
});

</script>

  </head>
<body> 
<div id="innerData">
<table border="0" width="100%">
     <tr>
       <!-- <td width="1%" bgcolor="#FFCC00">&nbsp;</td>
       -->
       <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.module} </td>
     </tr>
     <tr>
     	<td >
     	<g:form action="index" name="universitySelectForm" method="post" >
    	  <table border="0" align="left">
  			<tr >
    			<td class="textHead2" style="padding-left:0px;">select University Name </td>      
    			<td>
		        <g:select style="width:252px" name="universityId"
		          from="${universityInstance}" optionValue="universityName"
		          optionKey="id" size="1" onChange="getSubjects();" value="${session.selectedUniversityId}"/>
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
				<tbody><tr style="cursor: move; background-color: rgb(204, 227, 255);" class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#cce3ff">       
				    <td width="5%">Sl No</td>
				    <td width="25%">Name</td>
				    <td>Description</td>
				    <td width="5%">Edit</td>
				    <td width="6%">Delete</td>        
				
				</tr>	
				</tbody>
				<tbody>
					 <g:each in="${avlSubjectMasterInstanceList}" status="i" var="avlSubjectMasterInstance">     
						<tr class="text" id="${avlSubjectMasterInstance.id}" style="cursor: move; ">
						<td align="center">${i+1 }</td>
						<td align="left">
						<g:link action="index" controller="topic" params="[subjectId:avlSubjectMasterInstance.id]">${fieldValue(bean:avlSubjectMasterInstance, field:'subjectName')}</g:link>
						</td>
					    <td align="left">${fieldValue(bean:avlSubjectMasterInstance, field:'subjectDescription')}</td>
					    <td align="center">
					    <a href="javascript:showEditForm(${fieldValue(bean:avlSubjectMasterInstance, field:'id')},'${avlSubjectMasterInstance.subjectName.encodeAsBase64()}','${avlSubjectMasterInstance.subjectDescription.encodeAsBase64()}');"><img src="${hostname}/aell/images/Edit.gif" title="Edit Broad Area" height="20" width="20"></img></a>
					    </td>
					    <td align="center">	
					       			<input type="image" id="delete_submit" name="delete_submit"  src="${hostname}/aell/images/Delete.gif" title="Delete Broad Area" alt="Submit" onClick="deleteSubConfirm(this,${fieldValue(bean:avlSubjectMasterInstance, field:'id')},'${fieldValue(bean:avlSubjectMasterInstance, field:'subjectName')}')"/>  
					    </td> 
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
		 <a href="javascript:showAddForm();"><img src="${hostname}/aell/images/add.gif" title="Add" height="16" width="16"></img>Add new ${grailsApplication.config.module}</a>
        </td>

   		 </tr>
	 	<tr>
	 	<td align="center" colspan="2">
	 		<div id="addeditForm">		 
	 			<g:form action="saveSubject" name="subjectForm" method="post" >		
				    <input name="addeditedId" id="addeditedId" type="hidden"/>
				    <table border="0" width="50%" style="border:dotted">
				    	<tr class="textHead1"><td colspan="2" align="center"><u>New/Edit</u></td></tr>
				        <tr class="text"><td width="30%">${grailsApplication.config.module_text}</td>
				        <td>
				        	<input name="subjectName" id="subjectName" type="text"  value="" style="width:300px"  maxlength="100"/>
				        </td>
				        </tr>
				        <tr class="text">
				        <td>${grailsApplication.config.module_desc}</td>
				        <td><textarea name="subjectDescription" id="subjectDescription" rows="5" cols="35"></textarea></td>
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