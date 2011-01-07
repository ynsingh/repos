<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" import="java.sql.*" %>

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Application Menubar</title>

    <font color="330000" size="5"> <div align="center">
            Library Management System(LibMS)</div> </font>
       <%
       String username=(String)session.getAttribute("username");
       String library_id=(String)session.getAttribute("library_id");
       ResultSet rst1=(ResultSet)session.getAttribute("privilege_resultset");
       ResultSet rst2=(ResultSet)session.getAttribute("acq_privilege_resultset");
       ResultSet rst3=(ResultSet)session.getAttribute("cat_privilege_resultset");
       ResultSet rst4=(ResultSet)session.getAttribute("cir_privilege_resultset");
       ResultSet rst5=(ResultSet)session.getAttribute("ser_privilege_resultset");

       //out.println(staff_id);
      
       String acquisition=rst1.getString(2);
       String cataloguing=rst1.getString(3);
       String circulation=rst1.getString(4);
       String serial=rst1.getString(5);
       String system_setup=rst1.getString(6);
       String utility=rst1.getString(7);
       String administrator=rst1.getString(8);


String acq2=rst2.getString(2);
//acquisition privilege fields
String acq3=rst2.getString(3);
String acq4=rst2.getString(4);
String acq5=rst2.getString(5);
String acq6=rst2.getString(6);
String acq7=rst2.getString(7);
String acq8=rst2.getString(8);
String acq9=rst2.getString(9);
String acq10=rst2.getString(10);
String acq11=rst2.getString(11);
String acq12=rst2.getString(12);
String acq13=rst2.getString(13);
String acq14=rst2.getString(14);
String acq15=rst2.getString(15);
String acq16=rst2.getString(16);
String acq17=rst2.getString(17);
String acq18=rst2.getString(18);
String acq19=rst2.getString(19);
String acq20=rst2.getString(20);
String acq21=rst2.getString(21);
String acq22=rst2.getString(22);
String acq23=rst2.getString(23);
String acq24=rst2.getString(24);

//cataloguing privilege fields
String cat2=rst3.getString(2);
//out.println(cat2);
String cat3=rst3.getString(3);
String cat4=rst3.getString(4);
String cat5=rst3.getString(5);
String cat6=rst3.getString(6);
String cat7=rst3.getString(7);
String cat8=rst3.getString(8);
String cat9=rst3.getString(9);
String cat10=rst3.getString(10);
String cat11=rst3.getString(11);
String cat12=rst3.getString(12);
String cat13=rst3.getString(13);
String cat14=rst3.getString(14);
String cat15=rst3.getString(15);
String cat16=rst3.getString(16);
String cat17=rst3.getString(17);

//circulation privilege fields
String cir2=rst4.getString(2);
String cir3=rst4.getString(3);
String cir4=rst4.getString(4);
String cir5=rst4.getString(5);
String cir6=rst4.getString(6);
String cir7=rst4.getString(7);
String cir8=rst4.getString(8);
String cir9=rst4.getString(9);
String cir10=rst4.getString(10);
String cir11=rst4.getString(11);
String cir12=rst4.getString(12);
String cir13=rst4.getString(13);
String cir14=rst4.getString(14);
String cir15=rst4.getString(15);
String cir16=rst4.getString(16);
String cir17=rst4.getString(17);
String cir18=rst4.getString(18);
String cir19=rst4.getString(19);
String cir20=rst4.getString(20);
String cir21=rst4.getString(21);

//circulation privilege fields
String ser2=rst5.getString(2);
String ser3=rst5.getString(3);
String ser4=rst5.getString(4);
String ser5=rst5.getString(5);
String ser6=rst5.getString(6);
String ser7=rst5.getString(7);
String ser8=rst5.getString(8);
String ser9=rst5.getString(9);
String ser10=rst5.getString(10);
String ser11=rst5.getString(11);

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
                        String usern = (String) session.getAttribute("login_name");

            %>
           
            <p align="left"><font color="blue"><b>Hello <%=username%></b></font></p>
            <P align="right"><a href="logout.jsp"><font size="2">Sign Out</font></a></P>

    </head>
    <body>

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

                var oAcqRecordkeepingMenu = new YAHOO.widget.Menu("Record Keeping");

                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Vendors", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Budget Heads", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Currencies", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Exchange Rates", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Budgets", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Notices Taxt", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Accession Details", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Order cancellation", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Update Subjects/Class No", {url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Member's Set(Indenters)", {url: ""}));




                 var oAcqTitlesMenu = new YAHOO.widget.Menu("Enquiries");
                 
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("In Acquisition", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("On Request", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Sent for Approval", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Approved", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Rejected", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Ordered", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("For Accessioning", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Routing Status", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {url: ""}));
                 oAcqTitlesMenu.addItem(new YAHOO.widget.MenuItem("Titles By Subject", {url: ""}));
                 




               var oAcqOrdersMenu = new YAHOO.widget.Menu("Orders");

               oAcqOrdersMenu.addItem(new YAHOO.widget.MenuItem("Order Details", {url: ""}));
               oAcqOrdersMenu.addItem(new YAHOO.widget.MenuItem("Pending Orders", {url: ""}));
               oAcqOrdersMenu.addItem(new YAHOO.widget.MenuItem("Overdue Orders", {url: ""}));
               oAcqOrdersMenu.addItem(new YAHOO.widget.MenuItem("All Orders", {url: ""}));





              var oAcqInvoicesMenu = new YAHOO.widget.Menu("Invoices");

               oAcqInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Invoice details", {url: ""}));
               oAcqInvoicesMenu.addItem(new YAHOO.widget.MenuItem("With Library", {url: ""}));
               oAcqInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Upload Invoices", {url: ""}));
               oAcqInvoicesMenu.addItem(new YAHOO.widget.MenuItem("All Invoices", {url: ""}));




               var oAcqVendorsMenu = new YAHOO.widget.Menu("Vendors");

               oAcqVendorsMenu.addItem(new YAHOO.widget.MenuItem("Directory", {url: ""}));
               oAcqVendorsMenu.addItem(new YAHOO.widget.MenuItem("Titles for Approval", {url: ""}));
               oAcqVendorsMenu.addItem(new YAHOO.widget.MenuItem("Pending Titles", {url: ""}));
               oAcqVendorsMenu.addItem(new YAHOO.widget.MenuItem("Orders", {url: ""}));
               oAcqVendorsMenu.addItem(new YAHOO.widget.MenuItem("Invoices", {url: ""}));






                var oEnquiryMenu = new YAHOO.widget.Menu("Enquiries");

                oEnquiryMenu.addItem(new YAHOO.widget.MenuItem("Titles", {submenu:oAcqTitlesMenu , url: ""}));
                oEnquiryMenu.addItem(new YAHOO.widget.MenuItem("Orders", {submenu:oAcqOrdersMenu , url: ""}));
                oEnquiryMenu.addItem(new YAHOO.widget.MenuItem("Invoices", {submenu:oAcqInvoicesMenu , url: ""}));
                oEnquiryMenu.addItem(new YAHOO.widget.MenuItem("vendors", {submenu:oAcqVendorsMenu , url: ""}));


                var oBudgetMenu = new YAHOO.widget.Menu("Budget");

                oBudgetMenu.addItem(new YAHOO.widget.MenuItem("List Titles", {url: ""}));
                oBudgetMenu.addItem(new YAHOO.widget.MenuItem("Expenditure Analysis", {url: ""}))





               var oAcqListInvoicesMenu = new YAHOO.widget.Menu("List Invoices");

               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("All Invoices", {url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("With Library", {url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Unpaid Invoice", {url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", {url: ""}))




                var oInvoicingMenu = new YAHOO.widget.Menu("Invoicing/Accessoning");

                oInvoicingMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {url: ""}));
                 oInvoicingMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {disabled: <%=acq11%>,url: ""}));
                 oInvoicingMenu.addItem(new YAHOO.widget.MenuItem("Payment Updates", {disabled: <%=acq6%> , url: ""}));
                 oInvoicingMenu.addItem(new YAHOO.widget.MenuItem("List Invoices", {submenu:oAcqListInvoicesMenu , url: ""}));
                 oInvoicingMenu.addItem(new YAHOO.widget.MenuItem("Accessioning Register", {url: ""}));




                 var oPlaceorderMenu = new YAHOO.widget.Menu("Place Order");

                 oPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Develop Order", {url: ""}));
                 oPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("List Order", {url: ""}));
                 oPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Titles for Orderinng", {url: ""}));
                 oPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notices", {url: ""}));




                  var oAcqListTitlesMenu = new YAHOO.widget.Menu("List Titles");

                 oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On Approval Titles", {url: ""}));
                 oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On Request Titles", {url: ""}));
                 oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("For approval", {url: ""}));
                 oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Approved", {url: ""}));
                 oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("rejected", {url: ""}));




                 var oApprovalprocessMenu = new YAHOO.widget.Menu("Approval Process");

                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Request to Vendor", { disabled: <%=acq11%> , url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("List For Approval", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Return Items", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Request to Vendor", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("List For Approval", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Return Items", {url: ""}));
                 oApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("List Titles", {submenu:oAcqListTitlesMenu , url: ""}));





                var oAcqUserRequestMenu = new YAHOO.widget.Menu("Enter Title");

                oAcqUserRequestMenu.addItem(new YAHOO.widget.MenuItem("Review", {url: ""}));
                oAcqUserRequestMenu.addItem(new YAHOO.widget.MenuItem("Data Import", {url: ""}));
                oAcqUserRequestMenu.addItem(new YAHOO.widget.MenuItem("List", {url: ""}));




                var oEntertitleMenu = new YAHOO.widget.Menu("Enter Title");

                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("Data Entry", {url: ""}));
                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("Gift/Adhock", {url: ""}));
                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("User Request", {submenu:oAcqUserRequestMenu , url: ""}));
                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("List Titles", {url: ""}));
                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("Update Title Details", {url: ""}));
                oEntertitleMenu.addItem(new YAHOO.widget.MenuItem("Data Import", {url: ""}));








           var oUnioncatalogueMenu  = new YAHOO.widget.Menu("Union Catalogue");

           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("Journals", {url: ""}));
           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("Locations", {url: ""}));
           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("Journal Location Details", {url: ""}));
           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("Journal by Subject", {url: ""}));
           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("Journal by Location", {url: ""}));
           oUnioncatalogueMenu .addItem(new YAHOO.widget.MenuItem("List By Name", {url: ""}));






           var oCirculationMenu  = new YAHOO.widget.Menu("Circulation");

           oCirculationMenu .addItem(new YAHOO.widget.MenuItem("Check-out", {url: "check_out1.jsp"}));
           oCirculationMenu .addItem(new YAHOO.widget.MenuItem("Check-in", {url: ""}));
           oCirculationMenu .addItem(new YAHOO.widget.MenuItem("Renewal", {url: ""}));




            var oStockverificationMenu  = new YAHOO.widget.Menu("House Keeping");

             oStockverificationMenu .addItem(new YAHOO.widget.MenuItem("Initiate Stock Verification", {url: ""}));
             oStockverificationMenu .addItem(new YAHOO.widget.MenuItem("Verification", {url: ""}));
             oStockverificationMenu .addItem(new YAHOO.widget.MenuItem("Verification Report", {url: ""}));




             var oHousekeepingMenu  = new YAHOO.widget.Menu("House Keeping");

             oHousekeepingMenu .addItem(new YAHOO.widget.MenuItem("Redevelop Receipt File", {url: ""}));
             oHousekeepingMenu .addItem(new YAHOO.widget.MenuItem("Remove Records", {url: ""}));
             oHousekeepingMenu .addItem(new YAHOO.widget.MenuItem("Holding Summary", {url: ""}));
            oHousekeepingMenu .addItem(new YAHOO.widget.MenuItem("Create Serials Biblio Data", {url: ""}));
            oHousekeepingMenu.addItem(new YAHOO.widget.MenuItem("Rebuild Inverted Files", {url: ""}));







             var oReportMiscellaneousMenu  = new YAHOO.widget.Menu("Report Miscellaneous");

             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Vendors Directory", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Recent Arrivals", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Invoice Register", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Binding Register", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Specimen Request", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Serials By Location", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("BBound Volume/Issues", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Serials for Approval", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Serials for Ordering", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Unbilled Serials", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Supply Order", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Subscribed Serials", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Binding Details", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Exception Serials", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("New Serials", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Bound Volumes Check List", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Issues Check List", {url: ""}));
             oReportMiscellaneousMenu   .addItem(new YAHOO.widget.MenuItem("Document Slip", {url: ""}));






             var oCheckoutReportMenu  = new YAHOO.widget.Menu("Check-out Report");

             oCheckoutReportMenu  .addItem(new YAHOO.widget.MenuItem("By Member", {url: "check_out1.jsp"}));
             oCheckoutReportMenu  .addItem(new YAHOO.widget.MenuItem("By Serial", {url: "check_out1.jsp"}));




             var oSerialNoticesMenu  = new YAHOO.widget.Menu("Serial Notices");

             oSerialNoticesMenu .addItem(new YAHOO.widget.MenuItem("Approval Form", {url: ""}));
             oSerialNoticesMenu .addItem(new YAHOO.widget.MenuItem("Order Form", {url: ""}));
             oSerialNoticesMenu .addItem(new YAHOO.widget.MenuItem("Payment Request", {url: ""}));
             oSerialNoticesMenu .addItem(new YAHOO.widget.MenuItem("Check Delivery Notice", {url: ""}));
             oSerialNoticesMenu .addItem(new YAHOO.widget.MenuItem("Binding Order", {url: ""}));




             var oOnserialsMenu  = new YAHOO.widget.Menu(" On Serial");

             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("List by Title", {url: ""}));
             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("Current Serials", {url: ""}));
             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("List by Publisher", {url: ""}));
             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("Special Issues", {url: ""}));
             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("Duplicate Issues", {url: ""}));
             oOnserialsMenu .addItem(new YAHOO.widget.MenuItem("Missing/Overdue Issues", {url: ""}));




             var oSerialReportsMenu  = new YAHOO.widget.Menu(" Serial Reports");

             oSerialReportsMenu .addItem(new YAHOO.widget.MenuItem("Report Miscellaneous", {submenu:oReportMiscellaneousMenu , url: ""}));
             oSerialReportsMenu .addItem(new YAHOO.widget.MenuItem("On Serials", {submenu:oOnserialsMenu , url: ""}));
             oSerialReportsMenu .addItem(new YAHOO.widget.MenuItem("Serial Notices", {submenu:oSerialNoticesMenu , url: ""}));
             oSerialReportsMenu .addItem(new YAHOO.widget.MenuItem("Check-out Reports", {submenu:oCheckoutReportMenu  , url: ""}));









             var oEnquirymiscellaneousMenu   = new YAHOO.widget.Menu("Enquiry Miscellaneous");

             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Serials in Binding", {url: ""}));
             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Recent Arrivals", {url: ""}));
             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Unbilled Serials", {url: ""}));
             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Serials by Subject ", {url: ""}));
             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Serials List", {url: ""}));
             oEnquirymiscellaneousMenu .addItem(new YAHOO.widget.MenuItem("Special Issues", {url: ""}));




             var oSerialcheckoutMenu   = new YAHOO.widget.Menu("Serial Check Out");

             oSerialcheckoutMenu .addItem(new YAHOO.widget.MenuItem("By Member", {url: ""}));
             oSerialcheckoutMenu .addItem(new YAHOO.widget.MenuItem("By Serial", {url: ""}));
             oSerialcheckoutMenu .addItem(new YAHOO.widget.MenuItem("Issues Checked-out", {url: ""}));




               var oByVendorlMenu  = new YAHOO.widget.Menu("By Vendor");

               oByVendorlMenu .addItem(new YAHOO.widget.MenuItem("Directory", {url: ""}));
               oByVendorlMenu .addItem(new YAHOO.widget.MenuItem("Current Serials", {url: ""}));
               oByVendorlMenu .addItem(new YAHOO.widget.MenuItem("Orders", {url: ""}));
               oByVendorlMenu .addItem(new YAHOO.widget.MenuItem("Invoices", {url: ""}));





               var oOnserialMenu  = new YAHOO.widget.Menu("On Serial");

               oOnserialMenu .addItem(new YAHOO.widget.MenuItem("Serial details", {url: ""}));
               oOnserialMenu .addItem(new YAHOO.widget.MenuItem("New Serials", {url: "new_serials.jsp"}));
               oOnserialMenu .addItem(new YAHOO.widget.MenuItem("Renewed Serials", {url: ""}));
               oOnserialMenu .addItem(new YAHOO.widget.MenuItem("Current Serials", {url: ""}));
               oOnserialMenu .addItem(new YAHOO.widget.MenuItem("Missing/Overdue/Replaceable serial", {url: ""}));






               var oEnquiriesMenu  = new YAHOO.widget.Menu("Enquiries");

               oEnquiriesMenu .addItem(new YAHOO.widget.MenuItem("On Serials", {submenu:oOnserialMenu , url: ""}));
               oEnquiriesMenu .addItem(new YAHOO.widget.MenuItem("by Vendor", {submenu:oByVendorlMenu , url: ""}));
               oEnquiriesMenu .addItem(new YAHOO.widget.MenuItem("Expenditure Analysis(Budget Head)", { url: ""}));
               oEnquiriesMenu .addItem(new YAHOO.widget.MenuItem("Serial Check-out", {submenu:oSerialcheckoutMenu , url: ""}));
               oEnquiriesMenu .addItem(new YAHOO.widget.MenuItem("Enquiry Miscellaneous", {submenu:oEnquirymiscellaneousMenu , url: ""}));








             var oSdiMenu  = new YAHOO.widget.Menu("SDI");

             oSdiMenu .addItem(new YAHOO.widget.MenuItem("Subujects/Strategies", {url: ""}));
             oSdiMenu .addItem(new YAHOO.widget.MenuItem("Intrest Profile", {url: ""}));
             oSdiMenu .addItem(new YAHOO.widget.MenuItem("Develop SDI", {url: ""}));




              var oAIMiscellaneousMenu  = new YAHOO.widget.Menu("AI Miscellaneous");

              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Update Inverted Files", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Rebuild Inverted Files", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Print Keywords", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Data Import", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Develop File", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Data Export", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Remove Files", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Subject Updates", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Search Fields", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Multimedia", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("List by Journals", {url: ""}));
              oAIMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Classified Subjects", {url: ""}));




               var oRetrivalMenu  = new YAHOO.widget.Menu("Retrival");

               oRetrivalMenu.addItem(new YAHOO.widget.MenuItem("Online Searches", {url: ""}));
               oRetrivalMenu.addItem(new YAHOO.widget.MenuItem("Documentation", {url: ""}));
               oRetrivalMenu.addItem(new YAHOO.widget.MenuItem("Bibliography", {url: ""}));





               var oMaintenanceMenu = new YAHOO.widget.Menu("Maintenance");

               oMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Add", {url: ""}));
               oMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Modify", {url: ""}));
               oMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Remove", {url: ""}));
               oMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Details", {url: ""}));
               oMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Check List", {url: ""}));





               var oArticleindexingMenu = new YAHOO.widget.Menu("Article Indexing");

                oArticleindexingMenu.addItem(new YAHOO.widget.MenuItem("Maintenance", {submenu:oMaintenanceMenu , url: ""}));
                 oArticleindexingMenu.addItem(new YAHOO.widget.MenuItem("Retrivals", {submenu:oRetrivalMenu ,url: ""}));
                 oArticleindexingMenu.addItem(new YAHOO.widget.MenuItem("AI Miscellaneous", {submenu:oAIMiscellaneousMenu ,url: ""}));
                 oArticleindexingMenu.addItem(new YAHOO.widget.MenuItem("SDI", {submenu:oSdiMenu  ,url: ""}));




                 var oRecordkeepingMenu  = new YAHOO.widget.Menu("Record Keeping");

                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Serial Details", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Update Loose Issues", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("History Status", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Vendor/Publisher", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Budget Heads", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Currencies", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Exchange Rates", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Budgets", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Serial languages", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Serial Type", {url: ""}));
                 oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Types of Binding", {url: ""}));
                oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Subscription Modes", {url: ""}));
                oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Notices Texts", {url: ""}));
                oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Routing Details", {url: ""}));
                oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("ERS Link/Scanning", {url: ""}));
                oRecordkeepingMenu .addItem(new YAHOO.widget.MenuItem("Status Update(Issues)", {url: ""}));







              var oBindingvolumeMenu = new YAHOO.widget.Menu("Binding/Bind Volume Collection");

             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Binding Update", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Binding Order", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("To Collection", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Accessioning(Back Vols)", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Chyange Accession No", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Bindery Slip", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Sent For Repair", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {url: ""}));
             oBindingvolumeMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {url: ""}));




              var oClaimsmonitoringMenu = new YAHOO.widget.Menu("Claims Monitoring");

               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("Schedule Updates", {url: ""}));
               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("Reminders", {url: ""}));
               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("Reminders(Specific Serial)", {url: ""}));
               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("Missing/Overdue/Replaceable Issues", {url: ""}));
               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("List Reminders", {url: ""}));
               oClaimsmonitoringMenu.addItem(new YAHOO.widget.MenuItem("Credit/Refund Note", {url: ""}));





                var oIssuemanagementMenu = new YAHOO.widget.Menu("Issue Management");

                 oIssuemanagementMenu.addItem(new YAHOO.widget.MenuItem("Registering Issues", {url: ""}));
                 oIssuemanagementMenu.addItem(new YAHOO.widget.MenuItem("Additional Issues", {url: ""}));
                 oIssuemanagementMenu.addItem(new YAHOO.widget.MenuItem("Annual Issue", {url: ""}));
                oIssuemanagementMenu.addItem(new YAHOO.widget.MenuItem("Claims Monitoring", {submenu:oClaimsmonitoringMenu , url: ""}));
                 oIssuemanagementMenu.addItem(new YAHOO.widget.MenuItem("Binding/Bind Volume Collection", {submenu:oBindingvolumeMenu , url: ""}));





               var oRenewalMenu = new YAHOO.widget.Menu(" Renewal");

               oRenewalMenu .addItem(new YAHOO.widget.MenuItem("Initiate Approval Process", { url: ""}));
               oRenewalMenu .addItem(new YAHOO.widget.MenuItem("Approval Status Update", { url: ""}));
               oRenewalMenu .addItem(new YAHOO.widget.MenuItem("Ordering/Subscription", { url: ""}));
               oRenewalMenu .addItem(new YAHOO.widget.MenuItem("Update Subscription Details", { url: ""}));
               oRenewalMenu .addItem(new YAHOO.widget.MenuItem("Renewal Serials", { url: ""}));





               var oNewsubscriptionMenu = new YAHOO.widget.Menu(" New Subscription");

                oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("New Serial Details", { url: "new_serials.jsp"}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Request Specimen Copy", { url: "request_spicemenmain.jsp"}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Initiate Approval Process", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Approval Status Update", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Ordering/Subscription", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Update Subscription Details", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("New Serial Status", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Duplicate Subscription", { url: ""}));
                 oNewsubscriptionMenu .addItem(new YAHOO.widget.MenuItem("Initiate Old Subscription", { url: ""}));



                var oSubscriptionMenu = new YAHOO.widget.Menu("Subscription");

                oSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("New Subscription", {submenu:oNewsubscriptionMenu , url: ""}));
                oSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Renewal", {submenu:oRenewalMenu , url: ""}));
                oSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Invoicing", {url: ""}));
                oSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {url: ""}));
                oSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Payment Update", {url: ""}));


//sum menu of acquisition-staffAcc

                var oStaffAccMenu = new YAHOO.widget.Menu("StaffAcc");

                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Create Staff Account", {url: "createaccount.html"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("View Staff Account", {url: ""}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Change Password", {url: "manage_account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff Account", {url: ""}));


//submenu of acquisition-staff

                var oStaffMenu = new YAHOO.widget.Menu("Staff");

                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Register Staff", {url: "acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Change Staff Details", {url: "acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff", {url: "acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("View Staff Record", {url: "acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("View All Staff Record", {url: "viewstaff.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("Staff Account", { submenu: oStaffAccMenu }));

//submenu of acquisition-privilege
                var oPrivilegeMenu = new YAHOO.widget.Menu("Privilege");

                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Assign Privileges", {url: "set_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Change Privileges", {url: "set_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("View Privileges", {url: "set_privilege.jsp"}));

//submenu of serial
                var oSerMenu = new YAHOO.widget.Menu("Serial", { zIndex:2 });

                oSerMenu.addItem(new YAHOO.widget.MenuItem("Subscription", {submenu:oSubscriptionMenu ,disabled: <%=ser2%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Issue Management", {submenu:oIssuemanagementMenu , disabled: <%=ser3%> }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Article Indexing", {submenu:oArticleindexingMenu , disabled: <%=ser4%> }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Record Keeping", {submenu:oRecordkeepingMenu , disabled: <%=ser5%>}));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Enquiries", {submenu:oEnquiriesMenu ,   disabled: <%=ser6%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oSerialReportsMenu ,url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {  disabled: <%=ser7%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("House Keeping", {submenu:oHousekeepingMenu , disabled: <%=ser8%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Stock Verification", {submenu:oStockverificationMenu ,   disabled: <%=ser9%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Union Catalogue", {submenu:oUnioncatalogueMenu ,  disabled: <%=ser10%> } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("Serial Circulation", {  disabled: <%=ser11%> } ));
               // oSerMenu.addItem(new YAHOO.widget.MenuItem(" Online Search", {submenu:oCirculationMenu   } ));




//submenu of circulation


               var oMemberStatisticsMenu = new YAHOO.widget.Menu("Member Statistics");

                     oMemberStatisticsMenu.addItem(new YAHOO.widget.MenuItem("Member Set", {url: ""}));
                     oMemberStatisticsMenu.addItem(new YAHOO.widget.MenuItem("Daily", {url: ""}));
                     oMemberStatisticsMenu.addItem(new YAHOO.widget.MenuItem("By Group", {url: ""}));
                     oMemberStatisticsMenu.addItem(new YAHOO.widget.MenuItem("General", {url: ""}));






               var oMemberRecordsMenu  = new YAHOO.widget.Menu("Member Records");

                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Registration", {url: "add_member1.jsp"}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Temporary Member", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Modify Record", {url: "change_member_record1.jsp"}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Cancellation", {url: "cancel_registration1.jsp"}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Renewal", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Delinquent Member", {url: "delinquent_member0.jsp"}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Lost Card", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Card Found", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Duplicate Card", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Remove Card", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Institute Member", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Directory", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Member Statistics", {submenu:oMemberStatisticsMenu , url: ""}));
                  oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Schemes Allocation", {url: ""}));
                 oMemberRecordsMenu.addItem(new YAHOO.widget.MenuItem("Update Payment Details", {url: ""}));




               var oCollectionUpdatesMenu   = new YAHOO.widget.Menu("Collection Update");

                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Titles on Display", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Copy Missing", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Copy Damaged", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Copy", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Written-Off-Copy", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("For Binding", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Sent to Binder", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Return to Shelf", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("On Display to Shelf", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Binding to Shelf", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Copy Status", {url: ""}));
                 oCollectionUpdatesMenu.addItem(new YAHOO.widget.MenuItem("Directory", {url: ""}));




              var oLibraryLoanRecordKeepingMenu = new YAHOO.widget.Menu("Record Keeping");

                        oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Item Details", {url: ""}));
                 	oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Modify Recieved Items", {url: ""}));
                 	oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Return Items", {url: ""}));




               var oLibraryLoanCirculationMenu = new YAHOO.widget.Menu("Circulation");

                     oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-Out", {url: "check_out1.jsp"}));
                     oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-In", {url: ""}));



               var oLibraryLoanEnquiriesMenu  = new YAHOO.widget.Menu("Enquiries.");

                        oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("ILL Items", {url: ""}));
                 	oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Items Checked-Out", {url: ""}));




                 var oLibraryLoanReportMenu = new YAHOO.widget.Menu("Reports");

        	        oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("ILL Items", {url: ""}));
                	oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("Items Checked-Out", {url: ""}));




               var oInterLibraryLoanMenu = new YAHOO.widget.Menu("Inter Library Loan");

               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Record keeping", {submenu:oLibraryLoanRecordKeepingMenu , url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Circulation", {submenu :oLibraryLoanCirculationMenu , url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Enquiries.", {submenu:oLibraryLoanEnquiriesMenu , url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oLibraryLoanReportMenu , url: ""}));




               var oReservationMenu = new YAHOO.widget.Menu("Reservation");

                 oReservationMenu.addItem(new YAHOO.widget.MenuItem("Put On Reserve", {url: ""}));
                 oReservationMenu.addItem(new YAHOO.widget.MenuItem("Cancel Reserve", {url: ""}));
                 oReservationMenu.addItem(new YAHOO.widget.MenuItem("Alter Reserve Sequence", {url: ""}));





                var oEnquiryStatisticsMenu = new YAHOO.widget.Menu("Statistics");

                      oEnquiryStatisticsMenu.addItem(new YAHOO.widget.MenuItem("by Subject", {url: ""}));
                      oEnquiryStatisticsMenu.addItem(new YAHOO.widget.MenuItem("by Category", {url: ""}));
                      oEnquiryStatisticsMenu.addItem(new YAHOO.widget.MenuItem("Hourly", {url: ""}));




                 var oOnMembersMenu = new YAHOO.widget.Menu("On Member");

                        oOnMembersMenu.addItem(new YAHOO.widget.MenuItem("Check Outs", {url: ""}));
                 	oOnMembersMenu.addItem(new YAHOO.widget.MenuItem("Reserves", {url: ""}));
                	oOnMembersMenu.addItem(new YAHOO.widget.MenuItem("Delinquent Member", {url: ""}));
                 	oOnMembersMenu.addItem(new YAHOO.widget.MenuItem("Directory", {url: ""}));





                    var oOnCollectionMenu = new YAHOO.widget.Menu("On Collection");

 	                 oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Copies", {url: ""}));
        	         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Reserves", {url: ""}));
                	 oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Copy Status", {url: ""}));
                         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Lost", {url: ""}));
             	         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Damaged", {url: ""}));
 	       	         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Missing", {url: ""}));
               	         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("For Binding", {url: ""}));
                         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("On Display", {url: ""}));
                         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn", {url: ""}));
                         oOnCollectionMenu.addItem(new YAHOO.widget.MenuItem("Check-Out", {url: "check_out1.jsp"}));




                 var oCirculationEnquiriesMenu = new YAHOO.widget.Menu("Circulation Enquiries");

                 oCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("On Members", {submenu:oOnMembersMenu , url: ""}));
                 oCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("On Collection", {submenu:oOnCollectionMenu , url: ""}));
                 oCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Transaction Log", {url: ""}));
                 oCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Serials Based", {url: ""}));
                 oCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Statistics", {submenu:oEnquiryStatisticsMenu , url: ""}));





               var oCirMenu = new YAHOO.widget.Menu("Circulation", { zIndex:2 });

                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member Records", {Submenu:oMemberRecordsMenu , disabled: <%=cir2%> } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Collection Updates", {submenu:oCollectionUpdatesMenu , disabled: <%=cir3%> } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Check In", { disabled: <%=cir4%>  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Check Out", { disabled: <%=cir5%>,url:"check_out1.jsp" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Renewal", { disabled: <%=cir6%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Reservation", {submenu:oReservationMenu , disabled: <%=cir7%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Circulation Enquiries", {submenu:oCirculationEnquiriesMenu , disabled: <%=cir8%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Reports", { disabled: <%=cir9%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("System Setup", { disabled: <%=cir10%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("House Keeping", { disabled: <%=cir11%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Recall/Follow-up", { disabled: <%=cir12%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Inter Library Loan", {submenu:oInterLibraryLoanMenu ,  disabled: <%=cir13%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Serial Circulation", { disabled: <%=cir14%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Fine Collection", { disabled: <%=cir15%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Mangement Reporting", { disabled: <%=cir16%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member History", { disabled: <%=cir17%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Online Search", { disabled: <%=cir18%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("For Future Use1", { disabled: <%=cir19%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("For Future Use2", { disabled: <%=cir20%> }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("For Future Use3", { disabled: <%=cir21%> }));



//submenu of cataloguing

                var oCatMaintenanceMenu = new YAHOO.widget.Menu("Maintenance");

                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Titles In process", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Enter Title", {url: "enter_title.html"}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Update Title", {url: "update_book_details.html"}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Update Holding", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Remove Title", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Subject Updates", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Titles by Accn No", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Change Accn No", {url: "change_acc_no.jsp"}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Retrospective Conversion", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Change Type of Document", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Merge Titles", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Authority Files", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Thesaurus", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Multimedia", {url: ""}));
                oCatMaintenanceMenu.addItem(new YAHOO.widget.MenuItem("Analitycal Entries", {url: ""}));




                var oCatCurrentAwarenessMenu= new YAHOO.widget.Menu("Current Awareness");

                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("New Addition", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("Update New Addition List", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("New Addition Check List", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("New Additions Lists", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("Develop Files", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("Bibliography", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("Remove File", {url: ""}));
                oCatCurrentAwarenessMenu.addItem(new YAHOO.widget.MenuItem("Member's Set(New Addition List)", {url: ""}));





                var oCatSdiMenu= new YAHOO.widget.Menu("SDI");

                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("Subject/Stretgies", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("Cat Intrest Profile ", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("SDI(New Addition)", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("Develop File", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("Develop SDI", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("Remove File ", {url: ""}));
                oCatSdiMenu.addItem(new YAHOO.widget.MenuItem("News Letter", {url: ""}));




               var oCatPrintCatalogueMenu = new YAHOO.widget.Menu("Print Catalogue");

               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Develop File", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Print Cards", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Print Cards(New Additions)", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Remove Files", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Spine Slips", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Develop File(Acquisition)", {url: ""}));
               oCatPrintCatalogueMenu.addItem(new YAHOO.widget.MenuItem("Print Cards(Acquisition)", {url: ""}));








             var oCatSystemSetupMenu = new YAHOO.widget.Menu("System Setup");

             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Type of Document", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Parameters", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Document Categories", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Document Formats", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Search Fields", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Set Database Access", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define Articles", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Accented Charecters", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define Mark", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define Document", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Languages", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define Databases", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Catalogue Entries", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define Indexes", {url: ""}));
             oCatSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Define ACCR-2 Card", {url: ""}));




               var oCatReportsMenu = new YAHOO.widget.Menu("Catalogue Reports");

               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Titles by Accession No", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Subjects/Keywords", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Titles Check List", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Arrival Notice", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Titles by Class No", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("List by Title", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Classified Subjects", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Highly Priced titles", {url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Register", {url: ""}));





               var oCatHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");

               oCatHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Update Inverted Files", {url: ""}));
               oCatHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Rebuild Inverted Files", {url: ""}));
               oCatHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("reorganise New additions File", {url: ""}));
               oCatHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Global Changes", {url: ""}));




               var oCatDataImportExportMenu = new YAHOO.widget.Menu("Data Import/Export ");{url: ""}

              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Data Import", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Develop File", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Data Export", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Data Export(New Addition)", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Remove File", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Export For Barcode Labels", {url: ""}));
              oCatDataImportExportMenu.addItem(new YAHOO.widget.MenuItem("Data Import(External)", {url: ""}));




               var oCatStockVerificationMenu = new YAHOO.widget.Menu("Stock Verification");{url: ""}

              oCatStockVerificationMenu.addItem(new YAHOO.widget.MenuItem("Initiate Stock Verification", {url: ""}));
              oCatStockVerificationMenu.addItem(new YAHOO.widget.MenuItem("Verification", {url: ""}));
              oCatStockVerificationMenu.addItem(new YAHOO.widget.MenuItem("Verification Report", {url: ""}));





                var oCatMenu = new YAHOO.widget.Menu("Cataloguing", { zIndex:2 });

                oCatMenu.addItem(new YAHOO.widget.MenuItem("Maintenance", {submenu:oCatMaintenanceMenu , disabled: <%=cat2%> } ));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Current Awareness", {submenu:oCatCurrentAwarenessMenu ,  disabled: <%=cat3%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("SDI", {submenu:oCatSdiMenu ,  disabled: <%=cat4%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Print Catalogue", {submenu:oCatPrintCatalogueMenu , disabled: <%=cat5%> }));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue System Setup", {submenu:oCatSystemSetupMenu , disabled: <%=cat6%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Data Import/Export", {submenu:oCatDataImportExportMenu ,  disabled: <%=cat7%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Bibliography", { disabled: <%=cat8%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Print Catalogue Cards", { disabled: <%=cat9%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue Reports", {Submenu:oCatReportsMenu ,  disabled: <%=cat10%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Stock Verification", {submenu:oCatStockVerificationMenu , disabled: <%=cat11%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("House Keeping", {submenu:oCatHouseKeepingMenu ,  disabled: <%=cat12%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Online Search", { disabled: <%=cat13%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("For Future Use2", { disabled: <%=cat14%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("For Future Use2", { disabled: <%=cat15%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("For Future Use2", { disabled: <%=cat16%>}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("For Future Use2", { disabled: <%=cat17%>}));



//submenu of acquisition

              var oAcqReportMenu = new YAHOO.widget.Menu("Reports");{url: ""}

              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles in Acquisition", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Requested Titles", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles for Approval", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Collect Notice", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles For Ordering", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Form", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Vendors Directory", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Sanction Request", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Status Request", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Check Delivery Notice", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(Selective)", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(All Titles)", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Request to vendor", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles by Control No", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Acknowledge Letter", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Supply Order", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Approve Titles", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Sanction Form", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Amendment", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Expenditure Analysis", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order List", {url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Receipt Voucher", {url: ""}));

    



                var oAcqSystemSetupMenu = new YAHOO.widget.Menu("System Setup");{url: ""}

                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {url: ""}));
                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Option", {url: ""}));





                var oAcqHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");{url: ""}

                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Invoice Record", {url: ""}));
                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Flushout Order Form", {url: ""}));





                var oAcqMenu = new YAHOO.widget.Menu("Acquisition", { zIndex:2 });

                //oAcqMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oAcqReportMenu ,  disabled: <%=acq16%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Enter Title", {submenu:oEntertitleMenu , disabled: <%=acq2%> } ));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Initiate 'On Approval' Process" , {submenu:oApprovalprocessMenu , disabled: <%=acq3%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Place Order", {submenu:oPlaceorderMenu , disabled: <%=acq4%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Invoicing/Accessioning", {submenu:oInvoicingMenu , disabled: <%=acq5%>}));
                //oAcqMenu.addItem(new YAHOO.widget.MenuItem("Budget", {submenu: oBudgetMenu}));
              //oAcqMenu.addItem(new YAHOO.widget.MenuItem("PaymentUpdate", {disabled: <%=acq6%>}));
               // oAcqMenu.addItem(new YAHOO.widget.MenuItem("Request to Vendor", { disabled: <%=acq7%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Approval Status Update", { disabled: <%=acq8%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Update Title Details", { disabled: <%=acq9%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Receiving", { disabled: <%=acq10%>}));
              // oAcqMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", { disabled: <%=acq11%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Manage Demand List", { disabled: <%=acq12%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Return 'On Approval' Items", { disabled: <%=acq13%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Enter Titles(Free/Gifts etc)", { disabled: <%=acq14%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Record Keeping", {submenu: oAcqRecordkeepingMenu , disabled: <%=acq15%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oAcqReportMenu ,  disabled: <%=acq16%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("House Keping", {submenu:oAcqHouseKeepingMenu ,  disabled: <%=acq17%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Import Requested Titles", { disabled: <%=acq18%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Enquiries", {submenu:oEnquiryMenu ,  disabled: <%=acq19%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {submenu:oAcqSystemSetupMenu ,  disabled: <%=acq20%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Request For Review", { disabled: <%=acq21%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Data Import", { disabled: <%=acq22%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Online Search", { disabled: <%=acq23%>}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("For Future Use1", {disabled: <%=acq24%> }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Budget", {disabled: <%=acq24%> }));
                


//submenu of Administrator
                var oAdminMenu = new YAHOO.widget.Menu("Administrator", { zIndex:2 });

                oAdminMenu.addItem(new YAHOO.widget.MenuItem("Staff", { submenu: oStaffMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("Privilege", { submenu: oPrivilegeMenu }));
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Cut", { url: "newhtml.html", helptext: "Ctrl + X", disabled: false }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Copy", { helptext: "Ctrl + C", disabled: true }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Paste", { helptext: "Ctrl + V" }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Delete", { helptext: "Del", disabled: true }), 1);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Select All", { helptext: "Ctrl + A" }), 2);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Find", { helptext: "Ctrl + F" }), 3);
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Find Again", { helptext: "Ctrl + G" }), 3);


//main menu
                var oMenuBar = new YAHOO.widget.MenuBar("menubar");

                oMenuBar.addItem("HOME");
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ADMINISTRATOR", { submenu: oAdminMenu , disabled: <%=administrator%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ACQUISITION", { submenu: oAcqMenu, disabled: <%=acquisition%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CATALOGUING", { submenu: oCatMenu, disabled: <%=cataloguing%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CIRCULATION", { submenu: oCirMenu , disabled: <%=circulation%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SERIAL", { submenu: oSerMenu , disabled: <%=serial%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SYSTEM SETUP", { disabled: <%=system_setup%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("UTILITIES", { disabled: <%=utility%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("OPAC", {url:"OPACmain.jsp"}));
                oMenuBar.addItem("HELP");
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

