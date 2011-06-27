


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'university.label', default: 'University')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${universityInstance}">
            <div class="errors">
                <g:renderErrors bean="${universityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="univ_name"><g:message code="university.univ_name.label" default="Univname" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityInstance, field: 'univ_name', 'errors')}">
                                    <g:textField name="univ_name" value="${universityInstance?.univ_name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="univ_address"><g:message code="university.univ_address.label" default="Univaddress" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityInstance, field: 'univ_address', 'errors')}">
                                    <g:textField name="univ_address" value="${universityInstance?.univ_address}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="univ_email"><g:message code="university.univ_email.label" default="Univemail" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityInstance, field: 'univ_email', 'errors')}">
                                    <g:textField name="univ_email" value="${universityInstance?.univ_email}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
