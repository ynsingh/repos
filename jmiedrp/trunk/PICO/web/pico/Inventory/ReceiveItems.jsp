<%--
    Document   : ReceiveItems
    Created on : 15 May, 2012, 11:01:28 AM
    Author     : Saeed
    I18n By    : Mohd. Manauwar Alam
               : March 2014
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Saeed, Jamia Millia Islamia">
        <meta name="email" content="saeed.coer@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>

            <p align="center" class="pageMessage"><s:property value="message" /></p>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Inventory.ReceiveIssuedItemsFrom')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="FrmReceiveItems" action="SaveAction" theme="qxhtml" >

                        <s:hidden name ="ir.isrId" />



                        <%--use where only number is required for input other than number it does not accept value--%>
                        <script type='text/javascript'>
                            function isNumberKey(evt)
                            {
                                var charCode = (evt.which) ? evt.which : event.keyCode
                                if (charCode > 31 && (charCode < 48 || charCode > 57) )
                                    return false;
                                return true;
                            }
                        </script>
                        <table align="center">
                            <tr><td>
                        <s:select cssClass="textInput" key="Inventory.Institution"  required="true" requiredposition="" name="ir.institutionmaster.imId"
                                  headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                  onchange="getSubinstitutionList('SaveAction_ir_institutionmaster_imId', 'SaveAction_ir_subinstitutionmaster_simId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>


                        <s:select cssClass="textInput" required="true" requiredposition="" key="Inventory.SubInstitution" name="ir.subinstitutionmaster.simId" labelposition="left"
                                  headerKey="" headerValue="-- Please Select --" list="simImList" listKey="simId" listValue="simName"
                                  onchange="getDepartmentList('SaveAction_ir_subinstitutionmaster_simId', 'SaveAction_ir_departmentmaster_dmId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select cssClass="textInput" key="Inventory.Department" required="true" requiredposition="" name="ir.departmentmaster.dmId"
                                  headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"
                                  onchange="getEmployeeListByDmID('SaveAction_ir_departmentmaster_dmId', 'SaveAction_ir_employeemaster_empId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select cssClass="textInput" key="Inventory.EmployeeName" required="true" requiredposition="" name="ir.employeemaster.empId"
                                  headerKey="0" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname"
                                  onchange="getIssueNoByEmpId('SaveAction_ir_employeemaster_empId', 'SaveAction_ir_erpmIssueMaster_ismId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select cssClass="textInput" key="Inventory.Authority"  name="ir.committeemaster.committeeId"
                                  headerKey="0" headerValue="-- Please Select --" list="comList" listKey="committeeId" listValue="committeeName"
                                  onchange="getIssueNoByComId('SaveAction_ir_committeemaster_committeeId', 'SaveAction_ir_erpmIssueMaster_ismId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.ReceiptNo" required="true"  name="ir.isrReceiptNo" title="">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield key="Inventory.ReceiptDate" required="true" requiredposition="" maxLength="40" size="20"
                                     name="receiptDate" title="Enter Order"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>
                        <%-- <sx:datetimepicker name="ir.isrReceiptDate" label="Receip Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>--%>


                        <s:select cssClass="textInput" key="Inventory.IssueNo" name="ir.erpmIssueMaster.ismId"
                                required="true" requiredposition=""  headerKey="" headerValue="-- Please Select --" list="isueList" listKey="ismId" listValue="ismIssueNo">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                         </td></tr>

                            <tr><td align="center">

                                <s:submit theme="simple" key="Inventory.Save">
                                    <s:param name="colspan" value="%{2}" />
                                    <s:param name="align" value="left" />
                                </s:submit>

                        

                                <s:submit theme="simple" name="btnSubmit" key="Inventory.Browse" requiredposition="centre" action="BrowseAction">
                                    <s:param name="colspan" value="%{5}" />
                                    <s:param name="align" value="left" />
                                </s:submit>


                                <s:submit theme="simple" key="Inventory.Clear" action="Clear">
                                    <s:param name="colspan" value="%{2}" />
                                    <s:param name="align" value="left" />
                                </s:submit>

                                <s:submit theme="simple" name="showGFRreport"  key="Inventory.ShowGFR" action="showGFRreportReceive" disabled="varShowGFR" />

                            </td></tr>
                        
                        </table>
                    </s:form>
                    <br>
                </div>
               
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>



