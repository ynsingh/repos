
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OPAC Multilingual interface</title>
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
   
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }

        function loadHelp()
        {
            window.status="Press F1 for Help";
        }
    </script>



</head>


<frameset rows="16%,74%,*" border="0" frameborder=2 framespacing="0" dir="<%=rtl%>" align="<%=align%>">


  <frame name="f1" src="../OPAC/opacframe1.jsp" frameborder=2 scrolling="NO" dir="<%=rtl%>" align="<%=align%>"/>
  <%if(align.equals("left")){%>
<%--<frameset cols="11%,*">--%>
<%--<frame name="f2" src="../OPAC/m.jsp" frameborder=0 scrolling="NO" dir="<%=rtl%>" align="<%=align%>"/>--%>
<frame name="f3" src="../OPAC/opachome.jsp" frameborder=2 scrolling="yes" dir="<%=rtl%>" align="<%=align%>"/>
<%}else{%>
<%--<frameset cols="89%,*">--%>
<frame name="f3" src="../OPAC/opachome.jsp" frameborder=2 scrolling="yes" dir="<%=rtl%>" align="<%=align%>"/>
<%--<frame name="f2" src="../OPAC/m.jsp" frameborder=0 scrolling="NO" dir="<%=rtl%>" align="<%=align%>"/>--%>
<%}%>
<%--</frameset>--%>
       <frame name="f4" src="<%=request.getContextPath()%>/OPAC/footer.jsp" frameborder=0 scrolling="NO" dir="<%=rtl%>" align="<%=align%>"/>

</frameset>

</html>

