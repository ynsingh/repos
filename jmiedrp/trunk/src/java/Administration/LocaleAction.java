package Administration;
import utils.DevelopmentSupport;
import java.util.Locale;
//import com.opensymphony.xwork2.Action;
//import com.opensymphony.xwork2.ActionSupport;

public class LocaleAction extends DevelopmentSupport{

	//business logic
@Override
    public String execute() {

		return "SUCCESS";

	}


public String english() throws Exception {

//HttpSession session = request.getSession();

getSession().setAttribute("org.apache.struts.action.LOCALE", Locale.ENGLISH);

return SUCCESS;

}

public String France () throws Exception {

//HttpSession session = request.getSession();

getSession().setAttribute("org.apache.struts.action.LOCALE", Locale.FRANCE);

return SUCCESS;
}
}
