/**
 * @(#) UserInformationServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.userInformationInterface;

import in.ac.dei.edrp.cms.dao.userInformationInterface.UserInformationService;
import in.ac.dei.edrp.cms.domain.userInformationInterface.UserInformationDomain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This Class contain implementation methods for UserInterface Services
 * @author Devendra Singhal
 * @date Nov 09 2012
 */
public class UserInformationServiceImpl extends SqlMapClientDaoSupport implements UserInformationService{
/** Creating object of Logger for log Maintenance */
private Logger loggerObject = Logger.getLogger(UserInformationServiceImpl.class);
	
/**
 * Method to get Database name
 * @return List<UserInformationDomain>
 **/
@SuppressWarnings("unchecked")
public List<UserInformationDomain> getDatabase() {
	List<UserInformationDomain> list=null;
	try{
		list=getSqlMapClientTemplate().queryForList("userInformation.getDatabases");
	}
	catch(Exception e){
		loggerObject.error("Exception inside UserInformationServiceImpl During get databse names "+e);
	}
	return list;
}

/**
 * Method to get specific table names
 * @param UserInformationDomain input
 * @return List<UserInformationDomain>
 **/
@SuppressWarnings("unchecked")
public List<UserInformationDomain> getTables(UserInformationDomain input) {
	List<UserInformationDomain> list=null;
	try{
		list=getSqlMapClientTemplate().queryForList("userInformation.getTables",input);
	}
	catch(Exception e){
		loggerObject.error("Exception inside UserInformationServiceImpl During get Table names "+e);
	}
	return list;
}

/**
 * Method to get columns in selectd tables
 * @param UserInformationDomain input
 * @return List<UserInformationDomain>
 **/
@SuppressWarnings("unchecked")
public List<UserInformationDomain> getColumns(UserInformationDomain input) {
	List<UserInformationDomain> list=null;
	try{
		list=getSqlMapClientTemplate().queryForList("userInformation.getColumns",input);
	}
	catch(Exception e){
		loggerObject.error("Exception inside UserInformationServiceImpl During getColumns "+e);
	}
	return list;
}

/**
 * Method to execute query
 * @param UserInformationDomain input
 * @return List<UserInformationDomain>
**/
@SuppressWarnings("unchecked")
public List<UserInformationDomain> execute(UserInformationDomain input) {
	List<UserInformationDomain> list=null;
	try{
		list=getSqlMapClientTemplate().queryForList("userInformation.execute",input);		
	}
	catch(Exception e){
		loggerObject.error("Exception inside UserInformationServiceImpl During execute "+e);
	}
	return list;
}

}
