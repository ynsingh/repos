<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<script type="text/javascript" src="javascript/dropdown.js"></script>
		<script type="text/javascript">
		function msg(){
		}
</script>

<body>


<table style="border:0px solid #000066;width:100%;height:2%;" cellspacing="0px" cellpadding="0px" bgcolor=#C3D9FF>


		<tr>
	<td>

    <table cellspacing="0" cellpadding="0" ><tr>
    <td>
<dl class="dropdown">
  <dt id="one1-ddheader" >
  
  <html:link href="Menu.jsp" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"> <bean:message key="link.home"/> </html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)"><bean:message key="link.test"/> </dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
		<li><html:link styleClass="underline" href ="testSetUpjsp.jsp"> <bean:message key = "link.TestSetUp"/> </html:link>
		
		</li>
      <li><html:link styleClass="underline" action = "wrongTestOption"  ><bean:message key="link.setWrongQues"/> </html:link></li>
      <li><html:link styleClass="underline" action="correctTest" onclick="msg();"><bean:message key="link.setCorrAns"/> </html:link></li>
      	
    </ul>
  </dd>
</dl>
</td>
  


<td>
<dl class="dropdown">
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)"><bean:message key="link.pro"/> </dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="processTest"> <bean:message key="link.proSheet"/> </html:link></li>
     
   </ul>
  </dd>
</dl>
</td>

<td>
   <dl class="dropdown">
  <dt id="three-ddheader" onmouseover="ddMenu('three',1)" onmouseout="ddMenu('three',-1)"><bean:message key="link.log"/> </dt>
  <dd id="three-ddcontent" onmouseover="cancelHide('three')" onmouseout="ddMenu('three',-1)">
    <ul class="ss">
    <li><html:link styleClass="underline" action="errOption" > <bean:message key="link.errLog"/> </html:link></li>
      <li><html:link styleClass="underline" href = "ProcessLog.jsp"> <bean:message key="link.proLog"/> </html:link></li>
     
            
    </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)"><bean:message key="link.edit"/> </dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action = "editTestOption"> <bean:message key="link.testsetup"/> </html:link></li>
    </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="six-ddheader" onmouseover="ddMenu('six',1)" onmouseout="ddMenu('six',-1)"><bean:message key="link.result"/> </dt>
  <dd id="six-ddcontent" onmouseover="cancelHide('six')" onmouseout="ddMenu('six',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action = "resultOption" > <bean:message key="link.viewmarks"/> </html:link></li>
       
           </ul>
  </dd>
</dl>
</td>

    </tr></table>
   </td>
</tr>

  
</table>

</body>