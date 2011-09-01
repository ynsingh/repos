/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author afreen
 */
import java.util.List;
import org.hibernate.Query;

public class Page {
    private List results;
    private int pageSize;
    private int page;

    public Page(Query query, int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        results = query.setFirstResult(page * pageSize).setMaxResults(pageSize).list();
    }

    public boolean isNextPage() {
        return results.size() > pageSize;
    }

    public boolean isPreviousPage() {
        return page > 0;
    }

    public List getList() {
        return isNextPage() ? results.subList(0, pageSize-1) : results;
    }
}