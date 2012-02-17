
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */



import java.util.Date;
import java.lang.Long;
import java.io.File;
import java.io.FileOutputStream;

public class UserLog {

	public static void ErrorLog(String msg,String path)
	{
		try
		{
			Date Errordate=new Date();
			path=path+"/EMSLOG/UserLog.txt";
			FileOutputStream log=new FileOutputStream(path,true);
			log.write((Errordate+"===>"+msg+"\n").getBytes());
                       	log.close();
		}//try
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
}

