<%--
    Document   : FileMaster
    Created on : 8 Mar, 2012, 11:45:01 AM
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

        <s:form name="FrmFileMaster" theme="qxhtml">     
            
                <s:select label="Institution"  name="fm_new.institutionmaster.imId" cssClass="queryInput" value="defaultInsitute" disabled="true"
                           headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" title = "Select Institution"
                           onchange="getSubinstitutionAndEmployeeList('SaveFileMaster_fm_institutionmaster_imId',
                                                                      'SaveFileMaster_fm_subinstitutionmaster_simId',
                                                                      'SaveFileMaster_fm_employeemaster_empId');" cssClass="textInput" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                <s:label value="...." cssClass="tdSpace"/>

                <s:select label="College/Faculty/School" name="fm_new.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --"
                          list="simList" listKey="simId" listValue="simName" cssClass="textInput" value= "defaultSubInstitute" disabled="true"
                          onchange="getDepartmentList('SaveFileMaster_fm_subinstitutionmaster_simId', 'SaveFileMaster_fm_departmentmaster_dmId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                </s:select>

                <s:select cssClass="textInput" label="Department Name" name="fm_new.departmentmaster.dmId" disabled="true"
                          headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="defaultDepartment"
                          ondblclick="getDepartmentAfterValidation('SaveIndent_erpmindtmast_departmentmaster_dmId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                <s:label value="...." cssClass="tdSpace"/>

                <s:select cssClass="textInput" label="Signatory Name" name="fm_new.employeemaster.empId" value="defaultEmployee" disabled="true"
                          headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:select label="File Type"  name="fm_new.erpmGenMaster.erpmgmEgmId" cssClass="queryInput" value="defaultFileType" disabled="true"
                           headerKey="" headerValue="-- Please Select --" list="fileTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           title = "Select File Type">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:label value="...." cssClass="tdSpace"/>

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="10" size="4" disabled="true"
                              label="Pages in File" name="fm_new.filePages" required="yes" title="No. of Pages in the File" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:textfield>

                 <s:textarea cssClass="textInput" requiredposition="left" maxLength="200" cols="100" rows="2" disabled="true"
                             label="Subject" name="fm_new.fileSubject" required="yes" title="Enter File Subject" >
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
                              title="File Ref No " disabled="true"
                              label="File Ref No " name="fm_new.fileNumber" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:select cssClass="textInput" label="Is Confidential" name="confidential"
                           list="%{#{'0':'No','1':'Yes'}}" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{6}" />
                 </s:select>
<%-- ========================================================================================================================>
                            The Part Below Handle Despatch UI
 ========================================================================================================================--%>

            <s:label value="Despatch Details" cssClass="pageHeading"/>
            <s:iterator value="{1,1,1,1,1,1}">
                     <s:label value="...." cssClass="tdSpace"/>
            </s:iterator>          

            <s:hidden name = "fd.fileMaster.fileId" value="%{fm_new.fileId}"/>

            <s:select label="Institution Addressed"  name="fd.institutionmaster.imId" cssClass="queryInput" value="defaultInsitute"
                           headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" title = "Select Institution"
                           onchange="getSubinstitutionAndEmployeeList('DespatchFile_fd_institutionmaster_imId',
                                                                      'DespatchFile_fd_subinstitutionmaster_simId',
                                                                      'DespatchFile_fd_employeemaster_empId');" cssClass="textInput" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:label value="...." cssClass="tdSpace"/>

            <s:select label="College/Faculty/School Addressed" name="fd.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --"
                          list="simList" listKey="simId" listValue="simName" cssClass="textInput" value= "defaultSubInstitute"
                          onchange="getDepartmentList('DespatchFile_fd_subinstitutionmaster_simId', 'DespatchFile_fd_departmentmaster_dmId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Department Addressed" name="fd.departmentmaster.dmId"
                          headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="defaultDepartment">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
            </s:select>
            
            <s:label value="...." cssClass="tdSpace"/>

            <s:select cssClass="textInput" label="Official Addressed" name="fd.employeemaster.empId" value="defaultEmployee"
                      headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname">
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{1}" />
            </s:select>

            <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="30"
                              title="Designation of the person addressed"
                              name="fd.fdDesignationAddressed" >
                              <s:param name="labelcolspan" value="%{0}" />
                              <s:param name="inputcolspan" value="%{1}" />
            </s:textfield>


           <s:textarea cssClass="textInput" label="Public Comments" name="fd.fdPublicRemarks" value=""
                      rows="5" cols="50">
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textarea>

            <s:label value="...." cssClass="tdSpace"/>
            
           <s:textarea cssClass="textInput" label="Private Comments" name="fd.fdPrivateRemarks" value=""
                      rows="5" cols="50">
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{2}" />
           </s:textarea>

           <s:select cssClass="textInput" label="File Action" name="fd.fdAction"
                      list="%{#{'F':'Forward','C':'Close'}}" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
           </s:select>

           <s:textfield cssClass="textInput" requiredposition="left" maxLength="10" size="10"
                        title="File Despatch Date [MM-DD-YYYY]" value="%{fileDate}"
                        label="Despatch Date" name="fileDate" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
           </s:textfield>

                <s:label value="...." cssClass="tdSpace"/>
                
                <s:submit name="btnSubmit" value="Despatch File" action="SaveDespatchFile" cssClass="inputButton" >
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


