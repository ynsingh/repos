

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Investigator</title>
    </head>
     <!--  Import 'prototype' -->
    <g:javascript library="prototype"/>
    <!-- Hook up the ajax code - 
    	 first of all we attach a listener to the 'institution' element to detect for any 'change' 
    	 events fired when a User makes a selection on this element. Secondly we implement the ajax
    	 function that will be triggered when such an event is detected.In this instance we're using 
    	 prototype's Ajax.Updater() function, which takes a number of arguments.
    	 The first argument is the id of the element that will be updated by the response to 
    	 the ajax function. In our case this will be a second drop down list. The second argument
    	 is a url pointing to a grails action to service our ajax request. Finally we have a set 
    	 of optional parameters, the important one being 'selectedValue' which will hold the 
    	 selection made by the User on the first drop down list.
    -->
    <g:javascript>
		document.observe('dom:loaded', function() {
		   $("institution").observe("change", respondToSelect);
		   });
		
		 
		 function respondToSelect(event)
		 {
		       var baseUrl = "${createLink(controller:'investigator', action:'updateSelect')}"
		       new Ajax.Updater("department",
		          baseUrl,
		          {method:'get', parameters: {selectedValue : $F("institution"),id : $F("id")} }
		         );					        
		 }
	</g:javascript>
	<script>
		function validate()
		{
			if(document.getElementById('name').value == '' || document.getElementById('name').value == null)
			{
				alert("Please Enter the Investigator Name");
				document.getElementById('name').focus;
				return false;
			}
			if(document.getElementById('institution').value == '' || document.getElementById('institution').value == null)
			{
				alert("Please select the Institution");
				document.getElementById('institution').focus;
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
            <h1>Edit Investigator</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${investigatorInstance}">
            <div class="errors">
                <g:renderErrors bean="${investigatorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" id="id" name="id" value="${investigatorInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:investigatorInstance,field:'name')}" disabled="true"/>
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
                                    <g:select optionKey="id" id="institution" optionValue="code" from="${Party.list()}" name="party.id" value="${investigatorInstance?.party?.id}"></g:select>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="department">Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'department','errors')}">
                                   <div id="department">
                                    <g:select optionKey="id" optionValue="departmentCode" from="${PartyDepartment.list()}" name="department.id" value="${investigatorInstance?.department?.id}"></g:select>
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
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:investigatorInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validate()"/></span>
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
