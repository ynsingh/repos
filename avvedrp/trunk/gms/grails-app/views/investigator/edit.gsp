

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Investigator.EditInvestigator.head" /></title>         
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
    <body>
      <div class="wrapper"> 
        <div class="body">
            <h1><g:message code="default.Investigator.EditInvestigator.head" /></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            
            <g:form method="post" >
                <input type="hidden" id="id" name="id" value="${investigatorInstance?.id}" />
                <div class="dialog">
                    <table>
                       <tbody>
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                    <label for="name" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'name','errors')}">
                                	<input type="text" id="name" name="name" value="${fieldValue(bean:investigatorInstance,field:'name')}"/>                        
                                </td>
                         </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="designation"><g:message code="default.Designation.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'designation','errors')}">
                                    <input type="text" id="designation" name="designation" value="${fieldValue(bean:investigatorInstance,field:'designation')}"/>
                                </td>
                         </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party"><g:message code="default.Institution.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'party','errors')}">
                                    <strong>  ${fieldValue(bean:partyinstance,field:'code')} </strong>
                                     <input type="hidden" id="institution" name="institution" value="${fieldValue(bean:partyinstance,field:'id')}"/>
                                </td>
                         </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="department"><g:message code="default.Department.label"/>:</label>
                                    <label for="department" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'department','errors')}">
                                   <div id="department">
                                      <g:select optionKey="id" optionValue="departmentCode" from="${departmentList}" name="department.id" value="${investigatorInstance?.department?.id}"></g:select>
                               	   </div>
                                </td>
                         </tr> 
                  	     <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.Address.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'address','errors')}">
                                    <input type="text" id="address" name="address" value="${fieldValue(bean:investigatorInstance,field:'address')}"/>
                                </td>
                         </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="default.Email.label"/>:</label>
                                    <label for="email" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:investigatorInstance,field:'email')}"/>
                                </td>
                         </tr> 
                       </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validatePI()"/></span>
					<span class="button">
					
					</span>	
                </div>
            </g:form>
        </div>
      </div>
    </body>
</html>
