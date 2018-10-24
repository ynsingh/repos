package com.ehelpy.brihaspati4.authenticate ; 

import com.ehelpy.brihaspati4.authenticate.CertificateSignature;
import com.ehelpy.brihaspati4.authenticate.ReadVerifyCert;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.Object;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import com.sun.net.ssl.HttpsURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;

public class createConnection   {
	private final String USER_AGENT = "Chrome";
	
	
	// HTTP GET request
			void sendGet(String urlmaster) throws Exception {

			//String url = "http://www.google.com/search?q=mkyong";	
			
			URL obj = new URL(urlmaster);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + urlmaster);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}

		// HTTP POST request
		void sendPost(String urlmaster) throws Exception {

			//String mserverurl ="http://localhost:8080/IS";				
			//String MSrequrl = mserverurl +"/ProcessRequest?req=sscccertsign&";

			
			URL obj = new URL(urlmaster);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
		
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + urlmaster);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
		}
		
		
	}

