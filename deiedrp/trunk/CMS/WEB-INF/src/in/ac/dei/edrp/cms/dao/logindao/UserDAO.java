package in.ac.dei.edrp.cms.dao.logindao;

import in.ac.dei.edrp.cms.domain.logindomain.UserBean;

import java.util.List;

public interface UserDAO {

	public List<UserBean> getAllUserList();
	
	public Boolean editDetail(final UserBean userBean);
	
}
