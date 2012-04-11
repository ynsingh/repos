<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
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
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
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
       <div id ="mainContent" align="left">
         <br><br>
         <p align="center"><s:label cssClass="pageHeading" value="MANAGE INDENT FORMS" /></p>
         <s:actionerror/>
        <%-- <p align="left" class="pageMessage"><s:property value="message" /></p> <br>--%>
         <s:form name="FrmIndent" action="SaveIndent" >
         <p align="left" class="pageMessage"><s:property value="message" /></p>
         <s:hidden name ="erpmindtmast.indtIndentId" />
         <s:hidden name="erpmindtdet.ErpmIndentMaster.indtDetailId" />
         

         <table border="0" cellpadding="4" cellspacing="0">
         <tbody>
         <tr>
         <td>
             <script type='text/javascript'>
             function emailValidator(elem, helperMsg){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	if(elem.value.match(emailExp)){
		return true;
	}else{
		alert(helperMsg);
		elem.focus();
		return false;
	}
}

 function PopAlert(){
	confirm ('Thanks for visiting our web site.')
}
             </script>


              <sx:datetimepicker name="erpmindtmast.indtIndentDate" label="Indent Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
              <s:select cssClass="textInput" label="Institution" name="erpmindtmast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                  onchange="getSubinstitutionList('SaveIndent_erpmindtmast_institutionmaster_imId', 'SaveIndent_erpmindtmast_subinstitutionmaster_simId');" ondblclick="getInsituteaftervalidation('SaveIndent_erpmindtmast_institutionmaster_imId');"/>
              <s:select cssClass="textInput" label="College/Faculty/School" name="erpmindtmast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                  onchange="getDepartmentList('SaveIndent_erpmindtmast_subinstitutionmaster_simId', 'SaveIndent_erpmindtmast_departmentmaster_dmId');"/>
              <s:select cssClass="textInput" label="Department Name" name="erpmindtmast.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="DefaultDepartment"  ondblclick="getDepartmentAfterValidation('SaveIndent_erpmindtmast_departmentmaster_dmId');"/>
              <s:select cssClass="textInput" label="Currency of Purchase" name="erpmindtmast.erpmGenMasterByIndtCurrencyId.erpmgmEgmId"
                  headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  value="defaultCurrency"
                  ondblclick="getCurrencyAfterValidation('SaveIndent_erpmindtmast_erpmGenMasterByIndtCurrencyId_erpmgmEgmId');"/>

              <s:select cssClass="textInput" label="Budget Head Type" name="erpmindtmast.budgetheadmaster.bhmId" headerKey="" headerValue="-- Please Select --"
                  list="bhmList" listKey="bhmId" listValue="bhmName" onchange="getAllocatedAmount('SaveIndent_erpmindtmast_budgetheadmaster_bhmId','SaveIndent_AllocatedAmount', 'SaveIndent_erpmindtmast_departmentmaster_dmId')"
                  ondblclick="getBudgetAfterValidation('SaveIndent_erpmindtmast_budgetheadmaster_bhmId');"/>
              <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                  label="Amout Allocated" name="AllocatedAmount" title="" readonly="true"/>
              <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
                  label="Amout Available" name="AllocatedAmount" title="" readonly="true"/>
             
              <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                  label="Indent Signatory" name="erpmindtmast.indtGeneratedBy" title="" />


              <s:textarea cssClass="textArea"  rows="2" cols="50" label="Remarks" name="erpmindtmast.indtRemarks" title="" />


              <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput"
                  label="Forwarded To (Email ID)" name="erpmindtmast.indtForwardedToEmail" title=""/>

               <br><s:label value="Related To Work Flow Transaction" cssClass= "pageHeading"/>
               <table border="1" cellpadding="6" cellspacing="0">
         <tbody>

              <s:select cssClass="textInput" label="Work Flow Name" name="erpmindtmast.workflowmaster.wfmId" list="Wfmnamelist" listKey="wfmId"  listValue="wfmName" />

<%-------------------------------------------------------------------------------------------------------------------------------------

              <s:select cssClass="textInput" label="Work Flow Action" name="erpmindtmast.workflowmaster.workflowdetail.workflowactions.erpmGenMaster.erpmgmEgmId" list="WfaActionsList" listKey="wfaId"  listValue="workflowmaster.workflowdetail.workflowactions.erpmGenMaster.erpmgmEgmDesc" />
--%>
              <s:select cssClass="textInput" label="Work Flow Action" name="erpmgmEgmId" list="WfaActionsList" listKey="wfaId"  listValue="erpmgmEgmDesc" />

<%--              <s:select label="Work Flow Action" name="wft.wftActionTaken"
               list="#{'FW':'Forward',
                       'SB':'SendBack',
                       'RA':'Recommend Approval',
                       'RR':'Recommend Rejection',
                       'AP':'Approve',
                       'RJ':'Reject' }"  />    --%>
<%--------------------------------------------------------------------------------------------------------------------------------------%>

              <s:textarea cssClass="textArea"  rows="2" cols="50" label="Work Flow Remarks " name="wft.wftActionRemarks" title="" />
               

         </tbody>
         <tr><td colspan="2" align="center">
                 <s:submit  theme="simple" name="btnWFAction" value="Submit Workflow Action"   action="SubmitWorkflowAction" cssClass="savebutton" />
          </td></tr>
               </table>

              <%--  <s:select cssClass="textInput" label="Forwarded To (Email ID)" name="erpmindtmast.erpmusersByIndtForwardedToUserId.erpmuId" headerKey="" headerValue="-- Please Select --" list="erpmuserlist" listKey="erpmuId" listValue="erpmuFullName"
                  ondblclick="getForwardedToUserAfterValidation('SaveIndent_erpmindtmast_erpmusersByIndtForwardedToUserId_erpmuId');"/>--%>
  </td>
 </tr> <tr>
         <BR><BR>
                <td align="right">

  <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" value="Save and Proceed to Add Items"  />
  </td>
                <td>
  <s:submit  theme="simple" name="btnBrowse" value="Browse Indent"  action="BrowseIndent" cssClass="savebutton" />
  <s:submit  theme="simple" name="btnClear" value="Clear"   action="ManageIndent" cssClass="savebutton"/>
  <s:submit  theme="simple" name="btnBrowse" value="Show Pending Indent(s)"  action="ShowPendingIndents" cssClass="savebutton" />
<%--  <s:submit  theme="simple" name="btnClear" value="Show Indent Items"   action="ShowIndentDetailItems" cssClass="savebutton" />

  <s:submit theme="simple"  name="btnSubmit" value="Show Alert To User" cssClass="savebutton" /> --%>
                   </td>
                </tr>
                </tbody>
        </table>
               
                </s:form>
            </div>

         <div id ="mainContent" align="center">

             <s:form name="frmitem">
                 <table width="70%" border="2" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indtitemlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManageIndent.action">
                        <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="ItemName"
                                    maxLength="100" headerClass="gridheader" style="width:30%"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                     sortable="true"/>
                        <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="UOP"
                                     maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                        <display:column property="indtQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                        <display:column property="indtApproxcost" title="Approx_Cost"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                        <%--<display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/EditManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                        </display:column>--%>

                    </display:table>
                <br></td></tr>
                </table>

        </s:form>
                 <br>
            </div>

            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


