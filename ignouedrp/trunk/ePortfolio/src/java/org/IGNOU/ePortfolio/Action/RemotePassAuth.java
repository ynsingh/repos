/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.DAO.LoginDao;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;

/**
 *
 * @author IGNOU Team
 * @since 22-05-2012
 * @version 3
 */
public class RemotePassAuth extends ActionSupport {

    private Map session = ActionContext.getContext().getSession();
    private String sourceid = ReadPropertyFile("iitkScourceId");
    private String skey = ReadPropertyFile("iitkKey");
    private String serverURL = ReadPropertyFile("serverURL");
    private String reqURL = ReadPropertyFile("reqURL");
    private String encd, rand, hash;
    private String hDir, pPath, fPath = ReadPropertyFile("remoteUserPath"), line, enURL, hashcode, keyedHash;
    private String email;
    private String sess;
    private List<User> userList;
    private UserProgrammeDao updao = new UserProgrammeDao();
    private String role;
    private String uName;
    private String uValue;
    /*USer Information for Store in Local DB*/
    private String fname;
    private String lname;
    private Date regTime;
    private String univRegNo;
    private String programe;
    private Integer instituteId;
    private Integer programmeId;
    private LoginDao lDao = new LoginDao();
    private String msg;

    public RemotePassAuth() {
    }

    public String RemoteValueVarification() throws Exception {
        /*Get Security Key*/

//        hDir = System.getProperty("user.home");
//        String osnme = System.getProperty("os.name");
//        if (osnme.startsWith("Win")) {
//            pPath = hDir + "\\remote_auth\\brihaspati3-remote-access.properties";
//        } else {
//            pPath = hDir + "/remote_auth/brihaspati3-remote-access.properties";
//        }
        //pPath = hDir + "/Remote_auth/brihaspati3-remote-access.properties";
        // fPath = "C:/Users/Vinay/Remote_auth/remote-user.txt";
//        line = ReadNWriteInTxt.readLin(pPath, sourceid);
//        skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
//        serverURL = org.apache.commons.lang.StringUtils.substringAfterLast(line, ";");

        /*Decript Received Data*/

        enURL = EncrptDecrpt.decrypt(encd, sourceid);

        /*Get Email ID and Session*/

        email = StringUtils.substringBetween(enURL, "email=", "&");
        sess = StringUtils.substringAfter(enURL, "sess=");
        hashcode = EncrptDecrpt.keyedHash(email, rand, skey);
        /*Match Hashcode with KeyedHash*/
        if (hashcode.equals(hash)) {
            boolean verified = ReadNWriteInTxt.readF(fPath, email + ";" + sess);
            if (verified) {
                uName = email;
                session.put("user_id", email);
                session.put("role", "student");
                session.put("requri", reqURL);
                session.put("uName", uName);
                return SUCCESS;
            } else {
                msg = "Varify Email and Session from remote_user.txt file Failed";
                return INPUT;
            }
        } else {
            // System.out.println("Hash Code Not Matched.");
            msg = "Hash Code Not Matched with Hash." + hashcode + " encd:-" + encd + "rand:- " + rand + "Hash:-" + hash + "\n Home Dir :=" + hDir + "\n pPath := " + pPath;
            return INPUT;

        }
        //return SUCCESS;
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
    public void setSession(Map session) {
        this.session = session;
    }

    /**
     * @return the sourceid
     */
    public String getSourceid() {
        return sourceid;
    }

    /**
     * @param sourceid the sourceid to set
     */
    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    /**
     * @return the encd
     */
    public String getEncd() {
        return encd;
    }

    /**
     * @param encd the encd to set
     */
    public void setEncd(String encd) {
        this.encd = encd;
    }

    /**
     * @return the rand
     */
    public String getRand() {
        return rand;
    }

    /**
     * @param rand the rand to set
     */
    public void setRand(String rand) {
        this.rand = rand;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the hDir
     */
    public String gethDir() {
        return hDir;
    }

    /**
     * @param hDir the hDir to set
     */
    public void sethDir(String hDir) {
        this.hDir = hDir;
    }

    /**
     * @return the pPath
     */
    public String getpPath() {
        return pPath;
    }

    /**
     * @param pPath the pPath to set
     */
    public void setpPath(String pPath) {
        this.pPath = pPath;
    }

    /**
     * @return the fPath
     */
    public String getfPath() {
        return fPath;
    }

    /**
     * @param fPath the fPath to set
     */
    public void setfPath(String fPath) {
        this.fPath = fPath;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return the skey
     */
    public String getSkey() {
        return skey;
    }

    /**
     * @param skey the skey to set
     */
    public void setSkey(String skey) {
        this.skey = skey;
    }

    /**
     * @return the serverURL
     */
    public String getServerURL() {
        return serverURL;
    }

    /**
     * @param serverURL the serverURL to set
     */
    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * @return the enURL
     */
    public String getEnURL() {
        return enURL;
    }

    /**
     * @param enURL the enURL to set
     */
    public void setEnURL(String enURL) {
        this.enURL = enURL;
    }

    /**
     * @return the hashcode
     */
    public String getHashcode() {
        return hashcode;
    }

    /**
     * @param hashcode the hashcode to set
     */
    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sess
     */
    public String getSess() {
        return sess;
    }

    /**
     * @param sess the sess to set
     */
    public void setSess(String sess) {
        this.sess = sess;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the updao
     */
    public UserProgrammeDao getUpdao() {
        return updao;
    }

    /**
     * @param updao the updao to set
     */
    public void setUpdao(UserProgrammeDao updao) {
        this.updao = updao;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the keyedHash
     */
    public String getKeyedHash() {
        return keyedHash;
    }

    /**
     * @param keyedHash the keyedHash to set
     */
    public void setKeyedHash(String keyedHash) {
        this.keyedHash = keyedHash;
    }

    /**
     * @return the uValue
     */
    public String getuValue() {
        return uValue;
    }

    /**
     * @param uValue the uValue to set
     */
    public void setuValue(String uValue) {
        this.uValue = uValue;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the regTime
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime the regTime to set
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * @return the univRegNo
     */
    public String getUnivRegNo() {
        return univRegNo;
    }

    /**
     * @param univRegNo the univRegNo to set
     */
    public void setUnivRegNo(String univRegNo) {
        this.univRegNo = univRegNo;
    }

    /**
     * @return the programe
     */
    public String getPrograme() {
        return programe;
    }

    /**
     * @param programe the programe to set
     */
    public void setPrograme(String programe) {
        this.programe = programe;
    }

    /**
     * @return the instituteId
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    /**
     * @return the programmeId
     */
    public Integer getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the lDao
     */
    public LoginDao getlDao() {
        return lDao;
    }

    /**
     * @param lDao the lDao to set
     */
    public void setlDao(LoginDao lDao) {
        this.lDao = lDao;
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
     * @return the reqURL
     */
    public String getReqURL() {
        return reqURL;
    }

    /**
     * @param reqURL the reqURL to set
     */
    public void setReqURL(String reqURL) {
        this.reqURL = reqURL;
    }
}
