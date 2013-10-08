/*
 * @(#) EmployeeRoleController.java
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
package in.ac.dei.edrp.cms.controller.employee;

import in.ac.dei.edrp.cms.dao.employee.EmployeeRoleConnect;
import in.ac.dei.edrp.cms.domain.employee.EmployeeRoleInfoGetter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * This controller is designed for setting & retrieving
 * information about the employee of a university
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class EmployeeRoleController extends MultiActionController {
    private EmployeeRoleConnect employeeRoleConnect;

    /**
     * The setter method of the interface associated
     * with this controller
     * @param employeeRoleConnect
     */
    public void setEmployeeRoleConnect(EmployeeRoleConnect employeeRoleConnect) {
        this.employeeRoleConnect = employeeRoleConnect;
    }

    /**
    * Method for getting the details of the employee
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    public ModelAndView getEmployeeDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setUserName(session.getAttribute("userName").toString());

        List<EmployeeRoleInfoGetter> resultEmployeeDetails = employeeRoleConnect.getEmployeeDetails(input);

        return new ModelAndView("EmployeeRole/EmployeeDetails", "resultObject",
            resultEmployeeDetails);
    }

    /**
     * Method for defining role for the employee
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView setEmployeeRole(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setRoleId(request.getParameter("roleId"));
        input.setPrimaryEmailId(request.getParameter("mailId"));
        input.setRoleDescription(request.getParameter("roleDescription"));
        input.setEmployeeId(request.getParameter("employeeId"));
        input.setDesignation(request.getParameter("applicationId"));

        List<EmployeeRoleInfoGetter> resultUpdateDetails = employeeRoleConnect.setEmployeeRole(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultUpdateDetails);
    }

    /**
     * Method for retrieving the list of menu items concerned to the role
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getmenusforrole(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setCounter("one");

        List<EmployeeRoleInfoGetter> resultGetMenus = employeeRoleConnect.getMenuOnRole(input.getUserId(),
                input.getEmployeeCode(), input.getCounter());

        return new ModelAndView("EmployeeRole/EmployeeRoleItems",
            "resultObject", resultGetMenus);
    }

    /**
     * Method for updating authority on a menu item for the concerned employee
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView setSecondaryAuthority(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setSecondaryAuthority(request.getParameter("authority"));
        input.setEmployeeId(request.getParameter("employeeId"));
        input.setCounter("one");
        input.setMenuItemId(request.getParameter("menuItemId"));

        String resultSetauthorities = employeeRoleConnect.setUserAuthoroties(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultSetauthorities);
    }

    /**
     * Method for managing the authority set on a menu item for the concerned employee
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getmenusforemployee(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setCounter("two");

        List<EmployeeRoleInfoGetter> resultGetMenuOnRole = employeeRoleConnect.getMenuOnRole(input.getUserId(),
                input.getEmployeeCode(), input.getCounter());

        return new ModelAndView("EmployeeRole/setItemsAuthority",
            "resultObject", resultGetMenuOnRole);
    }

    /**
     * Method for updating secondary authority on any particular menu item.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView updateSecondaryAuthority(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setSecondaryAuthority(request.getParameter("authority"));
        input.setEmployeeId(request.getParameter("employeeId"));
        input.setCounter("two");
        input.setMenuItemId(request.getParameter("menuItemId"));

        String resultSetUserAuthoroties = employeeRoleConnect.setUserAuthoroties(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultSetUserAuthoroties);
    }
    
    /**
     * Method for getting the users of the application for the university
     * for redefining authorities on links associated with the concrened role.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
     public ModelAndView getUsers(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
         EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
         HttpSession session = request.getSession(true);

         String userId = (String) session.getAttribute("userId");
         String employeeCode = (String) session.getAttribute("userName");
 		
 		if(userId == null)
         {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
         }
 		
 		input.setUserId(userId);
         input.setEmployeeCode(employeeCode);
         input.setCounter(request.getParameter("counter"));

         List<EmployeeRoleInfoGetter> resultEmployeeDetails = employeeRoleConnect.getUsers(input);

         return new ModelAndView("UniversityRolesSetup/UniversityRoles", "resultObject",
             resultEmployeeDetails);
     }
     
     /**
      * Method for getting the employees for deletion
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ModelAndView getApplicationUsers(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
         EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
         HttpSession session = request.getSession(true);         

         String userId = (String) session.getAttribute("userId");
 		
 		if(userId == null)
         {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
         } 		
 			input.setEmployeeCode(session.getAttribute("userName").toString());
 			input.setUniversityCode("E"+userId.substring(1,5)+"%");
 			input.setCounter("one");

         List<EmployeeRoleInfoGetter> resultUserDetails = employeeRoleConnect.getEmployees(input);
         
         

         return new ModelAndView("UniversityRolesSetup/UniversityRoles",
             "resultObject", resultUserDetails);
     }
     
     /**
      * Method for getting the employees for deletion
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ModelAndView getUserDetails(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
         EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
         HttpSession session = request.getSession(true);         

         String userId = (String) session.getAttribute("userId");
 		
 		if(userId == null)
         {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
         } 		
 			input.setUniversityCode("E"+userId.substring(1,5)+"%");
 			input.setEmployeeCode(request.getParameter("employeeCode"));
 			input.setCounter("two");
 			input.setUserId(session.getAttribute("universityId").toString());

         List<EmployeeRoleInfoGetter> resultUserDetails = employeeRoleConnect.getEmployees(input);
         
         

         return new ModelAndView("EmployeeRole/ApplicationUsers",
             "resultObject", resultUserDetails);
     }
     
     /**
      * Method for deleting role for the employee
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
     public ModelAndView deleteUsers(HttpServletRequest request,
         HttpServletResponse response) throws Exception {
         EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
         HttpSession session = request.getSession(true);         

         String userId = (String) session.getAttribute("userId");
 		
 		if(userId == null)
         {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
         }		
 			input.setUniversityCode("E"+userId.substring(1,5)+"%");
 			input.setEmployeeCode(request.getParameter("employeeCode"));
 			input.setEmployeeRole(request.getParameter("userGroupId"));
 			input.setDesignation(request.getParameter("applicationId"));

 			String resultUserDetails = employeeRoleConnect.deleteEmployeesRecords(input);
         
         

         return new ModelAndView("preProcessChecks/preProcessResultlist",
             "resultObject", resultUserDetails);
     }
     
     /**
      * Method for getting the details of the employee
      * @param request
      * @param response
      * @return
      * @throws Exception
      */
      public ModelAndView getApplicationUserDetails(HttpServletRequest request,
          HttpServletResponse response) throws Exception {
          EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
          HttpSession session = request.getSession(true);

          String userId = (String) session.getAttribute("userId");
  		
  		if(userId == null)
          {
          return new ModelAndView("general/SessionInactive","sessionInactive",true);
          }
  		
  			input.setUserId(userId);
  			input.setUniversityCode(session.getAttribute("universityId").toString());
  			input.setUserName(request.getParameter("userName"));
  			input.setRoleId(request.getParameter("userType"));
  			input.setEmployeeMiddleName(request.getParameter("name"));
  			if(request.getParameter("dateofbirth").toString().equalsIgnoreCase("")){
  				
  				input.setDateOfBirth("%");
  				
  			}else{
  				input.setDateOfBirth(request.getParameter("dateofbirth"));
  			}  			
  			input.setEmployeeLastName(request.getParameter("fathername"));
  			input.setCounter(request.getParameter("counter"));
  			
  			List<EmployeeRoleInfoGetter> resultEmployeeDetails = employeeRoleConnect.getApplicationUserDetails(input);

          return new ModelAndView("EmployeeRole/EmployeeDetails", "resultObject",
              resultEmployeeDetails);
      }
      
      /**
       * Method for reseting password of the employee
       * @param request
       * @param response
       * @return
       * @throws Exception
       */
      public ModelAndView ResetPassword(HttpServletRequest request,
          HttpServletResponse response) throws Exception {
          EmployeeRoleInfoGetter input = new EmployeeRoleInfoGetter();
          HttpSession session = request.getSession(true);         
          String userId = (String) session.getAttribute("userId");  		
  		  if(userId == null){
  			  return new ModelAndView("general/SessionInactive","sessionInactive",true);
  		  }		
			input.setUniversityCode(userId.substring(1,5));
			input.setEmployeeCode(request.getParameter("employeeCode"));
			input.setRoleId(request.getParameter("userType"));
			input.setPrimaryEmailId(request.getParameter("emailId"));
			input.setStatus(request.getParameter("application"));
			input.setUserId(userId);
  			String resultUserDetails = employeeRoleConnect.ResetEmployeePassword(input);         
          return new ModelAndView("preProcessChecks/preProcessResultlist", "resultObject", resultUserDetails);
      }
     
}
