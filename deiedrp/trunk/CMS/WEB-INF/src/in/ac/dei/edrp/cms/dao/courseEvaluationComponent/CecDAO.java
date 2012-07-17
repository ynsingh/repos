/**
 * @(#) CecDAO.java
 * Author :Arush Kumar
 * Date :21/3/2011
 * Version 1.0
 * 
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
package in.ac.dei.edrp.cms.dao.courseEvaluationComponent;

import in.ac.dei.edrp.cms.domain.courseEvaluationComponent.Cec;

import java.util.List;
/**
 * this is client interface for Course Evaluation Component
 *
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public interface CecDAO {
	/** getting program course details **/
	public List<Cec> getProgramCourse(Cec cec);

	/** inserting course evaluation details **/
	public boolean insertCecDetail(Cec cec);

	/** getting list of evaluation ids **/
	public List<Cec> getEvaluationIds(Cec cec);

	/** getting course evaluation details **/
	public List<Cec> getCecList(Cec cec);

	/** getting no of duplicate record **/
	public List<Cec> getDuplicateCount(Cec cec);

	/** updating course evaluation details **/
	public boolean updateCecDetails(Cec cec);

	/** deleting course evaluation details **/
	public boolean deleteCecDetails(List<Cec> cecList);
}
