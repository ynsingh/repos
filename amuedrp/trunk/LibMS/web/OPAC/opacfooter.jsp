
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>


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
    <%
    String library_id = (String)session.getAttribute("library_id");
    String sublibrary_id = (String)session.getAttribute("memsublib");
     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
%>

</head>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px;" >
    <table width="100%" class="homepage">
       <tr><td align="center"  valign="top">
                    
                    <%=resource.getString("developedby")%>  &copy; <%=resource.getString("login.message.footer")%>
         &nbsp; <%--follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="13px" width="18px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="13px" width="18px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="13px" width="18px"/>
      --%><%--<a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="13px" width="40px"/></a>

      --%><i>( Best Viewed with 1024 X 768 on Mozilla FireFox Browser)</i> </td></tr>
            <tr><td align="center" >For reporting bugs, suggestion, feature request, and maintainence support
send Email to amuedrp@gmail.com<br><%--<a href="<%=request.getContextPath()%>/mem.jsp">View Server Memory Status</a>--%><%--&nbsp;|&nbsp;You are the Visitor, Number >>--%>
            <%--        <%
    Integer hitsCount =
      (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* First visit */

       hitsCount = 1;
    }else{
       /* return visit */

       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
%>
<%= hitsCount%>--%>

                </td></tr>
        </table>
    
</body>
</html>
