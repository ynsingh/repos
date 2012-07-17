/**
 * @(#) PrintJobWatcher.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.edrp.cms.controller.reportgeneration;

import javax.print.DocPrintJob;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 * This class is for watching the job while printing
 * @author Ashutosh Pachauri
 */
		
public class PrintJobWatcher {
	
	boolean done = true;
	/**
	 * This is a constructor implementing the needed interface for the tackling the print job 
	 * @param job An instance of DocPrintJob type
	 */
	public PrintJobWatcher(DocPrintJob job)	
	{
		System.out.println("in constructor");
		 (job).addPrintJobListener(new PrintJobAdapter(){						 
			public void printDataTransferCompleted(PrintJobEvent arg0) {
				
				System.out.println("print job transffer completed");
				allDone();
			}

			public void printJobCanceled(PrintJobEvent arg0) {
				System.out.println("print job cancelled");
				allDone();
			}

			public void printJobCompleted(PrintJobEvent arg0) {
				System.out.println("print job completed");
				allDone();
				
			}

			public void printJobFailed(PrintJobEvent arg0) {
				System.out.println("print job failed");
				allDone();
			}

			public void printJobNoMoreEvents(PrintJobEvent arg0) {				
				System.out.println("print job has no more events");
				allDone();
				
			}

			public void printJobRequiresAttention(PrintJobEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Rectifiable problem occured (e.g. printer out of paper).");
				allDone();
			}
			 void allDone() {
				                        synchronized (PrintJobWatcher.this) {
				                            done = true;
				                            PrintJobWatcher.this.notify();
				                        }
			 }
			
		});
	}
	public void notDone() 
	{
		System.out.println("in not done");
	synchronized (PrintJobWatcher.this) 
	{
	done = false;
	PrintJobWatcher.this.notify();
	}
	}
	public synchronized String waitForDone(){
		            try {
		            	System.out.println("in done");
		                while (!done) {
		                	System.out.println("in wait");
		                    wait();
		                    return "jobDone";
		                }
		                return "jobDone";
		            } catch (InterruptedException e) {
		            	System.out.println("iterruption");
		            	return e.getMessage();
		            	
		            }
		        }

}
