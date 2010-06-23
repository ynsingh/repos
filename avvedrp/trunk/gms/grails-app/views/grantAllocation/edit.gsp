

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Allocation </title>
    </head>

       <script>
    function validate()
    {     
	    if(isNaN(document.getElementById("amountAllocated").value))
	    {
	    alert("Please enter a valid Amount");
	    document.getElementById("amountAllocated").focus
	    return false;
	    }
	     if((document.getElementById("amountAllocated").value)=='')
	    {
	    alert("Please enter Amount");
	    return false;
	    }
	    
	    if(eval(document.getElementById("amountAllocated").value)<0)
	    {
	    alert("Please enter a valid Amount");
	    return false;
	    }    
    
    }
     </script>

    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="fundAllot" action="fundAllot">Fund Allocation List</g:link></span>
            
        </div>
        <div class="body">
            <h1>Edit GrantAllocation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                <div class="dialog">
                      <table>
                        <tbody>
                        
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td>
                                <strong>  ${fieldValue(bean:grantAllocationInstance,field:'projects.code')} </strong>
                                <input type="hidden" id="grantor" name="projects.id" value=" ${fieldValue(bean:grantAllocationInstance,field:'projects.id')}"/>
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

                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validate()" /></span>

                    <!-- <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span> -->
                </div>
            </g:form>
        </div>
          </div>
    </body>
</html>
