<%--Document   : IndentForm
    Created on : April 11, 2011, 12:28:32 PM
    Author     : Sajid Aziz--%>
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
        <script language="JavaScript"  type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
                <%@include  file="../Administration/header.jsp"%>
               <%-- <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>--%>
            </div>
            <div id="sidebar1">


                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="left">
                <br><br>
                <p align="center" class="pageHeading"><s:label value="FORM FOR MANAGING ITEMS IN THE INDENT" /></p>
                <p  align="center" Class="mymessage" ><s:property value="message"  /> </p>
                <%--------------------this is a internal indent request form fill by internal users--------------------%>
                  <s:actionerror />

                <s:form name="FrmIndentitems" action="SaveIndentItems">
                 <%--  <s:hidden name="erpmindtdet.indtDetailId" />--%>
                   <s:hidden name ="erpmindtmast.indtIndentId" />
                   <s:hidden name ="defaultCurrency" />

                   <table border="0" cellpadding="4" cellspacing="0">
                    <tbody>
                    <tr>
                    <td> 


                     <%--use where only number is required for input other than number it does not accept value--%>
             <script type='text/javascript'>
     function isNumberKey(evt)
     {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57) )
        return false;
     return true;
     }
     </script>

                   

               <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
                  label="You are now Adding Items For The Indent No:" name="defaultIndent" title="" readonly="true"/>
               <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
                  label="Indent Currency" name="defaultCurrencyName" title="" readonly="true"/>
               <br><s:label value="Please Add Item Details" cssClass= "mysubheading"/>

              <s:select cssClass="textInput" label="Item Name" name="erpmindtdet.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" required="true"
               list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc"  value="selectedItem"
               onchange="getItemRateAndUOP('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId','SaveIndentItems_defaultCurrency','SaveIndentItems_UOP','SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId');"
               ondblclick="getitemList('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId');"/>

               <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
               label="Unit of Purchase" name="UOP"  readonly="true"/>

              <s:select cssClass="textInput" label="Item Rates" name="erpmindtdet.erpmItemRate.irItemRateId" headerKey="" headerValue="-- Please Select --" required="true"
               list="itemRateList" listKey="irItemRateId" listValue="irdRate1" value="selectedItemRate"
               onchange="showItemRateDetails('SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId','SaveIndentItems_selectedItemRate',
                         'SaveIndentItems_selectedItemRateValidFrom', 'SaveIndentItems_selectedItemRateValidTo',
                         'SaveIndentItems_selectedItemRateCurrency','SaveIndentItems_erpmindtdet_indtApproxcost');"/>

               <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
               label="Item Rate" name="selectedItemRate" readonly="true" />

               <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
               label="Currency" name="selectedItemRateCurrency" readonly="true"/>


               <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
               label="Rate Valid From" name="selectedItemRateValidFrom" readonly="true"/>

            <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
               label="Rate Valid To" name="selectedItemRateValidTo" readonly="true"/>

            <s:textfield  maxLength="10" size="30"  label="Approximate Cost" name="erpmindtdet.indtApproxcost" title="" onkeypress="return isNumberKey(event)"/>
            <s:textfield  cssClass="textInput"  maxLength="5" size="30" label="Quantity" name="erpmindtdet.indtQuantity" onkeypress="return isNumberKey(event)"/>
               
            <s:textarea rows="3" cols="50"
               label="Purpose" name="erpmindtdet.indtPurpose" title=""  />
               
               
                 </td>
                </tr> <tr>
                <td align="right">

                    <s:submit theme="simple" cssClass="savebutton" name="btnSubmit" value="Save Items"  />
 </td>              <td>
                    <s:submit theme="simple" name="btnBrowse" value="Browse Indent"  action="BrowseIndent" cssClass="savebutton" />
                    <s:submit theme="simple" name="btnBrowse" value="Go To Indent Screen"  action="ManageIndent" cssClass="savebutton" />
                    <s:submit theme="simple" name="btnAddItems" value="Add More Items"  action="ManageItems" cssClass="savebutton" />

                    
                    </td>
                    </tr>
                   </tbody>
        </table>
                </s:form>
            </div>
             <div id ="mainContent" align="center">
             <s:form name="frmitem">
                 <table width="70%" border="2" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indtitemlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManageIndent.action">
                        <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="ItemName"
                                    maxLength="100" headerClass="gridheader" style="width:30%"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                     sortable="true"/>
                        <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="UOP"
                                     maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                        <display:column property="indtQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                        <display:column property="indtApproxcost" title="Approx_Cost"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                        <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/EditManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
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
