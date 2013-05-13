package org.iitk.brihaspati.modules.utils;

/*
 * @(#) QuotationThread.java
 *
 *  Copyright (c) 2013 conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

import java.util.Hashtable;
import java.io.File;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;

/**
 * This thread reads the quotation 
 * chosen randomly and displays the
 * quotation.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @date 25-04-2013
 */
public class QuotationThread implements Runnable {

	public static int load_flag = 0;
	public int active_user = 0;
        private boolean flag=false;
        private static Thread runner=null;
        private static QuotationThread quotation_thread=null;


        public static QuotationThread getController(){
                if(quotation_thread== null)
		{
			quotation_thread =new QuotationThread();
			load_flag = 0;
                }
		return  quotation_thread;
        }

        private void start(){
                if (runner == null) {
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
        }

        private void stop() {
                if (runner != null) {
                        flag=false;
                        runner.interrupt();
                        runner = null;
                }
        }

        public void Quotation(){
                start();
        }

        public void run() {
		String quot;
		//XMLWriter_Quotation xquote = new XMLWriter_Quotation();
                try{
                        while(flag){
				String filepath=TurbineServlet.getRealPath("/Quotation");
				filepath=filepath+"/Quotation.xml";
				Hashtable ht = QuotationController.getController().getHashtable();
                        	if(new File(filepath).exists())
				{
					quot = XMLWriter_Quotation.randomSelect(filepath);
					if(!quot.equals(null))
					{
						ht.put("tmp_quot", quot);
					}
				}
	                        stop();
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in QuotationThread class==="+e);}
        }

	public void setLoadFlag(int load_flag)
	{
		this.load_flag = load_flag;
	}

	public int getLoadFlag()
	{
		return load_flag;
	}

	public void setActiveUser(int active_user)
        {
                this.active_user = active_user;
        }

        public int getActiveUser()
        {
                return active_user;
        }

}//end of class
 
