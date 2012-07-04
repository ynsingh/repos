<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" 
			value="${message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<script type="text/javascript">
		</script>
    </head>
    <body>
    	<div class="wrapper"> 
    	<g:subMenuList/>   
		<div class="body">
      	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
			<h1><g:message code="default.ProjectEmployeeQualification.create.head" /></h1>
	        <g:if test="${flash.message}">
	        	<div class="message">${flash.message}</div>
	        </g:if>
	        <g:hasErrors bean="${projectEmployeeQualificationInstance}">
	        	<div class="errors">
	                <g:renderErrors bean="${projectEmployeeQualificationInstance}" as="list" />
	            </div>
	        </g:hasErrors>
	        <g:form action="save" method="post" >  
	        	<div class="dialog">   
			        <table>
			        	<tbody>
				  			<tr>
					    		<td width="15%" height="34" >
					    			<label><g:message code="default.EmployeeName.label" /></label>:</td>    		
					    		<td>${fieldValue(bean: projectEmployeeInstance,field: "empName")}
					    				/ ${fieldValue(bean: projectEmployeeInstance,field: "empNo")}</td>
					    			<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" 
					    				value="${projectEmployeeInstance.id}"/>
					    		<td width="15%"><label><g:message code="default.Designation.label" /></label>:</td>
								<td>${projectEmployeeInstance.employeeDesignation.Designation}</td>
				 		    </tr>
				  			<tr >
					  		    <td><label><g:message code="default.ProjectEmployeeQualification.ExamName.label" /></label>:
					  		    <label for="ExamName" style="color:red;font-weight:bold"> * </label>
					  		    </td>
					    		 <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance,
					    		 	 	field: 'examname', 'errors')}">
					                 <g:textField style='width: 200px; ' name="examname" 
					                  	value="${projectEmployeeQualificationInstance?.examname}" />
					              </td>
					    		<td><label><g:message code="default.ProjectEmployeeQualification.University.label" /></label>:
					    		<label for="University" style="color:red;font-weight:bold"> * </label>
					    		</td>
					    		<td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, 
					    				field: 'university', 'errors')}">
					                <g:textField style='width: 200px; ' name="university" 
					                	value="${projectEmployeeQualificationInstance?.university}" />
					            </td>
				  			</tr>
				  			<tr >
					    		<td ><label><g:message code="default.ProjectEmployeeQualification.PassoutYear.label" /></label>:
					    		<label for="PassoutYear" style="color:red;font-weight:bold"> * </label>
					    		</td>
					    		<td><g:select name="passoutYear" 
						    			from="${['2010', '2009', '2008','2007','2006','2005','2004','2003','2002','2001','2000','1999','1998','1997','1996','1995','1994','1993','1992','1991','1990','1989','1988','1987','1986','1985','1984','1983','1982','1981','1980','1979','1978','1977','1976','1975','1974','1973','1972','1971','1970','1969','1968','1967','1966','1965','1964','1963','1962','1961','1960','1959','1958','1957','1956','1955','1954','1953','1952','1951','1950','1949','1948','1947','1946','1945','1944','1943','1942','1941','1940','1939','1938','1937','1936','1935','1934','1933','1932','1931','1930']}" 
						    			noSelection="['null':'-Select-']"/> 
						    	</td>
					   			<td ><label><g:message code="default.ProjectEmployeeQualification.PercentageofMark.label" />
					   				 </label>:
					   				 <label for="PercentageofMark" style="color:red;font-weight:bold"> * </label>
					   				 </td>
					      		<td ><g:textField name="percMark" 
					      			value="${fieldValue(bean: projectEmployeeQualificationInstance, 
					      			field: 'percMark')}" />
					            </td>
				  			</tr>
					   	</tbody>
				   	</table>
			   	</div>	
		   		<div class="buttons">
		   			<span class="button"><g:submitButton name="create" 
		   				onClick="return validateProjectEmployeeQualification()" 
		   				class="save" value="${message(code: 'default.Save.button')}" />
	   				</span>
	            </div>
			</g:form>
		</div>
		
	<div class="body">
    	<div class="list">
    		<g:if test="${projectEmployeeQualificationInstanceList}">
    			<table> 	
    				<thead>
  						<tr>
  							<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                       
                   	        <g:sortableColumn property="examname" 
                   	        	title="${message(code: 'default.ProjectEmployeeQualification.ExamName.label')}" />
                   	        
                   	        <g:sortableColumn property="university" 
                   	        	title="${message(code: 'default.ProjectEmployeeQualification.University.label')}" />
                                              
                   	        <g:sortableColumn property="percMark" 
                   	        	title="${message(code: 'default.ProjectEmployeeQualification.%ofMark.label')}" />
                	       
                   	       	<g:sortableColumn property="passoutYear" 
                   	       		title="${message(code: 'default.ProjectEmployeeQualification.PassoutYear.label')}" />
                   	       	
                   	       	<th><g:message code="default.Edit.label" /></th>
           	             </tr>
               		 </thead>
               	 	 <tbody>	
            	 
	     		 	 <g:each in="${projectEmployeeQualificationInstanceList}" status="i"  
	     		 			var="projectEmployeeQualificationInstance">
     		 			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>${i+1}</td>
	                        
	                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "examname")}</td>
	                        
	                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "university")}</td>
	                        
	                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "percMark")}</td>
	                            
	                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "passoutYear")}</td>
	                       
		         				<g:hiddenField name="id" value="${projectEmployeeQualificationInstance?.id}" />
		       						<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" 
		       							value="${projectEmployeeInstance.id}"/>
		         	         	
	         	         		<td><g:link  action="edit" params="['projectEmployee.id':projectEmployeeInstance.id]" 
		         	         			id="${fieldValue(bean:projectEmployeeQualificationInstance,field:'id')}">
		         	         			<g:message code="default.Edit.label" />
	         	         			</g:link>
	         	         		</td>
       	                	</tr>
                		</g:each>
        	 		</tbody>
    	 		</table>	
        		</g:if>
         		<g:else>
         			<br><g:message code="default.NoRecordsAvailable.label"/></br>
         		</g:else>
			</div>
		 </div>
	  </div>
   	</body>
</html>
