package org.iitk.brihaspati.modules.utils;
/*
 * @(#)ViewAllQuestionUtil.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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

import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *  @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>
 *  @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 */

public class ViewAllQuestionUtil{

	/**Code for getting all combination(All Question type and All Difficulty Level)
	*@param String questype
	*@param String difflevel
	*Return Vector
	*/
	public static Vector getTopicAllFile(String topic,String filepath,String questype,String difflevel)
	{
		Vector allfile=new Vector();
		try
		{
			TopicMetaDataXmlReader topicmetadata=null;      
                        topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+"QBtopiclist.xml");
                        Vector collect=topicmetadata.getQuesBanklist_Detail();
                        if(collect!=null)
                        {
                                for(int i=0;i<collect.size();i++)
                                {//for
                                        String topicname =((FileEntry) collect.elementAt(i)).getTopic();
                                        String filename =((FileEntry) collect.elementAt(i)).getfileName();
					if(topicname.equals(topic)){
						if(questype.equals("All")&&(difflevel.equals("All"))){
						allfile.add(filename);
						}
						else if(questype.equals("All")&&(filename.contains(difflevel))){
						allfile.add(filename);
						}
						else if((filename.contains(questype))&&(difflevel.equals("All"))){
						allfile.add(filename);
						}
						else{
						if((filename.contains(questype))&&(filename.contains(difflevel)))
						allfile.add(filename);
						}
					}		
                                }
                        }
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("The Exception in ViewAllQuestionUtil method[getTopicAllFile]"+e);}
		return allfile;
	}
	public static Vector ReadTopicAllFile(String topic,String filepath,String questiontype,String difflevel)
	{
		Vector allques=new Vector();
		try{
			String quesid="",ques="",opt1="",opt2="",opt3="",opt4="",Answer="",Desc="",ImgUrl="";
			TopicMetaDataXmlReader topicmetadata=null;
                        Vector allfile=getTopicAllFile(topic,filepath,questiontype,difflevel);
                        for(int k=0;k<allfile.size();k++)
                        {
				Vector Read=new Vector();
                                String file=(String)allfile.get(k);
				String btwlist=StringUtils.substringBetween(file,"_",".");
				String tpc[]=btwlist.split("_");
				String diffval=tpc[0];
				String qtypeval=tpc[1];
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+file);
                                if(questiontype.equals("mcq")){
                                Read=topicmetadata.getQuesBank_Detail();
                                }
                                else{
                                Read=topicmetadata.getQuesBank_Detail1();
                                }
				if(Read != null)
                        	{
                               		for(int n=0;n<Read.size();n++)
                               		{
                                       		quesid =((FileEntry)Read.elementAt(n)).getquestionid();
                                       		ques =((FileEntry)Read.elementAt(n)).getquestion();
                                       		if(questiontype.equals("mcq")){
                                       			opt1 =((FileEntry)Read.elementAt(n)).getoptionA();
                                       			opt2 =((FileEntry)Read.elementAt(n)).getoptionB();
                                       			opt3 =((FileEntry)Read.elementAt(n)).getoptionC();
                                        		opt4 =((FileEntry)Read.elementAt(n)).getoptionD();
                                        	}
                                        	Answer =((FileEntry)Read.elementAt(n)).getAnswer();
                                        	Desc =((FileEntry)Read.elementAt(n)).getDescription();
                                        	ImgUrl=((FileEntry)Read.elementAt(n)).getUrl();
                                        	Map map = new HashMap();
                                        	if(questiontype.equals("mcq")){
                                        		map.put("quesid",quesid);
                                       			map.put("ques",ques);
                                        		map.put("opt1",opt1);
                                        		map.put("opt2",opt2);
                                        		map.put("opt3",opt3);
                                        		map.put("opt4",opt4);
                                        		map.put("Answer",Answer);
                                        		map.put("Desc",Desc);
                                        		map.put("ImgUrl",ImgUrl);
                                        		map.put("Qtype",qtypeval);
                                        		map.put("dlevel",diffval);
                                        	}
                                        	else{
                                        		map.put("quesid",quesid);
                                        		map.put("ques",ques);
                                        		map.put("Answer",Answer);
                                        		map.put("Desc",Desc);
                                        		map.put("ImgUrl",ImgUrl);
                                        		map.put("Qtype",qtypeval);
                                        		map.put("dlevel",diffval);
                                        	}
                                        	allques.add(map);
					}//for
				}//if
			}//for
		}//try
		catch(Exception e){ErrorDumpUtil.ErrorLog("The Exception in ViewAllQuestionUtil method[ReadTopicAllFile]"+e);}
		return allques;
	}//method
}
