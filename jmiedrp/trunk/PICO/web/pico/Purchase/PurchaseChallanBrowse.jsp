<%--
    Document   : PurchaseChallanBrowse
    Created on : 30 Aug, 2012, 2:57:42 PM
    Author     : Manauwar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
                <div style ="background-color: #215dc6;">
                <p align="center" class="pageHeading" style="color:  #ffffff">PURCHASE CHALLAN LIST</p>
                <p align="center" class="mymessage" style="color:  #ffff99" ><s:property value="message" /></p>
		</div>
                <div style="border: solid 1px #000000; background: gainsboro">
                <s:form name="frmPurchaseChallanBrowse" >

                    <table  border="0" cellspacing="0" cellpadding="6" align="center">
                        <tr><td>
                                <display:table name = "PChallanMastList" decorator="Purchase.PurchaseDecorator"
                                               excludedParams="*" export="false" cellpadding="8"
                                               cellspacing="0" id="doc"
                                               requestURI="/Purchase/BrowsePurchaseChallanMaster.action" >
                                    <display:column  class="griddata" title="Record" sortable="true"  headerClass="gridheader" style="width:10%">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                <display:column property="pono" title="PO No:"
                                                maxLength="30" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:15%" sortable="true"/>
                                <display:column property="imShortName" title="Institute"
                                                maxLength="10" headerClass="gridheader" style="width:5%"
                                                class="griddata" sortable="true"/>
                                <display:column property="simShortName" title="SubInstitute"
                                                maxLength="10" headerClass="gridheader" style="width:5%"
                                                class="griddata" sortable="true"/>
                                <display:column property="dmShortName" title="Department"
                                                maxLength="10" headerClass="gridheader" style="width:5%"
                                                class="griddata" sortable="true"/>
                                <display:column property="smName" title="Supplier"
                                                maxLength="30" headerClass="gridheader" style="width:20%"
                                                class="griddata" sortable="true"/>
                                <display:column property="challanNo" title="Purchase Challan no"
                                                maxLength="10" headerClass="gridheader" style="width:10%"
                                                class="griddata" sortable="true"/>
                                <display:column property="formattedRecvDate" title="Recv Date:"
                                                maxLength="45" headerClass="gridheader" style="width:10%"
                                                class="griddata" sortable="true"/>

                                <display:column property="formattedChallanDate" title="Challan Date"
                                                maxLength="45" headerClass="gridheader"
                                                class="griddata" sortable="true" style="width:10%"/>


                                <display:column paramId="PCID" paramProperty="pcmPcmId"
                                                href="/pico/Purchase/EditPurchaseChallan"
                                                headerClass="gridheader" class="griddata" media="html"  title="Edit" style="width:10%">
                                    Edit
                                </display:column>
                                <display:column paramId="PCID" paramProperty="pcmPcmId"
                                                href="/pico/Purchase/ShowDetailPurchaseChallan"
                                                headerClass="gridheader" class="griddata" media="html"  title="Show Detail" style="width:20%">
                                    Show Detail
                                </display:column>
                                <display:column paramId="PCID" paramProperty="pcmPcmId"
                                                href="/pico/Purchase/PrintReceiptPurchaseChallan"
                                                headerClass="gridheader" class="griddata" media="html"  title="Print Receipt" style="width:20%">
                                    Print Receipt
                                </display:column>


                            </display:table>
                            </td></tr>
                    </table>
                </s:form>
</div>
                <br><br><br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>

