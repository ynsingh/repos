/*
 * Created on Jun 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.erp.nfes;


import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/*=============06-12-2010 */
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * @author ahis
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HtmlTemplateHelper implements StaffProfileConstants {

	/**
	 * @param patientId
	 * @param formName
	 * @param number
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	static ArrayList getFormContent(Connection conn, String patientId, String formName, String number,String userDeptId) throws  SQLException {
		ArrayList queReport = null;
		QuestionsVO myQuestVO = null;
		String name, itemtype, ifBchControl = "N";

		StringBuffer actionStr = null;
		String sql = getAimsDisplayStringSql(formName);

		String sqlstr = "";
			//createConnectionIfNotExists();

			try {

					if (conn !=  null){
						//create statement.
						Statement st = conn.createStatement();
						//get the result set of the sql.
						ResultSet rs = st.executeQuery(sql);
						//initialize the array list.
						queReport = new ArrayList();
						while(rs.next()){
							//get the items to VO .
							myQuestVO = new QuestionsVO();
							name = rs.getString("name");
							myQuestVO.setName(name);
							myQuestVO.setNumber(rs.getInt("number"));
							myQuestVO.setDescription(rs.getString("description"));
							myQuestVO.setCreator(rs.getString("creator"));
							myQuestVO.setTime(rs.getString("time"));
							myQuestVO.setPrompt(rs.getString("prompt"));
							itemtype = rs.getString("itemtype");
							myQuestVO.setItemtype(itemtype);
							ifBchControl = (itemtype.trim().equals("bchcontrol") ? "Y" : (ifBchControl.trim().equals("Y") ? "Y" : "N"));
							myQuestVO.setPrioritem(rs.getString("prioritem"));
							myQuestVO.setNextitem(rs.getString("nextitem"));
							//get the item value from the table.
							sqlstr = "select " + name + " from " + formName + "_values where idf='" + patientId + "' and number=" + number;

							Statement sst = conn.createStatement();
							ResultSet rss = sst.executeQuery(sqlstr);

							if(rss.next()){

								//07-01-2011
								if (rss.getString(1)!=null){
									myQuestVO.setValue(rss.getString(1));
								}else{
									myQuestVO.setValue("");
								}

								actionStr = new StringBuffer();

								//for manipulating entries from a pick one combo box.
								actionStr = getValueIfPickControl(conn, formName,name,itemtype,rss.getString(1),userDeptId);

								actionStr = null;
								actionStr = new StringBuffer(sqlstr.length() + 100);
								actionStr.append("\n" + sqlstr + "\n");

								myQuestVO.setAction(actionStr.toString());

							}//end if.

							rss.close();

							//add to the array list.
							queReport.add(myQuestVO);
							}//end while.
							// close result set.
							rs.close();

							//if any bchcontrol is present in the form (?).
							if(ifBchControl.trim().equals("Y")){
								queReport = getValuesIfBchControl(conn, queReport, patientId, formName, number);
							}//end if.

							}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		//return the array list containing the report data.
		return queReport;
	}




		/**
		 * public StringBuffer getValueIfPickControl(....)
		 * Function to get the value for a pick controls Pickone, Pickmany, Pickone_radio
		 * @param formName
		 * @param name
		 * @param itemtype
		 * @param valuestring
		 * @return String buffer
		 * @throws Exception
		 */
		  private static StringBuffer getValueIfPickControl(Connection conn, String formName, String name, String itemtype, String valuestring,String userDeptId) throws  SQLException {
			StringBuffer strBuf = new StringBuffer (10000);
			strBuf.append("\n");
			String qryString = "select description, action, choice, code from " + formName + "_itemtypes where name = '" + itemtype + "'";
			//createConnectionIfNotExists();

			try {

				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(qryString);
				rs.next();
				String action = rs.getString("action");
				String choice = rs.getString("choice");
				String code = rs.getString("code");

				if (action.trim().equals("pickone") || action.trim().equals("pickmany") || action.trim().equals("pickone_radio")){
//					String choice = rs.getString("choice");
//					String code = rs.getString("code");

					String ch[] = choice.split(",");
					String co[] = code.split(",");

					int iter = (co.length >= ch.length ? ch.length : co.length);
					int get = 0;
					/*int actionInt = (action.trim().equals("pickone") ? 1 :
												(action.trim().equals("pickmany") ? 2 :
												(action.trim().equals("pickone_radio") ? 3 : 0)));
					22-01-2011*/
					int actionInt = (action.trim().equals("pickone") ? 1 :
						(action.trim().equals("pickmany") ? 2 :
						(action.trim().equals("pickone_radio") ? 3 :
						(action.trim().equals("pickone_radio_choose") ? 3 : 0))));
					switch (actionInt) {

						case 1 :
						case 3 :{
							if ( valuestring.startsWith( "," ) ) {
								StringBuffer sb = new StringBuffer();
								sb.append( valuestring );
								valuestring = sb.deleteCharAt( 0 ).toString();
							}
							if ( valuestring.endsWith( "," )) {
								StringBuffer sb = new StringBuffer();
								sb.append( valuestring );
								valuestring = sb.deleteCharAt( valuestring.length()-1 ).toString();
							}
							for (int cnt = 0; cnt < iter; cnt++){

								String col = co[cnt];
								get = (col.equals(valuestring) ? cnt : get);
							}
							strBuf.append(ch[get]);
							break;

						}//end case.

						case 2 :{

							String valStr[] = valuestring.split(",");
							int valCnt = valStr.length;

							for (int cnt = 0; cnt < iter; cnt++){

								String col = co[cnt];
								for(int vCnt = 0; vCnt < valCnt; vCnt++){
									String vGet = valStr[vCnt];
									if(col.equals(vGet)) {
										strBuf.append(ch[ Integer.parseInt( vGet ) ] + " ; ");
									}

								}//end for.

							}//end for.
							break;

						}//end case.

						case 0 :
						default: break;

					}//end switch.

				}else{
					strBuf.append(valuestring);
				}
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return strBuf;
		  }//end function.


		  /**
			 * getValuesIfBchControl(....)
			 * Function to get the bch control values to array list.
			 * @param queReport
			 * @param patientId
			 * @param formName
			 * @param number
			 * @return queReport
			 * @throws SQLException
			 * @throws HisException
			 */
				private static ArrayList getValuesIfBchControl( Connection conn,ArrayList queReport, String patientId, String formName, String number) throws SQLException{
					QuestionsVO myQuestVO = null;
					String sqlString = "select name from " + formName + "_items where itemtype = 'bchcontrol'";


					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sqlString);

					while(rs.next()){
						String conName = rs.getString("name");

						for(int cnt = 1; cnt < 6; cnt++){
							String selName = conName + cnt;
							String sqlQryString = "select bm.disease_category as description " +
													"from bchcode_master bm, " + formName + "_values tmp " +
													"where bm.disease_category_code = tmp." + selName + " and idf = '" + patientId + "'";

							Statement stt = conn.createStatement();
							ResultSet rss = stt.executeQuery(sqlQryString);

							myQuestVO = new QuestionsVO();
							if(rss.next()){
								myQuestVO.setName(selName + "_desc");
								myQuestVO.setNumber(0);
								myQuestVO.setDescription(selName + " description");
								myQuestVO.setCreator("");
								myQuestVO.setTime("");
								myQuestVO.setPrompt(selName + " description");
								myQuestVO.setItemtype("blank");
								myQuestVO.setPrioritem("");
								myQuestVO.setNextitem("");
								myQuestVO.setAction(rss.getString("description"));
							}else{
								myQuestVO.setName(selName + "_desc");
								myQuestVO.setNumber(0);
								myQuestVO.setDescription(selName + " description");
								myQuestVO.setCreator("");
								myQuestVO.setTime("");
								myQuestVO.setPrompt(selName + " description");
								myQuestVO.setItemtype("blank");
								myQuestVO.setPrioritem("");
								myQuestVO.setNextitem("");
								myQuestVO.setAction("");
							}//end if..else.

							//add to arraylist.
							queReport.add(myQuestVO);
							stt = null;
							rss = null;
						}//end for.

					}//end while.

					return queReport;
				}

				/**
				 * @param patientId
				 * @param formName
				 * @return
				 * @throws HisException
				 * @throws SQLException
				 */
				//Merging : This function has changes merged from 4.0.7 aims branch for PM#2420
				//static ArrayList getPrintFormElements(Connection hisconn,Connection conn, PersistenceManager pm, int patientId, int visitId, String formName,String parentDocumentId,String userDeptId) throws HisException, SQLException {

				static ArrayList getPrintFormElements(Connection conn, RequestParam userRequest) throws  SQLException {

					String ptid, name, itemtype, str;
					Integer o1;
					ArrayList arrMyQuest = null;
					QuestionsVO myQuestVO = null;

					StringBuffer actionStr = new StringBuffer (10000);


						try {

							if(conn != null){
								String sql = getAimsDisplayStringSql( userRequest.getFormName() );
								Statement stmt = conn.createStatement();
								ResultSet rs = stmt.executeQuery(sql);
								arrMyQuest = new ArrayList();
								while(rs.next()){
									myQuestVO = new QuestionsVO();
									name = rs.getString("name");
									myQuestVO.setName(name);
									myQuestVO.setNumber(rs.getInt("number"));
									myQuestVO.setDescription(rs.getString("description"));
									myQuestVO.setCreator(rs.getString("creator"));
									myQuestVO.setTime(rs.getString("time"));
									myQuestVO.setPrompt(rs.getString("prompt"));
									itemtype = rs.getString("itemtype");
									myQuestVO.setItemtype(itemtype);
									myQuestVO.setPrioritem(rs.getString("prioritem"));
									myQuestVO.setNextitem(rs.getString("nextitem"));

									actionStr = getActionHtmlString(conn,
																	userRequest.getFormName(),
																	name, itemtype,
																	"", userRequest.getEntityId(),
																	String.valueOf(rs.getInt("number")),
																	userRequest.getParentDocumentId(),
																	userRequest.getUserDepartmentId());
									myQuestVO.setAction(actionStr.toString());
									arrMyQuest.add(myQuestVO);
								}
								rs.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					return arrMyQuest;
				}


				/**
				 * StringBuffer getActionHtmlString(....)
				 * Function to get the HTML for the OIO form controls
				 * @param formName
				 * @param name
				 * @param itemtype
				 * @param valuestring
				 * @return HTML string buffer
				 * @throws HisException
				 * @throws RemoteException
				 */
				//Merging : This function has changes merged from 4.0.7 aims branch for PM#2420
					private static StringBuffer getActionHtmlString(
							Connection conn,
							String formName,
							String name,
							String itemtype,
							String valuestring,
							String entityId,
							String number,
							String parentDocumentId,
							String userDeptId)
					throws SQLException {

						String action, choice, code, qryString;
						StringBuffer htmlString = new StringBuffer (10000);

						//Connection cn = null;
						qryString = "select description, action, choice, code from " + formName + "_itemtypes where name = '" + itemtype + "'";
						htmlString.append("\n");

								if (conn != null){
									try {

										Statement state = conn.createStatement();

										ResultSet rss = state.executeQuery(qryString);
										rss.next();
										action = rss.getString("action");
										choice = rss.getString("choice");
										code = rss.getString("code");
										htmlString.append("");//Instantiate the string buffer.
										//get the object data before rendering.
										if (action.equalsIgnoreCase("text_area")){
											TextArea actionObj = new TextArea();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring);

										//	htmlString = null;
										//	htmlString = new StringBuffer(10000);
										//	htmlString.append("" );
										}else if (action.equalsIgnoreCase("except") || action.equalsIgnoreCase("mustbe")){
											Except actionObj = new Except();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("show_text")){
											ShowText actionObj = new ShowText();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("pickone")){
											PickOne actionObj = new PickOne();
											htmlString = actionObj.getObjectHtml(name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("rich_editcontrol")){
											RichEditControl actionObj = new RichEditControl();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring);
										}else if(action.equalsIgnoreCase("rich_editbox")){
											RichEditBox actionObj = new RichEditBox();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("pickmany")){
											PickMany actionObj = new PickMany();
											htmlString = actionObj.getObjectHtml(name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("pickone_radio")){
											PickRadio actionObj = new PickRadio();
											htmlString = actionObj.getObjectHtml(name, action, choice, code, valuestring);
										}else if (action.equalsIgnoreCase("calendar")){
											Calendar actionObj = new Calendar();
											htmlString = actionObj.getObjectHtml(name, action, choice, code, valuestring);
										}else if(action.equalsIgnoreCase("addendum_oio_text") ){
											TextArea actionObj = new TextArea();
											htmlString = actionObj.getAddendumObject( name, action, choice, code, valuestring,parentDocumentId);
										}/* ========== File Upload 07-12-2010===========*/
										else if(action.equalsIgnoreCase("file_upload") ){
											UploadFile actionObj = new UploadFile();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring,entityId);
										}/* ========== Detail Form 12-01-2011===========*/
										else if(action.equalsIgnoreCase("detail_form") ){
											DetailForm actionObj = new DetailForm();
											htmlString = actionObj.getObjectHtml( name, action, choice, code, valuestring,entityId);
											}
										/*========== Akhil ===========*/
										else if(action.equalsIgnoreCase("pickone_radio_choose") ){
											PickRadio actionObj = new PickRadio();
											htmlString = actionObj.getObjectHtml01(name, action, choice, code, valuestring);
										}//Akhil End
										//25-01-2011
										else if(action.equalsIgnoreCase("pickone_master") ){
											//System.out.println("pickone_master");
											PickOneMaster actionObj = new PickOneMaster();
											htmlString = actionObj.getObjectHtml(name, action, choice, code, valuestring);
										}//end 
										//15-03-2011
										else if(action.equalsIgnoreCase("pickone_table") ){
											PickOneMaster actionObj = new PickOneMaster();
											htmlString = actionObj.getObjectHtml01(name, action, choice, code, valuestring, entityId);
										}//end of addition 15-03-2011
										else{
											htmlString.append(valuestring);//as general string.
										}//end if.

										rss.close();

								} catch (SQLException e) {
									e.printStackTrace();
								}
							}

						return htmlString;
					}				//end function.




	/**
	 * @param patientId
	 * @param formName
	 * @param number
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	//Merging : This function has changes merged from 4.0.7 aims branch for PM#2420

	//public static ArrayList getEditableFormElements(Connection hisconn,Connection conn, PersistenceManager pm, String patientId, String visitId, String formName, String number,String userDeptId) throws HisException, SQLException {
	//Commented SN
	public static ArrayList getEditableFormElements(Connection conn,RequestParam userRequest,String number) throws SQLException {
		//System.out.println("========getEditableFormElements");
		ArrayList queReport = null;
		QuestionsVO myQuestVO = null;
		String name, itemtype, nVal = null;
		StringBuffer actionStr = new StringBuffer (10000);
		String sql = getAimsDisplayStringSql(userRequest.getFormName());
		String sqlstr = "";

		try {
			if (conn !=  null){
				//create statement
				Statement st = conn.createStatement();
				//get the result set of the sql
				ResultSet rs = st.executeQuery(sql);
				//initialize the array list

				String valstr = getReportDataFromValuesQuery ( userRequest.getFormName(), userRequest.getEntityId(), number );
				Statement vst = conn.createStatement();
				ResultSet valuesRs = vst.executeQuery( valstr );
				if ( valuesRs != null ) {
					valuesRs.setFetchDirection( ResultSet.FETCH_REVERSE );
					valuesRs.next();
				}

				queReport = new ArrayList();
				while(rs.next()){
					//get the items to VO
					myQuestVO = new QuestionsVO();
					name = rs.getString("name");
					myQuestVO.setName(name);
					myQuestVO.setNumber(rs.getInt("number"));
					myQuestVO.setDescription(rs.getString("description"));
					myQuestVO.setCreator(rs.getString("creator"));
					myQuestVO.setTime(rs.getString("time"));
					myQuestVO.setPrompt(rs.getString("prompt"));
					itemtype = rs.getString("itemtype");
					myQuestVO.setItemtype(itemtype);
					myQuestVO.setPrioritem(rs.getString("prioritem"));
					myQuestVO.setNextitem(rs.getString("nextitem"));

					//get the item value from the table
					nVal = valuesRs.getString( name );

					//actionStr = getActionHtmlString(conn, pm, formName, name, itemtype, nVal, patientId, visitId, number,"");
					//commented sn for checking parentDocId
					actionStr = getActionHtmlString(conn,
							                        userRequest.getFormName(),
							                        name, itemtype, nVal,
							                        userRequest.getEntityId(),
							                        number,"",
							                        userRequest.getUserDepartmentId());


					myQuestVO.setAction(actionStr.toString());

					//add to the array list
					queReport.add(myQuestVO);
				}//end while
				// close result set
				rs.close();
			}//end if
		} catch (SQLException e) {

			e.printStackTrace();

		}

		//return the array list containing the report data
		return queReport;
	}
	/**
	 * @param formName
	 * @param patientId
	 * @param number
	 * @return
	 */
	public static String getReportDataFromValuesQuery ( String formName, String patientId, String number ) {

		String string = "SELECT * FROM " + formName + "_values WHERE idf=" + patientId + " AND number=" + number;

		return string;

	}
	/**
	 * getBCHvalues(....) Function ot get the bch data from the table for any
	 * patient with given patientId
	 *
	 * @param name
	 * @param patientId
	 * @param number
	 * @param formName
	 * @return bchVals
	 * @throws SQLException
	 * @throws HisException
	 * @throws SQLException
	 */
		public static HashMap getBCHvalues(String name, String entityId, String number,
			String formName) throws SQLException  {

		HashMap bchVals = null;

		Connection connect = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			ConnectDB conObj=new ConnectDB();
			connect = conObj.getMysqlConnection();

			bchVals = new HashMap();
			ArrayList bchValueOne = new ArrayList();
			ArrayList bchValueTwo = new ArrayList();

			for (int cnt = 1; cnt < 6; cnt++) {
				String nameStr = name + cnt;
				String sqlQry = "select tmp." + nameStr
						+ ", bm.disease_category " + "from " + formName
						+ "_values tmp, bchcode_master bm " + "where idf = '"
						+ entityId + "' and number = '" + number
						+ "' and bm.disease_category_code = " + nameStr;

				stmt = connect.createStatement();
				rset = stmt.executeQuery(sqlQry);

				while (rset.next()) {

					bchValueOne.add(rset.getObject(1));
					bchValueTwo.add(rset.getObject(2));

				}//end while.

				stmt = null;
				rset = null;
			}//end for.

			bchVals.put("codes", bchValueOne);
			bchVals.put("values", bchValueTwo);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			connect.close();
		}
		return bchVals;
		}

		/**
		 * String getAimsDisplayStringSql(.)
		 * Function to get a query string for all the items of a form
		 * @param formName
		 * @return String query
		 * @throws HisException
		 */
		public static String getAimsDisplayStringSql(String formName)   {
			String SqlState;
			// Modified on 23-02-2011 By Rajitha, bcoz prompt abbreviation also set in the prompt field separated with '|' symbol
			/*SqlState = "select name, prompt, number, description, creator, time, itemtype, prioritem, nextitem from " +
			  formName + "_items order by number";*/
			SqlState = "select name, SUBSTRING_INDEX(Prompt, '|',1 ) AS Prompt, SUBSTR( prompt,(INSTR(prompt,'|'))-LENGTH(prompt)) AS abbreviation, number, description, creator, time, itemtype, prioritem, nextitem from " +
			  formName + "_items order by number";
			//System.out.println("*******"+SqlState);
			return SqlState;


		}//end function.




		public static long saveOrUpdateTheDocumentData(HashMap controlMap,RequestParam requestParam,HttpServletRequest request) throws Exception {
			String tableName = requestParam.getFormName() + "_values";
			String documentId = "";
			ConnectDB conObj=new ConnectDB();
			Connection connect = conObj.getMysqlConnection();
			//System.out.println("Document ID:"+requestParam.getDocumentId());

			//if (isValid(requestParam.getDocumentId()) && Integer.parseInt(requestParam.getDocumentId())>0){
			//&& requestParam.geteditwithNewDocID()!="1"
			if (isValid(requestParam.getDocumentId()) && Integer.parseInt(requestParam.getDocumentId())>0 ){
				documentId = requestParam.getDocumentId();
				edit(documentId, tableName, controlMap, requestParam, connect);
			}else{
				long hisDocumentNumber = 0;
				//System.out.println("New Document ID");
				hisDocumentNumber = getNextSequenceNumber("EntityDocument", connect);
				documentId = String.valueOf(hisDocumentNumber);
				save(documentId, tableName, controlMap, requestParam,connect);
			}
			connect.close();
			return Long.parseLong(documentId);
		}

		private static void edit(String documentId, String tableName, HashMap valueMap,RequestParam requestParam,Connection conn ) throws SQLException {

			String priorityCon = "Priority", priorityVal = "2";
			String userLogin = requestParam.getUserlogin();
			Connection hisConnection = null;
			try {
				DocumentInfo documentInfo = null;
				documentInfo = getEntityDocumentInfo(documentId,conn);
				if (documentInfo == null)
					return;
				if ( !isValid(requestParam.getDocumentId()) )
					return;

				tableName = requestParam.getFormName() + "_values";
				//get the question answer pairs from the hash map.
				Set set = valueMap.entrySet();
				Iterator it = (Iterator) set.iterator();
				Statement st = conn.createStatement();
				String updatesql = "";

				while (it.hasNext()) {
					//get the next question answer pair.
					Map.Entry entry = (Map.Entry) it.next();
					String question = (String) entry.getKey();

					String answer = (String) entry.getValue();

					String sqlthree = "select ap.action from "
							+ requestParam.getFormName() + "_itemtypes ap, "
							+ requestParam.getFormName()
							+ "_items ai where ai.name = '" + question
							+ "' and ap.name = ai.itemtype";
					ResultSet rs = st.executeQuery(sqlthree);
					rs.next();

					String tmpAction = rs.getString(1);
						StringBuffer sbAnswer = new StringBuffer(answer);
						int index = sbAnswer.indexOf("\\");
						if(index != -1){// there is no \0 in the string
							while(index != -1){
								sbAnswer.replace(index,index+1,"\\\\");
								index = sbAnswer.indexOf("\\",index+2);
							}
							answer = sbAnswer.toString();
						}

					answer = prepareAnswerStrings(answer);

					//To set the priority values from the template.
					if (priorityCon.equals(question.trim()))
						priorityVal = answer;

					if (tmpAction.trim().equals("text_area")) {
						answer = textToHtml(answer);
					}
					else if (tmpAction.trim().equals("pickone_radio")||tmpAction.trim().equals("pickone_radio_choose")){
						if (answer!="" || answer!=null){				//	to delete comma at first
						answer=answer.substring(1);
						}
					}  /*08-12-2010 To Extract File Name in IE*/
					else if (tmpAction.trim().equals("pickone")){
						if (answer!="" || answer!=null){
							if (answer.equals("Select")){
							answer="";
							}
						}
					}
					else if (tmpAction.trim().equals("file_upload")){
						int file_name_starts_from=answer.lastIndexOf("\\");
						if (file_name_starts_from>0){
							answer=answer.substring(file_name_starts_from+1);
						}
					}
					else if (tmpAction.trim().equals("detail_form")){
						int deleted_documentid_starts_from;
						String deleted_document_ids=null;

						//System.out.println("========Answer :"+answer);

						deleted_documentid_starts_from =answer.indexOf("Deleted:",1);
						if (deleted_documentid_starts_from>0){
							deleted_document_ids=answer.substring(deleted_documentid_starts_from+8);
							//System.out.println("======Del IDs :"+ deleted_document_ids+","+deleted_documentid_starts_from);
							answer=answer.substring(0,deleted_documentid_starts_from-1);
							//System.out.println("========Answer :"+answer);
							deleteEntities(deleted_document_ids);
						}

					}
					if (it.hasNext()) {
						updatesql = updatesql + question + "='" + answer + "', ";
					} else {
						updatesql = updatesql + question + "='" + answer + "' ";
					}//end if.
				}//end while.

				String idfField = "idf";
				String sql = "UPDATE " + tableName + " SET " + updatesql
						+ "WHERE " + idfField + "=" + documentInfo.getEntityId()
						+ " and number=" + documentInfo.getNumber();

				Statement sst = conn.createStatement();
				sst.execute(sql);
				int currentDocumentStatus = requestParam.getStatusId();
				int documentStatusId = 1;

				updateReportDocumentIdMaster(conn, documentId,documentInfo.getNumber(), requestParam, documentStatusId);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 *
		 * @param conn
		 * @param documentId
		 * @param entryNumber
		 * @param requestParam
		 * @param documentStatusId
		 * @throws SQLException
		 */
		public static void updateReportDocumentIdMaster(
			Connection conn,
			String documentId,
			String entryNumber,
	//Merging Starts -- Prajeesh
	//Taken From 407 AIMS branch
			RequestParam requestParam,
			int documentStatusId)
	//End Merging
			throws Exception {

			try {
				//Commeted by Manoj S not to edit the addendum/ammendment while updating the document...
//				String amendedDocId = "",
//					addendumDocId = "",
				String 	approvedYesNo = "",
					setValue = "";

//				if (requestParam.getAmmendedYesNo() != null
//					&& requestParam.getAmmendedYesNo().trim().equals("yes")) {
//					updateAmmendmentOrAddendum(
//						conn,
//						"amended",
//						documentId,
//						requestParam.getParentDocumentId(),
//						requestParam);
//				} else if (
//					requestParam.getAddendumYesNo() != null
//						&& requestParam.getAddendumYesNo().trim().equals("yes")) {
//					updateAmmendmentOrAddendum(
//						conn,
//						"addendum",
//						documentId,
//						requestParam.getParentDocumentId(),
//						requestParam);
//				}

				if (requestParam.getSubmitButton() != null
					&& requestParam.getSubmitButton().trim().equals("" + StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF ))
					approvedYesNo = StaffProfileConstants.CDOC_DOCUMENT_APPROVED_YES;
				else
					approvedYesNo = StaffProfileConstants.CDOC_DOCUMENT_APPROVED_NO;

				StringBuffer updateQry = new StringBuffer();
				updateQry.append( "UPDATE " );
				int currentStatus = 0;
				DocumentInfo docInfo = getEntityDocumentInfo(documentId,conn);
				if(docInfo != null){
					currentStatus = docInfo.getStatusId();
				}
				int statusId = getNextDocumentStatus( requestParam.getSubmitButton(), currentStatus );
				updateQry.append(StaffProfileConstants.CDOC_ENTINTY_DOCUMENT_MASTER )
					.append( " SET status_id = " ).append( statusId ).append( "," )
					.append( setValue ).append( " approved_yesno = '" ).append( approvedYesNo )
					.append( "', last_modified_by = '").append( requestParam.getUserlogin() )
					.append( "', last_modified_date_time = NOW()" )
					.append( " WHERE document_id = ").append(requestParam.getDocumentId())
					.append( " AND entity_id = " ).append(requestParam.getEntityId());


				Statement st = conn.createStatement();
				st.execute(updateQry.toString());
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}







		private static void save(String documentId, String tableName, HashMap valueMap, RequestParam requestParam,Connection conn) throws Exception {
			String nextSeqQuery = "select coalesce((max(number) + 1),1) as newnumber from " + tableName + " where idf =" + requestParam.getEntityId();
			//System.out.println("nextSeqQuery : "+nextSeqQuery);
			Statement sst = conn.createStatement();
			ResultSet Rset = sst.executeQuery(nextSeqQuery);
			Rset.next();
			String entryNumber = Rset.getString("newnumber");
			Rset.close();

			//To set the values of the priority control from the templates.
			String priorityCon = "Priority", priorityVal = "1";

			//Get the HashMap containing all the questions and answers.
			Set set = valueMap.entrySet();
			Iterator it = (Iterator) set.iterator ();

			String sqlstrone = " ( idf, number, ";

			String sqlstrtwo = " VALUES('" + requestParam.getEntityId() + "', '" + entryNumber + "', ";

			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();

				String question = (String) entry.getKey();
				String answer = (String) entry.getValue();
				sqlstrone = sqlstrone + question;

				String sqlthree = "select ap.action from " + requestParam.getFormName() + "_itemtypes ap, " +
									requestParam.getFormName() + "_items ai where ai.name = '" + question +
									"' and ap.name = ai.itemtype";

				Rset = sst.executeQuery(sqlthree);
				Rset.next();
				String tmpAction = Rset.getString(1);
				StringBuffer sbAnswer = new StringBuffer(answer);
				int index = sbAnswer.indexOf("\\");
				if(index != -1){
					while(index != -1){
						sbAnswer.replace(index,index+1,"\\\\");
						index = sbAnswer.indexOf("\\",index+2);
					}
					answer = sbAnswer.toString();
				}

				answer = prepareAnswerStrings(answer);

				if ( priorityCon.equals( question.trim() ) ) priorityVal = answer;

				if (tmpAction.trim().equals("text_area")){
					answer = textToHtml(answer);
				}
				else if (tmpAction.trim().equals("pickone_radio")||tmpAction.trim().equals("pickone_radio_choose")){
					if (answer!="" || answer!=null){
					answer=answer.substring(1);
					}
				}
				else if (tmpAction.trim().equals("pickone")){
					if (answer!="" || answer!=null){
						if (answer.equals("Select")){
						answer="";
						}
					}
				}
				/*08-12-2010 To Extract File Name in IE*/
				else if (tmpAction.trim().equals("file_upload")){
					int file_name_starts_from=answer.lastIndexOf("\\");
					if (file_name_starts_from>0){
						answer=answer.substring(file_name_starts_from+1);
					}
				}
				else if (tmpAction.trim().equals("detail_form")){
					int deleted_documentid_starts_from;
					String deleted_document_ids=null;

					//System.out.println("========Answer :"+answer);

					deleted_documentid_starts_from =answer.indexOf("Deleted:",1);
					if (deleted_documentid_starts_from>0){
						deleted_document_ids=answer.substring(deleted_documentid_starts_from+8);
						//System.out.println("======Del IDs :"+ deleted_document_ids+","+deleted_documentid_starts_from);
						answer=answer.substring(0,deleted_documentid_starts_from-1);
					//	System.out.println("========Answer :"+answer);
						deleteEntities(deleted_document_ids);
					}
				}
				sqlstrtwo = sqlstrtwo + "'" + answer + "'";

				if (it.hasNext()){
					sqlstrone = sqlstrone + ", ";
					sqlstrtwo = sqlstrtwo + ", ";
				}else{
					sqlstrone = sqlstrone + ") ";
					sqlstrtwo = sqlstrtwo + ") ";
				}//end if

			}// end while

			//prepare and execute the query to insert the value to the table
			String sql = "INSERT INTO " + tableName + sqlstrone + sqlstrtwo;
			sst.execute(sql);
			long entityDocumentMasterId =getNextSequenceNumber( "ENTITY_DOCUMENT_MASTER", conn );
			insertIntoEntityDocumentMaster ( conn, entityDocumentMasterId, documentId, entryNumber, requestParam );


		}

		public static void insertIntoEntityDocumentMaster(
			Connection conn,
			long entityDocumentMasterId,
			String documentId,
			String entryNumber,
			RequestParam requestParam)
			throws Exception, SQLException {

			String approvedYesNo = "";
			String fields = "(id, entity_id, entity_type,";

			try {
				String values =
					"(" + entityDocumentMasterId
						+ ","
						+ requestParam.getEntityId()
						+ ",'"
						+ requestParam.getEntityType()
						+ "',";

				if (requestParam.getSubmitButton().trim().equals("" + StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF))
					approvedYesNo = StaffProfileConstants.CDOC_DOCUMENT_APPROVED_YES;
				else
					approvedYesNo = StaffProfileConstants.CDOC_DOCUMENT_APPROVED_NO;

				String form_id = getFormIdFromFormName(conn, requestParam.getFormName());

				int amendedYesNo=0;
				String amendedDocumentId="0";

				int currentStatus = 0;
				DocumentInfo docInfo = getEntityDocumentInfo(documentId,conn);
				if(docInfo != null){
					currentStatus = docInfo.getStatusId();
				}
				int statusId = getNextDocumentStatus( requestParam.getSubmitButton(), currentStatus );

				StringBuffer insertQry = new StringBuffer();
				insertQry.append( "INSERT INTO " ).append( StaffProfileConstants.CDOC_ENTINTY_DOCUMENT_MASTER )
					.append( " ").append( fields )
					.append( " form_id, document_id, number, amended_yesno, amended_document_id, " )
					.append( "addendum_yesno, addendum_document_id, approved_yesno, active_yesno, status_id, " )
					.append( "last_modified_by, last_modified_date_time, created_by, created_date_time) " )
					.append( "VALUES " ).append( values ).append( form_id ).append( "," ).append( documentId ).append( "," )
					.append( entryNumber ).append( "," ).append( amendedYesNo ).append( "," ).append( amendedDocumentId )
					.append( ",0,0,'" ).append( approvedYesNo ).append( "',1," ).append( statusId )
					.append( ",'" ).append( requestParam.getUserlogin() ).append( "',NOW(),'" )
					.append( requestParam.getUserlogin() ).append( "',NOW() )" );

				Statement st = conn.createStatement();
				st.execute(insertQry.toString());


			} catch ( SQLException e ) {
				e.printStackTrace();
				throw new Exception ( "ERROR: SQLException in ClinicalDocHelper : " + e.getMessage() );
			}

		}
		/**
		 * @param conn
		 * @param formName
		 * @return
		 * @throws HisHelperException
		 * @throws SQLException
		 */
		public static String getFormIdFromFormName ( Connection conn, String formName ) throws Exception, SQLException {

			String formId = null;
			Statement st  = null;
			ResultSet rs  = null;


			StringBuffer sqlString = new StringBuffer();
			sqlString.append("SELECT id FROM " ).append( StaffProfileConstants.CDOC_FORM_MASTER )
			         .append( " WHERE CONCAT(form_name,\"_\",version)= '" ).append( formName )
			         .append( "' OR form_name='" ).append( formName ).append( "'" );

			try {

				st = conn.createStatement();
				rs = st.executeQuery( sqlString.toString() );

				if ( rs.next() ) {
					formId = rs.getString( "id" );
				}

			} catch ( SQLException e ) {
				e.printStackTrace();
			}finally{
				closeSqlResources( st, rs );
			}

			return formId;

		}

		/**
		 *
		 * @param string
		 * @return
		 */
		public static boolean isEmpty ( String string ) {

			if ( string == null ) return true;
			if ( string.trim().equals( "" ) ) return true;
			if ( string.trim().length() < 1 ) return true;

			return false;
		}

		public static DocumentInfo getEntityDocumentInfo(
			String documentId,
			Connection conn)
			throws Exception, SQLException  {
			DocumentInfo documentInfo = new DocumentInfo();

			if ( isEmpty( documentId ) ) {
				return documentInfo;
			}

			StringBuffer sqlQry = new StringBuffer();
			sqlQry.append( "SELECT rm.document_id, rm.entity_id,rm.entity_type, rm.form_id, rm.number, " )
			      .append( " rm.amended_yesno, rm.amended_document_id, rm.addendum_yesno, rm.status_id," )
			      .append( "rm.addendum_document_id, rm.approved_yesno, rm.last_modified_by, rm.last_modified_date_time, ")
			      .append( "rm.created_by, rm.created_date_time, fm.form_name, fm.version, fm.description, fm.title, " )
			      .append( "fm.document_type_id, dm.document_type, fm.form_type, fm.show_draft_copy_yesno, fm.iso_document_number, dm.description AS document_type_description " )
			      .append( "FROM " ).append( StaffProfileConstants.CDOC_ENTINTY_DOCUMENT_MASTER ).append( " as rm, " )
			      .append( StaffProfileConstants.CDOC_FORM_MASTER ).append( " as fm, " )
			      .append( StaffProfileConstants.CDOC_DOCUMENT_TYPE_MASTER ).append( " as dm " )
			      .append( "WHERE rm.document_id = " ).append( documentId )
			      .append( " AND rm.active_yesno = 1 AND fm.id = rm.form_id AND dm.id = fm.document_type_id" );


			Statement st =null;
			ResultSet rs = null;

			try {
	    		st = conn.createStatement();
	    		rs = st.executeQuery(sqlQry.toString());

	    		if ( rs.next() ) {
	    			//System.out.println("*****************************************");
	    			String formType = rs.getString("form_type");
	    			String formName = rs.getString("form_name");
	    			String version = rs.getString("version");
    				formName = rs.getString("form_name") + "_" + rs.getString("version");
	    			documentInfo.setFormName( formName );
	    			documentInfo.setAddendum_document_id(rs.getInt("addendum_document_id"));
	    			documentInfo.setAddendum_yesno(rs.getString("addendum_yesno"));
	    			documentInfo.setAmended_document_id(rs.getInt("amended_document_id"));
	    			documentInfo.setAmended_yesno(rs.getString("amended_yesno"));
	    			documentInfo.setApproved_yn(rs.getString("approved_yesno"));	    			
	    			documentInfo.setDescription(rs.getString("description"));
	    			documentInfo.setTitle(rs.getString("title"));
	    			documentInfo.setShow_draft_copy_yesno( rs.getInt( "fm.show_draft_copy_yesno" ) );
	    			documentInfo.setDocument_type(rs.getString("document_type"));
	    			documentInfo.setDocument_type_id(rs.getString("document_type_id"));
	    			documentInfo.setDocumentId(rs.getString("document_id"));
	    			documentInfo.setStatusId(rs.getInt("status_id"));
	    			documentInfo.setVersion( version );
	    			documentInfo.setId(rs.getString("form_id"));
	    			documentInfo.setNumber(rs.getString("number"));
	    			documentInfo.setEntity_type(rs.getString("entity_type"));
	    			documentInfo.setEntityId(rs.getString("entity_id"));
	    			documentInfo.setVersion(rs.getString("version"));
	    			documentInfo.setIso_document_number(rs.getString("iso_document_number"));
	    			documentInfo.setDocument_type_description( rs.getString("document_type_description" ) );
	    			documentInfo.setCreated_by(rs.getString("created_by"));
	    			documentInfo.setCreated_date_time(rs.getTimestamp("created_date_time"));
	    			documentInfo.setLast_modified_by(rs.getString("last_modified_by"));
	    			documentInfo.setLast_modified_date_time(rs.getTimestamp("last_modified_date_time"));
	    		}
	        }catch( SQLException e ){
	        	e.printStackTrace();
	        }finally {
	        	closeSqlResources( st,rs );

	        }


			return documentInfo;
		}
		/**
		 *
		 * @param stmt
		 * @param rs
		 * @throws HisHelperException
		 */
		public static void closeSqlResources( Statement stmt, ResultSet rs ) throws Exception {
			try {
				if ( rs != null )   { rs.close();   rs = null;   }
				if ( stmt != null ) { stmt.close(); stmt = null; }
			}catch ( SQLException e ) {
				e.printStackTrace();
			}
		}

		/**
		 * This function will return the status of the clinical form like 'New, Dictated etc
		 * @param submit
		 * @param privStatus
		 * @return
		 * @throws HisHelperException
		 */
		public static int getNextDocumentStatus(String submit, int privStatus) throws Exception{
			int returnStatus = 0;
			if ( privStatus == 0 ) {
				if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_DICTATED ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_DICTATED;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL;
				} else if ( submit.trim().equals( "7" ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_DICTATED;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_PROVISIONAL ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_PROVISIONAL;
				}
			} else {
				if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_DICTATED ) ) {
					if ( privStatus == StaffProfileConstants.CDOC_DOCUMENT_DICTATED ) {
						returnStatus = StaffProfileConstants.CDOC_DOCUMENT_DICTATED;
					} else if ( privStatus == StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION ) {
						returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION;
					} else if ( privStatus == StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL ) {
						returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL;
					}
				} else if ( submit.trim().equals( "7" ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_DICTATED;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_SIGNED_OFF;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_OPEN_FOR_APPROVAL;
				} else if ( submit.trim().equals( "" + StaffProfileConstants.CDOC_DOCUMENT_PROVISIONAL ) ) {
					returnStatus = StaffProfileConstants.CDOC_DOCUMENT_PROVISIONAL;
				}
			}

			return returnStatus;
		}

			/**
			 * String prepareAnswerStrings(.)
			 * Function to modify the answer string
			 * @param answer
			 * @return answer
			 * @throws Exception
			 */
				public static String prepareAnswerStrings(String answer) {
					String modifiedAnswer = "", cumStr = "";
					if(answer.trim().equals("'")){
						answer ="\\" + "\'";
					}
					else{
						StringTokenizer st = new StringTokenizer(answer,"'");
						if (answer != null && !answer.trim().equals("")){
							while(st.hasMoreTokens())
							{
								String tmpTocken = st.nextToken();

								modifiedAnswer = modifiedAnswer + tmpTocken + "\\" + "\'";
								cumStr = cumStr + tmpTocken + "'";
							}
							if(cumStr.trim().equals(answer)){
								modifiedAnswer = modifiedAnswer + "\\" + "\'";
							}
							answer = modifiedAnswer.substring(0,modifiedAnswer.length() - 2);
						}
					}
					return answer;
				}//end function.

				/**
				 * String textToHtml(.)
				 * Function to convert a text ot html
				 * @param inputStr
				 * @return htmlStr
				 */
				public static String textToHtml(String inputStr) {
					if (inputStr.trim().length() > 0) {
						inputStr = inputStr.replaceAll("\r\n", "<br/>");
						inputStr = inputStr.replaceAll("\t", "&nbsp;");
						//			inputStr = inputStr.replaceAll("<","&lt;");
						//			inputStr = inputStr.replaceAll(">", "&gt;");
						inputStr = inputStr.replaceAll("\"", "&quot;");
					} //end if.
					return inputStr;
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

		public static  long getNextSequenceNumber( String series,Connection connect) throws SQLException {

			Connection con =  null;
			ResultSet rs =  null;
		    Statement updateStmt =  null;
		    PreparedStatement statement =  null;

			long maxValue = 0L;
			long startValue = 1L;

			try  {
				ConnectDB conObj=new ConnectDB();
				con=conObj.getMysqlConnection();
				updateStmt = con.createStatement();
		    	String query = "SELECT max_number FROM profile_series WHERE series = ? FOR UPDATE";
				statement = con.prepareStatement(query);
				statement.setString( 1, series );

				rs = statement. executeQuery();

				if( rs.next() ){

					maxValue = rs.getLong(1);
					query = "UPDATE profile_series SET max_number = max_number + 1   WHERE series = '" + series + "'";

					int result = updateStmt.executeUpdate( query );

					if ( result == 0 )  {
						System.out.println( "Sequence : Updation failed for the Series : " +  series);
					}


				} else {

					/**
					 * If sequence is not exising for the series add an entry for passed document series
					 **/

					query = "INSERT INTO profile_series (series, max_number) VALUES ('" + series + "'," + ( startValue + 1 ) + ")";

					updateStmt.executeUpdate( query );
					maxValue = startValue;

				}

			}catch( SQLException e ){
				e.printStackTrace() ;

			} finally{
	     		statement.close();
				rs.close();
				updateStmt.close();

			}

				return maxValue;
			}


		public static ArrayList getEditableReportFormForPatients(RequestParam userRequest, String number) throws Exception {
			ConnectDB conObj=new ConnectDB();
			Connection connect = conObj.getMysqlConnection();
			ArrayList queReport =  getEditableFormElements(connect,userRequest,number);
			return queReport;
		}
//		=====================PRINT FUNCTION 06-12-2010=========================
		/**
		 * @param response
		 */
			public static void showErrorMessage ( HttpServletResponse response,String message )
			{
				String htmlstring = "<html>ERROR: " + message + "</html>";
				try{
					response.getWriter().println( htmlstring );
				}catch (IOException e1)	{
					//log.error( "" + e1.getMessage() );
					e1.printStackTrace();
				}
			}


		/**
		 * @param request
		 * @param response
		 * @param userRequest
		 */
	public static ArrayList getXMLString(HttpServletRequest request, HttpServletResponse response, RequestParam userRequest)throws Exception {
		Connection conn = null;
		if (isEmpty(userRequest.getDocumentId()) && Integer.parseInt(userRequest.getDocumentId()) < 1){
			showErrorMessage( response,"Invalid document id..." );
			return null;
		}
		//get the form object details from the database.
		ArrayList questionVOsList = getReportFormForStaffs( userRequest.getEntityId(),userRequest.getFormName(),userRequest.getNumber(), userRequest.getUserDepartmentId());
		int  addendumDocId = userRequest.getAddendumDocumentId();
		if( addendumDocId > 0 ){
			ArrayList addendumList = new ArrayList();
			 ConnectDB conObj=new ConnectDB();
			 conn = conObj.getMysqlConnection();
			 DocumentInfo addendumDocInfo = getEntityDocumentInfo(""+addendumDocId,conn);
			 addendumList =  getReportFormForStaffs(addendumDocInfo.getEntityId(),addendumDocInfo.getFormName(), addendumDocInfo.getNumber(),"");
			 if(addendumList.size() > 0){
				 for(Iterator itr =addendumList.iterator();itr.hasNext(); ){
					 QuestionsVO myQuestVO = (QuestionsVO) itr.next();
					 questionVOsList.add(myQuestVO);
				 }
			 }
			 conn.close();
		}
		ArrayList questionNames = new ArrayList();
		//Xml header.
		StringBuffer str = new StringBuffer (10000);
		str.append ("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> \n");
		str.append("<?xml-stylesheet type=\"text/xsl\" href=\"./xml/" + userRequest.getFormName() + "_Report.xml\" ?> \n");
		str.append("<OIO version=\"1.00\" xmlns:oioNS=\"http://devedge.netscape.com/2002/de\"> \n");
		str.append ("\n" + "<form> \n" + "<name>" + userRequest.getFormName().trim() + "</name>\n");
		str.append("<documentId></documentId>\n<docNumber></docNumber>\n\n");
		//iterate over ArrayList to generate Xml string for the questions.
		QuestionsVO questVO = new QuestionsVO();
		for (int cnt=0; cnt<questionVOsList.size(); cnt++){
			questVO = (QuestionsVO) questionVOsList.get(cnt);
			str.append("<" + questVO.getName() + ">\n");
			str.append("	<number>" + questVO.getNumber() + "</number>\n");
			str.append("	<description>" + questVO.getDescription() + "</description>\n");
			str.append("	<oioNS:prompt>" + questVO.getPrompt() + "</oioNS:prompt>\n");			
			str.append("	<oioNS:itemtype>" + questVO.getItemtype() + "</oioNS:itemtype>\n");
			//str.append("	<oioNS:renderinfo>\n\n" + questVO.getAction() + "\n\n</oioNS:renderinfo>\n");  //07/01/2011
			str.append("	<oioNS:renderinfo>\n\n" + questVO.getValue() + "\n\n</oioNS:renderinfo>\n"); //07/01/2011
			str.append("</" + questVO.getName() + ">\n\n");
			//as you iterate populate questionNames arrayList also.
			questionNames.add(questVO.getName());
		}
		str.append("</form>\n");
		str.append("</OIO>\n");
		ArrayList returnList = new ArrayList();
		returnList.add(questionNames);
		returnList.add(str);
		//end of the Xml string.
		return returnList;

	}


	/**
	 * FOR HTML Form Only
	 * ArrayList getReportFormForStaffs(...)
	 * Function to get a pritable format of the OIO report
	 * @param patientId
	 * @param formName
	 * @param number
	 * @return ArrayList of all items
	 * @throws HisException
	 * @throws RemoteException
	 */
		public static ArrayList getReportFormForStaffs(String entityId, String formName, String number,String userDeptId)throws Exception  {
			Connection conn = null;
			ArrayList arrList = null;
			try{
				 ConnectDB conObj=new ConnectDB();
				 conn = conObj.getMysqlConnection();
				 arrList = getFormContent(conn, entityId, formName,number,userDeptId);
			}
			catch(SQLException e){
			e.printStackTrace();
			}finally{
				conn.close();
			}
			return arrList;
		}//end function.

		public static void deleteEntities(String deletedDocmentIds)
		{
			Connection con =  null;
			String sqlStr=null;

			try  {
			ConnectDB conObj=new ConnectDB();
			con=conObj.getMysqlConnection();
			Statement sst = con.createStatement();
			Statement st = con.createStatement();

			sqlStr="SELECT entity_id,entity_type,number FROM entity_document_master WHERE  document_id IN(" + deletedDocmentIds + ")";
			ResultSet rs_delete = st.executeQuery(sqlStr);
			while (rs_delete.next()){
				String sql = "DELETE FROM staff_profile_"+ rs_delete.getString("entity_type")+ "_v0_values where idf =" + rs_delete.getString("entity_id") + " and number=" + rs_delete.getString("number");
				//System.out.println("SQl : "+  sql);
				sst.execute(sql);
			}
			String sql = "DELETE FROM entity_document_master where document_id IN (" + deletedDocmentIds +")" ;
			//System.out.println("SQl : "+  sql);
			sst.execute(sql);

			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
