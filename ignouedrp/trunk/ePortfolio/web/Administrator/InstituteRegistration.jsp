<%-- 
    Document   : InstituteRegistration
    Created on : Apr 21, 2012, 2:23:54 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Institute Registration</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>

        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Institute Registration</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> 
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<s:a action="ShowRegisteredInstitute">Registered Institutes</s:a>&nbsp;>&nbsp; Add New Institute
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:form method="post" action="AddInst" id="InstiRegId" theme="simple" namespace="/Registration">
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Institute Registration</legend>
                                                <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr><th align="left">Institute Name</th><td><s:textfield name="instituteName"/></td></tr>
                                                    <tr><th align="left">Short Name</th><td><s:textfield name="shortName"/></td></tr>
                                                    <s:url id="CountId" action="CountryAction" namespace="/Dropdown"/> 
                                                    <s:url id="CityId" action="CityAction" namespace="/Dropdown"/>                    
                                                    <tr><th align="left">Country</th>
                                                        <td><sj:select 
                                                                href="%{CountId}" 
                                                                id="CountryCode" 
                                                                onChangeTopics="reloadcitylist" 
                                                                name="CountryCode" 
                                                                list="countryMap" 
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select Country"
                                                                label="Country"
                                                                sortable="true"
                                                                /> 
                                                        </td>
                                                    </tr>
                                                    <tr><th align="left">City/Place</th><td>
                                                            <sj:select 
                                                                href="%{CityId}" 
                                                                id="CityName" 
                                                                formIds="InstiRegId" 
                                                                reloadTopics="reloadcitylist" 
                                                                name="city" 
                                                                list="cityMap" 
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select City"
                                                                label="City"
                                                                sortable="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr><th align="left" class="w">Address 1</th><td><s:textarea name="address1"/></td></tr>
                                                    <tr><th align="left">Address 2</th><td><s:textarea name="address2"/></td></tr>
                                                    <tr><th align="left">Pin/Zip Code</th><td><s:textfield name="pinzip"/></td></tr>
                                                    <tr><th align="left">Phone No.</th><td><s:textfield name="phone1"/></td></tr>
                                                    <tr><th align="left">Email Id</th><td><s:textfield name="emailId"/></td></tr>
                                                    <tr><th align="left">Website URL</th><td><s:textfield name="website"/></td></tr>
                                                    <tr><th align="left" valign="top">About us</th><td>
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="aboutus"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{aboutus}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                />
                                                        </td></tr>
                                           <!--     <tr><th align="left">Logo</th><td><s:file name="logo"/></td></tr> -->
                                                    <tr><th colspan="2" align="center">
                                                            <s:submit value="Save"/>&nbsp;&nbsp;
                                                            <s:reset value="Reset"/>&nbsp;&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </th>
                                                    </tr>
                                                </table>                                                
                                            </fieldset>
                                        </s:form>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
