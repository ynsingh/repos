package org.IGNOU.ePortfolio.Dropdown;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.User;
import org.IGNOU.ePortfolio.DAO.TestimonialDao;

/**
 *
 * @author IGNOU Team
 * @version 2
 * @since 31-08-2012
 */
public class TestimonialRequest extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private TestimonialDao dao = new TestimonialDao();
    private UserProgrammeDao updao=new UserProgrammeDao();
    private Map<String, String> facultyList = null;
    private List<User> IPList, TUList;

    public String getFaculty() {
        IPList = updao.UserListByUserId(user_id); //getting the institute and programm Id
        TUList = dao.UserListByInstituteIdProgrammeIdRole(IPList.iterator().next().getInstituteId(), IPList.iterator().next().getProgrammeId(), IPList.iterator().next().getRole());
        facultyList = new HashMap<String, String>();
        for (int i = 0; i < TUList.size(); i++) {
            facultyList.put(TUList.get(i).getEmailId(), TUList.get(i).getFname() + " " + TUList.get(i).getLname());
        }
        return SUCCESS;
    }

    public String getJSON() {
        return getFaculty();
    }

    /**
     * @return the facultyList
     */
    public Map<String, String> getFacultyList() {
        return facultyList;
    }
}
