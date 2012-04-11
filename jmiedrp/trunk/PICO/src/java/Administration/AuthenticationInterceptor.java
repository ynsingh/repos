/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

/*
 import utils.DevelopmentSupport;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor extends DevelopmentSupport implements Interceptor {
	public void destroy()
	{
	}
	public void init()
	{
	}
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{

		if (getSession().getAttribute("username") == null)
                    return "loginAction";
		else		
                    return actionInvocation.invoke();
	}
}
 *
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kazim
 */
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

public class AuthenticationInterceptor implements Interceptor
{
	public void destroy()
	{
	}
	public void init()
	{
	}
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		Map session = actionInvocation.getInvocationContext().getSession();

		if (session.get("username") == null)
		{
			return "loginAction";
		}
		else
		{
			return actionInvocation.invoke();
		}
	}
}