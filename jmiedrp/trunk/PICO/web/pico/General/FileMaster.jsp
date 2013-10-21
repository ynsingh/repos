<%-- 
    Document   : FileMaster
    Created on : 7 Mar, 2012, 11:45:01 AM
    Author     : sknaqvi
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
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
            <div id ="mainContent">

            <br><br>

                <p align="center"><s:label value="FILE TRACKING" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{7}" />
                </s:bean>

        <s:form name="FrmFileMaster" action="SaveFileMaster" theme="qxhtml">
                <s:hidden name ="fm.fileId" />

                 <s:select label="Institution"  name="fm.institutionmaster.imId" cssClass="queryInput" value="defaultInsitute"
                           headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" title = "Select Institution"
                           onchange="getSubinstitutionAndEmployeeList('SaveFileMaster_fm_institutionmaster_imId',
                                                                      'SaveFileMaster_fm_subinstitutionmaster_simId',
                                                                      'SaveFileMaster_fm_employeemaster_empId');" cssClass="textInput" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                <s:label value="...." cssClass="tdSpace"/>

                <s:select label="College/Faculty/School" name="fm.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --"
                          list="simList" listKey="simId" listValue="simName" cssClass="textInput" value= "defaultSubInstitute"
                          onchange="getDepartmentList('SaveFileMaster_fm_subinstitutionmaster_simId', 'SaveFileMaster_fm_departmentmaster_dmId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                </s:select>                 

                <s:select cssClass="textInput" label="Department Name" name="fm.departmentmaster.dmId"
                          headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="defaultDepartment"
                          ondblclick="getDepartmentAfterValidation('SaveIndent_erpmindtmast_departmentmaster_dmId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                <s:label value="...." cssClass="tdSpace"/>

                <s:select cssClass="textInput" label="Signatory Name" name="fm.employeemaster.empId" value="defaultEmployee"
                          headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:select label="File Type"  name="fm.erpmGenMaster.erpmgmEgmId" cssClass="queryInput" value="defaultFileType"
                           headerKey="" headerValue="-- Please Select --" list="fileTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           title = "Select File Type">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:label value="...." cssClass="tdSpace"/>

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="10" size="4"
                              label="Pages in File" name="fm.filePages" required="yes" title="No. of Pages in the File" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:textfield>

                 <s:textarea cssClass="textInput" requiredposition="left" maxLength="200" cols="100" rows="2"
                             label="Subject" name="fm.fileSubject" required="yes" title="Enter File Subject" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{6}" />
                 </s:textarea >

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="10" size="10"
                              title="File Despatch Date [MM-DD-YYYY]" disabled="true"
                              label="Date of despatch" name="fileDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="10" size="10"
                              title="File Ref No " disabled="false"
                              label="File Ref No " name="fm.fileNumber" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:select cssClass="textInput" label="Is Confidential" name="confidential"
                           list="%{#{'0':'No','1':'Yes'}}" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{6}" />
                 </s:select>



                 <%--                              

                 <s:select cssClass="queryInput" label="Item Name" name="itemrate.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                           list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List"
                           onchange="getsupplierforInsituteList('SaveItemRate_itemrate_institutionmaster_imId', 'SaveItemRate_itemrate_suppliermaster_smId');"
                           ondblclick="getitemList('SaveIndentRate_itemrate_erpmItemMaster_erpmimId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:label value="...." cssClass="tdSpace"/>

                 <s:select cssClass="queryInput" label="Supplier Name" name="itemrate.suppliermaster.smId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                           ondblclick="getsupplieraftervalidation('SaveIndentRate_itemrate_suppliermaster_smId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:select cssClass="queryInput" label="Currency of Purchase" name="itemrate.erpmGenMasterByIrCurrencyId.erpmgmEgmId" title="Enter Currency of Purchase"
                           headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getCurrencyAfterValidation('SaveItemRate_itemrate_erpmGenMasterByIrCurrencyId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="11" size="25"
                              label="Unit Rate" name="itemrate.irdRate" required="yes" title="Enter Unit Rate" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" requiredposition="left" maxLength="10" size="10" title="Enter Approval Effective From Date [MM-DD-YYYY]"
                              label="Approval Effective From Date" name="effDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" requiredposition="left" maxLength="10" size="10" title="Enter last date of approval validity [MM-DD-YYYY]"
                              label="Approval Valid Upto Date" name="validUptoDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="2" size="10" title="Enter warranty duration in months"
                              label="Warranty Duration(Months)" name="itemrate.irWarrantyMonths" value="12"  onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>


                 <s:select cssClass="textInput" label="Warranty Starts From" name="itemrate.erpmGenMasterByIrWarrantyStartsFromId.erpmgmEgmId"
                           headerKey="" headerValue="-- Please Select --" list="wsfList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getWarrantyaftervalidation('SaveIndentRate_itemrate_erpmGenMasterByIrWarrantyStartsFromId_erpmgmEgmId');"
                           title="Select warranty start point">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:textarea cssClass="textInput"  rows="2" cols="90" label="Warranty Description"
                             name="itemrate.irWarrantyClause" title="Enter warranty description">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{7}" />
                 </s:textarea>

                <tr><td> &nbsp; </td></tr>
                <s:label />
--%>
                <s:submit name="btnSubmit" value="Save File" cssClass="inputButton" >
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="btnFetch" value="Fetch Item Rate Details" action="FetchItemRates"  cssClass="inputButton">
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Clear Item Rates"  action="ClearItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Export Item Rates"  action="ExportItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <tr><td> &nbsp; </td></tr>
                <s:label />

    </s:form>
    </div>

    <div id="footer">
        <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
    </div>
    </div>
    </body>
</html>


