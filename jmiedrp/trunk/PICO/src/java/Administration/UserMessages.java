/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DevelopmentSupport;

import pojo.hibernate.UserMessage;
import pojo.hibernate.UserMessageDAO;

public class UserMessages extends DevelopmentSupport{

    private UserMessage userMessage = new UserMessage();
    private List<UserMessage> userMessageList = new ArrayList<UserMessage>();
    private UserMessageDAO userMessageDao = new UserMessageDAO();

    private String  message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserMessage getuserMessage() {
        return this.userMessage;
    }

    public void setuserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public List<UserMessage> getuserMessageList() {
        return userMessageList;
    }

    public void setuserMessageList(List<UserMessage> userMessageList) {
        this.userMessageList = userMessageList;
    }

    @Override

      public String execute() throws Exception {
        try {
            return SUCCESS;
            }
       catch (Exception e)
       {
           message = "Exception in UserMessageAxn -> Execute" + e.getMessage();
           return ERROR;
       }
       }


public String showJobs() throws Exception {
    try {
        userMessageList = userMessageDao.findByUserId(Integer.parseInt(getSession().getAttribute("userid").toString()));


        return SUCCESS;
     }
        catch (Exception e)
            {
             message = "Exception in showJobsAxn -> UserMessageAxn" + e.getMessage();
             return ERROR;
            }
        }

}
