
                
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'menuRoleMap.label', default: 'MenuRoleMap')}" />
        <title><g:message code="default.addMenu.label" args="[entityName]" /></title>
    </head>
    <body>
     <g:subMenuList/>
    	<div class="wrapper">
          <div class="body">
       			<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.projectsPIMap.AddProjectsToPI.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsPIMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsPIMapInstance}" as="list" />
            </div>
            </g:hasErrors>
	            <g:form action="save" method="post" name="copiSelection">
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                            <tr class="prop">
                                  <tr class="prop">
                                <td width="20%" height="34" >
                                    <label for="approvalAuthority"><g:message code="default.Projects.label" />:</label>
                                </td>
                                <td align="left" class="value ${hasErrors(bean: projectsPIMapInstance, field: 'projects', 'errors')}">
                                    ${fieldValue(bean: projectsInstance,field: "code")}
                                  <input type="hidden" name="id" value="${projectsInstance.id}">
                                </td>
                       </tr>          
	                        </tbody>
	                    </table>
	                      <div id="listCoPi">
						 	<table width="46%" height="195" border="0">
					  			<tr>
					    			<td width="35%">
					    				<div class="list">
											<table>							
												<tr>
													<th width="5%"><g:checkBox name="All" onclick="checkAllBox(document.copiSelection.All,document.copiSelection.copiselt)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
													<th width="50%"><g:message code="default.unAssignCoPI.label"/></th>
													
												</tr>
											</table>
											<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
					  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
					  									<g:each in="${investigatormapList}" status="i" var="investigatormapList">
															<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
																<td>
																<g:checkBox name="copiselt" id="copi" value="${investigatormapList.id}" checked="false"/>
															</td>
															<td>
																${investigatormapList.fullName}
															</td>
												            </tr>
														</g:each>
												</table>
											</div>
					      				</td>
					      				<td width="10%">
					      					<table>
					      						<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
				            	    				<g:submitToRemote class="addbutton" action="add" value="	    	     " update="[success:'listCoPi',failure:'error']" before="if(!validateCoPi(document.copiSelection.copiselt,'You did not check a box. Please check a box.')) return false" />
	            								    <g:submitToRemote class="removebutton"  action="remove" value="           " update="[success:'listCoPi',failure:'error']" before="if(!validateCoPi(document.copiSelection.copidisselt,'You did not check a box. Please check a box.')) return false"/>
					               				</td></tr></table>
					                		</td>
					                	</td><td width="35%">
						      				<div class="list">
						    					<table>
													<tr>
														<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.copiSelection.AllDel,document.copiSelection['copidisselt'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
														<th width="50%"><g:message code="default.assignCopi.label"/></th>
														
													</tr>
												</table>
												<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
													   <g:each in="${copiList}" status="i" var="copiList">
															<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
																<td>
																	<g:checkBox name="copidisselt" id="copi" value="${copiList.id}" checked="false"/>
																</td>
																<td>
																	${copiList.investigator.fullName}
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
						</div>
				</g:form>
	        </div>
        </div>
    </body>
</html>
