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
                <br>
                <p align="center" class="pageHeading"><s:label value="Form for Add Indent Items to Your PO" /></p>
                <p  align="center" Class="mymessage" ><s:property value="message"  /> </p>
                <%--------------------this is a internal indent request form fill by internal users--------------------%>
                  <s:actionerror />

                  <s:form name="FrmIndentitems" action="SaveIndentItemsToPO"  validate="true">
                   <s:hidden name="pomaster.pomPoMasterId" />
                   <s:hidden name="erpmindtdet.indtDetailId" />
                   <%--<s:hidden name ="erpmindtmast.indtIndentId" />--%>




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
                   label="You are now Adding Indent Items in your in PO No:" name="defaultPOM" title="" readonly="true"/>

    <s:select cssClass="textInput" label="Institution"  required="true" name="pomaster.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
     onchange="getSubinstitutionList('SaveIndentItemsToPO_pomaster_institutionmaster_imId', 'SaveIndentItemsToPO_pomaster_subinstitutionmaster_simId');"/>
    <s:select cssClass="textInput" required="true" label="College/Faculty/School" name="pomaster.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
     onchange="getDepartmentList('SaveIndentItemsToPO_pomaster_subinstitutionmaster_simId', 'SaveIndentItemsToPO_pomaster_departmentmaster_dmId');"/>
    <s:select cssClass="textInput" required="true" label="Department Name" name="pomaster.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="DefaultDepartment"/>
    <s:select cssClass="textInput" required="true" label="Indent ID" name="erpmindtmast.indtIndentId" headerKey="" headerValue="-- Please Select --" list="IndentIDList" listKey="indtIndentId" listValue="indtIndentId"/>
    <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
    label="Currency For Indent:" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="" readonly="true"/>
    <br>
    <div id ="mainContent" align="center">
        <br>
             <s:form name="frmitem">
                 <table width="70%" border="2" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indtitemlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManagePOMaster.action">
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
                                    class="griddata"  sortable="true" />
                       <display:column property="erpmItemRate.irdRate" title="Item_Rate"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata"  sortable="true" />

                    <%--  <display:column title="Add To PO"  >
                            <s:checkbox  id="check" name="selected"  theme="simple" onclick="" />

                       </display:column>--%>
                       <display:column paramId="indtdetailId" paramProperty="indtDetailId"
                                       href="/pico/PrePurchase/SaveIndentItemsToPO.action?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"
                                       headerClass="gridheader" class="griddata" media="html" title="Add To PO">
                                       Add To PO
                        </display:column>

                                       </display:table>



                <br></td></tr>
                </table>
                     <s:submit   theme="simple" cssClass="savebutton" name="btnSubmit" value="Save All Indent Items in PO" action="SaveAllIndentItemsToPO"/>

                     <s:submit theme="simple" cssClass="savebutton" name="btnSubmit" value="Fetch Selected Indent Items" action="FetchSelectedIndentItems" />

                     <s:submit theme="simple" cssClass="savebutton" name="btnSubmit" value="Fetch All Indent Items" action="FetchAllIndentItems"/>
                 <br>

        </s:form>
        <br><br>

            </div>

                               </td>
                </tr> <tr>
                <td align="right">
            <%--<s:submit theme="simple" name="btnAddItems" value="Add More Items"  action="ManageItems" cssClass="savebutton" />--%>
                    </td>
                    </tr>
                   </tbody>
        </table>
                  <%--  <s:submit cssClass="savebutton" name="btnSubmit" value="Save All Indent Items in PO" action="SaveIndentItemsToPO"/>--%>

                     <s:url action="POTerms"   id="NavigatetoURL"></s:url>
           <p align="center"><a href='<s:property value="NavigatetoURL" />' ><big>--->GO TO PO TERMS AND CONDITIONS</big></a></p>
                </s:form>

            </div>

            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
