package edu.avv.util;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtils {

  /*
   *    Example - Date Formats
   *     
   *  dd MMMMM yyyy - 24 January 2012
   *  yyyyMMdd - 20120124
   *  dd.MM.yy - 24.01.12
   *  MM/dd/yy - 01/24/12
   *  yyyy.MM.dd G 'at' hh:mm:ss z - 2012.01.24 AD at 02:26:44 IST
   *  EEE, MMM d, ''yy - Tue, Jan 24, '12
   *  h:mm a - 2:26 PM
   *  H:mm:ss:SSS - 14:26:44:149
   *  K:mm a,z - 2:38 PM,IST 
   *  yyyy.MMMMM.dd GGG hh:mm aaa - 2012.January.24 AD 02:38 PM
   * 
   */
	
  public static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	return sdf.format(cal.getTime());
  }	
  
}
