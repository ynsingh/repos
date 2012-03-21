<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectsSummaryReports.title" /></title>         
    </head>
    <script>
    </script>
    <body>
    	<div class="wrapper">
             <g:form action="listReport" method="post" name="projtsSumryReports" >
		         <div class="body">
		         <h1><g:message code="default.ProjectsSummaryReports.label"/></h1>
		         	<div class="dialog">
		            	<table width="75%">
             				<input type="hidden" id="party" name="party" value="${partyInstance.id}"/>
             	</g:form>
					    	<tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="InstitutionProjectsReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.ProjectsSummary.InstitutionProjectsReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="PartyID" value="${session.Party}" />
								       <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	        </g:jasperReport>
						          </td>
					           </tr>
					           <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="ReceivedProjectsReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.ProjectsSummary.ReceivedProjectsReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="PartyID" value="${session.Party}" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
							        </g:jasperReport>
						          </td>
					           </tr>
					            <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="GrantProjectsSummaryReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.ProjectsSummary.GrantSummaryReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="PartyID" value="${session.Party}" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
						  	        </g:jasperReport>
						          </td>
					           </tr>
					           <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="OverUtilizedProjectsReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.ProjectsSummary.OverUtilizedProjectsReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="PartyID" value="${session.Party}" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
						  	        </g:jasperReport>
						          </td>
					           </tr>
					      </table>  
                      	</div>   
                  	</div> 
                  </div> 
    </body>
</html>