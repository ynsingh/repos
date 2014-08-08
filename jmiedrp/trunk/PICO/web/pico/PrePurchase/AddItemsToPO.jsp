
<%--  
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>

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

                 <br>
                
                 <div style="background-color: #215dc6;">
                             <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;<s:property value="getText('PrePurchase.Step3of5AddNonIndentedItemsToPO')" /></p>
                             <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                 </div>

                 <div style="border: solid 1px #000000; background:  gainsboro">

                 <s:form name="FrmNonIndenteditems" action="SaveNonIndentItemsToPO" theme="qxhtml">
                    <s:hidden name ="podetail.podPodetailsId" />
                    <s:hidden name="poN" />
                    <s:hidden name="POMode" value="POMode" />

                    <s:textfield cssClass="textInputRO"  maxLength="20" size="20"
                                 key="PrePurchase.PurchaseOrderNo" name="poNumber" title="Purchase Order Number" readonly="true" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  maxLength="10" size="10" key="PrePurchase.PODate" name="poDate" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  maxLength="50" size="25" key="PrePurchase.SupplierName" name="pomaster.suppliermaster.smName" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>


                    <s:textfield  cssClass="textInputRO"  key="PrePurchase.Institution" name="pomaster.institutionmaster.imName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  key="PrePurchase.SubInstitution" name="pomaster.subinstitutionmaster.simName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInputRO"  key="PrePurchase.Department" name="pomaster.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>

                     <s:textfield  cssClass="textInputRO"  maxLength="25" size="20" key="PrePurchase.ApprovedBY" name="pomaster.erpmusersByPomApprovedById.erpmuFullName" title="Purchase Order approved by" readonly="true" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                key="PrePurchase.Currency" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>                    

            <s:iterator value="{1,1,1,1,1,1}">
                <s:label value="..." cssClass="tdSpace"/>
            </s:iterator>

                    <s:label value="%{getText('PrePurchase.ps_AddItemsToPO')}" cssClass= "pageSubHeading">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                    </s:label>

                    <s:if test="(editPODetail=='ON')">
                    <s:select   cssClass="textInputRO" key="PrePurchase.Items" name="podetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" value="defaultItem" disabled="true"
                                ondblclick="getitemList('SaveNonIndentItemsToPO_podetail_erpmItemMaster_erpmimId', 'SaveNonIndentItemsToPO_pomaster_suppliermaster_smName');" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:select>
                    </s:if>
                    <s:else>
                    <s:select   cssClass="textInput" key="PrePurchase.Items" name="podetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" value="defaultItem"
                                ondblclick="getitemList('SaveNonIndentItemsToPO_podetail_erpmItemMaster_erpmimId', 'SaveNonIndentItemsToPO_pomaster_suppliermaster_smName');"
                                >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:select>
                    </s:else>

                   
                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield  cssClass="textInput"  maxLength="5" size="30" key="PrePurchase.Quantity" name="podetail.podQuantity" 
                                  onkeypress="return isNumberKey(event)"
                                  onchange="getItemRateAndUOP('SaveNonIndentItemsToPO_podetail_erpmItemMaster_erpmimId',
                                                              'SaveNonIndentItemsToPO_pomaster_erpmGenMasterByPomCurrencyId_erpmgmEgmDesc',
                                                              'SaveNonIndentItemsToPO_podetail_podQuantity',
                                                              'SaveNonIndentItemsToPO_podetail_erpmItemRate_irItemRateId',
                                                              'SaveNonIndentItemsToPO_UOP',                                                              
                                                              'SaveNonIndentItemsToPO_selectedItemRate',
                                                              'SaveNonIndentItemsToPO_approxcost',                                                              
                                                              'SaveNonIndentItemsToPO_POMode');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>                   

                   <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    key="PrePurchase.ItemRate" name="selectedItemRate" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

      
                    <s:label value="..." cssClass="tdSpace"/>
                    
                    <s:textfield  maxLength="20" size="12"  key="PrePurchase.Cost" name="approxcost" cssClass="textInputRO"
                                  title="" onkeypress="return isNumberKey(event)" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:select  cssClass="textInput" key="PrePurchase.Rates" name="podetail.erpmItemRate.irItemRateId" headerKey="" headerValue="-- Please Select --" required="true"
                               list="itemRateList" listKey="irItemRateId" listValue="irItemRateId" value="selectedItemRate"
                               onchange="showItemRateDetails('SaveNonIndentItemsToPO_podetail_erpmItemRate_irItemRateId',
                                                             'SaveNonIndentItemsToPO_selectedItemRate',
                                                             'SaveNonIndentItemsToPO_selectedItemRateValidFrom',
                                                             'SaveNonIndentItemsToPO_selectedItemRateValidTo',
                                                             'SaveNonIndentItemsToPO_selectedItemRateCurrency',
                                                             'SaveNonIndentItemsToPO_minOrderQuantity',
                                                             'SaveNonIndentItemsToPO_maxOrderQuantity',
                                                             'SaveNonIndentItemsToPO_podetail_podQuantity',
                                                             'SaveNonIndentItemsToPO_approxcost',
                                                             'SaveNonIndentItemsToPO_taxNarration',
                                                             'SaveNonIndentItemsToPO_taxValue',
                                                             'SaveNonIndentItemsToPO_totalCost');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{10}" />
                    </s:select>
                    
                    <s:iterator value="{1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                   <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                key="PrePurchase.ApplicableTaxes" name="taxNarration"  readonly="true" title="Applicable Taxes">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                   key="PrePurchase.TaxValue" name="taxValue" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield   requiredposition="left" maxLength="15" size="15" cssClass="textInputRO"
                                   key="PrePurchase.TotalCost" name="totalCost" readonly="true" title="Total Cost">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:iterator value="{1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                   <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                key="PrePurchase.UnitOfPurchase" name="UOP"  readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                   key="PrePurchase.Currency" name="selectedItemRateCurrency" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:iterator value="{1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    key="PrePurchase.ValidFrom" name="selectedItemRateValidFrom" readonly="true" title="Rate valid from date">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    key="PrePurchase.ValidTo" name="selectedItemRateValidTo" readonly="true" title="Rate valid till date">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:iterator value="{1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                    </s:iterator>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    key="PrePurchase.MinOrderQty" name="minOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>
                    
                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    key="PrePurchase.MaxOrderQty" name="maxOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>
                    
                    <s:submit name="btnAddItemToPO" key="PrePurchase.AddItem" action="addNonIndentedItemsToPO">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{0}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit name="btnBrowsePO" key="PrePurchase.Browse" action="browsePOs" >
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:submit key="PrePurchase.IndentedItems2" action="prepareIndentedItemsForPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:submit key="PrePurchase.TermsAndConditions4"  action="prepareTermsForPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:label value="..." cssClass="tdSpace"/>

                    <s:submit name="btnPrintPO" key="PrePurchase.ItemsDistribution5"  title="Item(s) Distribution (Step -5)" action="prepareItemDeliveryLocationsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                    </s:submit>

                    <s:submit name="btnPrintPO" key="PrePurchase.Print" action="PrintPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>
                </s:form>

                <hr>

                <s:if test="PODetailList.size() > 0">                
                <s:label value="%{getText('PrePurchase.ps_ItemsInPO')}" cssClass= "pageSubHeading">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                </s:label>
                <hr>
                <s:form name="frmPODetails" align="center">
                    <display:table name="PODetailList" pagesize="15"  decorator="PrePurchase.PrePurchaseDecorator"
                               excludedParams="*" export="false" cellpadding="0" style="width:100%"
                               cellspacing="0"  id="doc" 
                               requestURI="/PrePurchase/ManagePOMaster.action">

                        <display:column  class="griddata" title="S.No"  sortable="true"
                                         maxLength="100" headerClass="gridheader" style="width:3%">
                        <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                        maxLength="80" headerClass="gridheader"
                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                        style="width:15%" sortable="true"/>

                        <display:column property="podQuantity" title="Quantity" 
                                    maxLength="100" headerClass="gridheader" total="true"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:5%" sortable="true"/>

                         <display:column property="podRate" title="Unit Rate" 
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                         <display:column property="podTaxes" title="Tax(es)" headerClass="gridheader" 
                                         maxLength="35"  class="griddata" style="width:20%"  />

                         <display:column property="podTaxValue" title="Tax Value" headerClass="gridheader" 
                                         maxLength="35"  class="griddata" style="width:10%"/>

                         <display:column property="podTotalValue" title="Total Value" headerClass="gridheader" 
                                         maxLength="35"  class="griddata" style="width:10%" />

                         <display:column property="erpmIndentDetail.erpmIndentMaster.indtTitle" title="Indent Title"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                        <display:column paramId="podPodetailsId" paramProperty="podPodetailsId"
                                    href="/pico/PrePurchase/editPODetails.action?indentFromDate=${param['indentFromDate']}&indentToDate=${param['indentToDate']}&poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit" style="width:5%">
                                    Edit
                        </display:column>

                        <display:column paramId="podPodetailsId" paramProperty="podPodetailsId"
                                    href="/pico/PrePurchase/deletePoDetailsStage3.action?indentFromDate=${param['indentFromDate']}&indentToDate=${param['indentToDate']}&poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Remove from PO" style="width:25%">
                                    Remove from PO
                        </display:column>


                   <%--     <display:footer>
                        <tr>
                         <td>Total Bill:</td>
                        <td><c:out value="${totals.column3}" /></td>
                        <tr>
  </display:footer> --%>

                    </display:table>
             </s:form>
             </s:if>

                 <br>
            </div>
            </div>
            <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
