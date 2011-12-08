<%-- 
    Document   : EmployeeInfo
    Created on : Sep 13, 2011, 11:52:03 AM
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
                    <h3>Employment Information</h3>
                    
                    <a href="EmployeeInfoAdd.jsp">
                        <img src="../icons/add.gif" align="right" title="Add Employment Information"/>
                    </a>
<br/><p/>
                    <table width="100%" class="defaulttab1" cellpadding="0" cellspacing="0">
                        <tr><th>Job Title</th>
                            <th>Organization Name</th>
                            <th width="225">Address</th>
                            <th>Joining Date</th>
                            <th>Relieved On</th>
                            <th>Role/Description</th>
                            <th>Edit, Delete</th></tr>

                        
                        
                        <s:iterator value="employmentListList" var="ProfileEmployment">
                            <tr>
                                <td><s:property value="jtitle"/></td>
                                <td><s:property value="orgName"/></td>
                                <td width="225"><s:property value="oaddress"/>
                                    <s:property value="ocity"/>
                                    <s:property value="ostate"/>
                                    <s:property value="ocountry"/>
                                </td>
                                
                                <td><s:property value="jdate"/></td>
                                <td><s:property value="ldate"/></td>
                                <td width="250"><s:property value="description"/></td>
                             
                                <td align="center"><a href="EditEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>">
                                        <img src="../icons/edit.gif" title="Edit Record"/>
                                    </a>
                                <a href="DeleteEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                        <img src="../icons/delete.gif" title="Delete Record"/>
                                    </a>
                                </td>

                            </tr>


                        </s:iterator>
                    </table>

                    <br/><br/><br/>
                    
                </div>
<jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
<jsp:include page="../Footer.jsp"/>
</body>
</html>
