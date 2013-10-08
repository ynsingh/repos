package in.ac.dei.edrp.cms.daoimpl.mailConfiguration;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import in.ac.dei.edrp.cms.dao.mailConfiguration.MailConfigurationDao;
import in.ac.dei.edrp.cms.domain.mailConfiguration.MailConfigurationDomain;


public class MailConfigurationDaoImpl extends SqlMapClientDaoSupport implements MailConfigurationDao{

	private static Logger logObj = Logger.getLogger(MailConfigurationDaoImpl.class);
			
	public List<MailConfigurationDomain> getExistingConfigurationDetails(
			MailConfigurationDomain mailConfigurationDomain) {
		List<MailConfigurationDomain> detailsList = null;
		try{
			detailsList = getSqlMapClientTemplate().queryForList("mailConfiguration.getExistingConfigurationDetails", mailConfigurationDomain);
		}catch(Exception ex){
			logObj.error("Exception In getExistingConfigurationDetails Method" + ex);
		}
		return detailsList;
	}

	public String insertConfigurationDetails(
			MailConfigurationDomain mailConfigurationDomain) {
		String statusValue = "";
		try{
			getSqlMapClientTemplate().insert("mailConfiguration.insertConfigurationDetails", mailConfigurationDomain);
			statusValue = "YES";
		}catch(Exception ex){
			statusValue = "NO";
		}
		return statusValue;
	}

	public String updateExistingConfigurationDetails(
			MailConfigurationDomain mailConfigurationDomain) {
		String statusValue = "";
		try{
			getSqlMapClientTemplate().update("mailConfiguration.updateExistingConfigurationDetails", mailConfigurationDomain);
			statusValue = "YES";
		}catch(Exception ex){
			statusValue = "NO";
		}
		return statusValue;
	}

}
