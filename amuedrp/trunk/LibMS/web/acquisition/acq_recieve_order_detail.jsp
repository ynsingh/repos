<%--
    Document   : acq_order_process
    Created on : Apr 18, 2011, 5:55:05 PM
    Author     : maqbool
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation,java.util.List,com.myapp.struts.Acquisition.ApprovalList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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

List<ApprovalList> l1=(List<ApprovalList>)session.getAttribute("opacList1");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else

     {
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
String msg1=(String) request.getAttribute("msg1");

DateCalculation doc=new DateCalculation();
String cdate=doc.now();

if(request.getAttribute("AcqOrderManagementActionForm")!=null)
    session.setAttribute("AcqOrderManagementActionForm", request.getAttribute("AcqOrderManagementActionForm"));
if(session.getAttribute("AcqOrderManagementActionForm")!=null)
    request.setAttribute("AcqOrderManagementActionForm", session.getAttribute("AcqOrderManagementActionForm"));
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
    alert ("List selected="+list+list1+list2+list3);
  document.getElementById("list").value  = list;
  document.getElementById("list1").value = list1;
  document.getElementById("list2").value = list2;
  document.getElementById("list3").value = list3;
  if (isEmpty(list)!=true||isEmpty(list1)||isEmpty(list2)||isEmpty(list3))
  {

    alert(list+list1+list2+list3);
        return true;
  }
  else
     return false;
}

function quit()
  {

      window.location="/LibMS/acquisition/acq_initiate_approval.jsp";
      return false;
  }


    </script>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <html:form method="post"  styleId="f" action="/acq_recieve_order_detail" style="position:absolute; left:20%; top:20%;">
            <table border="1" class="table" width="900" align="center">
                <html:hidden property="library_id" name="AcqOrderManagementActionForm" value="<%=library_id%>" />
                 <html:hidden property="sub_library_id" name="AcqOrderManagementActionForm" value="<%=sub_library_id%>" />
               
            <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Recieving Order Process</td>
       </tr>
       <tr><td><br>
               <table width="700" border="0">

                   <tr><td  align="right" class="txtStyle"> <strong> Recieving No:</strong></td>
                               <td><html:text property="receiving_no"  styleId="orderno" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" /></td>

                            </tr>


                   <tr>
                       <td align="right"> <strong>Order No: </strong></td>
                       <td> <html:text readonly="true" property="order_no" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" >

  </html:text>
</td>
                     <td width="150" align="right" class="txtStyle"><strong>Recieving date:</strong> </td>
                     <td><html:text readonly="false" value="<%=cdate%>" property="recieved_date" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />

    </td>
                   </tr>
                   <tr>
                       <td align="right" class="txtStyle"><strong>Recieved By:</strong></td>
                       <td><html:text readonly="false" styleId="orderby" property="recieved_by" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />

                        </td>
                        

                   </tr>
                   <tr>
                      <td align="right" class="txtStyle"><strong>Vendor:</strong></td>
                       <td><html:text readonly="true" property="vendor" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />
                         </td>
                   </tr>
                    <tr>
                   <td><input type="text" name="list" id="list" value="" /></td>
                       <td><input type="text" name="list1" id="list1" value="" /></td>
                    <td><input type="text" name="list2" id="list2" value="" /></td>
                    <td><input type="text" name="list3" id="list3" value="" /></td>
                   </tr>

        </table>


 </td></tr>
        <tr>
                       <td align="center"> <input type="hidden" name="list" id="list" value="" />
                       <input type="submit"   name="button" value="Process Order" class="txt1" onclick="return validate();" />
                    <input align="left" type="button" name="button" value="Cancel" class="txt1" onclick="return quit();"/></td>


                   </tr>
        <tr>
       <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Ordered List For Recieving </td>
       </tr>
        <tr><td><div id="divlist">
                   <iframe name="listdiv" frameborder="0px" width="100%" src="<%=request.getContextPath()%>/acquisition/recieved_orderlist.jsp"></iframe>
               </div>
           </td></tr>
        <tr align="center">
        <td align="center" dir="" colspan="4"><p align="center" dir="">Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="listdiv" href="<%=request.getContextPath()%>/acquisition/recieved_orderlist.jsp?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
            </p>
        </td>
        <tr><td>
               <table>
                  
               </table>

            </td></tr>

            </table>
        </html:form>
          <form name="myform" action="<%=request.getContextPath()%>/acquisition/acq_recieve_order_detail.jsp">
       <input type="hidden" name="recieved_by" value="" class="textBoxWidth"/>
       <input type="hidden" name="recieved_date" value="" class="textBoxWidth"/>
        <input type="hidden" id="check1" onchange="get_check_value(<%=i%>)" value="" class="textBoxWidth"/>
       <input type="hidden" id="check" onchange="get_check_value(<%=i%>)" value="" class="textBoxWidth"/>
   </form>
 </body>
</html>
