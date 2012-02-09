/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp01
 */
public class ListRoomsActionForm extends org.apache.struts.action.ActionForm {
    
    private String nickname;
    private String position;
     private String button;
       private String rd;
       private String rn;

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }
    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

   
   

    /**
     * @return
     */
   

    /**
     *
     */
    public ListRoomsActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
