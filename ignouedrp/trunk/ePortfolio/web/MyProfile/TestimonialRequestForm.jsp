<%-- 
    Document   : TestimonialRequestForm
    Created on : Sep 14, 2012, 4:00:33 PM
    Author     : Vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Testimonial Request Form</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">

            /* call onload with table id(s) */
            function TR_set_toggle()
            {
                /* toggleRow method */
                var toggleRow = function()
                {
                    this.style.display = ((this.style.display == '') ? 'none' : '');
                    return false;
                }

                for (var oTable, a = 0; a < arguments.length; ++a)
                {
                    oTable = document.getElementById(arguments[a]);
                    var r = 0, row, rows = oTable.rows;
                    while (row = rows.item(r++))
                        row.toggle = toggleRow;
                }

                /* convenience function */
                self.toggleRow = function(row_id)
                {
                    document.getElementById(row_id).toggle();
                }
            }

            onload = function()
            {
                TR_set_toggle('hide');
            }

        </script>
    </head>
    <body>
        <%  String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Testimonial Request Form</div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="TestimonialIndex.jsp">Testimonials</a> > Request for Testimonial 
                                </div>
                                <div class="w100 fl-l mart5">
                                    <div class="w100 marr15 fl-r mart10 tr padrl20">
                                        <% if (role.contains("faculty")) {%>     
                                        || Inbox || Create New Testimonial || Sent Testimonials ||
                                        <% } else if (role.contains("student")) {%>
                                        || <a href="TestimonialRequestForm.jsp">New Request</a> || <s:a action="TestimonialSent"> Inbox</s:a> || Sent Request ||
                                        <% }%> 
                                    </div>
                                    <div class="w100 marr15 fl-l mart10">
                                        <s:form method="post" action="TestiRequest" theme="simple" namespace="/MyProfile" enctype="multipart/form-data">
                                            <fieldset class="w600p mar0a">
                                                <legend><strong>Testimonial Request Form</strong></legend>
                                                <table width="100%" class="fl-l" border="0" cellpadding="4" cellspacing="0" id="hide">  
                                                    <tr>
                                                        <th width="40%" align="left">To:</th>
                                                        <td width="60%">
                                                            <s:url id="toID" action="getMailTo" namespace="/Dropdown"/> 
                                                            <sj:select 
                                                                href="%{toID}" 
                                                                id="testiTo" 
                                                                name="testiReqTo" 
                                                                list="facultyList" 
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select a Faculty"
                                                                />
                                                            <a onclick="toggleRow('hide1')" class="cursor">Add Cc</a>, <a onclick="toggleRow('hide2')" class="cursor">Add Bcc</a>
                                                        </td>
                                                    </tr>
                                                    <tr id="hide1" style="display:none;" >
                                                        <th width="40%" align="left">Cc:</th>
                                                        <td width="60%">
                                                            <s:textfield name="testiReqCc"  cssClass="w425p"/>
                                                        </td>
                                                    </tr>
                                                    <tr id="hide2" style="display:none;" >
                                                        <th width="40%" align="left">Bcc:</th>
                                                        <td width="60%"><s:textfield name="testiReqBcc" cssClass="w425p"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Type of Testimonial:</th>
                                                        <td><s:radio name="testiType" list="{'Academic','Industry','Others'}"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left" valign="top">Message/Purpose/Description:</th>
                                                        <td><s:textarea name="testiReqMessage" cols="60" rows="10" cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left" valign="top">Supported Documents Received (Optional):</th>
                                                        <td><s:file name="SupportingDoc" cssClass="w425p"/></td>
                                                    </tr>
                                                </table>
                                                <fieldset>
                                                    <table width="100%" align="center" class="fl-l" cellpadding="4" cellspacing="0">
                                                        <legend><strong>To Whom the Testimonial to be sent</strong></legend>
                                                        <tr>
                                                            <th width="40%" align="left" valign="top">Name:</th>
                                                            <td width="60%"><s:textfield name="testiForName" cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left" valign="top">Email:</th>
                                                            <td><s:textfield name="testiForEmail" cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left" valign="top">Address:</th>
                                                            <td><s:textarea name="testiForAddress" cols="60" rows="10" cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th colspan="2">
                                                                <button type="submit" value="Submit" name="buttonName">Request</button>
                                                                <button type="submit" value="Draft" name="buttonName">Draft</button>
                                                                <button type="reset" value="Cancel"  theme="simple" onClick="history.go(-1);">Cancel</button>
                                                            </th>
                                                        </tr>
                                                    </table>   
                                                </fieldset>
                                            </fieldset>
                                        </s:form>
                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>