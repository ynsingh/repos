<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
String authormsg=(String)request.getAttribute("authormsg");
%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_view_titlegrid.jsp";
    return false;
}
function acq()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_search_all_title.jsp";
    return false;
}
</script>
<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
            <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/animatedcollapse.js"></script>
<script type="text/javascript">
   // var i=getElementById(id);
  //  for(i=1;i<=3;i++)
//{animatedcollapse.addDiv(i, 'fade=1,height=20px')}
animatedcollapse.addDiv('1', 'fade=1,height=20px')
animatedcollapse.addDiv('2', 'fade=1,height=20px')
animatedcollapse.addDiv('3', 'fade=1,height=20px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}
animatedcollapse.init()

</script>

    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <html:form method="post" action="/acq_update_biblio" style="position:absolute; left:200px; top:150px;"  >
       <table border="0" class="table" width="900" align="center">
           <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b>Bibliographic Detail Entry</b></td></tr>
                <tr><td>
                        <table border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>
                        <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" /><td></td>
                        <td><html:hidden property="title_id" name="AcqBiblioActionForm"  /> </td>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="right" class="txtStyle"><strong>Document Type:</strong></td>
    <td><html:text readonly="true" property="document_type" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Author:</strong></td>
    <td><html:text readonly="true" property="author" name="AcqBiblioActionForm" styleClass="textBoxWidth" />



      <br><span class="err"><%if(authormsg!=null){%><%= authormsg %><%}%></span>
  </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

  <tr>
    <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
    <td><html:text readonly="true" property="publisher_name" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
   <td><html:text readonly="true" property="publishing_year" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
            <br><span class="err">   <html:messages id="err_name" property="publishing_year">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>

  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>ISBN: </strong></td>
    <td><html:text readonly="true" property="isbn" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
    <td><html:text readonly="true" property="edition" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>


  </tr>
    <tr><td colspan="5" height="5px"></td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>



</table>
</td>
<td>
    <table>

       <tr>
              <td width="150" align="right" class="txtStyle"><strong>Title:</strong> </td>
    <td><html:text readonly="true" property="title" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
    </td>
       </tr>
       <tr>
            <td align="right" class="txtStyle"><strong>Sub Authors:</strong></td>
            <td><html:text readonly="true" property="sub_author" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

  <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td></tr>
            <!--<input type="button" onclick="generateRow()" value="+"/><div id="my_div"></div></td>-->
            <tr>
                <td></td>
                <td>
            <div id="1" style="display: none;">
                <html:text readonly="true" property="sub_author0" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="7"/>
</div>
<div id="2" style="display: none;">
    <html:text readonly="true" property="sub_author1" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="8"/>
</div>
<div id="3" style="display: none;">
    <html:text readonly="true" property="sub_author2" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="9"/>
</div>
</td>
            </tr>
<tr>
            <td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
            <td><html:text readonly="true" property="publication_place" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
       </tr>
       <tr>
            <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
            <td><html:text readonly="true" property="lcc_no" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
         </td>
       </tr>
       <tr>
             <td align="right" class="txtStyle"><strong>No Of Volumes</strong></td>
             <td><html:text readonly="true" property="volume_no" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
       </tr>
   </table>

</td>
                </tr>

                    <tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="5px" class="mandatory" align="right"></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="4">
    <input name="button" type="button" onclick="return send()" value="Back" />

</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>

       </table>
  </html:form>




    </body>
</html>

