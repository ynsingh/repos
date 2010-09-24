

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create  Department</title>         
    </head>
    <body>
    <div class="wrapper">

        <div class="body">
            <h1>Create Department</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyDepartmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyDepartmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="departmentCode">Department Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'departmentCode','errors')}">
                                    <input type="text" id="departmentCode" name="departmentCode" value="${fieldValue(bean:partyDepartmentInstance,field:'departmentCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Department Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:partyDepartmentInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${partyList}" name="party.id" value="${partyDepartmentInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'activeYesNo','errors')}">
                                         <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:partyDepartmentInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validateDepartment()" /></span>
                </div>
            </g:form>
        </div>
         <div class="body">
            <h1>Department List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <th>SlNo</th>
                        
                   	        <g:sortableColumn property="departmentCode" title="Department Code" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="party" title="Party" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                   	        
                   	        <th>Edit</th>
                       
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyDepartmentInstanceList}" status="i" var="partyDepartmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td>${i+1}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'departmentCode')}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'party.code')}</td>
                        
                        	 <td><g:if test="${fieldValue(bean:partyDepartmentInstance, field:'activeYesNo') == 'Y'}">
	    							 ${'YES'}
	    							 </g:if>
	    							 <g:else>
	    							 ${'NO'}
	    							 </g:else>
	                         </td>
                        	 
                        	 <td><g:link action="edit" id="${partyDepartmentInstance.id}">Edit</g:link></td>
                        	
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
