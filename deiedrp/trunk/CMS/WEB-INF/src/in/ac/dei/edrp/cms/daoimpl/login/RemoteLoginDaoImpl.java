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

import in.ac.dei.edrp.cms.dao.login.RemoteLoginDao;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.login.Login;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 * this is Server side Implementation class for University Reservation
 *
 * @version 1.0 18 FEB 2011
 * @author MOHD AMIR
 */
public class RemoteLoginDaoImpl extends SqlMapClientDaoSupport implements RemoteLoginDao {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(RemoteLoginDaoImpl.class);

	
	/**
	 * This method retrieves Login Details from database
	 *
	 * @param login, object of the Login
	 * @return loginDetails containing login Details
	 */
	@SuppressWarnings("unchecked")
	public List<Login> getLoginDetails(Login login){
		try{
			List<Login> loginDetails=getSqlMapClientTemplate().queryForList("login.getRemoteLoginInfo",login);
		    return loginDetails;
	    }catch (Exception e){
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
}
