

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.CreateDepartment.head"/></title>         
    </head>
    <body>
    <div class="wrapper">

        <div class="body">
            <h1><g:message code="default.CreateDepartment.head"/></h1>
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
                                	<label for="code"><g:message code="default.DepartmentCode.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'departmentCode','errors')}">
                                    <input type="text" id="departmentCode" name="departmentCode" value="${fieldValue(bean:partyDepartmentInstance,field:'departmentCode')}"/>
                                    <label for="departmentCode" style="color:blue;font-weight:bold"> <g:message code="default.department.shortname.label"/></label> 
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.DepartmentName.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:partyDepartmentInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Institution.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'party','errors')}">
                                  <strong>  ${partyInstance.code}</strong>
                                    <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:partyInstance,field:'id')}"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateDepartment()" /></span>
                </div>
            </g:form>
        </div>
         <div class="body">
            <h1><g:message code="default.DepartmentList.head"/></h1>
           
            <div class="list">
                <table>
                    <thead>
                        <tr>
                    	    <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
							<g:sortableColumn property="departmentCode" title="${message(code: 'default.DepartmentCode.label')}" />
                   	        <g:sortableColumn property="departmentName" title="${message(code: 'default.DepartmentName.label')}" />
                    	    <g:sortableColumn property="party" title="${message(code: 'default.Institution.label')}" />
                   	        <th><g:message code="default.Edit.label"/></th>
                       
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyDepartmentInstanceList}" status="i" var="partyDepartmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td>${i+1}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'departmentCode')}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'party.code')}</td>

                        	 <td><g:link action="edit" id="${partyDepartmentInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
