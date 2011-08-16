<%-- 
    Document   : cat_card_print
    Created on : Jul 4, 2011, 9:03:15 AM
    Author     : EdRP-05
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.myapp.struts.hbm.*,java.util.*"%>
    <%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp"/>
    <html>
   <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean page=true;
    String newbutton;
    String newbutton1;
    String newbutton2;
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
             <%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
           %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Asif Iqubal" content="MCA,AMU">
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
    <head>
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
List<BibliographicDetails> l12=(List<BibliographicDetails>)session.getAttribute("opacListb");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }
if(l12!=null)
        size = l12.size();
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

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
              <table style="position:absolute; left: 5%; top: 30%;" dir="<%=rtl %>">
        <tr bgcolor="#E0E888"><td colspan="7" align="center"><b><%= resource.getString("cataloguing.cataccessionentry.bibliodetail")%></b></td></tr>
        <tr bgcolor="#E0E8F5"><td width="200"><%= resource.getString("cataloguing.catoldtitle.documenttype")%></td><td width="200"><%= resource.getString("cataloguing.catoldtitleentry1.title")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.mainentry")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.publishername")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.edition")%></td><td width="100" align="right"><%= resource.getString("cataloguing.catviewownbibliogrid.action")%></td><td width="50"></tr>
        <logic:iterate id="BibliographicDetails" name="opacListb" offset="<%=String.valueOf(fromIndex)%>" length="2">
                <tr bgcolor="#98AFC7">
            <td><bean:write name="BibliographicDetails" property="documentType"/></td>
            <td><bean:write name="BibliographicDetails" property="title"/></td>
            <td><bean:write name="BibliographicDetails" property="mainEntry"/></td>
            <td><bean:write name="BibliographicDetails" property="publisherName"/></td>
            <td><bean:write name="BibliographicDetails" property="edition"/></td>
            <td><a href="<%= request.getContextPath()%>/cataloguing/showPrintItems.do?a=<bean:write name="BibliographicDetails" property="id.biblioId"/>">Show Items</a></td>
           </tr>
        </logic:iterate>
        <tr><td colspan="7" align="center" height="20px;"></td></tr>
        <tr><td colspan="7" align="center"><input type="button" onclick="return send()"  value="<%=newbutton2%>"/></td></tr>
</table>
    </body>
</html>
