

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create SubAccountHeads</title>         
    </head>
    
    <script>
    function validateAccountHead(){
    	if(document.getElementById("name").value == ""){
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	if(document.getElementById("code").value == ""){
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    	return true;
    
    }
    </script>
    
    <body>     <div class="wrapper">
            <div class="nav">
             <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">AccountHeads List</g:link></span>
        </div>
        <table class="tablewrapper">
        
        <tr>
        <td>
        <div class="body">
            <h1>Create SubAccount Heads</h1>
            <g:hasErrors bean="${accountHeadsInstance}">
            <div class="errors">
                <g:renderErrors bean="${accountHeadsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table width="950" cellspacing="4" cellpading="2">
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parent">Main Account:</label>
                                </td>
                                
                                  <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                     ${fieldValue(bean:accountHeadsInstance,field:'parent.name')}
                                   
                                </td>
                                  <td valign="top" class="name">
                                    <label for="parent">Main AccountCode:</label>
                                </td>
                                
                                  <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                     ${fieldValue(bean:accountHeadsInstance,field:'parent.code')}
                                        <g:hiddenField id="parentid" name="parent.id" value="${fieldValue(bean:accountHeadsInstance, field:'parent.id')}"/>
                        
                                </td>
                                <td>
                                </td>
                                <td>
                                </td>
                              
                            </tr> 
                               <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:accountHeadsInstance,field:'name')}"/>
                                </td>
                             <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:accountHeadsInstance,field:'code')}"/>
                                </td>
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:accountHeadsInstance,field:'activeYesNo')}" />
                                </td>
                                <td>
                                </td>
                                <td>
                                <td>
                                </td>
                            </tr> 
                         </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validateAccountHead()" /></span>
                </div>
            </g:form>
        </div>
        </td>
        </tr>
        <tr>
        <td>
                 <div class="body">
            <h1>Sub Account Heads List of   ${fieldValue(bean:accountHeadsInstance,field:'parent.code')}</h1>
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
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                   	        <th>Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accountHeadsInstanceList}" status="i" var="accountHeadsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${i+1}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'name')}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'code')}</td>
                           <td>
                	             <g:if test="${fieldValue(bean:accountHeadsInstance, field:'activeYesNo') == 'Y'}">
    							 ${'YES'}
    							 </g:if>
    							 <g:else>
    							 ${'NO'}
    							 </g:else>
                        	 </td>
                           <td><g:link action="edit" id="${accountHeadsInstance.id}">Edit</g:link></td>
                          </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </td>
        </tr>
        </table>
       </div>
     
    </body>
</html>
