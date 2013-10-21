<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript"  type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.K. Naqvi Jamia Millia Islamia">
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
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="left">
                 <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{11}" />
                 </s:bean>

                 <div style="background-color: #215dc6;">
                             <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;Step 4 of 5 (Add Terms and Conditions to PO)</p>
                             <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                 </div>
                 <div style="border: solid 1px #000000; background:  gainsboro">

                 <s:form name="frmTermsAndCondition" action="saveTermsToPO" theme="qxhtml">
                    <s:hidden name ="podetail.podPodetailsId" />
                    <s:hidden name="poN" />
                    <s:hidden name="poTerm.potPotId"/>

                    <s:textfield cssClass="textInputRO"  maxLength="20" size="20"
                                 label="PO No" name="poNumber" title="Purchase Order Number" readonly="true" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  maxLength="10" size="10" label="PO Date" name="poDate" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  maxLength="50" size="25" label="Supplier" name="pomaster.suppliermaster.smName" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  label="Institution  " name="pomaster.institutionmaster.imName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  label="College/Faculty" name="pomaster.subinstitutionmaster.simName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  label="Department" name="pomaster.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  maxLength="25" size="20" label="Apprvd By" name="pomaster.erpmusersByPomApprovedById.erpmuFullName" title="Purchase Order approved by" readonly="true" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                label="Currency" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:iterator value="{1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                    <s:label value="In the following section, please select generic terms and customise them as per your requirements" cssClass= "pageSubHeadingBlue">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                    </s:label>

                    <s:select   cssClass="textInput" label="Terms" name="poGenericTerm" headerKey="" headerValue="-- Please Select --"
                                required="true" list="poGenericTermsList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  value="selectedTerm"
                                onChange="showClause('saveTermsToPO_poGenericTerm','saveTermsToPO_clause');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                   </s:select>

                    <s:iterator value="{1,1,1,1,1,1,1,1,1,1}">
                                <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                  <s:textarea name="clause" cols="100" rows="3">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{10}" />
                  </s:textarea>

                   <s:submit value="Add selected clause to PO" action="saveTermToPO">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{0}" />
                    </s:submit>

                    <s:submit value="Add all clauses to PO" action="prepareNonIndentedItemsForPO">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{0}" />
                    </s:submit>

                    <s:iterator value="{1,1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>


                    <s:submit name="btnBrowsePO" value="Browse Purchase Orders" action="browsePOs" >
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>                    

                    <s:submit value="Indented Items (2)" action="prepareIndentedItemsForPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:submit value="Non Indented Items (3)"  action="prepareNonIndentedItemsForPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>
                  
                    <s:submit value="Item Distribution (5)"  action="prepareItemDeliveryLocationsForPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    
                    <s:submit name="btnPrintPO" value="Print Purchase Order" action="PrintPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                </s:form>
                <hr>
                <s:if test="poTermList.size() > 0">
                <s:label value="Terms in Purchase Order" cssClass= "pageSubHeadingBlue">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                </s:label>
                <hr>
                <s:form name="frmPOTerms" align="center">
                    <display:table name="poTermList" pagesize="15"
                               excludedParams="*" export="false" cellpadding="0"
                               cellspacing="0" summary="false" id="doc" style="width:100%"
                               requestURI="/PrePurchase/ManagePOMaster.action">

                        <display:column  class="griddata" title="S.No"  sortable="true" headerClass="gridheader" style="width:5%">
                            <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Generic Term"
                                        headerClass="gridheader"
                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                        style="width:15%" sortable="true"/>

                         <display:column property="potTermsDescription" title="Term Description"
                                    headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:50%" sortable="true"/>

                        <display:column paramId="potPotId" paramProperty="potPotId"
                                    href="/pico/PrePurchase/editPoTerm.action?poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit Term" style="width:10%">
                                    Edit Term
                        </display:column>


                         <display:column paramId="potPotId" paramProperty="potPotId"
                                    href="/pico/PrePurchase/deletePoTerm.action?poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Remove Term" style="width:10%">
                                    Remove Term
                        </display:column>

                    </display:table>
             </s:form>
             </s:if>

             <s:if test="PODetailList.size() > 0">
                 <hr>
                    <s:label value="Items in Purchase Order" cssClass= "pageSubHeadingBlue">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                    </s:label>
                <hr>
                <s:form name="frmPODetails" align="center">
                    <display:table name="PODetailList" pagesize="15"
                               excludedParams="*" export="false" cellpadding="0"
                               cellspacing="0" summary="false" id="doc"
                               requestURI="/PrePurchase/ManagePOMaster.action">

                        <display:column  class="griddata" title="S.No"  sortable="true" maxLength="100" headerClass="gridheader" style="width:10%">
                            <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                        maxLength="80" headerClass="gridheader"
                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                        style="width:30%" sortable="true"/>

                         <display:column property="podQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:10%" sortable="true"/>

                        <display:column property="podRate" title="Unit Rate"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                        <display:column property="erpmIndentDetail.erpmIndentMaster.indtTitle" title="Indent Title"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>

                        <display:column paramId="podPodetailsId" paramProperty="podPodetailsId"
                                    href="/pico/PrePurchase/DeletePoDetails.action?indentFromDate=${param['indentFromDate']}&indentToDate=${param['indentToDate']}&poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Return to Indent" style="width:30%">
                                    Return to Indent
                        </display:column>

                    </display:table>
             </s:form>
             </s:if>


                 <br>
            </div>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
