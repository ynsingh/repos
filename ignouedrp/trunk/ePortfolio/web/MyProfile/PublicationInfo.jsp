<%-- 
 Document   : PublicationInfo
 Created on : Sep 13, 2011, 5:12:33 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
        <jsp:include page="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">

                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>My Publications</h3>

                    <a href="PublicationInfoAdd.jsp">
                        <img src="../icons/add.gif" align="right" title="Add Publication"/>
                    </a>
                    <br/><br/>

                    <div>
                        <p><strong>Conference Papers</strong></p>
                        <ol class="default1"><s:iterator value="PubListList" var="Pub">  


                                <li><span style="float:right; width:40px;"> 
                                        <a href="editPub?publicationId=<s:property value="publicationId"/>">
                                            <img src="../icons/edit.gif" title="Edit Record"/>
                                        </a>
                                        <a href="deletePub?publicationId=<s:property value="publicationId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                            <img src="../icons/delete.gif" title="Delete Record"/>
                                        </a>
                                    </span>
                                    <s:property value="auther1"/>,
                                    <s:property value="auther2"/>
                                    <s:property value="auther3"/>
                                    <s:property value="auther4"/>,
                                    <a href="<s:property value="pubUrl"/>" target="_blank">
                                        "<s:property value="pubTitle"/>"
                                    </a>
                                    in
                                    <i><s:property value="publisher"/></i>,
                                    <s:property value="pubDate"/>.
                                </li>                                    

                            </s:iterator></ol>
                    </div>

                    <br/><br/><br/>
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
