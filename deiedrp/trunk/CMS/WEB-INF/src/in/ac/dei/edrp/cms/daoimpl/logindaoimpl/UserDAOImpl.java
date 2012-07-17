package in.ac.dei.edrp.cms.daoimpl.logindaoimpl;

import in.ac.dei.edrp.cms.dao.logindao.UserDAO;
import in.ac.dei.edrp.cms.domain.login.Login;
import in.ac.dei.edrp.cms.domain.logindomain.UserBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO{

	public TransactionTemplate transactionTemplate;
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public List<UserBean> getAllUserList() {
		// TODO Auto-generated method stub
		List<UserBean> list=new ArrayList<UserBean>();
		try{
			
			
			List<UserBean> userList=(List<UserBean>)getSqlMapClientTemplate().queryForList("SpringDemo1.getUserList", new Login());
			
			for(UserBean userValue: userList){
			
				list.add(new UserBean(userValue.getUserId(),
						userValue.getUserName(),userValue.getCity(),
						userValue.getQualification()));
			}
			
		}catch(Exception e){
			System.out.println("Exception is: "+e.getMessage());
		}
		return list;
	}

	public Boolean editDetail(final UserBean userBean) {
		// TODO Auto-generated method stub
		
		return (Boolean) transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			//Object savepoint=null;
		
			@Override
			public void doInTransactionWithoutResult(TransactionStatus status) {
			try{
				System.out.println("Transaction started at this time:"+System.currentTimeMillis());
				List<UserBean> user=getSqlMapClientTemplate().queryForList("SpringDemo1.getLockOnUser",userBean);
				for(UserBean uBean:user){
					System.out.println("user name is "+uBean.getUserName());
				}
				
				Thread.sleep(5000);
				
				getSqlMapClientTemplate().update("SpringDemo1.updateCity",userBean);
				
				System.out.println("Transaction ended at this time:"+System.currentTimeMillis());
				
			}catch(DataAccessException e){
				System.out.println("Exception "+e);
				System.out.println("before save Deepak Pandey inside data access="+status.isNewTransaction());
				status.setRollbackOnly();
				//throw new CRException("Object is busy"+userBean.getUserId());
					} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}//
	
        });
			
			
	
		
		
	}

	
	
}
