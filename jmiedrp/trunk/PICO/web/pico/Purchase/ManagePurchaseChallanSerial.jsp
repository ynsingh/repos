<%--
    Document   : ManagePurchaseChallanSerial
    Created on : 23 Aug, 2011, 12:20:10 PM
    Author     : Sajid Aziz
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
        <meta name="author" content="SajidAziz, Jamia Millia Islamia">
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
            <div id ="mainContent" ><br><br>
                          <p align="center"><s:label cssClass="pageHeading" value="MANAGE PURCHASE CHALLAN SERIAL DETAILS" /></p>

                <%--------------------this is a internal indent request form fill by internal users--------------------%>

                <s:actionerror/>

                    <s:form name="FrmPurchaseChallanDetails" action="SavePurchaseChallanSerial">

                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="PCDetail.pcdPcdId" />
                    <s:hidden name="PChallanMast.pcmPcmId" />
                    <s:hidden name="PCSerial.pcsPcsId" />



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
                   label="You are now Adding Serial number for the PC ID NO:" name="defaultPCD" title="" readonly="true"  />

      <s:textfield  cssClass="textInput"  maxLength="50" size="50"
                     label="Equipment Serial Number" name="PCSerial.pcsEquipmentSerialNo" title=""  maxLength="30" size="30" />
     <s:textfield  cssClass="textInput"  maxLength="50" size="50"
                     label="Stock Serial Number" name="PCSerial.pcsStockSerialNo" title=""  maxLength="30" size="30" />

     <br>
     <s:submit cssClass="savebutton"  name="btnSubmit" value="Save"  action="SavePurchaseChallanSerial"/>
                       </s:form>
            </div>

            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


