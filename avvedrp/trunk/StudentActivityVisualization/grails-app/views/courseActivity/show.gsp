

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show CourseActivity</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CourseActivity List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CourseActivity</g:link></span>
        </div>
        	<div align ="center">
	 	<h2>LMS: ${session.LMS} &nbsp;&nbsp;&nbsp;&nbsp; Site: ${session.siteName}</h2>
	 </div>
        <div class="body">
            <h1>Show CourseActivity</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'courseDescription')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course ID:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'courseID')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course Start Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'courseStartDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ini Thrd Count:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'iniThrdCount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ini Tot Posts:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'iniTotPosts')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tot Sess:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'totSess')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tot Time:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'totTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tot View:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'totView')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tot View Res:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseActivityInstance, field:'totViewRes')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${courseActivityInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
