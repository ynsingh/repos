

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Institution.EditInstitution.head"/></title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="body">
            <h1><g:message code="default.Institution.EditInstitution.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyInstance?.id}" />
                <div class="dialog">
                   <table >
                        <tbody>
                        
                        
                        <tr class="prop">
                        
                            </tr> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfTheInstitution"><g:message code="default.Name.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'nameOfTheInstitution','errors')}">
                                    <input type="text" size="45" id="nameOfTheInstitution" name="nameOfTheInstitution" value="${fieldValue(bean:partyInstance,field:'nameOfTheInstitution')}"/>
                                </td>
                           </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:partyInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        
                          
                          
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.Address.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'address','errors')}">
                                   <g:textArea name="address" value="${fieldValue(bean:partyInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone"><g:message code="default.Phone.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:partyInstance,field:'phone')}"/>
                                </td>
                           </tr> 
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="default.Email.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:partyInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="${message(code: 'default.Update.button')}" onClick="return validateParty()" /></span>
                </div>
            </g:form>
       
 </div> </div>
    </body>
</html>
