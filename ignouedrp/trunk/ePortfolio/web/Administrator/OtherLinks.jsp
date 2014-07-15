<%-- 
    Document   : OtherLinks
    Created on : 20 Sep, 2012, 6:01:05 PM
    Author     : Vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<ul>
    <s:iterator value="CaptionList" status="stat">
        <li><a href="<s:property value="UrlList[#stat.index]"/>" target="_blank"><s:property value="CaptionList[#stat.index]"/></a></li>
    </s:iterator>
</ul>