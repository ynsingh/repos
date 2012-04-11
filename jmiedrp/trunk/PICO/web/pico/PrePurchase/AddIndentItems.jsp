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
                <p align="center" class="pageHeading"><s:label value="FORM FOR Adding Items To PO" /></p>
                <p  align="center" Class="mymessage" ><s:property value="message"  /> </p>
                <%--------------------this is a internal indent request form fill by internal users--------------------%>
                  <s:actionerror />

                <s:form name="FrmAddIndentitems">
                 <%--  <s:hidden name="erpmindtdet.indtDetailId" />--%>
                  <%-- <s:hidden name ="erpmindtmast.indtIndentId" />
                   <%--<s:hidden name ="defaultCurrency" />--%>

                   <table border="0" cellpadding="8" cellspacing="2">
                    <tbody>
                    <tr>
                    <td>

                       
                      
                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Department" name="pomaster.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30" />

                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Currency" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="" readonly="true" maxLength="30" size="30" />

                    </td>
                    </tr>
                   </tbody>
        </table>
        </s:form>
            </div>

                              
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        
    </body>
</html>
    