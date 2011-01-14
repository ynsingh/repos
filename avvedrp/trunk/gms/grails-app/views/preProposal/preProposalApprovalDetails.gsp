

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Approved PreProposal List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
       </div>
        <div class="body">
            <h1>Approved PreProposal List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                <input type="hidden" name="id" value="${preProposalInstance.id}" />
                    <thead>
                        <tr>
                        
                            <tr>
                        
                            <g:sortableColumn property="id" title="SLNo" />
                        
                             <th>Institution</th>
                             <th>Department</th>
                             <th>Project Title</th>
                             <th>Investigator</th>
                             
                            <th>Submit Full Proposal </th>
                        
                            </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalInstanceList}" status="i" var="preProposalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${preProposalInstance.id}">${fieldValue(bean: preProposalInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: preProposalInstance, field: "institution.name")}
                        
                            <td>${fieldValue(bean: preProposalInstance, field: "department.departmentCode")}</td>
                            <td>${fieldValue(bean: preProposalInstance, field: "projectTitle")}</td>
                            <td>${fieldValue(bean: preProposalInstance, field: "faculty.name")}</td>
                            
                            <td><g:link action="show" controller = "fullProposal" id="${fieldValue(bean: preProposalInstance, field: "id")}" params="${[id:preProposalInstance.id]}">
                               Submit Full Proposal</g:link></td>
                        
                           
                        
                        </tr>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
