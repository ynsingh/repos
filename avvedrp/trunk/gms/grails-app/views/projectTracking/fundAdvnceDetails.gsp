
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.FundAdvanceDetails.head"/></title>         
    </head>
    <body>
        <div class="wrapper"> 
            <div class="body">
             <h1><g:message code="default.FundAdvanceDetails.head"/></h1>
            
                   
                    <g:if test="${flash.message}">
                       <div class="message">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${projectTrackingInstance}">
                       <div class="errors">
                          <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                       </div>
                    </g:hasErrors>
                    <g:form action="save" method="post" >
                    <div class="dialog">
                     <g:if test="${advanceInstanceList}">
		               <table>
		                    <thead>
		                       <tr>
		                           <th><g:message code="default.SINo.label" /></th>
		                           
		                           <th><g:message code="default.advanceAmount.label"/></th>
		                           
		                           <th><g:message code="default.Status.label"/></th>
		                           
		                          </tr>
		                    </thead>
		                    <tbody>
		                      <% int j=0 %>
		                    <g:each in="${advanceInstanceList}" status="i" var="fundAdvanceInstance">
		                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                           <td>${i+1}</td>
		                           
		                           <td>${fundAdvanceInstance.advanceAmount}</td>
		                           
		                           <td>${fundAdvanceInstance.status}</td>
		                         
		                         </tr>
		                    </g:each>
		                    </tbody>
		               </table>
          			 </g:if>
		            <g:else>
		    	 	 <td><g:message code="default.NoDetails.label"/></td>
		      	    </g:else>
		      	    
                        <div class="buttons">
                             <span class="button"><g:actionSubmit class="cancel" action="cancel" value="${message(code: 'default.Cancel.button')}" /></span>
			 			 </div>
			 			 </div>
                    </g:form>
             
          </div>
        </div>
    </body>
</html>