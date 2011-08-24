<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.HeadAllocation.title" /></title> 
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'colorbox.css')}" type="text/css"  />
        <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>   
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
    if((parseFloat(document.getElementById("amount").value)) > parseFloat(document.getElementById("UnAll").value)){
  		alert("Please enter Amount Less Than Or Equal To Unallocated Amount  ");
  		document.getElementById("amount").focus();
    	return false;
  	}
    
    
    }
    </script>
    <body>
    	<div class="wrapper"> 
  			<g:subMenuList/>
	    	<div class="proptable"> 
				<table border="0" cellspacing="0" cellpadding="0" width="70%" align="left">
             		<tr>
                		<td valign="top">
                   			<label for="project"><g:message code="default.Project.label"/>:</label>
                		</td>
                 		<td valign="top" >
               				<strong>${fieldValue(bean:grantAllocationSplitInstance.projects,field:'code')}</strong>
                        </td>      
                 		<td valign="top" >
                  			<label for="party"><g:message code="default.Institution.label"/> :</label>
                  		</td>
                   		<td valign="top" >
               				<strong>${fieldValue(bean:grantAllocationSplitInstance.grantAllocation.party,field:'code')}</strong>
                        </td>
                        <td valign="top" >
                            <label for="party"><g:message code="default.AllocatedAmount(Rs).label"/>:</label>
                        </td>
                        <td valign="top" >
                            <strong><g:message code="default.Rs.label" /> 
                            	${currencyFormat.ConvertToIndainRS(grantAllocationSplitInstance.projects.totAllAmount)}
                        	</strong>
                    	</td>
                    </tr> 
                </table>
             </div>
            <div class="body">
       			<div class="dialog">
        			<table  class="tablewrapper" border="0" cellspacing="0" cellpadding="0">
     					<g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstanceList" >
  						<tr>
    						<td height="39" colspan="3">
	    						<%def grantAllocationSplitList=GrantAllocationSplit.executeQuery("select   GA.grantPeriod, GA.grantAllocation,GA.accountHead, GA.amount,GA.id  from GrantAllocationSplit GA where GA.grantAllocation ="+grantAllocationInstanceList.id); %>
	    
	    						<table width="840" border="0" cellspacing="0" cellpadding="0">
	      							<tr> 
	        							<td width="281">
	        								<strong>${i+1})<g:message code="default.Grant.label"/> : 
	        									<g:message code="default.Rs.label" /> 
	        									${currencyFormat.ConvertToIndainRS(grantAllocationInstanceList.amountAllocated)}
        									</strong>
    									</td>
	        							<td width="271">
	        								<strong> <g:message code="default.DateOfAllocation.label"/> :
	        									<g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstanceList.DateOfAllocation}"/>
        									</strong>
    									</td>
	        							<td width="448">
	        								<strong>
		        								<g:if test="${grantAllocationInstanceList.granter!=null}">
				      								<g:message code="default.Grantor.label"/> 
				      								:${grantAllocationInstanceList.granter.code}
				     							</g:if>
			     							</strong>
		     							</td>
	       							</tr>
								 </table>
							   </td>
  							</tr>
  							<%
							  	def totalGrantAlloctedAmt=GrantAllocationSplit.executeQuery("select sum(GA.amount)  from GrantAllocationSplit GA where GA.grantAllocation ="+grantAllocationInstanceList.id+" group by GA.grantAllocation");
		                        def unallocateAmt
		                        if(totalGrantAlloctedAmt[0]==null)
		                        {
		                        	if(subAllocatedAmount)
		                              unallocateAmt=grantAllocationInstanceList.amountAllocated-subAllocatedAmount
		                            else
		                        	  unallocateAmt=grantAllocationInstanceList.amountAllocated
		                        }
		                        else
		                        {
		                           if(subAllocatedAmount)
		                        	unallocateAmt=grantAllocationInstanceList.amountAllocated-(totalGrantAlloctedAmt[0]+subAllocatedAmount)
		                           else
		                        	unallocateAmt=grantAllocationInstanceList.amountAllocated-totalGrantAlloctedAmt[0]
		                        }
                           %>
  							<tr>
    							<td width="500" >
    								<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		      							<g:each in="${grantAllocationSplitList}" status="j" var="grantAllocationSplit" >
			      							<tr>
		        								<td width="147">${grantAllocationSplit[0].name} </td>
		    									<td width="126"> ${grantAllocationSplit[2].name}  </td>
		        								<td width="169"><g:message code="default.Rs.label" /> 
		        									${currencyFormat.ConvertToIndainRS(grantAllocationSplit[3])}
	        									</td>
		            							<td width="169"> 
		            								<g:link action="edit" id="${grantAllocationSplit[4]}"  rel="example3" 
		            									params="['grantAllotId':grantAllocationInstanceList.id,'UnAll':unallocateAmt]" title="Edit  Allocation" >  
		            									<g:message code="default.Edit.label"/> 
		        									</g:link> 
		    									</td>
		  									</tr>
	       								</g:each>
									</table>
								</td>
    							<td width="330">
    								<%
    								def  grantAllocationSplitGraph= GrantAllocationSplit.executeQuery("select sum(GAS.amount) as SumAmt,GAS.accountHead.code from GrantAllocationSplit GAS where GAS.grantAllocation.id ="+ grantAllocationInstanceList.id+" group by GAS.accountHead.id")
              						def chart = new GoogleChartBuilder()
				      				def textList =[]
				      				def total=0
				      				def k=0 
			      					i=0;
				      				grantAllocationSplitGraph.each 
				      				{
				      					textList.add (grantAllocationSplitGraph[k][0]*100/grantAllocationInstanceList.amountAllocated)
										total=total+grantAllocationSplitGraph[k][0]
										k++
									}
									if(total<grantAllocationInstanceList.amountAllocated)
									textList.add((grantAllocationInstanceList.amountAllocated-total)*100/grantAllocationInstanceList.amountAllocated)
				      				def resultPiechart = chart.pie3DChart{
				      				size(w:290, h:90)
				      				title
									   {
									      row('Fund Allocation') 
									   } 
							      	data(encoding:'text')
								      {
								       dataSet(textList)
								       }
									colors
										{
											color('e25d3a')
											color('fbc363')
									    }
								    title(color:'0000ff', size:1)
								    	{
											row('')
											row('')
									    }
									labels
								       { 
									 		grantAllocationSplitGraph.each 
								 				{
										 			label(grantAllocationSplitGraph[i][1]) 
						 							i++
				 								}
											if(total<grantAllocationInstanceList.amountAllocated)
											label("Un Allocated")
										}
				       				backgrounds
				       				{
										 background
										 	{
										  		solid(color:'cbe2f0')
										    }  

										 area
											 {
												gradient(angle:45, start:'cbe2f0', end:'cbe2f0')
											 } 
									 } 
							  		}
						  		%>
						  		<img src ="${resultPiechart}" align="middle" />
					  		</td>
    						<td width="100">&nbsp;</td>
					  	</tr>
					   <tr>
					  	

 						<td height="40">
 							<strong><g:message code="default.HeadAllocation.UnallocatedAmount(Rs).label"/> : <g:message code="default.Rs.label" />
 							${currencyFormat.ConvertToIndainRS(unallocateAmt)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 							<g:if test="${unallocateAmt>0}">
 								<g:link action="create" id="${grantAllocationSplitInstance.projects.id}" rel="example1" 
 									params="['grantAllotId':grantAllocationInstanceList.id,'UnAll':unallocateAmt]" 
 									title="Head Wise Allocation" >  <g:message code="default.HeadAllocation.Allocate.label"/> 
								</g:link>
							</g:if>
						</td>
    					<td align="left"> </td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
  					  </tr>
  					</g:each>
				</table>              
			</div> 
  		 </div>                     
       </div>
    </body>
</html>
