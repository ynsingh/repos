<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 

<%

Connection conn=null;
Statement Statement1=null;
Statement Statement2=null;
Statement theStatement=null;
ResultSet theResult=null;

try{
     String documentid=request.getParameter("documentId") ;     
     String tablename=request.getParameter("tablename");
     String order_number ="0";
     String entity_id="0";
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
    
    
     conn = db.getMysqlConnection();
     
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select number,entity_id from entity_document_master where document_id="+documentid);
     
      while(theResult.next()){
    	order_number=theResult.getString("number");
    	entity_id=theResult.getString("entity_id");
      }
    
     Statement1=conn.createStatement();     
     Statement1.execute("delete from entity_document_master where document_id="+documentid);
     
     Statement2=conn.createStatement();     
     Statement2.execute("delete from  "+tablename +"_values where number="+order_number+ " and idf="+entity_id);     
     
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();Statement1.close();Statement2.close();conn.close();	
%>