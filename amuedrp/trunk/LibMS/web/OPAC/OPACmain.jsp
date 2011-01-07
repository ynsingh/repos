
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
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
    </script>


</head>




          <%if(!page.equals(true)){%>

          <%--      <%String username=(String)session.getAttribute("username");
%>--%>

<frameset rows="28%,62%,10%" border="0" frameborder=2 framespacing="0">


  <frame name="f1" src="../OPAC/opacframe.jsp" frameborder=2 scrolling="NO"/>

<frameset cols="85%,*">

    <frame name="f3" src="../OPAC/opachome.jsp" frameborder=2 scrolling="NO"/>

<frame name="f2" src="../OPAC/m.jsp" frameborder=0 scrolling="NO"/>
</frameset>
       <frame name="f4" src="/LibMS-Struts/OPAC/footer.jsp" frameborder=0 scrolling="NO"/>

</frameset>

            <%}else{%>



            <%--    <%String username=(String)session.getAttribute("username");

if (username!=null){%>--%>
<frameset rows="28%,62%,10%" border="0" frameborder=2 framespacing="0">


  <frame name="f1" src="../OPAC/opacframe.jsp" frameborder=2  scrolling="NO"/>

<frameset cols="18%,*">


<frame name="f2" src="../OPAC/m.jsp" frameborder=0 scrolling="NO" />
     <frame name="f3" src="../OPAC/opachome.jsp" frameborder=2 scrolling="YES"/>


</frameset>
       <frame name="f4" src="/LibMS-Struts/OPAC/footer.jsp" frameborder=2 scrolling="NO"/>

</frameset>
<%}%>


</html>

