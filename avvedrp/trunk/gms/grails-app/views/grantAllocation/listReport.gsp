<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>report list</title>         
    </head>
    <body>
     
        <div class="nav">
        
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Grant Allocation List</g:link></span>
        </div>
         <h1>report list</h1>  
        <table>
                        <tbody>
                        
                        <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="HeadWiseExpendture" format="XLS,PDF,CSV,HTML" name="Head Wise Expenditure" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="${reportListInstance.projects.id}" />
					            <input type="hidden" name="partyID" value="${reportListInstance.party.id}" />
					            <input type="hidden" name="periodID" value="${reportListInstance.grantPeriod.id}" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					           
					            <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="ProjectWiseExpendture.jasper" format="XLS,PDF,CSV,HTML" name="Head Wise Expenditure" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="${reportListInstance.projects.id}" />
					            <input type="hidden" name="partyID" value="${reportListInstance.party.id}" />
					            <input type="hidden" name="periodID" value="${reportListInstance.grantPeriod.id}" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					                                
                                </table>  
                                
                                
        
            </body>
</html>
        