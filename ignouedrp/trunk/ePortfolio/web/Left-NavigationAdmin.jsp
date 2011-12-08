<%-- 
    Document   : Left-NavigationAdmin
    Created on : Nov 3, 2011, 11:56:01 AM 
Author     : Amit
Version    : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div id="col1">

    <s:url id="RefID" action="ShowReference" namespace="/MyProfile"/>
    <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
    <ul id="accordion">
        <li style="background:none;"><div>
                <a style="color:#000; font-size:16px;" href="<s:url value="/Admin-Index.jsp"/>">Home</a></div></li>
        
           
            <li><div><a href="#">Events</a></div><ul>
                <li><a href="<s:url value="/Events/AddNewEvent.jsp"/>">Create Event</a></li>
            </ul></li>
    </ul>
</div>