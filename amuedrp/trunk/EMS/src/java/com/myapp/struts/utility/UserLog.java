
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */



import java.util.Date;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;
import java.io.DataOutputStream;
import javax.servlet.http.*;


public class UserLog {
  static  String path1,path2,path3;

	public static void ErrorLog(String msg,String path)
	{
		try
		{
                    
			Date Errordate=new Date();

                       //redirect the path from build web folder to project web folder


                        path1=path.substring(0,path.lastIndexOf("/"));
                        System.out.println(path1);
                        path2=path1.substring(0,path1.lastIndexOf("/"));
                        path3=path2.substring(0,path2.lastIndexOf("/"));


			String LogfilePath=path3+"/web/logs/UserLog.txt";
                           System.out.print(LogfilePath);
			File existingFile=new File(LogfilePath);
                     
			//if(existingFile.length() >= 1048576 )
			//{
			//	boolean success=existingFile.delete();
			//}
			//else{
				FileOutputStream log=new FileOutputStream(LogfilePath,true);
				log.write((msg+Errordate+"\n").getBytes());
                            
				log.close();
			//}
		}//try
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
}

