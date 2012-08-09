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
  System.out.println("Occurrence of the word is: " + a+"    "+st+st.length());
  return st;
  }
   public static boolean  containsOnlyNumbers(String str) {

        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }

        return true;
    }
   public static String titlecase(String string){
        String result = "";
        for (int i = 0; i < string.length(); i++){
            String next = string.substring(i, i + 1);
            if (i == 0){
                result += next.toUpperCase();
            } else {
                result += next.toLowerCase();
            }
        }
        return result;
    }
}