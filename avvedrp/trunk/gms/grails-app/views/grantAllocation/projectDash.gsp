<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'images',file:'hro.css')}" />
        <meta name="layout" content="main" />
        <title><g:message code="default.FundList.title" /></title>
        <script type="text/javascript" src="${createLinkTo(dir:'images',file:'jquery-1.3.2.min.js')}">
        </script> 
		<style>
 		</style>
	</head>
<!-- Main Container Start -->
	<body>
    	<div class="wrapper">
        	<g:subMenuList />
        	<div class="dashbody">
 <!-- Prodetials Start-->	
				<table width="100%" align="center" border="0" cellspacing="5" cellpadding="5">
         			<tr>
           				<td>
 		 					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
 			  					<tr>
 									<td  width="33%" align="left">
					<!-- info Start-->	
 										<div class="prjInfo">
					       					<strong>
					       						<g:message code="default.Project.label" />
				       							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				       							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				       							: ${projectInstance.code}
				       						</strong>
					        				<br>
										    
										    <g:if test="${grantAllocationInstance.granter!=null}"> 
										    	<strong>
										    		<g:message code="default.Grantor.label" />
										    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				       								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										    		: ${grantAllocationInstance.granter.code}
									    		</strong>
									    		<br>
									    	</g:if>
							    	       	
							    	       	<strong>
								    	       	<g:message code="default.FundList.FundEstimated.label"/>
								    	       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								    	       	: <g:message code="default.Rs.label"/>
							    	       	</strong>
							  				${currencyFormat.ConvertToIndainRS(projectInstance.totAllAmount)} 
					    					<br>
					       					
					       					<strong>
					       						<g:message code="default.FundList.FundReceived.label" />
					       						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					       						: <g:message code="default.Rs.label"/>
					       					</strong> 
				       						${currencyFormat.ConvertToIndainRS(sumGrantRecieve[0].doubleValue())} 
					    					<br>
					    					
					    					<strong>
								    	       	<g:message code="default.FundTransferred.label"/>
								    	       	&nbsp;&nbsp;
								    	       	: <g:message code="default.Rs.label"/>
							    	       	</strong>
							  					${currencyFormat.ConvertToIndainRS(fundTransferInstance)} 
					    					<br>
					    					
					    					<strong>
					    						<g:message code="default.FundList.FundUtilized.label" />
					    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    						: <g:message code="default.Rs.label"/>
					    					</strong> 
					    					${currencyFormat.ConvertToIndainRS(sumAmount[0].doubleValue()+fundTransferInstance)}
					    					<br>
					    					
					    					<strong><g:message code="default.FundList.Balance.label" />
					    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    						: <g:message code="default.Rs.label"/> 
				    						</strong> 
					    						${currencyFormat.ConvertToIndainRS(sumGrantRecieve[0].doubleValue()-
					    						sumAmount[0].doubleValue()-fundTransferInstance)}
					    					<br>
					     					
					     					<strong>
					     						<g:message code="default.FundList.ProjectDuration.label" />
					     						&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
				     						</strong>
				     							<g:formatDate format="dd MMM yyyy" date="${projectInstance.projectStartDate}"/>- 
					      						<g:formatDate format="dd MMM yyyy" date="${projectInstance.projectEndDate}"/> 
						      					(${Math.round((projectInstance.projectEndDate.getTime()-
						      					projectInstance.projectStartDate.getTime())/86400000)} days)
					     					<br>
					     					
					     					<strong>
					     						<g:message code="default.FundList.DaysRemaining.label" />
					     						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
				     						</strong>
					     						(${Math.round((projectInstance.projectEndDate.getTime()-
					     							new Date().getTime())/86400000)} days)				    
			     						</div >
									</td>
    				<!-- info End-->    				
		  						</tr>
 			  					<tr>
 			    					<td align="left"> <table align="center" width="95%">
  										<tr>
    										<td  style="background-color: #cbe2f0; border: solid #FFFFFF 1px; 
	    										margin: 5px; border-right: none;" 
	    										width="33%" align="left">
 							<!-- piechart Start-->	
	 											<br>
	 											<br>
	 											<br>
	 											<img src ="${resultPiechart}" align="middle" />	
 							<!-- piechart End--> 				
								  			 </td>
								  			 <td  style="background-color: #cbe2f0; border: solid #FFFFFF 1px; 
								  			 		border-left: none; margin: 5px;" width="34%" align="right">
						<!-- Linechart Start-->	
			 								  	<img src ="${resultLinechart}" align="right" />
 						<!-- Linechart End --> 	
 											  </td>
										  </tr>
									</table>
								</td>
  							</tr>
 		  				</table>
 		   <!-- Prodetials End-->
	  				</td>
 			  	</tr>
 		 	 </table>
           </div>  
        </div>
    </body>
</html>
