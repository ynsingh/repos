package org.iitk.brihaspati.modules.screens;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

public class RemoteAuthenticate extends VelocityScreen
{
        public void doBuildTemplate(RunData data, Context context)
        {
                try{
                        ParameterParser pp=data.getParameters();
                        String lang=pp.getString("lang","english");
                        context.put("lang",lang);
                        String stat=pp.getString("status");
                        context.put("status",stat);
                        String msg=pp.getString("msg","");
                        data.setMessage(msg);
                        ErrorDumpUtil.ErrorLog("message in RemoteLogin screen=="+msg);
                }
                catch(Exception ex)
                {
                        data.setMessage("The error in RemoteLogin Screen !! "+ex);
                }

        }
}

