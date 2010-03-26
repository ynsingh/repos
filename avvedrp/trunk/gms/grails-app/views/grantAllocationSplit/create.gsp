

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title>Head Allocation</title>         
    </head>
    <style>
    .tablewrapperpopup
{ 
padding: 0px;
text-align:left;
width: 500px; 
}
</style>
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
    
    
      function refreshParent() {
  window.opener.location.href = window.opener.location.href;

  if (window.opener.progressWindow)
		
 {
    window.opener.progressWindow.close()
  }
  top.close();
}
     </script>
     
     
     

    <body>
    <div class="tablewrapperpopup"> 
        
            <div class="proptable"> 
        
      
                 <table >
                   <tr>
                    <td valign="top">
                       <label for="project">Projects:</label>
                    </td>
                     <td valign="top" >
                   <strong>  ${fieldValue(bean:grantAllocationSplitInstance.projects,field:'code')} </strong>
                                    
                    </td>
                                  
                     <td valign="top" >
                      <label for="party"> Institution :</label>
                      </td>
                            
                              <td valign="top" >
                                    <label for="party"> Allocated Amount (Rs):</label>
                                </td>
                                <td valign="top" >
                                    <strong><g:formatNumber number="${grantAllocationSplitInstance.projects.totAllAmount}" format="###,##0.00" /> </strong>
                                   </td>
                            </tr> 
                            
                            </table>
                   </div>
        <table  class="tablewrapperpopup" border="0" cellspacing="0" cellpadding="0" >
    <tr>
    <td scope="col"><div >
              <h1 >Add More Heads</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationSplitInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationSplitInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" onsubmit="refreshParent()" >
                <div class="dialog">
                    <table >
                        <tbody>
                          <tr>
                                <td valign="top" class="name">
                                    <label for="grantPeriod">Grant Period:</label>
                                    <input type="hidden" id="projectId" name="projectId" value="${fieldValue(bean:grantAllocationSplitInstance.projects, field:'id')}"/>
                                     <input type="hidden" id="grantAllotId" name="grantAllotId" value="${fieldValue(bean:grantAllocationSplitInstance.grantAllocation, field:'id')}"/>
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
                            
                             </tr> 
                            
                            <tr >
                                <td valign="top" class="name">
                                    <label for="amount">Amount(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:grantAllocationSplitInstance,field:'amount')}" style="text-align: right"/>
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
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validate()" /></span>
                                     </div>
            </g:form>
        </div></td>
        </tr>
        <tr>
     <td scope="row">
    
    </body>
</html>
