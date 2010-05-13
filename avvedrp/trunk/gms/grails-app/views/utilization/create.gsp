

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Utilization</title>   
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Utilization List</g:link></span>
        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Create Utilization</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${utilizationInstance}">
            <div class="errors">
                <g:renderErrors bean="${utilizationInstance}" as="list" />
            </div>
            </g:hasErrors>
            
                <div class="dialog">
                    <table>
                        <tbody>
                        <input type="hidden" name="projectsId" value="${params.id}" />
                        <tr class="prop">
                            <td valign="top" class="name">Project Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectInstance, field:'name')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Project StartDate:</td>
                            
                            <td valign="top" class="value"><g:formatDate format="dd/MM/yyyy" date="${projectInstance.projectStartDate}"/></td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Project EndDate:</td>
                            
                            <td valign="top" class="value"><g:formatDate format="dd/MM/yyyy" date="${projectInstance.projectEndDate}"/></td>
                            
                        </tr>
                        <div>    
                        <tr class="prop">
                                <td valign="top" class="name">
                                <label>Documents:</label>
                        </td>
                         <td valign="top" class="value">
                        	<g:jasperReport jasper="UtilizationCertificate" format="PDF" name="Utilization Certificate" >
										             <input type="hidden" name="id" value="${projectInstance.id}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						 </g:jasperReport>
						 </td>
						 </tr>  
						  <tr class="prop">
                                <td valign="top" class="name">
                                
                        </td>
                         <td valign="top" class="value">
						 <g:jasperReport jasper="StatementOFAccounts" format="PDF" name="Statement Of Accounts" >
										            <input type="hidden" name="id" value="${projectInstance.id}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						 </g:jasperReport>
                         </td>
                         </tr>                                             
                         </div>                           
                        </tbody>
                    </table>
                </div>
                <g:if test="${!utilizationInstanceCheck}">
                <div class="buttons">
                 <g:form action="save" method="post" controller="utilization" id="${fieldValue(bean:projectInstance, field:'id')}">
                <input type="hidden" name="projectsId" value="${params.id}" />
                    <span class="button"><g:actionSubmit value="Submit" action="save"/></span>
                 </g:form>
                </div>
               </g:if>
            
        </div>
        </div>
       </div>
    </body>
</html>
