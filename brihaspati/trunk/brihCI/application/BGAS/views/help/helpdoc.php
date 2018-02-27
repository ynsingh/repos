<html>
<head>
	<link rel="stylesheet" type="text/css" href="<?php echo asset_url(); ?>assets/bgas/css/helpdoc.css"/>
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/scroll.css"/>
        <script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/scroll.js"></script>
</head>
<body>
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="head">Brihaspati General Accounting System</div>
	<div class="content">
	<div class="sideleft">
        <div id="cssmenu">
        	<ul>
	             <li><a href="#AboutBrihaspatiGeneralAccontingSystem">About Brihaspati General Acconting System</a></li>
	             <li class='has-sub'><a href="#Administrator(Home)">Administrator(Home)</a>
	        	<ul>
				<li><a href="#ChangeAccount">Change Account</a></li>
				<li><a href="#CreateAccount">Create Account</a></li>
                                <li><a href="#ManageAccounts">Manage Accounts</a></li>
				<li class='has-sub'><a href="#ManageUsers">Manage Users</a>
				<ul>    
                                <li><a href="#AddUser">Add User</a></li>
                                </ul>
                                </li>
				<li><a href="#ManageMailSetting">Manage Mail Setting</a></li>
	                        <li class='has-sub'><a href="#ManageAuthorityAllocation">Manage Authority Allocation</a>
				<ul>
                                <li><a href="#AllocateAuthority">Allocate Authority</a></li>
                                </ul>
                                </li>
	                        <li><a href="#MySQLAdminSetting">MySQL Admin Setting</a></li>
				<li class='has-sub'><a href="#ManageAuthority">Manage Authority</a>
				<ul>
                                <li><a href="#AddAuthority">Add Authority</a></li>
                                </ul>
                                </li>
	                </ul>
			</li>
		      <li><a href="#Profile">Profile</a></li>
	              <li><a href="#AccountsFunctionality">Accounts Functionality</a></li>
	              <li><a href="#Dashboard">Dashboard</a></li>
	              <li class='has-sub'><a href="#Budget">Budget</a>
	                 <ul>
	                        <li><a href="#AddBudget">Add Budget</a></li>
	                        <li><a href="#ReappropriateBudget">Reappropriate Budget</a></li>
	                        <li><a href="#Projection">Projection</a></li>
	                        <li><a href="#AddProjection">Add Projection</a></li>
	                        <li><a href="#ReappropriateProjection">Reappropriate Projection</a></li>
	                 </ul>
                         </li>
	             <li><a href="#ChartofAccounts"><i class="icon-chevron-right"></i>Chart of Accounts</a></li> 
	             <li class='has-sub'><a href="#Vouchers">Vouchers</a>
	                 <ul>
	    		       <li><a href="#ViewAllVouchers">View All Vouchers</a></li>
	    		       <li><a href="#CreateJournalVoucher">Create Journal Voucher</a></li>
	    		       <li class='has-sub'><a href="#BillUpload/VoucherCreation">Bill Upload / Voucher Creation</a>
	                            <ul>
	        		         <li><a href="#ViewBill">View Bill</a></li>
	                               	 <li><a href="#BillUpload">Bill Upload</a></li>
	        	            </ul> 
				<li><a href="#BillUpload/VoucherCreationWithMultipleVarification">Bill Upload / Voucher Creation With Multiple Verification</a></li>
	                 </ul>
                         </li>
			</li>
	             <li class='has-sub'><a href="#Reports">Reports</a>
	                  <ul>
	                        <li class='has-sub'><a href="#BalanceSheet">Balance Sheet</a>
	                       	    <ul>
	                            	 <li><a href="#CorporateFormat">Corporate Format</a></li>
	                                 <li><a href="#MHRDFormat">MHRD Format 2015</a></li>
	                       	    </ul>
                               </li>
                               </li>
	             <li class='has-sub'><a href="#IncomeandExpenditureStatement">Income and Expenditure Statement</a>
	                             <ul>
	                                 <li><a href="#CorporateFormatIE">Corporate Format IE</a></li>
	                                 <li><a href="#MHRDFormatIE">MHRD Format IE 2015</a></li>
	                            </ul>
                     </li>       
	            			 <li><a href="#Payment&Receipt">Payment & Receipt</a></li>
	             			 <li><a href="#TrialBalance">Trial Balance</a></li>
	             		         <li><a href="#LedgerStatement">Ledger Statement</a></li>
	             			 <li><a href="#Reconciliation">Reconciliation</a></li>
	             			 <li><a href="#Depreciationastoday">Depreciation as Today</a></li>
	             			 <li><a href="#DayBook">Day Book</a></li>
	                    		 <li><a href="#CashBook">Cash Book</a></li>
                                       	 <li class='has-sub'><a href="#UnspentBalance">Unspent Balance</a>
                                           <ul>
						<li><a href="#PlanReport">Plan Report</a></li>
                                                <li><a href="#Non-PlanReport">Non-Plan Report</a></li>
                                                <li><a href="#SummaryReport">Summary Report</a></li>
	                   		  </ul>
                                     </ul>
                                </li>
	            <li class='has-sub'><a href="#OtherReport">Other Report</a> 
	                  <ul>
			       <li><a href="#FundList">Fund List</a></li>
			       <li><a href="#TagReport">Tag Report</a></li>
			       <li><a href="#PartyReport">Party Report</a></li>
			             <li class='has-sub'><a href="#LogReport">Log Report</a>
		                           <ul>
					       <li><a href="#ChartofAccountLog">Chart of Account Log</a></li>
					       <li><a href="#TransactionLog">Transaction Log</a></li>
					       <li><a href="#BudgetLog">Budget Log</a></li>
					       <li><a href="#OtherLog">Other Log</a></li>
				           </ul>
				      </li>	
			                  <li><a href="#TDSReport">TDS Report</a></li> 
			                  <li><a href="#SundryCreditorsReport">Sundry Creditors Report</a></li> 
			                  <li><a href="#SundryDebitorsReport">Sundry Debitors Report</a></li> 
	                    </ul>
	            </li>
	          <li class='has-sub'><a href="#Setting">Setting</a>
			<ul>
				<li><a href="#AccountSettings">Account Settings</a></li>
				<li><a href="#C/FAccount">C/F Account</a></li>
				<!--li><a href="#EmailSettings">Email Settings</a></li-->
				<li><a href="#PrinterSettings">Printer Settings</a></li>
				<li><a href="#CreateBackup">Create Backup</a></li>
				<li><a href="#Tags">Tags</a></li>
				<li><a href="#EntryTypes">Entry Types</a></li>
				<li><a href="#UploadLogo">Upload Logo</a></li>
		       </ul>
	          </li>
	         <li><a href="#PayrollSetup">Payroll Setup</a></li>
	         <li class='has-sub'><a href="#Party">Party</a>
	               <ul>
			        <li><a href="#ViewParty">View Party</a></li>
			        <li><a href="#AddParty">AddParty</a></li>
		      </ul>
	          </li>
	          <li><a href="#Help">Help</a></li>
	          <li><a href="#ChangePassword">Change Password</a></li>
            		</ul>                               
                </div>
            </div>
                <div class="sideright">
		<div id="sectionbgas">
		<div style="display:none;" class="scroll_up" id="scroll_up"></div>
                <div style="display:none;" class="scroll_down" id="scroll_down"></div>
					<section id="AboutBrihaspatiGeneralAccontingSystem">
					<div class="row-fluid">
						<h2>About Brihaspati General Acconting System</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
								BGAS is an Accounting System than creates reports of financial statement.  To provide a standardise web based, full featured Double Entry Accrual Accounting system Prescribed by MHRD for all Acedemic and Educational Institutions in India. BGAS is standard integrated accounting and genral ledger system. It is composed of a set of a module that correspond to the main business function of accounting management modules include Genral, Ledger Accounts, Payable Acounts, Fixed and Personal Assets. 
								</p>
							</ol>
						</font>
					</div>
					</section>

					<section id="Administrator(Home)">
						<div class="row-fluid">
							<h2>Administrator(Home)</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
									Administrator can login into system by filling username and password.
									</p>
								</ol>
							</font>
						<?php
						echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'adminhome.png', 'width' => 1300, 'height' =>700  ));
						?>
						</div>
					</section>


					<section id="ChangeAccount">
						<div class="row-fluid">
							<h2>Change Account</h2>
						</div>

						<div class="row-fluid">
							<font size="4">
									<ol>
									<p align="justify" STYLE="line-height: 150%">	
										From here we can create account and manage account.
									</p>						
								</ol>
							</font>
						
						<?php
						echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'changeaccount.png', 'width' => 1300, 'height' =>860));
						?>
						</div>
					</section>


					<section id="CreateAccount">
						<div class="row-fluid">
							<h2>Create Account</h2>
						</div>

						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
										User (Admin) can create account by filling all data.
									</p>
								</ol>
							</font>
							<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%"><BR>
							<table border="1" style="width:620px">
								<tr>	
									<td><B>Field</B></td>
									<td><B>Description</B></td>
								</tr>
								<tr>
									<td><B>Lable</B></td>
									<td>Enter labal.(Example : bgasiitk)</td>
								</tr>
								<tr>
									<td><B>Organization Name</B></td>
									<td>Enter Organization Name. (Ex-IITK)</td>
								</tr>
								<tr>
									<td><B>Account Unit Name</B></td>
									<td>Enter Account Unit Name.(ACES Lab 301)</td>
								</tr>
								<tr>
									<td><B>Account Name</B></td>
									<td>Enter Account Name.(EX.iitkcanteen)</td>
								</tr>
								<tr>
									<td><B>Account Address</B></td>
									<td>Enter Account Address.(Ex-staff canteen iitk)</td>
								</tr>
								<tr>
									<td><B>Account Email</B></td>
									<td>Enter Account Email.(Ex-staffcanteen@gmail.com)</td>
								</tr>
								<tr>
									<td><B>Currency</B></td>
									<td>Enter Currency. (Ex-INR)</td>
								</tr>
								<tr>
									<td><B>Date Format</B></td>
									<td>Select Date Format. (Ex-Day/Month/Year)</td>
								</tr>
								<tr>
									<td><B>Financial Year Start</B></td>
									<td>Enter financial year start date. (Warning : Financial Year Start cannot be changed later.Format as per 'Date Format' selected above.)</td>
								</tr>
								<tr>
									<td><B>Financial Year End</B></td>
									<td>Enter financial year end date. ( Warning : Financial Year End cannot be changed later.Format as per 'Date Formet' selected above.)</td>
								</tr>
								<tr>
									<td><B>Timezone</B></td>
									<td>Select timezone.(Ex.-(UTC + 5:30) India Standard Time,Sri Lanka Time)</td>
								</tr>
								<tr>
									<td><B>Chart of Account</B></td>
									<td>Select chart of account.(Ex-MHRD Fromat)</td>
								</tr>
							</table>
							
							<P ALIGN=LEFT STYLE="margin-bottom: 0in; line-height: 150%"><FONT SIZE=3 STYLE="font-size: 13pt"><B>Database Setting</B></FONT></P>
							
							<table border="1" style="width:620px">
								<tr>
									<td><B>Field</B></td>
									<td><B>Description</B></td>
								</tr>
								<tr>
									<td><B>Database Name</B></td>
									<td>Enter Database Name.(Ex-bgas)</td>
								</tr>
								<tr>
									<td><B>Database Username</B></td>
									<td>Enter Database Username.(Ex-bgasiitk)</td>
								</tr>
								<tr>
									<td><B>Database Password</B></td>
									<td>Enter Database Password.(Ex-bgasiitk)</td>
								</tr>
								<tr>
									<td><B>Database Host</B></td>
									<td>Enter Database Host. (Ex-localhost)</td>
								</tr>
								<tr>
									<td><B>Database Port</B></td>
									<td>Enter Database Port. (Ex-3306)</td>
								</tr>
							</table>
							
							<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%"><BR>
							<ol>
								<B>Note: After account creation go to Settings --> Account Settings page to set the ledger name to which the profit and loss balance will be carry forward.</B>
							</ol>
							
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'createaccount.png','width' => 1300, 'height' =>1100));
							?>
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'createaccount1.png','width' => 1300, 'height' =>700));
							?>
						</div>
					</section>


					<section id="ManageAccounts">
						<div class="row-fluid">
							<h2>Manage Accounts</h2>
						</div>

						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
										From here we can manage account and we ca see all currently active account.
									</p>
								</ol>
							</font>		
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'manageaccount.png','width' => 1300, 'height' =>700));
							?>
						</div>
					</section>
				

					<section id="ManageUsers">
						<div class="row-fluid">
							<h2>Manage Users</h2>
						</div>

						<div class="row-fluid">
							<font size="4">
								<ol>
								<p align="justify" STYLE="line-height: 150%">
								This link is for adding users for login & also assingn role & permission to other user.
								</p>
								</ol>
							</font>
							
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'manageuser.png','width' => 1300, 'height' =>700));
							?>
						</div>

					<section id="AddUser">
                                                <div class="row-fluid">
                                                        <h2>Add User</h2>
                                                </div>

                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
							<b>1.)</b> Click on Manage Users in Administer panel and then click on Add User.<br>
							<b>2.)</b> Enter email of user in Username field, password in Password field, email of user in Email field, first name of user in Firstname field 
							and last name of user in Lastname field.If the mobile number and UID number of user are available then enter the mobile number and UID number in
							Mobile No. field and UID No. field otherwise leave it as blank.<br>
							<b>3.)</b> Select the role of the user in Role drop-down and category type in Category Type drop-down, select the account name in which account of 
							the user will be created in Select Accounts and nowclick on Add button.
                                                                </p>
                                                                </ol>
                                                        </font>

                                                        <?php
                                                        echo img(array('src' => base_url() . "/assets/bgas/images/ BGAScreenshot/" . 'adduser.png','width' => 1300, 'height' =>700));
                                                        ?>
                                                </div>

					</section>
				        <section id="ManageMailSetting">
                                                <div class="row-fluid">
                                                        <h2>Manage Mail Setting</h2>
                                                </div>

                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                	<p align="justify" STYLE="line-height: 150%">
							This link for setting email configuration. You can select Email Protocol than Enter Email Hostname, Email Port, Email Username, Email Password 
							(Leave empty if you dont not want to change password) then cilck on update button.
									</p>
                                                                </ol>
                                                        </font>
							<?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'managemailsetting.png','width' => 1300, 'height' =>700));
                                                        ?>
						</div>
                                        </section>
					 <section id="ManageAuthorityAllocation">
                                                <div class="row-fluid">
                                                        <h2>Manage Authority Allocation</h2>
                                                </div>

                                                <div class="row-fluid">
                                                        <font size="4">
                                                                	<ol>
                                                               			 <p align="justify" STYLE="line-height: 150%">
					This link to provide Allocate Authorities to Users. From here we can see all list of authorities with User Name, User Email, From Date, Till Date.
										</p>
                                                                </ol>
                                                        </font>
							<?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'manageauthorityallocation.png','width' => 1300, 'height' =>700));
                                                        ?>
						</div>
					</section>
					<section id="AllocateAuthority">
                                                <div class="row-fluid">
                                                        <h2>Allocate Authority</h2>
                                                </div>

                                                <div class="row-fluid">
                                                        <font size="4">
                                                                        <ol>
                                                                                 <p align="justify" STYLE="line-height: 150%">
					<b>1.)</b> Only administer can add authorities. Click on Manage Authority Allocation in Administer panel, now to allocate authority click on Allocate Authority 
					button.<br>
					<b>2.)</b> Select the name of authority from Authority Name drop-down(To add the name of authorities read ADD AUTHORITY in this doc) and select the name of the 
					user from UserName drop-down(To add the name of user read ADD USER in this doc).<br>
					<b>3.)</b> Select type of authority from Authority Type drop-down. Select Acting from Authority Type drop-down if the person is going to serve as acting authority 
					otherwise select FullTime.<br>
					<b>4.)</b> Enter the date on which user starts serving as selected authority in From Date and the date on which user stops serving as selected authority in Till 
					Date and now click on Add button.<br>
                                                                                </p>
                                                                </ol>
                                                        </font>
                                                        <?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'AllocateAuthority.png','width' => 1300, 'height' =>700));
                                                        ?>
                                                </div>
                                        </section>


				<section id="MySQLAdminSetting">
				<div class="row-fluid">
					<h2>MySQL Admin Setting</h2>
				</div>

				<div class="row-fluid">
					<font size="4">
					 	<ol>
							<p align="justify" STYLE="line-height: 150%">
				This link is to username & password & save into a file in BGAS/config/accounts named as sqladmin.ini.
							</p>
						</ol>
					</font>	
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'adminsetting.png','width' => 1300, 'height' =>700));
							?>
				  </div>
				</section>
				<section id="ManageAuthority">
                                	 <div class="row-fluid">
                                                        <h2>Manage Authority</h2>
                                         </div>
                                         <div class="row-fluid">
                                         	<font size="4">
                                               		 <ol>
                                                         	<p align="justify" STYLE="line-height: 150%">
				This link to provide to add Authority to Users. From here we can see all list of Authority Name, Authority Nick Name, Authority Email.
								</p>
                                                        </ol>
                                                </font>
							<?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'manageauthority.png','width' => 1300, 'height' =>700));
                                                        ?>
                                        </div>

				<section id="AddAuthority">
                                         <div class="row-fluid">
                                         	<h2>Add Authority</h2>
                                         </div>
                                         <div class="row-fluid">
                                                <font size="4">
                                                         <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				<b>1.)</b>Click on Manage Authorities in Administer panel and then click on Add Authority.</br>
				<b>2.)</b> Enter the name of the authority in Authority Name field, the nickname of authority in Authority NickName field if it is available otherwise leave it as blank, 
				email of authority in Authority Email field and now click on Add button.
                                                                </p>
                                                        </ol>
                                                </font>
                                                        <?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'AddAuthority.png','width' => 1300, 'height' =>700));
                                                        ?>
                                        </div>

				</section>
				 <section id="Profile">
                                 	<div class="row-fluid">
                                            	<h2>Profile</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                                	<ol>
                                               			<p align="justify" STYLE="line-height: 150%">
					User Profile display of personal data associted with specific user.  From here user see Login Information and Other Information.
								</p>
                                                        </ol>
                                                </font>
							<?php
                                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'profile.png','width' => 1300, 'height' =>700));
                                                        ?>
				      </div>
				</section>
					<section id="AccountsFunctionality">
						<div class="row-fluid">
							<h2>Accounts Functionality</h2>
							<section id="Dashboard">
								<div class="row-fluid">
									<h2>Dashboard</h2>
								</div>
								<div class="row-fluid">
									<font size="4">
										<ol>
											<p align="justify" STYLE="line-height: 150%">
												This page consists of account details and summary, Bank and Cash accounts, and recent activity. The page is constantly updated on the basis of the activities carried out in the system. 
											</p>
										</ol>
									</font>
									<?php
									echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'dashboard.png','width' => 1300, 'height' =>700));
									?>
								</div>
							</section>
						</div>
					</section>
					<section id="Budget">
						<div class="row-fluid">
							<h2>Budget</h2>
						</div>
						
						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" style="line-height: 150%">
									The page displays chart of budgets added to the account. It consists of budget code, name, type, allocated amount, available amount and a flag that specifies whether over expense is allowed in the budget or not.
									</p>
								</ol>
							</font>
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'budget.png','width' => 1300, 'height' =>700));
							?>
						</div>
					</section>
					<section id="AddBudget">
						<div class="row-fluid">
							<h2>Add Budget</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
							<ol>
							<p align="justify" STYLE="line-height: 150%">
							This page displays a form that allows a user to add a budget to the account. The form consists of a dropdown list of Expense ledgers. The user can select a particular Expense ledger to specify the amount of budget to be allocated for that ledger.
							</ol>
							</font>
							<?php
							echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'addbudget.png','width' => 1300, 'height' =>700));
							?>
					       </div>
					</section>
					<section id="ReappropriateBudget">
						<div class="row-fluid">
							<h2>Reappropriate Budget</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page displays in a hierarchy all the budget heads along with their allocated and unallocated amount added to the system. A user can edit the amount allocated to a Budget head using this functionality.
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'BudgetReappropriation.png','width' => 1300, 'height' =>700));
					?>
					</div>
			        	</section>					
					<section id="Projection">
						<div class="row-fluid">
							<h2>Projection</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					The page displays chart of income projections estimated for the organization. It consists of projection code, name, type, estimated income and unearned income. The statement look ahead and make some estimates about the organization's financial status -- that is, the profits the organization will have -- at a given point in the future.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'projection.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="AddProjection">
						<div class="row-fluid">
							<h2>Add Projection</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page displays a form that allows a user to add a projection to the account. The form consists of a dropdown list of Income ledgers. The user can select a particular Income ledger to specify the estimated income to be earned for that particular ledger.
                                                                </p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'NewProjection.png','width' => 1300, 'height' =>600));
					?>
					</div>
					</section>
					<section id="ReappropriateProjection">
						<div class="row-fluid">
							<h2>Reappropriate Projection</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page displays in a hierarchy all the projections of income heads along with their estimated amount added to the system. A user can edit the estimated income amount for an Income head using this functionality.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'ProjectionReappropriation.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="ChartofAccounts">
						<div class="row-fluid">
							<h2>Chart of Accounts</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Chart of account reflect the operations performed in the accounting year.  All the Assets, Liability, Income and Expense Account shows in this page with their opening and closing balance.
								</p>
							</ol>
						</font>
					<h2>Sources of Funds</h2>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'chartofaccount.png','width' => 1300, 'height' =>700));
					?>
					<h2>Application of Funds<h2>
					 <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'chartofaccount1.png','width' => 1300, 'height' =>700));
                                        ?>
					<h2>Income<h2>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'chartofaccount2.png','width' => 1300, 'height' =>700));
                                        ?>
					<h2>Expenditure<h2>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'chartofaccount3.png','width' => 1300, 'height' =>700));
                                        ?>

					</div>
					</section>					
					<section id="Vouchers">
						<div class="row-fluid">
							<h2>Vouchers</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Entry page display the no. of entries has been done by user. Entry has two options.
                                        			</p>
							</ol>	
						</font>
					</div>
					</section>
					<section id="ViewAllVouchers">
						<div class="row-fluid">
							<h2>View All Vouchers</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page shows the all Payment, Receipt, countra or Journal Entry made in the financial year and we can print all the entry using the date rang facilaty.
								</p>
							</ol>   
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'allentries.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="CreateJournalVoucher">
						<div class="row-fluid">
							<h2>Create Journal Voucher</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page have a button to create a new Journal entry the link  will available to guide the user to make a new entry and have the option to print all Journal entry with the selected data range.
								</p>
							</ol>
						</font>	
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'newjournal.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="BillUpload/VoucherCreation">
						<div class="row-fluid">
							<h2>Bill Upload/Voucher Creation</h2>
						</div>
						<section id="ViewBill">
							<div class="row-fluid">
								<h2>View Bill</h2>
							</div>
							<div class="row-fluid">
								<font size="4">
									<ol>
										<p align="justify" STYLE="line-height: 150%">
						We can see all list of all uploaded bills with Submit Date, Submitted Email ID, type of expenses, consider during bill upload, total amount of bill, Decision, Approved date, Approved by voucher creation date, Bank cash account, Mode of Payment, Payment Status and Payment Date.
										</p>
									</ol>
								</font>
						<?php
						echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'viewbill.png','width' => 1300, 'height' =>700));
						?>
							</div>
						</section>
					</section>

					<section id="BillUpload">
						<div class="row-fluid">
							<h2>Bill Upload</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can add a new bill by filling Submitter Email ID, Total Amount, Select Secondary Unit ID, Select Expenses and upload Bill file (jpg, png, jpeg, pdf).
								</P>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'billupload.png','width' => 1300, 'height' =>700));
					?>
					</div>
					<section id="BillUpload/VoucherCreationWithMultipleVarification">
                                                <div class="row-fluid">
                                                        <h2>Bill Upload/Voucher Creation With Multiple Varification</h2>
                                                </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
					<b>1.)</b> Write the email of the person whose bill is to upload in Submitter Email ID field, bill numberof the supplier in Supplier Bill No field, purchase order 
					no. in Purchase Order No field and theamount proposed in the Total Amount field.<br>
					<b>2.)</b> Select the name of the party from Party Name drop-down, expenses type from Expenses drop-down and the name of authority to whom bill will be forwarded 
					from Forward To drop-down(Check ALLOCATE AUTHORITY to know how to add authorities in Forward To drop-down).<br>
					<b>3.)</b> Upload bill (size of the file should be less than or equal to 1000KB and the extension of a file should be in gif or jpg or jpeg or png or pdf)with the 
					help of Browse button.<br>
					<b>4.)</b> After filling all fields and uploading bill, press Submit button to submit the bill.<br>
                                      	<br> 
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'billuploadMultipleVarification.png','width' => 1300, 'height' =>700));
                                        ?>
					<?php   
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'billuploadMultipleVarification1.png','width' => 1300, 'height' =>700));
                                        ?>
					<br>
					<br>
					<b> BILL APPROVAL/REJECTION</b><br>
                                        <b>1.)</b> Once bill is uploaded user will be redirected to the index page and a link (Approve/Reject) will be created for that particular bill on the index page as 					    well as in Notifications on Dashboard.<br>
					<b>2.)</b> Only Administer and the user to whom the bill is forwarded can access these links. Once either administer or user will take action on that bill, the 
					links will disappear.<br>
      					<b>3.)</b> After clicking on the link :<br>
                                        <br>
                                        <b>BILL APPROVAL CASE</b><br>
					<b>3.1)</b> Now if the user to whom the bill is forwarded is approving the bill then he has to select the name of authority to whom bill will be forwarded in 
					Forward To drop-down.</br>
					<b>3.2)</b> If you are selecting Fund then select Expenditure Type according to selected Fund otherwise leave these drop-downs.</br>
					<b>3.3)</b> Select Sanction Type and Sanction Head according to the bill from Sanction Type drop-down and then select decision from Approve/Reject drop-down.<br>
					<b>3.4)</b> Enter the amount you want to approve in the Approved amount field, always remember approved amount must be less than the Total Amount and Budget 
					Balance. Write the narration and comments in Narration and Comments field and click on Submit button.</br>
                                        <br>
					<b>BILL REJECTION CASE</b><br>
					<b>3.1)</b> Now if the user to whom the bill is forwarded is rejecting the bill then he/she has to write reason of bill rejection in the Comments box and select 
					Rejected in Approve/Reject drop-down and then click on Submit button.<br>
                                        <br>
					<b>BILL HOLDING CASE</b></br>
					<b>3.1)</b> Now if the user to whom the bill is forwarded is holding the bill then he has to select Hold in Approve/Reject dropdown and no need to select any field 					    and then click on submit button.<br>
                                        <br>
					<b>BILL VOUCHER CREATION CASE</b><br>
					<b>3.1)</b> Now if the user to whom the bill is forwarded is the last authority to take actions on the bill then he has to select Voucher Creation in Approve/Reject					     drop-down.</br>
					<b>3.2)</b> Enter the amount you want to approve in Approved Amount field and write comments in the Comments field and click on Submit button.</br>
					<br>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'billapproval.png','width' => 1300, 'height' =>700));
                                        ?>
					<br>
					<br>
                                     	<b>VOUCHER CREATION</b></br>
					<b>1.)</b> Once the bill is approved and forwarded for Voucher Creation then the user who forwarded the bill for voucher creation can access the Voucher Creation 
					link immediately.<br>
					<b>2.)</b> Now select the mode by which money will be sanctioned from MODE OF PAYMENT drop-down and select the name of account from which money will be sanctioned 
					in BANK AND CASH ACCOUNT drop-down and click on Submit button.<br>
					<br>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'vouchercreation.png','width' => 1300, 'height' =>700));
                                        ?>
					<br>
					<br>
                       			<b>PRINT PREVIEW</b><br>
					<b>1.)</b> Once bill is approved and the voucher is created a PrintPreview link will be generated and also bill no. turns green.<br>
					<b>2.)</b> Once bill is rejected a PrintPreview link will be generated and also bill no. turns red.<br>
					<b>3.)</b> By clicking on the bill no. or on print preview link we can see the preview of the voucher and by clicking on print link user can take print of voucher.
					<br>
					<br>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'PrintPreview.png','width' => 1300, 'height' =>700));
                                        ?>
								 </p>
                                                        </ol>
                                                </font>
                                        </div>
					</section>					
					<section id="Reports">
						<div class="row-fluid"> 
							<h2>Reports<h2>
						</div>
					</section>


					<section id="BalanceSheet">
						<div class="row-fluid">                               
							<h2>Balance Sheet</h2>
						</div>
					</section>
					<section id="CorporateFormat">
						<div class="row-fluid">
							<h2>Corporate Format</h2>
						</div>
							<div class="row-fluid">
								<font size="4">
									<ol>
										<p align="justify" STYLE="line-height: 150%">
					Its a financial statement that summarizes the organization's assets, liabilities and owner's equity at a specific point in time. These three balance sheet segments give an idea as to what the organization owns and owes. Each of the three segments of the balance sheet have many ledger accounts within it that document the value of each. Accounts such as cash, office equipment and investments are on the asset side of the balance sheet, while on the liabilities and owner's equity side there are ledger accounts such as funds, accounts payable and loans & borrowings. Also, the balancesheet contains the account values for the current accounting year as well as for the previous accounting year. If the asset total exceeds the liabilities and owner's equity total, then the exceeding amount is the net profit and if the liabilities and owner's equity total exceeds the asset total, then the exceeding amount is the net loss.
										</p>
									</ol>
								</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'balancesheet.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'balancesheet1.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'balancesheet2.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="MHRDFormat2015">
						<div class="row-fluid">
							<h2>MHRD Format 2015</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					The balancesheet has the format as per the recommendation of the MHRD. The balancesheet has two main segments, the 'sources of funds' and the 'application of funds'.The 'sources of funds' contain the grants/donations/contributions received by theinstitution and other forms of revenue, the use of which may be either unrestricted or subject to the restrictions imposed by thecontributors i.e. use of funds for specific purposes. The 'application of funds' conatins the assets of the institute. If theinformation required to be given under any of the ledger account or sub-ledger account cannot be conveniently included in theBalance Sheet itself, then it has been furnished in a separate schedule or schedules annexed to and forming part of the Balance Sheet. The corresponding amounts for the immediately preceding financial year for all ledger accounts shown in the balance sheet are also given in the Balance Sheet.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'balancesheetmhrd.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'balancesheetmhrd2.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					
					<section id="IncomeandExpenditureStatement">
                                                <div class="row-fluid">
                                                        <h2>Income and Expenditure Statement</h2>
                                                </div>
                                        </section>
                                        	<section id="CorporateFormatIE">
                                        		<div class="row-fluid">
                                        			<h2>Corporate Format IE</h2>
                                        		</div>
                                        			<div class="row-fluid">
                                       					 <font size="4">
                                        					<ol>
                                        						<p align="justify" STYLE="line-height: 150%">
					The Income and Expenditure in statement includes revenue and expenses.  It summaries the income & expenses held by a company in a financial year.  User can get the summary of this statement with selected date & print it.
											</p>
										</ol>
									</font>
								</div>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'incomeandexpenditurestatement.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'incomeandexpenditurestatement1.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'incomeandexpenditurestatement2.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>	
					<section id="MHRDFormatIE2015">
                                        	<div class="row-fluid">
                                        		<h2>MHRD Format IE 2015</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
					The Income and Expenditure has the format as per the recommendation of the MHRD. The balancesheet has two main segments, the 'Income' and the 'Expenditure'. User 
					can get the summary of this statement with selected date & print it.
								</p>
							</ol>
                                        	</font>
					<?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'mhrdformatIE.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="Payment&Receipt">
							<div class="row-fluid">
								<h2>Payment & Receipt</h2>
							</div>
					<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
					The Payment and Receipt in statement includes revenue and expenses.  It summaries the income & expenses held by a company in a financial year.  User can get the 
					summary of this statement with selected date & print it.
									</p>
								</ol>
							</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'payment&receipt.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'payment&receipt1.png','width' => 1300, 'height' =>700));
					?>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'payment&receipt2.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="TrialBalance">
						<div class="row-fluid">
							<h2>Trial Balance</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Trial balance is a list of all the General ladger accounts.  This list will contain the name of ladger account and the value
					of the ladger account, value of ledger a/c will hold either a debit balance in the debit column or a credit balance in the credit column of trial balance.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'trialbalance.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="LedgerStatement">
						<div class="row-fluid">
							<h2>Ledger Statement</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					A general ledger contain all the account for recording transaction relation to a company's assets, liability, income, expences.  User get the all transation information of ant ledger account with in selected date range.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'ledgerstatement.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="Reconciliation">
						<div class="row-fluid">
							<h2> Reconciliation</h2>
						</div>
							<div class="row-fluid">
								<font size="4">
									<ol>
										<p align="justify" STYLE="line-height: 150%">
					It refer to the process of ensuring that tow sets of records are in agreement.  It is used to ensure that the money leaving in an account match the actual money spent, this is done by making sure the balance match at rhe end of a particular accounting period.
										</p>
									</ol>
								</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'reconciliation.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="Depreciationastoday">
						<div class="row-fluid">
							<h2>Depreciation as today</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page shows the depreciated amount of purchase assets in the financial year.
								</p>
							</ol>
						</font>
					</div>
					</section>
					<section id="DayBook">
						<div class="row-fluid">
							<h2>Day Book</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Day Book statement shows current activity of user in particular date.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'daystatement.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="CashBook">
						<div class="row-fluid">
							<h2>Cash Book</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can see all cash reports activity with in selected date range.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'cashreport.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="UnspentBalance">
                                        	<div class="row-fluid">
                                        		<h2>Unspent Balance</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
					This Report shows the non-plan, plan, and summary of balance.  Which is spend during unspent the financial year.
								</p>
							</ol>
                                        	</font>
                                        <!--?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'UnspentBalance.png'));
                                        ?-->
                                        </div>
                                        </section>
					<section id="PlanReport">
                                      	       <div class="row-fluid">
                                       			 <h2>Plan Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
					The plan report is about the total plan expenditure made during in finencial year.
								</p>
							</ol>
                                        	</font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'PlanReport.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="Non-PlanReport">
                                        	<div class="row-fluid">
                                        		<h2>Non-Plan Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
					Non-Plan report is showing report of grant in aid under non-plan.  The amount which is sanctioned and Released by the MHRD.
								</p>
							</ol>
                                        	</font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'NonPlanReport.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="SummaryReport">
                                       		 <div class="row-fluid">
                                       			 <h2>Summary Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%"0>
					Summary Report showing the Opening and Closing balance of plan & non plan expenditure and inspent balance during the year.
								</p>
							</ol>
                                        	</font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'SummaryReport.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="OtherReport">
						<div class="row-fluid">
							<h2>Other Report</h2>
						</div>
					</section>
					<section id="FundList">
						<div class="row-fluid">
							<h2>Fund List</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can see all type of fund list with S.No, Code, Fund Name, O/P Balance and C/L Balance.  We can get the statement with selected date range.										    </p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'fundlist.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="TagReport">
						<div class="row-fluid">
							<h2>Tag Report</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can see all tag statement with in selected date range.  We can also search tag account by selecting tag title.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'tagreport.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="PartyReport">
						<div class="row-fluid">
							<h2>Party Report</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					We can see all list of secondary unit statement with selected date range.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'secondaryunitstatement.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="LogReport">
						<div class="row-fluid">
							<h2>Log Report</h2>
						</div>
					</section>
					<section id="ChartofAccountLog">
						<div class="row-fluid">
							<h2>Chart of Account Log</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From hear we can see chart of account logs with Date, Host IP, User, Message andBrowser. We can also search log by selecting log title.\
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'chartofaccountlogs.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="TransactionLog">
						<div class="row-fluid">
							<h2>Transaction Log</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From hear we can see transaction log with Date, Host IP, User, Message and Browser.We can also search transaction log by selecting log title.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'transactionlogs.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="BudgetLog">
						<div class="row-fluid">
							<h2>Budget Log</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From hear we can see budget log with Date, Host IP, User, Message and Browser. We can also search Budget log by selecting log title.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'budgetlogs.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="OtherLog">
						<div class="row-fluid">
							<h2>Other Log</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From hear we can see recent activity with Date, Host IP, User, Message and Browser.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'otherlogs.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="TDSReport">
                                        	<div class="row-fluid">
                                        		<h2>TDS Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				It summaries all the TDS statement held by a company in a financial year.  User can see TDS statement with party name whose TDS is being deducted with the TDS amount, rate of TDS and Payment amount.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'tdsreport.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="SundryCreditorsReport">
                                        	<div class="row-fluid">
                                        		<h2>Sundry Creditors Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				From here we can see report of Sundry Creditors for all parties. To see the sundry creditor report for a particular party, click on the name of party or select the name of party from dropdown.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrycreditmain.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				After clicking on the name of party or selecting name of party from dropdown, it will show  all entries related to sundry creditor and all information related to entries.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrycreditparty.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				After selecting party from dropdown if there is no sundry creditor report for the selected party then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrycreditpnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				If there is no party in the dropdown then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrycreditnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				If there are parties in the dropdown but there are no sundry creditors then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrycreditparnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="SundryDebitorsReport">
                                        	<div class="row-fluid">
                                        		<h2>Sundry Debitors Report</h2>
                                        	</div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				From here we can see report of Sundry debitors for all parties. To see the sundry debitor report for a particular party, click on the name of party or select the name of party from dropdown.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrydebitmain.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				After clicking on the name of party or selecting name of party from dropdown, it will show  all entries related to sundry debitor and all information related to entries.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrydebitparty.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				After selecting party from dropdown if there is no sundry debitor report for the selected party then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrydebitpnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				If there is no party in the dropdown then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrydebitnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify" STYLE="line-height: 150%">
				If there are parties in the dropdown but there are no sundry debitors then screen will display a message as shown in image below.
								</p>
							</ol>
                                        </font>
                                        <?php
                                        echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'sundrydebitparnone.png','width' => 1300, 'height' =>700));
                                        ?>
                                        </div>
                                        </section>
					<section id="Setting">
						<div class="row-fluid">
							<h2>Setting</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Before Carrying forward the account to next year, make sure that you have set the account type and ledger name for transfer or profit/loss in the account settings.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'settings.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="AccountSettings">
						<div class="row-fluid">
							<h2>Account Settings</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can update Account Name, Account Address, Account Email, Currency, Date Format, Financial Year Start, Financial Year End, Timezone, Chart of Account, Account Type, Ledger Name. We can also locked account. Account head to which the profit and loss balance will be carry forward.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'accountsettings.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="C/FAccount">
						<div class="row-fluid">
							<h2>C/F Account</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					You need to manually carry forward the profit and loss balance to the capital account for the next year.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'carryfowardaccount.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<!--section id="EmailSettings">
					<div class="row-fluid">
						<h2>Email Settings</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
						<ol>	
						<p align="justify" STYLE="line-height: 150%">
						By Filling all email setting data we can setup outgoing email. Leave empty if you dont not want to change password.
						You need to use the following gmail settings in Settings Email Settings.-->
						<!--/ol>
						</font>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="1" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Email protocol</B></td>
						<td>smtp</td>
						</tr>
						<tr>
						<td><B>Hostname</B></td>
						<td>ssl://smtp.googlemail.com</td>
						</tr>
						<tr>
						<td><B>Port</B></td>
						<td>465</td>
						</tr>
						<tr>
						<td><B>Email username</B></td>
						<td>your-username@gmail.com</td>
						</tr>
						<tr>
						<td><B>Email Password</B></td>
						<td> your-password</td>
						</tr>
						</table>
						</P>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%"><BR>
						<!-?php
						echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'emailsettings.png'));
						?>
						</BR></P>
					</div>
					</section-->
					<section id="PrinterSettings">
						<div class="row-fluid">
							<h2>Printer Settings</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can setup printing option for entries, report etc.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'printersettings.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="CreateBackup">
						<div class="row-fluid">
							<h2>Create Backup</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can see list of backup with S.No, Name Of Backup File, Download Backup, Delete Backup file. we can download backup of current account data.  Backup data delete automatically in seven days.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'downloadbackup.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="Tags">
						<div class="row-fluid">
							<h2>Tags</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
						From here we can see all tags with title and color.  We can also add new tag by filling Tag Title, Tag Color, and Background Color. 
								</p>
							</ol>
						</font>
						<?php
						echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'tags.png','width' => 1300, 'height' =>700));
						?>
					</div>
					</section>
					<section id="EntryTypes">
						<div class="row-fluid">
							<h2>Entry Types</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					This page shows how many entries can be made by user, and we can add one entry type also.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'entrytype.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="UploadLogo">
						<div class="row-fluid">
							<h2>Upload Logo</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here every institute can upload their logo.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'uploadlogo.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="PayrollSetup">
						<div class="row-fluid">
							<h2>Payroll Setup</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					By filling Main Budget Code, after it select Salary Budget Code, and select Cash/Bank Account Code we can set payroll setup.  By this link we can genrate employee salary.  We can also add payroll setup by click on "Add Payroll Setup".
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'payrollsetup.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="Party">
						<div class="row-fluid">
							<h2>Party</h2>
						</div>
					</section>
					<section id="ViewParty">
						<div class="row-fluid">
							<h2>View Party</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					We can see all list of secondary unit with Party Name, Mobile NO., Email Id, Bank A/C No., Bank Name, Branch Name, IFSC Code,PAN No., TAN No., Service Tax No.	
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'viewparty.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="AddParty">
						<div class="row-fluid">
							<h2>Add Party</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					From here we can add party by filling Party Name, Secondary Accounting UID (Faculty - 00, Student - 01, Staff Clerical - 02, Technical staff - 03, Supplier - 04, Admin staff - 05, Contractor - 06, Service provider - 07), Mobile Number, Account Email, Address, Bank Account Number, Bank Name, Branch Name, Bank IFSC Code, Bank Address, PAN Number, TAN Number, Service Tax Number.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'addparty.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>					
					<section id="Help">
						<div class="row-fluid">
							<h2>Help</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Help link provides for convenience of user that tells how to use this project.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'help.png','width' => 1300, 'height' =>700));
					?>
					</div>
					</section>
					<section id="ChangePassword">
						<div class="row-fluid">
							<h2>Change Password</h2>
						</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
					Password can be changed from the Change Password link.  Each user must enter the old password and new password after then Retype New Password for the user.
								</p>
							</ol>
						</font>
					<?php
					echo img(array('src' => base_url() . "assets/bgas/images/BGAScreenshot/" . 'passwordsetting.png','width' => 1300, 'height' =>700));
					?>

					</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

