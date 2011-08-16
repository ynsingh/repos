<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for Ask for Create Account For Particular Ragistration Only Alert window will Appear
     This jsp page is Fourth page  for one Complete Process  of member Registration.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*;"%>
<jsp:include page="/admin/header.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
 // these values are get from cir_member_message set parameter values(from href link)
 //these are not from set attribute of action class
String mem_id=(String)request.getParameter("mem_id");
if(mem_id==null)
    mem_id=(String)request.getAttribute("mem_id");

String mem_name=(String)request.getParameter("mem_name");

if(mem_name==null)
    mem_name=(String)request.getAttribute("mem_name");

String mail_id=(String)request.getParameter("mail_id");
if(mail_id==null)
    mail_id=(String)request.getAttribute("mail_id");

String last_name=(String)request.getParameter("last_name");
if(last_name==null)
    last_name=(String)request.getAttribute("last_name");


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
}catch(Exception e)
      {
          locale1="en";
      }
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
    <table width="400px"   valign="top" class="table" align="center">
        <tr><td   width="400px" valign="top" class="headerStyle"  align="center"><%=resource.getString("circulation.cir_createaccount1.mangmemacc")%></td></tr>
        <tr><td class="mess" align="center">
						<br>
						<br>

                                               <%=resource.getString("circulation.cir_ask_for_createacc.doyouwanttocreateacc")%> :<br><br> <%=resource.getString("circulation.cir_createaccount1.memname")%>:<b><%=mem_name%></b><br>
                <input type="hidden" value="<%=mem_name%>" name="mem_name"/>
               <input type="hidden" value="<%=last_name%>" name="last_name"/>
               <input type="hidden" value="<%=mem_id%>" name="mem_id"/>


                    <br><br>&nbsp;&nbsp;
                    <input type="button" value="<%=resource.getString("circulation.cir_ask_for_createacc.createacc")%>" onClick="show()" class="txt2"/>
                    <input type="button" value="<%=resource.getString("circulation.cir_ask_for_createacc.skip")%>" class="txt2" onClick="window.location='<%=request.getContextPath()%>/admin/main.jsp';"/>
                    <br><br>
               

                    </td></tr></table>



        </div>
   

    </body>

<script language="javascript" type="text/javascript">
    function show()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_createaccount.jsp?mem_id=<%=mem_id%>&mem_name=<%=mem_name%>&mail_id=<%=mail_id%>&last_name=<%=last_name%>";
    }
    </script>



</html>
