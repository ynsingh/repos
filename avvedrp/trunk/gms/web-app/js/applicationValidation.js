function checkAll()
{
	if(document.accessPermission.All.checked==true)
	{
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			document.accessPermission.projectName[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			document.accessPermission.projectName[i].checked=false;
		}
	}
}
function checkAllDelete()
{
	if(document.accessPermission.AllDelete.checked==true)
	{
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			document.accessPermission.projectId[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			document.accessPermission.projectId[i].checked=false;
		}
	}
}
function validate()
{ 
		  var isChecked=0; 
		  if(document.getElementById("user.id").value == 'null')
		{
			alert("Please Select a User");
	   	 	return false;
		}
		if(document.accessPermission.projectName.length > 1)  
		  { 
		for(var i=0;i<document.accessPermission.projectName.length;i++)
		{
			if(document.accessPermission.projectName[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a project name. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.accessPermission.projectName.checked!=true)
			{
				alert('You didn\'t check a project name. Please check a box.'); 
			return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}
function validateForDelete()
{ 
		  var isChecked=0;  
		  if(document.getElementById("user.id").value == 'null')
		{
			alert("Please Select a User");
	   	 	return false;
		}
		if(document.accessPermission.projectId.length > 1)  
		  {
		for(var i=0;i<document.accessPermission.projectId.length;i++)
		{
			if(document.accessPermission.projectId[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a project name for delete. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.accessPermission.projectId.checked!=true)
			{
				alert('You didn\'t check a project name for delete. Please check a box.'); 
			return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}
 function checkAllActions()
{
	 
	if(document.rolePrivi.All.checked==true)
	{
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			document.rolePrivi.actionName[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			document.rolePrivi.actionName[i].checked=false;
		}
	}
}
function checkAllActionDelete()
{
	if(document.rolePrivi.AllDel.checked==true)
	{	
	
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{	
			document.rolePrivi.deleteAction[i].checked=true;
		}
	}
	else
	{
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{
			document.rolePrivi.deleteAction[i].checked=false;
		}
	}
}
function validateActions()
{ 
		  var isChecked=0;
		  if(document.rolePrivi.actionName.length > 1)  
		  {
		for(var i=0;i<document.rolePrivi.actionName.length;i++)
		{
			if(document.rolePrivi.actionName[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked <= 0) 
		{ 
			alert('You didn\'t check a action name. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.rolePrivi.actionName.checked!=true)
			{
				alert('You didn\'t check a action name. Please check a box.'); 
			return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}
function validateActionForDelete()
{ 
		  var isChecked=0;  
		   if(document.rolePrivi.deleteAction.length < 0)
		   {
		   	alert("All actions are deleted")
		   }
		  else if(document.rolePrivi.deleteAction.length > 1)  
		  {
		for(var i=0;i<document.rolePrivi.deleteAction.length;i++)
		{	
			if(document.rolePrivi.deleteAction[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t check a action name for delete. Please check a box.'); 
			return false;
		}
		}
		else
		{
			if(document.rolePrivi.deleteAction.checked!=true)
			{
				alert('You didn\'t check a action name for delete. Please check a box.'); 
				return false;
			}
		}
		
	//rolePrivi.submit();	
   return true;
}


function validateProject(){
	if(document.getElementById("name").value == "")
	{
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	}
	if(document.getElementById("code").value == "")
	{
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	if( ( (document.getElementById("projectType").value) == 'null') || ( (document.getElementById("projectType").value) == '') )
    {
	    alert("Please enter the Project Type");
	    return false;
    }
    if( ( (document.getElementById("investigator.id").value) == 'null') || ( (document.getElementById("investigator.id").value) == '') )
    {
	    alert("Please enter the Investigator");
	    return false;
    }
	
	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
	
	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	 
    	
    	if (newProjectStartDate>=newProjectEndDate)
    	{
    		alert("Project End Date should be greater than Start Date")
    		return false;
    	}
    	return true;
    
    }
    
   function validateSubProject(){
    		
       	if(document.getElementById("name").value == ""){
    		alert("Please Enter Name");
		    document.getElementById("name").focus();
		    return false;
    	}
    	if(document.getElementById("code").value == ""){
    		alert("Please Enter Code");
		    document.getElementById("code").focus();
		    return false;
    	}
    	
    	//Sub Project Start Date
    	var projectStartDateYear = document.getElementById("projectStartDate_year").value;
    	var projectStartDateMonth = document.getElementById("projectStartDate_month").value;
    	var projectStartDateDate = document.getElementById("projectStartDate_day").value;
    	
    	//Sub Project End Date
    	
    	var projectEndDateYear = document.getElementById("projectEndDate_year").value;
    	var projectEndDateMonth = document.getElementById("projectEndDate_month").value;
    	var projectEndDateDate = document.getElementById("projectEndDate_day").value;
    	
    	var newProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    	var newProjectEndDate = new Date(projectEndDateYear,projectEndDateMonth-1,projectEndDateDate);
    	
		//Parent Project Start Date
		var parentStartDate = document.getElementById("parentProjectStartDate").value;
    	  		
    	var parentStartDateYear=parentStartDate.substring(0,parentStartDate.indexOf("-"));
    	var parentStartDateMonth=parentStartDate.substring(parentStartDate.indexOf("-")+1,parentStartDate.lastIndexOf("-"));
    	var parentStartDateDate=parentStartDate.substring(parentStartDate.lastIndexOf("-")+1,parentStartDate.lastIndexOf(" "));
    		
    	//Parent Project End Date
    		
    	var parentEndDate = document.getElementById("parentProjectEndDate").value;
       		var parentEndDateYear=parentEndDate.substring(0,parentEndDate.indexOf("-"));
    		var parentEndDateMonth=parentEndDate.substring(parentEndDate.indexOf("-")+1,parentEndDate.lastIndexOf("-"));
    		var parentEndDateDate=parentEndDate.substring(parentEndDate.lastIndexOf("-")+1,parentEndDate.lastIndexOf(" "));
    		
    		var newParentProjectStartDate = new Date(parentStartDateYear,parentStartDateMonth-1,parentStartDateDate);
    		var newParentProjectEndDate = new Date(parentEndDateYear,parentEndDateMonth-1,parentEndDateDate);
    		
    		
    		
    		
    		if (newProjectStartDate>=newProjectEndDate)
    		{
    			alert("Sub project End Date should be greater than sub project Start Date")
    			return false;
    		}
    		
    		if ((newProjectStartDate<newParentProjectStartDate)||(newProjectEndDate>newParentProjectEndDate))
	 	{
		alert("Sub project Date period should be between the Main project date");
		return false;
		}
		
	if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
	    alert("Please enter the Recipient");
	    return false;
    }
    if( ( (document.getElementById("investigator.id").value) == 'null') || ( (document.getElementById("investigator.id").value) == '') )
    {
	    alert("Please enter the Investigator");
	    return false;
    }
    
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus
	    return false;
    }
    if((document.getElementById("amountAllocated").value)=='')
    {
	    alert("Please enter the Amount Allocated  ");
	    return false;
    }
    
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated   ");
	    return false;
    } 
       if((document.getElementById("sanctionOrderNo").value)=='')
    {
	    alert("Please enter the Sanction Order No  ");
	    return false;
    }     
   	return true;

}

function validateFundTransffered()
{

var transferDateYear = document.getElementById("dateOfTransfer_year").value;
var transferDateMonth = document.getElementById("dateOfTransfer_month").value;
var transferDateDate = document.getElementById("dateOfTransfer_day").value;
var TransferDate = new Date(transferDateYear,transferDateMonth-1,transferDateDate);
   
 var d = new Date();

var curr_date = d.getDate();

var curr_month = d.getMonth();

var curr_year = d.getFullYear();

var Today = new Date(curr_year,curr_month,curr_date);

var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
 var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);

 if(TransferDate > Today)
    {
        alert("Date of Transfer should be less than current date");
        return false; 
    }
 if(TransferDate < ProjectStartDate)
   {
   alert("Date of Transfer should be greater than or equal to Project Start Date");
   return false;
   }
   
 if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").focus
	    return false;
    }
 if((document.getElementById("amount").value)=='')
    {
	    alert("Please enter the Amount ");
	    return false;
    }
    
 if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter a valid Amount   ");
	    return false;
    } 
 }
function validateAccountHead()
{
	if(document.getElementById("name").value == ""){
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	}
	if(document.getElementById("code").value == ""){
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	return true;
}
function validateExpenseRequest()
{
    if((document.getElementById("requestedAmount").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
     
    if(isNaN(document.getElementById("requestedAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("requestedAmount").select();
	    return false;
    }
    if(eval(document.getElementById("requestedAmount").value)<=0)
	    {
		    alert("Please enter Proper Amount  ");
		    return false;
	    }
	}
	function validateGrantAllocationCreate()
    {
   
	    if(eval(document.getElementById("amountAllocated").value)+eval(document.getElementById("totAloAmt").value)>=eval(document.getElementById("totAmt").value))
    {
    alert("Amount Exceeded ,fund Allocation not possible ");
    return false;
    }
}
function validateGrantAllocationEdit()
{     
    if(isNaN(document.getElementById("amountAllocated").value))
    {
    alert("Please enter a valid Amount");
    document.getElementById("amountAllocated").focus
    return false;
    }
     if((document.getElementById("amountAllocated").value)=='')
    {
    alert("Please enter Amount");
    return false;
    }
    
    if(eval(document.getElementById("amountAllocated").value)<0)
    {
    alert("Please enter a valid Amount");
    return false;
    } 
    var allocationDateYear = document.getElementById("dateOfAllocation_year").value;
    var allocationDateMonth = document.getElementById("dateOfAllocation_month").value;
	var allocationDateDate = document.getElementById("dateOfAllocation_day").value;
	
	var AllocationDate = new Date(allocationDateYear,allocationDateMonth-1,allocationDateDate);
		
	var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
    var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    		
   if(AllocationDate < ProjectStartDate)
   {
   alert("Allocation Date should be greater than Project Start Date");
   return false;
   }  	
   return true;   

}
function validateEditProAllot()
{     
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }
    if((document.getElementById("amountAllocated").value)=='')
    {
	    alert("Please enter the Amount Allocated ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated ");
	    document.getElementById("amountAllocated").select();
	    return false;
    }    

}
function validateFundAllot()
{     	
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus
	    return false;
    }
    if((document.getElementById("amountAllocated").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    	
    var allocationDateYear = document.getElementById("dateOfAllocation_year").value;
    var allocationDateMonth = document.getElementById("dateOfAllocation_month").value;
	var allocationDateDate = document.getElementById("dateOfAllocation_day").value;
	
	var AllocationDate = new Date(allocationDateYear,allocationDateMonth-1,allocationDateDate);
		
	var projectStartDate = document.getElementById("ProjectStartDate").value;
    var projectStartDateYear=projectStartDate.substring(0,projectStartDate.indexOf("-"));
    var projectStartDateMonth=projectStartDate.substring(projectStartDate.indexOf("-")+1,projectStartDate.lastIndexOf("-"));
    var projectStartDateDate=projectStartDate.substring(projectStartDate.lastIndexOf("-")+1,projectStartDate.lastIndexOf(" "));
    		
    var ProjectStartDate = new Date(projectStartDateYear,projectStartDateMonth-1,projectStartDateDate);
    		
   if(AllocationDate < ProjectStartDate)
   {
   alert("Allocation Date should be greater than Project Start Date");
   return false;
   }  	
   return true;
    

}
function validateReportViewConfirmPrint()
{
    var answer = confirm("Do you want to view report?")
	if (answer)
	{
		document.viewStatementOfAccounts.target="_blank";
		return true;
	}
	else
	{
		return false;
	} 
	document.viewStatementOfAccounts.target="_blank";
	return true;
}
function validateSubGrantAllot()
{     
    
    if( ( (document.getElementById("recipient").value) == 'null') || ( (document.getElementById("recipient").value) == '') )
    {
	    alert("Please enter the Recipient");
	    return false;
    }
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus
	    return false;
    }
    if((document.getElementById("amountAllocated").value)=='')
    {
	    alert("Please enter the Amount Allocated  ");
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter the Amount Allocated   ");
	    return false;
    }      
}
function validateSubGrantAllotExt()
{ 
	if( ( (document.getElementById("granter").value) == 'null') || ( (document.getElementById("granter").value) == '') )
	{
		alert("Please enter granter");
		return false;
	}
    if(isNaN(document.getElementById("amountAllocated").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amountAllocated").focus
	    return false;
    }
    if((document.getElementById("amountAllocated").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    if(eval(document.getElementById("amountAllocated").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }    
}

function refreshParentGrantAllocationSplit() 
{
	window.opener.location.href = window.opener.location.href;
	if(window.opener.progressWindow)
	{
		window.opener.progressWindow.close()
	}
	top.close();
}
function validateGrantExpense()
{
    if((document.getElementById("expenseAmount").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    if(isNaN(document.getElementById("expenseAmount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("expenseAmount").focus
	    return false;
    }
    if(eval(document.getElementById("expenseAmount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    
   if(((document.getElementById("grantAllocationSplit.id").value) == 'null') || ( (document.getElementById("grantAllocationSplit.id").value) == '' ) )
   {
   alert("Please select Account Head");
   return false;
   }
    
    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
    alert("Please select Mode Of Payment");
    return false;
    }
    
    if((document.getElementById("ddNo").value)=='')
    {
	    alert("Please enter DD/Cheque No  ");
	    return false;
    }	
    
    if((document.getElementById("bankName").value)=='')
    {
	    alert("Please enter Bank Name  ");
	    return false;
    }
    
    if((document.getElementById("ddBranch").value)=='')
    {
	    alert("Please enter Bank Branch  ");
	    return false;
    }
    
	
}
function validateGrantPeriod()
{
	if(document.getElementById("name").value == "")
	{
		alert("Please Enter Name");
	    document.getElementById("name").focus();
	    return false;
	}
	return true;
}
function validateGrantReceipt()
{
 
    if((document.getElementById("referenceId").value)=='')
    {
	    alert("Please enter Funds Received Order No.  ");
	    return false;
    }	
    
    if((document.getElementById("amount").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }	     
    if((document.getElementById("amount").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").select();
	    return false;
    }
    if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    
    if(parseFloat(document.getElementById("amount").value) > parseFloat(document.getElementById("balanceAmt").value))
		{
			alert("Please Enter Amount Less Than Or Equal To Balance Amount");
			document.getElementById("amount").focus();
			return false;
		}   
    
    if(((document.getElementById("modeOfPayment").value) == 'null') || ( (document.getElementById("modeOfPayment").value) == '' ) )
    {
    alert("Please select Mode Of Payment");
    return false;
    }
    
    if((document.getElementById("ddNo").value)=='')
    {
	    alert("Please enter DD/Cheque No  ");
	    return false;
    }	
    
    if((document.getElementById("bankName").value)=='')
    {
	    alert("Please enter Bank Name  ");
	    return false;
    }
    
    if((document.getElementById("ddBranch").value)=='')
    {
	    alert("Please enter Bank Branch  ");
	    return false;
    }
    
	 
}
function validatePI()
{
	if(document.getElementById('name').value == '' || document.getElementById('name').value == null)
	{
		alert("Please Enter the Investigator Name");
		document.getElementById('name').focus;
		return false;
	}
	if(document.getElementById('institution').value == '' || document.getElementById('institution').value == null)
	{
		alert("Please select the Institution");
		document.getElementById('institution').focus;
		return false;
	}
	if(document.getElementById('department.id').value == '' || document.getElementById('department.id').value == null)
	{
		alert("Please select the Department");
		document.getElementById('department.id').focus;
		return false;
	}
	if(document.getElementById('email').value == '')
	{
		alert("Please Enter the email");
		document.getElementById('email').focus;
		return false;
	}
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
	return true;
}
function validateNotificationCreate()
{     
    if( ( (document.getElementById("projects").value) == 'null') || ( (document.getElementById("projects").value) == '') )
    {
	    alert("Please enter Project");
	    document.getElementById("projects").focus();
	    return false;
    }
    if( ( (document.getElementById("notificationCode").value) == 'null') || ( (document.getElementById("notificationCode").value) == '') )
    {
	    alert("Please enter notification code");
	     document.getElementById("notificationCode").focus();
	    return false;
    }
    var notificationDateYear = document.getElementById("notificationDate_year").value;
	var notificationDateMonth = document.getElementById("notificationDate_month").value;
	var notificationDateDate = document.getElementById("notificationDate_day").value;
	
	var proposalSubmissionLastDateYear = document.getElementById("proposalSubmissionLastDate_year").value;
	var proposalSubmissionLastDateMonth = document.getElementById("proposalSubmissionLastDate_month").value;
	var proposalSubmissionLastDateDate = document.getElementById("proposalSubmissionLastDate_day").value;
	
	var newnotificationDate = new Date(notificationDateYear,notificationDateMonth-1,notificationDateDate);
	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate);
	 
	if (newnotificationDate>newproposalSubmissionLastDate)
	{
		alert("Last Date for ProposalSubmission should be greater than Notification Date")
		return false;
	}
    	
	var id_value = document.getElementById('myFile').value;
 
	if(id_value != '')
	{ 
		var valid_extensions = /(.html|.htm)$/i;   
  		if(valid_extensions.test(id_value))
  		{ 
  			 return true;
	}
	else
	 {
 		alert('This File Is Not Allowed')
 		return false;
		}
	} 
	if( ( (document.getElementById("myFile").value) == 'null') || ( (document.getElementById("myFile").value) == '') )
    {
 		alert("Please upload a application form");
	    document.getElementById("myFile").focus();
	    return false;
    }
		    
	return true;
}
function validateNotificationEdit()
{ 
    var notificationDateYear = document.getElementById("notificationDate_year").value;
	var notificationDateMonth = document.getElementById("notificationDate_month").value;
	var notificationDateDate = document.getElementById("notificationDate_day").value;
	
	var proposalSubmissionLastDateYear = document.getElementById("proposalSubmissionLastDate_year").value;
	var proposalSubmissionLastDateMonth = document.getElementById("proposalSubmissionLastDate_month").value;
	var proposalSubmissionLastDateDate = document.getElementById("proposalSubmissionLastDate_day").value;
	
	var newnotificationDate = new Date(notificationDateYear,notificationDateMonth-1,notificationDateDate);
	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate);
	 
	
	if (newnotificationDate>newproposalSubmissionLastDate)
	{
		alert("Last Date for ProposalSubmission should be greater than Notification Date")
		return false;
	}
	if( ( (document.getElementById("project.id").value) == 'null') || ( (document.getElementById("project.id").value) == '') )
    {
	    alert("Please enter Project");
	    document.getElementById("project.id").focus();
	    return false;
    }
	    
	var id_value = document.getElementById('myFile').value;
 
	if(id_value != '')
	{ 
		var valid_extensions = /(.html|.htm)$/i;   
	if(valid_extensions.test(id_value))
	{ 
		 return true;
	}
	else
	 {
 		alert('This File Is Not Allowed')
 		return false;
 	}
	}
	
    
	return true;
}
function notificationEmailCheckAll()
{
	if(document.demo.All.checked==true)
	{
		for(var i=0;i<document.demo.choices.length;i++)
	{
		document.demo.choices[i].checked=true;
	}
	}
	else
	{
		for(var i=0;i<document.demo.choices.length;i++)
		{
			document.demo.choices[i].checked=false;
		}
	}
}
function validateParty()
{
	if(document.getElementById("nameOfTheInstitution").value == "")
	{
		alert("Please Enter Name");
	    document.getElementById("nameOfTheInstitution").focus();
	    return false;
	}
	if(document.getElementById("code").value == "")
	{
		alert("Please Enter Code");
	    document.getElementById("code").focus();
	    return false;
	}
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
	return true;
}
function validateProjectTracking()
{
	if(document.getElementById("percOfCompletion").value == "")
	{
		alert("Please Enter Percentage Of Completion");
	    document.getElementById("percOfCompletion").focus();
	    return false;
	}
	else if(isNaN(document.getElementById("percOfCompletion").value) )
	{
		alert("Please Enter Numeric Value For Percentage Of Completion");
	    document.getElementById("percOfCompletion").focus();
	    return false;
	}
	return true;
}
function validateProposal()
{
	if(document.getElementById("code").value == "")
	{
		alert("Please Enter the Proposal Code");
	    document.getElementById("code").focus();
	    return false;
	}
    var proposalSubmitteddateYear = document.getElementById("proposalSubmitteddate_year").value;
	var proposalSubmitteddateMonth = document.getElementById("proposalSubmitteddate_month").value;
	var proposalSubmitteddateDate = document.getElementById("proposalSubmitteddate_day").value;
    	
    var proposalSubmissionLastDate = document.getElementById("proposalSubmissionLastDate").value;
	  		
	var proposalSubmissionLastDateYear=proposalSubmissionLastDate.substring(0,proposalSubmissionLastDate.indexOf("-"));
	var proposalSubmissionLastDateMonth=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.indexOf("-")+1,proposalSubmissionLastDate.lastIndexOf("-"));
	var proposalSubmissionLastDateDate=proposalSubmissionLastDate.substring(proposalSubmissionLastDate.lastIndexOf("-")+1,proposalSubmissionLastDate.lastIndexOf(" "));
   	
   	var newproposalSubmitteddate = new Date(proposalSubmitteddateYear,proposalSubmitteddateMonth-1,proposalSubmitteddateDate);
   	var newproposalSubmissionLastDate = new Date(proposalSubmissionLastDateYear,proposalSubmissionLastDateMonth-1,proposalSubmissionLastDateDate); 
    
    if (newproposalSubmitteddate>newproposalSubmissionLastDate)
	{
		alert("Last Date for Proposal submission completed")
		return false;
	}	
    return true;
}
function fitThePage()
{
	//There is no other way than the following to identify whether inbox is open or not.

	//if(document.getElementById("inboxframe").style.height=="199px"){
		//If inbox is open, we will not do any fitting.
	//	return false;
	//}
	
	//First, get the editframe object and set its width and height to some default param.
	//This is to fit the page in worst cases when the calculated page height and width may be very small(very rare case).
	var frame = document.getElementById('editframe');
	var objToResize = (frame.style) ? frame.style : frame;
	
	var defaultH = 650;
	var defaultW = 650;
	
	objToResize.height = defaultH+'px';
	objToResize.width = defaultW+'px';
	
	//Second, get the loading page object (the page which is loading inside the editframe).
	var innerDoc = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
	
	var mcFrameH = innerDoc.body.scrollHeight;
	mcFrameH = innerDoc.body.scrollHeight;
	var mcFrameW = innerDoc.body.scrollWidth;
	
	//alert("innerDoc.body.scrollHeight:"+innerDoc.body.scrollHeight + " - innerDoc.body.scrollWidth:" + innerDoc.body.scrollWidth);
	
	//Third, set the page height and width to the editframe, after adding a small length to fit
	//the padding, if any. If in any case the page height or width is showing as zero (in some cases firefox do) 
	//then we will set back to the default editframe area.
	mcFrameH = parseInt(mcFrameH);
	mcFrameH=(mcFrameH==0 ? defaultH+50 : mcFrameH+50);
	
	objToResize.height = mcFrameH + 'px';
	
	mcFrameW = parseInt(mcFrameW);
	mcFrameW=(mcFrameW==0 ? defaultW : mcFrameW+10);
	
	objToResize.width = mcFrameW + 'px';
}
function breakOut() 
{
	if (top.frames['right'].length!=0) 
	{
    	if (window.location.href.replace)
        	top.frames['right'].location.replace(self.location.href);
        else
       		top.frames['right'].location.href=self.document.href;
	}
}
function validatePassword()
{
	if(document.getElementById("newPasswd").value == "" || document.getElementById("newPasswd").value == null )
	{
		document.getElementById("newPasswd").focus();
		alert("Please Enter New Password");
		return false;
	}
	if(document.getElementById("confirmNewPasswd").value == "" || document.getElementById("confirmNewPasswd").value == null )
	{
		document.getElementById("confirmNewPasswd").focus();
		alert("Confirm Password");
		return false;
	}
	if(document.getElementById("confirmNewPasswd").value != document.getElementById("newPasswd").value)
	{
		document.getElementById("confirmNewPasswd").focus();
		alert("Incorrect Password");
		return false;
	}
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("newPasswd").value) )
          {
             document.getElementById("newPasswd").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
	return true;
}
function validateUser()
{
	if(document.getElementById("userRealName").value == ""){
		alert("Please Enter First Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
    if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  if(document.getElementById("confirmPasswd").value == ""){
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Incorrect Password");
		return false;
	}
	 var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
	if(document.getElementById("authorities").value == "Select"){
		alert("Please Select Role");
	    document.getElementById("authorities").focus();
	    return false;
	}
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
	return true;

}
function validateEditUser()
{
	if(document.getElementById("username").value == ""){
		alert("Please Enter Login Name");
	    document.getElementById("username").focus();
	    return false;
	}
	if(document.getElementById("userRealName").value == ""){
		alert("Please Enter Full Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
    if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
	return true;

}
 function validateProjectType()
{
	if(document.getElementById("type").value == ""){
		alert("Please Enter the Project Type");
	    document.getElementById("type").focus();
	    return false;
	}
	return true;
}
function validateAuthority()
{     
    if( ( (document.getElementById("authority").value) == 'null') || ( (document.getElementById("authority").value) == '') )
    {
	    alert("Please enter the Role");
	    return false;
    }
    
   if((document.getElementById("authority").value) == 'ROLE_')
   {
      alert("Invalid Role!!!");
      return false;
      
   }
    if( (document.getElementById("authority").value) )
    {
    	var roleName= new String(document.getElementById("authority").value)
		var rolePrefix;
		rolePrefix = new String(roleName.substring(0,5));
		if(rolePrefix == 'ROLE_')
		{
			document.getElementById("authority").value = roleName.toUpperCase()
			return true;
		}
		else
		{
			alert("Incorrect Entry!!!!!!\r\r\Please Start with 'ROLE_' ");
			return false;
		}
	}     
}
function validateDepartment()
{
	if(document.getElementById("departmentCode").value == ""){
		alert("Please Enter Department Code");
	    document.getElementById("departmentCode").focus();
	    return false;
	}
	if(document.getElementById("name").value == ""){
		alert("Please Enter Department Name");
	    document.getElementById("name").focus();
	    return false;
	}
	return true;

}
function validateRegisterUser()
{
	if(document.getElementById("userRealName").value == ""){
		alert("Please Enter First Name");
	    document.getElementById("userRealName").focus();
	    return false;
	}
    if(document.getElementById("password").value == ""){
		alert("Please enter Password");
	    document.getElementById("password").focus();
	    return false;
	}
  	if(document.getElementById("confirmPasswd").value == ""){
		alert("Please enter confirm Password");
	    document.getElementById("confirmPasswd").focus();
	    return false;
	}
	if(document.getElementById("confirmPasswd").value != document.getElementById("password").value)
	{
		document.getElementById("password").focus();
		alert("Incorrect Password");
		return false;
	}
	var re = /^(?=.{6,12}$)(?=.*[A-Za-z])(?=.*[0-9])(?!.*[^A-Za-z0-9])(?!.*\s).*/;
       if ( !re.test(document.getElementById("password").value) )
          {
             document.getElementById("password").focus();
             alert('Your password must satisfy the following. \n\n* Password should be 6 to 12 character long. \n* Password should have at least one alphabet. \n* Password should have at least one numeric value. \n* Password should not have special characters.');
             return false;
          }
	var email = document.getElementById('email');
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.value != "")
	{
		if (!filter.test(email.value))
		{
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
	if(document.getElementById("code").value == ""){
		alert("Please enter Institution code");
	    document.getElementById("code").focus();
	    return false;
	}
	return true;

}
function validateAttachmentType()
{
	if(document.getElementById("type").value == "")
	{
		alert("Please Enter the Type");
	    document.getElementById("type").focus();
	    return false;
	}
	return true;
}

function validateItemPurchase()
{
	if(document.getElementById("name").value == "")
	{
		alert("Please Enter item name");
	    document.getElementById("name").focus();
	    return false;
	}
	if(document.getElementById("orderNo").value == 0)
	{
        alert("Please Enter order No");
	    document.getElementById("orderNo").focus();
	    return false;
	}
    if(document.getElementById("cost").value ==0)
    {
		alert("Please Enter cost");
	    document.getElementById("cost").focus();
	    return false;
    }
	if(document.getElementById("cost").value <= 0)
	{
		alert("Please Enter Proper cost");
	    document.getElementById("cost").focus();
	    return false;
	}
	if(isNaN(document.getElementById("cost").value))
    {
	    alert("Invalid Cost  ");
	    document.getElementById("cost").focus
	    return false;
    }
	if(document.getElementById("assetCode").value == "")
	{
		alert("Please Enter asset code");
	    document.getElementById("assetCode").focus();
	    return false;
	}
	return true;
}
            
function validateProjectEmployee()
{
	if(document.getElementById("empName").value == "")
	{
		alert("Please Enter Employee Name");
	    document.getElementById("empName").focus();
	    return false;
	}
	if(document.getElementById("empNo").value == "")
	{
		alert("Please Enter Employee Number");
	    document.getElementById("empNo").focus();
	    return false;
	}
	if( ( (document.getElementById("employeeDesignation.id").value) == 'null')  )
	{
	    alert("Please enter Designation");
	    document.getElementById("employeeDesignation.id").focus();
	    return false;
	}
	
	var employeeJoiningDateYear = document.getElementById("joiningDate_year").value;
	var employeeJoiningDateMonth = document.getElementById("joiningDate_month").value;
	var employeeJoiningDateDate = document.getElementById("joiningDate_day").value;
	
	var employeeRelievingDateYear = document.getElementById("relievingDate_year").value;
	var employeeRelievingDateMonth = document.getElementById("relievingDate_month").value;
	var employeeRelievingDateDate = document.getElementById("relievingDate_day").value;
	
	var newEmployeeJoiningDate = new Date(employeeJoiningDateYear,employeeJoiningDateMonth-1,employeeJoiningDateDate);
	var newEmployeeRelievingDate = new Date(employeeRelievingDateYear,employeeRelievingDateMonth-1,employeeRelievingDateDate);
 
	if(document.getElementById("relievingDate").value !== "")
	{
    	if (newEmployeeJoiningDate > newEmployeeRelievingDate)
		{
			alert("Joining Date should be less than Relieving Date")
			return false;
		}
	}
	
	var employeeDateOfBirthYear = document.getElementById("dOB_year").value;
	var employeeDateOfBirthMonth = document.getElementById("dOB_month").value;
	var employeeDateOfBirthDate = document.getElementById("dOB_day").value;
	
	var newEmployeeDateOfBirth = new Date(employeeDateOfBirthYear,employeeDateOfBirthMonth-1,employeeDateOfBirthDate);
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();
	var Today = new Date(curr_year,curr_month,curr_date);
	if(newEmployeeDateOfBirth > Today)
	{
			alert("Date of Birth sholud be less than Current Date")
			return false;
	}
	
    return true;
}

function validateProjectEmployeeExperience()
{
	if(document.getElementById("organizationName").value == "")
	{
		alert("Please Enter organization name");
	    document.getElementById("organizationName").focus();
	    return false;
	}
	if(document.getElementById("joiningDate").value == 'null')
	{
		alert("Please Enter joining date");
	    document.getElementById("joiningDate").focus();
	    return false;
	}
	if(document.getElementById("relievingDate").value == 'null')
	{
		alert("Please Enter relieving date");
	    document.getElementById("relievingDate").focus();
	    return false;
	}
	if(document.getElementById("designation").value == "")
	{
		alert("Please Enter designation");
	    document.getElementById("designation").focus();
	    return false;
	}
	var employeeJoiningDateYear = document.getElementById("joiningDate_year").value;
	var employeeJoiningDateMonth = document.getElementById("joiningDate_month").value;
	var employeeJoiningDateDate = document.getElementById("joiningDate_day").value;
	
	var employeeRelievingDateYear = document.getElementById("relievingDate_year").value;
	var employeeRelievingDateMonth = document.getElementById("relievingDate_month").value;
	var employeeRelievingDateDate = document.getElementById("relievingDate_day").value;
	
	var newEmployeeJoiningDate = 
			new Date(employeeJoiningDateYear,employeeJoiningDateMonth-1,employeeJoiningDateDate);
	var newEmployeeRelievingDate = 
			new Date(employeeRelievingDateYear,employeeRelievingDateMonth-1,employeeRelievingDateDate);
			    	
	if (newEmployeeJoiningDate>newEmployeeRelievingDate)
	{
		alert("Joining Date should be less than Relieving Date")
		return false;
	}
	
	return true;
}

function validateProjectEmployeeQualification()
{
	if(document.getElementById("examname").value == "")
	{
		alert("Please Enter exam name");
	    document.getElementById("examname").focus();
	    return false;
	}
	
	if(document.getElementById("university").value == "")
	{
		alert("Please Enter university");
	    document.getElementById("university").focus();
	    return false;
	}
	if(document.getElementById("passoutYear").value == 'null')
	{
		alert("Please Enter passout year");
	    document.getElementById("passoutYear").focus();
	    return false;
	}
	if(document.getElementById("percMark").value == 0)
	{
		alert("Please Enter perc mark");
	    document.getElementById("percMark").focus();
	    return false;
	}
	if(isNaN(document.getElementById("percMark").value))
    {
	    alert("Invalid Percentage");
	    document.getElementById("percMark").focus
	    return false;
    }
	return true;
}

function validateProjectEmployeeSalaryDetails()
{
	if(document.getElementById("salaryAmount").value == 0)
	{
		alert("Please Enter salary amount");
	    document.getElementById("salaryAmount").focus();
	    return false;
	}
	if(document.getElementById("salaryAmount").value <= 0)
	{
		alert("Please Enter Proper salary amount");
	    document.getElementById("salaryAmount").focus();
	    return false;
	}
	if(isNaN(document.getElementById("salaryAmount").value))
	{
	    alert("Invalid Salary Amount  ");
	    document.getElementById("salaryAmount").focus
	    return false;
    }
	if( ( (document.getElementById("salaryComponent.id").value) == 'null') || 
			( (document.getElementById("salaryComponent.id").value) == '') )
    {
	    alert("Please enter the Salary Component");
	    document.getElementById("salaryComponent.id").focus();
	    return false;
    }
	if(document.getElementById("withEffectFrom").value == 'null')
	{
		alert("Please Enter With Effect From Date");
	    document.getElementById("withEffectFrom").focus();
	    return false;
	}
	return true;
}
function validateSalaryComponent()
{
	if(document.getElementById("name").value == 'null' || document.getElementById("name").value == '' )
	{
		alert("Please Enter the Component Name");
	    document.getElementById("name").focus();
	    return false;
	}
	return true;
}
function validateEmployeeDesignation()
{
	if(document.getElementById("designation").value == 'null' || document.getElementById("designation").value == '' )
	{
		alert("Please Enter the Designation");
	    document.getElementById("designation").focus();
	    return false;
	}
	return true;
}

  function Redirect()
        {
       
         	 
         window.location="../login";
        }
function validateFundTransfer()
{
if(document.getElementById("fundTransfer.id").value == 'null' || document.getElementById("fundTransfer.id").value == '' )
	{
		alert("Please Select a Fund Transfer");
	    document.getElementById("ddNo").focus();
	    return false;
	}
	
	return true;
}

function displayAlertMessage(message) 
{
	jQuery('submit').attr('disabled', 'disabled')
	var timeOut = 2000;
	jQuery('#messageBox').text(message).fadeIn()
	jQuery('#messageBox').css({'display' : 'block', 'padding' : '10px','border': '1px solid #B0B0B0','width': '25%'})
	setTimeout(function() 
	{
	jQuery('#messageBox').fadeOut()
	jQuery('#messageBox').css("display", "none")
	}, timeOut * 1000);
}

function combineAlertAndRegister() 
{
	var retvalue;
	retvalue = validateRegisterUser();
	if(retvalue == true) { return displayAlertMessage("Please wait"); }
	return retvalue
}
function isDate(txtDate){  
  var objDate;  // date object initialized from the txtDate string  
  var mSeconds; // milliseconds from txtDate  
  
  // date length should be 10 characters - no more, no less  
  if (txtDate.length != 10) return false;  
  
  // extract day, month and year from the txtDate string  
  // expected format is mm/dd/yyyy  
  // subtraction will cast variables to integer implicitly  
  var month   = txtDate.substring(3,5)  - 1;  
  var day = txtDate.substring(0,2)  - 0; // because months in JS start with 0  
  var year  = txtDate.substring(6,10) - 0;  
  
  // third and sixth character should be /  
  if (txtDate.substring(2,3) != '/') return false;  
  if (txtDate.substring(5,6) != '/') return false;  
  
  // test year range  
  if (year < 999 || year > 3000) return false;  
  
  // convert txtDate to the milliseconds  
  mSeconds = (new Date(year, month, day)).getTime();  
  
  // initialize Date() object from calculated milliseconds  
  objDate = new Date();  
  objDate.setTime(mSeconds);  
  
  // compare input parameter date and created Date() object  
  // if difference exists then date isn't valid  
  if (objDate.getFullYear() != year)  return false;  
  if (objDate.getMonth()    != month) return false;  
  if (objDate.getDate()     != day)   return false;  
  
  // otherwise return true  
  return true;  
}
function checkDate(dateValue)
{
	Field=dateValue;
	if(isDate(dateValue.value) || dateValue.value=="")
	{
		return true;
	}
	else
	{
		alert("Please enter a date in the format dd/mm/yyyy");
		setTimeout("Field.focus();",0);
		return false;
	}
}
function validAuthority(authority)
{
    
    if( (document.getElementById("authority").value) )
    {
        
    	var roleName= new String(document.getElementById("authority").value)
		var rolePrefix;
		rolePrefix = new String(roleName.substring(0,5));
		
		if(rolePrefix != 'ROLE_')
		{
		    document.getElementById("authority").value = "ROLE_"+roleName;
			return true;
		}
	}
}

function validateEligibilityCriteria()
{
	if(document.getElementById("eligibilityCriteria").value == 'null' || document.getElementById("eligibilityCriteria").value == '' )
	{
		alert("Please Enter the Criteria Name");
	    document.getElementById("eligibilityCriteria").focus();
	    return false;
	}
	return true;
}
 
 function checkAllMembers()
    {
      
 
    	if(document.appAuthority.AllMembers.checked==true)
    	{
    		for(var i=0;i<document.appAuthority.person.length;i++)
    		{
    			document.appAuthority.person[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.appAuthority.person.length;i++)
    		{
    			document.appAuthority.person[i].checked=false;
    		}
    	}
    }
    
    
   function checkAllApprovalAuthorityDetails()
    {
      
 
    	if(document.appAuthority.AllapprovalAuthorityDetail.checked==true)
    	{
    		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
    		{
    			document.appAuthority.approvalAuthority[i].checked=true;
    		}
    	}
    	else
    	{
    		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
    		{
    			document.appAuthority.approvalAuthority[i].checked=false;
    		}
    	}
    } 
 	function validateAppAuthorityMembers()
	{ 
		var isChecked=0;
		if(document.appAuthority.person.length > 1)  
		{
			for(var i=0;i<document.appAuthority.person.length;i++)
			{
				if(document.appAuthority.person[i].checked==true)
				{
					isChecked++;
				}
				if (isChecked > 0) 
				{ 
					break; 
				}
			}
			if (isChecked <= 0) 
			{ 
				alert('You didn\'t check a person name. Please check a box.'); 
				return false;
			}
		}
		else
		{
	       if(document.appAuthority.person.checked!=true)
			{
				alert('You didn\'t check a person name. Please check a box.'); 
				return false;
			}
		}
  
  		return true;
	}
  
  function validateAppAuthorityPersonForDelete()
	{ 
	
	
	
	var isChecked=0;  
		   if(document.appAuthority.approvalAuthority.length < 0)
		   {
		   	alert("All actions are deleted")
		   }
		  else if(document.appAuthority.approvalAuthority.length > 1)  
		  {
		for(var i=0;i<document.appAuthority.approvalAuthority.length;i++)
		{	
			if(document.appAuthority.approvalAuthority[i].checked==true)
			{
				isChecked++;
			}
			if (isChecked > 0) 
			{ 
				break; 
			}
		}
		if (isChecked == 0) 
		{ 
			alert('You didn\'t an approvalAuthorityDetail  for delete. Please check a box.'); 
			return false;
		}
	}
		else
		{
			if(document.appAuthority.approvalAuthority.checked!=true)
			{
				alert('You didn\'t an approvalAuthorityDetail  for delete. Please check a box.'); 
				return false;
			}
		}
		

   return true;
		  
	 
	}
		
	