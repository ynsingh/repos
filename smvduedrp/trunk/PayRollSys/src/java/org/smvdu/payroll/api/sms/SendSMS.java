package org.smvdu.payroll.api.sms;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class SendSMS
{


	public static String send(ArrayList<String> numbers,String message,SMSProfile profile)
	{
		boolean debug=true;
                String report = "";
		String phone	="";
		String ppgHost	="http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp";
		String username ="sushant001";
		String password ="146948891";

		try
		{
                        for(String s: numbers)
                        {
                            phone+=URLEncoder.encode(s, "UTF-8")+",";
                        }
                        int pl = phone.length();
                        phone = phone.substring(0,pl-1);

			if(debug)
				System.out.println("phone------>"+phone);
			message=URLEncoder.encode(message, "UTF-8");

			if(debug)
				System.err.println("message---->"+message);
		}
		catch (UnsupportedEncodingException e)
		{
			System.out.println("Encoding not supported");
		}

		//String url_str=ppgHost+"?PhoneNumber="+phone+"&Text="+message;
		 String url_str=ppgHost+"?username="+profile.userName+"&password="+profile.password+
                         "&sendername="+profile.senderName+"&mobileno="+phone+"&message="+message;

		if(debug)
			System.out.println("url string->"+url_str);


		try
		{
			URL url2=new URL(url_str);

			HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
			connection.setDoOutput(false);
			connection.setDoInput(true);

			if(debug)
				System.out.println("Opened Con->"+connection);

			String res=connection.getResponseMessage();
                        Scanner sc = new Scanner(connection.getInputStream());
                        while(sc.hasNextLine())
                        {
                            report+=sc.nextLine()+"\n";
                        }
			if(debug)
				System.out.println("Get Resp  ->"+res);

			int code = connection.getResponseCode () ;

			if ( code == HttpURLConnection.HTTP_OK )
			{
				connection.disconnect() ;
			}
		}
		catch(IOException e)
		{
			System.out.println("unable to create new url"+e.getMessage());
		}
                 FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "SMS Sent", ""));
                return report;


	} // main
} // class