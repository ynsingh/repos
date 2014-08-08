<%-- 
    Document   : ReceiveIssuedItemsDetails
    Created on : 30 May, 2012, 11:01:28 AM
    Author     : Saeed
    I18n By    : Mohd. Manauwar Alam
               : March 2014
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="html" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


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
        <meta name="author" content="Saeed, Jamia Millia Islamia">
        <meta name="email" content="saeed.coer@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
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
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Inventory.ReceivedIssueItemsDetail')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>
                    <s:form name="FrmReceiveIssuedItemsDetails" action="ReceiveIssuedItemsDetailsAction" >
                        <s:hidden name ="isueDet.isdId"/>
                        <s:hidden name ="ir.isrId"/>
                        <s:hidden name="varIssueReceiveID"/>
                        <s:hidden name="varchar_IssueNo"/>

<table border="0" cellpadding="4" cellspacing="0" align="center">
                        <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO" key="Inventory.ReceiptNo" name="ir.isrReceiptNo" readonly="true" title="" />

                        <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO" key="Inventory.ReceiptDate" name="receiptDate" readonly="true" title="" />

                       
                        <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="50"
                                     key="Inventory.EmployeeName" name="ir.employeemaster.empFname+' '+ir.employeemaster.empMname+' '+ir.employeemaster.empLname" readonly="True" />

                        <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="50"
                                     key="Inventory.Authority" name="ir.committeemaster.committeeName" readonly="True" />

                        <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="50"
                                     key="Inventory.IssueNo" name="ir.erpmIssueMaster.ismIssueNo" readonly="True" />

                        </table>
                        
                    </s:form>
                <s:if test="isueDetList.size > 0">
                        <hr>

                        <s:label value="%{getText('Inventory.ItemDetailsAre')}" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
               
                    <s:form name="frmReceivedIssuedItemsDetailsRows" align="left">
                        <s:hidden name="varIssueReceiveID"/>

                        <table width="100%" >
                            <tr><td>
                                <display:table name="isueDetList" pagesize="10"
                                                   excludedParams="*"  cellpadding="8"
                                                   cellspacing="0" id="doc"
                                                   requestURI="/Inventory/ReceiveItemsAction.Action">

                                    <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name" style="width:30%"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="U.O.M" style="width:10%"
                                                    maxLength="100" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="isdIssuedQuantity" title="Issue Qty"
                                                    maxLength="35" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="isdReceivedQuantity" title="Receive Qty"
                                                    maxLength="35" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>

                                    <display:column paramId="isdId" paramProperty="isdId"
                                                    href="/pico/Inventory/Accept.action"
                                                    headerClass="gridheader" class="griddata" media="html"  value="Accept"/>

                                </display:table>
                                <br></td></tr>
                        </table>

                    </s:form>
                         </s:if>
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