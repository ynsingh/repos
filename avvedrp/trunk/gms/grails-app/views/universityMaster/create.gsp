


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'universityMaster.label', default: 'UniversityMaster')}" />
        <title><g:message code="default.UniversityMasterCreate.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper">
         <div class="body">
		<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.UniversityMasterCreate.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${universityMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${universityMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                          <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfUniversity"><g:message code="default.universityMaster.nameOfUniversity.label"  /></label>
                                     <label for="nameOfUniversity" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'nameOfUniversity', 'errors')}">
                                    <g:textField name="nameOfUniversity" value="${universityMasterInstance?.nameOfUniversity}" />
                                </td>
                                
                                   <td valign="top" class="name">
                                    <label for="code"><g:message code="default.universityMaster.code.label" /></label>
                                     <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'code', 'errors')}">
                                    <g:textField name="code" value="${universityMasterInstance?.code}" />
                                </td>
                                
                            </tr>
                        
                           <tr class="prop">
                               
                               <td valign="top" class="name">
                                    <label for="email"><g:message code="default.universityMaster.email.label" /></label>
                                     <label for="email" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${universityMasterInstance?.email}" />
                                </td>
                                
                                 <td valign="top" class="name">
                                    <label for="phoneNo"><g:message code="default.universityMaster.phoneNo.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'phoneNo', 'errors')}">
                                    <g:textField name="phoneNo" value="${universityMasterInstance?.phoneNo}" />
                                </td>
                                
                            </tr>
                        
                           <tr class="prop">
                               
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.universityMaster.address.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'address', 'errors')}">
                                    <g:textArea name="address" value="${universityMasterInstance?.address}" />
                                </td>
                               
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateUniversityMaster()" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
          </div>
          <div class="body">
         <h1><g:message code="default.UniversityMasterList.label" args="[entityName]" /></h1>
         
         <div class="list">
            <g:if test="${universityMasterInstanceList}">
                <table>
                    <thead>
                        <tr>
                     
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                           
                            <th><g:message code="default.universityMaster.nameOfUniversity.label" /></th>
                            
                            <th><g:message code="default.universityMaster.code.label" /></th>
                            
                            <th><g:message code="default.universityMaster.email.label" /></th>
                             
                            <th><g:message code="default.universityMaster.address.label" /></th>
                            
                            <th><g:message code="default.universityMaster.phoneNo.label" /></th>
                        
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                         
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${universityMasterInstanceList}" status="i" var="UniversityMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            
                            <td>${fieldValue(bean: UniversityMasterInstance, field: "nameOfUniversity")}</td>
                            
                            <td>${fieldValue(bean: UniversityMasterInstance, field: "code")}</td>
                            
                            <td>${fieldValue(bean: UniversityMasterInstance, field: "email")}</td>
                            
							<td>${fieldValue(bean: UniversityMasterInstance, field: "address")}</td>
                            
                            <td>${fieldValue(bean: UniversityMasterInstance, field: "phoneNo")}</td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:UniversityMasterInstance, field:'id')}" ><g:message code="default.Edit.label"/></g:link></td>
                            
                            </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
            </div>
           </div>
        </div>
    </body>
</html>
