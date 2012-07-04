package edu.avv.ws.service;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;


import edu.avv.util.ConnectDB;
import edu.avv.util.DateUtils;
import edu.avv.util.GetConsole;
import edu.avv.util.GetPropertiesFile;
import edu.avv.ws.xml.request.WSRequest;
import edu.avv.ws.xml.response.ResponseObjectFactory;
import edu.avv.ws.xml.response.WSResponse;

public class ServiceRequest {
	
  private static WSResponse wsresponse;
  
  public static WSResponse createResponse(WSRequest wsrequest){
	GetConsole.info("Creating Response");  
	ResponseObjectFactory object = new	ResponseObjectFactory(); 
	wsresponse = object.createWSResponse();
	wsresponse.setHeader(object.createWSResponseHeader());
	wsresponse.setBody(object.createWSResponseBody());
	wsresponse.getHeader().setServer(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVER"));
	wsresponse.getHeader().setDate(DateUtils.now("yyyy-MM-dd"));
	wsresponse.getHeader().setTime(DateUtils.now("K:mm:ss a z"));

	String institute = null;
	String subinstitute = null;
	String department = null;
	String project = null;
	String budgetId = null;

            if((wsrequest.getBody().getMethod()).equals("getProjectNamesFromGMS")){
               	institute=wsrequest.getBody().getParameter().get(0).getValue();
                  	subinstitute=wsrequest.getBody().getParameter().get(1).getValue();
                 	department=wsrequest.getBody().getParameter().get(2).getValue();
                 	ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
                	b=getProjectNamesOfInstitute(institute,subinstitute,department);
                	if(!b.isEmpty()){
                		wsresponse.getBody().getMessage().addAll(b);
                	}
                	else {
                		createErrorResponse("No Value");
                	}
               
            }
            else if((wsrequest.getBody().getMethod()).equals("getBudgetHeadsFromGMS")){
            		project=wsrequest.getBody().getParameter().get(0).getValue();
            		ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
            		b=getBudgetHeadsFromGMS(project);
                	if(!b.isEmpty()){
                		wsresponse.getBody().getMessage().addAll(b);
                	}
                	else {
                		createErrorResponse("No Value");
                	}
                   
            }
            else if((wsrequest.getBody().getMethod()).equals("getBudgetAmountFromGMS")){
            	
            		project=wsrequest.getBody().getParameter().get(0).getValue();
            		budgetId=wsrequest.getBody().getParameter().get(1).getValue();
            		ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
            		b=getBudgetAmountFromGMS(project,budgetId);
                	if(!b.isEmpty()){
                		wsresponse.getBody().getMessage().addAll(b);
                	}
                	else {
                		createErrorResponse("No Value");
                	}
              
            }
            else {
            }
          

	if(!wsrequest.getBody().getMethod().isEmpty())
	{
	}
    return wsresponse;	  
  }
  
  public static WSResponse createErrorResponse(String msg){
	GetConsole.info("Creating Error Response");  
    ResponseObjectFactory object = new ResponseObjectFactory();
    wsresponse = object.createWSResponse();
    wsresponse.setHeader(object.createWSResponseHeader());
    wsresponse.setBody(object.createWSResponseBody());
    wsresponse.getHeader().setServer(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVER"));
	wsresponse.getHeader().setDate(DateUtils.now("yyyy-MM-dd"));
	wsresponse.getHeader().setTime(DateUtils.now("K:mm:ss a z"));
	WSResponse.Body.Message m = object.createWSResponseBodyMessage();
	m.setName("ERROR_MESSAGE");
	m.getValue().add(msg);
	wsresponse.getBody().getMessage().add(m);
    return wsresponse;	  
  }
  public static ArrayList<WSResponse.Body.Message> getProjectNamesOfInstitute(String institute,String subinstitute,String department){

	  Connection con = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  boolean resultVal = false;
	  ResponseObjectFactory object = new	ResponseObjectFactory(); 
	  ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
	  try {
	con =  ConnectDB.getMysqlConnection();
		  stmt = con.createStatement();
		  String queryString = "SELECT p.name AS ProjectName,p.code AS ProjectCode, pd.name AS Department,pa.name_of_the_institution AS SubInstitution,u.name_of_university AS Institution "+
				"FROM project_department_map pm LEFT JOIN projects p ON (p.id=pm.projects_id)"+
				" LEFT JOIN party_department pd ON(pd.id=pm.party_department_id)"+
				" LEFT JOIN grant_allocation g ON (g.projects_id=p.id)"+
				" LEFT JOIN party pa ON(pa.id=g.party_id)"+
				" LEFT JOIN university_institution_map um ON (um.party_id=pa.id )"+
				" LEFT JOIN university_master u ON (u.id=um.university_master_id) WHERE 1=1";
		  if(!institute.equals("")){
			  queryString=queryString+" AND u.code='"+institute+"'";
		  }
		  if(!subinstitute.equals("")){
			  queryString=queryString+" AND pa.code='"+subinstitute+"'";
		  }
		  if(!department.equals("")){
			  queryString=queryString+" AND pd.department_code='"+department+"'";
		  }
		  rs = stmt.executeQuery(queryString+" group by p.id;");
		 
		  
		  int i =0;
		  WSResponse.Body.Message a=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message c=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message d=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message e=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message f=object.createWSResponseBodyMessage();
		  a.setName("ProjectName");
		  c.setName("Institution");
		  d.setName("SubInstitution");
		  e.setName("Department");
		  f.setName("ProjectCode");
		  
		  while (rs.next()){
			  resultVal = true;
			  i++;
			  if(rs.getRow()==1){
			  	c.getValue().add(rs.getString("Institution"));
				d.getValue().add(rs.getString("SubInstitution"));
				e.getValue().add(rs.getString("Department"));
			  }
			  	a.getValue().add(rs.getString("ProjectName"));
			  	f.getValue().add(rs.getString("ProjectCode"));
			     }
		  if(resultVal){
			  	b.add(c);
		  		b.add(d);
		  		b.add(e);
		  		b.add(a);
		  		b.add(f);
		  }
		  rs.last();
		  con.close();
		  }
	  catch(Exception e){
	  }
	  
	  
	  return b;
  }
  public static ArrayList<WSResponse.Body.Message> getBudgetHeadsFromGMS(String project){
	  Connection con = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  boolean resultVal = false;
	  ResponseObjectFactory object = new	ResponseObjectFactory(); 
	  ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
	  try {
		 
		  con =  ConnectDB.getMysqlConnection();
		  stmt = con.createStatement();
		  rs = stmt.executeQuery("SELECT  ah.name  AS AcHead ,ah.code  AS AcHeadId ,p.name AS ProjectName,pd.name AS Department, "+
				"pa.name_of_the_institution AS SubInstitution, u.name_of_university AS Institution "+
				" FROM account_heads ah LEFT JOIN grant_allocation_split gs ON (ah.id= gs.account_head_id )"+
				" LEFT JOIN grant_allocation ga ON(gs.grant_allocation_id=ga.id )"+
				" LEFT JOIN projects p ON(p.id=gs.projects_id)"+
				" LEFT JOIN project_department_map pm ON(p.id=pm.projects_id)"+
				" LEFT JOIN party_department pd ON(pd.id=pm.party_department_id)"+
				" LEFT JOIN party pa ON(pa.id=ga.party_id)"+
				" LEFT JOIN university_institution_map um ON (um.party_id=pa.id )"+
				" LEFT JOIN university_master u ON (u.id=um.university_master_id)"+
				" WHERE p.code='"+project+"';");
		  
		  WSResponse.Body.Message a=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message c=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message d=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message e=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message f=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message g=object.createWSResponseBodyMessage();
		  a.setName("ProjectName");
		  c.setName("Institution");
		  d.setName("SubInstitution");
		  e.setName("Department");
		  f.setName("Budget");
		  g.setName("BudgetId");
		  
		  while (rs.next()){
			  resultVal = true;
		  if(rs.getRow()==1){
				  if(rs.getString("Institution")!=null){
					  c.getValue().add(rs.getString("Institution"));
				  }else {
					  c.getValue().add("No Institute");
				  }
				d.getValue().add(rs.getString("SubInstitution"));
				if(rs.getString("Department")!=null){
					e.getValue().add(rs.getString("Department"));
				}
				else {
					e.getValue().add("No Department");
				}
				a.getValue().add(rs.getString("ProjectName"));
			  }
			  	
			  	f.getValue().add(rs.getString("AcHead"));
			  	g.getValue().add(rs.getString("AcHeadId"));
			     }
		  if(resultVal){
		  	b.add(c);
		  	b.add(d);
		  	b.add(e);
		  	b.add(a);
		  	b.add(g);
		  	b.add(f);
		  }
		  con.close();
		
	  }
	  catch(Exception e){
	  }
	  
	  
	  return b;
  }
  public static ArrayList<WSResponse.Body.Message> getBudgetAmountFromGMS(String project,String budgetId){
	  Connection con = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  boolean resultVal = false;
	  ResponseObjectFactory object = new	ResponseObjectFactory(); 
	  ArrayList <WSResponse.Body.Message> b = new ArrayList<WSResponse.Body.Message>();
	  try {
			  con =  ConnectDB.getMysqlConnection();
		  stmt = con.createStatement();
		  rs = stmt.executeQuery("SELECT  ah.name  AS AcHead ,ah.id AS AcHeadId,p.name AS ProjectName,pd.name AS Department,"+
				"pa.name_of_the_institution AS SubInstitution, u.name_of_university AS Institution,"+
				"SUM(gs.amount) AS BudgetAmount,"+
				"SUM(ge.expense_amount) AS ExpenseAmount,"+
				" ((SUM(gs.amount))-(SUM(ge.expense_amount))) AS AvailableAmount"+
				" FROM account_heads ah LEFT JOIN grant_allocation_split gs ON (ah.id= gs.account_head_id )"+
				" LEFT JOIN grant_allocation ga ON(gs.grant_allocation_id=ga.id )"+
				" LEFT JOIN projects p ON(p.id=gs.projects_id)"+
				" LEFT JOIN grant_expense ge ON(gs.id=ge.grant_allocation_split_id)"+
				" LEFT JOIN project_department_map pm ON(p.id=pm.projects_id)"+
				" LEFT JOIN party_department pd ON(pd.id=pm.party_department_id)"+
				" LEFT JOIN party pa ON(pa.id=ga.party_id) LEFT JOIN university_institution_map um ON (um.party_id=pa.id )"+
				" LEFT JOIN university_master u ON (u.id=um.university_master_id) WHERE "+
				" p.code='"+project+"'"+
				" AND ah.code='"+budgetId+"' GROUP BY gs.account_head_id");
		
		  WSResponse.Body.Message a=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message c=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message d=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message e=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message f=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message g=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message h=object.createWSResponseBodyMessage();
		  WSResponse.Body.Message i=object.createWSResponseBodyMessage();
		  a.setName("ProjectName");
		  c.setName("Institution");
		  d.setName("SubInstitution");
		  e.setName("Department");
		  f.setName("Budget");
		  i.setName("BudgetId");
		  g.setName("BudgetAmount");
		  h.setName("BudgetAvailable");
		  while (rs.next()){
			  resultVal = true;
			  if(rs.getRow()==1){
				  if(rs.getString("Institution")!=null){
					  c.getValue().add(rs.getString("Institution"));
				  }else {
					  c.getValue().add("No Institute");
				  }
				d.getValue().add(rs.getString("SubInstitution"));
				if(rs.getString("Department")!=null){
					e.getValue().add(rs.getString("Department"));
				}
				else {
					e.getValue().add("No Department");
				}
				a.getValue().add(rs.getString("ProjectName"));
			  }
			  	
			  	f.getValue().add(rs.getString("AcHead"));
			  	i.getValue().add(rs.getString("AcHeadId"));
			  	if(rs.getString("BudgetAmount")!=null){
					g.getValue().add(rs.getString("BudgetAmount"));
				}
				else {
					g.getValue().add("0");
				}
			  	if(rs.getString("AvailableAmount")!=null){
					h.getValue().add(rs.getString("AvailableAmount"));
				}
				else {
					h.getValue().add("0");
				}
			     }
		  if(resultVal){
		  	b.add(c);
		  	b.add(d);
		  	b.add(e);
		  	b.add(a);
		  	b.add(i);
		  	b.add(f);
		  	
		  	b.add(g);
		  	b.add(h);
		  }
		  con.close();
	  }
	  catch(Exception e){
	  }
	  
	  
	  return b;
  }
 
}
