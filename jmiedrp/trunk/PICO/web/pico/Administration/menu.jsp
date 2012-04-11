<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="../css/style.css" type="text/css" />
</head>
<body style="background-color:#EBEBEB">
    <ul id="css3menu1" class="tomenu" >
        <%
        try {
            String Id = session.getAttribute("username").toString();
/*
            out.println("<li class='topmenu'><a href='#' title='Welcome' style='width:250px;'>Welcome:" + Id + "</a></li>");
            out.println("<li class='topmenu'><a href='#' title='Administration' style='height:18px;line-height:18px;width:146px;'><span>Administration</span></a>");
            out.println("<ul>");
            out.println("<li><a href='#' title='Organization'><span>Organization</span></a>");
            out.println("<ul>");
            out.println("<li><a href='/pico/Administration/ManageInstitutionAction.action' title='Institution'>Institution</a></li>");
            out.println("<li><a href='/pico/Administration/ManageSubInstitutionAction.action' title='College/Faculty/School'>College/Faculty/School</a></li>");
            out.println("<li><a href='/pico/Administration/ManageDepartmentAction.action' title='Departments'>Departments</a></li>");
            out.println("<li><a href='/pico/Administration/ManageEmployeeAction.action' title='Employees'>Employees</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("<li><a href='/pico/Administration/ManageInstitutionUserRoleAction.action' title='User Role'>User Role</a></li>");
            out.println("<li><a href='/pico/Administration/ManageAuthorizationRequests.action' title='Authorization Requests'>Authorization Requests</a></li>");
            out.println("<li><a href='/pico/Administration/ManageGeneralMasterAction.action' title='General Master'>General Master</a></li>");
            out.println("<li><a href='/pico/Administration/ManageCapitalCategoryAction.action' title='Capital Items'>Capital Items</a></li>");
            out.println("<li><a href='/pico/Administration/ManagebudgetheadAction.action' title='Budget Heads'>Budget Heads</a></li>");
            out.println("<li><a href='/pico/Administration/ManageDepartmentalBudgetAllocationAction.action' title='Budget Allocation'>Budget Allocation</a></li>");
            out.println("<li><a href='/pico/Administration/ManageGeneralTermsAction.action' title='General Terms'>General Terms & Conditions</a></li>");

*/
            String menuItem, menuItemHref, nextMenuItem, menuEnvVariable;
            int FirstList, SecondList;
            int jIncremented, kIncremented, i, j, k;
            i = j = k = 0;
            menuItem = "Menu[" +  i + "][" + j  +"][" + 0 + "]";
            while(session.getAttribute(menuItem) != null){
                j = 0;
                FirstList = 0;
                SecondList = 0;
                menuItem = "Menu[" +  i + "][" + j  +"][" + 0 + "]";
                menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + 0 + "]";
                out.println("<li class='topmenu'><a href='" +  session.getAttribute(menuItemHref) + "' style='height:18px;line-height:18px;width:142px;'>" + session.getAttribute(menuItem) +  "</a>");

                FirstList = FirstList + 1;
                
                jIncremented = j + 1;
                nextMenuItem = "Menu[" +  i + "][" + jIncremented  +"][" + 0 + "]";
               
                if(session.getAttribute(nextMenuItem) != null) {
                    SecondList = SecondList + 1;
                    out.println("<ul>");
                }
                while (session.getAttribute(nextMenuItem) != null) {
                    k = 0;
                    j = j + 1;                    
                    menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";
                    menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + 0 + "]";                
                    //out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'><span>" + session.getAttribute(menuItem) + "</span></a>");
                    //FirstList = FirstList + 1;
                    kIncremented = k + 1;
                    nextMenuItem = "Menu[" +  i + "][" + j  +"][" + kIncremented  + "]";
                    if(session.getAttribute(nextMenuItem) != null)
                            {
                                FirstList = FirstList + 1;
                                out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'><span>" + session.getAttribute(menuItem) + "</span></a>");
                                SecondList = SecondList + 1;
                                out.println("<ul>");
                            }
                    else {
                        out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'>" + session.getAttribute(menuItem) + "</a>");
                        FirstList = FirstList + 1;
                        }
                        while (session.getAttribute(nextMenuItem) != null) {
                            k = k + 1;
                            menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";
                            menuItemHref = "MenuHref[" +  i + "][" + j  +"][" + k + "]";                            
                            out.println("<li><a href='" + session.getAttribute(menuItemHref)+ "'>" + session.getAttribute(menuItem) + "</a></li>");
                            kIncremented = k + 1;
                            nextMenuItem = "Menu[" +  i + "][" + j  +"][" + kIncremented + "]";
                            if (SecondList > 0 && session.getAttribute(nextMenuItem) == null){
                                out.println("</ul>");
                                out.println("</li>");
                                SecondList = SecondList - 1;
                            }
                        }
                        jIncremented = j + 1;
                        nextMenuItem = "Menu[" +  i + "][" + jIncremented  +"][" + 0 + "]";                     
                        }
                if (FirstList > 0 && session.getAttribute(nextMenuItem) == null){
                    if (SecondList > 0) {
                        out.println("</li>");
                        SecondList = SecondList - 1;
                        }
                        out.println("</li>");
                        FirstList = FirstList - 1;
                    }
                i = i + 1;
                j = k = 0;
                menuItem = "Menu[" +  i + "][" + j  +"][" + k + "]";

                } //End While
            out.println("</ul>");
            out.println("</li>");
            }
            catch (Exception e) {
        }
        %>     	
<!--
	<li class="topmenu"><a href="#" title="Pre-Purchase" style="height:18px;line-height:18px;width:110px;">Pre-Purchase</a>
	<ul>
		<li><a href="/pico/PrePurchase/ManageItems.action" title="Purchase Items">Purchase Items</a></li>
		<li><a href="/pico/PrePurchase/ManageSupplier.action" title="Suppliers">Suppliers</a></li>
		<li><a href="/pico/PrePurchase/ManageItemRates.action" title="Item Rates">Item Rates</a></li>
		<li><a href="/pico/PrePurchase/ManageIndent.action" title="Indent">Indent</a></li>
		<li><a href="/pico/PrePurchase/ManagePOMaster.action" title="Purchase Orders">Purchase Orders</a></li>
	</ul>
	</li>
	
<li class="topmenu"><a href="#" title="Purchase" style="height:18px;line-height:18px;width:110px;">Purchase</a>
<%--Code Inserted by Tanvir Ahmed --%>
            <ul>
		<li><a href="/pico/Purchase/ManagePurchaseChallanAction.action" title="Purchase Challan">Purchase Challan</a></li>
            </ul>

        </li>



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
		<li><a href="/pico/PrePurchase/ChangeProfileAction" title="Change Profile">Change Profile</a></li>
            </ul>
        </li>
	<li><a href="#" title="Help" style="height:18px;line-height:18px;width:110px;">Help</a></li>
        <li class="toplast"><a href="/pico/PrePurchase/LogoutAction" title="Exit" style="height:18px;line-height:18px;width:205px;">Exit</a></li>
-->
</ul>

</body>
</html>