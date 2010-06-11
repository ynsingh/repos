

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Notification List</title>
    </head>
  
    <body>
        <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Notification</g:link></span>
        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Notification List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${notificationInstanceList}">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                             <g:sortableColumn property="id" title="SlNo" />
                   	        
                        
                   	        <g:sortableColumn property="project.code" title="Project" />
                   	    
                   	        <g:sortableColumn property="project.projectType.type" title="Project Type" />
                   	       <g:sortableColumn property="notificationCode" title="Notification Code" />
                   	    
                   	        <g:sortableColumn property="notificationDate" title="Notification Date" />

                            <g:sortableColumn property="proposalSubmissionLastDate" title="Last Date for Proposal Submission" />
                   	        <g:sortableColumn property="applicationForm" title="Application Form" />
                   	        
                   	        <g:sortableColumn property="eligibilitydocument" title="Upload Attachments" />
                       		
                       		<th>Send Mail</th>
                       		
                       		<th>Proposals</th>
                   	        
                   	        <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${notificationInstanceList}" status="i" var="notificationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                              <td>${(i + 1)}</td>
                        
                            
                        
                            <td>${fieldValue(bean:notificationInstance, field:'project.code')}</td>
                        
                            <td>${fieldValue(bean:notificationInstance, field:'project.projectType.type')}</td>
                            <td>${fieldValue(bean:notificationInstance, field:'notificationCode')}</td>
                        <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance.notificationDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance.proposalSubmissionLastDate}"/></td>
                             <td><a href="${createLinkTo(dir:'proposalApplication',file:notificationInstance.applicationForm,absolute:true)}" target="_new">${notificationInstance.applicationForm}</a></td>
                            
                           
                            
                           <td><g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:notificationInstance, field:'id')}" params="[documentType:'Notification']">Upload Attachments</g:link></td>
                          
                            <td><g:link action="list" controller='notificationsEmails' id="${fieldValue(bean:notificationInstance, field:'id')}">Send Mail</g:link></td>
                             
                            <td><g:link action="proposalList" controller='proposal' id="${fieldValue(bean:notificationInstance, field:'id')}">Proposals</g:link></td>
                             
                            <td><g:link action="edit" id="${fieldValue(bean:notificationInstance, field:'id')}">edit</g:link></td>
                            
                            </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>
            No Records Available</br>
            </g:else>
            </div>
            </div>
        </div>
    </body>
</html>
