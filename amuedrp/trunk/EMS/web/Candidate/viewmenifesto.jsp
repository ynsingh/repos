<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail activity is performed.
     
--%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,com.myapp.struts.Candidate.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%
String institute_id=(String)session.getAttribute("institute_id");
String staff_id=(String)session.getAttribute("staff_id");
String election_id=(String)request.getParameter("id");
String position_id=(String)request.getParameter("pos_id");
if(staff_id==null)
    staff_id=(String)request.getParameter("candi");
Candidate1 voter  =(Candidate1)CandidateRegistrationDAO.searchcandidateMenifesto(institute_id, staff_id, election_id, position_id);
//System.out.println(voter.getMenifesto()+"  ......................");
byte[] bytes=null;
if(voter!=null){
if(voter.getMenifesto()!=null)
{
bytes = voter.getMenifesto();
response.setContentType("application/pdf");

ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(bytes);
servletOutputStream.flush();
}
else
{
  /*  File file = new File(application.getRealPath("images")+"/no-image.jpg");

      
    try{
         FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         bytes = new byte[bis.available()];
         bis.read(bytes);
          
    
    }catch(IOException e){
            System.out.println("image view Error:"+e);
      }*/

  %>  <script>
    alert("Menifesto not uploaded");
    location.href="<%=request.getContextPath()%>/electionview.do?id="+<%=election_id%>;
    </script>
<%}

}
else{
%>
<script>
    alert("Menifesto not uploaded");
   location.href="<%=request.getContextPath()%>/electionview.do?id="+<%=election_id%>;
    </script>
<%
}


%>

