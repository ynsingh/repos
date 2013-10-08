package in.ac.dei.edrp.cms.dao.mailConfiguration;



import in.ac.dei.edrp.cms.domain.mailConfiguration.MailConfigurationDomain;

import java.util.List;

public interface MailConfigurationDao {

	List<MailConfigurationDomain> getExistingConfigurationDetails(MailConfigurationDomain mailConfigurationDomain);
	
	String updateExistingConfigurationDetails(MailConfigurationDomain mailConfigurationDomain);
	
	String insertConfigurationDetails(MailConfigurationDomain mailConfigurationDomain);
}
