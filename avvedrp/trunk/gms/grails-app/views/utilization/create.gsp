

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Utilization</title>   
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Utilization List</g:link></span>
        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Create Utilization</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${utilizationInstance}">
            <div class="errors">
                <g:renderErrors bean="${utilizationInstance}" as="list" />
            </div>
            </g:hasErrors>
            
            <g:form action="save" method="post" enctype="multipart/form-data" controller="utilization">
                <div class="dialog">
                    <table>
                        <tbody>
                        <input type="hidden" name="projectsId" value="${params.id}" />
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attachmentPath">Attachment Path:</label>
                                </td>
                                <td valign="top" class="value">
                                    <input type="file" id="attachmentName" name="attachmentName"/>
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
       </div>
    </body>
</html>
