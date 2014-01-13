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

 <body bgcolor="cyan" style="text-decoration: none;">
    
            
 <ol type="1" class="opac_table_textbox">
<li><a href="EMSUserManual.html#superadmin" target="c">
        <a href="#" onClick="showhide('div1');" >SuperAdmin Module</a>


        </a>
    <div id="div1" style="display: none;">
<i>
<ol type="a">

<li><a href="EMSUserManual.html#Home" target="c">Home</a></li>
<%--<ol type="i">
<li><a href="AdminHelp.html#registerstaff" target="c">Register Staff</a></li>
<li><a href="AdminHelp.html#updateStaff" target="c">Change Staff details</a></li>
<li><a href="AdminHelp.html#deleteStaff" target="c">Delete Staff</a></li>
<li><a href="AdminHelp.html#viewStaffRecord" target="c">View Staff Record</a></li>
<li><a href="AdminHelp.html#viewAllStaffRecord" target="c">View All Staff Record</a></li>

</ol>--%>
<li><a href="EMSUserManual.html#InstituteList" target="c">Institute List</a></li>

<ol type="i">
<li><a href="EMSUserManual.html#ViewPendingList" target="c">View Pending List</a></li>
<li><a href="EMSUserManual.html#ViewApprovedList" target="c">View Approved List</a></li>
<li><a href="EMSUserManual.html#DelinquentInstituteList" target="c">Delinquent Institute List</a></li>
<li><a href="EMSUserManual.html#ViewAll" target="c">View All</a></li>
</ol>


<li><a href="EMSUserManual.html#ModifyInstituteRecord" target="c" >Modify Institute Record</a></li>
<%--<ol type="i">
<li><a href="AdminHelp.html#assignprivilege" target="c">Assign Privileges</a></li>
<li><a href="AdminHelp.html#changeprivilege" target="c">Change Privileges</a></li>
<li><a href="AdminHelp.html#viewprivilege" target="c">View Privileges</a></li>
</ol>--%>
<li><a href="EMSUserManual.html#SearchInstitute" target="c" >Search Institute</a></li>
<li><a href="EMSUserManual.html#ManageSuperadminAccount" target="c" >Manage Superadmin Account</a></li>
<li><a href="EMSUserManual.html#ChangeWorkingStatus" target="c" >Change Working Status</a></li>

</ol>
</i>

</div>

     
<li>
     <a href="#" onclick="showhide('div2');">InstituteAdmin Module</a>



    <div id="div2" style="display: none;">



<i>
<ol type ="a">

<li><a href="EMSUserManual.html#InstiHome" target="c">Home</a></li>

<li><a href="EMSUserManual.html#ManageElectionManager" target="c">Manage Election Manager</a></li>
<ol type="i">
    <li><a href="EMSUserManual.html#CreateElectionManager" target="c">Create Election Manager</a></li>
<li><a href="EMSUserManual.html#UpdateElectionManager" target="c">Update Election Manager</a></li>
<%--<li><a href="EMS%20User%20Manual.html#BlockElectionManager" target="c">Block Election Manager</a></li>
</ol>


<li><a href="EMS%20User%20Manual.html#ViewElectionManager" target="c">View Election Manager</a></li>
<ol type="i">
    <li><a href="EMS%20User%20Manual.html#ViewBlocked" target="c">View Blocked</a></li>
<li><a href="EMS%20User%20Manual.html#UpdateElectionManager" target="c">Update Election Manager</a></li>
<li><a href="EMS%20User%20Manual.html#ViewAllM" target="c">View All</a></li>
</ol>

<li><a href="EMS%20User%20Manual.html#ViewElectionDetail" target="c">View Election Detail</a></li>
--%>


</ol>

     </i>

    </div>
     
<li>

     <a href="#" onclick="showhide('div3');">Election Manager Module</a>



    <div id="div3" style="display: none;">


<i>
<ol type="a">

<li><a href="EMSUserManual.html#ManagerHome" target="c">Home</a></li>
<li><a href="EMSUserManual.html#ManageElection" target="c">Manage Election</a></li>

<ol type="i">
<li><a href="EMSUserManual.html#CreateElection" target="c">Create Election</a></li>
<li><a href="EMSUserManual.html#UpdateElection" target="c">Update Election</a></li>
<li><a href="EMSUserManual.html#PreviewBallot" target="c">Preview Ballot</a></li>
</ol>

<li><a href="EMSUserManual.html#ManageVoter" target="c">Manage Voter</a></li>

<ol type="i">
    
<li><a href="EMSUserManual.html#Add" target="c">Add</a></li>
<li><a href="EMSUserManual.html#View" target="c">View</a></li>
<li><a href="EMSUserManual.html#Block" target="c">Block</a></li>


</ol>


<li><a href="EMSUserManual.html#ManagecandidateRequest" target="c" >Manage candidate Request</a></li>
<%--<li><a href="EMS%20User%20Manual.html#Managecandidate" target="c" >Manage candidate</a></li>--%>

</ol>
</i>
    </div>
<li>
     <a href="#" onclick="showhide('div4');">Voter Module</a>



    <div id="div4" style="display: none;">


<i>
<ol type="a">
<li><a href="EMSUserManual.html#VoterRegistration" target="c">Voter Registration</a></li>
<li><a href="EMSUserManual.html#VoterHome" target="c">Voter Home</a></li>
<li><a href="EMSUserManual.html#CurrentElection" target="c">Current Election</a></li>
<li><a href="EMSUserManual.html#ElectionResults" target="c">Election Result</a></li>
<li><a href="EMSUserManual.html#VotingProcess" target="c">Voting Process</a></li>
   	
</ol>


</i>
    </div>
    </li>

 <li> <a href="#" onclick="showhide('div5');">Candidate Module</a>
     <div id="div5" style="display: none;">
<i>
<ol type="a">
<li><a href="EMSUserManual.html#CandiHome" target="c">Home</a></li>
<li><a href="EMSUserManual.html#NominationList" target="c">Nomination List</a></li>
<li><a href="EMSUserManual.html#SendWithdrawlRequest" target="c">Send Withdrawl Request</a></li>
<li><a href="EMSUserManual.html#FinalList" target="c">Final List</a></li>
<li><a href="EMSUserManual.html#UploadManifesto" target="c">Upload Manifesto</a></li>
   	
</ol>
</i>

     </div>
 </li>

 <br>Help<br/>
 <a href="<%=request.getContextPath()%>/ModuleHelp/EMSUserManual.odt" target="c">ODT Help</a><br/>
 
 <a href="<%=request.getContextPath()%>/ModuleHelp/CompleteUserManual.pdf" target="c">PDF Help</a>


</ol>









 </body>
</html>
