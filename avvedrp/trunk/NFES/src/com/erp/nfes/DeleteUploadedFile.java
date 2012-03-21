package com.erp.nfes;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

import java.io.*;
import java.util.Properties;

public class DeleteUploadedFile extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{   
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html; charset=UTF-8");
	    out.println("<HTML>\n<HEAD>\n<TITLE>\n</TITLE>");
		out.println("<script language='javascript'>");
	    out.println("function deleterow(rowId,ctrlName,total,fileName)"+
	    			"{var row=window.parent.document.getElementById(ctrlName+'_'+rowId);row.parentNode.removeChild(row);"+
	    			"var tableLength=window.parent.document.getElementById(ctrlName+'_filelist').getElementsByTagName('TR').length;"+
	    			"for(var i=1;i<tableLength;i++){window.parent.document.getElementById(ctrlName+'_filelist').rows[i].cells[0].innerHTML=i;}"+
	    			"if(tableLength == 1){ window.parent.document.getElementById(ctrlName+'_filelist').style.display='none';}"+
	    			"var files=window.parent.document.getElementById(ctrlName+'files').value;"+
	    			"if(name == 'upload_photo'){window.parent.document.getElementById(ctrlName+'files').value=files.replace(fileName,'');}"+
	    			"else {window.parent.document.getElementById(ctrlName+'files').value=files.replace(fileName+'|',''); }}");
		out.println("</script>");
	    String filename = request.getParameter("filename");
		String userId = request.getParameter("userId");
		String ctrlName = request.getParameter("ctrlName");
		String number = request.getParameter("number");
		String formName = request.getParameter("formname");
		String totalNumber = request.getParameter("totalNumber");
		String rowId = request.getParameter("rowId");		
		try
		{
			String propFileName = "fileuploadpath.properties";
			Properties properties = GetPropertiesFile.GetPropertiesFileFromCONF(propFileName);
			String path = properties.getProperty("DESTINATION_DIR_PATH")+"/"+userId;
			if(ctrlName.equals("upload_photo")){
				path = path+"/photo";
			}
			doDeleteUploadedFile(request,response,path+"/"+filename,userId,number,formName,filename,ctrlName);
		    out.println("</HEAD>\n<BODY onLoad=\"deleterow("+rowId+",'"+ctrlName+"',"+totalNumber+",'"+filename+"');\">\n<form></form>\n"+
		    		"</BODY>\n<HTML>");
		    out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	private void doDeleteUploadedFile(HttpServletRequest req,HttpServletResponse resp,String path,String userId,String number,String formName,String filename,String ctrlname)
	{
		File f = new File(path);
		boolean success = f.delete();
		if(success)
		{
			Connection conn;
			ConnectDB conobj = new ConnectDB();
			conn = conobj.getMysqlConnection();
			try
			{
			Statement st = conn.createStatement();
			st.executeUpdate("update "+formName+"_values set "+ctrlname+"=replace(replace("+ctrlname+",'"+ filename +"|',''),'"+filename+"','') where idf="+userId+" and number="+number);			
			conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
