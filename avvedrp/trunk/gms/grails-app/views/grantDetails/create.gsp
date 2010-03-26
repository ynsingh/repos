

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create GrantDetails</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantDetails List</g:link></span>
        </div>
        <div class="body">
            <h1>Create GrantDetails</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantDetailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantDetailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantMaster">Grant Master:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'grantMaster','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${GrantMaster.list()}" name="grantMaster.id" value="${grantDetailsInstance?.grantMaster?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="financialYear">Financial Year:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'financialYear','errors')}">
                                    <input type="text" id="financialYear" name="financialYear" value="${fieldValue(bean:grantDetailsInstance,field:'financialYear')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="budgetStartDate">Budget Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'budgetStartDate','errors')}">
                                    <g:datePicker name="budgetStartDate" value="${grantDetailsInstance?.budgetStartDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="budgetEndDate">Budget End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'budgetEndDate','errors')}">
                                    <g:datePicker name="budgetEndDate" value="${grantDetailsInstance?.budgetEndDate}" ></g:datePicker>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantAmount">Grant Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'grantAmount','errors')}">
                                    <input type="text" id="grantAmount" name="grantAmount" value="${fieldValue(bean:grantDetailsInstance,field:'grantAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'description','errors')}">
                                    <g:textArea name="description" value="${fieldValue(bean:grantDetailsInstance,field:'description')}" rows="2" cols="20"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantDetailsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:grantDetailsInstance,field:'activeYesNo')}" />
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
    </body>
</html>
