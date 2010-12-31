

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit CourseActivity</title>
    </head>
    <body>
        <div class="nav">
          <span class="menuButton"><a class="home" href="${resource(dir:'courseActivity/listGraph')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CourseActivity List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CourseActivity</g:link></span>
        </div>
        <div class="body">
            <h1>Edit CourseActivity</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${courseActivityInstance}">
            <div class="errors">
                <g:renderErrors bean="${courseActivityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${courseActivityInstance?.id}" />
                <input type="hidden" name="version" value="${courseActivityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseDescription">Course Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'courseDescription','errors')}">
                                    <input type="text" id="courseDescription" name="courseDescription" value="${fieldValue(bean:courseActivityInstance,field:'courseDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseID">Course ID:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'courseID','errors')}">
                                    <input type="text" id="courseID" name="courseID" value="${fieldValue(bean:courseActivityInstance,field:'courseID')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseStartDate">Course Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'courseStartDate','errors')}">
                                    <input type="text" id="courseStartDate" name="courseStartDate" value="${fieldValue(bean:courseActivityInstance,field:'courseStartDate')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="iniThrdCount">Ini Thrd Count:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'iniThrdCount','errors')}">
                                    <input type="text" id="iniThrdCount" name="iniThrdCount" value="${fieldValue(bean:courseActivityInstance,field:'iniThrdCount')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="iniTotPosts">Ini Tot Posts:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'iniTotPosts','errors')}">
                                    <input type="text" id="iniTotPosts" name="iniTotPosts" value="${fieldValue(bean:courseActivityInstance,field:'iniTotPosts')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totSess">Tot Sess:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'totSess','errors')}">
                                    <input type="text" id="totSess" name="totSess" value="${fieldValue(bean:courseActivityInstance,field:'totSess')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totTime">Tot Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'totTime','errors')}">
                                    <input type="text" id="totTime" name="totTime" value="${fieldValue(bean:courseActivityInstance,field:'totTime')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totView">Tot View:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'totView','errors')}">
                                    <input type="text" id="totView" name="totView" value="${fieldValue(bean:courseActivityInstance,field:'totView')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totViewRes">Tot View Res:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseActivityInstance,field:'totViewRes','errors')}">
                                    <input type="text" id="totViewRes" name="totViewRes" value="${fieldValue(bean:courseActivityInstance,field:'totViewRes')}"/>
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
