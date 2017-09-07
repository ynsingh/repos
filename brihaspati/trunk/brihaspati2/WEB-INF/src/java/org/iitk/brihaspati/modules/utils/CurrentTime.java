package org.iitk.brihaspati.modules.utils;
/*
		For getting current time from the server to calculate timer for the quiz in screen.
*/
import java.util.Date;
import java.util.Timer;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

public class CurrentTime{

		public String CurTime()
				{
						String a=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
						return a;
				}

}
