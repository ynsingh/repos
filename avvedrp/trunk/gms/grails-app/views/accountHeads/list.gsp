

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>AccountHeads List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
              <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
                
            <span class="menuButton"><g:link class="create" action="create">New AccountHeads</g:link></span>
            
        </div>
        <div class="body">
            <h1>AccountHeads List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                   	      <g:sortableColumn property="name" title="Name" />
                   	        <g:sortableColumn property="code" title="Code" />
                   	         <th>Sub Account Heads</th>
                   	         <th>Edit</th>
                           </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accountHeadsInstanceList}" status="i" var="accountHeadsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                           <td>${i+1}</td>
                           
                           <td>${fieldValue(bean:accountHeadsInstance, field:'name')}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'code')}</td>
                            <td><g:link  action="showSubAccountHeads"  id="${accountHeadsInstance.id}">Add Sub Account Heads</g:link></td>
                           <td><g:link action="edit" id="${accountHeadsInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
         </div>   
        </div>
    </body>
</html>
