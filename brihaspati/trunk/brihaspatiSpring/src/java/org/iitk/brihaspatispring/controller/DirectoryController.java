package org.iitk.brihaspatispring.controller;

/*
 * @(#)DirectoryController.java
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

/**
 *  @author: <a href="mailto:vipulk@iitk.ac.in">vipul kumar pal</a>
 */


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Vector;
import java.util.List;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspatispring.utils.Pagination;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.torque.Torque; 
import javax.servlet.ServletContext;
import org.apache.torque.util.Criteria;

public class DirectoryController extends SimpleFormController {

	private Torque set=null;
        private ServletContext context=null;
	public static String url;	
	String match="";
	String str="";
	String query="";
	String crtm=null;
    	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map myModel = new HashMap();
		int user_id=Integer.parseInt(request.getParameter("uid"));
		myModel.put("uid",user_id);
		String mode=request.getParameter("mode");
		myModel.put("mod",mode);
		String instid=request.getParameter("instid");
		myModel.put("instid",instid);
		String ip=request.getParameter("ip");
		myModel.put("ip",ip);
		int port=request.getServerPort();
                myModel.put("port",port);
		crtm = request.getParameter("crt");
		if(crtm == null)
		myModel.put("crt","null");
		else
		myModel.put("crt",crtm);
		Vector l=new Vector();
                try {
			set=new Torque();
                        String tempFileName = getServletContext().getRealPath("Torque.properties");
                        set.init(tempFileName);

			Vector vect1 = new Vector();
			Vector vect = new Vector();
			List st=null;
			List st1=null;
			match=request.getParameter("matchstring");
			myModel.put("match",match);
				Criteria crit = new Criteria();
				String table="TELEPHONE_DIRECTORY";

			if(instid.equals("")){
				 if( (match!=null) && (!match.equals("")) ) {
                                        query = request.getParameter("querylist");
                                        if(query.equals("name")){
                                                str="NAME";
                                        }
                                        else if(query.equals("mailid"))
                                                str="MAIL_ID";
                                        else if(query.equals("address"))
                                                str="ADDRESS";
                                        else if(query.equals("department"))
                                                str="DEPARTMENT";

                                        crit=new Criteria();
                                        crit.add(table,str,(Object)("%"+match.trim()+"%"),crit.LIKE);
                                        st1=TelephoneDirectoryPeer.doSelect(crit);
                                        vect = new Vector(st1);
                                        vect1.addAll(vect);
                                }
				else{
				Criteria crit1=new Criteria();
				crit1.addGroupByColumn(TelephoneDirectoryPeer.USER_ID);
				crit1.addAscendingOrderByColumn(TelephoneDirectoryPeer.ID);
                                                st1=TelephoneDirectoryPeer.doSelect(crit1);
                                                vect = new Vector(st1);
                                                vect1.addAll(vect);
				}

			}
			else{
			String []temp1;
			temp1 = instid.split("/");
			for(int p =1; p < temp1.length ; p++){
				String inst = temp1[p];

				if( (match!=null) && (!match.equals("")) ) {
					query = request.getParameter("querylist");
                	                if(query.equals("name")){
                        	                str="NAME";
					}
	                                else if(query.equals("mailid"))
        	                                str="MAIL_ID";
                	                else if(query.equals("address"))
                        	                str="ADDRESS";
                                	else if(query.equals("department"))
	                                        str="DEPARTMENT";
	
        	                        crit=new Criteria();
					String str44="INSTITUTE_ID";
					crit.add(table,str,(Object)("%"+match.trim()+"%"),crit.LIKE);
					crit.add(table,str44,(Object)("%"+inst+"%"),crit.LIKE);
	                                st1=TelephoneDirectoryPeer.doSelect(crit);
					vect = new Vector(st1);
					vect1.addAll(vect);
				}
				else {
                	               	Criteria crit1=new Criteria();
						String str11="INSTITUTE_ID";
						crit1.add(table,str11,(Object)("%"+inst+"%"),crit.LIKE);
                        		       	st1=TelephoneDirectoryPeer.doSelect(crit1);
						vect = new Vector(st1);
						vect1.addAll(vect);
				}
			}
			}
			st = vect1;
			HashSet set = new HashSet(st);
	                st.clear();
        	        st.addAll(set);

			myModel.put("query",query);


			int size = 0;
			for(int i=0;i<st.size();i++){
				TelephoneDirectory element=(TelephoneDirectory)(st.get(i));
				size++;
				TelephoneDirectory dir = new TelephoneDirectory();
				int id=element.getId();
				dir.setId(id);
				dir.setName(element.getName());
				dir.setMailId(element.getMailId());
				dir.setAddress(element.getAddress());

				if(mode.equals("psnl")){
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1") || officeno.startsWith("2") )
                                                dir.setOfficeNo(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeNo("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1") || mobileno.startsWith("2") )
                                                dir.setMobileNo(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileNo("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1") || homeno.startsWith("2") )
                                                dir.setHomeNo(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeNo("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1") || otherno.startsWith("2") )
                                                dir.setOtherNo(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherNo("");}
                                }
				else if(mode.equals("prot")){
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1") || officeno.startsWith("2"))
                                                dir.setOfficeNo(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeNo("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1") || mobileno.startsWith("2"))
                                                dir.setMobileNo(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileNo("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1") || homeno.startsWith("2"))
                                                dir.setHomeNo(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeNo("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1") || otherno.startsWith("2"))
                                                dir.setOtherNo(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherNo("");}

                                }
				else if(mode.equals("stud")){
				if(user_id == element.getUserId()){
					String officeno=element.getOfficeNo();
					String []off = officeno.split("-");
					dir.setOfficeNo(off[1]+off[2]+off[3]+off[4]);

					String mobileno=element.getMobileNo();
					String []temp = mobileno.split("-");
					dir.setMobileNo(temp[1]+temp[2]+temp[3]+temp[4]);

					String homeno = element.getHomeNo();
					String []home = homeno.split("-");
					dir.setHomeNo(home[1]+home[2]+home[3]+home[4]);

					String otherno = element.getOtherNo();
					String []other = otherno.split("-");
					dir.setOtherNo(other[1]+other[2]+other[3]+other[4]);

				}
				else{
                                        String officeno=element.getOfficeNo();
                                        String []off = officeno.split("-");
                                        if(officeno.startsWith("1"))
                                                dir.setOfficeNo(off[1]+off[2]+off[3]+off[4]);
                                        else{   dir.setOfficeNo("");    }

                                        String mobileno=element.getMobileNo();
                                        String []temp = mobileno.split("-");
                                        if(mobileno.startsWith("1"))
                                                dir.setMobileNo(temp[1]+temp[2]+temp[3]+temp[4]);
                                        else{dir.setMobileNo("");}

                                        String homeno = element.getHomeNo();
                                        String []home = homeno.split("-");
                                        if(homeno.startsWith("1"))
                                                dir.setHomeNo(home[1]+home[2]+home[3]+home[4]);
                                        else{dir.setHomeNo("");}

                                        String otherno = element.getOtherNo();
                                        String []other = otherno.split("-");
                                        if(otherno.startsWith("1"))
                                                dir.setOtherNo(other[1]+other[2]+other[3]+other[4]);
                                        else{dir.setOtherNo("");}
				}
                                }

				dir.setDepartment(element.getDepartment());
				dir.setDesignation(element.getDesignation());
				dir.setState(element.getState());
				l.add(dir);
			}
			if(size==0){
                                        myModel.put("ErrorMsg","No Data find Respected Your search");
                                   }

			myModel.put("list",l);
			String AdminConff = request.getParameter("adminconf");
                        int AdminConf = Integer.parseInt(AdminConff);
                        myModel.put("AdminConf",new Integer(AdminConf));
                        myModel.put("AdminConf_str",Integer.toString(AdminConf));
                        String Index=request.getParameter("startIndex");
			int startIndex;
			myModel.put("AdminConf_str11",Index);
			if(Index==null){
				startIndex=0;
			}else{
				startIndex=Integer.parseInt(Index);
			}
			myModel.put("count",startIndex+1);
                        String status=new String();
                        int t_size=size;
			if(size!=0){
                                        status="Noblank";
                                        int value[]=new int[7];
                                        value=Pagination.linkVisibility(startIndex,t_size,AdminConf);
                                        int k=value[6];
                                        myModel.put("k",String.valueOf(k));
                                        Integer total_size=new Integer(t_size);
                                        myModel.put("total_size",total_size);
                                        int eI=value[1];
                                        Integer endIndex=new Integer(eI);
					myModel.put("stIndex",value[0]);

                                        myModel.put("endIndex",endIndex);
                                        int check_first=value[2];
                                        myModel.put("check_first",String.valueOf(check_first));
                                        int check_pre=value[3];
                                        myModel.put("check_pre",String.valueOf(check_pre));
                                        int check_last1=value[4];
                                        myModel.put("check_last1",String.valueOf(check_last1));
                                        int check_last=value[5];
                                        myModel.put("check_last",String.valueOf(check_last));

                                        myModel.put("startIndex",String.valueOf(eI));
                                        Vector splitlist=Pagination.listDivide(l,startIndex,AdminConf);

                                        myModel.put("dirList",splitlist);
                                }
			else
                                {
                                        status="blank";
                                }
                                myModel.put("status",status);


		}catch(Exception es){}
		return new ModelAndView("directory", "model", myModel);
	}
}

