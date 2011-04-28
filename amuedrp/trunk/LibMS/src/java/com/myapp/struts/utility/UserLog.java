
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */



import java.util.Date;
import java.io.FileOutputStream;


public class UserLog {
  static  String path,path1,path2,path3;

	public static void setPath(String requestpath)
        {
            path = requestpath;
        }
        public static void ErrorLog(String msg)
	{
		try
		{
                    
			Date Errordate=new Date();

                     
                    
			  path1=path.substring(0,path.lastIndexOf("/"));
                        System.out.println(path1);
                        path2=path1.substring(0,path1.lastIndexOf("/"));
                        path3=path2.substring(0,path2.lastIndexOf("/"));


			String LogfilePath=path3+"/web/logs/UserLog.txt";
                         
			
                     
			System.out.println(LogfilePath);
				FileOutputStream log=new FileOutputStream(LogfilePath,true);
				log.write((msg+Errordate+"\n").getBytes());
                            
				log.close();
			
		}
		catch(Exception e)
		{
                    System.out.println(e);
		}
    	}
}

