<%-- 
    Document   : TestimonialPreview
    Created on : Sep 18, 2012, 5:39:07 PM
    Author     : Vinay
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
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            function template() {
                var div_num = $("#choice").val();
                if (div_num == 1) {
                    $("#div1").show(); 
                    $("#div2").hide(); 
                    $("#div3").hide();
                    $("#div4").hide();
                };
                if (div_num ==2) {
                    $("#div1").hide(); 
                    $("#div2").show(); 
                    $("#div3").hide(); 
                    $("#div4").hide();
                };
                if (div_num == 3) {
                    $("#div1").hide(); 
                    $("#div2").hide(); 
                    $("#div3").show(); 
                    $("#div4").hide();
                };
                if (div_num == 4) {
                    $("#div1").hide(); 
                    $("#div2").hide(); 
                    $("#div3").hide(); 
                    $("#div4").show(); 
                };
            }
        </script>
        <script type="text/javascript">
            function printfDiv1()
            {
                var contentv;
                if(document.getElementById("div1").style.display!="none")
                    contentv=document.getElementById("printDiv1").innerHTML;
                else if(document.getElementById("div2").style.display!="none")
                    contentv=document.getElementById("printDiv2").innerHTML;
                else if(document.getElementById("div3").style.display!="none")
                    contentv=document.getElementById("printDiv3").innerHTML;
                else if(document.getElementById("div4").style.display!="none")
                    contentv=document.getElementById("printDiv4").innerHTML;
                
                var WindowObject = window.open('', 'PrintWindow','width=550,height=350,top=50,left=50,toolbars=no,scrollbars=yes,status=no,resizable=yes');
                WindowObject.document.writeln(contentv);
                WindowObject.document.close();
                WindowObject.focus();
                WindowObject.print();
                WindowObject.close(); 
            }
        </script>
        <script type="text/javascript">
            function getContent()
            {
                var contentv;
                if(document.getElementById("div1").style.display!="none")
                    contentv=document.getElementById("printDiv1").innerHTML;
                else if(document.getElementById("div2").style.display!="none")
                    contentv=document.getElementById("printDiv2").innerHTML;
                else if(document.getElementById("div3").style.display!="none")
                    contentv=document.getElementById("printDiv3").innerHTML;
                else if(document.getElementById("div4").style.display!="none")
                    contentv=document.getElementById("printDiv4").innerHTML;
                document.getElementById("report").value=contentv;
                //alert(contentv);
            }
            // alert(contentv);
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
                            <div class="my_account_bg">Testimonial Preview</div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="TestimonialIndex.jsp">Testimonials</a> > Request for Testimonial 
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="marr15 fl-r mart10">
                                        <% if (role.contains("faculty")) {%>     
                                        || <s:a action="StdTestiReq">Inbox</s:a>  || <a href="TestimonialCreateForm.jsp">Create New Testimonial</a> || Sent Testimonials ||
                                        <% } else if (role.contains("student")) {%>
                                        || <a href="TestimonialRequestForm.jsp">New Request</a> || Inbox || Sent Request ||
                                        <% }%> 
                                    </div>
                                    <div class="w100 fl-l mart20">
                                        <table align="center" width="100%" border="0">
                                            <tr>
                                                <th align="right">Select Template</th>
                                                <th align="left">
                                                    <select id="choice" onchange="template();">
                                                        <option value="1" selected="true">Dean</option>
                                                        <option value="2">Head of Department</option>
                                                        <option value="3">Head of Institution</option>
                                                        <option value="4">Proctor</option>
                                                    </select>
                                                </th>
                                                <th><a class="cursor" onclick="printfDiv1()" title="Print"><img src="<s:url value="/images/print.png"/>" width="20px;" height="20px;"/></a></th>
                                            </tr>
                                        </table>
                                        <s:form method="post" action="saveCreatedTesti" theme="simple">
                                            <s:hidden name="testiReqId"/><s:hidden name="DestinationEmailId"/> <s:hidden name="testiReqCc"/>
                                            <s:hidden name="testiReqBcc"/>
                                            <input type="hidden" name="report" id="report" value="" />
                                            <div id='div1' class="w600p mar0a mart20 tj">
                                                <div id="printDiv1">
                                                    <p><s:property value="DestinationName"/></p>
                                                    <p><s:property value="DestinationAddress"/></p>
                                                    <p>Date <s:property value="todayDate"/></p>
                                                    <p>This is to certify that Mr. <strong><s:property value="stdName"/></strong>, s/o Mr. .............., had been my student from <strong><s:property value="enrollYear"/></strong> to <strong><s:property value="passingYear"/></strong>. He passed <strong><s:property value="programe"/></strong>, from this College in <strong><s:property value="passingYear"/></strong> and secured <strong><s:property value="gradePercentage"/></strong>% Marks.</p>
                                                    <p><strong><s:property value="stdName"/></strong>  was Captain of Cricket team of our College and won three trophies in Inter-College Tournaments. He also took active part in College debates.</p>
                                                    <p>I am confident about his good moral and character, and wish him all success in life.</p>
                                                    <p class="w255p fl-r tc fbld clear">Principal</p>
                                                    <p class="w255p fl-r tr clear">
                                                        <s:property value="facultyName"/><br/>
                                                        <s:property value="facultyDesignation"/><br/>
                                                        <s:property value="facultyOrg"/><br/>
                                                        <s:property value="facultyAddress"/><br/>
                                                        Ph:- <s:property value="facultyPhoneNo"/><br/>
                                                        Email:- <s:property value="facultyEmailId"/>
                                                    </p>
                                                </div>
                                            </div>
                                            <div id='div2' style="display:none;" class="w600p mar0a mart20 tj">
                                                <div id="printDiv2">
                                                    <p><s:property value="DestinationName"/></p>
                                                    <p><s:property value="DestinationAddress"/></p>
                                                    <p>Date <s:property value="todayDate"/></p>
                                                    <p>This is to certify that <strong><s:property value="stdName"/></strong> was a bona fide student of this school from <strong><s:property value="enrollYear"/></strong> to <strong><s:property value="passingYear"/></strong> and passed the Higher Secondary Examination with commercial subjects gelling distinction in Book-Keeping and English. He passed the examination in <strong><s:property value="passingYear"/></strong> and secured <strong><s:property value="gradePercentage"/></strong>% Marks.</p>
                                                    <p><strong><s:property value="stdName"/></strong>  was regular in attendance and diligent in his studies. He was liked by his teachers as well as classmates because of his pleasing manners.</p>
                                                    <p><strong><s:property value="stdName"/></strong> always took active part in sports and dramatic activities of the school and was General Secretary of the School association from ......... to ...........</p>
                                                    <p>He bears an excellent moral character. I wish him a success­full career,</p>
                                                    <p class="w255p fl-r tc fbld clear">Principal</p>
                                                    <p class="w255p fl-r tr clear">
                                                        <s:property value="facultyName"/><br/>
                                                        <s:property value="facultyDesignation"/><br/>
                                                        <s:property value="facultyOrg"/><br/>
                                                        <s:property value="facultyAddress"/><br/>
                                                        Ph:- <s:property value="facultyPhoneNo"/><br/>
                                                        Email:- <s:property value="facultyEmailId"/>
                                                    </p>
                                                </div>                                
                                            </div>
                                            <div id='div3' style="display:none;" class="w600p mar0a mart20 tj">
                                                <div id="printDiv3">
                                                    <p><s:property value="DestinationName"/><br/><s:property value="DestinationAddress"/></p>
                                                    <p><strong>Date</strong> <s:property value="todayDate"/></p>
                                                    <p>To whom it may concern,</p>
                                                    <p><strong><s:property value="stdName"/></strong> was my student in <strong><s:property value="programe"/></strong> from <strong><s:property value="enrollYear"/></strong> to <strong><s:property value="passingYear"/></strong>. He highly impressed me and other lecturers of this college by his intelligence, sociability and scholastic trend of mind.</p>
                                                    <p>He always look a keen interest in his subject. He was actively participating in different seminars and talks on his subject</p>
                                                    <p><strong><s:property value="stdName"/></strong> passed his examination in <strong><s:property value="passingYear"/></strong> with high first class getting 3rd position in the Province, lie was offered the post of lecturer in Politics in this College which he did not accept on account of his leaving Delhi for some private affairs.</p>
                                                    <p>I am confident of his painstaking nature and sincere interest in Politics. I wish him all success in life.</p>
                                                    <p class="w255p fl-r tc fbld clear">Principal/HOD/Faculty</p>
                                                    <p class="w255p fl-r tr clear">
                                                        <s:property value="facultyName"/><br/>
                                                        <s:property value="facultyDesignation"/><br/>
                                                        <s:property value="facultyOrg"/><br/>
                                                        <s:property value="facultyAddress"/><br/>
                                                        Ph:- <s:property value="facultyPhoneNo"/><br/>
                                                        Email:- <s:property value="facultyEmailId"/>
                                                    </p>
                                                </div>
                                            </div>
                                            <div id='div4' style="display:none;" class="w600p mar0a mart20 tj">
                                                <div id="printDiv4">
                                                    Proctor Template
                                                </div>                                                
                                            </div>                                            
                                            <p class="clear tc">
                                                <button type="submit" name="ButtonName" value="Email" onclick="getContent();">Email</button>
                                                <button type="submit" name="ButtonName" value="Export" onclick="getContent();">Export</button>
                                                <button type="submit" name="ButtonName" value="Save" onclick="getContent();">Draft</button>
                                                <button type="button" onClick="history.go(-1);" >Back</button>
                                            </p> 
                                        </s:form>
                                    </div>
                                    <!--
                                    <s:property value="JSONTestimonial"/> 
                                    -->
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