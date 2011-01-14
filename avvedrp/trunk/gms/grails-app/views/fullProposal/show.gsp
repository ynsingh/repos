

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <title>Full Proposal</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            
        </div>
        <div class="body">
            <h1>Full Proposal</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="save" method="post"  enctype="multipart/form-data" controller="fullProposal">
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle">Project Title </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalInstance, field: 'projectTitle', 'errors')}">
                                    <strong>${preProposalInstance.projectTitle}</strong>
                                </td>
                            </tr>
                    
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name">Authority Comments</td>
                            
                            <td valign="top" class="value"><strong>${fieldValue(bean: preProposalInstance, field: "preProposalStatus")}</strong></td>
                            
                        </tr>
                        
                        <tr class="prop">
                        <td valign="top" class="name">
                            <label for="createdBy">Select File</label>
                        </td>
                        <td valign="top" class="value">
                            <input type="file" id="myFile" name="myFile"/> 
                        </td>
                    </tr> 
                    
                       <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Comments</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalInstance, field: 'modifiedBy', 'errors')}">
                                    <g:textArea  name="comments" value="${fullProposalInstance?.modifiedBy}" style='width: 200px; height:75px;'/>
                                </td>
                            </tr>
                    
                        
                    
                    </tbody>
                </table>
            </div>
         
            <div class="buttons">
              <g:form>
                    <g:hiddenField name="id" value="${preProposalInstance?.id}" />
                    <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="fullProposalSave"  value="Create" /></span>
                </div>
               </g:form>
                   </g:form>
            </div>
        </div>
        
        <div class="body">
           
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <th>SL.no</th>
                            <th>Party</th>
                            
                            <th>File</th>
                            <th>Comments </th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <% int j=0 %>
                     
                       
                    <g:each in="${fullProposalInstanceList}" status="i" var="fullProposalInstance">
                        <% j++ %>
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${j}</td>
                        
                        
                            
                        
                            <td>${fieldValue(bean: preProposalInstance, field: "party.code")}
                        
                            
                            <td><a href="${g.createLink(controller:'fullProposal', action:'download', id:preProposalInstance.id)}"><g:message code="${preProposalInstance.createdBy}" encodeAs="HTML"/></a></td>
                              
                            <td>${fieldValue(bean: fullProposalInstance, field: "modifiedBy")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
