package org.bss.brihaspatisync.util;

/**DateUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT kanpur.
 */

import java.util.Calendar;
import java.util.Date;
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
	/*
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
	*/
	
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

       public static String getCurrentDate(String delimiter)
        {
                String cdate="";
                try{
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

                } catch(Exception e) {}
                return(cdate.trim());
        }



         public static String getSystemDateTime() {
                try {
                        java.text.SimpleDateFormat sdfDate = new java.text.SimpleDateFormat("yyyy/MM/dd");
                        java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat("HH:mm");
                        Date now = new Date();
                        String strTime = sdfTime.format(now);
                        return getCurrentDate("/")+" "+strTime;
                }catch(Exception e){return "UnSuccessfull"; }
        }
}

