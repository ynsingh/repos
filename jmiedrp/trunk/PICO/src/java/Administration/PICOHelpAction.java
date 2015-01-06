package Administration;

import com.opensymphony.xwork2.ActionContext;
import utils.DevelopmentSupport;

public class PICOHelpAction extends DevelopmentSupport {

    private String message;

    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }

    public String execute() throws Exception {
      try {
      return SUCCESS;
      }
      catch (Exception e) {
      return ERROR;
      }
   }

}


