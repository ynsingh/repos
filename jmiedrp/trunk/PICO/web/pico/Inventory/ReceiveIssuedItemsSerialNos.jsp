<%--
    Document   : ReceiveItems
    Created on : 15 May, 2012, 11:01:28 AM
    Author     : Saeed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="html" uri="/struts-tags"%>
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
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
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

            <p align="center" class="pageMessage"><s:property value="message" /></p>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{2}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">RECEIVE ISSUED ITEM SERIAL DETAILS</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="FrmReceiveItems" id="ReceiveItems_Id" action="SaveReceiveDetails" theme="qxhtml">

                        <%--  <s:hidden name ="isueDet.isdId" /> --%>
                        <s:hidden name ="isueSerialDet.issdId" />
                        <s:hidden name ="varIssueDetailID"/>
                        <s:hidden name="varIssueReceiveID"/>

                        <%--    use where only number is required for input other than number it does not accept value--%>
                        <script type='text/javascript'>
                            function isNumberKey(evt)
                            {
                                var charCode = (evt.which) ? evt.which : event.keyCode
                                if (charCode > 31 && (charCode < 48 || charCode > 57) )
                                    return false;
                                return true;
                            }
                        </script>


                        <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="15"
                                     label="IssueNo" name="varchar_IssueNo" readonly="true"/>

                        <s:textfield cssClass="textInputRO" required="" requiredposition="left" maxLength="50" size="50"
                                     label="Item Name" name="varchar_Item_Name" readonly="true"/>

                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="6" size="15"
                                     label="Receive Qty" name="varcharIsdReceivedQuantity"  title="" readonly="+ToLockRecdQty"/>

                        <tr><td> &nbsp; </td></tr>
                        <tr><td align="right">
                                <s:submit theme="simple" value="Save" action="SaveReceiveDetails"/>
                            </td>
                            <td>
                                <s:submit theme="simple" value="Recall All Items" action="ResetReceiveDetails" disabled="+dsbl"/>
                            </td>
                        </tr>
                        <tr><td> &nbsp; </td></tr>
                    </s:form>

                        </div>
            </div>
                <br>
                <div id ="mainContent" align="center">
               
                    <s:form name="FrmReceiveItems" id="ReceiveItems_Id" action="SaveReceiveDetails" align="center">

                        <table border="1" cellspacing="0" cellpadding="5" align="center">
                            <tr><td>
                                    <display:table name="issSerialDetList" pagesize="10"
                                                   excludedParams="*" export="true" cellpadding="0"
                                                   cellspacing="0" id="doc"
                                                   requestURI="/Inventory/ReceiveItemsAction.Action">
                                        <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="erpmStockReceived.stStockSerialNo" title="Serial No."
                                                     headerClass="gridheader"
                                                    class="griddata" sortable="true">
                                    </display:column>
                                    <display:column paramId="issdId" paramProperty="issdId"
                                                    href="/pico/Inventory/ExcludeItems.action"
                                                    headerClass="gridheader" class="griddata" media="html" value="Exclude">
                                    </display:column>

                                </display:table>
                                <br>
                                </td></tr>
                                </table>
                    </s:form>
                        <br>
                </div>
                    
            </div>
            <div id="footer">
                    <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>



