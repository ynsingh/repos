package in.ac.dei.edrp.cms.dao.withdrawmarkstransfer;

import in.ac.dei.edrp.cms.domain.withdrawmarkstransfer.WithdrawMarksTransferGetter;
import java.util.List;

public interface WithdrawMarksTransferDao {

	public List<WithdrawMarksTransferGetter> getDetails(WithdrawMarksTransferGetter input);

	public List<WithdrawMarksTransferGetter> getSession(WithdrawMarksTransferGetter input);

	public String enableRegistration(WithdrawMarksTransferGetter input);

	public List<WithdrawMarksTransferGetter> getRegistrationDates(WithdrawMarksTransferGetter input);
}
