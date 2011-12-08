<%-- 
    Document   : HonourAwardInfo
    Created on : Oct 13, 2011, 12:55:31 PM
    Version    : 1
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Honour &amp; Awards</title>
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
        <link href="theme1/style.css" rel="stylesheet" type="text/css" />
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
                    <h3>My Honour &amp; Awards</h3>

                    <a href="<s:url value="HonourAwardInfoAdd.jsp"/>">
                        <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Honour or Award"/>
                    </a>
                    <table width="100%" class="defaulttab1" cellpadding="0" cellspacing="0">
                        <tr>
                            <th width="120">Title</th>
                            <th>Issuer</th>
                            <th width="75">Issue Date</th>
                            <th  width="200">Description</th>
                            <th width="75">Edit&nbsp;&nbsp;Delete</th>                                                        
                        </tr>
                        <s:iterator value="HonorAwardList" var="ProHonor">
                            <tr>  
                                <td>
                                    <s:property value="haTitle"/>
                                </td><td>
                                    <s:property value="issuer"/>
                                </td><td>
                                    <s:property value="haDate"/>
                                </td><td>
                                    <s:property value="haDescription"/>
                                </td>
                                <td valign="middle" style="vertical-align:middle;" align="center">
                                    <a href="editAwardInfo?honorAwardId=<s:property value="honorAwardId"/>">
                                        <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/>
                                    </a>&nbsp;&nbsp;
                                    <a href="deleteAwardInfo?honorAwardId=<s:property value="honorAwardId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                        <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/>
                                    </a></td>                                                        
                            </tr>
                        </s:iterator>
                    </table>
                    <br/><br/><br/><br/>
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
