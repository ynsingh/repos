/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.VisitingCardDao;
import org.IGNOU.ePortfolio.Model.Vistingcard;

/**
 *
 * @author IGNOU Team
 */
public class MyEvisitingCardAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private String fid;
    private VisitingCardDao vcDao = new VisitingCardDao();
    private List<Vistingcard> vcList;
    private Map session = ActionContext.getContext().getSession();

    public String ShowEcard() throws IOException {
        vcList = vcDao.VisitingCardDetailByUserId(user_id);


        {
            String name = vcList.iterator().next().getDisplayName();
            String desig = vcList.iterator().next().getDesignation();

            String compname = vcList.iterator().next().getCompany();
            String emailid = "Email-Id: " + vcList.iterator().next().getEmail();
            String phoneNo = "Phone: " + vcList.iterator().next().getMobile().toString() + "(Mob.), " + vcList.iterator().next().getOfficePh().toString() + "(Off.)";
            String faxno = "Fax: " + vcList.iterator().next().getFax().toString();
            String website = "Website: " + vcList.iterator().next().getWebsitePer() + ", " + vcList.iterator().next().getWebsiteOff();


            // Getting the bytes of created image
            fid = session.get("appPath").toString() + "samples/" + getFid();
            byte[] b = TextonImage(fid, name, desig, compname, emailid, phoneNo, faxno, website, new Point(50, 60));
            String efilename = user_id.substring(0, 4) + "Ecard.png";
            session.put("Ecard", efilename);
            String outPutFile = session.get("appPath").toString() + efilename;
            FileOutputStream fos = new FileOutputStream(outPutFile);
            fos.write(b);
            fos.close();
            return SUCCESS;
        }
    }

    public static byte[] TextonImage(String imagePath, String empname, String desig, String compname, String emailid, String mobileno, String faxno, String website, Point textPosition) throws IOException {
        BufferedImage im = ImageIO.read(new File(imagePath));
        Graphics2D g2 = im.createGraphics();
        // Setting of font
        Font fontname = new Font("Times New Roman", Font.PLAIN, 34);
        Font fontdesig = new Font("serif", Font.ITALIC, 18);
        Font fontother = new Font("Times New Roman", Font.BOLD, 14);

        g2.setFont(fontname);
        g2.setFont(fontdesig);
        g2.setFont(fontother);

        AttributedString name = new AttributedString(empname);
        AttributedString designation = new AttributedString(desig);
        AttributedString cname = new AttributedString(compname);

        AttributedString email = new AttributedString(emailid);
        AttributedString mobile = new AttributedString(mobileno);
        AttributedString fax = new AttributedString(faxno);
        AttributedString web = new AttributedString(website);


        //setting the font for particular data
        name.addAttribute(TextAttribute.FONT, fontname, 0, empname.length());
        designation.addAttribute(TextAttribute.FONT, fontdesig, 0, desig.length());
        cname.addAttribute(TextAttribute.FONT, fontdesig, 0, compname.length());


        email.addAttribute(TextAttribute.FONT, fontother, 0, emailid.length());
        mobile.addAttribute(TextAttribute.FONT, fontother, 0, mobileno.length());
        fax.addAttribute(TextAttribute.FONT, fontother, 0, faxno.length());
        web.addAttribute(TextAttribute.FONT, fontother, 0, website.length());


        //setting of color of text to be printed
        name.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, empname.length());
        designation.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, desig.length());
        cname.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, compname.length());

        email.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, emailid.length());
        mobile.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, mobileno.length());
        fax.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, faxno.length());
        web.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, website.length());


        // Positioning the text on visiting Card
        g2.drawString(name.getIterator(), textPosition.x, textPosition.y);
        g2.drawString(designation.getIterator(), textPosition.x, textPosition.y + 23);
        g2.drawString(cname.getIterator(), textPosition.x, textPosition.y + 46);

        g2.drawString(email.getIterator(), textPosition.x, textPosition.y + 125);
        g2.drawString(mobile.getIterator(), textPosition.x, textPosition.y + 144);
        g2.drawString(fax.getIterator(), textPosition.x, textPosition.y + 163);
        g2.drawString(web.getIterator(), textPosition.x, textPosition.y + 183);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(im, "png", os);
        return os.toByteArray();
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the vcDao
     */
    public VisitingCardDao getVcDao() {
        return vcDao;
    }

    /**
     * @param vcDao the vcDao to set
     */
    public void setVcDao(VisitingCardDao vcDao) {
        this.vcDao = vcDao;
    }

    /**
     * @return the vcList
     */
    public List<Vistingcard> getVcList() {
        return vcList;
    }

    /**
     * @param vcList the vcList to set
     */
    public void setVcList(List<Vistingcard> vcList) {
        this.vcList = vcList;
    }
}
