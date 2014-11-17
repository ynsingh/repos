<%--  
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="html" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
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
                <div id ="mainContent" align="left">
                    <br><br>         
                <div align="right" style="margin-right: 10px">
		<a href="" onclick="window.open('Help','name','width=600,height=400,scrollbars=1,resizable=0')">HELP</a>
                </div>
                <s:actionerror/>
                <s:property value="message" />         
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style="background-color: #215dc6;">
                    <p align="left" class="pageHeading" style="color:  #ffffff"><s:property value="getText('PrePurchase.Step1of3CreateIndent')" /></p>
                    <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                </div>
                <div style="border: solid 1px #000000; background:  gainsboro">

                    <s:form name="FrmIndent" action="SaveIndent" theme="qxhtml" >

                        <s:hidden name ="erpmindtmast.indtIndentId" />
                        <s:hidden name="erpmindtdet.ErpmIndentMaster.indtDetailId" />        

                        <s:if test="btnEditEnabled==true">
                        <tr> <td> &nbsp;
                            <s:select cssClass="textInput" key="PrePurchase.Institution" required="true" name="erpmindtmast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                                      requiredposition=""      onchange="getSubinstitutionList('SaveIndent_erpmindtmast_institutionmaster_imId', 'SaveIndent_erpmindtmast_subinstitutionmaster_simId');" ondblclick="getInsituteaftervalidation('SaveIndent_erpmindtmast_institutionmaster_imId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield required="true" requiredposition="left" maxLength="10" size="10" title="Indent Date [DD-MM-YYYY]"
                                         key="PrePurchase.IndentDate" name="indentDate" >
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield>

                            <s:select cssClass="textInput" key="PrePurchase.SubInstitution" required="true" requiredposition="" name="erpmindtmast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                      onchange="getDepartmentList('SaveIndent_erpmindtmast_subinstitutionmaster_simId', 'SaveIndent_erpmindtmast_departmentmaster_dmId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:select cssClass="textInput" key="PrePurchase.Department" required="true" name="erpmindtmast.departmentmaster.dmId" 
                                      requiredposition=""      headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"
                                      value="DefaultDepartment"  ondblclick="getDepartmentAfterValidation('SaveIndent_erpmindtmast_departmentmaster_dmId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield  requiredposition="left" required="true" maxLength="100" size="100" title="Indent Title"
                                         key="PrePurchase.IndentTitle" name="erpmindtmast.indtTitle" >
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textfield>

                            <s:select cssClass="textInput" key="PrePurchase.CurrencyOfPurchase" required="true" requiredposition="" name="erpmindtmast.erpmGenMasterByIndtCurrencyId.erpmgmEgmId"
                                      headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  value="defaultCurrency"
                                      ondblclick="getCurrencyAfterValidation('SaveIndent_erpmindtmast_erpmGenMasterByIndtCurrencyId_erpmgmEgmId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:select cssClass="textInput" key="PrePurchase.BudgetHeadType" required="true" requiredposition="" name="erpmindtmast.budgetheadmaster.bhmId" headerKey="" headerValue="-- Please Select --"
                                      list="bhmList" listKey="bhmId" listValue="bhmName" onchange="getAllocatedAmount('SaveIndent_erpmindtmast_budgetheadmaster_bhmId','SaveIndent_AllocatedAmount', 'SaveIndent_erpmindtmast_departmentmaster_dmId')"
                                      ondblclick="getBudgetAfterValidation('SaveIndent_erpmindtmast_budgetheadmaster_bhmId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                                         key="PrePurchase.AmountAllocated" name="AllocatedAmount" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield >

                            <s:textfield  requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                                          key="PrePurchase.AmountAvailable" name="AllocatedAmount" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield >

                            <s:textfield  cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                                          key="PrePurchase.IndentSignatory" name="erpmindtmast.indtGeneratedBy" title="">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textfield >

                            <s:textarea   cssClass="textArea"  rows="2" cols="100" key="PrePurchase.Remarks" name="erpmindtmast.indtRemarks" title="">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textarea>
                        </s:if>
                        <s:else>
                            <s:select cssClass="textInputRO" key="PrePurchase.Institution" name="erpmindtmast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                                      onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                      onkeydown="alert('Work Flow Cannot be Changed!'); return false;"
                                      onchange="getSubinstitutionList('SaveIndent_erpmindtmast_institutionmaster_imId', 'SaveIndent_erpmindtmast_subinstitutionmaster_simId');" ondblclick="getInsituteaftervalidation('SaveIndent_erpmindtmast_institutionmaster_imId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield required="true" requiredposition="left" maxLength="10" size="10" title="Indent Date [DD-MM-YYYY]" 
                                         key="PrePurchase.IndentDate" name="indentDate"  onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                         onkeydown="alert('Work Flow Cannot be Changed!'); return false;" >
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield>

                            <s:select cssClass="textInput" key = "PrePurchase.SubInstitution" name="erpmindtmast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" 
                                      list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute" 
                                      onchange="getDepartmentList('SaveIndent_erpmindtmast_subinstitutionmaster_simId', 'SaveIndent_erpmindtmast_departmentmaster_dmId');"
                                      onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                      onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:select cssClass="textInput" key="PrePurchase.Department" name="erpmindtmast.departmentmaster.dmId"
                                      headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"
                                      value="DefaultDepartment"  ondblclick="getDepartmentAfterValidation('SaveIndent_erpmindtmast_departmentmaster_dmId');"
                                      onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                      onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield required="true" requiredposition="left" maxLength="100" size="100" title="Indent Title"
                                         key="PrePurchase.IndentTitle" name="erpmindtmast.indtTitle"
                                         onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                         onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textfield>

                            <s:select cssClass="textInput" key="PrePurchase.CurrencyOfPurchase" requiredposition="" name="erpmindtmast.erpmGenMasterByIndtCurrencyId.erpmgmEgmId"
                                      headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  value="defaultCurrency"
                                      ondblclick="getCurrencyAfterValidation('SaveIndent_erpmindtmast_erpmGenMasterByIndtCurrencyId_erpmgmEgmId');"
                                      onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                      onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:select cssClass="textInput" key="PrePurchase.BudgetHeadType"  requiredposition="" name="erpmindtmast.budgetheadmaster.bhmId" headerKey="" headerValue="-- Please Select --" 
                                      list="bhmList" listKey="bhmId" listValue="bhmName" onchange="getAllocatedAmount('SaveIndent_erpmindtmast_budgetheadmaster_bhmId','SaveIndent_AllocatedAmount', 'SaveIndent_erpmindtmast_departmentmaster_dmId')"
                                      ondblclick="getBudgetAfterValidation('SaveIndent_erpmindtmast_budgetheadmaster_bhmId');"
                                      onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                      onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                                         key="PrePurchase.AmountAllocated" name="AllocatedAmount" title="" readonly="true"
                                         onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                         onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield >

                            <s:textfield  requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                                          key="PrePurchase.AmountAvailable" name="AllocatedAmount" title="" readonly="true"
                                          onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                          onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield >

                            <s:textfield  cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                                          key="PrePurchase.IndentSignatory" name="erpmindtmast.indtGeneratedBy" title=""
                                          onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                          onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textfield >

                            <s:textarea   cssClass="textArea"  rows="2" cols="100" key="PrePurchase.Remarks" name="erpmindtmast.indtRemarks" title=""
                                          onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                          onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                            </s:textarea>
                        </s:else>
                        <tr> <td> &nbsp;
                        
                        <s:label />
                        <s:label />

                        <s:submit name="btnSubmit" key="PrePurchase.SaveOrNextStep" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnBrowse" key="PrePurchase.Browse" action="BrowseIndent" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="right" />
                        </s:submit>

                        <s:submit name="btnClear" key="PrePurchase.Clear" action="ManageIndent" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnSubmit" value="Export Pending Indent" action="PendingIndentDetailReport" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnBrowse" value="Print Indent" action="PrintIndents" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="showGFRreport"  key="PrePurchase.ShowGFR" action="showGFRreportIndent" disabled="varShowGFR" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="right" />
                        </s:submit>

                        <s:label/>

                    </s:form>
                </div>
                <br><br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


