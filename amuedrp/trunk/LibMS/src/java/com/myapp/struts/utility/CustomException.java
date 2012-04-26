package com.myapp.struts.utility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
public class CustomException extends ExceptionHandler{
  private static final Logger logger =  Logger.getLogger(CustomException.class);
  @Override
  public ActionForward execute(Exception ex, ExceptionConfig ae,
	ActionMapping mapping, ActionForm formInstance,
	HttpServletRequest request, HttpServletResponse response)
	throws ServletException {

      String error=ex.toString()+" "+ae.toString()+" "+mapping.toString()+" "+formInstance.toString()+" "+request.toString()+" "+response.toString();
      logger.error(error);
      return super.execute(ex, ae, mapping, formInstance, request, response);
  }
}