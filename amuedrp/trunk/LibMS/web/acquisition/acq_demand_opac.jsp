<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<jsp:include page="/admin/header.jsp"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");
  DateCalculation doc=new DateCalculation();
  String cdate=doc.now();
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="right";}
    else{ rtl="RTL";align="left";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String base=(String)session.getAttribute("base");
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_search_opac.jsp";
    return false;
}
function vendorFalse()
{
   var key=document.getElementById('mode').options[document.getElementById('mode').selectedIndex].value;
   var x=document.getElementById("vendor");
   if(key=="On Approval"){


    x.disabled=true;
    }
    else{
        x.disabled=false;
    }
}


function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }


function check1()
{
    if(document.getElementById('noc').value=="")
    {
        alert("Enter No of Copies");

        document.getElementById('noc').focus();

        return false;
    }
    if(document.getElementById('price').value=="")
    {
        alert("Enter Price");

        document.getElementById('price').focus();

        return false;
    }

    var keyValue = document.getElementById('primary_budget').options[document.getElementById('primary_budget').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Budget Head");

        document.getElementById('primary_budget').focus();

        return false;
    }

         if(document.getElementById('requestby').value=="")
    {
        alert("Enter Requested By Name");

        document.getElementById('requestby').focus();

        return false;
    }


      var keyValue = document.getElementById('mode').options[document.getElementById('mode').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Acq Mode");

        document.getElementById('mode').focus();

        return false;
    }

  if(keyValue=="Firm Order"){
     var keyValue1 = document.getElementById('vendor').options[document.getElementById('vendor').selectedIndex].value;
   if (keyValue1=="Select")
    {

        alert("Select Vendor");

        document.getElementById('vendor').focus();

        return false;
    }

}

return true;
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
   <html:form method="post" onsubmit="return check1();" action="/acquisition/acq_acquisition_of_opac" style="position:absolute; left:20%; top:20%;">
  <table border="1" class="table" width="750" align="center">
        <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
       <html:hidden property="demand_id" name="AcqBiblioActionForm"  />
        <html:hidden property="title" name="AcqBiblioActionForm"  />
        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" />
        <html:hidden property="mem_id" name="AcqBiblioActionForm" />
       <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2">Bibliographic Details</td></tr>
       <tr><td><br>

               <table border="0">
                   <tr>
                       <td align="right"> <strong>Document Type:</strong><br></td>
                       <td> <html:text readonly="true" property="document_type" name="AcqBiblioActionForm" styleClass="textBoxWidth" >

  </html:text>
</td>
                   </tr>
               <tr>
                       <td align="right" class="txtStyle"><strong>Author:</strong></td>
  <td><html:text readonly="true" property="author" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
  </td>
       </tr>
       <tr>
              <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
  <td><html:text readonly="true" property="publisher_name" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
                   <tr>
                       <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text readonly="true" property="publishing_year" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

  </td>
                   </tr>
               <tr>
                        <td align="right" class="txtStyle"><strong>ISBN: </strong></td>
    <td><html:text readonly="true"  property="isbn" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
               </table>
                   <br>       </td>
       <td>
           <table>
               <tr>
                      <td width="150" align="right" class="txtStyle"><strong>Title:</strong> </td>
    <td><html:text readonly="true" property="title" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

    </td>
               </tr>
       <tr>
            <td align="right" class="txtStyle"><strong>Sub Authors:</strong></td>
            <td><html:text property="sub_author" name="AcqBiblioActionForm" styleClass="textBoxWidth" readonly="true" />

  <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td></tr>
            <!--<input type="button" onclick="generateRow()" value="+"/><div id="my_div"></div></td>-->
            <tr>
                <td></td>
                <td>
            <div id="1" style="display: none;">
                        <html:text property="sub_author0" readonly="true" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="7"/>
</div>
<div id="2" style="display: none;">
    <html:text property="sub_author1" readonly="true" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="8"/>
</div>
<div id="3" style="display: none;">
    <html:text property="sub_author2" readonly="true" name="AcqBiblioActionForm" styleClass="textBoxWidth" tabindex="9"/>
</div>
</td>
            </tr>
               <tr>
                              <td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
  <td><html:text readonly="true" property="publication_place" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
  </td>
               </tr>
               <tr>
                                           <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
  <td><html:text readonly="true" property="lcc_no" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
         </td>
               </tr>
               <tr>
       <td align="right" class="txtStyle"><strong>Edition:</strong></td>
  <td><html:text readonly="true" property="edition" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
  </td>
               </tr>
               <tr>
                                      <tr>
  <td align="right" class="txtStyle"><strong>No of Volumes:</strong></td>
  <td><html:text property="volume_no" readonly="true" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
               </tr>
           </table>
       </td>
       </tr>

       <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2">Initiate Acquisition</td>
       </tr>
       <tr><td colspan="2"><br>  <table width="700" border="0" >
                    <tr>

   <td align="right" class="txtStyle"><strong>Volume No:</strong></td>
  <td><html:text property="volume" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
  <td  align="right" class="txtStyle">Currency Format</td><td>

      <html:select property="exchange" name="AcqBiblioActionForm" styleClass="textBoxWidth" value="Select">
          <html:option  value="Select"><%=base %></html:option>

      <%--<html:options collection="exchangerate" name="AcqCurrency" labelProperty="sourceCurrency" property="sourceCurrency"/>--%>
      <%
      List<String> acc=(List<String>)session.getAttribute("exchangerate");
      for(int i=0;i<acc.size();i++)
{
%>
<html:option  value="<%=acc.get(i) %>"><%=acc.get(i) %></html:option>
  <%}%>

      </html:select>

  </td>
                    </tr>
                    <tr>
                       <td align="right" class="txtStyle"><strong>No of Copies:</strong><a class="star">*</a>
                       </td>
                       <td><html:text property="no_of_copies" value="" name="AcqBiblioActionForm" styleId="noc" onkeypress="return isNumberKey(event)" styleClass="textBoxWidth" />
                             <br>
                           <span class="err"><html:messages id="err_name" property="no_of_copies">
                                  <bean:write name="err_name" />
                            </html:messages></span>
  </td>
                       <td align="right" class="txtStyle"><strong>Unit Price:<a class="star">*</a></strong></td>
                       <td><html:text property="unit_price" styleId="price" onkeypress="return isNumberKey(event)" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr><tr>
                       <td align="right" class="txtStyle"><strong>Budget Head Name:<a class="star">*</a></strong></td>
                       <td><html:select property="primary_budget" name="AcqBiblioActionForm" styleId="primary_budget" styleClass="textBoxWidth" >
         <html:option value="Select">Select</html:option>
      <html:options collection="budgetheads" name="MixBudgetAllocation" labelProperty="acqBudget.budgetheadName" property="acqBudget.id.budgetheadId"/>
      </html:select>
      </td>


  <td align="right" class="txtStyle"><strong>Requested By:</strong></td>
  <td><html:text property="requested_by" styleId="requestby" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
                   <tr>
  <td align="right" class="txtStyle"><strong>Requested Date:</strong></td>
  <td><html:text readonly="true" property="requested_date" value="<%=cdate%>" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

  <td align="right">
              <strong>Acq Mode:<a class="star">*</a></strong>
  </td>
  <td>
      <html:select property="acq_mode" name="AcqBiblioActionForm" styleClass="textBoxWidth" styleId="mode" onchange="return vendorFalse()" >
           <html:option value="Select">Select</html:option>
          <html:option value="Firm Order">Firm Order</html:option>
          <html:option value="On Approval">On Approval</html:option>
  </html:select>
    </td>
  </tr>
                   <tr><td align="right" class="txtStyle"><strong>Vendor:</strong></td>
                       <td><html:select property="vendor" name="AcqBiblioActionForm" styleClass="textBoxWidth" styleId="vendor" >
                                 <html:option value="Select">Select</html:option>
                               <html:options collection="vendors" name="AcqVendor" labelProperty="vendor" property="id.vendorId"/>
      </html:select>
  </td>
  <td></td><td></td></tr>
                   <tr><td></td><td></td></tr>
<tr><td height="10px"></td>
</tr>
<tr><td  height="5px" class="mandatory" align="right" colspan="4"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4">
    <input name="button" type="submit" value="Submit" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()" value="Cancel" class="txt1"/></td>
</tr>
<tr><td height="5px"></td>
</tr>
       </table></td></tr>
     </html:form>
   </table>
    </body>
</html>

