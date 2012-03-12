<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="/election_manager/login.jsp"/>
<%@page import="java.util.*,java.io.*,com.myapp.struts.hbm.Election"%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String status;
%>
<%
status = request.getParameter("status");
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



<html><head>
<title>Search Voter.....</title>


<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

List<Election> election=(List<Election>)session.getAttribute("SetVoterList");
%>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script language="javascript">

function fun()
{

  <%if(status!=null){%>
    document.DepActionForm.status.value = "<%=status%>";
    <%}%>
document.DepActionForm.action="/EMS/votersetup.do?status=A";
            document.DepActionForm.method="post";
            document.DepActionForm.target="f1";
            document.DepActionForm.submit();
            window.setTimeout('winresize()', 100);
}
function validate()
{
    
  document.getElementById('election').value=document.getElementById('election_id').value;

  var ele=document.getElementById('election').value;

//alert(ele);

document.getElementById('action').value=document.getElementById('action').value;
    var x=document.getElementById('action').value;
  //  alert(x);
    if(x=="Select")
        {
            alert("Please Action Type");
            return false;
         }

else{
    
if(x=="1"){
    
    if(ele=="Select")
            {
            	alert("Please select the election");
                return false;
            }
    
}

document.DepActionForm.action="/EMS/setvoter2.do";
document.DepActionForm.method="post";
document.DepActionForm.submit();
return true;
}
}
 function winresize()
{
    //alert(document.width);
  
   var winwidth = document.width;
    var IFRAMERef = frames['f1'];
   // alert(IFRAMERef);
    var frmwidth = IFRAMERef.document.width;
    var windiff=200;
    var frmheight;
        if(IFRAMERef.document!=undefined)
        frmheight= IFRAMERef.document.height;
        else
            if(IFRAMERef.document.getElementById("form3")!=undefined)
        frmheight= IFRAMERef.document.getElementById("form3").height;
        else
            frmheight = 500+"px";
    //alert("frmheight="+frmheight);
    if(winwidth!=undefined && frmwidth!=undefined)
        windiff= winwidth - frmwidth;
    document.getElementById("ifr3").style.paddingLeft = windiff*0.5+"px";
    document.getElementById("ifr3").style.paddingRight = windiff*0.5+"px";
    document.getElementById("ifr3").style.height = frmheight;
   
}
</script>

</head>
<link rel="stylesheet" href="/EMS/css/page.css"/>
<%
String msg=(String)request.getAttribute("msg");
if(msg!=null){
%>
<body  class="datagrid" onload="fun();">
<script>alert("<%=msg%>");</script>
<%}else{%>
<body class="datagrid" onload="fun();">
    <%}%>
   

    <form name="DepActionForm" id="f1" onsubmit="return validate();" >
      <table  align="left" width="100%"  class="datagrid"  style="border:solid 1px #e0e8f5;" dir="<%=rtl%>" align="<%=align%>">



          <tr class="header"><td  width="100%"   align="center" colspan="2" dir="<%=rtl%>">


                 Reset Voter Password 
                  <%--<%=resource.getString("login.searchinstitute.institutesearch")%>--%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table dir="<%=rtl%>" align="<%=align%>">
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("enterstartingkeyword")%></td><td><input  name="search_keyword" type="text" id="search_keyword" onkeyup="fun()"></td>
              <td>


                  <input type="reset" id="Button1" name="clear" value="<%=resource.getString("login.searchinstitute.clear")%>">


      </td></tr>
              

          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.infield")%> </td><td rowspan="2" valign="top">
 <select name="search_by" onChange="fun()" id="search_by" size="1">
<option selected value="enrollment">Enrollment No<%--<%=resource.getString("managername")%>--%></option>
<option value="email">User ID<%--<%=resource.getString("managername")%>--%></option>
<option value="alternateMail">Alternate Email<%--<%=resource.getString("managername")%>--%></option>
<option value="voterName">Voter Name<%--<%=resource.getString("managername")%>--%></option>


<%--<option value="city"><%=resource.getString("city")%></option>--%>


</select>

          
     </td>

              </tr></table></td></tr>
  <tr class="header"><td dir="<%=rtl%>" align="left" colspan="2"><%=resource.getString("login.searchinstitute.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table width="100%">
               <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.field")%></td><td ><select name="sort_by" id="sort_by" size="1" onChange="fun()" id="">
<option selected value="enrollment">Enrollment No<%--<%=resource.getString("managername")%>--%></option>

<option value="voterName">Voter Name<%--<%=resource.getString("managername")%>--%></option>
<option value="department">Department<%--<%=resource.getString("managerid")%>--%></option>
<option value="course">Course</option>
</select></td>
                   <td align="center">Select Election
                            <select name="electionId" class="btn2" id="election_id" size="1">
                             <option value="Select">Select</option>
                            <%if(election.size()>0){%>
                             <%
                            for(int i=0;i<election.size();i++)
                            {
                            %>
                            <option value="<%=election.get(i).getId().getElectionId()%>"><%=election.get(i).getElectionName() %></option>
                            <%}%>
                            <%}%>
                        </select>  <input type="hidden" name="election" id="election"/>&nbsp;&nbsp;&nbsp;
                        Action<select   size="1" name="action"  id="action">
<option selected value="Select">Select<%--<%=resource.getString("managername")%>--%></option>
<option  value="1">Current Page ( Reset Password & Send one Time Key for Voter not Cast there vote yet. )</option>
<option  value="2">All ( Reset Password & Send one Time Key for Voter not Cast there vote yet )</option>


</select><input type="button" onclick="return validate();" name="button" value="Submit" />
    
      </td>

                           </tr></table>


      </td>

  </tr>
  
   <tr><td colspan="2" id="ifr3"><IFRAME  name="f1" src="/EMS/votersetup.do?status=A" frameborder=0  id="f1" width="100%" height="700px" ></IFRAME></td></tr>
     
 
 
       </table>



   


<input  name="status" type="hidden" id="status"/>

 
 

</form>

    

</body>
</html>
