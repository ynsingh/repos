<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
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
              <%--  <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>                               --%>
            </div>
            <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>
           
            <!-- *********************************End Menu****************************** -->
                <br>
                <br>  
                
            <div id ="mainContent" align="center">
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">SUPPLIERS LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>                                
                <div style="border: solid 1px #000000; background: gainsboro">
	        <%--        <table style="height:19em;" width="100%"><tr><td valign="top"> --%>
                <s:form name="frmSupplierBrowse">                    
                         <table width="100%" border="0" cellspacing="0" cellpadding="0" align="right">
                        <display:table name="erpmsmList" pagesize="15"
                                       excludedParams="*" export="true" cellpadding="0"
                                       cellspacing="0" id="doc"
                                       requestURI="/PrePurchase/BrowseSupplier.action">
                             <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader" style="width:5%" >
                                <c:out> ${doc_rowNum}
                            </display:column>
                                    
                            <display:column property="institutionmaster.imShortName" title="Institution"
                                            maxLength="35" headerClass="gridheader"
                                            class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:5%" sortable="true"/>
                            
                            <display:column property="smName" title="Name"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:15%" sortable="true"/>
                            
                            <display:column property="erpmGenMasterBySmOwnershipType.erpmgmEgmDesc" title="Supplier Type"
                                            maxLength="20" headerClass="gridheader"
                                            class="griddata" style="width:15%" sortable="true"/>
                            
                            <display:column property="erpmGenMasterBySmSupplierType.erpmgmEgmDesc" title="Supplier Type"
                                            maxLength="20" headerClass="gridheader"
                                            class="griddata" style="width:15%" sortable="true"/>
                            
                            <display:column property="smDealsWith" title="Deals With"
                                            maxLength="20" headerClass="gridheader"
                                            class="griddata" style="width:15%" sortable="true"/>
                            
                            <display:column paramId="SMID" paramProperty="smId"
                                            href="/pico/PrePurchase/EditSupplier.action" style="width:5%" 
                                            headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                            Edit
                            </display:column>
                            
                            <display:column paramId="SMID" paramProperty="smId" style="width:5%" 
                                            href="/pico/PrePurchase/DeleteSupplier.action"
                                            headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                            Delete
                            </display:column>

                            <display:column paramId="SMID" paramProperty="smId" style="width:10%" 
                                            href="/pico/PrePurchase/ManageSupplierAddress.action"
                                            headerClass="gridheader" class="griddata" media="html" title="Address">
                                            Edit Address
                            </display:column>
                        </display:table>                    
<%--                </s:form>
               </td></tr></table>
                </div>
                <br> --%>
                        </table>

                    </s:form>
                    <br>
        </div>
                <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        
    </body>
</html>
