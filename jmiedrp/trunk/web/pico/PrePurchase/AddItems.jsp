<%--
    Document   : IndentForm
    Created on : April 11, 2011, 12:28:32 PM
    Author     : Sajid Aziz
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
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <p align="center" class="pageHeading"><s:label value="INTERNAL INDENT REQUEST FORM" /></p>
                <p  align="center" Class="mymessage" ><s:property value="message"  /> </p>
                <%--------------------this is a internal indent request form fill by internal users--------------------%>
                <s:form name="FrmIndentitems" action="SaveIndentItems"  validate="true"   >
                   <s:hidden name="erpmindtdet.indtDetailId" />
                   <s:hidden name ="erpmindtmast.indtIndentId" />
                   <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr><td><br>

               <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
               label="You are now Adding Items For The Indent No:" name="defaultIndent" title="" readonly="true"/>
               <br><s:label value="Please Add Item Details" cssClass= "mysubheading"/>
               <s:select cssClass="textInput" label="Item Name" name="erpmindtdet.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
               list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" onchange="getUOP('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId','SaveIndentItems_UOP')"/>
               <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInputRO"
               label="Quantity of Purchase" name="UOP" title=""  readonly="true"/>
               <p align="center"><s:label value="Rate... " /></p>
               <s:textfield required="true" requiredposition="left" maxLength="50" size="30"
               label="Quantity" name="erpmindtdet.indtQuantity" title="" />
                <s:textfield required="true" requiredposition="left" maxLength="50" size="30"
               label="Approx Cost" name="erpmindtdet.indtApproxcost" title="" />
                <s:textarea required="true" requiredposition="left" rows="3" cols="50"
               label="Purpose" name="erpmindtdet.indtPurpose" title=""  />
                <tr> <td <s:submit  cssClass="savebutton" name="btnSubmit" value="Save Items"  /></td>
                    <td  <s:submit  name="btnClear" value="Clear"   action="ClearIndentDetail" cssClass="savebutton"/></td>
                    </tr>

               <tr><td><br></td><td><br></td></tr>
               </tbody>
               </table>
                </s:form>
            </div>
             <div id ="mainContent" align="center">
             <s:form name="frmitem">
                 <table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indtitemlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManageIndent.action">
                        <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="ItemName"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    style="width:20%" sortable="true"/>
                        <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="UOP"
                                     maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>
                        <display:column property="indtQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>
                        <display:column property="indtApproxcost" title="Approx Cost"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>
                        <display:column property="indtPurpose" title="Purpose"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>

                        <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/EditManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/edit.jpg" border="0" alt="Edit"  style="cursor:pointer;"/>
                        </display:column>
                        <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
                        </display:column>








                    </display:table>
                <br></td></tr>
                </table>
        </s:form>
                 <br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
