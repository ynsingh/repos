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

        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">

        <sx:head/>
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
                <br><br>

                <p align="center"><s:label value="SUPPLIER MASTER" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>


                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>


                <s:form name="frmSupplier" action="SaveSupplier" theme="qxhtml" >

                    <s:hidden name="erpmsm.smId" />
                    <s:hidden name="supad.supAdId" />
                    <s:hidden name="SMID" />

                    <s:select label="Institution"  name="erpmsm.institutionmaster.imId" headerKey="" cssClass="queryInput"
                              headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" title = "Select Institution">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:select>

                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50" title = "Enter Supplier's/Company's Name"
                                 label="Supplier Name" name="erpmsm.smName" title="Supplier Name" cssClass="queryInput">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield >

                    <s:label value="...." cssClass="tdSpace"/>

                    <s:textfield requiredposition="left" maxLength="50" size="40" labelposition="left" title = "Enter CEO/Proprietor's Name"
                                 label="CEO's Name" name="erpmsm.smCeoOrProprietorName" title="">
                        <s:param name="labelcolspan" value="%{1}"  />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:select label="Supplier Type" name="erpmsm.erpmGenMasterBySmSupplierType.erpmgmEgmId" cssClass="queryInput"
                              headerKey="" headerValue="-- Please Select --" list="gmIdList" listKey="erpmgmEgmId" title = "Select Supplier Type from the List"
                              listValue="erpmgmEgmDesc" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:select label="Ownership Type" name="erpmsm.erpmGenMasterBySmOwnershipType.erpmgmEgmId" headerKey="" cssClass="queryInput"
                              headerValue="-- Please Select --" list="gmIdList1" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" title = "Select Ownership Type from the List">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:textfield required="" requiredposition="left" maxLength="10" size="10" title="Date of Establishment [DD-MM-YYYY]"
                                 label="Date of Estab" name="estDate" tooltip="Date of Establishment [MM-DD-YYYY]"  >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield name="regDate" label="Reg Date"  maxLength="10" size="10"  tooltip="Registration Date of the Supplier" title = "Enter Date of Registration [DD-MM-YYYY]">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textarea required="" requiredposition="left" label="Deals with" name="erpmsm.smDealsWith" title=""  rows="2" cols="130" title = "Enter Keywords describing Supplier's Operations">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textarea >

                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                 label="PAN No." name="erpmsm.smPanNo" title="Enter Supplier's PAN No">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                 label="TIN No." name="erpmsm.smTanNo" title="Enter Supplier's TIN No">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" maxLength="10" size="40"
                                 label="State VAT Reg No" name="erpmsm.smStateStVatRgnNo" title="Enter Supplier's State VAT Registration Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" maxLength="10" size="40"
                                 label="Central VAT Reg No" name="erpmsm.smCenStVatRgnNo" title="Enter Supplier's Central VAT Registration Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                  label="Entreprenure No" name="erpmsm.smEntreprenureMemNo" title="Enter Supplier's Entreprenure Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                 label="ECC Code" name="erpmsm.smEccCode" title="Enter Supplier's ECC Code">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                 label="ED Reg No" name="erpmsm.smEdRgnNo" title="Enter Supplier's Excise Duty Registration Number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textfield>

                    <s:textarea required="" rows="2" cols="130" cssClass="textInput"
                                 label="Remarks" name="erpmsm.smRemarks" title="Enter any remark about supplier">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textarea>

                    <s:textfield required="" maxLength="50" size="40" title = "Enter Primary Contact Address Line-1"
                                 label="Address" name="supad.adLine1" tooltip="Primary Contact Address Line-1" title="">
                        <s:param name="labelcolspan" value="%{1}" />
                         <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" maxLength="50" size="40" title = "Enter Primary Contact Address Line-2"
                                 label="Line-2" tooltip="Primary Contact Address Line-2" name="supad.adLine2" title="" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" maxLength="50" size="40" title = "Enter Supplier's Primary Contact Address City"
                                 label="City" name="supad.adCity" title="" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textfield>

                    <s:select label="Country" required="true" name="supad.countrymaster.countryId"  cssClass="queryInput" title = "Select Supplier's Primary Address Country"
                              headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"  value="defaultCountry"
                              onchange="getStateList('SaveSupplier_supad_countrymaster_countryId','SaveSupplier_supad_statemaster_stateId')">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:select>

                    <s:select label="State" name="supad.statemaster.stateId" cssClass="queryInput" title = "Enter Supplier's Primary Address State"
                              headerKey="" headerValue="-- Please Select --" list="stList" listKey="stateId" listValue="stateName">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:select>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Phone no." name="supad.adPhn" title = "Enter Primary Telephonic Contact Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Mob no." name="supad.adMob" title="Enter Supplier's Primary Mobile number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Fax no." name="supad.adFaxn" title="Enter Supplier's Primary FAX Number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Email id" name="supad.adEmail" title = "Enter Supplier's Primary EMail Address" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <tr><td> &nbsp; </td></tr>

                    <s:label />

                    <s:submit name="btnSubmit" value="Save Supplier"  >
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                    </s:submit>


                    <s:label value="..." cssClass="tdSpace" />
                    <s:submit name="btnSubmit" value="Browse Suppliers" action="BrowseSupplier"  cssClass="inputButton">
                        <s:param name="colspan" value="%{5}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace" />
                    <s:submit name="btnSubmit" value="Clear Entry Form" action="ManageSupplier">
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace" />
                    <s:submit name="btnSubmit" value="Export Suppliers' Data"  cssClass="inputButton" action="ExportSuppliers">
                        <s:param name="colspan" value="%{5}" />
                        <s:param name="align" value="left" />
                    </s:submit>
                <tr><td> &nbsp; </td></tr>
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


