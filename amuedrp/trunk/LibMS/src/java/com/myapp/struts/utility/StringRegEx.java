/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;
import java.util.regex.*;
import java.io.*;
/**
 *
 * @author edrp-07
 */
public class StringRegEx {

  public static StringBuffer sepByRegEx(String string)throws IOException{
  String str = "[A-Z]";
  Pattern pattern = Pattern.compile(str);
  Matcher matcher = pattern.matcher(string);
StringBuffer st=new StringBuffer();
//StringBuffer digit=new StringBuffer();
  int a = 0;
  int i=0;
while(matcher.find()){
  a = a + 1;
st.append(string.charAt(i++));
  }
//st.append(string.substring(i,string.length()));
  System.out.println("Occurrence of the word is: " + a+"    "+st.length());
  return st;
  }
}