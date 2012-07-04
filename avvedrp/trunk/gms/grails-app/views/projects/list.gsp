<html>
    <head>
	   	<title><g:message code="default.projects.list.head"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	    <meta name="layout" content="main" />
	    <g:javascript library="jquery"/>
	  	<g:javascript library="prototype" />
	    <g:javascript library="applicationValidation" />
	    <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
	    <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>
	   	<script type="text/javascript">
			  $.noConflict();
			  jQuery(document).ready(function($){${remoteFunction(action:'getalert', controller:'projects',onSuccess:'returnResult(e)')};});
	    </script>
	    <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
    </head>
    <body>
   	
    <div id="paginate">
    <div class="wrapper"> 
       
        <div class="body">
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
           <h1><g:message code="default.projects.list.head"/></h1>        
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${grantAllocationWithprojectsInstanceList}">
            <div class="list">
            <div class="paginateButtons" style="border: 0px;height:14px;text-align:right" ><util:remotePaginate controller="projects" action="list" total="${grantAllocationWithprojectsTotal}" update="paginate"  prev="Prev" pageSizes="[5,10,20,40,50]"/><font style="font:8pt verdana;font-weight:bold;color:black">Total:${grantAllocationWithprojectsTotal}</font></div>
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	              
                       		<%-- <g:sortableColumn property="parent" title="Main Project" /> --%>
                                 
                       
                   	        <g:sortableColumn property="projects.name" title="${message(code: 'default.Name.label')}"/>
                                              
                   	        <g:sortableColumn property="granter.code" title="${message(code: 'default.Grantor.label')}"/>
                   	        
                   	        <th><g:message code="default.Investigator.label"/></th>
                   	          
                	        <th align="center"><img src="/gms/images/attach1.png"/></th>
                   	       		<th><g:message code="default.Status.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <% int j=offset %>
                    <g:each in="${grantAllocationWithprojectsInstanceList}" status="i" var="grantAllocationInstance">
                   
                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
	                        <%  j++ %>
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                       
	                        
	                           <td>${j}</td>
	                            
	                                 <%-- <td>${fieldValue(bean:grantAllocationInstance, field:'projects.parent.code')}</td>--%>
	                      <%def projectTrackingInstanceCheck=Projects.findAll("from Projects P where P.parent.id= '"+grantAllocationInstance.projects.id+"'")%>
	             		<g:if test="${projectTrackingInstanceCheck}">
	             	      <td>
	                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
	                         		<g:if test="${(grantAllocationInstance.projects.status == 'Closed')}">   
	                       				<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}" params="[projectStatus:'Closed']">
		                         			<p title="Having Sub-Projects with names${projectTrackingInstanceCheck.name}">${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</p>
		                         		</g:link>
		                       		</g:if>
		                       		<g:else>
		                         		<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}">
		                         			<p title="Having Sub-Projects with names${projectTrackingInstanceCheck.name}">${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</pr>
		                         		</g:link>
		                         	</g:else>	
	                         	 </g:if>
	                        </td>
	                 	 </g:if>
	                   <g:else>
	                  		<td>
	                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
	                         		<g:if test="${(grantAllocationInstance.projects.status == 'Closed')}">   
	                       				<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}" params="[projectStatus:'Closed']">
		                         			${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}
		                         		</g:link>
		                       		</g:if>
		                       		<g:else>
		                         		<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}">
		                         			${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}
		                         		</g:link>
		                         	</g:else>	
	                         	 </g:if>
	                        </td>         	
	                           	
	                  </g:else>         	
	                           	
	                           	
	                           	<g:if test="${grantAllocationInstance.granter}">
	                           		<td><p title="Name of the Institution - ${grantAllocationInstance.granter.nameOfTheInstitution}">${fieldValue(bean:grantAllocationInstance, field:'granter.code')}</td>
	                           	</g:if>
	                           	<g:else>
	                          		<td><p title="Name of the Institution - ${grantAllocationInstance.party.nameOfTheInstitution}">${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
	                           </g:else>
	                           <td><g:if test="${pIMapList[i]!=null}">
	                         
	                           ${pIMapList[i].investigator.name}&nbsp;${pIMapList[i].investigator.userSurName}
	                           </g:if>
	                           </td>                           
	                           <td><g:if test="${Attachments.findByDomainId(grantAllocationInstance.projects.id)}">
	                           <g:link action="list" controller="attachments" id="${grantAllocationInstance.projects.id}">
	                           <img src="/gms/images/attach1.png"/></g:link>
	                           </g:if>
	                           <g:else>
	                          
	                           </g:else>
	                           </td>
	                        	<td>
		                        	<g:if test="${(grantAllocationInstance.projects.status == 'Closed')}">   
		                       			<g:message code="default.Closed.label"/>
		                       		</g:if>
		                       		<g:else>
	                          		 <g:message code="default.Active.label"/>
	                           </g:else>
	                       		</td>
	                        </tr>
	                        
	                       </g:if>
	                      
                    </g:each>
                    </tbody>
                </table>
                </div>
            </div>
            </g:if>
            <g:else>
            <br><g:message code="default.NoRecordsAvailable.label"/>
            </g:else>
          </div>  
        </div>
        
    </body>
</html>
