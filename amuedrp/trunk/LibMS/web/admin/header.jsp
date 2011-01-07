<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">


<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Application Menubar</title>
        <%!
        String privilege[]=new String[10];
        String acq_privilege[]=new String[100];
       String cat_privilege[]=new String[100];
       String cir_privilege[]=new String[100];
       String ser_privilege[]=new String[100];
        %>
           <%

           String username=(String)session.getAttribute("username");
       String library_id=(String)session.getAttribute("library_id");

       String library_name=(String)session.getAttribute("library_name");






     //  ResultSet rst1=(ResultSet)session.getAttribute("privilege_resultset");
     //  ResultSet rst2=(ResultSet)session.getAttribute("acq_privilege_resultset");
    //   ResultSet rst3=(ResultSet)session.getAttribute("cat_privilege_resultset");
    //   ResultSet rst4=(ResultSet)session.getAttribute("cir_privilege_resultset");
    //   ResultSet rst5=(ResultSet)session.getAttribute("ser_privilege_resultset");

       //out.println(staff_id);

       int start =1;
      
       ResultSet rst1=(ResultSet)session.getAttribute("privilege_resultset");
       ResultSet rst2=(ResultSet)session.getAttribute("acq_privilege_resultset");
       ResultSet rst3=(ResultSet)session.getAttribute("cat_privilege_resultset");
       ResultSet rst4=(ResultSet)session.getAttribute("cir_privilege_resultset");
       ResultSet rst5=(ResultSet)session.getAttribute("ser_privilege_resultset");

     //  out.println(rst4.getString(3));


       while(start<8)
           {
           privilege[start]=rst1.getString(start+2);
           start++;
        }
       start=1;
        while(start<100)
           {
           acq_privilege[start]=rst2.getString(start+2);
           cat_privilege[start]=rst3.getString(start+2);
           cir_privilege[start]=rst4.getString(start+2);
           ser_privilege[start]=rst5.getString(start+2);
           start++;
        }





%> 
        <!-- Standard reset and fonts -->
        <link rel="stylesheet" type="text/css" href="./build/reset/reset.css">
        <link rel="stylesheet" type="text/css" href="./build/fonts/fonts.css">
        <link rel="stylesheet" type="text/css" href="./build/container/assets/container.css">

        <!-- CSS for Menu -->
        <link rel="stylesheet" type="text/css" href="./build/menu/assets/menu.css">

        <!-- Page-specific styles -->
        <style type="text/css">

            body {

                background-color:white;

            }


            /* Define a new style for each menubar */

            div.yuimenubar {

                border-width:1px 0;
                border-color:#666;
                border-style:solid;
                background-color:#ccc;

            }

            div.yuimenubar div.bd {

                border-width:1px 0;
                border-color:#ddd;
                border-style:solid;

            }

            div.yuimenubar li.yuimenubaritem {

                border-width:0;
                border-style:none;
                padding:4px 12px;

            }

            div.yuimenubar li.yuimenubaritem img {

                margin:0;
                border:0;
                height:1px;
                width:1px;

            }


            /* Define a new style for each menu */

            div.yuimenu {

                border:solid 1px #666;
                background-color:#ccc;

            }

            div.yuimenu div.bd {

                border-width:0;
                border-style:none;

            }


            /* Define a new style for each menu item */

            div.yuimenu li.yuimenuitem {

                padding-top:4px;
                padding-bottom:4px;

            }

            div.yuimenu li.yuimenuitem img {

                height:8px;
                width:8px;
                margin:0 -16px 0 10px;
                border:0;

            }

            div.yuimenu ul {

                border:solid 1px #666;
                border-width:1px 0 0 0;

            }


            /* Define a new style for an item's "selected" state */

            div.yuimenu li.selected,
            div.yuimenubar li.selected {

                background-color:#039;

            }

            div.yuimenu li.selected a.selected,
            div.yuimenubar li.selected a.selected {

                text-decoration:none;

            }


            /* Define a new style for an item's "disabled" state */

            div.yuimenu li.disabled a.disabled,
            div.yuimenu li.disabled em.disabled,
            div.yuimenubar li.disabled a.disabled {

                color:#666;

            }

        </style>

         <%
                        String usern = "User..";

            %>


    </head>
    <body style="margin:0px 0px 0px 0px;">


<table width="100%" height="80px;" border="0px" style="">

               <tr><td >

                        <p align="left"  style="font-family:Tempus Sans ITC;color:brown;font-size:30px;"><span><b> &nbsp;&nbsp; <img src="/LibMS-Struts/images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;"></b></span></td>
                    <td><p align="center"  style="font-family:Tempus Sans ITC;color:brown;font-size:20px;"><span><b> <%=library_name%></b></span></td>

                    <td align="right" width="250px" valign="top"><span style="font-family:arial;color:brown;font-size:12px;"><b>Hello [&nbsp;<%=username%>&nbsp;]&nbsp;&nbsp;&nbsp; <a href="/LibMS-Struts/admin/logout.jsp" style="text-decoration: none;color:brown">Sign Out</a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>




                </table>


        <!-- Namespace source file -->
        <script type="text/javascript" src="./build/yahoo/yahoo.js"></script>

        <!-- Dependency source files -->
        <script type="text/javascript" src="./build/event/event.js"></script>
        <script type="text/javascript" src="./build/dom/dom.js"></script>
        <script type="text/javascript" src="./build/dragdrop/dragdrop.js"></script>
        <script type="text/javascript" src="./build/animation/animation.js"></script>

        <!-- Container source file -->
        <script type="text/javascript" src="./build/container/container.js"></script>

        <!-- Menu source file -->
        <script type="text/javascript" src="./build/menu/menu.js"></script>


        <!-- Page-specific script -->
        <script type="text/javascript">

            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator.
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator when a MenuBarItem instance is selected.
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.SELECTED_SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator when a MenuBarItem instance is disabled.
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.DISABLED_SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            // "load" event handler for the window

            YAHOO.example.onWindowLoad = function(p_oEvent) {


                // "click" event handler for each item in the root MenuBar instance

                function onMenuBarItemClick(p_sType, p_aArguments) {

                    this.parent.hasFocus = true;

                    var oActiveItem = this.parent.activeItem;


                    // Hide any other submenus that might be visible

                    if(oActiveItem && oActiveItem != this) {

                        this.parent.clearActiveItem();

                    }


                    // Select and focus the current MenuItem instance

                    this.cfg.setProperty("selected", true);
                    this.focus();


                    // Show the submenu for this instance

                    var oSubmenu = this.cfg.getProperty("submenu");

                    if(oSubmenu) {

                        if(oSubmenu.cfg.getProperty("visible")) {

                            oSubmenu.hide();

                        }
                        else {

                            oSubmenu.show();

                        }

                    }

                }


                // "mouseover" event handler for each item in the root MenuBar instance

                function onMenuBarItemMouseOver(p_sType, p_aArguments) {

                    var oActiveItem = this.parent.activeItem;


                    // Hide any other submenus that might be visible

                    if(oActiveItem && oActiveItem != this) {

                        this.parent.clearActiveItem();

                    }


                    // Select and focus the current MenuItem instance

                    this.cfg.setProperty("selected", true);
                    this.focus();

                    if(this.parent.hasFocus) {

                        // Show the submenu for this instance

                        var oSubmenu = this.cfg.getProperty("submenu");

                        if(oSubmenu) {

                            if(oSubmenu.cfg.getProperty("visible")) {

                                oSubmenu.hide();

                            }
                            else {

                                oSubmenu.show();

                            }

                        }

                    }

                }






//Start:Serial  Menu *********************************************************************************


//Start: Third Level Serial Menu 3333333333333333333333333333333333333333333333333333333333333333333333
 var oSerRequestToVendorMenu = new YAHOO.widget.Menu("Request To Vendor");

                oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=ser_privilege[11]%>,  url: ""}));
                oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=ser_privilege[12]%>,  url: ""}));


                var oSerStatusUpdateMenu = new YAHOO.widget.Menu("Status Update");
                oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Vendor Request", {  disabled: <%=ser_privilege[14]%>,  url: ""}));
                oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Approval Status", {  disabled: <%=ser_privilege[15]%>,  url: ""}));




                var oSerViewSubscriptionListMenu = new YAHOO.widget.Menu("View Subscription List");

                oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=ser_privilege[19]%>,  url: ""}));
                oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=ser_privilege[20]%>,  url: ""}));



                var oSerBindingMenu = new YAHOO.widget.Menu("Binding");

                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Prepare Binding List", {  disabled: <%=ser_privilege[39]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Binding Order", {  disabled: <%=ser_privilege[40]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Binding Update", {  disabled: <%=ser_privilege[41]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Receive Binded Update", {  disabled: <%=ser_privilege[42]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Prepare Binding Slip", {  disabled: <%=ser_privilege[43]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {  disabled: <%=ser_privilege[44]%>,  url: ""}));



                var oSerAccessioningMenu = new YAHOO.widget.Menu("Accessioning");

                oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Generate Accession No", {  disabled: <%=ser_privilege[46]%>,  url: ""}));
                oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Change Accession No", {  disabled: <%=ser_privilege[47]%>,  url: ""}));
                oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {  disabled: <%=ser_privilege[48]%>,  url: ""}));



                var oSerArticleRegisterMenu = new YAHOO.widget.Menu("Article Register");

                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Add", {  disabled: <%=ser_privilege[51]%>,  url: ""}));
                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Modify", {  disabled: <%=ser_privilege[52]%>,  url: ""}));
                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Remove", {  disabled: <%=ser_privilege[53]%>,  url: ""}));



                var oSerArticleRetrievalMenu = new YAHOO.widget.Menu("Article Retrieval");

                oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Web Search", {  disabled: <%=ser_privilege[57]%>,  url: ""}));
                oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Article Document", {  disabled: <%=ser_privilege[58]%>,  url: ""}));
                oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Article Bibliography", {  disabled: <%=ser_privilege[59]%>,  url: ""}));


                var oSerMiscellaneousMenu = new YAHOO.widget.Menu("Miscellaneous");

                oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Multimedia", {  disabled: <%=ser_privilege[61]%>,  url: ""}));
                oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("List By Journal", {  disabled: <%=ser_privilege[62]%>,  url: ""}));
                oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Classified Subject", {  disabled: <%=ser_privilege[63]%>,  url: ""}));



//End: Third Level Serial Menu 3333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Serial Menu 2222222222222222222222222222222222222222222222222222222222222222222222

                var oSerSubscriptionListMenu = new YAHOO.widget.Menu("Prepare Subscription List");

                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Refer From Serial Catalogue", {  disabled: <%=ser_privilege[2]%>,  url:""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Refer From Demand List", {  disabled: <%=ser_privilege[3]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("New Entry", {  disabled: <%=ser_privilege[4]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Request For Specimen Copy", {  disabled: <%=ser_privilege[5]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("New Serial Status", {  disabled: <%=ser_privilege[6]%>,  url: ""}));

                var oSerApprovalProcessMenu = new YAHOO.widget.Menu("Approval Process");

                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Add Serial Into Approval List", {  disabled: <%=ser_privilege[8]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Approve/Reject Serial", {  disabled: <%=ser_privilege[9]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Request To Vendor", {    submenu:oSerRequestToVendorMenu  ,  disabled: <%=ser_privilege[10]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {    submenu:oSerStatusUpdateMenu ,  disabled: <%=ser_privilege[13]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("List Of Serials", {  disabled: <%=ser_privilege[16]%>,  url: ""}));


                var oSerOrderSubscriptionMenu = new YAHOO.widget.Menu("Order Subscription");

                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("View Subscription List", {submenu:oSerViewSubscriptionListMenu ,  disabled: <%=ser_privilege[18]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Initiate Subscription/Ordering", {  disabled: <%=ser_privilege[21]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Duplicate Subscription", {  disabled: <%=ser_privilege[22]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Update Subscription Detail", {  disabled: <%=ser_privilege[23]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Initial Old Subscription", {  disabled: <%=ser_privilege[24]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Renewal Subscription", {  disabled: <%=ser_privilege[25]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Cancel/Reorder Subscription", {  disabled: <%=ser_privilege[26]%>,  url: ""}));



                var oSerPaymentProcessMenu = new YAHOO.widget.Menu("Payment Process");

                oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {  disabled: <%=ser_privilege[29]%>,  url: ""}));
                oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("Payment update", {  disabled: <%=ser_privilege[30]%>,  url: ""}));



                var oSerIssueManagementMenu = new YAHOO.widget.Menu("Issues Management");

                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Registering Issue", {  disabled: <%=ser_privilege[33]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Additional Issue", {  disabled: <%=ser_privilege[34]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Annual Issue", {  disabled: <%=ser_privilege[35]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Claim Monitoring", {  disabled: <%=ser_privilege[36]%>,  url: ""}));


                var oSerBindingManagementMenu = new YAHOO.widget.Menu("Binding Management");

                oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Binding", {submenu:oSerBindingMenu ,  disabled: <%=ser_privilege[38]%>,  url: ""}));
                oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {submenu:oSerAccessioningMenu ,  disabled: <%=ser_privilege[45]%>,  url: ""}));



                var oSerArticleIndexingMenu = new YAHOO.widget.Menu("Article Indexing");

                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Register", {submenu:oSerArticleRegisterMenu ,  disabled: <%=ser_privilege[50]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Detail", {  disabled: <%=ser_privilege[54]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article List", {  disabled: <%=ser_privilege[55]%>,   url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Retrieval", {submenu:oSerArticleRetrievalMenu ,  disabled: <%=ser_privilege[56]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Miscellaneous", {submenu:oSerMiscellaneousMenu ,  disabled: <%=ser_privilege[60]%>,  url: ""}));





//End: Secondt Level Serial Menu 2222222222222222222222222222222222222222222222222222222222222222222222
//Start: First Level Serial Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//submenu of serial
                var oSerMenu = new YAHOO.widget.Menu("Serial", { zIndex:2 });

                oSerMenu.addItem(new YAHOO.widget.MenuItem(" Prepare Subscription List", {submenu:oSerSubscriptionListMenu ,  disabled: <%=ser_privilege[1]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Approval Process", {submenu:oSerApprovalProcessMenu ,  disabled: <%=ser_privilege[7]%>,  url:"" }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Order Subscription", {submenu:oSerOrderSubscriptionMenu ,  disabled: <%=ser_privilege[17]%>,  url:"" }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Receive Subscription", {   disabled: <%=ser_privilege[27]%>,  url:""}));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Payment Process", {submenu:oSerPaymentProcessMenu ,  disabled: <%=ser_privilege[28]%>,  url:""} ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Invoice Process", {  disabled: <%=ser_privilege[31]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Issues Management", {submenu:oSerIssueManagementMenu ,  disabled: <%=ser_privilege[32]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Binding Management", {submenu:oSerBindingManagementMenu ,  disabled: <%=ser_privilege[37]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Article Indexing", {submenu:oSerArticleIndexingMenu ,   disabled: <%=ser_privilege[49]%>,  url:"" } ));



//End: First Level Serial Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Serial  Menu *********************************************************************************


//Start:Circulation  Menu *********************************************************************************
//submenu of circulation
//Start: Third Level Circulation Menu 3333333333333333333333333333333333333333333333333333333333333333333333

 var oCirInstituteMemberMenu = new YAHOO.widget.Menu("Institute Member");

                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("Member Set", {  disabled: <%=cir_privilege[10]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("Daily", {  disabled: <%=cir_privilege[11]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("By Group", {  disabled: <%=cir_privilege[12]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("General", {  disabled: <%=cir_privilege[13]%>,  url: ""}));


                 var oCirReminderMenu = new YAHOO.widget.Menu("Reminder");

                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Overdue Reminder", {  disabled: <%=cir_privilege[51]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Collect Notice", {  disabled: <%=cir_privilege[52]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Bindery Order", {  disabled: <%=cir_privilege[53]%>,  url: ""}));


                 var oCirMemberRelatedParameterMenu = new YAHOO.widget.Menu("Member Related Parameter");

                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("Member Id Sequence", {  disabled: <%=cir_privilege[58]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("Member Category", {  disabled: <%=cir_privilege[59]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("Member Group", {  disabled: <%=cir_privilege[60]%>,  url: ""}));



                 var oCirRemoveExpiredRecordsMenu = new YAHOO.widget.Menu("Remove Expired record");

                 oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("Related To Members", {  disabled: <%=cir_privilege[69]%>,  url: ""}));
                 oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("Related To Reservation", {  disabled: <%=cir_privilege[70]%>,  url: ""}));


                 var oLibraryLoanRecordKeepingMenu = new YAHOO.widget.Menu("Record Keeping");

                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Document Details", {  disabled: <%=cir_privilege[73]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Modify Recieved Documents", {  disabled: <%=cir_privilege[74]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Return Documents", {  disabled: <%=cir_privilege[75]%>,  url: ""}));



                 var oLibraryLoanCirculationMenu = new YAHOO.widget.Menu("Circulation");

                 oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-Out", {  disabled: <%=cir_privilege[77]%>,  url: ""}));
                  oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-In", {  disabled: <%=cir_privilege[78]%>,  url: ""}));



                  var oLibraryLoanEnquiriesMenu  = new YAHOO.widget.Menu("Enquiries.");

                  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("ILL Documents", {  disabled: <%=cir_privilege[80]%>,  url: ""}));
                  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Documents Checked-Out", {  disabled: <%=cir_privilege[81]%>,  url: ""}));



                var oLibraryLoanReportMenu = new YAHOO.widget.Menu("Reports");

        	oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("ILL Documents", {  disabled: <%=cir_privilege[83]%>,  url: ""}));
                oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("Documents Checked-Out", {  disabled: <%=cir_privilege[84]%>,  url: ""}));






//End: Third Level Circulation Menu 3333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Circulation Menu 2222222222222222222222222222222222222222222222222222222222222222222222

                  var oCirMemberManagementMenu = new YAHOO.widget.Menu("Member Management");

                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Registration", {   disabled: <%=cir_privilege[2]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Temporary Registration", {   disabled: <%=cir_privilege[3]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Modify Records", {   disabled: <%=cir_privilege[4]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Cancellation", {   disabled: <%=cir_privilege[5]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Renewal", {   disabled: <%=cir_privilege[6]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Delinquent Member", {   disabled: <%=cir_privilege[7]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Member Statistics", {   disabled: <%=cir_privilege[8]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Institute Member", {submenu:oCirInstituteMemberMenu ,   disabled: <%=cir_privilege[9]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Scheme Allocation", {   disabled: <%=cir_privilege[14]%>,  url:"" }));



                  var oCirMembershipCardManagementMenu = new YAHOO.widget.Menu("Membership Card Management");

                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Generate Card", {  disabled: <%=cir_privilege[17]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Lost Card/Found Card", {  disabled: <%=cir_privilege[18]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Duplicate Card", {  disabled: <%=cir_privilege[19]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Remove Card", {  disabled: <%=cir_privilege[20]%>,  url: ""}));



                 var oCirFineManagementMenu = new YAHOO.widget.Menu("Finer Management");

                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Fine Collection", {  disabled: <%=cir_privilege[22]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Update Fine Detail", {  disabled: <%=cir_privilege[23]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Fine Notices", {  disabled: <%=cir_privilege[24]%>,  url: ""}));



                 var oCirCollectionManagementMenu = new YAHOO.widget.Menu("Collection Management");

                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Titles On Showcase/Display", {  disabled: <%=cir_privilege[26]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Missing Document", {  disabled: <%=cir_privilege[27]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Damaged Document", {  disabled: <%=cir_privilege[28]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Doicument", {  disabled: <%=cir_privilege[29]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Written Off Document", {  disabled: <%=cir_privilege[30]%>,  url: ""}));



                 var oCirBindingManagementMenu = new YAHOO.widget.Menu("Binding Management");

                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Prepare List For Binding", {  disabled: <%=cir_privilege[32]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Sent to Binder", {  disabled: <%=cir_privilege[33]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Update Binding Status", {  disabled: <%=cir_privilege[34]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Receive Binded Titles", {  disabled: <%=cir_privilege[35]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Written Off Document", {  disabled: <%=cir_privilege[36]%>,  url: ""}));



                 var oCirDocumentReservationMenu = new YAHOO.widget.Menu("Document Reservation");

                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Reserve Document", {  disabled: <%=cir_privilege[38]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Cancel Reservation", {  disabled: <%=cir_privilege[39]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Change Reserve Sequence", {  disabled: <%=cir_privilege[40]%>,  url: ""}));



                 var oCirCirculationEnquiriesMenu = new YAHOO.widget.Menu("Circulation Enquiry");

                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Members", {  disabled: <%=cir_privilege[42]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Collection", {  disabled: <%=cir_privilege[43]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Transaction Log", {  disabled: <%=cir_privilege[44]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Serial Based", {  disabled: <%=cir_privilege[45]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Statistics", {  disabled: <%=cir_privilege[46]%>,  url: ""}));



                var oCirCirculationReport = new YAHOO.widget.Menu("Circulation Report");

                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("On Member", {  disabled: <%=cir_privilege[48]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("On Fine", {  disabled: <%=cir_privilege[49]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Reminder", {   submenu:oCirReminderMenu ,  disabled: <%=cir_privilege[50]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Management Reporting", {  disabled: <%=cir_privilege[54]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Written off Document", {  disabled: <%=cir_privilege[55]%>,  url: ""}));



                var oCirSystemSetupMenu = new YAHOO.widget.Menu("System Setup");

                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Member Related Parameter", {submenu:oCirMemberRelatedParameterMenu ,  disabled: <%=cir_privilege[57]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Delinquency Reasons", {  disabled: <%=cir_privilege[61]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Cancellation Reasons", {  disabled: <%=cir_privilege[62]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Fine Categories", {  disabled: <%=cir_privilege[63]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Text of Notices For Member", {  disabled: <%=cir_privilege[64]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Parameter For Slips", {  disabled: <%=cir_privilege[65]%>,  url: ""}));



                var oCirHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");

                oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Transaction Log", {  disabled: <%=cir_privilege[67]%>,  url: ""}));
                oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Expired Records", {submenu:oCirRemoveExpiredRecordsMenu ,  disabled: <%=cir_privilege[70]%>,  url: ""}));



                 var oInterLibraryLoanMenu = new YAHOO.widget.Menu("Inter Library Loan");

               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Record keeping", {submenu:oLibraryLoanRecordKeepingMenu ,disabled: <%=cir_privilege[72]%>,  url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Circulation", {submenu :oLibraryLoanCirculationMenu ,  disabled: <%=cir_privilege[76]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Enquiries.", {submenu:oLibraryLoanEnquiriesMenu ,  disabled: <%=cir_privilege[79]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oLibraryLoanReportMenu ,  disabled: <%=cir_privilege[82]%>,   url: ""}));



//End: Secondt Level Circulation Menu 2222222222222222222222222222222222222222222222222222222222222222222222

//Start: First Level Circulation Menu 11111111111111111111111111111111111111111111111111111111111111111111111

               var oCirMenu = new YAHOO.widget.Menu("Circulation", { zIndex:2 });

                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member Management", {Submenu:oCirMemberManagementMenu ,   disabled: <%=cir_privilege[1]%>,  url:"" } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member Directory", {   disabled: <%=cir_privilege[15]%>,  url:""  } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Membership Card Management", {submenu:oCirMembershipCardManagementMenu ,   disabled: <%=cir_privilege[16]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Fine Management", {submenu:oCirFineManagementMenu ,   disabled: <%=cir_privilege[21]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Collection Management", {submenu: oCirCollectionManagementMenu ,   disabled: <%=cir_privilege[25]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Binding Management", {submenu:oCirBindingManagementMenu ,   disabled: <%=cir_privilege[31]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Document Reservation", {submenu:oCirDocumentReservationMenu ,   disabled: <%=cir_privilege[37]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Circulation Enquiries", {submenu:oCirCirculationEnquiriesMenu ,   disabled: <%=cir_privilege[41]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Circulation Report", {submenu:oCirCirculationReport ,   disabled: <%=cir_privilege[47]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {submenu:oCirSystemSetupMenu ,   disabled: <%=cir_privilege[56]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("House Keeping", {submenu:oCirHouseKeepingMenu ,    disabled: <%=cir_privilege[66]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Inter Library Loan", {submenu:oInterLibraryLoanMenu ,    disabled: <%=cir_privilege[71]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Serial Circulation", {   disabled: <%=cir_privilege[85]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member History", {   disabled: <%=cir_privilege[86]%>,  url:""  }));


//End: First Level Circulation Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Circulation  Menu *********************************************************************************


//Start:Cataloguing Menu *********************************************************************************
//submenu of cataloguing


//Start: Third Level Catalogue Menu 333333333333333333333333333333333333333333333333333333333333333333333333


                var oCatTitleHoldingmanagementMenu =  new YAHOO.widget.Menu("Title Holding Management");

                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Merge Titles", {  disabled: <%=cat_privilege[5]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Update Holding", {  disabled: <%=cat_privilege[6]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Remove Title", {  disabled: <%=cat_privilege[7]%>,  url: ""}));



                var oCatAccessionManagementMenu =  new YAHOO.widget.Menu("Accession Management");

                oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("Change Accession No", {  disabled: <%=cat_privilege[10]%>,  url: ""}));
                oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("Assign Accession No", {  disabled: <%=cat_privilege[11]%>,  url: ""}));



                var oCatTitleManagementMenu =  new YAHOO.widget.Menu("Title Management");

                oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("Enter Titles", {  disabled: <%=cat_privilege[13]%>,  url: ""}));
                oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("Update Titles", {  disabled: <%=cat_privilege[14]%>,  url: ""}));

//End: Third Level Catalogue Menu 33333333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Catalogue Menu 2222222222222222222222222222222222222222222222222222222222222222222222
                var oCatInitiateCataloguingMenu = new YAHOO.widget.Menu("Initiate Cataloguing");

                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Titles ready for Cataloguing", {  disabled: <%=cat_privilege[2]%>,  url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Old Title Entry", {  disabled: <%=cat_privilege[3]%>, url: "enter_title.html"}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Title Holding management", {submenu:oCatTitleHoldingmanagementMenu,  disabled: <%=cat_privilege[4]%>, url: "update_book_details.html"}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Subject Updates", {  disabled: <%=cat_privilege[8]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Accession Management", {submenu:oCatAccessionManagementMenu,  disabled: <%=cat_privilege[9]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Title Management", {submenu:oCatTitleManagementMenu,  disabled: <%=cat_privilege[12]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Multimedia Management", {  disabled: <%=cat_privilege[15]%>, url: ""}));



                var oCatLatestCognizanceMenu= new YAHOO.widget.Menu("Latest Cognizance");

                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Generate Latest Addition List", {  disabled: <%=cat_privilege[17]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Update Latest Addition List", {  disabled: <%=cat_privilege[18]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("View Latest Addition List", {  disabled: <%=cat_privilege[19]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Develop Bibliography", {  disabled: <%=cat_privilege[20]%>, url: ""}));



               var oCatReportsMenu = new YAHOO.widget.Menu("Catalogue Reports");

               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {  disabled: <%=cat_privilege[25]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("List Titles", {  disabled: <%=cat_privilege[26]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Subjects/Keywords", {  disabled: <%=cat_privilege[27]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Arrival Notice", {  disabled: <%=cat_privilege[28]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Classified Subjects", {  disabled: <%=cat_privilege[29]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Highly Priced titles", {  disabled: <%=cat_privilege[30]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Register", {  disabled: <%=cat_privilege[31]%>, url: ""}));

//End: Second Level Catalogue Menu 2222222222222222222222222222222222222222222222222222222222222222222222
//Start: First Level Catalogue Menu 11111111111111111111111111111111111111111111111111111111111111111111111
               var oCatMenu = new YAHOO.widget.Menu("Cataloguing", { zIndex:2 });

                oCatMenu.addItem(new YAHOO.widget.MenuItem("Initiate Cataloguing", {submenu:oCatInitiateCataloguingMenu ,   disabled: <%=cat_privilege[1]%>, url:""} ));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Latest Cognizance", {submenu:oCatLatestCognizanceMenu ,  disabled: <%=cat_privilege[16]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Print Catalogue Cards", {  disabled: <%=cat_privilege[21]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue System Setup", {  disabled: <%=cat_privilege[22]%>,url:"" }));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Data Import/Export", {   disabled: <%=cat_privilege[23]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue Reports", {submenu:oCatReportsMenu ,  disabled: <%=cat_privilege[24]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Annual Stock", {  disabled: <%=cat_privilege[32]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Database Backup", {  disabled: <%=cat_privilege[33]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Online Search", {  disabled: <%=cat_privilege[34]%>, url:""}));

//End: First Level Catalogue Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Cataloguing Menu *********************************************************************************

//************************************************Start acquisition Menu*********************************************



//Start:Third Level Acquisition Menu 333333333333333333333333333333333333333333333333333333333333333333333


                var oAcqRequestToVendorMenu = new YAHOO.widget.Menu("Request To Vendor");

                oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=acq_privilege[10]%>, url: ""}));
                oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=acq_privilege[11]%>,url: ""}));

                var oAcqStatusUpdateMenu =  new YAHOO.widget.Menu("Status Update");

                oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Vendor Request", {  disabled: <%=acq_privilege[13]%>, url: ""}));
                oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Approval Status", {  disabled: <%=acq_privilege[14]%>,url: ""}));



                var oAcqListTitlesMenu =  new YAHOO.widget.Menu("List Titles");

                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On-Approval Titles", {  disabled: <%=acq_privilege[16]%>, url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On Request To Vendor Titles", {  disabled: <%=acq_privilege[17]%>, url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Pending Titles", {  disabled: <%=acq_privilege[18]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Approved Titles", {  disabled: <%=acq_privilege[19]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Rejected Titles", {  disabled: <%=acq_privilege[20]%>,url: ""}));



                var oAcqViewListForOrderMenu = new YAHOO.widget.Menu("View List for Order");

                oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=acq_privilege[23]%>,url: ""}));
                oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("Approved Request ", {  disabled: <%=acq_privilege[24]%>,url: ""}));



                var oAcqListInvoicesMenu = new YAHOO.widget.Menu("List Invoices");

               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("All Invoices", {  disabled: <%=acq_privilege[35]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("With Library", {  disabled: <%=acq_privilege[36]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Unpaid Invoice", {  disabled: <%=acq_privilege[37]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", {  disabled: <%=acq_privilege[38]%>,url: ""}))

//End:Third Level Acquisition Menu 333333333333333333333333333333333333333333333333333333333333333333333


//Start:Second Level Acquisition Menu 22222222222222222222222222222222222222222222222222222222222222222222

                var oAcqGenerateTitleMenu = new YAHOO.widget.Menu("Generate Title");

                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Refer from Demend list/ Requests", { disabled: <%=acq_privilege[2]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Refer From Catalogue", { disabled: <%=acq_privilege[3]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("New Entry of Title", {  disabled: <%=acq_privilege[4]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Refer from Library Of Congress", { disabled: <%=acq_privilege[5]%>,url: ""}));



              var oAcqApprovalprocessMenu = new YAHOO.widget.Menu("Approval Process");

                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Add Title into Approval List", { disabled: <%=acq_privilege[7]%>, url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Approve/Rejects Title", {disabled: <%=acq_privilege[8]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Request to Vendor", {submenu:oAcqRequestToVendorMenu  ,disabled: <%=acq_privilege[9]%> , url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {submenu:oAcqStatusUpdateMenu  ,disabled: <%=acq_privilege[12]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("List Titles ", {submenu:oAcqListTitlesMenu ,disabled: <%=acq_privilege[15]%> ,url: ""}));



              var oAcqPlaceorderMenu = new YAHOO.widget.Menu("Place Order");

                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("View List For Order", {submenu:oAcqViewListForOrderMenu , disabled: <%=acq_privilege[22]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Generate Order", {disabled: <%=acq_privilege[25]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("List of Ordered Titles", {disabled: <%=acq_privilege[26]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notices", {disabled: <%=acq_privilege[27]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Cancel Order", {disabled: <%=acq_privilege[28]%> ,url: ""}));



                var oInvoiceManagementMenu = new YAHOO.widget.Menu("Invoice Management");

                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Process Invoice", {disabled: <%=acq_privilege[31]%> ,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {disabled: <%=acq_privilege[32]%>,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Payment Updates", {disabled: <%=acq_privilege[33]%> , url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("List Invoices", {submenu:oAcqListInvoicesMenu ,disabled: <%=acq_privilege[34]%> , url: ""}));



               var oAccessioningMenu =new YAHOO.widget.Menu("Accessioning");

               oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Process", {disabled: <%=acq_privilege[40]%> ,url: ""}));
               oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {disabled: <%=acq_privilege[41]%> ,url: ""}));



                var oAcqRecordkeepingMenu = new YAHOO.widget.Menu("Record Keeping");

                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Vendors", {disabled: <%=acq_privilege[45]%> ,url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Member's Set", {disabled: <%=acq_privilege[46]%> ,url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Update Subjects/Class No", {disabled: <%=acq_privilege[47]%> ,url: ""}));




              var oAcqReportMenu = new YAHOO.widget.Menu("Reports");{url: ""}

              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles in Acquisition", { disabled: <%=acq_privilege[49]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Requested Titles", { disabled: <%=acq_privilege[50]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles for Approval", { disabled: <%=acq_privilege[51]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Collect Notice", { disabled: <%=acq_privilege[52]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles For Ordering", { disabled: <%=acq_privilege[53]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Form", { disabled: <%=acq_privilege[54]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Vendors Directory", { disabled: <%=acq_privilege[55]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Sanction Request", { disabled: <%=acq_privilege[56]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", { disabled: <%=acq_privilege[57]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Status Request", { disabled: <%=acq_privilege[58]%>,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Check Delivery Notice", { disabled: <%=acq_privilege[59]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(Selective)", { disabled: <%=acq_privilege[60]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(All Titles)", { disabled: <%=acq_privilege[61]%> ,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", { disabled: <%=acq_privilege[62]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", { disabled: <%=acq_privilege[63]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Request to vendor", { disabled: <%=acq_privilege[64]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles by Control No", { disabled: <%=acq_privilege[65]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Acknowledge Letter", { disabled: <%=acq_privilege[66]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Supply Order", { disabled: <%=acq_privilege[67]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Approve Titles", { disabled: <%=acq_privilege[68]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Sanction Form", { disabled: <%=acq_privilege[69]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Amendment", { disabled: <%=acq_privilege[70]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Expenditure Analysis", { disabled: <%=acq_privilege[71]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order List", { disabled: <%=acq_privilege[72]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Receipt Voucher", { disabled: <%=acq_privilege[73]%>, url: ""}));



               var oAcqHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");

                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Invoice Record", {  disabled: <%=acq_privilege[75]%>, url: ""}));
                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Flushout Order Form", {  disabled: <%=acq_privilege[76]%>,  url: ""}));



                var oAcqBudgetManagementMenu=new YAHOO.widget.Menu("Budget Management")

                oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Budget Heads", {  disabled: <%=acq_privilege[78]%>,url: ""}));
                oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Currencies", {  disabled: <%=acq_privilege[79]%>,  url: ""}));
               oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Exchange Rates", {  disabled: <%=acq_privilege[80]%>, url: ""}));



                var oAcqSystemSetupMenu = new YAHOO.widget.Menu("System Setup");

                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {  disabled: <%=acq_privilege[82]%>, url: ""}));
                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Option", {  disabled: <%=acq_privilege[83]%>, url: ""}));



//End:Second Level Acquisition Menu 22222222222222222222222222222222222222222222222222222222222222222222

//Start: First Level Acquisition Menu1111111111111111111111111111111111111111111111111111111111111111111

                var oAcqMenu = new YAHOO.widget.Menu("Acquisition", { zIndex:2 });
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Generate Title", {submenu:oAcqGenerateTitleMenu , disabled: <%=acq_privilege[1]%> , url:"" } ));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Approval Process" , {submenu:oAcqApprovalprocessMenu , disabled: <%=acq_privilege[6]%> , url:""}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Place Order", {submenu:oAcqPlaceorderMenu , disabled:<%=acq_privilege[21]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Receive Order", { disabled:<%=acq_privilege[29]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Invoice Management", {submenu:oInvoiceManagementMenu , disabled:<%=acq_privilege[30]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {submenu:oAccessioningMenu , disabled: <%=acq_privilege[39]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Manage Demand List", { disabled: <%=acq_privilege[42]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Free/Giftfted Documents", { disabled:<%=acq_privilege[43]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Record Keeping", {submenu:oAcqRecordkeepingMenu , disabled:<%=acq_privilege[44]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem(" Acquisition Reports", {submenu:oAcqReportMenu ,  disabled:<%=acq_privilege[48]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("House Keping", {submenu:oAcqHouseKeepingMenu ,  disabled:<%=acq_privilege[74]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Budget Management", {submenu:oAcqBudgetManagementMenu , disabled:<%=acq_privilege[77]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {submenu:oAcqSystemSetupMenu ,  disabled:<%=acq_privilege[81]%> , url:"" }));


 //End First Level Acquisition Menu1111111111111111111111111111111111111111111111111111111111111111111111111111
//************************************End Acquisition Menu******************************************************


//sum menu of acquisition-staffAcc

                var oStaffAccMenu = new YAHOO.widget.Menu("StaffAcc");

                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Create Staff Account", {url: "/LibMS-Struts/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("View Staff Account", {url: "/LibMS-Struts/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Change Password", {url: "/LibMS-Struts/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff Account", {url: "/LibMS-Struts/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("View All Staff Account", {url: "/LibMS-Struts/view_all_account"}));

//submenu of acquisition-staff

                var oStaffMenu = new YAHOO.widget.Menu("Staff");

                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Register Staff", {url: "/LibMS-Struts/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Change Staff Details", {url: "/LibMS-Struts/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff", {url: "/LibMS-Struts/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("View Staff Record", {url: "/LibMS-Struts/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("View All Staff Record", {url: "/LibMS-Struts/viewall"}));


//submenu of acquisition-privilege
                var oPrivilegeMenu = new YAHOO.widget.Menu("Privilege");

                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Assign Privileges", {url: "/LibMS-Struts/admin/assign_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Change Privileges", {url: "/LibMS-Struts/admin/assign_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("View Privileges", {url: "/LibMS-Struts/admin/assign_privilege.jsp"}));
                



//submenu of Administrator
                var oAdminMenu = new YAHOO.widget.Menu("Administrator", { zIndex:2 });

                oAdminMenu.addItem(new YAHOO.widget.MenuItem("Staff", { submenu: oStaffMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("Staff Account", { submenu: oStaffAccMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("Privilege", { submenu: oPrivilegeMenu }));
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Cut", { url: "newhtml.html", helptext: "Ctrl + X", disabled: false }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Copy", { helptext: "Ctrl + C", disabled: true }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Paste", { helptext: "Ctrl + V" }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Delete", { helptext: "Del", disabled: true }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Select All", { helptext: "Ctrl + A" }), 2);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Find", { helptext: "Ctrl + F" }), 3);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Find Again", { helptext: "Ctrl + G" }), 3);


//*****************************************Start main menu*******************************************************
                var oMenuBar = new YAHOO.widget.MenuBar("menubar");

                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("HOME", {url:"/LibMS-Struts/admin/main.jsp"}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ADMINISTRATOR", { submenu: oAdminMenu , disabled:<%=privilege[5]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ACQUISITION", { submenu: oAcqMenu, disabled:<%=privilege[1]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CATALOGUING", { submenu: oCatMenu, disabled: <%=privilege[2]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CIRCULATION", { submenu: oCirMenu , disabled: <%=privilege[3]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SERIAL", { submenu: oSerMenu , disabled: <%=privilege[4]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SYSTEM SETUP", { disabled:<%=privilege[6]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("UTILITIES", { disabled: <%=privilege[7]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("OPAC", {url:"/LibMS-Struts/OPAC/OPACmain.jsp"}));
                oMenuBar.addItem("HELP");
//*****************************************End main menu*******************************************************
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("Examples Home", { url: "index.html" }));


                // Render the MenuBar instance and corresponding submenus

                oMenuBar.render(document.body);


                /*
                    Add a "click" and "mouseover" event handler to each item
                    in the root MenuBar instnace
                */

                var i = oMenuBar.getItemGroups()[0].length - 1,
                    oMenuBarItem;

                do {

                    oMenuBarItem = oMenuBar.getItem(i);

                    if(oMenuBarItem) {

                        oMenuBarItem.clickEvent.subscribe(
                                onMenuBarItemClick,
                                oMenuBarItem,
                                true
                            );

                        oMenuBarItem.mouseOverEvent.subscribe(
                                onMenuBarItemMouseOver,
                                oMenuBarItem,
                                true
                            );

                    }

                }
                while(i--);


                // "click" event handler for the document

                function onDocumentClick(p_oEvent) {

                    var oTarget = YAHOO.util.Event.getTarget(p_oEvent);

                    if(
                        oTarget != oMenuBar.element &&
                        !YAHOO.util.Dom.isAncestor(oMenuBar.element, oTarget)
                    ) {

                        oMenuBar.hasFocus = false;

                        if(oMenuBar.activeItem) {

                            var oSubmenu = oMenuBar.activeItem.cfg.getProperty("submenu");

                            if(oSubmenu) {

                                oSubmenu.hide();

                            }

                            oMenuBar.clearActiveItem();
                            oMenuBar.activeItem.blur();

                        }

                    }

                }


            }


            // Add a "load" handler for the window

            YAHOO.util.Event.addListener(window, "load", YAHOO.example.onWindowLoad);

        </script>

    </body>
</html>

