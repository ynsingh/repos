<html>
       <head>

        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    
    
    
    
<style>

</style>





   <script type="text/javascript" src="../images/jquery.js"></script> 
            <script type="text/javascript" src="../images/jquery.pngFix.pack.js"></script> 

<script type="text/javascript"> 
    $(document).ready(function(){ 
        $(document).pngFix(); 
    }); 
</script> 

    
<script>
 function setValue()
{

	
	if(eval('typeof(document.grantReport)') != 'undefined')
	{
	  document.List_of_on_going_projects.partyID.value=document.grantReport.party.value;
	  document.GrantsAndContractsStatement10Abstract.partyID.value=document.grantReport.party.value;
	  document.GrantsAndContractsStatement10Abstract.projectType.value=document.grantReport.projectType.value; 
	  document.GrantsAndContractsStatement9.partyID.value=document.grantReport.party.value;
	  document.GrantsAndContractsStatement9.projectType.value=document.grantReport.projectType.value; 
	  document.GrantsAndContractsStatement9Abstract.partyID.value=document.grantReport.party.value;
	  document.GrantsAndContractsStatement9Abstract.projectType.value=document.grantReport.projectType.value; 
	  document.OnGoingClinicalTrials.partyID.value=document.grantReport.party.value;
	  document.OnGoingClinicalTrials.projectType.value=document.grantReport.projectType.value; 
	  var day;
	  var month;
	  var year;
	  day=document.grantReport.reportDate_day.value;
	  year=document.grantReport.reportDate_year.value;
	  month=document.grantReport.reportDate_month.value;
	  document.ResearchProjectGrantDailyAccounts.reportDate.value = day+"/"+month+"/"+year;
	  document.ResearchProjectGrantDailyAccounts.projectID.value=document.grantReport.projects.value;
	  document.expenditures.reportDate.value = day+"/"+month+"/"+year;
	  var today;
	  var tomonth;
	  var toyear;
	  today=document.grantReport.reportDateTo_day.value;
	  toyear=document.grantReport.reportDateTo_year.value;
	  tomonth=document.grantReport.reportDateTo_month.value;
	  document.ResearchProjectGrantAnnualStatement.projectID.value=document.grantReport.projects.value;
	  document.ResearchProjectGrantAnnualStatement.partyID.value=document.grantReport.party.value;
	  document.ResearchProjectGrantAnnualStatement.reportDate.value = day+"/"+month+"/"+year;
	  document.expenditures.partyID.value=document.grantReport.party.value;
	  document.ResearchProjectGrantFinalStatement.projectID.value=document.grantReport.projects.value;
	  document.ResearchProjectGrantFinalStatement.partyID.value=document.grantReport.party.value;
	  document.ResearchProjectGrantFinalStatement.reportDate.value = day+"/"+month+"/"+year;
	  document.ResearchProjectGrantFinalStatement.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
	  document.ResearchProjectGrantMonthwiseAccount.projectID.value=document.grantReport.projects.value;
	  document.ResearchProjectGrantMonthwiseAccount.reportDate.value = day+"/"+month+"/"+year;
	 }

else if(eval('typeof(document.frmreport)') != 'undefined')

{
	
       document.HeadWiseExpendture.projectID.value=document.frmreport.projects.value;
	 
     
      document.HeadWiseExpendture.partyID.value=document.frmreport.party.value;
  
       document.HeadWiseExpendture.periodID.value=document.frmreport.grantPeriod.value;
       
       
         document.ProjectWiseExpendture.projectID.value=document.frmreport.projects.value;
  
        document.ProjectWiseExpendture.partyID.value=document.frmreport.party.value;
  
        document.ProjectWiseExpendture.periodID.value=document.frmreport.grantPeriod.value;
        
        
         document.HeadWiseExpendtureNEw.projectID.value=document.frmreport.projects.value;
  
        document.HeadWiseExpendtureNEw.partyID.value=document.frmreport.party.value;
  
         document.HeadWiseExpendtureNEw.periodID.value=document.frmreport.grantPeriod.value;
        
         document.GrandAgencyReport.partyID.value=document.frmreport.party.value;
         document.GrandSummaryReport.partyID.value=document.frmreport.party.value;
		  
  
       

       
         document.InstitutionWiseGrandAgencyReport.projectID.value=document.frmreport.projects.value;

        document.InstitutionWiseGrandAgencyReport.partyID.value=document.frmreport.party.value;
         document.ProjectAssosiate.partyID.value=document.frmreport.party.value;
         
		
        
}
else if(eval('typeof(document.frmGrantTrackingReport)') != 'undefined'){
	document.GrantAllocationStatusReport.projectID.value = document.frmGrantTrackingReport.projects.value;
	document.GrantAllocationStatusReport.grantStatus.value = document.frmGrantTrackingReport.grantAllocationStatus.value;
	
	document.ProjectStatusReport.projectID.value = document.frmGrantTrackingReport.projects.value;
	document.ProjectStatusReport.projectStatus.value = document.frmGrantTrackingReport.projectStatus.value;
	if(document.frmGrantTrackingReport.projectStatus.value.indexOf("\'") == -1)
		document.ProjectStatusReport.projectStatus.value = "\'"+document.frmGrantTrackingReport.projectStatus.value+"\'";
	
  
}
   

    }
    </script>
  
<body onload="setValue()"  >
 
	<div id="spinner" class="spinner" style="display:none;">
    <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
	</div>
    

<!-- inner Banner Start-->



     


<div class="innnerBanner">
<div class="loginLink">
	<span>
	<a href="${createLinkTo(dir:'/user/changePassword')}"><img src="${createLinkTo(dir:'images/themesky',file:'key.gif')}"  title="Change Password" alt="Change Password" />  |</a>   <a href="${createLinkTo(dir:'UserDoc/GMS_DOC.html')}" target="_blank"><img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" alt="Help" title="Help"/>  |</a>  
	<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>
   	<g:isLoggedIn>
   	<b><g:loggedInUsername/></b> (<g:link  controller='logout'>Logout</g:link>)
   	</g:isLoggedIn></font>
   	</span>
   	
   	  <g:isNotLoggedIn>
         
       <%
    
     
       %>
       </g:isNotLoggedIn>
	
  	<g:if test="${session.PartyID == null}">   
     	 <%
       
       %>
    </g:if>   
</div>
</div>


<!-- inner Banner end-->   
	 
    </div>
  
        <g:layoutBody />
        
       	
    </body>	
</html>