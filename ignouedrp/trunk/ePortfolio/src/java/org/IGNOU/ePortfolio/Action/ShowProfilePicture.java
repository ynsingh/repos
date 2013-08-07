/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.IGNOU.ePortfolio.DAO.UserListDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author IGNOU Team
 */
public class ShowProfilePicture implements SessionAware {

    private String user_id = new UserSession().getUserInSession();
    private UserListDao ppDao = new UserListDao();
    private static List<User> spp;
    private String filtype;
    private Map session = ActionContext.getContext().getSession();

    public String ProPict() throws IOException {
        spp = ppDao.UserListByUserId(user_id);
        filtype = spp.iterator().next().getFiletype();
        if (filtype == null) {
            String filepath = "images/user.png";
            session.put("picname", filepath);
            return filepath;
        } else {
            byte bp[] = spp.iterator().next().getPicture();
            String ftype = "gif";
            final BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(bp));
            String aPath = session.get("appPath").toString();
            String picname = user_id.substring(0, 4) + "." + ftype;
            File f = new File(aPath + picname);
            session.put("picname", picname);
            ImageIO.write(bImage, ftype, f);
            String filen = user_id.substring(0, 4) + "." + ftype;
            String fipath = "images/" + filen;
            return fipath;
        }
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    @Override
    public void setSession(Map session) {
        this.session = session;
    }
}
