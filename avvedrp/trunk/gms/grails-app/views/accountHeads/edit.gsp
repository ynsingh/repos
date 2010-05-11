

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit AccountHeads</title>
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
    
    <body>
      <div class="wrapper">
        <div class="nav">
              <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
              <g:if test="${accountHeadsInstance.parent !=null}">
               <span class="menuButton"><g:link class="showSubAccountHeads" action="showSubAccountHeads" id="${accountHeadsInstance.parent.id}">Sub AccountHeads List</g:link></span>
               </g:if>
            <span class="menuButton"><g:link class="list" action="list">AccountHeads List</g:link></span>
              
        </div>
        <div class="body">
            <h1>Edit AccountHeads</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${accountHeadsInstance}">
            <div class="errors">
                <g:renderErrors bean="${accountHeadsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${accountHeadsInstance?.id}" />
                <div class="dialog">
                   <table >
                        <tbody>
                        
                            <tr class="prop">
                             <g:if test="${accountHeadsInstance.parent !=null}">
                                <td valign="top" class="name">
                                    <label for="parent">Main Account:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${AccountHeads.findAllByActiveYesNoAndParentIsNull('Y')}" name="parent.id" value="${accountHeadsInstance?.parent?.id}" noSelection="['null':'-Select-']"></g:select>
                                </td>
                                </g:if>
                            </tr> 
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:accountHeadsInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:accountHeadsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                            
                         
                        
                           
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:accountHeadsInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                           
                        
                           
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validateAccountHead()" /></span>
                </div>
            </g:form>
            </div>
        </div>
    </body>
</html>
