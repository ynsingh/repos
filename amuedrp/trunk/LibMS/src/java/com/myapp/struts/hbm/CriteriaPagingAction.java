package com.myapp.struts.hbm;
import org.hibernate.Criteria;

// ACTION TO EXTRACT RECORD FROM LIST IN SELECTED PAGE SIZE DEFALUT 100
import java.util.List;
public class CriteriaPagingAction
{

  private List results;
  private int pageSize;
  private int page;

  public CriteriaPagingAction(Criteria query, int page, int pageSize)
  {
    this.page = page;
    this.pageSize = pageSize;
    
    
    if((page*pageSize)==pageSize)
    {
        query=query.setFirstResult((page*pageSize)-1);
        query.setMaxResults(pageSize);
        System.out.println("here"+query.list().size());
    }
    else
    {
        query=query.setFirstResult((page*pageSize));
        query.setMaxResults(pageSize);
    }
    results = query.list();
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
      if(page==1)
      {
        System.out.println(results.size());
        return isNextPage() ? results : results;
      }
      else
      {
        return isNextPage() ? results.subList(0, pageSize) : results;
      }
  }
}
