

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Projects</title>
    </head>
    
    <script>
    function validateProject(){
    	if(document.getElementById("name").value == ""){
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	if(document.getElementById("code").value == ""){
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    	if( ( (document.getElementById("projectType.id").value) == 'null') || ( (document.getElementById("projectType.id").value) == '') )
	    {
		    alert("Please enter the Project Type");
		    return false;
	    }
    	return true;
    
    }
    </script>
    
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Projects List</g:link></span>
              
        </div>
        <div class="body">
            <h1>Edit Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectsInstance?.id}" />
                <div class="dialog">
                    <table width="950">
                        <tbody>
                        
                             <tr class="prop">
                               
                           <g:if test="${projectsInstance.parent != null}">
                            <td valign="top" class="name">
                                    <label for="parent">Main Project:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
                                
                                    <g:select optionKey="id" optionValue="code"  from="${Projects.findById(projectsInstance.parent.id)}" name="parent.id" value="${projectsInstance?.parent?.id}" ></g:select>
                                </td>
                          </g:if>
                                
                            </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Project Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}">
                                    <g:select optionKey="id" optionValue="type" id="projectType.id" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'select']" ></g:select>
                                </td>
                            </tr> 

                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStartDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                    <calendar:datePicker name="projectStartDate" value="${projectsInstance?.projectStartDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y" />
                                </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectEndDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:projectsInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validateProject()" /></span>
                </div>
            </g:form>
            </div>
        </div>
    </body>
</html>
