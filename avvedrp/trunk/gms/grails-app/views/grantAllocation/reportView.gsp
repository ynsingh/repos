

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Statement of Accounts Report</title>         
    </head>
    <script language="Javascript">
    function confirmPrint()
    {
    var answer = confirm("Do you want to view report?")
	if (answer){
		document.viewStatementOfAccounts.target="_blank";
		return true;
	}
	else{
		return false;
	} 
	document.viewStatementOfAccounts.target="_blank";
	return true;
    }
    </script>
    <body>
     <div class="wrapper"> 
     
        <div class="nav">
         <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
          <span class="menuButton"><a class="list"  href="../grantAllocation/projectDash/${session.ProjectID}">Project List</a></span>   
        </div>
        
        
        <div class="proptable">
      
   </div>
<table class="tablewrapper" cellspacing="0" cellpadding="0">
  <tr>
    <td>
        <div class="body">
            <h1>Statement of Accounts Report</h1>
            <g:hasErrors bean="${grantAllocationTrackingInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationTrackingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="showReports" name="viewStatementOfAccounts" controller="grantAllocation">
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr class="prop">
                                <td valign="top" class="name">
                                   <label for="project"> Project :</label>
                                </td>
                                <td valign="top">
                                     ${(Projects.get(session.ProjectID)).code}
                                </td>
                            </tr> 
                                             	
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantPeriod">Grant Period:</label>
                                </td>
                                <td valign="top">
                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.findAll('from GrantPeriod GP order by defaultYesNo desc')}"  name="grantPeriod"></g:select>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div>
             	<g:actionSubmit class="inputbutton" value="Statement of Accounts" action="showReports" target="_blank"  onclick="return confirmPrint();"/>
             	<g:actionSubmit class="inputbutton" value="Utilization Certificate" action="utilizationCertificate" target="_blank"  onclick="return confirmPrint();"/>
                </div>
            </g:form>
        </div>
         </td>
                            </tr> 
                        
                       
                    </table>
         </div>
    </body>
</html>
