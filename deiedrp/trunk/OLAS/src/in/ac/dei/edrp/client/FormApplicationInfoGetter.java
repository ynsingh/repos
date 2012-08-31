/*
 * @(#)  FormApplicationInfoGetter.java
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

package in.ac.dei.edrp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
/**
 * This bean file is associated with Application Form
 * @author UPASANA KULSHRESTHA
 * @date 10 February 2012
 * @version 1.0
 */
public class FormApplicationInfoGetter implements IsSerializable  {
	
	/** declaring private variables */
	private String universityCode;
	private String programId;
	private String programName;
	private String formNumber;
	private String formName;
	private String userId;
	private String maxFormNumber;
	private int countFormName;
	private String entityId;
	private String entityName;
	private String formDesc;
	private List<String> entityIdList;
	private String ownerEntityName;
	private String offeredBy;
	private String city;
	
	/** defining setter and getter method for private variable **/
	public String getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getFormNumber() {
		return formNumber;
	}
	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getMaxFormNumber() {
		return maxFormNumber;
	}
	public void setMaxFormNumber(String maxFormNumber) {
		this.maxFormNumber = maxFormNumber;
	}
	public int getCountFormName() {
		return countFormName;
	}
	public void setCountFormName(int countFormName) {
		this.countFormName = countFormName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getFormDesc() {
		return formDesc;
	}
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}
	public List<String> getEntityIdList() {
		return entityIdList;
	}
	public void setEntityIdList(List<String> entityList1) {
		this.entityIdList = entityList1;
	}
	public String getOwnerEntityName() {
		return ownerEntityName;
	}
	public void setOwnerEntityName(String ownerEntityName) {
		this.ownerEntityName = ownerEntityName;
	}
	public String getOfferedBy() {
		return offeredBy;
	}
	public void setOfferedBy(String offeredBy) {
		this.offeredBy = offeredBy;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
		
}
