package com.myapp.struts.Voter;
import org.hibernate.Query;

import java.util.List;

public class PagingAction {

  private List results;

  private int pageSize;

  private int page;

  public PagingAction(Query query, int page, int pageSize) {

    this.page = page;

    this.pageSize = pageSize;

    results = query.setFirstResult(page * pageSize).setMaxResults(pageSize + 1).list();

  }

  public boolean isNextPage() {

    return results.size() > pageSize;

  }

  public boolean isPreviousPage() {

    return page > 0;

  }

  public List getList() {

    return isNextPage() ?

        results.subList(0, pageSize) : results;

  }

}
