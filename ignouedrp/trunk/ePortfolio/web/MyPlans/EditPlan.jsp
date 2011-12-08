<%-- 
    Document   : EditPlan
    Created on : Aug 23, 2011, 10:56:08 AM
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
            function validatePlanForm()
            {
                var x=document.forms["newPlanForm"]["p_title"].value;
                var y=document.forms["newPlanForm"]["p_description"].value;
                if (x==null || x=="")
                {
                    alert("Plan Tile is either null or blank. Please Enter Plan Title.");
                    return false;
                }
                if(y==null||y=="")
                {
                    alert("Plan Description is either null or blank. Please Enter Plan Description.");
                    return false;
                }
            }
        </script>

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
                <jsp:include page="../Left-Nevigation.jspe"/>
                <div id="col2">
                    <h3>Edit My Plans</h3>
                    ||<s:a href="../index.jsp">Index</s:a>||
                    <s:a href="%{MyPlanID}">My Plans</s:a><hr/>    
                    <s:form action="updateplan" method="post" name="newPlanForm" onsubmit="return validatePlanForm()">
                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">                          
                            <s:iterator value="editPlanList" var="myplanlist">
                                <input type="hidden" name="plan_id" value="<s:property value="plan_id"/>"/>
                                <s:textfield label="Title" name="p_title"/>
                                <s:textarea label="Description" name="p_description" cols="20" rows="6"/>
                            </table>
                            <br/>
                        </s:iterator>
                        <s:submit cssClass="floatL buttonsMiddle" value="Update Plan" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                    <br/><br/><br/>                    
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
