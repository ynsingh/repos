/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;



//import  com.myapp.struts.hbm.*;

import  com.myapp.struts.hbm.Privilege;
import  com.myapp.struts.hbm.SerPrivilege;
import  com.myapp.struts.hbm.CirPrivilege;
import  com.myapp.struts.hbm.CatPrivilege;
import  com.myapp.struts.hbm.AcqPrivilege;
import  com.myapp.struts.hbm.Login;
import  com.myapp.struts.hbm.StaffDetail;
import  com.myapp.struts.AdminDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class ShowCheckedPrivilegeAction extends org.apache.struts.action.Action {
    
   
   
    private String staff_id;
    private String library_id;
    private String sublibrary_id;
LoginDAO logindao;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {



logindao=new LoginDAO();




     //   String sql1,sql2,sql3,sql4,sql5;
     //   ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
      //  staff_id = (String)request.getAttribute("staff_id");
      //  sublibrary_id=(String)session.getAttribute("sublibrary_id");


        //if (staff_id==null)
            staff_id = (String)request.getParameter("staff_id");
        System.out.println("staff_id4="+staff_id);

        StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
        sublibrary_id=staffobj.getSublibraryId();
System.out.println(sublibrary_id);

Login staffobj1=logindao.searchStaffLogin(staff_id, library_id,sublibrary_id);
if(staffobj1!=null)
 request.setAttribute("staff_role",staffobj1.getRole());

System.out.println(staffobj1.getRole());

        Privilege privobj=PrivilegeDAO.getPrivilege(library_id, sublibrary_id, staff_id);
        AcqPrivilege acqprivobj=AcqPrivilegeDAO.getPrivilege(library_id, sublibrary_id, staff_id);
        CatPrivilege catprivobj=CatPrivilegeDAO.getPrivilege(library_id, sublibrary_id, staff_id);
        CirPrivilege cirprivobj=CirPrivilegeDAO.getPrivilege(library_id, sublibrary_id, staff_id);
        SerPrivilege serprivobj=SerPrivilegeDAO.getPrivilege(library_id, sublibrary_id, staff_id);


      

        //sql1 ="select * from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        //sql2="select * from acq_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        //sql3="select * from cat_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        //sql4="select * from cir_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        //sql5="select * from ser_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;

        //privilege_resultset=MyQueryResult.getMyExecuteQuery(sql1);
        //acq_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql2);
        //cat_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql3);
        //cir_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql4);
        //ser_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql5);

        //privilege_resultset.next();
        //acq_privilege_resultset.next();
        //cat_privilege_resultset.next();
        //cir_privilege_resultset.next();
        //ser_privilege_resultset.next();
        //System.out.println(cir_privilege_resultset.getString(3));

        session.setAttribute("privilege", privobj);

        session.setAttribute("acq_privilege", acqprivobj);
        session.setAttribute("cat_privilege", catprivobj);
        session.setAttribute("cir_privilege", cirprivobj);
        session.setAttribute("ser_privilege", serprivobj);
        request.setAttribute("new_staff_id",staff_id);
        request.setAttribute("staff_sub_library",sublibrary_id);



        request.setAttribute("new_staff_name",staffobj.getFirstName()+" "+staffobj.getLastName());
      
        return mapping.findForward("success");
    }
}
