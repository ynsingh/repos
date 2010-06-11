

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Proposal</title> 
        <ckeditor:resources />            
    </head>
        <script>
        function validate()
        {
	    	if(document.getElementById("code").value == "")
	    	{
	    		alert("Please Enter the Proposal Code");
			    document.getElementById("code").focus();
			    return false;
	    	}
	    var proposalSubmitteddateYear = document.getElementById("proposalSubmitteddate_year").value;
    	var proposalSubmitteddateMonth = document.getElementById("proposalSubmitteddate_month").value;
    	var proposalSubmitteddateDate = document.getElementById("proposalSubmitteddate_day").value;
	    	
	    var proposalSubmissionLastDate = document.getElementById("proposalSubmissionLastDate").value;
    	  		
    	var proposalSubmissionLastDateYear=proposalSubmissionLastDate.substring(0,proposalSubmissionLastDate.indexOf("-"));
    	var proposalSubmissionLastDateMonth=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.indexOf("-")+1,proposalSubmissionLastDate.lastIndexOf("-"));
    	var proposalSubmissionLastDateDate=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.lastIndexOf("-")+1,proposalSubmissionLastDate.lastIndexOf(" "));
	   	
	   	var newproposalSubmitteddate = new Date(proposalSubmitteddateYear,proposalSubmitteddateMonth-1,proposalSubmitteddateDate);
	   	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate); 
	    
	    if (newproposalSubmitteddate>newproposalSubmissionLastDate)
    		{
    			alert("Last Date for Proposal submission completed")
    			return false;
    		}	
	    	return true;
    	}
    </script>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
        </div>
        <div class="body">
            <h1>Create Proposal</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
            </g:hasErrors>
        <g:form action="save" method="post" >
                <div class="dialog">
                    <table class="tablewrapper" border="0" cellspacing="0" cellpadding="0">
                        <tbody>
                        <tr class="prop">
                         <td>
                         <input type="hidden" id="proposalSubmissionLastDate" name="proposalSubmissionLastDate" value="${fieldValue(bean:proposalInstance, field:'notification.proposalSubmissionLastDate')}"/>
                         <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>                    
                         <input type="hidden" id="documentType" name="documentType" value="Proposal" />                   
                                                   </td> 
                            </tr>
                       		<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="code" name="code" value="${fieldValue(bean:proposalInstance,field:'code')}"/>
                                </tr> 
                            
                            <tr>
                            <td valign="top" class="name">
                                    <label for="proposalSubmitteddate">Submitted Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'proposalSubmitteddate','errors')}">
                                   <calendar:datePicker name="proposalSubmitteddate" defaultValue="${new Date()}" value="${notificationInstance?.proposalSubmitteddate}" dateFormat= "%d/%m/%Y" disabled="true" />
                             <%--  
                             <g:formatDate format="dd-MM-yyyy" date="${new Date()}"/>
                             <input type="text" format="yyyy-mm-dd" name="fieldname">
                                
                                <input type="text"  format= "dd-MM-yyyy" name="proposalSubmitteddate" value="${new Date()}"   />
                                --%>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'description','errors').encodeAsHTML()}">

                                <fckeditor:editor name="description" width="100%" height="300" toolbar="Standard" fileBrowser="default">
									
								</fckeditor:editor>  
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                <%--
                    <span class="button"><input class="save" type="submit" value="Save" onClick="return validate()" /></span>
                    
                    <span class="button"><input class="submit" type="submit" value="Submit" onClick="return validate()" /></span>
                --%>
                	<span class="button"><g:actionSubmit class="save" action="save" value="Save" onClick="return validate()"  /></span>
                   
              
                </div>
            </g:form>
<tr>
      
   
       <td scope="row"> <div class="list">
          
            <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                         <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
                                   
                   	       
                   	        <g:sortableColumn property="id" title="Slno" />
                   	        <g:sortableColumn property="code" title="Code" />
                   	        <th>Party</th>
                   	         <g:sortableColumn property="proposalSubmitteddate" title="Submitted Date" />
                   	          
                   	       
                   	        <g:sortableColumn property="proposalDocumentationPath" title="UploadAttachments" />                 
                   	      	<th>Edit and Submit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalInstanceList}" status="i" var="proposalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                             <td>${i+1}</td>
                            
                             <td>${fieldValue(bean:proposalInstance, field:'code')}</td>
                             <td>${fieldValue(bean:proposalInstance, field:'party.code')}</td>
                               <td><g:formatDate format="dd-MM-yyyy" date="${proposalInstance.proposalSubmitteddate}"/></td>
                            <td><g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:proposalInstance, field:'id')}" params="[documentType:'Proposal']">upload attachments</g:link></td>
                            <td><g:link action="edit" id="${proposalInstance.id}">Edit</g:link></td>
                            
                        
                           
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        
        </div>
        </td>
        </tr>
    </body>
</html>
