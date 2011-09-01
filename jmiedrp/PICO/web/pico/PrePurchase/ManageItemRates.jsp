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
                <p align="center"><s:label cssClass="pageHeading" value="MANAGE ITEM RATES" /></p>
  <%--------------------this is a internal indent request form fill by internal users--------------------%>

                 <s:form name="Frmitemrate" action="SaveIndentRate"  validate="true" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="itemrate.irItemRateId" />
                    <s:hidden name="itemratedet.irdItemRateDetailsId" />
                    <s:hidden name="itemratetax.irtItemRateTaxesId" />
                   
                    <s:select cssClass="textInput" label="Institution" name="itemrate.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                    onchange="getItemforInsituteList('SaveIndentRate_itemrate_institutionmaster_imId', 'SaveIndentRate_itemrate_erpmItemMaster_erpmimId');"/>

         <s:select cssClass="textInput" label="Item Name" name="itemrate.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                   list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc"
                   onchange="getsupplierforInsituteList('SaveIndentRate_itemrate_institutionmaster_imId', 'SaveIndentRate_itemrate_suppliermaster_smId');"/>

          <s:select cssClass="textInput" label="Supplier Name" name="itemrate.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"/>

          <s:select cssClass="textInput" label="Currency of Purchase" name="itemrate.erpmGenMasterByIrCurrencyId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" value="defaultCurrency"/>

          <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
                    label="Unit Rate" name="itemratedet.irdRate" title="" />
          <sx:datetimepicker name="itemratedet.irdWefDate" label="With Effect From Date(yyyy-mm-dd)"
                      displayFormat="yyyy-MM-dd" value="%{'today'}"  cssClass="textInput" />
          <sx:datetimepicker name="itemratedet.irdWetDate" label="Valid Upto  Date(yyyy-mm-dd)"
                      displayFormat="yyyy-MM-dd" value="%{'today'}" cssClass="textInput"   />
          <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Warranty Duration(Months)" name="itemrate.irWarrantyMonths" title="" />
          <s:select cssClass="textInput" label="Warranty Starts From" name="itemrate.erpmGenMasterByIrWarrantyStartsFromId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="wsfList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>

          <s:textarea cssClass="textArea"  rows="2" cols="50" label="Warranty Description" name="itemrate.irWarrantyClause" title="" />

         

          <s:submit cssClass="savebutton"  name="btnSubmit" value="Save" />
          <s:submit cssClass="savebutton"  name="btnSubmit" value="Browse Item Rates" action="BrowseitemRatesDetails" />



                       </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


