


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <g:set var="entityName" value="${message(code: 'approvalAuthority.label', default: 'ApprovalAuthority')}" />
        <title><g:message code="default.CreateApprovalAuthority.label" args="[entityName]" /></title>
    </head>
    <body>
    
        <div class ="wrapper">
        <div class="body">
            <h1><g:message code="default.CreateApprovalAuthority.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${approvalAuthorityInstance}">
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                                <tr class="prop">
                                    <td valign="top" class="name">
                                       <label for="name"><g:message code ="default.Name.label" />:</label>
                                       <label for="name" style="color:red;font-weight:bold"> * </label>
                                    </td>
                                    <td valign="top" class="value ${hasErrors(bean: approvalAutorityInstance,field: 'name','errors')}">
                                      <g:textField name="name" value="${approvalAuthorityInstance?.name}" />
                                    </td>
                                    
                                    <td valign="top" class="name">
                                    <label for="approveMandatory"><g:message code="default.approveMandatory.label" />:</label>
                                	</td>
                                	<td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'approveMandatory', 'errors')}">
                                    <g:select  name="approveMandatory" optionValue="key" optionKey="value" from ="${['Yes':'Y', 'No':'N']}"  value="${fieldValue(bean: approvalAuthorityInstance, field: 'approveMandatory')}" />
                                	</td>   
                              </tr>
                              <tr class= "prop">
                                 <td valign="top" class="name">
                                     <label for="party"><g:message code ="default.Institution.label" />:</label>
                                 </td>
                                 <td valign="top" class="value ${hasErrors(bean:approvalAuthorityInstance, field:'party', 'errors')}">
                                     <strong>  ${partyInstance.code} </strong>
                                     <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:partyInstance,field:'id')}"/>
                                 </td>           
                            
                             	 <td valign="top" class="name">
                                    <label for="viewAll"><g:message code="default.viewAll.label" />:</label>
                                 </td>
                                 <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'viewAll', 'errors')}">
                                    <g:select name="viewAll" optionValue="key" optionKey="value" from ="${['Yes':'Y', 'No':'N']}" value="${fieldValue(bean: approvalAuthorityInstance, field: 'viewAll')}" />
                                 </td>
                            </tr>
                            <input type="hidden" name="activeYesNo" id="activeYesNo" value='Y'/>
                            <input type="hidden" name="approveLevel" id="approveLevel" />
                           <tr class= "prop">
                              <td valign="top" class="name">
                                 <label for="approveAll"><g:message code="default.approveAll.label" />:</label>
                              </td>
                              <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'approveAll', 'errors')}">
                                <g:select name="approveAll" optionValue="key" optionKey="value" from ="${['Yes':'Y', 'No':'N']}"  value="${fieldValue(bean: approvalAuthorityInstance, field: 'approveAll')}" />
                              </td>
                              <td valign="top" class="name">
                                 <label for="email"><g:message code="default.Email.label" />:</label>
                              </td>
                              <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'email', 'errors')}">
                                 <g:textField name="email" value="${approvalAuthorityInstance?.email}" />
                              </td>
                           </tr>
                          <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="viewAll"><g:message code="default.DefaultAuthority.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'defaultYesNo', 'errors')}">
                                    <g:select name="defaultYesNo" optionValue="key" optionKey="value" from ="${['Yes':'Y', 'No':'N']}" value="${fieldValue(bean: approvalAuthorityInstance, field: 'defaultYesNo')}" />
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.Remarks.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'remarks', 'errors')}">
                                <g:textArea name="remarks" value="${approvalAuthorityInstance?.remarks}" style='width: 200px; height:75px;'/>
                               </td>
                           </tr> 
                      </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateApprovalAuthority()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
         <h1><g:message code="default.ApprovalAuthorityList.label" args="[entityName]" /></h1>

         <div class="list">
            <g:if test="${approvalAuthorityList}">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'default.Email.label')}" />

                            <g:sortableColumn property="approveMandatory" title="${message(code: 'default.approveMandatory.label')}" />
                        
                            <g:sortableColumn property="approveAll" title="${message(code: 'default.approveAll.label')}" />
                            
                             <g:sortableColumn property="approveAll" title="${message(code: 'default.viewAll.label')}" />
                           
                            <g:sortableColumn property="defaultYesNo" title="${message(code: 'default.DefaultAuthority.label')}" />
                              
                            <th><g:message code="default.ApprovalAuthority.AddMembers.label"/></th>
                            
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                             
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${approvalAuthorityList}" status="i" var="approvalAuthorityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                         <td>${i+1}</td>
                          
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "email")}</td>
                           
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "approveMandatory")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "approveAll")}</td>
                            
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "viewAll")}</td>
                           
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "defaultYesNo")}</td>
                            
                            <td><g:link controller ="approvalAuthorityDetail" action="create" id="${approvalAuthorityInstance.id}"><g:message code="default.ApprovalAuthority.AddMembers.label"/></g:link></td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:approvalAuthorityInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
            </div>
           </div>
        </div>
    </body>
</html>
