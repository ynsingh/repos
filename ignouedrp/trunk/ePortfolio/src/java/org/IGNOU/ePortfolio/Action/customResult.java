/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Amit
 */
public class customResult implements Result {

    final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(ActionInvocation ai) throws Exception {
        logger.warn(ai + "  " + new Exception().getStackTrace()[0].getLineNumber());
        ImageToByteAction action = (ImageToByteAction) ai.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();
        logger.warn(response + "  " + new Exception().getStackTrace()[0].getLineNumber());
        response.setContentType(action.getCustomContentType());
        response.getOutputStream().write(action.getCustomImageInBytes());
        response.getOutputStream().flush();

    }
}
