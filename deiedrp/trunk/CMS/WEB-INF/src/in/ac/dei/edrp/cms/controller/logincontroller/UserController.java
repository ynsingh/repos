package in.ac.dei.edrp.cms.controller.logincontroller;

import in.ac.dei.edrp.cms.dao.logindao.UserDAO;
import in.ac.dei.edrp.cms.domain.logindomain.UserBean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController{

	private UserDAO userDAO;
		
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public ModelAndView userList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
			List<UserBean> userList=userDAO.getAllUserList();
		
			System.out.println("Coming User controller");

			return new ModelAndView("spring/userList", "userList",userList);

	}
	
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
					
				
			System.out.println("Coming User edit controller");
			UserBean  userBean=new UserBean();
			userBean.setUserId(request.getParameter("param"));
			userBean.setCity("Auraiya");
			
			userDAO.editDetail(userBean);
			
			List<UserBean> userList=userDAO.getAllUserList();
			return new ModelAndView("spring/userList", "userList",userList);

	}
	 
}
