/**
 * @(#) BuildNextSessionDaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.buildnextsession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.ac.dei.edrp.cms.dao.buildnextsession.BuildNextSessionDao;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This file consist of the methods used at the
 * Build Next Session process.
 * @author Devendra Singhal
 * @date Nov 17 2011
 * @version 1.0
 */
public class BuildNextSessionDaoImpl extends SqlMapClientDaoSupport implements BuildNextSessionDao {
	
	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(BuildNextSessionDaoImpl.class);
	
	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;
	
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	/**
     * Method to get the university session start and end date.
     * @param BuildNextSession Object
     * @return List it will return the session start and end date. 
     */
	@SuppressWarnings("unchecked")
	public List<BuildNextSession> getCurrentSessionDetails(
			BuildNextSession buildNextSessionObject) {
		List<BuildNextSession> sessionStartEndList=null;
		try{
			sessionStartEndList = getSqlMapClientTemplate().queryForList("buildNextSession.getSessionStartEndDate", buildNextSessionObject);
			loggerObject.info("in BuildNextSessionDaoImpl inside method getCurrentSessionDetails"); 
		}
		catch (Exception e) {
			loggerObject.error("Error in BuildNextSessionDaoImpl inside method getCurrentSessionDetails"+ e);
		}
		return sessionStartEndList;
	}
	
	/**
     * Method to build the University's Next session.
     * @param BuildNextSession Object
     * @return String it will return a message(success/rollback/duplicate/lessDate/null/parseError/error)
	 * @throws ParseException 
     */
	@SuppressWarnings({ "unchecked" })
	public String buildNextSession(
			BuildNextSession buildNextSessionObject){
		List<BuildNextSession> sessionStartEndList=null;
		List<BuildNextSession> controlList=null;
		String msg="";
		Date endDate;
		try{
			sessionStartEndList=getSqlMapClientTemplate().queryForList("buildNextSession.getSessionDetails", buildNextSessionObject);
			if(sessionStartEndList.size()>0){
				loggerObject.info("in BuildNextSessionDaoImpl inside method buildNextSession sessionStartDate is "+sessionStartEndList.get(0).getSessionStartDate()
					+" : sessionEndDate is "+sessionStartEndList.get(0).getSessionEndDate()+" : nextSessionStartDate is "+sessionStartEndList.get(0).getNextSessionStartDate()
					+" : nextSessionEndDate is "+sessionStartEndList.get(0).getNextSessionEndDate());
				
				Date date=Calendar.getInstance().getTime();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String sysdate1 =dateFormat.format(date);
				Date sysdate=(Date)dateFormat.parse(sysdate1);
				endDate=dateFormat.parse(sessionStartEndList.get(0).getSessionEndDate());
				
				if(sysdate.after(endDate)){
					buildNextSessionObject.setDummyFlag("1");
					List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
					if(processList.size()>0){
						buildNextSessionObject.setProcessCode(processList.get(0).getProcessCode());
						buildNextSessionObject.setGroupCode("YEPCOD");
						buildNextSessionObject.setSessionStartDate(sessionStartEndList.get(0).getSessionStartDate());
						buildNextSessionObject.setSessionEndDate(sessionStartEndList.get(0).getSessionEndDate());
						buildNextSessionObject.setNextSessionStartDate(sessionStartEndList.get(0).getNextSessionStartDate());
						buildNextSessionObject.setNextSessionEndDate(sessionStartEndList.get(0).getNextSessionEndDate());
						buildNextSessionObject.setUniversityName(sessionStartEndList.get(0).getUniversityName());
						buildNextSessionObject.setUniversityAdd(sessionStartEndList.get(0).getUniversityAdd());
						buildNextSessionObject.setUniversityCity(sessionStartEndList.get(0).getUniversityCity());
						buildNextSessionObject.setUniversityPin(sessionStartEndList.get(0).getUniversityPin());
						buildNextSessionObject.setUniversityPhone(sessionStartEndList.get(0).getUniversityPhone());
						buildNextSessionObject.setUniversityOtherPh(sessionStartEndList.get(0).getUniversityOtherPh());
						buildNextSessionObject.setUniversityFax(sessionStartEndList.get(0).getUniversityFax());
						buildNextSessionObject.setUniversityNickName(sessionStartEndList.get(0).getUniversityNickName());
						buildNextSessionObject.setUniversityState(sessionStartEndList.get(0).getUniversityState());
						buildNextSessionObject.setCountry(sessionStartEndList.get(0).getCountry());
						controlList=getSqlMapClientTemplate().queryForList("buildNextSession.getControlDetails", buildNextSessionObject);
						
						if(controlList.size()==0 ||controlList.get(0).getStatus().equals("E")){
							final BuildNextSession build=buildNextSessionObject;
							
							return (String) transactionTemplate.execute(new TransactionCallback() {
								public Object doInTransaction(TransactionStatus tStatus) {
									Object savepoint = null;
									String msg="";
									try{
										savepoint = tStatus.createSavepoint();
										List<BuildNextSession>nextInactiveSession=getSqlMapClientTemplate().queryForList("buildNextSession.getNextInactiveSession",build);
										if(nextInactiveSession.size()==0){
											getSqlMapClientTemplate().insert("buildNextSession.builNextSession",build);
											loggerObject.info("Successfully inserted into university Master for university "+build.getUniversityId()
													+" session start date: "+build.getNextSessionStartDate()+" session end date: "+build.getNextSessionEndDate());
										}
										else{
											getSqlMapClientTemplate().update("buildNextSession.updateStatusForNexSession",build);
											loggerObject.info("Successfully update university Master: set current status '1' for university "+build.getUniversityId()
													+" session start date: "+build.getNextSessionStartDate()+" session end date: "+build.getNextSessionEndDate());
										}
										getSqlMapClientTemplate().update("buildNextSession.updateStatus",build);
										loggerObject.info("Successfully updatee university Master set current status '0' for university "+build.getUniversityId()
												+" session start date: "+build.getSessionStartDate()+" session end date: "+build.getSessionEndDate());
										build.setStatus("P");
										getSqlMapClientTemplate().insert("buildNextSession.setYearEndProcessControl",build);
										loggerObject.info("Successfully inserted into year end process control table with status 'P' for university "+build.getUniversityId()
												+" session start date: "+build.getNextSessionStartDate()+" session end date: "+build.getNextSessionEndDate());
										msg="success";
									}
									catch(Exception e){
										msg="sqlError";
										loggerObject.error("Error in BuildNextSessionDaoImpl inside method getCurrentSessionDetails"+ e);
										tStatus.rollbackToSavepoint(savepoint);
									}
									return msg;
								}
							});
							
						}
						else if(controlList.get(0).getStatus().equals("P") && controlList.get(0).getSessionStartDate().equals(buildNextSessionObject.getNextSessionStartDate())){
							msg="duplicate";
							return msg;
						}
					}
					else{
						msg="NOProcessCode";
					}
				}
				else{
					msg="lessDate";
					loggerObject.info("In BuildNextSessionDaoImpl Current University Session is not Complete");
					return msg;
				}
			}
			else{
				msg="null";
				loggerObject.info("In BuildNextSessionDaoImpl Ther is no current session in university master");
				return msg;
			}
		}
		catch(ParseException  e){
			msg="dateParseError";
			loggerObject.error("Error in BuildNextSessionDaoImpl inside method getCurrentSessionDetails"+ e);
		}
		catch(Exception e){
			msg="error";
			loggerObject.error("Error in BuildNextSessionDaoImpl inside method getCurrentSessionDetails"+ e);
		}
		return msg;
	}

}
