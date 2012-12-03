<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProposalDetailsReport.title"/></title>  
        <script>
		</script>       
    </head>
    
    <body>
	    <div class="wrapper">
		    <g:form action="listReport" method="post" name="notificationProposals" >
		    	<div class="body">
		    	<h1><g:message code="default.ProposalDetailsReport.label"/></h1>
		    	
			    	<div class="dialog">
			    		<table width="75%">
	                        <tbody>
                        		<tr>
                            		<td >
                               			<table>
                            				<tbody>
                        						<tr>
					                                <td valign="top" >  
					                                    <label for="Notification"> 
					                                    	<g:message code="default.Notification.label"/>
				                                    	</label>
					                                 </td>
					      					         <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'id','errors')}">
                                    					<g:select optionKey="id" optionValue="notificationTitle"  name="notification" from="${notificationList}" onChange="setValue()" value="${notificationInstance?.id}" noSelection="['null':'-Select-']" ></g:select>
                                 					 </td>
					                            </tr> 
				                             </tbody>   
                         				 </table>
                        			 </g:form>
                                 </td>
                        	</tr>    
                            <tr>
                                <td valign="top" class="name">
                                	<g:jasperReport jasper="ProposalDetails"  
	                                	name="${message(code: 'default.ProposalDetailsReport.label')}" 
	                                	format="XLS,PDF,CSV,HTML"  >
							            <input type="hidden" name="id" value="${session.AppFormID}" />
							            <input type="hidden" name="notificationID" value="" />
							            <input type="hidden" name="partyID" value="${session.Party}" />
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