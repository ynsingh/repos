

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Self Fund</title>         
    </head>
    
     <script>
    function validate()
    {     
     if(isNaN(document.getElementById("amountAllocated").value))
    {
    alert("Invalid Amount  ");
    document.getElementById("amountAllocated").focus
    return false;
    }
     if((document.getElementById("amountAllocated").value)=='')
    {
    alert("Please enter Proper Amount  ");
    return false;
    }
    
     if(eval(document.getElementById("amountAllocated").value)<=0)
    {
    alert("Please enter Proper Amount  ");
    return false;
    } 
     if( ( (document.getElementById("projects.id").value) == 'null') || ( (document.getElementById("projects.id").value) == '') )
	    {
		    alert("Please enter Project");
		    document.getElementById("projects.id").focus();
		    return false;
	    }
    return true;
    
    }
     </script>
    <body>
        <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
     
        </div>
        
        
        <table class="tablewrapper">
        <tr>
        <td>
        
        <div class="body">
            <h1>Self Fund</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form action="funtSave" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                         <tr >
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${projectsList}" name="projects.id" value="${grantAllocationInstance?.projects?.id}" noSelection="['null':'Select']"></g:select>
                                </td>
                            
                                <td align="left" class="name">
                                    <label for="party">Grant Agency:</label>
                                </td>
                                <td>
                                     <strong>  ${fieldValue(bean:partyinstance,field:'code')} </strong>
                                       <input type="hidden" id="grantor" name="grantor" value="${fieldValue(bean:partyinstance, field:'id')}"/>
                                </td>
                            </tr> 
                        
                           
                           <tr>
                                <!--<td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:grantAllocationInstance,field:'code')}"/>
                                </td>-->
                            
                                <td valign="top" class="name">
                                    <label for="amountAllocated">Amount Allocated(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" style="text-align: right" />
                                </td>
                            </tr> 
                        
                      
                            <tr>
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="1" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                 <input  class="inputbutton" type="submit" name="funtSave" value="Save" onClick="return validate()" >
            </form>
               </div>
               
               </td>
               </tr>
              
               <tr>
               <td>
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                                                                                 
                   	        <g:sortableColumn property="projects.code" title="Project Code " />
                   	        <g:sortableColumn property="projects.principalInvestigatorName" title="PI Name " />
                            <g:sortableColumn property="projects.projectStartDate" title="Project Start Date " />
                            <g:sortableColumn property="projects.projectEndDate" title="Project End date " />
                            
                   	        <g:sortableColumn property="party" title="Grant Agency" />
                   	        
                   	        <g:sortableColumn property="amountAllocated" title="AmountAllocated" />
                           <g:sortableColumn property="Remarks" title="Remarks" />     
                   	       
                        
                   	       <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                             <td>${fieldValue(bean:grantAllocationInstance, field:'projects.principalInvestigatorName')}</td>
                               <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectStartDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectEndDate}"/></td>
                            <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                            
                         
    	                     <td><g:formatNumber number="${grantAllocationInstance.amountAllocated}" format="###,##0.00" /></td>
                        <td>${fieldValue(bean:grantAllocationInstance, field:'remarks')}</td>
                            
                        <td><g:link action="edit" id="${grantAllocationInstance.id}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           </td>
           </tr>
           
             </div>
      </table>
    </body>
</html>
