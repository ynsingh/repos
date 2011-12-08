<%-- 
    Document   : Right-Nevigation
    Created on : Oct 10, 2011, 11:32:03 AM
Author     : Vinay
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div id="col3">  
    <s:include value="/Calender.jsp"/>
    <h3>Upcoming Events</h3>
    <s:include value="/Event.jsp"/>
    <div class="more"><a href="<s:url value="/Events/"/>" target="_blank">more...</a></div>
    <h3>Online Users</h3>
    <div>
        <img src="<s:url value="/images/onlineUsers.png"/>"/>
    </div>

</div>