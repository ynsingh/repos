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
             <script type='text/javascript'> 
            function getValue()    {
       //if (cc != "") {
       // var UName = document.getElementById(UserName).value;
   //var DateofBirth = document.getElementById(DOB).value;

   // if (cc == "" || um == "")
        //document.getElementById(SecretQuestion).setAttribute("value","Please provide values for the above fields");
    //else
    //{searchValue=" + UName +"&searchValue2="+DateofBirth,
    cc=document.getElementById(Photograph).value();
     /*   var msg = $.ajax({
            url:"/pico/ajax/getFile.action?searchValue=" + cc ,
            async:false
        }).responseText;*/
     //   document.getElementById(SecretQuestion).setAttribute("value",msg);
    alert("hello"+cc);
}
         //alert("hello");
      //}

     // }
    </script>


            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <br><br>

            <p align="center"><s:label value="BUDGET HEAD MANAGEMENT" cssClass="pageHeading"/></p>
            <p align="center"><s:property value="message" /></p>
            <div id ="mainContent">
                <s:form name="frmBudgetHeads" action="SavebudgeheadAction"  validate="true" enctype="multipart/form-data">
                    <s:hidden name="bhm.bhmId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td><br>
<%--                                                            <s:file  name="Record" label="record"
                                                      title="Enter the path of the File" ></s:file>
--%>
                                    <s:select label="Institution" name="bhm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 label="Budget Head Name" name="bhm.bhmName" title="Enter Capital Item Category Name"  cssClass="textInput"/>

                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="SaveBudgetHead "  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Budget Head Entries" action="FetchEntries" cssClass="textInput"/>
                                </td>
                                <td>
                                  <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearBudgetHead" cssClass="textInput"/>
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
                    <display:table name="bhmList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/Administration/ManageExportAction.action" >
                        <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>

                                   <display:column property="institutionmaster.imName" title="Institution"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:30%" sortable="true"  href=""/>
                       <display:column property="bhmName" title="Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                        <display:column paramId="BhmId" paramProperty="bhmId"
                                    href="/pico/Administration/EditBudgetHeadAction.action" 
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="BhmId" paramProperty="bhmId"
                                    href="/pico/Administration/DeleteBudgetHeadAction.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete" style="width:30%">
                                    Delete
                        </display:column>
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