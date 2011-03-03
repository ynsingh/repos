

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
        <title><g:message code="default.PreProposalList.label"  /></title>
    </head>
    <body>
       
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.PreProposalList.label"  /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                    
                        <tr>
                        	<g:sortableColumn property="id" title="${message(code: 'default.SINo.label', default: 'Id')}" />
                            
                            <th><g:message code="default.Institution.label"  /></th>
                            
                        
                            <g:sortableColumn property="projectTitle" title="${message(code: 'default.ProposalTitle.label')}" />
                        
                            
                        
                            <th><g:message code="default.Investigator.label"  /></th>
                        
                            <g:sortableColumn property="preProposalStatus" title="${message(code: 'default.PreProposalStatus.label')}" />
                            
                            
                            
                            <th><g:message code="default.Edit.label"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalInstanceList}" status="i" var="preProposalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${i+1}</td>
                             <td>${fieldValue(bean: preProposalInstance, field: "party.code")}</td>
                             
                             
                             
                            <td>${fieldValue(bean: preProposalInstance, field: "projectTitle")}</td>
                        
                            <td>${fieldValue(bean: preProposalInstance, field: "person.username")}</td>
                        
                            
                            
                            <g:if test ="${preProposalInstance.preProposalStatus == 'Submitted'}">
                              
                            
                            <td><g:link action="preProposalReviewalStatus" controller = "preProposal" id="${fieldValue(bean:preProposalInstance, field:'id')}"><g:message code="default.Reviewing.label"/></g:link></td>
                            </g:if>
                        <g:else>
                           <g:if test = "${preProposalInstance.preProposalStatus =='Approved'}">
                           <td><g:link action="preProposalReviewalStatus" controller = "preProposal" id="${fieldValue(bean:preProposalInstance, field:'id')}" >${preProposalInstance.preProposalStatus}</g:link></td>
                           </g:if>
                           <g:else>
                           <g:if test = "${preProposalInstance.preProposalStatus =='NeedMoreInfo'}">
                           <td><g:link action="preProposalReviewalStatus" controller = "preProposal" id="${fieldValue(bean:preProposalInstance, field:'id')}" >${preProposalInstance.preProposalStatus}</g:link></td>
                           </g:if>
                          
                           <g:else>
                           <td>${preProposalInstance.preProposalStatus}</td>
                           </g:else>
                           </g:else>
                           </g:else>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:preProposalInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
