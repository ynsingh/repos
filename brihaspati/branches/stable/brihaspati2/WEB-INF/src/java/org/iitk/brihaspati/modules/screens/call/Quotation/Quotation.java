package org.iitk.brihaspati.modules.screens.call.Quotation;

/*
 * @(#)Quotation.java
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

import java.util.Vector;
import java.util.List;
import java.util.Iterator;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Quotation;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.apache.turbine.services.servlet.TurbineServlet;

/**
 * This class reads the quotations
 * stored in xml file and displays 
 * them in the form of list.
 * @author <a href="mailto:richa.tandon1@gmail.com ">Richa Tandon</a>
 * @author <a href="mailto:rpriyanka12@ymail.com ">Priyanka Rawat</a> 
 * @date 23-04-2013
 */
public class Quotation extends SecureScreen
{
	public void doBuildTemplate( RunData data, Context context ) {
                try{
                        ParameterParser pp = data.getParameters();
			String file = (String)data.getUser().getTemp("LangFile");
			String mode=pp.getString("mode","");
                        context.put("mode",mode);
			String filePath=TurbineServlet.getRealPath("/Quotation");
			Vector quoteDetail =XMLWriter_Quotation.getQuotationDetails(filePath+"/Quotation.xml");
                        context.put("qdetail",quoteDetail);
			String quot_id = pp.getString("quotid","");
			String quotvalue="";
			if(!quot_id.equals(""))
			{
				for(int i=0;i<quoteDetail.size();i++)
				{
					InstituteFileEntry  InstfileEntry =  (InstituteFileEntry)quoteDetail.get(i);
					String xmlquotid = InstfileEntry.getQuotationId();
					if(xmlquotid.equals(quot_id)){
						quotvalue = InstfileEntry.getQuotationValue();
						break;
					}
				}
			}
			context.put("editQuot_Value",quotvalue);
			context.put("editQuot_Id",quot_id);
		}
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("An error occured in Quotation screens------- "+e);
                }
	}
}
