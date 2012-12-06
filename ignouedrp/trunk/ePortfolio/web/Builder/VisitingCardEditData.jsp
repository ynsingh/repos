<%-- 
    Document   : VisitingCardEditData
    Created on : Oct 17, 2012, 3:28:55 PM
    Author     : Amit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Visiting Card</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script type="text/javascript">  
            window.onload = setallonload;
        </script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
                window.history.forward(1);
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
                            <div class="my_account_bg">Edit Visiting card</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/.jsp"/>">My Builder</a> > Edit Visiting Card Information </div>
                                        <div class="w100 fl-l mart10">
                                            <div class="w100 fl-l tc fbld fcgreen">
                                                <s:property value="msg"/>
                                            </div>
                                            <div class="w100 fl-l mart5">
                                                <s:iterator value="vcList" >
                                                    <fieldset class="w400p mar0a">
                                                        <legend class="fbld">Edit Visiting Card Detail</legend>
                                                        <s:form action="UpdateVisiCard"  method="post" namespace="/" validate="true">
                                                            <table width="80%" class="mar0a" cellpadding="1" cellspacing="4">
                                                                <s:hidden name="visitcardId"/>
                                                                <s:textfield name="displayName" label="Display Name"/>
                                                                <s:textfield name="designation" label="Designation"/>
                                                                <s:textfield name="company" label="Company Name"/>
                                                                <s:textfield name="email" label="Email-Id"/>

                                                                <s:textfield name="mobile" label="Mobile No"/>
                                                                <s:textfield name="officePh" label="Office No"/>
                                                                <s:textfield name="fax" label="Fax No"/>
                                                                <s:textfield name="websiteOff" label="Website(off)"/>
                                                                <s:textfield name="websitePer" label="Website(Per)"/>
                                                                <td align="center" colspan="5"><s:submit value="Save" theme="simple" />
                                                                    <s:reset value="Reset" theme="simple"/>
                                                                </td>
                                                                </tr>
                                                            </table>
                                                        </fieldset>
                                                    </s:form>
                                                </div>

                                            </s:iterator>

                                        </div>
                                    </div>
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
