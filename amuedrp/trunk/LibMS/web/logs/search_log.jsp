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



<%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
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


<script language="javascript">
function fun()
{
document.Form1.action="search_log.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body onload="fun();">
   

<form name="Form1" action="search_log.do"  >




    <table  align="left" class="datagrid" border="0px" width="100%"  bgcolor="#7697BC" dir="<%=rtl%>" align="<%=align%>">



          <tr><td  width="80%" height="25px" colspan="2"   align="center" class="txtStyle1" dir="<%=rtl%>">


             Search User Log Details




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="80%">
          <table dir="<%=rtl%>" border="0px" align="<%=align%>">
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("enterstartingkeyword")%></td><td><input  name="search_keyword" type="text" id="search_keyword" onkeyup="fun()"></td>
              <td>


                  <input type="reset" id="Button1" name="clear" value="<%=resource.getString("login.searchinstitute.clear")%>">


      </td></tr>
              

          </table>
      </td>
      <td    align="left" valign="top">
          <table border="0px" >
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.infield")%> </td><td rowspan="2" valign="top">
                      <select name="search_by" onChange="fun()" id="search_by" size="1">
<option value="library_id"><%=resource.getString("instituteid")%></option>
<option value="user_id">User Id</option>



</select>

          
     </td>

              </tr></table></td></tr>
  <tr bgcolor="#7697BC" ><td dir="<%=rtl%>" align="left" colspan="2"><font color="white"><b><%=resource.getString("login.searchinstitute.sortby")%></b></font></td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table>
                           <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.searchinstitute.field")%></td><td><select name="sort_by" id="sort_by" size="1" onChange="fun()" id="">
<option value="library_id"><%=resource.getString("instituteid")%></option>
<option value="user_id">User Id</option>
<option value="date">Date</option>
<option value="sno">Sno</option>
</select></td>
                           </tr></table>


      </td>

  </tr>
  <tr bgcolor="white"><td colspan="2"><IFRAME  name="f1" src="#" frameborder="1"  id="f1" width="100%" height="700px" ></IFRAME></td></tr>
     

       </table>



   








</form>

    

</body>
</html>