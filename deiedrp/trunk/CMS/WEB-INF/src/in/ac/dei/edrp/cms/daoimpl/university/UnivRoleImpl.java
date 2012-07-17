/*
 * @(#) UnivRoleImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.university;

import in.ac.dei.edrp.cms.dao.university.RoleConnect;
import in.ac.dei.edrp.cms.daoimpl.employee.EmployeeRoleImpl;
import in.ac.dei.edrp.cms.daoimpl.employee.sendmail;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * This file consist of the methods used for setting up
 * the university role setup.
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class UnivRoleImpl extends SqlMapClientDaoSupport implements RoleConnect {
	
	TransactionTemplate transactionTemplate = null;
    private Logger loggerObject = Logger.getLogger(UnivRoleImpl.class);
    String sep = System.getProperty("file.separator");
    
    
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));

    /**
    * Method to get the list of roles defined in the university
    * @param userId userId of the logged in person
    * @param counter for developer in order to execute queries on choice
    * @return list of roles in the university
    */
    @SuppressWarnings("unchecked")
    public List<UnivRoleInfoGetter> getUniversityRoles(UnivRoleInfoGetter input) {
        List roleList;
        UnivRoleInfoGetter beanobject = new UnivRoleInfoGetter();

        String GROUP_CODE = "UNROLE";

        String uniId = input.getUserId().substring(1, 5);
        beanobject.setUniversityCode(uniId);
        beanobject.setGroupCode(GROUP_CODE);
        beanobject.setEmployeeCode(input.getEmployeeCode());

        try {
            if (input.getCounter().equalsIgnoreCase("one")) {
                roleList = (List) getSqlMapClientTemplate()
                                      .queryForList("unirolesetup.getComponentsInfo",
                        beanobject);

                return roleList;
            } else if (input.getCounter().equalsIgnoreCase("two")) {
                roleList = (List) getSqlMapClientTemplate()
                                      .queryForList("unirolesetup.getsetuniversityroles",
                        beanobject);

                return roleList;
            } else if (input.getCounter().equalsIgnoreCase("three")) {            	
            	
            	beanobject.setApplicationId(input.getApplicationId());
            	
                roleList = (List) getSqlMapClientTemplate()
                .queryForList("unirolesetup.getemployeeroles",
                		beanobject);               
                	
                	return roleList;
                	
}
        } catch (Exception e) {
            loggerObject.error("getUniversityRoles" + e);
        }

        return null;
    }

    /**
     * Method to get the list of links(menu items)
     * @param roleId roleId of the selected role
     * @param userId userId of the application user
     * @return list of links on the application for the role
     */
    @SuppressWarnings("unchecked")
    public List<UnivRoleInfoGetter> getMenuList(UnivRoleInfoGetter input) {
        List menuList;

        String uniId = input.getUserId().substring(1, 5);

        input.setUniversityCode(uniId);

        try {
            menuList = (List) getSqlMapClientTemplate()
                                  .queryForList("unirolesetup.getmenulist",
                    input);

            return menuList;
        } catch (Exception e) {
            loggerObject.error("getmenulist" + e);
        }

        return null;
    }

    /**
     * Method to define role authorities
     * @param menus list of selected menu items
     * @param userId userId of the logged in person
     * @param roleId roleId selected from the interface
     * @return String
     */
    public String setRoleAuthorities(StringTokenizer menus, UnivRoleInfoGetter input) {
        String uniId = input.getUserId().substring(1, 5);

        try {
            UnivRoleInfoGetter beanobject = new UnivRoleInfoGetter();

            while (menus.hasMoreTokens()) {
                beanobject.setMenuItemId(menus.nextToken());
                beanobject.setRoleId(input.getRoleId());
                beanobject.setAuthority(1);
                beanobject.setUniversityCode(uniId);
                beanobject.setCreatorId(input.getUserId());
                beanobject.setApplicationId(input.getApplicationId());

                getSqlMapClientTemplate()
                    .insert("unirolesetup.setroleauthorities", beanobject);
            }
        } catch (Exception e) {
            loggerObject.error("setroleauthorities" + e);
        }

        return "success";
    }

    /**
     * Method to get the list of the menu items already defined for a role
     * @param userId userId of the logged in person
     * @param roleId roleId selected from the list
     * @return list of menu items
     */
    @SuppressWarnings("unchecked")
    public List<UnivRoleInfoGetter> getSetMenuList(UnivRoleInfoGetter input) {
        List menuList;

        String uniId = input.getUserId().substring(1, 5);

        try {

            input.setUniversityCode(uniId);

            menuList = (List) getSqlMapClientTemplate()
                                  .queryForList("unirolesetup.getsetmenulist",
                                		  input);

            return menuList;
        } catch (Exception e) {
            loggerObject.error("getsetmenulist" + e);
            loggerObject.error("exception in getsetmenulist" + e);
        }

        return null;
    }

    /**
     * Method to delete menu items authorities for the selected role
     * @param univ list of selected menu items
     * @param userId userId of the logged in person
     * @param roleId roleId of the above role
     * @return String
     */
    public String deleteUniversityRolesDetails(StringTokenizer univ,
    		UnivRoleInfoGetter input) {
        String uniId = input.getUserId().substring(1, 5);
        UnivRoleInfoGetter beanobject = new UnivRoleInfoGetter();

        try {
            while (univ.hasMoreTokens()) {
                beanobject.setUniversityCode(uniId);
                beanobject.setMenuItemId(univ.nextToken());
                beanobject.setRoleId(input.getRoleId());
                beanobject.setApplicationId(input.getApplicationId());

                getSqlMapClientTemplate()
                    .delete("unirolesetup.deletemenuitemauthority", beanobject);
            }

            return "success";
        } catch (Exception e) {
            loggerObject.error(" in delete setroleauthorities " + e);
        }

        return null;
    }

    /**
     * Method for getting the list of universities from university_master
     * @return List of universities
     */
	@SuppressWarnings("unchecked")
	public List<UnivRoleInfoGetter> getUniversitiesList() {
		
		List universityList = null;
		
		try {
			
			universityList = getSqlMapClientTemplate().queryForList("unirolesetup.getuniversitylist",null);
			
		} catch (Exception e) {
			
			loggerObject.error(" in getUniversitiesList " + e);
			
		}
		
		return universityList;
	}

	/**
	 * Method adds a default user for newly created university 
	 * @param input object of the referenced bean
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String addDefaultUser(final UnivRoleInfoGetter input) {
		
		EmployeeRoleImpl roleImpl = new EmployeeRoleImpl();
		
		String success = "false";
		String dummyID = "0000000000";
		
		UnivRoleInfoGetter getter;
		
		
		
		input.setCreatorId("E"+input.getUniversityCode()+dummyID);
		
		getter = (UnivRoleInfoGetter) getSqlMapClientTemplate().queryForObject("unirolesetup.getuserswithid",input);
		
		if(getter.getAuthority()>0){
			
			success = "duplicate";
			
			return success;
			
		}else{
			
			String password = roleImpl.generatePassword();
			
			input.setComponentDescription(password);
			
			try {		
			
			if(input.getCounter().equalsIgnoreCase("insert")){			
				
				success = (String) transactionTemplate.execute(new TransactionCallback() {
					
					public Object doInTransaction(TransactionStatus transaction) {
						
						List menuList = null;
						
						Object savePoint = null;
						
						String flag = "false";						
						
						menuList = getSqlMapClientTemplate().queryForList("unirolesetup.getmenulists",input);
						
						Iterator iterator = menuList.iterator();
						
						try {
							
							savePoint = transaction.createSavepoint();	
						
						while (iterator.hasNext()) {
							UnivRoleInfoGetter object = (UnivRoleInfoGetter) iterator.next();
							
							input.setMenuItemId(object.getComponentId());										
							
							getSqlMapClientTemplate().insert("unirolesetup.insertdefaultitems",input);							
							
						}
						System.out.println(input.getComponentDescription()+"password");
						getSqlMapClientTemplate().insert("unirolesetup.insertdefrole",input);
						
						getSqlMapClientTemplate().insert("unirolesetup.insertdefaultuser",input);	
						
						flag = "true";
						
						} catch (Exception e) {
							System.out.println("inside default"+e);
							transaction.rollbackToSavepoint(savePoint);
							
							flag="false";
							
						}
						
						return flag;
					}
				});			
				
			}else if(input.getCounter().equalsIgnoreCase("update")){			
				
				getSqlMapClientTemplate().insert("unirolesetup.updatedefaultuser",input);
				
				success = "true";
				
			}
			
			String msg = "Your Account Information is as follows:\nUser Name: "
				+ input.getComponentId()
				+ "\nRole:"
				+ "Administrator"
				+ "\nPassword:" + input.getComponentDescription();
			msg = msg
				+ "\n\n\nClick here to activate your account.\n\n "
				+ resourceBundle.getString("url")
				+ "/sendPassword/activateAccount.htm?userId="
				+ input.getCreatorId()
				+ "&UI="
				+ input.getUniversityCode()
				+ "&AIUI="
				+ "UI"
				+ "&asahs=asnasa&dssssss=%ab$$gfff";
			
			/*sendmail(message,email id(reciever),email id(sender),subject)*/
				sendmail.main(msg,
					input.getComponentId(),
					resourceBundle.getString("emailId"),
					resourceBundle.getString("subject"));
			} catch (Exception e) {
				loggerObject.error(" in addDefaultUser " + e);
			}			
		}
		
		
		
		return success;
	}

	/**
	 * Method for getting the list of universities for who 
	 * login already exists in the database 
	 * @param input object of the referenced bean
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<UnivRoleInfoGetter> getUniversitieswithLogins(UnivRoleInfoGetter input) {
		
		List universityList = null;
		
		try {
			
			universityList = getSqlMapClientTemplate().queryForList("unirolesetup.getuniversityloginlist",input);
			
		} catch (Exception e) {
			
			loggerObject.error(" in getUniversitiesList " + e);
			
		}
		
		return universityList;
	}
}
