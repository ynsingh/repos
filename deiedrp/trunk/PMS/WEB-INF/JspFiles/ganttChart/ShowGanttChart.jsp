<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
  <head>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<link rel="stylesheet" href="style/jsgantt.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/jsgantt.js"></script>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!-- for refresh this page automatically at every 3 seconds
	<META HTTP-EQUIV="Refresh" CONTENT="3">-->
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is gantt chart page">
  </head>
  
   <body>
   <div style="position:relative" class="gantt" id="GanttChartDIV"></div>
	<script>
  	var g = new JSGantt.GanttChart('g',document.getElementById('GanttChartDIV'), 'day');
	g.setShowRes(1); // Show/Hide Responsible (0/1)
	g.setShowDur(1); // Show/Hide Duration (0/1)
	g.setShowComp(1); // Show/Hide % Complete(0/1)
   g.setCaptionType('Resource');  // Set to Show Caption (None,Caption,Resource,Duration,Complete)
  if( g ) {
    // Parameters             (pID, pName,                  pStart,      pEnd,        pColor,   pLink,          pMile, pRes,  pComp, pGroup, pParent, pOpen)
	// use the XML file parser 
	
	//JSGantt.parseXML('D:/Struts/MyPMS2.1/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/PMS/GChart.xml',g)
	JSGantt.parseXML('GChart.xml',g);
	 g.Draw();	
	 g.DrawDependencies();
	 }
  else
  {
    alert("not defined");
  }
	</script>

 </body>
</html>
