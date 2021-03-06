
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page  import="org.displaytag.decorator.*" %>

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
            <div id ="mainContent" align="center">
              <br><br>
               

               <div style ="background-color: #215dc6;">
                    <p align="center"  class="pageHeading" style="color: #ffffff"><s:label value="RETURN ISSUED ITEM LIST" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <%--------------------this is a issue serial detail  form --------------------%>
                <div style="border: solid 1px #000000; background:  gainsboro">  
             <s:form name="frmReturnIssuedItemsBrowse">
                 <br>
                    
                <table width="100%" >
                    <tr><td> 
                   
                    <display:table name = "erpmirmList" decorator="Inventory.InventoryDecorator"
                               excludedParams="*"  cellpadding="0"
                               cellspacing="0" id="doc" 
                               requestURI="/Inventory/BrowseReturnIssuedItems.action">
                    <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                             <display:column property="institutionmaster.imName" title="Institution"
                                    maxLength="35" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:15%"  sortable="true"/>
                             <display:column property="subinstitutionmaster.simName" title="SubInstitution"
                                    maxLength="50" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>
                              <display:column property="departmentmaster.dmName" title="Department"
                                    maxLength="50" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="true"/>
                            <display:column property="irmReturnNo" title="Issue No"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                           
                      <display:column property="formattedirmReturnDate" title="Date" style="width:8%"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                       <display:column property="irmRemarks" title="Remark"
                                    maxLength="70" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                         <display:column paramId="irmId" paramProperty="irmId" style="width:5%"
                                    href="/pico/Inventory/EditReturnIssuedItem.action"
                                    headerClass="gridheader" class="griddata" media="html"  value="Edit">

                    </display:column>
                    <display:column paramId="irmId" paramProperty="irmId" style="width:5%"
                                    href="/pico/Inventory/DeleteReturnIssuedItem.action"
                                    headerClass="gridheader" class="griddata" media="html" value="Delete">

                    </display:column>
                          <display:column paramId="irmId" paramProperty="irmId" style="width:8%"
                                    href="/pico/Inventory/PrintReturnIssuedItem.action"
                                    headerClass="gridheader" class="griddata" media="html" value="Print Receipt">

                    </display:column>
                </display:table>
                        </td></tr>
             
</table>
           
             </s:form>
                  </div>
                <br><br>
            </div>
             <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>