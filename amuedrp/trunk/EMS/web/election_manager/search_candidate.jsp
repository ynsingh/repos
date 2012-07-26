<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page import="java.util.*,java.io.*,java.net.*"%><%
String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<jsp:include page="/institute_admin/adminheader.jsp"/>
<%}else{%>
<jsp:include page="/election_manager/login.jsp"/>
<%}%>

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
    %><script>parent.location = "<%=request.getContextPath()%>/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

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
    document.Form1.status.value = "<%=status%>";
    <%}%>
document.Form1.action="<%=request.getContextPath()%>/candidatesetup1.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();

window.setTimeout('winresize()', 100);
}

function mailsend()
{



document.Form1.action="<%=request.getContextPath()%>/election_manager/ViewMailBody.jsp?mail=sendmailtoall";
document.Form1.method="post";
document.Form1.submit();

}


 function winresize()
{
    //alert(document.width);
  
   var winwidth = document.width;
    var IFRAMERef = frames['f1'];
   // alert(IFRAMERef);
   var frmwidth = IFRAMERef.document.width;
   if(frmwidth==0) frmwidth=1175;
    var windiff=200;
    var frmheight=250;
    if(IFRAMERef!=null)
        if(IFRAMERef.document!=undefined)
        frmheight= IFRAMERef.document.height;
        else
            if(IFRAMERef.document.getElementById("grid")!=undefined)
        frmheight= IFRAMERef.document.getElementById("grid").height;
        else
            frmheight = 500+"px";
    //alert("frmheight="+frmheight);
    else
        frmheight = "250px";
    if(winwidth!=undefined && frmwidth!=undefined)
        windiff= winwidth - frmwidth;
    document.getElementById("ifr3").style.paddingLeft = windiff*0.5+"px";
    document.getElementById("ifr3").style.paddingRight = windiff*0.5+"px";
    document.getElementById("ifr3").style.height = frmheight;
   
}
</script>
</head>
<link rel="stylesheet" href="/EMS/css/page.css"/>
<body onload="fun();" class="datagrid">
   

    <form name="Form1" id="form1" action="<%=request.getContextPath()%>/candidatesetup1.do" style="" >
      <table  align="left" width="100%"  class="datagrid"  style="border:solid 1px #e0e8f5;" dir="<%=rtl%>" align="<%=align%>">



          <tr class="header"><td  width="100%"   align="center" colspan="2" dir="<%=rtl%>">


                 Search Candidate
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
<option value="voter_name">Candidate Name<%--<%=resource.getString("managername")%>--%></option>
<option value="enrollment">Enrollment No<%--<%=resource.getString("managername")%>--%></option>
<option value="department">Department<%--<%=resource.getString("managerid")%>--%></option>
<option value="course">Course<%--<%=resource.getString("registrationid")%>--%></option>
<%--<option value="city"><%=resource.getString("city")%></option>--%>


</select>

          
     </td>

              </tr></table></td></tr>
  <tr class="header"><td dir="<%=rtl%>" align="left" colspan="2"><%=resource.getString("login.searchinstitute.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table>
                           <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.field")%></td><td><select name="sort_by" id="sort_by" size="1" onChange="fun()" >
<option value="voter_name">Candidate Name<%--<%=resource.getString("managername")%>--%></option>
<option value="enrollment">Enrollment No<%--<%=resource.getString("managername")%>--%></option>
<option value="department">Department<%--<%=resource.getString("managerid")%>--%></option>
<option value="course">Course<%--<%=resource.getString("registrationid")%>--%></option>
</select></td><td>
    <input type="button" name="button"  id="b1" onclick="mailsend();" value="Send Mail to All"/>
</td>
                           </tr></table>


      </td>

  </tr>
  <tr><td colspan="2" id="ifr3"><IFRAME  name="f1" src="<%=request.getContextPath()%>/candidatesetup1.do" frameborder=0  id="f1" width="90%" height="700px" ></IFRAME></td></tr>
     
  <tr><td><input type="hidden" id="hidHigh"/></td></tr>
       </table>




<input  name="status" type="hidden" id="status"/>



 

</form>

    

</body>
</html>