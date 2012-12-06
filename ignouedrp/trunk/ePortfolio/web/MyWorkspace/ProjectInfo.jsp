<%-- 
    Document   : ProjectInfo
    Created on : Oct 19, 2011, 3:26:02 PM
    Author     : IGNOU Team
    Version    : 2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Project</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">My Projects</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> My Projects </div>
                                    <div class="w100 fl-l">
                                        <s:a href="ProjectInfoAdd.jsp" namespace="/MyWorkspace"> <img src=" <s:url value="/icons/add.gif"/>" align="right" title="Add Extra Activity"/> </s:a>
                                        </div>
                                        <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" border="1" class="fl-l richtxt" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <th width="5%" align="center">S. No</th>
                                                <th width="9%">Title</th>
                                                <th width="6%">Role</th>
                                                <th width="9%">Team Size</th>
                                                <th width="14%">Duration</th>
                                                <th width="14%">Funding Agency</th>
                                                <th width="11%">Budget</th>
                                                <th width="19%">Description</th>
                                                <th width="5%">Edit</th>
                                                <th width="8%">Delete</th>
                                            </tr>
                                            <s:iterator value="ProList" var="pro" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="%{#stat.count}"/></td>
                                                    <td><a href="<s:property value="proUrl"/>" target="_blank">
                                                            <s:property value="proName"/>
                                                        </a> </td>
                                                    <td><s:property value="role"/>
                                                    </td>
                                                    <td align="center"><s:property value="teamSize"/>
                                                    </td>
                                                    <td><s:property value="startDate"/>
                                                        -
                                                        <s:property value="endDate"/>
                                                    </td>
                                                    <td><s:property value="agency"/>
                                                    </td>
                                                    <td align="center"><s:property value="budget"/>
                                                    </td>
                                                    <td><s:property value="description" escape="false"/>
                                                    </td>
                                                    <td align="center"><a href="editProject?projectId=<s:property value="projectId"/>"> <img src="<s:url value="/icons/edit.gif" />" title="Edit Record"/> </a></td>
                                                    <td align="center"><a href="deleteProject?projectId=<s:property value="projectId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/></a> </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
                                        <!--    <ol class=" mar0 pad0">
                                        <s:iterator value="ProList" var="pro">
                                            <li>
                                                <span style="float:right; width:40px;"> 
                                                    <a href="editProject?projectId=<s:property value="projectId"/>">
                                                        <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/>
                                                    </a>
                                                    <a href="deleteProject?projectId=<s:property value="projectId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                                        <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/>
                                                    </a>
                                                </span>
                                                <a href="<s:property value="proUrl"/>" target="_blank">
                                            <s:property value="proName"/>
                                        </a>
                                        <br/>                                   
                                        <strong>
                                            Role:&nbsp;
                                        </strong>
                                            <s:property value="role"/>
                                            <br/>
                                            <strong>Team Size:&nbsp;</strong>&nbsp;
                                            <s:property value="teamSize"/>
                                            ,&nbsp;&nbsp;<strong>Duration:&nbsp;</strong>&nbsp;
                                            <s:property value="startDate"/> - <s:property value="endDate"/>
                                            <br/>
                                            <s:if test="agency!=null">
                                                <strong>
                                                    Funding Agency:&nbsp;
                                                </strong>&nbsp;
                                                <s:property value="agency"/>,&nbsp;
                                            </s:if>
                                            <s:if test="budget!=null">
                                                <strong>
                                                    Budget:&nbsp;
                                                </strong>&nbsp;
                                                <s:property value="budget"/>
                                            </s:if>
                                            <s:if test="description!=null">
                                                <strong>
                                                    Description:&nbsp;
                                                </strong>
                                                <s:property value="description"/>
                                            </s:if>
                                        </li>                                    
                                        </s:iterator>
                                    </ol>
                                        -->
                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
