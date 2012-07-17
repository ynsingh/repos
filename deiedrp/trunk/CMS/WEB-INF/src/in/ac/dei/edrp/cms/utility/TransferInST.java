
/*
 * @(#) TransferInST.java
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
package in.ac.dei.edrp.cms.utility;


import java.util.List;

import in.ac.dei.edrp.cms.domain.registration.prestaging.ApplicantUserInfoBean;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @version 1.0 Class Description: It contains methods for TransferPSTRDYInPST.
 *          Creation date: 17-Jan-2011
 * @author Deepak Pandey
 */

public class TransferInST {

	private SqlMapClient sqlMapClient = null;

	static final Logger logger = Logger.getLogger(RegistrationFunction.class);

	public TransferInST(SqlMapClient sqlMapClient) {
		// System.out.println("Coming inside registration function");
		this.sqlMapClient = sqlMapClient;
		// System.out.println(sqlMapClient);
	}

	public String getPasswordFromUser(
			ApplicantUserInfoBean applicantUserInfoBean) {
		String password = "";
		System.out.println(applicantUserInfoBean.getUserName());
		try {
			List<ApplicantUserInfoBean> passwordList = (List<ApplicantUserInfoBean>) sqlMapClient
					.queryForList("TransferNORInPSTBean.getUserLoginDetails",
							applicantUserInfoBean);
			for (ApplicantUserInfoBean passwordValue : passwordList) {
				password = passwordValue.getPassWord();
				System.out.println(password + " in User "
						+ applicantUserInfoBean.getUserName());
			}
		} catch (Exception e) {
			logger.info("Exception while getting password");
		}
		return password;
	}

	/**
	 * It generate a random password
	 * 
	 * @param minLen
	 * @param maxLen
	 * @return a random string
	 */
	public static final String generatePassword(int minLen, int maxLen) {

		// Declares the variable of type Integer int and also defining the range
		// of the variable
		final int RANGE = 10 + 26 + 26;
		if ((maxLen < minLen) || (minLen <= 0)) {
			throw new IllegalArgumentException();
		}
		// Declares the variables of type integer and an array of characters.
		int len = (int) (Math.random() * (maxLen - minLen)) + minLen;
		char[] charr = new char[len];

		/*
		 * Place the variable in a FOR LOOP to generate a string of characters
		 * and numbers randomly using the predefined function random in the math
		 * class of java.awt.math and multiplying the variable with the range
		 * and then returning the string to the main function.
		 */
		for (int i = 0; i < len; i++) {
			int n = (int) (Math.random() * RANGE);
			if (n < 10) {
				charr[i] = (char) (n + 48);
			} else if (n < 36) {
				charr[i] = (char) (n + (65 - 10));
			} else {
				charr[i] = (char) (n + (97 - 36));
			}
		}
		// Passing the value to the caller function
		return new String(charr);

	}
}
