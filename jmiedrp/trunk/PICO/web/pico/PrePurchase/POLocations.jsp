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
        <link href = "../css/pico.css" rel="stylesheet" type="text/css" />
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
                <br><br> 
                 <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{11}" />
                 </s:bean>

                 <div style="background-color: #215dc6;">
                             <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;<s:property value="getText('PrePurchase.Step5of5AddDeliveryLocations')" /></p>
                             <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                 </div>
                 <div style="border: solid 1px #000000; background:  gainsboro">

                 <s:form name="frmPOLocations" action="saveLocationsToPO" theme="qxhtml">
                    <s:hidden name ="poLocation.poLocationsId"/>
                    <s:hidden name ="podetail.podPodetailsId" />
                    <s:hidden name = "poN" />
                    <s:hidden name="poTerm.potPotId"/>


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

                    <s:label value="%{getText('PrePurchase.ps_DeliveryLocation')}" cssClass= "pageSubHeading">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                    </s:label>

                    <s:select   cssClass="textInput" key="PrePurchase.ItemName" name="poLocation.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="PODetailList" listKey="erpmItemMaster.erpmimId" listValue="erpmItemMaster.erpmimItemBriefDesc">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                   </s:select>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>

                    <s:textfield cssClass="textInput"  maxLength="10" size="5"
                                key="PrePurchase.Quantity" name="poLocation.qty" title="" readonly="false"
                                onChange="checkQtyDistribution( 'saveLocationsToPO_poN',
                                                                'saveLocationsToPO_poLocation_erpmItemMaster_erpmimId',
                                                                'saveLocationsToPO_poLocation_qty',
                                                                'saveLocationsToPO_poLocation_poLocationsId');">

                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    <s:label value="..." cssClass="tdSpace"/>

                     <s:label value="..." cssClass="tdSpace"/>
                     
                    <s:select   cssClass="textInput" key="PrePurchase.Department" name="poLocation.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="departmentList" listKey="dmId" listValue="dmName"  >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                   </s:select>                    
                   
                   <s:label value="..." cssClass="tdSpace"/>
                   <s:label value="..." cssClass="tdSpace"/>

                   <s:textfield cssClass="textInput"  maxLength="40" size="40"
                                key="PrePurchase.Location" name="poLocation.location" title="" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label value="..." cssClass="tdSpace"/>
                    
                    <s:submit key="PrePurchase.AddLocation" action="saveLocationToPO">
                        <s:param name="labelcolspan" value="%{1}" />
                    </s:submit>

                   <s:iterator value="{1,1,1,1,1,1,1,1,1,1}">
                        <s:label value="..." cssClass="tdSpace"/>
                   </s:iterator>

                   <s:submit name="btnBrowsePO" key="PrePurchase.Browse" action="browsePOs" >
                      <s:param name="colspan" value="%{1}" />
                   </s:submit>

                   <s:label value="..." cssClass="tdSpace"/>
                   
                   <s:submit key="PrePurchase.IndentedItems2" action="prepareIndentedItemsForPO">
                      <s:param name="colspan" value="%{1}" />
                   </s:submit>

                   <s:submit key="PrePurchase.NonIndentedItems3"  action="prepareNonIndentedItemsForPO">
                      <s:param name="colspan" value="%{1}" />
                   </s:submit>

                   <s:label value="..." cssClass="tdSpace"/>

                   <s:submit key="PrePurchase.TermsAndConditions4"  action="prepareTermsForPO">
                      <s:param name="colspan" value="%{1}" />
                   </s:submit>

                    <s:submit name="btnPrintPO" key="PrePurchase.Print" action="PrintPO">
                      <s:param name="colspan" value="%{1}" />
                    </s:submit>
                </s:form>
                <s:if test="poLocationList.size() > 0">
                <s:label value="%{getText('PrePurchase.ps_LocWiseDistribution')}" cssClass= "pageSubHeading">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                </s:label>
                <hr>
                <s:form name="frmPOLocationDistibution" align="center">
                    <display:table name="poLocationList" pagesize="15"
                               excludedParams="*" export="false" cellpadding="0"
                               cellspacing="0" summary="false" id="doc" style="width:100%"
                               requestURI="/PrePurchase/ManagePOMaster.action">

                        <display:column  class="griddata" title="S.No"  sortable="true" headerClass="gridheader" style="width:5%">
                            <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="departmentmaster.dmName" title="Department"
                                        headerClass="gridheader"
                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                        style="width:30%" sortable="true"/>

                         <display:column property="location" title="Location within Department"
                                    headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:30%" sortable="true"/>

                         <display:column property="qty" title="Quantity"
                                    headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:5%" sortable="true"/>

                        <display:column paramId="poLocationsId" paramProperty="poLocationsId"
                                    href="/pico/PrePurchase/editPoLocation.action?poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit Locations" style="width:15%">
                                    Edit Location
                        </display:column>


                         <display:column paramId="poLocationsId" paramProperty="poLocationsId"
                                    href="/pico/PrePurchase/deletePoLocation.action?poN=${param['poN']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Remove Location" style="width:15%">
                                    Remove Location
                        </display:column>

                    </display:table>
             </s:form>
             </s:if>



                <hr>
                <s:if test="poTermList.size() > 0">
                <s:label value="%{getText('PrePurchase.ps_TermsInPO')}" cssClass= "pageSubHeading">
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
                    </display:table>
             </s:form>
             </s:if>


                 <br>
            </div>
                 <br><br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
