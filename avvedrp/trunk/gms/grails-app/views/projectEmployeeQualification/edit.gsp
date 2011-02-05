<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Employee Qualification</title>
        <script type="text/javascript">
		</script>
    </head>
    <body>
    	<div class="wrapper">
    	<g:subMenuList/> 
	    	<div class="body">
	            <h1><g:message code="default.ProjectEmployeeQualification.edit.head" /></h1> 
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeQualificationInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${projectEmployeeQualificationInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${projectEmployeeQualificationInstance?.id}" />
	                <g:hiddenField name="version" value="${projectEmployeeQualificationInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="examname"><g:message code="default.ProjectEmployeeQualification.ExamName.label" /></label>:
	                                  <label for="examname" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'examname', 'errors')}">
	                                    <g:textField style='width: 200px; ' name="examname" value="${projectEmployeeQualificationInstance?.examname}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="passoutYear"><g:message code="default.ProjectEmployeeQualification.PassoutYear.label" /></label>:
	                                  <label for="PassoutYear" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                               <td><g:select name="passoutYear" 
						    			from="${['2010', '2009', '2008','2007','2006','2005','2004','2003','2002','2001','2000','1999','1998','1997','1996','1995','1994','1993','1992','1991','1990','1989','1988','1987','1986','1985','1984','1983','1982','1981','1980','1979','1978','1977','1976','1975','1974','1973','1972','1971','1970','1969','1968','1967','1966','1965','1964','1963','1962','1961','1960','1959','1958','1957','1956','1955','1954','1953','1952','1951','1950','1949','1948','1947','1946','1945','1944','1943','1942','1941','1940','1939','1938','1937','1936','1935','1934','1933','1932','1931','1930']}" 
						    			noSelection="['null':'select']" name="passoutYear" value="${fieldValue(bean: projectEmployeeQualificationInstance, field: 'passoutYear')}"></g:select>
						    	</td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="percMark"><g:message code="default.ProjectEmployeeQualification.PercentageofMark.label" /></label>:
	                                  <label for="percMark" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'percMark', 'errors')}">
	                                    <g:textField name="percMark" value="${fieldValue(bean: projectEmployeeQualificationInstance, field: 'percMark')}" />
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="university"><g:message code="default.ProjectEmployeeQualification.University.label" /></label>:
	                                  <label for="University" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'university', 'errors')}">
	                                    <g:textField style='width: 200px;' name="university" value="${projectEmployeeQualificationInstance?.university}" />
	                                </td>
	                            </tr>
	                    	</tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                	<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" value="${projectEmployeeInstance.id}"/>
	                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" 
	                    	onClick="return validateProjectEmployeeQualification()"/>
	                	</span>
						<span class="button">
							<g:actionSubmit class="delete"  action="delete" 
							value="${message(code: 'default.Delete.button')}" 
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</span>
	                </div>  
	            </g:form>
	        </div>
        </div>
    </body>
</html>
