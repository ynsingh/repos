<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<title>User Manual</title>  
        <?php $this->load->view('template/header'); ?>
        
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
	
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="head"> Brihaspati Purchase and Inventory Control System</div>
	<div class="content">
	<div style="margin-top:-14px;" class="sideleft">
        <div id="cssmenu">
        	<ul>
        <li><a href="#BrihaspatiPurchaseandInventoryControlSystem">Brihaspati Purchase and Inventory Control System</a></li>
	    <li><a href="#Dashboard">Dashboard</a></li>
	    <li class='has-sub'><a href="#Setup">Setup</a>
			<ul>
			<li><a href="#EmailSetting">Email Setting</a></li>
			<li><a href="#Role">Role</a></li>
			<li><a href="#Category">Category</a></li>
			<li><a href="#StudyCenter">Study Center</a></li>
			<li><a href="#Department">Department</a></li>
            <li><a href="#Designation">Designation</a></li>
            <li><a href="#Authority">Authority</a></li>
			<li><a href="#Scheme">Scheme	</a></li>
			<li><a href="#DDO">DDO  </a> </li>
			</ul>
		</li>
	    <li class='has-sub'><a href="#Map">Map

	    </a>
			<ul>
			<li><a href="#UserwithRole">User with Role</a></li>
			<li><a href="#AuthorityandUser">Authority and User</a></li>
			<li><a href="#StudyCenterwithUO">Study Center with UO</a></li>
			<li><a href="#SetUO"> Set UO</a></li>
			<li><a href="#SetHOD">Set HOD</a></li>
			<li><a href="#UserwithSocieties">User with Societies</a></li>
			</ul>
	    <li class='has-sub'><a href="#PICOSetup">PICO Setup</a>
            <ul>
            <li><a href="#TypeofStore">Type of Store</a></li>
			<li><a href="#Financial">Financial</a></li>
			<li><a href="#PurchaseType">Purchase Type</a></li>
			<li><a href="#PurchaseCommitteeFormationRules">Purchase Committee Formation Rules </a></li>
			<li><a href="#ModeOfTender">Mode Of Tender</a></li>
			<li><a href="#CoverType">Cover Type</a></li>
			<li><a href="#BankList">Bank List</a></li>
			<li><a href="#supplier">Supplier</a></li>
            </ul>

	    <li class='has-sub'><a href="#IntenderProcess">Intender Process</a>
			<ul>
			<li><a href="#Specification">Specification</a></li>
			<li><a href="#Committee">Committee</a></li>
			<li><a href="#TenderForm">Tender Form</a></li>
			<li><a href="#InspectionReport">Inspection Report</a></li>
			</ul>
        <li class='has-sub'><a href="#PurchaseManagement">Purchase Management</a>
		    <ul>
			<li><a href="#TendersList">Tenders List</a></li>
			<li><a href="#SubmitWebsite">Submit Website</a></li>
			<li><a href="#TenderApply">Tender Apply</a></li>
			<li><a href="#TenderRequests">Tender Request</a></li>
			<li><a href="#ComparativeStatement">Comparative Statement</a></li>
			<li><a href="#PurchaseProposal">Purchase Proposal</a></li>
			<li><a href="#PurchaseApprovalviaGeM">Purchase Approval via GeM</a></li>
			<li><a href="#PurchaseOrder">Purchase Order</a></li>
            </ul>	
	    <li class='has-sub'><a href="#Recieve&Store">Recieve & Store</a>
                        <ul>
                        <li><a href="#ItemReceived">Item Received</a></li>
                        <li><a href="#ItemIssued">Item Issued</a></li>
                        <li><a href="#ItemReturn">Item Return</a></li>
                        <li><a href="#InspectionReports">Inspection Report</a></li>
                        <li><a href="#PaymentOrder">Payment Order</a></li>
                        <li><a href="#ItemTransfer">Item Transfer</a></li>
                        <li><a href="#ItemWriteOff">Item Write Off</a></li>
                        <li><a href="#ItemAuction">Item Auction</a></li>
			            </ul>
	    <!--li class='has-sub'><a href="#Reports">Report</a>
                        <ul>
                        <li><a href="#supplier">Supplier</a></li>
                        <li><a href="#Tenders">Tenders</a></li>
                        <li><a href="#ItemList">Item List</a></li>
                        <li><a href="#StockList">StockList</a></li>
                        <li><a href="#IssuedList">Issued List</a></li>
                        </ul>
                    </li-->
	    <!--li class='has-sub'><a href="#Archives">Archives</a>
			<ul>
			<li><a href="#LogDetails">Log Detail</a></li>
			<li><a href="#TenderCreateArchive">Tender Create Archive </a></li>
			<li><a href="#TenderApplyArchive">Tender Apply Archive</a></li>
			<li><a href="#VendorArchive">Vendor Archive</a></li>
			<li><a href="#StockItemArchive">Stock Item List</a></li>
			<li><a href="#ItemIssued/ReturnArchive">Item Issued/Return Archive</a></li>
			<li><a href="AuthorityArchive">Authority Archive </a></li>
			<li><a href="#UserRoleArchive">User Role Archive</a></li>
			<li><a href="#AnnouncementArchive">Announcement Archive</a></li>
		    </ul>  
		    </li-->

		<li class='has-sub'><a href="#Profile">Profile</a>
			<ul>
				<li><a href="#ViewProfile">View Profile</a></li>
				<li><a href="#ChangePassword"> Change Password</a></li>
				<li><a href="#ChangeEmployeePassword">Change Employee Password</a></li>
			</ul>
		</li>		       
                </div>
            </div>
                <div class="sideright">
					<section id="BrihaspatiPurchaseandInventoryControlSystem">
					<div class="row-fluid">


						<h2>Brihaspati Purchase and Inventory Control System</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify">
This Web-based information system was designed to allow you to view and maintain  the purchase of various kinds of equipment and stores by department /Inter-disciplinary programs/Centers /Central Facilitis /Units /Section, both from Isntitute's main account as well as from the project funds
								</p>
							</ol>
						</font>
					</div>
					</section>

					<section id="Dashboard">
						<div class="row-fluid">
							<h2>Dashboard</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify">
									This page consists of details of University profile. The page is constantly updated on the basis of the activities carried out in the system. 
									</p>
								</ol>
							</font>						
						<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/dashboard.png"  width="100%">
						</div>
					</section>

					<section id="Setup">
						<div class="row-fluid">
							<h2>Setup</h2>
						</div>
						<section id="EmailSetting">
						<div class="row-fluid">
							<h2>Email Setting</h2>
                                                        <h2>Add Email Setting</h2>
						</div>


						<div class="row-fluid">
							<font size="4">
									<ol>
									<p align="justify">	
						By Filling all email setting data we can setup outgoing email.<br>
						The format of email setting are given below:-
									</p>	
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="1px solid blue" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Email protocol</B></td>
						<td>Select Email protocol Ex:-smtp, I

						, POP</td>
						</tr>
						<tr>
						<td><B>Email Hostname</B></td>
						<td>Ex:-smtp.cc.iitk.ac.in or IP Address: 172.3.1.5</td>
						</tr>
						<tr>
						<td><B>Email Port</B></td>
						<td>Ex:-25</td>
						</tr>
						<tr>
						<td><B>Username</B></td>
						<td>Ex:-palseema30 or palseema30@gmail.com</td>
						</tr>
						<tr>
						<td><B>Password</B></td>
						<td> your-password</td>
						</tr>
						<tr>
						<td><B>Sendername</B></td>
						<td>Ex:-Brihaspati</td>
						</tr>
						</P>
						</table>					
								</ol>
							</font>
						<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/email.png" width="100%">
						</div>
					</section>
					<!--section id="ViewEmailSetting">
                                        <div class="row-fluid">
                                                <h2>View Email Setting</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Admin can also view email setting after adding email setting. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
					<img src="<?php echo base_url(); ?>uploads/SLCMS/helpimages/picohelpscreenshot/viewemail.png" height="50%" width="100%">

                                        </div>
                                        </section-->

<section id="Role">                                     
                                                <h2>Role</h2>
                                        <section id="AddRole">
 					<h2>Add Role</h2>
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
From here Admin can add role by filling Role Name and Role Description.<br>
The format of Add Role are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Role Name</B></td>
						<td>Admin, System Administrator</td>
						</tr>
						<tr>
						<td><B>Role Description</B></td>
						<td>System Admin a person who manages the operation of a computer system.</td>
						</tr>
						</table>
</P>

                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/role.png"  width="100%">
                                                </font>
                                       
                                        </section>
</section>
			<!--section id="ViewRoleDetail">
                                        <div class="row-fluid">
                                                <h2>View Role Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Admin can view role after adding role details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpimages/picohelpscreenshot/viewrole.png" height="65%" width="100%">
                                                </font>
                                        </div>
                                        </section-->

				<section id="Category">
                                        <div class="row-fluid">
                                                <h2>Category</h2>
						</div>
                            <div class="row-fluid">                    
				 <h2>Add Category</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
 Admin can add category by filling Category Name, Category Code, Category Short Name, Category Description.<br>
The format of Add category are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Category Name</B></td>
						<td>Scheduled Tribe, Other Backward Class, General etc.</td>
						</tr>
						<tr>
						<td><B>Category Code</B></td>
						<td>01, 02, 03, st04, sc-5 etc.</td>
						</tr>
						<tr>
						<td><B>Category Short Name</B></td>
						<td>ST, SC, OBC, GEN, PH etc.</td>
						</tr>
						<tr>
						<td><B>Category Description </B></td>
						<td>Reserved Category, Unreserved Category.</td>
						</tr>

</table>
</P>
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/addcategory.png"  width="100%">
                                        </div>
                                        </section>


			<section id="ViewCategaryDetail">
                                        <div class="row-fluid">
                                                <h2>View Category Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Admin can also view category after adding Category Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/community.png" width="100%">
                                                </font>
                                        </div>
                                        </section>
 <section id="StudyCenter">
                                        <div class="row-fluid">
                                                <h2>Study Center</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin can add study center by Select your University, Campus Code, Campus Name, Campus Nick Name, Address, Country, State, City, District, Pincode, Phone, Fax, Status, Start Date, Close Date, Website, Incharge, Mobile.<br>
The format of Add Study Center are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Choose your University</B></td>
                                                <td>Ex:-Indira Gandhi National Tribal University</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Code</B></td>
                                                <td>Ex:-CU001</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Name</B></td>
                                                <td>Ex:-Regional Campus</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Nick Name</B></td>
                                                <td>Ex:-RC</td>
                                                </tr>
                                                <tr>
                                                <td><B>Address</B></td>
                                                <td>Enter Address</td>
                                                </tr>
                                                <tr>
                                                <td><B>Country</B></td>
                                                <td>Select Country</td>
                                                </tr>
                                                <tr>
                                                <td><B>State</B></td>
                                                <td>Select State</td>
                                                </tr>
                                                <tr>
                                                <td><B>City</B></td>
                                                <td>Select City</td>
                                                </tr>
                                                <tr>
                                                <td><B>District</B></td>
                                                <td>Enter District</td>
                                                </tr>
						 <tr>
                                                <td><B>Pincode</B></td>
                                                <td>Enter Pincode</td>
                                                </tr>
                                                <tr>
                                                <td><B>Phone</B></td>
                                                <td>Enter Phone Number</td>
                                                </tr>
                                                <tr>
                                                <td><B>Fax</B></td>
                                                <td>Enter Fax Number</td>
                                                </tr>
                                                <tr>
                                                <td><B>Status</B></td>
                                                <td>Enter Status Ex:-Active, Inactive</td>
                                                </tr>
                                                <tr>
                                                <td><B>Start Date</B></td>
                                                <td>Select Start Date </td>
                                                </tr>
                                                <tr>
                                                <td><B>Close Date</B></td>
                                                <td>Select Close Date</td>
                                                </tr>
                                                <tr>
                                                <td><B>Website</B></td>
                                                <td>Enter Your Website</td>
                                                </tr>
                                                <tr>
                                                <td><B>Incharge</B></td>
                                                <td>Enter Incharge Name</td>
                                                </tr>
                                                <tr>
                                                <td><B>Mobile</B></td>
                                                <td>Enter Mobile Number</td>
                                                </tr>
                                                </table>
                                                </P>
                                                                 </p>
                                                        </ol>
                                                </font>
		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/studycenter.png" width="100%">
                                        </div>
                                        </section>
<section id="ViewStudyCenter">
                                        <div class="row-fluid">
                                                <h2>View Study Center</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                                Admin can also view study center after adding study center Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/viewstudycenter.png" width="100%">
                                                </font>
                                        </div>
                                        </section>

			<section id="Department">
                                        <div class="row-fluid">
                                                <h2>Department</h2>
                                                 <h2>Add Department</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
			Admin can add department by Choose your University, Choose your Campus, School Code, School Name, Department Code, Department Name, Department Nick Name and Department Description.<br>
                               The format of Add category are given below:-  
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Choose your University</B></td>
						<td>Ex:-Indira Gandhi National Tribal University</td>
						</tr>
						<tr>
						<td><B>Choose your Campus</B></td>
						<td>Ex:-Indira Gandhi National Research Center</td>
						</tr>
						<tr>
						<td><B>School Code</B></td>
						<td>SBS</td>
						</tr>
						<tr>
						<td><B>School Name</B></td>
						<td>School of Basic Science </td>
						</tr>
						<tr>
						<td><B>Department Code</B></td>
						<td>Phy001</td>
						</tr>
						<tr>
						<td><B>Department Name</B></td>
						<td>Physics Department</td>
						</tr>
						<tr>
						<td><B>Department Nick Name</B></td>
						<td>Phy</td>
						</tr>
						<tr>
						<td><B>Department Description</B></td>
						<td>Department of Physics</td>
						</tr>
						</table>
						</P>                                


</p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/adddepartment.png"  width="100%">
                                        </div>
                                        </section>
<section id="ViewDepartmentDetail">
                                        <div class="row-fluid">
                                                <h2>View Department Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Admin can view department after adding department Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/department.png"  width="100%">
                                        </div>
                                        </section>
<section id="Designation">
                                        <div class="row-fluid">
                                                <h2>Designation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                        Admin can add Designation by enter Designation Name, Designation Code, Designation short, Designation Description.<br>
                         The format of Add category are given below:-
			<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Name</B></td>
                                                <td>Enter Designation Name Ex:-Manager, Assistant Manager</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Code</B></td>
                                                <td>Enter Designation Code.Ex:-01,02</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation short</B></td>
                                                <td>Enter Designation short. Ex:-AM, AR</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Description</B></td>
                                                <td>Enter Designation Description.</td>
                                                </tr>
						 </table>
                                                </P>
                                                </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/adddesignation.png" width="100%">
                                        </div>
                                        </section>
<section id="ViewDesignation">
                                        <div class="row-fluid">
                                                <h2>View Designation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                                Admin can view Designation after adding Designation Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/designation.png"  width="100%">
                                        </div>
                                        </section>


  <section id="Authority">
                                        <div class="row-fluid">
                                                <h2>Authority</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                        Admin can add  Authority by enter Authority Name, Authority Nick Name,  Authority Email.<br>
                         The format of Add category are given below:-
                        <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B> Authority Name</B></td>
                                                <td>Enter Authority Name Ex:-Director, Finance Officer, Deputy Registar Account, Assistant Registar Account</td>
                                                </tr>
                                                <tr>
                                                <td><B>Authority Nickname</B></td>
                                                <td>Enter Authority Nickname.Ex:-FO, DRA, ARA</td>
                                                </tr>
                                                <tr>
                                                <td><B>Authority Email</B></td>
                                                <td>Enter Authority Email. Ex:-aracc@iitk.ac.in, dracc@iitk.ac.in</td>
                                                </tr>
                                                 </table>
                                                </P>
                                                </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/adduco.png"  width="100%">
                                        </div>
                                        </section>
<section id="ViewAuthority">
                                        <div class="row-fluid">
                                                <h2>View Authority</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                                Admin can view Authority after adding Authority Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/uco.png"  width="100%">
                                        </div>
                                        </section-->
<section id="Scheme">
                                        <div class="row-fluid">
                                                <h2>Scheme</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin can add scheme by filling  department name, scheme Name, scheme Code, Scheme Short name , Scheme Description.
                                                                 </p>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="3px solid black" style="width:700px">
						<tr>
						<th><B>Field</B></th>
						<th><B>Description</B></th>
						</tr>
						<tr>
							<td><B>Department Name</B></td>
							<td> Computer Science</td>
						</tr>
						<tr>
						<td><B>Scheme Name</B></td>
						<td>Admin Director</td>
						</tr>
						<tr>
						<td><B>Scheme Code</B></td>
						<td>002</td>
						</tr>
						<tr>
						<td><B>Scheme Short Name </B></td>
						<td>ADDIR</td>
						</tr>
						<tr>
						<td><B>Scheme Description</B></td>
						<td>director can add the admin description</td>
						</tr>
</table>
</P>

                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setup/scheme.png"width="100%">
                                                </font>
                                        </div>
                                        </section>

<section id="DDO">
                                        <div class="row-fluid">
                                                <h2>DDO List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Admin can add DDO by adding Campus ,Department ,Scheme ,DDO cade ,DDO name and Remark .
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/ddo.png"  width="100%">
                                        </div>
                                        </section>


                                    </section-->
<section id="UserwithRole">
                                        <div class="row-fluid">
                                                <h2>

                                                 User with Role</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
From here admin can 

 User with Role by Choose your Campus, Choose your Department, Select your Role, Select User Type, Select Username.<br>
The format of 

 User with Role list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
						<tr>
                                                <td><B>Choose your Campus</B></td>
                                                <td>Select Campus from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Choose your Department</B></td>
                                                <td>Select Department from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Select your Role</B></td>
                                                <td>Select Role from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>User Type</B></td>
                                                <td>Select User Type from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Select Username</B></td>
                                                <td>Select Username from dropdown.</td>
                                                </tr>
						</table>
						</P>
                                                                 </p>
                                                        </ol>
                                                </font>
                                                <li>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/userrole.png" width="100%" > 
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/userrole2.png" width="100%" > 

</li>
                                         </div>
                                    </section>

<section id="AuthorityandUser">
                                        <div class="row-fluid">
                                                <h2>

                                                 Authority and User</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin can View 

 Authority and User  List after adding 

 Authority and User  details (as shown) . Admin can edit and delete this data.
                                                        </p>
                                                        </ol>
                              <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/mapauthorityanduserlist.png" width="100%">       
                            <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/viewmapauthorityanduserlist.png" width="100%">
					</font>
                                         </div>
                                    </section>


<section id="StudyCenterwithUO">
                                        <div class="row-fluid">
                                                <h2>Map Study Centre with UO</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify">
                                        				Adimn can add study center with UO by adding Campus name 
                                        				and authority name
                                        			</p>
                                        		</ol>
                                 <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/mapstudycenterwithuo.png"  width="100%">
                             <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/viewmapstudycenterwithuo.png"  width="100%">
                                        	</font>
                                        </div>
</section>
<section id="SetUO">
                                        <div class="row-fluid">
                                                <h2>University Officer</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin can add user authority <br>
Enter the details in the given fields.<br>
Fill all information and now press the Submit Button for setting UO
                                                        </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/setuo.png" width="98%" > 
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/viewuo.png" width="98%" >       
					</font>
                                         </div>
                                    </section>
<section id="SetHOD">
                                        <div class="row-fluid">
                                                <h2>Set Head OF Department</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin can add HOD by filling respective field properly ,
                                                        </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/sethod.png"  width="100%" >   
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/viewhod.png"  width="100%" >
                                         <br><br><br><br><br><br>    
					</font>
                                         </div>
                                    </section>

		<section id="UserwithSocieties">
                                        <div class="row-fluid">
                                                <h2>User With Societies</h2>
                                        </div>
                                       <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">

For mapping user with societies admin need to fill Society Name ,Head ,Secretary, Treasure , members, Total values <br>
As per shown below <br>
                                                        </p>
                                                        </ol>
                            <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/mapuserwithsociety.png"  width="100%">
                            <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/viewmapuserwithsociety.png"  width="100%">  
                                         <br><br><br>     
					</font>
                                         </div>
</section>
<section id="TypeofStore">
                                        <div class="row-fluid">
                                                <h2>Type of Store</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
Admin have to add tpye of store and their full description 
                                                        </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/storetype.png" width="98%">       
					</font>
                                         </div>
                                    </section>

<section id="Financial">
                                        <div class="row-fluid">
                                                <h2>Financial power</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify">
                                        				Admin can add  financial power by mentioning Type of purchase ,sub-purchase , "Authority",
                                        				financial limits , and item description   
                                        			</P>
                                        		</ol>
                                        		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/financialpower.png" width="100%">
                                        	</font>
                                        	
                                        </div>
</section>




<section id="PurchaseType">
	<div class="row-fluid">
		<h2>Purchase type <h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<ol>
				<p align="justify" STYLE="line-height: 150%" >
					Adimn can add purchase type such Minor ,medium or major <br>
					sub purchase type <br>
					Amount
				</p>
			</ol>
		</font>
		 <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/purchasetype.png"  width="100%">
	</div>
	
</section>


 
 <section id="PurchaseCommitteeFormationRules">
 	<div class="row-fluid">
 		<h2>Purchase Commitee Formation Rules <h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<P aling="justify" STYLE="line-height: 150%">

 					along with represntative Admin also have to mention Aproving Authority, Purchase price(estimation) , and Purchase mode
 					
 				</P>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/purchasecommitte.png"  width="100%">
 	</div>

 </section>





 <section id="ModeOfTender">
 	<div class="row-fluid">
 		<h2>Mode Of Tender</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify" STYLE="line-height:150%">
 					Mode of tender include the description of various mode of tender for example Rate Contract,Single tender
 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/modeoftender.png" width="100%" >
 	</div>
 </section>




 <section id="CoverType">
 	<div class="row-fluid">
 		<h2>Cover Type</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify">
 					Admin have to enter Cover no, Fixed cover<br>
 					Cover type 1,2,3 etc <br>

 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/covertype.png"  width="98%" >
 	</div>
 </section>





 <section id="BankList">
 	<div class="row-fluid">
 		<h2>Bank List</h2>
 	</div> 
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify">
 					Admin can list bank by adding different colunm of bank detail form <br>
 					Campus Name <br>
 					University Officer Control,
 					Scheme Name ,Bank Name Branch , Bank address ,and bank detail
 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/bankname.png"  width="98%" >
 	</div>
 </section>




 <section id="supplier">
 	
 	<div class="row-fluid">
 		<h2>Adding Supplier Detail</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify" STYLE="line-height:150%">
 					Supplier registration deatil form must be filled properly <br>

 					uploading doc must be of PDF or JPG format<br>
 					PAN and GST no is compulsary
 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/pico/supplierdetail.png"  width="98%" >
 	</div>
 </section>



 <section id="Specification">
 	<div class="row-fluid">
 		<h2>specification</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify">
 					 User have specify enquiry Date limits ,Enquiry No , Item Name , and Item Quantity<br>
 					 User have also have to mention detail of himself in "intender Detail"
 					
 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/addspecification.png"  width="100%" >
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/specification.png"  width="100%" >
 	</div>
 </section>



 <section id="Committee">
 	<div class="row-fluid">
 		<h2> Committee</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4">
 			<ol>
 				<p align="justify">
 					committee selection will be different in various purchase mode , estimated price ,Department ,
 					Conveyner , and Represntative <br>
 					authority will be given some authorised personel eg : Deputy Director
 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/committee.png"  width="100%" >
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/viewcommittee.png"  width="100%" >
 	</div>
 </section>




 <section id="TenderForm">
 	<div class="row-fluid">
 		<h2> Tender Form</h2>
 	</div>
 	<div class="row-fluid">
 		<font size="4"> 
 			<ol>
 				<p align="justify">
 					Supplier have to filled the tender form after registration <br>
 					also have to clearify mode (online/offline) <br>
 					for online payment mode list of bank is given <br>

 				</p>
 			</ol>
 		</font>
 		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/tenderform.png"  width="100%" >
 	</div>
 </section>





<section id="InspectionReport">
	<div class="row-fluid">
		<h2> Inspection report</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<ol>
				<p align="justify">
					To see Inspection Report user have to enter tender ID
				</p>
			</ol>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/intenderprocess/inspectionreport.png"  width="100%">
	</div>
</section>










<section id="TendersList">
	 <div class="row-fluid">
	 	<h2>TENDERS LIST</h2>
	 </div>
	 <div class="row-fluid">
	 	<font size="4">
	 		<ol>
	 			<p align="justify">
	 				Admin can view list of tender complete or incomplete
	 			</p>

	compelete tender <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderlist1.png"width="98%" >
	 incomplete <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderlist.png" width="98%" >
	 		</ol>
	 	</font>
	 	
	 </div>
</section>






<section id="TenderApply">
	 <div class="row-fluid">
	 	<h2>Applying of Tender</h2>
	 </div>
	 <div class="row-fluid">
	 	<font size="4">
	 		<ol>
	 			<p align="justify">
	 			supplier can apply for tender ,can view and also query(if any) 
	 			<li>tenders list</li>
	 			    <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderapply1.png" width="99%" >  
	 			</p>
	 			<p align="justify">
	 				<li>Tender Detail</li>
	 				<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderapply2.png" width="99%" >
	 			</p>
	 			<p align="justify">
	 				<li>view tender</li>
	 				<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderapply3.png" width="99%" >
	 			</p>
	 			<p align="justify">
	 				<li>Query
	 				<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderapply4.png" width="99%" ></li>
	 			</p>
	 		</ol>
	 	</font>
	 </div>
</section>


 

<section id="TenderRequests">
    <div class="row-fluid">
        <h2>tender request list</h2>
    </div>
    <div class="row-fluid">
        <font size="4">
            <ul>
              <p align="justify">
              	
              	<li> Admin can see request list here

<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/tenderrequest1.png"  width="100%">
             </li>
             </p>
             <p align="justify">
             	<li>
             		on clicling view application you will be forwarded to full list of tender request <br>
             		here you can see approved list as well rejected list <br>
             		Also can re-approve a rejected list
             		<img src="<?php echo base_url() ?>helpimages/picohelpscreenshot/purchasemanagement/tenderrequest2.png"  width="100%">
             	</li>
             </p>
            </ul>
        </font>
                                         </div>
                                    </section>








<section id="ComparativeStatement">
        <div class="row-fluid">
                                                <h2>Comparative Statement</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
                                                  	Admin can view a camparative statement after comparing tender request
                                                  	 </p>
             <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/camparitivestatement1.png"  width="99%">
                                                                <p align="justify">
                                                                 	Admin can complete proposal form by clikcing on "complete proposal" <br>
            <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/camparitivestatement2.png" width="99%"> <br>
                                                                 </p>
                                                        </ol>
                                              </font>

                                         </div>
                                    </section>







                                    
<section id="PurchaseProposal">

               <div class="row-fluid">
                          <h2>Purchase Proposal</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                      
                                                        	<br>
                                                        	<br>
                                                                <p align="justify">
  admin can view purchase proposal here  
And can go for order completion<
<br>
                                      </p>
                  <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/purchaseproposal1.png"  width="100%">
                                                     
                                                    </font>
                                         </div>
                                    </section>




<section id="PurchaseApprovalviaGeM">
                                        <div class="row-fluid">
                                                <h2>Purchsae Approval via GeM</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        
                                                                <p align="justify">
              Purchase Approval via GeM have to fill different form

                      
                                     </p>
                                                        
                            <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/gem1.png" width="100%">
                                                        <p align="justify">
                                               purchase through GeM will shown in the Section         	
                                         <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/gem.png" width="100%">

                                        </p>

                                                </font>


                                         </div>
                                    </section >





			<section id="PurchaseOrder">
                                        <div class="row-fluid">
                                                <h2>Purchase Order </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                       
                                                                <p align="justify">
                                                                	order completion  can be completed by filling tender id and name in highligted area 
                                                                 </p>
                                                    
                                                </font>
                     <img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/purchasemanagement/purchaseorder.png" width="100%" >
                                         </div>
            </section>



          	<section id="ItemReceived">
        		<div class="row-fluid">
        			<h2>Item Received </h2>
        		</div>
        		<div class="row-fluid">
        			<font size="4">
        				<ol>
        					<p align="justify">
        					Item recieved will have to filled by reciever for record<br>
        					and can view recieved item<br>
        						<li>
        						<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/recieveditem1.png"  width="100%" >
        						</li>
        						<li>
        						<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/recieveditem2.png"width="100%">
        						</li>
        					</p>
        				</ol>
        			</font>
        		</div>
        	</section>       



       		<section id="ItemIssued">
        		<div class="row-fluid">
        			<h2> Item Issued</h2>
        		</div>
        		<div class="row-fluid">
        			<font size="4">
        				<p align="justify">
        					
        					Item isseud can be seen here
        					</p>
        					<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/issueitem1.png"  width="100%">
        					
        					<p>
        					issueing item need to be filled by issuer
        				</p>

        					<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/issueitem.png"  width="100%">
        				
        				
        			</font>
        		</div>
       		</section>   


         	<section id="ItemReturn">
         		<div>
         			<h2> item Return</h2>
         		</div>
         		<div class="row-fluid">
         			<font size="4">
         				<p align="justify">
         					<ol>
         					item return status will show here
         					<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/returnitem.png"  width="100%">
         					<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
         					</ol>
         				</p>
         	</section>   



   			<section id="InspectionReports">
   		<div class="row-fluid">
   		<h2> Inspection report</h2>
   	</div>
   	<div> 
   		<font size="4">
   			<p align="justify">
   		report generated are shown 
   		
   			</p>
   		</font>
   		<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/recieveandstore/inspectionreport1.png"  width="100%">
   	</div>
   </section>









			<!--section id="Upload">
                                        <div class="row-fluid">
                                                <h2>Upload</h2>
                                        </div>
                         </section>
			<section id="UploadLogo">
                                        <div class="row-fluid">
                                                <h2>Upload Logo</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
				Administrators can upload their logo from here. Image must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>uploads/SLCMS/helpimages/picohelpscreenshot/uploadlogo.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>
			




<section id="Archives">
                                        <div class="row-fluid">
                                                <h2>Archives</h2>
                                        </div>
                                        </section>




<section id="AuthorityArchive">
                                        <div class="row-fluid">
                                                <h2>Authority Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
This module will display the earlier data. which is not used now.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/helpimages/picohelpscreenshot/sspdarchive.png"  width="100%">
                                         </div>
                                    </section>
<!--section id="AuditTrails">
                                        <div class="row-fluid">
                                                <h2>Audit Trails</h2>
                                        </div>
<section id="LogDetails">
                                        <div class="row-fluid">
                                                <h2>Log Details</h2>
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
This page consists of View Audit Trails, Insert Audit Trails, Update Audit Trails and summary recent activity. The page is constantly updated on the basis of the activities carried out in the system.	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpimages/picohelpscreenshot/audittrails.png"  width="100%">
                                         </div>
                                    </section-->






<section id="Profile">
                                        <div class="row-fluid">
                                                <h2>Profile</h2>
                                        </div>
</section>
			<section id="ViewProfile">
                                        <div class="row-fluid">
                                                <h2>View Profile</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
    User Profile display of personal data associted with specific user. From here user see Login Information and Other Information.
                                                                 </p>
                                                        </ol>
                                                </font>
	<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/profile.png" width="100%">
                                         </div>
                                    </section>
<section id="ChangePassword">
                                        <div class="row-fluid">
                                                <h2>Change Password</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
    Password can be changed from the Change Password link. Each user must enter the old password and new password after then Retype New Password for the user.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/changepassword.png" width="100%">

                                         </div>
                                    </section>


<section id="ChangeEmployeePassword">
                                        <div class="row-fluid">
                                                <h2>Change Employee Password</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify">
    Password can be changed from the Change Employee Password link. Each user must enter the email and new password
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/picohelpscreenshot/employeepassword.png" width="100%">

                                         </div>
                                    </section>

					</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

