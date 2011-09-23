<%-- 
    Document   : cat_editmarc
    Created on : May 22, 2011, 3:18:19 PM
    Author     : zeeshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="/admin/header.jsp"/>
<html><head>
        <title>Edit MARC Tags</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.4.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">

<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>

<script type="text/javascript" >

animatedcollapse.addDiv('one', 'fade=0,speed=400,group=marc,persist=1')
animatedcollapse.addDiv('two', 'fade=0,speed=400,group=marc,hide=1')

animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()


</script>
           </head>
           <body>
               
                
               <table border="0"  cellpadding="4" style=" top: 20%;left: 20%; position: absolute; background-color: silver;  width: 50% " >
                       <html:form action="/shownewmarc" method="post">
                           <tr><td> &nbsp; </td></tr>
                           <tr><td align="center"><b><font face="georgia" size="4" >
               <a href="#" rel="toggle[one]">Edit Marc Tags</a></font>
               <div id="one" style="width: 550px;  background-color: lightsteelblue;"  >
                   <table>
                   <tr><td>&nbsp;</td> <td><input type="submit" Value="Add" name="btn"/></td>
                   <tr><td><input type="text" value="" name="tagnumber" /></td> <td><input type="submit" Value="Modify" name="btn"/></td></tr>
                 <tr><td>&nbsp;</td> <td><input type="submit" Value="Delete" name="btn"/></td></tr>
                  </table></div>
                              <br><hr width="90%" size="2" color="green"/>     <font face="georgia" size="4" >
              <a href="#" rel="toggle[two]">Customized MARC Biblio graphy</a></font>
               <div id="two" style="width: 550px;  background-color: lightsteelblue;" >
                 Add a New Customized MARC Record : <input type="submit" Value="New" name="btn"/><br>
                 Modify an Existing Customized MARC Record : <input type="submit" Value="Update" name="btn"/><br>
                 Delete an Existing Customized MARC Record : <input type="submit" Value="Delete" name="btn"/><br>
                  </div> </b></td></tr>
                           <tr><td> &nbsp;<font color="green"> <b> <% if(request.getAttribute("msg")!=null){out.println(request.getAttribute("msg")); }%></b></font> </td></tr>
                </html:form> 
               </table>




           </body>
</html>