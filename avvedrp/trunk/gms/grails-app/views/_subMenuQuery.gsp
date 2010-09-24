<html>
       <head>
 
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'jquery.megamenu.css')}" type="text/css" media="screen" />
          <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
        
       
     
    </head>
    
    
    
    

  
<body >

<div class="hrmenu-container" >	
	    <ul id="topnav"> 
        <li> <g:link  controller='grantAllocation' action="projectDash" id="${session.ProjectID}">Project Info</g:link></li> 
        <li> 
            <a href="#">Allocation</a> 
            <span> 
               <g:link  controller='projects' action="showSubProjects"  id="${session.ProjectID}">Sub Projects</g:link> |
               <g:link  controller='grantAllocation' action="subGrantAllot" id="${session.ProjectID}">Sub-allocate Project</g:link>|
				 <g:link  controller='grantAllocation' action="subGrantAllotExt" id="${session.ProjectID}">Grant Allotment by Ext Agency </g:link> |
				<g:link  controller='grantAllocationSplit' action="list" id="${session.ProjectID}">Head-wise Allocation </g:link>
            </span> 
        </li> 
        <li> 
            <a href="#">Expenses</a> 
            <span> 
            
             <g:if test="${session.Role == 'ROLE_PI'}">
			  	<g:link  controller='expenseRequest' action="create" id="${session.ProjectID}">Expense Request</g:link> |
			</g:if>
			<g:else>
				<g:link  controller='grantReceipt' action="create" id="${session.ProjectID}">Grant Receipt</g:link> |
			    <g:link  controller='grantExpense' action="create" id="${session.ProjectID}">Record Expenses</g:link> |
			</g:else>	
					                 
			<g:link  controller='grantExpense' action="listExpenses" id="${session.ProjectID}">View Expense For a Period</g:link> |
			<g:link  controller='grantExpense' action="listSummaryExpenses" id="${session.ProjectID}">View Expenses Headwise</g:link> 
            </span> 
        </li> 
        <li> 
            <a href="#">Reports</a> 
            <span> 
				<g:link  controller='grantAllocation' action="reportView">Utilization Certificate</g:link>|
				<g:link  controller='utilization' action="create id="${session.ProjectID}"> Submit utilization certificate</g:link>|
				<g:link  controller='grantAllocation' action="grantReports">Statistical Reports</g:link>					  	        
				
            </span> 
        </li> 
       
    </ul> 
</div> 
    
       	
    </body>	
</html>