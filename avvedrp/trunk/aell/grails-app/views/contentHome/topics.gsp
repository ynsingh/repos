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
function getTopics()
{
		document.subjectSelectForm.submit();			
}
function showEditForm(id,dec_name,description)
{
	  var name=decode64(dec_name);
      var desc=decode64(description);
	  $('#addeditForm').show();
	  $('#topicId').val(id);
	  $('#topicName').val(name);
	  $('#topicDescription').val(desc);
}



function showAddForm()
{
	  $('#addeditForm').show();
	  $('#topicId').val("");
	  $('#topicName').val("");
	  $('#topicDescription').val("");
}


function closeAddEditForm(id,name,description)
{
	  $('#addeditForm').hide();
}

function deleteSubConfirm(formName,topicId,topicName)
{
	
	var answer = confirm ("This will delete the Topic: '"+topicName+"'. Are you sure, you want to proceed?")
	if(answer)
	{
		document.contentForm.action='deleteTopic';
		$('#deleteId').val(topicId);
		document.contentForm.deleteId = topicId;
		document.contentForm.submit();
		return true;			
	}
	return false;
}


function checkAddEdit(){
	var name = document.subjectForm.topicName;
	var desc = document.subjectForm.topicDescription;
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
       <!-- <td width="1%" bgcolor="#FFCC00"> </td> 
       -->
       <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.topic}</td>
     </tr>
     <tr>
     	<td >
    	  <table border="0" class="noBorderTable" align="left">
  			<tr>
    			<td class="textHead2" style="padding-left:4px;">University Name</td>
	                <td class="textHead2" style="padding-left:18px;"> ${session.university.universityName}</td>       
		 	</tr>
		   </table>
	 	</td>
	 </tr>
	<g:form action="updateSequence" name="sequenceForm" method="post" >
		<input type="hidden" name="sequenceOrder" id="sequenceOrder"/>
	</g:form>
     <tr>
     	<td colspan="2">
 		<g:form action="index" name="subjectSelectForm" method="post" >
		<table border="1" width="100%" class="noBorderTable">
		    <tbody>
		    <tr class="textHead2">
		        <td width="15%">Select ${grailsApplication.config.module}</td>
		        <td colspan="3">
		        <g:select style="width:252px" name="subjectId"
		          from="${subjectList}" optionValue="subjectName"
		          optionKey="id" size="1" onChange="getTopics();" value="${session.selectedSubjectId}"/>
		        </td>
		    </tr>
		    <tr><td colspan="4">&nbsp;</td></tr>
		    </tbody>
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
		
			
			<g:form action="save" name="contentForm" method="post" >
			
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
					 <g:each in="${topicList}" status="i" var="topic">     
						<tr class="text" id="${topic.id}" >
						<td align="center">${i+1 }</td>
						<td align="left">
						<g:link action="index" controller="experiment" params="[topicId:topic.id]">${fieldValue(bean:topic, field:'topicName')}</g:link>
						</td>
					    <td align="left">${fieldValue(bean:topic, field:'topicDescription')}</td>
					    <td align="center">
					    <a href="javascript:showEditForm(${fieldValue(bean:topic, field:'id')},'${topic.topicName.encodeAsBase64()}','${topic.topicDescription.encodeAsBase64()}');"><img src="${hostname}/aell/images/Edit.gif" title="Edit Topic" height="20" width="20"></img></a>
					    </td>
					    <td align="center">					       			
					       			<input type="image" id="delete_submit" name="delete_submit"  src="${hostname}/aell/images/Delete.gif" title="Delete Lab" alt="Submit" onClick="deleteSubConfirm(this,${fieldValue(bean:topic, field:'id')},'${fieldValue(bean:topic, field:'topicName')}')"/>  
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
		 <a href="javascript:showAddForm();"><img src="${hostname}/aell/images/add.gif" title="Add New Topic" height="16" width="16"></img>Add new Topic</a>
        </td>
   		 </tr>
   		 
	 	<tr>
	 	<td colspan="2">
	 	
	 		<div id="addeditForm">
		 		<g:form action="saveTopic" name="subjectForm" method="post" >
				    <input name="topicId" id="topicId" type="hidden"/>
				    <table border="0" width="50%" style="border:dotted">
				    	<tr class="textHead1"><td colspan="2" align="center"><u>New/Edit</u></td>
				        <tr class="text"><td width="20%">${grailsApplication.config.topic_text}</td>
				        <td>
				        	<input name="topicName" id="topicName" type="text"  value="" style="width:300px"  maxlength="100"/>
				        </td>
				        </tr>
				        <tr class="text">
				        <td>${grailsApplication.config.topic_desc}</td>
				        <td><textarea name="topicDescription" id="topicDescription" rows="5" cols="35"></textarea></td>
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