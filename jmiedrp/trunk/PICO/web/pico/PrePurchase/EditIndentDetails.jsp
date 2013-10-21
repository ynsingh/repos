<%-- 
    Document   : EditIndentDetails
    Created on : 18 May, 2012, 7:21:23 PM
    Author     : sknaqvi
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

                 <div style="background-color: #215dc6;  " >
                     <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;Step 2 of 3 (Add Items to Indent)</p>
                     <p  align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"  /> </p>
                </div>
                <div style="border: solid 1px #000000; background:  gainsboro">
                 <s:form name="FrmIndentitems" action="SaveIndentItems" theme="qxhtml" cssStyle="border=1">
                    <s:hidden name ="erpmindtmast.indtIndentId" />
                    <s:hidden name ="erpmindtdet.indtDetailId" />
                    <s:hidden name ="defaultCurrency" />
                    <s:hidden name="indtDetailId" />
                    <s:hidden name="itemRateId" />
                    <s:hidden name="btnEditEnabled"/>
                    <s:hidden name="POMode" value="IndentMode" />
                    <s:property value=""/>


                     <s:iterator value="{1,1,1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                     </s:iterator>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                label="Indent No" name="defaultIndent" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                label="Indent Currency" name="defaultCurrencyName" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="100" size="100"
                                label="Indent Title" name="defaultIndentTitle" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                    </s:textfield>

                    <s:label value="...." cssClass="tdSpace">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{8}" />
                    </s:label>
                    
                    <s:label value="Please Add Item Details" cssClass= "mysubheading">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{8}" />
                    </s:label>

                    <s:select   cssClass="textInput" label="Item Name" name="erpmindtdet.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" tabindex="1"
                                ondblclick="getitemList_2('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:select>

                    <s:textfield  cssClass="textInput"  maxLength="5" size="30" label="Quantity" name="erpmindtdet.indtQuantity" 
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
                                    label="Item Rate" name="selectedItemRate" readonly="true" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:select  cssClass="textInput" label="Item Rates" name="erpmindtdet.erpmItemRate.irItemRateId" headerKey="" headerValue="-- Please Select --" required="true"
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

                    <s:textfield  maxLength="10" size="20"  label="Cost" name="erpmindtdet.indtApproxcost"
                                  title="(Approximate) Cost" onkeypress="return isNumberKey(event)" 
                                  onchange="updateTotalItemCost('SaveIndentItems_erpmindtdet_indtQuantity',
                                                                'SaveIndentItems_erpmindtdet_indtApproxcost',
                                                                'SaveIndentItems_totalCost');"
                                  tabindex="4">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:if test="btnEditEnabled == true">
                        <s:textfield  cssClass="textInput"  maxLength="5" size="30" label="Approved Quantity" name="erpmindtdet.indtApprovedQuantity"
                                      onkeypress="return isNumberKey(event);"
                                      onchange="UpdateIndentCost('SaveIndentItems_erpmindtdet_indtApprovedQuantity',
                                                                 'SaveIndentItems_erpmindtdet_indtAcceptedUnitRate',
                                                                 'SaveIndentItems_erpmindtdet_indtApproxcost');">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>
                    </s:if>

                    <s:if test="btnEditEnabled == true">
                    <s:textfield  cssClass="textInput"  maxLength="10" size="30" label="Approved Unit Rate" name="erpmindtdet.indtAcceptedUnitRate"
                                  onkeypress="return isNumberKey(event)"
                                  onchange="UpdateIndentCost('SaveIndentItems_erpmindtdet_indtApprovedQuantity',
                                                             'SaveIndentItems_erpmindtdet_indtAcceptedUnitRate',
                                                             'SaveIndentItems_erpmindtdet_indtApproxcost');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>
                    </s:if>

                    <s:textfield requiredposition="left" maxLength="5" size="84" cssClass="textInputRO"
                                label="Applic. Taxes" name="taxNarration"  readonly="true" title="Applicable Taxes">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{4}" />
                    </s:textfield>

                    <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                   label="Tax Value" name="taxValue" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO" label="Unit of Purchase" name="UOP"  readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                   label="Currency" name="selectedItemRateCurrency" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label />
                    <s:label />

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Rate Valid From" name="selectedItemRateValidFrom" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Rate Valid To" name="selectedItemRateValidTo" readonly="true">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                     </s:textfield>

                    <s:label />
                    <s:label />

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Min Order Qty" name="minOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Max Qty" name="maxOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label />
                    <s:label />

                    <s:textarea rows="2" cols="70" label="Purpose" name="erpmindtdet.indtPurpose" title="" tabindex="2">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{4}" />
                    </s:textarea>

                    <s:iterator value="{1,1,1,1,1,1,1,1}">
                        <s:label />
                    </s:iterator>
                    
                    <s:textfield   requiredposition="left" maxLength="10" size="30" cssClass="textInputRO"
                                   label="Total Cost" name="totalCost" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>
                                        
                    <s:submit name="btnSubmit" value="Add Item" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit name="btnBrowse" value="Browse Indent"  action="BrowseIndent">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit name="btnBrowse" value="New Indent"  action="ManageIndent">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    <s:submit name="btnSubmitIndent" value="Submit Indent"  action="SubmitIndent">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>
                </s:form>                                
                <s:if test="indentitemlist.size() > 0">
                    <hr>
                    <s:label value="Items in Indents are: "/>
                    <s:form name="frmitem">
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
        </s:form>
        <hr>
        </s:if>

            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
