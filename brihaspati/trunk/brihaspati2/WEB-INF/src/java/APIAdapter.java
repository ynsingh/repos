/*
	@program : APIAdapter.java
	@description : an API for SCORM run time implementations
*/

import java.applet.Applet;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.net.*;

/**
* A faceless applet that serves as a communication between the Content and the LMS Server.  This applet contains
* API functions that should conform to the AICC's CMI001 Guidelines for Interoperability document.  These functions
* can be categorized as three behaviours:
*<p>
*<li>
*<ul>Execution State</ul>
*<ul>State Management</ul>
*<ul>Data Transfer</ul>
*</li>
*<p>
*/
public class APIAdapter extends Applet {
	
	private Vector pages;
	private Vector items;
	private Hashtable idenHash;
	/**
	* ims_data - to store data element read from the imsmanifest file.
	*/
	private Vector ims_data;
	private Vector scoPages;
	private Vector scoTitles;
	private Vector scoIds;
	private Vector scoLevels;
	private Vector scoPrerequisites;
	private Hashtable contents;
	private Hashtable scoData;
	private String curScoId;
	private String realScoId;
	private Vector realScoData;
	
	private int itemNum = 0;
	private int pageNum = 0;
	private int maxItem = 0;
	private int maxPage = 0;
	
	private String userid = "";
	private String courseid = "";
	
	private String errorcode = "";
	private String errorstring = "";
	private Hashtable errorsTable;
	
	private boolean isLMSInitialized = false;
	private boolean isLMSFinished = false;
	public void init() {
		isLMSInitialized = false;
		isLMSFinished = false;
		errorsTable = new Hashtable();
		errorsTable.put("0", "No error");
		errorsTable.put("101", "General Exception");
		errorsTable.put("201", "Invalid argument error");
		errorsTable.put("202", "Element cannot have children");
		errorsTable.put("203", "Element not an array - Cannot have count");
		errorsTable.put("301", "Not initialized");
		errorsTable.put("302", "LMS error");
		errorsTable.put("401", "Not implemented error");
		errorsTable.put("402", "Invalid set value, element is a keyword");
		errorsTable.put("403", "Element is read only");
		errorsTable.put("404", "Element is write only");
		errorsTable.put("405", "Incorrect Data Type");		
	}

	public void start() {
	}		
	
	/*
	* This is not API's function.  
	*/
	public void setUserId(String s) { userid = s; }
	
	/*
	* This is not API's function.
	*/	
	public void setCourseId(String s) { courseid = s; }
	
	/*
	* This is not API's function.
	*/		
	public String getAboutMe() {
		return ":: Learning Management System ";
	}
	
	/**
	* This method is called by the content when it is first loaded into the learner's web browser.  
	* As conform by the AICC, the 
	* parameter's value must be an empty String ("").  If any value is passed as parameter, 
	* this method will return false with an errorcode 201.
	*
	*/
	public String LMSInitialize(String parameter) {
		String s = "true";	
		if ( isLMSInitialized ) {
			errorcode = "101";
		}
		if ( parameter == null ) parameter = "";
		errorcode = "";
		if ( parameter.equals("") ) {
			isLMSInitialized = true;
			scoData = initScoData(curScoId);
			System.out.println("ims_data========>  sLMSInitialized====="+scoData);
			String lesson_status = (String) scoData.get("cmi.core.lesson_status");
			String entry = (String) scoData.get("cmi.core.entry");
			System.out.println("ims_data========>  "+ims_data);
			for ( int i = 0; i < ims_data.size(); i++ ) {
				String ims_data_str = (String) ims_data.elementAt(i);
				String in_scoid = ims_data_str.substring(0, ims_data_str.indexOf("|"));
				if ( in_scoid.equals(curScoId) ) {
					String in_data = ims_data_str.substring(ims_data_str.indexOf("|") + 1);
					String in_data_name = in_data.substring(0, in_data.indexOf("="));
					String in_data_value = in_data.substring(in_data.indexOf("=") + 1);
					System.out.println(in_scoid + ":" + in_data_name + "=" + in_data_value);
					if ( in_data_name.equals("datafromlms") )
						scoData.put("cmi.launch_data", in_data_value);
					else if ( in_data_name.equals("masteryscore") )
						scoData.put("cmi.student_data.mastery_score", in_data_value);
					else if ( in_data_name.equals("maxtimeallowed") )
						scoData.put("cmi.student_data.max_time_allowed", in_data_value);
					else if ( in_data_name.equals("timelimitaction") )
						scoData.put("cmi.student_data.time_limit_action", in_data_value);
					else if ( ("prerequisites".equals(in_data_name) ) ){
						String cdata = in_data_value; 
						scoData.put("prerequisites", cdata);
					}
				} 
			}
			realScoId = curScoId;
			if ( scoData == null ) s = "false";
		} else {
			s = "false";
			errorcode = "201";
		}
		return s;
	}
	
	/**
	*
	*
	*/
	public String LMSCommit(String parameter) {
		if ( !isLMSInitialized ) return "false";
		if ( parameter == null ) parameter = "";
		String s = "false";
		if ( parameter.equals("") ) {
			errorcode = "";
			scoData.put("real", "Y"); //tell server that this data comes from client
			//if updating successfully, set the return value to true
			if (updateScoData(scoData, realScoId)) s = "true";
			if ( s.equals("false") ) {
				//determine the error	
				errorcode = "302";  //this is LMS specific error
			}
			return s;
		} else {
			errorcode = "201";
			return "false";	
		}
	}	
	
	/**
	*
	*
	*/	
	public String LMSGetValue(String element) {
		if ( !isLMSInitialized ) return "false";
		errorcode = "";
		String s = (String) scoData.get(element);
		if ( s == null ) s = "";
		//
		//System.out.println("LMSGetValue(" + element + ") = " + s);
		return s;
	}
	
	/**
	*
	*
	*/	
	public String LMSSetValue(String element, String value) {
		if ( !isLMSInitialized ) return "false";
		errorcode = "";
		//currently, this method ALWAYS return true
		String s = "true";
		if ( scoData != null ) {
			//
			if ( value == null ) value = "";
			System.out.println("LMSSetValue(" + element + ", " + value + ")");
			scoData.put(element, value);
		} else {
			System.out.println("Can't do LMSSetValue!, scoData is null");
		}
		return s;
	}

	/**
	*
	*
	*/
	public String LMSFinish(String parameter) {
		System.out.println(realScoId + ", LMSFinish...");
		if ( !isLMSInitialized ) return "false";
		if ( parameter == null ) parameter = "";
		if ( parameter.equals("") ) {
			errorcode = "";
			String s = "";
			//for now, this method just call LMSCommit to ensure that
			//all data are kept persisted
			s = LMSCommit("");
			//no matter what s returned, set isLMSFinished to true
			isLMSFinished = true;
			isLMSInitialized = false;
			return "true";
		} else {
			errorcode = "201";
			return "false";
		}
	}
	
	/**
	*
	*
	*/
	public String LMSGetLastError() {
		if ( errorcode != null )
			return errorcode;
		else
			return "";
	}
	
	/**
	*
	*
	*/	
	public String LMSGetErrorString(String code) {
		if ( code == null ) return "";
		String s = (String) errorsTable.get(code);
		if ( s == null ) s = "";
		return s;
	}
	
	/**
	*
	*
	*/	
	public String LMSGetDiagnostic(String code) {
		if ( code == null ) return "";
		String s = "";
		if ( code.equals("302") ) {
			s = "The API could not communicate with the server.";	
		}
		return s;
	}
	
	//read only elements - can't be used by the LMSSetValue()
	private boolean isReadOnly(String element) {
		boolean result = false;
		StringBuffer sb = new StringBuffer("cmi.core.student_id,cmi.core._children,");
		sb.append("");
		System.out.println("----------------> StringBuffer "+sb);
		return result;
	}
	
	private Vector getReadOrWriteOnlyElementVector() {
		Vector v = new Vector();
		v = (Vector) getObjectInputStream("element");
		System.out.println("getReadOrWriteOnlyElementVector()---------------"+ v);
		return v;
	}
	
	public String dumpScoData() {
		String s = "";
		Enumeration keys = scoData.keys();
		while ( keys.hasMoreElements() ) {
			String k = (String) keys.nextElement();
			s += k + " = " + (String) scoData.get(k) + "\n";
		}
		return s;	
	}
	
	private URLConnection getServletConnection(String servletname) throws MalformedURLException, IOException {
		System.out.println("servletname------>"+servletname);	
		URL urlServlet = new URL(getCodeBase(), servletname);
		URLConnection con = urlServlet.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
		return con;
	}	
	
	private Object getObjectInputStream(String servlet_name, Object send) {
		Object object = null;
		try {
			URLConnection con = getServletConnection(servlet_name);
			System.out.println("servletname------>getObjectInputStream====="+con);	
			OutputStream outstream = con.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstream);
			System.out.println("Object send --------------->  "+send);
			
			oos.writeObject(send);
			oos.flush();
			oos.close();			
			InputStream instr = con.getInputStream();
			ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
			object = inputFromServlet.readObject();
			System.out.println("Object recive  --------------->  "+object);
			inputFromServlet.close();
			instr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return object;
	}
	
	private Object getObjectInputStream(String servlet_name) {
		System.out.println("\nobject getObjectInputStream" +servlet_name);
		return getObjectInputStream(servlet_name, "");
	}
	
	public void sendMessage(String msg) {
		getObjectInputStream("course", msg);
	}
	
	public Hashtable initScoData(String scoId) {
		Hashtable data = (Hashtable) getObjectInputStream("initservlet", scoId);
		System.out.println("scoIds======"+scoIds);
		return data;
	}
	
	public boolean getScoItems(String courseid) {
		boolean success = false;
		contents = new Hashtable();
		scoLevels = new Vector();
		scoIds = new Vector();
				System.out.println("scoIds======"+scoIds);
				System.out.println("courseid======"+courseid);
		scoPages = new Vector();
		scoTitles = new Vector();
		scoPrerequisites = new Vector();
		//This Vector contains two Vectors
		Vector container = (Vector) getObjectInputStream("items", courseid);
				System.out.println("Container======"+container);
				System.out.println("courseid======"+courseid);
		//The first Vector is a collection of String of scos items
		items = (Vector) container.elementAt(0);
		//The second Vector is a collection of String of meta data and it's value
		ims_data = (Vector) container.elementAt(1);	
		//The third is a Hashtable for mapping between idref and iden
		idenHash = (Hashtable) container.elementAt(2);	
		//The forth is a prerequisites Hashtable
		
		if ( items != null ) {
			maxItem = items.size() - 1;
			for ( int i = 0; i < items.size(); i++ ) {
				String s = (String) items.elementAt(i);
				//System.out.println(s);
				StringTokenizer st = new StringTokenizer(s, "|");
				//First three token is level, identifier, start page and title
				String sco_level = st.nextToken();
				String sco_id = st.nextToken();
				String page_name = st.nextToken();
				//String sco_page = courseid + "/";
				//String sco_page = "xxx/";
				String sco_page = "";  //modified on 27/10/2004 -- because relative full path available in the manifest
				if ( page_name.equals("undefined") ) sco_page = "blank";
				else sco_page += page_name;
				String sco_title = st.nextToken();
				scoLevels.addElement(sco_level);
				scoIds.addElement(sco_id);
				scoPages.addElement(sco_page);
				scoTitles.addElement(sco_title);
				Vector p = new Vector();
				while ( st.hasMoreTokens() ) {
					String token = courseid + "/" + st.nextToken();	
					p.addElement(token);
				}

				contents.put(sco_id, p);
				
			}
			pageNum = 0;
			itemNum = 0;
			success = true;
		}
		return success;
	}
	
	private void getItemPages(String scoid) {
		pages = (Vector) contents.get(scoid);
	}
	
	public String getInitPage() {
		String s = "";
		s = getScoPage(0);
		 System.out.println("getInitPage()======"+s);
		return s;	
	}
	
	/**
	*
	*
	*/	
	public int getScoLevel(int i) {
		String s = "";
		s = (String) scoLevels.elementAt(i);
		return Integer.parseInt(s);
	}
	
	/**
	* Returns the sco page.
	*
	*/	
	public String getScoPage(int i) {
		String s = "";
		s = (String) scoPages.elementAt(i);
		String scoId = (String) scoIds.elementAt(i);
		curScoId = scoId;
		boolean getpage = true;
		String preq_page = "";
		for ( int k = 0; k < ims_data.size(); k++ ) {
			String ims_data_str = (String) ims_data.elementAt(k);
			String in_scoid = ims_data_str.substring(0, ims_data_str.indexOf("|"));
			//get the data for the current SCO
			if ( in_scoid.equals(curScoId) ) {
				String in_data = ims_data_str.substring(ims_data_str.indexOf("|") + 1);
				String in_data_name = in_data.substring(0, in_data.indexOf("="));
				String in_data_value = in_data.substring(in_data.indexOf("=") + 1);
				
				System.out.println(in_scoid + "=" + in_data_name);
				
				if ( ("prerequisites".equals(in_data_name) ) ){
					String cdata = in_data_value; 
					System.out.println("THIS SCO HAS PRE-REQUISITE = " + cdata);
					String pre_scoid = (String) idenHash.get(cdata);
					if ( pre_scoid != null ) {
						//System.out.println("=" + pre_scoid);
					} else {
						//System.out.println("= many sco");	
					}
					//get status from servlet tunnel
					Hashtable result = (Hashtable) getObjectInputStream("preq", curScoId + "=" + cdata);
					if ( result != null ) {
						String preq_met = (String) result.get("preq_met");
						System.out.println("PreReq Status=" + preq_met);
						if ( "false".equals(preq_met) ) {
							getpage = false;
							preq_page = (String) result.get("page");
						}
					}
					break;
				}
			} 
		}		
		if ( getpage ) {
			updateMetaData(scoId);
			pages = (Vector) contents.get(scoId);
			maxPage = pages.size() - 1;
			if ( s.equals((String) pages.elementAt(0)) ) pageNum = 0;
			else pageNum = -1;
		} else {
			s = preq_page;	
		}
		return s;	
	}
	
	/**
	* Returns the current sco id.
	*/
	public String getCurrentScoId() { return curScoId; }
	
	private void updateMetaData(String scoId) {
		updateMetaData(null, scoId);
	}	
	
	private void updateMetaData(Hashtable data, String scoId) {
		if ( data == null ) data = initScoData(scoId);
		
		String last_exit = (String) data.get("cmi.core.exit");
		if ( last_exit == null || last_exit.equals("") ) {
			data.put("cmi.core.entry", "");
		} else if ( last_exit.equals("suspend")) {
			data.put("cmi.core.entry", "resume");
		} else if ( last_exit.equals("logout") || last_exit.equals("time-out") ) {
			data.put("cmi.core.entry", "");
		}
		
		String lesson_status = (String) data.get("cmi.core.lesson_status");
		if ( lesson_status == null || lesson_status.equals("")) {
			data.put("cmi.core.lesson_status", "browsed");
			data.put("cmi.core.entry", "ab-initio");
		}
		
		updateScoData(data, scoId);  //this will save the data
	}

	public int getTotalSco() {
		return maxItem + 1;
	}
	
	public String goNextSco() {
		String s = "";
		if ( itemNum < maxItem ) {
			itemNum++;	
			s = getScoPage(itemNum);
		}
		return s;
	}
	
	public String goPreviousSco() {
		String s = "";
		if ( itemNum > 0 ) {
			itemNum--;	
			s = getScoPage(itemNum);
		}
		return s;
	}
	
	public String goToSco(int i) {
		String s = "";
		if ( i > maxItem ) {
			s = "";
		} else {
			itemNum = i;
			s = getScoPage(i);	
		}
		return s;
	}
	
	public String goNextPage() {
		String s = "";
		if ( pageNum < maxPage ) {
			pageNum++;
			s = (String) pages.elementAt(pageNum);
			System.out.println("page = " + pageNum);
		}
		return s;
	}

	public String goPreviousPage() {
		String s = "";
		if ( pageNum > 0 ) {
			pageNum--;
			s = (String) pages.elementAt(pageNum);
			System.out.println("page = " + pageNum);
		}
		return s;
	}
	
	public String getTitle() {
		String s = "";
		s = (String) scoTitles.elementAt(itemNum);
		return s;
	}	
	
	/**
	* Returns the title of the sco at ith element of the Vector of sco's titles
	*/	
	public String getTitle(int i) {
		String s = "";
		s = (String) scoTitles.elementAt(i);
		return s;
	}
	
	/**
	* Returns the current sco item's number
	*/	
	public String getCurrentSco() {	return Integer.toString(itemNum + 1); }
	
	/**
	* Returns the total number of scos
	*/	
	public String getTotalScos() { return Integer.toString(maxItem + 1); }
	
	/**
	* Returns the current sco page number
	*/	
	public String getCurrentPage() { return Integer.toString(pageNum + 1); }
	
	/**
	* Return the total page of the current sco
	*/	
	public String getTotalPages() { return Integer.toString(maxPage + 1); }
	
	/**
	* Returns the String representing the page number of the current sco
	*/	
	public String showPageNumber() {
		String s = Integer.toString(itemNum + 1) + " : " + Integer.toString(pageNum + 1) + "/" + Integer.toString(maxPage + 1);
		return s;	
	}
	
	public boolean updateScoData(Hashtable data, String scoId) {
		boolean result = false;
		if ( data != null ) {
			System.out.println("Update sco for id = " + scoId+" data---------------> "+data);
			
			data.put("scoId", scoId);
			String lesson_status = (String) data.get("cmi.core.lesson_status");
			if ( lesson_status == null || lesson_status.equals("") ) {
				data.put("cmi.core.lesson_status", "browsed");		
			}
			String success = (String) getObjectInputStream("update", data);
			if ( success.equals("true") ) result = true;
		}
		return result;
	}
}
