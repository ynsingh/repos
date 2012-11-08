package com.myapp.struts.opac;
import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.Department;
import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class OpacSubLibrarySearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emp_ids = new StringBuffer();
        OpacSearchDAO opacDAO = new OpacSearchDAO();
DeptDAO dept=new DeptDAO();
AdminRegistrationDAO admindao=new AdminRegistrationDAO();
        String searchText = request.getParameter("getSubLibrary_Id");
        List sublibrary = opacDAO.subLibrarySearch(searchText);

        Iterator it = sublibrary.iterator();
        int tcount=0;
        emp_ids.append("<sublibrary_ids>");
        while (it.hasNext())
        {

//construct the xml string.
            SubLibrary sublibpojo = (SubLibrary)sublibrary.get(tcount);
            Department deptdao=(Department)dept.getDeptName(searchText,sublibpojo.getSublibName());
            emp_ids.append("<sublibrary_id>"+sublibpojo.getId().getSublibraryId()+"</sublibrary_id>");
            if(deptdao!=null)
            {
            emp_ids.append("<sublibrary_name>"+deptdao.getDeptName()+"</sublibrary_name>");
            }else{
                if(sublibpojo.getId().getSublibraryId().equalsIgnoreCase(searchText))
                {
                      AdminRegistration admin=admindao.searchInstitute(searchText);

                    emp_ids.append("<sublibrary_name>"+admin.getLibraryName()+"/Central Library"+"</sublibrary_name>");
                }
                else
                    emp_ids.append("<sublibrary_name>"+sublibpojo.getSublibName()+"</sublibrary_name>");
            }
                
                
                tcount++;
                it.next();
        }
        emp_ids.append("</sublibrary_ids>");


        if(emp_ids!=null)
        {response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
        }
        return null;
    }
}






