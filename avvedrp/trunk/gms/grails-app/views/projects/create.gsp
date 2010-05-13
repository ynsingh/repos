

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Projects</title>         
    </head>
    
    <script>
    function validateProject(){
    	if(document.getElementById("name").value == "")
    	{
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	if(document.getElementById("code").value == "")
    	{
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    	if( ( (document.getElementById("projectType.id").value) == 'null') || ( (document.getElementById("projectType.id").value) == '') )
	    {
		    alert("Please enter the Project Type");
		    return false;
	    }
    	
    	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
    	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
    	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
    	
    	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
    	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
    	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	 
    	
    	if (newProjectStartDate>newProjectEndDate)
    	{
    		alert("Project End Date should be greater than Start Date")
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
            <h1>Create Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table width="950">
                        <tbody>
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
                                    <input type="text"  id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Project Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}">
                                    <g:select optionKey="id" optionValue="type" id="projectType" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'select']" ></g:select>
                                </td>
                            </tr> 
                                                    
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStartDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                <calendar:datePicker name="projectStartDate" defaultValue="${new Date()}" value="${projectsInstance?.projectStartDate}" dateFormat= "%d/%m/%Y"/>
                                    </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectEndDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                            
                            
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:projectsInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" onClick="return validateProject()" value="Create" /></span>
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
