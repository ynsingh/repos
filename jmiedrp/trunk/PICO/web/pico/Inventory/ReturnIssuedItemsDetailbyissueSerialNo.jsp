<%--   I18n By    : Mohd. Manauwar Alam
                   : March 2014
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
        <meta name="author" content=", Jamia Millia Islamia">
        <meta name="email" content="@gmail.com">
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
            <div id ="mainContent" ><br>
                <br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
            <s:property value="message"></s:property>
            <div style="background-color: #215dc6;  " >
                <p align="center" class="pageHeading" style="color:  #ffffff"><s:property value="getText('Inventory.ManageReturnIssuedItems')" /></p>
                
                <s:if test="%{VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod!=null}">
                    <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod" /></p>
                </s:if>

                <s:if test="%{messageInItemSerialNoPage!=null}">
                    <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="messageInItemSerialNoPage" /></p>
                </s:if>
                    <p align="center" class="mymessage" style="color:  #ffff99 ">   <s:property value="message"></s:property></p>
                    </div>
                <%--------------------this is a Purchase Order  form --------------------%>

                <s:actionerror />
<div style="border: solid 1px #000000; background:  gainsboro">
                <s:form name="FrmReturnIssuedItems" action="ReturnIssuedItemsAction" >
                    <p align="left" class="pageMessage"></p>
                    <s:hidden name ="erpmirm.irmId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>

<%--                                    <sx:datetimepicker name="erpmirm.irmReturnDate" label="Return Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{ReturnDate}" disabled="true"/>
--%>
                                   <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.ReturnDate" name="returnDate" value="%{returnDate}" disable="true"/>

                                    <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.ReturnNo" name="erpmirm.irmReturnNo" value="%{ReturnNo}" disable="true"/>

                                    <s:select cssClass="textInput" key="Inventory.ReturnType" name="erpmirm.ErpmIssueReturnMaster.irmReturnType" headerKey="" headerValue="%{returntype}" list="#{'U':'Returned After USE','R':'Returned After REPAIR'}"  value="%{Returntype}" disabled="true"/>

                                    <s:select id="ItemSerialNoId" cssClass="textInput" key="Inventory.ItemSerialNo" name="erpmIssueSerialDetail.erpmStockReceived.stId" headerKey="" headerValue="-- Please Select --" list="ErpmIssueSerialDetailListforitemSerialNo"
                                              listKey="erpmStockReceived.stId" listValue="erpmStockReceived.stStockSerialNo" disabled="false"/>
                                    <s:if test="%{condvarforItemSerialNoPage!=null}">
                                        <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.ReturnQuantity" name="returnQuantityWhenItemSerialNoZero" value="%{returnQuantityWhenItemSerialNoZero}" disabled="true"/>
                                        <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.SelectedItemSerialNo" name="ItemSerialNo" value="%{ItemSerialNo}" disabled="true"/>

                                    </s:if>

                                </td>
                            </tr> <tr>
                                <td align="right">
                                    <s:submit theme="simple" name="bthReset" key="Inventory.BackToMainPage" action="BacktoErpmReturnIssuedItemPage" />
                                    <s:submit theme="simple" name="bthReset" key="Inventory.ReceiveBack" action="DoneReturnIssuedItemsAction" />
                                    <s:submit theme="simple" name="bthReset" key="Inventory.ShowDetails" action="SaveReturnIssuedItemsbyShowDetail"  />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </s:form>
                <s:if test="%{ErpmIssueSerialDetailList.size > 0}">
                    <hr>
                        
                        <s:label value="%{getText('Inventory.AvailableItemsForReturnAre')}" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
                    
                        <s:form name="frmReturnIssuedItemsBrowse" >
                            
                            <s:hidden name ="erpmirm.irmId" />
                            <s:hidden name ="%{ismId}" />
                            <s:hidden name ="%{irmId}" />
                            <s:hidden name ="%{stId}" />

                           <table width="100%" >

                                <display:table name = "ErpmIssueSerialDetailList"
                                               excludedParams="*" export="false" cellpadding="8"
                                               cellspacing="0" id="doc"
                                               requestURI="/Inventory/ReturnIssuedItemsAction.action" >
                                    <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                        <c:out> ${doc_rowNum}
                                        </display:column>

                                        <display:column property="erpmStockReceived.stStockSerialNo" title="Item Serial No"
                                                         headerClass="gridheader" maxLength="100"
                                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:30%"  sortable="true"/>
                                   <display:column property="erpmIssueDetail.erpmIssueMaster.ismIssueNo" title="Issue No"
                                                         headerClass="gridheader" maxLength="100"
                                                        class="griddata" style="width:10%" sortable="true"/>
                                         <display:column property="erpmIssueDetail.erpmItemMaster.erpmimDetailedDesc" title="Item Name"
                                                        maxLength="100" headerClass="gridheader" style="width:15%"
                                                        class="griddata" sortable="true"/>
                                       <display:column property="issdReturned" title="Return Quantity" style="width:15%"
                                                          maxLength="100" headerClass="gridheader"
                                                        class="griddata" sortable="true"/>
                                        <display:column paramId="issdId" paramProperty="issdId" style="width:10%"
                                                        href="/pico/Inventory/ReturnIssuedItemsReceivedBackOk.action"
                                                        headerClass="gridheader" class="griddata" media="html" value="ReceivedBacK">
                                        </display:column>
                                    </display:table>
                            </table>
                        </s:form>
                   
                </s:if>
                        
                         <s:else>
                        <s:form name="FrmReturnIssuedItems" >
                            <br>
                            
                            <s:label value="%{getText('Inventory.NoItemsAvailableToReturn')}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>

                            
                        </s:form>
                    </s:else>
                <s:if test="erpmIssueReturnDetailList.size > 0">
                    <br>
                        <hr>
                        <s:label  cssClass= "pageSubHeading" value="%{getText('Inventory.AlreadyReturnedItemsAre')}">
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{11}" />
                        </s:label>

                        <hr>
                        <s:form name="FrmReturnIssuedItems" >
                           
                            <s:hidden name ="erpmirm.irmId" />
                            <s:hidden name ="%{ismId}" />
                            <s:hidden name ="%{irmId}" />
                            <s:hidden name ="%{stId}" />

                            <table width="100%" >

                                <display:table name = "erpmIssueReturnDetailList"
                                               excludedParams="*" export="false" cellpadding="8" defaultorder="ascending" sort="list"
                                               cellspacing="0" id="doc"
                                               requestURI="/Inventory/ReturnIssuedItemsReceivedBackOk.action">
                                    <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                        <c:out> ${doc_rowNum}
                                        </display:column>

                                        <display:column property="erpmStockReceived.stStockSerialNo" title="Item Serial No"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:40%"  sortable="true"/>
                                        <display:column property="erpmIssueMaster.ismIssueNo" title="Issue No" style="width:15%"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="griddata"  sortable="true"/>
                                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name" style="width:15%"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="griddata" sortable="true" />
                                        <display:column property="irmdReturnQuantity" title="Return Quantity" style="width:15%"
                                                        maxLength="100" headerClass="gridheader"
                                                        class="griddata" sortable="true" />

                                    </display:table>
                            </table>
                        </s:form>
                    
                </s:if>
                        <s:else>
                        <s:form name="FrmReturnIssuedItems" >

                            <br>

                            <s:label value="%{getText('Inventory.NoItemsInAlreadyReturnedList')}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>


                        </s:form>
                    </s:else>

                    <br>
            </div>
 <s:actionerror />
            </div>
            <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
