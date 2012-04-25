package org.iitk.brihaspati.modules.screens.call;

/*
 * @(#)ViewFilePlayer.java
 *
 *  Copyright (c)  2010 ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
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
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ViewFileUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.RemoteCourseUtilClient;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.net.URLEncoder;
import java.net.URLDecoder;

/**
 * In this class, Display of contents for Flv file.
 * @author <a href="smita37uiet@gmail.com">Smita Pal</a>
 */

public class ViewFilePlayer extends SecureScreen
{
        private String fileID=null;
        private String filePath=new String();
        private String URL=new String();
        private int index_filename;
        private MultilingualUtil m_u=new MultilingualUtil();


        /**
        * In this method,We display contents of Flv file 
        * @param data RunData
	* @param context Context
        */

        public void doBuildTemplate( RunData data, Context context )
        {
                try{
                        ParameterParser pp=data.getParameters();
			/*
                         * getting the url of file and by the help of url getting filename
                         */
                        URL = data.getRequest().getPathInfo().toString();
                        index_filename=URL.indexOf("filename");
                        int endIndex=URL.indexOf("/",index_filename);
                        fileID=URL.substring(endIndex+1);
                        fileID=fileID.replace(",","/");
                        fileID=URLDecoder.decode(fileID, "UTF-8");
			/*
			 * getting the topic,type for file path
                         */
			String topic=pp.getString("topic","");
                        topic=topic.replaceAll(" ","+");
                        String filename=pp.getString("filename","");
			String type=pp.getString("type","");
			/*
                         * sending the varible to default.vm
                         */
                        pp.add("str","video");
			/*
	       		 *getting the servername and portno
			 */
                        String Servername=data.getServerName();
                        int Portno=data.getServerPort();
			/*
			 *getting the actual path where stored the flv file
			 */
                        String filePath="http://"+Servername+":"+Portno+"/brihaspati/servlet/brihaspati/template/call,ViewFileContent.vm/topic/"+topic+"/type/"+type+"/filename/"+filename;
			/*
                         *sending the path to vm file
                         */

                        context.put("filePath",filePath);


                }//try
                catch(Exception e)
                {
                        String msg="Error in Raw Page for display contains !!"+ e;
                        ErrorDumpUtil.ErrorLog(msg);

                }
 }//dobuild
}

