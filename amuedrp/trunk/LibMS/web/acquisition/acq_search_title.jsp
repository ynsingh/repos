<%--
    Document   : search
    Created on : Mar 3, 2011, 3:16:02 PM
    Author     : EdRP-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:include page="/admin/header.jsp"/>
<html>
    <head>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
%>
        <style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script language="javascript">
function fun()
{
document.Form1.action="acqAllBiblio.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body onload="fun()" class="datagrid">


<form name="Form1" action="acqAllBiblio.do"  >
      <table  align="left" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5; position: absolute; top: 20%">



  <tr class="header"><td  width="100%"   align="center" colspan="2">


		Own Library Bibliographic Details




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table>
              <tr><td >Enter Starting Keyword(s)</td><td><input  name="search_keyword" type="text" id="search_keyword" onkeyup="fun()"></td>
              <td>


<input type="reset" id="Button1" name="clear" value="CLEAR">


      </td></tr>


          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td>in Field </td><td rowspan="2" valign="top">
                      <select name="search_by" onChange="fun()" id="search_by" size="1">
<option value="title">Title</option>
             <option value="author">Author</option>

</select>
     </td>

              </tr></table></td></tr>
  <tr class="header"><td align="left" colspan="2">Sort By</td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table>
                           <tr><td>Field</td><td><select name="sort_by" id="sort_by" size="1" onChange="fun()" id="">
<option value="title">Title</option>
<option value="author">Author</option>
<option value="id.titleId">Title Id</option>
                                   </select></td>
                           </tr></table>


      </td>

  </tr>
                    <tr><td colspan="2">
                        <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
<%}%>
                        </td> </tr>
  <tr><td><IFRAME  name="f1" src="#" frameborder=0  id="f1" width="100%" height="700px" ></IFRAME></td></tr>


       </table>


</form>



</body>
</html>