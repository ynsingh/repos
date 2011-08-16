<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>
<%@ page import="java.util.*,com.myapp.struts.hbm.*,com.myapp.struts.Acquisition.AcqBiblioActionForm,java.text.*,java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

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

List<AcqBibliographyDetails> l1=(List<AcqBibliographyDetails>)session.getAttribute("approval1");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(l1!=null)
        size = l1.size();
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

<% int i=0;
 int j=0;
%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
String msg1=(String) request.getAttribute("msg1");
String approval_no=(String)session.getAttribute("approval_no");
DateCalculation doc=new DateCalculation();
String cdate=doc.now();

if(request.getAttribute("AcqBiblioActionForm")!=null)
    session.setAttribute("AcqBiblioActionForm", request.getAttribute("AcqBiblioActionForm"));
if(session.getAttribute("AcqBiblioActionForm")!=null)
    request.setAttribute("AcqBiblioActionForm", session.getAttribute("AcqBiblioActionForm"));
%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_update_approval_list.jsp";
    return false;
}
</script>

<script language="javascript">

function validate()
{
    var list =top.f1.document.f.list.value;
    var list1=top.f1.document.f.list1.value;
    var list2 =top.f1.document.f.list2.value;
    var list3=top.f1.document.f.list3.value;
   // alert ("List selected="+list+list1+list2+list3);
  document.getElementById("list").value  = list;
  document.getElementById("list1").value = list1;
  document.getElementById("list2").value = list2;
  document.getElementById("list3").value = list3;
  if (isEmpty(list)!=true||isEmpty(list1)||isEmpty(list2)||isEmpty(list3))
  {

 //   alert(list+list1+list2+list3);
        return true;
  }
  else
     return false;
}

function quit()
  {

      window.location="<%=request.getContextPath()%>/acquisition/acq_initiate_approval.jsp";
      return false;
  }

function del1()
{
  //  alert("dd");
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('button').focus();
        return false;
    }
    else
        {
        return true;

        }


}

</script>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <html:form method="post" styleId="f" action="/deleteapproval" onsubmit="return del1();" style="position:absolute; left:20%; top:20%;">
   <table border="1" class="table" width="900" align="center">
        <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
        <html:hidden property="title_id" name="AcqBiblioActionForm"  />
        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" />
       <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Approval Process</td>
       </tr>
       <tr>
           <td><br>
               <table width="700" border="0">
                   <tr>
                       <td align="right"> <strong>Approval No:</strong><br></td>
                       <td> <html:text readonly="true" property="approval_no" styleId="approval_no" name="AcqBiblioActionForm" styleClass="textBoxWidth" >

  </html:text>
</td>
    <td width="150" align="right" class="txtStyle"><strong>Recommended By:</strong> </td>
    <td><html:text readonly="true" property="recommended_by"  name="AcqBiblioActionForm" styleClass="textBoxWidth" />

    </td>
         </tr>
               <tr>
                       <td align="right" class="txtStyle"><strong>Approved on:</strong></td>
                       <td><html:text readonly="true"  property="approval_date" styleId="approval_date" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

  </td>
       <td align="right" class="txtStyle"><strong>Vendor:</strong></td>
       <td><html:text property="vendor"  readonly="true" name="AcqBiblioActionForm"/></td>
       </tr>

       <tr>
              <td align="right" class="txtStyle"><strong>Approved By:</strong></td>
              <td><html:text readonly="true" styleId="approved_by" property="approved_by"  name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                        <td></td>
  <td></td>
                   </tr>
                   <tr>

                       <td colspan="3" align="right">
                           <% if(button.equalsIgnoreCase("Delete")){%>
                           <input  type="submit" name="button" id="button" value="Delete" class="txt1" />
                           <%}%>
                           <input  type="button" name="button" value="Back" class="txt1" onclick="return quit();"/></td>
        
                   </tr>
</table>
                   <br>
           </td></tr>

       <tr>

           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Approval List</td>
       </tr>
       <tr><td><div id="divlist">
                   <iframe name="listdiv"  width="100%" src="<%=request.getContextPath()%>/acquisition/approvallist1.jsp"></iframe>
               </div>
           </td></tr>
<tr align="center">
        <td align="center" dir="" colspan="4"><p align="center" dir="">Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="listdiv" href="<%=request.getContextPath()%>/acquisition/approvallist1.jsp?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
            </p>
        </td>
    </tr>
    </table>
     </html:form>
   <form name="myform" action="<%=request.getContextPath()%>/acquisition/acq_update_approval_list.jsp">
       <input type="hidden" name="recommended_by" value="" class="textBoxWidth"/>
       <input type="hidden" name="approval_date" value="" class="textBoxWidth"/>
       <input type="hidden" name="recommended_by" value="" class="textBoxWidth"/>
       <input type="hidden" name="approved_by" value="" class="textBoxWidth"/>
       <input type="hidden" id="check1" onchange="get_check_value(<%=i%>)" value="" class="textBoxWidth"/>
       <input type="hidden" id="check" onchange="get_check_value(<%=i%>)" value="" class="textBoxWidth"/>
   </form>
    </body>
</html>
