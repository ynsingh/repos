<%-- 
    Document   : EducationInfo
    Created on : Sep 1, 2011, 5:58:53 PM
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
       <s:include value="/Header.jsp"/>

            <div id="container">
                <div class="wrapper">

                    <s:include value="/Left-Nevigation.jsp"/>
                    <div id="col2">
                        <h3>Academic Information</h3>

                        <a href="AcademicInfoAdd.jsp">
                            <img src=" <s:url value="/icons/add.gif"/>" align="right" title="Add Qualification"/>
                        </a>
              <br/><p/>
                        <table width="100%" class="defaulttab1" cellpadding="0" cellspacing="0">
                            <tr><th>Degree</th>
                                <th>University / Institute &amp; Location</th>
                                <th>Specialization</th>
                                <th>Month, Year</th>
                                <th>Percentage(%)</th>
                                <th>Division</th>
                                <th>Edit</th>
                                <th>Delete</th></tr>

                        <s:iterator value="academicListList" var="ProfileAcademic">
                            <tr>
                                <td><s:property value="degree"/></td>
                                <td><s:property value="university"/>, <s:property value="location"/></td>
                                <td><s:property value="fstudy"/></td>
                                <td align="center"><s:property value="pyear"/></td>
                                <td align="center"><s:property value="percentage"/></td>
                                <td align="center"><s:property value="division"/></td>
                                <td align="center"><a href="editAcademic?academicInfoId=<s:property value="academicInfoId"/>">
                                        <img src="../icons/edit.gif" title="Edit Record"/>
                                    </a></td>
                                <td align="center"><a href="deleteAcademic?academicInfoId=<s:property value="academicInfoId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                        <img src="../icons/delete.gif" title="Delete Record"/>
                                    </a></td>
                            </tr>
                        </s:iterator>
                    </table>
                    <br/><br/><br/>                    
                </div>
                     <s:include value="/Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
                     <s:include value="/Footer.jsp"/>
    </body>
</html>
