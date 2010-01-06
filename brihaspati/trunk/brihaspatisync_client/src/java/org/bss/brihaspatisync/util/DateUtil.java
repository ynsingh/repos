package org.bss.brihaspatisync.util;

/**DateUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT kanpur.
 */

import java.util.Calendar;

/**
 * @author: <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>
 * @author <a href=arvindjss17@yahoo.co.in>Arvind Pal</a>
 */


public class DateUtil {

        private static DateUtil dateutil=null;

        public static DateUtil getController() {
                if(dateutil==null)
                        dateutil=new DateUtil();
                return dateutil;
        }

        public DateUtil() { }
	
	
        public int checkTimeInput() {
			Calendar calendar=Calendar.getInstance();
                         int curmin=calendar.get(Calendar.HOUR);
                         int min=calendar.get(Calendar.MINUTE);
                         int am_pm=calendar.get(Calendar.AM_PM);
                         if(am_pm == 1)
        			curmin= curmin+12;
			curmin=curmin*60;
			curmin=curmin+min;
		return curmin;
	}
	
	
        /**
         * This method is used to check to date validation.
         */
	
        public boolean checkDateInput(String yearin1,String monthin1,String dayin1) {
		int yearin=Integer.parseInt(yearin1);
		int monthin=Integer.parseInt(monthin1);
		int dayin=Integer.parseInt(dayin1);
                boolean datFlag=true;
		if((monthin==4||monthin==6||monthin==9||monthin==11) && (dayin>30))
                {
         	       	javax.swing.JOptionPane.showMessageDialog(null,"There should only 30 days in this Month !!");
			datFlag=false;
			//return;
                }
                if(monthin==2)
                {
                	if((dayin>29)&&((yearin%4==0)&&(yearin%100!=0))||((yearin%100==0)&&(yearin%400==0))) {
                        	javax.swing.JOptionPane.showMessageDialog(null,"There should 29 days in this month  !!");
                        	datFlag=false;
				//return;
			}
                        else if (dayin>28 && (!(((yearin%4==0)&&(yearin%100!=0))||((yearin%100==0)&&(yearin%400==0))))){
				javax.swing.JOptionPane.showMessageDialog(null,"There should 28 days in this month  !!");
        	                datFlag=false;
			}
                        
		}
                return datFlag;
        }
}

