package in.ac.dei.edrp.pms.bugzillaConfig;
 
import org.apache.struts.action.ActionForm;

/**
 * @author Anil Kumar Tiwari
 * 
**/
 
public class DispatchActionForm extends ActionForm
{
  private String parameter =" "; 
  
  public String getParameter() {
        return parameter;
    }

  public void setParameter(String parameter) {
        this.parameter=parameter;
   }
 
} 