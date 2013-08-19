package org.iitk.brihaspati.modules.utils;

/*
 * @(#)QuotationController.java
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
import java.util.Hashtable;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.commons.lang.StringUtils;

/**
 * This class sets the quotation 
 * to be displayed in a hashtable
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @date 23-04-2013
 */
public class QuotationController
{
	//private String quotation;

	private static QuotationController quotc=null;

	private Hashtable ht = new java.util.Hashtable();

	public static QuotationController getController()
	{
                if (quotc==null)
                        quotc=new QuotationController();
                return quotc;
        }

	protected Hashtable getHashtable()
	{
                return ht;
        }	

	public String getQuotation()
        {
		String str = null;
		str = (String)ht.get("tmp_quot");
		if(StringUtils.isBlank(str)){
			QuotationThread.getController().Quotation();
			str = (String)ht.get("tmp_quot");
		}
		return str;
        }

	
}
