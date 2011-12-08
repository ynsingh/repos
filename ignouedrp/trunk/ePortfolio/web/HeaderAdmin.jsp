<%-- 
    Document   : HeaderAdmin
    Created on : Nov 3, 2011, 12:32:18 PM 
Author     : Amit
Version    : 1
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div id="header">
    <div class="wrapper">
        <div id="logo">
            <a href="<s:url value="/Index.jsp"/>">
            <img src="<s:url value="/theme1/images/logo.png"/>"/>
            </a>
        </div>
        <div id="accountinfo">
         
            <img src="<s:url value="/theme1/images/ignou-logo.jpg"/>"/>
            <div id="profilelinks">
                <s:url id="logoutID" action="logout" namespace="/"/>
                <s:a href="%{logoutID}">Logout</s:a></div></div>
            <div id="changecolors">
                <a href="#">
                    <img src="<s:url value="/theme1/images/theme-red.gif"/>"  alt="Red"/></a>
            <a href="#">
                <img src="<s:url value="/theme1/images/theme-green.gif"/>" alt="Green" />
            </a><a href="#">
                <img src="<s:url value="/theme1/images/theme-yellow.gif"/>" alt="Yellow" />
            </a><a href="#">
                <img src="<s:url value="/theme1/images/theme-blue.gif"/>" alt="Blue" />
            </a>
        </div>
    </div>
</div>