<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="header">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <p align="center"><s:label value="REPORT" cssClass="pageHeading"/></p>
            <p align="center"><s:property value="message" /></p>
            <div id ="mainContent">
                <s:form name="frmReport" action=""  validate="true">

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td><br>

                                  <%--  <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 label="Indent"  readonly="true" name="bhm.bhmName" title="Enter Capital Item Category Name"  cssClass="textInput"/>--%>
                                    <s:label name="IndentId"  label="Indent No" cssClass="textInput"/>
                                <s:label name="imName"  label="Institution Name" cssClass="textInput"/>
                                    <s:label name="smName"  label="College/Faculty Name" cssClass="textInput"/>
                                  <s:label name="dmName"  label="Department Name" cssClass="textInput"/>
                                <s:label name="bhmName"  label="Budget Head Name " cssClass="textInput"/>
                                  <s:label name=" inDate"  label="Indent Date " cssClass="textInput"/>
                               
         <s:label name="PreparedBy"  label="Indent Prepared By" cssClass="textInput"/>
          <s:label name="ForwardedTo"  label="Forwarded To" cssClass="textInput"/>
                                </td>
                            </tr> <tr>
                                <td>
                       
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmBudgetHeadBrowse">
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="indtDetail" pagesize="15"
                                excludedParams="*" export="" cellpadding="0"
                               cellspacing="0" summary="true"
                               requestURI="/Administration/ManageExportAction.action">

                       <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:30%" sortable="true" />
                      <display:column property="erpmItemMaster.erpmGenMaster.erpmgmEgmDesc" title="UOP"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true" />
                       <display:column property="indtQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                       <display:column property="indtApproxcost" title="ApproxCost"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                         <display:column property="indtPurpose" title="Purpose"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                                        </display:table>
                <br></td></tr>
                </table>
             </s:form>
                 <br>
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>