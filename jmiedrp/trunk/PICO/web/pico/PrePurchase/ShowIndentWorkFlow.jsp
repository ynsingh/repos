<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript"  type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <%@include  file="../Administration/header.jsp"%>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>


            <div id ="mainContent">
                <br><br>
                <p align="center" class="pageHeading"><s:label value="Indent Workflow" /></p>
                <p  align="center" Class="mymessage" ><s:property value="message"  /> </p>
                <s:actionerror/>

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <s:form name="FrmIndentitems" action="SubmitIndentAction" theme="qxhtml">

                    <s:hidden name ="workFlowTransaction.wftId" />
                    <s:hidden name="workFlowTransaction.committeemasterByWftSourceId.committeeId"  />
                    <s:hidden name="workFlowTransaction.committeemasterByWftDestinationId.committeeId"  />
                    <s:hidden name="userMessage.umId" />
                    <s:hidden name="msgId" />

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                 label="Indent No" name="indentId" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="100" size="100"
                                 label="Indent Title" name="erpmIndentMaster.indtTitle" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{5}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                 label="No of Items in Indent" name="numberOfIndentItems" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="15" size="15"
                                 label="Approx. Indent Value" name="approxIndentValue" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="15" size="15"
                                 label="Currency" name="erpmIndentMaster.erpmGenMasterByIndtCurrencyId.erpmgmEgmDesc" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="50" size="20"
                                 label="Signatory" name="erpmIndentMaster.indtGeneratedBy" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>
                    
                            <tr> <td> &nbsp;

                    <s:submit name="btnSubmit" value="New Indent" action="ManageIndent">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit name="btnSubmit" value="Browse Indents" action="BrowseIndent">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="right" />
                    </s:submit>
                
                </s:form>
            </div>

            <div id ="mainContent">
                <s:if test="workFlowTransactionList.size() > 0">
                    <s:form name="frmViewWorkFlow" theme="simple">

                        <table width="100%" cellspacing="0" cellpadding="0" align="center" >
                            <tr><td colspan="2">
                                <tr> <td> &nbsp;
                                <display:table name="workFlowTransactionList" pagesize="15"
                                                   excludedParams="*"  export="true" cellpadding="0"
                                                   cellspacing="0" summary="false" id="doc"
                                                   requestURI="/PrePurchase/ManageIndent.action">

                                    <display:column  class="griddata" title="SNo" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="committeemasterByWftSourceId.committeeName" title="Source Committee Name"
                                                    maxLength="100" headerClass="gridheader" style="width:20%"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                    sortable="true"/>
                                    <display:column property="committeemasterByWftDestinationId.committeeName" title="Destination Comittee"
                                                    maxLength="100" headerClass="gridheader" style="width:20%"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Action"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata"  sortable="true"/>
                                    <display:column property="wftDate" title="Action"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata"  sortable="true"/>
                                    <display:column property="wftActionRemarks" title="Remarks"
                                                    maxLength="100" headerClass="gridheader" style="width:30%"
                                                    class="griddata"  sortable="true"/>
                                    <s:if test="btnEditEnabled == true">
                                        <display:column    paramId="indentId" paramProperty="workflowmaster.wfmId"
                                                           href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                                           headerClass="gridheader" class="griddata" media="html" title="Delete">
                                                           Edit
                                        </display:column>
                                    </s:if>
                                </display:table>

                                            <br></td></tr>
                        </table>

                    </s:form>
                </s:if>
                <p>&nbsp;</p>
            </div>
    
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
