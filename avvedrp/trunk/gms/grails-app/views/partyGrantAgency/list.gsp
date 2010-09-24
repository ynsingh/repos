

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Grant Agency List</title>
    </head>
    <body>
    <div class="wrapper">
    
        <div class="body">
            <h1>Grant Agency List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${partyInstanceList}">
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                   	        <g:sortableColumn property="nameOfTheInstitution" title="Name" />
                            <g:sortableColumn property="code" title="Code" />
                            <g:sortableColumn property="address" title="Address" />  
                            <g:sortableColumn property="phone" title="Phone" />
                            <g:sortableColumn property="email" title="Email" />
                            <g:sortableColumn property="activeYesNo" title="Active" />
                             <th>Edit</th>             	      
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyInstanceList}" status="i" var="partyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                          <td>${fieldValue(bean:partyInstance, field:'nameOfTheInstitution')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'code')}</td>
                            
                           
                            <td>${fieldValue(bean:partyInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'phone')}</td>
                            
                            <td>${fieldValue(bean:partyInstance, field:'email')}</td>
                        	
                        	<td><g:if test="${fieldValue(bean:partyInstance, field:'activeYesNo') == 'Y'}">
	    							 ${'YES'}
	    							 </g:if>
	    							 <g:else>
	    							 ${'NO'}
	    							 </g:else>
	                         </td>
                           <td><g:link action="edit" id="${fieldValue(bean:partyInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
             </g:if>
            <g:else>
            <br>
            No Records Available</br>
            </g:else>
           </div> 
        </div>
    </body>
</html>
