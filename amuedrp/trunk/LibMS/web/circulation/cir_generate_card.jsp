<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>
 <jsp:include page="/admin/header.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
// message from circulationmemberaction
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");
// message from circulationnewmemberregaction
String msg2=(String)request.getAttribute("msg2");
%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<body>

    <html:form method="post" action="/membercard">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table dir="<%=rtl%>" width="400px" height="200px"  valign="top" align="center" class="table">

        <tr><td dir="<%=rtl%>"  class="headerStyle" align="center">



          <b>Card Management</b>





        </td></tr>
   <tr><td></td></tr>

        <tr><td dir="<%=rtl%>"  width="400px" height="200px" valign="top" style="" align="center">
                <br><br>
                <table cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("circulation.cir_member_reg.entermemid")%><br><br>
                            <html:text property="TXTMEMID" value="" /><br/>
                         

                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn2width"   id="button1"  value="Generate Card" onclick="return Register('Generate Card');" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="button2" class="btn2width"  value="Lost Card" onclick="return Register('Lost Card');"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="button3" class="btn2width"  value="Duplicate Card" onclick="return Register('Duplicate Card');"  /></td></tr>

 
 <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="button5"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn2width" onclick="return quit()"/></td></tr>



                </table>



    <input type="hidden" name="library_id" value="<%=library_id%>">
   <input type="hidden" id="button" name="button" value=""/>








</td></tr>

        <tr><td class="mess">

    <%     if (msg1!=null){
 %>

<p class="err"><%=msg1%></p>

 <%
}

%>

 <%     if (msg!=null){
 %>
 <p class="mess"> <%=msg%></p>

 <%
}

%>

            </td></tr>

    </table>
        </div>

</html:form>

</body>
<script language="javascript" type="text/javascript">


 function Register(x)
{
    var buttonvalue=x;
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>

</html>