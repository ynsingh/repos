<%-- 
    Document   : ManageCommitteeMaster
    Created on : 13 Oct, 2011, 07:47 AM
    Author     : sknaqvi
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <p align="center"><s:label value="WORKFLOW MASTER" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>
                <s:form name="frmWorkFlowMaster" action="SaveWorkFlowMasterAction">
                    <s:hidden name="wfm.wfmId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>
                                    <br>
                                    <s:select label="Institution" name="wfm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                        onchange="getAllSubinstitutionList('SaveWorkFlowMasterAction_wfm_institutionmaster_imId', 'SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId');"/>
                                    <s:select label="College/Faculty/School" name="wfm.subinstitutionmaster.simId" headerKey="0" headerValue="All Colleges/Faculties/Schools" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"
                                        onchange="getAllDepartmentList('SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId', 'SaveWorkFlowMasterAction_wfm_departmentmaster_dmId');"/>
                                    <s:select label="Department" name="wfm.departmentmaster.dmId" headerKey="0" headerValue="All Departments" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>
                                    <s:textfield label="Name of the Workflow" name="wfm.wfmName" title="Enter Name of the Committee/Authority"
                                                 required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"/>
                                    <s:select label="Workflow Type" name="wfm.erpmGenMaster.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="egmList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Workflow"  cssClass="textInput"/>

                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Workflow Entries" action="FetchWorkFlowMaster"  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearWorkFlowMasterAction" cssClass="textInput"/>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmWorkflowMasterBrowse">
                 <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                     <display:table name="wfmList" pagesize="15"
                               excludedParams="" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/FetchWorkFlowMaster">
                         <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                         </display:column>
                         
                         <display:column property="institutionmaster.imName" title="Institution Name"
                                    maxLength="100" headerClass="gridheader" 
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                        <display:column property="subinstitutionmaster.simName" title="SubInstitution"
                                    maxLength="120" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column property="departmentmaster.dmName" title="Department"
                                    maxLength="120" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column property="wfmName" title="Work Flow Name" style="width:30%"
                                    maxLength="120" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Work Flow Type" style="width:30%"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>
                       
                         <display:column paramId="wfmId" paramProperty="wfmId"
                                    href="/pico/Administration/EditWorkFlowMasterAction.action"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>
                    <display:column paramId="wfmId" paramProperty="wfmId"
                                    href="/pico/Administration/DeleteWorkFlowMasterAction.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>

                    <display:column paramId="wfmId" paramProperty="wfmId"
                                    href="/pico/Administration/AddWorkFlowDetail.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Add Workflow Details">
                                    Add Workflow Details
                    </display:column>

                </display:table>
                </td></tr>
                </table>
                 <br>
             </s:form>
             </div> 
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>