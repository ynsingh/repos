<%-- 
    Document   : cat_add_MARC
    Created on : Apr 13, 2011, 4:31:46 PM
    Author     : zeeshan
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
        <title>Assign New MARC Tag for cataloging </title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/formstyle.css"/>
      <%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/formstyle.css"/> --%>


<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">
    </script>
<script type="text/javascript">
            function back()
            {
                location.href="<%=request.getContextPath()%>/cataloguing/cat_editmarc.jsp";
            }
 </script>

    </head>
    <body background="marccat1.jpg" >

        
        <table border="0" cellspacing="4" cellpadding="1" bgcolor="lightskyblue" width="60%" style="position: absolute; top: 23%; left: 20%">
            <tr><td colspan="3" height="30px" bgcolor="#E0E8F5" align="center" ><b>ADD NEW MARC TAG</b></td></tr>
         
          <% String tagnumber="";
            if(request.getAttribute("tagnumber")!=null){
                     tagnumber = (String)request.getAttribute("tagnumber");}
          %>
            <html:form action="/newmarc" method="post" >
                <tr><td align="center" >Tag Number</td><td ><input type="text" value="<%=tagnumber %>" readonly name="tagnumber" /></td><td></td></tr>
            <tr><td align="center">Tag Name</td><td ><input type="text" value="" name="tagname" /></td><td></td></tr>
            <tr><td align="center"><b>Subfield</b>   Symbol &nbsp;$<input type="text" value="a" name="subsymbol" readonly size="2" maxlength="1" /></td><td> Description <input type="text" name="subdesc" value="" maxlength="100" /></td><td>
            R <select name="repeatable1" >
                <option value="true" >true</option>
            <option value="false" >false</option>
            </select></td></tr>

            <tr><td colspan="3"><a href="javascript:animatedcollapse.toggle('toggle')">+</a>
                    <div id="toggle" style="width: 100%; background: lightskyblue; display:none" >
                        <table border="0" align="center" width="80%">
                            <th><b>More Subfields</b></th>
                            <tr><td>
                                     Symbol $<input type="text" value="" name="subsymbol1" size="2" maxlength="1" /></td><td>   Description <input type="text" name="subdesc1" value="" maxlength="100" /></td>
                                <td>R <select name="repeatable11" >
                <option value="true" >true</option>
            <option value="false" >false</option>
            </select></td></tr>

                            <tr><td> Symbol $<input type="text" value="" name="subsymbol2" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc2" value="" maxlength="100" /></td>
                                <td>R <select name="repeatable12" >
                <option value="true" >true</option>
            <option value="false" >false</option>
            </select></td></tr>
                            <tr><td> Symbol $<input type="text" value="" name="subsymbol3" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc3" value="" maxlength="100" /></td>
                                <td>R <select name="repeatable13" >
                <option value="true" >true</option>
            <option value="false" >false</option>
            </select></td></tr>
                            <tr><td> Symbol $<input type="text" value="" name="subsymbol4" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc4" value="" maxlength="100" /></td>
                                <td>R <select name="repeatable14" >
                <option value="true" >true</option>
            <option value="false" >false</option>
            </select></td></tr>
            </table></div></td></tr>

            <tr><td align="center"><input type="submit" value="Save" name="btn" style="width: 150px;" /></td><td><input type="button" value="Cancel" name="cancel" onclick="back();" style="width: 150px;" /></td></tr>
           </html:form>
        </table>

<script type="text/javascript">

animatedcollapse.addDiv('toggle', 'fade=1,height=200px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init();

</script>
    </body>
</html>
