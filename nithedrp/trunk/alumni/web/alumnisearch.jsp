<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List,java.io.Serializable"%>

<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
<style type="text/css">
.pg-normal
{
	color: #0000FF;
	font-weight: normal;
	text-decoration: none;
	cursor: pointer;
}

.pg-selected {
	color: #800080;
	font-weight: bold;
	text-decoration: underline;
	cursor: pointer;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<script type="text/javascript">
function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
    
    this.showPage = function(pageNumber) {
     if (! this.inited) {
      alert("not inited");
      return;
     }

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
     if (! this.inited) {
      alert("not inited");
      return;
     }
     var element = document.getElementById(positionId);
     
     var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> &#171 Prev </span> | ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next &#187;</span>';            
        
        element.innerHTML = pagerHtml;
    }
}
</script>


<body>

<%@ include file="includes/header.jsp" %>
<script type="text/javascript" src="ew.js"></script>

<script type="text/javascript">

EW_dateSep = "/"; // set date separator	

</script>
<script type="text/javascript">

var EW_DHTMLEditors = [];

</script>

<script type="text/javascript">

var firstrowoffset = 1; // first data row start at
var tablename = 'ewlistmain'; // table name
var usecss = true; // use css
var rowclass = 'ewTableRow'; // row class
var rowaltclass = 'ewTableAltRow'; // row alternate class
var rowmoverclass = 'ewTableHighlightRow'; // row mouse over class
var rowselectedclass = 'ewTableSelectRow'; // row selected class
var roweditclass = 'ewTableEditRow'; // row edit class
var rowcolor = '#DDA0DD'; // row color
var rowaltcolor = '#FFE4C4'; // row alternate color
var rowmovercolor = '#FFB6C1'; // row mouse over color
var rowselectedcolor = '#FAFAD2'; // row selected color
var roweditcolor = '#6B8E23'; // row edit color

</script>
<%	
	
	String psearchtype= (String) session.getAttribute("psearchtype");
	%>
<div class="wrapper">

	<form  name="theForm" action="includes/alumnisearch1.jsp" method="post">
		</br><div class="catheader">Search by (Please select one*)</div>
		<hr></hr>
		<div class="container" >
			<div class="linepart1"><input type="radio" name="psearchtype" value="All words" checked>&nbsp;<strong>All words</strong></div></br>
			<div class="linepart1"><input type="radio" name="psearchtype" value="Firstname">&nbsp;<strong>First Name</strong></div>
			<div class="linepart1"><input type="radio" name="psearchtype" value="Lastname">&nbsp;<strong>Last Name</strong></div>
			<div class="linepart1"><input type="radio" name="psearchtype" value="Department">&nbsp;<strong>Department</strong></div>
			<div class="linepart1"><input type="radio" name="psearchtype" value="Batch">&nbsp;<strong>Passing out Year</strong></div>
		</div>
		</br><input type="text" name="psearch" size="30" value="">
		<strong><font size="1" face="Arial"><input type="Submit" name="Submit" value="Search&nbsp;(*)" style="background-color:Lightgrey">&nbsp; <input type="Button" name="Reset" value="Reset" style="background-color:Lightgrey" onClick="EW_clearForm(this.form);this.form.psearchtype[0].checked = true;"></font></strong>
	</form>
	           	   <p align="center"><font size="4" color="#FF0000"></font>

                   <%
                   if (session.getAttribute("login")!= "true")
                    {
                                          
                                              
                   %>
                               <font face=arial size=2 color="#3333CC" style="float:left">Login for more details</font><br/>

                     <%
                     }
                 %>



	<%
	
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("member")))
	{
		%>
		<a href="alumniedit.jsp"><font size="4" color="Blue">&#187;Edit Your Own Details||</font></a>
	<%
	}
	%>
		<a href="search.jsp"><font size="4" color="Blue">&#187;Show all</font></a>
        
        
		<hr></hr>
	
		<p>
<form method="post">
<div align="center">
  <center>
  </br>
	
	<%
		List flist = (List) session.getAttribute("alumniDetails1");
		List llist = (List) session.getAttribute("alumniDetails2");
		List dlist = (List) session.getAttribute("alumniDetails3");
		List blist = (List) session.getAttribute("alumniDetails4");
		List elist = (List) session.getAttribute("alumniDetails5");
		List clist = (List) session.getAttribute("alumniDetails6");
	%>
	
	<table id="results" class="ewTable" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" width="607">
		<tr class="ewTableHeader" >
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>First name</strong></td>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Last name</strong></td>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Department</strong></td>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Paasing out Year</strong></td>
		<%
			if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("member")))
			{
		%>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Emailid</strong></td>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Current City</strong></td>
					<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Details</strong></td>
				</tr>
	<%
			}
			for (int i = 0; i < flist.size(); i++)
			{
	%>
	
				<tr class="ewTableRow" onmouseover='ew_mouseover(this);' onmouseout='ew_mouseout(this);' onclick='ew_click(this);'>
					<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(flist.get(i) + "</td>");%>
					<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(llist.get(i) + "</td>");%>
					<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(dlist.get(i) + "</td>");%>
					<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(blist.get(i) + "</td>");%>
			<%
				if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("member")))
				{
			%>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(elist.get(i) + "</td>");%>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(clist.get(i) + "</td>");%>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><a href="alumnidetails.jsp?email=<%out.println(elist.get(i)+ "\"");
				%>
				><font color="Blue">View Details</font></a>
			</tr>
		<%
				}
			}
		%>
</table>

<div id="pageNavPosition"></div>

<script type="text/javascript"><!--
        var pager = new Pager('results', 50);
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    //--></script>
</center>
</div>
</form>

</body>
</html>