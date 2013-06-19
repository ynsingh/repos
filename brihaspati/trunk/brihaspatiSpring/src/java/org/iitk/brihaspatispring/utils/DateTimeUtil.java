package org.iitk.brihaspatispring.utils;

/*@(#)DateTimeUtil.java
 *  Copyright (c) 2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  
 */
import java.util.Calendar;

/**
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu singh sisaudiya</a>
 */

public class  DateTimeUtil {
	
	/**
	 * This method retreives the current date of the system with delimiter
	 * in yyyymmdd or yyyy-mm-dd etc format
	 * @param delimiter String delimitertype (-,/,. etc)
	 * @return String 
	 */
	public static String getCurrentDate(String delimiter) {
		String cdate="";
		try {
			Calendar calendar=Calendar.getInstance();
			int curr_day=calendar.get(calendar.DATE);
			int curr_month=calendar.get(calendar.MONTH)+1;
			int curr_year=calendar.get(calendar.YEAR);
			String current_day=Integer.toString(curr_day);
			String current_month=Integer.toString(curr_month);
			String current_year=Integer.toString(curr_year);
			if(curr_month<10)
				current_month="0"+current_month;
			if(curr_day<10)
				current_day="0"+current_day;
			if(!delimiter.equals(""))
				cdate=current_year+delimiter+current_month+delimiter+current_day;
			else
				cdate=current_year+current_month+current_day;
		}catch(Exception ex) {
			ErrorDumpUtil.ErrorLog("The error in getCurrentDate() - DateTimeUtil class !!"+ex);
		}
		return(cdate);
	}
}//end of class
