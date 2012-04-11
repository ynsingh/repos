<%-- 
    Document   : ManageWorkflowActions
    Created on : 11 Jan, 2012, 11:24:11 AM
    Author     : Tanvir Ahmed
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
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tahmed@jmi.ac.in">
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
                <p align="center"><s:label value="WORKFLOW DETAIL ACTIONS" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>
                <s:form name="frmWorkFlowMaster" action="SaveWorkFlowActions">
                    <s:hidden name="wfdtl.wfdId" />
                    <s:hidden name="wfactn.wfaId" />

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

                                        <s:textfield label="Workflow Step" name="wfdtl.wfdStage" size="100"  cssClass="textInputRO" readonly="true"/>
                                        <s:textfield label="Source Committee/Authority" name="wfdtl.committeemasterByWfdSourceCommittee.committeeName" size="100"  cssClass="textInputRO" readonly="true"/>
                                        <s:textfield label="Source Committee/Authority" name="wfdtl.committeemasterByWfdDestinationCommittee.committeeName" size="100"  cssClass="textInputRO" readonly="true"/>


                                        <s:label value="Select Actions Committee/Authority can take"  />

                                        <s:select label="Work Flow Action"  name="wfactn.erpmGenMaster.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="WfaActionsList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Workflow Actions" action="SaveWorkFlowActions"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Workflow Actions" action="FetchWorkFlowActions"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearWorkFlowAction" />
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmWorkFlowActionBrowse">
                 <table width="50%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>

                     <display:table name="wfactnList" pagesize="15"
                               excludedParams="" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/FetchWorkFlowActions">

                         <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                         </display:column>

                        <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Committee/Authority Action"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column paramId="wfaId" paramProperty="wfaId"
                                        href="/pico/Administration/DeleteWorkFlowAction.action"
                                        headerClass="gridheader" class="griddata" media="html" title="Delete">
                                        Delete WF Action
                        </display:column>

                        <display:column paramId="wfaId" paramProperty="wfaId"
                                        href="/pico/Administration/EditWorkFlowAction.action"
                                        headerClass="gridheader" class="griddata" media="html" title="Edit">
                                        Edit WF Action
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