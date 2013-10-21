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
                <%-- <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>--%>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            

            <div id ="mainContent">
                <s:actionerror/>
                <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                
                 <div style="background-color: #215dc6;  " >
                     <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;Step 3 of 3 (Submit Indent)</p>
                     <p  align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"  /> </p>
                </div>

                <div style="border: solid 1px #000000; background:  gainsboro">
                <s:form name="FrmIndentitems" action="SubmitIndentAction" theme="qxhtml">

                    <s:hidden name ="workFlowTransaction.wftId" />
                    <s:hidden name="workFlowTransaction.committeemasterByWftSourceId.committeeId"  />
                    <s:hidden name="workFlowTransaction.committeemasterByWftDestinationId.committeeId"  />
                    <s:hidden name="userMessage.umId" />
                    <s:hidden name="msgId" />

                     <s:iterator value="{1,1,1,1,1,1,1,1}">
                     <s:label value="..." cssClass="tdSpace"/>
                     </s:iterator>



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


                    <s:if test="defaultWfmId == null">
                        <s:select   cssClass="textInput" label="Indent Work Flows" name="workFlowTransaction.workflowmaster.wfmId"
                                    headerKey="" headerValue="-- Please Select --"
                                    required="true" list="indentWorkFlowList" listKey="wfmId" listValue="wfmName" value="defaultWfmId"
                                    onchange="getWorkFlowStage('SubmitIndentAction_workFlowTransaction_workflowmaster_wfmId',
                                    'SubmitIndentAction_indentId',
                                    'SubmitIndentAction_workFlowTransaction_wftStage',
                                    'SubmitIndentAction_workFlowTransaction_erpmGenMaster_erpmgmEgmId',
                                    'SubmitIndentAction_workFlowTransaction_committeemasterByWftSourceId_committeeId',
                                    'SubmitIndentAction_sourceCommittee',
                                    'SubmitIndentAction_workFlowTransaction_committeemasterByWftDestinationId_committeeId',
                                    'SubmitIndentAction_destinationCommittee',
                                    'SubmitIndentAction_workFlowTransaction_wftDestinationEmail',
                                    'SubmitIndentAction_btnEditWork');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:select>
                    </s:if>
                    <s:else>
                        <s:select   cssClass="textInput" label="Indent Work Flows" name="workFlowTransaction.workflowmaster.wfmId"
                                    headerKey="" headerValue="-- Please Select --"
                                    required="true" list="indentWorkFlowList" listKey="wfmId" listValue="wfmName" value="defaultWfmId"
                                    onclick="alert('Work Flow Cannot be Changed!'); return false;"
                                    onkeydown="alert('Work Flow Cannot be Changed!'); return false;">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:select>
                    </s:else>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                 label="Current Workflow Stage" name="workFlowTransaction.wftStage" title="" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label />
                    <s:label />
                    <s:label />
                    <s:label />

                    <s:textfield cssClass="textInputRO"  maxLength="40" size="40" label="Source "
                                 name = "sourceCommittee" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:label />

                    <s:textfield cssClass="textInputRO"  maxLength="30" size="30" label="Destination "
                                 name = "destinationCommittee" readonly="true">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="30" size="30"
                                 name="workFlowTransaction.wftDestinationEmail" readonly="true">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textarea rows="2" cols="140" label="Action Remarks" name="workFlowTransaction.wftActionRemarks" title="">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{7}" />
                    </s:textarea>

                    <s:select   cssClass="textInput" label="Action" name="workFlowTransaction.erpmGenMaster.erpmgmEgmId"
                                headerKey="" headerValue="-- Please Select --"
                                required="true" list="workFlowActionList" listKey="erpmGenMaster.erpmgmEgmId" listValue="erpmGenMaster.erpmgmEgmDesc">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <tr><td> &nbsp; </td></tr>


                    <s:submit   name="btnSubmit" value="Take Action"
                                onclick="alert('The Indent will now be forwarded to the next stage. We will keep you posted on the progress of work.');">
                                <s:param name="colspan" value="%{1}" />
                                <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit   name="btnViewWorkFlow" value="View WorkFlow" action="viewWorkFlow" align="center">
                                <s:param name="colspan" value="%{1}" />
                                <s:param name="align" value="left" />
                    </s:submit>

                    <s:if test="btnEditEnabled == true">
                        <s:submit name="btnEditWork" value="Edit Indent" action="editWork" align="left">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>
                    </s:if>

                     <s:iterator value="{1,1,1,1,1,1,1,1}">
                     <s:label value="..." cssClass="tdSpace"/>
                     </s:iterator>


                </s:form>
                </div>
                    <p>&nbsp;</p>
            </div>

            <div id ="mainContent">
                <s:if test="workFlowTransactionList.size() > 0">
                <s:form name="frmViewWorkFlow">
                    <table width="100%" border="1" cellspacing="0" cellpadding="0" align="left" >
                        <tr><td>
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
