
/*
 * @(#) StringToke.java
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class StringToke {

	public static void main(String[] s) {
		System.out.println(getFianl("03/01"));

	}// main ends

	public static String getFianl(String inputDate) {
		String finalDate = "";
		Calendar cal = Calendar.getInstance();

		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		System.out.println("year: " + year + " and moth" + month);
		StringTokenizer st = new StringTokenizer(inputDate, "/-");
		Map<Integer, Integer> monthMap = new HashMap<Integer, Integer>();
		int key = 0;
		while (st.hasMoreElements()) {
			int token = Integer.parseInt(st.nextToken());
			// System.out.println("token = " + token);
			monthMap.put(key, token);
			key++;
		}// loop ends
		int monthCompare = 0;
		int date = 0;
		for (Map.Entry<Integer, Integer> map : monthMap.entrySet()) {
			if (map.getKey() == 0) {
				monthCompare = map.getValue();
				System.out.println(monthCompare);
				if (monthCompare < month) {
					year = year + 1;
				}
			}
			if (map.getKey() == 1) {
				date = map.getValue();
			}

		}
		finalDate = year + "-" + monthCompare + "-" + date;
		return finalDate;
	}
}
