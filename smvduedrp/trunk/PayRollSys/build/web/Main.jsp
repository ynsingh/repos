<%--
    Document        : SimpleSalaryData
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:02 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
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
        <h:form>
<div class="sidebar">
          <div class="logo">  </div>
          <h1>Payroll System</h1>
          <p> Logged-In At : <h:outputText value="#{OrgProfileBean.name}"/> </p>
          <jsp:include page="org/PlainProfile.jsp"/>
        </div>
<div class="usermenu"> <p>
    <a href="#" class="faded">[Login@<h:outputText value="#{UserBean.timeStamp}"/> as <b> <h:outputText  value="#{UserBean.userName}"/></b> ] </a>
    |
    <a href="./account/ChangePass.faces" onClick="return loadIframe('ifrm', this.href)"> Change Password</a>
    |
    <strong>
        <h:commandLink action="account/Logout.faces">Logout </h:commandLink>
    </strong>
    | <strong>
        <h:commandLink action="account/help/index.html">Help </h:commandLink>
    </strong>
    </p>
</div>
<div class="body">
          <div class="nav-menu">
    <!-- navigation bar-->

<ul class="sf-menu">

<li> <a href="#a">Employee</a>
	<ul>
	<li><a href="EmployeeProfile.faces" onClick="return loadIframe('ifrm', this.href)">Add New</a></li>
	<li><a href="EditEmployeeProfile.faces" onClick="return loadIframe('ifrm', this.href)">Edit</a></li>
	
	<li><a href="SearchEmployee.faces" onClick="return loadIframe('ifrm', this.href)">Search </a></li>
        
        
    </ul>
</li>
<li><a href="#">Salary</a>

    <ul>
        <li><a href="SalaryHead.faces" onClick="return loadIframe('ifrm', this.href)">Manage Salary Heads</a></li>
        <li><a href="SalaryFormula.faces" onClick="return loadIframe('ifrm', this.href)">Edit Salary Formula</a></li>
        <li><a href="FormulaViewer.faces" onClick="return loadIframe('ifrm', this.href)">View Salary Formula</a></li>
        <li><a href="SalaryOption.faces" onClick="return loadIframe('ifrm', this.href)">Salary Head Setting </a></li>
        <li><a href="DefaultSalaryData.jsp" onClick="return loadIframe('ifrm', this.href)">Default Salary Value Setting </a></li>
        <li><a href="SimpleSalaryData.jsp" onClick="return loadIframe('ifrm', this.href)">Process Monthly Salary Data </a></li>
    </ul>
</li>
<li><a href="#">Setup</a>

    <ul>
        <li><a href="Department.faces" onClick="return loadIframe('ifrm', this.href)">Department</a></li>
        <li><a href="account/AddUser.faces" onClick="return loadIframe('ifrm', this.href)">Add User</a></li>
        <li><a href="Designation.faces" onClick="return loadIframe('ifrm', this.href)">Designation</a></li>
        <li><a href="SalaryGrade.faces" onClick="return loadIframe('ifrm', this.href)">Manage Grades</a></li>
       <li> <a href="SalaryProfile.faces" onClick="return loadIframe('ifrm', this.href)">Employee Types</a></li>
   </ul>

</li>
<li><a href="#">Tax Management</a>
    <ul>
	<li><a href="InvestmentHead.faces" onClick="return loadIframe('ifrm', this.href)">Investment Heads</a></li>
        <li><a href="InvestmentPlanViewer.faces" onClick="return loadIframe('ifrm', this.href)">Employees Investment Planing</a></li>
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
	<li><a href="./account/ChangePass.faces" onClick="return loadIframe('ifrm', this.href)">Change Password</a></li>
        <li><a href="account/Logout.faces"> Log Out</a></li>
    </ul>
</li>

</ul>

    <!-- navigation bar-->
  </div>
          <div class="content-area" >


              <iframe name="ifrm" id="ifrm" src="updates.html" style="background-color:#ffffff" width="100%" height="400px">Your browser doesn't support iframes.</iframe>
              <p class="hfooter"> Developed By SMVDU . Developer Team  | 2010 </p>
          </div>
    


</div>
    </h:form>
</f:view>

</body>
</html>
