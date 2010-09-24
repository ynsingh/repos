

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Investigator List</title>
    </head>
    <body>
     <div class="wrapper">
        <div class="body">
            <h1>Investigator List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${investigatorInstanceList}">
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="designation" title="Designation" />
                   	        
                   	        <th>Department</th>
                   	    
                   	        <g:sortableColumn property="party" title="Institution" />
                        
                   	        <g:sortableColumn property="address" title="Address" />
                                         
                   	        <g:sortableColumn property="email" title="Email" />
                   	       
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                   	       
                   	        <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${investigatorInstanceList}" status="i" var="investigatorInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'designation')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'department.departmentCode')}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'party.code')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'email')}</td>
                            <td><g:if test="${fieldValue(bean:investigatorInstance, field:'activeYesNo') == 'Y'}">
    							 ${'YES'}
    							 </g:if>
    							 <g:else>
    							 ${'NO'}
    							 </g:else>
                           </td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:investigatorInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>No Records Available</br>
            </g:else>
            
        </div>
        </div>
    </body>
</html>
