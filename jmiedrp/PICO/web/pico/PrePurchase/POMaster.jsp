<%--
    Document   : Purchase Order Master form
    Created on : May  2011, 12:28:32 PM
    Author     : Sajid Aziz
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
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <p align="center"><s:label cssClass="pageHeading" value="MANAGE PO MASTER" /></p>
                 <%--------------------this is a Purchase Order  form --------------------%>

                  <s:actionerror />

    <s:form name="Frmpomaster" action="SavePOMaster" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="pomaster.pomPoMasterId" />
                                    
    <s:select cssClass="textInput" label="Institution"  required="true" name="pomaster.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
              onchange="getSubinstitutionList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_subinstitutionmaster_simId');"/>
    <s:select cssClass="textInput" label="College/Faculty/School" name="pomaster.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                   onchange="getDepartmentList('SavePOMaster_pomaster_subinstitutionmaster_simId', 'SavePOMaster_pomaster_departmentmaster_dmId');"/>
    <s:select cssClass="textInput" label="Department Name" name="pomaster.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="DefaultDepartment"
                   onchange="getsupplierforInsituteList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_suppliermaster_smId');"/>
    <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" label="PO: NO" name="pomaster.pomPoNo" title="" />
    <sx:datetimepicker name="pomaster.pomPoDate" label="PO Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
    <s:select cssClass="textInput" label="Supplier Name" name="pomaster.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"/>
    <s:select cssClass="textInput" label="Currency of Purchase" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId"  value="defaultCurrency" listValue="erpmgmEgmDesc" />
    <s:select cssClass="textInput" label="Payment Mode " name="pomaster.erpmGenMasterByPomPaymentModeId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="paymodelist" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>
    <s:textarea cssClass="textArea"  rows="2" cols="50" label="Remarks" name="pomaster.pomRemarks" title="" />
    <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
    label="Form ID" name="pomaster.pomDiscount" title="" />
    <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
    label="Form NO" name="pomaster.pomFormNo" title="" />
    <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
     label="Term Days " name="pomaster.pomTermsDays" title="" />
    <s:select cssClass="textInput" label="Approved BY" name="pomaster.erpmusersByPomApprovedById.erpmuId" headerKey="" headerValue="-- Please Select --" list="erpmuserlist" listKey="erpmuId" listValue="erpmuFullName"/>
    <sx:datetimepicker name="pomaster.pomDeliveryDate" label="PO DeliveryDate(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
    <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
    label="Against Reference" name="pomaster.pomAgainstReferenceId" title="" />

  <s:select label="Accompolish"  name="pomaster.pomAccomplished"  list="{'No','Yes'}"  />

  <s:select label="Purchase Mode "  name="pomaster.pomPurchaseMode" list="{'Import','Domestic '}" />

  <s:select  label="PO Cancel" name="pomaster.pomCancelled"   list="{'No','Yes'}" />


          <s:submit cssClass="savebutton"  name="btnSubmit" value="Save and Add Po Details" />
          <s:submit  name="btnBrowse" value="Browse POMaster" action="BrowseIndentPOMaster"  />
</s:form>

            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


