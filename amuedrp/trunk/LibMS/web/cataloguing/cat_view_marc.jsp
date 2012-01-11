<%-- 
    Document   : cat_view_marc
    Created on : Jul 23, 2011, 12:46:48 PM
    Author     : zeeshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


        <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@page import="com.myapp.struts.hbm.*,java.util.*"%>
    <%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
  <jsp:include page="/admin/header.jsp"/>
<html>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <head>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");


String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String title=(String)session.getAttribute("title");
String doc_type=(String)session.getAttribute("doc_type");
String isbn10=(String)session.getAttribute("isbn10");
String path= request.getContextPath();
pageContext.setAttribute("path", path);
%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Muhammad Zeeshan" content="MCA,AMU">
      <title></title>
       <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_old_title.jsp";
    return false;
}
</script>
             <script type="text/javascript" language="javascript">
    function submitNew()
{
    var buttonvalue="Merge Title";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
    </script>
         <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }

</style>
</head>
<body bgcolor="#FFFFFF">
<%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;
%>
<%
int i=0;
 int j=0;
List<Biblio> l11=(List<Biblio>)session.getAttribute("MARCList");
//System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffffffffffff = "+l11.size());
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(l11!=null)
        size = l11.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;
%>

<%
String val="?";
if(session.getAttribute("marcbutton").equals("Delete")){
    val="Delete";
    }
else
    {
        val="View";
    }
    %>

        <table style="position:absolute; left: 10%; top: 20%;" class="table">
        <tr class="headerStyle" ><td width="100" >Biblio Id</td><td width="200">MarcTag</td><td width="200">Title</td><td width="100">Action</td></tr>
        <logic:iterate id="Biblio" name="MARCList" offset="<%=String.valueOf(fromIndex)%>" length="2">
          <html:form action="/viewMARC">
            <tr>
        <input type="hidden" name="bib_id" value='<bean:write name="Biblio" property="id.bibId"/>'/>
            <td><bean:write name="Biblio" property="id.bibId"/></td>
            <td><bean:write name="Biblio" property="id.marctag"/></td>
            <td><bean:write name="Biblio" property="$a"/></td>

        </tr>
                <tr><td colspan="4" align="right">  <a><input type="submit" value="<%=val %>" style="border: hidden; cursor: pointer;"/></a></td></tr>
        </html:form>
        </logic:iterate>

<tr align="center">
        <td align="center" dir="" colspan="4"><p align="center" dir="">Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="same" href="<%=request.getContextPath()%>/cataloguing/cat_view_marc.jsp?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
            </p>
        </td>
    </tr>
        </table>
</body>
</html>
