
package com.ehelpy.brihaspati4.overlaymgmt;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestBed {
	//*************** Creating a Overlay mgmt  Task **********************************
	static TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {

			//Invoking Overlay Mgmt function.

			Overlay OverlayMgmtObject = new Overlay(); 	//Instance of New7 class
			OverlayMgmtObject.OverlayMgmt();		// Actual action of giving corrections to
			//own RT. i.e. Overlay mgmt.

		}
	};

	//*************** Implementing periodic / Trigger-based  Overlay Mgmt **************
	
	//  Periodic ---------> 	At every 30 mins or so using timer.
	//
	//  Trigger-based ----> 	In event of any of the below :
	//							(a) new Node Addition  OR
	//							(b) Node Departure (gracefully OR w/o info)  OR
	//							(c) Node dies(no ping response).

	public static void main(String[] args) {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 15*1000);   //Overlay Mgmt every 15 secs in this case.
															// Actually Periodic = At every 30 mins (1800*1000 milli seconds).
		
		// timer.cancel() on Client LOG-OUT.
	}

}
