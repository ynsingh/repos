


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'universityMaster.label', default: 'UniversityMaster')}" />
        <title><g:message code="default.UniversityInstitutionMapCreate.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper">
         <div class="body">
            <h1><g:message code="default.UniversityInstitutionMapCreate.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
             <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:hasErrors bean="${universityInstitutionMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${universityInstitutionMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        <input type="hidden" id="id" name="id" value="${universityInstitutionMapInstance.id}"/>
                          <tr class="prop">
                               
                                <td valign="top" class="name">
                                    <label for="universityMaster"><g:message code="default.universityMaster.nameOfUniversity.label" /></label>
                                     <label for="universityMaster" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityInstitutionMapInstance, field: 'universityMaster', 'errors')}">
                                    <g:select id="university.id"  name="university.id" from="${universityMasterInstanceList}" optionKey="id" optionValue="nameOfUniversity" value="${universityInstitutionMapInstance?.universityMaster?.id}" noSelection="['null': '-Select-']" />
                                </td>
                                <td valign="top" class="name">
                                    <label for="party"><g:message code="default.Institution.label" /></label>
                                     <label for="party" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: universityInstitutionMapInstance, field: 'party', 'errors')}">
                                    <g:select id="party.id"  name="party.id" from="${partyInstanceList}" optionKey="id" optionValue="code" value="${universityInstitutionMapInstance?.party?.id}" noSelection="['null': '-Select-']" />
                                </td>
                               
                          </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="updateInstitutionMap" onClick="return validateUniversityInstitutionMap()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="deleteInstitutionMap" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
          </div>
        </div>
    </body>
</html>