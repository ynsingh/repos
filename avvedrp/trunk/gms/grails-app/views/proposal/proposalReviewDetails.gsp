<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.ReviewDetails.head"/></title> 
    </head>
    <body>
        <div class ="wrapper">
        <div class="body">
        
           <h1><g:message code="default.ReviewDetails.head"/><h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

             <div class="dialog">
            <g:if test="${evalAnswerInstance[0]}">
       
            <div class="list">
       
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="person.username" title="${message(code: 'default.Reviewer.label')}" />
                            <g:each in="${evalItemInstance}" status="j" var="evalItemInstance">
                            <td><label name="Question:${j+1}" title="${evalItemInstance.item}">Question:${j+1}</label></td>
                            </g:each>
                            
                        </tr>
                    </thead>
                    <tbody>
                   
			       
                    <g:each in="${evalAnswerInstance}" status="i" var="evalAnswerInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                       
                       <td>${fieldValue(bean: evalAnswerInstance.person, field: "userRealName")} ${fieldValue(bean: evalAnswerInstance.person, field: "userSurName")}</td>
                      
                      <g:each in="${evalList}" status="j" var="evalList">
                      <g:if test="${evalList.person == evalAnswerInstance.person}">
                       <g:if test="${evalList==null}">
                       <td>
                      </td>
                      </g:if>
                      <g:else>
                      <td>${evalList.evalScaleOptions?.scaleOption}</td>
                      
                      </g:else>
                      </g:if>
                        </g:each>
                        
                     </tr>
                   
                    </g:each>
                    
                    </tbody>
                </table>
            </div>
    </g:if>
    <g:else>
    <g:message code="default.NotReviewed.label"/>
    </g:else>
       </div>
       </div>
       </div>
    </body>
</html>
