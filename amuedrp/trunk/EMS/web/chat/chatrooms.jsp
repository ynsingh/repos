<%-- 
    Document   : chatrooms
    Created on : Oct 20, 2011, 12:15:04 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:include page="/Voter/chatlogin.jsp"/>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<TITLE>ChatRoom </TITLE>

</HEAD>
<FRAMESET rows="80%,20%" >
<FRAME SRC="/EMS/chat/displayMessage.jsp" name="MessageWin" frameborder="0">
<FRAME SRC="/EMS/chat/sendMessage.jsp" name="TypeWin" frameborder="0" scolling="no">
</FRAMESET>
<NOFRAMES>
<H2></H2>

</NOFRAMES>
</HTML>

