<%--
    Document   : indentApproval
    Created on : Feb 23, 2011, 4:36:35 PM
    Author     : afreen
--%>

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
         <META HTTP-EQUIV="Cache-Control" CONTENT=" must-revalidate, no-cache,no-store,max-age=1">
         <META HTTP-EQUIV="expires"CONTENT="0">
       <meta http-equiv="refresh" content="120">
         <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">

        <script type='text/javascript'>

      function getCurrentStatus(cc,um)    {
       //if (cc != "") {
       // var UName = document.getElementById(UserName).value;
   //var DateofBirth = document.getElementById(DOB).value;

   // if (cc == "" || um == "")
        //document.getElementById(SecretQuestion).setAttribute("value","Please provide values for the above fields");
    //else
    //{searchValue=" + UName +"&searchValue2="+DateofBirth,
        var msg = $.ajax({
            url:"/pico/ajax/getStatus.action?searchValue=" + cc +"&searchValue2="+um,
            async:false
        }).responseText;
     //   document.getElementById(SecretQuestion).setAttribute("value",msg);
    alert("hello"+msg);
}
         //alert("hello");
      //}

     // }
    </script>
        <div id="container">
            <div id="header">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <p align="center"><s:label value="INDENT APPROVAL MANAGEMENT" cssClass="pageHeading"/></p>
           
          


           <div id ="mainContent" align="center">
 
  <s:property value="message" />
                     <s:form name="frmMessagereport" >               
                         <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="umList" pagesize="15"
                               excludedParams="" export="true" cellpadding="0"
                               cellspacing="0" summary="true" decorator="" id="doc"
                                  requestURI="/Administration/MessageReport.action">
                        <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmusersByUmFromErpmuId.erpmuFullName" title="From"
                                    maxLength="100" headerClass="gridheader" 
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:30%" sortable="true" />
                       <display:column property="erpmusersByUmToErpmuId.erpmuFullName" title="To"
                                       maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true" />
                        <display:column property="umReqTypeId" title="Indent Id"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true" />

                         <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Status"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true" />

                    <display:column paramId="ReqId" paramProperty="umReqTypeId" title="Report"
                                    href="/pico/Administration/GenerateReport.action"
                                    headerClass="gridheader" class="griddata" media="html" style="width:30%">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;" title="Delete"/>
                    </display:column>
                    </display:table>
                <%--<s:select  name="um.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="statusList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>--%>
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