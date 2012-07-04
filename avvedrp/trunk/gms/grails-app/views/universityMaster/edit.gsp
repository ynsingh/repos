


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'universityMaster.label', default: 'UniversityMaster')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${universityMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${universityMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${universityMasterInstance?.id}" />
                <g:hiddenField name="version" value="${universityMasterInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                          <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfUniversity"><g:message code="universityMaster.nameOfUniversity.label" default="Name Of University" /></label>
                                    <label for="nameOfUniversity" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'nameOfUniversity', 'errors')}">
                                    <g:textField name="nameOfUniversity" value="${universityMasterInstance?.nameOfUniversity}" />
                                </td>
                                
                                   <td valign="top" class="name">
                                    <label for="code"><g:message code="universityMaster.code.label" default="Code" /></label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'code', 'errors')}">
                                    <g:textField name="code" value="${universityMasterInstance?.code}" />
                                </td>
                                
                            </tr>
                        
                           <tr class="prop">
                               
                               <td valign="top" class="name">
                                    <label for="email"><g:message code="universityMaster.email.label" default="Email" /></label>
                                    <label for="email" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${universityMasterInstance?.email}" />
                                </td>
                                
                                 <td valign="top" class="name">
                                    <label for="phoneNo"><g:message code="universityMaster.phoneNo.label" default="Phone No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'phoneNo', 'errors')}">
                                    <g:textField name="phoneNo" value="${universityMasterInstance?.phoneNo}" />
                                </td>
                                
                            </tr>
                        
                           <tr class="prop">
                               
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="universityMaster.address.label" default="Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityMasterInstance, field: 'address', 'errors')}">
                                    <g:textArea name="address" value="${universityMasterInstance?.address}" />
                                </td>
                               
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateUniversityMaster()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
     </div>
    </body>
</html>
