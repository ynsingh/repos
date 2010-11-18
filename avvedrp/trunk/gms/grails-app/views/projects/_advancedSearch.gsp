<link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'jquery-ui.css')}" type="text/css" /> 
  <script src="${createLinkTo(dir:'images',file:'jquery.min.js')}"></script>
  <script src="${createLinkTo(dir:'images',file:'jquery-ui.min.js')}"></script>
       <script>
  $(function() {
  		var currentTime = new Date()
		var month = currentTime.getMonth() + 1
		var day = currentTime.getDate()
		var year = currentTime.getFullYear()
		var today = day + "/" + month + "/" + year
		var curr = new Date(today);
    $('.date-pick').datepicker({ dateFormat: 'dd/mm/yy' ,
			showOn: "button",
			buttonImage: "../images/themesky/calendar.gif",
			buttonImageOnly: true
		});
  });
  </script>
<p>&nbsp;</p>
  <table>
        <tbody>
        
        <tr class="prop">
        	<td valign="top">
                   &nbsp;
                </td>
                <td valign="top" class="name">
                    <label for="name"><g:message code="default.Name.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                </td>
           
                <td valign="top" class="name">
                    <label for="code"><g:message code="default.Code.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                    <input type="text"  id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                </td>
                
            </tr> 
        	
        	<tr class="prop">
        	<td valign="top">
                   &nbsp;
                </td>
                <td valign="top" class="name">
                    <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}">
                    <g:select optionKey="id" optionValue="type" id="projectType" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'select']" ></g:select>
                </td>
           
                <td valign="top" class="name">
                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'investigator','errors')}">
                    <g:select optionKey="id" optionValue="name" from="${Investigator.findAll('from Investigator I where I.activeYesNo=\'Y\' ')}" name="investigator.id" value="${projectsInstance?.investigator?.id}" noSelection="['null':'select']"></g:select>
                </td>
         </tr>  
         <tr class="prop">
         <td valign="top">
                   &nbsp;
                </td>
         		<td valign="top" class="name">
                    <label for="projectStartDate"><g:message code="default.StartDate.label"/> <g:message code="default.from.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                	<input id="datepicker1" size="10" class="date-pick" name="projectStartDate" type="text" value="${projectsInstance?.projectStartDate}" onBlur="return checkDate(this);" /> 
                	<label for="projectStartDate"><g:message code="default.to.label"/>:</label><input id="datepickerTo" size="10" class="date-pick" name="projectStartDateTo" type="text" value="${projectsInstance?.projectStartDate}" onBlur="return checkDate(this);" /> 
                </td> 
               
         </tr>
         <tr class="prop">
         <td valign="top">
                   &nbsp;
                </td>
                <td valign="top" class="name">
                    <label for="projectEndDate"><g:message code="default.EndDate.label"/> <g:message code="default.from.label"/>:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                    <input id="datepicker2" size="10" class="date-pick" name="projectEndDate" type="text" value="${projectsInstance?.projectEndDate}" onBlur="return checkDate(this);" /> 
                	<label for="projectEndDate"><g:message code="default.to.label"/>:</label><input id="datepickerEndTo" size="10" class="date-pick" name="projectEndDateTo" type="text" value="${projectsInstance?.projectEndDate}" onBlur="return checkDate(this);" /> 
                </td>
                
            </tr>                                                           
        </tbody>
                    </table>
                    		<p>&nbsp;</p>
			      			<p ALIGN=CENTER>&nbsp;<input class="searchButton" value="                              " type="submit" onClick="" /></p>
			    			
			    			<p ALIGN=RIGHT><g:remoteLink style="font-size:11px;font-weight: normal;color: #0033CC" action="advancedSearchProjects" id="1" update="search">Simple Search</g:remoteLink>&nbsp;&nbsp;</p>