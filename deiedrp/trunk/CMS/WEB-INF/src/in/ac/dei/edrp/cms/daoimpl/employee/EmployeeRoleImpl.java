/*
 * @(#) EmployeeRoleImpl.java
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

import in.ac.dei.edrp.cms.dao.employee.EmployeeRoleConnect;
import in.ac.dei.edrp.cms.domain.employee.EmployeeRoleInfoGetter;
import in.ac.dei.edrp.cms.domain.login.Login;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *This file consist of the methods used for setting up the employee role setup.
 * 
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class EmployeeRoleImpl extends SqlMapClientDaoSupport implements
		EmployeeRoleConnect {
	private Logger loggerObject = Logger.getLogger(EmployeeRoleImpl.class);

	/**
	 * Method to get details of the concerned employee whose employee code is
	 * passed from the interface.
	 * 
	 * @param userId
	 *            userId of the user who is accessing the interface
	 * @param employeecode
	 *            code of the employee.
	 * @return employee details
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeRoleInfoGetter> getEmployeeDetails(
			EmployeeRoleInfoGetter input) {
		String gender = "GENDER";
		String category = "STDCTG";

		List detailsList;

		input.setUniversityCode(input.getUserId().substring(1, 5));
		input.setGenderCode(gender);
		input.setCategoryCode(category);

		try {
			detailsList = (List) getSqlMapClientTemplate().queryForList(
					"assignroletoemployee.getemployeedetails", input);

			return detailsList;
		} catch (Exception e) {
			loggerObject.error("getEmployeeDetails" + e);
		}

		return null;
	}

	/**
	 * Method to assign a role to the employee of the university
	 * 
	 * @param userId
	 *            userId of the of the user who is accessing the interface
	 * @param employeeCode
	 *            employee code to be used as login id
	 * @param roleId
	 *            roleId of the role assigned to the employee
	 * @param email
	 *            mail id of the employee
	 * @param roleDescription
	 *            name of the role assigned to the employee
	 * @param employeeId
	 *            id of the employee of the university
	 * @return String
	 */
	public List<EmployeeRoleInfoGetter> setEmployeeRole(
			EmployeeRoleInfoGetter input) {
		
		input.setUniversityCode(input.getUserId().substring(1,5));
		//DS
		String universityId = input.getUserId().substring(1,5);
		try {

			EmployeeRoleInfoGetter employeeRoleInfoGetter = new EmployeeRoleInfoGetter();
			
			EmployeeRoleInfoGetter employeeIdInfoGetter = new EmployeeRoleInfoGetter();

			List<EmployeeRoleInfoGetter> employeeRoleObject = new ArrayList<EmployeeRoleInfoGetter>();
			
			employeeIdInfoGetter = (EmployeeRoleInfoGetter) getSqlMapClientTemplate().queryForObject(
					"assignroletoemployee.selectEmployeeId",
					input);			

			employeeRoleInfoGetter = (EmployeeRoleInfoGetter) getSqlMapClientTemplate()
					.queryForObject(
							"assignroletoemployee.getpasswordforemployee",
							input);
			
			input.setComponentDescription(input.getEmployeeCode());

			input.setStatus("ACT");

			if (employeeRoleInfoGetter == null) {

				String password = generatePassword();
				//DS
				sendMailtoUser(input.getEmployeeCode(), password, input
						.getPrimaryEmailId(), input.getRoleDescription(), universityId);

				input.setPassword(password);
				input.setComponentId(password);
				input.setEmployeeId(employeeIdInfoGetter.getEmployeeId());

				getSqlMapClientTemplate().insert(
						"assignroletoemployee.insertemployeerole", input);

			} else {
				//DS
				sendMailtoUser(input.getEmployeeCode(),
						"You can use the same password for all logins", input
								.getPrimaryEmailId(), input
								.getRoleDescription(), universityId);

				input.setPassword(employeeRoleInfoGetter.getPassword());
				input.setComponentId(employeeRoleInfoGetter.getPassword());
				input.setEmployeeId(employeeIdInfoGetter.getEmployeeId());

				getSqlMapClientTemplate().insert(
						"assignroletoemployee.insertemployeelogin", input);

			}

			employeeRoleObject.add(input);

			return employeeRoleObject;
		} catch (Exception e) {
			loggerObject.error("Exception in setEmployeeRole" + e);
		}

		return null;
	}

	/**
	 * Method to generate a string(password).
	 * 
	 * @return auto-generated string(password)
	 */
	public String generatePassword() {
		Random r = new Random();
		int i = 1;
		int n = 0;
		char c;
		String str = "";

		for (int t = 0; t < 3; t++) {
			while (true) {
				i = r.nextInt(10);

				if ((i > 5) && (i < 10)) {
					if (i == 9) {
						i = 90;
						n = 90;

						break;
					}

					if (i != 90) {
						n = (i * 10) + r.nextInt(10);

						while (n < 65) {
							n = (i * 10) + r.nextInt(10);
						}
					}

					break;
				}
			}

			c = (char) n;

			str = String.valueOf(c) + str;
		}

		while (true) {
			i = r.nextInt(100000);

			if (i > 9999) {
				break;
			}
		}

		str = str + i;
		loggerObject.info("password :" + str);

		return str;
	}

	/**
	 * Method to send mail to the user for delivering account access details
	 * 
	 * @param userName
	 *            userName to be used as login id
	 * @param password
	 *            password to access the application
	 * @param email
	 *            mail-id of the employee
	 * @param role
	 *            role assigned to the employee
	 * @return String
	 */
	public String sendMailtoUser(String userName, String password,
			String email, String role, String universityId) {
		String text = "Your Account Information is as follows :" + "\n"
				+ "Role :" + role + "\n" + "userName :" + userName + "\n"
				+ "Password :" + password;
		String to = email;
		String sep=System.getProperty("file.separator");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in"+sep+"ac"+sep+"dei"+sep+"edrp"+sep+"cms"+sep+"databasesetting"+sep+"MessageProperties",
    			new Locale("en", "US"));
		String from = resourceBundle.getString("emailId");
		String subject = resourceBundle.getString("subject");

		try {			
			sendmail.main(text, to, subject, universityId);

			return "success";
		} catch (Exception e) {
			loggerObject.error("exception in sending mail" + e);
		}

		return null;
	}

	/**
	 * Method generates the list of menus(links) for the role assigned to the
	 * user
	 * 
	 * @param userId
	 *            userId of the concerned user
	 * @param employeeCode
	 *            employeeCode to be used as login id
	 * @param counter
	 *            counter for different processes
	 * @return list of menu items for the concerned role
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeRoleInfoGetter> getMenuOnRole(String userId,
			String employeeCode, String counter) {
		List roleList;

		EmployeeRoleInfoGetter beanobject = new EmployeeRoleInfoGetter();

		beanobject.setUniversityCode(userId.substring(1, 5));
		beanobject.setEmployeeCode(employeeCode);

		try {
			if (counter.equalsIgnoreCase("one")) {
				roleList = getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getrolemenulist", beanobject);

				return roleList;
			}

			if (counter.equalsIgnoreCase("two")) {
				roleList = getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getusermenulist", beanobject);

				return roleList;
			}
		} catch (Exception e) {
			loggerObject.error("exception" + e);
		}

		return null;
	}

	/**
	 * Method insert/update authorities on a link for the concerned user
	 * 
	 * @param userId
	 *            creator id
	 * @param employeeCode
	 *            user whose authority is to be altered
	 * @param menuItemId
	 *            id of the link
	 * @param secondaryAuthority
	 *            authority on the link
	 * @param counter
	 *            counter for insertion/update ping
	 * @param employeeId
	 *            system generated id of the employee
	 * @return String
	 */
	public String setUserAuthoroties(EmployeeRoleInfoGetter input) {
		try {
			if (input.getCounter().equalsIgnoreCase("one")) {
				input.setUniversityCode(input.getUserId().substring(1, 5));

				getSqlMapClientTemplate().insert(
						"assignroletoemployee.insertintofunctionauthority",
						input);

				return "success";
			}

			if (input.getCounter().equalsIgnoreCase("two")) {
				getSqlMapClientTemplate().update(
						"assignroletoemployee.updateintofunctionauthority",
						input);

				return "success";
			}
		} catch (Exception e) {
			loggerObject.error("Exception in setUserAuthoroties" + e);
		}

		return null;
	}

	/**
	 * Method for getting the users of the application for the university for
	 * redefining authorities on links associated with the concrened role.
	 * 
	 * @param input
	 *            object of the referenced bean
	 * @return list of users
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeRoleInfoGetter> getUsers(EmployeeRoleInfoGetter input) {

		List detailsList;

		input.setUniversityCode(input.getUserId().substring(1, 5));

		try {

			if (input.getCounter().equalsIgnoreCase("one")) {

				detailsList = (List) getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getUsers", input);

				return detailsList;

			} else if (input.getCounter().equalsIgnoreCase("two")) {

				detailsList = (List) getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getUsersForManage", input);

				return detailsList;

			}

		} catch (Exception e) {
			loggerObject.error("getEmployeeDetails" + e);
		}

		return null;
	}

	/**
     * Method for getting the users of the application for the university
     * for deleting roles already assigned to them
     * @param input object of the referenced bean
     * @return list of users
     */
	@SuppressWarnings("unchecked")
	public List<EmployeeRoleInfoGetter> getEmployees(
			EmployeeRoleInfoGetter input) {
		
		List detailsList;
		
		try {
			
			if(input.getCounter().equalsIgnoreCase("one")){
				
				detailsList = (List) getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getDistinctUsers", input);
				
				return detailsList;
				
				
			}else if(input.getCounter().equalsIgnoreCase("two")){
				
				detailsList = (List) getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getuserdetails", input);
				
				return detailsList;
				
			}
			
		} catch (Exception e) {
			loggerObject.error("getEmployeeDetails" + e);			
		}		
		
		return null;
	}

	/**
     * Method for deleting roles already assigned to selected employee
     * @param input object of the referenced bean
     * @return Success
     */
	public String deleteEmployeesRecords(
			EmployeeRoleInfoGetter input) {
		
		String success="failure";
		
		try{
			
			getSqlMapClientTemplate().delete(
					"assignroletoemployee.deleteRole", input);
			
			success = "success";
			
			
		}catch (Exception e) {
			loggerObject.error("deleteEmployeesRecords" + e);
		}
		
		
		return success;
	}


	@SuppressWarnings("unchecked")
	public List<EmployeeRoleInfoGetter> getApplicationUserDetails(
			EmployeeRoleInfoGetter input) {	
		
		String gender = "GENDER";
		String category = "STDCTG";

		List detailsList = null;

		input.setUniversityCode(input.getUserId().substring(1, 5));
		input.setGenderCode(gender);
		input.setCategoryCode(category);

		try {
			
			if(input.getCounter().equalsIgnoreCase("one")){
				
				/*
				 * for new applicants
				 */				
				if(input.getRoleId().equalsIgnoreCase("app")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getselectedapplicantdetails", input);
					
				}
				/*
				 * for employees
				 */	
				else if(input.getRoleId().equalsIgnoreCase("emp")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getselectedemployeedetails", input);
					
				}
				/*
				 * for students
				 */	
				else if(input.getRoleId().equalsIgnoreCase("stu")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getselectedstudentdetails", input);
					
				}
				/*
				 * for students(enrollment form login)
				 */	
				else if(input.getRoleId().equalsIgnoreCase("enr")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getselectedstudentenrdetails", input);
					
				}
				
				
			}else if(input.getCounter().equalsIgnoreCase("two")){				
				
				/*
				 * for new applicants
				 */				
				if(input.getRoleId().equalsIgnoreCase("app")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getlikeapplicantdetails", input);
					
				}
				/*
				 * for employees
				 */	
				else if(input.getRoleId().equalsIgnoreCase("emp")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getlikedemployeedetails", input);
					
				}
				/*
				 * for students
				 */	
				else if(input.getRoleId().equalsIgnoreCase("stu")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getlikestudentdetails", input);
					
				}
				/*
				 * for students(enrollment form login)
				 */	
				else if(input.getRoleId().equalsIgnoreCase("enr")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getlikestudentenrdetails", input);
					
				}
				
			}else if(input.getCounter().equalsIgnoreCase("three")){
				
				/*
				 * for students
				 */	
				if(input.getRoleId().equalsIgnoreCase("stu")){
					
					detailsList = (List) getSqlMapClientTemplate().queryForList(
							"assignroletoemployee.getstustudentdetails", input);
					
				}
				
			}else if(input.getCounter().equalsIgnoreCase("four")){
				
				detailsList = (List) getSqlMapClientTemplate().queryForList(
						"assignroletoemployee.getlikestustudentdetails", input);
				
			}

			return detailsList;
		} catch (Exception e) {
			loggerObject.error("getEmployeeDetails" + e);
		}
		
		
		return null;
	}

	public String ResetEmployeePassword(EmployeeRoleInfoGetter input) {
		
		String success="failure";
		//DS
		String universityId = input.getUniversityCode();
		try{
			
			String password = generatePassword();
			
			if(!input.getPrimaryEmailId().equalsIgnoreCase("")){
				//DS
				sendMailtoUser(input.getEmployeeCode(), password, input
						.getPrimaryEmailId(), "Unchanged", universityId);
				
			}			
			input.setPassword(password);
			
			if((input.getRoleId().equalsIgnoreCase("app"))||(input.getRoleId().equalsIgnoreCase("enr"))){
				
				getSqlMapClientTemplate().update(
						"assignroletoemployee.resetpasswordforapplicants", input);
				
				success = password;			
				
			}else{				
				getSqlMapClientTemplate().update("assignroletoemployee.resetpassword", input);
				Login login=new Login();
				login.setAttempt(0);
				login.setUserName(input.getEmployeeCode());
				login.setApplication(input.getStatus());
				getSqlMapClientTemplate().update("login.updatetLoginAttempt", login);
				success = password;				
			}		
			
		}catch (Exception e) {
			loggerObject.error("deleteEmployeesRecords" + e);
		}
		
		
		return success;
	}
}
