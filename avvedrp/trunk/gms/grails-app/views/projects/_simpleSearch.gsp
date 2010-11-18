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
             
                                                                   
        </tbody>
            </table>
                    		<p>&nbsp;</p>
			      			<p ALIGN=CENTER>&nbsp;<input class="searchButton" value="                              " type="submit" onClick="" /></p>
			    			
			    			<p ALIGN=RIGHT><g:remoteLink style="font-size:11px;font-weight: normal;color: #0033CC" action="advancedSearchProjects" id="Advance" update="search">Advanced Search</g:remoteLink>&nbsp;&nbsp;</p>