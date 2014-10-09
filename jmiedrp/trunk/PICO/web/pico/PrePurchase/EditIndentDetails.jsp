<%-- 
    Document   : EditIndentDetails
    Created on : 18 May, 2012, 7:21:23 PM
    Author     : sknaqvi
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
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
                <!-- *********************************End Menu****************************** -->
                <div id ="mainContent" align="left">
                    <br><br>
                <s:actionerror />

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style="background-color: #215dc6;" >
                    <p align="left" class="pageHeading" style="color:  #ffffff"> <s:property value="getText('PrePurchase.Step2of3AddItemsToIndent')" /></p>
                    <p  align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"  /> </p>
                </div>
                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="FrmIndentitems" action="SaveIndentItems" theme="qxhtml">
                        <s:hidden name ="erpmindtmast.indtIndentId" />
                        <s:hidden name ="erpmindtdet.indtDetailId" />
                        <s:hidden name ="defaultCurrency" />
                        <s:hidden name="indtDetailId" />
                        <s:hidden name="itemRateId" />
                        <s:hidden name="btnEditEnabled"/>
                        <s:hidden name="indentId"/>
                        <s:hidden name="POMode" value="IndentMode" />
                        <s:property value=""/>
                        <s:hidden name="someId" value="55" />



                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                        <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                     key = "PrePurchase.IndentNo" name="defaultIndent" title="" readonly="true">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                     key="PrePurchase.IndentCurrency" name="defaultCurrencyName" title="" readonly="true">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:textfield cssClass="textInputRO"  maxLength="100" size="100"
                                     key="PrePurchase.IndentTitle" name="defaultIndentTitle" title="" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:textfield>

                        <s:label value="...." cssClass="tdSpace">
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{8}" />
                        </s:label>

                        
                        <s:label value="Please Add Item Details" cssClass= "pageSubHeading">
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>
                        
                        <s:select   cssClass="textInput" key="PrePurchase.ItemName" name="erpmindtdet.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                    required="true" list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" tabindex="1"
                                    onchange="getStockInHand('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId','SaveIndentItems_stockInHand','SaveIndentItems_indentId');"
                                    ondblclick="getitemList_2('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId');" >

                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:textfield  cssClass="textInput"  maxLength="5" size="30" key="PrePurchase.Quantity" name="erpmindtdet.indtQuantity" 
                                      onkeypress="return isNumberKey(event)" tabindex="1"
                                      onchange="getItemRateAndUOP(  'SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId',
                                      'SaveIndentItems_defaultCurrencyName',
                                      'SaveIndentItems_erpmindtdet_indtQuantity',
                                      'SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId',
                                      'SaveIndentItems_UOP',                                                                 
                                      'SaveIndentItems_selectedItemRate',
                                      'SaveIndentItems_erpmindtdet_indtApproxcost',
                                      'SaveIndentItems_POMode');
                                      updateTotalItemCost('SaveIndentItems_erpmindtdet_indtQuantity',
                                      'SaveIndentItems_erpmindtdet_indtApproxcost',
                                      'SaveIndentItems_totalCost');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:textfield    requiredposition="left" maxLength="10" size="30" cssClass="textInputRO"
                                        key="PrePurchase.ItemRate" name="selectedItemRate" readonly="true" >
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:select  cssClass="textInput" key="PrePurchase.ItemRates" name="erpmindtdet.erpmItemRate.irItemRateId" headerKey="" headerValue="-- Please Select --" required="true"
                                   list="itemRateList" listKey="irItemRateId" listValue="irdRate" value="selectedItemRate" tabindex="3"
                                   onchange="showItemRateDetails('SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId',
                                   'SaveIndentItems_selectedItemRate',
                                   'SaveIndentItems_selectedItemRateValidFrom',
                                   'SaveIndentItems_selectedItemRateValidTo',
                                   'SaveIndentItems_selectedItemRateCurrency',
                                   'SaveIndentItems_minOrderQuantity',
                                   'SaveIndentItems_maxOrderQuantity',
                                   'SaveIndentItems_erpmindtdet_indtQuantity',
                                   'SaveIndentItems_erpmindtdet_indtApproxcost',
                                   'SaveIndentItems_taxNarration',
                                   'SaveIndentItems_taxValue',
                                   'SaveIndentItems_totalCost');">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:select>

                        <s:textfield  maxLength="10" size="20"  key="PrePurchase.Cost" name="erpmindtdet.indtApproxcost"
                                      title="(Approximate) Cost" onkeypress="return isNumberKey(event)" 
                                      onchange="updateTotalItemCost('SaveIndentItems_erpmindtdet_indtQuantity',
                                      'SaveIndentItems_erpmindtdet_indtApproxcost',
                                      'SaveIndentItems_totalCost');"
                                      tabindex="4">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:if test="btnEditEnabled == true">
                            <s:textfield  cssClass="textInput"  maxLength="5" size="30" key="PrePurchase.ApprovedQuantity" name="erpmindtdet.indtApprovedQuantity"
                                          onkeypress="return isNumberKey(event);"
                                          onchange="UpdateIndentCost('SaveIndentItems_erpmindtdet_indtApprovedQuantity',
                                          'SaveIndentItems_erpmindtdet_indtAcceptedUnitRate',
                                          'SaveIndentItems_erpmindtdet_indtApproxcost');">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                            </s:textfield>
                        </s:if>

                        <s:if test="btnEditEnabled == true">
                            <s:textfield  cssClass="textInput"  maxLength="10" size="30" key="PrePurchase.ApprovedUnitRate" name="erpmindtdet.indtAcceptedUnitRate"
                                          onkeypress="return isNumberKey(event)"
                                          onchange="UpdateIndentCost('SaveIndentItems_erpmindtdet_indtApprovedQuantity',
                                          'SaveIndentItems_erpmindtdet_indtAcceptedUnitRate',
                                          'SaveIndentItems_erpmindtdet_indtApproxcost');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                            </s:textfield>
                        </s:if>

                        <s:textfield requiredposition="left" maxLength="5" size="84" cssClass="textInputRO"
                                     key="PrePurchase.ApplicableTaxes" name="taxNarration"  readonly="true" title="Applicable Taxes">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:textfield>

                        <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                       key="PrePurchase.TaxValue" name="taxValue" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO" key="PrePurchase.UnitOfPurchase" name="UOP"  readonly="true">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                       key="PrePurchase.Currency" name="selectedItemRateCurrency" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                       key="PrePurchase.StockInHand" name="stockInHand" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield> 
                        <%--  <s:label />
                        <s:label />  --%>

                        <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                        key="PrePurchase.RateValidFrom" name="selectedItemRateValidFrom" readonly="true">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                        key="PrePurchase.RateValidTo" name="selectedItemRateValidTo" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:label />
                        <s:label />

                        <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                        key="PrePurchase.MinOrderQty" name="minOrderQuantity" readonly="true">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                        key="PrePurchase.MaximumQuantity" name="maxOrderQuantity" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:label />
                        <s:label />

                        <s:textarea rows="2" cols="70" key="PrePurchase.Purpose" name="erpmindtdet.indtPurpose" title="" tabindex="2">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:textarea>

                        <%--s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label />
                        </s:iterator>
--%>
                        <s:textfield   requiredposition="left" maxLength="10" size="30" cssClass="textInputRO"
                                       key="PrePurchase.TotalCost" name="totalCost" readonly="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:submit name="btnSubmit" key="PrePurchase.AddItem" >
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnBrowse" key="PrePurchase.Browse"  action="BrowseIndent">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnBrowse" key="PrePurchase.NewIndent"  action="ManageIndent">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>

                        <s:submit name="btnSubmitIndent" key="PrePurchase.SubmitIndent"  action="SubmitIndent">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="left" />
                        </s:submit>
                    </s:form>                                
                    <s:if test="indentitemlist.size() > 0">
                        <hr>
                        
                        <s:label value="  Items in Indents are:" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
                        
                        <s:form name="frmitem">
                            <table width="100%" >
                            <display:table name="indentitemlist" pagesize="15"
                                           excludedParams="*"  cellpadding="0" cellspacing="0" summary="true" id="doc"
                                           requestURI="/PrePurchase/ManageIndent.action">

                                <display:column title="Record" sortable="true" maxLength="100" headerClass="gridheader"  class="griddata" >
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name" 
                                                    maxLength="100" style="width:30%" headerClass="gridheader"  
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                    sortable="true" />

                                    <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="UOP"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata" />

                                    <display:column property="indtQuantity" title="Quantity"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata" />

                                    <display:column property="indtApproxcost" title="Approx Cost"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata" />

                                    <display:column property="indtPurpose" title="Purpose"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata"  sortable="false"/>

                                    <s:if test="btnEditEnabled == true">
                                        <display:column property="indtApprovedQuantity" title="Approved Quantity"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="griddata"  sortable="false"/>
                                        <display:column property="indtAcceptedUnitRate" title="Approved Unit Cost"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="griddata"  sortable="false"/>
                                        <display:column paramId="indtDetailId" paramProperty="indtDetailId"
                                                        href="/pico/PrePurchase/EditIndentDetailForApproval.action?btnEditEnabled=true"
                                                        headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                            Edit
                                        </display:column>
                                    </s:if>
                                    <s:else>
                                        <display:column paramId="indtDetailId" paramProperty="indtDetailId"
                                                        href="/pico/PrePurchase/EditIndentDetailForApproval.action"
                                                        headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                            Edit
                                        </display:column>
                                    </s:else>

                                </display:table>
                                    </table>
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
