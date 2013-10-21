<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->

            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" /> 
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">NEWS MANAGEMENT</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro" >
                    <br>
                    <s:form name="frmManageNewsAction" action="ManageNewsAction" theme="qxhtml">
                        <s:hidden name="erpmNews.newsId" /> 

                        <s:textfield maxLength="30" size="30" cssErrorStyle="true"
                                     label="News Type" required="true" requiredposition="" name="erpmNews.newsType" title="Enter HeadLine of News" cssClass="queryInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield> <br>
                        <s:textarea label="News Text" required="true" requiredposition="" name="erpmNews.newsText" cols="99" rows="3">
                           <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                       </s:textarea >
                        <s:textfield requiredposition="left" maxLength="100" size="100" cssErrorStyle="true"
                                     label="Enter Publish Date(dd-mm-yyyy)" required="true" name="publishDate"  cssClass="queryInput">
                           <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>
                        <s:textfield requiredposition="left" maxLength="100" size="100" cssErrorStyle="true"
                                     label="Enter Expiry Date(dd-mm-yyyy)" required="true" name="expiryDate"  cssClass="queryInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                            <tr><td> &nbsp; </td></tr>
                            <s:submit value="Save News " action="SaveManageNewsAction">
                                <s:param name="colspan" value="%{3}" />
                                <s:param name="align" value="%{'center'}" />
                            </s:submit>

                            <s:submit name="btnSubmit" value="Browse News" action="BrowseManageNewsAction">
                                <s:param name="colspan" value="%{2}" />
                                <s:param name="align" value="%{'center'}" />
                            </s:submit>

                            <s:submit value="Clear Screen" action="ManageNewsAction" >
                                <s:param name="colspan" value="%{2}" />
                                <s:param name="align" value="%{'center'}" />
                            </s:submit>

                            <%--
                          <s:submit name="btnReport" value="Export Data" action="PrintManageNewsAction">
                              <s:param name="colspan" value="%{1}" />
                              <s:param name="align" value="%{'center'}" />
                          </s:submit>
                            --%>
                        </s:form>
                        <br>
                        </div>
                        &nbsp;
                        </div>
                        <div id="footer">
                            <jsp:include page="footer.jsp" flush="true"></jsp:include>
                        </div>
                </div>
                </body>

                </html>


