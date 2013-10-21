/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author afreen
 */
import utils.HibernateUtil;
import utils.Page;
import org.hibernate.Session;

public class PaginationHibDao {
/**
 * getPage() based on the table name and the page number it receives, extracts the appropriate records belonging to the page number and returns the same in the form of Page object.
 * It retrieves the records by calling the constructor of the Page class.
 * This constructor is passed the query, the page number and the maximum number of records on that page.
 * This function allows retrieving the page when a user clicks the page number from JSP.
 * @param page The page to navigate to.
 * @param tableName The table name.
 * @return The page object.
 */
    public Page getPage(int page, String tableName) {
       // Session session = HibernateUtil.getSession().openSession();   
        Session session = HibernateUtil.getSession();

        try {
            String query = "FROM " + tableName;
            session.beginTransaction();
            return new Page(session.createQuery(query), page, 10);
        }
        catch(Exception e) {
            System.out.print("Error while fetching" + e);
            return null;
        }
        finally {
            session.close();
        }
    }

/**
 * getTotalPages() when invoked retrieves the total number of pages based on the number of records in the table and the maximum number of records on a page.
 * The value that this function returns is used to form the pagination links in JSP.
 * @param tableName The table name.
 * @return The number of pages.
 */
    public int getTotalPages(String tableName) {
       // Session session = HibernateUtil.getSession().openSession();  //.getSession();
        Session session = HibernateUtil.getSession();
        
        try {
            session.beginTransaction();
            String total = session.createSQLQuery("SELECT COUNT(*) AS Total FROM " + tableName).addScalar("Total").uniqueResult().toString();
            return (int) Math.ceil(Double.parseDouble(total)/10);
        }
        catch(Exception e) {
            System.out.print("Error while fetching" + e);
            return 0;
        }
        finally {
            session.close();
        }
    }
}
