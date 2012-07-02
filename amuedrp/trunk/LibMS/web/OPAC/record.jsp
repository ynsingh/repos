<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.hbm.DocumentDetails"%>

<html style="height: 100%"><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body
{
   overflow: hidden;
   height: 100%;
}

        </style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

int fromIndex,toIndex;
int pagesize=10,size;
int pageIndex;
int noofpages;
int modvalue;
String index,head;
List obj1;

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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
session.setAttribute("page_name", "accessionno");


List<DocumentDetails> l12=(List<DocumentDetails>)session.getAttribute("documentDetail");
System.out.println(l12.size()+"............");
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
 System.out.println("ModValue"+modvalue);
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;

 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;


toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;

System.out.println(size+"   "+pageIndex+" "+noofpages);
  %>
  <script>
        //   reSize Iframe when ever child  calls  it
   function setIframeHeight1() {
       iframe=document.getElementById('f2');
    if (iframe) {

        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
};
   

      </script>


    </head>
    <%
head=(String)   session.getAttribute("head");
if(head!=null){
%>
<jsp:include page="opacheader.jsp"/>
<%}%>
    <body onload="parent.setIframeHeight2(document.getElementById('f2').height);" style="margin:0px 0px 0px 0px;font: arial;font-size: 11px;text-align: center;height:100%">
        



   
             <IFRAME  name="f2"   src="<%=request.getContextPath()%>/OPAC/allitems.jsp" frameborder=0  width="100%" scrolling="no"  id="f2"></IFRAME>
             <br/>Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="f1" href="<%=request.getContextPath()%>/OPAC/allitems.jsp?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
          
  

 <%
head=(String)   session.getAttribute("head");
if(head!=null){
%>
<jsp:include page="opacfooter.jsp"/>
<%}%>

    </body></html>