<%-- 
    Document   : CertificationInfo
    Created on : Oct 4, 2011, 4:31:14 PM
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
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>
        <div id="container">
            <div class="wrapper">
                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>My Certification</h3>

                    <a href="CertificationInfoAdd.jsp">
                        <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Certificate"/>
                    </a>
                    <br/><p/>
                    <table width="100%" class="defaulttab1" cellpadding="0" cellspacing="0">
                        <tr><th>Certification Name</th>
                            <th>Certification Authority</th>
                            <th>License No.</th>
                            <th>Validity<br/>(From-To)</th>                                
                            <th>Edit&nbsp;&nbsp;Delete</th></tr>

                        <s:iterator value="CertificateList" var="Certificate">
                            <tr valign="middle"> 
                                <td><s:property value="certificationName"/> </td>
                                <td><s:property value="certificationAuthority"/></td>
                                <td><s:property value="license"/></td>
                                <td width="85"><s:property value="certificationDate"/> &nbsp;&nbsp; <s:property value="validDate"/>
                                </td>
                                <td align="center">
                                    <a href="editCertificate?certificationId=<s:property value="certificationId"/>">
                                        <img src="../icons/edit.gif" title="Edit Record"/>
                                    </a>&nbsp;
                                    <a href="deleteCertificate?certificationId=<s:property value="certificationId"/>" onclick="return confirm('Are you sure you want to delete this record')">
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
