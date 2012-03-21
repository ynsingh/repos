package com.erp.nfes;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
		
public class MultiLanguageString{
    
	String language;
    String country;
	Locale currentLocale;
    ResourceBundle messages;
    String message_caption="";
    
    public void init(String languageCode){
    	language=languageCode;
    }
    
    public void init(String fileName,String languageCode){
    	language=languageCode;
    }
    public String getValue (String message_code) {  
    	try {
    		String language_code=language;//"hi";//Locale.getDefault();
        	
        	if(language_code==null){
        		language="en";    		
        	}else if(language_code.equals("")){
        		language="en";    		   		
        	}else{
        		language=language_code;
        	}
        	country=language_code.toUpperCase();
        	//System.out.println("language:"+language+"_"+country);
        	currentLocale = new Locale(language, country);
            messages = ResourceBundle.getBundle("MessagesBundle",currentLocale);               
            message_caption=messages.getString(message_code);        
                    
            byte bytes[] = message_caption.getBytes("ISO-8859-1");
            String message_caption_utf = new String(bytes, "UTF-8");        
            message_caption=message_caption_utf;
        
		} catch (Exception e){			
			return("message."+language+"."+country+"."+message_code);
		}            
        return(message_caption);		 
    }     
     
}