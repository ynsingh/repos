<%--
    Document   : ReceiveItems
    Created on : 15 May, 2012, 11:01:28 AM
    Author     : Saeed
    I18n By    : Mohd. Manauwar Alam
               : March 2014
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
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Inventory.ReceivedIssuedItemSerialDetails')" /></p>
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
                        <table align="center">
                            <tr><td>
                        <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="15"
                                     key="Inventory.IssueNo" name="varchar_IssueNo" readonly="true"/>

                        <s:textfield cssClass="textInputRO" required="" requiredposition="left" maxLength="50" size="50"
                                     key="Inventory.ItemName" name="varchar_Item_Name" readonly="true"/>

                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="6" size="15"
                                     key="Inventory.ReceiveQty" name="varcharIsdReceivedQuantity"  title="" readonly="+ToLockRecdQty"/>
                        </td></tr>
                        <tr><td> 
                        <tr><td align="right">
                                <s:submit theme="simple" key="Inventory.Save" action="SaveReceiveDetails"/>
                           
                                <s:submit theme="simple" key="Inventory.RecallAllItems" action="ResetReceiveDetails" disabled="+dsbl"/>
                        
                        <tr><td> 
                        </table>
                    </s:form>

                        
          
                <s:if test="issSerialDetList.size > 0">
                        <hr>

                        <s:label value="%{getText('Inventory.ItemDetailsAre')}" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
              
               
                    <s:form name="FrmReceiveItems" id="ReceiveItems_Id" action="SaveReceiveDetails" align="center">

                        <table width="100%">
                            <tr><td>
                                    <display:table name="issSerialDetList" 
                                                   excludedParams="*"  cellpadding="8"
                                                   cellspacing="0" id="doc"
                                                   requestURI="/Inventory/ReceiveItemsAction.Action">
                                        <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="erpmStockReceived.stStockSerialNo" title="Serial No." 
                                                     headerClass="gridheader"
                                                    class="griddata" sortable="true">
                                    </display:column>
                                    <display:column paramId="issdId" paramProperty="issdId" style="width:10%"
                                                    href="/pico/Inventory/ExcludeItems.action"
                                                    headerClass="gridheader" class="griddata" media="html" value="Exclude">
                                    </display:column>

                                </display:table>
                                
                                </td></tr>
                                </table>
                    </s:form>
                         </s:if>
                        <br>
                        
                </div>
                <br>
            </div>
            
            <div id="footer">
                    <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>



