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
import java.beans.DesignMode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.VisitingCardDao;
import org.IGNOU.ePortfolio.Model.Vistingcard;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class MyEvisitingCardAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private String fid;
    private VisitingCardDao vcDao = new VisitingCardDao();
    private List<Vistingcard> vcList;
    private Map session = ActionContext.getContext().getSession();
    private String name, desig, compname, emailid, mobileno, officeno, faxno, website, owebsite;

    public String ShowEcard() throws IOException {
        vcList = vcDao.VisitingCardDetailByUserId(user_id);
        {
            if (vcList.iterator().next().getDisplayName() != null) {
                setName(vcList.iterator().next().getDisplayName());
            }
            if (vcList.iterator().next().getDesignation() != null) {
                setDesig(vcList.iterator().next().getDesignation());
            }
            if (vcList.iterator().next().getCompany() != null) {
                setCompname(vcList.iterator().next().getCompany());
            }
            if (vcList.iterator().next().getEmail() != null) {
                setEmailid("Email-Id: " + vcList.iterator().next().getEmail());
            }
            if (vcList.iterator().next().getMobile() != null) {
                setMobileno("PH.: " + vcList.iterator().next().getMobile().toString() + "(Mob.)");
            }
            if (vcList.iterator().next().getOfficePh() != null) {
                setOfficeno(vcList.iterator().next().getOfficePh().toString() + "(Off.)");
            }
            if (vcList.iterator().next().getFax() != null) {
                setFaxno("Fax: " + vcList.iterator().next().getFax().toString());
            }
            if (vcList.iterator().next().getWebsitePer() != null) {
                setWebsite("Web: " + vcList.iterator().next().getWebsitePer());
            }
            if (vcList.iterator().next().getWebsiteOff() != null) {
                setOwebsite(vcList.iterator().next().getWebsiteOff());
            }

            // Getting the bytes of created image
            fid = session.get("appPath").toString() + "samples/" + getFid();
            byte[] b = TextonImage(fid, getName(), getDesig(), getCompname(), getEmailid(), getOfficeno(), getMobileno(), getFaxno(), getOwebsite(), getWebsite(), new Point(50, 60));
            String efilename = user_id.substring(0, 4) + "Ecard.png";
            session.put("Ecard", efilename);
            String outPutFile = session.get("appPath").toString() + efilename;
            FileOutputStream fos = new FileOutputStream(outPutFile);
            fos.write(b);
            fos.close();
            return SUCCESS;
        }
    }

    public static byte[] TextonImage(String imagePath, String empname, String desig, String compname, String emailid, String officeno, String mobileno, String faxno, String owebsite, String website, Point textPosition) throws IOException {

        BufferedImage im = ImageIO.read(new File(imagePath));
        Graphics2D g2 = im.createGraphics();
        // Setting of font
        Font fontname = new Font("Times New Roman", Font.PLAIN, 34);
        Font fontdesig = new Font("serif", Font.ITALIC, 18);
        Font fontother = new Font("Times New Roman", Font.BOLD, 14);

        g2.setFont(fontname);
        g2.setFont(fontdesig);
        g2.setFont(fontother);
        //Attribute Variable Declearation
        AttributedString uName = null, designation = null, cname = null, email = null, offno = null, mobile = null, fax = null, oweb = null, web = null;

        if (empname != null && !empname.equals(" ") && empname.length() > 0) {
            uName = new AttributedString(empname);
            //setting the font for particular data
            uName.addAttribute(TextAttribute.FONT, fontname, 0, empname.length());
            //setting of color of text to be printed
            uName.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, empname.length());
            // Positioning the text on visiting Card
            g2.drawString(uName.getIterator(), textPosition.x, textPosition.y);
        }
        if (desig != null && !desig.equals(" ") && desig.length() > 0) {
            designation = new AttributedString(desig);
            designation.addAttribute(TextAttribute.FONT, fontdesig, 0, desig.length());
            designation.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, desig.length());
            g2.drawString(designation.getIterator(), textPosition.x, textPosition.y + 23);
        }
        if (compname != null && !compname.equals(" ") && compname.length() > 0) {
            cname = new AttributedString(compname);
            cname.addAttribute(TextAttribute.FONT, fontdesig, 0, compname.length());
            cname.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, compname.length());
            g2.drawString(cname.getIterator(), textPosition.x, textPosition.y + 46);
        }
        if (emailid != null && !emailid.equals(" ") && emailid.length() > 0) {
            email = new AttributedString(emailid);
            email.addAttribute(TextAttribute.FONT, fontother, 0, emailid.length());
            email.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, emailid.length());
            g2.drawString(email.getIterator(), textPosition.x, textPosition.y + 105);
        }
        if (officeno != null && !officeno.equals(" ") && officeno.length() > 0) {
            offno = new AttributedString(officeno);
            offno.addAttribute(TextAttribute.FONT, fontother, 0, officeno.length());
            offno.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, officeno.length());
            g2.drawString(offno.getIterator(), textPosition.x + 150, textPosition.y + 124);
        }
        if (mobileno != null && !mobileno.equals(" ") && mobileno.length() > 0) {
            mobile = new AttributedString(mobileno);
            mobile.addAttribute(TextAttribute.FONT, fontother, 0, mobileno.length());
            mobile.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, mobileno.length());
            g2.drawString(mobile.getIterator(), textPosition.x, textPosition.y + 124);
        }
        if (faxno != null && !faxno.equals(" ") && faxno.length() > 0) {
            fax = new AttributedString(faxno);
            fax.addAttribute(TextAttribute.FONT, fontother, 0, faxno.length());
            fax.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, faxno.length());
            g2.drawString(fax.getIterator(), textPosition.x, textPosition.y + 143);
        }
        if (website != null && !website.equals(" ") && website.length() > 0) {
            web = new AttributedString(website);
            web.addAttribute(TextAttribute.FONT, fontother, 0, website.length());
            web.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, website.length());
            g2.drawString(web.getIterator(), textPosition.x, textPosition.y + 163);
        }
        if (owebsite != null && !owebsite.equals(" ") && owebsite.length() > 0) {
            oweb = new AttributedString(owebsite);
            oweb.addAttribute(TextAttribute.FONT, fontother, 0, owebsite.length());
            oweb.addAttribute(TextAttribute.FOREGROUND, Color.white, 0, owebsite.length());
            g2.drawString(oweb.getIterator(), textPosition.x + 40, textPosition.y + 183);
        }

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desig
     */
    public String getDesig() {
        return desig;
    }

    /**
     * @param desig the desig to set
     */
    public void setDesig(String desig) {
        this.desig = desig;
    }

    /**
     * @return the compname
     */
    public String getCompname() {
        return compname;
    }

    /**
     * @param compname the compname to set
     */
    public void setCompname(String compname) {
        this.compname = compname;
    }

    /**
     * @return the emailid
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * @param emailid the emailid to set
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * @return the mobileno
     */
    public String getMobileno() {
        return mobileno;
    }

    /**
     * @param mobileno the mobileno to set
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * @return the officeno
     */
    public String getOfficeno() {
        return officeno;
    }

    /**
     * @param officeno the officeno to set
     */
    public void setOfficeno(String officeno) {
        this.officeno = officeno;
    }

    /**
     * @return the faxno
     */
    public String getFaxno() {
        return faxno;
    }

    /**
     * @param faxno the faxno to set
     */
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the owebsite
     */
    public String getOwebsite() {
        return owebsite;
    }

    /**
     * @param owebsite the owebsite to set
     */
    public void setOwebsite(String owebsite) {
        this.owebsite = owebsite;
    }
}
