<%-- 
    Document   : menu
    Created on : 3 Oct, 2010, 5:53:17 PM
    Author     : kazim
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

 <div class="navbar">
        <!-- *********************************Start Menu****************************** -->
        <div class="mainDiv" >
                <div class="mainDiv">
                <div class="topItem"> Administration </div>
                <div class="dropMenu" ><!-- -->
                <div class="subMenu" >
                        <div class="subItem">
                            <s:url namespace="/Administration" action="ManageInstitutionUserRoleAction" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Manage Institution User Roles</a>
                        </div>
                        <div class="subItem">
                            <s:url namespace="/Administration" action="ManageGeneralMasterAction" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Manage General Master</a>
                        </div>
                        <div class="subItem">
                            <s:url namespace="/Administration" action="ManageAuthorizationRequests" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Authorization Requests</a>
                        </div>                        
                        <div class="subItem">
                        <s:url namespace="/Administration" action="ManageInstitutionAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Institutions</a>
                        </div>
                        <div class="subItem">
                        <s:url namespace="/Administration" action="ManageSubInstitutionAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Colleges/Faculties/Schools</a>
                        </div>
                        <div class="subItem">
                        <s:url namespace="/Administration" action="ManageDepartmentAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Departments</a>
                        </div>
                        <div class="subItem">
                        <s:url namespace="/Administration" action="ManageCapitalCategoryAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Capital Items</a>
                        </div>                        
                        <div class="subItem">
                        <s:url namespace="/Administration" action="ManagebudgetheadAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Budget Heads</a>
                        </div>
               <div class="subItem">
                        <s:url namespace="/Administration" action="ManageDepartmentalBudgetAllocationAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Departmental Budget Allocation</a>
                        </div>
                         <div class="subItem">
                        <s:url namespace="/Administration" action="ManageMessageAction" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Messages</a>
                        </div>
                </div>
            </div>
                </div>
        <div class="mainDiv">
        <div class="topItem"> Pre-Purchase </div>
        <div class="dropMenu" style="display:inline;" ><!-- -->
                <div class="subMenu" >                                           
                    <div class="subItem">
                        <s:url namespace="/PrePurchase" action="ManageItems" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Manage Items</a>
                        </div>
                      <div class="subItem">
                        <s:url namespace="/PrePurchase" action="ManageSupplier" id="NavigateToURL"></s:url>
                             <a href='<s:property value="NavigateToURL"/>'>Manage Supplier</a>
                      </div>
                      <div class="subItem">
                            <s:url namespace="/PrePurchase" action="ManageItemRates" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Manage Item Rates</a>
                      </div>
                        <div class="subItem">
                            <s:url namespace="/PrePurchase" action="ManageIndent.action" id="NavigateToURL"></s:url>
                            <a href='<s:property value="NavigateToURL"/>'>Indent Form</a>
                        </div>
                        <div class="subItem">
                        <s:url namespace="/PrePurchase" action="ManagePOMaster" id="NavigateToURL"></s:url>
                         <a href='<s:property value="NavigateToURL"/>'>Purchase Orders</a>

                        </div>
                        <div class="subItem">
                        <s:url action="ShowSubInstitution.action" id="NavigateToURL"></s:url>
                        <a href='<s:property value="NavigateToURL"/>'>Manage Sub Institution</a>
                        </div>

                         <div class="subItem"><a href="#">Sub Menu 5</a></div>
                </div>
        </div>
        </div>
        <div class="mainDiv">
        <div class="topItem"> Post Purchase </div>
        <div class="dropMenu" style="display:inline" ><!-- -->
                <div class="subMenu" >
                        <div class="subItem"><a href="#">Manage Budget</a></div>
                        <div class="subItem"><a href="#">Raise Indent</a></div>
                        <div class="subItem"><a href="#">Create Item</a></div>
                        <div class="subItem"><a href="#">Sub Menu 4</a></div>
                        <div class="subItem"><a href="#">Sub Menu 5</a></div>
                </div>
        </div>
         </div>

        <script type="text/javascript" src="../css/xpmenuv21.js"></script> 
        </div>
 </div>

