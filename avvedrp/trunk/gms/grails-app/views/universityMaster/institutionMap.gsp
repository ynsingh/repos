


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
		<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
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
                    <span class="button"><g:actionSubmit name="saveInstitutionMap" onClick="return validateUniversityInstitutionMap()"  value="${message(code: 'default.Save.button')}"  action="saveInstitutionMap"/> </span>
                </div>
            </g:form>
          </div>
           <div class="body">
         <h1><g:message code="default.UniversityInstitutionMapList.label" args="[entityName]" /></h1>
         
         <div class="list">
            <g:if test="${universityInstitutionInstanceList}">
                <table>
                    <thead>
                        <tr>
                     
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                           
                            <th><g:message code="default.universityMaster.nameOfUniversity.label" /></th>
                            
                            <th><g:message code="default.Institution.label" /></th>
                            
                        	<g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                         
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${universityInstitutionInstanceList}" status="i" var="UniversityInstitutionMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            
                            <td>${fieldValue(bean: UniversityInstitutionMapInstance, field: "universityMaster.nameOfUniversity")}</td>
                            
                            <td>${fieldValue(bean: UniversityInstitutionMapInstance, field: "party.code")}</td>
                            
                            <td><g:link action="editInstitutionMap" id="${fieldValue(bean:UniversityInstitutionMapInstance, field:'id')}" ><g:message code="default.Edit.label"/></g:link></td>
                            
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
