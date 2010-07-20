package in.ac.dei.edrp.pms.ganttChart;

import in.ac.dei.edrp.pms.dataBaseConnection.*;

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

import java.sql.*;

/**
 * This class is used for generating gant chart.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 *
 */
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
		HttpSession session=request.getSession();
		String validorgportal=(String)session.getAttribute("validOrgInPortal");
		String pselect=request.getParameter("pname");
		if(pselect==null)
			return mapping.findForward("dgchart");
	
	//System.out.println("pselect="+pselect);
		String project_code="";
	    String project_name="";
	    String project_ss_date="";
	    String project_send_date="";
	    String project_gchart_color="";
	    String resource_name="";
	    float project_completion=0;
	    
	    	    
	    String task_id="";
	    String task_name="";
	    String task_sstart_date="";
	    String task_send_date="";
	    String task_color="";
	    String task_per_completion="";
	    String task_dependency="";
	    String task_resource_name="";
	    String forwardString="charterror";
	   
	    Connection con=null;
	    PreparedStatement pst_projectDetails=null;
	    PreparedStatement pst_projectCompletion=null;
	    ResultSet rs_projectDetails=null;
	    ResultSet rs_projectCompletion=null;
	    BufferedOutputStream bw=null;
	  	  
		//String uid=(String)session.getAttribute("uid");
	    try
	    {	    	
	    	con=MyDataSource.getConnection();
	      	pst_projectDetails=con.prepareStatement("Select distinct p.Project_code,p.Project_Name," +
    				" date_format(p.schedule_Start_Date,'%m/%d/%Y')," +
    				"date_format(p.schedule_end_Date,'%m/%d/%Y')," +
    				"Substring(p.Gchart_Color,2),SUBSTRING_INDEX(u.valid_User_ID,'@',1)" +
    				" from project p,user_in_org u,task t,validatetab v," +
    				"task_with_user twu where u.valid_orgportal=?" +
    				" and u.valid_key=v.valid_user_key and " +
    				"v.valid_project_code=t.vproject_code and " +
    				"p.project_code=t.vproject_code and " +
    				"v.valid_id=twu.valid_id and twu.task_id=t.task_id" +
    				" and p.Project_Name=?"); 
    		pst_projectDetails.setString(1, validorgportal);
    		pst_projectDetails.setString(2,pselect);
    		    		
    		pst_projectCompletion=con.prepareStatement("SELECT avg(t.per_completed) FROM task t,project p," +
	    			"validatetab v,task_with_user twu,user_in_org u where " +
	    			"(u.valid_orgportal=? and u.valid_key=v.valid_user_key) " +
	    			"and (p.Project_Name=? and p.Project_code=v.valid_project_code " +
	    			"and v.valid_project_code=t.vproject_code) and " +
	    			"(v.valid_id=twu.valid_id and t.task_id=twu.task_id)");
	    	pst_projectCompletion.setString(1, validorgportal);
    		pst_projectCompletion.setString(2,pselect);
	    		
	    }
	    catch(Exception e1){}
	    try{
	    	rs_projectDetails=pst_projectDetails.executeQuery();
	    	rs_projectCompletion=pst_projectCompletion.executeQuery();
	    	
	    	if(rs_projectCompletion.next())
	    		forwardString="dgchart";
	    	else
	    		forwardString="charterror";
	    	project_completion=Float.parseFloat(rs_projectCompletion.getString(1));
	    //	System.out.println("avg="+project_completion);
	    	String xmlString="";
	    	
	    	DocumentBuilderFactory documentBuilderFactory = 
                DocumentBuilderFactory.newInstance();
	    	DocumentBuilder documentBuilder = 
	    		documentBuilderFactory.newDocumentBuilder();
	    	Document document = documentBuilder.newDocument();
	    	Element rootElement = document.createElement("project");
	    	document.appendChild(rootElement);

	    	if(rs_projectDetails.next())
	    	{
	    		project_code=rs_projectDetails.getString(1);
	    		//System.out.println("project_code="+project_code);
	    		
	    		project_name=rs_projectDetails.getString(2);
	    		//System.out.println("project_name="+project_name);
	    		
	    		project_ss_date=rs_projectDetails.getString(3);
	    		project_send_date=rs_projectDetails.getString(4);
	    		project_gchart_color=rs_projectDetails.getString(5);
	    		resource_name=rs_projectDetails.getString(6);
	    		
	    		Element rootElement1 = document.createElement("task");
	    		rootElement.appendChild(rootElement1);
	    				    
	    		    
	    		      Element em = document.createElement("pID");
	    		      em.appendChild(document.createTextNode(project_name));//here we can pass(project_code) also which is project id
	    		      rootElement1.appendChild(em);
	    		    
	    	
	    		      Element em1 = document.createElement("pName");
	    		      em1.appendChild(document.createTextNode(project_name));
	    		      rootElement1.appendChild(em1);
	    		      
	    		      Element em2 = document.createElement("pStart");
	    		      em2.appendChild(document.createTextNode(project_ss_date));
	    		      rootElement1.appendChild(em2);
	    		      
	    		      Element em3 = document.createElement("pEnd");
	    		      em3.appendChild(document.createTextNode(project_send_date));
	    		      rootElement1.appendChild(em3);
	    		      
	    		      Element em4 = document.createElement("pParent");
	    		      em4.appendChild(document.createTextNode("0"));
	    		      rootElement1.appendChild(em4);
	    		      
	    		      
	    		      Element em5 = document.createElement("pOpen");
	    		      em5.appendChild(document.createTextNode("1"));
	    		      rootElement1.appendChild(em5);
	    		      
	    		      
	    		      Element em6 = document.createElement("pColor");
	    		      em6.appendChild(document.createTextNode(project_gchart_color));
	    		      rootElement1.appendChild(em6);
	    		      
	    		      
	    		      Element em7 = document.createElement("pRes");
	    		      em7.appendChild(document.createTextNode(resource_name));
	    		      rootElement1.appendChild(em7);
	    		      
	    		      Element em8 = document.createElement("pComp");
	    		      em8.appendChild(document.createTextNode(String.valueOf(project_completion)));
	    		      rootElement1.appendChild(em8);
	    		      
	    		      PreparedStatement ps=con.prepareStatement("Select t.Task_Name," +
	  	    	      		" date_format(t.schedule_Start_Date,'%m/%d/%Y')," +
	  	    	      		"date_format(t.schedule_end_Date,'%m/%d/%Y')," +
	  	    	      		"Substring(t.Gchart_Color,2),t.Per_Completed," +
	  	    	      		"t.Dependency ,SUBSTRING_INDEX(u.valid_User_ID,'@',1)," +
	  	    	      		"t.Task_ID from task t,project p,validatetab v," +
	  	    	      		"user_in_org u,task_with_user twu " +
	  	    	      		"where u.valid_orgportal=? and " +
	  	    	      		"u.valid_key=v.valid_user_key and " +
	  	    	      		"v.valid_id=twu.valid_id and twu.task_id=t.task_id " +
	  	    	      		"and p.Project_Name=? and p.Project_code=v.valid_Project_code " +
	  	    	      		"and v.valid_Project_code=t.vproject_code order by t.Dependency asc");	    		      ps.setString(1, pselect);
	  	    	      		ps.setString(1, validorgportal);
	  	    	    		ps.setString(2,pselect);
	  	    	      		ResultSet rst=ps.executeQuery();
	  	    		    	while(rst.next())
	  	    		    	{
	    		    			    		    		
	    		    		task_name=rst.getString(1);
	    		    	//	System.out.println("task_name="+task_name);
	    		    		
	    		    		task_sstart_date=rst.getString(2);
	    		    	//	System.out.println("task_sstart_date="+task_sstart_date);
	    		    		
	    		    		task_send_date=rst.getString(3);
	    		    	//	System.out.println("task_send_date="+task_send_date);
	    		    		
	    		    		task_color=rst.getString(4);
	    		    		task_per_completion=rst.getString(5);
	    		    		task_dependency=rst.getString(6);
	    		    		task_resource_name=rst.getString(7);
	    		    		task_id=rst.getString(8);
	    		    	    		    		
	    		    		
	    		    		Element rootElement11 = document.createElement("task");
	    		    		rootElement.appendChild(rootElement11);
	    		    		    
	    		    		      Element emt = document.createElement("pID");
	    		    		      emt.appendChild(document.createTextNode(task_id));
	    		    		      rootElement11.appendChild(emt);
	    		    		    
	    		    	
	    		    		      Element emt1 = document.createElement("pName");
	    		    		      emt1.appendChild(document.createTextNode(task_name));
	    		    		      rootElement11.appendChild(emt1);
	    		    		      
	    		    		      Element emt2 = document.createElement("pStart");
	    		    		      emt2.appendChild(document.createTextNode(task_sstart_date));
	    		    		      rootElement11.appendChild(emt2);
	    		    		
	    		    		      Element emt3 = document.createElement("pEnd");
	    		    		      emt3.appendChild(document.createTextNode(task_send_date));
	    		    		      rootElement11.appendChild(emt3);
	    		    		      
	    		    		      Element emt4 = document.createElement("pColor");
	    		    		      emt4.appendChild(document.createTextNode(task_color));
	    		    		      rootElement11.appendChild(emt4);
	    	
	    		    		      Element emt5 = document.createElement("pComp");
	    		    		      emt5.appendChild(document.createTextNode(task_per_completion));
	    		    		      rootElement11.appendChild(emt5);
	    		    		      
	    		    		      
	    		    		      Element emt8 = document.createElement("pDepend");
	    		    		      emt8.appendChild(document.createTextNode(task_dependency));
	    		    		      rootElement11.appendChild(emt8);
	    		    		      
	    		    		      Element emt6 = document.createElement("pParent");
	    		    		      emt6.appendChild(document.createTextNode(project_code));
	    		    		      rootElement11.appendChild(emt6);
	    		    		      
	    		    		      Element emt7 = document.createElement("pOpen");
	    		    		      emt7.appendChild(document.createTextNode("1"));
	    		    		      rootElement11.appendChild(emt7);
	    		    		      
	    		    		      Element emt9 = document.createElement("pRes");
	    		    		      emt9.appendChild(document.createTextNode(task_resource_name));
	    		    		      rootElement11.appendChild(emt9);
	    		    		     	    		    		      
	    		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    		    Transformer transformer = transformerFactory.newTransformer();
	    		    
	    		    DOMSource source = new DOMSource(document);
	    		    
	    		        StringWriter sw = new StringWriter();
	    		        StreamResult result =  new StreamResult(sw);
	    		        transformer.transform(source, result);
	    		       
	    		        xmlString = sw.toString();
	    	       	}	
	    	}
	    	byte b[]=xmlString.getBytes();
	    	 String filePath = getServlet().getServletContext().getRealPath("/");
	//request.setAttribute("filePath",filePath);
		     //   System.out.println(filePath);
		        
	    	bw = new BufferedOutputStream(new FileOutputStream(filePath+"GChart.xml"));
	    	//BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("/login.xml"));
	    	//String f=filePath+"login.xml";
	    //	String f=filePath+"GChart.xml";
	    //	System.out.println(f);
	  //request.setAttribute("f",f);
	        bw.write(b);
	        
            System.out.println("This is working");
            bw.flush();
            bw.close();
	    }
	    catch(Exception e)
	    {
	    	forwardString="charterror";
	    	//System.out.println("error in gantt chart with xml file="+e);
	    }
	    finally
		{
	    	MyDataSource.freeConnection(con);
		}
	 
	return mapping.findForward(forwardString);
	}

}

