

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposal.label', default: 'FullProposal')}" />
        <title><g:message code="default.FullProposalList.label" /></title>
    </head>
    <body>
        
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.FullProposalList.label" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                            <th><g:message code="default.Institution.label" /></th>
                              <th><g:message code="default.Users.label" /></th>  
                              <th><g:message code="default.ProjectTitle.label" /></th>   
                                <th><g:message code="default.FullProposalStatus.label"/></th>             
                            <th><g:message code="default.SubmitFullProposal.label" /></th>
                        
                            
                        
                            
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalList}" status="i" var="fullProposalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${i+1}</td>
                        
                             <td>${fieldValue(bean: fullProposalInstance, field: "party.code")} 
                                 <td>${fieldValue(bean: fullProposalInstance, field: "person.username")} 
                                 <td>${fieldValue(bean: fullProposalInstance, field: "projectTitle")}</td>                
                      
                              <g:if test="${chkFullProposalInstance}">
                              
                              <g:if test = "${fullProposalInstance.proposalStatus =='Submitted'}">
                              
                           
                            <td><g:link action="fullProposalReviewalStatus" controller = "fullProposal" id="${fieldValue(bean:fullProposalInstance, field:'id')}" params="${[id:fullProposalInstance.id]}"><g:message code="default.Reviewing.label" /></g:link></td>
                           </g:if>
                           <g:else>
                           <g:if test = "${fullProposalInstance.proposalStatus =='Approved'}">
                           <td><g:link action="fullProposalReviewalStatus" controller = "fullProposal" id="${fieldValue(bean:fullProposalInstance, field:'id')}" params="${[id:fullProposalInstance.id]}">${fullProposalInstance.proposalStatus}</g:link></td>
                           </g:if>
                           <g:else>
                           <g:if test = "${fullProposalInstance.proposalStatus =='NeedMoreInfo'}">
                           <td><g:link action="fullProposalReviewalStatus" controller = "fullProposal" id="${fieldValue(bean:fullProposalInstance, field:'id')}" params="${[id:fullProposalInstance.id]}">${fullProposalInstance.proposalStatus}</g:link></td>
                           </g:if>
                          
                           <g:else>
                           <td>${fullProposalInstance.proposalStatus}</td>
                           </g:else>
                           </g:else>
                           </g:else>
                           </g:if>
                           <g:else>
                           
                           <td><g:message code="default.NoFullProposal.label"/></td>
                           </g:else>
                           
                           
                           
                           
                        <td><g:link action="create" controller = "fullProposal" id="${fieldValue(bean: fullProposalInstance, field: "id")}" params="${[id:fullProposalInstance.id]}">
                               <g:message code="default.SubmitFullProposal.label" /></g:link></td>
                            
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
