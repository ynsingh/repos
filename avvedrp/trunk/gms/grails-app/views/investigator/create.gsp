

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Investigator</title>         
    </head>
    
	<script>
		function validate()
		{
			if(document.getElementById('name').value == '' || document.getElementById('name').value == null)
			{
				alert("Please Enter the Investigator Name");
				document.getElementById('name').focus;
				return false;
			}
						
			return true;
		}
	</script>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Investigator List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Investigator</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${investigatorInstance}">
            <div class="errors">
                <g:renderErrors bean="${investigatorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'name','errors')}">
                                	<input type="text" id="name" name="name" value="${fieldValue(bean:investigatorInstance,field:'name')}">                        
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="designation">Designation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'designation','errors')}">
                                    <input type="text" id="designation" name="designation" value="${fieldValue(bean:investigatorInstance,field:'designation')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'party','errors')}">
                    				<strong>  ${fieldValue(bean:partyinstance,field:'code')} </strong>
                                     <input type="hidden" id="institution" name="institution" value="${fieldValue(bean:partyinstance,field:'id')}"/>
            						</td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="department">Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'department','errors')}">
                                   <div id="department">
                                    <g:select optionKey="id" optionValue="departmentCode" from="${PartyDepartment.findAllByParty(Party.findById(session.Party))}" name="department.id" value="${investigatorInstance?.department?.id}"></g:select>
                               		</div>
                                </td>
                            </tr> 
                  			<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'address','errors')}">
                                    <input type="text" id="address" name="address" value="${fieldValue(bean:investigatorInstance,field:'address')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:investigatorInstance,field:'email')}"/>
                                </td>
                            </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:investigatorInstance,field:'activeYesNo')}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validate()"/></span>
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
