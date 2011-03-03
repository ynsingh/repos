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
		            <g:if test="${session.Role == 'ROLE_SITEADMIN'}">
			            <span> 
							<g:link  controller='projects' action="edit" id="${session.ProjectID}">Project Home</g:link> |
							<g:link  controller='projectsPIMap' action="create" id="${session.ProjectID}">Co-PI Addition</g:link> |<g:link  controller='projectDepartmentMap' action="create" id="${session.ProjectID}">Department Addition</g:link>  
			            	<g:if test="${projectsInstance.parent}"></g:if>
			        		<g:else>
			        			|<g:link controller='grantAllocation' action="fundAllot" id="${session.ProjectID}"><g:message code="default.FundAllocation.label"/></g:link>
			        		</g:else>
			            </span> 
		            </g:if>
		        
		        </li> 
		        <li> 
					<a href="#"><g:message code="default.Allocation.label"/></a> 
		            <span> 
		                <g:link  controller='grantAllocationSplit' action="list" id="${session.ProjectID}"><g:message code="default.HeadwiseAllocation.label"/></g:link> |
						<g:link  controller='grantAllocation' action="subGrantAllot" id="${session.ProjectID}"><g:message code="default.SuballocateProject.label"/></g:link> 
						
		            </span> 
		        </li> 
		        <li> 
		            <a href="#"><g:message code="default.Expenses.label"/></a> 
		            <span> 
						<g:if test="${session.Role == 'ROLE_PI'}">
							<g:link  controller='expenseRequest' action="create" id="${session.ProjectID}"><g:message code="default.ExpenseRequest.label"/></g:link> |
						</g:if>
						<g:else>
							<g:link  controller='grantReceipt' action="create" id="${session.ProjectID}"><g:message code="default.GrantReceipt.label"/></g:link> |
					    	<g:link  controller='grantExpense' action="create" id="${session.ProjectID}"><g:message code="default.RecordExpenses.label"/></g:link> |
						</g:else>	
						<g:link  controller='grantExpense' action="listExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpenseForaPeriod.label"/></g:link> |
						<g:link  controller='grantExpense' action="listSummaryExpenses" id="${session.ProjectID}"><g:message code="default.ViewExpensesHeadwise.label"/></g:link> 
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
						<g:link  controller='grantAllocation' action="reportView"><g:message code="default.UtilizationCertificates.label"/></g:link>|
						<g:link  controller='utilization' action="create" id="${session.ProjectID}"><g:message code="default.Submitutilizationcertificate.label"/></g:link>|
						<g:link  controller='projectEmployee' action="hrassetReports"><g:message code="default.AssetReports.label"/></g:link>
            		</span> 
        		</li>  
        	    
    		</ul> 
		</div> 
    </body>	
</html>