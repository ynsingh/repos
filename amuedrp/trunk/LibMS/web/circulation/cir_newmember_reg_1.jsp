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
       CirMemberAccount cmaccount=(CirMemberAccount)request.getAttribute("cmaccount");
    EmployeeType empobj=null;
         SubEmployeeType subempobj=null;
         Faculty faculty=null;
         Department dept=null;
         Courses course=null;
       String library_id=(String)session.getAttribute("library_id");
       if(cmaccount!=null){
MemberCategoryDAO memdao=new MemberCategoryDAO();
FacultyDAO facdao=new FacultyDAO();
DeptDAO deptdao=new DeptDAO();
CourseDAO coursedao=new CourseDAO();
                   empobj=memdao.searchMemberType(library_id,cmaccount.getMemType());
       subempobj=memdao.searchSubMemberType(library_id, cmaccount.getMemType(),cmaccount.getSubMemberType());
       faculty=facdao.getFacultyName(library_id, cmaccount.getFacultyId());
       dept=deptdao.getDeptByFaculty(library_id, cmaccount.getFacultyId(), cmaccount.getDeptId());
       course=coursedao.searchCourseName(library_id, cmaccount.getFacultyId(), cmaccount.getDeptId(), cmaccount.getCourseId());
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



</head>
<div
   style="  top:130px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">




<style type="text/css">
body
{
  
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>


</head>
<body>
    

    <html:form action="/cir_account_updateview">
   
        <table  dir="<%=rtl%>" align="center" width="500px" class="table">



  <tr><td   dir="<%=rtl%>"   class="headerStyle"  align="center">


		 <%=resource.getString("circulation.cir_newmember_reg_1.memberaccdetail")%>



        </td></tr>

  <tr><td  dir="<%=rtl%>" valign="center" align="<%=align%>" >

          <br>
          <table  dir="<%=rtl%>"  align="<%=align%>" class="table_text" >



               
             <tr>
                 <td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.memberid")%></td><td class="table_textbox">

                     <html:text  property="memid" style="width:160px" value="<%=cmaccount.getId().getMemid()%>" styleId="emptype_id"/>



                  </td>


            </tr>
             <tr>
                 <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_createaccount1.memname")%></td><td class="table_textbox">

                     <html:text  property="member_name" style="width:160px" value="<%=fname%>" styleId="emptype_id"/>



                  </td>


            </tr>
            
            
            <tr> 
                 <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.typeofmem")%></td><td class="table_textbox">
                 
                     <html:text  property="MEMCAT" style="width:160px" value="<%=empobj.getEmptypeFullName() %>" styleId="emptype_id"/>
                       
                 

                  </td>


            </tr>
             <tr>
              <td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.desg")%>*
                  </td><td  dir="<%=rtl%>" class="table_textbox">
                      <html:text  property="MEMSUBCAT" styleId="subemptype_id" style="width:160px" value="<%=subempobj.getSubEmptypeFullName()%>"  tabindex="3"/>
                   
                      </td>

             </tr>
             <tr><td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td class="table_textbox"><html:text  property="TXTDESG1" style="width:160px" value="<%=cmaccount.getDesg()%>"/></td></tr>
             <tr><td  dir="<%=rtl%>"  ><%=resource.getString("circulation.cir_newmember.officename")%></td><td class="table_textbox"><html:text  property="TXTOFFICE" style="width:160px" value="<%=cmaccount.getOffice()%>"/></td></tr>
             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.facof")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox">
                     <%if(faculty!=null){%>
                     <html:text  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=faculty.getFacultyName() %>"  tabindex="3"/>
                     <%}else{%>
                     <html:text  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value=""  tabindex="3"/>
                  <%}%>
                  </td></tr>
             <tr> <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.dept")%> </td><td  dir="<%=rtl%>" class="table_textbox">
<% if(dept!=null){%>
                     <html:text  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" value="<%=dept.getDeptName() %>" tabindex="3"/>
               <%}else{%>
                <html:text  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" value="" tabindex="3"/>
               <%}%>



                 </td></tr>
             <tr> <td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.course")%>
                  </td><td  dir="<%=rtl%>" class="table_textbox">
                       <% if(course!=null){%>
                      <html:text  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=course.getCourseName() %>"   tabindex="3"/>
                      <%}else{%>
                      <html:text  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value=""   tabindex="3"/>
                      <%}%>
                  

                  





</td></tr>
             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.sem")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSEM"   value="<%=cmaccount.getSemester()%>" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

          
             <tr><td  dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.reg")%>
                 </td><td  dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTREG_DATE" styleId="TXTREG_DATE"  style="width:160px"  value="<%=cmaccount.getReqDate()%>" styleClass="textBoxWidth"  />
                     

                  </td></tr>
             <tr>
                 <td  dir="<%=rtl%>" valign="top"><%=resource.getString("circulation.cir_newmember.exp")%>
                  </td>
                  <td  dir="<%=rtl%>" class="table_textbox" valign="top"><html:text  property="TXTEXP_DATE" styleId="TXTEXP_DATE" value="<%=cmaccount.getExpiryDate()%>" style="width:160px"/>
                   
                       </td></tr>
             
            <tr><td  dir="<%=rtl%>" > <%=resource.getString("opac.myaccount.reservationrequest.cardid")%>
                </td><td  dir="<%=rtl%>" class="table_textbox"><html:text  property="card_id"   value="<%=cmaccount.getCardId()%>" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

            <tr><td  dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.pass")%>
                  </td><td  dir="<%=rtl%>" class="table_textbox"><input type="password"   name="password" style="width:160px" id="password"  value="<%=cmaccount.getPassword()%>"/>

                  </td> </tr>

            
         
     </table>
      </td></tr>
  <tr><td align="center" class="txt2">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return back();"/>
                      </td>

          </tr>
     </table>

      
    </html:form>

</body>


</div>
  <script language="javascript" type="text/javascript">


   function back()
    {
        
        location.href="<%=request.getContextPath()%>/circulation/member_account_viewall.jsp";
    }

 </script>



   

</html>
