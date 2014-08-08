
<%--
    Document   : PurchaseChallanStockInfo
    Created on : 10 sep, 2012, 12:22:10 PM
    Author     : Mohd. Manauwar Alam
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

                <div style="background-color: #215dc6;  " >
                    <p align="center" class="pageHeading" style="color:  #ffffff"> &nbsp;<s:property value="getText('Purchase.ManagePurchaseChallanStockInfo')" /></p>
                    <p  align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"  /> </p>
                </div>
                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="frmIssueDetails" action="SavePurchaseChallanStockInfo" theme="qxhtml" cssStyle="border=1">

                        <s:hidden name = "esr.stId" />
                        <s:hidden name ="PurchaseChallanId" />
                        <s:hidden name ="VSRID"/>
                        <%--                        <s:hidden name = "esr"/>   --%>

                        <s:property value=""/>

                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                        <table border="0" cellpadding="4" cellspacing="0" align="center">

                            <s:textfield key="Purchase.ItemName" value="%{itemName}"  cssClass="textInputRO"  size="40" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:textfield key="Purchase.ProductNo" name="esr.stProductNo"  cssClass="textInput" size="40" maxLength="30" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:textfield key="Purchase.CentralStockRegNo" name="esr.stCsrNo"  cssClass="textInput" size="40"   maxLength="20" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:textfield key="Purchase.CentralStockRegPgNo" name="esr.stCsrPgNo"  cssClass="textInput" size="40" maxLength="30" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:textfield key="Purchase.DeptSerialNo" name="esr.stDeptSrNo"  cssClass="textInput" size="40"  maxLength="20" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:textfield key="Purchase.DeptSerialPageNo" name="esr.stDeptSrPgNo"  cssClass="textInput" size="40"  maxLength="10" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>

                            <s:textfield key="Purchase.WarrantyExpiryDate" name="WarrantyExpiryDate"  cssClass="textInput" size="40"  maxLength="30" disabled="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:textfield>
                            <s:select key="Purchase.WarrantyType"  name="WarrantyType" headerKey="" headerValue="-- Please Select --" list="tosWTList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                            </s:select>

                            <s:submit name="btnSubmit" key="Purchase.Save" disabled="PrdNoReadOnly" >
                                <s:param name="colspan" value="%{1}" />
                                <s:param name="align" value="left" />
                            </s:submit>

                            <s:submit name="btnSubmit" key="Purchase.PreviousPage" disabled="PrdNoReadOnly1" action="Done">
                                <s:param name="colspan" value="%{1}" />
                                <s:param name="align" value="left" />
                            </s:submit>

                            <s:submit name="btnSubmit" key="Purchase.AddNewSerialNumber" action="AddSerialNo" disabled="PrdNoReadOnly1">
                                <s:param name="colspan" value="%{1}" />
                                <s:param name="align" value="left" />
                            </s:submit>

                        </table>

                    </s:form>
                    <s:if test="viewStockRecList.size() > 0">
                       <hr>
                        
                        <s:label value="%{getText('Purchase.ps_ItemSerDetailsAre')}" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
                       
                        <s:form name="frmPurchaseChallanStock">
                             <table width="100%" >
                            <display:table  name="viewStockRecList" pagesize=""
                                            excludedParams="*" export="false" cellpadding="8"
                                            cellspacing="0" id="doc"
                                            requestURI="/pico/Purchase/SavePurchaseChallanDetail.action">
                                <display:column  class="griddata" title="SNo" sortable="true"  headerClass="gridheader" >
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="serialCode" title="Item Serial Number"
                                                    headerClass="gridheader" style="width:30%"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                                    <display:column  property="stProductNo" title="Product No" style="width:20%"
                                                     headerClass="gridheader"
                                                     class="griddata" />


                                    <display:column paramId="VSRID" paramProperty="stId" style="width:10%"
                                                    href="/pico/Purchase/EditPurchaseChallanStockInfo"
                                                    headerClass="gridheader" class="griddata" media="html"   >
                                        Edit
                                    </display:column>

                                    <display:column paramId="VSRID" paramProperty="stId" 
                                                    href="/pico/Purchase/DeletePurchaseChallanItem"
                                                    headerClass="gridheader" class="griddata" media="html" >
                                        Delete
                                    </display:column>
                                </display:table>
                             </table>
                            </s:form>
                           
                        </s:if>
                        <br>
                        </div>
                        </div>
                <br>
                <br>
                        <div id="footer">
                            <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
                        </div>
                </div>
                </body>
                </html>
