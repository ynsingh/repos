<%-- 
    Document   : index
    Created on : Jul 22, 2011, 2:12:14 PM 
Author     : Amit
Version    : 1
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
        <script type="text/javascript" src="../JS/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="../JS/jquery-ui.css"/>

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
                    <h3>Edit File</h3>  
                    <br/>
                    <s:actionerror/>
                    <s:form action="UpdateFile" method="post" enctype="multipart/form-data">
                        <table align="center" border="0" cellpadding="3" cellspacing="0" width="60%">
                            <th colspan="2" align="center"></th>
                            <tr><td><s:iterator value="imagelistlist">
                                        <input type="hidden" name="fileid" value="<s:property value="fileid"/>"/>

                                        <s:textfield name="filename" label="filename"></s:textfield>
                                        <s:textfield name="description" label="Description:"></s:textfield>
                                        </td></tr>
                                    <tr><td>
                                        <s:submit align="center"/></td></tr>
                                    </s:iterator>
                        </table>
                    </s:form>    
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
