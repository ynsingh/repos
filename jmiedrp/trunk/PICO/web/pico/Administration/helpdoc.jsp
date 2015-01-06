<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/scroll.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" /> 
        <link href="../css/helpdoc.css" rel="stylesheet" type="text/css" />
        <link href="../css/scroll.css" rel="stylesheet" type="text/css" />
	<meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India"> 
        <s:head />
</head>

    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
           <div id ="mainContent" align="center"> 
		<br>

	    <%-- Subhead
		================================================== --%> 
		<h1 class="header">Purchase and Inventory Control System</h1>
		 <%-- Docs nav
	    	================================================== --%>
	<div style="border: solid 1px #000000; background: gainsboro"> 
        	<div class="row-fluid">
		<div id="left-nav">
            	 <div class="span3 bs-docs-sidebar">
                 <ul id="ulNav" class="nav nav-list bs-docs-sidenav affix-top">

                    <li><a href="#AboutPurchaseandInventoryControlSystem"><i class="icon-chevron-right"></i>About Purchase and Inventory Control System</a></li>
                    <li><a href="#Administrator(Home)"><i class="icon-chevron-right"></i>Administrator(Home)</a>
		    <li><a href="#InstitutionAdministratorRegistration"><i class="icon-chevron-right"></i>Institution Administrator Registration</a></li>
                    <li><a href="#NewUser"><i class="icon-chevron-right"></i>New User</a></li>
	            <li><a href="#RegisteredInstitutions"><i class="icon-chevron-right"></i>Registered Institutions</a></li>
	            <li><a href="#Administration"><i class="icon-chevron-right"></i>Administration</a></li>
		    <li><a href="#Organization"><i class="icon-chevron-right"></i>Organization</a></li>
       		    <li><a href="#Institution"><i class="icon-chevron-right"></i>Institution</a></li>
                    <li><a href="#College/Faculty/School"><i class="icon-chevron-right"></i>College/Faculty/School</a></li>
                    <li><a href="#Department"><i class="icon-chevron-right"></i>Department</a></li>
                    <li><a href="#Employee"><i class="icon-chevron-right"></i>Employee</a></li>
                    <li><a href="#Committee"><i class="icon-chevron-right"></i>Committee</a></li>
                    <li><a href="#MasterData"><i class="icon-chevron-right"></i>Master Data</a></li>
                    <li><a href="#GeneralMaster"><i class="icon-chevron-right"></i>General Master</a></li>
                    <li><a href="#CapitalItems"><i class="icon-chevron-right"></i>Capital Items</a></li>
	            <li><a href="#ItemCategory"><i class="icon-chevron-right"></i>Item Category</a></li>
		    <li><a href="#BudgetHeads"><i class="icon-chevron-right"></i>Budget Heads</a></li>
                    <li><a href="#GeneralTerms"><i class="icon-chevron-right"></i>General Terms</a></li>
                    <li><a href="#WorkFlow"><i class="icon-chevron-right"></i>Work Flow</a></li>
		    <li><a href="#GFRMaster"><i class="icon-chevron-right"></i>GFR Master</a></li>
		    <li><a href="#PICOAdmin"><i class="icon-chevron-right"></i>PICO Admin</a></li>
		    <li><a href="#PICOSubModule"><i class="icon-chevron-right"></i>PICO Sub-Module</a></li>
		    <li><a href="#PICOPrograms"><i class="icon-chevron-right"></i>PICO Programs</a></li>
		    <li><a href="#PICOUserRoles"><i class="icon-chevron-right"></i>PICO User Roles</a></li>
		    <li><a href="#AuthorizationRequests"><i class="icon-chevron-right"></i>Authorization Requests</a></li>
		    <li><a href="#GFRProgramMapping"><i class="icon-chevron-right"></i>GFR Program Mapping</a></li>
		    <li><a href="#BudgetAllocation"><i class="icon-chevron-right"></i>Budget Allocation</a></li>
		    <li><a href="#PICONews"><i class="icon-chevron-right"></i>PICO News</a></li>
		    <li><a href="#PrePurchase"><i class="icon-chevron-right"></i>PrePurchase</a></li>
		    <li><a href="#PurchaseItems"><i class="icon-chevron-right"></i>Purchase Items</a></li>
		    <li><a href="#Suppliers"><i class="icon-chevron-right"></i>Supplier Master</a></li>
		    <li><a href="#ItemRates"><i class="icon-chevron-right"></i>Item Rates</a></li>
		    <li><a href="#Indent"><i class="icon-chevron-right"></i>Indent Master</a></li>
		    <li><a href="#Tender"><i class="icon-chevron-right"></i>Tender</a></li>
		    <li><a href="#TenderMaster"><i class="icon-chevron-right"></i>Tender Master</a></li>
		    <li><a href="#TenderSchedule"><i class="icon-chevron-right"></i>Tender Schedule</a></li>
		    <li><a href="#TenderSubmission"><i class="icon-chevron-right"></i>Tender Submission</a></li>
		    <li><a href="#TenderRevision"><i class="icon-chevron-right"></i>Tender Revision</a></li>
		    <li><a href="#PurchaseOrder"><i class="icon-chevron-right"></i>Purchase Order</a></li>
		    <li><a href="#PrePurchaseReports"><i class="icon-chevron-right"></i>Pre-Purchase Reports</a></li>
		    <li><a href="#Purchase"><i class="icon-chevron-right"></i>Purchase</a></li>
		    <li><a href="#PurchaseChallan"><i class="icon-chevron-right"></i>Purchase Challan</a></li>
		    <li><a href="#PurchaseInvoice"><i class="icon-chevron-right"></i>Purchase Invoice</a></li>
		    <li><a href="#PurchaseReports"><i class="icon-chevron-right"></i>Purchase Reports</a></li>
		    <li><a href="#Inventory"><i class="icon-chevron-right"></i>Inventory</a></li>
		    <li><a href="#IssueItems"><i class="icon-chevron-right"></i>Issue Items</a></li>
		    <li><a href="#OpeningStock"><i class="icon-chevron-right"></i>Opening Stock</a></li>
		    <li><a href="#ReceiveItems"><i class="icon-chevron-right"></i>Receive Items</a></li>
		    <li><a href="#ReturnItems"><i class="icon-chevron-right"></i>Return Items</a></li>
		    <li><a href="#StockReports"><i class="icon-chevron-right"></i>Stock Reports</a></li>
		    <li><a href="#UserProfile"><i class="icon-chevron-right"></i>User Profile</a></li>
		    <li><a href="#CurrentUserProfile"><i class="icon-chevron-right"></i>Current User Profile</a></li>
		    <li><a href="#ChangeUserProfile"><i class="icon-chevron-right"></i>Change User Profile</a></li>
                 </ul>
                 </div>
                 </div>
            <div class="span15">

                <section id="AboutPurchaseandInventoryControlSystem">
                    <div class="row-fluid">
                        <h2>About Purchase and Inventory Control System</h2>
                    </div>

                    <div class="row-fluid">
			<font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
                           The Purchase and Inventory Control system offers comprehensive reporting capabilities to keep you on top of inventory status. It can help you bring about the creation of new or improved purchasing policies, sales policies, pricing methods, and even enhanced customer service. By leveraging Sage BusinessWorks, you have the tools to create an inventory system with the depth to meet needs of your company for years to come.  
                        </ol>
	</font>
                       </section>
	                <section id="Administrator(Home)">
                    <div class="row-fluid">
                        <h2>Administrator(Home)</h2>
                    </div>
                    <div class="row-fluid">
		 <font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
                        Administrator can login into system by filling username and password. For login into the system, user will have to register our institute. Go to link <u>Institution Administrator Registration</u> in Login Page. 
			</font>
                        </ol>
			<img src="../images/HelpDocImages/mainpage.png"  alt="JMI's PICO Banner" border="0" width="1000" height="600"/>
                 </div>
                </section>

                <section id="InstitutionAdministratorRegistration">
                    <div class="row-fluid">
                        <h2>Institution Administrator Registration</h2>
                    </div>

                    <div class="row-fluid">
		 <font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
				User (Admin) can create account by filling all data.
			</font>
                        </ol>
			<img src="../images/HelpDocImages/InsAdminReg.png"  alt="JMI's PICO Banner" border="0" width="1000" height="600"/>	
                 </div>
                </section>

                  <section id="NewUser">
                    <div class="row-fluid">
                        <h2>New User</h2>
                    </div>

                  <div class="row-fluid">
		<font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">	
			This from is to add new user in an Institute by filling all details.
			</font>
                        </ol>
                        <img src="../images/HelpDocImages/newuserReg.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>
		
		 <section id="RegisteredInstitutions">
                    <div class="row-fluid">
                        <h2>Registered Institutions</h2>
                    </div>

                    <div class="row-fluid">
                   <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
                        Here we show all registered institutions in PICO System.
			</font>
                        </ol>
			<img src="../images/HelpDocImages/RegIns.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                 </div>
                </section>

		<section id="Administration">
                    <div class="row-fluid">
                        <h2>Administration</h2>
                    </div>

                    <div class="row-fluid">
                    <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
                       Administration has five programs named as Organization, Master Data, PICO Admin, Budget Allocation and PICO News and manage all data of system.
			</font>
                        </ol>
                    </div>
                </section>

		<section id="Organization">
                    <div class="row-fluid">
                        <h2>Organization</h2>
                    </div>
                    <div class="row-fluid">
                    <font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
                        Organization also has five sub programs and named as Institution, College/Faculty/School, Department, Employee & Committee.It maintains all records of organization.
			</font>
                        </ol>
                    </div>
                </section>

		<section id="Institution">
		 <div class="row-fluid">
			 <h2>INSTITUTE RECORDS MANAGEMENT</h2>
                    </div>
                    <div class="row-fluid">
                    <font size="4">
                        <ol>
                        <p align="justify" STYLE="line-height: 150%">
                       By filling all form fields, user can create new institute in PICO system and user can see the list of all institutes by clicking on Browse button.
                        </font>
                        </ol>
			<img src="../images/HelpDocImages/Institute.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>
		<section id="College/Faculty/School">
		<div class="row-fluid">
                        <h2>College/Faculty/School Records Management</h2>
                    </div>
		 <div class="row-fluid">
			<font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
    		By filling all form fields, user can create new College/Faculty/School in an institute and user can see the list of Sub-Institution by clicking on Browse button. 
			</font>
                        </ol>
                        <img src="../images/HelpDocImages/SubInst.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="Department">
		<div class="row-fluid">
                        <h2>Department Records Management</h2>
                    </div>
		 <div class="row-fluid">
			<font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
			User can create one or more department in an institute by entering all data and user can see the list of departments by clicking on Browse button.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/Department.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="Employee">
		<div class="row-fluid">
                        <h2>EMPLOYEE MASTER SCREEN</h2>
                    </div>
			 <div class="row-fluid">
			<font size="4">
                        <ol>
			<p align="justify" STYLE="line-height: 150%">
			User creates one or more employee in an institute by entering all data and user can see the list of employee by clicking on Browse button.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/Employee.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>
	
		<section id="Committee">
		<div class="row-fluid">
                        <h2>COMMITTEE MASTER</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			User has to make Authority/Committee for Institute. Browse button is to show list of Authority/Committee.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/Committee.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="MasterData">
		<div class="row-fluid">
                        <h2>Master Data</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			It maintains master data of Administration. It contains seven sub programs named as General Master, Capital Items, Item Category, Budget Heads, General Term, Work Flow, GFR Master.
                        </ol>
			</font>
		<section id="GeneralMaster">
		<div class="row-fluid">
                        <h2>General Master</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add description to the control parameter for administrative use. The form consists of a dropdown list of general control parameter. The user can select a particular general control parameter and click on Browse button to display General Master list.
                      </ol>
			</font>
			<img src="../images/HelpDocImages/GenMaster.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		<section id="CapitalItems">
		<div class="row-fluid">
                        <h2>CAPITAL ITEM CATEGORIES MANAGEMENT</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add capital item category name into a selected institute. User can see the list of capital items by clicking on browse button.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/capitalItems.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="ItemCategory">
		<div class="row-fluid">
                        <h2>ITEM CATEGORY MANAGEMENT</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
                        <ol>
                <p align="justify" STYLE="line-height: 150%">
		This page displays a form that allows a user to maintain the category of items according to levels. There are three levels if user selects level 1 that means super parent, if level 2 that means item is child of level 1 and if level 3 that means item is child of level 2. Browse button to show list of all items in an institute.
			</ol>
			</font>
                        <img src="../images/HelpDocImages/ItemCategory.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="BudgetHeads">		
		<div class="row-fluid">
                        <h2>BUDGET HEAD RECORDS MANAGEMENT</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			Budget head screen displays a form to add budget head name in institute. User can see the list of budget heads name in an institute by clicking on browse button.
		 </ol>	
			</font>
                        <img src="../images/HelpDocImages/Budgetheads.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="GeneralTerms">
		<div class="row-fluid">
                        <h2>GENERAL TERMS & CONDITIONS</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen open a form to maintain term and condition of institute and also provide browse functionality to display list terms and condition. 
 			</ol>   
			</font>
                        <img src="../images/HelpDocImages/GenTermsCon.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="WorkFlow">
		<div class="row-fluid">
                        <h2>WORKFLOW MASTER</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen opens a form to add details of work flow. User will have to define the Authorities/Committees before entering workflow. User will have to define Actions (which an authority or committee is authorized to take). Though this Workflow preparetion will be one time process which will be defined by the Institute Administrator as per the ordenances of the Institute.
			</ol>
			</font>	
                        <img src="../images/HelpDocImages/WorkflowMas.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="GFRMaster">
		<div class="row-fluid">
		<h2>GENERAL FINANCIAL RULE</h2>
                    </div>
                 <div class="row-fluid">
		<font size="4">
                 <ol>
                <p align="justify" STYLE="line-height: 150%">
			This screen is to add general financial rules of institute.
		</ol>
		</font>
                        <img src="../images/HelpDocImages/GFRMaster.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/> 

		<section id="PICOAdmin">
		<div class="row-fluid">
                        <h2>PICO Admin</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		<ol>
		<p align="justify" STYLE="line-height: 150%">
			It is functionality of administrator and it provides to administrator to add sub module, programs into the main module. It has authority to approve users in institute and it can also set the roles of user.
		</ol>
                </font>

		<section id="PICOSubModule">
		<div class="row-fluid">
				        <h2>SUB-MODULE RECORDS MANAGEMENT</h2>
				    </div>
		 <div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
		From here we can add a sub module into the main module named as PICO by filling all feilds of form.
		 </ol>
		</font>
	        <img src="../images/HelpDocImages/subModule.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="PICOPrograms">
		<div class="row-fluid">
		<font size="4">
		</font>
                        <h2>PROGRAM MANAGEMENT</h2>
                    </div>
		 <div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			From here we can add a new programs into sub modules by entering data into the form and program gets created.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PICOProgram.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="PICOUserRoles">
		<div class="row-fluid">
                        <h2>INSTITUTION USER ROLE MANAGEMENT</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		<ol>
		<p align="justify" STYLE="line-height: 150%">
			From here administrator maintains the role of user in institution.
		</ol>
		</font>
                <img src="../images/HelpDocImages/UserRole.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="AuthorizationRequests">
		<div class="row-fluid">
                        <h2>Authorization Requests</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen shows the list of those users that do not have permission to login into the PICO System and there is a link to assign permission to user by administrator.
		</ol>
                </font>
                <img src="../images/HelpDocImages/AuthorizationReq.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="GFRProgramMapping">
		<div class="row-fluid">
                        <h2>GFR & Program Mapping</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen allows the user to map the GFR Rules for selected program. The form consists of a dropdown list of sub module and program. The user can select a particular sub module and program for the mapping of GFR Rules & then some data appears in a table with a link named as include.
		</ol>
                </font>
                <img src="../images/HelpDocImages/GFR&ProgramMapping.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/> 
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			User has to click on that link then GFR RULES MAPPED TO THE SELECTED PROGRAM. 
		</ol>
                </font>
                <img src="../images/HelpDocImages/GFR&Progrm.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/> 

		<section id="BudgetAllocation">
		<div class="row-fluid">
                        <h2>DEPARTMENTAL BUDGET ALLOCATION</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add a budget to the institute. The form consists of a dropdown list of budget head name. The user can select a particular budget head name to specify the amount of budget to be allocated for that budget head. 
		</ol>
                </font>
                <img src="../images/HelpDocImages/BudAllocation.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>

		<section id="PICONews">
		<div class="row-fluid">
                        <h2>NEWS MANAGEMENT</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add any new activity into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PicoNews.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
         
		<section id="PrePurchase">
		<div class="row-fluid">
                        <h2>Pre-Purchase</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			PrePurchase sub-module consists of Purchase Items, Suppliers, Item Rates, Indent, Tender, Purchase Order, Pre-Purchase Reports programs. This module will offer all facilities that are required to be carried out by purchase users, managers and administrators before purchase process.
		</ol>
                </font>
		</div> 
		<section id="PurchaseItems">
		<div class="row-fluid">
                        <h2>Purchase Items</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add item details into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PurchaseItems.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="Suppliers">
		<div class="row-fluid">
                        <h2>Supplier Master</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen displays a form that allows a user to add all information of supplier into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/SupplierMaster.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="ItemRates">
		<div class="row-fluid">
                        <h2>Item Rates</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page opens a form to set the rate of items and rate of items is to be approved by supplier.
		</ol>
                </font>
                <img src="../images/HelpDocImages/ItemRates.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="Indent">
		<div class="row-fluid">
                        <h2>Indent</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen is meant to send a request for procuring items for use. It is three step process.Before generating indent we will have to prepare the followings,
				<br><b>I</b>	First of all we will check if the sufficient fund is available in the Budget if the items are to be purchased.  If not then we will have to allocate amount by entering data into <b>Administration > Budget Allocation</b> screen. Since in the Step 1 of Indent, Budget Amount Available will be fetched, after the change of Budget Head Type.
				<br><b>II</b>	Then we have to check whether the Item which we are going to indent and is to be purchased, is having rates approved by the competent authority or not. If not then the approved rate is to be entered into <b>Pre Purchase > Item Rates</b> screen.
			<br><b>Step 1 :</b> User will have to enter the Indent Title after selecting Institution name, College/Faculty/School name, Department name, currency of purchase and Budget heads type. User enters the Budget Heads Type, then amount allocated is fetched and populated, then enter indent signatory, then user click on save/next button.
		</ol>
                </font>
                <img src="../images/HelpDocImages/Indent1.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div> 
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<br><b>Step 2 :</b>A new form is open to add details of items for which indent is to be generated. User will have to click Add Item for adding the items into the indent after filling up the Item details above, If user wants to add more item details then user will have to again fill up details & click on Add Item button.
After the Indent Items have been put in then the user can submit this indent, by clicking Submit indent Button.
		</ol>
                </font>
                <img src="../images/HelpDocImages/Indent2.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<br><b>Step 3 :</b> It will ask you on which Workflow you want to submit the indent. It will also ask you what action you want to take. 
		</ol>
                </font>
                <img src="../images/HelpDocImages/Indent3.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="Tender">
		<div class="row-fluid">
                        <h2>Tender</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This sub module has four programs to complete the process of Tender.
		</ol>
                </font>
		</div> 

		<section id="TenderMaster">
		<div class="row-fluid">
                        <h2>Tender Master</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This page displays a form that allows a user to add all details regarding tender into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/TenderMaster.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>    

		<section id="TenderSchedule">
		<div class="row-fluid">
                        <h2>Tender Schedule</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen opens a form to add tender schedule for selected tender number into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/TenderSchedule.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="TenderSubmission">
		<div class="row-fluid">
                        <h2>Tender Submission</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen displays a form to submit the tender by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/TenderSubm.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			There is also a functionality to upload submission file by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/UploadSubmFile.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="TenderRevision">
		<div class="row-fluid">
                        <h2>Tender Revision</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen displays a form to revise the tender into the system by entering data into the form.
		</ol>
                </font>
                <img src="../images/HelpDocImages/TenderRevision.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="PurchaseOrder">
		<div class="row-fluid">
                        <h2>Purchase Order</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			PO can be generate for <b>indent item</b> as well as for <b>non-indent item</b> both. The Steps for creation of Purchase Order is explained below :-<br><b>Step 1 : </b>In the generation of purchase order,  user will fill the required fields and then will click on save/next button. Purchase order number is automatically generated on save.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PO1.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<b>Step 2 :</b> In step 2, if user wants create PO for indented item then there is a drop down to select an indent title. User will have to click <b>"Get Indent Items"</b> button. This will save all the Items belonging to the selected Indent into the Table with the heading "Items in Purchase Order". Items in the selected indent are shown and there is a link <b>Transfer to PO</b> to move the item to PO.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PO2.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		<div class="row-fluid">
                <font size="4">
                 <ol>
		<p align="justify" STYLE="line-height: 150%">Items in the selected indent are shown and there is a link Transfer to PO to move the item to PO. Now if you want to modify the quantity then click "Edit" in the Edit column and if you want to remove an Item completely, you can click "Remove from PO".</p>
                </ol>
                </font>
		<img src="../images/HelpDocImages/PO2a.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<b>Step 3 :</b> If user wants to create PO for non-indent item then user clicks on <b>Non Indented Items</b> Button. Then a form will open, user will have to fill up the details and click <b>Add Items</b> button then it will add  <b>non-indented items to PO</b>. Items in the selected indent are shown and there is a link <b>Transfer to PO</b> to move the item to PO.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PO3.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<b>Step 4 :</b> Now user can add Terms and Conditions for the PO by opening a screen on click of <b>Terms & Conditions</b>  button. Here the Terms and Conditions are pre-defined, which are saved in Administrator sub-module. User can change them if required. 
		</ol>
                </font>
                <img src="../images/HelpDocImages/PO4.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			<b>Step 5 : </b>Now lastly there is a provision for delivering the Items at different locations. User can mention what quantity of an item is to be delivered in which department and at which floor / room no. This screen appears if we click on <b>Item(s) Distribution</b> button.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PO5.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="PrePurchaseReports">
		<div class="row-fluid">
                        <h2>Pre-Purchase Reports</h2>
                </div>
		<div class="row-fluid">
		<font size="4">
		 <ol>
		<p align="justify" STYLE="line-height: 150%">
			When user goes to Pre-Purchase >  Pre-Purchase Reports and define the filter criteria for displaying various reports mentioned.
		</ol>
                </font>
                <img src="../images/HelpDocImages/PrePurchaseReports.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
		</div>

		<section id="Purchase">
		<div class="row-fluid">
                        <h2>PURCHASE </h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			It consists of three sub-menus. These are described as:- Purchase Challan, Purchase Invoice, Purchase Reports.
                        </ol>
			</font>
                    </div>
                </section>

		<section id="PurchaseChallan">
		<div class="row-fluid">
                        <h2> MANAGE PURCHASE CHALLAN MASTER</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			When the purchased material is delivered then it is accompanied with a document it can be a Delivery Challan or a Purchase Bill/Invoice. 
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/PurchaseChallan.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="PurchaseInvoice">
		<div class="row-fluid">
                        <h2> PURCHASE INVOICE/BILL</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			Bill is received with the material or it arrives later if the material is delivered with Delivery Challan.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/PurchaseBill.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen is to check and verified the quantity of items of institute.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/PurchaseBillDetail.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="PurchaseReports">
		<div class="row-fluid">
                        <h2> MANAGE PURCHASE REPORTS</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			Purchase Reports screen gives information about Pending Bills, Pending PO, Unchecked and unverified items and invoice/Bill received.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/PurchaseReports.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="Inventory">
		<div class="row-fluid">
                        <h2>Inventory </h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			It consists of five module. These are described as:- Opening Stock, Issue Items, Received Items, Return Items, Stock Reports.
                        </ol>
			</font>
                    </div>
                </section>

		<section id="IssueItems">
		<div class="row-fluid">
                        <h2> MANAGE ISSUE ITEMS</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen is meant to enter the opening stock which already exists in the Institute at the time of starting the PICO system. User has to put all information of items in stock register. This is one time process that means user has to make entries of items in stock.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/IssueItems.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="OpeningStock">
		<div class="row-fluid">
                        <h2> OPENING STOCK </h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			When we issue items to user, we will have to maintain record to know about that which item has been issued to which user from which authority.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/OpeningStock.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="ReceiveItems">
		<div class="row-fluid">
                        <h2>RECEIVE ISSUED ITEMS FORM</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			When the Items are issued then the Receiver will have to approve that he has received the Items in proper condition. It is design to maintain records of those items for which user created the indent and that items are received from stock.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/ReceiveItems.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="ReturnItems">
		<div class="row-fluid">
                        <h2> MANAGE RETURN ISSUED ITEMS</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			When user returned the items with its return type that means items are received from user, an entry is to be maintained in stock register. There are two types of return type of items.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/ReturnItems.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="StockReports">
		<div class="row-fluid">
                        <h2> INVENTORY REPORTS</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen is meant to print various reports, filtered as per the user selection. Currently existing reports are Stock Received Summary, Stock Received Item Details, Items Pending to be Received, Stock in Hand, GFR Report 40 (For Fixed Assets), GFR Report 41 (For Consumables)  and Stock Register.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/StockReports.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="UserProfile">
		<div class="row-fluid">
                        <h2> User Profile</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			User Profile provides facility to change the user profile and display the current user profile.
                        </ol>
			</font>
                    </div>
                </section>

		<section id="CurrentUserProfile">
		<div class="row-fluid">
                        <h2> CURRENT USER PROFILE</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			This screen displays the information of current user profile.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/CurrentProfile.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
                </section>

		<section id="ChangeUserProfile">
		<div class="row-fluid">
                        <h2> CHANGE USER PROFILE</h2>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
			Administrator has authority to add another profile into the institute and there is a button named as <b>Request Additional Profile</b> to add another profile.
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/ChangeUserProfile.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                    </div>
		 <div class="row-fluid">
		 <font size="4">
                        <ol>
		<p align="justify" STYLE="line-height: 150%">
                        </ol>
			</font>
                        <img src="../images/HelpDocImages/SetupUserProfile.png" alt="JMI's PICO Banner" border="0" width="1250" height="700"/>
                </section>
<div id="backToTop">
<input id="top" class="scrolltotop" type="image" onclick="scrollToTop()" src="../images/top.png" alt="Submit" width="35" height=35 />
<input id="bottom" class="scrolltobottom" type="image" onclick="scrollToBottom()" src="../images/bottom.png" alt="Submit" width="35" height=35 />
</div>

	</div>
        &nbsp;
	</div> 
	</div>
    </body>
</html>


