<%-- 
    Document   : cust_marc_entry6
    Created on : Jul 2, 2011, 12:45:26 PM
    Author     : zeeshan
--%>

<%@page import="com.myapp.struts.hbm.Editmarc"%>
<%@page import="java.util.List"%>
<%@page import="com.myapp.struts.marc.NewMARCDAO"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <title> Bibliographic Cataloging According to MARC21</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.4.2.min.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/


</script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 6) //initialize Tab Menu #3 with 1st tab selected
</script>


</head>

    <body>

<script type="text/javascript">


function func1(t){
     alert("func1"+t);
     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){

    if(t.value!=6){

        document.getElementById("cat1").submit();
        alert("submitted! ");
}


}
</script>

<script type="text/javascript" >
    animatedcollapse.addDiv('020c', 'fade=1,height=200px')
    animatedcollapse.addDiv('020z', 'fade=1,height=200px')
    animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}
animatedcollapse.init();
</script>

                                          <h2 align="center">Bibliographic Cataloging</h2>


<div id="ddtabs3" class="solidblockmenu">
<ul>
   <li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cust_marc_entry9.jsp" onclick="func1(9)" rel="sb9">9</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cat_editmarc.jsp"  rel="home">HOME</a></li>
</ul>
</div>

<DIV class="tabcontainer ieclass">
<FONT color="#8B008B">
<div id="sb0" class="tabcontent">
Number and Code Fields.
</div>


<div id="sb1" class="tabcontent">
Main Entry Fields.
</div>

<div id="sb2" class="tabcontent">
Title, Edition, Imprints etc Fields .
</div>

<div id="sb3" class="tabcontent">
Physical Description, etc Fields.
</div>


<div id="sb4" class="tabcontent">
Series Statements Field.
</div>


<div id="sb5" class="tabcontent">
Note Fields.
</div>


<div id="sb6" class="tabcontent">
Subject Access Fields.
</div>


<div id="sb7" class="tabcontent">
Added Entry and Linking Entry Fields.
</div>


<div id="sb8" class="tabcontent">
Series added Entry, Holding, Location, Alternate Graphics Fields.
</div>


<div id="sb9" class="tabcontent">
Local Fields 900 and onwards.
</div>

    <div id="home" class="tabcontent">
Go BACK to Manage MARC Bibliography.
</div>
</FONT>
</DIV>



                                      <!-- Marc entries Starts from here . -->
<div style="position:absolute;left:5%;top:23%;width:90%;border: gray medium;background: #f5fffa;">
    <html:form styleId="cat1" action="/custommarcentry" method="post">
<table width="100%"  cellspacing="5"  >
    <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>

<!----------------->


<div id="tags1" style=" background: powderblue; ">
     <%
     if(request.getAttribute("tagno")!=null){
     List marc=(List)request.getAttribute("tagno");
    int i=0;
    while(marc.size()>i)
    {
          List obj = (List)marc.get(i);
          String tagnr = (String)obj.get(0).toString();

          String tagnm=(String)obj.get(1);

          System.out.println("first column = "+tagnr);
          System.out.println("first column = "+tagnm);
     %>
<input type="hidden" name="marctags" value="<%=tagnr %>" />
   <tr bgcolor="powderblue" ><td>&nbsp;(<%=tagnr  %>) &nbsp;<%=tagnm %> &nbsp;ind1<input type="text" value="" name="ind1" maxlength="1" size="1" /> ind2<input type="text" value="" name="ind2"  maxlength="1" size="1" /></td>

       <td>
        <div id="<%=tagnr%> " style=" background: #FDF5E6; ">


              <%  NewMARCDAO m1=new NewMARCDAO();
                List marclist= (List)m1.getMarc((tagnr));
                    System.out.println(tagnr);
       int s=0;
       System.out.println("marc list size= "+marclist.size());
        if(marclist.isEmpty()==false){
             %>
            <%for(s=0;s<marclist.size();s++){
               Editmarc em=new Editmarc();
               em=(Editmarc)marclist.get(s);
               System.out.println(em.getId().getSubsymbol()+"    "+em.getSubdescription());
                %>

                <p>$<%=em.getId().getSubsymbol() %> <input type="hidden" name="allmarc" value="<%=em.getId().getSubsymbol() %>" />  &nbsp;<%=em.getSubdescription() %> &nbsp;<input type="text" value="" name="sub_names" /></p>

               <%
                    }
}%>
</div> </td>
    </tr>
    <tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
    <script type="text/javascript" >

animatedcollapse.addDiv('<%=tagnr%>', 'fade=1,height=150px')


</script>

    <% i++;  }%>

<%}
    %>


</div>

<!--------------------->

</table>
    </html:form>
</div>

    </body>

</html>

