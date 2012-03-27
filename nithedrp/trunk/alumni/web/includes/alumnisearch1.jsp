<%@ include file="connectionstrings.jsp" %>
<%@ page session="true" buffer="16kb" import="java.sql.*,java.util.*,java.text.*,javax.servlet.http.HttpSession,java.io.Serializable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<body>
<title></title>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
<script language="javascript" 
</script>
</head>

<body bgColor="white" link="white" vLink="white" >
<script type="text/javascript" src="ew.js"></script>
<script type="text/javascript">
<!--
EW_dateSep = "/"; // set date separator	
//-->
</script>
<script type="text/javascript">
<!--
var EW_DHTMLEditors = [];
//-->
</script>

<form method="post">
<div align="center">
  <center>
  <%
		  class AlumniDetailsDTO implements Serializable {

			private String Firstame;
			private String LastName;
			private String Department;
			private String Batch;
			private String Emailid;
			private String City;
			

			public AlumniDetailsDTO(String Firstame, String LastName, String Department,String Batch,String Emailid,String City) {
				this.Firstame = Firstame;
				this.LastName = LastName;
				this.Department = Department;
				this.Batch = Batch;
				this.Emailid = Emailid;
				this.City = City;
				
			}
			public String getFirstame() {
				return Firstame;
			}

			public void setFirstame(String Firstame) {
				this.Firstame = Firstame;
			}

			public String getLastName() {
				return LastName;
			}

			public void setLastName(String LastName) {
				this.LastName = LastName;
			}

			public String getDepartment() {
				return Department;
			}

			public void setDepartment(String Department) {
				this.Department = Department;
			}

			public String getBatch() {
				return Batch;
			}

			public void setBatch(String Batch) {
				this.Batch = Batch;
			}
			
		public String getEmailid() {
				return Emailid;
			}

			public void setEmailid(String Emailid) {
				this.Emailid = Emailid;
			}
			
			public String getCity() {
				return City;
			}

			public void setCity(String City) {
				this.City = City;
			}

			
		}
			List flist = new ArrayList();
			List llist = new ArrayList();
			List dlist = new ArrayList();
			List blist = new ArrayList();
			List elist = new ArrayList();
			List clist = new ArrayList();
		
				String z = request.getParameter("psearchtype");
				String z1 = request.getParameter("psearch");
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("psearchtype", z);
				String escapeString = "\\\\'";
				
				ResultSet rst=null;
				
				if(z1 != null && z1.length() > 0)
				{
				z1 = z1.replaceAll("'",escapeString);
				
					while (z1.indexOf("  ") > 0) 
					{
						z1 = z1.replaceAll("  ", " ");
					}
					String [] arpsearch = z1.trim().split(" ");
					String z2="";	
						for (int i = 0; i < arpsearch.length; i++)
						{	
							
							String temp = arpsearch[i].trim();
						
							if(!temp.equals(" "))
							{
							 z2= temp;
					
								if(z.equals("All words"))
								{							
									rst= stmt.executeQuery("select * from info where Firstname like '%"+z2+"%' or Midname like '%"+z2+"%' or Lastname like '%"+z2+"%' or Alias like '%"+z2+"%' or Nickname like '%"+z2+"%' or Gender like '%"+z2+"%' or Dob like '%"+z2+"%' or Deg1 like '%"+z2+"%' or Dep1 like '%"+z2+"%' or Batch1 like '%"+z2+"%' or Host1 like '%"+z2+"%' or Deg2 like '%"+z2+"%' or Dep2 like '%"+z2+"%' or Batch2 like '%"+z2+"%' or Host2 like '%"+z2+"%' or Deg3 like '%"+z2+"%' or Dep3 like '%"+z2+"%' or Batch3 like '%"+z2+"%' or Host3 like '%"+z2+"%' or Street1 like '%"+z2+"%' or City1 like '%"+z2+"%' or State1 like '%"+z2+"%' or Country1 like '%"+z2+"%' or Pin1 like '%"+z2+"%' or Phone1 like '%"+z2+"%' or Mobile1 like '%"+z2+"%' or Fax1 like '%"+z2+"%' or Email like '%"+z2+"%' or Url like '%"+z2+"%' or Occupation like '%"+z2+"%' or Organisation like '%"+z2+"%' or Designation  like '%"+z2+"%' or Street2 like '%"+z2+"%' or City2 like '%"+z2+"%' or State2 like '%"+z2+"%' or Country2 like '%"+z2+"%' or Pin2 like '%"+z2+"%' or Phone2 like '%"+z2+"%' or Mobile2 like '%"+z2+"%' or Fax2 like '%"+z2+"%' ");
								}
									
								if(z.equals("Firstname"))
								{							
									rst=stmt.executeQuery("select * from info where Firstname like '%"+z2+"%' ");
								}
								if(z.equals("Lastname"))
								{	
									rst=stmt.executeQuery("select * from info where Lastname like '%"+z2+"%' ");
								}
								if(z.equals("Department"))
								{		
									rst=stmt.executeQuery("select * from info where Dep1 like '%"+z2+"%' ");
								}
								if(z.equals("Batch"))
								{
									rst=stmt.executeQuery("select * from info where Batch1 like '%"+z2+"%' ");
								}
							}
							while (rst.next())
							{   
								String Firstname = rst.getString("Firstname");
								String Lastname = rst.getString("Lastname");
								String Dep = rst.getString("Dep1");
								String Batch = rst.getString("Batch1");
								String Emailid = rst.getString("LoginMail");
								String City = rst.getString("City2");
								flist.add(Firstname);
								llist.add(Lastname);
								dlist.add(Dep);
								blist.add(Batch);
								elist.add(Emailid);
								clist.add(City);
							}
							for(int j=0;j<elist.size()-1;j++)
							{
								for(int k=j+1;k<elist.size();k++)
								{
									if(elist.get(j).equals(elist.get(k)))
									{
										elist.remove(k);
										flist.remove(k);
										llist.remove(k);
										dlist.remove(k);
										blist.remove(k);
										clist.remove(k);
									}
								}
							}
							httpSession.setAttribute("alumniDetails1", flist);
							httpSession.setAttribute("alumniDetails2", llist);
							httpSession.setAttribute("alumniDetails3", dlist);
							httpSession.setAttribute("alumniDetails4", blist);
							httpSession.setAttribute("alumniDetails5", elist);
							httpSession.setAttribute("alumniDetails6", clist);	
						}	
					
						response.sendRedirect("../searchresult.jsp"); 
				
				}
				
				if(z1 != null && z1.length() == 0)
				response.sendRedirect("../search.jsp"); 
		
	%>

</center>
</div>
</form>

</div>	
</body>
</html>