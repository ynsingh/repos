<%--
    Document   : ManageChallan
    Created on : 3 Jun, 2011, 11:32:07 AM
    Author     : Mohd. Manauwar Alam
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Inventory/Inventory.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="manauwar, Jamia Millia Islamia">
        <meta name="email" content="manauwar.alam@outlook.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color:  #ffffff"><s:property value="getText('Inventory.ManageIssueItems')" /> </p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <%----------------------this is a issue items  form --------------------%>

                <s:actionerror />
                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmIssueItems" action="SaveIssueItems" theme="qxhtml">
                        <table border="0" cellpadding="4" cellspacing="0" align="center">

                            <s:hidden name ="eim.ismId"/>

                            <s:label value="%{getText('Inventory.IssueItemsFrom')}" cssClass="pageSubHeading">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{8}" />
                            </s:label>                                                    

                            <s:select  key="Inventory.Institution"  required="true"  requiredposition="" name="eim.institutionmasterByIsmFromInstituteId.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                      onchange="getSubinstitutionList('SaveIssueItems_eim_institutionmasterByIsmFromInstituteId_imId', 'SaveIssueItems_eim_subinstitutionmasterByIsmFromSubinstituteId_simId');"
                                      onclick= "getSubinstitutionList('SaveIssueItems_eim_institutionmasterByIsmFromInstituteId_imId', 'SaveIssueItems_eim_subinstitutionmasterByIsmFromSubinstituteId_simId');">
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="" cssClass="tdSpace"/>

                            <s:select  key="Inventory.SubInstitution"  required="true" requiredposition="" name="eim.subinstitutionmasterByIsmFromSubinstituteId.simId" headerKey="" headerValue="-- Please Select --" list="subImList" listKey="simId" listValue="simName"
                                      onchange="getDepartmentList('SaveIssueItems_eim_subinstitutionmasterByIsmFromSubinstituteId_simId','SaveIssueItems_eim_departmentmasterByIsmFromDepartmentId_dmId');" >
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:select  key="Inventory.Department" required="true" requiredposition="" name="eim.departmentmasterByIsmFromDepartmentId.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"
                                      onchange="getEmployeeListByDmID('SaveIssueItems_eim_departmentmasterByIsmFromDepartmentId_dmId','SaveIssueItems_eim_employeemasterByIsmFromEmployeeId_empId');">
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="" cssClass="tdSpace"/>

                            <s:select  key="Inventory.IssuingAuthority"  required="true" requiredposition="" name="eim.employeemasterByIsmFromEmployeeId.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname">
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:textfield  maxLength="30" size="20"  key="Inventory.IssueNo" requiredposition="" name="eim.ismIssueNo" required="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:label value="" cssClass="tdSpace"/>

                          <s:textfield  maxLength="20" size="20" key="Inventory.Date" name="issueDate"  title="Enter the date " >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
        
                            <s:textarea  key="Inventory.IssueDescription" maxLength="100" name="eim.ismIssueDesc" cols="80" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textarea>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>


                            <s:label value="%{getText('Inventory.IssueItemsTo')}"  cssClass="pageSubHeading">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{9}" />
                            </s:label>

                            <s:select key="Inventory.Institution" required="true" requiredposition="" name="eim.institutionmasterByIsmToInstituteId.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                      onchange="getSubinstitutionList('SaveIssueItems_eim_institutionmasterByIsmToInstituteId_imId', 'SaveIssueItems_eim_subinstitutionmasterByIsmToSubinstituteId_simId');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:select>

                            <s:select  key="Inventory.SubInstitution"  required="true" requiredposition="" name="eim.subinstitutionmasterByIsmToSubinstituteId.simId" headerKey="" headerValue="-- Please Select --" list="subImList" listKey="simId" listValue="simName"
                                      onchange="getDepartmentList('SaveIssueItems_eim_subinstitutionmasterByIsmToSubinstituteId_simId', 'SaveIssueItems_eim_departmentmasterByIsmToDepartmentId_dmId');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="" cssClass="tdSpace"/>

                            <s:select  key="Inventory.Department" requiredposition=""  name="eim.departmentmasterByIsmToDepartmentId.dmId"
                                      headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" required="true"
                                      onchange="getEmployeeListByDmID('SaveIssueItems_eim_departmentmasterByIsmToDepartmentId_dmId', 'SaveIssueItems_eim_employeemasterByIsmToEmployeeId_empId');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <tr></tr>
                            <tr></tr>
                            <tr></tr>

                            <s:radio key="Inventory.SelectAnyOne"  list="#{'A':'Authority','E':'Employee','S':'Supplier'}"  name="radSelect"   value="radValue"
                                     onchange="makeDisable('SaveIssueItems_eim_employeemasterByIsmToEmployeeId_empId','SaveIssueItems_eim_committeemaster_committeeId','SaveIssueItems_eim_suppliermaster_smId');" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:radio>


                            <s:select  key="Inventory.AuthorityName" name="eim.committeemaster.committeeId" headerKey="-1" headerValue="-- Please Select --" list="cmList" listKey="committeeId" listValue="committeeName" disabled="IssueToAuthority"
                                       onchange="getEmailAddressForCommittee('SaveIssueItems_eim_committeemaster_committeeId','SaveIssueItems_emailAddress');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:select>



                            <s:select  key="Inventory.EmployeeName" name="eim.employeemasterByIsmToEmployeeId.empId" headerKey="-1" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" disabled="IssueToEmployee"
                                       onchange="getEmailAddressForEmployee('SaveIssueItems_eim_employeemasterByIsmToEmployeeId_empId','SaveIssueItems_emailAddress');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                             <s:label value="" cssClass="tdSpace"/>
                             <s:textfield  maxLength="50" size="40"  key="Inventory.EmailAdress" name="emailAddress"  title="Enter the date " >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:select  key="Inventory.SupplierName" name="eim.suppliermaster.smId" headerKey="-1" headerValue="-- Please Select --" list="smList" listKey="smId" listValue="smName" disabled="IssueToSupplier"
                                        ondblclick="getSuppliersName('SaveIssueItems_eim_suppliermaster_smId','SaveIssueItems_emailAddress');"
                                        onchange="getEmailAddressForSupplier('SaveIssueItems_eim_suppliermaster_smId','SaveIssueItems_emailAddress');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:select>


                            <s:select  key="Inventory.AgainstIndentTitle" name="eim.erpmIndentMaster.indtIndentId" headerKey="0" headerValue="-- Please Select --" list="indList" listKey="indtIndentId" listValue="indtTitle" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>
                            <s:label value="" cssClass="tdSpace"/>

                            <s:select  key="Inventory.IssueType"  name="eim.ismIssueType" headerKey="-1" headerValue="-- Please Select --" list="#{'U':'Issue for Use','S':'Issue for Stock Transfer','R':'Issue for Repair/Maintenance','W':'Issue for Write-off'}"   >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>
                            <s:textfield  maxLength="50" size="30"  key="Inventory.ReturnDueDate" name="returnDueDate"  title="Enter the date " >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield>
                            <s:textarea  key="Inventory.Remarks" name="eim.ismRemarks" cols="80" maxLength="200" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textarea>

                            <tr></tr>
                            <tr></tr>
                            <tr>
                                <td align="right">
                                    <s:submit theme="simple" name="btnSubmit" key="Inventory.SaveProceed" />

                                </td>
                                <td>

                                    <s:submit theme="simple" name="bthReset" key="Inventory.Browse" action="BrowseIssueItems"  />
                                    </td><td>
                                    <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" key="Inventory.Clear" onclick="ClearIssueItemsFields();"/>
                                    </td><td>
                                    <s:submit theme="simple" name="bthPrintPO" key="Inventory.Print" action="IssueReport"/>
                                    </td><td>
                                    <s:submit theme="simple" name="showGFRreport"  key="Inventory.ShowGFR" action="showGFRreport" disabled="varShowGFR"/>
                                </td><td>
                                    <s:submit theme="simple" name="bthReset"  key="Inventory.PrintGatePass" action="exportGatePass" />

                                </td>
                            </tr>
                            <tr></tr>
                            <tr></tr>
                            <tr></tr>
                        </table>
                    </s:form>
                </div>
                <br>
            </div>
        

        <div id="footer">
            <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
        </div>

</div>
    </body>
</html>



