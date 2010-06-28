<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Grant Expense</title>         
    </head>
    <script>
   
     </script>
    
    <body  onload="setValue()">
     <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="grantAllocationTrackingReports" controller="grantAllocationTracking">Grant Allocation/Project Status Reports</g:link></span>
            <span class="menuButton"><g:link class="list" action="list" controller="utilization">Utilization Certificates</g:link></span>
        </div>
        <br>
         <g:form action="listReport" method="post" name="frmreport" >
         <div class="body">
         <div class="dialog">
             <table width="75%">
                        <tbody>
                        
                        <tr>
                                <td >
                               <table>
                            <tbody>
                        
                        <tr>
                                <td valign="top" >  
                                    <label for="project"> Project :</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${projectInstanceList}" name="projects" onChange="setValue()" value="${grantAllocationInstance?.projects?.id}"></g:select>
                                      <input type="hidden" id="projectHidden" name="projectHidden" value=""/>
                                      <input type="hidden" id="party" name="party" value="${Party.find('from Party P where P.activeYesNo=\'Y\' and P.id in '+session.PartyID).id}"/>
                                </td>
                                
                                <td valign="top" >
                                    <label for="grantPeriod">Grant Period:</label>
                                                                   </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'grantPeriod','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.findAll('from GrantPeriod GP order by defaultYesNo desc')}" onChange="setValue()" name="grantPeriod" value="id" ></g:select>
                                      <input type="hidden" id="periodHidden" name="periodHidden" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                                </td>
                                
                                   
                            </tr> 
                            
                            
                            
                            </tbody>   
                             </table>
                            
                             
                           
                                 </g:form>
                                 
                                </td>
                                
                                   
                            </tr>    
                                 
                                   
                        <tr>
                                <td valign="top" class="name"><g:jasperReport jasper="HeadWiseExpendture"  name="Head Wise Expenditure" format="XLS,PDF,CSV,HTML"  >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="" />
					            <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="periodID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					            <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="ProjectWiseExpendture" format="XLS,PDF,CSV,HTML" name="Institution wise Consolidated Expenditure" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="" />
					            <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="periodID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					           
					           
					            <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="HeadWiseExpendtureNEw" format="XLS,PDF,CSV,HTML" name="Fund Utilization" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="" />
					            <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="periodID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					            </tr>
					           
					           
					            <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="GrandSummaryReport" format="XLS,PDF,CSV,HTML" name="Grand Summary Report" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					             <input type="hidden" name="partyID" value="" />
					            <input type="hidden" name="projectID" value="${session.ProjectID}" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					           
					            <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="GrandAgencyReport" format="XLS,PDF,CSV,HTML" name="Grant Agency Reports" >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="partyID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					           
					           
					              <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="InstitutionWiseGrandAgencyReport" format="XLS,PDF,CSV,HTML" name=" Institution Reports " >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="${session.ProjectID}" />
					          
					               <input type="hidden" name="partyID" value="" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					           
					               <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="ProjectAssosiate" format="XLS,PDF,CSV,HTML" name=" Project Associate Reports " >
					            <input type="hidden" name="id" value="${session.AppFormID}" />
					            <input type="hidden" name="projectID" value="${session.ProjectID}" />
					          
					               <input type="hidden" name="partyID" value=value="${session.Party}" />
					             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
				  	         </g:jasperReport>
					           </td>
					           
					           </tr>
					                                
                                </table>  
                      </div>   </div> 
                      </div> 
    </body>
</html>