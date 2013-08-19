package org.iitk.brihaspati.modules.utils;
/*
 * @(#)CalendarUtil.java
 *
 *  Copyright (c) 2005-2008,2011,2013 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 *
 */
import java.util.Properties;
import java.util.Vector;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Calendar;
//import org.joda.time.*;
//import org.joda.time.chrono.*;
//import org.joda.time.field.*;
import org.joda.time.DateTimeField;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.LocalDate;
/**
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:rekha20july@gmail.com">Rekha Pal</a>
 * @modify date:- 08-07-2011
 */

/** 
 * In this class, set and return the value of Academic_Calendar.properties 
 * and Calendar_Holidays.properties file for configuration parameter.
 */
public class CalendarUtil
{
	/**
	 * This method returns the value of configuration parameter
	 * in Academic_Calendar.properties and Calendar_Holidays.properties file
	 * @param path String
	 * @param key String
	 * @return retval String array
	 * @exception a generic exception
	 */
	public static String [] getValue(String path , String key) throws Exception
	{
	 	InputStream f = new FileInputStream(path);
		Properties p = new Properties();
		p.load(f);
		String val = p.getProperty(key);
		if(val == null)
			 return null;
		else 
		{
			String prop []=val.split(";");
			return(prop);
		}
	 }

	public static int  getSequence(String fullpath,String topic ,String fName )
        {
                int seq = -1;
                try{
                        String Xml_file="";
                        if(topic.endsWith(".xml"))
                                Xml_file=topic;
                        else
                                Xml_file=topic+"__des.xml";

                        String filename=null;
                        TopicMetaDataXmlReader xmlRead=new TopicMetaDataXmlReader(fullpath +"/"+ Xml_file);
                        Vector Read1=new Vector();
                        Read1=xmlRead.getFileDetails();
                        for(int i=0;i<Read1.size();i++)
                        {
                                filename =((FileEntry) Read1.elementAt(i)).getName();
                                ErrorDumpUtil.ErrorLog("filename in getSequence-->"+filename);
                                if(fName.equals(filename))
                                {
                                        seq = i;
                                        break;
                                }
                        }
                }
                catch( Exception e)
                {
                        ErrorDumpUtil.ErrorLog("error in getSequence Method-->"+e );
                }
                return seq;
        }
	
	
	public static void DeleteLine(String filePath, String date)
        {
        try{
                String str[] = new String[1000];
                int i =0;
                int start = 0;
                BufferedReader br=new BufferedReader(new FileReader (filePath));
                while ((str[i]=br.readLine()) != null)
                {
                        if (str[i].startsWith(date))
                        {
                                start = i;
                        }
                        i= i +1;
                }
                br.close();
                FileWriter fw=new FileWriter(filePath);
                for(int x=0;x<start;x++)
                {
                        fw.write(str[x]+"\r\n");
                }
                for(int x=start+1;x<i;x++)
                {
                        fw.write(str[x]+"\r\n");
                }
                fw.close();
                str=null;
        }
        catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in CalendarUtil !!"+ex);}
        }

	/**
	 * This Method used for getting year, month and day 
	 * from date into a string 
	 * @ExpiryUtil
	 * @return String
	 */

	public static String getDay(String date)
        {
		String getDate="";
		try{
			/**
			 * Now get day, month and year seperately in vector from given date
			 * get each value from vector into string
			 */
                        Vector preVc= ExpiryUtil.getPostDate(date);
                        String year=(String)preVc.elementAt(0);
                        String month=(String)preVc.elementAt(1);
                        String day=(String)preVc.elementAt(2);
                        int preday = Integer.parseInt(day);
			/**
			 * If day is less than 10 then get only number  
			 */
                        if(preday<10)
                        {
                                char day_nm = day.charAt(1);
				getDate=getDate+day_nm;
                        }
                        else
                        {
				getDate=getDate+day;
                        }
			getDate=getDate+"-"+year;
                        int premonth = Integer.parseInt(month);
			/**
			 * If month is less than 10 then get only number  
			 */
                        if(premonth<10)
                        {
                                char month_nm = month.charAt(1);
				getDate = getDate+"-"+month_nm;
                        }
                        else
                        {
				getDate = getDate+"-"+month;
                        }
		}
		catch(Exception e){}
		return getDate;
	}
	public static int correctEntry(String mon,String year)
	{
			int maxvalue=0;
		try{	
			int year1=Integer.parseInt(year);
			int month= Integer.parseInt(mon);
			GregorianChronology calendar = GregorianChronology.getInstance();
        		DateTimeField field = calendar.dayOfMonth();
			LocalDate date = new LocalDate(year1,month,1, calendar);
			maxvalue=(field.getMaximumValue(date));
			
			
		}catch(Exception e){ ErrorDumpUtil.ErrorLog("Error in CalendarUtil method correctEntry()-------"+e);}
		return maxvalue;
	}
}	
