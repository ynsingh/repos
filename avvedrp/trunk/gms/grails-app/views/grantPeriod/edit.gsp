

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit GrantPeriod</title>
    </head>
    
    <script>
    function validateGrantPeriod(){
    	if(document.getElementById("name").value == ""){
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	
    	return true;
    
    }
    </script>
    
    <body>
        <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Grant Period List</g:link></span>
            
        </div>
        <div class="body">
            <h1>Edit GrantPeriod</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantPeriodInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantPeriodInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantPeriodInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                          <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:grantPeriodInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'startDate','errors')}">
                                <calendar:datePicker name="startDate" defaultValue="${new Date()}" value="${grantPeriodInstance?.startDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'endDate','errors')}">
                                    <calendar:datePicker name="endDate" value="${grantPeriodInstance?.endDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                            </td>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'activeYesNo','errors')}">
                                         <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:grantPeriodInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="defaultYesNo">Default:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'defaultYesNo','errors')}">
                                         <g:select name="defaultYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:grantPeriodInstance,field:'defaultYesNo')}" />
                                </td>
                            </tr> 
                        
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validateGrantPeriod()"  /></span>
                </div>
            </g:form>
            </div>
        </div>
    </body>
</html>
