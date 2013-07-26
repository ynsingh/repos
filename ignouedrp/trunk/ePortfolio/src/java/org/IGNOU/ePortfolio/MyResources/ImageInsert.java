/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyResources;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ImageInfoDAO;
import org.IGNOU.ePortfolio.Model.Userdocs;

/**
 *
 * @author IGNOU Team
 * * @version 1.0 on 19 aug 2011
 */
public class ImageInsert extends ActionSupport implements ModelDriven<Userdocs> {

    private String user_id = new UserSession().getUserInSession();
    private ImageInfoDAO obInfoDAO = new ImageInfoDAO();
    private Userdocs obModel = new Userdocs();
    private String filepath = ReadPropertyFile("Filepath") + "/" + user_id + "/";
    private Calendar c_Date = Calendar.getInstance();
    private SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    private String filedate = f.format(c_Date.getTime());
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public String dataInsert() throws IOException {
        obInfoDAO.saveInfo(obModel);
        System.out.println("inserted");
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Userdocs getModel() {

        obModel.setUser_id(user_id);
        obModel.setFilepath(filepath);
        obModel.setFiledate(filedate);
        return obModel;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return the filedate
     */
    public String getFiledate() {
        return filedate;
    }

    /**
     * @param filedate the filedate to set
     */
    public void setFiledate(String filedate) {
        this.filedate = filedate;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}
