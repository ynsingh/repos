

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Sub Projects</title>         
    </head>
    
    <script>
    function validateProject(){
    		
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
    	//Sub Project Start Date
    	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
    	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
    	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
    	
    	//Sub Project End Date
    	
    	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
    	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
    	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	
		//Parent Project Start Date
		var parentStartDate = document.getElementById("parentProjectStartDate").value;
    	  		
    	var parentStartDateYear=parentStartDate.substring(0,parentStartDate.indexOf("-"));
    	var parentStartDateMonth=parentStartDate.substring(parentStartDate.indexOf("-")+1,parentStartDate.lastIndexOf("-"));
    	var parentStartDateDate=parentStartDate.substring(parentStartDate.lastIndexOf("-")+1,parentStartDate.lastIndexOf(" "));
    		
    	//Parent Project End Date
    		
    	var parentEndDate = document.getElementById("parentProjectEndDate").value;
       		var parentEndDateYear=parentEndDate.substring(0,parentEndDate.indexOf("-"));
    		var parentEndDateMonth=parentEndDate.substring(parentEndDate.indexOf("-")+1,parentEndDate.lastIndexOf("-"));
    		var parentEndDateDate=parentEndDate.substring(parentEndDate.lastIndexOf("-")+1,parentEndDate.lastIndexOf(" "));
    		
    		var newParentProjectStartDate = new Date(parentStartDateYear,parentStartDateMonth-1,parentStartDateDate);
    		var newParentProjectEndDate = new Date(parentEndDateYear,parentEndDateMonth-1,parentEndDateDate);
    		
    		
    		
    		
    		if (newProjectStartDate>=newProjectEndDate)
    		{
    			alert("Sub project End Date should be greater or equal to sub project Start Date")
    			return false;
    		}
    		
    		if ((newProjectStartDate<newParentProjectStartDate)||(newProjectEndDate>newParentProjectEndDate))
    	 	{
    		alert("Sub project Date period should be between the Main project date");
    		return false;
    		}
    		
       	return true;
    
    }
    </script>
    
    <body>
    <div class="wrapper"> 
    
         <div class="nav">
             <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Projects List</g:link></span>
        </div>
        <g:hiddenField name="parentProjectStartDate" value="${fieldValue(bean:projectsInstance, field:'parent.projectStartDate')}"/>
        <g:hiddenField name="parentProjectEndDate" value="${fieldValue(bean:projectsInstance, field:'parent.projectEndDate')}"/>
        
       <table class="tablewrapper"> 
       <tr>
       <td>   
     
        
        <div class="body">
            <h1>Create Sub Projects</h1>
            
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="List" />
            </div>
            </g:hasErrors>
            <g:form action="saveSub" method="post" >
                <div class="dialog">
                    <table  cellpading="0" cellspacing="0">
                     <tbody>
                    
                       <tr class="prop">
                         <td valign="top" class="name">
                         <label for="parent">Main Project:</label>
                         </td>
                           
                   <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
                    <strong>    ${fieldValue(bean:projectsInstance,field:'parent.name')} </strong>
                           
                   </td>
                    <td  valign="top" class="name">
                         <label for="parent">Main Project Code:</label>
                  
                         
                           <td align="left" valign="top" class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
                                    <strong> ${fieldValue(bean:projectsInstance,field:'parent.code')}</strong>
                                     <g:hiddenField id="parentid" name="parent.id" value="${fieldValue(bean:projectsInstance, field:'parent.id')}"/>
                   </td>
                   
                  
                   </tr> 
                                                 
                           <tr >
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="35" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                </td>
                           
                                <td  valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
		                   <tr class="prop">
		                   <td colspan="3">
		                   <div align="left"><label for="dateRangeFrom">Main Project Start Date: </label>
		                    <strong><g:formatDate date="${projectsInstance.parent.projectStartDate}" format="dd/MM/yyyy"/> </strong>
		                    <label for="dateRangeTo">End Date: </label>              
		                    <strong> <g:formatDate date="${projectsInstance.parent.projectEndDate}" format="dd/MM/yyyy"/> </strong> 
		                    </td>
		                    </tr> 
                            <tr class="prop">
                            <td valign="top" class="name">
                            <label for="projectStartDate">Start Date:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                            <calendar:datePicker name="projectStartDate" value="${projectsInstance?.projectStartDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
                            </td>
                            <td valign="top" class="name">
                            <label for="projectEndDate">End Date:</label>
                            </td>
                            <td colspan="3"  valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                            <calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
                            </td>
                            <td valign="top" class="name">
                            <div align="top">
                            <label for="activeYesNo">Active:</label>
                            </div>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
                            <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:projectsInstance,field:'activeYesNo')}" />
                           <td>
                           </td>
                   </td>
                 </tr> 
              </tbody>
   </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validateProject()" /></span>
                </div>
               
            </g:form>
        
         </div>
         </td>
         </tr>
        
        
        
       <tr>
    <td>   
                
            
             <div class="body">
            <h1>Sub Projects List of ${fieldValue(bean:projectsInstance,field:'parent.name')}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${projectsInstanceList}">
            <div class="list">
            <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
             	       <g:sortableColumn property="id" title="SlNo" />
                       <g:sortableColumn property="name" title="Name" />
                       <g:sortableColumn property="code" title="Code" />               
                   	   <g:sortableColumn property="activeYesNo" title="Active" />
                   	   <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsInstanceList}" status="i" var="projectsInstanceList">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${i+1}</td>
                         <td>${fieldValue(bean:projectsInstanceList, field:'name')}</td>
                         <td>${fieldValue(bean:projectsInstanceList, field:'code')}</td>
                         <td>
                	             <g:if test="${fieldValue(bean:projectsInstanceList, field:'activeYesNo') == 'Y'}">
    							 ${'YES'}
    							 </g:if>
    							 <g:else>
    							 ${'NO'}
    							 </g:else>
                         </td>
                         <td><g:link  action="edit" id="${projectsInstanceList.id}" params="[flag:'Y']">Edit</g:link></td>
                       </tr>
                    </g:each>
                    </tbody>
                    
                    
                </table>
            </div>
            </g:if>
            <g:else>
            <br>No Records Available</br>
            </g:else>
         
      
         </div>
        
        
        </td>
        </tr>
        
         </table>
        </div>
        
    </body>
</html>
