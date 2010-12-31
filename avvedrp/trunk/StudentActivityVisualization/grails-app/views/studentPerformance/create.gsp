

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create StudentPerformance</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">StudentPerformance List</g:link></span>
        </div>
        <div class="body">
            <h1>Create StudentPerformance</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentPerformanceInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentPerformanceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseId">Course Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentPerformanceInstance,field:'courseId','errors')}">
                                    <input type="text" id="courseId" name="courseId" value="${fieldValue(bean:studentPerformanceInstance,field:'courseId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="scorePercent">Score Percent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentPerformanceInstance,field:'scorePercent','errors')}">
                                    <input type="text" id="scorePercent" name="scorePercent" value="${fieldValue(bean:studentPerformanceInstance,field:'scorePercent')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentId">Student Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentPerformanceInstance,field:'studentId','errors')}">
                                    <input type="text" id="studentId" name="studentId" value="${fieldValue(bean:studentPerformanceInstance,field:'studentId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="test_method">Testmethod:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentPerformanceInstance,field:'test_method','errors')}">
                                    <input type="text" id="test_method" name="test_method" value="${fieldValue(bean:studentPerformanceInstance,field:'test_method')}"/>
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
