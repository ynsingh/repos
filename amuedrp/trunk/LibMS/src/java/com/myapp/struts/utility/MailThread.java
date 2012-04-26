package com.myapp.struts.utility;
public class MailThread extends Thread
{
    @Override
public void run()
{

while(true) {
    UserLog.HibernatePrintErrorLog("ErrorLog.txt");
//if(UserLog.HibernatePrintErrorLog("ErrorLog.txt")==null)
//{Thread.currentThread().interrupt();
   
//}


try {
//Thread.sleep(60000 * 60); // Sleep for an hour
    Thread.sleep((10000/10) * 60); // Sleep for an hour
} catch (Exception e) {System.out.println(e);}
}
}
}

//      executor.submit(new Runnable() {
//
//                public void run() {
//                    System.out.println("I am Hreere");
//
//try {
////Thread.sleep(60000 * 60); // Sleep for an hour
//    Thread.sleep((10000/10) * 60); // Sleep for an hour
//} catch (Exception e) {System.out.println(e);}
//                }
//            });
