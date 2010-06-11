

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show NotificationsEmails</title>
    </head>
    <body>
     <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link controller="notification" action="list">Notifications List</g:link></span>
            
        </div>
       <div class="tablewrapper">
        <div class="body">
           
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <table>
             <tbody>
             
                  <h1>  Send Details <h1>
                               
                    <g:each in="${notificationPartyList}" status="i" var="notificationPartyList">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <g:if test="${notificationPartyList}">
                        	<td>${notificationPartyList}</td>
                        	</g:if>
                            <g:else>
                           <td> Invalid Email Id</td>
                            </g:else>
                          
                        </tr>
                        
                    </g:each>
                    
                    </tbody>
            </table>
        </div>
        </div>
        </div>
    </body>
</html>
