

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Sub Project Allotment </title>
    </head>
    
    <script>
    function validate()
    {     
	    if(isNaN(document.getElementById("amountAllocated").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amountAllocated").select();
		    return false;
	    }
	    if((document.getElementById("amountAllocated").value)=='')
	    {
		    alert("Please enter the Amount Allocated ");
		    document.getElementById("amountAllocated").select();
		    return false;
	    }
	    if(eval(document.getElementById("amountAllocated").value)<=0)
	    {
		    alert("Please enter the Amount Allocated ");
		    document.getElementById("amountAllocated").select();
		    return false;
	    }    
    
    }
     </script>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            
             <span class="menuButton"><a class="list"  href="../projectDash/${projectInstance.id}">Project List</a></span>
        </div>
        <div class="body">
            <h1>Edit Sub Project Allocation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" action="updateProAllot" >
                <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                <div class="dialog">
                      <table>
                        <tbody>
                        
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Projects.list()}" name="projects.id" value="${grantAllocationInstance?.projects?.id}" disabled="true"></g:select>
                                </td>
                            </tr> 
                        
                        
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Recipient:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Party.list()}" name="party.id" value="${grantAllocationInstance?.party?.id}" disabled="true"></g:select>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Sanction Order No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'sanctionOrderNo','errors')}">
                                    <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" value="${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')}" style="text-align: right"/>
                                </td>
                            </tr> 
                            
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfAllocation">Date Of Allocation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">
                                    <calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amountAllocated">Amount Allocated(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" style="text-align: right" />
                                   <input type="hidden" id="totAllAmount" name="totAllAmount" value="${projectInstance.totAllAmount}"/>
                                   <input type="hidden" id="amount" name="amount" value="${grantAllocationInstance.totAllAmount}"/>
                                </td>
                            </tr> 
                        
                      
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Update" onclick="return validate()" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
                </div>
        
    </body>
</html>
