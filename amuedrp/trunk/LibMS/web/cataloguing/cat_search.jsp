<!-- Search Book Based on Title/Author or Accession No etc.-->  
<%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:include page="/admin/header.jsp"/>
<html>
    <head>
  <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
      boolean page=true;
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>
<script language="javascript">
function fun()
{
document.Form1.action="viewAllBiblio.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
</head>
<div
   style="  top:100px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<body onload="fun()">

<form name="Form1" action="viewAllBiblio.do"  >
    <table dir="<%=rtl%>"  align="center" width="90%" border="1px" height="100%">
      <tr class="headerStyle"><td  width="100%"  height="25px" align="center" colspan="2"><%= resource.getString("cataloguing.catviewownbibliogrid.header") %>
      </td></tr>
        <tr><td width="80%" height="40px" valign="middle" >
          <table>
              <tr><td ><%= resource.getString("cataloguing.catsearch.enterkeyword")%>&nbsp;&nbsp;</td><td><input  name="search_keyword" type="text" id="search_keyword" onkeyup="fun()"></td>
              <td>
               &nbsp;&nbsp; <input type="reset" id="Button1" name="clear" value="<%= resource.getString("cataloguing.catsearch.clear")%>">
            </td></tr>
          </table>
      </td>
      <td align="<%=align%>" valign="middle">
          <table >
              <tr><td>&nbsp;&nbsp;<%= resource.getString("cataloguing.catsearch.infield")%></td>
                  <td rowspan="2" valign="top">
                    &nbsp;&nbsp;  <select name="search_by" onChange="fun()" id="search_by" size="1">
                          <option value="title"><%= resource.getString("cataloguing.catoldtitleentry1.title")%></option>
                          <option value="callNo">Call No</option>
                          <option value="mainEntry"><%= resource.getString("cataloguing.catoldtitleentry1.mainentry")%></option>
                      </select>
                    </td>
              </tr></table></td></tr>
            <tr bgcolor="#7697BC"><td align="<%=align%>" colspan="2"><font color="white"> <b><%= resource.getString("cataloguing.catsearch.sortby")%> </b></font></td></tr>
            <tr style="background-color:#e0e8f5;">
                <td align="<%=align%>" colspan="2">
                <table>
                           <tr><td>&nbsp;&nbsp;<%= resource.getString("cataloguing.catsearch.field")%></td><td>&nbsp;&nbsp;<select name="sort_by" id="sort_by" size="1" onChange="fun()" id="">
                            <option value="title"><%= resource.getString("cataloguing.catoldtitleentry1.title")%></option>
                            <option value="mainEntry"><%= resource.getString("cataloguing.catoldtitleentry1.mainentry")%></option>
                            <option value="callNo">Call No</option>
                             </select></td>
                           </tr></table>
              </td>
            </tr>
            <tr><td colspan="2"><IFRAME  name="f1" src="" frameborder=0  id="f1" width="100%" height="700px" class="table" ></IFRAME></td></tr>
       </table>
</form>
</body>
</div>
</html>