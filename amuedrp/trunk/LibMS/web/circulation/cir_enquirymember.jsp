<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page  pageEncoding="UTF-8" import="java.util.*,com.myapp.struts.hbm.*,com.myapp.struts.systemsetupDAO.DocumentCategoryDAO"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
  
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
 <script language="javascript" type="text/javascript">
   function Submit()
{

    var sublib_name=document.getElementById('memid');
var str="Enter Following Values:-";
    if(sublib_name.value=="")
        {str+="\n Enter Member Id ";
             alert(str);
             document.getElementById('memid').focus();
            return false;
        }else{
            return true;

        }
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
 function Submit1()
{
   var sublib_name=document.getElementById('accessionno');
var str="Enter Following Values:-";
    if(sublib_name.value=="")
        {str+="\n Enter AccessionNo ";
             alert(str);
             document.getElementById('accessionno').focus();
            return false;
        }else{
            return true;

        }

}

 function back()
  {

       window.location="<%=request.getContextPath()%>/admin/main.jsp";

   }
  // function validation()
  //  {

      // var buttonvalue="Register";
   // document.getElementById("button").setAttribute("value", buttonvalue);
  //  var sublib_name=document.getElementById('memid');
//var str="Enter Following Values:-";
 //   if(sublib_name.value=="")
       // {str+="\n Enter Member Id ";
       //      alert(str);
          //   document.getElementById('memid').focus();
          //  return false;
      //  }else{
          //  return true;

      //  }
   // }
 </script>
      <%!
    Locale locale=null;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
</head>
<body>
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

     <html:form  action="/cir_chk1" method="post">
 <table dir="<%=rtl%>" border="1" class="table" width="50%" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Enquiry on Member</td>
                    <td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Enquiry on Title</td>
                </tr>
                    <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">

                   
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>

  <tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.cir_newmember.memberid")%><a class="star">*</a></strong></td>
    <td dir="<%=rtl%>"><html:text property="memid" styleId="memid"  value="" styleClass="textBoxWidth"/>

    </td>
  </tr>
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
 
  <tr>
    <td dir="<%=rtl%>" colspan="4" align="center"><input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"  onClick="return Submit();"/>

        <input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="back();" />
 </td>
</tr>
  <tr><td dir="<%=rtl%>" height="15px" colspan="4" ></td></tr>
</table>
                    </td><td dir="<%=rtl%>" valign="top" align="center"> <br/>
              <table dir="<%=rtl%>" cellspacing="10px">


  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>

  <tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong>Accession No: <a class="star">*</a></strong>

    </td>
    <td dir="<%=rtl%>"><html:text property="accessionno" styleId="accessionno"  value="" styleClass="textBoxWidth"/>

    </td>
  </tr>
     
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>

  <tr>
    <td dir="<%=rtl%>" colspan="4" align="center"><input type="submit" name="button" value="searchTitle"  onClick="return Submit1();"/>

        <input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="back();" />
 </td>
</tr>
  <tr><td dir="<%=rtl%>" height="15px" colspan="4" ></td></tr>
</table>



                    </td></tr>
     <tr><td>


  <%
 String msg=(String)request.getAttribute("msg");
if(msg!=null){
%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg%></span>
 <%
}

%>
  <%
 String msg1=(String)request.getAttribute("msg1");
if(msg1!=null){
%>
 <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
 <%
}
%>                               </td></tr>
<tr><td colspan="2">

        <%! String title,author,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype;
int no_of_copy=0;
 BibliographicDetails dd=new BibliographicDetails();

%>
<%


    DocumentDetails list=(DocumentDetails)session.getAttribute("TitleDetails");
    session.removeAttribute("TitleDetails");
dd=(BibliographicDetails)session.getAttribute("Title");
int total=0;

//System.out.println("data"+list+session.getAttribute("noofchk"));
if(list!=null && dd!=null && session.getAttribute("noofchk")!=null ){

total=Integer.parseInt((String)session.getAttribute("noofchk"));
      

           booktype=list.getBookType();
            title=list.getTitle();
            subtitle=list.getSubtitle();
            author=list.getMainEntry();
            publ_pl=list.getPublicationPlace();
            pub_name=list.getPublisherName();
            pub_yr=list.getPublishingYear();

         accno=list.getAccessionNo();
           
            callno=list.getCallNo();
            isbn=list.getIsbn10();
            ed=list.getEdition();
           
            lib_id = list.getId().getLibraryId();
          no_of_copy=dd.getNoOfCopies();
            status =list.getStatus();
            String sublib_id = (String)list.getId().getSublibraryId();
            if(title==null)title="";
            if(subtitle==null)subtitle="";
            if(author==null)author="";
            if(publ_pl==null)publ_pl="";
            if(pub_name==null)pub_name="";
            if(pub_yr==null)pub_yr="";
            if(pages==null)pages="";
            if(index==null)index="";
            if(callno==null)callno="";
            if(isbn==null)isbn="";
            if(ed==null)ed="";
            if(lib_id==null)lib_id="";
            if(phy_width==null)phy_width="";
            if(status==null)status="";
            if(accno==null)accno="";
            DocumentCategoryDAO doccatdao=new DocumentCategoryDAO();
            DocumentCategory docc = (DocumentCategory)doccatdao.searchDocumentCategory(lib_id, sublib_id, booktype);
            String issuetype ="";
            if(docc!=null)
                issuetype=docc.getIssueCheck();
 %>

<TABLE align="<%=align%>" dir="<%=rtl%>"  border='0' class="table" cellspacing='0' cellpadding='0' valign="top" width="100%" >

 <TR dir="<%=rtl%>">
        <TD class="headerStyle" align="center" width="45%" valign='top' dir="<%=rtl%>" colspan="4" >Title Details</TD>

    </TR>
    <TR dir="<%=rtl%>">
        <TD class="headerStyle" align="right" width="45%" valign='top' dir="<%=rtl%>" ><%=resource.getString("opac.newjsp.title")%></TD>
        <TD valign='top' dir="<%=rtl%>" >&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=title%></TD>
    </TR>

    <TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>" align="right" class="headerStyle"><%=resource.getString("opac.newjsp.mainauthor")%></TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=author%></TD>
    </TR>

    <TR dir="<%=rtl%>">
        <TD class="headerStyle" valign='top' align="right" dir="<%=rtl%>" ><%=resource.getString("opac.newjsp.pubplace")%>  </TD>
        <TD valign='top' dir="<%=rtl%>" >&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=publ_pl%></TD>
    </TR>
    <TR dir="<%=rtl%>">
        <TD class="headerStyle" valign='top' align="right" dir="<%=rtl%>" ><%=resource.getString("opac.newjsp.pubname")%>  </TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD colspan=2 dir="<%=rtl%>"><%=pub_name%></TD>
    </TR>
    <TR dir="<%=rtl%>">
        <TD class="headerStyle" valign='top' align="right" dir="<%=rtl%>" ><%=resource.getString("opac.newjsp.pubyear")%>  </TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=pub_yr%></TD>
    </TR>

    <TR dir="<%=rtl%>">
        <TD class="headerStyle" valign='top' align="right" dir="<%=rtl%>" ><%=resource.getString("opac.newjsp.callno")%> </TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=callno%></TD>
    </TR>

    <TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>"  align="right" class="headerStyle"><%=resource.getString("opac.newjsp.isbn")%></TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=isbn%></TD>
    </TR>
 <TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>" align="right" class="headerStyle">No of Copies</TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=no_of_copy %></TD>
    </TR>

    <TR dir="<%=rtl%>">
        <TD class="headerStyle" valign='top' align="right" dir="<%=rtl%>"  >AccessionNo</TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=accno %></TD>
    </TR>
     <TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>" align="right"  class="headerStyle">Issue Type</TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=issuetype %></TD>
    </TR>
<TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>" align="right" class="headerStyle">Total CheckOut</TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=total %></TD>
    </TR>
<TR dir="<%=rtl%>">
        <TD  valign='top' dir="<%=rtl%>" align="right" class="headerStyle">On Self</TD>
        <TD valign='top' dir="<%=rtl%>">&nbsp; </TD>
        <TD  colspan=2 dir="<%=rtl%>"><%=no_of_copy-total %></TD>
    </TR>
</TABLE>
<%}%>

    </td></tr>
 </table>
           <input type="hidden" id="button" name="button" value=""/>
</html:form>
</div>
</body>


</html>

