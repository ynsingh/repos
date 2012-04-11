<%--
    Document   : ManagePurchaseChallanDetail
    Created on : 24 Jun, 2011, 3:20:50 PM
    Author     : Tanvir Ahmed and sajid
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
        <title> ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/gen_validatorv4.js"></script>

        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tanvirahmed74@gmail.com">
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
            <div id ="mainContent" ><br><br>
                          <p align="center"><s:label cssClass="pageHeading" value="MANAGE PURCHASE CHALLAN DETAILS" /></p>

                <%--------------------this is a internal indent request form fill by internal users--------------------%>

                <s:actionerror/>

                    <s:form name="FrmPurchaseChallanDetails" action="SavePurchaseChallanDetail">

                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                   <s:hidden name ="PCDetail.pcdPcdId" />
                    <s:hidden name="PChallanMast.pcmPcmId" />



                    <%--use where only number is required for input other than number it does not accept value--%>
                          <script type='text/javascript'>
     function isNumberKey(evt)
     {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
     return false;
     return true;
     }
     
     function checkDropdown(choice) {
     var error = "";
     if (choice == 0) {
     error = "You didn't choose an option from the drop-down list.\n";
     }
     return error;
}

 var frmvalidator  = new Validator("FrmPurchaseChallanDetails");
 frmvalidator.addValidation("Quantity","req","Please enter Quantity");

</script>
    

     <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
                   label="You are now Adding Details for the PC ID NO:" name="defaultPCM" title="" readonly="true"/>
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Challan No" name="PChallanMast.pcmChallanNo" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Insitute Name" name="PChallanMast.institutionmaster.imName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="College/Faculty" name="PChallanMast.subinstitutionmaster.simName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Department" name="PChallanMast.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30" />

     <br><s:label value="Please Add  Item Details For Your Purchase Challan" cssClass= "pageSubHeading"/>
     <s:select cssClass="textInput" label="Item Name" name="PCDetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="itemlist" listKey="erpmimId" listValue="erpmimItemBriefDesc"
               ondblclick="getitemList('SavePurchaseChallanDetail_PCDetail_erpmItemMaster_erpmimId');"/>
     <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="8" size="10"
                    label="Quantity" name="PCDetail.pcdRecvQuantity" title="" onkeypress="return isNumberKey(event)"/>
     <br>
     <s:submit cssClass="savebutton"  name="btnSubmit" value="Add Items" />
                       </s:form>
            </div>

           <div id ="mainContent" align="center">
             <s:form name="FrmPurchaseChallanDetails">
                 <table width="40%" border="1" cellspacing="0" cellpadding="4" align="center" >
                    <tr><td>
                    <display:table name="PCDetailslist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/Purchase/ManagePurchaseChallanAction.action">
                        <display:column  class="griddata" title="Record"  sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>



                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                    maxLength="80" headerClass="gridheader" 
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    sortable="true"  href=""/>


                         <display:column property="pcdRecvQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    sortable="true"  href=""/>


                        <display:column paramId="PCDetailsId" paramProperty="pcdPcdId"
                                    href="/pico/Purchase/DeletePurchaseChallanDetail.action?PChallanMast.pcmPcmId=${param['PChallanMast.pcmPcmId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                        </display:column>


                       <display:column paramId="PCDetailsId" paramProperty="pcdPcdId"
                                    href="/pico/Purchase/EditPurchaseChallanDetail.action?PChallanMast.pcmPcmId=${param['PChallanMast.pcmPcmId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit">
                                   Edit
                        </display:column>

                  </display:table>
                </td></tr>
                </table>
                <br>
        </s:form>
        </div>

           <s:url action="PurchaseChallanSerial"   id="NavigatetoURL"></s:url>
           <p align="center"><a href='<s:property value="NavigatetoURL" />' ><big>--->GO TO ITEM SERIAL NUMBER</big></a></p>
                     <br>


                    <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>

