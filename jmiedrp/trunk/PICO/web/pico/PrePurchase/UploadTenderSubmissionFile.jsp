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
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
                </div>

                <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
                </div>

            <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>

                <!-- *********************************End Menu****************************** -->

                <div id ="mainContent">

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{4}" />
                </s:bean>

                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">Upload Submission File</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <s:form  enctype="multipart/form-data" name="frmTenderSubmission"  action="UploadTenderSubmissionFile" method="POST" theme="qxhtml">
                    <s:textfield  cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                                  label="Tender No" name="localttenderno" disabled="true">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield >
                    <s:textfield  cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                                  label="Tender Name" name="localtendername" disabled="true">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield >
                    <s:textfield  cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                                  label="Company Name" name="localtcompanyname" disabled="true">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield > 

                    <s:textfield  cssClass="textInput" required="left"  maxLength="50" size="50"
                                  label="File Name" name="fileUploadFileName" title="rt">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                    </s:textfield >
                    <s:file name="Uploadfile" label="Select  File " size="40" />
                    <s:textarea cssClass="textArea"  rows="1" cols="40" label="Remarks" name="TsfFileRemarks" title="" />


                    <s:submit value="Submit" align="center"  action="SaveUploadTenderSubmissionFile" >
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="%{1}" />
                    </s:submit> 
                    <s:submit name="btnPrint" value="Clear"  action="ClearTenderSubmissionFile"
                              >

                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="%{1}" />
                    </s:submit> 

                    <s:submit name="btnPrint" value="Back"  action="BackTenderSubmissionFile">
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="%{1}" />
                    </s:submit> 

                </s:form>
                <s:hidden name="erpmtsb.tsbTsbId" />
                <table style="height:19em;" width="80%"><tr><td valign="top">

                            <s:form name="frmuploadfile">                    
                                <display:table name="erpmtsfList" pagesize="15"
                                               excludedParams="*" export="true" cellpadding="0"
                                               cellspacing="0" id="doc"
                                               requestURI="/PrePurchase/DownLoadTenderSubmissionFile.action">
                                    <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader" style="width:5%" >
                                <c:out> ${doc_rowNum}
                                </display:column>

                                <display:column property="tsfFileName" title="File Name"
                                                maxLength="35" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:5%" sortable="true"/>

                                <display:column property="tsfFileRemarks" title="Remarks"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:15%" sortable="true"/>



                                <display:column paramId="tsfTsfId" paramProperty="tsfTsfId"
                                                href="/pico/PrePurchase/EditTenderSubmissionFile.action" style="width:5%" 
                                                headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                    Edit
                                </display:column>

                                <display:column paramId="tsfTsfId" paramProperty="tsfTsfId" style="width:5%" 
                                                href="/pico/PrePurchase/DeleteTenderSubmissionFile.action"
                                                headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                    Delete
                                </display:column>
                                <display:column paramId="tsfTsfId" paramProperty="tsfTsfId" style="width:5%" 
                                                href="/pico/PrePurchase/DownLoadTenderSubmissionFile.action"
                                                headerClass="gridheader" class="griddata" media="html" title="DownLoad" >
                                    DownLoad
                                </display:column>



                            </display:table>                    
                        </s:form>
                    </td></tr></table>
                <br>
            </div>
        </div>
        <br>
        <div id="footer">
            <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
        </div>

    </body>
</html>