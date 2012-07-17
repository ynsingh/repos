/**
 * @(#) CourseGroupImpl.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.daoimpl.login;

import in.ac.dei.edrp.cms.dao.login.LoginService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.login.Login;

import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 * this is Server side Implementation class for University Reservation
 *
 * @version 1.0 18 FEB 2011
 * @author MOHD AMIR
 */
public class LoginServiceImpl extends SqlMapClientDaoSupport implements LoginService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(UniversityReservationServiceImpl.class);

	
	/**
	 * This method retrieves Login Details from database
	 *
	 * @param login, object of the Login
	 * @return loginDetails containing login Details
	 */
	@SuppressWarnings("unchecked")
	public List<Login> getLoginDetails(Login login)
	{
		try
		{
			List<Login> loginDetails=getSqlMapClientTemplate().queryForList("login.getLoginInfo",login);
		    return loginDetails;
	    }
	    catch (Exception e)
	    {
		    logObj.error(e.getMessage());
	    }
	    return null;
	}

	/**
	 * This method update Last Login Details from database
	 *
	 * @param login, object of the Login
	 * @return updateCount containing no of records updated
	 */
	public int updateLastLogin(Login login)
	{
		int updateCount=0;
		try
		{
			updateCount=getSqlMapClientTemplate().update("login.updateLastLogin",login);
		}
		catch (Exception e)
		{
			logObj.error(e.getMessage());
		}
		return updateCount;
	}
	
	
	/**
	 * This method will generate XML for the menu
	 *
	 * @param login, object of the Login
	 * @return StringWriter containing the generated XML 
	 */	
	@SuppressWarnings({ "unchecked" })
	public StringWriter getMenu(Login login) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    Document doc = null;
	   	Element rootElement = null;
	   	StringWriter sw = null;
	   	logObj.info("getMenu");
	   	
	    try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			rootElement = doc.createElement("menuList");
			doc.appendChild(rootElement);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	
		List<String> createdElement= new ArrayList<String>();
		List<Element> elementList= new ArrayList<Element>();
		String errorMenu=null;
		try{
			List<Login> authorizdMenu=getSqlMapClientTemplate().queryForList("login.getAuthorizedMenuItem",login);
				
			int index=0;
			int maxLengthMenuId=0;
			for(int i=0;i<authorizdMenu.size();i++){
				for(int j=2;j<=authorizdMenu.get(i).getMenuId().length();j++){
						Login loginObj=new Login();
						loginObj.setMenuId(authorizdMenu.get(i).getMenuId().substring(0, j));
						errorMenu=authorizdMenu.get(i).getMenuId().substring(0, j);
						List<Login> menuDetail=getSqlMapClientTemplate().queryForList("login.getMenuDetail",loginObj);
						
						boolean check=checkDuplicateElement(createdElement,menuDetail.get(0).getMenuId());
						if(!check){
							Element element = doc.createElement("menuItem");
							element.setAttribute("menuId",menuDetail.get(0).getMenuId());
							element.setAttribute("menuName",menuDetail.get(0).getMenuName());
							
							if(menuDetail.get(0).getMenuId().length()>maxLengthMenuId){
								maxLengthMenuId=menuDetail.get(0).getMenuId().length();
							}
							
							elementList.add(element);
							createdElement.add(menuDetail.get(0).getMenuId());
							index++;
						}
					}
			}
		}catch (java.lang.IndexOutOfBoundsException e) {
			logObj.error("Menu Fail due to ("+ errorMenu+") menu item entry missing in system_table_two table in database");
			logObj.error(e.getMessage());
			sw=new StringWriter();
			sw.write("errorMenu:"+errorMenu);
			return sw;
		}
		
		//logic to add created node into XML doc
		for(int m=0;m<elementList.size();m++){
			if(elementList.get(m).getAttribute("menuId").length()==6){
				int itemPosition=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 2));
				int itemPosition1=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 3));
				int itemPosition2=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 4));
				int itemPosition3=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 5));
				elementList.get(itemPosition).appendChild(elementList.get(itemPosition1)).appendChild(elementList.get(itemPosition2)).appendChild(elementList.get(itemPosition3)).appendChild(elementList.get(m));
				rootElement.appendChild(elementList.get(itemPosition));
			}			
			if(elementList.get(m).getAttribute("menuId").length()==5){				
				int itemPosition=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 2));
				int itemPosition1=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 3));
				int itemPosition2=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 4));
				elementList.get(itemPosition).appendChild(elementList.get(itemPosition1)).appendChild(elementList.get(itemPosition2)).appendChild(elementList.get(m));
				rootElement.appendChild(elementList.get(itemPosition));
			}			
			if(elementList.get(m).getAttribute("menuId").length()==4){				
				int itemPosition=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 2));
				int itemPosition1=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 3));
				elementList.get(itemPosition).appendChild(elementList.get(itemPosition1)).appendChild(elementList.get(m));
				rootElement.appendChild(elementList.get(itemPosition));
			}
			if(elementList.get(m).getAttribute("menuId").length()==3){
				int itemPosition=searchElement(elementList,elementList.get(m).getAttribute("menuId").substring(0, 2));
				elementList.get(itemPosition).appendChild(elementList.get(m));
				rootElement.appendChild(elementList.get(itemPosition));
			}
			if(elementList.get(m).getAttribute("menuId").length()==2){
				rootElement.appendChild(elementList.get(m));
			}
			
		}
			
		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = null;
		
		try{
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		    sw = new StringWriter();
		    StreamResult sr = new StreamResult(sw);
		    try{
		    	transformer.transform(domSource, sr);
		    } 
		    catch(TransformerException e4) {
		    	System.out.println("The Problem4: "+e4);
		    }
		    
		}
		catch (Exception e6) {
			System.out.println("The Problem6: "+e6);
		}
		return sw;
	}
	
	private boolean checkDuplicateElement(List<String> createdElement, String newMenuId){
		
		boolean check=false;
		for(int i=0;i<createdElement.size();i++){
			if(createdElement.get(i).equalsIgnoreCase(newMenuId)){
				check=true;
				break;
			}			
		}
		return check;
	}
	
	private int searchElement(List<Element> elementList, String searchMenuId){
		int index = 0;
		for(int i=0;i<elementList.size();i++){
			if(elementList.get(i).getAttribute("menuId").equalsIgnoreCase(searchMenuId)){
				break;
			}
			index++;
		}
		return index;		
	}

	/**
	 * This method get The Session Start-End Date
	 * @return List<resultList>
	 */
	public List<List<Login>> getSessionDate(Login input) {
      List<Login>sessionDateList=new ArrayList<Login>();
      List<Login>enrollPeriod=new ArrayList<Login>();
      List<Login>enrollExtDays=new ArrayList<Login>();
      List<Login>registDays=new ArrayList<Login>();
      List<Login>registExtDays=new ArrayList<Login>();
      List<List<Login>>resultList=new ArrayList<List<Login>>();
      sessionDateList=getSqlMapClientTemplate().queryForList("login.getSessionDate",input);
      enrollPeriod=getSqlMapClientTemplate().queryForList("login.getEnrollmentPeriod",input);
      enrollExtDays=getSqlMapClientTemplate().queryForList("login.getEnrollExtend",input);
      registDays=getSqlMapClientTemplate().queryForList("login.getRegDays",input);
      registExtDays=getSqlMapClientTemplate().queryForList("login.getRegExtDays",input);
      resultList.add(sessionDateList);
      resultList.add(enrollPeriod);
      resultList.add(enrollExtDays);
      resultList.add(registDays);
      resultList.add(registExtDays);
		return resultList;
	}
	
	/**
	 * This method get The Registration Start-End Date
	 * @return List<resultList>
	 */
	public List<Login> getRegisDate(Login input) {
		List<Login>regisDateList=new ArrayList<Login>();
		regisDateList=getSqlMapClientTemplate().queryForList("login.getRegistrationDate",input);
		return regisDateList;
	}
	
}
