<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
    <head>
    
  
    </head>
    
  
    
    
    <body>
    






	<br>
	<br>
	
	
	<table width="75%">
	 <tr>
	<g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                       
       
        <%
    
   	def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.grantAllocation.id ="+ grantAllocationInstance.id) 
      def expAmt=0;
      if(sumAmount[0]==null)
            expAmt=0
            else
            expAmt=sumAmount[0]
            def fund=Math.round((grantAllocationInstance.amountAllocated-expAmt)*100/grantAllocationInstance.amountAllocated)
            
             def time=Math.round((grantAllocationInstance.projects.projectEndDate.getTime()-new Date().getTime())/(grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime())*100)
               
             
    %>      
    
      fund
                                                 
                            <td> 
                                                    
              
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="14%"><g:link action="projectDash" id="${grantAllocationInstance.id}"  >${grantAllocationInstance.projects.code}</g:link> </td>
    <td width="86%"> </td>
  </tr>
  <tr>
    <td>
    Fund remaining
	<dl>
		
		<dd>
			
			
		<span><em style="left:${100-fund}px">${100-fund}</em></span>
		</dd>

	</dl></td>
    <td>${100-fund}%</td>
  </tr>
  <tr>
    <td>
    
Time remaining 
    <dl>  
		
		<dd>
		
			<span><em style="left:${time}px">${time}</em></span>
		</dd>
		
      </dl> </td>
    <td>${time}%</td>
  </tr>
</table>
              
                      
		

				              
  </g:each>
                   
                    
                    
                                   </table>
		  
			
			
	   


    </body>
</html>