<%-- 
    Document   : Event
    Created on : Oct 19, 2011, 12:18:55 PM
Author     : IGNOU Team
Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="u_events_txt">
    <s:url id="EventID" action="allEvents" namespace="/Events"/>
    <s:property value="msg"/>
    <marquee direction="up" scrollamount="2"  scrolldelay="1"  onmouseover="this.stop();" onmouseout="this.start();">
        <s:iterator value="eventList" >
            <a href="<s:property value="website"/>" target="_blank">
                <s:property value="eventTitle"/>
                on 
                <s:property value="eventDateFrom"/>
                to
                <s:property value="eventDateTo"/>
                at
                <s:property value="venue"/>
            </a>
        </s:iterator> 
    </marquee>
</div>