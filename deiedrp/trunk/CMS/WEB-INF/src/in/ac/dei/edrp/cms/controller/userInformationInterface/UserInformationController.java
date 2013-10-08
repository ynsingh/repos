/**
 * @(#) UserInformationController.java
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
package in.ac.dei.edrp.cms.controller.userInformationInterface;

import in.ac.dei.edrp.cms.dao.userInformationInterface.UserInformationService;
import in.ac.dei.edrp.cms.domain.userInformationInterface.UserInformationDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving database information for
 * Interface UserDataInterface
 * @author Devendra Singhal
 * @date Nov 09 2012
 */
public class UserInformationController extends MultiActionController{
	//Create UserInformationService interface reference variable
	private UserInformationService userInfoservice;
	//Setter method for UserInformationService interface reference variable
	public void setUserInfoService(UserInformationService userInfoservice) {
		this.userInfoservice = userInfoservice;
	}
	
	/**
	 * Method to get all database name
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @return Modal and view contain database name information list
	 **/
	public ModelAndView getDatabase(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);		
		if(session == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}		
		List<UserInformationDomain>list=userInfoservice.getDatabase();
		return new ModelAndView("userInformationInterface/databaseInformation", "resultObject",list);

	}
	
	/**
	 * Method to get all database name
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @return Modal and view contain table name list
	 **/
	public ModelAndView getTables(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);		
		if(session == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}		
		UserInformationDomain input=new UserInformationDomain();
		input.setDatabaseName(request.getParameter("database"));
		input.setUniversityId((String)session.getAttribute("universityId"));
		List<UserInformationDomain>list=userInfoservice.getTables(input);
		return new ModelAndView("userInformationInterface/databaseInformation", "resultObject",list);

	}
	/**
	 * Method to get columns for selected tables
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @return Modal and view contain columns list
	 **/
	public ModelAndView getColumn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);		
		if(session == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}		
		UserInformationDomain input=new UserInformationDomain();
		input.setDatabaseName(request.getParameter("database"));
		String str=request.getParameter("tables");		
		StringTokenizer tkn=new StringTokenizer(str,",");
		List<String>list=new ArrayList<String>();
		while(tkn.hasMoreTokens()){
			list.add(tkn.nextToken());
		}
		input.setList(list);
		List<UserInformationDomain>resultList=userInfoservice.getColumns(input);
		return new ModelAndView("userInformationInterface/databaseInformation", "resultObject",resultList);

	}
	
	/**
	 * Method to Execute Query
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws Exception
	 * @return Modal and view contain result
	 **/
	public ModelAndView execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);		
		if(session == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}		
		UserInformationDomain input=new UserInformationDomain();
		String selectedColumn=request.getParameter("selectedColumn");
		String tables=request.getParameter("tables");
		String whereCondition=request.getParameter("whereCondition");
		String joinCondition=request.getParameter("joinCondition");
		String groupByCondition=request.getParameter("groupByCondition");
		String havingCondition=request.getParameter("havingCondition");
		String orderByCondition=request.getParameter("orderByCondition");
		String universityFlag=request.getParameter("universityFlag");
		String universityId=(String)session.getAttribute("universityId");
		selectedColumn=selectedColumn.substring(0, (selectedColumn.length()-1));		
		selectedColumn="CONCAT("+selectedColumn+")";
		tables=tables.substring(0, (tables.length()-1));		
		input.setSelectedColumns(selectedColumn);		
		if(!universityFlag.equals("")){
			universityFlag=universityFlag+" = "+universityId;
		}		
		if(!whereCondition.equals("")){
			if(!joinCondition.equals("")){
				if(!groupByCondition.equals("")){
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" GROUP BY "+groupByCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" GROUP BY "+groupByCondition;
							input.setTableName(tables+whereCondition);
						}					
					}					
				}
				else{
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{							
							whereCondition=" WHERE "+joinCondition+" AND "+whereCondition+universityFlag;							
							input.setTableName(tables+whereCondition);
						}
						
					}					
				}				
			}
			else{
				if(!groupByCondition.equals("")){
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+whereCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+whereCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+whereCondition +universityFlag+" GROUP BY "+groupByCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+whereCondition +universityFlag+" GROUP BY "+groupByCondition;
							input.setTableName(tables+whereCondition);
						}					
					}					
				}
				else{
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+whereCondition+universityFlag+" HAVING "+havingCondition+"ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+whereCondition+universityFlag+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);					
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+whereCondition+universityFlag+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+whereCondition+universityFlag;
							input.setTableName(tables+whereCondition);
						}						
					}					
				}				
			}			
		}				
		else{
			if(!joinCondition.equals("")){
				if(!groupByCondition.equals("")){
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+joinCondition+universityFlag+" GROUP BY "+groupByCondition+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							joinCondition=" WHERE "+joinCondition+universityFlag+" GROUP BY "+groupByCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+joinCondition);
						}
						else{
							joinCondition=" WHERE "+joinCondition+universityFlag+" GROUP BY "+groupByCondition;
							input.setTableName(tables+joinCondition);
						}						
					}				
				}
				else{
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							whereCondition=" WHERE "+joinCondition+universityFlag+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
							input.setTableName(tables+whereCondition);
						}
						else{
							whereCondition=" WHERE "+joinCondition+universityFlag+" HAVING "+havingCondition;
							input.setTableName(tables+whereCondition);
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							joinCondition=" WHERE "+joinCondition+universityFlag+" ORDER BY "+orderByCondition;
							input.setTableName(tables+joinCondition);
						}
						else{
							joinCondition=" WHERE "+joinCondition+universityFlag;
							input.setTableName(tables+joinCondition);
						}						
					}					
				}				
			}
			else{
				if(!groupByCondition.equals("")){
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							if(universityFlag.equals("")){
								whereCondition=" GROUP BY "+groupByCondition+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+whereCondition);
							}
							else{
								whereCondition=" WHERE "+universityFlag.substring(4)+" GROUP BY "+groupByCondition+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+whereCondition);
							}
							
						}
						else{
							if(universityFlag.equals("")){
								whereCondition=" GROUP BY "+groupByCondition+" HAVING "+havingCondition;
								input.setTableName(tables+whereCondition);
							}
							else{
								whereCondition=" WHERE "+universityFlag.substring(4)+" GROUP BY "+groupByCondition+" HAVING "+havingCondition;
								input.setTableName(tables+whereCondition);
							}
							
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							if(universityFlag.equals("")){
								groupByCondition=" GROUP BY "+groupByCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+groupByCondition);
							}
							else{
								groupByCondition=" WHERE "+universityFlag.substring(4)+" GROUP BY "+groupByCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+groupByCondition);
							}
							
						}
						else{
							if(universityFlag.equals("")){
								groupByCondition=" GROUP BY "+groupByCondition;
								input.setTableName(tables+groupByCondition);
							}
							else{
								groupByCondition=" WHERE "+universityFlag.substring(4)+" GROUP BY "+groupByCondition;
								input.setTableName(tables+groupByCondition);
							}
							
						}					
					}					
				}
				else{
					if(!havingCondition.equals("")){
						if(!orderByCondition.equals("")){
							if(universityFlag.equals("")){
								whereCondition=" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+whereCondition);
							}
							else{
								whereCondition=" WHERE "+universityFlag.substring(4)+" HAVING "+havingCondition+" ORDER BY "+orderByCondition;
								input.setTableName(tables+whereCondition);
							}
							
						}
						else{
							if(universityFlag.equals("")){
								whereCondition=" HAVING "+havingCondition;
								input.setTableName(tables+whereCondition);
							}
							else{
								whereCondition=" WHERE "+universityFlag.substring(4)+" HAVING "+havingCondition;
								input.setTableName(tables+whereCondition);
							}
							
						}						
					}
					else{
						if(!orderByCondition.equals("")){
							if(universityFlag.equals("")){
								orderByCondition=" ORDER BY "+orderByCondition;
								input.setTableName(tables+orderByCondition);
							}
							else{
								orderByCondition=" WHERE "+universityFlag.substring(4)+" ORDER BY "+orderByCondition;
								input.setTableName(tables+orderByCondition);
							}
							
						}
						else{
							if(universityFlag.equals("")){
								input.setTableName(tables);								
							}
							else{
								input.setTableName(tables+" WHERE "+universityFlag.substring(4));
							}
							
						}						
					}					
				}				
			}			
		}
		List<UserInformationDomain>resultList=userInfoservice.execute(input);
		return new ModelAndView("userInformationInterface/databaseInformation", "resultObject",resultList);

	}
}
