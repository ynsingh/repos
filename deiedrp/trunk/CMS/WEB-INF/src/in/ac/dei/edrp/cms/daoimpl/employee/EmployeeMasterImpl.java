/*
 * @(#) EmployeeMasterImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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
package in.ac.dei.edrp.cms.daoimpl.employee;

import in.ac.dei.edrp.cms.dao.employee.EmployeeMasterConnect;
import in.ac.dei.edrp.cms.domain.employee.EmployeeMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * This file consist of the methods used for setting up the employee master
 * setup.
 * 
 * @author Ashish
 * @date 21 Feb 2011
 * @version 1.0
 */
public class EmployeeMasterImpl extends SqlMapClientDaoSupport implements
		EmployeeMasterConnect {
	TransactionTemplate transactionTemplate = null;
	private Logger loggerObject = Logger.getLogger(EmployeeMasterImpl.class);

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * This method retrieves the list of parent entities in the university
	 * 
	 * @param userId
	 *            user id of the user who is accessing the application
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getParentEntity(String userId) {
		String universityCode = userId.substring(1, 5);
		List parentEntityList;
		// UnivRoleInfoGetter univObject;

		parentEntityList = getSqlMapClientTemplate().queryForList(
				"employeemaster.getparentEntity", universityCode);

		// univObject = (UnivRoleInfoGetter) getSqlMapClientTemplate()
		// .queryForObject("employeemaster.getuniversity", universityCode);

		// parentEntityList.add(univObject);

		return parentEntityList;
	}

	/**
	 * This methods retrieves the list of designations(active) for the concerned
	 * university
	 * 
	 * @param userId
	 *            user id of the user who is accessing the application
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getDesignations(String userId) {
		String universityCode = userId.substring(1, 5);
		List designationslist;
		String GROUP_CODE = "DESGNS";
		UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

		beanObject.setGroupCode(GROUP_CODE);
		beanObject.setUniversityCode(universityCode);

		designationslist = getSqlMapClientTemplate().queryForList(
				"unirolesetup.getComponentsInfo", beanObject);

		return designationslist;
	}

	/**
	 * This method is used to define/manage the employee profile for the
	 * university
	 * 
	 * @param input
	 *            details of the employee
	 * @return String
	 * @throws Exception
	 */
	public String setEmployeeProfile(final EmployeeMasterInfoGetter input)
			throws Exception {
		final String universityCode = input.getUserId().substring(1, 5);
		final StudentNumbersInfoGetter systemvalue = new StudentNumbersInfoGetter();
		EmployeeMasterInfoGetter masterInfoGetter = new EmployeeMasterInfoGetter();

		String bool = "false";
		
		

		masterInfoGetter = (EmployeeMasterInfoGetter) getSqlMapClient()
				.queryForObject("employeemaster.getduplicateemployee", input);

		if ((input.getActivity().equalsIgnoreCase("insert"))
				&& (masterInfoGetter.getComponentId().equalsIgnoreCase("1"))) {

			return "duplicate";

		}
		if (input.getActivity().equalsIgnoreCase("insert")) {
			
			masterInfoGetter = (EmployeeMasterInfoGetter) getSqlMapClient()
			.queryForObject("employeemaster.getduplicateemployeecodes", input);
			
			if(masterInfoGetter.getComponentId().equalsIgnoreCase("0")){
				
				bool = (String) transactionTemplate
				.execute(new TransactionCallback() {
					public String doInTransaction(
							TransactionStatus transaction) {
						/*
						 * to create a point to which the function revert
						 * when an exception is encountered during the
						 * process
						 */
						Object savepoint = null;

						String b;

						/*
						 * to get the (counter value) for employee code
						 */
						StudentNumbersInfoGetter sysObject;

						/*
						 * to get the session of the university
						 */
						UniversityMasterInfoGetter sessionObject;

						String employeeCode = null;

						systemvalue.setUniversityId(universityCode);
						systemvalue.setCode("EMPCOD");
						/*
						 * Query for getting the value from system_values
						 * for the above binded code
						 */
						sysObject = (StudentNumbersInfoGetter) getSqlMapClientTemplate()
								.queryForObject(
										"studentenrollment.sysvalue",
										systemvalue);

						/*
						 * Query for getting the university session dates
						 */
						sessionObject = (UniversityMasterInfoGetter) getSqlMapClientTemplate()
								.queryForObject(
										"universityMaster.getUniversitySession",
										universityCode);

						try {
							savepoint = transaction.createSavepoint();

							int value = Integer.parseInt(sysObject
									.getSystemValue()) + 1;

							employeeCode = String.format("%03d", value);

							systemvalue.setSystemValue(employeeCode);
							/*
							 * Query for updating the for the binded code
							 */
							getSqlMapClientTemplate().update(
									"studentenrollment.updatesysvalue",
									systemvalue);

							input.setEmployeeCode(input.getEmployeeCodeUpdate());
							
							/*
							 * code temp. removed
							 */
//							input
//									.setEmployeeCode("E"
//											+ sessionObject
//													.getSessionStartDate()
//													.substring(2, 4)
//											+ employeeCode);
							input.setUserType("EMP");
							input.setUniversityCode(universityCode);
							input
									.setEmployeeId("E"
											+ input.getParentEntity()
											+ sessionObject
													.getSessionStartDate()
													.substring(2, 4)
											+ employeeCode);
							/*
							 * Query for inserting basic information of the
							 * employee
							 */
							getSqlMapClientTemplate()
									.insert(
											"employeemaster.inputemployeebasicdetails",
											input);
							/*
							 * Query for inserting permanent address
							 * information of the employee
							 */
							getSqlMapClientTemplate()
									.insert(
											"employeemaster.inputpermanentaddressdetails",
											input);
							/*
							 * Query for inserting correspondence address
							 * information of the employee
							 */
							getSqlMapClientTemplate()
									.insert(
											"employeemaster.inputcoresspondenceaddressdetails",
											input);

							b = "true";

						} catch (Exception e) {
							loggerObject.info("connection rollback..." + e);
							transaction.rollbackToSavepoint(savepoint);
							b = "false"+e;
						}
						return b;
					}
				});
				
			}else{
				
				return "duplicateemployeecode";
				
			}
			
			
		} else if (input.getActivity().equalsIgnoreCase("update")) {

			EmployeeMasterInfoGetter infoGetter = null;
			
			/*
			 * to get the session of the university
			 */
			UniversityMasterInfoGetter sessionObject;
			
			/*
			 * Query for getting the university session dates
			 */
			sessionObject = (UniversityMasterInfoGetter) getSqlMapClientTemplate()
					.queryForObject(
							"universityMaster.getUniversitySession",
							universityCode);
			
			input.setUniversityCode(universityCode);			

			infoGetter = (EmployeeMasterInfoGetter) getSqlMapClient()
					.queryForObject("employeemaster.getEmployeeId",
							input);
			
			input.setOldEmployeeId(infoGetter.getOldEmployeeId());
			
			String oldEmployeeCode = infoGetter.getOldEmployeeId().substring(11);
			/*
			 * code temp.removed	
			 */
//			String oldEmployeeCode = input.getEmployeeCodeUpdate().substring(3);
			input.setEmployeeId("E"+input.getParentEntity()+
					sessionObject.getSessionStartDate().substring(2, 4)+ oldEmployeeCode);
			 return ""+getSqlMapClientTemplate().update("employeemaster.updateEmployeeDetails", input);

			
		}

		if (bool.equalsIgnoreCase("true")) {

			return input.getEmployeeCode();

		} else {

			return bool;

		}

	}

	/**
	 * This method retrieves the details of the concerned employee for
	 * managing(update basic details).
	 * 
	 * @param input
	 *            details of the employee(employee code)
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getEmployeeDetails(
			EmployeeMasterInfoGetter input) {
		List employeeDetailsList;
		
		input.setUniversityCode(input.getUserId().substring(1,5));

		employeeDetailsList = getSqlMapClientTemplate().queryForList(
				"employeemaster.getEmployeeDetails", input);

		return employeeDetailsList;
	}

	/**
	 * This method retrieves the addresses information of the concerned employee
	 * for managing(updating address details)
	 * 
	 * @param input
	 *            details of the employee(employee code)
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getEmployeeAddressDetails(
			EmployeeMasterInfoGetter input) {
		List employeeDetailsList;

		employeeDetailsList = getSqlMapClientTemplate().queryForList(
				"employeemaster.getAddressDetails", input);

		return employeeDetailsList;
	}

	/**
	 * The method retrives the category defined for the university
	 * 
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getCategoryDetails(
			EmployeeMasterInfoGetter input) {
		String universityCode = input.getUserId().substring(1, 5);
		List categoryList;
		String GROUP_CODE = "STDCTG";

		UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

		beanObject.setGroupCode(GROUP_CODE);
		beanObject.setUniversityCode(universityCode);

		categoryList = getSqlMapClientTemplate().queryForList(
				"unirolesetup.getComponentsInfo", beanObject);

		return categoryList;
	}

	/**
	 * The method retrives the genders defined for the university
	 * 
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getGenderDetails(
			EmployeeMasterInfoGetter input) {
		String universityCode = input.getUserId().substring(1, 5);
		List genderList;
		String GROUP_CODE = "GENDER";

		UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

		beanObject.setGroupCode(GROUP_CODE);
		beanObject.setUniversityCode(universityCode);

		genderList = getSqlMapClientTemplate().queryForList(
				"unirolesetup.getComponentsInfo", beanObject);

		return genderList;
	}

	/**
	 * The method retrives the genders minority groups for the university
	 * 
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getMinorityDetails(
			EmployeeMasterInfoGetter input) {
		String universityCode = input.getUserId().substring(1, 5);
		List minoritylist;
		String GROUP_CODE = "MNORTY";
		UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

		beanObject.setGroupCode(GROUP_CODE);
		beanObject.setUniversityCode(universityCode);

		minoritylist = getSqlMapClientTemplate().queryForList(
				"unirolesetup.getComponentsInfo", beanObject);

		return minoritylist;
	}

	/**
	 * The method retrives the pension descriptions defined for the university
	 * 
	 * @param input
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterInfoGetter> getPensionDetails(
			EmployeeMasterInfoGetter input) {
		String universityCode = input.getUserId().substring(1, 5);
		List pensionlist;
		String GROUP_CODE = "PENSON";
		UnivRoleInfoGetter beanObject = new UnivRoleInfoGetter();

		beanObject.setGroupCode(GROUP_CODE);
		beanObject.setUniversityCode(universityCode);

		pensionlist = getSqlMapClientTemplate().queryForList(
				"unirolesetup.getComponentsInfo", beanObject);

		return pensionlist;
	}
}
