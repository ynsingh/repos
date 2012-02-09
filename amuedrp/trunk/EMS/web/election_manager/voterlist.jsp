
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,com.myapp.struts.Candidate.CandidateRegistrationDAO,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>

<jsp:include page="/election_manager/login.jsp"/>
<html><head>
<title> </title>
<link href="common" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />

<% 
   String msg=(String)request.getAttribute("msg");
%>

<script language="javascript">

function validate()
{

    var list =document.getElementById("list2").value;
  
  alert(list);
  if (list=="")
  {
 alert("Please Select the List");
    
        return false;
  }
  else
   {
     
       return true;
   }
}


</script>
<%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;
%>
<%

List<VoterRegistration> l1=(List<VoterRegistration>)session.getAttribute("VoterList");

List<Election> election=(List<Election>)session.getAttribute("underprocessList");
System.out.println(election.size()+"..............");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else

     {
     pageIndex = 1;
     }

 if(l1!=null)
        size = l1.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;



%>

</head>
<body>

 
    <form name="DepActionForm" id="f" action="/EMS/setvoter.do">

     
   <table width="80%" border="0" cellspacing="4" cellpadding="1" valign="top" align="center" >
    <tr><td colspan="4"   height="25px" width="50px" align="center">



          <b>Set Voter List</b>





        </td></tr>
     <tr><td colspan="4"   height="25px" width="50px" align="center">



          <b>Select Election </b>
         <%if(election.size()>0){%>
          <select name="electionId" id="election_id">
                <%
               
for(int i=0;i<election.size();i++){
%>
     <option value="<%=election.get(i).getId().getElectionId()%>"><%=election.get(i).getElectionName() %></option>
     <input type="hidden" name="election" value="<%=election.get(i).getElectionName() %>"/>
     <%}%>
   </select>

<%}else{%>No Election<%}%>

        </td></tr>

    <tr><td>
            <IFRAME  name="f1"  src="<%=request.getContextPath()%>/votersetup.do?id='setvoter'" frameborder=0 scrolling="yes" style="height:300px;width:100%;visibility:true;" id="f1"></IFRAME>
            
        </td>     </tr>

       <tr align="center">
        <td align="center" dir="" colspan="4"><p align="center" dir="">Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="f1" href="<%=request.getContextPath()%>/votersetup.do?id='setvoter'&amp;pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
            </p>
        </td>
       </tr>

 </table>




<div align="center">
    <input type="hidden" name="list" id="list2" value="" />
    <input type="submit" name="button" value="Submit" onclick="return validate();" />
    <input type="button" name="button" value="Back" onclick="return quit()"/>
</div>
    </form>

</body>
</html>