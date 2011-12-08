<%-- 
    Document   : ProjectInfo
    Created on : Oct 19, 2011, 3:26:02 PM
Author     : Vinay
Version      : 1
    Version    : 2
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body><%
        if (session.getAttribute("user_id") == null) {
            pageContext.forward("../login.jsp");
        }

        %>
        <s:include value="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <s:include value="/Left-Nevigation.jsp"/>

                <div id="col2">
                    <h3>My Projects</h3>
                    <a href="<s:url value="/MyWorkspace/ProjectInfoAdd.jsp"/>">
                        <img src=" <s:url value="/icons/add.gif"/>" align="right" title="Add Project"/>
                    </a>
                    <br/>
                    <div>

                        <ol class="default1">
                            <s:iterator value="ProList" var="pro">


                                <li><span style="float:right; width:40px;"> 
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
                    </div>


                </div>
                <s:include value="/Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/> 
    </body>
</html>
