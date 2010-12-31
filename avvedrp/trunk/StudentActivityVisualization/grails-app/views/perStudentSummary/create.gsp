

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create PerStudentSummary</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">PerStudentSummary List</g:link></span>
        </div>
        <div class="body">
            <h1>Create PerStudentSummary</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${perStudentSummaryInstance}">
            <div class="errors">
                <g:renderErrors bean="${perStudentSummaryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="resourcesCount">Resources Count:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'resourcesCount','errors')}">
                                    <input type="text" id="resourcesCount" name="resourcesCount" value="${fieldValue(bean:perStudentSummaryInstance,field:'resourcesCount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sessionCount">Session Count:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'sessionCount','errors')}">
                                    <input type="text" id="sessionCount" name="sessionCount" value="${fieldValue(bean:perStudentSummaryInstance,field:'sessionCount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentName">Student Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'studentName','errors')}">
                                    <input type="text" id="studentName" name="studentName" value="${fieldValue(bean:perStudentSummaryInstance,field:'studentName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="timeSpent">Time Spent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'timeSpent','errors')}">
                                    <input type="text" id="timeSpent" name="timeSpent" value="${fieldValue(bean:perStudentSummaryInstance,field:'timeSpent')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId">User Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'userId','errors')}">
                                    <input type="text" id="userId" name="userId" value="${fieldValue(bean:perStudentSummaryInstance,field:'userId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="viewCount">View Count:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:perStudentSummaryInstance,field:'viewCount','errors')}">
                                    <input type="text" id="viewCount" name="viewCount" value="${fieldValue(bean:perStudentSummaryInstance,field:'viewCount')}" />
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
