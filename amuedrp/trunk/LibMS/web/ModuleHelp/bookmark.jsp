<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
   
        <script language="javascript">
<!--

var state = 'none';

function showhide(layer_ref) {

if (state == 'block') {
state = 'none';
}
else {
state = 'block';
}
if (document.all) { //IS IE 4 or 5 (or 6 beta)
eval( "document.all." + layer_ref + ".style.display = state");
}
if (document.layers) { //IS NETSCAPE 4 or below
document.layers[layer_ref].display = state;
}
if (document.getElementById &&!document.all) {
hza = document.getElementById(layer_ref);
hza.style.display = state;
}
}
//-->
</script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

 <body  style="text-decoration: none;">
    
     <h5>Table of Content</h5>
 <ol type="1" class="opac_table_textbox">
<li><a href="AdminHelp.html#admin" target="c">
        <a href="#" onClick="showhide('div1');" >Administrator/Admin Module</a>

        
        </a>
    <div id="div1" style="display: none;">
<i>
<ol type="a">

<li><a href="AdminHelp.html#registerstaff" target="c">Manage Staff</a></li>
<ol type="i">
<li><a href="AdminHelp.html#registerstaff" target="c">Register Staff</a></li>
<li><a href="AdminHelp.html#updateStaff" target="c">Change Staff details</a></li>
<li><a href="AdminHelp.html#deleteStaff" target="c">Delete Staff</a></li>
<li><a href="AdminHelp.html#viewStaffRecord" target="c">View Staff Record</a></li>
<li><a href="AdminHelp.html#viewAllStaffRecord" target="c">View All Staff Record</a></li>

</ol>
<li><a href="AdminHelp.html#createstaff" target="c">Manage Staff Account</a></li>

<ol type="i">
<li><a href="AdminHelp.html#createstaff" target="c">Create Staff Acccount</a></li>
<li><a href="AdminHelp.html#viewStaffAccount" target="c">View Staff Account</a></li>
<li><a href="AdminHelp.html#updateAccount" target="c">update Account</a></li>
<li><a href="AdminHelp.html#deleteStaffAccount" target="c">Delete Staff Account</a></li>
<li><a href="AdminHelp.html#viewAllStaffAccount" target="c">View All Staff Account</a></li>

</ol>


<li><a href="AdminHelp.html#manageprivilege" target="c" >Manage Staff Privilege</a></li>
<ol type="i">
<li><a href="AdminHelp.html#assignprivilege" target="c">Assign Privileges</a></li>
<li><a href="AdminHelp.html#changeprivilege" target="c">Change Privileges</a></li>
<li><a href="AdminHelp.html#viewprivilege" target="c">View Privileges</a></li>
</ol>

</ol>
</i>

</div>

     
<li>
     <a href="#" onclick="showhide('div2');">OPAC Module</a>



    <div id="div2" style="display: none;">



<i>
<ol type ="1">

<li><a href="mainHelpOpac.html#simpleSearch" target="c">Simple Search</a></li>
<li><a href="mainHelpOpac.html#browseSearch" target="c">Browse Search</a></li>
<li><a href="mainHelpOpac.html#additionalSearch" target="c">Additional Search</a></li>
<li><a href="mainHelpOpac.html#advanceSearch" target="c">Advance Search</a></li>
<li><a href="mainHelpOpac.html#isbnSearch" target="c">ISBN/ISSN Search</a></li>
<li><a href="mainHelpOpac.html#callNoSearch" target="c">Call No Search</a></li>
<li><a href="mainHelpOpac.html#accessionNoSearch" target="c">Accession No.Search</a></li>

<li><a href="mainHelpOpac.html#newArrivals" target="c">New Arrivals</a></li>
<li><a href="mainHelpOpac.html#memberRegistration" target="c">Member Registration</a></li>
<li><a href="mainHelpOpac.html#myAccount" target="c">My Account</a></li>
<li><a href="mainHelpOpac.html#notices" target="c">Notices</a></li>
<li><a href="mainHelpOpac.html#locations" target="c">Locations</a></li>
<li><a href="mainHelpOpac.html#feedback" target="c">Feedback</a></li>

</ol>

     </i>

    </div>
     
<li>

     <a href="#" onclick="showhide('div3');">System Setup Module</a>



    <div id="div3" style="display: none;">


<i>
<ol type="a">

<li><a href="helpSystemSetup.html#manageNotices" target="c">Manage Notices</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addNotice" target="c">Add Notice</a></li>
<li><a href="helpSystemSetup.html#updateNotice" target="c">Update Notice</a></li>
<li><a href="helpSystemSetup.html#deleteNotice" target="c">Delete Notice</a></li>
<li><a href="helpSystemSetup.html#viewNotice" target="c">View Notice</a></li>
<li><a href="helpSystemSetup.html#viewAllNotice" target="c">View All Notice</a></li>

</ol>
<li><a href="helpSystemSetup.html#manageLocation" target="c">Manage Location</a></li>

<ol type="i">
<li><a href="helpSystemSetup.html#addLocation" target="c">Add Location</a></li>
<li><a href="helpSystemSetup.html#updateLocation" target="c">Update Location</a></li>
<li><a href="helpSystemSetup.html#deleteLocation" target="c">Delete Location</a></li>
<li><a href="helpSystemSetup.html#viewLocation" target="c">View Location</a></li>
<li><a href="helpSystemSetup.html#viewAllLocation" target="c">View All Location</a></li>

</ol>


<li><a href="helpSystemSetup.html#manageMemberTypes" target="c" >Manage Member Types</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addMember" target="c">Add Member</a></li>
<li><a href="helpSystemSetup.html#updateMember" target="c">Update Member</a></li>
<li><a href="helpSystemSetup.html#deleteMember" target="c">Delete Member</a></li>
<li><a href="helpSystemSetup.html#viewMember" target="c">View Member</a></li>
<li><a href="helpSystemSetup.html#viewAllMember" target="c">View All Member</a></li>
</ol>
<li><a href="helpSystemSetup.html#manageSubMemberTypes" target="c" >Manage SubMember Types</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addSubMember" target="c">Add SubMember</a></li>
<li><a href="helpSystemSetup.html#updateSubMember" target="c">Update SubMember</a></li>
<li><a href="helpSystemSetup.html#deleteSubMember" target="c">Delete SubMember</a><set/li>
<li><a href="helpSystemSetup.html#viewSubMember" target="c">View SubMember</a></li>
<li><a href="helpSystemSetup.html#viewAllSubMember" target="c">View All SubMember</a></li>
</ol>
<li><a href="helpSystemSetup.html#manageDocumentCategory" target="c" >Manage Document Category</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addDocumentCategory" target="c">Add Document category</a></li>
<li><a href="helpSystemSetup.html#updateDocumentCategory" target="c">Update Document Category</a></li>
<li><a href="helpSystemSetup.html#deleteDocumentCategory" target="c">delete Document Category</a></li>
<li><a href="helpSystemSetup.html#viewDocumentCategory" target="c">View Document Category</a></li>
<li><a href="helpSystemSetup.html#viewAllDocumentCategory" target="c">View All Document Category</a></li>
</ol>
<li><a href="helpSystemSetup.html#configureFineParameters" target="c" >Configure Fine Parameters</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#configureFineParameters" target="c">Set FineDetail</a></li>
<li><a href="helpSystemSetup.html#updateFinedetail" target="c">Update FineDetail</a></li>
<li><a href="helpSystemSetup.html#deleteFinedetail" target="c">Delete FineDetail</a></li>
<li><a href="helpSystemSetup.html#viewFinedetail" target="c">View FineDetail</a></li>
<li><a href="helpSystemSetup.html#viewAllFinedetail" target="c">View AllFine Detail</a></li>

</ol>
<li><a href="helpSystemSetup.html#manageFaculty" target="c" >Manage Faculty</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addFaculty" target="c">Add Faculty</a></li>
<li><a href="helpSystemSetup.html#updateFaculty" target="c">Update Faculty</a></li>
<li><a href="helpSystemSetup.html#deleteFaculty" target="c">Delete Faculty</a></li>
<li><a href="helpSystemSetup.html#viewFaculty" target="c">View Faculty</a></li>
<li><a href="helpSystemSetup.html#viewAllFaculty" target="c">View All Faculty</a></li>
</ol>
<li><a href="helpSystemSetup.html#manageDepartment" target="c" >Manage Department</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addDepartment" target="c">Add department</a></li>
<li><a href="helpSystemSetup.html#updateDepartment" target="c">Update Department</a></li>
<li><a href="helpSystemSetup.html#deleteDepartment" target="c">Delete Department</a></li>
<li><a href="helpSystemSetup.html#viewDepartment" target="c">View Department</a></li>
<li><a href="helpSystemSetup.html#viewAllDepartment" target="c">View All Department</a></li>
</ol>
<li><a href="helpSystemSetup.html#manageCourse" target="c" >Manage Courses</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addCourse" target="c">Add Course</a></li>
<li><a href="helpSystemSetup.html#updateCourse" target="c">Update Course</a></li>
<li><a href="helpSystemSetup.html#deleteCourse" target="c">Delete Course</a></li>
<li><a href="helpSystemSetup.html#viewCourse" target="c">View Course</a></li>
<li><a href="helpSystemSetup.html#viewAllCourse" target="c">View All Course</a></li>
</ol>
<li><a href="helpSystemSetup.html#manageSubLibrary" target="c" >Manage Sub Library</a></li>
<ol type="i">
<li><a href="helpSystemSetup.html#addSubLibrary" target="c">Add SubLibrary</a></li>
<li><a href="helpSystemSetup.html#updateSubLibrary" target="c">Update SublLibrary</a></li>
<li><a href="helpSystemSetup.html#deleteSubLibrary" target="c">Delete SubLibrary</a></li>
<li><a href="helpSystemSetup.html#viewSubLibrary" target="c">View SubLibrary</a></li>
<li><a href="helpSystemSetup.html#viewAllSubLibrary" target="c">View All SubLibrary</a></li>
</ol>
</ol>
</i>
    </div>
<li>
     <a href="#" onclick="showhide('div4');">Cateloguing Module</a>



    <div id="div4" style="display: none;">


<i>
<ol type="a">
<li><a href="helpCat.html#initiateCateloguing" target="c">Initiate Cateloguing</a></li>
   	<ol type="i">
	<li><a href="helpCat.html#titlesReadyForCateloguing" target="c">Titles Ready for Cateloguing</a></li>
	<li><a href="helpCat.html#oldTitleEntry" target="c">Old Title Entry</a></li>
		<ol type="i">
		<li><a href="helpCat.html#manageBibliography" target="c">Manage Bibliography</a></li>
		<li><a href="helpCat.html#viewAll" target="c">View All</a></li>
		</ol>
	<li><a href="helpCat.html#titleHoldingManagement" target="c">Title Holding Management</a></li>
		<ol type="i">
		<li><a href="helpCat.html#mergeTitles" target="c">Merge Titles</a></li>
		<li><a href="helpCat.html#updateHolding" target="c">Update Holding</a></li>
		<li><a href="helpCat.html#removeTitle" target="c">Remove Title</a></li>
		</ol>
	<li><a href="helpCat.html#subjectUpdate" target="c">Subject Updates</a></li>
	<li><a href="helpCat.html#accessionManagement" target="c">Accession Management</a></li>
		<ol type="i">
		<li><a href="helpCat.html#changeAccession" target="c">Change Accession No</a></li>
		<li><a href="helpCat.html#assignAccession" target="c">Assign Accession No</a></li>
		</ol>
	<li><a href="helpCat.html#" target="c">Cateloguing of New Titles</a></li>
		<ol type="i">
		<li><a href="helpCat.html#entryUsingSimpleTemplate" target="c">Entry Using Simple Template</a></li>
		<li><a href="helpCat.html#entryUsingMarc21Template" target="c">Entry Using MARC21 Template</a></li>
		<li><a href="helpCat.html#entryUsingCustomizedTemplate" target="c">Entry Using Customized Template</a></li>
		</ol>
	<li><a href="helpCat.html#" target="c">Multimedia Management</a></li>

	</ol>
<li><a href="helpCat.html#latestCognizance" target="c">Latest Cognizance</a></li>
	<ol type="i">
	<li><a href="helpCat.html#" target="c">Generate Latest Addition List</a></li>
	<li><a href="helpCat.html#" target="c">Update Latest Addtion List</a></li>
	<li><a href="helpCat.html#" target="c">View Latest Addtion List</a></li>
	<li><a href="helpCat.html#" target="c">Bibliography</a></li>
	</ol>
<li><a href="helpCat.html#printCatelogueCards" target="c" >Print Catelogue Cards</a></li>
<li><a href="helpCat.html#catelogueSystemSetup" target="c" >Catelogue System Setup</a></li>
<li><a href="helpCat.html#dataImportExport" target="c" >Data Impot/Export</a></li>
	<ol type="i">
	<li><a href="helpCat.html#import" target="c">Data Import</a></li>
	<li><a href="helpCat.html#export" target="c">Data Export</a></li>
	</ol>
<li><a href="helpCat.html#catelogueReports" target="c" >Catelogue Reports</a></li>
	<ol type="i">
	<li><a href="helpCat.html#"target="c">Document Slip</a></li>
	<li><a href="helpCat.html#" target="c">List Titles</a></li>
	<li><a href="helpCat.html#" target="c">Subjects/Keywords</a></li>
	<li><a href="helpCat.html#" target="c">Arrival Notice</a></li>
	<li><a href="helpCat.html#" target="c">Classified Subjects</a></li>
	<li><a href="helpCat.html#" target="c">Highly Priced Titles</a></li>
	<li><a href="helpCat.html#" target="c">Withdrawn Register</a></li>
	</ol>
<li><a href="helpCat.html#annualStock" target="c" >Annual Stock</a></li>
<li><a href="helpCat.html#databaseBackup" target="c" >Database backup</a></li>
<li><a href="helpCat.html#onlineSearch" target="c" >Online Search</a></li>
</ol>


</i>
    </div>
    </li>

 <li> <a href="#" onclick="showhide('div5');">Circulation Module</a>
     <div id="div5" style="display: none;">
<i>
<ol type="a">
<li><a href="helpCir.html#" target="c">Member Management</a></li>
   	<ol type="1">
	<li><a href="helpCir.html#register" target="c">Member Details</a></li>
		<ol type="i">
			<li><a href="helpCir.html#register" target="c">Register</a></li>
			<li><a href="helpCir.html#update" target="c">Update</a></li>
			<li><a href="helpCir.html#delete" target="c">Delete</a></li>
			<li><a href="helpCir.html#view" target="c">View</a></li>
			<li><a href="helpCir.html#viewAll" target="c">View All</a></li>
		</ol>
	<li><a href="helpCir.html#create" target="c">Member Account</a></li>
		<ol type="i">
			<li><a href="helpCir.html#create" target="c">Create</a></li>
			<li><a href="helpCir.html#aUpdate" target="c">Update</a></li>
			<li><a href="helpCir.html#aDelete" target="c">Delete</a></li>
			<li><a href="helpCir.html#aView" target="c">View</a></li>
			<li><a href="helpCir.html#aviewAll" target="c">View All</a></li>
		</ol>

	<li><a href="helpCir.html#" target="c">Registration Request From OPAC</a></li>
	<li><a href="helpCir.html#" target="c">Temporary Registration</a></li>
	<li><a href="helpCir.html#" target="c">Modify Record</a></li>
	<li><a href="helpCir.html#" target="c">Cancellation</a></li>
	<li><a href="helpCir.html#" target="c">Renewal</a></li>
	<li><a href="helpCir.html#delinquentMember" target="c">Delinquent Member</a></li>
	<li><a href="helpCir.html#" target="c">Memeber Statistics</a></li>
	<li><a href="helpCir.html#" target="c">Institute Member</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Member Set</a></li>
			<li><a href="helpCir.html#" target="c">Daily</a></li>
			<li><a href="helpCir.html#" target="c">By Group</a></li>
			<li><a href="helpCir.html#" target="c">General</a></li>
		</ol>
	<li><a href="helpCir.html#" target="c">Scheme Allocation</a></li>
	</ol>

<li><a href="helpCir.html#" target="c">Member Directory</a></li>
<li><a href="helpCir.html#" target="c" >Membership Card Management</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Generate Card</a></li>
			<li><a href="helpCir.html#" target="c">Lost/Found Card</a></li>
			<li><a href="helpCir.html#" target="c">Duplicate Card</a></li>
			<li><a href="helpCir.html#" target="c">View</a></li>
			<li><a href="helpCir.html#" target="c">Remove Card</a></li>
		</ol>
<li><a href="helpCir.html#issue" target="c" >Check Out(Issue)</a></li>
<li><a href="helpCir.html#return" target="c" >Check In(Return)</a></li>
<li><a href="helpCir.html#" target="c" >File Management</a></li>
			<ol type="i">
			<li><a href="helpCir.html#" target="c">Fine Collection</a></li>
			<li><a href="helpCir.html#" target="c">Update Fine Detail</a></li>
			<li><a href="helpCir.html#" target="c">Fine Notes</a></li>
			</ol>
<li><a href="helpCir.html#" target="c" >Collection Management</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Titles on ShowCase/Display</a></li>
			<li><a href="helpCir.html#" target="c">Missing Document</a></li>
			<li><a href="helpCir.html#" target="c">Damaged Document</a></li>
			<li><a href="helpCir.html#" target="c">Withdrawn Document</a></li>
			<li><a href="helpCir.html#" target="c">Written Off Document</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >Binding Management</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Prepare List for Binding</a></li>
			<li><a href="helpCir.html#" target="c">Sent to Binder</a></li>
			<li><a href="helpCir.html#" target="c">Update Binding Status</a></li>
			<li><a href="helpCir.html#" target="c">Recieved Binded Titles</a></li>
			<li><a href="helpCir.html#" target="c">Written Off Document</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >Document Reservation</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Reserve Document</a></li>
			<li><a href="helpCir.html#" target="c">Cancel Reservation</a></li>
			<li><a href="helpCir.html#" target="c">Change Resevation Sequence</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >Circulation Enquiries</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Member</a></li>
			<li><a href="helpCir.html#" target="c"> Collection </a></li>
			<li><a href="helpCir.html#" target="c">Transaction Log</a></li>
			<li><a href="helpCir.html#" target="c">Seria Based</li>
			<li><a href="helpCir.html#" target="c">Statistics</a></li>
		</ol>
<li><a href="helpCir.html#checkOut" target="c" >Circulation Report</a></li>
		<ol type="i">
			<li><a href="helpCir.html#checkOut" target="c">CheckOut Report</a></li>
			<li><a href="helpCir.html#checkIn" target="c"> CheckIn Report</a></li>
			<li><a href="helpCir.html#" target="c">Reminder</a></li>
			<li><a href="helpCir.html#" target="c">Management Reporting</li>
			<li><a href="helpCir.html#" target="c">Written Off Document</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >System Setup</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Member Related Parameters</a></li>
			<li><a href="helpCir.html#" target="c">Delinquency Reasons</a></li>
			<li><a href="helpCir.html#" target="c">Cancellation Reasons</a></li>
			<li><a href="helpCir.html#" target="c">Fine Categories</li>
			<li><a href="helpCir.html#" target="c">Text of Notices for Members</a></li>
			<li><a href="helpCir.html#" target="c">Parameters for Slips</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >House Keeping</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Remove Transaction Log</a></li>
			<li><a href="helpCir.html#" target="c">Remove Expired Records</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >Inter Library Loan</a></li>
		<ol type="i">
			<li><a href="helpCir.html#" target="c">Record Keeping </a></li>
			<li><a href="helpCir.html#" target="c"> Circulation</a></li>
			<li><a href="helpCir.html#" target="c"> Enquiries</a></li>
			<li><a href="helpCir.html#" target="c">  Reports</a></li>
		</ol>
<li><a href="helpCir.html#" target="c" >Serial Circulation</a></li>
</ol>
</i>

     </div>
 </li>

 <br>Help<br/>
 <a href="<%=request.getContextPath()%>/ModuleHelp/UserManual.odt">ODT Help</a><br/>
<a href="<%=request.getContextPath()%>/ModuleHelp/UserManual.pdf">PDF Help</a>


</ol>









 </body>
</html>
