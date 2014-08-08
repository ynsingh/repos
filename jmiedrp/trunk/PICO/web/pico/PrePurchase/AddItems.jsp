<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
        <s:head />
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
<%--                 <p align="center" class="pageHeading"><s:label value="Indent Items" /></p>
                 <p  align="center" Class="mymessage" ><s:property value="message"  /> </p> --%>
                 <div style="background-color: #215dc6;  " >
                 <p align="center" class="pageHeading" style="color:  #ffffff"><s:label value="Step 2 of 3 (Populate Indent)" /></p>
                 <p  align="center" Class="mymessage" style="color:  #ffff99"><s:property value="message"  /> </p>
                 </div>
                 <s:actionerror />

                 <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                 </s:bean>

<%--                 <p align="left" class="pageHeading">Step 2 of 3 (Populate Indent)</p>

--%>
                 <s:form name="FrmIndentitems" action="SaveIndentItems" theme="qxhtml">
                    <s:hidden name ="erpmindtmast.indtIndentId" />
                    <s:hidden name ="erpmindtdet.indtDetailId" />
                    <s:hidden name ="defaultCurrency" />

                    <s:property value=""/>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                label="Indent No" name="defaultIndent" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="10" size="10"
                                label="Indent Currency" name="defaultCurrencyName" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{1}" />
                    </s:textfield>

                    <s:textfield cssClass="textInputRO"  maxLength="100" size="100"
                                label="Indent Title" name="defaultIndentTitle" title="" readonly="true">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{4}" />
                    </s:textfield>

                    <s:label value="...." cssClass="tdSpace">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{8}" />
                    </s:label>

                    <s:label value="Please Add Item Details" cssClass= "mysubheading">
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{8}" />
                    </s:label>

                    <s:select   cssClass="textInput" label="Item Name" name="erpmindtdet.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                                required="true" list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc"                                 
                                ondblclick="getitemList_2('SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:select>

                    <s:textfield  cssClass="textInput"  maxLength="5" size="30" label="Quantity" name="erpmindtdet.indtQuantity" onkeypress="return isNumberKey(event)"
                                  onchange="getItemRateAndUOP(  'SaveIndentItems_erpmindtdet_erpmItemMaster_erpmimId',
                                                                'SaveIndentItems_selectedItemRateCurrency',
                                                                'SaveIndentItems_erpmindtdet_indtQuantity',
                                                                'SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId',
                                                                'SaveIndentItems_UOP');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>
                   
                   <s:textfield requiredposition="left" maxLength="5" size="30" cssClass="textInputRO" label="Unit of Purchase" name="UOP"  readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:select  cssClass="textInput" label="Item Rates" name="erpmindtdet.erpmItemRate.irItemRateId" headerKey="" headerValue="-- Please Select --" required="true"
                               list="itemRateList" listKey="irItemRateId" listValue="irdRate1" value="selectedItemRate"
                               onchange="showItemRateDetails('SaveIndentItems_erpmindtdet_erpmItemRate_irItemRateId',
                                                             'SaveIndentItems_selectedItemRate',
                                                             'SaveIndentItems_selectedItemRateValidFrom',
                                                             'SaveIndentItems_selectedItemRateValidTo',
                                                             'SaveIndentItems_selectedItemRateCurrency',
                                                             'SaveIndentItems_erpmindtdet_indtQuantity',
                                                             'SaveIndentItems_erpmindtdet_indtApproxcost',
                                                             'SaveIndentItems_minOrderQuantity',
                                                             'SaveIndentItems_maxOrderQuantity');">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:select>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Item Rate" name="selectedItemRate" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>                    

                    <s:textfield   requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                   label="Currency" name="selectedItemRateCurrency" readonly="true">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Rate Valid From" name="selectedItemRateValidFrom" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Rate Valid To" name="selectedItemRateValidTo" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                     </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Minimum Order Quantity" name="minOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield>

                    <s:textfield    requiredposition="left" maxLength="5" size="30" cssClass="textInputRO"
                                    label="Maximum Order Quantity" name="maxOrderQuantity" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}" />
                                    <s:param name="inputcolspan" value="%{2}" />
                     </s:textfield>

                    <s:textfield  maxLength="10" size="30"  label="(Approximate) Cost" name="erpmindtdet.indtApproxcost"
                                  title="" onkeypress="return isNumberKey(event)">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                    </s:textfield>
               
                    <s:textarea rows="3" cols="50" label="Purpose" name="erpmindtdet.indtPurpose" title="">
                                <s:param name="labelcolspan" value="%{2}" />
                                <s:param name="inputcolspan" value="%{6}" />
                    </s:textarea>

                    <s:label />
                    <s:label />

                    <s:submit name="btnSubmit" value="Add Item" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="left" />
                    </s:submit>

                    
                    <s:submit name="btnBrowse" value="Browse Indent"  action="BrowseIndent" cssClass="savebutton" />
                    <s:submit name="btnBrowse" value="Go To Indent Screen"  action="ManageIndent" cssClass="savebutton" />                    
                    <s:submit name="btnSubmitIndent" value="Submit Indent"  action="SubmitIndent" cssClass="savebutton" />

                </s:form>
            </div>

            <div id ="mainContent" align="center">
             <s:form name="frmitem">
                 <table width="70%" border="2" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indentitemlist" pagesize="15"
                                excludedParams="*"  export="true" cellpadding="0"
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
                        <display:column property="indtPurpose" title="Purpose"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="false"/>
                        <display:column paramId="indtDetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/EditIndentDetailAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="indtDetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                        </display:column>

<%--                        <display:column paramId="indentDetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/EditManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="indentDetailId" paramProperty="indtDetailId"
                                    href="/pico/PrePurchase/DeleteManageIndentAction.action?erpmindtmast.indtIndentId=${param['erpmindtmast.indtIndentId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                        </display:column>

--%>





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
