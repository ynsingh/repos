<%--
    Document   : IssueSerialNumberUserChoice
    Created on : 13 Aug, 2012, 11:53:19 AM
    Author     : Mohd. Manauwar Alam
    I18n By    : Mohd. Manauwar Alam
               : March 2014
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
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;">
                <p align="center"  style="color: #ffffff" class="pageHeading" ><s:property value="getText('Inventory.IssueSerialDetailUserChoice')"   /></p>
               <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="frmIssueSerialNoUserChoice" theme="qxhtml">
                    <table border="0" cellpadding="4" cellspacing="0" align="center">

                    <s:hidden name="Issue_No" />
                    <s:hidden name="Issue_Date" />
                    <s:hidden name="ItemName" />


                    <s:textfield key="Inventory.IssueNo" name="" readonly="true" value="%{Issue_No}" cssClass="textInput" >
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <s:label value=". . ." cssClass="tdSpace"/>
                    <s:textfield key="Inventory.IssueDate" name="" readonly="true" value="%{Issue_Date}" cssClass="textInput">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>



                    <s:select cssClass="textInput" key="Inventory.ItemName" name="eid.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                              list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List" disabled="true" value="%{ItemId}">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:select>

                    <s:label value=". . ." cssClass="tdSpace"/>
                    <s:textfield key="Inventory.IssueQuantity" name="eid.isdIssuedQuantity"  value="%{IssQnty_val}" readonly="true" cssClass="textInput">
                        <s:param name="labelcolspan" value="%{1}" />
                        <s:param name="inputcolspan" value="%{3}" />
                    </s:textfield>

                    <br>
                    </table>
                    </s:form>
                 <s:if test="listStockRecSerialNo.size > 0">
                        <hr>
                        
                        <s:label value="%{getText('Inventory.SerialNoToBeIssued')}" cssClass = "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
             
                
                    <s:form name="frmIssueSerialNoUserChoice" theme="qxhtml">

                    <table width="100%" >

                       
                        <display:table name="listStockRecSerialNo"
                                       excludedParams="*" export="false" cellpadding="8"
                                       cellspacing="0" id="doc"
                                       requestURI="/Inventory/IssueAndRemoveItemsUserChoice.action" >
                            <display:column  class="griddata" title="Record" sortable="true"  headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                  <display:column property="stStockSerialNo" title="Serial Numbers" 
                                                  headerClass="gridheader" maxLength="100"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="true"/>
                                   <display:column paramId="ESRID" paramProperty="stId" style="width:10%" maxLength="100"
                                                    href="/pico/Inventory/IssueSerialNumber"
                                                    headerClass="gridheader" class="griddata" media="html"   >
                                        Issue
                                   </display:column>

                            </display:table>
                    </table>

                  </s:form>
              
                   </s:if>
                        
                         <s:else>
                        <s:form name="frmIssueSerialNoUserChoice" >
                            <br>
                            
                            <s:label value="%{getText('Inventory.NothingFoundToDisplay')}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>

                            
                        </s:form>
                    </s:else>
                             <s:if test="listIssueSerialDetail.size > 0">

                        <br>
                        <hr>
                        <s:label  cssClass= "pageSubHeading" value="%{getText('Inventory.IssuedSerialNoAre')}">
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{11}" />
                        </s:label>

                        <hr>
                <s:form name="frmIssueSerialNoUserChoice" >
           

          

                     <table width="100%" >
                        <display:table name="listIssueSerialDetail"
                                       excludedParams="*" export="false" cellpadding="8"
                                       cellspacing="0" id="doc"
                                       requestURI="/Inventory/IssueAndRemoveItemsUserChoice.action" >
                            <display:column  class="griddata" title="Record" sortable="true"  headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                  <display:column property="erpmStockReceived.stStockSerialNo" title="Serial Numbers" 
                                                  headerClass="gridheader" maxLength="100"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="true"/>
                                <display:column paramId="EISDID" paramProperty="issdId" style="width:10%" maxLength="100"
                                                    href="/pico/Inventory/RemoveSerialNumber"
                                                    headerClass="gridheader" class="griddata" media="html"  >
                                        Remove
                                        </display:column>

                            </display:table>
                                        <br>
</table>
<s:submit  action="IssueDone" value="Done" align="centre" />

                    
</s:form>
              </s:if>
                    <s:else>
                        <s:form name="frmIssueSerialNoUserChoice" >

                            <br>

                            <s:label value="%{getText('Inventory.NothingFoundToDisplay')}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>


                        </s:form>
                    </s:else>
               
                <br>
                </div>
            </div>
                <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>