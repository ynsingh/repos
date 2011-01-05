<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>home.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="home-files/styles.css" type="text/css" rel="stylesheet"/>
		<style type="text/css">A#vbUL_1g2j7a{display:none}</style>

  </head>
  
  <body>
    <div style="background-color:0000cc">	    
<table id="buton" width="25%" cellpadding="0" cellspacing="0" border="0">
	<tr><td style="padding-right:1px" title ="Evaluate" bgcolor="#0000cc">
		<a onmouseover='xpe("1g2j7o");xpshow("1g2j7",0,this);xpsmover(this);' onmouseout='xpsmout(this);' onmousedown='xpe("1g2j7c");'>
			<font size="5%" color="white"><bean:message key="link.evaluate"/></font>
		</a>

<ul id="vbUL_1g2j7" class="vbUL1g2j7" style="background-color: #0000cc">
	<li>
		<a title="Test Set Up"><font size="4"><bean:message key="link.testsetup"/> </font> </a>
			<img class="menu" src="home-files/tri.JPG" alt="»" width="15px" height="10px" align="right"/>
			<ul id="vbUL_cg2j7" class="vbUL1g2j7">
				<li><html:link href="testSetUpjsp.jsp" title="NewTestSetUp"><font size="4"><bean:message key="link.newtestsetup"/></font></html:link></li>
				<li><html:link href="manage.jsp" title="Manage Test SetUp"> <font size="4"><bean:message key="link.managetestsetup"/></font>  </html:link></li></ul></li>
				
				<li><html:link href="SelectCorrectAnsJsp.jsp" title="Upload Correct Answer"><font size="4"> <bean:message key="link.uploadcorrans"/> </font> </html:link></li>
				<li><html:link href="uploadResponse.jsp" title="Upload Response Sheet"> <font size="4"> <bean:message key="link.upressheet"/> </font> </html:link></li>
				<li><html:link href="ProcessSheet.jsp" title="Process Test"> <font size="4"><bean:message key="link.processtest"/></font> </html:link></li>
				<li><a title="Result"><font size="4"> <bean:message key="link.result"/></font>  </a>
				<img src="home-files/tri.JPG" alt="»" width="10px" height="10px" align="right"/>
			<ul id="vbUL_7g2j7" class="vbUL1g2j7">
				<li><html:link href = "SelectResult.jsp" title="View Marks"> <font size="4"> <bean:message key="link.viewmarks"/> </font> </html:link></li>
				<li><a title="Manage Result" href="testjquery.jsp"><font size="4"> <bean:message key="link.manageresult"/></font> </a></li>
				<li><html:link href="logInterface.jsp" title="Log"> <font size="4"> <bean:message key="link.log"/> </font> </html:link></li>
			
			</ul></li>
				<li><a title="Download"> <font size="4"> <bean:message key="log.download"/></font> </a>
				<img src="home-files/tri.JPG" alt="»"  width="10px" height="10px" align="right"/>
			<ul id="vbUL_qg2j7" class="vbUL1g2j7">
				<li><html:link action="/downLoadSheet" title="Processed Folder"><font size="4"><bean:message key="log.profolder"/> </font> </html:link></li>
				
			</ul></li>
			<li><a title="Mis Printed Questions"><font size="4"> <bean:message key="link.misprintedquestion"/> </font></a>
				<img src="home-files/tri.JPG" alt="»" width="10px" height="10px" align="right"/>
			<ul id="vbUL_dg2j7" class="vbUL1g2j7">
				<li><html:link href="WrongQues.jsp" title="Insert Question No."><font size="4"> <bean:message key="link.insertquesno"/> </font></html:link></li>
				<li><html:link href="manageWrongQues.jsp" title="Delete Question No."><font size="4"><bean:message key="link.delquesno"/></font></html:link></li>
			</ul></li>
</ul>
</td></tr>
</table>
</div>
<script type="text/javascript"> var vbImgPath="home-files/"</script>
<script type="text/javascript" src="home-files/styles.js"></script>
  </body>
</html:html>
