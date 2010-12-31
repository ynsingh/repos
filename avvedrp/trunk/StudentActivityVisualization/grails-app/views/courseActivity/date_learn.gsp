<%@ page import="org.codehaus.groovy.grails.plugins.ofchart.demo.DemoCharts" contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Student Visualization Charts</title></head>
<table width="50%">
	                    
	                        <thead>
	                            <tr>
					 <th>SlNo</th>
	                                 <th>User Name</th>              	        
	                       	        <th>Event Count</th>
	                                 
	                            
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <g:each in="${lst}" status="i" var="siteResourceInstance">
	                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            
	                                <td>${i+1}</td>
					<td>${crs[i]}</td>
	                                 <td>${lst[i]}</td>
            
	                             </tr>
	                        </g:each>
	                        </tbody>
	                    </table>
	                    <br>
  <ofchart:resources/>
  <g:javascript library="prototype"/> 
  <body>
    <center> 
    	<ofchart:resources/>
  	<g:javascript library="prototype"/> 
	<ofchart:chart name="demo-chart" url="${createLink(action:'DATE_CHART',id:params.eventId)}" width="600" height="400"/>
  </center>
<br>
 </body>

</html>
















