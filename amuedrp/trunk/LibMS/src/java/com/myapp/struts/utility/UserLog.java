
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */




import java.sql.*;

import com.myapp.struts.hbm.Logsetting;
import com.myapp.struts.AdminDAO.LogsDAO;
import com.myapp.struts.hbm.Logs;



public class UserLog {
  static  String path,path1,path2,path3;

	public static void setPath(String requestpath)
        {
            path = requestpath;
        }
      
        public UserLog()
        {
           
        }


        public  void writelog(String path,String login_id,String date,String time,String class_name,String url,String activity,String action_result,String library_id,String sublibrary_id)
        {


        String login="";
        String class1="";
        String activity1="";
        String url1="";
        String time1="";
        String date1="";
        String action_result1="";

        Logsetting logs=LogsDAO.searchSetting();
        if(logs!=null){



if(logs.getP1()!=null)
{


   login=login_id;
}

if(logs.getP2()!=null)
{
  date1=date;

}

 if(logs.getP3()!=null)
{

    time1=time;

}
 if(logs.getP4()!=null)
{

   class1=class_name;


}
if(logs.getP5()!=null)
{

   url1=url;

}
if(logs.getP6()!=null)
{

   activity1=activity;


}
if(logs.getP7()!=null)
{

   action_result1=action_result;


}



        }





  









     Logs obj=new Logs();
     obj.setActionMessage(action_result1);
     obj.setActionResult(action_result);
     obj.setClassname(class_name);
     obj.setDate(date);
     obj.setLibraryId(library_id);
     obj.setSublibraryId(sublibrary_id);
     obj.setUserId(login);
     obj.setTime(time);
     obj.setUrl(url);

     LogsDAO.insertLog(obj);










        }
}

