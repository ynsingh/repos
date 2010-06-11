

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Notification</title>
    </head>
     
<script>
 function validate()
    { 
        var notificationDateYear = document.getElementById("notificationDate_year").value;
    	var notificationDateMonth = document.getElementById("notificationDate_month").value;
    	var notificationDateDate = document.getElementById("notificationDate_day").value;
    	
    	var proposalSubmissionLastDateYear = document.getElementById("proposalSubmissionLastDate_year").value;
    	var proposalSubmissionLastDateMonth = document.getElementById("proposalSubmissionLastDate_month").value;
    	var proposalSubmissionLastDateDate = document.getElementById("proposalSubmissionLastDate_day").value;
    	
    	var newnotificationDate = new Date(notificationDateYear,notificationDateMonth-1,notificationDateDate);
    	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate);
    	 
    	
    	if (newnotificationDate>newproposalSubmissionLastDate)
    	{
    		alert("Last Date for ProposalSubmission should be greater than Notification Date")
    		return false;
    	}
    	 if( ( (document.getElementById("project.id").value) == 'null') || ( (document.getElementById("project.id").value) == '') )
	    {
		    alert("Please enter Project");
		    document.getElementById("project.id").focus();
		    return false;
	    }
	    
	    
    	return true;
    	}
    </script>
    
    
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Notification List</g:link></span>
           
        </div>
        <div class="body">
            <h1>Edit Notification</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${notificationInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="project">Project:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'project','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Projects.list()}" name="project.id" value="${notificationInstance?.project?.id}" noSelection="['null':'select']"></g:select>
                                </td>
                            </tr> 
                        
                          
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notificationCode">Notification Code:</label>
                                </td>
                                
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'notificationCode','errors')}">
                                    <input type="text" id="notificationCode" name="notificationCode" value="${fieldValue(bean:notificationInstance,field:'notificationCode')}" disabled="true"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notificationDate">Notification Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'notificationDate','errors')}">
                                     <calendar:datePicker name="notificationDate" defaultValue="${new Date()}" value="${notificationInstance?.notificationDate}" dateFormat= "%d/%m/%Y"   />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalSubmissionLastDate">Last Date for ProposalSubmission:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'proposalSubmissionLastDate','errors')}">
                                <calendar:datePicker name="proposalSubmissionLastDate" defaultValue="${new Date()}" value="${notificationInstance?.proposalSubmissionLastDate}" dateFormat= "%d/%m/%Y"   />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'description','errors')}">
   								<fckeditor:editor name="description" width="100%" height="300" toolbar="Standard" fileBrowser="default" >
									${notificationInstance.description}
								</fckeditor:editor>
                                </td>
                            </tr> 
                        
                        	
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" onClick="return validate()"  value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
            </div>
        </div>
    </body>
</html>
