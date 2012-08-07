/*
 * @(#) LoginAction.java
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
package in.ac.dei.mhrd.omr.login;

import in.ac.dei.mhrd.omr.GroupOperation;
import in.ac.dei.mhrd.omr.dbConnection.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 12-04-2010
 * @author Devendra Singhal
 * XDoclet definition:
 * @struts.action path="/logInter" name="logInterfaceForm" input="/Menu.jsp" scope="request" validate="true"
 * @version 1.0
 * 
 */
public class LoginAction extends Action{
	/* Creating object for writing logs*/
	private static Logger log = Logger.getLogger(GroupOperation.class);
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {			
		Boolean bool = false;
		Connection con = null;
		try{	
			LoginForm form1 =(LoginForm)form;
			con=Connect.prepareConnection();
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM user_info WHERE user_name=? AND password=sha1(?) AND application=? AND status='ACT'");			
			ps.setString(1,form1.getUsername());
			ps.setString(2,form1.getPassword());
			ps.setString(3,"OMR");
			ResultSet result=ps.executeQuery();			
			while(result.next()){				
				if(result.getString(1).equals("0")){
					bool=false;
				}
				else{
					bool=true;
				}
			}			
			if(bool){				
				return mapping.findForward("home");		
			}
			else{
				request.setAttribute("loginDetail", "* Invalid Login Details !!");
				return mapping.getInputForward();		
			}
		}
		catch(Exception e){
			log.error("Exception during get Login Details inside MessageDisplay class :: "+e);			
		}
		return null;					
	}
}