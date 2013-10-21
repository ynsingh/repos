<%--
    Document   : PurchaseInvoiceDetails
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed , Saeed-uz-Zama & mkhan
--%>



<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <!--<script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>-->
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">View Serial Info</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">
                <%-- <p align="center"><s:label value="View Serial Info" cssClass="pageHeading"/></p>
                     <p align="center"><s:property value="message" /></p>--%>
                <s:form name="frmViewSerialInfo"   theme="qxhtml">

                    <s:hidden name="esr.stId" />
                    <s:hidden name ="VSRID"/>
                    <s:hidden name="pid.pidPidId" />



                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>

                            <s:textfield label="Item" required="" name="pid.erpmItemMaster.erpmimItemBriefDesc" headerKey="" cssClass="textInput" 
                                         readonly="PrdNoReadOnly">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield>


                            <s:textfield label="Product No" name="productNo"  cssClass="textInput" size="40" readonly="PrdNoReadOnly" maxLength="30" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
                                <tr><td> &nbsp; </td></tr>

                                <tr><td align="left">

                                    <s:submit theme="simple"  value="Save"   action="SavePOStokInfo" disabled="PrdNoReadOnly">
                                    </s:submit>

                                    <s:submit theme="simple"  value="Back"   action="BackPOStokInfo" >
                                    </s:submit>

                                    <s:submit theme="simple"  value="Add"   action="AddPOStokInfo" disabled="PrdNoReadOnly" >
                                    </s:submit>

                                </td><td align="left">

                                    
                               </td></tr>


                                <tr><td> &nbsp; </td></tr>
                            </tbody>
                    </table>
                                      
                        <table border="1" cellspacing="5" cellpadding="4" align="center">

                            <tr><td>
                                    <display:table  name="viewStockRecList" pagesize=""
                                                    excludedParams="*" export="false" cellpadding="5"
                                                    cellspacing="0" id="doc"
                                                    requestURI="/Purchase/PurchaseInvoiceAction.Action">
                                        <display:column  class="griddata" title="SNo" sortable="true"  headerClass="gridheader" >
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                      <display:column property="serialCode" title="Item Serial Number"
                                                    headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                                    <display:column  property="stProductNo" title="Product No"
                                                     headerClass="gridheader"
                                                     class="griddata" />

                                    <%--  <s:if test="pid.erpmPurchaseinvoiceMaster.erpmPoMaster = null">
    
                                    <%--  <display:column paramId="VSRID" paramProperty="stId"
                                                    href="/pico/Purchase/EditPOStockInfo"
                                                    headerClass="gridheader" class="griddata" media="html"  title="" value="hi"  >
                                    </display:column>

                                    <display:column paramId="VSRID" paramProperty="stId"
                                    href="/pico/Purchase/DeletePOStockInfo"
                                    headerClass="gridheader" class="griddata" media="html" value="hello" >
                                   
                                    </display:column>
                                    
                                    </s:if>
                                    --%>
                                    <s:if test="pid.erpmPurchaseinvoiceMaster.erpmPoMaster != null">
                                    <display:column paramId="VSRID" paramProperty="stId"
                                                    href="/pico/Purchase/EditPOStockInfo"
                                                    headerClass="gridheader" class="griddata" media="html"  title="" value="Edit"  >
                                    </display:column>

                                    <display:column paramId="VSRID" paramProperty="stId"
                                    href="/pico/Purchase/DeletePOStockInfo"
                                    headerClass="gridheader" class="griddata" media="html"  >
                                    Delete
                                    </display:column>
                                    </s:if>>
                                </display:table>
                                    </td></tr>
                        </table>

                    </s:form>
                    <br>
                     </div>
                &nbsp;
                </div>

            <div id="footer">
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
