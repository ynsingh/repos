

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title>Edit Heads Allocation </title>
    </head>
    

    <script>
    function validate()
    {
     
     if(isNaN(document.getElementById("amount").value))
    {
    alert("Invalid Amount  ");
    document.getElementById("amount").focus
    return false;
    }
     if((document.getElementById("amount").value)=='')
    {
    alert("Please enter Proper Amount  ");
    return false;
    }
    
     if(eval(document.getElementById("amount").value)<=0)
    {
    alert("Please enter Proper Amount  ");
    return false;
    }
   
    
    
    }
     </script>
   <style>
    .tablewrapperpopup
{ 
padding: 0px;
text-align:left;
width: 500px; 
}
</style>
    <body>
        <div class="tablewrapperpopup"> 
    
        <div class="tablewrapperpopup">
            <h1>Edit Grant Heads</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationSplitInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationSplitInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantAllocationSplitInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                          
                            
                            
                             <tr>
                                <td valign="top" class="name">
                                    <label for="grantPeriod">Grant Period:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'grantPeriod','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.list()}" name="grantPeriod.id" value="${grantAllocationSplitInstance?.grantPeriod?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            <tr >
                                <td valign="top" class="name">
                                    <label for="accountHead">Account Head:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'accountHead','errors')}">
                                    <g:select optionKey="id" from="${AccountHeads.list()}" optionValue="code" name="accountHead.id" value="${grantAllocationSplitInstance?.accountHead?.id}" ></g:select>
                                </td>
                            
                            
                                <td valign="top" class="name">
                                    <label for="amount">Amount(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:grantAllocationSplitInstance,field:'amount')}" style="text-align: right" />
                                </td>
                            </tr> 
                        
                            
                        
                            
                        
                            
                        
                           
                            <tr >
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'description','errors')}">
                                 <g:textArea name="description" value="${fieldValue(bean:grantAllocationSplitInstance,field:'description')}" rows="3" cols="30"/>
                                  
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validate()"  /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
               </div>
    </body>
</html>
