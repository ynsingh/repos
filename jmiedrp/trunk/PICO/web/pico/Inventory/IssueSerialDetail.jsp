<%--
    Document   : IssueSerialDetail
    Created on : 19 Jun, 2012, 4:40:27 PM
    Author     : manauwar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Inventory/Inventory.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="manauwar, Jamia Millia Islamia">
        <meta name="email" content="manauwar.alam@live.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
        <s:head/>
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
            <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;">
                    <p align="center"  class="pageHeading" style="color: #ffffff"><s:label value="MANAGE ISSUE SERIAL DETAILS" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <%--------------------this is a issue serial detail  form --------------------%>
                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="frmIssueSerialDetails" action="SaveIssueSerialDetails" theme="qxhtml">
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <s:hidden name="Issue_No" />
                            <s:hidden name="Issue_Date" />
                            <s:hidden name="ItemName" />


                            <s:textfield label="Issue Number" name="" readonly="true" value="%{Issue_No}" cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:label value=". . ." cssClass="tdSpace"/>
                            <s:textfield label="Issue Date" name="" readonly="true" value="%{Issue_Date}" cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:select cssClass="textInput" label="Item Name" name="eid.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                      list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List" disabled="+VAL1" value="%{ItemId}">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value=". . ." cssClass="tdSpace"/>

                            <s:textfield label="Issue Quantity" name="eid.isdIssuedQuantity"  value="%{IssQnty_val}" readonly="true" cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:submit action="AddSerialNo" value="Add one more item" align="left"/>
                             <s:submit  action="IssueDone" value="Done" align="centre" />
                           
                        </table>
                    </s:form>

                </div>
                <br>

                <div id ="mainContent" align="center">
                    <div style="border: solid 1px #000000; background: gainsboro">
                        <s:form name="frmIssueSerialDetails" action="SaveIssueSerialDetails" theme="qxhtml">


                            <s:label value="ISSUED SERIAL NUMBERS"  cssClass="pageSubHeading"/>

                            <display:table name="listIssueSerialDetail"
                                           excludedParams="*" export="false" cellpadding="2"
                                           cellspacing="0" id="doc"
                                           requestURI="/Inventory/IssueAndRemoveItemsUserChoice.action" >
                                <display:column  class="griddata" title="Record" sortable="true"  headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="erpmStockReceived.stStockSerialNo" title="Serial Numbers"
                                                    headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="true"/>

                                  <display:column paramId="EISDID" paramProperty="issdId" style="width:10%"
                                                  href="/pico/Inventory/DeleteSerialNo"  sortable="true" value="Delete"
                                                  class="griddata" media="html"  title="Delete" headerClass = "gridheader" />
                                </display:table>

                            </s:form>
                            </div>
                            <br>

                            </div>
                            </div>
                            <div id="footer">
                                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
                            </div>
        </div>

                            </body>
                            </html>
