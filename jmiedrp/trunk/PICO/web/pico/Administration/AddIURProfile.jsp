<%--
    Document   : ManageCapitalCategory
    Created on : 19 Feb, 2011, 23:00:20 PM
    Author     : sknaqvi
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">INSTITUTION USER ROLE PRIVILEGES MANAGEMENT</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmIURPEdit" action="SaveIURP">
                    <s:hidden name="iur.iurId"/>
                    <s:hidden name="irp.iupId"/>
                <%--    <s:hidden name ="irp.institutionuserroles.iurId" /> --%>
                

                <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tr>
                            <td>
                                <s:select label="Module Name" name="irp.erpmmodule.erpmmId" headerKey="" headerValue="-- Please Select --" list="erpmmList" listKey="erpmmId" listValue="erpmmName" 
                                         onchange="getProgramList('SaveIURP_irp_erpmmodule_erpmmId', 'SaveIURP_irp_erpmprogram_erpmpId', 'SaveIURP_iur_iurId');"/>
                                
                                <s:select label="Program Name" name="irp.erpmprogram.erpmpId" headerKey="" headerValue="-- Please Select --" list="erpmpList" listKey="erpmpId" listValue="erpmpDisplayName"/>
                               
                                <s:textfield cssClass="textInputRO" label="Role Name" name="iur.iurName" maxLength="100" size="100"  readonly="true"/>
                                <s:label  cssClass="tdLabel" value="Privileges"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Can Add" name="irp.iupCanAdd" labelposition="right"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Can Edit" name="irp.iupCanEdit" labelposition="right"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Can Delete" name="irp.iupCanDelete"  labelposition="right"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Can View" name="irp.iupCanView" labelposition="right"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:submit theme="simple" name="btnSubmit" value="Save Role Privileges" action="SaveIURP"/>
                            </td>
                        </tr>

                </table>
                </s:form>
                <br>
                </div>
                &nbsp;
            </div>

            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
    </body>
</html>
