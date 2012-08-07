/*
 * @(#) ConfirmAnsGroupForm.java
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


package in.ac.dei.mhrd.omr.uploadCorrectGroup;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Bean class for getting and setting correct answers related data
 * @author UPASANA KULSHRESTHA
 * @date 9-12-2011
 * 
 */

public class ConfirmAnsGroupForm extends ActionForm {
		
		/** testid property */
		private String testid;
		/** ansList property */
		private ArrayList<String> ansList;
		/** path property */
		private String path;
		/** ques property */
		private String ques;
		/** group property */
		private String group;
		

		/** 
		 * Returns the path.
		 * @return String
		 */
		public String getPath() {
			return path;
		}

		/** 
		 * Set the path.
		 * @param path The path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}

		/** 
		 * Returns the question number.
		 * @return String
		 */
		public String getQues() {
			return ques;
		}

		/** 
		 * Set the question.
		 * @param ques The ques to set
		 */
		public void setQues(String ques) {
			this.ques = ques;
		}

		
		/** 
		 * Returns the test id.
		 * @return String
		 */
		public String getTestid() {
			return testid;
		}

		/** 
		 * Set the test id.
		 * @param testid The test id to set
		 */
		public void setTestid(String testid) {
			this.testid = testid;
		}

		/** 
		 * Returns the ansList.
		 * @return ArrayList String
		 */
		public ArrayList<String> getAnsList() {
			return ansList;
		}

		/** 
		 * Set the ansList.
		 * @param ansList The answer List to set
		 */
		public void setAnsList(ArrayList<String> ansList) {
			this.ansList = ansList;
		}

		/** 
		 * Returns the group.
		 * @return String
		 */
		public String getGroup() {
			return group;
		}
		
		/** 
		 * Set the group.
		 * @param group The group code to set
		 */
		public void setGroup(String group) {
			this.group = group;
		}
		
		/** 
		 * Method validate
		 * @param mapping
		 * @param request
		 * @return ActionErrors
		 */
		public ActionErrors validate(ActionMapping mapping,
				HttpServletRequest request) {
			// TODO Auto-generated method stub
			return null;
		}

		/** 
		 * Method reset
		 * @param mapping
		 * @param request
		 */
		public void reset(ActionMapping mapping, HttpServletRequest request) {
			// TODO Auto-generated method stub
		}


	}
