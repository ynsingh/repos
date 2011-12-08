<%-- 
    Document   : ContactInfoEdit
    Created on : Sep 15, 2011, 4:57:30 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
      <script type="text/javascript">
            $(document).ready(function() {
            $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body><%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>

            <div id="container">
                <div class="wrapper">
                    <jsp:include page="../Left-Nevigation.jsp"/>
                    <div id="col2">
                        <h3>Contact Information</h3>
                        <br/>
                    <s:form action="updateContact" method="post">
                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">

                            <s:iterator value="contactListList" var="ProfileContact">
                                <tr><td><s:hidden name="contactInfoId"/>
                                        <s:hidden name="userId" label="User ID"/>                                        
                                    </td></tr>
                                <tr><td>
                                        <s:textfield name="address1" label="Address 1">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="address2" label="Address 2">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="city" label="City">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="state" label="State">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="country" label="Country">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="pin" label="ZIP Code">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="htelephone" label="Telephone(Home)">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="otelephone" label="Telephone(Office)">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="mobile" label="Mobile No.">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="fax" label="FAX No.">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="email1" label="Alternative Email 1">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="email2" label="Alternative Email 2">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="email3" label="Alternative Email 3">

                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="owebsite" label="Website(Organization)">
                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="pwebsite" label="Website(Personal)">
                                        </s:textfield>
                                    </td></tr>
                                </s:iterator>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                    <br/><br/>
                </div>
                    <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
                    <jsp:include page="../Footer.jsp"/>    </body>
</html>
