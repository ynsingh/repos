<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="../css/style.css" type="text/css" />
</head>
<body style="background-color:#EBEBEB">

    <ul id="css3menu1" class="topmenu">
        <%
        try {
            String Id = session.getAttribute("username").toString();

            out.println("<li class='topfirst'><a href='#' title='Welcome' style='width:250px;'>Welcome:" + Id + "</a></li>");
            }
            catch (Exception e) {
        }
        %>


        <li class="topmenu"><a href="#" title="Administration" style="height:18px;line-height:18px;width:146px;"><span>Administration</span></a>
	<ul>
		<li><a href="#" title="Organization"><span>Organization</span></a>
		<ul>
			<li><a href="http://localhost:8080/pico/Administration/ManageInstitutionAction.action" title="Institution">Institution</a></li>
			<li><a href="http://localhost:8080/pico/Administration/ManageSubInstitutionAction.action" title="College/Faculty/School">College/Faculty/School</a></li>
			<li><a href="http://localhost:8080/pico/Administration/ManageDepartmentAction.action" title="Departments">Departments</a></li>
		</ul>
		</li>
		<li><a href="http://localhost:8080/pico/Administration/ManageInstitutionUserRoleAction.action" title="User Role">User Role</a></li>
		<li><a href="http://localhost:8080/pico/Administration/ManageAuthorizationRequests.action" title="Authorization Requets">Authorization Requets</a></li>
		<li><a href="http://localhost:8080/pico/Administration/ManageGeneralMasterAction.action" title="General Master">General Master</a></li>
		<li><a href="http://localhost:8080/pico/Administration/ManageCapitalCategoryAction.action" title="Capital Items">Capital Items</a></li>
		<li><a href="http://localhost:8080/pico/Administration/ManagebudgetheadAction.action" title="Budget Heads">Budget Heads</a></li>
		<li><a href="http://localhost:8080/pico/Administration/ManageDepartmentalBudgetAllocationAction.action" title="Budget Allocation">Budget Allocation</a></li>
<%--Code Inserted by Tanvir Ahmed --%>
                <li><a href="http://localhost:8080/pico/Administration/ManageGeneralTermsAction.action" title="General Terms">General Terms & Conditions</a></li>
	</ul>
	</li>
	<li class="topmenu"><a href="#" title="Pre-Purchase" style="height:18px;line-height:18px;width:110px;"><span>Pre-Purchase</span></a>
	<ul>
		<li><a href="http://localhost:8080/pico/PrePurchase/ManageItems.action" title="Purchase Items">Purchase Items</a></li>
		<li><a href="http://localhost:8080/pico/PrePurchase/ManageSupplier.action" title="Suppliers">Suppliers</a></li>
		<li><a href="http://localhost:8080/pico/PrePurchase/ManageItemRates.action" title="Item Rates">Item Rates</a></li>
		<li><a href="http://localhost:8080/pico/PrePurchase/ManageIndent.action" title="Indent">Indent</a></li>
		<li><a href="http://localhost:8080/pico/PrePurchase/ManagePOMaster.action" title="Purchase Orders">Purchase Orders</a></li>
	</ul>
	</li>
	<li class="topmenu"><a href="#" title="Purchase" style="height:18px;line-height:18px;width:110px;">Purchase</a></li>
	<li class="topmenu"><a href="#" title="Inventory" style="height:18px;line-height:18px;width:110px;">Inventory</a></li>
        <li><a href="#" title="User Profile" style="width:98px;">User Profile</a>
            <ul>
		<li><a href="#" title="Current Profile"><span>Current Profile</span></a>
		<ul>
                     <%
                     try {
                            String IM_SN = session.getAttribute("imshortname").toString();
                            String SIM_SN = session.getAttribute("simshortname").toString();
                            String DM_SN = session.getAttribute("dmshortname").toString();
                            String ERPMU_Full_Name = session.getAttribute("userfullname").toString();


                            out.println("<li><a href='#' title='Current Profile' style='width:250px;'>" +
                                                                IM_SN + "/" + SIM_SN + "/" + DM_SN + "/" + ERPMU_Full_Name
                                                                + "</a></li>");
                            }
                    catch (Exception e) {
                            //out.println("Exception!!" + e.getMessage());
                        }
                    %>
                </ul>
		</li>
		<li><a href="http://localhost:8080/pico/PrePurchase/ChangeProfileAction" title="Change Profile">Change Profile</a></li>
            </ul>
        </li>
	<li><a href="#" title="Help" style="height:18px;line-height:18px;width:110px;">Help</a></li>
        <li class="toplast"><a href="http://localhost:8080/pico/PrePurchase/LogoutAction" title="Exit" style="height:18px;line-height:18px;width:205px;">Exit</a></li>
</ul>

<p style="display:none"><a href="http://css3menu.com/">Web 2 0 CSS Buttons Css3Menu.com</a></p>
</body>
</html>