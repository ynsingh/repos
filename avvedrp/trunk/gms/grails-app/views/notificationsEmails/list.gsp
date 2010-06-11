

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>NotificationsEmails</title>
        <ckeditor:resources />
    </head>
    <script>
    function checkAll()
    {
    	if(document.demo.All.checked==true)
    	{
    		for(var i=0;i<document.demo.choices.length;i++)
    		{
    			document.demo.choices[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.demo.choices.length;i++)
    		{
    			document.demo.choices[i].checked=false;
    		}
    	}
    }
    </script>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
           
        </div>
       
        <div class="body">
            <h1>Notification Emails</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form name="demo" action="send" method="post">
            <input type="hidden" name="notificationId" value="${params.id}" />
            <div class="list">
            <table>
            <thead>
           <tbody>
            <tr>
            
            <td>
            Message:
           <fckeditor:editor name="remarks" width="100%" height="400" toolbar="Standard" fileBrowser="default">
									
			</fckeditor:editor>

            <tr>
            </tbody>
            </thead>
                </table>
                <table>
                    <thead>
                        <tr>
                        
                   	       <%-- <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="notificationId" title="Notification Id" /> --%>
                        	<th><g:checkBox name="All" onclick="checkAll()" value="${All}" checked="false"/></th>
                   	        <th>Party Name</th>
                   	        <th>Email Address</th>
                   	        <th>Status</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                                      
                    <g:each in="${partyInstanceList}" status="i" var="partyInstanceList">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:checkBox name="choices" value="${partyInstanceList.id}" disabled="${partyInstanceList.statusEmail}" checked="false"/></td> 
                        <td>${fieldValue(bean:partyInstanceList, field:'code')}</td>
                        <td>${fieldValue(bean:partyInstanceList, field:'email')}</td>
                        <td><g:if test="${partyInstanceList.statusEmail==true}">
                         Send
                        </g:if></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
               <div class="buttons">
                    <input class="send" type="submit" value="Send" />
                </div>
                 </g:form>
           <%-- <div class="paginateButtons">
                <g:paginate total="${NotificationsEmails.count()}" />
            </div>--%>
        </div>
        </div>
        </div>
    </body>
</html>
