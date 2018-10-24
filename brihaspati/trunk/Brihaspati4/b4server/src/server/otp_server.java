package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebServlet;


public class otp_server { 
	public static boolean otpverify(String otp) throws IOException {
	boolean flag = false;
	ServerSocket serverSocket = new ServerSocket(6666);
	//System.out.println("Server running and waiting for client...");
	Socket clientSocket = serverSocket.accept();
	DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
	//Scanner sc = new Scanner(clientSocket.getInputStream());
	String str = (String)dis.readUTF();		
	//int number =sc.nextInt();
	System.out.println(str);
	//String recdotp = String.valueOf(number);
	if (str==str) {
		flag = true;
		serverSocket.close();
		return flag;
		
	}
	else 
	{	serverSocket.close();
		return flag;}
	
	//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
	// Server waits for a client to send its user ID
	//String id = in.readLine();
	
	// Server generates an OTP and waits for client to send this
	//Random r = new Random();
	//String otp = new String();
	//for(int i=0 ; i < 8 ; i++) {
	//	otp += r.nextInt(10);
//	}
	//System.out.println(otp);
	
	// Server starts a timer of 10 seconds during which the OTP is valid.
	
			
	/*if(newId.equals(id)) {
		// User ID is verified
		if(task.isTimedOut) {
			// User took more than 100 seconds and hence the OTP is invalid
			out.println("Time out!");
		} else if(!newOtp.equals(otp)) {
			out.println("Incorrect OTP!");
		} else {
			out.println("Logged In!");
			flag = true;
		}
	}*/
	
}
	
}
