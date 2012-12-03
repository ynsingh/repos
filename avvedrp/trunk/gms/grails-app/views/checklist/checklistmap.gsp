


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'checklist.label', default: 'Checklist')}" />
        <title><g:message code="default.checklistmap.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper">
         <div class="body">
         <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.checklistmap.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${checklistInstance}">
            <div class="errors">
                <g:renderErrors bean="${checklistInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" name="chklist">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                             <tr class="prop">
                             
                               
                                <td valign="top" class="name">
                                <label for="controllerName"><g:message code="default.Projects.label"/>:</label>
                                <label for="controllerName" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td>
                                <g:select from="${grantAllocationInstance}" name="projects.id" id="projects.id" optionValue="code" optionKey="id"
                                onchange="${remoteFunction(controller:'checklist',action:'getChecklistName',update:'checkNameSel',params:'\'projects=\'+this.value')};"
                                value="${checklistMapInstance?.projects?.id}" noSelection="['null':'-Select-']"></g:select>
                                </td> 
                                 <td valign="top" class="name">
                                    <label for="field"><g:message code="default.Compulsory.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checklistMapInstance, field: 'field', 'errors')}">
                                  <g:select name="compulsory" from="${['Y', 'N']}"  value="${fieldValue(bean:checklistMapInstance,field:'compulsory')}" />
                                </td>
                                
                              
                            </tr> 
                            
                        </tbody>
                    </table>
                    
                    	<div id="checkNameSel">
						 	<table width="46%" height="195" border="0">
					  			<tr>
					    			<td width="35%">
					    				<div class="list">
											<table>							
												<tr>
													<th width="5%"><g:checkBox name="All" onclick="checkAllBox(document.chklist.All,document.chklist.chkselt)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
													<th width="50%"><g:message code="default.unAssignchecklist.label"/></th>
													
												</tr>
											</table>
											<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
					  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
					  									<g:each in="${checkList}" status="i" var="checkList">
															<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
																<td>
																<g:checkBox name="chkselt" id="check" value="${checkList.id}" checked="false"/>
															</td>
															<td>
																${checkList.field}
															</td>
												            </tr>
														</g:each>
												</table>
											</div>
					      				</td>
					      				<td width="10%">
					      					<table>																											
					      						<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>   								 
				            	    				<g:submitToRemote class="addbutton" action="add" value="	    	     " update="[success:'checkNameSel',failure:'error']" before="if(!validateCheckList(document.chklist['projects.id'],document.chklist['chkselt'],'You did not check a box. Please check a box.')) return false" />
	            								    <g:submitToRemote class="removebutton"  action="remove" value="           " update="[success:'checkNameSel',failure:'error']" before="if(!validateCheckList(document.chklist['projects.id'],document.chklist.chkdisselt,'You did not check a box. Please check a box.')) return false"/>
					               				</td></tr></table>
					                		</td>
					                	</td><td width="35%">
						      				<div class="list">
						    					<table>
													<tr>
														<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.chklist.AllDel,document.chklist['chkdisselt'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
														<th width="50%"><g:message code="default.assignChecklist.label"/></th>
														
													</tr>
												</table>
												<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
													   <g:each in="${mapcheckList}" status="i" var="mapcheckList">
															<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
																<td>
																	<g:checkBox name="chkdisselt" id="check" value="${mapcheckList.id}" checked="false"/>
																</td>
																<td>
																	${mapcheckList.checklist.field}
																</td>
															</tr>
														</g:each>
													</table>
											   </div>
										 </div>	
								   </td>
					  		  </tr>
						 </table>
				     </div>
               </g:form>
           </div>
	   </div>
    </body>
</html>
