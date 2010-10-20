<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html> 
	<head>
	 <link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false,
		autoHeight: false
		});
	});
});
</script>
	<script type="text/javascript">
	function seeOrganisation() {
   var name = DWRUtil.getValue("iname");
   var info="org";
   DynamicList.seeExistence(name,info,function(data)
  {
  	DWRUtil.setValue("orgname",data);
  }
  ); 
 }
 function resetstate(){
 DWRUtil.setValue("istate","--Select--");
 }
 
 function cityGenerate() {
   	var stateName = DWRUtil.getValue("istate");
   	 	
  DynamicList.cityList(stateName,function(data)
  {
  	DWRUtil.removeAllOptions(document.getElementsByName("icity")[0]);
  	DWRUtil.addOptions(document.getElementsByName("icity")[0],data);
  	}
  ); 
 }
 </script>
 </head>
		
	<body onload="resetstate();">
<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.addOrganization"/> -</a></h3>
	<div>
		<html:javascript formName="orgform" />
		<html:form action="saveorganization" onsubmit="return validateOrgform(this);">
	  	   <br>
	  	   <div align="center">
		  <html:errors property="iname"/>
		  <html:errors property="iurl"/>
		  <html:errors property="orgmsg"/>
		  </div>
		  <br>
		  		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
			<bean:message key="label.orgName"/> : </td>
			<td class="form-widget"><html:text property="iname" indexed="iname" size="40" value="" onchange="seeOrganisation()" /><font color="red" size="2">*</font>
			</td>
			<td class="form-label">
			<bean:message key="label.orgPhone"/> : </td>
			<td class="form-widget"><html:text property="iphoneno" size="40" value=""/><font color="red" size="2">*</font><html:errors property="iphoneno"/>
			</td></tr>
			<tr class="form-element"><td class="form-label">
			<bean:message key="label.orgFax"/> :</td>
			<td class="form-widget"> <html:text property="ifax" size="40" value=""/><html:errors property="ifax"/>
			</td><td class="form-label">
			<bean:message key="label.orgwebSite"/> :</td>
			<td class="form-widget"> <html:text property="iurl" size="40" value=""/>
			</td></tr>
			
			<tr class="form-element"><td class="form-label">
			<bean:message key="label.orgState"/> : </td>
			<td class="form-widget">
			<html:select property="istate" indexed="istate" style="width: 260px;" 
			 onchange="cityGenerate()">
			<html:option value="--Select--">--Select--</html:option>
			<sql:query var="orgstate" dataSource="jdbc/mydb">
				select distinct s.state_name from state s order by state_name asc
			</sql:query>
			<c:forEach var="row" items="${orgstate.rows}">
			<html:option value="${row.state_name}"></html:option>
			</c:forEach>
			</html:select><html:errors property="istate"/><font color="red" size="2">*</font>
			</td><td class="form-label">
			<bean:message key="label.orgCity"/> : </td>
			<td class="form-widget"><html:select property="icity" style="width: 260px;" value="" >
			
		 </html:select><font color="red" size="2">*</font><html:errors property="icity"/>
			</td></tr>
			<tr class="form-element">
			<td class="form-label">
			<bean:message key="label.orgAddress"/> :</td>
			<td class="form-widget"><html:textarea property="iaddress" rows="2" cols="37" value=""/><font color="red" size="2">*</font><html:errors property="iaddress"/>
			</td></tr>
			
			</table><br>
			<table align="center">
		<tr>
			<td><input type="submit" value='<bean:message key="label.add.button" />' class="butStnd"/></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd"/></td>
			<td><input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" /></td>
			<td>
			<input type="hidden" name="orgname" id="orgname" size="20" readonly="readonly"/>
			<html:errors property="orgname"/>
			</td>
			</tr></table>
		</html:form>
	</div>
	</div>
	</div>
	 
	</body>
</html>

