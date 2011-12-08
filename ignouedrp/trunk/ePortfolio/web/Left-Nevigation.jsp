<%-- 
    Document   : Left-Nevigation
    Created on : Oct 10, 2011, 11:32:19 AM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div id="col1">

    <s:url id="RefID" action="ShowReference" namespace="/MyProfile"/>
    <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
    <ul id="accordion">
        <li style="background:none;"><div>
                <a style="color:#000; font-size:16px;" href="<s:url value="/Student-Index.jsp"/>">Home</a></div></li>
        <li><div><a href="#">My Portfolio</a></div>
            <ul><li><a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Testimonials</a></li>
                <li><s:a href="%{RefID}">References</s:a></li>
                </ul></li>
            <li><div><a href="#">My Education and Work</a></div><ul>
                    <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Institute</a></li>
                    <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Course</a></li>
                    <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Examination and Evaluation</a></li>
                    <li><a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a></li>
            </ul></li>
        <li><div><a href="#">My Connections</a></div><ul>
                <li><s:a href="%{PSID}">Social Network</s:a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Chat</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Blog</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Wiki</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Communication</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Discussion Forum</a></li>
            </ul></li>
        <li><div><a href="#">My Builder</a></div><ul>
                <li><a href="<s:url value="/MyResume/indexResume.jsp"/>">Resume Builder</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">Visiting Card Builder</a></li>
                <li><a href="<s:url value="/PageUnderConstruction.jsp"/>">My Portfolio Generator</a></li>
            </ul></li>
    </ul>
</div>