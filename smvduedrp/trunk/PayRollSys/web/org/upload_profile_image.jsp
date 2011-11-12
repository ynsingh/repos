   <%@page import="org.smvdu.payroll.beans.Org"%>
<%@ page import="java.util.List" %>
   <%@ page import="java.util.Iterator" %>
   <%@ page import="java.io.File" %>
   <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
   <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
   <%@ page import="org.apache.commons.fileupload.*"%>
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  
        <center><h1>Your  Profile has been Uploaded</h1></center>
   <%!
     String emp_name="";	 
	 String emp_c_number="";
	 String emp_emailid="";
     String address1="";
	 String address2="";
         String weblink="";
	 int count1=0,count2=0,count3=0,count4=0,count5=0;
 %>
 <%

  File f = new File(config.getServletContext().getRealPath("/"));
  out.println("Path : "+f.getAbsolutePath());
 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 if (!isMultipart) {
 } else {
   FileItemFactory factory = new DiskFileItemFactory();
   ServletFileUpload upload = new ServletFileUpload(factory);
   List items = null;
   try {
   items = upload.parseRequest(request);
   } catch (FileUploadException e) {
   e.printStackTrace();
   }
   Iterator itr = items.iterator();
   while (itr.hasNext()) 
	   {
   FileItem item = (FileItem) itr.next();
   if (item.isFormField())
	   {
	      String name = item.getFieldName();
		  String value = item.getString();
		  if(name.equals("emp_name"))
	           {
			   emp_name=value;
            		 count1=1;
			   }
			  if(name.equals("address1"))
	                  {  
				         address1=value;            		 
                         count2=2;
					  }
			  if(name.equals("address2"))
	                  {  
				         address2=value;            		 
                         count5=5;
					  }
                   if(name.equals("web_id"))
	                  {
				         weblink=value;
                                            count5=5;
					  }
			  if(name.equals("contact_number"))
	                  {
			         emp_c_number=value;
			         count3=3;
					  }
            		 
			  if(name.equals("email_id"))
	             {
			      count4=4;
				  emp_emailid=value;
				 }	    
		 
		 
		    
   } else
	   {
    try {
	
   String itemName = item.getName();
   File savedFile = new File(config.getServletContext().getRealPath("/")+"org\\uploadimg\\logo.gif");
   if(savedFile.exists())
       {
       savedFile.delete();
       }
   item.write(savedFile);
   out.println("Image File copied ... ");
     %>
         <table ><tr><td width="210"></td><td> <img  border="2" src=<%="uploadimg\\"+itemName %> width="137"  height="140"></td></tr></table><table border="2" width="350">
   
   <% 
      		 
	  
		     out.println("</td><tr><td align='left'><b>Addresss:</td><td><b>"+address1);
	   
		     out.println("</td><tr><td align='left'><b></td><td><b>"+address2);
	 
		     out.println("</td><tr><td align='left'><b>Contact No</td><td><b>"+emp_c_number);
                     out.println("</td><tr><td align='left'><b>Website</td><td><b>"+weblink);
		     out.println("</td><tr><td align='left'><b>Email ID</td><td><b>"+emp_emailid);

                     Org org = new Org();
                     org.email = emp_emailid;
                     org.tagLine = emp_name;
                     org.web = weblink;
                     org.address1 = address1;
                     org.address2 = address2;
                     org.phone = emp_c_number;
                     org.update();


   } catch (Exception e) {
   e.printStackTrace();
   out.println(e.getMessage());
   }
   }
   }
   }
  response.sendRedirect("../MainPage.jsf");
   %>


	