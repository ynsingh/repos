

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit InstitutionMaster</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">InstitutionMaster List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New InstitutionMaster</g:link></span>
        </div>
        <div class="body">
            <h1>Edit InstitutionMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${institutionMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${institutionMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${institutionMasterInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:institutionMasterInstance,field:'code','errors')}">
                                    <input type="text" maxlength="100" id="code" name="code" value="${fieldValue(bean:institutionMasterInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:institutionMasterInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:institutionMasterInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:institutionMasterInstance,field:'address','errors')}">
                                <g:textArea name="address" value="${fieldValue(bean:institutionMasterInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone">Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:institutionMasterInstance,field:'phone','errors')}">
                                    <input type="text" maxlength="100" id="phone" name="phone" value="${fieldValue(bean:institutionMasterInstance,field:'phone')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:institutionMasterInstance,field:'activeYesNo','errors')}">
                                	<g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:institutionMasterInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
