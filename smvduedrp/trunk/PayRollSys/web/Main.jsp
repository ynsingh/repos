<%--
    Document        : SimpleSalaryData
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:02 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Payroll System | Welcome</title>
		<link rel="stylesheet" type="text/css" href="css/reset.css"/>
                <link rel="stylesheet" type="text/css" href="css/table.css"/>
		<link rel="stylesheet" type="text/css" href="css/layout.css"/>
		<link rel="stylesheet" type="text/css" href="css/mainpage.css"/>
		<link rel="stylesheet" type="text/css" href="css/superfish.css" media="screen"/>
		<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
		<script type="text/javascript" src="js/hoverIntent.js"></script>
		<script type="text/javascript" src="js/superfish.js"></script>
		<script type="text/javascript">

	// initialise plugins
		jQuery(function(){
			jQuery('ul.sf-menu').superfish();
		});

		////////////////////////////////////////
		// loading of iframes
		/////////////////////////////////////////


		function loadIframe(iframeName, url) {
			if ( window.frames[iframeName] ) {
				window.frames[iframeName].location = url;
				return false;
			}
			return true;
		}

		// assign new url to iframe element's src property
		function changeIframeSrc(id, url) {
			if (!document.getElementById) return;
			var el = document.getElementById(id);
			if (el && el.src) {
				el.src = url;
				return false;
			}
			return true;
		}

		</script>
		</head>

		<body id="" class="mainpage">
<f:view>
      
           
<div class="sidebar">
          <div class="logo">  </div>
          <rich:panel header="Institute Details">             
              <h1>Payroll System</h1>
              <hr width="100%"/>
              <p> Logged-In At : <h:outputText value="#{OrgProfileBean.name}"/> </p>
              <hr width="100%"/>
              <jsp:include page="org/PlainProfile.jsp"/>
              <h:commandButton value="Logout"/>
              <hr width="100%"/>              
              <h:commandButton onclick="Richfaces.showModalPanel('themepanel');" value="Change Theme"/>
              <h:commandButton value="Help"/>
          </rich:panel>
</div>
         

<div class="body">
          <div class="nav-menu">
    <!-- navigation bar-->

<ul class="sf-menu">

<li> <a href="#a">Employee</a>
	<ul>
	<li><a href="employee/EmployeeProfile.jsf" onClick="return loadIframe('ifrm', this.href)">Add New</a></li>
	<li><a href="employee/EditEmployeeProfile.jsf" onClick="return loadIframe('ifrm', this.href)">Edit</a></li>
	<li><a href="employee/SearchEmployee.jsf" onClick="return loadIframe('ifrm', this.href)">Search </a></li>
    </ul>
</li>
<li><a href="#">Salary</a>
    <ul>
        
        <li><a href="salary/SalaryFormula.jsf" onClick="return loadIframe('ifrm', this.href)">Edit Salary Formula</a></li>
        <li><a href="FormulaViewer.jsf" onClick="return loadIframe('ifrm', this.href)">View Salary Formula</a></li>
        <li><a href="SalaryOption.jsf" onClick="return loadIframe('ifrm', this.href)">Salary Head Setting </a></li>
        <li><a href="DefaultSalaryData.jsp" onClick="return loadIframe('ifrm', this.href)">Default Salary Value Setting </a></li>
        <li><a href="SimpleSalaryData.jsf" onClick="return loadIframe('ifrm', this.href)">Process Monthly Salary Data </a></li>
    </ul>
</li>
<li><a href="#">Setup</a>
    <ul>
        <li><a href="setup/Departments.jsf" onClick="return loadIframe('ifrm', this.href)">Department</a></li>        
        <li><a href="setup/Designation.jsf" onClick="return loadIframe('ifrm', this.href)">Designation</a></li>
        <li><a href="setup/SalaryGrade.jsf" onClick="return loadIframe('ifrm', this.href)">Manage Grades</a></li>
       <li> <a href="SalaryProfile.jsf" onClick="return loadIframe('ifrm', this.href)">Employee Types</a></li>
       <li><a href="setup/SalaryHead.jsf" onClick="return loadIframe('ifrm', this.href)">Manage Salary Heads</a></li>
       <li><a href="account/AddUser.jsf" onClick="return loadIframe('ifrm', this.href)">Add User</a></li>
   </ul>
</li>
<li><a href="#">Tax Management</a>
    <ul>
	<li><a href="InvestmentHead.jsf" onClick="return loadIframe('ifrm', this.href)">Investment Heads</a></li>
        <li><a href="InvestmentPlanViewer.jsf" onClick="return loadIframe('ifrm', this.href)">Employees Investment Planing</a></li>
    </ul>
</li>
<li><a href="#">Provident Fund</a>

</li>
<li><a href="#">Leave</a>


</li>
<li><a href="#">Loans & Advances</a>


</li>
<li><a href="#">Report</a>
    <ul>
	<li><a href="MonthlySalarySingle.jsp" onClick="return loadIframe('ifrm', this.href)">Monthly Salary Slip</a></li>
        <li><a href="BankStatement.jsp" onClick="return loadIframe('ifrm', this.href)">Monthly Bank Statement</a></li>
    </ul>
</li>
<li><a href="#">Account</a>
    <ul>
	<li><a href="./account/ChangePass.jsf" onClick="return loadIframe('ifrm', this.href)">Change Password</a></li>
        <li><a href="account/Logout.jsf"> Log Out</a></li>
    </ul>
</li>

</ul>

    <!-- navigation bar-->
  </div>
          <div class="content-area" >


              <iframe name="ifrm" id="ifrm" src="MyUpdates.jsf" style="background-color:#ffffff" width="100%" height="400px">Your browser doesn't support iframes.</iframe>
              
          </div>
    


</div>
   

    <rich:modalPanel id="themepanel">
        <f:facet name="controls">
            <h:graphicImage value="/img/cls.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('themepanel')" />
      </f:facet>
        <f:facet name="header">
            <h:outputText value="Theme Selector" />
      </f:facet>
            <h:form>
            <h:panelGrid columns="3">
                <h:outputText value="Select Theme"/>
                <h:selectOneMenu onchange="submit();" value="#{ThemeBean.theme}">
                    <f:selectItems value="#{ThemeBean.themes}"/>
                </h:selectOneMenu>
                <h:commandButton onclick="Richfaces.hideModalPanel('themepanel');" value="Close"/>
            </h:panelGrid>
            </h:form>
        
    </rich:modalPanel>



</f:view>

</body>
</html>
