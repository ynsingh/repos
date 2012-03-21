<html>
	<head>
		<link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
		<link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
		<script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
	</head>
	<body >
		<div class="hrmenu-container" >	
	   		<ul id="topnav"> 
		        <li> <g:link  controller='grantAllocation' action="projectDash" id="${session.ProjectID}"><g:message code="default.ProjectInfo.label"/></g:link>
		           			<span> 
								<g:link  controller='projects' action="edit" id="${session.ProjectID}"><g:message code="default.ProjectHome.label"/></g:link> |
								<g:link  controller='projectsPIMap' action="create" id="${session.ProjectID}"><g:message code="default.Co-PIAddition.label"/></g:link> |<g:link  controller='projectDepartmentMap' action="create" id="${session.ProjectID}"><g:message code="default.DepartmentAddition.label"/></g:link>|
								<g:link  controller='budgetDetails' action="assignedBudget" params="[moduleType:'Projects']" id="${session.ProjectID}"><g:message code="default.BudgetDetails.label"/></g:link>  
				            	<g:if test="${projectsInstance.parent}"></g:if>
				        		<g:else>
				        			|<g:link controller='grantAllocation' action="fundAllot" id="${session.ProjectID}"><g:message code="default.FundAllocation.label"/></g:link>
				        		</g:else>
				            </span> 
				        </li> 
		        <li> 
					<a href="#"><g:message code="default.Allocation.label"/></a> 
		            <span> 
		            <g:if test="${projectsInstance.parent}">
			            <g:link  controller='grantAllocationSplit' action="list" id="${session.ProjectID}"><g:message code="default.HeadwiseAllocationByGranter.label"/></g:link> |
			            <g:link  controller='grantAllocation' action="subGrantAllot" id="${session.ProjectID}"><g:message code="default.SuballocateProject.label"/></g:link> |
		                <g:link  controller='externalFundAllocation' action="agencyCreate" id="${session.ProjectID}"><g:message code="default.ExternalFundAllocation.label"/></g:link>
		            </g:if>
		            <g:else>   
		                <g:link  controller='grantAllocationSplit' action="list" id="${session.ProjectID}"><g:message code="default.HeadwiseAllocation.label"/></g:link> |
						<g:link  controller='grantAllocation' action="subGrantAllot" id="${session.ProjectID}"><g:message code="default.SuballocateProject.label"/></g:link> |
						<g:link  controller='externalFundAllocation' action="agencyCreate" id="${session.ProjectID}"><g:message code="default.ExternalFundAllocation.label"/></g:link>
		            </g:else>
		            </span> 
		        </li> 
		        <li> 
		            <a href="#"><g:message code="default.Expenses.label"/></a> 
		            <span> 
		             <% def authorityPersonInstance = UserRole.executeQuery("select UR.role from UserRole UR where UR.user.id= "+session.UserId+" and UR.role.id=(select A.id from Authority A where A.authority="+ROLE_PI+")"  )%>
		     			<g:if test="${authorityPersonInstance}">
							<g:link  controller='grantExpense' action="listExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpenseForaPeriod.label"/></g:link> |
							<g:link  controller='grantExpense' action="listSummaryExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpensesHeadwise.label"/></g:link> 
						</g:if>
						<g:else>
							<g:link  controller='grantReceipt' action="create" id="${session.ProjectID}"><g:message code="default.GrantReceipt.label"/></g:link> |
					    	<g:link  controller='grantExpense' action="create" id="${session.ProjectID}"><g:message code="default.RecordExpenses.label"/></g:link> |
							<g:link  controller='grantExpense' action="listExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpenseForaPeriod.label"/></g:link> |
							<g:link  controller='grantExpense' action="listSummaryExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpensesHeadwise.label"/></g:link> |
							<g:link  controller='fundAdvance' action="create" id="${session.ProjectID}"><g:message code="default.FundAdvance.label"/></g:link>
					   </g:else>	 
		            </span> 
				</li> 
				<li> 
        			<g:link  action="create" controller='projectTracking'  id="${session.ProjectID}"><g:message code="default.ProjectClosure.label"/></g:link>
        		</li>
        		     
    	     	<li> 
        			<g:link  controller='projectEmployee' action="create"   id="${session.ProjectID}"><g:message code="default.ProjectPersonnel.label"/></g:link>
        	    </li>
        	    
    		    <li> 
        			<g:link  controller='itemPurchase' action="create" id="${session.ProjectID}"><g:message code="default.Asset.label"/></g:link>
        	    </li>
        	    
        	    <li> 
            		<a href="#"><g:message code="default.Reports.label"/></a> 
            		<span> 
						
						<g:link  controller='utilization' action="create" id="${session.ProjectID}"><g:message code="default.Submitutilizationcertificate.label"/></g:link>|
						<g:link  controller='projectEmployee' action="hrassetReports"><g:message code="default.AssetReports.label"/></g:link>
            		</span> 
        		</li>  
        	    
    		</ul> 
		</div> 
    </body>	
</html>