

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>AttachmentType List</title>
    </head>
    <body>
      <div class="wrapper">
      
        <div class="body">
            <h1>AttachmentType List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	         <th>Document Type</th>
                             
                   	        <g:sortableColumn property="type" title="Type" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	         <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attachmentTypeInstanceList}" status="i" var="attachmentTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'documentType')}</td>
                        
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'type')}</td>
                        
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'description')}</td>
                        
                            <td><g:link action="edit" id="${attachmentTypeInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${AttachmentType.count()}" />
            </div>
        </div>
    </body>
</html>
