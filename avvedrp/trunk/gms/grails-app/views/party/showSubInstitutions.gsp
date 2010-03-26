

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Sub Institution </title>         
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
             <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Institution List</g:link></span>
        </div>
        
        <table class="tablewrapper">
        <tr>
        <td>
          <div class="body">
            <h1>Sub Institution List of ${fieldValue(bean:partyInstance,field:'parent.nameOfTheInstitution')} </h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                          
                              <g:sortableColumn property="nameOfTheInstitution" title="Name" />
                            <g:sortableColumn property="code" title="Code" />
                          
                            <g:sortableColumn property="address" title="Address" />  
                            <g:sortableColumn property="phone" title="Phone" />
                            <g:sortableColumn property="email" title="Email" />
                            	      
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyInstanceList}" status="i" var="partyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="edit" id="${fieldValue(bean:partyInstance, field:'id')}">${(i + 1)}</g:link></td>
                        
                               
                            <td>${fieldValue(bean:partyInstance, field:'nameOfTheInstitution')}</td>
                                          
                            <td>${fieldValue(bean:partyInstance, field:'code')}</td>
                           
                            <td>${fieldValue(bean:partyInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'phone')}</td>
                            
                            <td>${fieldValue(bean:partyInstance, field:'email')}</td>
                                                     
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           </td>
           </tr> 
           <tr>
           <td>
        </div>
        <div class="body">
            <h1>Create Sub Institution</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table width="950" cellpading="2" cellspacing="2">
                        <tbody>
                        
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parent">Main Institution:</label>
                                </td>
                                   
                            <td width="150" valign="top" class="value ${hasErrors(bean:partyInstance,field:'parent','errors')}">
                                     ${fieldValue(bean:partyInstance,field:'parent.nameOfTheInstitution')}
                             </td>
            				 <td valign="top" class="name">
                                    <label for="parent">Main Institution Code:</label>
                                </td>
                                 <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'parent','errors')}">
                                     ${fieldValue(bean:partyInstance,field:'parent.code')}
                                        <g:hiddenField id="parentid" name="parent.id" value="${fieldValue(bean:partyInstance, field:'parent.id')}"/>
                        
                                </td>
                                <td width="20%">
                                </td>
                             
                            </tr> 
                            
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfTheInstitution">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'nameOfTheInstitution','errors')}">
                                    <input type="text" size="45" id="nameOfTheInstitution" name="nameOfTheInstitution" value="${fieldValue(bean:partyInstance,field:'nameOfTheInstitution')}"/>
                                </td>
                               <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:partyInstance,field:'code')}"/>
                                </td>
                                  <td width="20%">
                                </td>
                             
                            </tr> 
                        
                          
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'address','errors')}">
                                   <g:textArea name="address" value="${fieldValue(bean:partyInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                                <td valign="top" class="name">
                                    <label for="phone">Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:partyInstance,field:'phone')}"/>
                                </td>
                                  <td width="20%">
                                </td>
                             
                           </tr> 
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:partyInstance,field:'email')}"/>
                                </td>
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:partyInstance,field:'activeYesNo')}" />
                                </td>
                                  <td width="20%">
                                </td>
                             
                            </tr> 
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
        </td>
        <tr>
        </table>
         </div>
        
    </body>
</html>
