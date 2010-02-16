package org.dei.edrp.pms.ganttChart;

import java.io.*;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;  

//import org.xml.sax.SAXException;

import java.sql.*;
//import java.sql.Date;
//import java.util.*;

import org.dei.edrp.pms.dataBaseConnection.*;
public class GanttWithXmlAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GanttChartForm gcform=(GanttChartForm) form;// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		String pselect=request.getParameter("pname");
		if(pselect==null)
			pselect=gcform.getPselect().trim();
		request.setAttribute("pselect",pselect);
	System.out.println("pselect="+pselect);
			String i="";
	    String s1="";
	    String s2="";
	    String s3="";
	    String s4="";
	    String s5="";
	    float s6=0;
	    
	    	    
	    String ti="";
	    String ts1="";
	    String ts2="";
	    String ts3="";
	    String ts4="";
	    String ts5="";
	    String ts6="";
	    String ts7="";
	    String ss="charterror";
	   
	    Connection con=null;
	    PreparedStatement s=null;
	    PreparedStatement st=null;
	    ResultSet rs=null;
	    ResultSet rs1=null;
	    BufferedOutputStream bw=null;
	    //OutputStream f0;
	    int flag=0;
		String uid=(String)session.getAttribute("uid");
	    try
	    {
	    	
	    	con=MyDataSource.getConnection();
	        		 //out.println("uid="+uid);
	    		 	s=con.prepareStatement("select Authority from login where User_ID=?");
	    			s.setString(1,uid);
	    			rs=s.executeQuery();
	    				rs.next();
	    				//out.println("authority="+rs.getString(1));
	    				if(rs.getString(1).equals("Admin"))
	    				{
	    				flag=1;
	    				}
	    				else
	    				flag=0;
	    		if(flag==1)
	    		{
	    		s=con.prepareStatement("Select distinct p.Project_ID, p.Project_Name, date_format(Start_Date,'%m/%d/%Y'),"+
	    				" date_format(Finish_Date,'%m/%d/%Y'),Substring(Gchart_Color,2),SUBSTRING_INDEX(l.User_ID,'@',1) "+
	    				"from project p,login l,validatetab v where p.Project_Name=? "+
	    				"and p.Project_ID=v.Project_ID and l.User_ID=v.User_ID and (v.PermittedBy=?)");
	    		s.setString(1, pselect);
	    		s.setString(2,uid);
	    		}
	    		else
//	    		{
//	PreparedStatement pst=con.prepareStatement("select p.Project_ID from project p where p.Project_Name=? and p.View_Permission='For All'");
//	pst.setString(1, pselect);
//	ResultSet rst=pst.executeQuery();
//	if(rst.next())
//	{
//		s=con.prepareStatement("Select p.Project_ID, p.Project_Name, date_format(Start_Date,'%m/%d/%Y'),"+
//    			" date_format(Finish_Date,'%m/%d/%Y'),Substring(Gchart_Color,2),l.User_ID "+
//    			"from project p,login l where p.Project_Name=? and l.User_ID=?");
//		s.setString(1, pselect);
//    	s.setString(2,uid);
//    		
//	}
//	else
		{
		s=con.prepareStatement("Select p.Project_ID, p.Project_Name, date_format(Start_Date,'%m/%d/%Y'),"+
	    			" date_format(Finish_Date,'%m/%d/%Y'),Substring(Gchart_Color,2),SUBSTRING_INDEX(v.User_ID,'@',1) "+
	    			"from project p,login l,validatetab v where p.Project_Name=? "+
	    			"and (l.User_ID=v.User_ID and p.Project_ID=v.Project_ID) and v.User_ID="+
	    			"(select v.User_ID from validatetab v,login l,project p where p.Project_Name=? "+
	    			"and p.Project_ID=v.Project_ID and v.PermittedBy=l.User_ID and l.Authority='Admin')");
	    	s.setString(1, pselect);
	    	s.setString(2, pselect);
	    	//s.setString(2,uid);
	    	//s.setString(3, "Admin");
	    }
	  // 	}
	    	st=con.prepareStatement("SELECT avg(t.Per_Completed) FROM task t,project p,validatetab v"+
	    			" where p.Project_Name=? and p.Project_ID=v.Project_ID and v.Valid_ID=t.Valid_ID");
	    	st.setString(1, pselect);
	    		
	    }
	    catch(Exception e1){}
	    try{
	    	rs=s.executeQuery();
	    	rs1=st.executeQuery();
	    	
	    	if(rs1.next())
	    		ss="dgchart";
	    	else
	    		ss="charterror";
	    	s6=Float.parseFloat(rs1.getString(1));
	    	System.out.println("avg="+s6);
	    	//String ss1="";
	    	//StringBuffer sb=new StringBuffer();;
	    	String xmlString="";
	    	
	    	DocumentBuilderFactory documentBuilderFactory = 
                DocumentBuilderFactory.newInstance();
DocumentBuilder documentBuilder = 
           documentBuilderFactory.newDocumentBuilder();
Document document = documentBuilder.newDocument();
Element rootElement = document.createElement("project");
document.appendChild(rootElement);

	    	while(rs.next())
	    	{
	    		i=rs.getString(1);
	    		System.out.println("i="+i);
	    		
	    		s1=rs.getString(2);
	    		System.out.println("s1="+s1);
	    		
	    		s2=rs.getString(3);
	    		System.out.println("s2="+s2);
	    		
	    		s3=rs.getString(4);
	    		System.out.println("s3="+s3);
	    		
	    		s4=rs.getString(5);
	    		
	    		s5=rs.getString(6);
	    
	    		Element rootElement1 = document.createElement("task");
	    		rootElement.appendChild(rootElement1);
	    				    
	    		    
	    		      Element em = document.createElement("pID");
	    		      em.appendChild(document.createTextNode(s1));//here we can pass(i) also which is project id
	    		      rootElement1.appendChild(em);
	    		    
	    	
	    		      Element em1 = document.createElement("pName");
	    		      em1.appendChild(document.createTextNode(s1));
	    		      rootElement1.appendChild(em1);
	    		      
	    		      Element em2 = document.createElement("pStart");
	    		      em2.appendChild(document.createTextNode(s2));
	    		      rootElement1.appendChild(em2);
	    		      
	    		      Element em3 = document.createElement("pEnd");
	    		      em3.appendChild(document.createTextNode(s3));
	    		      rootElement1.appendChild(em3);
	    		      
	    		      Element em4 = document.createElement("pParent");
	    		      em4.appendChild(document.createTextNode("0"));
	    		      rootElement1.appendChild(em4);
	    		      
	    		      
	    		      Element em5 = document.createElement("pOpen");
	    		      em5.appendChild(document.createTextNode("1"));
	    		      rootElement1.appendChild(em5);
	    		      
	    		      
	    		      Element em6 = document.createElement("pColor");
	    		      em6.appendChild(document.createTextNode(s4));
	    		      rootElement1.appendChild(em6);
	    		      
	    		      
	    		      Element em7 = document.createElement("pRes");
	    		      em7.appendChild(document.createTextNode(s5));
	    		      rootElement1.appendChild(em7);
	    		      
	    		      Element em8 = document.createElement("pComp");
	    		      em8.appendChild(document.createTextNode(String.valueOf(s6)));
	    		      rootElement1.appendChild(em8);
	    		      
	    	      PreparedStatement ps=con.prepareStatement("Select t.Task_Name, date_format(t.Start_Date,'%m/%d/%Y'),"+
	    		    		  "date_format(t.Finished_Date,'%m/%d/%Y'),Substring(t.Gchart_Color,2),t.Per_Completed,"+
	    		    		  "t.Dependency ,SUBSTRING_INDEX(v.User_ID,'@',1),t.Task_ID from task t,project p,validatetab v "+
	    		    		  "where p.Project_Name=? and p.Project_ID=v.Project_ID and v.Valid_ID=t.Valid_ID order by t.Dependency asc");
	    		      ps.setString(1, pselect);
	    		      ResultSet rst=ps.executeQuery();
	    		    	while(rst.next())
	    		    	{
	    		    		
	    		    		//ti=rst.getString(1);
	    		    		//System.out.println("ti="+ti);
	    		    		
	    		    		ts1=rst.getString(1);
	    		    		System.out.println("ts1="+ts1);
	    		    		
	    		    		ts2=rst.getString(2);
	    		    		System.out.println("ts2="+ts2);
	    		    		
	    		    		ts3=rst.getString(3);
	    		    		System.out.println("ts3="+ts3);
	    		    		
	    		    		ts4=rst.getString(4);
	    		    		ts5=rst.getString(5);
	    		    		ts6=rst.getString(6);
	    		    		ts7=rst.getString(7);
	    		    		ti=rst.getString(8);
	    		    		
	    		    		
	    		    		
	    		    		Element rootElement11 = document.createElement("task");
	    		    		rootElement.appendChild(rootElement11);
	    		    		    
	    		    		      Element emt = document.createElement("pID");
	    		    		      emt.appendChild(document.createTextNode(ti));
	    		    		      rootElement11.appendChild(emt);
	    		    		    
	    		    	
	    		    		      Element emt1 = document.createElement("pName");
	    		    		      emt1.appendChild(document.createTextNode(ts1));
	    		    		      rootElement11.appendChild(emt1);
	    		    		      
	    		    		      Element emt2 = document.createElement("pStart");
	    		    		      emt2.appendChild(document.createTextNode(ts2));
	    		    		      rootElement11.appendChild(emt2);
	    		    		
	    		    		      Element emt3 = document.createElement("pEnd");
	    		    		      emt3.appendChild(document.createTextNode(ts3));
	    		    		      rootElement11.appendChild(emt3);
	    		    		      
	    		    		      Element emt4 = document.createElement("pColor");
	    		    		      emt4.appendChild(document.createTextNode(ts4));
	    		    		      rootElement11.appendChild(emt4);
	    	
	    		    		      Element emt5 = document.createElement("pComp");
	    		    		      emt5.appendChild(document.createTextNode(ts5));
	    		    		      rootElement11.appendChild(emt5);
	    		    		      
	    		    		      
	    		    		      Element emt8 = document.createElement("pDepend");
	    		    		      emt8.appendChild(document.createTextNode(ts6));
	    		    		      rootElement11.appendChild(emt8);
	    		    		      
	    		    		      Element emt6 = document.createElement("pParent");
	    		    		      emt6.appendChild(document.createTextNode(i));
	    		    		      rootElement11.appendChild(emt6);
	    		    		      
	    		    		      Element emt7 = document.createElement("pOpen");
	    		    		      emt7.appendChild(document.createTextNode("1"));
	    		    		      rootElement11.appendChild(emt7);
	    		    		      
	    		    		      Element emt9 = document.createElement("pRes");
	    		    		      emt9.appendChild(document.createTextNode(ts7));
	    		    		      rootElement11.appendChild(emt9);
	    		    		      
	    		    		      
	    		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    		        Transformer transformer = transformerFactory.newTransformer();
	    		        DOMSource source = new DOMSource(document);
	    		        StringWriter sw = new StringWriter();
	    		        StreamResult result =  new StreamResult(sw);
	    		        transformer.transform(source, result);
	    		        xmlString = sw.toString();
	    	            //System.out.println("xmlString is "+xmlString);
	    	    		//ss1=ss1+xmlString;

	    		    	}	
	    	}
	    	System.out.println(xmlString);
	    	byte b[]=xmlString.getBytes();
	    	 String filePath = getServlet().getServletContext().getRealPath("/");
		        request.setAttribute("filePath",filePath);
		        System.out.println(filePath);
		        
	    	bw = new BufferedOutputStream(new FileOutputStream(filePath+"GChart.xml"));
	    	//BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("/login.xml"));
	    	//String f=filePath+"login.xml";
	    	String f=filePath+"GChart.xml";
	    	System.out.println(f);
	    	request.setAttribute("f",f);
	        bw.write(b);
            System.out.println("This is working");
            bw.flush();
            bw.close();

	    }
	    catch(Exception e)
	    {
	    	ss="charterror";
	    	//System.out.println("Data base error"+e);
	    }
	    finally
		{
	    	MyDataSource.freeConnection(con);
		}
	       
		//byte buf[] = xmlString.getBytes();
        
	return mapping.findForward(ss);
	}

}

