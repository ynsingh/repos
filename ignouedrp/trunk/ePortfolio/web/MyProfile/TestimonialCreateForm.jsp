<%-- 
    Document   : TestimonialCreateForm
    Created on : Sep 14, 2012, 4:35:07 PM
    Author     : Vinay Kr. Sharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
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
                                <div class="marr15 fl-r mart10">                                    
                                    <% if (role.contains("faculty")) {%>     
                                    || <s:a action="StdTestiReq">Inbox</s:a>  || Sent Testimonials ||
                                    <% } else if (role.contains("student")) {%>
                                    || <a href="TestimonialRequestForm.jsp">New Request</a> || Inbox || Sent Request ||
                                    <% }%> 
                                </div>
                                <s:iterator value="ReqsentList" status="stat">
                                    <s:if test="testiType=='Academic'">
                                        <s:form action="priviewTestiInfo" method="post" theme="simple">
                                            <s:hidden name="testiReqId"/>
                                            <s:hidden name="testiReqCc"/>
                                            <s:hidden name="testiReqBcc"/>
                                            <fieldset class="w700p fl-l">
                                                <legend class="fbld">Student Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td width="25%">Student Name:</td>
                                                        <td><s:textfield name="stdName" value="%{userByTestiRequestor.fname} %{userByTestiRequestor.lname}"/></td>
                                                        <td width="25%">Student's Registration No.:</td>
                                                        <td><s:textfield name="univRegNo" value="%{userByTestiRequestor.univRegNo}"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Programme Enrolled&nbsp;&nbsp;(Code and Title):</td>
                                                        <td colspan="3"><s:textfield name="programe" value="%{userByTestiRequestor.programe}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Year of Enrolled:</td>
                                                        <td><s:textfield name="enrollYear" value="%{}"/></td>
                                                        <td>Year of Completion:</td>
                                                        <td><s:textfield name="passingYear" value="%{}"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Contact No.:</td>
                                                        <td><s:iterator value="userByTestiRequestor.profileContacts">
                                                                <s:textfield name="ContactNo" value="%{mobileNo}"/>
                                                            </s:iterator>
                                                        </td>
                                                        <td>Email Id:</td>
                                                        <td><s:textfield name="emailId" value="%{userByTestiRequestor.emailId}"/></td>
                                                    </tr>
                                                    <s:iterator value="userByTestiRequestor.persi">
                                                        <tr>
                                                            <td>Passport No.:</td>
                                                            <td><s:textfield name="passportNo" value="%{passportNo}"/></td>
                                                            <td>PAN:</td>
                                                            <td><s:textfield name="panNo" value="%{panNo}"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr>
                                                        <td>Purpose:</td>
                                                        <td colspan="3"><s:textfield name="purpose" value="%{user.}"  cssClass="w425p"/></td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">Provide estimates if precise dates as not readily available.</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td>General character:</td>
                                                        <td colspan="3"><s:textfield name="genCharactor"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Attitude:</td>
                                                        <td colspan="3"><s:textfield name="attitude"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Relationships with others/peers/subordinates:</td>
                                                        <td colspan="3"><s:textfield name="RelationwithOthers"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Team-Working:</td>
                                                        <td colspan="3"><s:textfield name="teamWork"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Personal integrity and honesty:</td>
                                                        <td colspan="3"><s:textfield name="personalIntegrity"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Reliability:</td>
                                                        <td colspan="3"><s:textfield name="reliability"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Calmness under pressure:</td>
                                                        <td colspan="3"><s:textfield name="calmness"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Competence(state skills if appropriate.....):</td>
                                                        <td colspan="3"><s:textfield name="competence"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Ambition:</td>
                                                        <td colspan="3"><s:textfield name="ambition"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Overall performance:</td>
                                                        <td colspan="3"><s:textfield name="overallPerformance"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Qualifications/Training attained:</td>
                                                        <td colspan="3"><s:textfield name="qualificationAttained"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Rating:</td>
                                                        <td colspan="3"><s:radio name="rating" list="{'Satisfactory','Good','Very Good','Excellent'}"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Grade/Percentage:</td>
                                                        <td colspan="3"><s:textfield name="gradePercentage"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">Any other comments?:</td>
                                                        <td colspan="3"><s:textarea name="comments" cols="40" rows="10" cssClass="w425p"/></td>
                                                    </tr>

                                                </table>
                                            </fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">From Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td valign="top">Name</td>
                                                        <td colspan="3"><s:textfield name="facultyName" value="%{userByTestiReqTo.fname} %{userByTestiReqTo.lname}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Designation</td>
                                                        <td colspan="3"><s:textfield name="facultyDesignation" value="%{}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Organization/Institute/University</td>
                                                        <td colspan="3"><s:textfield name="facultyOrg" value="%{}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <s:iterator value="userByTestiReqTo.profileContacts">
                                                        <tr>
                                                            <td>Address</td>
                                                            <td colspan="3"><s:textfield name="facultyAddress" value="%{address1}, %{address2}, %{city}, %{state}, %{country}, %{pin}"  cssClass="w425p" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phone No.</td>
                                                            <td colspan="3"><s:textfield name="facultyPhoneNo" value="%{mobileNo}"  cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Email Id</td>
                                                            <td colspan="3"><s:textfield name="facultyEmailId" value="%{email1}"  cssClass="w425p"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                </table>
                                            </fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">To Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td valign="top" align="left">Name</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationName" value="%{testiForName}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top" align="left">Email</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationEmailId" value="%{testiForEmail}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top" align="left">Address</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationAddress" value="%{testiForAddress}" cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" align="center">
                                                            <s:submit value="Preview"/>&nbsp;&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
                                    </s:if>
                                    <s:elseif test="testiType=='Industry'">
                                        <s:form action="priviewTestiInfo" method="post" theme="simple">
                                            <s:hidden name="testiReqId"/>
                                            <s:hidden name="testiReqCc"/>
                                            <s:hidden name="testiReqBcc"/>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">Employee Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td width="25%">Name of Applicant:</td>
                                                        <td><s:textfield name="stdName" value="%{userByTestiRequestor.fname} %{userByTestiRequestor.lname}"/></td>
                                                        <td width="25%">Employee Id:</td>
                                                        <td><s:textfield name="univRegNo" value="%{userByTestiRequestor.univRegNo}"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Designation:</td>
                                                        <td><s:textfield name="designation" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Joining Date:</td>
                                                        <td><s:textfield name="joiningDate" /></td>
                                                        <td>Leaving Date:</td>
                                                        <td><s:textfield name="leavingDate" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Contact No.:</td>
                                                        <td><s:iterator value="userByTestiRequestor.profileContacts">
                                                                <s:textfield name="ContactNo" value="%{mobileNo}"/>
                                                            </s:iterator>
                                                        </td>
                                                        <td>Email Id:</td>
                                                        <td><s:textfield name="emailId" value="%{userByTestiRequestor.emailId}"/></td>
                                                    </tr>
                                                    <s:iterator value="userByTestiRequestor.persi">
                                                        <tr>
                                                            <td>Passport No.:</td>
                                                            <td><s:textfield name="passportNo" value="%{passportNo}"/></td>
                                                            <td>PAN:</td>
                                                            <td><s:textfield name="panNo" value="%{panNo}"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr>
                                                        <td>Purpose:</td>
                                                        <td colspan="3"><s:textfield name="purpose"  cssClass="w425p"/></td>
                                                    </tr>

                                                </table></fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">Provide estimates if precise dates as not readily available.</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td>General character:</td>
                                                        <td colspan="3"><s:textfield name="genCharactor"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Attitude:</td>
                                                        <td colspan="3"><s:textfield name="attitude"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Relationships with others/peers/subordinates:</td>
                                                        <td colspan="3"><s:textfield name="RelationwithOthers"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Team-Working:</td>
                                                        <td colspan="3"><s:textfield name="teamWork"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Personal integrity and honesty:</td>
                                                        <td colspan="3"><s:textfield name="personalIntegrity"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Reliability:</td>
                                                        <td colspan="3"><s:textfield name="reliability"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Calmness under pressure:</td>
                                                        <td colspan="3"><s:textfield name="calmness"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Competence(state skills if appropriate.....):</td>
                                                        <td colspan="3"><s:textfield name="competence"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Ambition:</td>
                                                        <td colspan="3"><s:textfield name="ambition"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Overall performance:</td>
                                                        <td colspan="3"><s:textfield name="overallPerformance"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Qualifications/Training attained:</td>
                                                        <td colspan="3"><s:textfield name="qualificationAttained"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Rating:</td>
                                                        <td colspan="3"><s:radio name="rating" list="{'Satisfactory','Good','Very Good','Excellent'}"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Why did the person leave?:</td>
                                                        <td colspan="3"><s:textfield name="whyLeaving" cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Would you re-employ the person if a suitable vacancy existed?:</td>
                                                        <td colspan="3"><s:textfield name="reEmployment" cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">Any other comments?:</td>
                                                        <td colspan="3"><s:textarea name="comments" cols="40" rows="10" cssClass="w425p"/></td>
                                                    </tr>

                                                </table></fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">From Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td valign="top">Name</td>
                                                        <td colspan="3"><s:textfield name="facultyName" value="%{userByTestiReqTo.fname} %{userByTestiReqTo.lname}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Designation</td>
                                                        <td colspan="3"><s:textfield name="facultyDesignation"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Organization/Institute/University</td>
                                                        <td colspan="3"><s:textfield name="facultyOrg"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <s:iterator value="userByTestiReqTo.profileContacts">
                                                        <tr>
                                                            <td>Address</td>
                                                            <td colspan="3"><s:textfield name="facultyAddress" value="%{address1}, %{address2}, %{city}, %{state}, %{country}, %{pin}"  cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phone No.</td>
                                                            <td colspan="3"><s:textfield name="facultyPhoneNo" value="%{mobileNo}"  cssClass="w425p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Email Id</td>
                                                            <td colspan="3"><s:textfield name="facultyEmailId" value="%{email1}"  cssClass="w425p"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                </table>
                                            </fieldset>
                                            <fieldset class="w700p fl-l mart10">
                                                <legend class="fbld">To Details</legend>
                                                <table width="95%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td valign="top" align="left">Name</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationName" value="%{testiForName}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top" align="left">Email</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationEmailId" value="%{testiForEmail}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top" align="left">Address</td>
                                                        <td align="left" colspan="3"><s:textfield name="DestinationAddress" value="%{testiForAddress}"  cssClass="w425p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" align="center">
                                                            <s:submit value="Preview"/>&nbsp;&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
                                    </s:elseif>
                                    <s:elseif test="testiType=='Others'">
                                        <s:form action="SendtoConcern" method="post" namespace="" theme="simple">
                                            <s:hidden name="testiReqId"/>
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Other Details</legend>
                                                <table width="95%" class="mar0a" border="0" cellpadding="4" cellspacing="0" id="hide">    
                                                    <tr>
                                                        <th width="10%" align="left">To:</th>
                                                        <td width="70%"><s:textfield name="testiForEmail"  cssClass="w425p"/><br/>
                                                            <a  onclick="toggleRow('hide1')">Add Cc</a>, <a  onclick="toggleRow('hide2')">Add Bcc</a>
                                                        </td>
                                                    </tr>
                                                    <tr id="hide1" style="display:none;" >
                                                        <th width="10%" align="left">Cc:</th>
                                                        <td width="70%">
                                                            <s:textfield name="testiReqCc"  cssClass="w425p"/>

                                                        </td>
                                                    </tr>
                                                    <tr id="hide2" style="display:none;" >
                                                        <th width="10%" align="left">Bcc:</th>
                                                        <td width="70%"><s:textfield name="testiReqBcc" cssClass="w425p"/>
                                                        </td>
                                                    </tr>
                                                    <tr><th align="left">Subject/Purpose:</th><td><s:textfield name="testiType" cssClass="w425p"/></td></tr>
                                                    <tr>
                                                        <th valign="top" align="left">Testimonial:</th><td>
                                                            <sjr:tinymce
                                                                id="message"                              
                                                                name="testiReqMessage"
                                                                rows="20"
                                                                cols="80"
                                                                value=""
                                                                bindOn="true"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                />
                                                        </td>
                                                    </tr>       
                                                    <tr>
                                                        <td colspan="2" align="center"> 
                                                            <s:submit value="Send"/>&nbsp;&nbsp;
                                                            <s:reset value="Back" onclick="history.go(-1);"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
                                    </s:elseif>
                                </s:iterator>
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