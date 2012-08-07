<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <%@ page import="java.util.*,java.lang.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Election Management System</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #7697BC; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%--<%
List rst =(List)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");
 int i=1;
%>--%>

<script type="text/javascript" language="javascript">
    if(this.opener!=null)
    this.opener.close();
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<script language="javascript" type="text/javascript">

            function check1()
            {
                if(document.getElementById('electionid').value=="")
                {
                    alert("Enter Election Id...");
                    document.getElementById('electionid').focus();
                    return false;
                }
            }

</script>
</head>



<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/chromejs/chrome.js"/>

            <script language="javascript" type="text/javascript">
            function pageload(loc)
            {
              // alert("hey");

               document.getElementById("page").innerHTML ="";
               var loc1="";
               if(loc==1) loc1="<%=request.getContextPath()%>/election_manager/enterelectionid.jsp";
               if(loc==2) loc1="<%=request.getContextPath()%>/election_manager/createballot.jsp";
               if(loc==3) loc1="<%=request.getContextPath()%>/update_managers.do";
               if(loc==4) loc1="<%=request.getContextPath()%>/view_managers.do";
               if(loc==5) loc1="<%=request.getContextPath()%>admin_account.do";

               document.getElementById("page").innerHTML = "<iframe name=\"page\" id=\"pagetab\" height=\"800px\" width=\"100%\" src=\"/"+loc1+"\"/>";

               return true;
            }
function change(){

if(top.location=="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/login.do")
    {
        //alert(top.location);
        top.location="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/election_manager/login.jsp";
    }}





        </script>


<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>
    <style>
        body
{
  background-image: url("<%=request.getContextPath()%>/images/images.jpg");
  background-attachment: fixed;
   background-repeat: repeat-x;

}
 <%String msg=(String)request.getAttribute("msg1"); %>
        </style>
        <body style="height: 100%; z-index: 0" >
    



    
        <table align="center" style="" dir="<%=rtl%>" width="100%">
            
        <tr>
            <td  valign="top" colspan="2" width="100%" align="<%=align%>">

                <table  align="<%=align%>"  dir="<%=rtl%>" width="100%">
            <tr><td valign="bottom"  align="<%=align%>">
            <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
            </td>
            <td style="color: maroon;font-size: 12px"><%=instituteName%><br>&nbsp; Role[<%=role%>]</td>
            <td align="right" valign="top" dir="<%=rtl%>"><span style="font-family:arial;color:brown;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>">Hi [<%=user%>]&nbsp;|<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             </td></tr>
            </table><br>
            </td>

            </tr>
            <!--href="<%=request.getContextPath()%>/election_manager/enterelectionid.jsp"-->
            <%--<tr>
                <td>
                    <div>
        <ul class="dd-menu">
        <li>
            <a href="<%=contextPath%>/electionmanager.do"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
                <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%></b>
            </a>
        </li>
      <li>
          <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
              <b>Manage Election</b>
          </a>
              
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/manageElection.do" onclick="return switchMain()"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Create Election<%=resource.getString("createelectionmanager")%></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/manageElection.do" onclick="return switchMain()" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Update Election<%=resource.getString("updateelectionmanager")%></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/manageElection.do" onclick="return switchMain()" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">View Election<%=resource.getString("deleteelectionmanager")%></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/manageElection.do" onclick="return switchMain()" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block Election<%=resource.getString("blockmanager")%></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/manageElection.do" onclick="return switchMain()" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Preview Ballot<%=resource.getString("blockmanager")%></a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b>Manage Voter</b></a>
            <ul>
                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Add<%=resource.getString("Viewmanagerdetails")%></a>
                    
                    <ul>
                    <li>
                        <a href="<%=contextPath%>/addVoter.do"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Register New Voter<%=resource.getString("Viewmanagerdetails")%></a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/election_manager/pending_voter.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Register From Pending List<%=resource.getString("Viewmanagerdetails")%></a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/view_managers.do"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Add Voter From Existing List<%=resource.getString("Viewmanagerdetails")%></a>
                    </li>
                </ul>
                </li>
                
                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">View<%=resource.getString("Viewmanagerdetails")%></a>
                    <ul>
                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=A"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Active<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=B"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">View All<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/election_manager/search_voter.jsp?status=AB"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block<%=resource.getString("Viewmanagerdetails")%></a>
                </li>
            </ul>
        </li>
            <li>
                <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>">Manage Candidate Request</b><%=resource.getString("Viewmanagerdetails")%></a>
                <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/view_managers.do?status=B"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Accept<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/view_managers.do?status=A"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Reject<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>

                </ul>
           </li>
           <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b>Manage Candidate</b></a>
            <ul>
                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">View<%=resource.getString("Viewmanagerdetails")%></a>
                    <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=B"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Blocked<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=A"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Accepted<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=R"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Rejected<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">View All<%=resource.getString("Viewmanagerdetails")%></a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=U" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Update<%=resource.getString("Viewmanagerdetails")%></a>

                </li>
                
            </ul>
        </li>
</ul>
            
            </div>
                    <a  href="<%=contextPath%>/electionmanager.do" style="text-decoration:none; color: black;font-size: 15px">Home</a>
                    <a  onclick="return switchMain()" style="text-decoration:none; color: black;font-size: 15px">Election</a>
                    <a  href="<%=contextPath%>/ballot_design.do" style="text-decoration:none; color: black;font-size: 15px">Ballot</a>
                    <a  href="<%=contextPath%>/votersetup.do" style="text-decoration:none; color: black;font-size: 15px">Voter Setup</a>
                    <a  href="<%=contextPath%>/candidatesetup.do" style="text-decoration:none; color: black;font-size: 15px">Candidate Request</a>
                    <a  href="<%=contextPath%>/candidatesetup1.do" style="text-decoration:none; color: black;font-size: 15px">Candidate Setup</a>
                    <a  href="<%=contextPath%>/addVoter.do" style="text-decoration:none; color: black;font-size: 15px">Add Voter</a>
                </td></tr>--%>
        </table>
            
        <div id="main" style="margin-left: 300px;visibility: hidden; overflow: auto;border: solid aqua 5px;background-color: cyan;width:500px;height: 90px;position: absolute;z-index: 100" >
           <html:form method="post" onsubmit="return check1()" action="/electionview">
              Enter Election ID<br>
                                        <html:text property="electionId" styleId="electionid" name="DepActionForm"/><br>
                                        <br><input type="submit" class="btn" id="Button1" name="button" value="Add" />
                                <input type="submit" id="Button2" class="btn" name="button" value="Update"  />
                                  <input type="submit" id="Button4" name="button" value="View" class="btn" />
                                <input type="submit" id="Button2" class="btn" name="button" value="Delete"  />
                                
                              
                                
              
</html:form>


        </div>


           

</body>
      
</html>

<script>
    function getStyle()
   {
      var temp = document.getElementById("main").style.visibility;

      return temp;
   }

 function switchMain()
  {

      var current = getStyle();
    

      if( current == "hidden" )
       {
         document.getElementById("main").style.visibility = "visible";
         document.getElementById("main").style.top = "250px";
       }
       else
       {

         document.getElementById("main").style.visibility = "hidden";
       }
  }
</script>