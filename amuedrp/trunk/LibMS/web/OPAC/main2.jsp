<%@ page language="java" %>

<%String username=(String)session.getAttribute("username");

if (username!=null){%>
<frameset rows="85%,*" border="0" frameborder=0 framespacing="0" scrolling="YES" >


  <frame src="header.jsp" align="top" frameborder=0 scrolling="NO"/>

  <IFRAME name="f2" src="" frameborder=0 scrolling="NO" />
  <frameset rows="*" border="0" frameborder ="0" framespacing="0">
   
       <frame name="f4" src="footer.jsp" frameborder=0 scrolling="NO"/>
  </frameset>
</frameset>
<%}else{   %>
<jsp:forward page="login.jsp"/>

    <%}%>
