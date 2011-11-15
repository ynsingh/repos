package org.bss.brihaspatisync.util;

/*
 * @(#)Language.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;


/**
 *  * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 *   */

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
				prop.load(inputStream);
				System.out.println("value read"+getLangValue("lang"));
                	}catch(Exception e){ System.out.println("Error on loading properties file"+e.getMessage());}
		}else if(str.equals("Hindi")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/hindi.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Tamil")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/tamil.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Telugu")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/telugu.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Bhojpuri")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/bhojpuri.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Arabic")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/arabic.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Chinies")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/chinies.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
		}else if(str.equals("Greek")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/greek.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Japanese")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/japanese.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Korean")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/korean.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Persian")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/persian.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }else if(str.equals("Rusian")) {
                        try{
                                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/lang/rusian.properties");
                                prop.load(inputStream);
                        }catch(Exception e){System.out.println("Error on loading properties file"+e.getMessage());}
                }
	}
        
                            
	public String getLangValue(String TagValue){
                  return prop.getProperty(TagValue);
        }
}
