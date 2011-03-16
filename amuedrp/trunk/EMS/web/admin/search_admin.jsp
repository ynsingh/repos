<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page import="java.util.*,java.io.*,java.net.*"%>

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



<html><head>
<title>Browsing.....</title>


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
document.Form1.action="search_institute.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<link rel="stylesheet" href="/EMS-Struts/css/page.css"/>
<body onload="fun()" class="datagrid">
   

<form name="Form1" action="search_institute.do"  >
      <table  align="left" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5;" dir="<%=rtl%>" align="<%=align%>">



          <tr class="header"><td  width="100%"   align="center" colspan="2" dir="<%=rtl%>">


                  <%=resource.getString("login.searchinstitute.institutesearch")%>




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
<option value="institute_name"><%=resource.getString("institutename")%></option>
<option value="institute_id"><%=resource.getString("instituteid")%></option>
<option value="registration_id"><%=resource.getString("registrationid")%></option>
<option value="city"><%=resource.getString("city")%></option>


</select>

          
     </td>

              </tr></table></td></tr>
  <tr class="header"><td dir="<%=rtl%>" align="left" colspan="2"><%=resource.getString("login.searchinstitute.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table>
                           <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.field")%></td><td><select name="sort_by" id="sort_by" size="1" onChange="fun()" id="">
<option selected value="institute_name"><%=resource.getString("institutename")%></option>
<option value="city"><%=resource.getString("city")%></option>
<option value="registration_id"><%=resource.getString("registrationid")%></option>
</select></td>
                           </tr></table>


      </td>

  </tr>
  <tr><td><IFRAME  name="f1" src="#" frameborder=0  id="f1" width="100%" height="700px" ></IFRAME></td></tr>
     

       </table>



   






 

</form>

    

</body>
</html>