

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Add Projects To PI</title>         
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
        </div>
                
       <table class="tablewrapper"> 
       <tr>
       <td> 
        <div class="body">
            <h1>Add Projects To PI</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsPIMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsPIMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator">Investigator:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'investigator','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${Investigator.findAll('from Investigator I where I.activeYesNo=\'Y\' ')}" name="investigator.id" value="${projectsPIMapInstance?.investigator?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${projectsList}" name="projects.id" value="${projectsPIMapInstance?.projects?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role">Role:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'role','errors')}">
                                    <g:select id="role" name="role"  from="${['PI','CO-PI']}" value="${projectsPIMapInstance?.role}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
         </td>
         </tr>
        
        
        
       <tr>
    <td>    <div class="body">
            <h1>Projects List for PI</h1>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <th>Investigator</th>
                   	    
                   	        <th>Projects</th>
                   	    
                   	        <g:sortableColumn property="role" title="Role" />
                        
                        	<th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsPIMapInstanceList}" status="i" var="projectsPIMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'investigator.name')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'projects.code')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'role')}</td>
                            
                            <td><g:link action="edit" id="${projectsPIMapInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProjectsPIMap.count()}" />
            </div>
        </div>
        </td>
        </tr>
        
         </table>
        </div>
    </body>
</html>
