<%@ page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.cataloguingDAO.MarcHibDAO,com.myapp.struts.opacDAO.*;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script language="javaScript" src="fulldetail.js"></script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body>
<%! String title,author,doctype,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype,lccno;
int no_of_copy=0,bib_id=0;



%>
<%
 List<BibliographicDetails> dd = (List<BibliographicDetails>)session.getAttribute("documentDetail1");
  MarcHibDAO dao=new MarcHibDAO();
 String call=null;
 if(dd!=null){
 call= dd.get(0).getCallNo();
}
 
if(call!=null)
{

        Biblio rst1 = dao.searchopacBiblioId(dd.get(0).getId().getLibraryId(),dd.get(0).getId().getSublibraryId(),call);

if(rst1!=null)
       {
bib_id=rst1.getId().getBibId();
       List<Biblio> rst2 = dao.searchBiblioId(dd.get(0).getId().getLibraryId(),dd.get(0).getId().getSublibraryId(),bib_id);
        session.setAttribute("marcList", rst2);
        }
}
%>

<%!
    Locale locale;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>

<%
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


    <TABLE align="center" dir="<%=rtl%>"  style="text-align: justify;"  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="60%">
   <TR dir="<%=rtl%>">
       <td colspan="3" align="center" style="margin:0px 0px 0px 0px" class="header">
           <%--<a href="" style="border: none;text-decoration: none;" onclick="document.getElementById('s1').src='<%=request.getContextPath()%>/images/sub.gif'">  <img id="s1" src="<%=request.getContextPath()%>/images/sub1.gif"/></a>
           <img id="b1" src="<%=request.getContextPath()%>/images/brief1.gif"/>
       <img id="m1" src="<%=request.getContextPath()%>/images/marc1.gif"/>
       <img id="f1" src="<%=request.getContextPath()%>/images/full1.gif"/>--%>
      <a href="../OPAC/newjsp.jsp" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b >Brief Details</b></a>&nbsp;|&nbsp;
       <a href="../OPAC/subject.jsp" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b > Subject Details</b></a>&nbsp;|&nbsp;
      <a href="../OPAC/fulldetails.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b> Full Details</b></a>&nbsp;|&nbsp;
     <a href="<%=request.getContextPath()%>/OPAC/marc_details.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b> MARC Details</b></a>

       </td>
    </TR>
    <tr><td>
            <%if(session.getAttribute("marcList")!=null){%>
<table class="datagrid">

            <logic:iterate id="BiblioTemp" name="marcList">
                <tr>
                    <td>
                        <bean:write name="BiblioTemp" property="id.marctag"/>
                    </td>
                    <td>
                        <logic:present name="BiblioTemp" property="indicator1">
                        <bean:write name="BiblioTemp" property="indicator1"/>
                        </logic:present>
                        <logic:notPresent name="BiblioTemp" property="indicator1">
                        #
                        </logic:notPresent>
                    </td>
                    <td>
                        <logic:notPresent name="BiblioTemp" property="indicator2">
                        #
                        </logic:notPresent>
                        <logic:present name="BiblioTemp" property="indicator2">
                        <bean:write name="BiblioTemp" property="indicator2"/>
                        </logic:present>
                    </td>
                    <td style="text-align: justify">
                        <logic:lessEqual value="010" name="BiblioTemp" property="id.marctag">
                        <bean:write name="BiblioTemp" property="$a"/>
                        </logic:lessEqual>
                        <logic:greaterEqual value="011" name="BiblioTemp" property="id.marctag">
                        $a<bean:write name="BiblioTemp" property="$a"/>
                        </logic:greaterEqual>
                        <logic:present name="BiblioTemp" property="$b">
                        $b<bean:write name="BiblioTemp" property="$b"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$c">
                        $c<bean:write name="BiblioTemp" property="$c"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$d">
                        $d<bean:write name="BiblioTemp" property="$d"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$e">
                        $e<bean:write name="BiblioTemp" property="$e"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$f">
                        $f<bean:write name="BiblioTemp" property="$f"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$g">
                        $g<bean:write name="BiblioTemp" property="$g"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$h">
                        $h<bean:write name="BiblioTemp" property="$h"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$i">
                        $i<bean:write name="BiblioTemp" property="$i"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$j">
                        $j<bean:write name="BiblioTemp" property="$j"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$k">
                        $k<bean:write name="BiblioTemp" property="$k"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$l">
                        $l<bean:write name="BiblioTemp" property="$l"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$m">
                        $m<bean:write name="BiblioTemp" property="$m"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$n">
                        $n<bean:write name="BiblioTemp" property="$n"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$o">
                        $o<bean:write name="BiblioTemp" property="$o"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$p">
                        $p<bean:write name="BiblioTemp" property="$p"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$q">
                        $q<bean:write name="BiblioTemp" property="$q"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$r">
                        $r<bean:write name="BiblioTemp" property="$r"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$s">
                        $s<bean:write name="BiblioTemp" property="$s"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$t">
                        $t<bean:write name="BiblioTemp" property="$t"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$u">
                        $u<bean:write name="BiblioTemp" property="$u"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$v">
                        $v<bean:write name="BiblioTemp" property="$v"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$w">
                        $w<bean:write name="BiblioTemp" property="$w"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$x">
                        $x<bean:write name="BiblioTemp" property="$x"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$y">
                        $y<bean:write name="BiblioTemp" property="$y"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$z">
                        $z<bean:write name="BiblioTemp" property="$z"/>
                        </logic:present>
                    </td>
                </tr>
            </logic:iterate>
        </table>
<%}else{%>
Marc Information Not Found
<%}%>
        </td></tr>
    </TABLE>
<%
session.removeAttribute("marcList");
%>
</body>
</html>