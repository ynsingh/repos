
/*
 * @(#) OldSemesterDetails.java
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
package in.ac.dei.edrp.cms.domain.registration.prestaging;

public class OldSemesterDetails {

	private String modeOfEntry;
	private String programStatus;
	private String enrollmentNumber;
	private int switchNumber;
	private int sequenceNumber;

	private String oldRollNumber;

	public String getModeOfEntry() {
		return modeOfEntry;
	}

	public void setModeOfEntry(String modeOfEntry) {
		this.modeOfEntry = modeOfEntry;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public int getSwitchNumber() {
		return switchNumber;
	}

	public void setSwitchNumber(int switchNumber) {
		this.switchNumber = switchNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getOldRollNumber() {
		return oldRollNumber;
	}

	public void setOldRollNumber(String oldRollNumber) {
		this.oldRollNumber = oldRollNumber;
	}

	public OldSemesterDetails(String modeOfEntry, String programStatus,
			int switchNumber, int sequenceNumber, String enrollmentNumber,
			String oldRollNumber) {
		super();
		this.modeOfEntry = modeOfEntry;
		this.programStatus = programStatus;
		this.switchNumber = switchNumber;
		this.sequenceNumber = sequenceNumber;
		this.enrollmentNumber = enrollmentNumber;
		this.oldRollNumber = oldRollNumber;
	}

}
