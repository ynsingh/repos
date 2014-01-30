<%--
    Document   : Left-Nevigation
    Created on : Oct 10, 2011, 11:32:19 AM
Author     : IGNOU Team
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib  prefix="sx"  uri="/struts-dojo-tags"%>
<head>
    <sx:head/>
</head>

<%
    String role = session.getAttribute("role").toString();
%>
<s:url id="LinksID" action="ShowNavLinks" namespace="/Administrator"/>
<s:url id="RegInsiID" action="ShowRegisteredInstitute" namespace="/Administrator"/>
<s:url id="AllInsiID" value="/Administrator/Programmes.jsp"/>
<s:url id="DepID" value="/Administrator/Departments.jsp"/>
<div class="left_box">
    <% if (role.contains("admin")) {%>
    <div class="w100 fl-l">
        <div class="accordion">
            <div id="accordion">
                <h3><a href="#">Admin Responsibilities</a></h3>
                <div class="accordion_txt">
                    <ul>
                        <li><s:a href="%{RegInsiID}">Institutes</s:a></li>
                        <li><s:a href="%{DepID}">Department/School</s:a></li>
                        <li><s:a href="%{AllInsiID}">Programmes</s:a></li>
                            <s:url id="cId" action="myCourse" namespace="/Administrator"/>
                        <li><s:a href="%{cId}">Course</s:a></li>
                        <li><s:a href="%{LinksID}">Add Other Links</s:a></li>
                        <li><a href="<s:url value="/Administrator/HeaderChange.jsp"/>">Change Header</a></li>
                        <li><s:a action="UserReqList" namespace="/Requests">Student Requests</s:a></li>
                        <li><s:a action="ShowContactUsAdmin" namespace="/Administrator">Contact Us</s:a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    <% } else {%>
    <div class="w100 fl-l">
        <div class="accordion">
            <div id="accordion">
                <% if (role.contains("student")) {%>
                <h3><a href="#">My Affiliations</a></h3>
                <div class="accordion_txt">
                    <ul>
                        <!--      <li>Courses</li>
                        <li><a href="<s:url value="/Activities/ActivityIndex.jsp"/>">My Activity</a></li> -->
                        <li>Progress</li>
                        <li><s:a action="myPeerGroup" namespace="/MyGroups">Peer Group</s:a></li>
                        <li><s:a action="myFacultyGroup" namespace="/MyGroups">Faculty</s:a></li>
                        <li><a  href="<s:url value="/Requests/RequestIndex.jsp"/>">Requests for Change Information</a></li>
              <!--          <li><a href="<s:url value="/TalkTeacher.jsp"/>"   target="_blank">Talk To Teacher</a></li> -->
                    </ul>
                </div>
                <% } else {%>
                <h3><a href="#">My Affiliations</a></h3>
                <div class="accordion_txt">
                    <ul>
                        <li><s:a href="%{RegInsiID}">Institutes</s:a></li>
                        <li><s:a href="%{DepID}">Department/School</s:a></li>
                        <li><s:a href="%{AllInsiID}">Programmes</s:a></li>
                            <s:url id="cId" action="myCourse" namespace="/Administrator"/>
                        <li><s:a href="%{cId}">Course</s:a></li>
                            <li>Team</li>
                            <li>On-going Work</li>
                        <!--          <li><a href="<s:url value="/TalkTeacher.jsp"/>"   target="_blank">Talk To Teacher</a></li>
                    <li><a href="<s:url value="/Activities/ActivityForm.jsp"/>">My Activity</a></li> -->
                        <li>Grade</li>
                        <li><a  href="<s:url value="/Requests/RequestIndex.jsp"/>">Requests for Change Personal Information</a></li>
                    </ul>
                </div>
                <%}%>
                <h3><a href="#">Others</a></h3>
                <div class="accordion_txt">
                    <s:url id="olid" value="/Administrator/ShowOtherLinks.action"/>
                    <!-- Calling Actions -->
                    <sx:div href="%{#olid}"/>
                </div>
            </div>
        </div>
    </div>
    <%}%>
    <div class="u_events mart10">Upcoming Events</div>
    <s:url id="eventI" value="/Events/eShowEventInfo.action" />
    <!-- Calling Actions -->
    <sx:div href="%{#eventI}" />

</div>
