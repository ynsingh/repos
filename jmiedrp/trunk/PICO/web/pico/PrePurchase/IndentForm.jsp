<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="html" uri="/struts-tags"%>

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
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <p align="center"><s:label cssClass="pageHeading" value="INDENT FORM" /></p>
  <%--------------------this is a internal indent request form fill by internal users--------------------%>

                 <s:form name="FrmIndent" action="SaveIndent"  validate="true" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="erpmindtmast.indtIndentId" />
                    <s:hidden name="erpmindtdet.ErpmIndentMaster.indtDetailId" />
                    <sx:datetimepicker name="erpmindtmast.indtIndentDate" label="IndentDate(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
                    <s:select cssClass="textInput" label="Institution" name="erpmindtmast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                              onchange="getSubinstitutionList('SaveIndent_erpmindtmast_institutionmaster_imId', 'SaveIndent_erpmindtmast_subinstitutionmaster_simId');"/>
                    <s:select cssClass="textInput" label="College/Faculty/School" name="erpmindtmast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                              onchange="getDepartmentList('SaveIndent_erpmindtmast_subinstitutionmaster_simId', 'SaveIndent_erpmindtmast_departmentmaster_dmId');"/>
                    <s:select cssClass="textInput" label="Department Name" name="erpmindtmast.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" />
                    <s:select cssClass="textInput" label="Budget Head Type" name="erpmindtmast.budgetheadmaster.bhmId" headerKey="" headerValue="-- Please Select --"
                              list="bhmList" listKey="bhmId" listValue="bhmName" onchange="getAllocatedAmount('SaveIndent_erpmindtmast_budgetheadmaster_bhmId','SaveIndent_AllocatedAmount', 'SaveIndent_erpmindtmast_departmentmaster_dmId')"/>
                    <s:select cssClass="textInput" label="Currency of Purchase" name="erpmindtmast.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>
                    <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                    label="Amout Allocated" name="AllocatedAmount" title="" readonly="true"/>
                    <p align="center"><s:label cssClass="tdLabel" value="Amout Available... "/></p>
                    <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Indent Prepared  By" name="erpmindtmast.indtGeneratedBy" title="" />
                    <s:select cssClass="textInput" label="Forwarded To" name="erpmindtmast.erpmusersByIndtForwardedToUserId.erpmuId" headerKey="" headerValue="-- Please Select --" list="erpmuserList1" listKey="erpmuId" listValue="erpmuFullName"/>
                    <s:textarea cssClass="textArea"  rows="2" cols="50" label="Remarks" name="erpmindtmast.indtRemarks" title="" />
                   
                    <s:submit cssClass="savebutton"  name="btnSubmit" value="Save and Proceed to Add Items"  />

                    <s:submit  name="btnBrowse" value="Browse Indent"  action="BrowseIndent" cssClass="savebutton" />

                    <s:submit  name="btnClear" value="Clear"   action="ClearIndentMaster" cssClass="savebutton"/>
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


