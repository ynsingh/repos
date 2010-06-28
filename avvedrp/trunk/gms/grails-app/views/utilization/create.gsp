

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
            <span class="menuButton"><g:link controller="grantAllocation" class="create" action="projectDash" id="${session.ProjectID}">Project List</g:link></span>
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
             <g:form action="save" method="post" controller="utilization" id="${fieldValue(bean:projectInstance, field:'id')}">
              <div class="dialog">
                    <table>
                        <tbody>
                        
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
                        <tr>
                         <td valign="top" class="name">
                                    <label for="grantPeriod">Grant Period:</label>
                         <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'grantPeriod','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.findAll('from GrantPeriod GP order by defaultYesNo desc')}"  name="grantPeriod.id" value="${utilizationInstance?.grantPeriod?.id}"  ></g:select>
                                </td>
                        </tr>
                        <div>    
                        <tr class="prop">
                                <td valign="top" class="name">
                                <label>Documents:</label>
                        </td>
                        <td>
                        <g:link  controller='grantAllocation' action="reportView"  id="${projectInstance.id}">View</g:link>
                        </td>
						 </tr>  
						                                              
                         </div>                           
                        </tbody>
                    </table>
                </div>
                
                <div>
                <input type="hidden" name="projects.id" value="${projectInstance.id}" />
                    <g:actionSubmit class="inputbutton" value="Submit" action="save"/>
                
                </div>
                </g:form>
            
        </div>
        </div>
       </div>
    </body>
</html>
