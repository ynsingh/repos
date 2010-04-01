

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
         <meta name="layout" content="main" />
        <title>Fund List</title>
 <style>
 
.sublist{
}

.sublist h1{
font-size: 12px;
font-weight: bold;
color: #a9021a;
 text-align: left;
 padding-left:1px;
}
.sublist2 ul{
  color: #234a64;
  list-style-type: none;
  margin: 0;
  padding: 0;
}
.sublist2 h1{
font-size: 12px;
font-weight: bold;
color: #a9021a;
 text-align: left;
 padding-left:1px;
}
.sublist ul{
list-style-type: none;
margin: 0;
padding: 0;
}

.sublist ul li{
padding-bottom: 0px; /*bottom spacing between menu items*/
display: inline;
}

.sublist ul li a{
color: #234a64;
list-style-image: url(bullet.jpg) no-repeat center left; 
display: block;
padding: 1px 0;
padding-left: 2 px; /*link text is indented 19px*/
text-decoration: none;
font-weight: bold;
font-size: 11px;
}

.sublist ul li a:visited{
color: #234a64;
}

.sublist ul li a:hover{ /*hover state CSS*/
color: #234a64;
text-decoration: underline;
}

/* dashboard Start */
.iTop {
	margin: 0 auto; 
	width: 975px;
	height: 8px;
	background-color: #d2e4ee;
	background-image: url(../../images/themesky/innertop.gif);
	background-repeat: no-repeat;
	}
.iMiddle {
	margin: 0 auto; 
	width: 975px;
	height: auto;
	text-align: center;
	background-color: #d2e4ee;
	background-image: url(../../images/themesky/innerMiddle.gif);
	background-repeat: repeat-y;  
	}
.iFooter {
	margin: 0 auto; 
	width: 975px;
	height: 26px;
	background-color: #d2e4ee;
	background-image: url(../../images/themesky/innerFooter.gif);
	background-repeat: no-repeat;  
	}
/* dashboard end */
</style>
    </head>
    <body>
<!-- Main Container Start -->
  
	
<div class="wrapper">
<div class="innernav">
	 <span class="menuButton">&nbsp;&nbsp;&nbsp;<a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
</div>
</div>
 <!-- InnerDashBoard Start -->
 
 <div class="iTop"></div>
 
 
 <div class="iMiddle">
 <div style="padding-left:11px" align="center">

 
 	  <table width="95%" align="center" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>
 		  <!-- Prodetials Start-->
 		  
 		  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 			  <tr>
 				<td  width="33%" align="left">
 				
 			<!-- info Start-->	
 				<div class="prjInfo">
				       <strong>Project :${projectInstance.code}</strong>
				       <br>
				    <strong>Fund Estimated :</strong><g:formatNumber number="${projectInstance.totAllAmount}" format="##,##,##0.00" />
				    <br>
				       <strong>Fund Received :</strong> <g:formatNumber number="${sumGrantRecieve[0].doubleValue()}" format="##,##,##0.00" />
				    <br>

				       <strong>Fund Utilized :</strong> <g:formatNumber number="${sumAmount[0].doubleValue()}" format="##,##,##0.00" />
				    <br>

				     <strong> Balance :</strong> <g:formatNumber number="${sumGrantRecieve[0].doubleValue()-sumAmount[0].doubleValue()}" format="###,##0.00" />
				    <br>
				     <strong>Project Duration:</strong>
				     <br>
				      <g:formatDate format="dd MMM yyyy" date="${projectInstance.projectStartDate}"/>  - <g:formatDate format="dd MMM yyyy" date="${projectInstance.projectEndDate}"/> (${Math.round((projectInstance.projectEndDate.getTime()-projectInstance.projectStartDate.getTime())/86400000)} days)
				     <br>
				     <strong>Days Remaining : </strong>(${Math.round((projectInstance.projectEndDate.getTime()-new Date().getTime())/86400000)} days)
				    </div >
    
    			<!-- info End-->
    
    
    				</td>
    				
    				
 				<td  style="background-color: #cbe2f0; border: solid #FFFFFF 1px; margin: 5px; border-right: none;" width="33%" align="left">
 				
 			<!-- piechart Start-->	
 				<br>
 				<br>
 				<br>
					

				 <img src ="${resultPiechart}" align="middle" />	
 				
				
 				
 			<!-- piechart End--> 				
 				</td>
 				
 				
 				
 				
 				<td  style="background-color: #cbe2f0; border: solid #FFFFFF 1px; border-left: none; margin: 5px;" width="34%" align="right">
			<!-- Linechart Start-->	
				
				  <img src ="${resultLinechart}" align="right" />	
 				
 				
 				
 			<!-- Linechart End--> 	
 				</td>
 			  </tr>
 		  </table>
 
 
 
 
 
 		   <!-- Prodetials End-->
 		  
 		  </td>
         	</tr>
         	
         	<tr>
          	 <td>
 		  <!-- status Start-->

			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td  width="25%" align="left" >
					<div class="sublist">
							<h1><img src="../../images/themesky/acc.gif" />Allocation</h1>
							
							<ul>
							
							         <li><g:link  controller='projects' action="showSubProjects"  id="${projectInstance.id}">* Sub Projects</g:link></li>
					                <li><a href="../../grantAllocation/subGrantAllot/${projectInstance.id}"><span>* Sub-allocate Project</span></a></li>
					                 <li><a href="../../grantAllocation/subGrantAllotExt/${projectInstance.id}"><span>* Grant Allotment by Ext Agency </span></a></li>

					                

					                <li><a href="../../grantAllocationSplit/list/${projectInstance.id}"><span>* Head-wise Allocation</span></a></li>

					               
					                
					            </ul>
					</div>
				
					</td>
					
					
					<td  width="25%" align="left">
					<div class="sublist">
							<h1><img src="../../images/themesky/exp.gif" />Expenses</h1>
									<ul>
									
									 <g:if test="${session.Role == 'ROLE_PI'}">
									<li><a href="../../expenseRequest/create/${projectInstance.id}"><span>* Expense Request</span></a></li>
									</g:if>
									<g:else>
									<li><a href="../../grantReceipt/create/${projectInstance.id}"><span>*  Grant Receipt</span></a></li>
					                <li><a href="../../grantExpense/create/${projectInstance.id}"><span>* Record Expenses</span></a></li>
					               </g:else>	
					                 
					                <li><a href="../../grantExpense/listExpenses/${projectInstance.id}"><span>* View Expense For a Period</span></a></li>
					                <li><a href="../../grantExpense/listSummaryExpenses/${projectInstance.id}"><span>* View Expenses Headwise</span></a></li>
					               
					            </ul>
					</div>
					
					</td>
					
					
					<td  width="50%" align="left">
					<div class="sublist2">
					<h1><img src="../../images/themesky/rep.gif" />Reports</h1>
							 <ul>
					                <li> <g:jasperReport jasper="HeadWiseExpendturedash" format="PDF" name="Fund Utilization" >
										            <input type="hidden" name="id" value="${session.AppFormID}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										            <input type="hidden" name="partyID" value="${session.Party}" />
										            <input type="hidden" name="periodID" value="" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></li>
					                <li><g:jasperReport jasper="HeadWiseExpendture" format="PDF" name="Expendture" >
										            <input type="hidden" name="id" value="${session.AppFormID}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										           <input type="hidden" name="partyID" value="${session.Party}" />
										            <input type="hidden" name="periodID" value="" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></li>
					                <li><g:jasperReport jasper="UtilizationCertificate" format="PDF" name="Utilization Certificate" >
										            <input type="hidden" name="id" value="${projectInstance.id}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></li>
									<li><g:jasperReport jasper="StatementOFAccounts" format="PDF" name="Statement Of Accounts" >
										            <input type="hidden" name="id" value="${projectInstance.id}" />
										            <input type="hidden" name="projectID" value="${projectInstance.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></li>
								  	 			 <li><a href="../../utilization/create/${projectInstance.id}"><span>* Upload utilization certificate</span></a></li>        
									  	         <li><a href="../../grantAllocation/reports.gsp">More ...</a></li>
            						</ul>
					</div>
					</td>
				  </tr>
			  </table>
 
 		  <!-- status End-->
 		  </td>
        	 </tr>
       		</table>
		</div>
 
 
  
	</div>
 	
	
<div class="iFooter"></div>
</div>
  <!-- InnerDashBoard end -->

        
        
    </body>
</html>
