<html>
       <head>

        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <g:javascript library="jquery" plugin="jquery"/>
         <g:javascript library="applicationValidation" />
           
          <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
            <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>
    		<link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
         <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'colorbox.css')}" type="text/css" media="screen" /> 
                   
           <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" > 
        </script> 
           
        
        <script type="text/javascript"> 
            $(document).ready(function(){
                     
            
			
		$("ul#topnav li").hover(function() { //Hover over event on list item
			$(this).css({ 'background' : '#fcdfa6 url(../../images/hr-menu-hover.jpg) repeat-x'}); //Add background color + image on hovered list item
			$(this).find("span").show(); //Show the subnav
		} , function() { //on hover out...
			$(this).css({ 'background' : 'none'}); //Ditch the background
			$(this).find("span").hide(); //Hide the subnav
		});
		
		
					//Examples of how to assign the ColorBox event to elements
				$("a[rel='example1']").colorbox();
				$("a[rel='example2']").colorbox({transition:"fade"});
				$("a[rel='example3']").colorbox({transition:"none", width:"75%", height:"75%"});
				$("a[rel='example4']").colorbox({slideshow:true});
				$(".example5").colorbox();
				$(".example6").colorbox({iframe:true, innerWidth:600, innerHeight:400});
				$(".example7").colorbox({width:"80%", height:"90%", iframe:true});
				$(".example8").colorbox({width:"50%", inline:true, href:"#inline_example1"});
				$(".example9").colorbox({
					onOpen:function(){ alert('onOpen: colorbox is about to open'); },
					onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
					onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
					onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
					onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
				});
	
		
			
		});
		</script>
		
        
        <g:layoutHead />
        <g:javascript library="application" />				
    </head>
    
    
    
    
<style>

</style>

  
    
<script>
 function setValue()
{

	
	if(eval('typeof(document.grantReport)') != 'undefined')
	{
	  document.List_of_on_going_projects.partyID.value=document.grantReport.party.value;
	  document.GrantsAndContractsStatement11ConsolidatedAbstract.partyID.value=document.grantReport.party.value;
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
	  document.ResearchProjectGrantDailyAccounts.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
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
      
      document.ProjectWiseExpendture.projectID.value=document.frmreport.projects.value;
      document.ProjectWiseExpendture.partyID.value=document.frmreport.party.value;
      
      document.HeadWiseExpendtureNEw.projectID.value=document.frmreport.projects.value;
      document.HeadWiseExpendtureNEw.partyID.value=document.frmreport.party.value;
      
      document.GrandAgencyReport.partyID.value=document.frmreport.party.value;
      document.GrandSummaryReport.partyID.value=document.frmreport.party.value;
      document.InstitutionWiseGrandAgencyReport.projectID.value=document.frmreport.projects.value;
      document.InstitutionWiseGrandAgencyReport.partyID.value=document.frmreport.party.value;
      document.ProjectAssosiate.partyID.value=document.frmreport.party.value;
         
      var day;
	  var month;
	  var year;
	  
	  day=document.frmreport.reportDate_day.value;
	  year=document.frmreport.reportDate_year.value;
	  month=document.frmreport.reportDate_month.value;
	 
	  document.HeadWiseExpendture.reportDate.value = day+"/"+month+"/"+year;	  
	  document.GrandAgencyReport.reportDate.value = day+"/"+month+"/"+year;
	  document.HeadWiseExpendtureNEw.reportDate.value = day+"/"+month+"/"+year;
	  document.GrandSummaryReport.reportDate.value = day+"/"+month+"/"+year;
	  document.InstitutionWiseGrandAgencyReport.reportDate.value = day+"/"+month+"/"+year;
	  document.ProjectAssosiate.reportDate.value = day+"/"+month+"/"+year;
	  document.ProjectWiseExpendture.reportDate.value = day+"/"+month+"/"+year;
	  
	  var today;
	  var tomonth;
	  var toyear;
	  
	  today=document.frmreport.reportDateTo_day.value;
	  toyear=document.frmreport.reportDateTo_year.value;
	  tomonth=document.frmreport.reportDateTo_month.value;
	  
	  document.HeadWiseExpendture.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
      document.GrandAgencyReport.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
      document.HeadWiseExpendtureNEw.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
      document.GrandSummaryReport.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
      document.InstitutionWiseGrandAgencyReport.reportDateTo.value = today+"/"+tomonth+"/"+toyear;	
      document.ProjectAssosiate.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
      document.ProjectWiseExpendture.reportDateTo.value = today+"/"+tomonth+"/"+toyear; 
}
else if(eval('typeof(document.frmGrantTrackingReport)') != 'undefined')
{
	
	document.GrantAllocationStatusReport.projectID.value = document.frmGrantTrackingReport.projects.value;
	document.GrantAllocationStatusReport.grantStatus.value = document.frmGrantTrackingReport.grantAllocationStatus.value;
	
	document.ProjectStatusReport.projectID.value = document.frmGrantTrackingReport.projects.value;
	document.ProjectStatusReport.projectStatus.value = document.frmGrantTrackingReport.projectStatus.value;
	if(document.frmGrantTrackingReport.projectStatus.value.indexOf("\'") == -1)
	document.ProjectStatusReport.projectStatus.value = "\'"+document.frmGrantTrackingReport.projectStatus.value+"\'";
	
  
}

else if(eval('typeof(document.granteeReports)') != 'undefined')
{
	
	document.List_of_grantees.partyID.value=document.granteeReports.party.value;
	document.NotificationReport.partyID.value=document.granteeReports.party.value;	  
	document.ProposalReport.partyID.value=document.granteeReports.party.value;
}
else if(eval('typeof(document.auditLoggingReport)') != 'undefined')
{
var day;
	  var month;
	  var year;
	  day=document.auditLoggingReport.reportDate_day.value;
	  year=document.auditLoggingReport.reportDate_year.value;
	  month=document.auditLoggingReport.reportDate_month.value;
	  document.AuditLogReport.reportDate.value = day+"/"+month+"/"+year;
	  var today;
	  var tomonth;
	  var toyear;
	  today=document.auditLoggingReport.reportDateTo_day.value;
	  toyear=document.auditLoggingReport.reportDateTo_year.value;
	  tomonth=document.auditLoggingReport.reportDateTo_month.value;
	  document.AuditLogReport.reportDateTo.value = today+"/"+tomonth+"/"+toyear;
	  
	 }
	 
	 else if(eval('typeof(document.reportView)') != 'undefined')
	 {
		  
		  document.UtilizationCertificate.reportDate.value = document.reportView.reportDate.value;
		  //document.StatementOfAccounts.reportDate.value = day+"/"+month+"/"+year;
		  
	
		  
		  document.UtilizationCertificatess.reportDateTo.value = document.reportView.reportDateTo.value;
		 //document.StatementOfAccounts.reportDateTo.value = day+"/"+month+"/"+year;
	  
	 }

    }
    
    
    
       

    
    
    $(function() {
 
	// if the function argument is given to overlay,
	// it is assumed to be the onBeforeLoad event listener
	$("a[rel]").overlay({
 
		expose: 'white',
		effect: 'apple',
 
		onBeforeLoad: function() {
 
			// grab wrapper element inside content
			var wrap = this.getContent().find(".contentWrap");
 
			// load the page specified in the trigger
			wrap.load(this.getTrigger().attr("href"));
		}
 
	});
});
    
    </script>
    
    
    
  
<body onload="setValue()"  >
 <!-- 
 	<div>
	
 <a href="${createLinkTo(dir:'/images/help',file:session.Help)}" target="right" title="Help" alt="Help" rel="#overlay" > 
			<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}"/> 
			</a>
			<a class='example6' href="${createLinkTo(dir:'/images/help',file:session.Help)}" title="Help"><img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}"/> </a>
			
	</div>

<!-- inner Banner Start-->



     




    
          <g:layoutBody />
       
       
    </body>	
</html>