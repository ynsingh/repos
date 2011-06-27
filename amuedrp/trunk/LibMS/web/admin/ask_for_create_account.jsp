

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String staff_id=(String)request.getParameter("staff_id");
String first_name=(String)request.getParameter("first_name");
String last_name=(String)request.getParameter("last_name");

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
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS : Manage Staff Create Account </title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" dir="<%=rtl%>" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("admin.ask_for_create_account.head") %></td></tr>
                <tr><td valign="top"   align="center"> <br/>
      						<br>
						<br>

                                            <%=resource.getString("admin.ask_for_create_account.mess") %>   &nbsp; <b><%=first_name%>&nbsp;<%=last_name%></b>?<br>
<input type="hidden" value="<%=first_name%>" name="first_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    
</td></tr>
                <tr><td  dir="<%=rtl%>" align="center">
                   
                    <input type="button" value="<%=resource.getString("admin.ask_for_create_account.createaccount") %> " onClick="show()" class="txt2"/>
                    <input type="button" value="<%=resource.getString("admin.ask_for_create_account.skip") %> " class="txt2" onClick="window.location='<%=request.getContextPath()%>/admin/main.jsp';"/>

                    </td></tr>
                    </table>



        </div>
   

    </body>

<script language="javascript" type="text/javascript">
    function show()
    {
        location.href="<%=request.getContextPath()%>/admin/account2.do?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>";
    }
    </script>



</html>
