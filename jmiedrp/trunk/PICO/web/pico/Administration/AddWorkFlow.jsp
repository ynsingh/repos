<%--
    Document   : AddWorkFlowDetail
    Created on : 15 Oct, 2011, 03:23 AM
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
                <p align="center"><s:label value="WORKFLOW DETAIL" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>
                <s:form name="frmWorkFlowMaster" action="SaveWorkFlowDetail">
                    <s:hidden name="wfm.wfmId" />
                    <s:hidden name="wfdtl.wfdId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>
                                    <br>

                                       <%--use where only number is required for input other than number it does not accept value--%>
             <script type='text/javascript'>
     function isNumberKey(evt)
     {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57) )
        return false;
     return true;
     }
     </script>


                                    <%--  <s:textfield name="wfm.wfmId" />
                                    <s:select label="Institution" name="wfm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                        onchange="getSubinstitutionListForWorkFlowMaster('SaveWorkFlowMasterAction_wfm_institutionmaster_imId', 'SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId');"/>
                                    <s:select label="College/Faculty/School" name="wfm.subinstitutionmaster.simId" headerKey="0" headerValue="All Colleges/Faculties/Schools" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"
                                        onchange="getDepartmentListForWorkFlowMaster('SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId', 'SaveWorkFlowMasterAction_wfm_departmentmaster_dmId');"/>
                                    <s:select label="Department" name="wfm.departmentmaster.dmId" headerKey="0" headerValue="All Departments" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>
                                   --%> <s:textfield label="Name of the Workflow" name="wfm.wfmName" disabled="true" size="100" cssClass="textInputRO" readonly="true"/>
                                        <s:textfield label="Workflow Step" name="wfdtl.wfdStage" cssClass="ROtextInput" onkeypress="return isNumberKey(event)"/>
                                        <s:select label="Source Committee/Authority" name="wfdtl.committeemasterByWfdSourceCommittee.committeeId" headerKey="0" headerValue="-- Please Select --" list="scommitteeList" listKey="committeeId" listValue="committeeName" cssClass="textInput"/>
                                        <s:select label="Destination Committee/Authority" name="wfdtl.committeemasterByWfdDestinationCommittee.committeeId" headerKey="0" headerValue="-- Please Select --" list="dcommitteeList" listKey="committeeId" listValue="committeeName" cssClass="textInput"/>

<%--                                        <s:label value="Select Actions Committee/Authority can take"  />

                                        <s:checkbox label="Can Forward" name="wfdtl.wfdForward" cssClass="textInput"/>
                                        <s:checkbox label="Can Send Back" name="wfdtl.wfdSendBack"  cssClass="textInput"/>

                                        <s:checkbox label="Can Recommend Approval" name="wfdtl.wfdRecommendApproval"  cssClass="textInput"/>
                                        <s:checkbox label="Can Recommend Rejection" name="wfdtl.wfdRecommendReject"   cssClass="textInput"/>

                                        <s:checkbox label="Can Approve" name="wfdtl.wfdApprove"  cssClass="textInput"/>
                                        <s:checkbox label="Can Reject" name="wfdtl.wfdReject"  cssClass="textInput"/>

                                        <s:select label="Allowed Action for the Destination Committee " name="wfdtl.wfdAction"  headerKey="0" headerValue="-- Please Select Action --"
                                                    list="#{'F':'Forward the Work',
                                                         'B':'Send back the Work',
                                                         'a':'Recommend Approval of the Work',
                                                         'r':'Recommend Rejection of the Work',
                                                         'A':'Approve the Work',
                                                         'R':'Reject the Work'
                                                    }" value="'F'"  cssClass="textInput" />

                                </td>#{'Y':'Yes','N':'No'}
--%>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Workflow Step"  
                                              onclick="compareCommittees('SaveWorkFlowDetail_wfdtl_committeemasterByWfdSourceCommittee_committeeId',
                                                                         'SaveWorkFlowDetail_wfdtl_committeemasterByWfdDestinationCommittee_committeeId');"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Workflow Entries" action="FetchWorkFlowDetail"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearWorkFlowDetail" />
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmWorkFlowBrowse">
                 <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                     <display:table name="wfdtlList" pagesize="15"
                               excludedParams="" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/FetchWorkFlowMaster">
                         <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                         </display:column>

                         <display:column property="wfdStage" title="Step"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                        <display:column property="committeemasterByWfdSourceCommittee.committeeName" title="Source Committee/Authority"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column property="committeemasterByWfdDestinationCommittee.committeeName" title="Destination Committee/Authority"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>
                        
                    <display:column paramId="wfdId" paramProperty="wfdId"
                                    href="/pico/Administration/DeleteWorkFlowDetail.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete WF Detail
                    </display:column>

                    <display:column paramId="wfdId" paramProperty="wfdId"
                                    href="/pico/Administration/EditWorkFlowDetail.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit">
                                    Edit WF Detail
                    </display:column>

                    <display:column paramId="wfdId" paramProperty="wfdId"
                                    href="/pico/Administration/AddWorkFlowActions.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Add">
                                    Add WF Actions
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