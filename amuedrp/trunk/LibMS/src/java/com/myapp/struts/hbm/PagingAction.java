package com.myapp.struts.hbm;
import org.hibernate.Query;

// ACTION TO EXTRACT RECORD FROM LIST IN SELECTED PAGE SIZE DEFALUT 100
import java.util.List;
public class PagingAction
{

  private List results;
  private int pageSize;
  private int page;

  public PagingAction(Query query, int page, int pageSize)
  {
    this.page = page;
    this.pageSize = pageSize;
    results = query.setFirstResult(page * pageSize).setMaxResults(pageSize + 1).list();
  }
  public boolean isNextPage()
  {
    return results.size() > pageSize;
  }
  public boolean isPreviousPage()
  {
    return page > 0;
  }
  public List getList()
  {
    return isNextPage() ?
    results.subList(0, pageSize) : results;
  }
}
