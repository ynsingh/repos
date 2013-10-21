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
            
            <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>
            
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <br><br>

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{9}" />
                </s:bean>
                <%--   ------------------------------  --%>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">SUPPLIER MASTER</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                <%--   ------------------------------   --%>

                <s:form name="frmSupplier" action="SaveSupplier" theme="qxhtml" >

                    <s:hidden name="erpmsm.smId" />
                    <s:hidden name="supad.supAdId" />
                    <s:hidden name="SMID" />
                    <br>
                    <s:label value="..........." cssClass="tdSpace"/>
                    <s:select label="Institution" required="true" requiredposition="" name="erpmsm.institutionmaster.imId" headerKey="" cssClass="queryInput"
                              headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" title = "Select Institution">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:select>

                    <s:label/>
                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50" title = "Enter Supplier's/Company's Name"
                                 label="Supplier Name" name="erpmsm.smName" cssClass="queryInput">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield >

                    <s:label value="...." cssClass="tdSpace"/>

                    <s:textfield requiredposition="left" maxLength="50" size="40" labelposition="left" title = "Enter CEO/Proprietor's Name"
                                 label="CEO's Name" name="erpmsm.smCeoOrProprietorName" >
                        <s:param name="labelcolspan" value="%{1}"  />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
                    <s:select label="Supplier Type" required="true" requiredposition="" name="erpmsm.erpmGenMasterBySmSupplierType.erpmgmEgmId" cssClass="queryInput"
                              headerKey="" headerValue="-- Please Select --" list="gmIdList" listKey="erpmgmEgmId" title = "Select Supplier Type from the List"
                              listValue="erpmgmEgmDesc" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:select label="Ownership Type" required="true" requiredposition="" name="erpmsm.erpmGenMasterBySmOwnershipType.erpmgmEgmId" headerKey="" cssClass="queryInput"
                              headerValue="-- Please Select --" list="gmIdList1" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" title = "Select Ownership Type from the List">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:label/>
                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10" title="Date of Establishment [DD-MM-YYYY]"
                                 label="Date of Estab" name="estDate" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="true" requiredposition="" name="regDate" label="Reg Date"  maxLength="10" size="10"  title = "Enter Date of Registration [DD-MM-YYYY]">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
                    <s:textarea required="" requiredposition="left" label="Deals with" name="erpmsm.smDealsWith" rows="2" cols="130" title = "Enter Keywords describing Supplier's Operations">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textarea >

                    <s:label/>
                    <s:textfield required="true" requiredposition="left" maxLength="10" size="40"
                                 label="PAN No." name="erpmsm.smPanNo" title="Enter Supplier's PAN No">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="true"  requiredposition="left" maxLength="11" size="40"
                                 label="VAT TIN No." name="erpmsm.smTanNo" title="Enter Supplier's VAT TIN No">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
                    <s:textfield required="" maxLength="15" size="40"
                                 label="Service Tax No" name="erpmsm.smStateStVatRgnNo" title="Enter Supplier's Service Tax Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" maxLength="11" size="40"
                                 label="CST No" name="erpmsm.smCenStVatRgnNo" title="Enter Supplier's CST Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
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

                    <s:label/>
                    <s:textfield required="" requiredposition="left" maxLength="10" size="40"
                                 label="ED Reg No" name="erpmsm.smEdRgnNo" title="Enter Supplier's Excise Duty Registration Number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textfield>

                    <s:label/>
                    <s:textarea required="" rows="2" cols="130" cssClass="textInput"
                                 label="Remarks" name="erpmsm.smRemarks" title="Enter any remark about supplier">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textarea>                    
                    
                    <s:label/>
                    <s:textfield required="true" requiredposition="" maxLength="50" size="40" title = "Enter Primary Contact Address Line-1"
                                 label="Address" name="supad.adLine1">
                        <s:param name="labelcolspan" value="%{1}" />
                         <s:param name="inputcolspan" value="%{7}" />
                    </s:textfield>

                    <s:label/>
                    <s:textfield required="" maxLength="50" size="40" title = "Enter Primary Contact Address Line-2"
                                 label="Line-2"  name="supad.adLine2"  >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="true" requiredposition="" maxLength="50" size="40" title = "Enter Supplier's Primary Contact Address City"
                                 label="City" name="supad.adCity" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
                    <s:select label="State" required="true" requiredposition=""  name="supad.statemaster.stateId" cssClass="queryInput" title = "Enter Supplier's Primary Address State"
                              headerKey="" headerValue="-- Please Select --" list="stList" listKey="stateId" listValue="stateName">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>
                    
                    <s:select label="Country" required="true" requiredposition="" name="supad.countrymaster.countryId"  cssClass="queryInput" title = "Select Supplier's Primary Address Country"
                              headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"  value="defaultCountry"
                              onchange="getStateList('SaveSupplier_supad_countrymaster_countryId','SaveSupplier_supad_statemaster_stateId')">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:label/>
                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Phone no." name="supad.adPhn" title = "Enter Primary Telephonic Contact Number">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Mobile No." name="supad.adMob" title="Enter Supplier's Primary Mobile number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label/>
                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Fax No." name="supad.adFaxn" title="Enter Supplier's Primary FAX Number" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:textfield required="" requiredposition="left" maxLength="50" size="40"
                                 label="Email Id" name="supad.adEmail" title = "Enter Supplier's Primary EMail Address" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <tr><td> &nbsp; </td></tr>

                    <s:label />
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

                    <s:label />
                    <s:label value="..." cssClass="tdSpace" />
                    <s:submit name="btnSubmit" value="Clear Entry Form" action="ManageSupplier">
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace" />
                    <s:submit name="btnSubmit" value="Print Suppliers' Data"  cssClass="inputButton" action="ExportSuppliers">
                        <s:param name="colspan" value="%{5}" />
                        <s:param name="align" value="left" />
                    </s:submit>
                      <tr><td>
                    <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportSupplier" disabled="varShowGFR" >
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                    </s:submit>
                     </td></tr>
                <tr><td> &nbsp; </td></tr>
                </s:form>
                </div>
                &nbsp;
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


