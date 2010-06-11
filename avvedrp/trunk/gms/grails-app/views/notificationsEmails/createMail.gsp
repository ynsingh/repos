

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create NotificationsEmails</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">NotificationsEmails List</g:link></span>
        </div>
        <div class="body">
            <h1>Create NotificationsEmails--${params.choices.size()}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationsEmailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationsEmailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="send">
            <input type="hidden" name="choices" value="${params.choices}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                           <%-- Email Address<input name="email">--%>
                            Name<input name="userName">
                            <input type="submit">
                        
                        </tbody>
                    </table>
                </div>
                
            </g:form>
        </div>
    </body>
</html>
