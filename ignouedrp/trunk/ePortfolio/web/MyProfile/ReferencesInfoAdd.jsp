<%-- 
    Document   : ReferencesInfoAdd
    Created on : Oct 11, 2011, 2:20:10 PM
    Version    : 1
Author     : Vinay
Version      : 1
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My References</title>
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
                    <h3>My References</h3>
                    <br/><br/>
                    <s:form action="AddReferenceInfo" method="post" namespace="/MyProfile">

                        <table align="center" width="85%" cellpadding="4" border="0" cellspacing="0">                          
                            <s:textfield cssClass="width250" name="name" label="Name"/>
                            <s:textfield cssClass="width250" name="designation" label="Designation"/>
                            <s:textfield cssClass="width250" name="department" label="Department / School"/>
                            <s:textfield cssClass="width250" name="orgUniv" label="University / Organization"/>
                            <s:textfield cssClass="width250" name="place" label="Address"/>
                            <s:textfield cssClass="width250" name="city" label="City"/>
                            <s:textfield cssClass="width250" name="state" label="State"/>
                            <s:textfield cssClass="width250" name="country" label="Country"/>
                            <s:textfield cssClass="width250" name="phoneno" label="Phone No." value="Ex. +91-11-12345678"/>
                            <s:textfield cssClass="width250" name="mobileno" label="Mobile No."/>
                            <s:textfield cssClass="width250" name="emailId" label="Email ID"/>
                            <s:textfield cssClass="width250" name="website" label="Website"/>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />

                    </s:form>
                    <br/><br/><br/><br/>
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
