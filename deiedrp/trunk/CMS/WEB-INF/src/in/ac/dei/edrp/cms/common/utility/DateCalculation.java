/*
 * @(#) DateCalculation.java
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
package in.ac.dei.edrp.cms.common.utility;

import java.util.*;
import java.text.*;

/**
 * Creation date: 12-Feb-2011
 * This class is used for finding desired dates.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class DateCalculation{
		
	/**
	 * Method getRegistrationStartDate is used for generating desired year
	 * @param startMonth
	 * @param endMonth
	 * @param startYear
	 * @return year
	 */
	public static int getRegistrationStartDate(int startMonth, int endMonth, int startYear){		
		int endYear=0;
		
		if(startMonth <= endMonth){
			endYear = startYear;
		}
		else if(startMonth > endMonth){
			endYear = startYear + 1;
		}
		return endYear;
	}
	
	/**
	 * Method getSemesterEndDate is used for generating desired year
	 * @param startMonth
	 * @param endMonth
	 * @param startYear
	 * @return year
	 */
	public static int getSemesterEndDate(int startMonth, int endMonth, int startYear){		
		int endYear=0;
		
		if(startMonth < endMonth){
			endYear = startYear;
		}
		else if(startMonth >= endMonth){
			endYear = startYear + 1;
		}
		return endYear;
	}
	
	/**
	 * Method getRegistrationEndDate is used for getting registration date
	 * @param startingDate
	 * @param numberOfDays
	 * @return registrationEndDate
	 * @throws ParseException
	 */
	public static String getRegistrationEndDate(String startingDate, int numberOfDays) throws ParseException{		
		 DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(formatter.parse(startingDate));
	     cal.add(Calendar.DATE, numberOfDays);
	     String registrationEndDate = formatter.format(cal.getTime());
	         return registrationEndDate;
	}
}
