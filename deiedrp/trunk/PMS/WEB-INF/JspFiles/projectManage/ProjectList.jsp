<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.projmanage.ProjectList"%>
<%@ page import="in.ac.dei.edrp.pms.projmanage.ProjectFields;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>example.jsp</title>
 	
	<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
	<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<link rel="stylesheet" href="style/general.css" type="text/css" media="screen" />
		
	 <!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
         <!-- You have to include this file for jquery -->
	<script src='javascript/jquery-1.3.2.js' type='text/javascript'></script>
	<script type="text/javascript">
	
		
	function getPname(){
		var pcode=document.getElementById('pcode').value;
		if(pcode!="")
		{
			var orgportal = DWRUtil.getValue("orgportal");
			DynamicList.getProjectName(pcode,orgportal,function(data)
  			{
  				DWRUtil.setValue("projectName",data);
   				projectTeamGenerate();
   		 	}
  			); 
		}
		}
		
	 	function projectTeamGenerate() {
    	var orgportal = DWRUtil.getValue("orgportal");
   		var projectName = DWRUtil.getValue("projectName");
   		
   		DynamicList.generateProjectTeamList(projectName,orgportal,0,function(data)
  		{
  			DWRUtil.removeAllOptions("select2");
   			DWRUtil.addOptions("select2",data);
   			orgTeamGenerate();
   		 }
  		); 
	 }
   
   		function orgTeamGenerate() {
    	var orgportal = DWRUtil.getValue("orgportal");
   		var projectName = DWRUtil.getValue("projectName");;
  		DynamicList.generateOrgEmployeeList(projectName,orgportal,function(data)
  		{
  			DWRUtil.removeAllOptions("select1");
   			DWRUtil.addOptions("select1",data);
   		 }
  		); 
	 }
	 
	  <!-- jQuery for option transfer -->
  
  		jQuery.noConflict();
      	jQuery(document).ready(function() { 
      	
       	jQuery('#add').click(function() {  
        return !jQuery('#select1 option:selected').remove().appendTo('#select2');  
       	});  
      	 jQuery('#remove').click(function() {  
      	 return !jQuery('#select2 option:selected').remove().appendTo('#select1');  
     	 }); 
       	 		
 		jQuery('#addAll').click(function() {
		return !jQuery('#select1 option').remove().appendTo('#select2').removeAttr("selected");
		});
		
		jQuery('#removeAll').click(function() {
		return !jQuery('#select2 option').remove().appendTo('#select1').removeAttr("selected");
		});
		 		
 		jQuery('form').submit(function() {  
 		document.getElementById('defValueOfSelect2').value=-1;
 		jQuery('#select2 option').each(function(i) {  
  		document.getElementById('defValueOfSelect2').value=i;
  		foo = jQuery('#select2 :selected').val();
 		jQuery(this).attr("selected", "selected");  
 	 		});  
 		}); 
 		
 		
 	 <!-- for popup window -->
 	 
 	jQuery('a.poplight[href^=#]').click(function() {
    var popID = jQuery(this).attr('rel'); //Get Popup Name
    var popURL = jQuery(this).attr('href'); //Get Popup href to define size
 
    //Pull Query & Variables from href URL
    var query= popURL.split('?');
    var dim= query[1].split('&');
    var popWidth = dim[0].split('=')[1]; //Gets the first query string value
    var pcode = dim[1].split('=')[1]; //Gets the second query string value
    document.getElementById('pcode').value = pcode; 
		getPname();
    //Fade in the Popup and add close button
    jQuery('#' + popID).fadeIn().css({ 'width': Number( popWidth ) }).prepend('<a href="#" class="close"><img src="img/close.PNG" class="btn_close" title="Close Window" alt="Close" /></a>');

    //Define margin for center alignment (vertical   horizontal) - we add 80px to the height/width to accomodate for the padding  and border width defined in the css
    var popMargTop = (jQuery('#' + popID).height() + 80) / 2;
    var popMargLeft = (jQuery('#' + popID).width() + 80) / 2;

    //Apply Margin to Popup
    jQuery('#' + popID).css({
        'margin-top' : -popMargTop,
        'margin-left' : -popMargLeft
    });

    //Fade in Background
    jQuery('body').append('<div id="fade"></div>'); //Add the fade layer to bottom of the body tag.
    jQuery('#fade').css({'filter' : 'alpha(opacity=0.7)'}).fadeIn(); //Fade in the fade layer - .css({'filter' : 'alpha(opacity=80)'}) is used to fix the IE Bug on fading transparencies 

    return false;
});

//Close Popups and Fade Layer
jQuery('a.close, #fade').live('click', function() { //When clicking on the close or fade layer...
    jQuery('#fade , .popup_block').fadeOut(function() {
        jQuery('#fade, a.close').remove();  //fade them both out
    });
    return false;
	});
 		
 });  
    </script>  

  </head>
  
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="viewproject.do?key="+a[0].value;
	}
	function rowchange()
	{
	    var table = document.getElementById("row");    
    	var tbody = table.getElementsByTagName("tbody")[0];
    	var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    	for (i=0; i < rows.length; i++) {
        	var value = rows[i].getElementsByTagName("td")[0].firstChild.nodeValue;
          if (value.indexOf('N') >= 0) {
            rows[i].style.backgroundColor = "#C0C0C0";
            rows[i].style.textDecoration="line-through";
            rows[i].style.color="#FF6347";
        }
    	}
	}
	
	function deleteProject()
	{
	return(confirm("Are you sure want to disable this project?"));
	}
	</script>
  <body onload="rowchange();">
    
   <% request.setAttribute("projectList", new ProjectList((String)session.getAttribute("uid"),
   (String)session.getAttribute("validOrgInPortal"),(String)session.getAttribute("roleid"))); %>
  
  <div id="main_title" align="left">
		    <font color="#0044ff">Project List:</font></div><br>
<%!  String key=null;%>
<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
  %>
 
 <div align="left">
	Number of records to be displayed:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5">5</html:option>
    <html:option value="10">10</html:option>
    <html:option value="15">15</html:option>
    <html:option value="20">20</html:option>
        </html:select>
			<html:errors property="nrec"/>
			<div id="button">
				<html:link action="newproject">New Project<img border="0" title="Add new project" src="img/user1_add.png" width="15" "height="15" ></html:link>
			</div>
	</div>
   <logic:notEmpty name="projectList" property="list">
 <display:table name="projectList.list" defaultsort="2" id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewproject.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		
		<display:column title="Active" sortable="true" >
		<logic:equal name="row" property="enable" value="0">Yes</logic:equal>
		<logic:equal name="row" property="enable" value="1">No</logic:equal>
		</display:column>
		<display:column title="Project Name" sortable="true">
		<html:link title="click for view the Task List" href="projectDetails.do" paramProperty="project_name" paramId="key1" paramName="row">
		<%=((ProjectFields)pageContext.getAttribute("row")).getProject_name()%>
		</html:link>
		</display:column>	
		<display:column property="scheduleStartDate" title="Plan Start Date" sortable="true" />
		<display:column property="scheduleEndDate" title="Plan End Date" sortable="true" />
		<display:column property="actualStartDate" title="Actual Start Date" sortable="true" />
		<display:column property="actualEndDate" title="Actual End Date" sortable="true" />
		
		<display:column property="tbudget" title="Target Budget (Rs.)" format="{0,number,0,000.00}"
		 sortable="true" />
		<display:column property="priority" title="Priority" sortable="true" />
		<display:column property="status" title="Status" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<logic:equal name="row" property="enable" value="0">
			<html:link title="click for view the Gantt Chart" href="drawGanttChart.do" paramProperty="project_name" paramId="pname" paramName="row">
			view<font style="padding-left:70%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;">
			</font></html:link>
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
			<font style="padding-left:100%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;"></font>
		</logic:equal>
		</display:column>
		<display:column property="description" maxLength="15" title="Description" sortable="true" />
		
		<!-- for view the project members -->
		
		<display:column media="html" title="Project Team">
		<logic:equal name="row" property="enable" value="0">
		
		<html:link href="viewProjTeam.do" paramProperty="project_code" paramId="key1" paramName="row">
		<img border="0" title="View project team" src="img/user1_add.png" width="20"  height="10" >
		  </html:link>
		  		<logic:equal name="row" property="teamCreation" value="Allow">
		   			| <a href="#?w=600&pname=<%=((ProjectFields)pageContext.getAttribute("row")).getProject_code()%>" rel="popup_name" class="poplight">
		  	 		<img border="0" title="Create your project team" src="img/users3.png" width="20"  height="10" ></a>
		 		</logic:equal>
		  </logic:equal>
		  <logic:equal name="row" property="enable" value="1">
		  <img border="0" title="View Team" src="img/users3.png" width="20"  height="10" >
		  </logic:equal>
		  </display:column>
	
		<!-- end here -->
		
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column media="html" title="Actions">
		 	<logic:equal name="row" property="enable" value="0">
			<html:link href="editProject.do" paramProperty="project_code" paramId="project_code" paramName="row"><img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		 	</html:link> | 
			<html:link href="deleteProject.do" onclick="return deleteProject();" paramProperty="project_code" paramId="project_code" paramName="row">Disable
			 </html:link>  
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
	 		 <html:link href="deleteProject.do" paramProperty="project_code" paramId="project_code" paramName="row">Enable
		 	</html:link>
		</logic:equal>

		</display:column>
		</logic:equal>
		<display:setProperty name="export.pdf.filename" value="ProjectDetails.pdf"/>
		<display:setProperty name="export.excel.filename" value="ProjectDetails.xls"/>
		<display:setProperty name="export.xml.filename" value="ProjectDetails.xml"/>
		<display:setProperty name="export.csv.filename" value="ProjectDetails.csv"/>
		
	</display:table>
	 </logic:notEmpty>
    <logic:empty name="projectList" property="list">
   <br><font color="#550003" size="2">Nothing found to display.</font><br><br>
    <html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
    </logic:empty>
    
    
     <!-- for open popup window -->
 
  	<div id="popup_name" class="popup_block">
 	<div class="title">Select Employees</div>
 	 <p>
   		<html:form action="/addTeam" >			
   		<input type="hidden" name="pcode" id="pcode"><html:errors property="pcode"/>
   		<input type="hidden" name="defValueOfSelect2" id="defValueOfSelect2"><html:errors property="defValueOfSelect2"/>
   		<input type="hidden" name="orgportal" id="orgportal" 
			value="${sessionScope.validOrgInPortal}"/>
		<html:errors property="orgportal"/>
		<table cellspacing="1" cellpadding="1" width="auto" border="0" align="center">
			<tr class="form-element">
				<td  class="form-label">
					Project Name :
			  	<input type="text" name="projectName" 
			  	id="projectName" size="70" readonly="readonly" 
			  	style="border: none;background-color: threedlightshadow;color: blue;">
				<html:errors property="projectName"/>
				</td></tr>
		</table>
		<table cellspacing="1" cellpadding="6" width="auto" border="0" align="center">
			<tr class="form-element"><td class="form-label">
     		<div class="div">
     		<div> Organization Employee</div><br>
     		<select class="select" multiple="multiple" id="select1" name="select1" style="width: 270px;">
			</select><html:errors property="select1"/>
			 <div align="center">
     			<a class="a" href="#" id="add">&gt;&gt;</a><br>
     			<a class="a" href="#" id="addAll">&gt;&gt;&gt;</a>
    		</div>
    	</div>  
     <div class="div">
    	<div>
   		Project Team</div><br>
     	<select class="select" multiple="multiple" id="select2" name="select2" style="width: 270px;">
     	 </select> <html:errors property="select2"/>
     	<div align="center"> 
    		 <a class="a" href="#" id="remove">&lt;&lt;</a><br>
    		 <a class="a" href="#" id="removeAll">&lt;&lt;&lt;</a>
    	</div>
    	</div> 
   	 </td></tr>
    </table> 
    <table align="center">
    <tr><td>
    <html:submit value="Create Team" styleClass="butStnd"/>
    <html:reset value="Reset" styleClass="butStnd" onclick="getPname()"/>
    </td></tr></table>
    </html:form>
  	</p>
  	
	</div>
	
  </body>
</html:html>


