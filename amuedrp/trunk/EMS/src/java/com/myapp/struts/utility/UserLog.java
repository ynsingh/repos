
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */



import java.util.Date;
import java.lang.Long;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

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
          public static void ErrorListLog(List log,String path)
	{
		try
		{
			Date Errordate=new Date();
			StringBuffer str = new StringBuffer();

                            PrintWriter pw = new PrintWriter(new FileOutputStream(path,true));

			        for(int ii=0;ii<log.size();ii++)
		                str.append(Errordate+">>"+log.get(ii)+"\n");
      			        pw.println(str+"\n");
                       	pw.close();
		}//try
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
}

