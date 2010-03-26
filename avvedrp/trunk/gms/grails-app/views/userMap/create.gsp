

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Mapping the User With Projects</title>         
    </head>
    <body>
    	<div class="wrapper">
    
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
           
        </div>
        
        
        <table class="tablewrapper">
        <tr>
        <td>
        
        <div class="body">
            <h1>Mapping the User With Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${userMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table cellspacing="0" cellpadding="0">
                        <tbody>
                        
                            
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user">User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:userMapInstance,field:'user','errors')}">
                                    <g:select optionKey="id"  optionValue="username" from="${userInstanceList}" name="user.id" value="${userMapInstance?.user?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:userMapInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${partyInstanceList}" optionValue="code" name="party.id" value="${userMapInstance?.party?.id}" ></g:select>
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
            <td>
            
            <h1>Users List</h1>
            
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <th>SlNo</th>
                        
                   	        <th>User Name</th>
                   	    
                   	        
                   	       <th>Institution</th>
                   	       <th>Edit</th>
                        
                   	   
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userMapInstanceList}" status="i" var="userMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'user.username')}</td>
                           
                            <td>${fieldValue(bean:userMapInstance, field:'party.code')}</td>
                             <td><g:link action="edit" id="${userMapInstance.id}">Edit</g:link></td>
                            
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </td>
            </tr>
            </table>
        </div>
    </body>
</html>
