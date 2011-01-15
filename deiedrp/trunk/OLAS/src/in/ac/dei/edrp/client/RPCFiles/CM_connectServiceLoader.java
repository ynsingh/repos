package in.ac.dei.edrp.client.RPCFiles;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


public class CM_connectServiceLoader {
    public static CM_connectAsync getService() {
        CM_connectAsync calService = (CM_connectAsync) GWT.create(CM_connect.class);
        ServiceDefTarget target = (ServiceDefTarget) calService;
        String moduleRelativeURL = GWT.getModuleBaseURL() + "hello1";
        target.setServiceEntryPoint(moduleRelativeURL);

        return calService;
    }
}
