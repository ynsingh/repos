package utils;

import java.util.Date;
import java.lang.String;
import java.lang.Long;
import java.io.FileOutputStream;
import java.io.File;

/**
 * This class create Exception(error) log file with error message's
 * @author <a href="mailto:jaivirpal@gmail.com"> Jaivir Singh</a>
 */
public class ExceptionLogUtil
{

	private static int count = 1;
        public static void ExceptionLog(String msg)
        {
                try
                {
                        Date Errordate=new Date();
			String  workingDir = System.getProperty("user.home");
			String path=workingDir+"/"+"PICO";
			String  str=path+"/"+"logs";
			File fle = new File(str);
			fle.mkdir();
                        String LogfilePath=str+"/ErrorLog.txt";
                        File existingFile=new File(LogfilePath);
                        if(existingFile.length() >= 1048576 )
                        {
                                boolean success=existingFile.delete();
                        }
                        else{
                                FileOutputStream log=new FileOutputStream(LogfilePath,true);
                                log.write((Errordate+"------"+msg+"\n").getBytes());
                                log.close();
                        }
                }
                catch(Exception e)
                {}
        }
}
