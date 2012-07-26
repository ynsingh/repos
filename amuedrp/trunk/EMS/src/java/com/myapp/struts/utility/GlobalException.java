package com.myapp.struts.utility;

public class GlobalException extends Exception
{

  public GlobalException(String mess)
  {
  super(mess);
  }
  public GlobalException(String mess,Throwable cause)
  {
  super(mess,cause);
  }

  

}