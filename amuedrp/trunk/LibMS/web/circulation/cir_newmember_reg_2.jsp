<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register newMember,Ajax for Dept,Fac,course is used & image upload can be done.
     This jsp page is Second page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
 <%@page contentType="text/html" import="java.util.*,com.myapp.struts.hbm.*,com.myapp.struts.systemsetupDAO.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

 <%
       String fname=(String)request.getAttribute("fname");
       String status=(String)request.getAttribute("status");
       CirMemberAccount cmaccount=(CirMemberAccount)request.getAttribute("cmaccount");
        EmployeeType empobj=null;
         SubEmployeeType subempobj=null;
         Faculty faculty=null;
         Department dept=null;
         Courses course=null;
       String library_id=(String)session.getAttribute("library_id");
       if(cmaccount!=null){
         empobj=MemberCategoryDAO.searchMemberType(library_id,cmaccount.getMemType());
       subempobj=MemberCategoryDAO.searchSubMemberType(library_id, cmaccount.getMemType(),cmaccount.getSubMemberType());
       faculty=FacultyDAO.getFacultyName(library_id, cmaccount.getFacultyId());
       dept=DeptDAO.getDeptByFaculty(library_id, cmaccount.getFacultyId(), cmaccount.getDeptId());
       course=CourseDAO.searchCourseName(library_id, cmaccount.getFacultyId(), cmaccount.getDeptId(), cmaccount.getCourseId());
}
%>

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


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script>


$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#TXTREG_DATE").datepicker(jQueryDatePicker1Opts);
   $("#TXTEXP_DATE").datepicker(jQueryDatePicker1Opts);



});

   

function dcheck() {



    if (isValidDate(TXTREG_DATE.value)==false)
    {

        alert(str1);
		TXTREG_DATE.value="";

                TXTREG_DATE.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck_releaving() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(TXTEXP_DATE.value)==false)
    {
        alert(str1);
		TXTEXP_DATE.value="";

                TXTEXP_DATE.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
    }
}

function isValidDate(dateStr) {
// Checks for the following valid date formats:
// YYYY-mm-dd
// Also separates date into month, day, and year variables

var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

// To require a 4 digit year entry, use this line instead:
// var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
str1="Date is not in a valid format.";
return false;
}
month = matchArray[3]; // parse date into variables
day = matchArray[4];
year = matchArray[1];
if (month < 1 || month > 12) { // check month range
str1="Month must be between 1 and 12.";
return false;
}
if (day < 1 || day > 31) {
str1="Day must be between 1 and 31.";

return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
str1="Month "+month+" doesn't have 31 days!";

return false
}
if (month == 2) { // check for february 29th
var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
if (day>29 || (day==29 && !isleap)) {
str1="February " + year + " doesn't have " + day + " days!";

return false;
   }
}
return true;  // date is valid
}
</script>

</head>
<div
   style="  top:130px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
</head>
<body>

    <html:form action="/DelinquentMember" >

        <table  dir="<%=rtl%>" align="center" width="500px" class="table">



  <tr><td   dir="<%=rtl%>"   class="headerStyle"  align="center">


		 <%=resource.getString("circulation.cir_newmember_reg_1.memberaccdetail")%>



        </td></tr>

  <tr><td  dir="<%=rtl%>" valign="center" align="<%=align%>" >

          <br>
          <table  dir="<%=rtl%>"  align="<%=align%>" class="table_text" >




             <tr>
                 <td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.memberid")%></td><td class="table_textbox">

                     <html:text readonly="true"  property="memid" style="width:160px" value="<%=cmaccount.getId().getMemid()%>" styleId="emptype_id"/>



                  </td>


            </tr>
             <tr>
                 <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_createaccount1.memname")%></td><td class="table_textbox">

                     <html:text readonly="true"  property="member_name" style="width:160px" value="<%=fname%>" styleId="emptype_id"/>



                  </td>


            </tr>


            <tr>
                 <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.typeofmem")%></td><td class="table_textbox">

                     <html:text  readonly="true" property="MEMCAT" style="width:160px" value="<%=empobj.getEmptypeFullName() %>" styleId="emptype_id"/>



                  </td>


            </tr>
             <tr>
              <td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.desg")%>*
                  </td><td  dir="<%=rtl%>" class="table_textbox">
                      <html:text  readonly="true" property="MEMSUBCAT" styleId="subemptype_id" style="width:160px" value="<%=subempobj.getSubEmptypeFullName()%>"  tabindex="3"/>

                      </td>

             </tr>
             <tr><td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td class="table_textbox"><html:text  property="TXTDESG1" readonly="true" style="width:160px" value="<%=cmaccount.getDesg()%>"/></td></tr>
             <tr><td  dir="<%=rtl%>"  ><%=resource.getString("circulation.cir_newmember.officename")%></td><td class="table_textbox"><html:text  property="TXTOFFICE" readonly="true" style="width:160px" value="<%=cmaccount.getOffice()%>"/></td></tr>
             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.facof")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox">
                     <%if(faculty!=null){%>
                     <html:text  property="TXTFACULTY" styleId="TXTFACULTY" readonly="true" style="width:160px" value="<%=faculty.getFacultyName() %>"  tabindex="3"/>
                     <%}else{%>
                     <html:text  property="TXTFACULTY" styleId="TXTFACULTY" readonly="true" style="width:160px" value=""  tabindex="3"/>
                  <%}%>
                  </td></tr>
             <tr> <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.dept")%> </td><td  dir="<%=rtl%>" class="table_textbox">
<% if(dept!=null){%>
<html:text  property="TXTDEPT" styleId="TXTDEPT" readonly="true" style="width:160px" value="<%=dept.getDeptName() %>" tabindex="3"/>
               <%}else{%>
               <html:text  property="TXTDEPT" styleId="TXTDEPT" readonly="true" style="width:160px" value="" tabindex="3"/>
               <%}%>



                 </td></tr>
             <tr> <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.course")%>
                  </td><td  dir="<%=rtl%>" class="table_textbox">
                       <% if(course!=null){%>
                       <html:text  property="TXTCOURSE" readonly="true" styleId="TXTCOURSE" style="width:160px" value="<%=course.getCourseName() %>"   tabindex="3"/>
                      <%}else{%>
                      <html:text  property="TXTCOURSE" readonly="true" styleId="TXTCOURSE" style="width:160px" value=""   tabindex="3"/>
                      <%}%>
</td></tr>
             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.sem")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox">
                       <%if(status.equalsIgnoreCase("Renewal")){%>
                       <html:text  property="TXTSEM" readonly="false"  styleId="TXTSEM" value="<%=cmaccount.getSemester()%>" styleClass="textBoxWidth" style="width:160px"  />
                       <%}else{%>
                     
                     <html:text  property="TXTSEM" readonly="true" styleId="TXTSEM"  value="<%=cmaccount.getSemester()%>" styleClass="textBoxWidth" style="width:160px"  />
                     <%}%>

                  </td></tr>


             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.reg")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox">
                     <%if(status.equalsIgnoreCase("Renewal")){%>
                                       <html:text   property="TXTREG_DATE" styleId="TXTREG_DATE"  style="width:160px"  value="<%=cmaccount.getReqDate()%>" styleClass="textBoxWidth"  />
                       <%}else{%>


                     <html:text readonly="true"  property="TXTREG_DATE" styleId="TXTREG_DATE"  style="width:160px"  value="<%=cmaccount.getReqDate()%>" styleClass="textBoxWidth"  />
<%}%>

                  </td></tr>
             <tr>
                 <td  dir="<%=rtl%>" valign="top"><%=resource.getString("circulation.cir_newmember.exp")%>
                  </td>
                  <td  dir="<%=rtl%>" class="table_textbox" valign="top">

                      <%if(status.equalsIgnoreCase("Renewal")){%>
                        <html:text   property="TXTEXP_DATE" styleId="TXTEXP_DATE" value="<%=cmaccount.getExpiryDate()%>" style="width:160px"/>
                      <%}else{%>
                      <html:text  readonly="true" property="TXTEXP_DATE" styleId="TXTEXP_DATE" value="<%=cmaccount.getExpiryDate()%>" style="width:160px"/>
                    <%}%>
                       </td></tr>

            <tr><td  dir="<%=rtl%>" > <%=resource.getString("opac.myaccount.reservationrequest.cardid")%>
                </td><td  dir="<%=rtl%>" class="table_textbox">
                      <%if(status.equalsIgnoreCase("Renewal")){%>
                    
                      <html:text  property="card_id" styleId="card_id"  value="<%=cmaccount.getCardId()%>" styleClass="textBoxWidth" style="width:160px"  />
                    <%}else{%>
                    <html:text readonly="true"  property="card_id"   value="<%=cmaccount.getCardId()%>" styleClass="textBoxWidth" style="width:160px"  styleId="card_id"  />
                    <%}%>

                  </td></tr>

            <tr><td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.pass")%>
                </td><td  dir="<%=rtl%>" class="table_textbox"><input  readonly="true" type="password"   name="password" style="width:160px" id="password"  value="<%=cmaccount.getPassword()%>"/>

                  </td> </tr>
            <% if(status==null){%>
             <tr><td align="center" class="txt2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return back();">

     </td></tr>
             <%}%>
            <% if(status!=null){%>
            <tr><td  dir="<%=rtl%>" >Change Working Status
                  </td><td dir="<%=rtl%>" class="table_textbox">
                      <select   name="changestatus"   style="width:160px" >
                         

                          <option value="<%=status%>"><%=status%></option>
                      

                      </select>

                      </td></tr>
           
            <%if(status.equalsIgnoreCase("Blocked") && status !=null && status.equalsIgnoreCase("Cancel")){%>
         <tr><td align="center" class="txt2">Enter Reason </td> <td align="center" class="txt2"><input type="textarea" name="reason"  value="" >
                  &nbsp;&nbsp;&nbsp;<input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return back();">
                      </td>
          </tr>
          <%}%>

          <tr><td align="center" class="txt2" colspan="2" height="40px" valign="middle">
          <%if(status.equalsIgnoreCase("Renewal")){%>
                  
                  <input type="submit" name="button"  value="Submit" onClick="return validation();" >
         <%}else{%>
                <input type="submit" name="button"  value="Submit">
         &nbsp;&nbsp;&nbsp;<input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return back();">
         <%}%>
     </td> 
          </tr>
         <%}%>
     </table>
      </td></tr>

     </table>


    </html:form>

</body>


</div>
  <script language="javascript" type="text/javascript">


   function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/member_account_viewall1.jsp";
    }

    function validation()
    {


    var TXTSEM=document.getElementById('TXTSEM');
    var card_id=document.getElementById('card_id');
    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";

  <% if(course!=null){%>
 


    if(TXTSEM.value=="")
       { str+="\n Enter Semester ";
            alert(str);
            document.getElementById('TXTSEM').focus();
            return false;

       }
 <%}%>

    if(card_id.value=="")
       { str+="\n Enter Card ID";
            alert(str);
            document.getElementById('card_id').focus();
            return false;

       }

    if(TXTREG_DATE.value=="")
       { str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofreg")%>";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofexp")%>";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }

if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\n <%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }

if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('TXTSEM').focus();
        return false;
    }




    }


function IsDateGreater(DateValue1, DateValue2)
{

var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));
if(DaysDiff > 0)
{

  return true;
}
else
{

return false;
}
}

 </script>





</html>
