<%-- 
    Document   : electionResult
    Created on : Jul 19, 2011, 3:12:06 PM
    Author     : Edrp-04
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.myapp.struts.Voting.Result,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Candidate1,java.sql.ResultSet"%>

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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election Results</title>
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
    </head>
    <body onload="designBallot()">

        <%!


   Result Ob;
   VoterRegistration voter;
   CandidateRegistration candiadte;
   
  // Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   //ElectionManager electionmanager;
   //StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>



 <%

 //List rs = (List)session.getAttribute("resultset");
 List <Result>rs = (List<Result>)session.getAttribute("resultset");
Result r = rs.get(0);
System.out.println("it="+r);
   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=5;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
if(rs!=null){
  Iterator it = rs.iterator();
System.out.println("it="+(tcount));
//requestList = (Login)rs.get(0);

//ArrayList[] rs1 = (ArrayList[])it.next();
while (it.hasNext()) {
            Result ls = (Result)it.next();
	System.out.println("it="+(tcount));
        
       // staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();

        Ob = new Result();
        //ems=new Election_Manager_StaffDetail();

       // Ob.setmanager_id(electionmanager.getId().getManagerId());
        //Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        //Ob.setfirst_name(staffdetail.getFirstName());
        //Ob.setlast_name(staffdetail.getLastName());
        //Ob.setStaff_id(electionmanager.getStaffId());
        //Ob.setUser_id(electionmanager.getUserId());
        //Ob.setStatus(electionmanager.getStatus());

       /* Ob.setEnrollment(voter1.get);
       // Ob.setVoter_name(voter1.getVoterRegistration().getVoterName());
       // Ob.setDepartment(voter1.getVoterRegistration().getDepartment());
       // Ob.setCourse(voter1.getVoterRegistration().getCourse());
        Ob.setYear(voter1.getVoterRegistration().getYear());
        Ob.setStatus(voter1.getCandidateRegistration().getStatus());*/
        //ems.getElectionManager().setStatus(ems.getElectionManager().getStatus());

        Ob.setElection_name((String)ls.getElection_name());
        Ob.setPosition_name((String)ls.getPosition_name());
        Ob.setNumber_of_choice((String)ls.getNumber_of_choice());
        Ob.setCandidate_name((String)ls.getCandidate_name());
        Ob.setVotes((String)ls.getVotes());





   requestList.add(Ob);
   tcount++;

   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+5) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
String electionName = (String)((Result)requestList.get(0)).getElection_name();
  %>
  <script type="text/javascript" language="javascript">
//alert("yes its working");
function designBallot(){
var htm="";
var ival = 0;
document.getElementById("ballot").innerHTML="";
 
//var em = cartXML.firstChild.value;
var divtag1 = document.createElement("div");
                divtag1.id = "ElectionName";
                divtag1.style.backgroundColor = "#D8CEF6";
                divtag1.style.border = "solid 5px #F2F5A9";
                divtag1.style.borderTopLeftRadius = "15px";
                divtag1.style.borderTopRightRadius = "15px";
                divtag1.style.borderBottomLeftRadius = "15px";
                divtag1.style.borderBottomRightRadius = "15px";
                divtag1.style.marginLeft = "30px";
                divtag1.style.width = "590px";
                divtag1.style.align = "center";
                divtag1.style.marginTop = "5px";
                divtag1.style.height = "20px";
                //alert("yes");
                var htm1 = "<span style='margin-left: 45%'><%=electionName%></span>";
               
               divtag1.innerHTML = htm1;
            document.getElementById("ballot").appendChild(divtag1);
   <%
     String position_id=((Result)requestList.get(0)).getPosition_id();
     String position_name=((Result)requestList.get(0)).getPosition_name();
  String no_of_choice=((Result)requestList.get(0)).getNumber_of_choice();%>
var divtag = document.createElement("div");
                divtag.id = "position<%=position_id%>";
                divtag.style.backgroundColor = "#D8CEF6";
                divtag.style.border = "solid 5px #F2F5A9";
                divtag.style.borderTopLeftRadius = "15px";
                divtag.style.borderTopRightRadius = "15px";
                divtag.style.borderBottomLeftRadius = "15px";
                divtag.style.borderBottomRightRadius = "15px";
                divtag.style.marginLeft = "30px";
                divtag.style.width = "590px";
                divtag.style.align = "center";
                divtag.style.marginTop = "5px";
                divtag.style.height = document.height;
                //divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name0'+iii +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice0'+ iii +'" size="40px"/><br><br><div id="candidate0'+iii+'" style="position: relative;background-color: #D8CEF6; border: 3px solid #F2F5A9;display: block;width: 595px"></div><input type="button" name="add candidate" style="" id="add0'+iii+'" onclick="create(0,'+iii+',this);" value="Add Candidate" size="50px"><br>';
                htm = '<div class="building_block" >Position: <strong>'+<%=position_name%>+'</strong><br><strong>No of Choice= '+<%=no_of_choice%>+'</strong></div>';
                divtag.innerHTML =htm;
document.getElementById("ballot").appendChild(divtag);
<%
   /*  for(int i=1;i<requestList.size();i++)
       {
         Result result = (Result)requestList.get(i);
         if(result.getPosition_id().equals(position_id))
             {



         }
         else
             {
             position_id = result.getPosition_id();
             }
       }*/
   %>/*


if(noofchoice>1) htm=htm + ' candidates';
else htm=htm + ' candidate';
htm = htm +' for this position</strong><table class="ballot"><tbody><tr><th style="text-align: left;">Candidate Name</th><th>Selection</th></tr>';

var ca = em1[iii].getElementsByTagName("candidate");
choice[iii]=noofchoice;
for(jj=0;jj<ca.length;jj++)
    {
        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
       htm = htm +'<tr><td style="text-align: left;"><label for="entry'+iii+'">'+candidatename+'</label></td>';

       var cl = "checkCandidateLimit("+iii+","+jj+",this,"+ noofchoice +",count1["+iii+"],'"+candidatename+"','"+positionname+"')";
if(noofchoice>1)
       {
               htm = htm +'<td><input type="checkbox" value="'+jj+'"   onclick="'+cl+'" name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
       }
       else
           {
               htm = htm +'<td><input type="radio" value="'+jj+'" onclick="'+cl+'"  name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
           }
ival = iii;
    }
  htm = htm + '</tbody></table></div>';
//alert("create("+jj+","+iii+",this);");
//alert(document.getElementById(idadd).attributes.onclick.value);
divtag.innerHTML =htm;
document.getElementById("ballot").appendChild(divtag);


document.getElementById("ballot").style.display="block";

}
var divtag2 = document.createElement("div");
                divtag2.id = "submit";
                divtag2.style.backgroundColor = "#D8CEF6";
                divtag2.style.border = "solid 5px #F2F5A9";
                divtag2.style.borderTopLeftRadius = "15px";
                divtag2.style.borderTopRightRadius = "15px";
                divtag2.style.borderBottomLeftRadius = "15px";
                divtag2.style.borderBottomRightRadius = "15px";
                divtag2.style.marginLeft = "30px";
                divtag2.style.width = "590px";
                divtag2.style.align = "center";
                divtag2.style.marginTop = "5px";
                divtag2.style.height = document.height;
                var sql = '<input type="button" value="Vote Now" onclick="Validate('+ ival +','+ choice +',posXml)" style="margin-left: 45%;"/>';
divtag2.innerHTML = sql;
document.getElementById("ballot").appendChild(divtag2);
*/
//}
    }
            </script>
 
  
               <div id="main" style="border: 2px solid black;height: 350px">
      <div id="ballot" style="display: block; border: 2px solid black;height: 150px">
         Yes Its here
      </div>
  </div>

<br><br>

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<table align="" dir="" width="80%">
    <tr dir=""><td dir="">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="10%">
      <header value="ElectionName" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_name}" hyperLink="#"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Position_Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.position_name}" hAlign="left" hyperLink="#"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="No_of_Choice" hAlign="left" styleClass="header"/>
      <item   value="${doc.number_of_choice}" hAlign="left" hyperLink="#"  styleClass="item"/>
    </column>

    <column width="10%">
      <header value="candidate_name" hAlign="left" styleClass="header"/>
      <item   value="${doc.candidate_name}" hyperLink="#"  hAlign="left" styleClass="item"/>
    </column>


       <column width="10%">
      <header value="votes" hAlign="left" styleClass="header"/>
      <item   value="${doc.votes}" hyperLink="#"  hAlign="left" styleClass="item"/>
    </column>


      



 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="5" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <table width="500" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
    <a href="<c:out value="${next}"/>">Next</a>
</c:if>

</td><td width="400px" align="center">

<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
    <b><a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>

</tr>
<%--<tr><td>
        <%

String msg=(String)request.getAttribute("msg");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

</td></tr>--%>
</table>

  <%}%></td></tr>
</table>
    </body>

</html>
