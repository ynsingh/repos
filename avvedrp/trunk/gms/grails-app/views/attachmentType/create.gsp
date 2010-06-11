

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create AttachmentType</title>         
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">AttachmentType List</g:link></span>
        </div>
        <div class="body">
            <h1>Create AttachmentType</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attachmentTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attachmentTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr>
                            <td valign="top" class="name">
                                    <label for="documentType">Document Type :</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'documentType','errors')}">
                                    <g:select name="documentType" from="${['Proposal', 'Notification']}" value="${attachmentTypeInstance?.documentType}" ></g:select>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attachmentTypeInstance,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:attachmentTypeInstance,field:'type')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attachmentTypeInstance,field:'description','errors')}">
                                    <g:textArea id="description" name="description" value="${fieldValue(bean:attachmentTypeInstance,field:'description')}" rows="3" cols="30"/>
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
        </div>
    </body>
</html>
