/*
 * @(#) CRConstant.java
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

/**
 * It contains constant or fixed values in code.
 * 
 * @author Deepak
 */

package in.ac.dei.edrp.cms.constants;

public abstract class CRConstant {

	public static final String DUPLICATE_REGISTRATION_ROLL_NUMBER = "DNR";

	public static final String DUPLICATE_SWITCH = "DSW";

	public static final String DUPLICATE_NORMAL_SWITCH = "DNS";

	public static final String DUPLICATE_NORMAL_SWITCH_REASON = "Duplicate normal and switch student found. Roll number";

	public static final String DUPLICATE_NORMAL_REASON = "Duplicate normal student found. Roll number";

	public static final String DUPLICATE_SWITCH_REASON = "Duplicate switch student found. Roll number";

	public static final String DUPLICATE_REGISTRATION_REASON = "Duplicate roll number/registration number student found. Roll number";

	public static final String ERROR_STATUS = "ERR";

	public static final String INACTIVE_STATUS = "INA";

	public static final String YET_TO_REGISTER = "YTR";

	// For Both Process5 and Process2
	public static final String ACTIVE_STATUS = "ACT";

	public static final String READY_STATUS = "RDY";

	public static final String NORMAL_MODE = "NOR";

	public static final String SWITCH_MODE = "SWT";

	public static final String NEW_MODE = "NEW";

	// For Process2
	public static final String PROCESSED_STATUS = "PRC";

	public static final String USER_STUDENT_GROUP_ID = "STD";

	public static final int MIN_PASSWORD_LENGTH = 5;

	public static final int MAX_PASSWORD_LENGTH = 7;

	public static final String LOAD_REGULAR_STUDENT = "LRG";

	// For Process1
	public static final String STATUS_PASS = "PAS";

	public static final String STATUS_FAIL = "FAL";

	public static final String STATUS_REMEDIAL = "REM";

	// Admission Mode:
	public static final String REGULAR_ADMISSION_MODE = "RG";

	public static final String SWITCH_ADMISSION_MODE = "SW";

	// code for master transfer
	public static final String STUDENT_ID_CODE = "FSTUID";

	public static final String SWITCH_NUMBER_CODE = "SWTNUM";

	// code for student master
	public static final String PERMANENT_ADDRESS = "PER";

	public static final String CORRESPONDENT_ADDRESS = "COR";

	// group code
	public static final String PROCESS_MASTER = "PRCMST";
	public static final String ACTIVITY_MASTER = "ACTMST";
	public static final String SEMESTER_GROUP_CODE = "SEMCOD";

	public static final String COMPLETE_STATUS = "COM";

	// Registration Status in SRSH for result processing
	public static final String REGISTRATION_STATUS = "REG";

	//
	public static final String PRESTAGING_READY_PROCESS = "PR2";
	public static final String STAGING_TRANSFER_PROCESS = "PR5";

	// For Error Codes
	public static final String MASTER_ERROR_CODE = "UNR";
	public static final String MASTER_ERROR_CODE_REASON = "Unknown Reason: Probably database problem";

	public static final String PROGRAM_LEVEL_ACTIVITY = "PLA";

}
