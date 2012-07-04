
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ModifyProposalFilter.head"/></title>     
	</head>
    <body>
  <div class="wrapper"> 
  <div class="body">
<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
                </g:if>
<h1><g:message code="default.ModifyProposalFilter.head"/></h1>
<g:form action="proposalFilterSave" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
					<label for="proposalFilter"><g:message code="default.proposalFilterForDays.label" />:</label>
	                                    <label for="proposalFilter" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top">
	                                    <g:textField id="proposalFilter" name="proposalFilter" 
	                                    	value="${days}" />
	                                </td>
	                        	</tr>
	                        </tbody>
	                    </table>
	            	 </div>
	        	
                 	<div class="buttons">
	                    <span class="button"><g:submitButton name="Save" class="save" 
	                    	value="${message(code: 'default.Save.button')}" onClick="return validateProposalfilter()" />
	                	</span>
                	</div>
                 </div>
             </g:form>
</div>
</div>

</body>
</html>
