package org.bss.brihaspatisync.util;

/*
 * @(#)Language.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *  @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>Created on Oct2011
 *  @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class Language {

        private static Language obj=null;
	private Properties prop=null;

 	public static Language getController(){
                if(obj==null) {
                        obj=new Language();
                }
                return obj;
        }

	public void SelectLanguage(String str) {
		prop=null;
		prop=new Properties();

		if(str.equals("English")){
                	try{
                        	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/english.properties");
				prop.load(new InputStreamReader(inputStream,"UTF8"));
                	}catch(Exception e){ System.out.println("Error on loading properties file"+e.getMessage());}
		}else if(str.equals("Hindi")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/hindi.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Tamil")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/tamil.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Telugu")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/telugu.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Bhojpuri")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/bhojpuri.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Arabic")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/arabic.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Chinese")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/chinese.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
		}else if(str.equals("Greek")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/greek.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Japanese")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/japanese.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Korean")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/korean.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Persian")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/persian.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Russian")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/russian.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Bangala")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/bangala.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("French")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/french.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Spanish")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/spanish.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Dutch")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/dutch.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Nepali")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/nepali.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("German")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/german.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Italian")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/italian.properties");
                                prop.load(new InputStreamReader(inputStream,"UTF8"));
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }
	}
        
                            
	public String getLangValue(String TagValue){
                  return prop.getProperty(TagValue);
        }
}
