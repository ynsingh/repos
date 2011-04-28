
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>OPAC</title>
<style type="text/css">

body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>

<style type="text/css">
a:active
{
   color: #0000FF;
}
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
  .header1        { font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
     .datagrid      { 
    font-weight: normal;font-size: 10px;
	  }
     .item{ padding-left: 10px;}

</style>
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
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
    </script>


</head>

<body>


    <form method="post" action="opachomeRTL.jsp" name="form1">

       
     

        <table cellpadding="0" cellspacing="1" id="Table1" dir="<%=rtl%>" align="<%=align%>">
 
 <tr><td dir="<%=rtl%>" align="<%=align%>" valign="top">
        <table  dir="<%=rtl%>" align="<%=align%>" width="800x"  style="border:solid 1px #e0e8f5;">



  <tr class="header" dir="<%=rtl%>" align="<%=align%>"><td  width="800px"  height="40px" dir="<%=rtl%>"  align="center" colspan="2" style="font-size: 18px;">


		<%=resource.getString("opacmainframe.opachome.text1")%>



        </td></tr>
  <tr><td width="800px" dir="<%=rtl%>">
              <table  height="300px" border="0" dir="<%=rtl%>" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" >

    <tbody><tr dir="<%=rtl%>">
            <td  align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text2")%>
    </td>

    </tr>
<tr>
    <td  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text3")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" dir="<%=rtl%>" align="<%=align%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text4")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text5")%>
    </td>

    </tr>
    <tr>
    <td class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text6")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Check Your Account history using MyAccount Option
    </td>

    </tr>
     <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text8")%>
    </td>

    </tr>
     <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text9")%>
    </td>

    </tr>

  </tbody></table>





      </td></tr></table>


     </td></tr>


  <%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %> <br>
       <TABLE class="datagrid" align="<%=align%>" dir="<%=rtl%>" style="background-color: #E3E4FA;font-size:15px;"
                   WIDTH="40%" border="1" >
		      <tr><th><%=message%></th></tr>
		   </TABLE>
   <% }else
        message="";
    %>
</table>
    </form>
</body>
</html>
