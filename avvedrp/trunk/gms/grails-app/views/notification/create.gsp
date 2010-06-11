

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Notification</title>     
        <ckeditor:resources />    
    </head>
 
 <script>
 
 function validate()
    {     
	    if( ( (document.getElementById("projects").value) == 'null') || ( (document.getElementById("projects").value) == '') )
	    {
		    alert("Please enter Project");
		    document.getElementById("projects").focus();
		    return false;
	    }
	    if( ( (document.getElementById("notificationCode").value) == 'null') || ( (document.getElementById("notificationCode").value) == '') )
	    {
		    alert("Please enter notification code");
		     document.getElementById("notificationCode").focus();
		    return false;
	    }
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
            <h1>Create Notification</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form  action="save"  method="post" enctype="multipart/form-data" controller="notification">  
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="project">Project:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'project','errors')}">
                                    <g:select optionKey="id" optionValue="code" id="projects" from="${grantAllocationWithprojectsInstanceList.projects}"  name="project.id" value="${notificationInstance?.project?.id}" noSelection="['null':'select']"></g:select>                           
                                </td>
                            </tr> 
                        
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notificationCode">Notification Code:</label>
                                </td>
                                
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'notificationCode','errors')}">
                                    <input type="text" id="notificationCode" name="notificationCode" value="${fieldValue(bean:notificationInstance,field:'notificationCode')}"/>
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
                                <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'description','errors').encodeAsHTML()}">

                                <fckeditor:editor name="description" width="100%" height="300" toolbar="Standard" fileBrowser="default">
									
								</fckeditor:editor>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="applicationForm">Application Form:</label>
                                </td>
                                
                                <td valign="top" class="value">
                                    <input type="file" id="myFile" name="myFile"/>
                                </td>
                            </tr> 
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit"  onClick="return validate()" value="Create" /></span>
               
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
