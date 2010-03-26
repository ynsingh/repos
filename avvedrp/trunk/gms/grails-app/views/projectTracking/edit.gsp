

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Project Closure Edit</title>
    </head>
    
    <script>
    function validateProjectTracking(){
    	if(document.getElementById("percOfCompletion").value == ""){
    		alert("Please Enter Percentage Of Completion");
		    document.getElementById("percOfCompletion").focus();
		    return false;
    	}
    	else if(isNaN(document.getElementById("percOfCompletion").value) ){
    		alert("Please Enter Numeric Value For Percentage Of Completion");
		    document.getElementById("percOfCompletion").focus();
		    return false;
    	}
    	return true;
    
    }
    </script>
    
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create" id="${projectTrackingInstance.projects.id}">Project Closure List</g:link></span>
        </div>
        <div class="proptable">
        <table>
        
        <tr>
	        <td valign="top" >Project Code:</td>
	        <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
                               
            <td valign="top" >Project Name:</td>
            <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'name')}</strong></td>
               
        </tr>  
      	</table>   
        </div>
         <table class="tablewrapper" cellspacing="0" cellpadding="0">
        
        <tr >
	        <td >
        <div class="body">
            <h1>Project Closure Edit</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectTrackingInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectTrackingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectTrackingInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                                           
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStatus">Project Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
                                    <g:select name="projectStatus" from="${['Open','Deadline Passed','Grant Funded','Closed']}"  value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}" />
                                    <g:hiddenField name="projects.id" value="${projectTrackingInstance?.projects?.id}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="percOfCompletion">Percentage Of Completion:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'percOfCompletion','errors')}">
                                    <input type="text" id="percOfCompletion" name="percOfCompletion" value="${fieldValue(bean:projectTrackingInstance,field:'percOfCompletion')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfTracking"> Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'dateOfTracking','errors')}">
                                    <calendar:datePicker name="dateOfTracking" defaultValue="${new Date()}" value="${projectTrackingInstance?.dateOfTracking}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>  
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'remarks','errors')}">
                                    <g:textArea name="remarks" value="${fieldValue(bean:projectTrackingInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validateProjectTracking()"  /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
        </td>
        </tr>
         <tr>
      
   
       <td scope="row"> <div class="list">
          
            <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                    <thead>
        
	               <tr>
	                
	           	        <th>SlNo</th>
	                
	           	        <g:sortableColumn property="projectStatus" title="Project Status" />
	                
	           	        <g:sortableColumn property="percOfCompletion" title="% Of Completion" />
	                
	           	        <g:sortableColumn property="dateOfTracking" title="Date" />
	                
	           	        <g:sortableColumn property="remarks" title="Remarks" />
	                
	                </tr>
	            </thead>
	            <tbody>
	            <g:each in="${projectTrackingInstanceList}" status="i" var="projectTrackingInstance">
	                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                
	                    <td><g:link action="edit" id="${projectTrackingInstance.id}">${i+1}</g:link></td>
	                
	                    <td>${fieldValue(bean:projectTrackingInstance, field:'projectStatus')}</td>
	                
	                    <td><g:formatNumber number="${projectTrackingInstance.percOfCompletion}" format="###,##0.00" /></td>
	                
	                    <td><g:formatDate format="dd/MM/yyyy" date="${projectTrackingInstance.dateOfTracking}"/></td>
	                
	                    <td>${fieldValue(bean:projectTrackingInstance, field:'remarks')}</td>
	                
	                </tr>
	            </g:each>
	            </tbody>
	        </table>
	         </div>
        
        </td>
        </tr>
    </body>
</html>
