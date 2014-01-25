<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="java.util.*,java.io.*,com.myapp.struts.hbm.Election,com.myapp.struts.hbm.ElectionDAO"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<html>
    <head>
       <%try{%>
         <jsp:include page="/election_manager/login.jsp"/>
         
        <html:base />
    </head>
    <body>
   <%
   String institute_id=session.getAttribute("institute_id").toString();
   String manager_id=(String)session.getAttribute("user_id");
    List<Election>     election= ElectionDAO.GetElectionDetails1(institute_id,manager_id,0);
   //List<Election> election=(List<Election>)session.getAttribute("SetVoterList");

%>
    <table align="center" class="datagrid">
            <tr><td align="center" colspan="3" height="25px" class="headerStyle">Select Election for Candidate Report</td></tr>
            <tr><td height="5px"></td></tr>

           <html:form action="/CandiReport.do" method="post" >
               <%-- <tr><td colspan="3" align="center" class="headerStyle">Upload Excel File</td></tr>--%>
            <tr>
               <td align="left" style="padding:10px">
                  <%-- <p class="mess"><b>Note</b>:You Need to Import Candidate  Details data in XLS Format. </p>--%>
                   <br/>   Select Election : <br>
                    <%
                    System.out.println("i am fine  ");
                    if(session.getAttribute("SetVoterList")!=null){
    
                        
    %>
         <select name="electionId" id="election_id" >
             <option selected value="Select">Select<%--<%=resource.getString("managername")%>--%></option>
                <%

for(int i=0;i<election.size();i++){
%>
     <option value="<%=election.get(i).getId().getElectionId()%>"><%=election.get(i).getElectionName() %></option>

     <%}%><input type="hidden" name="election" id="election"/>
    <% for(int i=0;i<election.size();i++){
%>


     <%}%>
   </select>

<%}else{
       
       
       %>
       No Election
       <%}%>


                   
                </td>            
            </tr>
            <tr>
                <td>
                    <input type="submit" name="genreport" value="Generate Report"/>
                </td>
            </tr>
            </html:form>

                 <tr><td colspan="3">
                 
                <p class="mess">

                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("msg"));
                                    }
                        %>
                </p>


            </td>
        </tr>
        <tr><td>
                
            </td></tr>

        </table>
</body>
<% }catch(Exception er){
             System.out.println("Error "+er);
             } %>
</html>
