<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%--    <title>BGAS</title>
    <link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/helpdoc.css"> --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" /> 
        <link href="../css/helpdoc.css" rel="stylesheet" type="text/css" />
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

            	 <div class="span3 bs-docs-sidebar">
                 <ul id="ulNav" class="nav nav-list bs-docs-sidenav affix-top">

                    <li><a href="#AboutPurchaseandInventoryControlSystem"><i class="icon-chevron-right"></i>About Purchase and Inventory Control System</a></li>
                    <li><a href="#Administrator(Home)"><i class="icon-chevron-right"></i>Administrator(Home)</a>
		    <li><a href="#InstitutionAdministratorRegistration"><i class="icon-chevron-right"></i>Institution Administrator Registration</a></li>
                    <li><a href="#NewUser"><i class="icon-chevron-right"></i>New User</a></li>
                    <li><a href="#CreateAccount"><i class="icon-chevron-right"></i>Create Account</a></li>    
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
                 </ul>
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
			<img src="../images/HelpDocImages/InsAdminReg.png"  alt="JMI's PICO Banner" border="0" width="1250" height="700"/>	
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
                
	</div>
        &nbsp;
	</div>

	</div>
    </body>
</html>


