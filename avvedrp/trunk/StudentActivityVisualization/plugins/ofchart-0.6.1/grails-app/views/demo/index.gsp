<%@ page import="org.codehaus.groovy.grails.plugins.ofchart.demo.DemoCharts" contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Open Flash Chart Plugin Demo</title></head>
  <ofchart:resources/>
  <g:javascript library="prototype"/>  
  <body>
    <h2>Open Flash Chart Plugin Demo</h2>    
    <g:select name="charts" from="${DemoCharts.values()}" optionValue="display"
            onChange="new Ajax.Request('/ofchart/demo/'+\$('charts').value,{asynchronous:true,evalScripts:true,onSuccess:function(e){uploadChart('demo-chart',e.responseText)}});"/>
    <br/><br/>
    <ofchart:chart name="demo-chart" url="${createLink(action:'SIMPLE_CHART')}" width="800" height="400"/>
  </body>
</html>