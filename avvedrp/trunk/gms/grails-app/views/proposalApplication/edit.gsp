

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ProposalApplication</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProposalApplication List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProposalApplication</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ProposalApplication</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalApplicationInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalApplicationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${proposalApplicationInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy">Created By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'createdBy','errors')}">
                                    <input type="text" id="createdBy" name="createdBy" value="${fieldValue(bean:proposalApplicationInstance,field:'createdBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdDate">Created Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'createdDate','errors')}">
                                    <g:datePicker name="createdDate" value="${proposalApplicationInstance?.createdDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Modified By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'modifiedBy','errors')}">
                                    <input type="text" id="modifiedBy" name="modifiedBy" value="${fieldValue(bean:proposalApplicationInstance,field:'modifiedBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedDate">Modified Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'modifiedDate','errors')}">
                                    <g:datePicker name="modifiedDate" value="${proposalApplicationInstance?.modifiedDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="applicationSubmitDate">Application Submit Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'applicationSubmitDate','errors')}">
                                    <g:datePicker name="applicationSubmitDate" value="${proposalApplicationInstance?.applicationSubmitDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposal">Proposal:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'proposal','errors')}">
                                    <g:select optionKey="id" from="${Proposal.list()}" name="proposal.id" value="${proposalApplicationInstance?.proposal?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
