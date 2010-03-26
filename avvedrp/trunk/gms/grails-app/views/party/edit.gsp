

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Institution</title>
    </head>
    
    <script>
    function validateParty(){
    	if(document.getElementById("nameOfTheInstitution").value == ""){
    		alert("Please Enter Name");
		    document.getElementById("nameOfTheInstitution").focus();
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
            <span class="menuButton"><g:link class="list" action="list">Institution List</g:link></span>
                
        </div>
        <div class="body">
            <h1>Edit Institution</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyInstance?.id}" />
                <div class="dialog">
                   <table >
                        <tbody>
                        
                        
                        <tr class="prop">
                        
                            </tr> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfTheInstitution">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'nameOfTheInstitution','errors')}">
                                    <input type="text" size="45" id="nameOfTheInstitution" name="nameOfTheInstitution" value="${fieldValue(bean:partyInstance,field:'nameOfTheInstitution')}"/>
                                </td>
                           </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:partyInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        
                          
                          
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'address','errors')}">
                                   <g:textArea name="address" value="${fieldValue(bean:partyInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone">Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:partyInstance,field:'phone')}"/>
                                </td>
                           </tr> 
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:partyInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:partyInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validateParty()" /></span>
                     <g:if test="${session.Role == 'ROLE_ADMIN'}"> 
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                    	</g:if>
                </div>
            </g:form>
       
 </div> </div>
    </body>
</html>
