package com.erp.nfes;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





public class StaffProfileServlet extends HttpServlet {


	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//Set the session time out to 45 minutes...
		request.getSession().setMaxInactiveInterval(2700);
		String language=(String) request.getSession().getAttribute("language");
		//System.out.println("Language1: "+language);
		response.setContentType("text/html; charset=UTF-8");
		Locale locale = new Locale(language, "");
		RequestVariables userRequest = new RequestVariables ();
		try {
			userRequest = getRequestParamenters ( request );
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		String action = userRequest.getAction();

		if (request.getParameter("entityId")==null){
			response.sendRedirect("login.jsp");
		}

		if ( action.trim().equals( "CDOC-OPEN_A_DOCUMENT" )||(action.trim().equals( "CDOC-OPEN_A_DOCUMENT_FOR_APPROVE" )) ) {
			request.getSession().setAttribute("clinicalform-actiondesc","Opening clinical form");
			createNewDocument ( request, response, userRequest,language );
		} else if ( action.trim().equals( "CDOC-SAVE_THE_DOCUMENT" ) ) {
			request.getSession().setAttribute("clinicalform-actiondesc","Saving Staff Profile");
			userRequest.setSubmitButton( "" + FacultyProfileConstants.CDOC_DOCUMENT_DICTATED );
			userRequest.setSaveFormat( FacultyProfileConstants.CDOC_REGULAR_FORM );
			try {
				saveDocument ( request, response, userRequest );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ( action.trim().equals( "CDOC-PRINT_READY_DOCUMENT" ) ) {
			request.getSession().setAttribute("clinicalform-actiondesc","View in printableformat (pdf)");
			userRequest.setSubmitButton( FacultyProfileConstants.CDOC_DOCUMENT_SHOW_PRINT_READY_REPORT );
			showPrintableReport ( request, response, userRequest );
		}else if ( action.trim().equals( "CDOC-FINAL_APPROVE")||(action.trim().equals( "CDOC-RECORD_APPROVE" )) ) {
			request.getSession().setAttribute("clinicalform-actiondesc","Approving Staff Profile");
			userRequest.setSubmitButton( "" + FacultyProfileConstants.CDOC_DOCUMENT_SIGNED_OFF );
			userRequest.setSaveFormat( FacultyProfileConstants.CDOC_REGULAR_FORM );
			try {
				saveDocument ( request, response, userRequest );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}//end of function service.

	private void saveDocument(HttpServletRequest request,HttpServletResponse response, RequestVariables userRequest) throws Exception {
		long documentId = 0;
		documentId = saveTheDocument(request, response, userRequest);
		if ( documentId != 0 ) {
			redirectUrlDocument ( request, response, documentId, userRequest,"null" );
		}	else{
			if (userRequest.getDocumentId().equals("")){
					documentId=0;}
			else{
				documentId=Long.parseLong(userRequest.getDocumentId())	;			
			}
			//System.out.println("Doc ID:"+documentId);
			redirectUrlDocument ( request, response,documentId, userRequest,"Unable to Save Record.Try Again." );
		}

	}

	private void redirectUrlDocument(HttpServletRequest request,HttpServletResponse response, long documentId,RequestVariables userRequest,String message) throws Exception {
		if ( !userRequest.getSubmitButton().trim().equals( FacultyProfileConstants.CDOC_DOCUMENT_SHOW_PRINT_READY_REPORT ) ) {
			String conPath = request.getContextPath();
			String form_Name=userRequest.getFormName();
			String action="";
			//System.out.println("++++++++++Action :+++"+userRequest.getAction());
			if (userRequest.getAction().trim().equals( "CDOC-RECORD_APPROVE" )){
				if(message.equals("null")){
					message="Record Approved.";		
					action="CDOC-OPEN_A_DOCUMENT";
				}else{
					action="CDOC-OPEN_A_DOCUMENT_FOR_APPROVE";
				}				
				response.sendRedirect("./StaffProfileServlet?action="+action+"&entityId=" + userRequest.getEntityId() + "&documentId=" + documentId +
							"&formName=" + userRequest.getFormName() + "&docType=" + userRequest.getDocumentType() +
							"&addendumYN=" + userRequest.getAddendumYesNo() + "&parentDocId=" + userRequest.getParentDocumentId()+"&user_dept_id=" + userRequest.getUserDepartmentId() +
							( !isValid( userRequest.getAnchor() ) ? "" : "&form_anchor=" + userRequest.getAnchor() ) +
							( !isValid( userRequest.getEntityType() ) ? "" : "&entityType=" + userRequest.getEntityType())+ "&reload_after_save="+message);
			}
			else if (form_Name.equals("staff_profile_report_v0") ){			
				if(message.equals("null")){
					message="Saved Successfully. ";							
				}				
			response.sendRedirect("./StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId=" + userRequest.getEntityId() + "&documentId=" + documentId +
						"&formName=" + userRequest.getFormName() + "&docType=" + userRequest.getDocumentType() +
						"&addendumYN=" + userRequest.getAddendumYesNo() + "&parentDocId=" + userRequest.getParentDocumentId()+"&user_dept_id=" + userRequest.getUserDepartmentId() +
						( !isValid( userRequest.getAnchor() ) ? "" : "&form_anchor=" + userRequest.getAnchor() ) +
						( !isValid( userRequest.getEntityType() ) ? "" : "&entityType=" + userRequest.getEntityType())+ "&reload_after_save="+message);
			}
			else {
				ArrayList tableData=getTableData(documentId, userRequest.getEntityId(),userRequest.getFormName(),userRequest.getEntityType());
				if(message.equals("null")){
					message="'Record Saved.'";							
				}else{
					message="'"+message+"'";
				}
				String args="entityId="+userRequest.getEntityId()+"&documentId="+ documentId+"&formname='"+userRequest.getFormName()+"'&entitytype='"+userRequest.getEntityType()+"'&reload=1"+ "&Data="+tableData+"&message="+message;
				response.sendRedirect( conPath + "/jsp/showdetailedform.jsp?"+args);
				return;
			}

		}

	}

	/**
	 *
	 * @param string
	 * @return
	 */
	public static boolean isValid ( String string ) {

		if ( string == null ) return false;
		if ( string.trim().equals( "" ) ) return false;
		if ( string.trim().length() < 1 ) return false;

		return true;
	}


	private long saveTheDocument(HttpServletRequest request,HttpServletResponse response, RequestVariables userRequest) throws Exception {
		HashMap qaMap = userRequest.getQuestionAnswerMap();
		if(qaMap==null){
			//System.out.println("Empty Has Map");			
			//return (Long.parseLong(userRequest.getDocumentId()));
			return 0;
		}
		else{
		long documentId = HtmlTemplateHelper.saveOrUpdateTheDocumentData ( qaMap, userRequest,request );
		return documentId;
		}
	}




	/**
	 * @param request
	 * @param response
	 * @param userRequest
	 */
	private void createNewDocument(HttpServletRequest request, HttpServletResponse response, RequestVariables userRequest,String language) {
		try {
			createOrOpenNewDocument ( request, response, userRequest,language );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param userRequest
	 */
	private void createOrOpenNewDocument(HttpServletRequest request,
									HttpServletResponse response,
									RequestVariables userRequest,String language)
									throws Exception {



    	DocumentInfo  dInfo = new DocumentInfo();
		if ( dInfo == null ) {
			//pdfreportHelper.showErrorMessage( response,"Invalid result document info..." );
			return;
		}

		String formType = "0";//dInfo.getForm_type();
		userRequest.setFormType( formType );
		userRequest.setDocumentType( "1");//dInfo.getDocument_type() );
		createOrOpenStylesheetDocument ( request, response, userRequest,language );


	}


	/**
	 * @param request
	 * @param response
	 * @param userRequest
	 * @throws Exception
	 */
	private void createOrOpenStylesheetDocument(HttpServletRequest request, HttpServletResponse response, RequestVariables userRequest,String language) throws Exception
 {
		Connection connect = null;
		try {


			String number = "";
			ArrayList questionVOsList = new ArrayList();
			number = userRequest.getNumber();
			//System.out.println("number==========="+number);
			
			ConnectDB conObj=new ConnectDB();
			connect = conObj.getMysqlConnection();			
			if ( isValid(userRequest.getDocumentId()) && Integer.parseInt(userRequest.getDocumentId())>0 ){
				questionVOsList = HtmlTemplateHelper.getEditableReportFormForPatients( userRequest,number,language);

			} else {
				questionVOsList = HtmlTemplateHelper.getPrintFormElements(connect, userRequest, language);
			}

			//String patientDob="30/05/1980";


			ArrayList questionNames = new ArrayList();
			StringBuffer str = new StringBuffer (10000);
			QuestionsVO questVO = new QuestionsVO();

			//System.out.println("<documentId>" + userRequest.getDocumentId() + "</documentId>\n");
			//System.out.println("<docNumber>" + userRequest.getNumber() + "</docNumber>\n");
			//System.out.println("<docType>" + userRequest.getDocumentType() + "</docType>\n\n");
			//System.out.println("<entityId>" + userRequest.getEntityId() + "</entityId>\n\n");
			//System.out.println("=========<?xml-stylesheet type=\"text/xsl\" href=\"./xml/" + userRequest.getFormName().trim() + ".xml\" ?> \n");
			
			String userfullname="";
			String staffemail="";
			String university="";
			String institution="";
			String department="";
			String designation="";
			Statement st_userdet = connect.createStatement();			
			ResultSet rs_userdets = st_userdet.executeQuery("SELECT * FROM `staff_profile_masterdetails_v0_values` WHERE userid="+ userRequest.getEntityId());
			while(rs_userdets.next()) {				
				userfullname=rs_userdets.getString("user_full_name");				
				university=rs_userdets.getString("University");
				institution=rs_userdets.getString("Institution");
				department=rs_userdets.getString("Department");
				designation=rs_userdets.getString("Designation");
				staffemail=rs_userdets.getString("username");
			}		
			
			str.append ("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> \n");
			str.append ("<?xml-stylesheet type=\"text/xsl\" href=\"./xml/" + userRequest.getFormName().trim() + ".xml\" ?> \n");
			str.append ("<OIO version=\"1.00\" xmlns:oioNS=\"http://devedge.netscape.com/2002/de\"> \n");
			str.append ("\n" + "<form> \n" + "<name>" + userRequest.getFormName().trim() + "</name>\n");
			if ( isValid(userRequest.getDocumentId()) && Integer.parseInt(userRequest.getDocumentId())>0 ){
				str.append ("<documentId>" + userRequest.getDocumentId() + "</documentId>\n");
				str.append( "<docNumber>" + userRequest.getNumber() + "</docNumber>\n");
			} else {
				str.append ("<documentId></documentId>\n<docNumber></docNumber>\n");
			}
			//System.out.println("Reload :"+request.getParameter("reload_after_save"));
			str.append ("<reload_after_save>"+request.getParameter("reload_after_save")+"</reload_after_save>");
			str.append("<action>" + userRequest.getAction() + "</action>\n\n");
			str.append("<approved>" + userRequest.getApprovedYesNo() + "</approved>\n\n");
			str.append ("<docType>" + userRequest.getDocumentType() + "</docType>\n\n");
			str.append ("<entityId>" + userRequest.getEntityId() + "</entityId>\n\n");
			str.append("<parentDocumentId>" + userRequest.getParentDocumentId() + "</parentDocumentId>\n");
			if(userRequest.getParentDocumentId() != null ) {
				str.append("<addendumYN>" + "yes" + "</addendumYN>\n");
			}
			str.append ("<user_dept_id>" + userRequest.getUserDepartmentId() + "</user_dept_id>\n");			
			String str1 = userRequest.getAmmendedYesNo();
			if("1".equals(str1))  {

				str.append("<amendYN>" + "yes" + "</amendYN>\n");
			}
			
			str.append("<loguser>"+ request.getUserPrincipal().getName() +"</loguser>");
			str.append("<staffemail>"+staffemail+"</staffemail>");
			str.append("<userfullname>"+ userfullname +"</userfullname>");
			str.append("<university>"+university+"</university>");
			str.append("<institution>"+institution+"</institution>");
			str.append("<department>"+department+"</department>");
			str.append("<designation>"+designation+"</designation>");
			
			//iterate over ArrayList to generate Xml string for the questions.
			for (int cnt=0; cnt<questionVOsList.size(); cnt++){
				questVO = (QuestionsVO) questionVOsList.get(cnt);
				str.append("<" + questVO.getName() + ">\n");
				str.append("	<number>" + questVO.getNumber() + "</number>\n");
				str.append("	<description>" + questVO.getDescription() + "</description>\n");

				str.append("	<oioNS:prompt>" + questVO.getPrompt().replace("&", "&amp;") + "</oioNS:prompt>\n");
				str.append("	<oioNS:itemtype>" + questVO.getItemtype() + "</oioNS:itemtype>\n");

				str.append("	<oioNS:renderinfo>\n\n" + questVO.getAction() + "\n\n</oioNS:renderinfo>\n");
				str.append("</" + questVO.getName() + ">\n\n");
				//as you iterate populate questionNames arrayList also.
				questionNames.add(questVO.getName());
			}
			str.append("</form>\n");
			str.append("</OIO>\n");
			//end of the Xml string.
			//System.out.println(str.toString());
			String key = "myQuestions" + userRequest.getFormName();
			HttpSession session = request.getSession();
			session.setAttribute(key, questionNames);

			response.setContentType("application/xml");

			response.getWriter().println(str);

			//connect.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }	
	}








/**
 *
 */
	/*private String getErrorPage(String msg) {
		StringBuffer sbr = new StringBuffer();
		sbr.append("<html>")
			.append("<head>")
			.append("<link rel=\"stylesheet\" href=\"/STYLESHEETS/AhisDetailScreen.css\" type=\"text/css\">")
			.append("<script src=\"../Core_Common/coolbuttons.js\"></script>")
			.append("<script src=\"../Core_Common/CommonFunctions.js\"></script>")
			.append("<script src=\"../Core_Common/RegExpFormValidation.js\"></script>")
			.append("<script src=\"../Core_Common/Validate.js\"></script>")
			.append("</head>")
			.append("<body>")
			.append("<form name=\"staffProfileForm\">")
			.append("<table width=\"100%\" height=\"100\" class=\"table\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">")
			.append("<tr class=\"info\">")
			.append("<td align=\"center\"> Sorry !!! </td>")
			.append("</tr>")
			.append("<tr height=\"80\">")
			.append("<td align=\"center\">"+msg+"</td>")
			.append("</tr>")
			.append("</table>")
			.append("</form>")
			//.append("<%@include file=\"../Core_Common/Footer.jsp\"%>")
			.append("</html>");
		return sbr.toString();
	}*/
//End Merging
	/**
	 * @param request
	 * @return
	 */
	public RequestVariables getRequestParamenters( HttpServletRequest request ) throws Exception {



		RequestVariables userRequest = new RequestVariables ();
		HttpSession session = request.getSession();
		//System.out.println("formName,entitytype :"+request.getParameter("formName")+request.getParameter("entitytype"));
		/*
		userRequest.setFormName( "staff_profile_report_v0");
		userRequest.setEntityType("staff");
		*/
		userRequest.setFormName(request.getParameter("formName"));
		userRequest.setEntityType(request.getParameter("entitytype"));


		userRequest.setEntityId(request.getParameter("entityId"));
		//System.out.println("entityId : " +userRequest.getEntityId());
		String formId = "1";
		userRequest.setFormId(formId);
		userRequest.setFormType( "0" );
		userRequest.setDocumentId( request.getParameter("documentId") );
		String action = request.getParameter("action");
		userRequest.setAction( action );

		userRequest.setDocumentType( request.getParameter("SPF") );


		userRequest.setAmmendedYesNo( request.getParameter( "amendYN" ) );
		if ( isValid(userRequest.getDocumentId()) && Integer.parseInt(userRequest.getDocumentId()) > 0){
			DocumentInfo dInfo = null;
			Connection connect= null;
			ConnectDB conObj=new ConnectDB();
			connect = conObj.getMysqlConnection();
			dInfo = HtmlTemplateHelper.getEntityDocumentInfo( userRequest.getDocumentId(), connect);
			if ( dInfo != null ) {
				userRequest.setDocumentType( dInfo.getDocument_type() );
				userRequest.setFormName( dInfo.getFormName() );
				userRequest.setEntityId(dInfo.getEntityId() );
				userRequest.setFormType( dInfo.getForm_type() );
				userRequest.setEntityType(dInfo.getEntity_type());
				userRequest.setNumber(dInfo.getNumber());
				userRequest.setAmmendedDocumentId(""+dInfo.getAmended_document_id());
				userRequest.setApprovedYesNo(dInfo.getApproved_yn());
				userRequest.setStatusId(dInfo.getStatusId());
				userRequest.setAmmendedYesNo(dInfo.getAmended_yesno());
				userRequest.setAddendumDocumentId(dInfo.getAddendum_document_id());


			}
			connect.close();

		}
		//userRequest.setSubmitButton( getSubmitButtonValue ( request ) );
		userRequest.setAddendumYesNo( request.getParameter( "addendumYN" ) );
		userRequest.setParentDocumentId( request.getParameter( "parentDocId" ) );

		//userRequest.setUserlogin("admin"); commented on 07-05-2011 by Rajitha
		userRequest.setUserlogin(request.getUserPrincipal().getName());


		//Get the question and answer details of the form...
		userRequest.setQuestionAnswerMap( getFormQuestionAnswerFromSession( request, userRequest.getFormName() ) );
		String userDeptId = "";
		userDeptId = request.getParameter("user_dept_id");
		userRequest.setUserDepartmentId(userDeptId);
		userRequest.setAnchor( request.getParameter("form_anchor") );
		userRequest.setSpecialityDesc(request.getParameter("specialityDesc"));
		userRequest.setFormTitle(request.getParameter("docName"));
		/*=================================*/
		//System.out.println("========editwithNewDocID:"+request.getParameter("editwithNewDocID"));
		//userRequest.seteditwithNewDocID(request.getParameter("editwithNewDocID"));
		/*=================== end ===================*/
		return userRequest;

	}




	/**
	 * @param request
	 * @param formName
	 * @return
	 */
	private HashMap getFormQuestionAnswerFromSession ( HttpServletRequest request, String formName ) {

		//qaMap is a hashtable of questions and answers. Key is question, value is answer.
		HashMap qaMap = new HashMap();

		HttpSession session = request.getSession(true);

		//to make the key more unique
		String sessionKey = "myQuestions" + formName;
		ArrayList myQues =(ArrayList) session.getAttribute(sessionKey);
		if ( myQues == null ) return null;

		StringBuffer answer = null;

		Iterator it = myQues.iterator();
		while (it.hasNext()){
			String question = (String) it.next();
			//String answer = request.getParameter(question);
			answer=new StringBuffer();
			String[] answerValues = request.getParameterValues(question);

			if (answerValues != null) {
				int cnt = answerValues.length;

				for(int i=0; i < cnt; i++){

					String tmpAnswerString = answerValues[i];

					if ( cnt > 1 ) {
						if ( tmpAnswerString.trim().equalsIgnoreCase( "," ) ) tmpAnswerString = "";
						if ( tmpAnswerString.endsWith( "," ) ) {
							tmpAnswerString = tmpAnswerString.substring( 0, tmpAnswerString.length() - 1);
						}
					}

					answer = answer.append(tmpAnswerString).append(",");

				}//end for.

				answer.deleteCharAt(answer.length()-1);
			}else{
				answer.append("");
			}//end if.

			//put the question and its answer to an array list.
			qaMap.put(question, answer.toString());

		}//end while.

		return qaMap;

	}


	/**
	 * API added for PMT#6159 : Integration of jasper report to clinical form.
	 * This API retrieves the document status message to be displayed on the header part of the report
	 * @param userRequest
	 * @return
	 */
	private String getDocumentStatusMsg(RequestVariables userRequest) {

		String ammendedDocId = userRequest.getAmmendedDocumentId();
		String approvedYN = userRequest.getApprovedYesNo();
		String statusMessage = "";

		if( ammendedDocId != null && Integer.parseInt(ammendedDocId)>0 ){
			statusMessage = "Provisional - Interim - DO NOT PRINT";
		}else if(approvedYN != null && approvedYN.equals("0") && FacultyProfileConstants.CDOC_DOCUMENT_FROZEN != userRequest.getStatusId()){
			statusMessage = "D R A F T - C O P Y";
		}

		return statusMessage;

	}

	//API to show the error message
	public void showErrorMessage ( HttpServletResponse response,String message ) {

		String htmlstring = "Info: " + message + "";
		try {
			response.getWriter().println( htmlstring );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/*==============Print function 06-12-2010 ===================*/
	/**
	 * @param request
	 * @param response
	 * @param userRequest
	 */
	private void showPrintableReport(HttpServletRequest request, HttpServletResponse response, RequestVariables userRequest){
		String documentId = userRequest.getDocumentId();
		if ( documentId == null || documentId.trim().length() < 1 ) {
			HtmlTemplateHelper.showErrorMessage( response,"No document available for print..." );
			return;
		}
		try {
			if (HtmlTemplateHelper.isEmpty(userRequest.getDocumentId()) && Integer.parseInt(userRequest.getDocumentId()) < 1 ){
				HtmlTemplateHelper.showErrorMessage( response,"Empty result document information..." );
				return;
			}
			request.getSession().setAttribute("ACTION", userRequest.getAction());
		   	showHtmlPrintableReport(request, response, userRequest );
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}


	public void showHtmlPrintableReport(HttpServletRequest request, HttpServletResponse response, RequestVariables userRequest) throws Exception{
	    ArrayList returnList = new ArrayList();
		returnList =HtmlTemplateHelper.getXMLString( request,  response,  userRequest);
		String key = "myQuestions" + userRequest.getFormName();
		HttpSession session = request.getSession();
		session.setAttribute(key, returnList.get(0));
		response.setContentType("application/xml");
		response.getWriter().println(returnList.get(1));
	}
//============================= END ======================================

	public static ArrayList getTableData(long documetId,String entityID,String formName,String entity_type)
	{
		Connection con =  null;
		ArrayList dataArray=new ArrayList();
		try  {
			ConnectDB conObj=new ConnectDB();
			con=conObj.getMysqlConnection();
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();

			String listedFields="";
			ResultSet rs_listedFields = st2.executeQuery("SELECT CODE FROM staff_profile_report_v0_itemtypes WHERE NAME='"+ entity_type + "_childform'");
			while(rs_listedFields.next()) {
				listedFields=rs_listedFields.getString("CODE");
			}
			dataArray.add ("'"+listedFields+"'");
			/* commented on 23-05-2011
			listedFields="'" + listedFields.replace(",", "','")+"'";
			ResultSet rs_items = st.executeQuery("Select * from " + formName + "_items where NAME IN(" + listedFields + ")");
			ResultSet rs_values = st1.executeQuery("Select * from " + formName + "_values where idf="+entityID + " and number=(SELECT number FROM entity_document_master WHERE document_id="+documetId + ")");
			dataArray.add("'"+documetId+"'");
			while(rs_values.next()){
				while (rs_items.next()){
					dataArray.add ("'||"+rs_values.getString(rs_items.getString("name"))+"'");
				}
			}================ END ===========*/
			//System.out.println("Data Array :"+dataArray);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				//System.out.println("conn.close()");
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		return dataArray;
	}

	private void show_messge(HttpServletResponse response,String msg) throws IOException{
		StringBuffer str = new StringBuffer (10000);
		str.append("<html>");
		str.append("<head><script>");
		str.append("alert(\""+msg+"\");");
		str.append("</script></head>");
		str.append("</html>");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(str);
		System.out.println(str);
	}
	
}//StaffProfileServlet...
