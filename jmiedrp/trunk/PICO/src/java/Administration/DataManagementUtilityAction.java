/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author wml3
 */
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import pojo.hibernate.*;
import utils.DevelopmentSupport;

public class DataManagementUtilityAction extends DevelopmentSupport {

    private String message;
    private String myPassword;
    private String mySQLTableName;
    private String hibernateClassName;
    private String sQLToBeExecuted;
    private ErpmPurchaseinvoiceMaster pibm;
    private List<ErpmPurchaseinvoiceMaster> pibmList = new ArrayList<ErpmPurchaseinvoiceMaster>();
    private ErpmPurchaseinvoiceMasterDAO pibmDao = new ErpmPurchaseinvoiceMasterDAO();
    private List<String> queryList = new ArrayList<String>();
    private ArrayList<String[]> result = new ArrayList<String[]>();

    static String dataSourceURL=null;

    public String getMyPassword() {
        return myPassword;
    }

    public void setMyPassword(String myPassword) {
        this.myPassword = myPassword;
    }

    public ArrayList<String[]> getResult() {
        return result;
    }

    public void setResult(ArrayList<String[]> result) {
        this.result = result;
    }

    public List<ErpmPurchaseinvoiceMaster> getPibmList() {
        return pibmList;
    }

    public void setPibmList(List<ErpmPurchaseinvoiceMaster> pibmList) {
        this.pibmList = pibmList;
    }

    public List<String> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<String> queryList) {
        this.queryList = queryList;
    }

    public String getHibernateClassName() {
        return hibernateClassName;
    }

    public void setHibernateClassName(String hibernateClassName) {
        this.hibernateClassName = hibernateClassName;
    }

    public String getMySQLTableName() {
        return mySQLTableName;
    }

    public void setMySQLTableName(String mySQLTableName) {
        this.mySQLTableName = mySQLTableName;
    }

    public String getsQLToBeExecuted() {
        return sQLToBeExecuted;
    }

    public void setsQLToBeExecuted(String sQLToBeExecuted) {
        this.sQLToBeExecuted = sQLToBeExecuted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String execute() throws Exception {
        try {

//            prepare_lovs();

            return SUCCESS;
        } catch (Exception e) {
            message = message + e.getMessage();
            return ERROR;
        }
    }

//     @SkipValidation
    public String ExecuteSqlQuery() throws Exception {
        try {

            if (!getsQLToBeExecuted().isEmpty()) {
//                Locale locale = ActionContext.getContext().getLocale();
//                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

                Statement st = conn.createStatement();

                if (getsQLToBeExecuted().toUpperCase().contains("SELECT") || getsQLToBeExecuted().toUpperCase().contains("DESC")) {

                    ResultSet rs = st.executeQuery(getsQLToBeExecuted());

                    int columnCount = rs.getMetaData().getColumnCount();

                    while (rs.next()) {
                        String[] row = new String[columnCount];

                        for (int i = 0; i < columnCount; i++) {

                            row[i] = rs.getString(i + 1);

                        }

                        result.add(row);

                    }
                    message = result.size() + " Record(s) found";

                    rs.close();

                } else {
                    st.executeUpdate(getsQLToBeExecuted());
                    int i = st.getResultSetType();
                    
                    message = st.getUpdateCount() + " Record(s) affected";
                }
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ExecuteSql  method Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Clear() throws Exception {
        try {
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> Clear  method Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SignIn() throws Exception {
        try {

            String paswrd = "saeed";
            if (getMyPassword().equals(paswrd)) {
                return SUCCESS;
            } else {
                message = "Wrong Password !";
            }
            return "SUCCESS1";
        } catch (Exception e) {
            message = "Exception in -> SignIn  method Cause is: " + e.getCause();
            return ERROR;
        }
    }
}
