package edu.avv.util;

public class GetConsole {

  private static String message;
  
  public static void info(String m){
    message = m;
    System.out.println(m);	  
  }
	
  public static String getMessage(){
    return message;	  
  }
  
  public static void fatal(String msg) {
    System.err.println(msg);
  }
	
}
